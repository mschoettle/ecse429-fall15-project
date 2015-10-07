package ca.mcgill.sel.ram.ui.utils;

import org.mt4j.components.TransformSpace;
import org.mt4j.components.bounds.IBoundingShape;

/**
 * A collection of print statements, use these as it makes it easier to turn on and off printing.
 * Also provides some useful "toPrettyString"s for classes that do not
 * provide any.
 * 
 * @author vbonnet
 */
public final class LoggerUtils {
    
    /**
     * Creates a new instance.
     */
    private LoggerUtils() {
        
    }
    
    /**
     * Prints the string with a DEDBUG prefix.
     * 
     * @param string
     *            The string to print.
     */
    public static void debug(final String string) {
        System.out.println("DEBUG: " + string);
    }
    
    /**
     * Prints the string with an ERROR prefix.
     * 
     * @param string
     *            The string to print.
     */
    public static void error(final String string) {
        System.out.println("ERROR: " + string);
    }
    
    /**
     * Prints the string an INFO prefix.
     * 
     * @param string
     *            The string to print.
     */
    public static void info(final String string) {
        System.out.println("INFO: " + string);
    }
    
    /**
     * Pretty prints the bounds.
     * 
     * @param bounds
     *            The bounds to print.
     */
    // TODO: unused. remove?
    public static void printBounds(final IBoundingShape bounds) {
        final float x = MathUtils.getXFromBounds(bounds);
        final float y = MathUtils.getYFromBounds(bounds);
        final float w = bounds.getWidthXY(TransformSpace.LOCAL);
        final float h = bounds.getHeightXY(TransformSpace.LOCAL);
        System.out.println("  bounds:\n    x: " + x + "\n    y: " + y + "\n    w: " + w + "\n    h: " + h);
    }
    
    /**
     * Prints the string with a WARN prefix.
     * 
     * @param string
     *            The string to print.
     */
    public static void warn(final String string) {
        System.out.println("WARN: " + string);
    }
}
