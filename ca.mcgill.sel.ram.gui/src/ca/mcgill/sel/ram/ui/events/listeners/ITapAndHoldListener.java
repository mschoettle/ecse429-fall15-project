package ca.mcgill.sel.ram.ui.events.listeners;

import org.mt4j.input.inputProcessors.IGestureEventListener;
import org.mt4j.input.inputProcessors.componentProcessors.tapAndHoldProcessor.TapAndHoldEvent;

/**
 * This interface is implemented by something that wants to handle {@link TapAndHoldEvent}.
 * 
 * @author mschoettle
 */
public interface ITapAndHoldListener extends IGestureEventListener {
    
    /**
     * Handles a tap and hold event.
     * 
     * @param tapAndHoldEvent
     *            the {@link TapAndHoldEvent}
     * @return true, if the event was handled, false otherwise
     */
    boolean processTapAndHoldEvent(TapAndHoldEvent tapAndHoldEvent);
    
}
