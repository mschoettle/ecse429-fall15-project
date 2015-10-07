package ca.mcgill.sel.ram.ui.layouts;

import org.mt4j.components.MTComponent;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;
import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent.Cardinal;

/**
 * A list Layout that lays children out horizontally and sets all the heights to be identical.
 * The width and height of the component will be resized to fit all children.
 * Additionally, if there are too many children to fit on the screen it will move them to another row.
 * 
 * @author vbonnet
 * @author mschoettle
 */
public class HorizontalWraparoundLayout extends AbstractBaseLayout {
    
    private float padding;
    
    /**
     * Creates a new horizontal layout that wraps around and has no padding.
     */
    public HorizontalWraparoundLayout() {
        this(0);
    }
    
    /**
     * Creates a new horizontal layout that wraps around with the given padding for each component.
     * 
     * @param padding the padding to use for each component
     */
    public HorizontalWraparoundLayout(float padding) {
        this.padding = padding;
    }
    
    @Override
    public void layout(final RamRectangleComponent component, LayoutUpdatePhase updatePhase) {
        
        final float startX = component.getBufferSize(Cardinal.WEST);
        final float startY = component.getBufferSize(Cardinal.NORTH);
        
        // start previous with left corner and
        float previousX = startX;
        float previousY = startY;
        float maxHeight = 0;
        float maxWidth = previousX;
        
        // move and resize each child as necessary
        for (final MTComponent c : component.getChildren()) {
            if (!(c instanceof RamRectangleComponent)) {
                continue;
            }
            final RamRectangleComponent child = (RamRectangleComponent) c;
            
            child.minimizeSize();
            
            // in case this will be off screen move it to the next "row"
            if ((component.getX() + previousX + child.getWidth() + padding) >= RamApp.getApplication()
                    .getWidth()) {
                previousX = startX;
                previousY += maxHeight;
            }
            
            // shift right to be flush with the left of the previous child
            child.setPositionRelativeToParent(new Vector3D(previousX, previousY));
            
            // previous becomes the right of this element
            previousX += child.getWidth() + padding;
            maxHeight = Math.max(maxHeight, child.getHeight());
            maxWidth = Math.max(maxWidth, previousX);
        }
        
        if (component.getChildCount() > 0) {
            previousX -= padding;
        }
        
        // resize ourselves, we need to be big enough to include all elements
        if (updatePhase == LayoutUpdatePhase.FROM_CHILD && !getForceToKeepSize()) {
            final float width = maxWidth + component.getBufferSize(Cardinal.EAST);
            final float height = previousY + maxHeight + component.getBufferSize(Cardinal.SOUTH);
            setMinimumSize(component, width, height);
        }
        
        // maxHeight = component.getChildHeight();
        
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
