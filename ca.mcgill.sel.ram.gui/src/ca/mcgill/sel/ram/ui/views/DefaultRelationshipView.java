package ca.mcgill.sel.ram.ui.views;

import org.eclipse.emf.ecore.EObject;
import org.mt4j.components.TransformSpace;
import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.views.RamEnd.Position;

/**
 * Default relationship view which link two RamRectangle components together.
 * It has a default positioning for the link.
 * 
 * @author g.Nicolas
 *
 * @param <T> - type of the eObject related to the RamRectangle.
 */
public class DefaultRelationshipView<T extends EObject> extends RelationshipView<T, RamRectangleComponent> {

    private boolean positionInCorners;

    /**
     * Constructs a default relationship view which link two RamRectangles together.
     * 
     * @param eObject - the eObject related to the RamRectangle.
     * @param fromView - from RamRectangle
     * @param toView - to RamRectangle
     * @param inCorner - true if the link has to be in corners. Otherwise it will be in the middle of left and right
     *            edges.
     */
    public DefaultRelationshipView(T eObject, RamRectangleComponent fromView, RamRectangleComponent toView,
            boolean inCorner) {
        super(eObject, fromView, eObject, toView);
        this.positionInCorners = inCorner;
    }

    @Override
    protected void update() {
        float fromX = fromEnd.getLocation().getX();
        float fromY = fromEnd.getLocation().getY();
        float toX = toEnd.getLocation().getX();
        float toY = toEnd.getLocation().getY();

        this.drawLine(fromX, fromY, null, toX, toY);
    }

    @Override
    protected void updateRelationshipEnds(RamRectangleComponent viewFrom, RamRectangleComponent viewTo,
            Position fromEndPosition, Position toEndPosition) {
        if (fromEnd.getComponentView() instanceof IRelationshipEndView) {
            super.updateRelationshipEnds(viewFrom, viewTo, fromEndPosition, toEndPosition);
        } else {
            updateRelationshipEnd(fromEnd);
        }
        if (toEnd.getComponentView() instanceof IRelationshipEndView) {
            super.updateRelationshipEnds(viewFrom, viewTo, fromEndPosition, toEndPosition);
        } else {
            updateRelationshipEnd(toEnd);
        }
    }

    /**
     * Position the link on the view depending on its position and the one of its opposite.
     * 
     * @param end - RamEnd element to update the link position
     */
    private void updateRelationshipEnd(RamEnd<?, ?> end) {

        RamRectangleComponent oppositeView = (RamRectangleComponent) end.getOpposite().getComponentView();
        RamRectangleComponent view = (RamRectangleComponent) end.getComponentView();

        float width = view.getWidthXY(TransformSpace.GLOBAL);
        float height = view.getHeightXY(TransformSpace.GLOBAL);

        Vector3D location = view.getPosition(TransformSpace.GLOBAL);
        Vector3D oppositeLocation = oppositeView.getPosition(TransformSpace.GLOBAL);

        float newX = 0;
        float newY = 0;

        if (positionInCorners) {
            boolean topLeft =
                    (oppositeLocation.x <= (location.x + width / 2))
                            && (oppositeLocation.y <= (location.y + height / 2));
            boolean topRight =
                    (oppositeLocation.x > (location.x + width / 2))
                            && (oppositeLocation.y <= (location.y + height / 2));
            boolean bottomLeft =
                    (oppositeLocation.x <= (location.x + width / 2))
                            && (oppositeLocation.y > (location.y + height / 2));
            boolean bottomRight =
                    (oppositeLocation.x > (location.x + width / 2)) && (oppositeLocation.y > (location.y + height / 2));
            if (topLeft) {
                newX = location.x;
                newY = location.y;
            } else if (topRight) {
                newX = location.x + width;
                newY = location.y;
            } else if (bottomLeft) {
                newX = location.x;
                newY = location.y + height;
            } else if (bottomRight) {
                newX = location.x + width;
                newY = location.y + height;
            }
        } else {
            boolean left = oppositeLocation.x <= (location.x + width / 2);

            newX = left ? location.x : location.x + width;
            newY = location.y + height / 2;
        }

        location.setX(newX);
        location.setY(newY);

        /*
         * The position needs to be converted to relative position, because drawing everything excepts a relative
         * position.
         */
        location = getGlobalVecToParentRelativeSpace(this, location);
        end.setLocation(location);
    }
}
