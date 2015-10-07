package ca.mcgill.sel.ram.ui.views.structural;

import org.mt4j.components.visibleComponents.shapes.MTPolygon;

import ca.mcgill.sel.ram.Classifier;
import ca.mcgill.sel.ram.ui.views.RelationshipView;
import ca.mcgill.sel.ram.ui.views.RamEnd.Position;

/**
 * Represents an inheritance relationship between two classes.
 * The derived class can only be a {@link ca.mcgill.sel.ram.Class},
 * whereas the super class can be any {@link Classifier}.
 * A distinction is made between inheritances of classes and interfaces, which needs to be visualized differently.
 * 
 * @author mschoettle
 * @author eyildirim
 */
public class InheritanceView extends RelationshipView<Classifier, ClassifierView<?>> {
    
    /**
     * Distance from the end of the super class to the point where the next line is drawn (orthogonal), also referred to
     * as the "center".
     */
    private static final float DISTANCE_INHERITANCE = 40.0f;
    
    private ClassifierView<?> superClassView;
    private ClassifierView<?> derivedClassView;
    private boolean implementsInheritance;
    
    /**
     * Creates a new {@link InheritanceView} for the given base and super class.
     * 
     * @param derivedClassView
     *            the {@link ClassifierView} for the base class
     * @param superClassView
     *            the {@link ClassifierView} for the super class
     */
    public InheritanceView(ClassifierView<?> derivedClassView, ClassifierView<?> superClassView) {
        super(derivedClassView.getClassifier(), derivedClassView, superClassView.getClassifier(), superClassView);
        
        this.superClassView = superClassView;
        this.derivedClassView = derivedClassView;
    }
    
    /**
     * Draws all the lines necessary to connect the two end points correctly.
     */
    @Override
    protected void drawAllLines() {
        float fromX = fromEnd.getLocation().getX();
        float fromY = fromEnd.getLocation().getY();
        Position positionFrom = fromEnd.getPosition();
        float toX = toEnd.getLocation().getX();
        float toY = toEnd.getLocation().getY();
        Position positionTo = toEnd.getPosition();
        
        // if the x or y values are the same they are just directly next to each other or above each other
        // only one line necessary
        // since the values are floats a comparison needs to be done using an epsilon
        if (Math.abs(fromX - toX) < EPSILON || Math.abs(fromY - toY) < EPSILON) {
            drawLine(fromX, fromY, null, toX, toY);
        } else {
            // "to" is the superclass, "from" is the subclass
            // the center should be closer to the super class
            float centerX = toX + (fromX - toX > 0 ? DISTANCE_INHERITANCE : -DISTANCE_INHERITANCE);
            float centerY = toY + (fromY - toY > 0 ? DISTANCE_INHERITANCE : -DISTANCE_INHERITANCE);
            drawLine(fromX, fromY, positionFrom, centerX, centerY);
            drawLine(toX, toY, positionTo, centerX, centerY);
            // revert the position according to the current one
            // since drawLine does the same for TOP and BOTTOM it is not necessary to distinguish between the two cases
            // if the classes are next to each other the y value stays the same and the x center is used
            if (positionFrom == Position.RIGHT || positionFrom == Position.LEFT) {
                drawLine(centerX, fromY, Position.TOP, centerX, toY);
                // otherwise x stays the same and the y center is used
            } else {
                drawLine(fromX, centerY, Position.LEFT, toX, centerY);
            }
        }
    }
    
    /**
     * Draws the inheritance arrow at the super class.
     */
    private void drawEnd() {
        float x = getToEnd().getLocation().getX();
        float y = getToEnd().getLocation().getY();
        
        MTPolygon polygon = new InheritancePolygon(x, y, drawColor);
        
        rotateShape(polygon, x, y, getToEnd().getPosition());
        
        addChild(polygon);
    }
    
    /**
     * Returns the view of the derived class.
     * 
     * @return the {@link ClassView} of the derived (sub) class
     */
    public ClassifierView<?> getDerivedClassView() {
        return derivedClassView;
    }
    
    /**
     * Returns the view of the super class.
     * 
     * @return the the {@link ClassifierView} of the super class
     */
    public ClassifierView<?> getSuperClassView() {
        return superClassView;
    }
    
    @Override
    public void update() {
        drawAllLines();
        drawEnd();
    }
    
    /**
     * Sets whether this inheritance is of the implements kind. It implements if the super class is an interface.
     * 
     * @param implementsInheritance true, if the inheritance is an implements inheritance, false otherwise (extends)
     */
    public void setImplementsInheritance(boolean implementsInheritance) {
        this.implementsInheritance = implementsInheritance;
        
        // Visualize implements relationships differently.
        if (implementsInheritance) {
            setLineStyle(LineStyle.DASHED);
        } else {
            setLineStyle(LineStyle.SOLID);
        }
    }
    
    /**
     * Returns whether this inheritance is of the extends kind.
     * 
     * @return true, if the inheritance is an extends inheritance, false otherwise (implements)
     */
    public boolean isExtends() {
        return !implementsInheritance;
    }
    
}
