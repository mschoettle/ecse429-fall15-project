package ca.mcgill.sel.ram.ui.components.menu;

import org.mt4j.components.TransformSpace;
import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamButton;
import ca.mcgill.sel.ram.ui.components.RamImageComponent;
import ca.mcgill.sel.ram.ui.components.RamTextComponent;
import ca.mcgill.sel.ram.ui.components.RamTextComponent.Alignment;
import ca.mcgill.sel.ram.ui.components.menu.RamMenu.Position;
import ca.mcgill.sel.ram.ui.events.listeners.ActionListener;
import ca.mcgill.sel.ram.ui.utils.Colors;

/**
 * An action button which can be in textual format or in icon format.
 * 
 * @author g.Nicolas
 *
 */
public class RamMenuAction extends RamButton {

    /**
     * Radius for the corner of textual format button.
     */
    protected static final int ITEM_TEXT_RADIUS = 10;
    /**
     * Text in the button in text format.
     */
    protected String text;
    /**
     * Icon in the button in icon format.
     */
    protected RamImageComponent icon;
    /**
     * Text component for button label.
     */
    protected RamTextComponent label;

    /** Listener of the button. */
    private ActionListener listener;

    /**
     * Constructs a textual action button with the given name and command.
     * 
     * @param text the text which will appear in the button and which identify the action
     * @param commandName the command name which will be sent to the listener
     * @param listener the listener which implements the button reaction
     * @param parentRadius - radius of the parent.
     */
    public RamMenuAction(String text, String commandName, ActionListener listener, int parentRadius) {
        super(text, ITEM_TEXT_RADIUS);
        init(text, commandName, listener, parentRadius);
    }

    /**
     * Constructs an icon action button which can be switch to textual format with the text given in parameter.
     * 
     * @param ico the icon which will be inside the button in icon format
     * @param text the text inside the button in textual format
     * @param commandName the command name which will be sent to the listener
     * @param listener the listener which implements the button reaction
     * @param usesImage true if you want the button to be in icon format by default.
     * @param parentRadius - radius of the parent.
     */
    public RamMenuAction(RamImageComponent ico, String text, String commandName, ActionListener listener,
            boolean usesImage, int parentRadius) {
        super(text, ico, parentRadius, ITEM_TEXT_RADIUS, 2 * parentRadius, 2 * parentRadius, usesImage);
        this.icon = ico;

        this.label = new RamTextComponent();
        this.label.setNoFill(true);
        this.label.setText(text);
        this.label.setVisible(false);
        this.label.setAlignment(Alignment.CENTER_ALIGN);
        this.label.setAnchor(PositionAnchor.CENTER);

        init(text, commandName, listener, parentRadius);
    }

    /**
     * Initialize graphically the button and link it to the listener.
     * 
     * @param tex the text inside the button in textual format
     * @param commandName the command name which will be sent to the listener
     * @param lis the listener which implements the button reaction
     * @param parentRadius - radius of the parent.
     */
    private void init(String tex, String commandName, ActionListener lis, int parentRadius) {
        this.listener = lis;
        this.text = tex;
        this.setNoFill(false);
        this.setBackgroundColor(Colors.MENU_BACKGROUND_COLOR);

        this.setName(text);
        this.setVisible(false);
        this.setActionCommand(commandName);
        this.addActionListener(lis);

    }

