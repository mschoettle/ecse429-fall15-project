package ca.mcgill.sel.ram.ui.layouts;

import org.mt4j.components.MTComponent;
import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent.Cardinal;

/**
 * A list Layout that lays children out vertically and sets all the widths to be identical.
 * The width and height of the component will be resized to fit all children.
 *
 * @author vbonnet
 */
public class VerticalLayout extends AbstractBaseLayout {

    private float padding;
    private HorizontalAlignment horizontalAlignment;
    private VerticalAlignment verticalAlignment;

    /**
     * Creates a new vertical layout with no padding and left alignment.
     */
    public VerticalLayout() {
        this(0);
    }

    /**
     * Creates a new vertical layout with the given padding and horizontal alignment.
     *
     * @param padding the padding to use for each component
     */
    public VerticalLayout(float padding) {
        this(padding, HorizontalAlignment.LEFT);
    }

    /**
     * Creates a new vertical layout with no padding and the given horizontal alignment.
     *
     * @param alignment the alignment each component should have
     */
    public VerticalLayout(HorizontalAlignment alignment) {
        this(0, alignment);
    }

    /**
     * Creates a new vertical layout with the given padding and horizontal alignment.
     *
     * @param padding the padding to use for each component
     * @param alignment the alignment each component should have
     */
    public VerticalLayout(float padding, HorizontalAlignment alignment) {
        this.padding = padding;
        this.horizontalAlignment = alignment;
        this.verticalAlignment = VerticalAlignment.TOP;
    }

    @Override
    public void layout(final RamRectangleComponent component, LayoutUpdatePhase updatePhase) {

        float maxChildWidth = 0;
        float totalHeight = 0;

        // determine max width and total height
        for (final MTComponent c : component.getChildren()) {
            if (!(c instanceof RamRectangleComponent)) {
                continue;
            }
            final RamRectangleComponent child = (RamRectangleComponent) c;

            child.minimizeSize();
            maxChildWidth = Math.max(maxChildWidth, child.getWidth());
            totalHeight += child.getHeight() + padding;
        }

        if (component.getChildCount() > 0) {
            totalHeight -= padding;
        }

        float contentWidth;
        float contentHeight;

        // resize ourselves, we need to be big enough to include all elements
        // otherwise, use the current space to stretch children
        if (updatePhase == LayoutUpdatePhase.FROM_CHILD && !getForceToKeepSize()) {

            contentWidth = maxChildWidth;
            contentHeight = totalHeight;

            final float width = component.getBufferSize(Cardinal.WEST) + contentWidth
                    + component.getBufferSize(Cardinal.EAST);
            final float height = component.getBufferSize(Cardinal.NORTH) + contentHeight
                    + component.getBufferSize(Cardinal.SOUTH);
            setMinimumSize(component, width, height);

        } else {
            contentWidth = component.getChildWidth();
            contentHeight = component.getChildHeight();
        }

        // vertical alignment
        float previousY = component.getBufferSize(Cardinal.NORTH);
        switch (verticalAlignment) {
            case MIDDLE:
                previousY += (contentHeight - totalHeight) / 2f;
                break;
            case BOTTOM:
                previousY += contentHeight - totalHeight;
                break;
        }

        // move and resize each child as necessary
        for (final MTComponent c : component.getChildren()) {
            if (!(c instanceof RamRectangleComponent)) {
                continue;
            }
            final RamRectangleComponent child = (RamRectangleComponent) c;

            // shift down to be flush with the bottom of the previous child
            // child.setHeightLocal(child.getMinimumHeight());
            float newX = component.getBufferSize(Cardinal.WEST);

            if (!child.autoMaximizes()) {
                switch (horizontalAlignment) {
                    case CENTER:
                        newX = newX + ((contentWidth - child.getWidth()) / 2f);
                        break;
                    case RIGHT:
                        newX = newX + contentWidth - child.getWidth();
                        break;
                }
            } else {
                child.setWidthLocal(contentWidth);
            }

            if (newX < 0) {
                newX = 0;
            }

            child.setPositionRelativeToParent(new Vector3D(newX, previousY));

            // previous becomes the bottom of this element
            previousY += child.getHeight() + padding;
        }
    }

    /**
     * Sets the vertical alignment of the layout.
     *
     * @param alignment The vertical alignment
     */
    public void setVerticalAlignment(VerticalAlignment alignment) {
        this.verticalAlignment = alignment;
    }
}
