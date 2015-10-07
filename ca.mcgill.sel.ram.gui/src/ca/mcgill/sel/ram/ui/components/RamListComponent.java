package ca.mcgill.sel.ram.ui.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.mt4j.components.MTComponent;
import org.mt4j.input.gestureAction.TapAndHoldVisualizer;
import org.mt4j.input.inputProcessors.IGestureEventListener;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.AbstractComponentProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragEvent;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.tapAndHoldProcessor.TapAndHoldEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapAndHoldProcessor.TapAndHoldProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapProcessor;
import org.mt4j.util.MTColor;
import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.commons.StringUtil;
import ca.mcgill.sel.core.CORENamedElement;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.listeners.RamListListener;
import ca.mcgill.sel.ram.ui.events.DelayedDrag;
import ca.mcgill.sel.ram.ui.events.MouseWheelProcessor;
import ca.mcgill.sel.ram.ui.events.WheelEvent;
import ca.mcgill.sel.ram.ui.events.listeners.IDragListener;
import ca.mcgill.sel.ram.ui.events.listeners.ITapAndHoldListener;
import ca.mcgill.sel.ram.ui.events.listeners.ITapListener;
import ca.mcgill.sel.ram.ui.layouts.HorizontalLayout;
import ca.mcgill.sel.ram.ui.layouts.HorizontalLayoutAllCentered;
import ca.mcgill.sel.ram.ui.layouts.VerticalLayout;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.utils.Constants;
import ca.mcgill.sel.ram.ui.utils.Fonts;
import ca.mcgill.sel.ram.ui.utils.LoggerUtils;
import ca.mcgill.sel.ram.ui.utils.MathUtils;
import ca.mcgill.sel.ram.ui.views.handler.BaseHandler;
import ca.mcgill.sel.ram.ui.views.handler.IHandled;

/**
 * A component that show a List of any Object. If no namer is given, a default {@link RamTextComponent} will be created.
 * This list handle scrolling. The maximum height will be calculated using the field "maxNumberOfElements"
 *
 * You can listen to {@link ca.mcgill.sel.ram.ui.components.listeners.RamListListener} to be notified when the user
 * click (or double click) on a element of this list.
 *
 * @author Romain
 *
 * @param <T> The type of objects you want to put in this list
 */
public class RamListComponent<T> extends RamRectangleComponent implements IHandled<IGestureEventListener> {
    private static final float MINIMUM_WIDTH = 150;
    private static final long TIME_BEFORE_BOUNCING = 100;
    private static final float DELTA_EPSILON_HEIGHT = 0.1f;

    /** The map of all elements of the list associated to its RamRectangleComponent to display. */
    protected Map<T, RamRectangleComponent> elementMap;

    /** The map of all elements of the list associated to its sorting name. Each sorting name is unique. */
    protected Map<T, String> sortingNameMap;

    /**
     * Contains the listener of this list.
     */
    protected Set<RamListListener<T>> listeners;

    /**
     * Comparator used to sort elements of the list.
     */
    protected Comparator<T> elementsOrder = new InternalComparator();

    /**
     * This field is used to allow the user to display only the elements which start with this string.
     */
    protected String searchString;
    /**
     * This field is used to deselect an element in the operation "deselectElement". This color will be set to
     * DEFAULT_COLOR or, if the Namer retrieve an element with a FillColor, it will be set to this color.
     */
    protected MTColor defaultColor;

    /** The main sorted list of all elements corresponding to the search. */
    private List<RamRectangleComponent> componentsList;

    /** The displayed list (with a max number of elements) which is a sublist of the main list. */
    private RamRectangleComponent displayedComponentsList;

    /** The mask which hide the top and bottom of the displayedList to add fluidity for the scroll. */
    private RamRectangleComponent maskComponents;

    /** The layout of the maskComponents. */
    private ScrollListLayout scrollListLayout;

    /**
     * The max number of elements to display.
     */
    private int maxNumberOfElements = 10;

    /**
     * Comparator used to sort elements of the list.
     */
    private class InternalComparator implements Comparator<T> {

        @Override
        public int compare(T o1, T o2) {
            int result = 0;

            if (o1 != o2) {
                String name1 = sortingNameMap.get(o1);
                String name2 = sortingNameMap.get(o2);

                if (name1 == null && name2 == null) {
                    result = 0;
                } else if (name1 == null) {
                    result = -1;
                } else if (name2 == null) {
                    result = 1;
                } else {
                    result = String.CASE_INSENSITIVE_ORDER.compare(name1, name2);
                }
            }

            return result;
        }

    }

