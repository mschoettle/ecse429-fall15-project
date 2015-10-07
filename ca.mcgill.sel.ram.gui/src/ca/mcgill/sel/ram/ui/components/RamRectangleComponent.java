package ca.mcgill.sel.ram.ui.components;

import org.mt4j.components.MTComponent;
import org.mt4j.components.StateChange;
import org.mt4j.components.TransformSpace;
import org.mt4j.components.clipping.Clip;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;
import org.mt4j.input.gestureAction.DefaultDragAction;
import org.mt4j.input.gestureAction.DefaultRotateAction;
import org.mt4j.input.gestureAction.DefaultScaleAction;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.rotateProcessor.RotateProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.scaleProcessor.ScaleProcessor;
import org.mt4j.util.MTColor;
import org.mt4j.util.math.Vector3D;
import org.mt4j.util.math.Vertex;

import processing.core.PImage;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.layouts.Layout;
import ca.mcgill.sel.ram.ui.layouts.Layout.LayoutUpdatePhase;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.utils.GraphicalUpdater.HighlightEvent;
import ca.mcgill.sel.ram.ui.utils.GraphicalUpdaterListener;
import ca.mcgill.sel.ram.ui.utils.Icons;
import ca.mcgill.sel.ram.ui.utils.MathUtils;

/**
 * An abstract component that allow for an automatic parent-child resizing link. Subclasses wishing to respond to events
 * should override the handle[Event]() methods and NOT the processGestureEvent() method.
 *
 * @author vbonnet
 * @author tdimeco
 */
public class RamRectangleComponent extends MTRectangle implements GraphicalUpdaterListener {

    /**
     * Cardinal directions for rectangle edges.
     */
    public enum Cardinal {

        /** North (or upper) edge. */
        NORTH,
        /** East (or right) edge. */
        EAST,
        /** South (or bottom) edge. */
        SOUTH,
        /** West (or left) edge. */
        WEST
    }

    private RamRectangleComponent containingComponent;

    private Layout layout;
    private float[] buffers;
    private float minimumWidth;
    private float minimumHeight;
    private float maximumWidth;
    private float maximumHeight;
    private boolean autoMinimizes;
    private boolean autoMaximizes;

    /**
     * Creates a new instance at (0,0) with dimensions (0,0).
     */
    public RamRectangleComponent() {
        this(0, 0, 0, 0);
    }

    /**
     * Creates a new instance at (0,0) with the given dimensions.
     *
     * @param width The width of the component
     * @param height The height of the component
     */
    protected RamRectangleComponent(float width, float height) {
        this(0, 0, width, height);
    }

    /**
     * Creates a new instance with the given position and dimensions.
     *
     * @param x The x value for the top left vertex of the component
     * @param y The y value for the top left vertex of the component
     * @param width The width of the component
     * @param height The height of the component
     */
    public RamRectangleComponent(float x, float y, float width, float height) {

        super(RamApp.getApplication(), x, y, width, height);

        buffers = new float[Cardinal.values().length];
        minimumWidth = -1;
        minimumHeight = -1;
        maximumWidth = Float.MAX_VALUE;
        maximumHeight = Float.MAX_VALUE;

        autoMinimizes = true;
        autoMaximizes = true;

        setAnchor(PositionAnchor.UPPER_LEFT);
        setStrokeColor(Colors.DEFAULT_ELEMENT_STROKE_COLOR);
        setStrokeWeight(2f);
        setNoStroke(true);
        setNoFill(true);

        registerInputProcessors();
    }

    /**
     * Creates a rectangle at (0,0) with dimensions (0,0) and sets the layout.
     *
     * @param layout The layout to be used by this component
     */
    public RamRectangleComponent(Layout layout) {
        this(0, 0, 0, 0);
        this.layout = layout;
    }

    /**
     * Add a child component to this component at a given index and update the entire hierarchy to re-layout itself.
     *
     * @param i The index
     * @param child The child component to be added
     */
    @Override
    public void addChild(int i, MTComponent child) {
        addChild(i, child, true);
    }

