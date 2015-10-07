package ca.mcgill.sel.ram.ui.views.state.handler;

import ca.mcgill.sel.ram.ui.events.listeners.IDragListener;
import ca.mcgill.sel.ram.ui.events.listeners.ITapAndHoldListener;
import ca.mcgill.sel.ram.ui.events.listeners.ITapListener;
import ca.mcgill.sel.ram.ui.views.state.StateComponentView;
import ca.mcgill.sel.ram.ui.views.state.StateMachineView;

/**
 * @author abirayed
 */
public interface IStateMachineViewHandler extends IDragListener, ITapAndHoldListener, ITapListener {
    
    boolean handleDoubleTapOnState(StateMachineView stateMachineView, StateComponentView stateComponentView);
    
}
