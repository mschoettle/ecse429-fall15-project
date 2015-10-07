package ca.mcgill.sel.ram.ui.components;

import java.util.HashSet;
import java.util.Set;

import org.mt4j.components.MTComponent;
import org.mt4j.components.TransformSpace;
import org.mt4j.components.interfaces.IMTComponent3D;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragEvent;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapProcessor;
import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.listeners.RamPanelListener;
import ca.mcgill.sel.ram.ui.events.DelayedDrag;
import ca.mcgill.sel.ram.ui.events.listeners.ActionListener;
import ca.mcgill.sel.ram.ui.events.listeners.ITapListener;
import ca.mcgill.sel.ram.ui.layouts.HorizontalLayoutVerticallyCentered;
import ca.mcgill.sel.ram.ui.layouts.VerticalLayout;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.utils.Constants;
import ca.mcgill.sel.ram.ui.utils.Fonts;
import ca.mcgill.sel.ram.ui.utils.Icons;
import ca.mcgill.sel.ram.ui.utils.MathUtils;
import ca.mcgill.sel.ram.ui.views.handler.BaseHandler;

/**
 * Component which cans be closed thanks to the close button in the header.
 * The header can be customized with other information.
 * The component can be dragged but not outside the screen.
 * 
 * @author lmartellotto
 */
public class RamPanelComponent extends RamRoundedRectangleComponent {

    private static final int ICON_SIZE = 25;

    private static final float PADDING_HEADER = 8;
    private static final float DEFAULT_WIDTH_SPACER_HEADER = 40;

    /** Tolerated distance if the component is not stuck on side to re-stick it on side. */
    private static final float STICK_TOLERANCE = 20;

    /** Component in the top of the panel, containing the close button. */
    protected RamRectangleComponent header;
    /** Component containing the main content of the panel. */
    protected RamRectangleComponent content;

    private HorizontalStick hStick;
    private VerticalStick vStick;

    private Set<RamPanelListener> listeners;
    private RamTextComponent label;
    private RamButton closeButton;

