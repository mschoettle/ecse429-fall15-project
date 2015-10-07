package ca.mcgill.sel.ram.ui.events;

import org.mt4j.components.interfaces.IMTComponent3D;
import org.mt4j.input.gestureAction.DefaultDragAction;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragEvent;
import org.mt4j.util.math.Vector3D;

/**
 * Delays dragging by enforcing that the cursor has to be moved a certain minimum distance before an object actually is
 * dragged. Uses the behavior of {@link DefaultDragAction}.
 *
 * @author mschoettle
 */
public class DelayedDrag extends DefaultDragAction {

    private float minimumDistance;
    private boolean dragPerformed;
    private Vector3D dragFrom;
    private Axis lockedAxis;

    /**
     * Creates a new {@link DelayedDrag} with the given minimum distance.
     *
     * @param minimumDistance the minimum distance to be moved with the cursor before an object gets actually dragged
     */
    public DelayedDrag(float minimumDistance) {
        super();
        this.minimumDistance = minimumDistance;
    }

    /**
     * Creates a new {@link DelayedDrag} action with the given minimum distance and the axis locked.
     * Components can then only be dragged across the non-locked axis.
     *
     * @param minimumDistance the minimum distance to be moved with the cursor before an object gets actually dragged
     * @param lockedAxis the axis that should be locked
     */
    public DelayedDrag(float minimumDistance, Axis lockedAxis) {
        this(minimumDistance);

        this.lockedAxis = lockedAxis;
    }

    /**
     * Creates a new {@link DelayedDrag} with the given minimum distance and a customized target.
     *
     * @param dragTarget The customized target of the drag.
     * @param minimumDistance the minimum distance to be moved with the cursor before an object gets actually dragged
     */
    public DelayedDrag(IMTComponent3D dragTarget, float minimumDistance) {
        super(dragTarget);
        this.minimumDistance = minimumDistance;
    }

    @Override
    public boolean processGestureEvent(MTGestureEvent event) {
        if (event instanceof DragEvent) {
            DragEvent dragEvent = (DragEvent) event;

            if (dragEvent.getId() == MTGestureEvent.GESTURE_STARTED) {
                dragFrom = dragEvent.getFrom();
                dragPerformed = false;
            } else if (dragEvent.getId() == MTGestureEvent.GESTURE_UPDATED) {
                if (dragFrom != null) {
                    float dragDistance = dragFrom.distance(dragEvent.getTo());

                    // stop processing this event if the distance hasn't reached the minimum distance yet
                    if (dragDistance < minimumDistance) {
                        return true;
                    }

                    dragPerformed = true;
                }
            }

            if (lockedAxis != null
                    && (dragEvent.getId() == MTGestureEvent.GESTURE_UPDATED
                    || dragEvent.getId() == MTGestureEvent.GESTURE_ENDED)) {

                // Lock the actual translation vector.
                // The cursor position is not locked, because it might be shared across different
                // input processors and would affect their processing.
                switch (lockedAxis) {
                    case X:
                        dragEvent.getTranslationVect().setX(0f);
                        break;
                    case Y:
                        dragEvent.getTranslationVect().setY(0f);
                        break;
                }
            }
        }

        return super.processGestureEvent(event);
    }

    @Override
    protected void translate(IMTComponent3D comp, DragEvent de) {
        if (!gestureAborted()) {
            comp.translateGlobal(new Vector3D(de.getTranslationVect()));
        }
    }

    /**
     * Returns whether a drag was performed during processing of several drag events.
     *
     * @return true, when a drag was performed, false otherwise
     */
    public boolean wasDragPerformed() {
        return dragPerformed;
    }

}