    /**
     * Add a child component to this component. You can disable the re-layout by setting update to false. After that,
     * you have to call the updateLayout() method to update the layout manually.
     *
     * @param child The child component to be added
     * @param update Specifies if the method should update the entire hierarchy to re-layout itself
     */
    public void addChild(MTComponent child, boolean update) {
        addChild(getChildCount(), child, update);
    }

    /**
     * Add a child component to this component at a given index. You can disable the re-layout by setting update to
     * false. After that, you have to call the updateLayout() method to update the layout manually.
     *
     * @param i The index
     * @param child The child component to be added
     * @param update Specifies if the method should update the entire hierarchy to re-layout itself
     */
    public void addChild(int i, MTComponent child, boolean update) {
        child.translate(new Vector3D(getBufferSize(Cardinal.WEST), getBufferSize(Cardinal.NORTH)));
        super.addChild(i, child);

        if (child instanceof RamRectangleComponent) {
            RamRectangleComponent ramChild = (RamRectangleComponent) child;
            ramChild.setParent(this);
            if (update) {
                ramChild.updateParent();
            }
        }
    }

    /**
     * Adds a {@link RamSpacerComponent} with the given dimensions.
     *
     * @param width The width of the spacer
     * @param height The height of the spacer
     */
    public void addSpacer(float width, float height) {
        addChild(new RamSpacerComponent(width, height));
    }

    /**
     * Whether the component auto-maximizes itself.
     *
     * @return true if yes, false otherwise
     */
    public boolean autoMaximizes() {
        return autoMaximizes;
    }

    /**
     * Whether the component auto-minimizes itself.
     *
     * @return true if yes, false otherwise
     */
    public boolean autoMinimizes() {
        return autoMinimizes;
    }

    /**
     * Called when a component has changed size and believes this component wishes to be informed.
     *
     * @param child The component whose size has changed
     */
    protected void childResized(MTComponent child) {
        if (containsChild(child)) {
            if (hasLayout()) {
                layout.layout(this, LayoutUpdatePhase.FROM_CHILD);
                handleChildResized(child);
                updateChildren();
                updateParent();
                fireStateChange(StateChange.RESIZED);
            }
        }
    }

    @Override
    public boolean containsPointGlobal(Vector3D testPoint) {
        // Make sure the check only happens if this component is currently visible.
        if (getParent() != null) {
            return super.containsPointGlobal(testPoint);
        }

        return false;
    }

    /**
     * Remove and destroy all children without update the layout after each removal. Only after all children are
     * removed, update the entire hierarchy to re-layout itself.
     */
    public void destroyAllChildren() {
        MTComponent[] children = getChildren().clone();
        removeAllChildren();
        for (MTComponent c : children) {
            c.destroy();
        }
    }

    /**
     * Get the buffer size of a cardinal direction.
     *
     * @param cardinal The cardinal direction of the buffer size of interest
     * @return The buffer size for the given cardinal direction
     */
    public float getBufferSize(Cardinal cardinal) {
        return buffers[cardinal.ordinal()];
    }

    /**
     * Get the child under the given point in global space.
     *
     * @param point The point to check for child collision
     * @return The child component at the given point in global space, null if no such child exists
     */
    public MTComponent getChildByPoint(Vector3D point) {
        for (MTComponent child : getChildren()) {
            if (child.containsPointGlobal(point)) {
                return child;
            }
        }
        return null;
    }

    /**
     * Returns the height available for children to display.
     *
     * @return The height available for children to display (totalHeight - North/South buffers).
     */
    public float getChildHeight() {
        return getHeight() - getBufferSize(Cardinal.NORTH) - getBufferSize(Cardinal.SOUTH);
    }

    /**
     * Returns the width available for children to display.
     *
     * @return The width available for children to display (totalWidth - East/West buffers).
     */
    public float getChildWidth() {
        return getWidth() - getBufferSize(Cardinal.EAST) - getBufferSize(Cardinal.WEST);
    }

    /**
     * The rectangle's height on the global transform space.
     *
     * @return The rectangle's height on the global transform space
     */
    public float getHeight() {
        return getHeightXY(TransformSpace.LOCAL);
    }

    /**
     * Returns the associated layout of the component.
     *
     * @return The layout reference, or null if there is no layout
     */
    public Layout getLayout() {
        return layout;
    }

