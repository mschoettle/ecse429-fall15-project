package ca.mcgill.sel.ram.ui.views.state.handler;

import ca.mcgill.sel.ram.ui.events.listeners.IDragListener;
import ca.mcgill.sel.ram.ui.events.listeners.ITapAndHoldListener;
import ca.mcgill.sel.ram.ui.events.listeners.ITapListener;
import ca.mcgill.sel.ram.ui.views.state.StateComponentView;

/**
 * @author abirayed
 */
public interface IStateComponentViewHandler extends IDragListener, ITapAndHoldListener, ITapListener {
    
    /**
     * Handles the removal of a class.
     * 
     * @param stateComponentView
     *            the StateComponentView to be removed
     */
    void removeState(StateComponentView stateComponentView);
    
}
