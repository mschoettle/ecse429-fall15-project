package ca.mcgill.sel.ram.ui.views.handler.impl;

import org.mt4j.components.MTComponent;
import org.mt4j.input.gestureAction.DefaultScaleAction;
import org.mt4j.input.gestureAction.InertiaDragAction;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragEvent;
import org.mt4j.input.inputProcessors.componentProcessors.panProcessor.PanEvent;
import org.mt4j.input.inputProcessors.componentProcessors.scaleProcessor.ScaleEvent;
import org.mt4j.input.inputProcessors.componentProcessors.unistrokeProcessor.UnistrokeEvent;
import org.mt4j.input.inputProcessors.componentProcessors.unistrokeProcessor.UnistrokeUtils.UnistrokeGesture;
import org.mt4j.input.inputProcessors.componentProcessors.zoomProcessor.ZoomEvent;
import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.ram.ui.events.WheelEvent;
import ca.mcgill.sel.ram.ui.views.AbstractView;
import ca.mcgill.sel.ram.ui.views.handler.BaseHandler;
import ca.mcgill.sel.ram.ui.views.handler.IAbstractViewHandler;

/**
 * The default handler for a {@link ca.mcgill.sel.ram.ui.views.AbstractView}. It handles {@link PanEvent},
 * {@link ZoomEvent}, {@link UnistrokeEvent} and {@link WheelEvent}s. {@link UnistrokeEvent}s are forwarded to
 * sub-classes and can be handled appropriately.
 *
 * @author mschoettle
 */
public abstract class AbstractViewHandler extends BaseHandler implements IAbstractViewHandler {

    private static final double SCALE_FACTOR_ADJUSTMENT = 0.005;

    private InertiaDragAction inertiaDrag = new InertiaDragAction();

    @Override
    public boolean processDragEvent(DragEvent dragEvent) {
        AbstractView<?> target = (AbstractView<?>) dragEvent.getTarget();

        // We need to drag the container layer which has all the view components of the structural diagram
        // so that our structural diagram view still covers the entire screen to catch gestures.
        MTComponent containerLayer = target.getContainerLayer();

        switch (dragEvent.getId()) {
            case MTGestureEvent.GESTURE_CANCELED:
            case MTGestureEvent.GESTURE_ENDED:
                DragEvent inertiaDragEvent =
                        new DragEvent(dragEvent.getSource(), dragEvent.getId(), containerLayer,
                                dragEvent.getDragCursor(), dragEvent.getDragCursor().getStartPosition(), dragEvent
                                        .getDragCursor().getPosition());
                inertiaDrag.processGestureEvent(inertiaDragEvent);
                break;
            case MTGestureEvent.GESTURE_UPDATED:
                containerLayer.translate(dragEvent.getTranslationVect());
                break;
        }

        return true;
    }

    @Override
    public boolean processGestureEvent(MTGestureEvent gestureEvent) {

        if (gestureEvent instanceof WheelEvent) {
            WheelEvent wheelEvent = (WheelEvent) gestureEvent;
            return processWheelEvent(wheelEvent);
        } else if (gestureEvent instanceof ZoomEvent) {
            ZoomEvent zoomEvent = (ZoomEvent) gestureEvent;
            return processZoomEvent(zoomEvent);
        }

        return super.processGestureEvent(gestureEvent);
    }

    /**
     * Handles a {@link PanEvent}. Moves the contents of the {@link ca.mcgill.sel.ram.ui.views.AbstractView} (i.e., the
     * container layer).
     *
     * @param panEvent the {@link PanEvent}
     * @return true, if the event was handled, false otherwise
     */
    @Override
    public boolean processPanEvent(PanEvent panEvent) {
        AbstractView<?> target = (AbstractView<?>) panEvent.getTarget();
        MTComponent containerLayer = target.getContainerLayer();

        switch (panEvent.getId()) {
            case MTGestureEvent.GESTURE_STARTED:
                // sometimes panning leaves yellow marks unistroke events
                target.cleanUnistrokeLayer();
                break;
            case MTGestureEvent.GESTURE_UPDATED:
                containerLayer.translate(new Vector3D(panEvent.getTranslationVector().x, panEvent
                        .getTranslationVector().y));
                break;
            case MTGestureEvent.GESTURE_ENDED:
                DragEvent dragEvent =
                        new DragEvent(panEvent.getSource(), panEvent.getId(), containerLayer,
                                panEvent.getFirstCursor(), panEvent.getFirstCursor().getStartPosition(), panEvent
                                        .getFirstCursor().getPosition());
                inertiaDrag.processGestureEvent(dragEvent);
                break;
        }

        return true;
    }

