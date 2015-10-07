package ca.mcgill.sel.ram.ui.events.listeners;

import org.mt4j.input.inputProcessors.IGestureEventListener;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragEvent;

/**
 * This interface is implemented by something that wants to handle {@link DragEvent}.
 * 
 * @author mschoettle
 */
public interface IDragListener extends IGestureEventListener {
    
    /**
     * Handles a drag event.
     * 
     * @param dragEvent
     *            the {@link DragEvent}
     * @return true, if the event was handled, false otherwise
     */
    boolean processDragEvent(DragEvent dragEvent);
    
}
