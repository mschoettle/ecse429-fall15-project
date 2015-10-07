package ca.mcgill.sel.ram.ui.components;

import org.mt4j.components.visibleComponents.shapes.MTLine;
import org.mt4j.util.MTColor;
import org.mt4j.util.math.Vertex;

import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.utils.Colors;

/**
 * This Class represents our basic RAM lines used to draw associations between classes. Lines are drawn between two
 * global vertices.
 * 
 * @author walabe
 */
public class RamLineComponent extends MTLine {
    /**
     * Creates a line connecting the two vertices defined by the points passed. The default color and line thickness are
     * used.
     * 
     * @param x1
     *            top left x value
     * @param y1
     *            top left y value
     * @param x2
     *            bottom right x value
     * @param y2
     *            bottom right y value
     */
    public RamLineComponent(final float x1, final float y1, final float x2, final float y2) {
        this(Colors.DEFAULT_ELEMENT_STROKE_COLOR, x1, y1, x2, y2);
    }
    
    /**
     * Creates a line of the given color connecting the two vertices defined by the points passed. The default line
     * thickness is used.
     * 
     * @param color
     *            The color of the line to create.
     * @param x1
     *            top left x value
     * @param y1
     *            top left y value
     * @param x2
     *            bottom right x value
     * @param y2
     *            bottom right y value
     */
    public RamLineComponent(final MTColor color, final float x1, final float y1, final float x2, final float y2) {
        super(RamApp.getApplication(), x1, y1, x2, y2);
        setStrokeColor(color);
        setStrokeWeight(2.0f);
    }
    
    /**
     * Creates a line connecting the two vertices.
     * 
     * @param vertex
     *            The first vertex.
     * @param vertex2
     *            The second vertex.
     */
    public RamLineComponent(final Vertex vertex, final Vertex vertex2) {
        this(vertex.getX(), vertex.getY(), vertex2.getX(), vertex2.getY());
    }
    
    @Override
    protected void setDefaultGestureActions() {
        // Do nothing by default.
    }
}
