package ca.mcgill.sel.ram.ui.events;

import java.awt.event.InputEvent;

import org.mt4j.MTApplication;
import org.mt4j.components.MTCanvas;
import org.mt4j.components.interfaces.IMTComponent3D;
import org.mt4j.input.inputData.MTInputEvent;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.AbstractComponentProcessor;
import org.mt4j.util.math.Vector3D;

import processing.event.MouseEvent;
import ca.mcgill.sel.ram.ui.RamApp;

/**
 * The MouseWheelProcessor listens for mouse wheel events and forwards them to all registered listeners.
 * Currently it creates a {@link org.mt4j.input.inputProcessors.componentProcessors.scaleProcessor.ScaleEvent} and
 * forwards it, but this should migrate to a new event type.
 *
 * @author vbonnet
 */
public class MouseWheelProcessor extends AbstractComponentProcessor {

    private MTApplication application;

    /**
     * Creates a new MouseWheelProcessor that listen to MouseWheel events for the application.
     *
     * @param app
     *            The application whose MouseWheel events will be caught.
     */
    public MouseWheelProcessor(final MTApplication app) {
        application = app;
        application.registerMethod("mouseEvent", this);
    }

    @Override
    public String getName() {
        return "MouseWheelProcessor";
    }

    @Override
    public boolean isInterestedIn(final MTInputEvent inputEvt) {
        return false;
    }

    /**
     * Mouse event.
     *
     * @param event the event
     */
    public void mouseEvent(MouseEvent event) {
        // System.out.println(event.getButton());
        // /*
        switch (event.getAction()) {
            case MouseEvent.WHEEL:
                this.mouseWheeled(event);
                break;
        }
        // */
    }

    /**
     * Handle MouseEven when the action is "wheel".
     *
     * @param mouseWheelEvent the event
     */
    public void mouseWheeled(final MouseEvent mouseWheelEvent) {

        RamApp.getApplication().invokeLater(new Runnable() {
            @Override
            public void run() {
                // run later otherwise we can catch the graphics thread at a bad time
                final int x = mouseWheelEvent.getX();
                final int y = mouseWheelEvent.getY();
                float scale = 1.0f + mouseWheelEvent.getCount() / 100f;
                // there is a difference between the actual mouse wheel and two-finger pinch (it is reversed)
                // when fingers are used there is a modifier (CTRL), so we can use that and reverse the scale
                if (mouseWheelEvent.getModifiers() == InputEvent.CTRL_DOWN_MASK) {
                    scale *= -1;
                }

                MTCanvas canvas = application.getCurrentScene().getCanvas();
                final IMTComponent3D component = canvas.getComponentAt((float) x, (float) y);
                final Vector3D scalingPoint = new Vector3D((float) x, (float) y);
                final WheelEvent mouseWheelEvent =
                        new WheelEvent(MouseWheelProcessor.this, MTGestureEvent.GESTURE_UPDATED,
                                component, scale, scalingPoint);
                fireGestureEvent(mouseWheelEvent);
            }
        });
    }

    @Override
    protected void preProcessImpl(final MTInputEvent inputEvent) {
    }

    @Override
    protected void processInputEvtImpl(final MTInputEvent inputEvent) {
    }
}
