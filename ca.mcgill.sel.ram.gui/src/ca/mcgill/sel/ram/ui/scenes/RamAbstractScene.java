package ca.mcgill.sel.ram.ui.scenes;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.ecore.EObject;
import org.mt4j.components.MTComponent;
import org.mt4j.input.gestureAction.TapAndHoldVisualizer;
import org.mt4j.input.inputProcessors.componentProcessors.tapAndHoldProcessor.TapAndHoldProcessor;
import org.mt4j.input.inputProcessors.globalProcessors.CursorTracer;
import org.mt4j.sceneManagement.AbstractScene;
import org.mt4j.sceneManagement.ISceneChangeListener;
import org.mt4j.sceneManagement.Iscene;
import org.mt4j.sceneManagement.SceneChangeEvent;
import org.mt4j.sceneManagement.transition.ITransition;
import org.mt4j.util.MT4jSettings;
import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamButton;
import ca.mcgill.sel.ram.ui.components.RamPopup;
import ca.mcgill.sel.ram.ui.components.RamPopup.PopupType;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.components.menu.RamMenu;
import ca.mcgill.sel.ram.ui.events.listeners.ActionListener;
import ca.mcgill.sel.ram.ui.scenes.handler.IRamAbstractSceneHandler;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.utils.Icons;
import ca.mcgill.sel.ram.ui.utils.Strings;

/**
 * RamAbstractScene is the top class which define the basic structure for sub-classes with the menu and a main layer.
 *
 * @author g.Nicolas
 * @param <T> Generic type for the handler of the scene
 */
