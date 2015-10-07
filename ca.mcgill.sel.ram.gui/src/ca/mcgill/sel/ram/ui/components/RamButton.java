package ca.mcgill.sel.ram.ui.components;

import java.util.ArrayList;
import java.util.List;

import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.AbstractComponentProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapProcessor;
import org.mt4j.util.MTColor;
import org.mt4j.util.font.IFont;
import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.events.listeners.ActionListener;
import ca.mcgill.sel.ram.ui.events.listeners.ActionListener.ActionEvent;
import ca.mcgill.sel.ram.ui.events.listeners.ITapListener;
import ca.mcgill.sel.ram.ui.layouts.Layout.HorizontalAlignment;
import ca.mcgill.sel.ram.ui.layouts.Layout.VerticalAlignment;
import ca.mcgill.sel.ram.ui.layouts.VerticalLayout;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.utils.Constants;
import ca.mcgill.sel.ram.ui.utils.Fonts;
import ca.mcgill.sel.ram.ui.views.handler.BaseHandler;

/**
 * RamButton is a simple class that displays a text or image. Interested parties have to register themselves as an
 * {@link ActionListener} in order to receive a notification when the button was pressed. Each button has an action
 * command which makes it possible to distinguish between different buttons and to use the same {@link ActionListener}.
 * 
 * @author vbonnet
 * @author mschoettle
 * @see java.awt.event.ActionListener ActionListener of AWT
 */
public class RamButton extends RamRoundedRectangleComponent {

    /**
     * Button tap handler class.
     */
    private class ButtonHandler extends BaseHandler implements ITapListener {

        @Override
        public boolean processTapEvent(TapEvent tapEvent) {
            // only proceed if button is enabled (although this seems to be taken care of by MT4j already)
            if (tapEvent.isTapped() && isEnabled()) {
                if (!listeners.isEmpty()) {
                    executeAction();
                }
            }
            return true;
        }
    }

    private RamTextComponent nameField;

    private List<ActionListener> listeners;
    private String actionCommand;
    private ButtonHandler buttonHandler;
    private boolean usesImage;
    private MTColor backgroundColor;
    private MTColor disabledBackgroundColor;
    private IFont font;
    private IFont disabledFont;
    private RamImageComponent icon;

    private int arcRadiusTextFormat;
    private int arcRadiusIconFormat;
    private float sizeIconFormatWidth;
    private float sizeIconFormatHeight;

    /**
     * Creates a new button with the given arc radius.
     * 
     * @param arcRadius
     *            The radius of the corners.
     */
    private RamButton(int arcRadius) {
        super(arcRadius);

        setNoStroke(true);
        setNoFill(false);
        usesImage = false;

        backgroundColor = Colors.COLOR_BUTTON_ENABLED;
        disabledBackgroundColor = Colors.COLOR_BUTTON_DISABLED;
        updateButtonGUI();

        VerticalLayout layout = new VerticalLayout(HorizontalAlignment.CENTER);
        layout.setVerticalAlignment(VerticalAlignment.MIDDLE);
        setLayout(layout);
        setAutoMaximizes(false);

        listeners = new ArrayList<ActionListener>();
        buttonHandler = new ButtonHandler();
    }

    /**
     * Creates a new button with the given image representation.
     * 
     * @param image
     *            the image to be shown
     */
    public RamButton(RamImageComponent image) {
        this(image, 10);
    }

    /**
     * Creates a new button with the given image representation and arc radius.
     * 
     * @param image
     *            the image to be shown
     * @param arcRadius
     *            The radius of the corners.
     */
    public RamButton(RamImageComponent image, int arcRadius) {
        this(arcRadius);
        setBuffers(0);
        usesImage = true;

        addChild(image);
        setNoFill(true);
    }

    /**
     * Creates a new RamButton.
     * 
     * @param name
     *            The name to be displayed by the button.
     * @param clickAction
     */
    public RamButton(String name) {
        this(name, 10);
    }

