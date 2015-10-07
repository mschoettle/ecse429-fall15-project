package ca.mcgill.sel.ram.ui.views;

import java.util.Comparator;

import org.eclipse.emf.ecore.EObject;
import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;

/**
 * A RamEnd represents one end of a relationship at a given location and for a given position relative to its parent.
 * This information is necessary for drawing the end appropriately.
 * 
 * @param <T>
 *            the model class this end represents
 * @param <X>
 *            the view class that represents the model element T
 * @author mschoettle
 * @author walabe
 */
public class RamEnd<T extends EObject, X extends RamRectangleComponent> {
    /**
     * Enum for stating the position an object is connected to a class.
     */
    public enum Position {
        /**
         * Represents a connection to the top edge of a Class.
         */
        TOP,
        /**
         * Represents a connection to the left edge of a Class.
         */
        LEFT,
        /**
         * Represents a connection to the right edge of a Class.
         */
        RIGHT,
        /**
         * Represents a connection to the bottom edge of a Class.
         */
        BOTTOM,
        /**
         * Represents a connection to a Class that should not be drawn.
         */
        OFFSCREEN
    }
    
    /**
     * Compares two ends according to their horizontal (x) location.
     */
    public static final Comparator<RamEnd<?, ?>> HORIZONTAL_COMPARATOR = new Comparator<RamEnd<?, ?>>() {
        
        @Override
        public int compare(final RamEnd<?, ?> o1, final RamEnd<?, ?> o2) {
            final Vector3D oppositeLocation1 = o1.getOpposite().getLocation();
            final Vector3D oppositeLocation2 = o2.getOpposite().getLocation();
            return oppositeLocation1 == null || oppositeLocation2 == null
                    ? 0
                    : (int) (oppositeLocation1.getX() - oppositeLocation2.getX());
        }
    };
    
    /**
     * Compares two ends according to their vertical (y) location.
     */
    public static final Comparator<RamEnd<?, ?>> VERTICAL_COMPARATOR = new Comparator<RamEnd<?, ?>>() {
        
        @Override
        public int compare(final RamEnd<?, ?> o1, final RamEnd<?, ?> o2) {
            final Vector3D oppositeLocation1 = o1.getOpposite().getLocation();
            final Vector3D oppositeLocation2 = o2.getOpposite().getLocation();
            return oppositeLocation1 == null || oppositeLocation2 == null
                    ? 0
                    : (int) (oppositeLocation1.getY() - oppositeLocation2.getY());
        }
    };
    
    private Vector3D location;
    private Position position;
    private RamEnd<T, X> oppositeEnd;
    private T model;
    private RelationshipView<T, X> relationshipView;
    private X componentView;
    private boolean isAloneOnEdge;
    
    /**
     * Creates a new {@link RamEnd} for the given model of type T.
     * 
     * @param model
     *            the model that should be visualized
     * @param view
     *            the view that is observing this end
     * @param componentView
     *            the component this end is attached to
     */
    public RamEnd(T model, RelationshipView<T, X> view, X componentView) {
        
        this.model = model;
        relationshipView = view;
        this.componentView = componentView;
        location = new Vector3D(0, 0);
    }
    
    /**
     * Returns the view this end is attached to.
     * 
     * @return the view this end is attached to
     */
    public X getComponentView() {
        return componentView;
    }
    
    /**
     * Returns whether the end is alone on the class' edge.
     * 
     * @return is this end is alone on the class' edge
     */
    public boolean isAlone() {
        return isAloneOnEdge;
    }
    
    /**
     * Returns the position of the end.
     * 
     * @return The global position for the top left of the view.
     */
    public Vector3D getLocation() {
        return location;
    }
    
    /**
     * Returns the associated model element.
     * 
     * @return the associated model element
     */
    public T getModel() {
        return model;
    }
    
    /**
     * Returns the opposite end.
     * 
     * @return The end at the opposite of the relationship.
     */
    public RamEnd<T, X> getOpposite() {
        return oppositeEnd;
    }
    
    /**
     * Returns the position of the end along the edge.
     * 
     * @return The position of the end on it's adjoined {@link ca.mcgill.sel.ram.Class}.
     */
    public Position getPosition() {
        return position;
    }
    
    /**
     * Returns the relationship view this end is part of.
     * 
     * @return the view that observes this end
     */
    public RelationshipView<T, X> getRelationshipView() {
        return relationshipView;
    }
    
    /**
     * Sets whether the end is alone on the edge.
     * 
     * @param alone
     *            true, if the end is alone on the edge, false otherwise
     */
    public void setIsAlone(boolean alone) {
        isAloneOnEdge = alone;
    }
    
    /**
     * Set the global position for the top left of the view.
     * 
     * @param location
     *            the new position
     */
    public void setLocation(Vector3D location) {
        this.location = location;
        getRelationshipView().shouldUpdate();
    }
    
    /**
     * Sets the opposite relationship end.
     * 
     * @param opposite
     *            the opposite relationship end
     */
    public void setOpposite(RamEnd<T, X> opposite) {
        this.oppositeEnd = opposite;
    }
    
    /**
     * Set the position of the end on it's adjoined {@link ca.mcgill.sel.ram.Class}.
     * 
     * @param position
     *            the new position
     */
    public void setPosition(Position position) {
        this.position = position;
        getRelationshipView().shouldUpdate();
    }
    
}