public abstract class RamAbstractScene<T extends IRamAbstractSceneHandler> extends AbstractScene
        implements ISceneChangeListener, ActionListener {

    // Actions for the default menu
    private static final String ACTION_SAVE = "action.save";
    private static final String ACTION_UNDO = "action.undo";
    private static final String ACTION_REDO = "action.redo";

    /**
     * Handler for the scene.
     */
    protected T handler;
    /**
     * Layer in which all components will be added for the scene.
     */
    protected MTComponent containerLayer;
    /**
     * Menu.
     */
    protected RamMenu menu;

    private final RamApp application;
    private Iscene previousScene;
    private Iscene previousNextScene;
    private boolean defaultActions;
    private CommandStackListener commandStackListener;
    private MTComponent topLayer;

    private List<RamRectangleComponent> tempComponents;

    /**
     * Constructs a RamAbstractScene with the default actions in the menu.
     * 
     * @param application - The current {@link RamApp}
     * @param name - the name of the scene
     */
    public RamAbstractScene(final RamApp application, final String name) {
        this(application, name, true);
    }

    /**
     * Constructs a RamAbstractScene.
     *
     * @param application - The current {@link RamApp}
     * @param name - the name of the scene
     * @param addDefaultActions - true if you want default actions in the menu
     */
    public RamAbstractScene(final RamApp application, final String name, boolean addDefaultActions) {
        super(application, name);

        this.application = application;
        this.defaultActions = addDefaultActions;
        this.tempComponents = new ArrayList<RamRectangleComponent>();

        // create scene
        setClearColor(Colors.BACKGROUND_COLOR);

        registerGlobalInputProcessor(new CursorTracer(application, this));
        application.addSceneChangeListener(this);

        initDisplay();
    }

    /**
     * Initialize the scene creating the menu, positioning it, adding default actions if needed and layers
     * initialization.
     */
    private void initDisplay() {
        this.menu = new RamMenu();
        this.menu.setMenuInTopRight(getWidth());
        if (this.defaultActions) {
            addDefaultActionsInMenu();
        }
        initMenu();

        // menus and the items that we want to put on top of the structural view are added in this layer
        topLayer = new MTComponent(application, "top layer group");

        // views are added into this layer
        containerLayer = new MTComponent(application, "container layer");

        topLayer.addChild(this.containerLayer);
        topLayer.addChild(this.menu);
        menu.addGestureListener(TapAndHoldProcessor.class, new TapAndHoldVisualizer(RamApp.getApplication(), topLayer));
        getCanvas().addChild(topLayer);

    }

    /**
     * Initialize the menu.
     */
    protected abstract void initMenu();

    /**
     * Add the default actions in the menu and initialize them.
     */
    protected void addDefaultActionsInMenu() {
        this.menu.addAction(Strings.MENU_SAVE, Icons.ICON_MENU_SAVE, ACTION_SAVE, this, true);
        this.menu.addAction(Strings.MENU_UNDO, Icons.ICON_MENU_UNDO, ACTION_UNDO, this, true);
        this.menu.addAction(Strings.MENU_REDO, Icons.ICON_MENU_REDO, ACTION_REDO, this, true);

        if (getElementToSave() != null && commandStackListener == null) {
            setCommandStackListener(getElementToSave());
        }
    }

    /**
     * Function called to add a component to the topLayer.
     * 
     * @param rectangle - the rectangle to add
     * @param location - where to put the selector
     */
    public void addComponent(RamRectangleComponent rectangle, Vector3D location) {
        topLayer.addChild(rectangle);
        // Position the selector centered on the menu
        location.x -= rectangle.getWidth() / 2;
        location.y -= rectangle.getHeight() / 2;

        if ((location.getX() + rectangle.getWidth()) > getWidth()) {
            location.x -= (location.getX() + rectangle.getWidth()) - getWidth();
        } else if (location.getX() < 0) {
            location.x -= location.x;
        }
        if ((location.getY() + rectangle.getHeight()) > getHeight()) {
            location.y -= (location.getY() + rectangle.getHeight()) - getHeight();
        } else if (location.getY() < 0) {
            location.y -= location.y;
        }
        rectangle.translate(location.getX(), location.getY());
        this.tempComponents.add(rectangle);
    }

    /**
     * Initialize a listener for the commandStack.
     * 
     * @param elementToSave - The element that will be modified.
     */
    protected final void setCommandStackListener(EObject elementToSave) {
        if (elementToSave == null || commandStackListener != null) {
            return;
        }
        // Add listener to command stack for updating of Save/Redo/Undo buttons.
        final BasicCommandStack commandStack = EMFEditUtil.getCommandStack(elementToSave);
        updateDefaultActionsStatus(commandStack);
        commandStackListener = new CommandStackListener() {
            @Override
            public void commandStackChanged(EventObject event) {
                // disable/enable buttons depending on whether undo/redo is possible
                updateDefaultActionsStatus(commandStack);
            }
        };
        commandStack.addCommandStackListener(commandStackListener);
    }

    /**
     * Update default actions state in the RamMenu in function of the command stack.
     * 
     * @param commandStack - the command stack
     */
    private void updateDefaultActionsStatus(BasicCommandStack commandStack) {
        RamButton rb = menu.getAction(ACTION_SAVE);
        if (rb != null) {
            rb.setEnabled(commandStack.isSaveNeeded());
        }
        rb = menu.getAction(ACTION_UNDO);
        if (rb != null) {
            rb.setEnabled(commandStack.canUndo());
        }
        rb = menu.getAction(ACTION_REDO);
        if (rb != null) {
            rb.setEnabled(commandStack.canRedo());
        }
    }

    /**
     * Method used to retrieve the element to save in the current scene.
     *
     * @return The EObject we want to save for this scene.
     */
    protected abstract EObject getElementToSave();

    @Override
    public boolean destroy() {
        if (commandStackListener != null) {
            EMFEditUtil.getCommandStack(getElementToSave()).removeCommandStackListener(commandStackListener);
        }

        application.removeSceneChangeListener(this);

        return super.destroy();
    }

    /**
     * Display the given {@link RamPopup}.
     *
     * @param popup - The {@link RamPopup} to display.
     */
    public void displayPopup(final RamPopup popup) {
        popup.translate((getWidth() - popup.getWidth()) / 2, (getHeight() - popup.getHeight()) / 2);
        getCanvas().addChild(popup);
    }

    /**
     * Display a {@link RamPopup}.
     *
     * @param popup - The text to display in the popup.
     */
    public void displayPopup(final String popup) {
        displayPopup(new RamPopup(popup, true));
    }

    /**
     * Display a {@link RamPopup}.
     *
     * @param popup - The text to display in the popup.
     * @param popupType - The type of popup.
     */
    public void displayPopup(final String popup, PopupType popupType) {
        displayPopup(new RamPopup(popup, true, popupType));
    }

    /**
     * Get the RamMenu.
     *
     * @return the RamMenu
     */
    public RamMenu getMenu() {
        return menu;
    }

    /**
     * Get the {@link RamApp}.
     *
     * @return the RamApp
     */
    public RamApp getApplication() {
        return application;
    }

    /**
     * Get the height of the app.
     * 
     * @return the height.
     */
    public float getHeight() {
        return application.getHeight();
    }

    /**
     * Get the width of the app.
     * 
     * @return the width.
     */
    public float getWidth() {
        return application.getWidth();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (handler != null) {
            String actionCommand = event.getActionCommand();
            if (ACTION_SAVE.equals(actionCommand)) {
                handler.save(getElementToSave());
                return;
            } else if (ACTION_UNDO.equals(actionCommand)) {
                handler.undo(getElementToSave());
                return;
            } else if (ACTION_REDO.equals(actionCommand)) {
                handler.redo(getElementToSave());
                return;
            }
        }
    }

    /**
     * Get the container layer.
     * 
     * @return the container layer
     */
    public MTComponent getContainerLayer() {
        return containerLayer;
    }

    /**
     * Get the RamAbstractScene handler.
     * 
     * @return the scene handler
     */
    public T getHandler() {
        return handler;
    }

    /**
     * Set the RamAbstractScene handler.
     * 
     * @param handler - the RamAbstractScene handler
     */
    public void setHandler(T handler) {
        this.handler = handler;
    }

    @Override
    public void setTransition(final ITransition transition) {
        if (MT4jSettings.getInstance().isOpenGlMode()) {
            super.setTransition(transition);
        }
        // P3D rendering doesn't seem to support transitions
    }

    /**
     * Ask this scene whether it is acceptable to exit the application.
     * Sub-classes can override this method in order to implement certain checks
     * that make sure that is is acceptable for that particular scene to exit.
     * For example, this can include requesting the user to confirm.
     * 
     * @return true, if this scene can be quit, false otherwise
     */
    public boolean shouldExit() {
        return true;
    }

    /**
     * Returns the previous scene.
     * The previous scene is the scene previously switched from to this one.
     * 
     * @return the previous scene
     */
    public Iscene getPreviousScene() {
        return previousScene;
    }

    @Override
    public void processSceneChangeEvent(SceneChangeEvent sc) {
        // Also store the previous next scene (the scene switched to from this one),
        // because when coming back from the previous scene, it should not be
        // overwritten with the last scene (switched from), but the actual
        // previous scene should be kept.
        if (sc.getNewScene() == this && previousNextScene != sc.getLastScene()) {
            previousScene = sc.getLastScene();
        } else if (sc.getLastScene() == this) {
            previousNextScene = sc.getNewScene();
        }
    }

    /**
     * Removes all temporary components on the top Layer.
     * It can be selectors, menus, etc...
     */
    protected void clearTemporaryComponents() {
        for (RamRectangleComponent rect : this.tempComponents) {
            rect.destroy();
        }
    }

}
