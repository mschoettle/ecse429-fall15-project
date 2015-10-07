package ca.mcgill.sel.ram.ui.views.message.handler.impl;

import org.mt4j.components.TransformSpace;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapAndHoldProcessor.TapAndHoldEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;
import org.mt4j.input.inputProcessors.componentProcessors.unistrokeProcessor.UnistrokeEvent;
import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.ram.FragmentContainer;
import ca.mcgill.sel.ram.Lifeline;
import ca.mcgill.sel.ram.controller.ControllerFactory;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.events.Axis;
import ca.mcgill.sel.ram.ui.events.DelayedDrag;
import ca.mcgill.sel.ram.ui.views.TextView;
import ca.mcgill.sel.ram.ui.views.handler.BaseHandler;
import ca.mcgill.sel.ram.ui.views.handler.HandlerFactory;
import ca.mcgill.sel.ram.ui.views.message.LifelineView;
import ca.mcgill.sel.ram.ui.views.message.MessageViewView;
import ca.mcgill.sel.ram.ui.views.message.handler.ILifelineViewHandler;

/**
 * The default handler for {@link LifelineView}s.
 *
 * @author mschoettle
 */
public class LifelineViewHandler extends BaseHandler implements ILifelineViewHandler {

    /**
     * The minimum distance an object has to be dragged before it actually is dragged.
     */
    private static final float MIN_DRAG_DISTANCE = 11.5f;

    /**
     * The delayed drag action with the drag restricted to only the x-axis. Currently, dragging on the y-axis will cause
     * a stack over flow due to an update loop caused when resizing the spacer and updateParent called in
     * RamRectangleComponent.setSizeLocal(...).
     */
    private DelayedDrag dragAction = new DelayedDrag(MIN_DRAG_DISTANCE, Axis.Y);

    @Override
    public boolean processDragEvent(DragEvent dragEvent) {
        if (dragEvent.getTarget() instanceof RamRectangleComponent) {
            RamRectangleComponent lifelineNameBox = (RamRectangleComponent) dragEvent.getTarget();
            LifelineView target = (LifelineView) lifelineNameBox.getParent();
            dragEvent.setTarget(target);

            switch (dragEvent.getId()) {
                case MTGestureEvent.GESTURE_STARTED:
                case MTGestureEvent.GESTURE_UPDATED:
                    dragAction.processGestureEvent(dragEvent);
                    break;
                case MTGestureEvent.GESTURE_ENDED:
                    if (dragAction.wasDragPerformed()) {
                        Lifeline lifeline = target.getLifeline();

                        // Relative position is required in order to correctly set the position
                        // independent of zoom level.
                        Vector3D position = target.getPosition(TransformSpace.RELATIVE_TO_PARENT);

                        ControllerFactory.INSTANCE.getMessageViewController().moveLifeline(lifeline, position.getX(),
                                position.getY());
                    }
                    break;
            }

            return true;
        }

        return false;
    }

    @Override
    public boolean processUnistrokeEvent(UnistrokeEvent unistrokeEvent) {
        LifelineView lifelineView = (LifelineView) unistrokeEvent.getTarget();
        MessageViewView parent = lifelineView.getMessageViewView();
        unistrokeEvent.setTarget(parent);

        return parent.getHandler().processUnistrokeEvent(unistrokeEvent);
    }

    @Override
    public boolean processTapEvent(TapEvent tapEvent) {
        if (tapEvent.isDoubleTap() && tapEvent.getTarget() instanceof TextView) {
            TextView textView = (TextView) tapEvent.getTarget();
            Object featureValue = textView.getData().eGet(textView.getFeature());

            // Allow changing "represents" only if it hasn't been set yet.
            if (featureValue == null) {
                HandlerFactory.INSTANCE.getTextViewHandler().processTapEvent(tapEvent);
            }
        }

        return true;
    }

    @Override
    public boolean processTapAndHoldEvent(TapAndHoldEvent tapAndHoldEvent) {

        if (tapAndHoldEvent.isHoldComplete()) {
            if (tapAndHoldEvent.getTarget() instanceof LifelineView) {
                LifelineView lifelineView = (LifelineView) tapAndHoldEvent.getTarget();

                MessageViewView parent = lifelineView.getMessageViewView();
                Vector3D location = tapAndHoldEvent.getCursor().getStartPosition();

                FragmentContainer container = lifelineView.getFragmentContainerAt(location);
                parent.getHandler().handleCreateFragment(parent, lifelineView, location, container);
            }
        }

        return true;
    }

    @Override
    public void removeLifeline(LifelineView lifelineView) {

    }

}
