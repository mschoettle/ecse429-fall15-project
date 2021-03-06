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
public class HorizontalLayoutAllCentered extends AbstractBaseLayout {
    
    private float padding;
    
    public HorizontalLayoutAllCentered() {
        this(0);
    }
    
    public HorizontalLayoutAllCentered(final float padding) {
        this.padding = padding;
    }
    
    @Override
    public void layout(final RamRectangleComponent component, LayoutUpdatePhase updatePhase) {
        
        // start previous with left corner and
        float previousX = 0;
        float maxHeight = 0;
        float totalWidth = 0;
        
        // determine the total width and the maximum height
        for (final MTComponent c : component.getChildren()) {
            if (!(c instanceof RamRectangleComponent)) {
                continue;
            }
            final RamRectangleComponent child = (RamRectangleComponent) c;
            
            child.minimizeSize();
            totalWidth += child.getWidth() + padding;
            maxHeight = Math.max(maxHeight, child.getHeight());
        }
        
        if (component.getChildCount() > 0) {
            totalWidth -= padding;
        }
        
        // resize ourselves, we need to be big enough to include all elements
        if (updatePhase == LayoutUpdatePhase.FROM_CHILD && !getForceToKeepSize()) {
            final float width = component.getBufferSize(Cardinal.WEST) + totalWidth
                    + component.getBufferSize(Cardinal.EAST);
            final float height = component.getBufferSize(Cardinal.NORTH) + maxHeight
                    + component.getBufferSize(Cardinal.SOUTH);
            setMinimumSize(component, width, height);
        }
        
        previousX = component.getWidth() / 2f - totalWidth / 2f;
        
        // move and resize each child as necessary
        for (final MTComponent c : component.getChildren()) {
            if (!(c instanceof RamRectangleComponent)) {
                continue;
            }
            final RamRectangleComponent child = (RamRectangleComponent) c;
            
            // shift right to be flush with the left of the previous child
            child.setPositionRelativeToParent(new Vector3D(previousX, component.getBufferSize(Cardinal.NORTH)
                    + (maxHeight - child.getHeight()) / 2 + 1));
            
            // previous becomes the right of this element
            previousX += child.getWidth() + padding;
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