    /**
     * Returns the maximum height available for children to display.
     *
     * @return The maximum height available for children to display
     */
    public float getMaximumChildHeight() {
        return getMaximumHeight() - getBufferSize(Cardinal.NORTH) - getBufferSize(Cardinal.SOUTH);
    }

    /**
     * Returns the maximum width available for children to display.
     *
     * @return The maximum width available for children to display
     */
    public float getMaximumChildWidth() {
        return getMaximumWidth() - getBufferSize(Cardinal.WEST) - getBufferSize(Cardinal.EAST);
    }

    /**
     * Returns the maximum height of the component.
     *
     * @return The maximum height of the component
     */
    public float getMaximumHeight() {
        return maximumHeight;
    }

    /**
     * Returns the maximum width of the component.
     *
     * @return The maximum width of the component
     */
    public float getMaximumWidth() {
        return maximumWidth;
    }

    /**
     * Returns the minimum height. This will be equivalent to getHeight() if no minimum height is set.
     *
     * @return The minimum height
     */
    public float getMinimumHeight() {
        return minimumHeight < 0 ? getHeight() : minimumHeight;
    }

    /**
     * Returns the minimum width. This will be equivalent to getWidth() if no minimum width is set.
     *
     * @return The minimum width
     */
    public float getMinimumWidth() {
        return minimumWidth < 0 ? getWidth() : minimumWidth;
    }

    /**
     * Returns the parent of the related type.
     * 
     * @param type - type of the parent requested
     * @param <T> - the expected return type
     * @return the parent related to the given type.
     */
    public <T extends MTComponent> T getParentOfType(Class<T> type) {

        MTComponent currentObject = this;
        while (currentObject.getParent() != null) {
            currentObject = currentObject.getParent();
            if (type.isInstance(currentObject)) {
                @SuppressWarnings("unchecked")
                T typed = (T) currentObject;
                return typed;
            }
        }

        return null;
    }

    /**
     * Returns the scale of the component.
     *
     * @return The scale of the component
     */
    public Vector3D getScale() {
        return getGlobalMatrix().getScale();
    }

    /**
     * Returns the rectangle's width on the global transform space.
     *
     * @return The rectangle's width on the global transform space
     */
    public float getWidth() {
        return getWidthXY(TransformSpace.LOCAL);
    }

    /**
     * Returns the x value of top left corner of this component in global coordinates.
     *
     * @return The x value of top left corner of this component in global coordinates
     */
    public float getX() {
        return getBounds().getVectorsGlobal()[0].getX();
    }

    /**
     * Returns the x value of point at which this component's children should appear in global coordinates.
     *
     * @return The x value of point at which this component's children should appear in global coordinates
     */
    public float getXForChildren() {
        return getX() + getBufferSize(Cardinal.WEST);
    }

    /**
     * Returns the y value of top left corner of this component in global coordinates.
     *
     * @return The y value of top left corner of this component in global coordinates
     */
    public float getY() {
        return getBounds().getVectorsGlobal()[0].getY();
    }

    /**
     * Returns the y value of point at which this component's children should appear in global coordinates.
     *
     * @return The y value of point at which this component's children should appear in global coordinates
     */
    public float getYForChildren() {
        return getY() + getBufferSize(Cardinal.NORTH);
    }

    /**
     * Can be overridden by subclasses to handle a child resize.
     *
     * @param component The child component that changed
     */
    protected void handleChildResized(MTComponent component) {
    }

    /**
     * Can be overridden by subclasses to handle the parent resize.
     */
    protected void handleParentResized() {
    }

    /**
     * Whether the component has an associated layout or not.
     *
     * @return true if yes, false otherwise
     */
    public boolean hasLayout() {
        return layout != null;
    }

    /**
     * Sets the size of the component to be the maximum size.
     */
    public void maximizeSize() {
        setSizeLocal(getMaximumWidth() == Float.MAX_VALUE ? getWidth() : getMaximumWidth(),
                getMaximumHeight() == Float.MAX_VALUE ? getHeight() : getMaximumHeight());
    }

