package ca.mcgill.sel.ram.ui.events;

import java.util.List;

import org.mt4j.components.MTComponent;
import org.mt4j.input.gestureAction.InertiaDragAction;
import org.mt4j.input.inputData.AbstractCursorInputEvt;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragEvent;

/**
 * An {@link InertiaDragAction} that restricts drag inertia to the non-locked axis.
 * 
 * @author mschoettle
 */
public class LockedAxisInertiaDrag extends InertiaDragAction {
    
    private static final int INTEGRATION_TIME = 125;
    
    private static final float DAMPING = 0.85f;
    
    private static final float MAX_VELOCITY_LENGTH = 25f;
    
    private Axis lockedAxis;
    
    /**
     * Creates a new {@link LockedAxisInertiaDrag} with the given axis being locked.
     * 
     * @param lockedAxis the {@link Axis} to be locked
     */
    public LockedAxisInertiaDrag(Axis lockedAxis) {
        super(INTEGRATION_TIME, DAMPING, MAX_VELOCITY_LENGTH);
        
        this.lockedAxis = lockedAxis;
    }
    
    @Override
    public boolean processGestureEvent(MTGestureEvent ge) {
        // lock the axis
        // only do this when InertiaDragAction actually does something
        // (see InertiaDragAction#processGestureEvent(MTGestureEvent ge))
        if (ge.getTarget() instanceof MTComponent && ge.getId() == MTGestureEvent.GESTURE_ENDED) {
            DragEvent dragEvent = (DragEvent) ge;
            
            // reset all positions of the drag cursor for the locked axis
            // in order to get the right translation vector later
            List<AbstractCursorInputEvt> events = dragEvent.getDragCursor().getEvents(INTEGRATION_TIME);
            
            // get first event
            AbstractCursorInputEvt firstEvent = dragEvent.getDragCursor().getEvents().get(0);
            
            for (AbstractCursorInputEvt event : events) {
                if (lockedAxis == Axis.X) {
                    event.setScreenX(firstEvent.getX());
                } else if (lockedAxis == Axis.Y) {
                    event.setScreenY(firstEvent.getY());
                }
            }
        }
        
        return super.processGestureEvent(ge);
    }
}
