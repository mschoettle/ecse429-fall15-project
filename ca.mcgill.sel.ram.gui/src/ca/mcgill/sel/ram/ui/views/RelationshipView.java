package ca.mcgill.sel.ram.ui.views;

import org.eclipse.emf.ecore.EObject;
import org.mt4j.components.MTComponent;
import org.mt4j.components.TransformSpace;
import org.mt4j.components.visibleComponents.shapes.AbstractShape;
import org.mt4j.components.visibleComponents.shapes.MTPolygon;
import org.mt4j.input.gestureAction.TapAndHoldVisualizer;
import org.mt4j.input.inputProcessors.IGestureEventListener;
import org.mt4j.input.inputProcessors.componentProcessors.tapAndHoldProcessor.TapAndHoldEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapAndHoldProcessor.TapAndHoldProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapProcessor;
import org.mt4j.util.MTColor;
import org.mt4j.util.math.Vector3D;
import org.mt4j.util.math.Vertex;

import processing.core.PGraphics;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamLineComponent;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.events.listeners.ITapAndHoldListener;
import ca.mcgill.sel.ram.ui.events.listeners.ITapListener;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.utils.Constants;
import ca.mcgill.sel.ram.ui.views.RamEnd.Position;
import ca.mcgill.sel.ram.ui.views.handler.BaseHandler;
import ca.mcgill.sel.ram.ui.views.handler.IHandled;
import ca.mcgill.sel.ram.ui.views.handler.IRelationshipViewHandler;
import ca.mcgill.sel.ram.ui.views.state.StateComponentView;
import ca.mcgill.sel.ram.ui.views.structural.ClassifierView;
import ca.mcgill.sel.ram.ui.views.structural.InheritanceView;

/**
 * RelationshipViews are components used to draw relationships between certain elements. The ends of relationships have
 * their own representation and they are linked together using lines. Subclasses may reuse functionality provided or
 * extend it using their own as needed.
 *
 * @param <T>
 *            the class the ends of this relationship represent
 * @param <X>
 *            the view class the ends of this relationship represent
 * @author mschoettle
 * @author walabe
 */
