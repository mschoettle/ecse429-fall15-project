package ca.mcgill.sel.ram.ui.scenes;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.mt4j.components.MTComponent;
import org.mt4j.components.visibleComponents.shapes.MTRectangle.PositionAnchor;
import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.commons.emf.util.EMFModelUtil;
import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.ram.AbstractMessageView;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.RamFactory;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.StructuralView;
import ca.mcgill.sel.ram.impl.ContainerMapImpl;
import ca.mcgill.sel.ram.impl.RamFactoryImpl;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.ConfirmPopup;
import ca.mcgill.sel.ram.ui.components.ConfirmPopup.OptionType;
import ca.mcgill.sel.ram.ui.components.RamPanelComponent;
import ca.mcgill.sel.ram.ui.components.listeners.RamPanelListener;
import ca.mcgill.sel.ram.ui.utils.Constants;
import ca.mcgill.sel.ram.ui.utils.Fonts;
import ca.mcgill.sel.ram.ui.utils.GraphicalUpdater;
import ca.mcgill.sel.ram.ui.utils.Icons;
import ca.mcgill.sel.ram.ui.utils.LoggerUtils;
import ca.mcgill.sel.ram.ui.utils.Strings;
import ca.mcgill.sel.ram.ui.utils.validation.ValidationManager;
import ca.mcgill.sel.ram.ui.views.TextView;
import ca.mcgill.sel.ram.ui.views.containers.TracingView;
import ca.mcgill.sel.ram.ui.views.containers.ValidationView;
import ca.mcgill.sel.ram.ui.views.handler.HandlerFactory;
import ca.mcgill.sel.ram.ui.views.handler.IDisplaySceneHandler;
import ca.mcgill.sel.ram.ui.views.message.MessageViewView;
import ca.mcgill.sel.ram.ui.views.message.old.MessageViewContainer;
import ca.mcgill.sel.ram.ui.views.state.StateDiagramView;
import ca.mcgill.sel.ram.ui.views.structural.InstantiationSplitEditingView;
import ca.mcgill.sel.ram.ui.views.structural.InstantiationsPanel;
import ca.mcgill.sel.ram.ui.views.structural.StructuralDiagramView;
import ca.mcgill.sel.ram.util.RAMModelUtil;
import ca.mcgill.sel.ram.validator.Validator;

/**
 * This scene displays everything relevant to an aspect. TODO write up
 *
 * @author vbonnet
 * @author mschoettle
 */
