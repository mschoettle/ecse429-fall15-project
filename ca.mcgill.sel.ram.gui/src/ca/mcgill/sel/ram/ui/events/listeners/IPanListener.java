package ca.mcgill.sel.ram.ui.events.listeners;

import org.mt4j.input.inputProcessors.IGestureEventListener;
import org.mt4j.input.inputProcessors.componentProcessors.panProcessor.PanEvent;

/**
 * This interface is implemented by something that wants to handle {@link PanEvent}.
 * 
 * @author mschoettle
 */
public interface IPanListener extends IGestureEventListener {
    
    /**
     * Handles a pan event.
     * 
     * @param panEvent
     *            the {@link PanEvent}
     * @return true, if the event was handled, false otherwise
     */
    boolean processPanEvent(PanEvent panEvent);
    
}
