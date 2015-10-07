package ca.mcgill.sel.ram.ui.events.listeners;

import org.mt4j.input.inputProcessors.IGestureEventListener;
import org.mt4j.input.inputProcessors.componentProcessors.unistrokeProcessor.UnistrokeEvent;

/**
 * This interface is implemented by something that wants to handle {@link UnistrokeEvent}.
 * 
 * @author mschoettle
 */
public interface IUnistrokeListener extends IGestureEventListener {
    
    /**
     * Handles a unistroke event.
     * 
     * @param unistrokeEvent the {@link UnistrokeEvent}
     * @return true, if the event was handled, false otherwise
     */
    boolean processUnistrokeEvent(UnistrokeEvent unistrokeEvent);
    
}
