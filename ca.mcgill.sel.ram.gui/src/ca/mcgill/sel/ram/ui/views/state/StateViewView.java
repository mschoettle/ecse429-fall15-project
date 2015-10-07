package ca.mcgill.sel.ram.ui.views.state;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.mt4j.util.font.IFont;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.commons.emf.util.EMFModelUtil;
import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.Classifier;
import ca.mcgill.sel.ram.Layout;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.StateMachine;
import ca.mcgill.sel.ram.StateView;
import ca.mcgill.sel.ram.impl.ContainerMapImpl;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.ContainerComponent;
import ca.mcgill.sel.ram.ui.components.RamButton;
import ca.mcgill.sel.ram.ui.components.RamImageComponent;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.components.RamTextComponent;
import ca.mcgill.sel.ram.ui.events.listeners.ActionListener;
import ca.mcgill.sel.ram.ui.layouts.HorizontalLayout;
import ca.mcgill.sel.ram.ui.layouts.HorizontalLayoutVerticallyCentered;
import ca.mcgill.sel.ram.ui.layouts.StateMachineLayout;
import ca.mcgill.sel.ram.ui.layouts.VerticalLayout;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.utils.Fonts;
import ca.mcgill.sel.ram.ui.utils.Icons;
import ca.mcgill.sel.ram.ui.utils.LoggerUtils;
import ca.mcgill.sel.ram.ui.utils.Strings;
import ca.mcgill.sel.ram.ui.views.TextView;
import ca.mcgill.sel.ram.ui.views.handler.HandlerFactory;
import ca.mcgill.sel.ram.ui.views.state.handler.IStateViewViewHandler;

/**
 * Visual representation of the State view.
 * 
 * @author abirayed
 */
