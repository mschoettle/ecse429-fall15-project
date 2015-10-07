package ca.mcgill.sel.ram.ui.views.message.handler;

import ca.mcgill.sel.ram.ui.events.listeners.IDragListener;
import ca.mcgill.sel.ram.ui.events.listeners.ITapAndHoldListener;
import ca.mcgill.sel.ram.ui.events.listeners.ITapListener;
import ca.mcgill.sel.ram.ui.events.listeners.IUnistrokeListener;
import ca.mcgill.sel.ram.ui.views.message.LifelineView;

/**
 * This interface is implemented by something that can handle events for a {@link LifelineView}.
 * 
 * @author mschoettle
 */
public interface ILifelineViewHandler extends IDragListener, ITapListener, ITapAndHoldListener, IUnistrokeListener {
    
    /**
     * Handles the removal of a lifeline.
     * 
     * @param lifelineView the {@link LifelineView} to be removed
     */
    void removeLifeline(LifelineView lifelineView);
    
}