    /**
     * Hander for the list which handles {@link DragEvent}, {@link TapEvent} and {@link WheelEvent}.
     *
     */
    private class InternalScrollableListHandler extends BaseHandler implements IDragListener, ITapListener,
            ITapAndHoldListener {

        private DelayedDrag dragAction = new DelayedDrag(Constants.DELAYED_DRAG_MIN_DRAG_DISTANCE);

        @Override
        public boolean processDragEvent(DragEvent dragEvent) {
            if (!isScrollable || !isDragAvailable || !isScrollNeeded) {
                return false;
            }

            if (dragEvent.getId() == DragEvent.GESTURE_ENDED) {
                needToBounce = true;
            }

            isUpScroll = dragEvent.getTranslationVect().getY() > 0;

            // restrict the dragging to one axis depending on the layout
            if (getLayout() instanceof HorizontalLayout) {
                dragEvent.getTranslationVect().setY(0);
            } else if (getLayout() instanceof VerticalLayout) {
                dragEvent.getTranslationVect().setX(0);
            }

            // Restricts the scroll beyond the top or bottom limit
            // CHECKSTYLE:IGNORE MagicNumber: The limit is set to the size of an element minus a margin of error
            float limitScroll = (maskComponents.getHeight() / maxNumberOfElements) - 10;
            if (displayedComponentsList.getChildCount() > 1 && componentsList.size() > 1) {
                RamRectangleComponent firstDisplayed =
                        (RamRectangleComponent) displayedComponentsList.getChildByIndex(0);
                RamRectangleComponent fst = componentsList.get(0);

                RamRectangleComponent lastDisplayed =
                        (RamRectangleComponent) displayedComponentsList.getChildByIndex(displayedComponentsList
                                .getChildCount() - 1);
                RamRectangleComponent last = componentsList.get(componentsList.size() - 1);

                if ((firstDisplayed == fst && firstDisplayed.getY() > maskComponents.getY() + limitScroll && isUpScroll)
                        || (lastDisplayed == last
                                && lastDisplayed.getY() + lastDisplayed.getHeight() < maskComponents.getY()
                                        + maskComponents.getHeight() - limitScroll
                                && !isUpScroll)) {
                    dragEvent.getTranslationVect().setY(0);
                }
            }

            // Restricts the scroll so the element clicked doesn't go out of the display list.
            // If the element go out, it will be removed of the child of the displayedComponentsList and it will lead to
            // a bug (The lock of the cursor in mt4j would not be release so we will not receive event anymore).
            // So we need to check that the "from" is in the maskComponents.
            if (maskComponents.containsPointGlobal(dragEvent.getFrom())
                    && dragEvent.getFrom().getY() >= maskComponents.getY()
                    && dragEvent.getFrom().getY() <= maskComponents.getY() + maskComponents.getHeight()
                    && dragEvent.getFrom().getX() >= maskComponents.getX()
                    && dragEvent.getFrom().getX() <= maskComponents.getX() + maskComponents.getWidth()) {

                dragEvent.setTarget(displayedComponentsList);
                dragAction.processGestureEvent(dragEvent);
                scrollBottomButton.sendToFront();
                scrollTopButton.sendToFront();

                updateDragging();
                return true;
            }

            return false;
        }

        @Override
        public boolean processTapEvent(TapEvent tapEvent) {
            if (tapEvent.isDoubleTap()) {
                if (maskComponents.containsPointGlobal(tapEvent.getLocationOnScreen())
                        && tapEvent.getLocationOnScreen().getY() > maskComponents.getY()
                        && tapEvent.getLocationOnScreen().getY() < maskComponents.getY() + maskComponents.getHeight()) {

                    for (Entry<T, RamRectangleComponent> element : elementMap.entrySet()) {
                        if (element.getValue().containsPointGlobal(tapEvent.getLocationOnScreen())) {
                            selectElementDoubleClick(element.getKey());

                            return true;
                        }
                    }
                }
            }

            if (tapEvent.isTapped()) {
                if (maskComponents.containsPointGlobal(tapEvent.getLocationOnScreen())
                        && tapEvent.getLocationOnScreen().getY() > maskComponents.getY()
                        && tapEvent.getLocationOnScreen().getY() < maskComponents.getY() + maskComponents.getHeight()) {

                    for (Entry<T, RamRectangleComponent> element : elementMap.entrySet()) {
                        if (element.getValue().containsPointGlobal(tapEvent.getLocationOnScreen())) {
                            selectElement(element.getKey());

                            return true;
                        }
                    }
                }

            }

            return false;
        }

        /**
         * Handles a {@link WheelEvent} caused by a mouse wheel rotation. Scroll this list components.
         *
         * @param wheelEvent the {@link WheelEvent}
         * @return true, if the event was handled, false otherwise
         */
        protected boolean processWheelEvent(WheelEvent wheelEvent) {

            if (!isScrollable || !isScrollNeeded
                    || !displayedComponentsList.containsPointGlobal(wheelEvent.getLocationOnScreen())) {
                return false;
            }

            float scale = wheelEvent.getScale();

            // Computation of "from" and "to" points
            Vector3D from =
                    new Vector3D(wheelEvent.getLocationOnScreen().getX(), wheelEvent.getLocationOnScreen().getY());

            Vector3D to = null;
            // restrict the dragging to one axis depending on the layout
            if (getLayout() instanceof HorizontalLayout) {
                to = new Vector3D(from.getX() * (2 - scale), 0);
            } else if (getLayout() instanceof VerticalLayout) {
                to = new Vector3D(0, from.getY() * (2 - scale));
            }

            DragEvent dragEvent =
                    new DragEvent(wheelEvent.getSource(), MTGestureEvent.GESTURE_STARTED, wheelEvent.getTarget(), null,
                            from, to);
            if (processDragEvent(dragEvent)) {
                needToBounce = true;
                updateDragging();
                lastWheelEvent = System.currentTimeMillis();

                return true;
            }

            return false;
        }

        @Override
        public boolean processGestureEvent(MTGestureEvent gestureEvent) {
            if (gestureEvent instanceof WheelEvent) {
                if (isDragAvailable) {
                    WheelEvent wheelEvent = (WheelEvent) gestureEvent;
                    dragAction.processGestureEvent(wheelEvent);
                    return processWheelEvent(wheelEvent);
                }
            }
            return super.processGestureEvent(gestureEvent);
        }

        @Override
        public boolean processTapAndHoldEvent(TapAndHoldEvent tapAndHoldEvent) {
            if (tapAndHoldEvent.isHoldComplete()) {
                if (maskComponents.containsPointGlobal(tapAndHoldEvent.getLocationOnScreen())
                        && tapAndHoldEvent.getLocationOnScreen().getY() > maskComponents.getY()
                        && tapAndHoldEvent.getLocationOnScreen().getY() < maskComponents.getY()
                                + maskComponents.getHeight()) {

                    for (Entry<T, RamRectangleComponent> element : elementMap.entrySet()) {
                        if (element.getValue().containsPointGlobal(tapAndHoldEvent.getLocationOnScreen())) {
                            selectElementTapAndHold(element.getKey());

                            return true;
                        }
                    }
                }

            }
            return false;
        }
    }

