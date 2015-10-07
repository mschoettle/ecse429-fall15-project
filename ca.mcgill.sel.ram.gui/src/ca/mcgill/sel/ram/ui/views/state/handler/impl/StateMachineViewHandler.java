package ca.mcgill.sel.ram.ui.views.state.handler.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapAndHoldProcessor.TapAndHoldEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;
import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.State;
import ca.mcgill.sel.ram.StateMachine;
import ca.mcgill.sel.ram.controller.ControllerFactory;
import ca.mcgill.sel.ram.controller.StateMachineViewController;
import ca.mcgill.sel.ram.ui.views.handler.BaseHandler;
import ca.mcgill.sel.ram.ui.views.state.StateComponentView;
import ca.mcgill.sel.ram.ui.views.state.StateDiagramView;
import ca.mcgill.sel.ram.ui.views.state.StateMachineView;
import ca.mcgill.sel.ram.ui.views.state.handler.IStateMachineViewHandler;

/**
 * The default handler for a {@link StateMachineView}.
 * 
 * @author Abir Ayed
 */
public class StateMachineViewHandler extends BaseHandler implements IStateMachineViewHandler {
    
    /**
     * Minimum event count for dragging.
     */
    private static final int MIN_EVENT_COUNT = 5;
    
    /**
     * Handles the creation of a new state.
     * 
     * @param view the view for the state machine in which a new state should be created/added to
     * @param position the position where the view for the state should be added to
     */
    private static void createNewState(final StateMachineView view, final Vector3D position) {
        final StateMachine stateMachine = view.getStateMachine();
        
        final Aspect aspect = (Aspect) stateMachine.eContainer().eContainer();
        
        aspect.eAdapters().add(new EContentAdapter() {
            
            private State state;
            
            @Override
            public void notifyChanged(Notification notification) {
                if (notification.getFeature() == RamPackage.Literals.STATE_MACHINE__STATES) {
                    if (notification.getEventType() == Notification.ADD) {
                        state = (State) notification.getNewValue();
                    }
                } else if (notification.getFeature() == RamPackage.Literals.CONTAINER_MAP__VALUE) {
                    if (notification.getEventType() == Notification.ADD) {
                        view.getStateComponentViewOf(state).showKeyboard();
                        aspect.eAdapters().remove(this);
                    }
                }
            }
        });
        
        StateMachineViewController controller = ControllerFactory.INSTANCE.getStateMachineViewController();
        controller.createNewState(stateMachine, null, position.getX(), position.getY());
    }
    
    @Override
    public boolean handleDoubleTapOnState(StateMachineView stateMachineView, StateComponentView stateComponentView) {
        StateComponentView selectedStateView = stateMachineView.getSelectedStateComponentView();
        
        if (selectedStateView != null) {
            StateMachineViewController controller = ControllerFactory.INSTANCE.getStateMachineViewController();
            State from = selectedStateView.getState();
            State to = stateComponentView.getState();
            controller.createTransition(stateMachineView.getStateMachine(), from, to);
        }
        
        return true;
    }
    
    @Override
    public boolean processDragEvent(DragEvent dragEvent) {
        StateMachineView target = (StateMachineView) dragEvent.getTarget();
        
        // drag it only if it was moved enough
        if (dragEvent.getDragCursor().getEventCount() >= MIN_EVENT_COUNT) {
            StateDiagramView view = target.getStateViewView().getStateDiagramView();
            dragEvent.setTarget(view);
            view.getHandler().processDragEvent(dragEvent);
        }
        
        return false;
    }
    
    @Override
    public boolean processTapAndHoldEvent(TapAndHoldEvent tapAndHoldEvent) {
        if (tapAndHoldEvent.isHoldComplete()) {
            StateMachineView target = (StateMachineView) tapAndHoldEvent.getTarget();
            createNewState(target, tapAndHoldEvent.getLocationOnScreen());
        }
        
        return true;
    }
    
    @Override
    public boolean processTapEvent(TapEvent tapEvent) {
        StateMachineView target = (StateMachineView) tapEvent.getTarget();
        target.deselect();
        
        return true;
    }
    
}