    /**
     * Draw text labels near the button if icon format.
     * 
     * @param pos - position of the Menu
     * @param angle - angle of the button around the menu (between 0 and 360).
     * @param last - true if the button is the last on the sub-menu line or not into a sub-menu
     * @param gap - true if there is a larger gap between the button and the label.
     * @param globalPosition - position X of the button in the scene
     * @param positionRelatedToParent - position of the button related to its parent
     */
    public void draw(Position pos, float angle, boolean last, boolean gap, float globalPosition, Vector3D
            positionRelatedToParent) {
        label.setWidthXYGlobal(label.getWidth());
        float angl = angle % 360;
        if (label != null) {
            getParent().addChild(label);
            if (pos == Position.CENTER) {
                if ((angl == 0 && !last) || (angl == 180 && !last) || angl == 90) {
                    setLabelComponentOnBottom(last, gap, globalPosition, positionRelatedToParent);
                } else if (angl == 270) {
                    setLabelComponentOnTop(last, gap, globalPosition, positionRelatedToParent);
                } else if (angl < 90 || angl > 270) {
                    setLabelComponentOnRight(positionRelatedToParent);
                } else if (angl > 90 || angl < 270) {
                    setLabelComponentOnLeft(positionRelatedToParent);
                }
            } else if (pos == Position.WEST) {
                if (angl == 0 && !last) {
                    setLabelComponentOnBottom(last, gap, globalPosition, positionRelatedToParent);
                } else {
                    setLabelComponentOnRight(positionRelatedToParent);
                }
            } else if (pos == Position.EST) {
                if (angl == 180 && !last) {
                    setLabelComponentOnBottom(last, gap, globalPosition, positionRelatedToParent);
                } else {
                    setLabelComponentOnLeft(positionRelatedToParent);
                }
            } else if (pos == Position.NORTH) {
                if ((!last && (angl == 0 || angl == 180)) || (last && angle == 90)) {
                    setLabelComponentOnBottom(last, gap, globalPosition, positionRelatedToParent);
                } else if (angl > 90 && angl <= 180) {
                    setLabelComponentOnLeft(positionRelatedToParent);
                } else if (angl <= 90) {
                    setLabelComponentOnRight(positionRelatedToParent);
                }
            } else if (pos == Position.NORTH_WEST) {
                if ((!last && angl == 0) || (last && angl == 90)) {
                    setLabelComponentOnBottom(last, gap, globalPosition, positionRelatedToParent);
                } else {
                    setLabelComponentOnRight(positionRelatedToParent);
                }
            } else if (pos == Position.NORTH_EST) {
                if ((!last && angl == 180) || (last && angl == 90)) {
                    setLabelComponentOnBottom(last, gap, globalPosition, positionRelatedToParent);
                } else {
                    setLabelComponentOnLeft(positionRelatedToParent);
                }
            } else if (pos == Position.SOUTH) {
                if ((!last && (angl == 0 || angl == 180)) || (last && angle == 270)) {
                    setLabelComponentOnTop(last, gap, globalPosition, positionRelatedToParent);
                } else if (angl < 270 && angl >= 180) {
                    setLabelComponentOnLeft(positionRelatedToParent);
                } else if (angl >= 270 || angl == 0) {
                    setLabelComponentOnRight(positionRelatedToParent);
                }
            } else if (pos == Position.SOUTH_EST) {
                if ((!last && angl == 180) || (last && angl == 270)) {
                    setLabelComponentOnTop(last, gap, globalPosition, positionRelatedToParent);
                } else {
                    setLabelComponentOnLeft(positionRelatedToParent);
                }
            } else if (pos == Position.SOUTH_WEST) {
                if ((!last && angl == 0) || (last && angle == 270)) {
                    setLabelComponentOnTop(last, gap, globalPosition, positionRelatedToParent);
                } else {
                    setLabelComponentOnRight(positionRelatedToParent);
                }
            }
        }
    }

    /**
     * Set the label component on top of the button with a margin.
     * 
     * @param last - true if the label is the last.
     * @param scale - true if the label will overlap other labels.
     * @param globalPosition - position of the button in the scene
     * @param positionRelatedToParent - position of the button related to its parent
     */
    private void setLabelComponentOnTop(boolean last, boolean scale, float globalPosition,
            Vector3D positionRelatedToParent) {
        if (scale && label.getWidth() > getWidth()) {
            label.setWidthXYGlobal(getWidth());
        }

        float labelX = (float) (positionRelatedToParent.x);
        float labelY = (float) (positionRelatedToParent.y - 0.5 * label.getHeightXY(TransformSpace.GLOBAL));

        // If the label is out of the screen. Do this only if label is last.
        if (last) {
            float labelXTopLeftGlobal =
                    (globalPosition + getWidth() / 2) + labelX - label.getWidthXY(TransformSpace.GLOBAL) / 2;
            float labelXTopRightGlobal =
                    (globalPosition + getWidth() / 2) + labelX + label.getWidthXY(TransformSpace.GLOBAL) / 2;

            if (labelXTopLeftGlobal < 0) {
                labelX -= labelXTopLeftGlobal;
            } else if (labelXTopRightGlobal > RamApp.getApplication().getWidth()) {
                labelX -= labelXTopRightGlobal - RamApp.getApplication().getWidth();
            }
        }
        label.setPositionRelativeToParent(new Vector3D(labelX, labelY));
    }