    /**
     * A layout that adaptively adjusts its width but keeps a maximum height.
     */
    private class ScrollListLayout extends VerticalLayout {

        private float maximumHeight;

        /**
         * Sets the maximum height to the layout.
         *
         * @param height The maximum height.
         */
        public void setMaximumHeight(float height) {
            this.maximumHeight = height;
        }

        /**
         * Get the maximum height of this layout.
         *
         * @return the maximum height
         */
        public float getMaximumHeight() {
            return maximumHeight;
        }

        @Override
        public void layout(RamRectangleComponent component, LayoutUpdatePhase updatePhase) {
            super.layout(component, updatePhase);

            float width = displayedComponentsList.getWidth();
            float height = Math.min(maximumHeight, displayedComponentsList.getHeight());

            if (updatePhase == LayoutUpdatePhase.FROM_PARENT) {
                scrollBottomButton.setPositionGlobal(new Vector3D(maskComponents.getX(), maskComponents.getY()
                        + maskComponents.getHeight() - scrollBottomButton.getHeight()));

                scrollTopButton.setPositionRelativeToParent(Vector3D.ZERO_VECTOR);

                scrollTopButton.setWidthLocal(width + 1);
                scrollBottomButton.setWidthLocal(width + 1);
            } else if (updatePhase == LayoutUpdatePhase.FROM_CHILD) {
                setMinimumSize(component, width, height);
            }
        }
    }

    /**
     * Determines the sort and display names for an element. This allows users to select elements by typing the actual
     * name while allowing the list to display more information about the element. The original use case for this was a
     * list for RAM {@link Class}s; I wanted partial classes to show up as |[name] but still let users choose the class
     * by typing in name instead of |name.
     *
     * @author vbonnet
     * @param <T> The type of the elements to be named.
     */
    public interface Namer<T> {
        /**
         * Gets the element to display.
         *
         * @param element The element to display.
         * @return The name of the for the text field row to create.
         */
        RamRectangleComponent getDisplayComponent(T element);

        /**
         * Gets the string used to sort the list.
         *
         * @param element The element to sort on.
         * @return The String to be used for sorting.
         */
        String getSortingName(T element);

        /**
         * Gets the string used to search.
         *
         * @param element The element to be searched.
         * @return The String to be used for searching.
         */
        String getSearchingName(T element);
    }

    private Namer<T> namer;
    private IGestureEventListener handler;

    private RamButton scrollTopButton;
    private RamButton scrollBottomButton;
    private boolean isDragAvailable;
    private boolean needToBounce;
    private long lastWheelEvent;
    private boolean isUpScroll;
    private boolean isScrollNeeded;
    private boolean isUpdatingDragging;
    private boolean isScrollable;

    /**
     * Creates a list of components. This list can be scrollable.
     *
     * @param isScrollable true if the list must be scrollable, false otherwise
     */
    protected RamListComponent(boolean isScrollable) {
        super();
        elementMap = new TreeMap<T, RamRectangleComponent>(elementsOrder);
        sortingNameMap = new HashMap<T, String>();

        listeners = new HashSet<RamListListener<T>>();

        searchString = "";
        defaultColor = Colors.DEFAULT_ELEMENT_FILL_COLOR;

        isDragAvailable = true;
        this.isScrollable = isScrollable;

        componentsList = new ArrayList<RamRectangleComponent>();

        maskComponents = new RamRectangleComponent();
        displayedComponentsList = new RamRectangleComponent() {
            @Override
            protected void handleChildResized(MTComponent component) {
                if (RamListComponent.this.isScrollable && component.getParent() != null && !isUpdatingDragging) {
                    isUpdatingDragging = true;

                    handleElementSizeDecreased();
                    if (displayedComponentsList.getChildHeight() > scrollListLayout.getMaximumHeight()) {
                        handleElementSizeIncreased();
                    }

                    isUpdatingDragging = false;
                }
            }
        };
        displayedComponentsList.setLayout(new VerticalLayout());
        displayedComponentsList.setMinimumWidth(MINIMUM_WIDTH);

        maskComponents.addChild(displayedComponentsList);

        final int arrowHeight = 18;
        final float bufferSize = -4;

        scrollTopButton = new RamButton("\u25B2", 1);
        scrollTopButton.setLayout(new HorizontalLayoutAllCentered());
        scrollTopButton.setFont(Fonts.SCROLL_ARROW_FONT);
        scrollTopButton.setBuffers(0);
        scrollTopButton.setBufferSize(Cardinal.NORTH, bufferSize);
        scrollTopButton.setHeightLocal(arrowHeight);
        scrollTopButton.getNameField().setBuffers(1);
        scrollTopButton.setVisible(false);
        maskComponents.addChild(scrollTopButton);

        scrollBottomButton = new RamButton("\u25BC", 1);
        scrollBottomButton.setLayout(new HorizontalLayoutAllCentered());
        scrollBottomButton.setFont(Fonts.SCROLL_ARROW_FONT);
        scrollBottomButton.setBuffers(0);
        scrollBottomButton.setBufferSize(Cardinal.NORTH, bufferSize);
        scrollBottomButton.setHeightLocal(arrowHeight);
        scrollBottomButton.getNameField().setBuffers(1);
        scrollBottomButton.setVisible(false);
        setScrollButtonColor(Colors.LIST_SCROLL_BUTTON_COLOR);
        maskComponents.addChild(scrollBottomButton);

        this.scrollListLayout = new ScrollListLayout();
        this.maskComponents.setLayout(this.scrollListLayout);

        addChild(maskComponents);

        super.setLayout(new VerticalLayout());

        needToBounce = false;
        isUpScroll = false;
        isScrollNeeded = false;
        isUpdatingDragging = false;
        lastWheelEvent = System.currentTimeMillis();

        initGestureListeners();
    }

