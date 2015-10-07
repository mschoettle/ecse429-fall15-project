package ca.mcgill.sel.ram.ui.views.handler;

import ca.mcgill.sel.ram.ui.events.listeners.IDragListener;
import ca.mcgill.sel.ram.ui.events.listeners.IPanListener;
import ca.mcgill.sel.ram.ui.events.listeners.IUnistrokeListener;

/**
 * This interface is implemented by something that can handle events for a
 * {@link ca.mcgill.sel.ram.ui.views.AbstractView}.
 * 
 * @author mschoettle
 */
public interface IAbstractViewHandler extends IDragListener, IUnistrokeListener, IPanListener {
    
}