    /**
     * Handles different {@link UnistrokeEvent}s. Forwards the specific handling of gestures to sub-classes.
     *
     * @param uniStrokeEvent the {@link UnistrokeEvent}
     * @return true, if the event was handled, false otherwise
     */
    @Override
    public boolean processUnistrokeEvent(UnistrokeEvent uniStrokeEvent) {
        AbstractView<?> target = (AbstractView<?>) uniStrokeEvent.getTarget();

        switch (uniStrokeEvent.getId()) {
            case MTGestureEvent.GESTURE_STARTED:
                target.getUnistrokeLayer().addChild(uniStrokeEvent.getVisualization());
                break;
            case MTGestureEvent.GESTURE_UPDATED:
                break;
            case MTGestureEvent.GESTURE_ENDED:
                UnistrokeGesture g = uniStrokeEvent.getGesture();
                Vector3D startPosition = uniStrokeEvent.getCursor().getStartPosition();
                handleUnistrokeGesture(target, g, startPosition, uniStrokeEvent);

                target.cleanUnistrokeLayer();
                break;

            case MTGestureEvent.GESTURE_CANCELED:
                target.cleanUnistrokeLayer();
                break;
        }

        return true;
    }

    /**
     * Handles the gesture performed on the target. If the gesture is not supported, it is ignored.
     *
     * @param target the {@link AbstractView} on which the gesture was performed
     * @param gesture the gesture performed
     * @param startPosition the position at which the gesture started
     * @param event the {@link UnistrokeEvent}, which contains all information regarding the gesture
     */
    public abstract void handleUnistrokeGesture(AbstractView<?> target, UnistrokeGesture gesture,
            Vector3D startPosition, UnistrokeEvent event);

    /**
     * Handles a {@link WheelEvent} caused by a mouse wheel rotation. Scales the abstract view accordingly.
     *
     * @param wheelEvent the {@link WheelEvent}
     * @return true, if the event was handled, false otherwise
     */
    protected boolean processWheelEvent(WheelEvent wheelEvent) {
        if (wheelEvent.getTarget() instanceof AbstractView) {
            /**
             * Because the wheel event is global, all views that are a sub-class of AbstractView might receive this
             * event. We need to make sure that only the event where the handler matches actually handles it.
             */
            AbstractView<?> target = (AbstractView<?>) wheelEvent.getTarget();
            AbstractViewHandler handler = (AbstractViewHandler) target.getHandler();
            if (this == handler) {
                MTComponent containerLayer = target.getContainerLayer();

                ScaleEvent se = wheelEvent.asScaleEvent(target);
                DefaultScaleAction defaultScaleAction = new DefaultScaleAction(containerLayer);
                defaultScaleAction.processGestureEvent(se);

                return true;
            }
        }

        return false;
    }

    /**
     * Handles a {@link ZoomEvent}. We use this because default zoom action works on canvas' camera and also it causes
     * trouble. After processing of default zoom action, camera angle change causes problems with the pick() function
     * (in container component). So instead of default zoom action we define our own action for the zoom gesture. The
     * speed of the zoom gesture is fixed no matter how fast you do the pinch in/out gesture.
     *
     * @param zoomEvent the zoom event to handle
     * @return true, if the event was handled, false otherwise
     */
    protected boolean processZoomEvent(ZoomEvent zoomEvent) {
        AbstractView<?> target = (AbstractView<?>) zoomEvent.getTarget();
        MTComponent containerLayer = target.getContainerLayer();

        float camZoomAmount = zoomEvent.getCamZoomAmount();
        float scaleFactor = 1;

        // TODO: zoom amount can be made more precise and set depending on the speed. Also the view is not centered
        // after zoom.
        // camZoomAmount grows really fast and can be huge. using that causes strange behaviours so we give fix scaling
        // ratio
        // It can be modified to be more smooth depending on the zooming speed
        if (camZoomAmount > 0) {
            scaleFactor += SCALE_FACTOR_ADJUSTMENT;
        } else if (camZoomAmount < 0) {
            scaleFactor -= SCALE_FACTOR_ADJUSTMENT;
        }

        containerLayer.scaleGlobal(scaleFactor, scaleFactor, 1, zoomEvent.getFirstCursor().getPosition());

        return false;
    }

}
