package ca.mcgill.sel.ram.ui.layouts;

import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;

/**
 * Layouts take a {@link RamRectangleComponent} and arrange its children in a certain order when the layout() method is
 * invoked.
 * 
 * @author vbonnet
 */
public interface Layout {
    
    /**
     * The horizontal alignment for the child components of a component.
     */
    public enum HorizontalAlignment {
        /** Align to the left. */
        LEFT,
        /** Align to the center. */
        CENTER,
        /** Align to the right. */
        RIGHT
    }
    
    /**
     * The vertical alignment for the child components of a component.
     */
    public enum VerticalAlignment {
        /** Align to the top. */
        TOP,
        /** Align to the middle. */
        MIDDLE,
        /** Align to the bottom. */
        BOTTOM
    }
    
    /**
     * Where the layout update order come from.
     */
    public enum LayoutUpdatePhase {
        /** If the layout order come from a child. */
        FROM_CHILD,
        /** If the layout order come from the parent. */
        FROM_PARENT
    }
    
    /**
     * Lays out the children of the component.
     * 
     * @param component the component whose children will move
     * @param updatePhase The phase of the layout update order
     */
    void layout(final RamRectangleComponent component, LayoutUpdatePhase updatePhase);
    
    /**
     * Returns the minimum width of the layout.
     * @return The minimum width
     */
    float getMinimumWidth();
    
    /**
     * Returns the minimum height of the layout.
     * @return The minimum height
     */
    float getMinimumHeight();
    
    /**
     * Gets whether the layout should keep its size and does not resize itself.
     * @return true forces, false otherwise
     */
    boolean getForceToKeepSize();
}
