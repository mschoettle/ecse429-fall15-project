package ca.mcgill.sel.ram.ui.views.message.old;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.mt4j.components.MTComponent;
import org.mt4j.input.gestureAction.InertiaDragAction;
import org.mt4j.input.inputProcessors.IGestureEventListener;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragEvent;
import org.mt4j.input.inputProcessors.componentProcessors.panProcessor.PanEvent;
import org.mt4j.input.inputProcessors.componentProcessors.panProcessor.PanProcessorTwoFingers;
import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.ram.AbstractMessageView;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.AspectMessageView;
import ca.mcgill.sel.ram.MessageView;
import ca.mcgill.sel.ram.MessageViewReference;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.events.DelayedDrag;
import ca.mcgill.sel.ram.ui.events.RightClickDragProcessor;
import ca.mcgill.sel.ram.ui.events.listeners.IDragListener;
import ca.mcgill.sel.ram.ui.events.listeners.IPanListener;
import ca.mcgill.sel.ram.ui.layouts.VerticalLayout;
import ca.mcgill.sel.ram.ui.utils.Constants;
import ca.mcgill.sel.ram.ui.views.handler.BaseHandler;

/**
 * The view container which displays all message views of an aspect.
 *
 * @author mschoettle
 */
// TODO: Extend AbstractView instead
public class MessageViewContainer extends RamRectangleComponent implements INotifyChangedListener {

    /**
     * The internal handler of this view.
     */
    // TODO: move to external class (necessary anyway once AbstractView is extended)
    private class Handler extends BaseHandler implements IDragListener, IPanListener {

        private DelayedDrag dragAction = new DelayedDrag(Constants.DELAYED_DRAG_MIN_DRAG_DISTANCE);
        private InertiaDragAction inertiaAction = new InertiaDragAction();

        @Override
        public boolean processDragEvent(DragEvent dragEvent) {
            // fix the x value, allow only to move vertically
            // dragEvent.getTranslationVect().setX(0);
            dragEvent.setTarget(viewContainer);

            dragAction.processGestureEvent(dragEvent);

            return true;
        }

        @Override
        public boolean processPanEvent(PanEvent panEvent) {
            MTComponent containerLayer = viewContainer;

            switch (panEvent.getId()) {
                case MTGestureEvent.GESTURE_UPDATED:
                    containerLayer.translate(new Vector3D(panEvent.getTranslationVector().x,
                            panEvent.getTranslationVector().y));
                    break;

                case MTGestureEvent.GESTURE_ENDED:
                    DragEvent de = new DragEvent(panEvent.getSource(), panEvent.getId(), containerLayer,
                            panEvent.getFirstCursor(), panEvent.getFirstCursor().getStartPosition(),
                            panEvent.getFirstCursor().getPosition());
                    inertiaAction.processGestureEvent(de);
                    break;
            }

            return false;
        }

    }

    private static final int PADDING = 30;
    private static final float START_X = 200.0f;
    private static final float START_Y = 65.0f;

    private RamRectangleComponent viewContainer;

    private Aspect aspect;
    private Map<AbstractMessageView, AbstractMessageViewView<?>> messageViews;

    /**
     * Creates a new message view container for the given aspect.
     *
     * @param aspect the aspect for which message views should be displayed
     * @param width the width of this container
     * @param height the height of this container
     */
    public MessageViewContainer(Aspect aspect, float width, float height) {
        super(0, 0, width, height);

        this.aspect = aspect;
        this.messageViews = new HashMap<AbstractMessageView, AbstractMessageViewView<?>>();

        // setComposite(true);

        // everything will be added to the container
        // that way this view maintains the whole width and height
        // and the container can be translated while gestures can be received on the whole background
        viewContainer = new RamRectangleComponent(0, 0, width, height);
        addChild(viewContainer);

        for (AbstractMessageView messageView : aspect.getMessageViews()) {
            addMessageView(messageView);
        }

        viewContainer.setLayout(new VerticalLayout(PADDING));
        viewContainer.translate(START_X, START_Y);

        registerGestureListeners(new Handler());

        EMFEditUtil.addListenerFor(aspect, this);
    }

    @Override
    protected void destroyComponent() {
        EMFEditUtil.removeListenerFor(aspect, this);
    }

    /**
     * Adds the given message view to this container (at the end).
     * Each subclass of {@link AbstractMessageView} has its own visualization.
     *
     * @param messageView the {@link AbstractMessageView} to add
     */
    private void addMessageView(AbstractMessageView messageView) {
        float viewWidth = getWidth() - START_X;
        AbstractMessageViewView<?> view = null;
        if (messageView instanceof MessageView) {
            view = new MessageViewView((MessageView) messageView);
        } else if (messageView instanceof AspectMessageView) {
            view = new AspectMessageViewView((AspectMessageView) messageView);
        } else if (messageView instanceof MessageViewReference) {
            view = new MessageViewReferenceView((MessageViewReference) messageView);
        }

        view.setWidthLocal(viewWidth);
        // build later (not in constructor)
        // in order to let the view have its width
        view.build();
        viewContainer.addChild(view);

        messageViews.put(messageView, view);
    }

    /**
     * Removes the given message view from this container.
     *
     * @param messageView the {@link AbstractMessageView} to remove
     */
    private void removeMessageView(AbstractMessageView messageView) {
        AbstractMessageViewView<?> view = messageViews.get(messageView);
        view.destroy();
    }

    @Override
    public void notifyChanged(Notification notification) {
        if (notification.getNotifier() == aspect
                && notification.getFeature() == RamPackage.Literals.ASPECT__MESSAGE_VIEWS) {
            switch (notification.getEventType()) {
                case Notification.ADD:
                    AbstractMessageView messageView = (AbstractMessageView) notification.getNewValue();
                    addMessageView(messageView);
                    break;
                case Notification.REMOVE:
                    messageView = (AbstractMessageView) notification.getOldValue();
                    removeMessageView(messageView);
                    break;
            }
        }
    }

    /**
     * Registers the given listener as a gesture listener for the required processors.
     *
     * @param listener the listener to register
     */
    private void registerGestureListeners(IGestureEventListener listener) {
        addGestureListener(RightClickDragProcessor.class, listener);
        addGestureListener(PanProcessorTwoFingers.class, listener);
    }

    @Override
    protected void registerInputProcessors() {
        registerInputProcessor(new RightClickDragProcessor(RamApp.getApplication()));
        registerInputProcessor(new PanProcessorTwoFingers(RamApp.getApplication()));
    }

}
