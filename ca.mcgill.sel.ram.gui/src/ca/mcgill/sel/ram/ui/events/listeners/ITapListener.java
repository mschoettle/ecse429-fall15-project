package ca.mcgill.sel.ram.ui.events.listeners;

import org.mt4j.input.inputProcessors.IGestureEventListener;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;

/**
 * This interface is implemented by something that wants to handle {@link TapEvent}.
 * 
 * @author mschoettle
 */
public interface ITapListener extends IGestureEventListener {
    
    /**
     * Handles a tap event.
     * 
     * @param tapEvent
     *            the {@link TapEvent}
     * @return true, if the event was handled, false otherwise
     */
    boolean processTapEvent(TapEvent tapEvent);
    
}
