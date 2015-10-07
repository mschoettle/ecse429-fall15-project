package ca.mcgill.sel.ram.ui.views.containers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.mt4j.components.MTComponent;
import org.mt4j.util.MTColor;

import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.Traceable;
import ca.mcgill.sel.ram.WovenAspect;
import ca.mcgill.sel.ram.ui.components.RamExpendableComponent;
import ca.mcgill.sel.ram.ui.components.RamListComponent;
import ca.mcgill.sel.ram.ui.components.RamListComponent.Namer;
import ca.mcgill.sel.ram.ui.components.RamPanelComponent;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.components.RamTextComponent;
import ca.mcgill.sel.ram.ui.components.listeners.RamListListener;
import ca.mcgill.sel.ram.ui.layouts.HorizontalLayoutVerticallyCentered;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.utils.GraphicalUpdater;
import ca.mcgill.sel.ram.ui.utils.GraphicalUpdater.HighlightEvent;
import ca.mcgill.sel.ram.ui.utils.GraphicalUpdaterListener;
import ca.mcgill.sel.ram.ui.utils.Strings;

/**
 * Displays the hierarchy of extended aspects and allows to the user to highlight components from the extended aspect
 * selected.
 *
 * @author lmartellotto
 * @author ccamillieri
 */
public class TracingView extends RamPanelComponent {

    private static final int MAX_NUMBER_ELEMENTS_LIST = 8;

    private RamListComponent<WovenAspect> selector;
    /** Contains the list of first level WovenAspect in the hierarchy. */
    private List<WovenAspect> wovenAspects;

    /** Maintains the order of current selected woven aspects. */
    private LinkedList<WovenAspect> currentSelectedObjects;
    /** Maintains the order of current selected woven aspects indexes. */
    private LinkedList<Integer> currentSelectedIndexes;

    private GraphicalUpdater gu;

    /**
     * Basic Constructor.
     *
     * @param g The GUI updater.
     * @param a The aspect.
     */
    public TracingView(GraphicalUpdater g, Aspect a) {
        // CHECKSTYLE:IGNORE MagicNumber: Radius of the arc of corners
        super(5);

        gu = g;
        currentSelectedIndexes = new LinkedList<Integer>();
        currentSelectedObjects = new LinkedList<WovenAspect>();

        setLabel(Strings.LABEL_TRACING_VIEW);

        setPositionGlobal(getCorrectPosition(this, 0, getRenderer().getHeight(), true));

        initListWovenAspects(a);
        selector = createListWovenAspects(wovenAspects, true);

        setContent(selector);

        showContent(false);

        setVerticalAlign(VerticalStick.CENTER);
        setHorizontalAlign(HorizontalStick.LEFT);
    }

    /**
     * Creates a {@link RamListComponent} used to display the given wovenAspects.
     *
     * @param aspects the list of {@link WovenAspect} to add to the list
     * @param scrollable whether the list should be scrollable or not
     * @return the newly created {@link RamListComponent}
     */
    private RamListComponent<WovenAspect> createListWovenAspects(List<WovenAspect> aspects, boolean scrollable) {
        RamListComponent<WovenAspect> list =
                new RamListComponent<WovenAspect>(aspects, new InternalWovenAspectNamer(), scrollable);
        if (scrollable) {
            list.setMaxNumberOfElements(MAX_NUMBER_ELEMENTS_LIST);
            list.setScrollButtonColor(Colors.BACKGROUND_COLOR);
        }

        list.setNoFill(true);
        list.setNoStroke(true);
        list.registerListener(new TracingViewSelectorListener());
        return list;
    }

    /**
     * Creates a non scrollable {@link RamListComponent} used to display the given wovenAspects.
     *
     * @param aspects the list of {@link WovenAspect} to add to the list
     * @return the newly created {@link RamListComponent}
     */
    private RamListComponent<WovenAspect> createListWovenAspects(List<WovenAspect> aspects) {
        return createListWovenAspects(aspects, false);
    }

    /**
     * Initialize the list of {@link WovenAspect} with only the first-level in the hierarchy.
     *
     * @param a The root aspect.
     */
    private void initListWovenAspects(Aspect a) {
        wovenAspects = new ArrayList<WovenAspect>();
        for (WovenAspect wovenAspect : a.getWovenAspects()) {
            wovenAspects.add(wovenAspect);
        }
    }

