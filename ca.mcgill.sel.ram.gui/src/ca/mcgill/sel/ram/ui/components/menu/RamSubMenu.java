package ca.mcgill.sel.ram.ui.components.menu;

import java.util.ArrayList;
import java.util.List;

import org.mt4j.components.MTComponent;
import org.mt4j.components.TransformSpace;
import org.mt4j.components.visibleComponents.shapes.MTEllipse;
import org.mt4j.input.inputData.InputCursor;
import org.mt4j.input.inputProcessors.IGestureEventListener;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragEvent;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapProcessor;
import org.mt4j.util.MTColor;
import org.mt4j.util.math.Vector3D;

import processing.core.PImage;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamButton;
import ca.mcgill.sel.ram.ui.components.RamImageComponent;
import ca.mcgill.sel.ram.ui.components.menu.RamMenu.Position;
import ca.mcgill.sel.ram.ui.events.listeners.ActionListener;
import ca.mcgill.sel.ram.ui.events.listeners.IDragListener;
import ca.mcgill.sel.ram.ui.events.listeners.ITapListener;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.utils.Icons;
import ca.mcgill.sel.ram.ui.views.handler.BaseHandler;

/**
 * This class represents a sub-menu. It can contains actions which can be visible or hidden.
 * It has a priority which is at least 1. This priority is used to position the menu and items around the menu in
 * function of the menu position on the screen and so the available space.
 * 1 is the higher priority. Higher the priority is, most probable to be visible the actions will be.
 * The sub-menu can be single tapped to show items or dragged to show items and select one on drop.
 * 
 * @author g.Nicolas
 *
 */
public class RamSubMenu extends MTEllipse implements IGestureEventListener {

    private int radius;
    // priority (at least 1) to define the importance of actions
    private int priority;
    // id of the sub-menu
    private String id;
    // all the actions
    private List<RamMenuAction> actions;
    // true if the sub-menu is expended
    private boolean actionVisible;
    // background icon of the sub-menu
    private RamImageComponent image;
    // angle of the sub-menu related to its parent.
    private float angle;
    // True if labels are shown around the button.
    private boolean labelShown;

    /**
     * Constructs a sub-menu with the given priority and id.
     * 
     * @param prior priority of the sub-menu which define the importance of actions. See class javadoc for more
     *            information
     * @param id id of the sub-menu
     * @param rad - radius of the current sub-menu ellipse
     */
    public RamSubMenu(int prior, String id, int rad) {
        super(RamApp.getApplication(), new Vector3D(0, 0), rad, rad);
        this.priority = prior;
        this.id = id;
        this.actions = new ArrayList<RamMenuAction>();
        this.angle = 0;
        this.radius = rad;
        this.labelShown = false;

        this.setNoStroke(true);
        this.setFillColor(Colors.MENU_BACKGROUND_COLOR);

        this.registerInputProcessor(new DragProcessor(RamApp.getApplication()));
        this.registerInputProcessor(new TapProcessor(RamApp.getApplication()));

        RamSubMenuHandler handler = new RamSubMenuHandler();
        this.addGestureListener(DragProcessor.class, handler);
        this.addGestureListener(TapProcessor.class, handler);

    }

    /**
     * Add a textual action in the main commands list. The priority is 0.
     * The action button can't be switched to icon format.
     * 
     * @param text the text printed on the action button and which identify the button.
     * @param command the command of the ActionEvent given when an action is performed.
     * @param listener the action listener which will be called when the action is performed.
     * @param isEnabled - true if the button is enabled
     * @return true if the action has been added, so if there is enough space in the sub-menu. False otherwise.
     */
    public boolean addAction(String text, String command, ActionListener listener, boolean isEnabled) {
        return this.addAction(text, null, null, null, command, listener, false, false, isEnabled);
    }