    /**
     * Creates a scrollable list class for the given elements. The names (both for sorting and displaying) are determine
     * by element.toString()
     *
     * @param elements The elements which can be selected.
     * @param isScrollable true if the list must be scrollable, false otherwise
     */
    public RamListComponent(List<T> elements, boolean isScrollable) {
        this(elements, null, isScrollable);
    }

    /**
     * Creates an empty scrollable list with the given namer.
     *
     * @param namer The namer to use in displaying elements.
     * @param isScrollable true if the list must be scrollable, false otherwise
     */
    public RamListComponent(Namer<T> namer, boolean isScrollable) {
        this(isScrollable);
        setNamer(namer);
    }

    /**
     * Creates a scrollable list class for the given elements.
     *
     * @param elements The elements which can be selected.
     * @param namer Determines the sorting and display names for the elements.
     * @param isScrollable true if the list must be scrollable, false otherwise
     */
    public RamListComponent(List<T> elements, Namer<T> namer, boolean isScrollable) {
        this(namer, isScrollable);
        setElements(elements);
    }

    /**
     * Creates a scrollable list class for the given elements. The names (both for sorting and displaying) are
     * determine
     * by element.toString()
     *
     * @param elements The elements which can be selected.
     */
    public RamListComponent(List<T> elements) {
        this(elements, null, false);
    }

    /**
     * Creates an empty scrollable list with the given namer.
     *
     * @param namer The namer to use in displaying elements.
     */
    public RamListComponent(Namer<T> namer) {
        this(namer, false);
    }

    /**
     * Creates a scrollable list class for the given elements.
     *
     * @param elements The elements which can be selected.
     * @param namer Determines the sorting and display names for the elements.
     */
    public RamListComponent(List<T> elements, Namer<T> namer) {
        this(elements, namer, false);
    }

    @Override
    public void destroy() {

        // invoke later in order to make sure that it gets executed after all events have been handled
        // otherwise in certain circumstances it is possible that Mt4j tries to process an event even though
        // the component has been already destroyed
        // more specific: this fixes a bug where on Windows the drag event of the list gets processed after tap (due
        // to ordering the list of registered processors
        // differently). Since everything is destroyed in ComponentInputProcessorSupport line 73 an exception occurs
        // because the list is empty.
        RamApp.getApplication().invokeLater(new Runnable() {
            @Override
            public void run() {
                for (RamRectangleComponent element : elementMap.values()) {
                    element.destroy();
                }
                elementMap.clear();
                sortingNameMap.clear();
                listeners.clear();
                componentsList.clear();
                displayedComponentsList.removeAllChildren();
                RamListComponent.super.destroy();
            }
        });
    }

    /**
     * Initialize the gestureListeners.
     */
    protected void initGestureListeners() {
        setHandler(new InternalScrollableListHandler());
    }

    /**
     * Adds the given elements to the list. Removes any already existing elements from the list.
     *
     * @param elements the elements to be added to the list
     */
    public void setElements(List<T> elements) {
        fillList(elements);
    }

    /**
     * Adds the given elements to the list. Removes any already existing elements from the list.
     *
     * @param elements the elements to be added to the list
     */
    protected synchronized void fillList(List<T> elements) {
        // remove already existing elements
        for (Entry<T, RamRectangleComponent> element : new HashSet<Map.Entry<T, RamRectangleComponent>>(
                elementMap.entrySet())) {
            removeElement(element.getKey(), false);
        }

        elementMap.clear();
        sortingNameMap.clear();

        // add the elements
        for (T element : elements) {
            addElement(element, false);
        }

        displayElements();
    }

    /**
     * Adds the element to the list.
     *
     * @param element The element to add.
     */
    public void addElement(T element) {
        if (getSearchingName(element) == null) {
            return;
        }
        addElement(element, true);
    }

    /**
     * Adds element to the list.
     *
     * @param element The element to add
     * @param update If the layout has to be updated
     */
    private void addElement(T element, boolean update) {
        RamRectangleComponent view = buildRowFor(element);

        Collection<String> names = this.sortingNameMap.values();
        names.remove(element);

        String sortingName = StringUtil.createUniqueName(getSortingName(element), names);
        // the map uses comparator to compare (and sort) values
        // the sorting name of the element is used
        // and if one class name exists multiple times only the last one is taken
        // this leads to a view created but never used
        // TODO: make sure this never happens (depends on the comparator of the map)
        try {
            sortingNameMap.put(element, sortingName);

            if (elementMap.containsKey(element)) {
                LoggerUtils.error("RamListComponent: key (" + sortingNameMap.get(element)
                        + ") of map already in the map. The list can't have the same object twice.");
            } else {
                elementMap.put(element, view);
            }
            if (update) {
                displayElements();
            }

            if (getWidth() > view.getWidth()) {
                view.setMinimumWidth(getWidth());
            }
        } catch (NullPointerException npe) {
            LoggerUtils.error("Given element '" + element + "' is null and was not added.");
            view.destroy();
        }
    }

    /**
     * Builds a row for element in order to display it after.
     *
     * @param element The selected element.
     * @return The RamRectangleComponent associated to the element.
     */
    private RamRectangleComponent buildRowFor(T element) {
        if (namer == null) {
            RamTextComponent view = null;
            String name = null;

            if (element instanceof CORENamedElement) {
                name = ((CORENamedElement) element).getName();
            }

            if (name != null) {
                view = new RamTextComponent(name);
            } else {
                view = new RamTextComponent(element.toString());
            }

            view.setNoFill(false);
            view.setFillColor(defaultColor);
            view.setNoStroke(false);
            return view;
        } else {
            RamRectangleComponent rect = namer.getDisplayComponent(element);
            MTColor rectColor = rect.getFillColor();
            if (rectColor != null) {
                defaultColor = rect.getFillColor();
            }
            return rect;
        }
    }

