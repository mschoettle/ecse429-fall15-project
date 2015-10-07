package ca.mcgill.sel.ram.ui.components.menu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mt4j.components.MTComponent;
import org.mt4j.components.TransformSpace;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragEvent;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.tapAndHoldProcessor.TapAndHoldEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapAndHoldProcessor.TapAndHoldProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapProcessor;
import org.mt4j.util.math.Vector3D;

import processing.core.PImage;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamButton;
import ca.mcgill.sel.ram.ui.components.RamRoundedRectangleComponent;
import ca.mcgill.sel.ram.ui.components.RamTextComponent;
import ca.mcgill.sel.ram.ui.events.DelayedDrag;
import ca.mcgill.sel.ram.ui.events.listeners.ActionListener;
import ca.mcgill.sel.ram.ui.events.listeners.IDragListener;
import ca.mcgill.sel.ram.ui.events.listeners.ITapAndHoldListener;
import ca.mcgill.sel.ram.ui.events.listeners.ITapListener;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.utils.Constants;
import ca.mcgill.sel.ram.ui.views.handler.BaseHandler;

/**
 * An independent menu which can be dragged and automatically organize itself in order to show at any time, all the
 * available actions to the user. Actions are placed around the menu in function of their sub-menu priority (1 to
 * infinity -- 1 is priority ) and the space.
 * You can add main actions which will be shown in priority when there is enough space around the menu.
 * You can add actions with text only or text + icon. Which allow the user to see a textual format of the icon.
 *
 * @author g.Nicolas
 *
 */
public class RamMenu extends RamRoundedRectangleComponent {

    /**
     * Default radius for the big menu.
     */
    public static final int DEFAULT_BIG_MENU_RADIUS = 64;
    /**
     * Default radius for the small menu.
     */
    public static final int DEFAULT_SMALL_MENU_RADIUS = 32;

    // maximum of items when the menu is fully visible ("centered")
    private static final int MAX_ITEMS_CENTER = 8;
    // maximum of items when the menu is in a corner (knowing that a bit more than a quarter of the menu is visible)
    private static final int MAX_ITEMS_CORNER = 3;
    // maximum of items when the menu is on an edge
    private static final int MAX_ITEMS_EDGE = 5;
    // ID of the main commands sub-menu.
    private static final String MAIN_COMMANDS_ID = "MAIN";
    // ID of the temporary sub-menu (when the menu is on an edge or in a corner and several sub-menus are gathered)
    private static final String TEMPORARY_SUBMENU_ID = "SUB_MENU_TEMP";
    // the current position of the menu
    private Position position;
    // the list of sub-menus containing actions. main commands are in the first sub-menu (index 0).
    private List<RamSubMenu> items;
    // temporary sub-menu
    private RamSubMenu temporarySubMenu;
    // The comparator allowing to sort sub-menus by their priority.
    private Comparator<RamSubMenu> itemsComparator = new Comparator<RamSubMenu>() {

        @Override
        public int compare(RamSubMenu o1, RamSubMenu o2) {
            Integer i1 = new Integer(o1.getPriority());
            Integer i2 = new Integer(o2.getPriority());
            return i1.compareTo(i2);
        }
    };

    /**
     * Radius of the central ellipse (the root). Used as the X and Y radius in order to have a circle.
     */
    private int radius;

    /**
     * Anchor when the menu is into a corner (in order to see more than half of the menu).
     */
    private float marginCorners;
    /**
     * Distance between menu and items around.
     */
    private float itemRadius;
    /**
     * Trigger box size which will detect when the menu is near a corner or an edge.
     */
    private float triggerSize;
    /**
     * True if labels are shown around the button.
     */
    private boolean labelShown;

    /**
     * Enumeration which represents the position of the menu.
     *
     * @author g.Nicolas
     *
     */
    public enum Position {
        /**
         * The menu is on the top edge.
         */
        NORTH,
        /**
         * The menu is on the bottom edge.
         */
        SOUTH,
        /**
         * The menu is on the right edge.
         */
        EST,
        /**
         * The menu is on the left edge.
         */
        WEST,
        /**
         * The menu is in the top left corner.
         */
        NORTH_WEST,
        /**
         * The menu is in the top right corner.
         */
        NORTH_EST,
        /**
         * The menu is in the bottom left corner.
         */
        SOUTH_WEST,
        /**
         * The menu is in the bottom right corner.
         */
        SOUTH_EST,
        /**
         * The menu is fully visible in the scene.
         */
        CENTER
    }

    /**
     * Constructs the menu with specific characteristics set in constants.
     */
    public RamMenu() {
        this(DEFAULT_BIG_MENU_RADIUS, true);
    }

    /**
     * Constructs the menu with specific characteristics set in constants.
     *
     * @param rad - radius of the menu
     * @param isIconFormat - true if you want to show the menu in icon format.
     */
    public RamMenu(int rad, boolean isIconFormat) {
        super(rad);

        this.setFillColor(Colors.MENU_BACKGROUND_COLOR);
        setNoStroke(true);

        items = new ArrayList<RamSubMenu>();
        this.position = Position.CENTER;
        this.radius = rad;
        this.marginCorners = radius / 2;
        this.itemRadius = (float) 5 / 3 * radius;
        this.triggerSize = radius;
        this.labelShown = !isIconFormat;

        this.registerInputProcessor(new DragProcessor(RamApp.getApplication()));
        this.registerInputProcessor(new TapProcessor(RamApp.getApplication()));
        this.registerInputProcessor(new TapAndHoldProcessor(RamApp.getApplication(), Constants.TAP_AND_HOLD_DURATION));
        RamMenuHandler menuHandler = new RamMenuHandler();
        this.addGestureListener(DragProcessor.class, menuHandler);
        this.addGestureListener(TapProcessor.class, menuHandler);
        this.addGestureListener(TapAndHoldProcessor.class, menuHandler);
        draw();
    }