    /**
     * Add a textual action in the main commands list. The priority is 0.
     * The action button can't be switched to icon format.
     * 
     * @param text the text printed on the action button and which identify the button.
     * @param textToggled - the text which will appear in the button when it's toggled
     * @param command the command of the ActionEvent given when an action is performed.
     * @param listener the action listener which will be called when the action is performed.
     * @param isToggled - true if the button is toggled
     * @param isEnabled - true if the button is enabled
     * @return true if the action has been added, so if there is enough space in the sub-menu. False otherwise.
     */
    // CHECKSTYLE:IGNORE ParameterNumberCheck: Can do a fluent API, but it takes time to create it.
    public boolean addAction(String text, String textToggled, String command, ActionListener listener,
            boolean isToggled, boolean isEnabled) {
        return addAction(text, textToggled, null, null, command, listener, false, isToggled, isEnabled);
    }

    /**
     * Add an icon action in the main commands list. The priority is 0.
     * The action button can be switched to textual format.
     * 
     * @param text the text printed on the action button in textual format and which identify the button.
     * @param icon the image of the icon inside the button when it's in icon format.
     * @param command the command of the ActionEvent given when an action is performed.
     * @param listener the action listener which will be called when the action is performed.
     * @param usesImage true if the button is in icon format by default. False otherwise.
     * @param isEnabled - true if the button is enabled
     * @return true if the action has been added, so if there is enough space in the sub-menu. False otherwise.
     */
    public boolean addAction(String text, PImage icon, String command, ActionListener listener, boolean usesImage,
            boolean isEnabled) {
        return this.addAction(text, null, icon, null, command, listener, usesImage, false, isEnabled);
    }

    /**
     * Add an icon action which can be toggled in the main commands list. The priority is 0.
     * The action button can be switched to textual format.
     * 
     * @param text the text printed on the action button in textual format and which identify the button.
     * @param textToggled - the text which will appear in the button when it's toggled
     * @param icon the image of the icon inside the button when it's in icon format.
     * @param iconToggled - the icon which will be inside the button in icon format when toggled
     * @param command the command of the ActionEvent given when an action is performed.
     * @param listener the action listener which will be called when the action is performed.
     * @param usesImage true if the button is in icon format by default. False otherwise.
     * @param isToggled - true if the button is toggled
     * @param isEnabled - true if the button is enabled
     * @return true if the action has been added, so if there is enough space in the sub-menu. False otherwise.
     */
    // CHECKSTYLE:IGNORE ParameterNumberCheck: Can do a fluent API, but it takes time to create it.
    public boolean addAction(String text, String textToggled, PImage icon, PImage iconToggled, String command,
            ActionListener listener, boolean usesImage, boolean isToggled, boolean isEnabled) {

        RamMenuAction action;

        if (icon == null) {
            if (textToggled != null) {
                action = new RamMenuToggleAction(text, textToggled, command, listener, radius, isToggled);
            } else {
                action = new RamMenuAction(text, command, listener, radius);
            }
        } else {
            RamImageComponent iconImg = new RamImageComponent(icon, MTColor.WHITE);
            iconImg.setSizeLocal(4 * radius / 3, 4 * radius / 3);

            if (iconToggled != null) {
                RamImageComponent iconToggledImg = new RamImageComponent(iconToggled, MTColor.WHITE);
                iconToggledImg.setSizeLocal(4 * radius / 3, 4 * radius / 3);
                action = new RamMenuToggleAction(iconImg, iconToggledImg, text, textToggled, command, listener,
                        usesImage, radius, isToggled);
            } else {
                action = new RamMenuAction(iconImg, text, command, listener, usesImage, radius);
            }
        }
        action.setEnabled(isEnabled);

        this.actions.add(action);

        return true;

    }

