package ca.mcgill.sel.ram.ui.layouts;

import org.mt4j.components.MTComponent;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;
import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent.Cardinal;

/**
 * A list Layout that lays children out horizontally and sets all the heights to be identical.
 * The width and height of the component will be resized to fit all children.
 * 
 * @author vbonnet
 */
public class HorizontalLayout extends AbstractBaseLayout {
    
    private float padding;
    
    /**
     * Creates a new horizontal layout with no padding.
     */
    public HorizontalLayout() {
        this(0);
    }
    
    /**
     * Creates a new horizontal layout with the given padding.
     * 
     * @param padding the padding to use for each component
     */
    public HorizontalLayout(float padding) {
        this.padding = padding;
    }
    
    @Override
    public void layout(RamRectangleComponent component, LayoutUpdatePhase updatePhase) {
        
        // start previous with left corner and
        float previousX = component.getBufferSize(Cardinal.WEST);
        float maxHeight = 0;
        
        // move and resize each child as necessary
        for (final MTComponent c : component.getChildren()) {
            if (!(c instanceof RamRectangleComponent)) {
                continue;
            }
            final RamRectangleComponent child = (RamRectangleComponent) c;
            
            child.minimizeSize();
            child.setPositionRelativeToParent(new Vector3D(previousX, component.getBufferSize(Cardinal.NORTH)));
            
            // previous becomes the right of this element
            previousX += child.getWidth() + padding;
            maxHeight = Math.max(maxHeight, child.getHeight());
        }
        
        if (component.getChildCount() > 0) {
            previousX -= padding;
        }
        
        // resize ourselves, we need to be big enough to include all elements
        if (updatePhase == LayoutUpdatePhase.FROM_CHILD && !getForceToKeepSize()) {
            final float width = previousX + component.getBufferSize(Cardinal.EAST);
            final float height = component.getBufferSize(Cardinal.NORTH) + maxHeight
                    + component.getBufferSize(Cardinal.SOUTH);
            setMinimumSize(component, width, height);
        }
        
        maxHeight = component.getChildHeight();
        
        for (final MTComponent child : component.getChildren()) {
            if (!(child instanceof RamRectangleComponent)) {
                continue;
            }
            if (((RamRectangleComponent) child).autoMaximizes()) {
                ((MTRectangle) child).setHeightLocal(maxHeight);
            }
        }
    }
}
