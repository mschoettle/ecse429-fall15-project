package ca.mcgill.sel.ram.ui.components;

import java.util.HashSet;
import java.util.Set;

import org.mt4j.util.MTColor;
import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.ram.ui.components.listeners.RamExpendableListener;
import ca.mcgill.sel.ram.ui.events.listeners.ActionListener;
import ca.mcgill.sel.ram.ui.layouts.HorizontalLayoutVerticallyCentered;
import ca.mcgill.sel.ram.ui.layouts.VerticalLayout;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.utils.Fonts;
import ca.mcgill.sel.ram.ui.utils.Icons;

/**
 * This component contains a title and a container that contains a hideable RamRectangleComponent.
 *
 * @author Romain
 */
public class RamExpendableComponent extends RamRectangleComponent implements ActionListener {
    private static final int DEFAULT_ICON_SIZE = Fonts.FONTSIZE_INSTANTIATION + 2;

    /**
     * The size of the indentation.
     */
    private static final int DEFAULT_HIDEABLE_COMPONENT_WEST_BUFFER_SIZE = 27;

    private static final String ACTION_INSTANTIATION_SHOW_MAPPING_DETAILS = "view.expendable.showDetails";
    private static final String ACTION_INSTANTIATION_HIDE_MAPPING_DETAILS = "view.expendable.hideDetails";

    /**
     * Contains the listener of this list.
     */
    protected Set<RamExpendableListener> listeners;

    /**
     * Button container for expanding or collapsing button. These expand and collapse buttons will be used to show/hide
     * the RamRectangleComponent of the ExpendableComponent.
     */

    /**
     * Button to show RamRectangleComponent of the ExpendableComponent.
     */
    private RamButton buttonExpand;

    /**
     * Button to hide RamRectangleComponent of the ExpendableComponent.
     */
    private RamButton buttonCollapse;

    /**
     * Represent the first line. It contains the Expand/Collapse buttons and the RamRectangleComponent of the title.
     */
    private RamRectangleComponent firstLine;

    /**
     * The title of the ExpendableComponent.
     */
    private RamRectangleComponent myTitle;

    /**
     * This container will contains the myHideableComponent.
     */
    private RamRectangleComponent hideableContainer;

    /**
     * We will show/hide this container by adding/removing it as a child of hideableContainer. It keeps all the mapping
     * related containers.
     */
    private RamRectangleComponent myHideableComponent;

    /**
     * Create an ExpendableComponent.
     */
    protected RamExpendableComponent() {
        this(DEFAULT_HIDEABLE_COMPONENT_WEST_BUFFER_SIZE, DEFAULT_ICON_SIZE);
    }

    /**
     * Create an ExpendableComponent.
     *
     * @param bufferSize - Size of the buffer for the hideable container.
     * @param buttonsSize - Size of the expand and collapse buttons.
     */
    protected RamExpendableComponent(float bufferSize, float buttonsSize) {
        listeners = new HashSet<RamExpendableListener>();

        // CHECKSTYLE:IGNORE MagicNumber FOR 3 LINES: Otherwise, the buttons will be stuck to the stroke
        firstLine = new RamRectangleComponent(new HorizontalLayoutVerticallyCentered(5f));
        firstLine.setBuffers(0);
        firstLine.setBufferSize(Cardinal.WEST, 3);

        firstLine.setNoFill(false);
        firstLine.setFillColor(Colors.DEFAULT_ELEMENT_FILL_COLOR);
        firstLine.setNoStroke(false);

        // This will be invisible and we will put expand/collapse buttons
        // inside of this container depending on the situation
        // expandCollapseButtonsContainer = new RamRectangleComponent(new HorizontalLayout());
        // expandCollapseButtonsContainer.setNoStroke(false);
        // expandCollapseButtonsContainer.setStrokeColor(MTColor.RED);

        // firstLine.addChild(expandCollapseButtonsContainer);

        // Expand button
        RamImageComponent expandImage = new RamImageComponent(Icons.ICON_EXPAND,
                Colors.TRIANGLE_EXPAND_COLLAPSE_FILL_COLOR);
        expandImage.setMinimumSize(buttonsSize, buttonsSize);
        expandImage.setMaximumSize(buttonsSize, buttonsSize);
        buttonExpand = new RamButton(expandImage);
        buttonExpand.setActionCommand(ACTION_INSTANTIATION_SHOW_MAPPING_DETAILS);
        buttonExpand.addActionListener(this);

        // Collapse button
        RamImageComponent collapseImage = new RamImageComponent(Icons.ICON_COLLAPSE,
                Colors.TRIANGLE_EXPAND_COLLAPSE_FILL_COLOR);
        collapseImage.setMinimumSize(buttonsSize, buttonsSize);
        collapseImage.setMaximumSize(buttonsSize, buttonsSize);
        buttonCollapse = new RamButton(collapseImage);
        buttonCollapse.setActionCommand(ACTION_INSTANTIATION_HIDE_MAPPING_DETAILS);
        buttonCollapse.addActionListener(this);

        firstLine.addChild(0, buttonExpand);

        this.addChild(firstLine, true);

        hideableContainer = new RamRectangleComponent(new VerticalLayout());
        hideableContainer.setNoFill(true);

        hideableContainer.setBuffers(0);
        hideableContainer.setBufferSize(Cardinal.WEST, bufferSize);

        this.addChild(hideableContainer);

        setNoStroke(false);
        setBuffers(0);
        setLayout(new VerticalLayout());

    }

    /**
     * Create a RamExpandableComponent with these parameters.
     *
     * @param titleRectangle Component to display for the title, if null, a default one will be created.
     * @param hideableComponent Component that will be hide/display when you click on the button.
     */
    public RamExpendableComponent(RamRectangleComponent titleRectangle, RamRectangleComponent hideableComponent) {
        this();
        setTitle(titleRectangle);
        myHideableComponent = hideableComponent;
    }