    /**
     * Creates a new RamButton.
     * 
     * @param name
     *            The name to be displayed by the button.
     * @param arcRadius
     *            The radius of the corners.
     * @param clickAction
     */
    public RamButton(String name, int arcRadius) {
        this(arcRadius);

        font = Fonts.DEFAULT_FONT_MENU;
        disabledFont = Fonts.DEFAULT_FONT_MENU;

        setBuffers(3);
        setBufferSize(Cardinal.SOUTH, 0);

        nameField = new RamTextComponent(font, name);
        nameField.setBuffers(6);
        nameField.setEnabled(false);
        nameField.enableKeyboard(false);
        addChild(nameField);

    }

    /**
     * Creates a RamButton which can be either in textual format or with an image inside. It can be switched.
     * 
     * @param name text inside the button in textual format
     * @param image image inside the button in icon format
     * @param arcRadiusIconFormat arc radius of the corner in icon format.
     * @param arcRadiusTextFormat arc radius of the corner in textual format
     * @param iconFormatWidth width of the button in icon format
     * @param iconFormatHeight height of the button in icon format
     * @param useImage true if the button uses the icon format by default
     */
    public RamButton(String name, RamImageComponent image, int arcRadiusIconFormat, int arcRadiusTextFormat,
            float iconFormatWidth, float iconFormatHeight, boolean useImage) {
        this(useImage ? arcRadiusIconFormat : arcRadiusTextFormat);

        font = Fonts.DEFAULT_FONT_MENU;
        disabledFont = Fonts.DEFAULT_FONT_MENU;
        this.arcRadiusTextFormat = arcRadiusTextFormat;
        this.arcRadiusIconFormat = arcRadiusIconFormat;
        this.sizeIconFormatWidth = iconFormatWidth;
        this.sizeIconFormatHeight = iconFormatHeight;
        icon = image;
        usesImage = useImage;

        nameField = new RamTextComponent(font, name);
        nameField.setBuffers(6);
        nameField.setEnabled(false);
        nameField.enableKeyboard(false);

        if (usesImage) {
            setBuffers(0);
            usesImage = true;

            addChild(image);

            this.setSizeLocal(sizeIconFormatWidth, sizeIconFormatHeight);

            centerImage();
        } else {
            setBuffers(3);
            setBufferSize(Cardinal.SOUTH, 0);
            addChild(nameField);
        }

    }

    /**
     * Switch between icon and textual format if possible.
     * 
     * @param toText - true if you want to switch to the icon format
     */
    public void switchToTextFormat(boolean toText) {
        if (toText && usesImage && this.nameField != null) {
            removeAllChildren();

            setBuffers(3);
            setBufferSize(Cardinal.SOUTH, 0);

            nameField = new RamTextComponent(font, this.nameField.getText());
            nameField.setBuffers(6);
            nameField.setEnabled(false);
            nameField.enableKeyboard(false);

            this.setArcRadius(arcRadiusTextFormat);
            addChild(nameField);

            usesImage = false;

        } else if (!toText && !usesImage && this.icon != null) {

            usesImage = true;
            setIcon(icon);

        }

    }

    /**
     * Replace the current icon by the one given in parameter.
     * 
     * @param img - the new icon for the button.
     */
    protected void setIcon(RamImageComponent img) {
        if (img != null) {
            this.icon = img;
            if (usesImage) {
                removeAllChildren();
                setBuffers(0);
                this.addChild(img);
                this.setArcRadius(arcRadiusIconFormat);
                this.setSizeLocal(sizeIconFormatWidth, sizeIconFormatHeight);

                centerImage();
            }
        }
    }

    /**
     * Center the image inside the button.
     */
    private void centerImage() {
        float posX = (float) (0.5 * (this.getWidth() - this.icon.getWidth()));
        float posY = (float) (0.5 * (this.getHeight() - this.icon.getHeight()));
        this.icon.setPositionRelativeToParent(new Vector3D(posX, posY));
    }

