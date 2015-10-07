package ca.mcgill.sel.ram.ui.layouts;

import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;

/**
 * A basic common implementation of the layout interface.
 * 
 * @author tdimeco
 */
public abstract class AbstractBaseLayout implements Layout {
    
    /** Minimum width set by the layout. */
    private float minimumWidth;
    
    /** Minimum height set by the layout. */
    private float minimumHeight;
    
    /** Force the layout to keep the current size and does not resize itself. */
    private boolean forceToKeepSize;
    
    /**
     * Sets the minimum size of the layout.
     * 
     * @param component The layouted component
     * @param width The minimum width
     * @param height The minimum height
     */
    protected void setMinimumSize(RamRectangleComponent component, float width, float height) {
        minimumWidth = width;
        minimumHeight = height;
        if (component.autoMinimizes() || component.getHeight() < minimumHeight || component.getWidth() < minimumWidth) {
            component.minimizeSize();
        }
    }
    
    /**
     * Sets whether the layout should keep its size and does not resize itself.
     * @param force true to force, false otherwise
     */
    public void setForceToKeepSize(boolean force) {
        forceToKeepSize = force;
    }
    
    @Override
    public float getMinimumWidth() {
        return minimumWidth;
    }
    
    @Override
    public float getMinimumHeight() {
        return minimumHeight;
    }
    
    @Override
    public boolean getForceToKeepSize() {
        return forceToKeepSize;
    }
}
