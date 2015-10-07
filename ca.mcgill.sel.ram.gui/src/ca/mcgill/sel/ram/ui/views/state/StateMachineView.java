package ca.mcgill.sel.ram.ui.views.state;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.mt4j.components.TransformSpace;
import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.commons.emf.util.EMFModelUtil;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.LayoutElement;
import ca.mcgill.sel.ram.Operation;
import ca.mcgill.sel.ram.RamFactory;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.State;
import ca.mcgill.sel.ram.StateMachine;
import ca.mcgill.sel.ram.Transition;
import ca.mcgill.sel.ram.impl.ContainerMapImpl;
import ca.mcgill.sel.ram.impl.ElementMapImpl;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.ContainerComponent;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.views.RamEnd;
import ca.mcgill.sel.ram.ui.views.handler.HandlerFactory;
import ca.mcgill.sel.ram.ui.views.state.handler.IStateMachineViewHandler;

/**
 * Visual representation of a State Machine.
 * 
 * @author abirayed
 */
public class StateMachineView extends ContainerComponent<IStateMachineViewHandler> implements INotifyChangedListener {

    private StateMachine stateMachine;

    private Map<State, StateComponentView> states;
    private Map<Transition, TransitionView> transitions;

    private ContainerMapImpl layoutContainerMap;

    private StateViewView stateViewView;

    private StateComponentView selectedState;

    /**
     * @param stateMachine
     * @param layoutContainerMap
     * @param stateViewView
     */
    public StateMachineView(StateMachine stateMachine, ContainerMapImpl layoutContainerMap,
            StateViewView stateViewView) {
        this.stateMachine = stateMachine;
        this.stateViewView = stateViewView;
        this.layoutContainerMap = layoutContainerMap;
    }

    private void initializeLayout() {
        setEnabled(true);
        setNoFill(false);
        setFillColor(Colors.DEFAULT_ELEMENT_FILL_COLOR);
        setAutoMinimizes(false);
        setAutoMaximizes(true);
        setNoStroke(false);
        setStrokeColor(Colors.DEFAULT_ELEMENT_STROKE_COLOR);

        states = new HashMap<State, StateComponentView>();
        transitions = new HashMap<Transition, TransitionView>();
        if (layoutContainerMap != null) {
            EMFEditUtil.addListenerFor(layoutContainerMap, this);
        }
        EMFEditUtil.addListenerFor(stateMachine, this);
    }

    @Override
    protected void destroyComponent() {
        EMFEditUtil.removeListenerFor(stateMachine, this);
        if (layoutContainerMap != null) {
            EMFEditUtil.removeListenerFor(layoutContainerMap, this);
        }
    }

    public void build() {

        initializeLayout();

        Vector3D position = new Vector3D(0, 0);
        int maxHeight = 0;

        float maxX = 1280;
        float maxY = 800;
        // go through all fragments as they define how things are built through their order
        for (State state : stateMachine.getStates()) {
            LayoutElement layoutElement = null;
            if (layoutContainerMap != null) {
                cleanLayout();
                layoutElement = layoutContainerMap.getValue().get(state);
            }

            StateComponentView stateView = buildState(state, layoutContainerMap.getValue().get(state));

            if (layoutElement == null) {
                createStateLayout(state, stateView, position, maxHeight);

            } else {
                position.setX(Math.max(position.getX(), layoutElement.getX() + stateView.getWidth()));
                position.setY(Math.max(position.getY(), layoutElement.getY() + stateView.getHeight()));
            }

            maxX = Math.max(maxX, stateView.getX() + stateView.getWidth());
            maxY = Math.max(maxY, stateView.getY() + stateView.getHeight());

        }

        for (Transition transition : stateMachine.getTransitions()) {
            buildTransition(transition);
        }

    }

    /**
     * TODO This is a Walkaround in the case of a woven model. For some reason The layout of a woven model contain some
     * unwanted elements. Need to figure out when they are added to fix this properly
     */
    private void cleanLayout() {
        Collection<Object> toBeRemoved = new ArrayList<Object>();
        for (Object key : layoutContainerMap.getValue().keySet()) {
            if (((State) key).eContainer() != null && ((State) key).eContainer().eContainer() == null) {
                toBeRemoved.add(key);
            }
        }
        for (Object key : toBeRemoved) {
            layoutContainerMap.getValue().remove(key);
        }
    }

    protected StateComponentView buildState(State state, LayoutElement layoutElement) {
        if (!states.containsKey(state)) {

            StateComponentView stateView = new StateComponentView(state, this, layoutElement);
            stateView.build();
            addChild(stateView);
            stateView.setHandler(HandlerFactory.INSTANCE.getStateComponentViewHandler());
            states.put(state, stateView);
        }
        return states.get(state);
    }

    private void buildTransition(Transition transition) {
        TransitionView view = new TransitionView(transition, states.get(transition.getStartState()),
                states.get(transition.getEndState()));
        view.setHandler(HandlerFactory.INSTANCE.getTransitionViewHandler());
        transitions.put(transition, view);
        view.updateLines();
        addChild(view);
    }

    private void deleteState(final State state) {
        RamApp.getApplication().invokeLater(new Runnable() {

            @Override
            public void run() {
                selectedState = null;
                StateComponentView stateView = states.remove(state);

                removeChild(stateView);
                stateView.destroy();
            }
        });
    }