    /**
     * Gets if the list is empty or not.
     *
     * @return if the list of woven aspects is empty.
     */
    public boolean isEmpty() {
        return wovenAspects.size() == 0;
    }

    @Override
    public void destroy() {
        // Unselect all
        updateElementSelected(null);

        // Remove all listeners for the graphical updater
        for (WovenAspect a : selector.getElementMap().keySet()) {
            RamRectangleComponent view = selector.getElementMap().get(a);
            gu.removeGUListener(a, view);
        }

        super.destroy();
    }

    /**
     * Updates the background and the call of listeners depending on the selected element.
     *
     * @param wa The selected element.
     */
    private void updateElementSelected(WovenAspect wa) {
        // if null, we unselect all the current selected
        if (wa == null) {
            for (WovenAspect waTemp : currentSelectedObjects) {
                int index = currentSelectedObjects.indexOf(waTemp);
                int colorIndex = currentSelectedIndexes.get(index);
                sendTracingEvent(waTemp, colorIndex, false);
            }
            return;
        }
        int rangeWa = currentSelectedObjects.indexOf(wa);

        if (rangeWa == -1 && currentSelectedObjects.size() == gu.getSizeTracingEventColors()) {
            // If the Woven Aspect is not selected yet, and all colors are used
            // We unselect the first Woven Aspect and use its color.
            WovenAspect toUnselect = currentSelectedObjects.pollFirst();
            int index = currentSelectedIndexes.pollFirst();

            sendTracingEvent(toUnselect, index, false);

            // Select
            currentSelectedObjects.add(wa);
            currentSelectedIndexes.add(index);
            sendTracingEvent(wa, index, true);

        } else if (rangeWa == -1) {
            // If the Woven Aspect is not selected yet, and there are unused colors
            // We use one of its unused color.

            // Find the index
            int index;
            for (index = 0; index < gu.getSizeTracingEventColors(); ++index) {
                if (!currentSelectedIndexes.contains(index)) {
                    break;
                }
            }

            // Select
            currentSelectedObjects.add(wa);
            currentSelectedIndexes.add(index);
            sendTracingEvent(wa, index, true);

        } else {
            // If the Woven Aspect is already selected, we unselect it.
            int index = currentSelectedIndexes.get(rangeWa);
            currentSelectedObjects.remove(rangeWa);
            currentSelectedIndexes.remove(rangeWa);

            sendTracingEvent(wa, index, false);
        }

    }

    /**
     * Send the tracing events corresponding to the given WovenAspect.
     *
     * @param wa the {@link WovenAspect} we have to highlight (or not)
     * @param index the index of the color to be used for highlighting
     * @param toHighlight whether we have to hightlight or revert to default color
     */
    private void sendTracingEvent(WovenAspect wa, int index, boolean toHighlight) {
        gu.tracingEvent(wa, index, toHighlight);
        for (Traceable obj : wa.getWovenElements()) {
            gu.tracingEvent(obj, index, toHighlight);
        }
    }

    /**
     * Element displayed in the list. Listens the {@link GraphicalUpdater} to update the background color.
     */
    private class WovenAspectComponent extends RamRectangleComponent implements GraphicalUpdaterListener {
        @Override
        public void highlight(MTColor color, HighlightEvent event) {
            MTColor fillColor =
                    (color != null && event == HighlightEvent.TRACING_EVENT) ? color
                            : Colors.DEFAULT_ELEMENT_FILL_COLOR;
            RamRectangleComponent parent = (RamRectangleComponent) this.getParent();
            if (parent == null || getBounds() == null) {
                // This probably means the component is not displayed because of the scrolling of the view list.
                return;
            }
            // If the grandparent is a RamExpendableComponent, it means the component is its title.
            if (parent.getParent() instanceof RamExpendableComponent) {
                // We color the whole title instead of just the component.
                parent.setFillColor(fillColor);
            } else {
                setFillColor(fillColor);
            }
        }
    }

    /**
     * A namer that is capable of displaying a hierarchy of {@link WovenAspect}. If the {@link WovenAspect} has no
     * child, just creates a {@link WovenAspectComponent} else, create an {@link RamExpendableComponent} with a
     * {@link WovenAspectComponent} as title and a {@link RamListComponent} with this same namer as hideable part of the
     * container Just the error message is displayed.
     */
    private class InternalWovenAspectNamer implements Namer<WovenAspect> {