    /**
     * Create a RamExpandableComponent with these parameters.
     *
     * @param title string to display
     * @param hideableComponent Component that will be hide/display when you click on the button.
     */
    public RamExpendableComponent(String title, RamRectangleComponent hideableComponent) {
        this(buildTitle(title), hideableComponent);
    }

    /**
     * Set the title of this component. A default display component will be created.
     *
     * @param title string to display
     */
    public void setTitle(String title) {
        this.setTitle(buildTitle(title));
    }

    /**
     * Set the title of this component.
     *
     * If titleRectangle is null, it will throw a IllegalArgumentException.
     *
     * @param titleRectangle display component
     */
    public void setTitle(RamRectangleComponent titleRectangle) {
        if (titleRectangle == null) {
            throw new IllegalArgumentException("Display component can not be null");
        }

        // The first line will always contains at most two children
        if (firstLine.getChildCount() == 2) {
            firstLine.getChildbyID(firstLine.getChildCount() - 1).destroy();
        }

        // disable stroke
        titleRectangle.setNoStroke(true);

        // set the title
        myTitle = titleRectangle;
        firstLine.addChild(myTitle, true);
    }

    /**
     * Set the content of this component.
     *
     * If hideableComponent is null, it will throw a IllegalArgumentException.
     *
     * @param hideableComponent display component
     */
    public void setHideableComponent(RamRectangleComponent hideableComponent) {
        if (hideableComponent == null) {
            throw new IllegalArgumentException("Display component can not be null");
        }
        if (myHideableComponent != null) {
            hideHideableComponent();
        }
        this.myHideableComponent = hideableComponent;
    }

    /**
     * Builds a row for selected element in order to display it after.
     *
     * @param element The selected element.
     * @return The RamRectangleComponent associated to the element.
     */
    private static RamRectangleComponent buildTitle(String element) {
        RamTextComponent view = new RamTextComponent(element);
        view.setNoFill(true);
        view.setNoStroke(true);
        return view;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String actionCommand = event.getActionCommand();

        if (ACTION_INSTANTIATION_SHOW_MAPPING_DETAILS.equals(actionCommand)) {
            this.showHideableComponent();
        } else if (ACTION_INSTANTIATION_HIDE_MAPPING_DETAILS.equals(actionCommand)) {
            this.hideHideableComponent();
        }
    }

    /**
     * Shows hideableComponent.
     */
    public void showHideableComponent() {
        hideableContainer.addChild(myHideableComponent, true);
        firstLine.removeChild(buttonExpand);
        firstLine.addChild(0, buttonCollapse, true);
        for (RamExpendableListener listener : listeners) {
            listener.elementExpanded(this);
        }
    }

    /**
     * Hide hideableComponent.
     */
    public void hideHideableComponent() {
        hideableContainer.removeChild(myHideableComponent);
        firstLine.removeChild(buttonCollapse);
        firstLine.addChild(0, buttonExpand, true);
        for (RamExpendableListener listener : listeners) {
            listener.elementCollapsed(this);
        }
    }

    /**
     * Set fill color for the title bar.
     *
     * @param color - The color to set
     */
    public void setTitleFillColor(MTColor color) {
        firstLine.setFillColor(color);
    }

    /**
     * Set whether to fill or not title bar.
     *
     * @param noFill - fill or not title bar.
     */
    public void setTitleNoFill(boolean noFill) {
        firstLine.setNoFill(noFill);
    }

    /**
     * Set whether to have a stroke for the title bar.
     *
     * @param noStroke - stroke or not for title bar.
     */
    public void setTitleNoStroke(boolean noStroke) {
        firstLine.setNoStroke(noStroke);
    }

    @Override
    public MTColor getFillColor() {
        return firstLine.getFillColor();
    }

    @Override
    public boolean containsPointGlobal(Vector3D testPoint) {
        return firstLine.containsPointGlobal(testPoint);
    }

    /**
     * Registers a listener for selection events.
     *
     * @param listener The listener.
     */
    public void registerListener(RamExpendableListener listener) {
        listeners.add(listener);
    }

    /**
     * Unregisters the listener.
     *
     * @param listener The listener to unregister.
     */
    public void unregisterListener(RamExpendableListener listener) {
        listeners.remove(listener);
    }

    /**
     * Return the first line of this {@link RamExpendableComponent}.
     *
     * @return the first line of this {@link RamExpendableComponent}.
     */
    public RamRectangleComponent getFirstLine() {
        return firstLine;
    }

    /**
     * Return the hideable container of this {@link RamExpendableComponent}.
     *
     * @return the the hideable container of this {@link RamExpendableComponent}.
     */
    public RamRectangleComponent getHideableContainer() {
        return hideableContainer;
    }

    /**
     * Sets the visibility.
     *
     * @param visibility the new visibility
     */
    public void setIconsVisible(boolean visibility) {
        // Remove the button. Otherwise, it will take some places even if set Visibility to false.
        if (visibility) {
            this.showHideableComponent();

            if (firstLine.containsChild(buttonExpand)) {
                firstLine.removeChild(buttonExpand);
            }

            if (!firstLine.containsChild(buttonCollapse)) {
                firstLine.addChild(0, buttonCollapse, true);
            }
        } else {
            this.hideHideableComponent();

            if (firstLine.containsChild(buttonCollapse)) {
                firstLine.removeChild(buttonCollapse);
            } else if (firstLine.containsChild(buttonExpand)) {
                firstLine.removeChild(buttonExpand);
            }
        }
    }
}