    /**
     * Deselects the currently selected state and disabled its edit mode.
     */
    public void deselect() {
        if (selectedState != null) {
            selectedState.disableEditMode();
            selectedState = null;
        }
    }

    /**
     * Returns the currently selected (i.e., the state component view in edit mode) state component view.
     * 
     * @return the currently selected {@link StateComponentView}
     */
    public StateComponentView getSelectedStateComponentView() {
        return selectedState;
    }

    /**
     * @return the stateMachine
     */
    public StateMachine getStateMachine() {
        return stateMachine;
    }

    /**
     * @return the stateViewView
     */
    public StateViewView getStateViewView() {
        return stateViewView;
    }

    @Override
    public void notifyChanged(Notification notification) {
        EObject notifier = (EObject) notification.getNotifier();
        Object feature = notification.getFeature();

        if (notifier == stateMachine) {
            if (feature == RamPackage.Literals.STATE_MACHINE__STATES) {
                State state = null;
                switch (notification.getEventType()) {
                    case Notification.ADD:
                        state = (State) notification.getNewValue();
                        buildState(state, layoutContainerMap.getValue().get(state));

                        break;
                    case Notification.REMOVE:
                        state = (State) notification.getOldValue();
                        deleteState(state);
                        break;
                }
            } else if (feature == RamPackage.Literals.STATE_MACHINE__TRANSITIONS) {
                switch (notification.getEventType()) {
                    case Notification.REMOVE:
                        Transition transition = (Transition) notification.getOldValue();
                        removeTransitionView(transition);
                        break;
                    case Notification.ADD:
                        transition = (Transition) notification.getNewValue();
                        buildTransition(transition);
                        break;
                }
            } else if (feature == RamPackage.Literals.STATE_MACHINE__START) {
                if (notification.getEventType() == Notification.SET) {
                    State newStartState = (State) notification.getNewValue();
                    State oldStartState = (State) notification.getOldValue();

                    if (newStartState != null) {
                        states.get(newStartState).setStartState(true);
                    }

                    if (oldStartState != null) {
                        states.get(oldStartState).setStartState(false);
                    }
                }
            }
        } else if (notifier == layoutContainerMap) {
            if (feature == RamPackage.Literals.CONTAINER_MAP__VALUE) {
                if (notification.getEventType() == Notification.ADD) {
                    ElementMapImpl elementMap = (ElementMapImpl) notification.getNewValue();
                    StateComponentView view = states.get(elementMap.getKey());
                    if (view != null) {
                        states.get(elementMap.getKey()).setLayoutElement(elementMap.getValue());
                    }
                }
            }
        }
    }

    /**
     * Selects the given view and enables edit mode for it.
     * 
     * @param view
     *            The view to enable.
     */
    public void select(StateComponentView view) {
        selectedState = view;
        selectedState.enableEditMode();
    }

    /**
     * Removes the {@link TransitionView} for the given {@link Transition}.
     * 
     * @param transition
     *            the transition that was deleted
     */
    public void removeTransitionView(Transition transition) {
        TransitionView transitionView = transitions.remove(transition);

        removeChild(transitionView);

        RamEnd<Operation, StateComponentView> ramEnd = transitionView.getFromEnd();
        RamEnd<Operation, StateComponentView> ramEndOpposite = transitionView.getToEnd();
        StateComponentView stateComponentViewFrom = ramEnd.getComponentView();
        StateComponentView stateComponentViewTo = ramEndOpposite.getComponentView();

        stateComponentViewFrom.removeRelationshipEnd(ramEnd);
        stateComponentViewTo.removeRelationshipEnd(ramEndOpposite);

        transitionView.destroy();
    }

    /**
     * Gets the {@link StateComponentView} of the specified state.
     * 
     * @param specifiedState
     *            the State element for which we want to get the state component view
     * @return {@link StateComponentView}
     */
    public StateComponentView getStateComponentViewOf(State specifiedState) {
        return states.get(specifiedState);
    }

    private void createStateLayout(State state, StateComponentView stateView, Vector3D position, int maxHeight) {
        int distanceBetweenStates = 100;
        int width = 100;

        LayoutElement layoutElement = RamFactory.eINSTANCE.createLayoutElement();
        // get the layout of the aspect

        Aspect aspect = (Aspect) stateMachine.eContainer().eContainer();
        ContainerMapImpl layout =
                EMFModelUtil.getEntryFromMap(aspect.getLayout().getContainers(), stateMachine.eContainer());

        layout.getValue().put(state, layoutElement);

        // TODO: move to separate method or class (e.g. a layouter)
        // move the class so they will be created next to each other
        position.setX(position.getX() + distanceBetweenStates);
        // really weird fix (3 * width) but it allows (for now) to have a wider class diagram after weaving
        if (position.getX() + stateView.getWidth() >= 3 * width) {
            position.setX(distanceBetweenStates);
            position.setY(position.getY() + maxHeight + distanceBetweenStates);
            maxHeight = 0;
        }

        layoutElement.setX(position.getX());
        layoutElement.setY(position.getY());
        stateView.setLayoutElement(layoutElement);

        maxHeight = Math.max(maxHeight, (int) stateView.getHeightXY(TransformSpace.LOCAL));
        position.setX(position.getX() + stateView.getWidthXY(TransformSpace.RELATIVE_TO_PARENT));
    }
}
