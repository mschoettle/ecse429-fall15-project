package ca.mcgill.sel.ram.ui.views.handler;

import org.mt4j.input.inputProcessors.componentProcessors.tapAndHoldProcessor.TapAndHoldEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;

import ca.mcgill.sel.ram.ui.views.RamEnd;

/**
 * This interface is implemented by something that can handle events of
 * {@link ca.mcgill.sel.ram.ui.views.RelationshipView}.
 * More over, a relationship view has two ends and the events are distinguished by which of the end the event occurs on.
 * 
 * @author mschoettle
 */
public interface IRelationshipViewHandler {
    
    /**
     * Processes a double tap on a specific end of a relationship view.
     * 
     * @param tapEvent
     *            the underlying event that is processed
     * @param end the {@link RamEnd} which represents the tapped end
     *            of the {@link ca.mcgill.sel.ram.ui.views.RelationshipView}
     * @return true, if the event was processed, false otherwise
     */
    boolean processDoubleTap(TapEvent tapEvent, RamEnd<?, ?> end);
    
    /**
     * Processes a tap and hold on a specific end of a relationship view.
     * 
     * @param tapAndHoldEvent
     *            the underlying event that is processed
     * @param end the {@link RamEnd} which represents the tapped end
     *            of the {@link ca.mcgill.sel.ram.ui.views.RelationshipView}
     * @return true, if the event was processed, false otherwise
     */
    boolean processTapAndHold(TapAndHoldEvent tapAndHoldEvent, RamEnd<?, ?> end);
    
}