    /**
     * Add a textual action in the main commands list. The priority is 0.
     * The action button can't be switched to icon format.
     *
     * @param text the text printed on the action button and which identify the button.
     * @param command the command of the ActionEvent given when an action is performed.
     * @param listener the action listener which will be called when the action is performed.
     * @return true if the action has been added, so if there is enough space in the sub-menu. False otherwise.
     */
    public boolean addAction(String text, String command, ActionListener listener) {
        return addAction(text, null, null, null, command, listener, false, false);
    }

    /**
     * Add a textual action which can be toggled, in the main commands list. The priority is 0.
     * The action button can't be switched to icon format.
     *
     * @param text the text printed on the action button and which identify the button.
     * @param toggledText - the text which will appear in the button when it's toggled
     * @param command the command of the ActionEvent given when an action is performed.
     * @param listener the action listener which will be called when the action is performed.
     * @param isToggled - true if the button is toggled
     * @return true if the action has been added, so if there is enough space in the sub-menu. False otherwise.
     */
    public boolean addAction(String text, String toggledText, String command, ActionListener listener,
            boolean isToggled) {
        RamSubMenu sm = getSubMenu(MAIN_COMMANDS_ID);
        if (sm == null) {
            addSubMenu(0, MAIN_COMMANDS_ID);
            getSubMenu(MAIN_COMMANDS_ID).hideActions(false);
        }
        return addAction(text, toggledText, null, null, command, listener, MAIN_COMMANDS_ID, false, isToggled);
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
     * @return true if the action has been added, so if there is enough space in the sub-menu. False otherwise.
     */
    public boolean addAction(String text, PImage icon, String command, ActionListener listener, boolean usesImage) {
        return addAction(text, null, icon, null, command, listener, usesImage, false);
    }

    /**
     * Add an icon action which can be toggled, in the main commands list. The priority is 0.
     * The action button can be switched to textual format.
     *
     * @param text the text printed on the action button in textual format and which identify the button.
     * @param toggledText - the text which will appear in the button when it's toggled
     * @param icon the image of the icon inside the button when it's in icon format.
     * @param toggledIcon - the icon which will be inside the button in icon format when toggled
     * @param command the command of the ActionEvent given when an action is performed.
     * @param listener the action listener which will be called when the action is performed.
     * @param usesImage true if the button is in icon format by default. False otherwise.
     * @param isToggled - true if the button is toggled
     * @return true if the action has been added, so if there is enough space in the sub-menu. False otherwise.
     */
    // CHECKSTYLE:IGNORE ParameterNumberCheck: Can do a fluent API, but it takes time to create it.
    public boolean addAction(String text, String toggledText, PImage icon, PImage toggledIcon, String command,
            ActionListener listener, boolean usesImage, boolean isToggled) {
        RamSubMenu sm = getSubMenu(MAIN_COMMANDS_ID);
        if (sm == null) {
            addSubMenu(0, MAIN_COMMANDS_ID);
            getSubMenu(MAIN_COMMANDS_ID).hideActions(false);
        }
        return addAction(text, toggledText, icon, toggledIcon, command, listener, MAIN_COMMANDS_ID, usesImage,
                isToggled);
    }

    /**
     * Add a textual action in the sub menu corresponding to the parameter.
     * The action button can't be switched to icon format.
     *
     * @param text the text printed on the action button and which identify the button.
     * @param command the command of the ActionEvent given when an action is performed.
     * @param listener the action listener which will be called when the action is performed.
     * @param subId id of the sub-menu in which the action will be added.
     * @return true if the action has been added, so if the sub-menu exists and if there is enough space in it. False
     *         otherwise.
     */
    public boolean addAction(String text, String command, ActionListener listener, String subId) {
        return addAction(text, null, null, null, command, listener, subId, false, false);
    }

    /**
     * Add a textual action which can be toggled, in the sub menu corresponding to the parameter.
     * The action button can't be switched to icon format.
     *
     * @param text the text printed on the action button in textual format and which identify the button.
     * @param toggledText - the text which will appear in the button when it's toggled
     * @param command the command of the ActionEvent given when an action is performed.
     * @param listener the action listener which will be called when the action is performed.
     * @param subId id of the sub-menu in which the action will be added.
     * @param isToggled - true if the button is toggled
     * @return true if the action has been added, so if the sub-menu exists and if there is enough space in it. False
     *         otherwise.
     */
    public boolean addAction(String text, String toggledText, String command, ActionListener listener, String subId,
            boolean isToggled) {
        return addAction(text, toggledText, null, null, command, listener, subId, false, isToggled);
    }

    /**
     * Add an icon action in the main commands list. The priority is 0.
     * The action button can be switched to textual format.
     *
     * @param text the text printed on the action button in textual format and which identify the button.
     * @param icon the image of the icon inside the button when it's in icon format.
     * @param command the command of the ActionEvent given when an action is performed.
     * @param listener the action listener which will be called when the action is performed.
     * @param subId id of the sub-menu in which the action will be added.
     * @param usesImage true if the button is in icon format by default. False otherwise.
     * @return true if the action has been added, so if the sub-menu exists and if there is enough space in it. False
     *         otherwise.
     */
    public boolean addAction(String text, PImage icon, String command, ActionListener listener, String subId,
            boolean usesImage) {
        return addAction(text, null, icon, null, command, listener, subId, usesImage, false);
    }

    /**
     * Add an icon action which can be toggled, in the main commands list. The priority is 0.
     * The action button can be switched to textual format.
     *
     * @param text the text printed on the action button in textual format and which identify the button.
     * @param toggledText - the text which will appear in the button when it's toggled
     * @param icon the image of the icon inside the button when it's in icon format.
     * @param toggledImage - the icon which will be inside the button in icon format when toggled
     * @param command the command of the ActionEvent given when an action is performed.
     * @param listener the action listener which will be called when the action is performed.
     * @param subId id of the sub-menu in which the action will be added.
     * @param usesImage true if the button is in icon format by default. False otherwise.
     * @param isToggled - true if the button is toggled
     * @return true if the action has been added, so if the sub-menu exists and if there is enough space in it. False
     *         otherwise.
     */
    // CHECKSTYLE:IGNORE ParameterNumberCheck: Can do a fluent API, but it takes time to create it.
    public boolean addAction(String text, String toggledText, PImage icon, PImage toggledImage, String command,
            ActionListener listener, String subId, boolean usesImage, boolean isToggled) {
        RamSubMenu sm = getSubMenu(subId);
        if (sm == null) {
            return false;
        }
        Collections.sort(this.items, this.itemsComparator);
        boolean ret =
                sm.addAction(text, toggledText, icon, toggledImage, command, listener, usesImage, isToggled, true);
        this.draw();
        return ret;
    }

    /**
     * Remove the action identified by the text from the corresponding sub-menu.
     *
     * @param actionCommand the action command corresponding to the action you want to delete.
     * @return true if the action has been removed (if it exists), false otherwise.
     */
    public boolean removeAction(String actionCommand) {
        Map<RamSubMenu, RamMenuAction> toRemove = new HashMap<RamSubMenu, RamMenuAction>();
        for (RamSubMenu sm : this.items) {
            for (int i = 0; i < sm.getActions().size(); i++) {
                RamMenuAction action = sm.getActions().get(i);
                if (action.getActionCommand().equalsIgnoreCase(actionCommand)) {
                    toRemove.put(sm, action);
                    continue;
                }
            }
        }
        for (int i = 0; i < temporarySubMenu.getActions().size(); i++) {
            if (temporarySubMenu.getActions().get(i).getActionCommand().equalsIgnoreCase(actionCommand)) {
                toRemove.put(temporarySubMenu, temporarySubMenu.getActions().get(i));
                break;
            }
        }
        if (toRemove.isEmpty()) {
            return false;
        }
        for (RamSubMenu sm : toRemove.keySet()) {
            sm.getActions().remove(toRemove.get(sm));
            toRemove.get(sm).destroy();
        }
        draw();
        return true;
    }

    /**
     * Add a sub-menu with the priority given in parameter and the unique id.
     *
     * @param priority the priority between 1 to infinity. Allows the menu to show higher (1) priority sub-menu actions
     *            if there is enough space. Lower priority are shown if all the higher priorities are shown.
     * @param subId the sub-menu id which will identify it.
     * @return true if the sub-menu has been added, so if there is enough space and the id doesn't already exists. False
     *         otherwise.
     */
    public boolean addSubMenu(int priority, String subId) {
        if (this.items.size() < MAX_ITEMS_CENTER) {
            RamSubMenu sm = new RamSubMenu(priority, subId, radius / 2);
            boolean ret = this.items.add(sm);
            this.draw();
            return ret;
        }
        return false;

    }

    /**
     * Remove the sub-menu corresponding to the id given in parameter. All the actions inside the sub-menu will be
     * removed.
     *
     * @param subId id of the sub-menu you want to remove.
     * @return true if the sub-menu has been removed, so if the id exists. False otherwise.
     */
    public boolean removeSubMenu(String subId) {
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).getId().equalsIgnoreCase(subId)) {
                this.items.remove(i);
                this.draw();
                return true;
            }
        }
        return false;
    }

    /**
     * Remove all the child into the given sub-menu.
     *
     * @param subId - Sub-menu ID you want to clear
     * @return true if the sub-menu has been cleared
     */
    public boolean clearSubMenu(String subId) {
        RamSubMenu sm = getSubMenu(subId);
        if (sm != null) {
            for (RamMenuAction action : sm.getActions()) {
                action.destroy();
            }
            sm.getActions().clear();
            this.draw();
            return true;
        }
        return false;
    }

    /**
     * Remove all the sub-menus previously added. Main commands are removed too.
     */
    public void removeAll() {
        for (RamSubMenu submenu : items) {
            submenu.destroy();
        }
        this.items.clear();
        if (temporarySubMenu != null) {
            temporarySubMenu.destroy();
            temporarySubMenu = null;
        }
        this.draw();
    }

    /**
     * Get a reference to the action identified by the given id.
     *
     * @param actionCommand - the action command of the button
     * @return a reference to the action
     */
    public RamButton getAction(String actionCommand) {
        for (RamSubMenu sm : items) {
            for (RamButton b : sm.getActions()) {
                if (b.getActionCommand().equals(actionCommand)) {
                    return b;
                }
            }
        }
        return null;
    }

    /**
     * Get a reference to the sub-menu identified by the id given in parameter.
     *
     * @param id the sub-menu id you want to have a reference.
     * @return the reference, or null if the id doesn't exist.
     */
    private RamSubMenu getSubMenu(String id) {
        for (RamSubMenu sm : this.items) {
            if (sm.getId().equalsIgnoreCase(id)) {
                return sm;
            }
        }
        return null;
    }

    /**
     * Enable or disable the given action button.
     *
     * @param enable - true to enable the button
     * @param command - action command of the button
     */
    public void enableAction(boolean enable, String command) {
        RamButton rb = getAction(command);
        if (rb != null) {
            rb.setEnabled(enable);
        }

        if (temporarySubMenu != null) {
            for (RamMenuAction ma : temporarySubMenu.getActions()) {
                if (ma.getActionCommand().equalsIgnoreCase(command)) {
                    ma.setEnabled(enable);
                    return;
                }
            }
        }
    }

    /**
     * Toggle the given action button.
     *
     * @param toggle - true if you want the button toggled
     * @param command - action command of the button
     */
    public void toggleAction(boolean toggle, String command) {
        RamButton rb = getAction(command);
        if (rb != null && rb instanceof RamMenuToggleAction) {
            ((RamMenuToggleAction) rb).toggle(toggle);
        }

        if (temporarySubMenu != null) {
            for (RamMenuAction ma : temporarySubMenu.getActions()) {
                if (ma.getActionCommand().equalsIgnoreCase(command)) {
                    if (ma instanceof RamMenuToggleAction) {
                        ((RamMenuToggleAction) ma).toggle(toggle);
                        return;
                    }
                }
            }
        }
        draw();
    }

    /**
     * The main method which will draw all items of the menu and actions into sub-menus.
     * It will first get the list of items it has to show, then compute the exact angle between items in function of the
     * number of items to show so to its position. Finally it will draw the items.
     */
    protected void draw() {
        Collection<MTComponent> list = createItemsList();
        float angle = 0;
        float startAngle = 0;
        float listSize = list.size();

        if (listSize > 0) {
            if (this.position == Position.CENTER) {
                angle = 360 / listSize;
                startAngle = 0;
            } else if (this.position == Position.EST || this.position == Position.WEST
                    || this.position == Position.NORTH || this.position == Position.SOUTH) {
                if (listSize > 1) {
                    angle = 180 / (listSize - 1);
                }
                if (this.position == Position.EST) {
                    startAngle = 90;
                } else if (this.position == Position.WEST) {
                    startAngle = 270;
                } else if (this.position == Position.NORTH) {
                    startAngle = 0;
                } else if (this.position == Position.SOUTH) {
                    startAngle = 180;
                }

            } else if (this.position == Position.NORTH_EST || this.position == Position.NORTH_WEST
                    || this.position == Position.SOUTH_EST || this.position == Position.SOUTH_WEST) {
                if (listSize > 1) {
                    angle = 90 / (listSize - 1);
                }
                if (this.position == Position.NORTH_EST) {
                    startAngle = 90;
                } else if (this.position == Position.NORTH_WEST) {
                    startAngle = 0;
                } else if (this.position == Position.SOUTH_EST) {
                    startAngle = 180;
                } else if (this.position == Position.SOUTH_WEST) {
                    startAngle = 270;
                }
            }
        }
        drawItems(list, angle, startAngle);

    }

    /**
     * Get the list of items around the menu as they will be drawn.
     * In function of the available space and priority of sub-menus, it will return actions or sub-menus containing
     * actions, and even temporary sub-menu which will contain all the actions which can't be shown because of the left
     * space.
     *
     * @return the list of items around the menu as they will be drawn.
     */
    private Collection<MTComponent> createItemsList() {
        List<MTComponent> ret = new ArrayList<MTComponent>();
        // List of the subMenus we want to put into temporary menu.
        List<RamSubMenu> toTemp = new ArrayList<RamSubMenu>();
        int nbActionsRestantes = this.nbActions();
        int nbPlace;
        if (this.isOnEdge()) {
            nbPlace = MAX_ITEMS_EDGE;
            // If there is more actions than places, then we'll add at least one sub-menu
            if (nbActionsRestantes > nbPlace) {
                nbPlace--;
            }
        } else if (this.isInCorner()) {
            nbPlace = MAX_ITEMS_CORNER;
            // If there is more actions than places, then we'll add at least one sub-menu
            if (nbActionsRestantes > nbPlace) {
                nbPlace--;
            }
        } else {
            nbPlace = MAX_ITEMS_CENTER;
        }

        boolean tempOpened = false;
        if (temporarySubMenu != null) {
            tempOpened = temporarySubMenu.isActionVisible();
            temporarySubMenu.destroy();
        }
        temporarySubMenu = new RamSubMenu(0, TEMPORARY_SUBMENU_ID, radius / 2);
        temporarySubMenu.hideActions(!tempOpened);
        for (RamSubMenu sm : this.items) {

            if (sm.getActions().isEmpty()) {
                continue;
            }

            int nbCurrentAction = sm.getActions().size();

            nbActionsRestantes -= nbCurrentAction;
            // If it's the last action to add and the temporary sub-menu is empty, then we replace it by the action
            if (nbCurrentAction == 1 && nbActionsRestantes == 0 && toTemp.size() == 0) {
                nbPlace = 1;
            }
            if (nbCurrentAction > nbPlace) {
                if (nbPlace == 0) {
                    toTemp.add(sm);
                } else {
                    if (sm.getActions().size() == 1) {
                        ret.add(sm.getActions().get(0));
                    } else {
                        ret.add(sm);
                    }
                    nbPlace--;
                }
            } else if (nbCurrentAction == nbPlace) {
                if (nbActionsRestantes > 0) {
                    if (sm.getActions().size() == 1) {
                        ret.add(sm.getActions().get(0));
                    } else {
                        ret.add(sm);
                    }
                    nbPlace--;
                } else {
                    ret.addAll(sm.getActions());
                    nbPlace -= sm.getActions().size();
                }
            } else {
                if (nbCurrentAction < nbPlace) {
                    ret.addAll(sm.getActions());
                    nbPlace -= sm.getActions().size();
                } else {
                    if (sm.getActions().size() == 1) {
                        ret.add(sm.getActions().get(0));
                    } else {
                        ret.add(sm);
                    }
                    nbPlace--;
                }
            }
        }
        // Add subMenus to the temporary menu
        if (toTemp.size() > 1) {
            for (RamSubMenu sm : toTemp) {
                for (RamMenuAction action : sm.getActions()) {

                    if (action instanceof RamMenuToggleAction) {
                        RamMenuToggleAction toggledAction = (RamMenuToggleAction) action;
                        if (toggledAction.getIcon() != null) {
                            temporarySubMenu.addAction(toggledAction.getText(), toggledAction.getToggledText(),

                                    toggledAction.getIcon().getTexture(), toggledAction.getToggledIcon().getTexture(),

                                    toggledAction.getActionCommand(), toggledAction.getListener(),
                                    toggledAction.isUsingImage(), toggledAction.isToggled(), toggledAction.isEnabled());
                        } else {
                            temporarySubMenu.addAction(toggledAction.getText(), toggledAction.getToggledText(),
                                    toggledAction.getActionCommand(), toggledAction.getListener(),
                                    toggledAction.isToggled(), toggledAction.isEnabled());
                        }
                    } else {
                        if (action.getIcon() != null) {
                            temporarySubMenu.addAction(action.getText(), action.getIcon().getTexture(),
                                    action.getActionCommand(), action.getListener(), action.isUsingImage(),
                                    action.isEnabled());
                        } else {
                            temporarySubMenu.addAction(action.getText(), action.getActionCommand(),
                                    action.getListener(), action.isEnabled());
                        }
                    }

                    // If at least one sub-menu of the temporary sub-menu is opened, open it.
                    if (sm.isActionVisible()) {
                        tempOpened = true;
                    }
                }
            }
            // Open the menu if it was before or if one of the contained menu was opened before
            if (tempOpened) {
                temporarySubMenu.hideActions(false);
            }
        } else if (toTemp.size() == 1) {
            ret.add(toTemp.get(0));
        }

        if (temporarySubMenu.getActions().size() > 0) {
            ret.add(temporarySubMenu);
        }

        return ret;
    }

    /**
     * Draw a list of items around the menu with a start angle and an angle between items.
     * This method call the draw method of the sub-menu which will be drawn, in order to draw its actions.
     *
     * @param collection the collection of items which will be drawn around the menu
     * @param angle the angle between items
     * @param startAngle the start angle where will be drawn the first item of the list.
     */
    private void drawItems(Collection<? extends MTComponent> collection, float angle, float startAngle) {
        float degre = startAngle;

        Vector3D coordinates = new Vector3D();
        Vector3D menuPosition = this.getCenterPointLocal();
        menuPosition.x -= getWidth() / 2;

        // Clean and redraw childs
        this.removeAllChildren();

        for (MTComponent c : collection) {
            // trigonometry and move the (0,0) point for childs which is the center point of the parent (-->
            // ROUNDED_RECTANGLE_RADIUS / 2)
            float itemX = (float) (this.itemRadius * Math.cos(degre * Math.PI / 180));
            float itemY = (float) (this.itemRadius * Math.sin(degre * Math.PI / 180));

            showLabelsOnAction(labelShown);

            if (c instanceof RamSubMenu) {
                RamSubMenu sm = (RamSubMenu) c;
                sm.setPositionRelativeToParent(new Vector3D(itemX + menuPosition.x, itemY + menuPosition.y));
                sm.draw(degre, itemX, itemY, this.getCenterPointGlobal().x, position);

            } else if (c instanceof RamButton) {
                RamButton b = (RamButton) c;
                if (b.isUsingImage()) {
                    coordinates.x = itemX - b.getWidth() / 2;
                    coordinates.y = itemY - b.getHeight() / 2;
                    b.setPositionRelativeToParent(new Vector3D(coordinates.x, coordinates.y));
                } else {
                    float degreMod = degre % 360;

                    if (degreMod > 0 && degreMod < 90) {
                        itemX += menuPosition.x;
                        itemY += menuPosition.y;
                    } else if (degreMod > 90 && degreMod < 180) {
                        itemX += menuPosition.x - b.getWidth();
                        itemY += menuPosition.y;
                    } else if (degreMod > 180 && degreMod < 270) {
                        itemX += menuPosition.x - b.getWidth();
                        itemY += menuPosition.y - b.getHeight();
                    } else if (degreMod > 270 && degreMod < 360) {
                        itemX += menuPosition.x;
                        itemY += menuPosition.y - b.getHeight();
                    } else if (degreMod == 0) {
                        itemX += menuPosition.x;
                        itemY += menuPosition.y - b.getHeight() / 2;
                    } else if (degreMod == 90) {
                        itemX += menuPosition.x - b.getWidth() / 2;
                        itemY += menuPosition.y;
                    } else if (degreMod == 180) {
                        itemX += menuPosition.x - b.getWidth();
                        itemY += menuPosition.y - b.getHeight() / 2;
                    } else if (degreMod == 270) {
                        itemX += menuPosition.x - b.getWidth() / 2;
                        itemY += menuPosition.y - b.getHeight();
                    }

                    if (isOnEdge() || isInCorner()) {
                        float itemXRelatedToGlobal = this.getCenterPointGlobal().x + itemX;
                        float rectangleWidth = b.getWidth();
                        float rectangleTopRightCornerPositionX = itemXRelatedToGlobal + rectangleWidth;
                        float sceneWidth = RamApp.getApplication().getWidth();
                        if (itemXRelatedToGlobal < 0) {
                            itemX -= itemXRelatedToGlobal;
                        }
                        if (rectangleTopRightCornerPositionX > sceneWidth) {
                            itemX -= rectangleTopRightCornerPositionX - sceneWidth;
                        }
                    }

                    coordinates.x = itemX;
                    coordinates.y = itemY;
                    b.setPositionRelativeToParent(coordinates);
                }

            }

            c.setVisible(true);
            this.addChild(c);

            if (c instanceof RamMenuAction) {
                RamMenuAction ma = (RamMenuAction) c;
                ma.draw(position, degre, true, false, getCenterPointGlobal().x - ma.getWidthXY(TransformSpace.GLOBAL),
                        ma.getPosition(TransformSpace.RELATIVE_TO_PARENT));
            }

            degre += angle;
        }
    }

    /**
     * Get the coordinates of the trigger in the top left of the menu.
     *
     * @return the position of the trigger in the top left of the menu.
     */
    private Vector3D getNorthWestTriggerPosition() {
        Vector3D pos = this.getCenterPointGlobal();
        return new Vector3D(pos.x - this.triggerSize, pos.y - this.triggerSize);
    }

    /**
     * Get the coordinates of the trigger in the top right of the menu.
     *
     * @return the position of the trigger in the top right of the menu.
     */
    private Vector3D getNorthEstTriggerPosition() {
        Vector3D pos = this.getCenterPointGlobal();
        return new Vector3D(pos.x + this.triggerSize, pos.y - this.triggerSize);

    }

    /**
     * Get the coordinates of the trigger in the bottom left of the menu.
     *
     * @return the position of the trigger in the bottom left of the menu.
     */
    private Vector3D getSouthWestTriggerPosition() {
        Vector3D pos = this.getCenterPointGlobal();
        return new Vector3D(pos.x - this.triggerSize, pos.y + this.triggerSize);
    }

    /**
     * Get the coordinates of the trigger in the bottom right of the menu.
     *
     * @return the position of the trigger in the bottom right of the menu.
     */
    private Vector3D getSouthEstTriggerPosition() {
        Vector3D pos = this.getCenterPointGlobal();
        return new Vector3D(pos.x + this.triggerSize, pos.y + this.triggerSize);
    }

    /**
     * Update the menu position parameter in function of its position on the screen.
     * It creates a virtual square into the menu circle to detect if the menu is on edge or in corner.
     *
     * @param source position of the center of the menu circle
     * @param isDragged true if the position of the menu has been changed during a drag action
     */
    public void updateMenuPosition(Vector3D source, boolean isDragged) {
        float windowWidth = RamApp.getActiveScene().getWidth();
        float windowHeight = RamApp.getActiveScene().getHeight();

        // Get internal menu trigger. It's a square inside the menu ellipse.
        Vector3D nw = getNorthWestTriggerPosition();
        Vector3D ne = getNorthEstTriggerPosition();
        Vector3D sw = getSouthWestTriggerPosition();
        Vector3D se = getSouthEstTriggerPosition();

        // Edges triggers
        boolean topTrigger = false;
        boolean leftTrigger = false;
        boolean bottomTrigger = false;
        boolean rightTrigger = false;

        // Center triggers
        boolean leftWindowTrigger = false;
        boolean rightWindowTrigger = false;
        boolean topWindowTrigger = false;
        boolean bottomWindowTrigger = false;

        if (isDragged) {
            topTrigger = nw.y < 0 && ne.y < 0;
            leftTrigger = nw.x < 0 && sw.x < 0;
            bottomTrigger = sw.y > windowHeight && se.y > windowHeight;
            rightTrigger = ne.x > windowWidth && se.x > windowWidth;
        } else {
            leftWindowTrigger = source.x < windowWidth / 2;
            rightWindowTrigger = source.x >= windowWidth / 2;
            topWindowTrigger = source.y < windowHeight / 2;
            bottomWindowTrigger = source.y >= windowHeight / 2;
        }

        if (this.position != Position.CENTER && !isDragged) {
            if (this.position == Position.NORTH_EST) {
                setMenuInCenterTopRight(windowWidth, windowHeight);
            } else if (this.position == Position.NORTH_WEST) {
                setMenuInCenterTopLeft(windowWidth, windowHeight);
            } else if (this.position == Position.SOUTH_WEST) {
                setMenuInCenterBottomLeft(windowWidth, windowHeight);
            } else if (this.position == Position.SOUTH_EST) {
                setMenuInCenterBottomRight(windowWidth, windowHeight);
            } else if (this.position == Position.NORTH) {
                setMenuInCenterTop(source.x, windowHeight);
            } else if (this.position == Position.SOUTH) {
                setMenuInCenterBottom(source.x, windowHeight);
            } else if (this.position == Position.WEST) {
                setMenuInCenterLeft(source.y, windowWidth);
            } else if (this.position == Position.EST) {
                setMenuInCenterRight(source.y, windowWidth);
            } else {
                return;
            }
        } else {
            if ((topTrigger && leftTrigger) || (leftWindowTrigger && topWindowTrigger && !isDragged)) {
                setMenuInTopLeft();
            } else if ((topTrigger && rightTrigger) || (rightWindowTrigger && topWindowTrigger && !isDragged)) {
                setMenuInTopRight(windowWidth);
            } else if ((bottomTrigger && leftTrigger) || (leftWindowTrigger && bottomWindowTrigger && !isDragged)) {
                setMenuInBottomLeft(windowHeight);
            } else if ((bottomTrigger && rightTrigger) || (rightWindowTrigger && bottomWindowTrigger && !isDragged)) {
                setMenuInBottomRight(windowWidth, windowHeight);
            } else if (topTrigger) {
                setMenuOnTop(source.x);
            } else if (leftTrigger) {
                setMenuOnLeft(source.y);
            } else if (bottomTrigger) {
                setMenuOnBottom(source.x, windowHeight);
            } else if (rightTrigger) {
                setMenuOnRight(source.y, windowWidth);
            } else {
                return;
            }
        }
        this.draw();
    }

    /**
     * Set the menu a the position specified in parameter.
     *
     * @param pos the future position of the menu
     */
    private void setMenuAtPosition(Vector3D pos) {
        setPositionGlobal(new Vector3D(pos.x, pos.y - getHeight() / 2));
    }

    /**
     * Set the menu at the center.
     *
     * @param x - the X position
     * @param y - the Y position
     */
    public void setMenuInCenter(float x, float y) {
        setMenuAtPosition(new Vector3D(x, y));
        this.position = Position.CENTER;
    }

    /**
     * Set the menu at the top center.
     *
     * @param x the X position
     * @param windowHeight height of the window
     */
    public void setMenuInCenterTop(float x, float windowHeight) {
        setMenuAtPosition(new Vector3D(x, (windowHeight / 4) + Constants.MENU_BAR_HEIGHT));
        this.position = Position.CENTER;
    }

    /**
     * Set the menu at the bottom center.
     *
     * @param x the X position
     * @param windowHeight height of the window
     */
    public void setMenuInCenterBottom(float x, float windowHeight) {
        setMenuAtPosition(new Vector3D(x, 3 * windowHeight / 4));
        this.position = Position.CENTER;
    }

    /**
     * Set the menu at the bottom left.
     *
     * @param y the Y position
     * @param windowWidth width of the window
     */
    public void setMenuInCenterLeft(float y, float windowWidth) {
        setMenuAtPosition(new Vector3D(windowWidth / 4, y));
        this.position = Position.CENTER;
    }

    /**
     * Set the menu at the bottom left.
     *
     * @param y the Y position
     * @param windowWidth width of the window
     */
    public void setMenuInCenterRight(float y, float windowWidth) {
        setMenuAtPosition(new Vector3D(3 * windowWidth / 4, y));
        this.position = Position.CENTER;
    }

    /**
     * Set the menu at the center top left.
     *
     * @param windowWidth width of the window
     * @param windowHeight height of the window
     */
    public void setMenuInCenterTopLeft(float windowWidth, float windowHeight) {
        setMenuAtPosition(new Vector3D(windowWidth / 4, windowHeight / 4 + Constants.MENU_BAR_HEIGHT));
        this.position = Position.CENTER;
    }

    /**
     * Set the menu at the center bottom right.
     *
     * @param windowWidth width of the window
     * @param windowHeight height of the window
     */
    public void setMenuInCenterBottomRight(float windowWidth, float windowHeight) {
        setMenuAtPosition(new Vector3D(3 * windowWidth / 4, 3 * windowHeight / 4));
        this.position = Position.CENTER;
    }

    /**
     * Set the menu at the center bottom left.
     *
     * @param windowWidth width of the window
     * @param windowHeight height of the window
     */
    public void setMenuInCenterBottomLeft(float windowWidth, float windowHeight) {
        setMenuAtPosition(new Vector3D(windowWidth / 4, 3 * windowHeight / 4));
        this.position = Position.CENTER;
    }

    /**
     * Set the menu at the center top right.
     *
     * @param windowWidth width of the window
     * @param windowHeight height of the window
     */
    public void setMenuInCenterTopRight(float windowWidth, float windowHeight) {
        setMenuAtPosition(new Vector3D(3 * windowWidth / 4, windowHeight / 4));
        this.position = Position.CENTER;
    }

    /**
     * Set the menu in the corner top-left with margins.
     */
    public void setMenuInTopLeft() {
        setMenuAtPosition(new Vector3D(this.marginCorners, this.marginCorners + Constants.MENU_BAR_HEIGHT));
        this.position = Position.NORTH_WEST;
    }

    /**
     * Set the menu in the corner top-right with margins.
     *
     * @param windowWidth width of the window
     */
    public void setMenuInTopRight(float windowWidth) {
        setMenuAtPosition(new Vector3D(windowWidth - this.marginCorners,
                this.marginCorners + Constants.MENU_BAR_HEIGHT));
        this.position = Position.NORTH_EST;
    }

    /**
     * Set the menu in the corner bottom-left with margins.
     *
     * @param windowHeight height of the window
     */
    public void setMenuInBottomLeft(float windowHeight) {
        setMenuAtPosition(new Vector3D(this.marginCorners, windowHeight - this.marginCorners));
        this.position = Position.SOUTH_WEST;
    }

    /**
     * Set the menu in the corner bottom-right with margins.
     *
     * @param windowWidth width of the window
     * @param windowHeight height of the window
     */
    public void setMenuInBottomRight(float windowWidth, float windowHeight) {
        setMenuAtPosition(new Vector3D(windowWidth - this.marginCorners, windowHeight - this.marginCorners));
        this.position = Position.SOUTH_EST;
    }

    /**
     * Set the menu on the top of the screen at the x position with margins.
     *
     * @param x the X position
     */
    public void setMenuOnTop(float x) {
        setMenuAtPosition(new Vector3D(x, this.marginCorners + Constants.MENU_BAR_HEIGHT));
        this.position = Position.NORTH;
    }

    /**
     * Set the menu on the left of the screen at the y position with margins.
     *
     * @param y the Y position
     */
    public void setMenuOnLeft(float y) {
        setMenuAtPosition(new Vector3D(this.marginCorners, y));
        this.position = Position.WEST;
    }

    /**
     * Set the menu on the right of the screen at the y position with margins.
     *
     * @param y the Y position
     * @param windowWidth width of the window
     */
    public void setMenuOnRight(float y, float windowWidth) {
        setMenuAtPosition(new Vector3D(windowWidth - this.marginCorners, y));
        this.position = Position.EST;
    }

    /**
     * Set the menu on the bottom of the screen at the x position with margins.
     *
     * @param x the X position
     * @param windowHeight height of the window
     */
    public void setMenuOnBottom(float x, float windowHeight) {
        setMenuAtPosition(new Vector3D(x, windowHeight - this.marginCorners));
        this.position = Position.SOUTH;
    }

    /**
     * To know if the menu is into a corner.
     *
     * @return true if the menu is into a corner
     */
    private boolean isInCorner() {
        return this.position == Position.NORTH_EST || this.position == Position.NORTH_WEST
                || this.position == Position.SOUTH_EST || this.position == Position.SOUTH_WEST;
    }

    /**
     * To know if the menu is on an edge.
     *
     * @return true if the menu is on an edge
     */
    private boolean isOnEdge() {
        return this.position == Position.NORTH || this.position == Position.WEST || this.position == Position.EST
                || this.position == Position.SOUTH;

    }

    /**
     * Get the current menu radius.
     *
     * @return the current menu radius
     */
    public int getRadius() {
        return radius;
    }

    /**
     * Show labels around the action button.
     *
     * @param show - true if you want to show labels
     */
    private void showLabelsOnAction(boolean show) {
        List<RamSubMenu> allSubMenus = new ArrayList<RamSubMenu>();
        allSubMenus.addAll(items);
        allSubMenus.add(temporarySubMenu);
        for (RamSubMenu sm : allSubMenus) {
            for (RamMenuAction ra : sm.getActions()) {
                RamTextComponent label = ra.getLabel();
                if (label != null) {
                    if (ra.isVisible()) {
                        label.setVisible(show);
                    }
                }
            }
            sm.setLabelShown(show);
        }
    }

    /**
     * Get the number of actions of the menu.
     *
     * @return the number of actions of the menu
     */
    private int nbActions() {
        int ret = 0;
        for (RamSubMenu sm : this.items) {
            ret += sm.getActions().size();
        }
        return ret;
    }

    @Override
    protected void setDefaultGestureActions() {
        // NOTE - make sure to override and not call super, otherwise extra input processors will be added to this
        // component.
        // This causes gestures to be handled twice and makes it look like drag events are scaled wrong.
    }

    @Override
    public void destroy() {
        for (RamSubMenu sm : items) {
            sm.destroy();
        }
        items.clear();
        if (temporarySubMenu != null) {
            temporarySubMenu.destroy();
            temporarySubMenu = null;
        }
        super.destroy();
    }

    /**
     * Handler of the menu which implements the drag, tap and tap&hold reaction.
     *
     * @author g.Nicolas
     *
     */
    protected class RamMenuHandler extends BaseHandler implements ITapListener, IDragListener, ITapAndHoldListener {

        private DelayedDrag defaultHandler = new DelayedDrag(10);
        private boolean isDragged;

        /**
         * Constructs the menu handler.
         */
        RamMenuHandler() {
            isDragged = false;
        }

        @Override
        public boolean processTapAndHoldEvent(TapAndHoldEvent tapAndHoldEvent) {
            if (tapAndHoldEvent.isHoldComplete()) {
                labelShown = !labelShown;
                showLabelsOnAction(labelShown);
            }
            return false;
        }

        @Override
        public boolean processTapEvent(TapEvent tapEvent) {
            // only proceed if button is enabled (although this seems to be taken care of by MT4j already)
            if (tapEvent.isTapped() && isEnabled()) {
                updateMenuPosition(tapEvent.getCursor().getPosition(), false);
            }
            return true;
        }

        @Override
        public boolean processDragEvent(DragEvent dragEvent) {
            defaultHandler.processGestureEvent(dragEvent);
            if (defaultHandler.wasDragPerformed()) {
                switch (dragEvent.getId()) {
                    case MTGestureEvent.GESTURE_UPDATED:
                        isDragged = true;
                        position = Position.CENTER;
                        draw();
                        break;
                    case MTGestureEvent.GESTURE_ENDED:
                        if (isDragged) {
                            updateMenuPosition(dragEvent.getDragCursor().getPosition(), true);
                            isDragged = false;
                        }
                        break;
                }
            }

            return true;
        }
    }
}
