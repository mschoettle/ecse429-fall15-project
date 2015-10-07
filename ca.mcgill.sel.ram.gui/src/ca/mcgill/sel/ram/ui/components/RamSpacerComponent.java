package ca.mcgill.sel.ram.ui.components;

import org.mt4j.components.MTComponent;

/**
 * A simple transparent component used to indent other components in a layout. NTOE: Cannot have children.
 * 
 * @author vbonnet
 */
public class RamSpacerComponent extends RamRectangleComponent {
    /**
     * Creates a spacer with the given dimensions.
     * 
     * @param width
     *            The width of the spacer.
     * @param height
     *            The height of the spacer.
     */
    public RamSpacerComponent(float width, float height) {
        super(width, height);
        setNoFill(true);
        setNoStroke(true);
        setMinimumSize(width, height);
    }
    
    @Override
    public void addChild(int i, MTComponent tangibleComp) {
        // WE DO NOT HAVE CHILDREN
    }
}
