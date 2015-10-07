package ca.mcgill.sel.ram.ui.views.state.handler.impl;

import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapAndHoldProcessor.TapAndHoldEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;

import ca.mcgill.sel.ram.controller.ControllerFactory;
import ca.mcgill.sel.ram.ui.views.handler.BaseHandler;
import ca.mcgill.sel.ram.ui.views.state.StateViewView;
import ca.mcgill.sel.ram.ui.views.state.handler.IStateViewViewHandler;

/**
 * The default handler for a {@link StateViewView}.
 * 
 * @author mschoettle
 */
public class StateViewViewHandler extends BaseHandler implements IStateViewViewHandler {
    
    @Override
    public boolean processDragEvent(DragEvent dragEvent) {
        return false;
    }
    
    @Override
    public boolean processTapEvent(TapEvent tapEvent) {
        return false;
    }
    
    @Override
    public boolean processTapAndHoldEvent(TapAndHoldEvent tapAndHoldEvent) {
        if (tapAndHoldEvent.isHoldComplete()) {
            StateViewView target = (StateViewView) tapAndHoldEvent.getTarget();
            // return
            target.getStateDiagramView().getHandler().handleTapAndHoldOnStateView(target.getStateDiagramView(), target);
        }
        
        return true;
    }
    
    @Override
    public void removeStateView(StateViewView stateViewView) {
        ControllerFactory.INSTANCE.getStateViewController().removeStateView(stateViewView.getStateView());
        
    }
    
    @Override
    public void addStateMachine(StateViewView stateViewView) {
        ControllerFactory.INSTANCE.getStateViewViewController().createNewStateMachine(stateViewView.getStateView());
        
    }
    
}
