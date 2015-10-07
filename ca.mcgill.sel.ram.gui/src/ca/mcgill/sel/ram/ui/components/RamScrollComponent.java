package ca.mcgill.sel.ram.ui.components;

import org.mt4j.components.TransformSpace;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.AbstractComponentProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragEvent;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragProcessor;
import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.events.MouseWheelProcessor;
import ca.mcgill.sel.ram.ui.events.WheelEvent;
import ca.mcgill.sel.ram.ui.events.listeners.IDragListener;
import ca.mcgill.sel.ram.ui.views.handler.BaseHandler;

/**
 * Scroll view component that contains another view and allows the user to drag/scroll the inner view.
 * 
 * @author tdimeco
 */
public class RamScrollComponent extends RamRectangleComponent {
    
    private static final float DEFAULT_MOUSE_WHEEL_SENSITIVITY = 100;
    
    private RamRectangleComponent innerView;
    private float mouseWheelSensitivity;
    private float minimumDragDistance;
    
    /**
     * Constructs a new scroll component.
     */
    public RamScrollComponent() {
        this(0, 0, 0, 0, null);
    }
    
    /**
     * Constructs a new scroll component.
     * 
     * @param innerView The inner view you want to scroll
     */
    public RamScrollComponent(RamRectangleComponent innerView) {
        this(0, 0, 0, 0, innerView);
    }
    
    /**
     * Constructs a new scroll component.
     * 
     * @param width The width
     * @param height The height
     */
    public RamScrollComponent(float width, float height) {
        this(0, 0, width, height, null);
    }
    
    /**
     * Constructs a new scroll component.
     * 
     * @param width The width
     * @param height The height
     * @param innerView The inner view you want to scroll
     */
    public RamScrollComponent(float width, float height, RamRectangleComponent innerView) {
        this(0, 0, width, height, innerView);
    }
    
    /**
     * Constructs a new scroll component.
     * 
     * @param x The x position
     * @param y The y position
     * @param width The width
     * @param height The height
     */
    public RamScrollComponent(float x, float y, float width, float height) {
        this(x, y, width, height, null);
    }
    
    /**
     * Constructs a new scroll component.
     * 
     * @param x The x position
     * @param y The y position
     * @param width The width
     * @param height The height
     * @param innerView The inner view you want to scroll
     */
    public RamScrollComponent(float x, float y, float width, float height, RamRectangleComponent innerView) {
        
        super(x, y, width, height);
        this.mouseWheelSensitivity = DEFAULT_MOUSE_WHEEL_SENSITIVITY;
        if (innerView != null) {
            setInnerView(innerView);
        }
        
        // Events
        DragHandler handler = new DragHandler();
        addGestureListener(DragProcessor.class, handler);
        addGestureListener(MouseWheelProcessor.class, handler);
    }
    
    @Override
    protected void registerInputProcessors() {
        
        registerInputProcessor(new DragProcessor(RamApp.getApplication()));
        registerInputProcessor(new MouseWheelProcessor(RamApp.getApplication()));
        
        for (AbstractComponentProcessor processor : getInputProcessors()) {
            processor.setStopPropagation(true);
            processor.setBubbledEventsEnabled(true);
        }
    }
    
    /**
     * Update the inner view position depending on the delta.
     * 
     * @param deltaY The Y delta position
     */
    protected void dragInnerView(float deltaY) {
        
        // Check if the inner view exists
        if (innerView == null) {
            return;
        }
        
        final float innerViewX = innerView.getPosition(TransformSpace.RELATIVE_TO_PARENT).getX();
        final float innerViewY = innerView.getPosition(TransformSpace.RELATIVE_TO_PARENT).getY();
        
        if (deltaY > 0) {
            final float yTop = getBufferSize(Cardinal.NORTH);
            final float yTopInnerView = innerViewY;
            
            if (yTopInnerView < yTop) {
                innerView.setPositionRelativeToParent(new Vector3D(
                        innerViewX,
                        innerViewY + Math.min(deltaY, yTop - yTopInnerView)));
            }
        } else if (deltaY < 0) {
            final float yBottom = getHeight() - getBufferSize(Cardinal.SOUTH);
            final float yBottomInnerView = innerViewY + innerView.getHeight();
            
            if (yBottomInnerView > yBottom) {
                innerView.setPositionRelativeToParent(new Vector3D(
                        innerViewX,
                        innerViewY - Math.min(-deltaY, yBottomInnerView - yBottom)));
            }
        }
    }
    
    /**
     * Sets the inner view of the scroll component.
     * 
     * @param innerView The inner view
     */
    public void setInnerView(RamRectangleComponent innerView) {
        this.innerView = innerView;
        removeAllChildren();
        addChild(innerView);
    }
    
    /**
     * Gets the inner view of the scroll component.
     * 
     * @return The inner view, or null if there is no inner view
     */
    public RamRectangleComponent getInnerView() {
        return innerView;
    }
    
    /**
     * Gets the mouse wheel sensitivity.
     * 
     * @return The mouse wheel sensitivity.
     */
    public float getMouseWheelSensitivity() {
        return mouseWheelSensitivity;
    }
    
    /**
     * Sets the mouse wheel sensitivity.
     * 
     * @param mouseWheelSensitivity The mouse wheel sensitivity
     */
    public void setMouseWheelSensitivity(float mouseWheelSensitivity) {
        this.mouseWheelSensitivity = mouseWheelSensitivity;
    }
    
    /**
     * Gets the minimum drag distance.
     * 
     * @return The minimum drag distance.
     */
    public float getMinimumDragDistance() {
        return minimumDragDistance;
    }
    
    /**
     * Sets the minimum drag distance.
     * 
     * @param minimumDragDistance The minimum drag distance
     */
    public void setMinimumDragDistance(float minimumDragDistance) {
        this.minimumDragDistance = minimumDragDistance;
    }
    
    /**
     * Drag events handler for the scroll component.
     */
    private class DragHandler extends BaseHandler implements IDragListener {
        
        private Vector3D dragFrom;
        private boolean dragStarted;
        
        @Override
        public boolean processDragEvent(DragEvent dragEvent) {
            
            final Vector3D from = dragEvent.getFrom();
            final Vector3D to = dragEvent.getTo();
            
            if (dragEvent.getId() == MTGestureEvent.GESTURE_STARTED) {
                dragFrom = from;
                dragStarted = false;
            }
            
            // Check minimum distance
            if (dragFrom != null && (dragFrom.distance(to) >= minimumDragDistance || dragStarted)) {
                dragStarted = true;
                final float deltaY = globalToLocal(to).getY() - globalToLocal(from).getY();
                dragInnerView(deltaY);
            }
            
            return true;
        }
        
        /**
         * Process the mouse wheel event.
         * 
         * @param wheelEvent The wheel event
         * @return Whether the event was processed or not
         */
        protected boolean processWheelEvent(WheelEvent wheelEvent) {
            
            // NOTE: Add the hasBounds() check because sometimes,
            // the shape has no bounds the first time the event is called.
            if (!hasBounds() || !getBounds().containsPointLocal(globalToLocal(wheelEvent.getLocationOnScreen()))) {
                return true;
            }
            
            final float scale = wheelEvent.getScale();
            final float deltaY = -(scale - 1) * mouseWheelSensitivity;
            dragInnerView(deltaY);
            
            return true;
        }
        
        @Override
        public boolean processGestureEvent(MTGestureEvent gestureEvent) {
            if (gestureEvent instanceof WheelEvent) {
                return processWheelEvent((WheelEvent) gestureEvent);
            }
            return super.processGestureEvent(gestureEvent);
        }
    }
}