    /**
     * Sets the size of the component to be the minimum size.
     */
    public void minimizeSize() {

        final float width =
                (hasLayout() && !layout.getForceToKeepSize()) ? Math.max(minimumWidth, layout.getMinimumWidth())
                        : getMinimumWidth();

        final float height =
                (hasLayout() && !layout.getForceToKeepSize()) ? Math.max(minimumHeight, layout.getMinimumHeight())
                        : getMinimumHeight();

        setSizeLocal(width, height);
    }

    /**
     * Called when the parent component has changed size and believes this component wishes to be informed.
     */
    protected void parentResized() {
        if (hasLayout()) {
            layout.layout(this, LayoutUpdatePhase.FROM_PARENT);
            handleParentResized();
            updateChildren();
        }
    }

    /**
     * Registers input processors for this component. Can be overridden by subclasses.
     */
    protected void registerInputProcessors() {
    }

    /**
     * Remove all children without update the layout after each removal. Only after all children are removed, update the
     * entire hierarchy to re-layout itself.
     */
    @Override
    public void removeAllChildren() {
        for (int i = getChildCount() - 1; i >= 0; i--) {
            MTComponent child = getChildByIndex(i);
            removeChild(child, false);
        }
        updateLayout();
    }

    /**
     * Remove the child at the given index and update the entire hierarchy to re-layout itself.
     *
     * @param i The index
     */
    @Override
    public void removeChild(int i) {
        removeChild(i, true);
    }

    /**
     * Remove the child at the given index. You can disable the re-layout by setting update to false. After that, you
     * have to call the updateLayout() method to update the layout manually.
     *
     * @param i The index
     * @param update Specifies if the method should update the entire hierarchy to re-layout itself
     */
    public void removeChild(int i, boolean update) {
        super.removeChild(i);
        if (update) {
            updateLayout();
        }
    }

    /**
     * Remove the given child and update the entire hierarchy to re-layout itself.
     *
     * @param comp The child you want to remove
     */
    @Override
    public void removeChild(MTComponent comp) {
        removeChild(comp, true);
    }

    /**
     * Remove the given child. You can disable the re-layout by setting update to false. After that, you have to call
     * the updateLayout() method to update the layout manually.
     *
     * @param comp The child component
     * @param update Specifies if the method should update the entire hierarchy to re-layout itself
     */
    public void removeChild(MTComponent comp, boolean update) {
        super.removeChild(comp);
        if (update) {
            updateLayout();
        }
    }

    /**
     * Scales the component by the given amount.
     *
     * @param scale The values to scale x,y,z by
     */
    public void scale(Vector3D scale) {
        scale(scale.getX(), scale.getY(), scale.getZ(), getCenterPointLocal());
    }

    /**
     * Sets the component to auto-maximizes itself.
     *
     * @param autoMaximizes Whether the component is automatically maximized
     */
    public void setAutoMaximizes(boolean autoMaximizes) {
        this.autoMaximizes = autoMaximizes;
    }

    /**
     * Sets the component to auto-minimizes itself.
     *
     * @param autoMinimizes Whether the component is automatically minimized when new minimum bounds are set
     */
    public void setAutoMinimizes(boolean autoMinimizes) {
        this.autoMinimizes = autoMinimizes;
    }

    /**
     * Sets the same buffer size to all cardinal directions.
     *
     * @param size The size for the buffers
     */
    public void setBuffers(float size) {
        for (int index = 0; index < Cardinal.values().length; index++) {
            setBufferSize(Cardinal.values()[index], size);
        }
    }

    /**
     * Sets the buffer size for a given cardinal direction.
     *
     * @param cardinal The cardinal direction of the buffer of interest
     * @param size The size to set for the buffer
     */
    public void setBufferSize(Cardinal cardinal, float size) {
        buffers[cardinal.ordinal()] = size;
    }

    /**
     * Sets the child clip according for the buffers on the sides.
     *
     * @param width The width of the clip to create
     * @param height The height of the clip to create
     */
    public void setChildClip(float width, float height) {
        setChildClip(new Clip(RamApp.getApplication(), getBufferSize(Cardinal.WEST), getBufferSize(Cardinal.NORTH),
                width - getBufferSize(Cardinal.WEST) - getBufferSize(Cardinal.EAST), height
                        - getBufferSize(Cardinal.NORTH) - getBufferSize(Cardinal.SOUTH)));
    }