    /**
     * Initializes the panel with close button.
     * 
     * @param arcRadius The arc radius.
     */
    public RamPanelComponent(int arcRadius) {
        super(arcRadius);

        listeners = new HashSet<RamPanelListener>();

        setLayout(new VerticalLayout());
        setNoFill(false);
        setFillColor(Colors.TRANSPARENT_DARK_GREY);

        header = new RamRectangleComponent(new HeaderLayout());

        // Gesture Listener for tap and drag events
        header.addGestureListener(TapProcessor.class, new InternalTopHandler());
        header.registerInputProcessor(new TapProcessor(RamApp.getApplication()));
        header.addGestureListener(DragProcessor.class, new PanelDragAction(this));
        header.registerInputProcessor(new DragProcessor(RamApp.getApplication()));

        label = new RamTextComponent("");
        label.setFont(Fonts.PANEL_COMPONENT_TITLE_FONT);
        label.setPickable(false);
        header.addChild(label);

        RamSpacerComponent spacer = new RamSpacerComponent(DEFAULT_WIDTH_SPACER_HEADER, 1);
        spacer.setPickable(false);
        header.addChild(spacer);

        RamImageComponent closeImage = new RamImageComponent(Icons.ICON_DELETE, Colors.ICON_CLOSE_COLOR,
                ICON_SIZE, ICON_SIZE);
        closeButton = new RamButton(closeImage);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                panelClosed();
            }
        });
        header.addChild(closeButton);

        addChild(header);

        addGestureListener(DragProcessor.class, new PanelDragAction());
        registerInputProcessor(new DragProcessor(RamApp.getApplication()));

        // Bug if the anchor is UPPER_LEFT. So the anchor is set to CENTER.
        setAnchor(PositionAnchor.CENTER);
    }

    @Override
    public void destroy() {
        // Have to delete the content manually in case it is not displayed
        content.destroy();

        super.destroy();
    }

    /**
     * Add a component in the header, before the close button. Re-layouts after.
     * 
     * @param comp The component to add.
     */
    protected void addHeaderComponent(MTComponent comp) {
        addHeaderComponent(comp, true);
    }

    /**
     * Add a component in the header, before the close button.
     * 
     * @param comp The component to add.
     * @param toUpdate Specifies if the method should update the entire hierarchy to re-layout itself.
     */
    protected void addHeaderComponent(MTComponent comp, boolean toUpdate) {
        int nbComponents = header.getChildCount();

        // Fixed components = the closed button and the spacer.
        int nbFixedComponents = 2;
        header.addChild(nbComponents - nbFixedComponents, comp, toUpdate);
    }

    /**
     * Add a component in the header, before the close button.
     * 
     * @param index The index.
     * @param comp The component to add.
     * @param toUpdate Specifies if the method should update the entire hierarchy to re-layout itself.
     */
    protected void addHeaderComponent(int index, MTComponent comp, boolean toUpdate) {
        header.addChild(index, comp, toUpdate);
    }

    /**
     * Sets the label string of the panel.
     * 
     * @param lab the new label.
     */
    protected void setLabel(String lab) {
        label.setText(lab);
    }

    /**
     * Sets the content of the panel.
     * 
     * @param comp The content.
     */
    protected void setContent(MTComponent comp) {
        if (containsChild(content)) {
            removeChild(content);
            content.destroy();
        }

        content = new RamRectangleComponent(new VerticalLayout());
        content.addChild(comp);
    }

    /**
     * Shows the content.
     * 
     * @param toShow To show or not.
     */
    protected void showContent(boolean toShow) {
        if (content == null) {
            return;
        }

        if (toShow && !containsChild(content)) {
            addChild(content);
        }

        if (!toShow && containsChild(content)) {
            removeChild(content);
        }
    }

    /**
     * Call the action to do when the close button is selected.
     */
    private void panelClosed() {
        for (RamPanelListener listener : listeners) {
            listener.panelClosed(this);
        }
        destroy();
    }

    /**
     * Computes and returns the correct position (must be in the current scene)
     * depends on the desired x and y params.
     * 
     * @param obj The object for what the positions must be checked.
     * @param x The desired x.
     * @param y The desired y.
     * @param toStick True if the component must be stuck to the desired sides
     *            (when the list is updated or scrolled)
     *            false otherwise (when the component is dragged).
     * @return The correct x and y.
     */
    protected Vector3D getCorrectPosition(RamRectangleComponent obj, float x, float y, boolean toStick) {

        float newX = x;
        float newY = y;

        newX = MathUtils.clampFloat(0, x, getRenderer().getWidth() - obj.getWidth());
        newY = MathUtils.clampFloat(Constants.MENU_BAR_HEIGHT, y, getRenderer().getHeight() - obj.getHeight());

        if (toStick && getHorizontalAlign() == HorizontalStick.RIGHT) {
            newX = getRenderer().getWidth() - obj.getWidth();
        }

        if (toStick && getVerticalAlign() == VerticalStick.BOTTOM) {
            newY = getRenderer().getHeight() - obj.getHeight();
        }

        return new Vector3D(newX, newY);
    }

    @Override
    public void setPositionGlobal(Vector3D position) {
        // make sure it doesn't go off-screen
        Vector3D correctPosition = getCorrectPosition(this, position.x, position.y, false);
        // Because the anchor is CENTER.
        float x = correctPosition.x + getWidth() / 2;
        // Make it doesn't go off-screen
        super.setPositionGlobal(new Vector3D(x, correctPosition.y));
    }

    @Override
    protected void handleChildResized(MTComponent component) {
        super.handleChildResized(component);
        if (component.getParent() != null) {
            // will check if it goes out of screen because of the resize
            setPositionGlobal(new Vector3D(getX(), getY()));
        }
    }

    /**
     * Registers a listener for close event.
     * 
     * @param listener listener.
     */
    public void registerListener(RamPanelListener listener) {
        listeners.add(listener);
    }

    /**
     * Gets the horizontal alignment.
     * 
     * @return the hAlign
     */
    public HorizontalStick getHorizontalAlign() {
        return hStick;
    }

    /**
     * Sets the horizontal alignment.
     * 
     * @param h the hAlign to set
     */
    public void setHorizontalAlign(HorizontalStick h) {
        this.hStick = h;
    }

    /**
     * Gets the vertical alignment.
     * 
     * @return the vAlign
     */
    public VerticalStick getVerticalAlign() {
        return vStick;
    }

    /**
     * Sets the vertical alignment.
     * 
     * @param v the vAlign to set
     */
    public void setVerticalAlign(VerticalStick v) {
        this.vStick = v;
    }

    /**
     * If the displayed component is stuck on the right/left or none of both (center).
     */
    public enum HorizontalStick {
        /** Right position. */
        RIGHT,
        /** Center position. */
        CENTER,
        /** Left position. */
        LEFT
    }

    /**
     * If the displayed component is stuck on the top/bottom or none of both (center).
     */
    public enum VerticalStick {
        /** Top position. */
        TOP,
        /** Center position. */
        CENTER,
        /** Bottom position. */
        BOTTOM
    }

    /**
     * Layout class of the header.
     * Sets the close button to the right.
     */
    private class HeaderLayout extends HorizontalLayoutVerticallyCentered {

        /**
         * Constructs a layout with the global padding.
         */
        public HeaderLayout() {
            super(PADDING_HEADER);
        }

        @Override
        public void layout(RamRectangleComponent component, LayoutUpdatePhase updatePhase) {

            super.layout(component, updatePhase);

            if (closeButton != null) {
                Vector3D newPosition = closeButton.getPosition(TransformSpace.RELATIVE_TO_PARENT);
                newPosition.setX(header.getWidth() - header.getBufferSize(Cardinal.EAST) - closeButton.getWidth());
                closeButton.setPositionRelativeToParent(newPosition);
            }
        }
    }

    /**
     * Limits the drag events:
     * Forbids the drag outside the screen and so allows to keep the component in the current scene.
     */
    private class PanelDragAction extends DelayedDrag {

        private static final float DISTANCE_BEFORE_DRAG = 15;

        /**
         * Constructor.
         * 
         * @param panel The target.
         */
        public PanelDragAction(RamPanelComponent panel) {
            super(panel, DISTANCE_BEFORE_DRAG);
        }

        /**
         * Constructor.
         */
        public PanelDragAction() {
            super(DISTANCE_BEFORE_DRAG);
        }

        @Override
        protected void translate(IMTComponent3D comp, DragEvent de) {

            // Gets the translated point from the current drag event
            Vector3D afterDrag = new Vector3D(getX(), getY());
            afterDrag.transformDirectionVector(getParent().getGlobalInverseMatrix());
            afterDrag.translate(de.getTranslationVect());

            // Check if the translated point is correct
            Vector3D correctAfterDrag =
                    getCorrectPosition(RamPanelComponent.this, afterDrag.getX(), afterDrag.getY(), false);
            Vector3D current = new Vector3D(getX(), getY());

            DragEvent newD = new DragEvent(de.getSource(), de.getId(),
                    de.getTarget(), de.getDragCursor(), current,
                    correctAfterDrag);

            super.translate(comp, newD);

            // Updates desired position for future scrolling and opening/closing view
            // Only if there is a drag action
            if (wasDragPerformed()) {
                setHorizontalAlign(HorizontalStick.CENTER);
                setVerticalAlign(VerticalStick.CENTER);
                if (getX() <= STICK_TOLERANCE) {
                    setHorizontalAlign(HorizontalStick.LEFT);
                } else if (getX() + getWidth() >= getRenderer().getWidth() - STICK_TOLERANCE) {
                    setHorizontalAlign(HorizontalStick.RIGHT);
                }

                if (getY() <= STICK_TOLERANCE) {
                    setVerticalAlign(VerticalStick.TOP);
                } else if (getY() + getHeight() >= getRenderer().getHeight() - STICK_TOLERANCE) {
                    setVerticalAlign(VerticalStick.BOTTOM);
                }
            }
        }
    }

    /**
     * Handler for the top of the window to open/close the selector.
     */
    private class InternalTopHandler extends BaseHandler implements ITapListener {

        @Override
        public boolean processTapEvent(TapEvent tapEvent) {
            if (tapEvent.isTapped()) {
                if (content == null) {
                    return true;
                }

                if (containsChild(content)) {
                    removeChild(content);

                } else {
                    addChild(content);
                }
                setPositionGlobal(getCorrectPosition(RamPanelComponent.this, getX(), getY(), true));
                return true;
            }
            return false;
        }
    }

    /**
     * Shows the close Button.
     * 
     * @param toShow - Parameter passed to determine whether the close button is to be shown.
     */
    protected void showCloseButton(boolean toShow) {
        if (toShow) {
            header.addChild(closeButton);
        } else {
            header.removeChild(closeButton);
        }
    }
}
