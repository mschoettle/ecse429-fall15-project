package ca.mcgill.sel.ram.ui.components.menu;

import ca.mcgill.sel.ram.ui.components.RamImageComponent;
import ca.mcgill.sel.ram.ui.events.listeners.ActionListener;

/**
 * An action button which can be in textual format or in icon format.
 * This button can be toggled, it mean that the text is changed and the icon if exists
 * 
 * @author g.Nicolas
 *
 */
public class RamMenuToggleAction extends RamMenuAction {

    private RamImageComponent toggledIcon;
    private String toggledText;

    // true if the button is toggled
    private boolean toggled;

    /**
     * Constructs a textual action button with the given name and command which can be toggled with a different text.
     * 
     * @param text - the text which will appear in the button
     * @param toggledTex - the text which will appear in the button when it's toggled
     * @param commandName the command name which will be sent to the listener
     * @param listener the listener which implements the button reaction
     * @param parentRadius - radius of the parent.
     * @param toggle - true if you want a toggled button
     */
    public RamMenuToggleAction(String text, String toggledTex, String commandName, ActionListener listener,
            int parentRadius, boolean toggle) {
        super(text, commandName, listener, parentRadius);
        init(toggle);
    }

    /**
     * Constructs an icon action button which can be switch to textual format with the text given in parameter and
     * toggled with a new icon and new text.
     * 
     * @param ico - the icon which will be inside the button in icon format
     * @param toggledIco - the icon which will be inside the button in icon format when toggled
     * @param text the text inside the button in textual format
     * @param toggledTex - the text inside the button in textual format when toggled
     * @param commandName the command name which will be sent to the listener
     * @param listener the listener which implements the button reaction
     * @param usesImage true if you want the button to be in icon format by default.
     * @param parentRadius - radius of the parent.
     * @param toggle - true if you want a toggled button
     */
    // CHECKSTYLE:IGNORE ParameterNumberCheck: Can do a fluent API, but it takes time to create it.
    public RamMenuToggleAction(RamImageComponent ico, RamImageComponent toggledIco, String text, String toggledTex,
            String commandName, ActionListener listener, boolean usesImage, int parentRadius, boolean toggle) {
        super(ico, text, commandName, listener, usesImage, parentRadius);
        this.toggledIcon = toggledIco;
        this.toggledText = toggledTex;
        if (toggle) {
            this.label.setText(toggledTex);
        }
        init(toggle);

    }

    /**
     * Initialize the button.
     * 
     * @param toggle - true if you want to initialize with a toggled button
     */
    private void init(boolean toggle) {
        this.toggled = toggle;
        this.toggle(toggle);
    }

    /**
     * Toggle the button to modify text and icon inside.
     * 
     * @param toggle - true if you want to toggle the button
     */
    public void toggle(boolean toggle) {
        this.toggled = toggle;
        this.label.setText(toggle ? toggledText : text);
        this.setText(toggle ? toggledText : text);
        this.setIcon(toggle ? toggledIcon : icon);
    }

    /**
     * Returns true if the button is toggled.
     * 
     * @return true if the button is toggled.
     */
    public boolean isToggled() {
        return this.toggled;
    }

    /**
     * Get the icon inside the button in icon format when it's toggled.
     * 
     * @return the icon inside the button in icon format when it's toggled.
     */
    public RamImageComponent getToggledIcon() {
        return toggledIcon;
    }

    /**
     * Get the text inside the button in textual format when it's toggled.
     * 
     * @return the text inside the button in textual format when it's toggled.
     */
    public String getToggledText() {
        return toggledText;
    }

}