    @Override
    protected void setDefaultGestureActions() {
        // NOTE - make sure to override and not call super, otherwise extra input processors will be added to this
        // component.
        // This causes gestures to be handled twice and makes it look like drag events are scaled wrong.
    }

    /**
     * Enable default MT4J gesture actions.
     */
    protected void setDefaultMT4JGestureActions() {
        this.registerInputProcessor(new RotateProcessor(this.getRenderer()));
        this.setGestureAllowance(RotateProcessor.class, true);
        this.addGestureListener(RotateProcessor.class, new DefaultRotateAction());

        this.registerInputProcessor(new ScaleProcessor(this.getRenderer()));
        this.setGestureAllowance(ScaleProcessor.class, true);
        this.addGestureListener(ScaleProcessor.class, new DefaultScaleAction());

        this.registerInputProcessor(new DragProcessor(this.getRenderer()));
        this.setGestureAllowance(DragProcessor.class, true);
        this.addGestureListener(DragProcessor.class, new DefaultDragAction());
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        for (MTComponent child : getChildren()) {
            child.setEnabled(enabled);
        }
    }

    @Override
    public void setHeightLocal(float height) {
        setSizeLocal(getWidth(), height);
    }

    /**
     * Sets the layout object that will determine how the children of this component are laid out.
     *
     * @param layout The layout object
     */
    public void setLayout(Layout layout) {
        this.layout = layout;
        updateLayout();
    }

    /**
     * Sets the maximum height of the component.
     *
     * @param height The maximum height
     */
    public void setMaximumHeight(float height) {
        setMaximumSize(getMaximumWidth(), height);
    }

    /**
     * Sets the maximum size of the component.
     *
     * @param width The maximum width
     * @param height The maximum height
     */
    public void setMaximumSize(float width, float height) {
        maximumWidth = width;
        maximumHeight = height;
        if (autoMaximizes() || getHeight() > getMaximumHeight() || getWidth() > getMaximumWidth()) {
            maximizeSize();
        }
    }

    /**
     * Sets the maximum width of the component.
     *
     * @param width The maximum width
     */
    public void setMaximumWidth(float width) {
        setMaximumSize(width, getMaximumHeight());
    }

    /**
     * Sets the minimum height of the component. Set a value < 0 to disable the minimum. With no minimum, the current
     * height is the minimum for non-layouted component only.
     *
     * @param height The minimum height
     */
    public void setMinimumHeight(float height) {
        setMinimumSize(minimumWidth, height);
    }

    /**
     * Sets the minimum size of the component. Set a value < 0 to disable the minimum. With no minimum, the current size
     * is the minimum for non-layouted component only.
     *
     * @param width The minimum width
     * @param height The minimum height
     */
    public void setMinimumSize(float width, float height) {
        minimumWidth = width;
        minimumHeight = height;
        if (autoMinimizes() || getHeight() < getMinimumHeight() || getWidth() < getMinimumWidth()) {
            minimizeSize();
        }
    }

    /**
     * Sets the minimum width of the component. Set a value < 0 to disable the minimum. With no minimum, the current
     * width is the minimum for non-layouted component only.
     *
     * @param width The minimum width
     */
    public void setMinimumWidth(float width) {
        setMinimumSize(width, minimumHeight);
    }

    /**
     * Sets the parent of this component. Parents are used to position children and to layout the hierarchy of
     * components.
     *
     * @param parent The new parent component
     */
    protected void setParent(RamRectangleComponent parent) {
        containingComponent = parent;
    }