public abstract class RelationshipView<T extends EObject, X extends RamRectangleComponent> extends
        RamRectangleComponent {
    /**
     * The style a line can be visualized with.
     */
    public enum LineStyle {

        /**
         * A solid line.
         */
        SOLID(0),
        /**
         * A dashed line.
         */
        DASHED(0x00FF),
        /**
         * A dotted line.
         */
        DOTTED(0x0F0F);

        // See org.mt4j.components.css.style.CSSStyle#getBorderStylePattern().
        private short stylePattern;

        /**
         * Creates a new line style with the given pattern.
         *
         * @param stylePattern the style pattern for the line style
         */
        LineStyle(int stylePattern) {
            this.stylePattern = (short) stylePattern;
        }

        /**
         * Returns the style pattern for the line style.
         *
         * @return the style pattern for the line style
         */
        public short getStylePattern() {
            return stylePattern;
        }
    }

    /**
     * Polygon which represents an arrow to be used for associations. The arrow is drawn pointing to the given
     * coordinates (coming from the right side).
     *
     * @author mschoettle
     */
    protected class ArrowPolygon extends MTPolygon {

        /**
         * Creates a new array polygon.
         *
         * @param x the X position of the arrow tip
         * @param y the Y position of the arrow tip
         * @param drawColor the color of the arrow lines
         */
        public ArrowPolygon(float x, float y, MTColor drawColor) {
            super(RamApp.getApplication(), new Vertex[] {});

            // vertices should be set first
            Vertex start = new Vertex(x + 12, y - 5);
            Vertex center = new Vertex(x, y);
            Vertex end = new Vertex(x + 12, y + 5);
            setVertices(new Vertex[] { start, center, end });

            // trick to use polygon as a polyline
            setNoFill(true);
            setStrokeWeight(STROKE_WEIGHT);
            setStrokeColor(drawColor);
            setEnabled(false);
            setPickable(false);
        }
    }

    /**
     * Polygon representing a composition (diamond). It is drawn to the right side of the given coordinates.
     *
     * @author mschoettle
     */
    protected class CompositionPolygon extends MTPolygon {

        /**
         * Creates a composition polygon.
         *
         * @param x the X position of the left tip
         * @param y the Y position of the left tip
         * @param drawColor the color of the polygon
         */
        public CompositionPolygon(float x, float y, MTColor drawColor) {
            super(RamApp.getApplication(), new Vertex[] {});

            // vertices should be set first
            Vertex start = new Vertex(x, y);
            Vertex centerUp = new Vertex(x + 10, y + 6);
            Vertex centerBottom = new Vertex(x + 10, y - 6);
            Vertex end = new Vertex(x + 20, y);
            setVertices(new Vertex[] { start, centerUp, end, centerBottom, start });

            setNoFill(false);
            setStrokeColor(drawColor);
            setFillColor(drawColor);
            setEnabled(false);
            setPickable(false);
        }
    }

    /**
     * Polygon representing a inheritance arrow (triangle). It is drawn to the right side of the given coordinates.
     *
     * @author mschoettle
     */
    protected class InheritancePolygon extends MTPolygon {

        /**
         * Creates a inheritance polygon.
         *
         * @param x the X position of the tip of the inheritance
         * @param y the Y position of the tip of the inheritance
         * @param drawColor the color of the inheritance lines
         */
        public InheritancePolygon(float x, float y, MTColor drawColor) {
            super(RamApp.getApplication(), new Vertex[] {});

            // vertices should be set first
            Vertex start = new Vertex(x, y);
            Vertex top = new Vertex(x + 15, y - 10);
            Vertex bottom = new Vertex(x + 15, y + 10);
            setVertices(new Vertex[] { start, top, bottom, start });

            setNoFill(false);
            setFillColor(Colors.DEFAULT_RELATIONSHIP_FILL_COLOR);
            setStrokeWeight(STROKE_WEIGHT);
            setStrokeColor(drawColor);
            setPickable(false);
            setEnabled(false);
        }
    }

    /**
     * The internal handler for invisible boxes that forwards events to the actual handler.
     */
    private class InternalHandler extends BaseHandler implements ITapListener, ITapAndHoldListener {

        @Override
        public boolean processTapAndHoldEvent(TapAndHoldEvent tapAndHoldEvent) {
            if (handler != null && tapAndHoldEvent.isHoldComplete()) {
                @SuppressWarnings("unchecked")
                InvisibleRamEndBox target = (InvisibleRamEndBox) tapAndHoldEvent.getTarget();

                return handler.processTapAndHold(tapAndHoldEvent, target.getRamEnd());
            }

            return true;
        }

        @Override
        public boolean processTapEvent(TapEvent tapEvent) {
            if (handler != null && tapEvent.isDoubleTap()) {
                @SuppressWarnings("unchecked")
                InvisibleRamEndBox target = (InvisibleRamEndBox) tapEvent.getTarget();

                return handler.processDoubleTap(tapEvent, target.getRamEnd());
            }

            return false;
        }
    }

    /**
     * This class is used to make an invisible box behind the association/inheritance arrow shapes to capture gestures
     * and holds a reference to its RamEnds.
     */
    private class InvisibleRamEndBox extends RamRectangleComponent implements IHandled<IGestureEventListener> {

        private static final float BOX_SIZE = 20f;

        private RamEnd<T, X> end;
        private IGestureEventListener handler;

        /**
         * Creates a new invisible box for the given end.
         *
         * @param end the end to represent
         */
        public InvisibleRamEndBox(RamEnd<T, X> end) {
            // we use (0,0) as x and y coordinate initially since it will be relocated with the relocateForPosition
            // function.
            super(0, 0, BOX_SIZE, BOX_SIZE);

            // For Debug purposes.
            // setNoStroke(false);

            this.end = end;
        }

        @Override
        protected void registerInputProcessors() {
            registerInputProcessor(new TapProcessor(RamApp.getApplication(), Constants.TAP_MAX_FINGER_UP, true,
                    Constants.TAP_DOUBLE_TAP_TIME));
            registerInputProcessor(new TapAndHoldProcessor(RamApp.getApplication(), Constants.TAP_AND_HOLD_DURATION));
            addGestureListener(TapAndHoldProcessor.class, new TapAndHoldVisualizer(RamApp.getApplication(), this));
        }

        /**
         * Returns the end belong to this box.
         *
         * @return the end belonging to this box
         */
        public RamEnd<T, X> getRamEnd() {
            return end;
        }

        /**
         * This function moves and resizes the rectangular invisible box depending on the orientation.
         */
        public void updatePosition() {
            if (end.getPosition() != null) {

                Position position = end.getPosition();
                float x = end.getLocation().getX();
                float y = end.getLocation().getY();

                switch (position) {
                    case TOP:
                        x -= 10;
                        y -= 20;
                        break;
                    case BOTTOM:
                        x -= 10;
                        break;
                    case LEFT:
                        x -= 20;
                        y -= 10;
                        break;
                    case RIGHT:
                        y -= 10;
                        break;
                }

                setPositionRelativeToParent(new Vector3D(x, y, 0));
            }

        }

        @Override
        public IGestureEventListener getHandler() {
            return handler;
        }

        @Override
        public void setHandler(IGestureEventListener handler) {
            this.handler = handler;
            registerGestureListeners(handler);
        }

        /**
         * Registers the given handler as a gesture listener.
         *
         * @param listener the handler to register
         */
        private void registerGestureListeners(IGestureEventListener listener) {
            addGestureListener(TapProcessor.class, listener);
            addGestureListener(TapAndHoldProcessor.class, listener);
        }
    }

    /**
     * The weight of the stroke of a line.
     */
    public static final float STROKE_WEIGHT = 2.0f;

    /**
     * The difference the position of an association end can have so it's considered the same.
     */
    public static final double EPSILON = 0.00005;

    /**
     * The distance away from the end the line goes when a self-reference is drawn.
     */
    public static final int DISTANCE_SELF_REFERENCE = 30;

    /**
     * The from end of the relationship.
     */
    protected RamEnd<T, X> fromEnd;

    /**
     * The to end of the relationship.
     */
    protected RamEnd<T, X> toEnd;

    /**
     * The default color of the relationship.
     */
    protected MTColor drawColor;

    /**
     * The background color where the relationship will be drawn (important for polygons).
     */
    protected MTColor backgroundColor;

    /**
     * The handler of the relationship.
     */
    protected IRelationshipViewHandler handler;

    /**
     * The style the line(s) of this relationship should be displayed with.
     */
    protected LineStyle lineStyle;

    private boolean shouldUpdate;

    /**
     * These rectangles are used to capture gestures on arrowheads since the arrow heads are hard to tap with finger.
     */
    private InvisibleRamEndBox toEndInvisibleBox;
    private InvisibleRamEndBox fromEndInvisibleBox;

    /**
     * Creates a new RelationshipView between the given ends.
     *
     * @param from
     *            The meta-model element that the from end represents.
     * @param fromView
     *            the view the from end is attached to
     * @param to
     *            the meta-model element that the to end represents.
     * @param toView
     *            the view to which the to end is attached.
     */
    public RelationshipView(T from, X fromView, T to, X toView) {
        super();

        drawColor = Colors.DEFAULT_ELEMENT_STROKE_COLOR;
        backgroundColor = Colors.DEFAULT_RELATIONSHIP_FILL_COLOR;
        lineStyle = LineStyle.SOLID;

        // create ends and set opposite
        fromEnd = new RamEnd<T, X>(from, this, fromView);
        toEnd = new RamEnd<T, X>(to, this, toView);
        fromEnd.setOpposite(toEnd);
        toEnd.setOpposite(fromEnd);
        this.shouldUpdate = true;

        fromEndInvisibleBox = new InvisibleRamEndBox(fromEnd);
        toEndInvisibleBox = new InvisibleRamEndBox(toEnd);

        // use an internal handler to be able to pass the actual end to the actual handler
        // because the actual handler doesn't (shouldn't) know about the inner workings of this class
        // (encapsulation/information hiding)
        InternalHandler internalHandler = new InternalHandler();
        fromEndInvisibleBox.setHandler(internalHandler);
        toEndInvisibleBox.setHandler(internalHandler);

        addChild(fromEndInvisibleBox);
        addChild(toEndInvisibleBox);
    }

    /**
     * Draws all the lines necessary to connect the two end points correctly.
     */
    protected void drawAllLines() {
        drawAllLines(0, 0, 0, 0);
    }

    /**
     * Draws all the lines necessary to connect the two end points correctly.
     *
     * @param fromEndOffsetX - X offset if from end needs to be shifted
     * @param fromEndOffsetY - Y offset if from end needs to be shifted
     * @param toEndOffsetX - X offset if to end needs to be shifted
     * @param toEndOffsetY - Y offset if to end needs to be shifted
     */
    protected void drawAllLines(float fromEndOffsetX, float fromEndOffsetY, float toEndOffsetX, float toEndOffsetY) {
        float fromX = fromEnd.getLocation().getX() + fromEndOffsetX;
        float fromY = fromEnd.getLocation().getY() + fromEndOffsetY;
        Position positionFrom = fromEnd.getPosition();
        float toX = toEnd.getLocation().getX() + toEndOffsetX;
        float toY = toEnd.getLocation().getY() + toEndOffsetY;
        Position positionTo = toEnd.getPosition();

        // if the x or y values are the same they are just directly next to each other or above each other
        // only one line necessary
        // since the values are floats a comparison needs to be done using an epsilon
        // which was determined through a simple test
        // if (Math.abs(fromX - toX) < EPSILON || Math.abs(fromY - toY) < EPSILON) {
        // drawLine(fromX, fromY, null, toX, toY);
        // }
        // if it is a self-reference
        // four lines are necessary
        if (toEnd.getComponentView() == fromEnd.getComponentView()) {
            float newFromY = fromY - DISTANCE_SELF_REFERENCE;
            float newToX = toX + DISTANCE_SELF_REFERENCE;

            // draw a line upwards from the from end
            drawLine(fromX, fromY, positionFrom, fromX - DISTANCE_SELF_REFERENCE, newFromY);
            // draw a line to the right from the to end
            drawLine(toX, toY, positionTo, newToX, toY + DISTANCE_SELF_REFERENCE);
            // draw a line from the end of the first line to the right
            drawLine(fromX, newFromY, Position.RIGHT, newToX, newFromY);
            // draw a line from the end of the second line upwards
            drawLine(newToX, toY, Position.TOP, newToX, newFromY);
        } else if (positionFrom == Position.BOTTOM && positionTo == Position.TOP
                || positionFrom == Position.TOP && positionTo == Position.BOTTOM
                || positionFrom == Position.LEFT && positionTo == Position.RIGHT
                || positionFrom == Position.RIGHT && positionTo == Position.LEFT) {
            // if the ends are the opposite of each other
            // (e.g. LEFT and RIGHT which means the two classes are next to each other)
            // we need three lines
            // therefore create one from each end that goes to the middle of the two classes
            // the third line will then connect the two lines
            boolean isFromAlone = fromEnd.isAlone();
            boolean isToAlone = toEnd.isAlone();

            boolean lineDrawn = false;

            // if both ends are alone on their side of the class, draw one line in the center
            if (isFromAlone && isToAlone) {
                if (positionFrom == Position.RIGHT || positionFrom == Position.LEFT) {
                    float centerY = fromY + (toY - fromY) / 2.0f;
                    drawLine(fromX, centerY, null, toX, centerY);
                    lineDrawn = true;
                } else {
                    float centerX = fromX + (toX - fromX) / 2.0f;
                    drawLine(centerX, fromY, null, centerX, toY);
                    lineDrawn = true;
                }
            } else if (isFromAlone) {
                // if the "from" end is alone on its side of the class, draw the line straight from the "to" position
                Vector3D fromClassLocation = getLocalVecToParentRelativeSpace(fromEnd.getComponentView(),
                        fromEnd.getComponentView().getBounds().getVectorsLocal()[0]);
                float fromTop = fromClassLocation.getY();
                float fromBottom = fromTop + fromEnd.getComponentView().getHeightXY(TransformSpace.RELATIVE_TO_PARENT);
                if (positionFrom == Position.RIGHT || positionFrom == Position.LEFT) {
                    if (toY > fromTop && toY < fromBottom) {
                        drawLine(fromX, toY, null, toX, toY);
                        lineDrawn = true;
                    }
                }
                float fromLeft = fromClassLocation.getX();
                float fromRight = fromLeft + fromEnd.getComponentView().getWidthXY(TransformSpace.RELATIVE_TO_PARENT);
                if (toX > fromLeft && toX < fromRight) {
                    drawLine(toX, fromY, null, toX, toY);
                    lineDrawn = true;
                }
            } else if (isToAlone) {
                // if the "to" end is alone on its side of the class, draw the line straight from the "from" position
                Vector3D toClassLocation = getLocalVecToParentRelativeSpace(toEnd.getComponentView(),
                        toEnd.getComponentView().getBounds().getVectorsLocal()[0]);
                float toTop = toClassLocation.getY();
                float toBottom = toTop + toEnd.getComponentView().getHeightXY(TransformSpace.RELATIVE_TO_PARENT);
                if (positionFrom == Position.RIGHT || positionFrom == Position.LEFT) {
                    if (fromY > toTop && fromY < toBottom) {
                        drawLine(fromX, fromY, null, toX, fromY);
                        lineDrawn = true;
                    }
                }
                float toLeft = toClassLocation.getX();
                float toRight = toLeft + toEnd.getComponentView().getWidthXY(TransformSpace.RELATIVE_TO_PARENT);
                if (fromX > toLeft && fromX < toRight) {
                    drawLine(fromX, fromY, null, fromX, toY);
                    lineDrawn = true;
                }
            }

            // otherwise draw 3 lines
            if (!lineDrawn) {
                float fromCenterX = fromX + (toX - fromX) / 2.0f;
                float fromCenterY = fromY + (toY - fromY) / 2.0f;
                float toCenterX = toX + (fromX - toX) / 2.0f;
                float toCenterY = toY + (fromY - toY) / 2.0f;
                drawLine(fromX, fromY, positionFrom, fromCenterX, fromCenterY);
                drawLine(toX, toY, positionTo, toCenterX, toCenterY);
                // Revert the position according to the current one.
                // Since drawLine does the same for TOP and BOTTOM
                // it is not necessary to distinguish between the two cases.
                // If the classes are next to each the y value stays the same and the x center is used.
                if (positionFrom == Position.RIGHT || positionFrom == Position.LEFT) {
                    drawLine(fromCenterX, fromY, Position.TOP, toCenterX, toY);
                    // otherwise x stays the same and the y center is used
                } else {
                    drawLine(fromX, fromCenterY, Position.LEFT, toX, toCenterY);
                }
            }
        } else {
            // two lines are sufficient
            // so just draw them starting one from each end
            drawLine(fromX, fromY, positionFrom, toX, toY);
            drawLine(toX, toY, positionTo, fromX, fromY);
        }
    }

    @Override
    public void drawComponent(PGraphics g) {
        if (this.shouldUpdate) {
            prepareUpdate();
            this.shouldUpdate = false;
        }
        super.drawComponent(g);
    }

    /**
     * Draws a single line starting at the point (fromX,fromY).
     * The line will be vertical if position is BOTTOM or TOP
     * and vertical if position is LEFT or RIGHT.
     * If position is null the lines is drawn between the two points.
     *
     * @param fromX the X position of the start of the line
     * @param fromY the Y position of the start of the line
     * @param position the position of the edge where the line will be connected
     * @param toX the X position of the end of the line
     * @param toY the Y position of the end of the line
     */
    protected void drawLine(float fromX, float fromY, Position position, float toX, float toY) {
        RamLineComponent line = null;
        if (position == null) {
            line = new RamLineComponent(fromX, fromY, toX, toY);
        } else {
            switch (position) {
                case BOTTOM:
                case TOP:
                    // create a vertical line
                    line = new RamLineComponent(fromX, fromY, fromX, toY);
                    break;
                case LEFT:
                case RIGHT:
                    // create a horizontal line
                    line = new RamLineComponent(fromX, fromY, toX, fromY);
                    break;
            }
        }
        line.setStrokeColor(drawColor);
        line.setPickable(false);
        line.setEnabled(false);
        line.setLineStipple(lineStyle.getStylePattern());
        addChild(line);
    }

    /**
     * The end the relationship starts from.
     *
     * @return the end the relationship starts from
     */
    public RamEnd<T, X> getFromEnd() {
        return fromEnd;
    }

    /**
     * The end the relationship goes to.
     *
     * @return the end the relationship goes to
     */
    public RamEnd<T, X> getToEnd() {
        return toEnd;
    }

    /**
     * Rotates the given shape at the given point depending on the position.
     * The position determines the rotation degree.
     *
     * @param shape the shape to rotate
     * @param x the rotation X point
     * @param y the rotation Y point
     * @param position the edge where the shape needs to attach to
     */
    protected void rotateShape(AbstractShape shape, float x, float y, Position position) {
        Vector3D rotationPoint = new Vector3D(x, y, 1);
        // rotate depending on position
        switch (position) {
            case BOTTOM:
                shape.rotateZ(rotationPoint, 90);
                break;
            case TOP:
                shape.rotateZ(rotationPoint, -90);
                break;
            case LEFT:
                shape.rotateZ(rotationPoint, 180);
                break;
            case RIGHT:
                // nothing to do since the shape is initially created for the right side
                break;
        }
    }

    /**
     * Requests that the view will be redrawn in the next frame.
     */
    public void shouldUpdate() {
        if (toEnd.getPosition() != Position.OFFSCREEN && fromEnd.getPosition() != Position.OFFSCREEN) {
            this.shouldUpdate = true;
            this.setVisible(true);
        } else {
            this.setVisible(false);
        }
    }

    /**
     * Redraw this relationship. Unless already drawn components are reused, the children should be removed first by
     * calling {@link #removeAllChildren()}
     */
    protected abstract void update();

    /**
     * Prepares the view for an upcoming update.
     * The update is skipped, if the ends are outside the screen.
     */
    private void prepareUpdate() {
        removeChildren();

        if (toEnd.getPosition() != Position.OFFSCREEN && fromEnd.getPosition() != Position.OFFSCREEN) {
            update();
        }

        // Since the lines are moved the invisible rectangles of the arrowheads needs to be repositioned as well. (these
        // components are used to capture gesture for
        // arrowheads)
        fromEndInvisibleBox.updatePosition();
        toEndInvisibleBox.updatePosition();
    }

    /**
     * Removes all children that are a line or a polygon.
     * All other components are kept and are intended to be reused (e.g., need to be moved).
     */
    private void removeChildren() {
        // removing by index does not increase performance,
        // because MT4j internally deletes from the list by object, not by index
        for (MTComponent child : getChildren()) {
            // Don't remove the texts that are created just once.
            if (!(child instanceof RamRectangleComponent)
                    && (child instanceof RamLineComponent || child instanceof MTPolygon
                    )) {
                removeChild(child);
                child.destroy();
            }
        }
    }

    /**
     * Updates the line layout for the view.
     */
    // TODO: mschoettle: should this be done in each sub-class for that type specifically?
    public void updateLines() {
        RamRectangleComponent viewFrom = fromEnd.getComponentView();
        RamRectangleComponent viewTo = toEnd.getComponentView();

        // get the top left corner coordinates of the classes for all calculations
        Vector3D topLeftFrom = viewFrom.getPosition(TransformSpace.GLOBAL);
        Vector3D topLeftTo = viewTo.getPosition(TransformSpace.GLOBAL);

        float heightFrom = viewFrom.getHeightXY(TransformSpace.GLOBAL);
        float widthFrom = viewFrom.getWidthXY(TransformSpace.GLOBAL);
        float heightTo = viewTo.getHeightXY(TransformSpace.GLOBAL);
        float widthTo = viewTo.getWidthXY(TransformSpace.GLOBAL);

        // calculate the outer coordinates and center of each class
        float leftXFrom = topLeftFrom.getX();
        float rightXFrom = topLeftFrom.getX() + widthFrom;
        float topYFrom = topLeftFrom.getY();
        float bottomYFrom = topLeftFrom.getY() + heightFrom;
        float leftXTo = topLeftTo.getX();
        float rightXTo = topLeftTo.getX() + widthTo;
        float topYTo = topLeftTo.getY();
        float bottomYTo = topLeftTo.getY() + heightTo;

        Position fromEndPosition = null;
        Position toEndPosition = null;

        // positions are done differently for inheritance, so first check what we are dealing with
        // TODO: this should be done by InheritanceView (override this method)
        if (fromEnd.getRelationshipView() instanceof InheritanceView) {
            // it is an inheritance relationship
            // "to" is the superclass, "from" is the subclass
            if (topYTo > bottomYFrom) {
                fromEndPosition = Position.BOTTOM;
                toEndPosition = Position.TOP;
            } else if (bottomYTo < topYFrom) {
                fromEndPosition = Position.TOP;
                toEndPosition = Position.BOTTOM;
            } else if (rightXFrom < leftXTo) {
                fromEndPosition = Position.RIGHT;
                toEndPosition = Position.LEFT;
            } else if (leftXFrom > rightXTo) {
                fromEndPosition = Position.LEFT;
                toEndPosition = Position.RIGHT;
            } else {
                fromEndPosition = Position.OFFSCREEN;
                toEndPosition = Position.OFFSCREEN;
            }

        } else {
            // it is an association relationship (and not inheritance)
            // if the classes are the same (self-reference)
            // just specify something
            if (viewFrom == viewTo) {
                fromEndPosition = Position.TOP;
                toEndPosition = Position.RIGHT;
            } else if (rightXFrom < leftXTo) {
                // depending on the two classes get the position of the ends
                // where they should be drawn
                // from class is on the left side of to class

                // from class is above to class
                if (bottomYFrom < topYTo) {
                    fromEndPosition = Position.BOTTOM;
                    toEndPosition = Position.LEFT;
                } else if (bottomYTo < topYFrom) {
                    // to class is above from class
                    fromEndPosition = Position.RIGHT;
                    toEndPosition = Position.BOTTOM;
                } else {
                    // classes are next each other (neither above nor below)
                    fromEndPosition = Position.RIGHT;
                    toEndPosition = Position.LEFT;
                }
            } else if (rightXTo < leftXFrom) {
                // to class is on the left side of from class
                // from class is above to class
                if (bottomYFrom < topYTo) {
                    fromEndPosition = Position.LEFT;
                    toEndPosition = Position.TOP;
                } else if (bottomYTo < topYFrom) {
                    // to class is above from class
                    fromEndPosition = Position.LEFT;
                    toEndPosition = Position.BOTTOM;
                } else {
                    // classes are next each other (neither above nor below)
                    fromEndPosition = Position.LEFT;
                    toEndPosition = Position.RIGHT;
                }
            } else if (bottomYFrom < topYTo) {
                // classes neither on the left nor on the right of each other
                // from class is above to class
                fromEndPosition = Position.BOTTOM;
                toEndPosition = Position.TOP;
            } else if (bottomYTo < topYFrom) {
                // classes neither on the left nor on the right of each other
                // to class is above from class
                fromEndPosition = Position.TOP;
                toEndPosition = Position.BOTTOM;
            } else {
                fromEndPosition = Position.OFFSCREEN;
                toEndPosition = Position.OFFSCREEN;
            }
        }
        updateRelationshipEnds(viewFrom, viewTo, fromEndPosition, toEndPosition);
    }

    /**
     * Update the relationship end views depending on their position.
     * 
     * @param viewFrom - view of the from component
     * @param viewTo - view of the to component
     * @param fromEndPosition - position of the from component
     * @param toEndPosition - position of the to component
     */
    protected void updateRelationshipEnds(RamRectangleComponent viewFrom, RamRectangleComponent viewTo,
            Position fromEndPosition, Position toEndPosition) {
        // now we set the positions.
        if (viewFrom instanceof ClassifierView) {
            // process from end
            ClassifierView<?> classViewFrom = (ClassifierView<?>) viewFrom;

            // if previous and current positions are different
            // also if fromEnd.getPosition() is null
            if (fromEndPosition != fromEnd.getPosition()) {
                classViewFrom.moveRelationshipEnd(fromEnd, fromEndPosition);
                // Shouldn't be necessary, because in the previous call it will be called at some point.
                // See issue #36.
                // classViewFrom.setCorrectPosition(fromEnd);
            } else {
                // if position is the same reset the positions
                // for all ends on the same edge of this view.
                classViewFrom.setCorrectPosition(fromEnd);
            }
            // process to end
            ClassifierView<?> classViewTo = (ClassifierView<?>) viewTo;

            if (toEndPosition != toEnd.getPosition()) {
                classViewTo.moveRelationshipEnd(toEnd, toEndPosition);
                // Correct the position of the fromEnd if necessary (due to changes of the to end).
                // See issue #36.
                classViewFrom.setCorrectPosition(fromEnd);
            } else {
                classViewTo.setCorrectPosition(toEnd);
            }
        } else if (viewFrom instanceof StateComponentView) {
            // process from end
            StateComponentView stateViewFrom = (StateComponentView) viewFrom;

            // if previous and current positions are different (also if fromEnd.getPosition()==null )
            if (fromEndPosition != fromEnd.getPosition()) {
                stateViewFrom.moveTransitionEnd(fromEnd, fromEndPosition);
            } else {
                // if position is the same reset the positions for all ends on the same
                // edge of this view.
                stateViewFrom.setCorrectPosition(fromEnd);
            }
            // process to end
            StateComponentView stateViewTo = (StateComponentView) viewTo;

            if (toEndPosition != toEnd.getPosition()) {
                stateViewTo.moveTransitionEnd(toEnd, toEndPosition);
            } else {
                stateViewTo.setCorrectPosition(toEnd);
            }
        } else if (viewFrom instanceof RamRectangleComponent) {
            // TODO: This is what everyone should do (see issue #109).
            IRelationshipEndView fromRelationshipView = null;
            IRelationshipEndView toRelationshipView = null;
            if (fromEnd.getComponentView() instanceof IRelationshipEndView) {
                fromRelationshipView = (IRelationshipEndView) fromEnd.getComponentView();
            }
            if (toEnd.getComponentView() instanceof IRelationshipEndView) {
                toRelationshipView = (IRelationshipEndView) toEnd.getComponentView();
            }
            // End has to be moved somewhere else.
            if (fromEndPosition != fromEnd.getPosition()) {
                Position oldPosition = fromEnd.getPosition();
                fromEnd.setPosition(fromEndPosition);
                if (fromRelationshipView != null) {
                    fromRelationshipView.moveRelationshipEnd(fromEnd, oldPosition, fromEndPosition);
                }
            } else {
                if (fromRelationshipView != null) {
                    fromRelationshipView.updateRelationshipEnd(fromEnd);
                }
            }

            if (toEndPosition != toEnd.getPosition()) {
                Position oldPosition = toEnd.getPosition();
                toEnd.setPosition(toEndPosition);
                if (toRelationshipView != null) {
                    toRelationshipView.moveRelationshipEnd(toEnd, oldPosition, toEndPosition);
                }
                // The from end might have to be fixed depending on what happened to the to end.
                if (fromRelationshipView != null) {
                    fromRelationshipView.updateRelationshipEnd(fromEnd);
                }
            } else {
                if (toRelationshipView != null) {
                    toRelationshipView.updateRelationshipEnd(toEnd);
                }
            }
        }
    }

    /**
     * Sets the handler for this view. The handler has to implement the {@link IRelationshipViewHandler} interface,
     * which provides the ability to tap and tap and hold on a relationship view.
     *
     * @param handler
     *            the handler to use for this view
     */
    public void setHandler(IRelationshipViewHandler handler) {
        this.handler = handler;
    }

    /**
     * Set the background color of some elements like the aggregation shape.
     *
     * @param color
     *            The new background color
     */
    public void setBackgroundColor(MTColor color) {
        this.backgroundColor = color;
    }

    /**
     * Get the background color of the relationship view.
     *
     * @return The background color
     */
    public MTColor getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * Sets the line style to be used when drawing lines for this relationship.
     *
     * @param lineStyle the line style to use for drawing lines
     */
    public void setLineStyle(LineStyle lineStyle) {
        this.lineStyle = lineStyle;
    }

}