    /**
     * Removes the element from the list's list.
     *
     * @param element The element to remove.
     * @return true if the element was contained, and thus removed, false otherwise
     */
    public boolean removeElement(T element) {
        return removeElement(element, true);
    }

    /**
     * Removes the element among the list.
     *
     * @param element The element.
     * @param update If the layout has to update or not.
     * @return true if the element was contained, and thus removed, false otherwise
     */
    private boolean removeElement(T element, boolean update) {
        RamRectangleComponent view = elementMap.remove(element);
        sortingNameMap.remove(element);

        if (view != null) {
            removeChild(view, false);
            view.destroy();
            if (update) {
                displayElements();
            }
            return true;
        }
        return false;
    }

    /**
     * Displays the list of components. This operation used element which match with the searchString field. By default
     * this searchString is equal to empty string.
     */
    protected void displayElements() {
        displayedComponentsList.removeAllChildren();
        componentsList.clear();

        // convert key set into list (in order to make it possible to sort)
        List<T> keys = new ArrayList<T>(elementMap.keySet());
        Collections.sort(keys, elementsOrder);

        // To remove any buffer size if the displayed list is updated
        displayedComponentsList.setBufferSize(Cardinal.NORTH, 0);

        int range = 0;
        float height = 0;

        for (T key : keys) {
            if (getSearchingName(key).toLowerCase().startsWith(this.searchString.toLowerCase())) {
                if (!isScrollable || range < maxNumberOfElements) {
                    displayedComponentsList.addChild(elementMap.get(key), false);
                    height += elementMap.get(key).getHeight();
                } else if (range == maxNumberOfElements) {
                    displayedComponentsList.addChild(elementMap.get(key), false);
                }
                componentsList.add(elementMap.get(key));
                range++;
            }
        }

        // adjust the max height if there were not as much elements as the max number in the list
        // (useful if the elements are RamExependables, and thus can change their height).
        float updatedHeight = height;
        if (range > 0 && range < maxNumberOfElements) {
            updatedHeight *= maxNumberOfElements / range;
        }
        // Manually update the list
        scrollListLayout.setMaximumHeight(updatedHeight);
        displayedComponentsList.updateLayout();

        if (isScrollable) {
            // Manually update the list with padding
            // The previous update is mandatory : the elements have to be layouted before computing the current height.
            if (range > 0) {
                RamRectangleComponent first = (RamRectangleComponent) displayedComponentsList.getChildByIndex(0);
                RamRectangleComponent last = null;

                if (range > maxNumberOfElements) {
                    last =
                            (RamRectangleComponent) displayedComponentsList.getChildByIndex(displayedComponentsList
                                    .getChildCount() - 2);
                } else {
                    last =
                            (RamRectangleComponent) displayedComponentsList.getChildByIndex(displayedComponentsList
                                    .getChildCount() - 1);
                }

                float heigthWithPadding = (last.getY() + last.getHeight()) - (first.getY());
                if (Math.abs(height - heigthWithPadding) > DELTA_EPSILON_HEIGHT) {
                    scrollListLayout.setMaximumHeight(heigthWithPadding * (((float) maxNumberOfElements) / range));
                    displayedComponentsList.updateLayout();
                }
            }
        }

        updateScrollButtons();

        // TODO add an item if empty?
    }

    /**
     * Gets the element map.
     *
     * @return The element map.
     */
    public Map<T, RamRectangleComponent> getElementMap() {
        return elementMap;
    }

    /**
     * Gets the string used to sort the list.
     *
     * @param element The element to sort on.
     * @return The String to be used for sorting.
     */
    protected String getSortingName(T element) {
        String result = null;
        if (namer == null) {
            if (element instanceof CORENamedElement) {
                result = ((CORENamedElement) element).getName();
            }
        } else {
            result = namer.getSortingName(element);
        }

        if (result == null) {
            result = element.toString();
        }

        return result;
    }

    /**
     * Gets the string used to search on the list.
     *
     * @param element The element to be searched.
     * @return The String to be used for searching.
     */
    protected String getSearchingName(T element) {
        String result = null;
        if (namer == null) {
            if (element instanceof CORENamedElement) {
                result = ((CORENamedElement) element).getName();
            }
        } else {
            result = namer.getSearchingName(element);
        }

        if (result == null) {
            result = element.toString();
        }

        return result;
    }

    /**
     * Get the string used when we want to display some elements according to a string.
     *
     * @return the search string
     */
    public String getSearchString() {
        return searchString;
    }

    /**
     * Set the string used when we want to display some elements according to this string.
     *
     * @param searchString the search string used in displayElement
     */
    public void setSearchString(String searchString) {
        this.searchString = searchString;
        displayElements();
    }

    /**
     * Sets a new way to sort the list.
     *
     * @param elementsOrder The elementsOrder to set.
     */
    public void setElementsOrder(Comparator<T> elementsOrder) {
        this.elementsOrder = elementsOrder;
        Collection<T> elements = elementMap.keySet();
        elementMap = new TreeMap<T, RamRectangleComponent>(elementsOrder);
        setElements(new LinkedList<T>(elements));
        displayElements();
    }

    /**
     * Sets the map of elements.
     *
     * @param someMap The map of elements of the list.
     */
    public void setMap(Map<T, RamRectangleComponent> someMap) {
        someMap.putAll(this.elementMap);
        this.elementMap = someMap;
    }

    /**
     * Set if this list has to be scrollable or not.
     *
     * @param isScrollable true if the list must be scrollable, false otherwise
     */
    public void setIsScrollable(boolean isScrollable) {
        this.isScrollable = isScrollable;
        displayElements();
    }