public class StateViewView extends ContainerComponent<IStateViewViewHandler> implements INotifyChangedListener,
        ActionListener {

    public static final int PADDING = 10;
    public static final int MIN_HEIGHT = 100;

    protected static final IFont DEFAULT_FONT = Fonts.DEFAULT_FONT_MEDIUM;

    private static final String ACTION_STATE_VIEW_REMOVE = "view.stateView.remove";
    private static final String ACTION_STATE_MACHINE_ADD = "view.stateView.stateMachine.add";

    private StateView stateView;

    private RamRectangleComponent header;
    private TextView nameField;

    private StateDiagramView stateDiagramView;
    private Map<StateMachine, StateMachineView> stateMachines;

    private Layout layout;
    private ContainerMapImpl layoutContainer;
    private Aspect aspect;
    private Classifier specifies;

    private boolean editModeEnabled;
    private RamButton minusButton;
    private RamButton addButton;

    public StateViewView(Aspect aspect, StateView stateView, Layout layout, StateDiagramView stateDiagramView) {
        this.stateView = stateView;
        this.stateDiagramView = stateDiagramView;
        this.layout = layout;
        this.aspect = aspect;
        layoutContainer = EMFModelUtil.getEntryFromMap(this.layout.getContainers(), stateView);
        setSpecifies(stateView.getSpecifies());
        stateMachines = new HashMap<StateMachine, StateMachineView>();

    }
    
    @Override
    protected void destroyComponent() {
        EMFEditUtil.removeListenerFor(stateView, this);
        EMFEditUtil.removeListenerFor(layout, this);
    }

    private void initializeLayout() {
        setNoStroke(false);
        setStrokeColor(Colors.DEFAULT_ELEMENT_STROKE_COLOR);
        setNoFill(false);
        setFillColor(Colors.DEFAULT_ELEMENT_FILL_COLOR);
        setAutoMinimizes(false);
        setAutoMaximizes(true);

        EMFEditUtil.addListenerFor(stateView, this);
        // register to the layout to receive adds/removes of ElementMaps
        EMFEditUtil.addListenerFor(layout, this);
    }

    private void createEditModeButtons() {
        // create header field - button
        RamImageComponent deleteImage = new RamImageComponent(Icons.ICON_DELETE, Colors.ICON_DELETE_COLOR);
        deleteImage.setMinimumSize(Fonts.FONTSIZE_CLASS_NAME + 2, Fonts.FONTSIZE_CLASS_NAME + 2);
        deleteImage.setMaximumSize(Fonts.FONTSIZE_CLASS_NAME + 2, Fonts.FONTSIZE_CLASS_NAME + 2);
        minusButton = new RamButton(deleteImage);
        minusButton.setActionCommand(ACTION_STATE_VIEW_REMOVE);
        minusButton.addActionListener(this);

        // create header field + button
        RamImageComponent addImage = new RamImageComponent(Icons.ICON_STATE_MACHINE_ADD,
                Colors.ICON_ADD_STATEVIEW_COLOR);
        addImage.setMinimumSize(Fonts.FONTSIZE_CLASS_NAME + 2, Fonts.FONTSIZE_CLASS_NAME + 2);
        addImage.setMaximumSize(Fonts.FONTSIZE_CLASS_NAME + 2, Fonts.FONTSIZE_CLASS_NAME + 2);
        addButton = new RamButton(addImage);
        addButton.setActionCommand(ACTION_STATE_MACHINE_ADD);
        addButton.addActionListener(this);

    }

    protected void addNameField(TextView nameField) {
        // add name after type
        header.addChild(1, nameField);
    }

    protected void build() {
        initializeLayout();
        createEditModeButtons();

        // create a component for all the head components
        header = new RamRectangleComponent(new HorizontalLayout());
        header.setNoStroke(false);
        header.setBufferSize(Cardinal.EAST, PADDING);
        header.setLayout(new HorizontalLayoutVerticallyCentered(Fonts.FONTSIZE_CLASS_NAME / 8));

        // let the header not auto resize
        // this way it stays as big as it is and the border stops there (looks nicer)
        header.setAutoMaximizes(false);
        header.setAutoMinimizes(true);
        addChild(header);

        RamTextComponent typeNameField = new RamTextComponent(DEFAULT_FONT);
        typeNameField.setText(stateView.eClass().getName());
        header.addChild(typeNameField);

        buildNameField();

        for (StateMachine stateMachine : stateView.getStateMachines()) {
            createStateMachineView(stateMachine);
        }

        setLayout(new VerticalLayout(0));

    }

    protected void buildNameField() {
        nameField = new TextView(stateView, RamPackage.Literals.STATE_VIEW__SPECIFIES, true);
        nameField.setFont(DEFAULT_FONT);
        nameField.setPlaceholderText(Strings.PH_ENTER_STATEVIEW_NAME);
        addNameField(nameField);
        nameField.setHandler(HandlerFactory.INSTANCE.getStateViewNameHandler());

    }

    protected void createStateMachineView(StateMachine stateMachine) {

        StateMachineView stateMachineView = new StateMachineView(stateMachine, layoutContainer, this);

        stateMachineView.build();
        addChild(stateMachineView);
        stateMachineView.setHandler(HandlerFactory.INSTANCE.getStateMachineViewHandler());

        stateMachineView.setLayout(new StateMachineLayout(PADDING));
        stateMachineView.setMinimumHeight(MIN_HEIGHT);

        stateMachines.put(stateMachine, stateMachineView);

    }

    /**
     * @return the stateDiagramView
     */
    public StateDiagramView getStateDiagramView() {
        return stateDiagramView;
    }

    /**
     * Displays the keyboard.
     */
    public void showKeyboard() {
        nameField.showKeyboard();
    }

    /**
     * @return aspect
     */
    public Aspect getAspect() {
        return aspect;
    }

    public StateView getStateView() {
        return stateView;
    }

    @Override
    public void notifyChanged(Notification notification) {
        // Change the name of the state view if the name of the class changes
        if (notification.getNotifier() == specifies) {
            if (notification.getFeature() == CorePackage.Literals.CORE_NAMED_ELEMENT__NAME) {
                setSpecifies(specifies);
            }
        }
        if (notification.getFeature() == RamPackage.Literals.STATE_VIEW__STATE_MACHINES) {
            StateMachine stateMachine = null;
            if (!((StateView) notification.getNotifier()).equals(stateView)) {
                return;
            }
            switch (notification.getEventType()) {
                case Notification.ADD:
                    stateMachine = (StateMachine) notification.getNewValue();
                    createStateMachineView(stateMachine);

                    break;
                case Notification.REMOVE:
                    stateMachine = (StateMachine) notification.getOldValue();
                    deleteStateMachine(stateMachine);
                    break;
            }
        }

    }

    private void deleteStateMachine(final StateMachine stateMachine) {
        RamApp.getApplication().invokeLater(new Runnable() {

            @Override
            public void run() {
                StateMachineView stateMachineView = stateMachines.remove(stateMachine);

                removeChild(stateMachineView);
                stateMachineView.destroy();
            }
        });
    }

    public Classifier getSpecifies() {
        return specifies;
    }

    /**
     * Sets the class specified by this state view.
     * 
     * @param specifies
     */
    public void setSpecifies(Classifier specifies) {
        this.specifies = specifies;

        if (specifies != null) {
            stateView.setSpecifies(specifies);
            stateView.setName(specifies.getName());
            EMFEditUtil.addListenerFor(specifies, this);
        }
    }

    /**
     * Disables edit mode for the state view and resets to the default display state.
     */
    public void disableEditMode() {
        // reset state
        editModeEnabled = false;
        setStrokeColor(Colors.DEFAULT_ELEMENT_STROKE_COLOR);

        // remove the - button and + button
        header.removeChild(minusButton);
        header.removeChild(addButton);

    }

    /**
     * Enables edit mode, highlights state red, adds buttons for editing state view.
     */
    public void enableEditMode() {
        if (!editModeEnabled) {
            editModeEnabled = true;
            setStrokeColor(Colors.STATE_EDIT_MODE_STROKE_COLOR);

            // add header field - button
            header.addChild(0, minusButton);

            // add header field + button
            header.addChild(0, addButton);
        }

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (handler != null) {
            String actionCommand = event.getActionCommand();

            if (ACTION_STATE_VIEW_REMOVE.equals(actionCommand)) {
                handler.removeStateView(this);
            } else if (ACTION_STATE_MACHINE_ADD.equals(actionCommand)) {
                handler.addStateMachine(this);
            }
        } else {
            LoggerUtils.warn("No handler set for " + this.getClass().getName());
        }

    }

    public void setLayoutElement(ContainerMapImpl containerMap) {
        layoutContainer = containerMap;

    }
}