        @Override
        public RamRectangleComponent getDisplayComponent(WovenAspect wovenAspect) {
            if (wovenAspect.getChildren() == null || wovenAspect.getChildren().isEmpty()) {
                // create a simple selectable component
                return createWovenAspectComponent(wovenAspect);
            }
            return createExpandableWovenAspectComponent(wovenAspect);
        }

        /**
         * Creates a {@link WovenAspectComponent} associated to the given {@link WovenAspect}. Registers it with the
         * {@link GraphicalUpdater} to be able to update the background color. By default the background is colored
         *
         * @param wovenAspect the {@link WovenAspect} to create the view for
         * @return the created {@link WovenAspectComponent}
         */
        private WovenAspectComponent createWovenAspectComponent(WovenAspect wovenAspect) {
            return createWovenAspectComponent(wovenAspect, false);
        }

        /**
         * Creates a {@link WovenAspectComponent} associated to the given {@link WovenAspect}. Registers it with the
         * {@link GraphicalUpdater} to be able to update the background color.
         *
         * @param wovenAspect the {@link WovenAspect} to create the view for
         * @param noFill whether the background shouldn't be colored or not
         * @return the created {@link WovenAspectComponent}
         */
        private WovenAspectComponent createWovenAspectComponent(WovenAspect wovenAspect, boolean noFill) {
            WovenAspectComponent view = new WovenAspectComponent();
            view.setLayout(new HorizontalLayoutVerticallyCentered());

            // Removes quotes of the message
            String message = wovenAspect.getName();
            RamTextComponent text = new RamTextComponent(message);
            text.setPickable(false);

            // Adds spaces in left and right
            final int space = 5;
            view.setBufferSize(Cardinal.WEST, space);
            view.setBufferSize(Cardinal.EAST, space);

            view.addChild(text);

            view.setNoFill(noFill);
            view.setFillColor(Colors.DEFAULT_ELEMENT_FILL_COLOR);
            view.setNoStroke(false);

            // Registers to the GraphicalUpdater to updates the background color
            gu.addGUListener(wovenAspect, view);
            return view;
        }

        /**
         * Creates a {@link RamExpendableComponent} associated to the given {@link WovenAspect}. The
         * {@link RamExpendableComponent}'s title is a {@link WovenAspectComponent} Its hideable content is a
         * {@link RamListComponent} of the children {@link WovenAspect}.
         *
         * @param wovenAspect the {@link WovenAspect} to create the view for
         * @return the created {@link RamExpendableComponent}
         */
        private RamExpendableComponent createExpandableWovenAspectComponent(WovenAspect wovenAspect) {
            WovenAspectComponent title = createWovenAspectComponent(wovenAspect, true);
            RamListComponent<WovenAspect> content = createListWovenAspects(wovenAspect.getChildren());
            RamExpendableComponent component = new RamExpendableComponent(title, content);
            component.setFillColor(Colors.DEFAULT_ELEMENT_FILL_COLOR);
            return component;
        }

        @Override
        public String getSortingName(WovenAspect e) {
            // Aspects without children will be displayed first
            String hasChildren = e.getChildren().isEmpty() ? "0" : "1";
            // TODO temp fix to ensure having unique names for each element. (Remove once #351 has been fixed)
            return hasChildren + e.getName() + e.hashCode();
        }

        @Override
        public String getSearchingName(WovenAspect e) {
            return e.getName();
        }

    }

    /**
     * Selects or unselects the selected element.
     */
    private class TracingViewSelectorListener implements RamListListener<WovenAspect> {

        @Override
        public void elementSelected(RamListComponent<WovenAspect> list, WovenAspect element) {
            updateElementSelected(element);
        }

        @Override
        public void elementDoubleClicked(RamListComponent<WovenAspect> list, WovenAspect element) {
            // Do nothing
        }

        @Override
        public void elementHeld(RamListComponent<WovenAspect> list, WovenAspect element) {
            // Do nothing
        }
    }

    @Override
    protected void handleChildResized(MTComponent component) {
        super.handleChildResized(component);
        // stick to the edges if children are resized
        setPositionGlobal(getCorrectPosition(this, getX(), getY(), true));
    }

}
