package ca.mcgill.sel.ram.ui.utils;

import java.util.Collection;

import org.mt4j.input.inputData.AbstractCursorInputEvt;
import org.mt4j.input.inputData.InputCursor;
import org.mt4j.util.math.Vector3D;

/**
 * A utils class used as a helper class for functions which are used when UniStroke Events are used.
 * 
 * @author Nishanth
 */
public final class UnistrokeProcessorUtils {
    
    /**
     * Creates a new instance.
     */
    private UnistrokeProcessorUtils() {
        
    }
    
    /**
     * Function used to get the intersection point when a 'X' gesture is detected/drawn on the screen.
     * @param startPosition - The start position of the gesture drawn (X).
     * @param endPosition - The end position of the gesture drawn (x).
     * @param inputCursor - The input cursor which was used to draw the unistroke gesture.
     * @return Intersection Point - Returns the intersection point in a 3D Vector.
     */
    public static Vector3D getIntersectionPoint(Vector3D startPosition, Vector3D endPosition,
            InputCursor inputCursor) {
        
        Collection<AbstractCursorInputEvt> evts = inputCursor.getEvents();
        AbstractCursorInputEvt[] eventArray = new AbstractCursorInputEvt[0];
        
        eventArray = evts.toArray(eventArray);
        
        float intersectionX = 0;
        float intersectionY = 0;
        
        // find the 2 segments that intersect
        for (int i = 0; i < eventArray.length - 1; i++) {
            for (int j = i + 1; j < eventArray.length - 1; j++) {
                float x1 = eventArray[i].getX();
                float y1 = eventArray[i].getY();
                float x2 = eventArray[i + 1].getX();
                float y2 = eventArray[i + 1].getY();
                float x3 = eventArray[j].getX();
                float y3 = eventArray[j].getY();
                float x4 = eventArray[j + 1].getX();
                float y4 = eventArray[j + 1].getY();
                
                // the segments intersect if the 2 points of one segment are on opposite sides of the other segment
                if (determinant(x1, y1, x2, y2, x3, y3) * determinant(x1, y1, x2, y2, x4, y4) < 0
                        && determinant(x1, y1, x3, y3, x4, y4) * determinant(x2, y2, x3, y3, x4, y4) < 0) {
                    // System.out.println("Found: (" + x1 + "," + y1 + "-" + x2 + "," + y2 + ") and (" + x3 + "," + y3 +
                    // "-" + x4 + "," + y4 + ")");
                    // calculate the intersection point
                    intersectionX = ((x1 * y2 - x2 * y1) * (x3 - x4) - (x1 - x2) * (x3 * y4 - x4 * y3))
                            / ((x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4));
                    intersectionY = ((x1 * y2 - x2 * y1) * (y3 - y4) - (y1 - y2) * (x3 * y4 - x4 * y3))
                            / ((x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4));
                    // System.out.println("Intersection at: " + intersectionX + "," + intersectionY);
                }
            }
        }
        return new Vector3D(intersectionX, intersectionY);
    }
    
    /**
     * Function used to compute the determinant based on the six points inputted.
     * @param x1 - The x1 position.
     * @param y1 - The y1 position.
     * @param x2 - The x2 position.
     * @param y2 - The y2 position.
     * @param x3 - The x3 position.
     * @param y3 - The y3 position.
     * @return determinant - Function returns the determinant based on the six points.
     */
    private static float determinant(float x1, float y1, float x2, float y2, float x3, float y3) {
        return (x1 - x3) * (y2 - y3) - (y1 - y3) * (x2 - x3);
    }
    
}