    @Override
    public void setSizeLocal(float width, float height) {

        float newWidth = MathUtils.clampFloat(minimumWidth, width, maximumWidth);
        float newHeight = MathUtils.clampFloat(minimumHeight, height, maximumHeight);

        // only update if we're actually changing size
        if (newWidth != getWidth() || newHeight != getHeight()) {
            // we want to allow zero width/height, best way it to fully override setSizeLocal
            Vertex[] v = getVerticesLocal();

            // As stated in the original comment of this method
            // "The scaling is done from the rectangles upper left corner".
            // This means that the position of the component changes if the size decreases, the position moves to the
            // left/top. If we move the position of the component correspondingly, the change will be reflected
            // properly.
            // The position will actually stay at the wanted location.
            Vector3D position = getPosition(TransformSpace.RELATIVE_TO_PARENT);
            boolean positionUpdated = false;

            // CHECKSTYLE:IGNORE MagicNumber FOR 14 LINES: Matrix points
            switch (getAnchor()) {
                case UPPER_RIGHT:
                    position.x += v[1].x - newWidth;
                    positionUpdated = true;
                    break;
                case LOWER_RIGHT:
                    position.x += v[1].x - newWidth;
                    position.y += v[3].y - newHeight;
                    positionUpdated = true;
                    break;
                case LOWER_LEFT:
                    position.y += v[3].y - newHeight;
                    positionUpdated = true;
                    break;
            }

            // Prevent updating the position if nothing has changed.
            if (positionUpdated) {
                setPositionRelativeToParent(position);
            }

            // CHECKSTYLE:IGNORE MagicNumber FOR 7 LINES: Matrix points
            // CHECKSTYLE:IGNORE LineLength FOR 6 LINES: Okay here
            setVertices(new Vertex[] {
                new Vertex(v[0].x, v[0].y, v[0].z, v[0].getTexCoordU(), v[0].getTexCoordV(), v[0].getR(), v[0].getG(),
                        v[0].getB(), v[0].getA()),
                new Vertex(v[0].x + newWidth, v[1].y, v[1].z, v[1].getTexCoordU(), v[1].getTexCoordV(), v[1].getR(),
                        v[1].getG(), v[1].getB(), v[1].getA()),
                new Vertex(v[0].x + newWidth, v[1].y + newHeight, v[2].z, v[2].getTexCoordU(), v[2].getTexCoordV(),
                        v[2].getR(), v[2].getG(), v[2].getB(), v[2].getA()),
                new Vertex(v[3].x, v[0].y + newHeight, v[3].z, v[3].getTexCoordU(), v[3].getTexCoordV(), v[3].getR(),
                        v[3].getG(), v[3].getB(), v[3].getA()),
                new Vertex(v[4].x, v[4].y, v[4].z, v[4].getTexCoordU(), v[4].getTexCoordV(), v[4].getR(), v[4].getG(),
                        v[4].getB(), v[4].getA()), });

            // Sets child clip, so elements are hidden
            setChildClip(newWidth, newHeight);
        }
    }

    /**
     * Enables/Disables the underline of the component.
     *
     * @param underlined True if you want it to be underlined, false to remove it
     */
    public void setUnderlined(boolean underlined) {
        if (underlined) {
            PImage underline = Icons.ICON_UNDERLINE;
            setTexture(underline);
            setNoFill(false);
        } else {
            setTexture(null);
            setNoFill(true);
        }
    }

    @Override
    public void setWidthLocal(float width) {
        setSizeLocal(width, getHeight());
    }

    /**
     * Translates the rectangle by the given coordinates.
     *
     * @param xDelta The x to translate by
     * @param yDelta The y to translate by
     */
    public void translate(float xDelta, float yDelta) {
        translate(new Vector3D(xDelta, yDelta));
    }

    /**
     * Tells this component to be updated and re-layouted recursively.
     */
    public void updateLayout() {
        if (hasLayout()) {
            layout.layout(this, LayoutUpdatePhase.FROM_CHILD);
            updateChildren();
        }
        updateParent();
    }

    /**
     * Tells all the children (if there are any) that this component has changed size.
     */
    public void updateChildren() {
        for (final MTComponent c : getChildren()) {
            if (c instanceof RamRectangleComponent) {
                ((RamRectangleComponent) c).parentResized();
            }
        }
    }

    /**
     * Tells the parent (if there is any) that this component has changed size.
     */
    public void updateParent() {
        if (containingComponent != null) {
            containingComponent.childResized(this);
        }
    }

    @Override
    public void highlight(MTColor color, HighlightEvent event) {

        if (event == HighlightEvent.VALIDATOR_EVENT) {
            setNoFill(true);
            return;
        }

        if (color == null) {
            setNoFill(true);
        } else {
            setNoFill(false);
            setFillColor(color);
        }
    }
}
