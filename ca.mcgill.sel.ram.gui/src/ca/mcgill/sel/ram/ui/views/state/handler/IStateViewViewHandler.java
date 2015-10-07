package ca.mcgill.sel.ram.ui.views.state.handler;

import ca.mcgill.sel.ram.ui.events.listeners.IDragListener;
import ca.mcgill.sel.ram.ui.events.listeners.ITapAndHoldListener;
import ca.mcgill.sel.ram.ui.events.listeners.ITapListener;
import ca.mcgill.sel.ram.ui.views.state.StateViewView;

public interface IStateViewViewHandler extends IDragListener, ITapAndHoldListener, ITapListener {
    
    void removeStateView(StateViewView stateViewView);
    
    void addStateMachine(StateViewView stateViewView);
    
}
