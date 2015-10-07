package ca.mcgill.sel.ram.ui.views.state.handler.impl;

import org.mt4j.components.TransformSpace;
import org.mt4j.input.gestureAction.InertiaDragAction;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapAndHoldProcessor.TapAndHoldEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;
import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.ram.State;
import ca.mcgill.sel.ram.controller.ControllerFactory;
import ca.mcgill.sel.ram.controller.StateMachineViewController;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamImageComponent;
import ca.mcgill.sel.ram.ui.components.RamSelectorComponent;
import ca.mcgill.sel.ram.ui.components.listeners.AbstractDefaultRamSelectorListener;
import ca.mcgill.sel.ram.ui.events.DelayedDrag;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.utils.Constants;
import ca.mcgill.sel.ram.ui.utils.Icons;
import ca.mcgill.sel.ram.ui.views.OptionSelectorView;
import ca.mcgill.sel.ram.ui.views.OptionSelectorView.Iconified;
import ca.mcgill.sel.ram.ui.views.handler.BaseHandler;
import ca.mcgill.sel.ram.ui.views.state.StateComponentView;
import ca.mcgill.sel.ram.ui.views.state.StateMachineView;
import ca.mcgill.sel.ram.ui.views.state.handler.IStateComponentViewHandler;
import ca.mcgill.sel.ram.ui.views.state.handler.IStateMachineViewHandler;

/**
 * The default handler for a {@link StateComponentView}.
 * 
 * @author mschoettle
 */
public class StateComponentViewHandler extends BaseHandler implements IStateComponentViewHandler {
    
    /**
     * The options to display for a state.
     */
    private enum StateOptions implements Iconified {
        DELETE(new RamImageComponent(Icons.ICON_DELETE, Colors.ICON_DELETE_COLOR)),
        SET_START(new RamImageComponent(Icons.ICON_ADD, Colors.ICON_ADD_DEFAULT_COLOR));
        
        private RamImageComponent icon;
        
        /**
         * Creates a new options literal with the given icon.
         * 
         * @param icon the icon to display for this literal
         */
        StateOptions(RamImageComponent icon) {
            this.icon = icon;
        }
        
        @Override
        public RamImageComponent getIcon() {
            return icon;
        }
        
    }
    
    /**
     * Minimum event count for dragging.
     */
    private static final int MIN_EVENT_COUNT = 5;
    
    private DelayedDrag dragAction = new DelayedDrag(Constants.DELAYED_DRAG_MIN_DRAG_DISTANCE);
    private InertiaDragAction inertiaAction = new InertiaDragAction();
    
    @Override
    public boolean processDragEvent(DragEvent dragEvent) {
        dragAction.processGestureEvent(dragEvent);
        inertiaAction.processGestureEvent(dragEvent);
        
        StateComponentView target = (StateComponentView) dragEvent.getTarget();
        
        if (dragEvent.getId() == MTGestureEvent.GESTURE_ENDED) {
            // drag it only if it was moved enough
            if (dragEvent.getDragCursor().getEventCount() >= MIN_EVENT_COUNT) {
                State state = target.getState();
                Vector3D position = target.getPosition(TransformSpace.GLOBAL);
                
                // TODO: mschoettle: right now this only moves to the current position, if inertia is activated the
                // class will get moved visually a bit further
                ControllerFactory.INSTANCE.getStateController().moveState(state, position.getX(), position.getY());
            }
        }
        
        return true;
    }
    
    @Override
    public boolean processTapAndHoldEvent(TapAndHoldEvent tapAndHoldEvent) {
        if (tapAndHoldEvent.isHoldComplete()) {
            final StateComponentView target = (StateComponentView) tapAndHoldEvent.getTarget();
            final StateMachineView stateMachineView = target.getStateMachineView();
            
            OptionSelectorView<StateOptions> selector = new OptionSelectorView<StateOptions>(StateOptions.values());
            
            RamApp.getActiveAspectScene().addComponent(selector, tapAndHoldEvent.getLocationOnScreen());
            
            selector.registerListener(new AbstractDefaultRamSelectorListener<StateOptions>() {
                @Override
                public void elementSelected(RamSelectorComponent<StateOptions> selector, StateOptions element) {
                    
                    switch (element) {
                        case DELETE:
                            removeState(target);
                            break;
                        case SET_START:
                            StateMachineViewController controller =
                                    ControllerFactory.INSTANCE.getStateMachineViewController();
                            controller.setStartState(stateMachineView.getStateMachine(), target.getState());
                            break;
                        default:
                            break;
                            
                    }
                }
            });
            
        }
        
        return true;
    }
    
    @Override
    public boolean processTapEvent(TapEvent tapEvent) {
        if (tapEvent.isDoubleTap()) {
            StateComponentView target = (StateComponentView) tapEvent.getTarget();
            IStateMachineViewHandler handler = target.getStateMachineView().getHandler();
            return handler.handleDoubleTapOnState(target.getStateMachineView(), target);
        } else if (tapEvent.isTapped()) {
            StateComponentView target = (StateComponentView) tapEvent.getTarget();
            StateMachineView targetMachine = target.getStateMachineView();
            StateComponentView selectedStateView = targetMachine.getSelectedStateComponentView();
            if (selectedStateView != target && selectedStateView == null) {
                targetMachine.select(target);
            }
        }
        
        return true;
    }
    
    @Override
    public void removeState(StateComponentView stateComponentView) {
        ControllerFactory.INSTANCE.getStateMachineViewController().removeState(stateComponentView.getState());
    }
    
}