    /**
     * Get the namer to be used for naming components.
     *
     * @return the namer to be used
     */
    public Namer<T> getNamer() {
        return namer;
    }

    /**
     * Sets the namer to be used for naming components.
     *
     * @param namer the namer to be used
     */
    public void setNamer(Namer<T> namer) {
        this.namer = namer;
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return The number of elements in the list.
     */
    public int size() {
        return elementMap.size();
    }

    /**
     * Retrieve the display Component of this element.
     *
     * @param key the element
     * @return the display component
     */
    public RamRectangleComponent getDisplayComponent(T key) {
        return elementMap.get(key);
    }

    /**
     * Fill color of the display component of this element with the color given in parameter.
     *
     * @param key the element
     * @param color the color used
     */
    public void selectElement(T key, MTColor color) {
        this.getDisplayComponent(key).setFillColor(color);
    }

    /**
     * Fill color of the display component of this element with the default color.
     *
     * @param key the element
     */
    public void deselectElement(T key) {
        this.getDisplayComponent(key).setFillColor(defaultColor);
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
     * Registers a listener for selection events.
     *
     * @param listener The listener.
     */
    public void registerListener(RamListListener<T> listener) {
        listeners.add(listener);
    }

    /**
     * Unregisters the listener.
     *
     * @param listener The listener to unregister.
     */
    public void unregisterListener(RamListListener<T> listener) {
        listeners.remove(listener);
    }

    /**
     * Call all the listener that an element has been clicked in this list.
     *
     * @param element The clicked element.
     */
    protected void selectElement(T element) {
        for (RamListListener<T> listener : listeners) {
            listener.elementSelected(this, element);
        }
    }

    /**
     * Call all the listener that an element has been double clicked in this list.
     *
     * @param element The double clicked element.
     */
    protected void selectElementDoubleClick(T element) {
        for (RamListListener<T> listener : listeners) {
            listener.elementDoubleClicked(this, element);
        }
    }

    /**
     * Call all the listener that an element has been held in this list.
     *
     * @param element The held element.
     */
    protected void selectElementTapAndHold(T element) {
        for (RamListListener<T> listener : listeners) {
            listener.elementHeld(this, element);
        }
    }

    @Override
    public void setMaximumSize(float width, float height) {
        displayedComponentsList.setMaximumSize(width, height);
    }

    /**
     * Sets the new max number of displayed elements in this list.
     *
     * @param n The number of elements.
     */
    public void setMaxNumberOfElements(int n) {
        maxNumberOfElements = n;
        // TODO If setMaximumnumber then shall we setIsScrollable to true ?

        // update everything
        displayElements();
    }

    /**
     * Sets the new color to the scroll buttons. If color is null, sets no color.
     *
     * @param color The new color. If null, the buttons will have no color.
     */
    public void setScrollButtonColor(MTColor color) {
        if (color == null) {
            scrollBottomButton.setNoFill(true);
            scrollTopButton.setNoFill(true);
        } else {
            scrollBottomButton.setBackgroundColor(color);
            scrollTopButton.setBackgroundColor(color);
        }
    }

    /**
     * Registers the listener for the selected gestures.
     *
     * @param listener The listener to gesture events.
     */
    protected void registerGestureListeners(IGestureEventListener listener) {
        addGestureListener(DragProcessor.class, listener);
        addGestureListener(TapProcessor.class, listener);
        addGestureListener(MouseWheelProcessor.class, listener);
    }

    @Override
    protected void registerInputProcessors() {
        registerInputProcessor(new DragProcessor(RamApp.getApplication()));
        registerInputProcessor(new TapProcessor(RamApp.getApplication(), Constants.TAP_MAX_FINGER_UP, true,
                Constants.TAP_DOUBLE_TAP_TIME));

        registerInputProcessor(new MouseWheelProcessor(RamApp.getApplication()));

        // enable event propagation to parents
        for (AbstractComponentProcessor processor : getInputProcessors()) {
            processor.setBubbledEventsEnabled(true);
        }
    }

    /**
     * Register the tap and hold processor for this {@link RamListComponent} using the handler provide by setHandler
     * operation.
     */
    public void registerTapAndHoldProcessor() {
        if (handler != null) {
            addGestureListener(TapAndHoldProcessor.class, this.handler);
            addGestureListener(TapAndHoldProcessor.class,
                    new TapAndHoldVisualizer(RamApp.getApplication(), getParent()));
            registerInputProcessor(new TapAndHoldProcessor(RamApp.getApplication(),
                    Constants.TAP_AND_HOLD_DURATION));
        }
    }

    @Override
    public synchronized void updateComponent(long timeDelta) {
        super.updateComponent(timeDelta);

        // Bounce back the components
        if (needToBounce && lastWheelEvent + TIME_BEFORE_BOUNCING < System.currentTimeMillis()) {

            if (componentsList.size() < 1 && displayedComponentsList.getChildCount() < 1) {
                return;
            }

            RamRectangleComponent firstDisplayed = (RamRectangleComponent) displayedComponentsList.getChildByIndex(0);
            RamRectangleComponent firstComponent = componentsList.get(0);

            RamRectangleComponent lastDisplayed =
                    (RamRectangleComponent) displayedComponentsList.getChildByIndex(displayedComponentsList
                            .getChildCount() - 1);
            RamRectangleComponent lastComponent = componentsList.get(componentsList.size() - 1);

            // Bounce back the components
            // when the first element of main list is displayed (top of the list)
            // or when the last element of main list is displayed (bottom of the list)
            boolean isFirstElementDisplayed =
                    firstComponent == firstDisplayed && firstDisplayed.getY() > maskComponents.getY();

            boolean isLastElementDisplayed =
                    lastComponent == lastDisplayed
                            && (lastDisplayed.getY() + lastDisplayed.getHeight() < maskComponents.getY()
                                    + maskComponents.getHeight());

            if (!(isFirstElementDisplayed || isLastElementDisplayed)) {
                needToBounce = false;
                return;
            }

            doBounce();

            updateScrollButtons();
        }
    }

    /**
     * If the scroll is to far up/down or right/left, translate to the boundary.
     */
    private void doBounce() {
        // TODO vbonnet: document what the hell is going on here.
        Vector3D first = null;
        RamRectangleComponent last = null;
        if (displayedComponentsList.getChildCount() > 0) {
            first = MathUtils.getUpperLeftFromComponent(displayedComponentsList.getChildByIndex(0));
            last =
                    (RamRectangleComponent) displayedComponentsList.getChildByIndex(displayedComponentsList
                            .getChildCount() - 1);
        }

        Vector3D transVect = null;
        // interpretation by mschoettle: if the components are too far left or right,
        // in this part the position to be translated to is figured out
        if (displayedComponentsList.getLayout() instanceof HorizontalLayout) {
            if (first != null && displayedComponentsList.getXForChildren() - first.x < 1) {
                transVect = new Vector3D(maskComponents.getX(), maskComponents.getY()).subtractLocal(first);
            } else if (last != null
                    && last.getX() - displayedComponentsList.getXForChildren()
                            + displayedComponentsList.getChildWidth() - last.getWidth() < 1) {
                Vector3D position = MathUtils.getUpperLeftFromComponent(last);
                transVect =
                        new Vector3D(maskComponents.getX() + maskComponents.getWidth() - last.getWidth(),
                                maskComponents.getY()).subtractLocal(position);
            }
        } else if (displayedComponentsList.getLayout() instanceof VerticalLayout) {
            if (first != null && displayedComponentsList.getYForChildren() > maskComponents.getYForChildren()) {

                transVect = new Vector3D(maskComponents.getX(), maskComponents.getY()).subtractLocal(first);
            } else if (last != null
                    && displayedComponentsList.getY() < maskComponents.getY() + maskComponents.getHeight()
                            - displayedComponentsList.getHeight()) {

                Vector3D position = MathUtils.getUpperLeftFromComponent(last);
                transVect =
                        new Vector3D(maskComponents.getX(), maskComponents.getY() + maskComponents.getHeight()
                                - last.getHeight()).subtractLocal(position);
            }
        }

        // interpretation by mschoettle:
        // here the translation vector will be limited in order to have some kind of
        // animation that moves the components smoothly (since
        // updateComponent is called continuously)
        if (transVect != null) {
            // only translate if there is actually something to do
            if (!transVect.equalsVector(new Vector3D(0, 0, 0))) {
                transVect = transVect.limitLocal((float) Math.sqrt(transVect.length()));
                displayedComponentsList.translate(transVect);
            }
        }
    }

    /**
     * Updates the list of components when the list is dragging. Adds one component on the top or bottom when the drag
     * is enough.
     */
    private void updateDragging() {

        isUpdatingDragging = true;

        // If there are other elements not displayed
        if (displayedComponentsList.getChildHeight() >= scrollListLayout.getMaximumHeight()) {
            int countDisplayedComponents = displayedComponentsList.getChildCount();

            RamRectangleComponent firstDisplayed = (RamRectangleComponent) displayedComponentsList.getChildByIndex(0);
            RamRectangleComponent first = componentsList.get(0);

            RamRectangleComponent lastDisplayed =
                    (RamRectangleComponent) displayedComponentsList.getChildByIndex(countDisplayedComponents - 1);
            RamRectangleComponent last = componentsList.get(componentsList.size() - 1);

            RamRectangleComponent previous = (firstDisplayed != first)
                    ? componentsList.get(componentsList.indexOf(firstDisplayed) - 1)
                    : null;

            RamRectangleComponent next = (lastDisplayed != last)
                    ? componentsList.get(componentsList.indexOf(lastDisplayed) + 1)
                    : null;

            if (isUpScroll) {
                boolean removeBottom =
                        firstDisplayed != first
                                && lastDisplayed.getY() > this.maskComponents.getY()
                                        + this.scrollListLayout.getMaximumHeight();
                boolean addTop = previous != null && firstDisplayed.getY() > maskComponents.getY();

                if (removeBottom && addTop) {
                    // Remove last children
                    this.removeChildToDisplayList(displayedComponentsList.getChildCount() - 1, false);

                    // Add previous child to the top of the display list
                    this.addChildToDisplayList(0, previous, true);

                    this.displayedComponentsList.setPositionRelativeToParent(new Vector3D(0, previous.getHeight()
                            * (-1)));
                } else if (removeBottom) {
                    // Remove last children
                    this.removeChildToDisplayList(displayedComponentsList.getChildCount() - 1, true);
                } else if (addTop) {
                    // Add previous child to the top of the display list
                    this.addChildToDisplayList(0, previous, true);
                    this.displayedComponentsList.setPositionRelativeToParent(new Vector3D(0, previous.getHeight()
                            * (-1)));
                }
            } else {
                boolean addBottom =
                        next != null
                                && lastDisplayed.getY() + lastDisplayed.getHeight() < this.maskComponents.getY()
                                        + this.scrollListLayout.getMaximumHeight();
                boolean removeTop = firstDisplayed.getY() + firstDisplayed.getHeight() < maskComponents.getY();
                if (addBottom && removeTop) {
                    // Remove first children
                    this.removeChildToDisplayList(0, false);
                    this.displayedComponentsList.setPositionRelativeToParent(Vector3D.ZERO_VECTOR);

                    firstDisplayed = (RamRectangleComponent) displayedComponentsList.getChildByIndex(0);

                    // Add next child to the end of the display list
                    this.addChildToDisplayList(displayedComponentsList.getChildCount(), next, true);
                } else if (removeTop) {
                    // Remove first children
                    this.removeChildToDisplayList(0, true);
                    this.displayedComponentsList.setPositionRelativeToParent(Vector3D.ZERO_VECTOR);
                } else if (addBottom) {
                    // Add next child to the end of the display list
                    this.addChildToDisplayList(displayedComponentsList.getChildCount(), next, true);
                }
            }

            updateScrollButtons();
        }

        isUpdatingDragging = false;
    }

    /**
     * Add a child to the display list.
     *
     * @param pos the position where the child will be added
     * @param child the child to add
     * @param update if the layout has to be updated after the insertion
     */
    private void addChildToDisplayList(int pos, RamRectangleComponent child, boolean update) {
        displayedComponentsList.addChild(pos, child, update);
    }

    /**
     * Remove a child from the display list.
     *
     * @param position the position where the child will be removed
     * @param update if the layout has to be updated after the deletion
     */
    private void removeChildToDisplayList(int position, boolean update) {
        RamRectangleComponent child = (RamRectangleComponent) displayedComponentsList.getChildByIndex(position);
        // We don't want to remove a child if the final height of the displayedComponentsList will be inferior than the
        // maximum height
        if (displayedComponentsList.getChildHeight() - child.getHeight() >= scrollListLayout.getMaximumHeight()) {
            displayedComponentsList.removeChild(position, update);
        }
    }

    /**
     * Update the visibility of the scroll indicators.
     */
    private void updateScrollButtons() {
        if (displayedComponentsList.getChildCount() == 0) {
            return;
        }

        RamRectangleComponent firstDisplayed = (RamRectangleComponent) displayedComponentsList.getChildByIndex(0);
        RamRectangleComponent first = componentsList.get(0);

        RamRectangleComponent lastDisplayed =
                (RamRectangleComponent) displayedComponentsList
                        .getChildByIndex(displayedComponentsList.getChildCount() - 1);
        RamRectangleComponent last = componentsList.get(componentsList.size() - 1);

        boolean topVisible = false;
        boolean bottomVisible = false;

        topVisible =
                (firstDisplayed != first)
                        || ((firstDisplayed == first) && firstDisplayed.getY() < maskComponents.getY());

        bottomVisible =
                (lastDisplayed != last)
                        || ((lastDisplayed == last) && lastDisplayed.getY() + lastDisplayed.getHeight() > maskComponents
                                .getY()
                                + maskComponents.getChildHeight());

        isScrollNeeded = topVisible || bottomVisible;

        scrollTopButton.setVisible(topVisible);
        scrollBottomButton.setVisible(bottomVisible);
    }

    /**
     * Handle the list if the size of an element increased.
     */
    private void handleElementSizeIncreased() {
        float maskComponentsYBottom =
                maskComponents.getY() + this.scrollListLayout.getMaximumHeight()
                        - displayedComponentsList.getBufferSize(Cardinal.NORTH);
        RamRectangleComponent lastDisplayed =
                (RamRectangleComponent) displayedComponentsList
                        .getChildByIndex(displayedComponentsList.getChildCount() - 1);

        // remove useless elements not visible at the end of the displayedComponentsList
        while (lastDisplayed.getY() > maskComponentsYBottom) {
            this.removeChildToDisplayList(displayedComponentsList.getChildCount() - 1, true);
            lastDisplayed =
                    (RamRectangleComponent) displayedComponentsList.getChildByIndex(displayedComponentsList
                            .getChildCount() - 1);
        }
        updateScrollButtons();
    }

    /**
     * Handle the list if the size of an element decreased.
     */
    private void handleElementSizeDecreased() {
        RamRectangleComponent first = componentsList.get(0);
        RamRectangleComponent last = componentsList.get(componentsList.size() - 1);

        RamRectangleComponent lastDisplayed =
                (RamRectangleComponent) displayedComponentsList
                        .getChildByIndex(displayedComponentsList.getChildCount() - 1);
        RamRectangleComponent next =
                (lastDisplayed != last) ? componentsList.get(componentsList.indexOf(lastDisplayed) + 1) : null;
        // add elements at the end of the displayedComponentsList as long as there are any
        while (next != null
                && lastDisplayed.getY() + lastDisplayed.getHeight() < this.maskComponents.getY()
                        + this.scrollListLayout.getMaximumHeight()) {

            // Add next child to the end of the display list
            this.addChildToDisplayList(displayedComponentsList.getChildCount(), next, true);

            lastDisplayed =
                    (RamRectangleComponent) displayedComponentsList.getChildByIndex(displayedComponentsList
                            .getChildCount() - 1);
            next = (lastDisplayed != last) ? componentsList.get(componentsList.indexOf(lastDisplayed) + 1) : null;
        }

        // add elements at the beginning of the displayedComponentsList if needed
        RamRectangleComponent firstDisplayed = (RamRectangleComponent) displayedComponentsList.getChildByIndex(0);
        RamRectangleComponent previous =
                (firstDisplayed != first) ? componentsList.get(componentsList.indexOf(firstDisplayed) - 1) : null;

        float emptySpace = this.scrollListLayout.getMaximumHeight() - displayedComponentsList.getHeight();

        while (emptySpace > 0 && previous != null) {
            this.addChildToDisplayList(0, previous, true);
            emptySpace -= previous.getHeight();
            firstDisplayed = previous;
            previous =
                    (firstDisplayed != first) ? componentsList.get(componentsList.indexOf(firstDisplayed) - 1) : null;
        }

        if (emptySpace > 0) {
            this.displayedComponentsList.setBufferSize(Cardinal.NORTH, 0);
            this.displayedComponentsList.updateLayout();
        }

        updateScrollButtons();
    }
}
