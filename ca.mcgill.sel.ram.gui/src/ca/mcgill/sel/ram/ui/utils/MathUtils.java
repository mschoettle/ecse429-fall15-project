package ca.mcgill.sel.ram.ui.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.mt4j.components.MTComponent;
import org.mt4j.components.TransformSpace;
import org.mt4j.components.bounds.IBoundingShape;
import org.mt4j.components.clipping.Clip;
import org.mt4j.components.visibleComponents.shapes.AbstractShape;
import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;

/**
 * This is a utility class made in order to wrap some commonly used functionality. The hope is to make code more
 * readable all the while removing potential for confusion with the math and structure. Many of the methods attempt to
 * wrap methods that (I find) have been poorly provided for in the mt4j stack.
 *
 * @author vbonnet
 */
public final class MathUtils {

    /**
     * Creates a new instance.
     */
    private MathUtils() {

    }

    public static float clampFloat(final float minimum, final float value, final float maximum) {
        return Math.min(Math.max(value, minimum), maximum);
    }

    /**
     * @param bounds The bounds that will determine the size and location of the Clip.
     * @return a Clip encompasing the entire bounds provided.
     */
    // TODO: unused. remove?
    public static Clip getClipFromBounds(final IBoundingShape bounds) {
        return new Clip(RamApp.getApplication(), getXFromBounds(bounds), getYFromBounds(bounds), getWidthLocal(bounds),
                getHeightFromBounds(bounds));
    }

    /**
     * @param bounds The bound to analyze
     * @return The height (x-axis length) of the bounds.
     */
    public static float getHeightFromBounds(final IBoundingShape bounds) {
        return bounds.getVectorsLocal()[3].getY() - bounds.getVectorsLocal()[0].getY();
    }

    /**
     * @param component The component whose bounds will be used to determine the height.
     * @return The height (x-axis length) of the bounds.
     */
    public static float getHeightFromComponent(final MTComponent component) {
        return getHeightFromBounds(component.getBounds());
    }

    public static Vector3D getUpperLeftFromComponent(final MTComponent component) {
        if (component == null) {
            return null;
        } else {
            return new Vector3D(getXFromComponent(component), getYFromComponent(component));
        }
    }

    public static float getWidthLocal(final IBoundingShape bounds) {
        return bounds.getWidthXY(TransformSpace.LOCAL);
    }

    public static float getWidthLocal(final MTComponent component) {
        return getWidthLocal(component.getBounds());
    }

    public static float getHeightLocal(final IBoundingShape bounds) {
        return bounds.getHeightXY(TransformSpace.LOCAL);
    }

    public static float getHeightLocal(final MTComponent component) {
        return getHeightLocal(component.getBounds());
    }

    /**
     * @param bounds The bound to analyze
     * @return The X value for the top left vertex of the bound.
     */
    public static float getXFromBounds(final IBoundingShape bounds) {
        return bounds.getVectorsGlobal()[0].getX();
    }

    /**
     * @param component The component whose bounds will be used to determine the x value.
     * @return The X value for the top left vertex of the bound.
     */
    public static float getXFromComponent(final MTComponent component) {
        return getXFromBounds(component.getBounds());
    }

    /**
     * @param bounds The bound to analyze
     * @return The Y value for the top left vertex of the bound.
     */
    public static float getYFromBounds(final IBoundingShape bounds) {
        return bounds.getVectorsGlobal()[0].getY();
    }

    /**
     * @param component The component whose bounds will be used to determine the y value.
     * @return The Y value for the top left vertex of the bound.
     */
    public static float getYFromComponent(final MTComponent component) {
        return getYFromBounds(component.getBounds());
    }

    /**
     * Set the width and keep the shape centered from its parent.
     *
     * @param shape The shape
     * @param width The new width
     * @return Whether it succeed or not
     */
    public static boolean setWidthRelativeToParent(AbstractShape shape, float width) {

        if (width > 0) {
            Vector3D centerPoint;
            if (shape.hasBounds()) {
                centerPoint = shape.getBounds().getCenterPointLocal();
                centerPoint.transform(shape.getLocalMatrix());
            } else {
                centerPoint = shape.getCenterPointGlobal();
                centerPoint.transform(shape.getGlobalInverseMatrix());
            }
            shape.scale(width * (1 / shape.getWidthXY(TransformSpace.RELATIVE_TO_PARENT)),
                    width * (1 / shape.getWidthXY(TransformSpace.RELATIVE_TO_PARENT)), 1, centerPoint);

            return true;
        }

        return false;
    }

    /**
     * Function used to check if a point is inside a rectangle plus margin.
     *
     * @param point - The point to be checked.
     * @param rectangle - The goal visual representation against which the point should be checked.
     * @param margin - The acceptance margin
     * @return if it exists inside the visual representation of goal.
     */
    public static boolean pointIsInRectangle(Vector3D point, RamRectangleComponent rectangle, int margin) {
        return rectangle.containsPointGlobal(point.getAdded(new Vector3D(margin, 0)))
                || rectangle.containsPointGlobal(point.getSubtracted(new Vector3D(margin, 0)))
                || rectangle.containsPointGlobal(point.getAdded(new Vector3D(0, margin)))
                || rectangle.containsPointGlobal(point.getSubtracted(new Vector3D(0, margin)));
    }

    /**
     * Return the floatValue rounded.
     *
     * @param floatValue the float value to round
     * @param decimalNumber the number of decimal
     * @param roundingMode the rounding mode.
     * @return The floatValue rounded
     */
    public static Float round(Float floatValue, int decimalNumber, RoundingMode roundingMode) {
        BigDecimal bd = new BigDecimal(floatValue);
        bd = bd.setScale(decimalNumber, roundingMode);
        return bd.floatValue();
    }
}