    /**
     * Set the label component on bottom of the button with a margin.
     * 
     * @param last - true if the label is the last.
     * @param scale - true if the label will overlap other labels.
     * @param globalPosition - position of the button (top-left) in the scene
     * @param positionRelatedToParent - position of the button related to its parent
     */
    private void setLabelComponentOnBottom(boolean last, boolean scale, float globalPosition,
            Vector3D positionRelatedToParent) {
        if (scale && label.getWidth() > getWidth()) {
            label.setWidthXYGlobal(getWidth());
        }
        float labelX = (float) (positionRelatedToParent.x);
        float labelY =
                (float) (positionRelatedToParent.y + getHeight() + 0.5 * label.getHeightXY(TransformSpace.GLOBAL));

        // If the label is out of the screen. Do this only if label is last.
        if (last) {
            float labelXTopLeftGlobal =
                    (globalPosition + getWidth() / 2) + labelX - label.getWidthXY(TransformSpace.GLOBAL) / 2;
            float labelXTopRightGlobal =
                    (globalPosition + getWidth() / 2) + labelX + label.getWidthXY(TransformSpace.GLOBAL) / 2;

            if (labelXTopLeftGlobal < 0) {
                labelX -= labelXTopLeftGlobal;
            } else if (labelXTopRightGlobal > RamApp.getApplication().getWidth()) {
                labelX -= labelXTopRightGlobal - RamApp.getApplication().getWidth();
            }
        }
        label.setPositionRelativeToParent(new Vector3D(labelX, labelY));
    }

    /**
     * Set the label component on the left of the button with a margin.
     * 
     * @param positionRelatedToParent - position of the button related to its parent
     */
    private void setLabelComponentOnLeft(Vector3D positionRelatedToParent) {
        float labelX = (float) (positionRelatedToParent.x - 0.5 * (getWidth() + label.getWidth()));
        float labelY = (float) (positionRelatedToParent.y + 0.5 * getHeight());
        label.setPositionRelativeToParent(new Vector3D(labelX, labelY));
    }

    /**
     * Set the label component on the right of the button with a margin.
     * 
     * @param positionRelatedToParent - position of the button related to its parent
     */
    private void setLabelComponentOnRight(Vector3D positionRelatedToParent) {
        float labelX = (float) (positionRelatedToParent.x + 0.5 * (getWidth() + label.getWidth()));
        float labelY = (float) (positionRelatedToParent.y + 0.5 * getHeight());
        label.setPositionRelativeToParent(new Vector3D(labelX, labelY));
    }

    /**
     * Get the listener linked to the button.
     * 
     * @return the listener linked to the button
     */
    public ActionListener getListener() {
        return listener;
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        this.setNoFill(false);
    }

    /**
     * Get the text inside the button in textual format.
     * 
     * @return the text inside the button in textual format.
     */
    public String getText() {
        return text;
    }

    /**
     * Get the icon inside the button in icon format.
     * 
     * @return the icon inside the button in icon format.
     */
    public RamImageComponent getIcon() {
        return icon;
    }

    /**
     * Get the label component of the button.
     * 
     * @return the label component of the button.
     */
    public RamTextComponent getLabel() {
        return label;
    }

    @Override
    public void destroy() {
        this.label.destroy();
        this.icon.destroy();
        super.destroy();
    }

}