    // TODO: mschoettle: another idea could be to forward directly to the handler (using IButtonListener ->
    // buttonPressed(RamButton source)), that way the view containing
    // the button doesn't have to forward the call to the handler itself
    // another idea could be to use the actionCommand as the method name and the method in the handler will be called
    // using reflection
    /**
     * Adds the given action listener.
     * 
     * @param listener
     *            the {@link ActionListener} to be added
     */
    public void addActionListener(ActionListener listener) {
        listeners.add(listener);
    }

    /**
     * Returns the action command for this button.
     * 
     * @return the action command
     */
    public String getActionCommand() {
        return actionCommand;
    }

    /**
     * Returns the name field.
     * 
     * @return the name field
     */
    public RamTextComponent getNameField() {
        return nameField;
    }

    /**
     * Returns the icon image.
     * 
     * @return the icon image.
     */
    public RamImageComponent getIconImage() {
        return this.icon;
    }

    @Override
    public boolean processGestureEvent(MTGestureEvent gestureEvent) {
        return buttonHandler.processGestureEvent(gestureEvent);
    }

    @Override
    protected void registerInputProcessors() {
        // register tap processor
        // stop the event propagation so now children will get tapped for sure
        AbstractComponentProcessor tapProcessor =
                new TapProcessor(RamApp.getApplication(), Constants.TAP_MAX_FINGER_UP, false,
                        Constants.TAP_DOUBLE_TAP_TIME, true);
        // this will allow the button to receive a tap event even if a child was tapped
        tapProcessor.setBubbledEventsEnabled(true);
        registerInputProcessor(tapProcessor);
    }

    /**
     * Removes the given action listener.
     * 
     * @param listener
     *            the {@link ActionListener} to remove
     */
    public void removeActionListener(ActionListener listener) {
        listeners.remove(listener);
    }

    /**
     * Sets the action command to the given one. See class description for more information.
     * 
     * @param actionCommand
     *            the action command to set
     */
    public void setActionCommand(String actionCommand) {
        this.actionCommand = actionCommand;
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        updateButtonGUI();
        if (usesImage) {
            setNoFill(enabled);
        }
    }

    /**
     * Sets the text for the button.
     * 
     * @param newText
     *            the new text to use for the button
     */
    public void setText(String newText) {
        nameField.setText(newText);
    }

    /**
     * Sets the font for the button.
     * 
     * @param font
     *            the font to use for text
     */
    public void setFont(IFont font) {
        this.font = font;
        updateButtonGUI();
    }

    /**
     * Sets the font for the disabled button.
     * 
     * @param disabledFont
     *            the font to use for disabled text
     */
    public void setDisabledFont(IFont disabledFont) {
        this.disabledFont = disabledFont;
        updateButtonGUI();
    }

    /**
     * Sets the background color of the button.
     * 
     * @param color
     *            The new background color
     */
    public void setBackgroundColor(MTColor color) {
        backgroundColor = color;
        updateButtonGUI();
    }

    /**
     * Sets the background color of the disabled button.
     * 
     * @param color
     *            The new disabled background color
     */
    public void setDisabledBackgroundColor(MTColor color) {
        disabledBackgroundColor = color;
        updateButtonGUI();
    }

    /**
     * Update the GUI of the button (background/font...).
     */
    private void updateButtonGUI() {
        MTColor fillColor = isEnabled() ? backgroundColor : disabledBackgroundColor;
        if (fillColor != null) {
            setFillColor(fillColor);
        }
        if (nameField != null) {
            IFont textFont = isEnabled() ? font : disabledFont;
            if (textFont != null) {
                nameField.setFont(textFont);
            }
        }
    }

    /**
     * Call all the listeners to execute their reaction to the action command name.
     */
    public void executeAction() {
        if (this.isEnabled()) {
            ActionEvent event = new ActionEvent(this, this.getActionCommand());

            for (ActionListener listener : listeners) {
                listener.actionPerformed(event);
            }
        }
    }

    /**
     * To know if the button is in image format or not.
     * 
     * @return true is the button is in image format.
     */
    public boolean isUsingImage() {
        return usesImage;
    }
}