public class DisplayAspectScene extends RamAbstractScene<IDisplaySceneHandler>
        implements INotifyChangedListener, RamPanelListener {

    // Actions for the scene
    private static final String ACTION_TOGGLE_VIEWS = "display.toggle.views";
    private static final String ACTION_GENERATE_CODE = "display.generate.code";
    private static final String ACTION_BACK = "display.back";
    private static final String ACTION_OPEN_VALIDATOR = "display.validator";
    private static final String ACTION_CONCERN_BACK = "display.concern.back";
    private static final String ACTION_OPEN_TRACING = "display.tracing";

    private static final String ACTION_WEAVE_STATE_MACHINES = "display.weave.state.machines";
    private static final String ACTION_WEAVE_STATE_MACHINES_NO_CSP = "display.weave.state.machines.no.csp";
    private static final String ACTION_CLOSE_SPLIT_VIEW = "display.close.splitview";

    // Name of the submenus
    private static final String SUBMENU_WEAVE = "sub.weave";
    private static final String SUBMENU_GOTO = "sub.goto";
    private static final String SUBMENU_SHOWPANELS = "sub.panel";
    private static final String SUBMENU_GENERATE = "sub.gen";
    private static final String SUBMENU_BACK = "sub.back";

    // Default Y location for tracing view
    private static final float TRACING_DEFAULT_Y = Constants.MENU_BAR_HEIGHT + 80;

    private StructuralDiagramView structuralView;
    private MessageViewContainer messageViewContainer;
    private StateDiagramView stateDiagramView;
    private Map<AbstractMessageView, MessageViewView> messageViewViews;
    private TextView aspectName;

    private ValidationView aspectValidatorView;
    private ValidationManager validatorLauncher;

    private TracingView tracingView;

    private GraphicalUpdater graphicalUpdater;

    private Aspect aspect;

    private MTComponent viewContainer;
    private InstantiationsPanel instantiationsPanel;
    private Stack<MTComponent> previousViews;
    private MTComponent currentView;

    /**
     * Creates a scene that will display this aspect.
     *
     * @param app
     *            The application to which the scene belongs.
     * @param aspect
     *            The aspect this Scene will display.
     * @param sceneName
     *            the name of the scene
     */
    public DisplayAspectScene(RamApp app, Aspect aspect, String sceneName) {
        super(app, sceneName);
        this.aspect = aspect;

        messageViewViews = new HashMap<AbstractMessageView, MessageViewView>();

        // views are added into this layer
        viewContainer = new MTComponent(app, "view container");
        getContainerLayer().addChild(viewContainer);

        // Graphical Updater linked to this aspect
        graphicalUpdater = RamApp.getApplication().getGraphicalUpdaterForAspect(aspect);

        buildAspectName();
        buildViews();

        EMFEditUtil.addListenerFor(aspect, this);

        // The manager of the validation thread responsible for its launch
        validatorLauncher = new ValidationManager(aspect, new Validator(), graphicalUpdater);
        showValidation(true);
        showTracing(true);

        setCommandStackListener(aspect);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (handler != null) {
            String actionCommand = event.getActionCommand();
            if (ACTION_TOGGLE_VIEWS.equals(actionCommand)) {
                handler.toggleView(this);
            } else if (ACTION_WEAVE_STATE_MACHINES.equals(actionCommand)) {
                handler.weaveStateMachines(this);
            } else if (ACTION_WEAVE_STATE_MACHINES_NO_CSP.equals(actionCommand)) {
                handler.weaveAllNoCSPForStateViews(this);
            } else if (ACTION_GENERATE_CODE.equals(actionCommand)) {
                handler.generateCode(this);
            } else if (ACTION_BACK.equals(actionCommand)) {
                handler.back(this);
            } else if (ACTION_OPEN_VALIDATOR.equals(actionCommand)) {
                handler.showValidation(this);
            } else if (ACTION_CONCERN_BACK.equals(actionCommand)) {
                handler.switchToConcern(this);
            } else if (ACTION_OPEN_TRACING.equals(actionCommand)) {
                handler.showTracing(this);
            } else if (ACTION_CLOSE_SPLIT_VIEW.equals(actionCommand)) {
                handler.closeSplitView(this);
            } else {
                super.actionPerformed(event);
            }
        } else {
            LoggerUtils.warn("No handler set for " + this.getClass().getName());
        }
    }

    /**
     * Function called to display the aspect name and menu.
     * Creates the aspect name at the top left corner of the screen and assigns it.
     */
    private void buildAspectName() {
        // aspect name
        aspectName = new TextView(aspect, CorePackage.Literals.CORE_NAMED_ELEMENT__NAME);
        aspectName.setFont(Fonts.SCENE_FONT);
        aspectName.setPlaceholderFont(Fonts.SCENE_FONT);
        aspectName.setPlaceholderText(Strings.PH_ENTER_ASPECT_NAME);
        getContainerLayer().addChild(aspectName);
        aspectName.translate(10, Constants.MENU_BAR_HEIGHT);
        // event handling has to be done manually since it is not part of a ContainerComponent
        aspectName.registerTapProcessor(HandlerFactory.INSTANCE.getTextViewHandler());
        graphicalUpdater.addGUListener(aspect, aspectName);
    }

    /**
     * Builds the structural, state and initial message views.
     */
    private void buildViews() {
        // TODO: probably it would make sense to split up DisplayAspectScene, have a general one with all the
        // general information and then one scene for each view
        // structural view (Note, elements added first will be drawn first. Drawing this view first means other
        // things will clip it)
        StructuralView structuralViewElement = aspect.getStructuralView();

        // create layout if it doesn't exist yet
        if (aspect.getLayout() == null) {
            RAMModelUtil.createLayout(aspect, structuralViewElement);
        }

        ContainerMapImpl layout = EMFModelUtil.getEntryFromMap(aspect.getLayout().getContainers(),
                structuralViewElement);
        structuralView = new StructuralDiagramView(structuralViewElement, layout, getWidth(), getHeight());

        // This is important since we want to be able to relocate the structural diagram views easily in split
        // instantiation editing mode. Using upper left
        // corner as a reference makes relocation much easier in global space because when we use
        // "structuralView.getPosition(TransformSpace.GLOBAL)"
        // we will get position of the upper left corner of the view instead of center.
        structuralView.setAnchor(PositionAnchor.UPPER_LEFT);

        previousViews = new Stack<MTComponent>();
        switchToView(structuralView);

        structuralView.setHandler(HandlerFactory.INSTANCE.getStructuralViewHandler());

        messageViewContainer = new MessageViewContainer(aspect, getWidth(), getHeight());

        stateDiagramView = new StateDiagramView(aspect, aspect.getLayout(), getWidth(), getHeight());
        // Temporarily add the view to be able to set the handler.
        viewContainer.addChild(stateDiagramView);
        stateDiagramView.setHandler(HandlerFactory.INSTANCE.getStateViewHandler());
        viewContainer.removeChild(stateDiagramView);
    }

    @Override
    public boolean destroy() {
        graphicalUpdater.removeGUListener(aspect, aspectName);

        EMFEditUtil.removeListenerFor(aspect, this);

        /**
         * Not all views are currently children, so we need to destroy them explicitly.
         * I.e., only the last active view was a children of the view container.
         */
        destroyViews();

        return super.destroy();
    }

    /**
     * Destroys all views that are part of this scene.
     * This includes those views that are not currently visible.
     */
    private void destroyViews() {
        structuralView.destroy();
        messageViewContainer.destroy();
        stateDiagramView.destroy();

        for (MessageViewView messageViewView : messageViewViews.values()) {
            messageViewView.destroy();
        }
    }

    /**
     * Returns the aspect, the current scene is displaying.
     *
     * @return The aspect that this scene is displaying.
     */
    public Aspect getAspect() {
        return aspect;
    }

    /**
     * Returns the container for instantiations.
     *
     * @return The {@link InstantiationsPanel} for this scene.
     */
    public InstantiationsPanel getInstantiationsContainerViewContainerView() {
        return instantiationsPanel;
    }

    /**
     * Gets the Graphical Updater of the aspect.
     *
     * @return the graphicalUpdater
     */
    public GraphicalUpdater getGraphicalUpdater() {
        return graphicalUpdater;
    }

    /**
     * Returns the Structural Diagram view for the scene.
     *
     * @return The structural diagram view for this scene.
     */
    public StructuralDiagramView getStructuralDiagramView() {
        return structuralView;
    }

    /**
     * Hides all menu buttons other than back, undo and redo.
     */
    private void showMenuMessageViewView() {
        // Remove unused submenus
        menu.clearSubMenu(SUBMENU_GENERATE);
        menu.clearSubMenu(SUBMENU_WEAVE);
        menu.clearSubMenu(SUBMENU_GOTO);
        // Remove unused back/close buttons
        menu.removeAction(ACTION_CONCERN_BACK);
        menu.removeAction(ACTION_CLOSE_SPLIT_VIEW);
        // Add correct actions
        menu.addAction(Strings.MENU_BACK, Icons.ICON_MENU_CLOSE, ACTION_BACK, this, SUBMENU_BACK, true);
    }

    /**
     * Hides all menu buttons other than close split view, save, undo and redo.
     */
    private void showSplitViewButtons() {
        // Remove unsued submenus
        menu.clearSubMenu(SUBMENU_GENERATE);
        menu.clearSubMenu(SUBMENU_WEAVE);
        menu.clearSubMenu(SUBMENU_GOTO);
        // Remove unused back/close buttons
        menu.removeAction(ACTION_CONCERN_BACK);
        menu.removeAction(ACTION_BACK);
        // Add correct actions
        this.getMenu().addAction(Strings.MENU_CLOSE_SPLITVIEW,
                Icons.ICON_MENU_CLOSE, ACTION_CLOSE_SPLIT_VIEW, this, SUBMENU_BACK, true);
    }

    /**
     * Shows all of the buttons in the menu for message view.
     */
    private void showMessageViewButtons() {
        showMainMenuButtons();

        menu.removeAction(ACTION_TOGGLE_VIEWS);
        menu.addAction(Strings.MENU_GOTO_STATEVIEW, Icons.ICON_MENU_STATE_VIEW,
                ACTION_TOGGLE_VIEWS, this, SUBMENU_GOTO, true);
    }

    /**
     * Shows all of the buttons in the menu for structural view.
     */
    private void showStructuralViewButtons() {
        showMainMenuButtons();

        // toggle view Button
        menu.removeAction(ACTION_TOGGLE_VIEWS);
        menu.addAction(Strings.MENU_GOTO_MESSAGEVIEW, Icons.ICON_MENU_MESSAGE_VIEW,
                ACTION_TOGGLE_VIEWS, this, SUBMENU_GOTO, true);
    }

    /**
     * Show buttons present in structural, message and state views.
     */
    private void showMainMenuButtons() {
        // remove unused actions
        menu.removeAction(ACTION_WEAVE_STATE_MACHINES);
        menu.removeAction(ACTION_WEAVE_STATE_MACHINES_NO_CSP);
        menu.removeAction(ACTION_BACK);
        menu.removeAction(ACTION_CLOSE_SPLIT_VIEW);

        // Back to concern button
        if (menu.getAction(ACTION_CONCERN_BACK) == null) {
            this.getMenu().addAction(Strings.MENU_BACK_CONCERN, Icons.ICON_MENU_BACK, ACTION_CONCERN_BACK, this, true);
        }
        // Generate and weave
        if (menu.getAction(ACTION_GENERATE_CODE) == null) {
            this.getMenu().addAction(Strings.MENU_GENERATE_CODE, Icons.ICON_MENU_GENERATE, ACTION_GENERATE_CODE,
                    this, SUBMENU_GENERATE, true);
        }
    }

    /**
     * Additionally show state machine weaving buttons after the "Weave All" button.
     */
    private void showWeaveStateMachineButtons() {
        showMainMenuButtons();

        // switch view
        menu.removeAction(ACTION_TOGGLE_VIEWS);
        menu.addAction(Strings.MENU_GOTO_STRUCTURALVIEW, Icons.ICON_MENU_STRUCTURAL_VIEW,
                ACTION_TOGGLE_VIEWS, this, SUBMENU_GOTO, true);

        // add weave actions
        menu.addAction(Strings.MENU_APPLY_CSP, Icons.ICON_MENU_WEAVE_CSP, ACTION_WEAVE_STATE_MACHINES, this,
                SUBMENU_WEAVE, true);
        menu.addAction(Strings.MENU_WEAVE_NO_CSP, Icons.ICON_MENU_WEAVE_NO_CSP,
                ACTION_WEAVE_STATE_MACHINES_NO_CSP, this, SUBMENU_WEAVE, true);
    }

    /**
     * Replaces the current view with the given view. Remembers the old current view in case the user wants to go back
     * to it.
     *
     * @param view
     *            the view to display
     * @see #switchToPreviousView()
     */
    public void switchToView(MTComponent view) {
        this.switchToView(view, true);
    }

    /**
     * Replaces the current view with the given view. Remembers the old current view in case the user wants to go back
     * to it.
     *
     * @param view
     *            the view to display
     * @param saveCurrent - whether we want to remember the current view as the previous one.
     * @see #switchToPreviousView()
     */
    public void switchToView(MTComponent view, boolean saveCurrent) {
        if (saveCurrent) {
            previousViews.push(currentView);
        }
        currentView = view;
        viewContainer.removeAllChildren();
        viewContainer.addChild(view);
        showMenu();
        clearTemporaryComponents();
    }

    /**
     * Show menu related to the current view.
     */
    private void showMenu() {
        if (currentView instanceof StateDiagramView) {
            showWeaveStateMachineButtons();
        } else if (currentView instanceof MessageViewView) {
            showMenuMessageViewView();
        } else if (currentView instanceof InstantiationSplitEditingView) {
            showSplitViewButtons();
        } else if (currentView instanceof MessageViewContainer) {
            showMessageViewButtons();
        } else {
            showStructuralViewButtons();
        }
    }

    /**
     * Returns the current view that is displayed.
     *
     * @return the current view
     */
    public MTComponent getCurrentView() {
        return currentView;
    }

    /**
     * Displays the view that was displayed previously.
     *
     * @see #switchToView(MTComponent)
     */
    public void switchToPreviousView() {
        if (!previousViews.isEmpty()) {
            switchToView(previousViews.pop(), false);
        }
    }

    /**
     * Toggles between the different views.
     */
    public void toggleView() {
        if (currentView == structuralView) {
            switchToView(messageViewContainer);
        } else if (currentView == messageViewContainer) {
            switchToView(stateDiagramView);
        } else {
            switchToView(structuralView);
        }
    }

    /**
     * Displays a view for the message view.
     * Creates a new view if it hasn't been created before, otherwise it uses the existing view.
     *
     * @param messageView the {@link AbstractMessageView} to display its {@link MessageViewView} for
     */
    public void showMessageView(AbstractMessageView messageView) {
        MessageViewView view = messageViewViews.get(messageView);

        if (view == null) {
            ContainerMapImpl layout = EMFModelUtil.getEntryFromMap(aspect.getLayout().getContainers(), messageView);

            // There may be no layout in certain cases, e.g., when the message view was created outside of TouchCORE
            // or for older models.
            if (layout == null) {
                layout = (ContainerMapImpl) ((RamFactoryImpl) RamFactory.eINSTANCE).createContainerMap();
                layout.setKey(messageView);
                aspect.getLayout().getContainers().add(layout);
            }

            view = new MessageViewView(messageView, layout, getWidth(), getHeight());
            // Temporarily add the view to be able to set the handler.
            viewContainer.addChild(view);
            view.setHandler(ca.mcgill.sel.ram.ui.views.message.handler.MessageViewHandlerFactory.INSTANCE
                    .getMessageViewHandler());
            viewContainer.removeChild(view);
            messageViewViews.put(messageView, view);
        }

        switchToView(view);
    }

    /**
     * Shows a confirm popup for the given aspect to ask the user whether the aspect should be saved.
     *
     * @param parent
     *            the scene where the popup should be displayed, usually the current scene
     * @param listener
     *            the listener to inform which option the user selected
     */
    public void showCloseConfirmPopup(RamAbstractScene<?> parent, ConfirmPopup.SelectionListener listener) {
        showCloseConfirmPopup(parent, listener, OptionType.YES_NO_CANCEL);
    }

    /**
     * Shows a confirm popup for the given aspect to ask the user whether the aspect should be saved.
     *
     * @param parent the scene where the popup should be displayed, usually the current scene
     * @param listener the listener to inform which option the user selected
     * @param options the buttons to display in the popup
     */
    public void showCloseConfirmPopup(RamAbstractScene<?> parent, ConfirmPopup.SelectionListener listener,
            OptionType options) {
        String message = Strings.MODEL_ASPECT + " " + aspect.getName() + Strings.POPUP_MODIFIED_SAVE;
        ConfirmPopup saveConfirmPopup = new ConfirmPopup(message, options);
        saveConfirmPopup.setListener(listener);

        parent.displayPopup(saveConfirmPopup);
    }

    @Override
    public void notifyChanged(Notification notification) {
        if (notification.getFeature() == RamPackage.Literals.ASPECT__MESSAGE_VIEWS
                && notification.getEventType() == Notification.REMOVE) {
            AbstractMessageView messageView = (AbstractMessageView) notification.getOldValue();
            removeMessageView(messageView);
        } else if (notification.getFeature() == CorePackage.Literals.CORE_MODEL__CORE_CONCERN
                && notification.getEventType() == Notification.SET
                && notification.getNewValue() == null) {
            // Go back to the concern if the link with it is undone.
            handler.switchToConcern(this);
        }
    }

    /**
     * Removes the MessageViewView of the given message view. In case the view is currently the active view,
     * it will switch to the previous view.
     *
     * @param messageView the {@link AbstractMessageView} the view to remove for
     */
    private void removeMessageView(AbstractMessageView messageView) {
        MessageViewView view = messageViewViews.remove(messageView);

        // Switch back to previous view if it is currently displayed.
        if (currentView == view) {
            switchToPreviousView();
        }

        // View could be null if the message view hasn't been loaded yet.
        if (view != null) {
            viewContainer.removeChild(view);
            view.destroy();
        }
    }

    /**
     * Closes or opens the {@link ValidationView} depends on the boolean parameter.
     * Launches also the {@link ValidationManager}.
     *
     * @param toOpen true if you want to open it, false to close it.
     */
    public void showValidation(boolean toOpen) {

        if (toOpen && aspectValidatorView == null) {

            // Validator and the view for the aspect
            aspectValidatorView = new ValidationView(graphicalUpdater);
            aspectValidatorView.registerListener(this);

            // Starts the thread
            validatorLauncher.addListener(aspectValidatorView);
            validatorLauncher.startValidationThread();

            // To make the "validatorManager" able to collect possible new errors from the validator
            aspectValidatorView.setController(validatorLauncher);
            getContainerLayer().addChild(aspectValidatorView);

            if (this.getMenu().getAction(ACTION_OPEN_VALIDATOR) != null) {
                this.getMenu().removeAction(ACTION_OPEN_VALIDATOR);
            }

            // Launch a first validation
            validatorLauncher.launchValidation(true, 0);

        } else {
            validatorLauncher.stopValidationThread();
            validatorLauncher.removeListener(aspectValidatorView);

            if (containerLayer.containsChild(aspectValidatorView)) {
                containerLayer.removeChild(aspectValidatorView);
            }
            this.getMenu().addAction(Strings.PANEL_OPEN_VALIDATION, Icons.ICON_MENU_VALIDATOR, ACTION_OPEN_VALIDATOR,
                    this, SUBMENU_SHOWPANELS, true);
            aspectValidatorView = null;
        }

    }

    /**
     * Closes or opens the {@link TracingView} depends on the boolean parameter.
     *
     * @param toOpen true if you want to open it, false to close it.
     */
    public void showTracing(boolean toOpen) {

        TracingView newTracingView = new TracingView(graphicalUpdater, aspect);
        boolean isEmpty = newTracingView.isEmpty();

        if (toOpen && tracingView == null) {
            tracingView = newTracingView;

            if (this.getMenu().getAction(ACTION_OPEN_TRACING) != null) {
                this.getMenu().removeAction(ACTION_OPEN_TRACING);
            }

            // If there are no woven aspects to display,
            // we remove the tracing view and do not display the button to open it
            if (isEmpty) {
                tracingView.destroy();
            } else {
                tracingView.registerListener(this);
                tracingView.setPositionGlobal(new Vector3D(0, TRACING_DEFAULT_Y, 0));
                containerLayer.addChild(tracingView);
            }

        } else {
            if (containerLayer.containsChild(tracingView)) {
                containerLayer.removeChild(tracingView);
            }

            // If there are no woven aspects to display,
            // we remove the tracing view and do not display the button to open it
            if (!isEmpty) {
                menu.addAction(Strings.PANEL_OPEN_TRACING, Icons.ICON_MENU_TRACING, ACTION_OPEN_TRACING,
                        this, SUBMENU_SHOWPANELS, true);
            }

            tracingView = null;
        }
    }

    @Override
    public void panelClosed(RamPanelComponent panel) {
        if (panel == aspectValidatorView) {
            showValidation(false);
        } else if (panel == tracingView) {
            showTracing(false);
        }

    }

    @Override
    protected void initMenu() {
        // Initialize the subMenus
        menu.addSubMenu(3, SUBMENU_SHOWPANELS);
        menu.addSubMenu(2, SUBMENU_GENERATE);
        menu.addSubMenu(2, SUBMENU_WEAVE);
        menu.addSubMenu(1, SUBMENU_BACK);
        menu.addSubMenu(1, SUBMENU_GOTO);
    }

    @Override
    protected EObject getElementToSave() {
        return aspect;
    }

}
