package ca.mcgill.sel.ram.ui.views;

import org.mt4j.components.MTComponent;
import org.mt4j.components.bounds.IBoundingShape;
import org.mt4j.components.clipping.Clip;
import org.mt4j.input.inputProcessors.IGestureEventListener;
import org.mt4j.input.inputProcessors.componentProcessors.panProcessor.PanProcessorTwoFingers;
import org.mt4j.input.inputProcessors.componentProcessors.zoomProcessor.ZoomProcessor;
import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.events.MouseWheelProcessor;
import ca.mcgill.sel.ram.ui.events.RightClickDragProcessor;
import ca.mcgill.sel.ram.ui.events.UnistrokeProcessorLeftClick;
import ca.mcgill.sel.ram.ui.layouts.AbstractBaseLayout;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.utils.MathUtils;
import ca.mcgill.sel.ram.ui.views.handler.IAbstractViewHandler;
import ca.mcgill.sel.ram.ui.views.handler.IHandled;

/**
 * The visual representation for a general view.
 * All view elements are added/located on a special layer in order to support several gestures.
 * This view supports the following gestures: unistroke, scale, zoom (using mouse wheel or pinch-to-zoom gesture)
 * and panning (by right-click or using two fingers). <br/>
 * <b>Note</b> however, that the input processors have to be registered by the sub-class.
 * 
 * @param <H> the handler for this view
 * @author mschoettle
 */
public abstract class AbstractView<H extends IAbstractViewHandler> extends RamRectangleComponent
        implements IHandled<H> {
    
    /**
     * A layout that does nothing just to have a layout and enable propagation up.
     */
    private class InternalLayout extends AbstractBaseLayout {
        
        @Override
        public void layout(RamRectangleComponent component, LayoutUpdatePhase updatePhase) {
            // Do nothing.
        }
        
    }
    
    /**
     * Layer for all view elements. This allows scaling and dragging over the entire screen of the whole view.
     */
    protected RamRectangleComponent containerLayer;
    
    /**
     * Layer for unistroke gestures. This sits on top of everything (overlay).
     */
    protected MTComponent unistrokeLayer;
    
    /**
     * The handler for this view.
     */
    protected H handler;
    
    /**
     * Creates a new view with a given width and height.
     * 
     * @param width The width over which the elements can be displayed
     * @param height The height over which the elements can be displayed.
     */
    public AbstractView(float width, float height) {
        super(width, height);
        
        // The background color information is held by the structural view because the split view can customize
        // the background of each structural view. (see issue #86)
        // We call super setFillColor to avoid using the overloaded method.
        setNoFill(false);
        super.setFillColor(Colors.BACKGROUND_COLOR);
        
        // everything will be added to a container. This way we can scale and translate it to our heart's content
        // and yet still be able to handle input on the correct locations on screen.
        containerLayer = new RamRectangleComponent();
        containerLayer.setName("view container layer");
        /**
         * Set a default layout that does nothing to be able to propagate events to this view.
         */
        containerLayer.setLayout(new InternalLayout());
        
        unistrokeLayer = new MTComponent(RamApp.getApplication(), "unistroke drawing layer");
        unistrokeLayer.unregisterAllInputProcessors();
        unistrokeLayer.removeAllGestureEventListeners();
        
        super.addChild(0, containerLayer);
        super.addChild(1, unistrokeLayer);
    }
    
    @Override
    public void addChild(int i, MTComponent tangibleComp) {
        containerLayer.addChild(i, tangibleComp);
    }
    
    @Override
    public void addChild(MTComponent tangibleComp) {
        containerLayer.addChild(tangibleComp);
    }
    
    @Override
    public boolean containsChild(MTComponent tangibleComp) {
        if (tangibleComp != containerLayer && tangibleComp != unistrokeLayer) {
            return containerLayer.containsChild(tangibleComp);
        }
        
        return super.containsChild(tangibleComp);
    }
    
    @Override
    public MTComponent getChildbyID(int id) {
        return containerLayer.getChildbyID(id);
    }
    
    @Override
    public MTComponent getChildByIndex(int index) {
        return containerLayer.getChildByIndex(index);
    }
    
    @Override
    public void removeAllChildren() {
        containerLayer.removeAllChildren();
    }
    
    @Override
    public void removeChild(int i) {
        containerLayer.removeChild(i);
    }
    
    @Override
    public void removeChild(MTComponent comp) {
        if (comp != containerLayer && comp != unistrokeLayer) {
            containerLayer.removeChild(comp);
        }
        
        super.removeChild(comp);
    }
    
    @Override
    public void scaleGlobal(float x, float y, float z, Vector3D scalingPoint) {
        containerLayer.scaleGlobal(x, y, z, scalingPoint);
    }
    
    @Override
    public void setBounds(IBoundingShape bounds) {
        super.setBounds(bounds);
        if (bounds != null) {
            super.setChildClip(new Clip(RamApp.getApplication(), MathUtils.getXFromBounds(bounds), MathUtils
                    .getYFromBounds(bounds), MathUtils.getWidthLocal(bounds), MathUtils.getHeightFromBounds(bounds)));
        }
    }
    
    /**
     * Returns the layer that contains all the view elements.
     * 
     * @return the layer that contains all the view elements
     */
    public RamRectangleComponent getContainerLayer() {
        return containerLayer;
    }
    
    /**
     * Gets the layer for unistroke drawings.
     * 
     * @return the layer that is used to draw unistroke drawings on
     */
    public MTComponent getUnistrokeLayer() {
        return unistrokeLayer;
    }
    
    /**
     * Clears the unistroke drawings that have been drawn on the unistroke layer.
     */
    public void cleanUnistrokeLayer() {
        MTComponent[] components = unistrokeLayer.getChildren();
        for (MTComponent component : components) {
            component.destroy();
        }
    }
    
    /**
     * Registers the given gesture listener as a listener to all input processors.
     * 
     * @param listener the listener to register for all processors
     */
    protected void registerGestureListeners(IGestureEventListener listener) {
        addGestureListener(RightClickDragProcessor.class, listener);
        addGestureListener(MouseWheelProcessor.class, listener);
        addGestureListener(PanProcessorTwoFingers.class, listener);
        addGestureListener(ZoomProcessor.class, listener);
        addGestureListener(UnistrokeProcessorLeftClick.class, listener);
    }
    
    @Override
    public H getHandler() {
        return handler;
    }
    
    /**
     * Sets the handler for this view.
     * Unregisters previous handler as gesture listener and registers the new handler.
     * <b>Important:</b> Has to be called after addChild in order to have a parent.
     * 
     * @param handler the handler for this view
     */
    @Override
    public void setHandler(H handler) {
        if (RamApp.getApplication().getCanvas() == null || getParent() == null) {
            throw new RuntimeException(
                    "Component "
                            + this
                            + " has no parent, handler may only be addded once the component has a parent"
                            + " (i.e., after adding it to another component)");
        }
        
        this.handler = handler;
        removeAllGestureEventListeners();
        registerGestureListeners(handler);
    }
    
}
