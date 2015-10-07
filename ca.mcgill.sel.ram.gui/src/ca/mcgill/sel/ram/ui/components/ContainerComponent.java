package ca.mcgill.sel.ram.ui.components;

import org.mt4j.components.MTComponent;
import org.mt4j.components.PickResult;
import org.mt4j.components.PickResult.PickEntry;
import org.mt4j.input.gestureAction.TapAndHoldVisualizer;
import org.mt4j.input.inputProcessors.IGestureEventListener;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragEvent;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.scaleProcessor.ScaleEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapAndHoldProcessor.TapAndHoldEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapAndHoldProcessor.TapAndHoldProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.unistrokeProcessor.UnistrokeEvent;
import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.events.WheelEvent;
import ca.mcgill.sel.ram.ui.layouts.AbstractBaseLayout;
import ca.mcgill.sel.ram.ui.layouts.Layout;
import ca.mcgill.sel.ram.ui.utils.Constants;
import ca.mcgill.sel.ram.ui.views.handler.IHandled;

/**
 * A container component which uses an overlay on top of itself to catch gesture events and handle them. Passes events
 * to its children until a child handles the event. Children must implement the {@link IHandled} interface. Also, its
 * layout is vertical layout by default. You can change it by using setLayout() method.
 * 
 * @param <T>
 *            the handler interface that this component uses
 * @see IHandled
 * @author mschoettle
 */
public class ContainerComponent<T extends IGestureEventListener> extends RamRectangleComponent implements IHandled<T> {

    /**
     * A layout that adaptively adjusts its sizes depending on the children inside a container.
     */
    private class AdaptiveLayout extends AbstractBaseLayout {

        @Override
        public void layout(RamRectangleComponent component, LayoutUpdatePhase updatePhase) {
            if (updatePhase == LayoutUpdatePhase.FROM_CHILD) {
                float width = childContainer.getWidth();
                float height = childContainer.getHeight();

                // minimum size has to be set (not size local)
                setMinimumSize(component, width, height);
            }
        }
    }

    /**
     * The handler for this component.
     */
    protected T handler;

    /**
     * The transparent overlay on top of this component to receive input events.
     */
    protected RamRectangleComponent inputOverlay;

    /**
     * The actual container for all children to be able to support the overlay.
     */
    protected RamRectangleComponent childContainer;

    /**
     * Remembers whether a tap and hold was performed.
     * Workaround to prevent receiving a tap and tap and hold event at the same time.
     */
    protected boolean tapAndHoldPerformed;

    /**
     * Creates a new container component.
     */
    public ContainerComponent() {
        super();

        childContainer = new RamRectangleComponent();
        childContainer.setName("childContainer");
        super.addChild(childContainer);

        inputOverlay = new RamRectangleComponent();
        inputOverlay.setName("inputOverlay");
        // // can be used for debugging purposes (transparent overlay)
        // inputOverlay.setNoFill(false);
        // inputOverlay.setFillColor(new org.mt4j.util.MTColor(128, 128, 128, 180));

        // inputOverlay component should consume all picking.
        inputOverlay.setComposite(true);

        // To make inoutOverlay on top of the childContainer we added as a child after the childContainer
        super.addChild(inputOverlay);

        // add an adaptive layout (resizes this component and the input overlay)
        // since setLayout is overwritten we need to call it on the super class
        super.setLayout(new AdaptiveLayout());
    }

    @Override
    public void addChild(MTComponent child) {
        childContainer.addChild(child);
    }

    @Override
    public T getHandler() {
        return handler;
    }

    @Override
    protected void handleChildResized(MTComponent component) {
        // adjust the size of the overlay
        inputOverlay.setSizeLocal(childContainer.getWidth(), childContainer.getHeight());
    }