    /**
     * The main method which will draw the sub-menu at the exact position given in parameter. It will also draw actions
     * into the menu just modifying the distance from the center in order to keep them aligned on the same angle.
     * 
     * @param angl angle of the sub-menu from parent position which allow to keep actions aligned.
     * @param x the X position of the sub-menu relative to its parent.
     * @param y the Y position of the sub-menu relative to its parent
     * @param parentPosition - X position of its parent on the scene.
     * @param menuPosition - position of the menu on the screen.
     */
    public void draw(float angl, float x, float y, float parentPosition, Position menuPosition) {
        this.angle = angl;
        removeAllChildren();

        float rad = 0;
        MTComponent previousComponent = this;

        for (int i = 0; i < this.actions.size(); i++) {

            // Look the previous component to get its height
            RamMenuAction c = this.actions.get(i);

            float angleMod = angle % 360;
            float prevHeight = 0;
            if (previousComponent instanceof RamSubMenu) {
                RamSubMenu sm = (RamSubMenu) previousComponent;
                prevHeight = sm.getRadiusY();
            } else if (previousComponent instanceof RamMenuAction) {
                RamMenuAction ma = (RamMenuAction) previousComponent;
                if (angleMod == 0 || angleMod == 180) {
                    prevHeight = ma.getWidth() / 2;
                } else {
                    prevHeight = ma.getHeight() / 2;
                }
            }
            // Compute the radius using half of the current item height and the previous component height
            if (angleMod == 0 || angleMod == 180) {
                rad += c.getWidth() / 2 + prevHeight + this.getRadiusX() / 3;
            } else if (angleMod == 90 || angleMod == 270 || c.isUsingImage()) {
                rad += c.getHeight() / 2 + prevHeight + this.getRadiusX() / 3;
            } else {
                rad += c.getHeight() / 2 + prevHeight + 2 * this.getRadiusX() / 3;
            }

            float itemX = (float) (rad * Math.cos((angle * Math.PI) / 180) - c.getWidth() / 2);
            float itemY = (float) (rad * Math.sin((angle * Math.PI) / 180) - c.getHeight() / 2);

            // If rectangle is partially out of the screen move it to be fully visible
            if (!c.isUsingImage()) {
                float itemXRelatedToGlobal = parentPosition + x + itemX;
                float rectangleWidth = c.getWidth();
                float rectangleTopRightCornerPositionX = itemXRelatedToGlobal + rectangleWidth;
                float sceneWidth = RamApp.getApplication().getWidth();

                if (itemXRelatedToGlobal < 0) {
                    itemX -= itemXRelatedToGlobal;
                }
                if (rectangleTopRightCornerPositionX > sceneWidth) {
                    itemX -= rectangleTopRightCornerPositionX - sceneWidth;
                }
            }

            c.setPositionRelativeToParent(new Vector3D(itemX, itemY));

            this.addChild(c);

            boolean last = i == this.actions.size() - 1 ? true : false;
            boolean scale = !last && actions.size() != 2;
            c.draw(menuPosition, angleMod, last, scale, parentPosition + x + itemX,
                    c.getPosition(TransformSpace.RELATIVE_TO_PARENT));

            previousComponent = c;
        }
        this.hideActions(!actionVisible);

    }

    /**
     * Get the priority of the sub-menu.
     * 
     * @return the priority of the sub-menu
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Get the ID of the sub-menu.
     * 
     * @return the ID of the sub-menu
     */
    public String getId() {
        return id;
    }

    /**
     * Get the list of actions of the sub-menu.
     * 
     * @return the list of actions of the sub-menu
     */
    public List<RamMenuAction> getActions() {
        return actions;
    }

    /**
     * Set the label shown value.
     * 
     * @param labelShown - true or false
     */
    public void setLabelShown(boolean labelShown) {
        this.labelShown = labelShown;
    }

    /**
     * To know if the sub-menu is collapsed or not.
     * 
     * @return true if the sub-menu is expanded
     */
    public boolean isActionVisible() {
        return actionVisible;
    }

    /**
     * Modify the color of the action given in parameter in order to highlight it.
     * 
     * @param b the button you want to highlight
     * @param hightlight true if you want to highlight the button, false otherwise.
     */
    private void highlightAction(RamButton b, boolean hightlight) {
        float alpha = hightlight ? 170 : 255;
        MTColor color = b.getFillColor();
        color.setAlpha(alpha);
        b.setFillColor(color);
        if (!labelShown && b instanceof RamMenuAction) {
            ((RamMenuAction) b).getLabel().setVisible(hightlight);
        }
    }

