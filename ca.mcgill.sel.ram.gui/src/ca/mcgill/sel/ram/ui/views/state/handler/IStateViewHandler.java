package ca.mcgill.sel.ram.ui.views.state.handler;

import ca.mcgill.sel.ram.ui.events.listeners.ITapAndHoldListener;
import ca.mcgill.sel.ram.ui.events.listeners.ITapListener;
import ca.mcgill.sel.ram.ui.views.handler.IAbstractViewHandler;
import ca.mcgill.sel.ram.ui.views.state.StateDiagramView;
import ca.mcgill.sel.ram.ui.views.state.StateViewView;

/**
 * This interface is implemented by something that can handle events for a {@link StateDiagramView}.
 * 
 * @author mschoettle
 */
public interface IStateViewHandler extends IAbstractViewHandler, ITapListener, ITapAndHoldListener {
    
    boolean handleTapAndHoldOnStateView(StateDiagramView stateDigram, StateViewView stateView);
    
}