    @Override
    public boolean processGestureEvent(MTGestureEvent gestureEvent) {
        Vector3D pickPoint = null;
        boolean stopProcessing = false;

        if (gestureEvent instanceof TapEvent) {
            // prevent a tap event from being processed when a tap and hold event was processed before
            if (tapAndHoldPerformed) {
                tapAndHoldPerformed = false;
                stopProcessing = true;
            }

            pickPoint = ((TapEvent) gestureEvent).getLocationOnScreen();
        } else if (gestureEvent instanceof DragEvent) {
            pickPoint = ((DragEvent) gestureEvent).getFrom();
        } else if (gestureEvent instanceof TapAndHoldEvent) {
            TapAndHoldEvent tapAndHoldEvent = (TapAndHoldEvent) gestureEvent;
            // used for workaround to prevent tap event from being executed at the same time
            switch (tapAndHoldEvent.getId()) {
                case MTGestureEvent.GESTURE_ENDED:
                    if (tapAndHoldEvent.isHoldComplete()) {
                        tapAndHoldPerformed = true;
                    }
                    break;
            }

            pickPoint = tapAndHoldEvent.getLocationOnScreen();
        } else if (gestureEvent instanceof ScaleEvent) {
            pickPoint = ((ScaleEvent) gestureEvent).getScalingPoint();
        } else if (gestureEvent instanceof WheelEvent) {
            pickPoint = ((WheelEvent) gestureEvent).getLocationOnScreen();
        } else if (gestureEvent instanceof UnistrokeEvent) {
            pickPoint = ((UnistrokeEvent) gestureEvent).getCursor().getStartPosition();
            if (pickPoint == null) {
                stopProcessing = true;
            }
        } else {
            stopProcessing = true;
        }

        if (!stopProcessing) {
            PickResult pickResults = pick(pickPoint.getX(), pickPoint.getY(), false);

            for (final PickEntry pick : pickResults.getPickList()) {

                final MTComponent pickComponent = pick.hitObj;
                if (pickComponent != inputOverlay) {
                    gestureEvent.setTarget(pickComponent);
                    if (pickComponent instanceof IHandled) {
                        // see if the component wants to handle the event
                        IHandled<?> handledComponent = (IHandled<?>) pickComponent;

                        if (handledComponent.getHandler() != null
                                && handledComponent.getHandler().processGestureEvent(gestureEvent)) {
                            return false;
                        }
                    } else if (pickComponent instanceof RamButton) {
                        if (pickComponent.processGestureEvent(gestureEvent)) {
                            return false;
                        }
                    }

                }
            }
        }

        return false;
    }

    /**
     * Registers the input processors and gesture listeners supported by this component.
     * Namely drag, tap and tap-and-hold.
     */
    protected void registerGestureListeners() {
        inputOverlay.registerInputProcessor(new DragProcessor(RamApp.getApplication()));
        inputOverlay.registerInputProcessor(new TapProcessor(RamApp.getApplication(), Constants.TAP_MAX_FINGER_UP,
                true, Constants.TAP_DOUBLE_TAP_TIME));
        inputOverlay.registerInputProcessor(new TapAndHoldProcessor(RamApp.getApplication(),
                Constants.TAP_AND_HOLD_DURATION));

        inputOverlay.addGestureListener(DragProcessor.class, this);
        inputOverlay.addGestureListener(TapProcessor.class, this);
        inputOverlay.addGestureListener(TapAndHoldProcessor.class, this);
        inputOverlay.addGestureListener(TapAndHoldProcessor.class,
                new TapAndHoldVisualizer(RamApp.getApplication(), getParent()));
    }

    @Override
    public void removeAllChildren() {
        childContainer.removeAllChildren();
    }

    @Override
    public void removeChild(MTComponent child) {
        childContainer.removeChild(child);
    }

    /**
     * Sets the handler for this view. Unregisters previous handler as gesture listener and registers the new handler.
     * <b>Important:</b> Has to be called after addChild in order to have a parent.
     * 
     * @param handler the handler for this component
     */
    @Override
    public void setHandler(T handler) {
        if (RamApp.getApplication().getCanvas() == null || getParent() == null) {
            throw new RuntimeException("Component " + this
                    + " has no parent, handler may only be addded once the component has a parent"
                    + " (i.e., after adding it to another component)");
        }

        // unregister previous handlers
        // if (this.handler != null) {
        // removeAllGestureEventListeners();
        // }

        this.handler = handler;
        registerGestureListeners();
    }

    @Override
    public void setLayout(Layout layout) {
        childContainer.setLayout(layout);
    }

    @Override
    public void setMinimumWidth(float width) {
        childContainer.setMinimumWidth(width);
    }
}
