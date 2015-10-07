package ca.mcgill.sel.ram.ui.views.handler;

import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.mt4j.components.TransformSpace;
import org.mt4j.components.interfaces.IMTComponent3D;
import org.mt4j.input.gestureAction.TapAndHoldVisualizer;
import org.mt4j.input.inputProcessors.IGestureEventListener;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragEvent;
import org.mt4j.input.inputProcessors.componentProcessors.panProcessor.PanEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapAndHoldProcessor.TapAndHoldEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapAndHoldProcessor.TapAndHoldProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;
import org.mt4j.input.inputProcessors.componentProcessors.unistrokeProcessor.UnistrokeEvent;
import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.components.menu.RamLinkedMenu;
import ca.mcgill.sel.ram.ui.events.WheelEvent;
import ca.mcgill.sel.ram.ui.events.listeners.IDragListener;
import ca.mcgill.sel.ram.ui.events.listeners.IPanListener;
import ca.mcgill.sel.ram.ui.events.listeners.ITapAndHoldListener;
import ca.mcgill.sel.ram.ui.events.listeners.ITapListener;
import ca.mcgill.sel.ram.ui.events.listeners.IUnistrokeListener;

/**
 * Acts as the base for all handlers.
 * It is an {@link IGestureEventListener} such that it can be registered as a listener to any component.
 * It processes a gesture event and delegates it depending on its type
 * and if the sub class implements the respective interface.
 * Additionally, it prevents a {@link TapEvent} to be handled after a {@link TapAndHoldEvent} has been performed
 * completely.
 * Sub-classes may override {@link BaseHandler#processGestureEvent(MTGestureEvent)} to add support for more events.
 * 
 * @author mschoettle
 */
public abstract class BaseHandler implements IGestureEventListener {

    /**
     * The wait time after a completed pan to process wheel events again.
     */
    private static final long SCROLL_PAN_THRESHOLD = 1000;

    /**
     * Workaround to prevent receiving a tap and tap and hold event at the same time.
     */
    private boolean tapAndHoldPerformed;

    /**
     * Keep state whether a pan is currently taking place.
     * In Mac OS and Windows two finger panning automatically activates scrolling.
     */
    private boolean panActive;

    /**
     * The last time when the two finger panning gesture was completed.
     */
    private long timePanCompleted;

    /**
     * Processes default gesture events and forwards them to the implemented methods of its listener interface
     * if the handler implements it. Can be overridden but should
     * still be called after the additional tasks have been performed to preserve its functionality.
     * 
     * @param gestureEvent the gesture event that was triggered by the user
     * @return true, if the event was handled, false otherwise
     */
    @Override
    public boolean processGestureEvent(MTGestureEvent gestureEvent) {
        boolean eventProcessed = false;

        if (gestureEvent instanceof DragEvent && this instanceof IDragListener) {
            DragEvent dragEvent = (DragEvent) gestureEvent;

            eventProcessed = ((IDragListener) this).processDragEvent(dragEvent);
        } else if (gestureEvent instanceof TapEvent && this instanceof ITapListener) {
            TapEvent tapEvent = (TapEvent) gestureEvent;

            // prevent a tap event from being processed when a tap and hold event was processed before
            if (tapAndHoldPerformed) {
                tapAndHoldPerformed = false;
                return true;
            }

            eventProcessed = ((ITapListener) this).processTapEvent(tapEvent);
        } else if (gestureEvent instanceof TapAndHoldEvent) {
            TapAndHoldEvent tapAndHoldEvent = (TapAndHoldEvent) gestureEvent;

            // used for workaround to prevent tap event from being executed at the same time
            switch (tapAndHoldEvent.getId()) {
                case MTGestureEvent.GESTURE_ENDED:
                    if (tapAndHoldEvent.isHoldComplete()) {
                        tapAndHoldPerformed = true;
                    }
                    break;
                default:
                    break;
            }

            if (this instanceof ITapAndHoldListener) {
                eventProcessed = ((ITapAndHoldListener) this).processTapAndHoldEvent(tapAndHoldEvent);
            }
            if (!eventProcessed && this instanceof ILinkedMenuListener) {
                if (tapAndHoldEvent.isHoldComplete()) {
                    processLinkedMenuCreation((ILinkedMenuListener) this, tapAndHoldEvent);
                    eventProcessed = true;
                }
            }

        } else if (gestureEvent instanceof UnistrokeEvent && this instanceof IUnistrokeListener) {
            UnistrokeEvent unistrokeEvent = (UnistrokeEvent) gestureEvent;

            eventProcessed = ((IUnistrokeListener) this).processUnistrokeEvent(unistrokeEvent);
        } else if (gestureEvent instanceof PanEvent && this instanceof IPanListener) {
            PanEvent panEvent = (PanEvent) gestureEvent;

            // Workaround to prevent scrolling at the same time.
            switch (panEvent.getId()) {
                case MTGestureEvent.GESTURE_STARTED:
                    panActive = true;
                    break;
                case MTGestureEvent.GESTURE_ENDED:
                    panActive = false;
                    timePanCompleted = System.currentTimeMillis();
                    break;
            }

            eventProcessed = ((IPanListener) this).processPanEvent(panEvent);
        } else if (gestureEvent instanceof WheelEvent) {
            long timeSincePan = System.currentTimeMillis() - timePanCompleted;

            // Prevent processing wheel events (scrolling) if a pan is active
            // or wasn't completed long enough ago.
            // It is possible that the OS transforms pan events into scroll events.
            // We need to avoid this.
            // Furthermore, this means that after the pan is done, it is possible that
            // some inertia happens and more scroll events are received.
            // We wait until a certain time has passed to prevent this.
            if (panActive || timeSincePan <= SCROLL_PAN_THRESHOLD) {
                return true;
            }
        }

        return eventProcessed;
    }

    /**
     * Creates a contextual menu for the BaseView.
     * 
     * @param linkedMenuHandler - handler of the tap-and-holded component.
     * @param event - event sent when tap-and-holding the component.
     */
    private static void processLinkedMenuCreation(final ILinkedMenuListener linkedMenuHandler, TapAndHoldEvent event) {
        IMTComponent3D component = event.getTarget();
        if (component instanceof RamRectangleComponent) {
            RamRectangleComponent rectangle = (RamRectangleComponent) component;
            EObject eobject = linkedMenuHandler.getEobject(rectangle);
            final RamLinkedMenu menu =
                    new RamLinkedMenu(eobject, rectangle, linkedMenuHandler.getVisualLinkedComponent(rectangle));
            linkedMenuHandler.initMenu(menu);
            INotifyChangedListener notifyListener = new INotifyChangedListener() {

                @Override
                public void notifyChanged(Notification notification) {
                    linkedMenuHandler.updateMenu(menu, notification);
                }
            };
            List<EObject> eObjectsToListen = linkedMenuHandler.getEObjectToListenForUpdateMenu(rectangle);
            if (eObjectsToListen != null) {
                for (EObject eObject : eObjectsToListen) {
                    EMFEditUtil.addListenerFor(eObject, notifyListener);
                }
                menu.registerNotifyChangeListener(notifyListener,
                        linkedMenuHandler.getEObjectToListenForUpdateMenu(rectangle));
            }
            RamApp.getActiveScene().addComponent(menu, new Vector3D());
            menu.updateMenuPosition(menu.getPosition(TransformSpace.GLOBAL), true);
            menu.addGestureListener(TapAndHoldProcessor.class,
                    new TapAndHoldVisualizer(RamApp.getApplication(), menu.getParent()));
        }

    }
}
