package ca.mcgill.sel.ram.ui.components;

import java.util.ArrayList;

import org.mt4j.components.TransformSpace;
import org.mt4j.util.MTColor;
import org.mt4j.util.math.ToolsGeometry;
import org.mt4j.util.math.Vector3D;
import org.mt4j.util.math.Vertex;

import ca.mcgill.sel.ram.ui.utils.Colors;

/**
 * A subclass of {@link RamRectangleComponent} that creates a rectangular component with rounded corner.
 * 
 * @author vbonnet
 */
public class RamRoundedRectangleComponent extends RamRectangleComponent {
    private float arcRadius;
    /**
     * The number of segments to use for each corner (higher number will look smoother but take longer to compute and
     * display).
     */
    private final int arcSegments;
    
    /**
     * Creates a rounded rectangle component with top left corner (0,0), dimensions (width,height) and the given corner
     * arcs.
     * 
     * @param width
     *            The width of the rectangle.
     * @param height
     *            The height of the rectangle.
     * @param arcRadius
     *            The radius of the arc to create for the corners.
     */
    public RamRoundedRectangleComponent(final float width, final float height, final float arcRadius) {
        
        this(0, 0, width, height, arcRadius);
    }
    
    /**
     * Creates a rounded rectangle component with top left corner (x,y), dimensions (width,height) and the given corner
     * arcs.
     * 
     * @param x
     *            The x location of the top left corner.
     * @param y
     *            the y location of the top left corner.
     * @param width
     *            The width of the rectangle.
     * @param height
     *            The height of the rectangle.
     * @param arcRadius
     *            The radius of the arc to create for the corners.
     */
    public RamRoundedRectangleComponent(final float x, final float y, final float width, final float height,
            final float arcRadius) {
        
        super(x, y, width, height);
        this.arcRadius = arcRadius;
        arcSegments = 50;
        
        setStrokeColor(Colors.DEFAULT_ELEMENT_STROKE_COLOR);
        setNoFill(false);
        setNoStroke(false);
        
        setBuffers(arcRadius);
        
        // set the vertices that we start with
        setVertices(getRoundedVertices(x, y, 0, width, height, arcRadius, arcRadius, arcSegments, true));
    }
    
    /**
     * Creates a rounded rectangle component with top left corner (0,0), dimensions (0,0) and the given corner arcs.
     * 
     * @param arcRadius
     *            The radius of the arc to create for the corners.
     */
    public RamRoundedRectangleComponent(final int arcRadius) {
        this(0, 0, 2 * arcRadius, 2 * arcRadius, arcRadius);
    }
    
    private Vertex[] getRoundedVertices(final float x, final float y, final float z, final float width,
            final float height, final float arcWidth, final float arcHeight, final int segments,
            final boolean createTexCoordinates) {
        final MTColor strokeColor = getFillColor();
        
        final ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        
        // if we don't start and end at the same place the stroke will be incomplete
        vertices.add(new Vertex(x + arcWidth, y));
        
        // north east (Note, arcTo will add the first vertex but not necessarily the last)
        vertices.addAll(ToolsGeometry.arcTo(x + width - arcWidth, y, arcWidth, arcHeight, 0, false, true, x + width, y
                + arcHeight, segments));
        vertices.add(new Vertex(x + width, y + arcHeight));
        
        // south east (Note, arcTo will add the first vertex but not necessarily the last)
        vertices.addAll(ToolsGeometry.arcTo(x + width, y + height - arcHeight, arcWidth, arcHeight, 0, false, true, x
                + width - arcWidth, y + height, segments));
        
        vertices.add(new Vertex(x + width - arcWidth, y + height));
        
        // south west (Note, arcTo will add the first vertex but not necessarily the last)
        vertices.addAll(ToolsGeometry.arcTo(x + arcWidth, y + height, arcWidth, arcHeight, 0, false, true, x, y
                + height - arcHeight, segments));
        vertices.add(new Vertex(x, y + height - arcHeight));
        
        // north west (Note, arcTo will add the first vertex but not necessarily the last)
        vertices.addAll(ToolsGeometry.arcTo(x, y + arcHeight, arcWidth, arcHeight, 0, false, true, x + arcWidth, y,
                segments));
        // top left
        vertices.add(new Vertex(x + arcWidth, y));
        
        final Vertex[] newVertices = vertices.toArray(new Vertex[vertices.size()]);
        for (final Vertex vertex : newVertices) {
            if (createTexCoordinates) {
                vertex.setTexCoordU((vertex.x - x) / width);
                vertex.setTexCoordV((vertex.y - y) / height);
            }
            // fill color
            vertex.setRGBA(strokeColor.getR(), strokeColor.getG(), strokeColor.getB(), strokeColor.getAlpha());
        }
        return newVertices;
    }
    
    @Override
    public float getX() {
        // 0th index is not upper left
        return getVerticesGlobal()[0].x - arcRadius;
    }
    
    @Override
    public float getY() {
        return getVerticesGlobal()[0].y;
    }
    
    @Override
    public void setPositionRelativeToParent(final Vector3D position) {
        // subtract arc radius because the 0th index is not the upper left
        final Vector3D v = getVerticesLocal()[0].getCopy().subtractLocal(new Vector3D(arcRadius, 0));
        // transform to local scale
        v.transform(getLocalMatrix());
        // continue business as usual
        translate(position.getSubtracted(v), TransformSpace.RELATIVE_TO_PARENT);
    }
    
    @Override
    public void setSizeLocal(final float width, final float height) {
        if ((width != getWidth() || height != getHeight()) && (width >= 0 && height >= 0)) {
            // TODO - if this is affecting efficiency significantly, only move vertices as necessary (good luck)
            setVertices(getRoundedVertices(0, 0, 0, width, height, arcRadius, arcRadius, arcSegments, true));
            
            // setChildClip(width, height);
        }
    }

    /**
     * Set the desired radius for the corners.
     * 
     * @param arcRadius - the radius
     */
    public void setArcRadius(float arcRadius) {
        this.arcRadius = arcRadius;
        updateLayout();
    }
    
}