    /**
     * Collapse or expand the sub-menu in order to show the actions.
     * 
     * @param hide true if you want to collapse the sub-menu
     */
    public void hideActions(boolean hide) {

        if (image != null) {
            this.removeChild(image);
        }

        // Add icon in background
        image = new RamImageComponent(Icons.ICON_MENU_COLLAPSE, MTColor.WHITE);
        image.setPickable(false);
        image.setSizeLocal(4 * radius / 3, 4 * radius / 3);
        image.setPositionRelativeToParent(new Vector3D(-2 * radius / 3, -2 * radius / 3));

        float rotation = hide ? 0 : 180;
        image.rotateZ(new Vector3D(0, 0), (this.angle + 90 + rotation) % 360);
        this.addChild(image);

        this.actionVisible = !hide;
        for (MTComponent c : this.getChildList()) {
            if (c instanceof RamMenuAction) {
                RamMenuAction ma = (RamMenuAction) c;
                ma.setVisible(!hide);
                ma.getLabel().setVisible(!hide && labelShown);
            }
        }
    }

    @Override
    protected void setDefaultGestureActions() {
        // NOTE - make sure to override and not call super, otherwise extra input processors will be added to this
        // component.
        // This causes gestures to be handled twice and makes it look like drag events are scaled wrong.
    }

    @Override
    public void destroy() {
        for (RamMenuAction ma : actions) {
            ma.destroy();
        }
        actions.clear();
        super.destroy();
    }

    /**
     * Handler of the sub-menu which implements the drag and tap reaction.
     * 
     * @author g.Nicolas
     *
     */
    class RamSubMenuHandler extends BaseHandler implements ITapListener, IDragListener {

        private boolean isDragged;
        private boolean displayedStart;

        /**
         * Construct a sub-menu handler.
         */
        RamSubMenuHandler() {
            isDragged = false;
            displayedStart = false;
        }

        @Override
        public boolean processDragEvent(DragEvent dragEvent) {
            switch (dragEvent.getId()) {
                case MTGestureEvent.GESTURE_STARTED:
                    displayedStart = actionVisible;
                    break;
                case MTGestureEvent.GESTURE_UPDATED:
                    isDragged = true;
                    if (!actionVisible) {
                        hideActions(false);
                    }
                    // Mouse over effect
                    for (MTComponent c : getChildList()) {
                        if (c instanceof RamButton) {
                            RamButton rb = (RamButton) c;
                            if (rb.containsPointGlobal(dragEvent.getTo())) {
                                highlightAction(rb, true);
                            } else {
                                highlightAction(rb, false);
                            }
                        }
                    }
                    break;
                case MTGestureEvent.GESTURE_ENDED:
                    if (isDragged) {
                        InputCursor cursor = dragEvent.getDragCursor();
                        for (MTComponent c : getChildList()) {
                            if (c.containsPointGlobal(cursor.getPosition())) {
                                if (c instanceof RamButton) {
                                    final RamButton rb = (RamButton) c;
                                    highlightAction(rb, false);
                                    RamApp.getApplication().invokeLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            rb.executeAction();
                                        }
                                    });
                                } else if (c instanceof RamImageComponent) {
                                    isDragged = false;
                                    return true;
                                }
                            }
                        }
                        if (!displayedStart) {
                            hideActions(true);
                        }
                        isDragged = false;
                    }
                    break;
            }
            return true;
        }

        @Override
        public boolean processTapEvent(TapEvent tapEvent) {
            if (tapEvent.isTapped() && isEnabled()) {
                if (actionVisible && displayedStart) {
                    hideActions(true);
                } else if (!actionVisible) {
                    hideActions(false);
                }
            }
            return true;
        }

    }

}
