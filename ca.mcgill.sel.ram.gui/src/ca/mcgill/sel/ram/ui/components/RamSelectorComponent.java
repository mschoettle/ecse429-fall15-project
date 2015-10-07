package ca.mcgill.sel.ram.ui.components;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.mt4j.input.inputProcessors.IGestureEventListener;
import org.mt4j.input.inputProcessors.componentProcessors.AbstractComponentProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragEvent;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapProcessor;
import org.mt4j.util.MTColor;

import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamKeyboard.KeyboardPosition;
import ca.mcgill.sel.ram.ui.components.RamListComponent.Namer;
import ca.mcgill.sel.ram.ui.components.listeners.RamKeyboardListener;
import ca.mcgill.sel.ram.ui.components.listeners.RamListListener;
import ca.mcgill.sel.ram.ui.components.listeners.RamSelectorListener;
import ca.mcgill.sel.ram.ui.components.listeners.RamTextListener;
import ca.mcgill.sel.ram.ui.events.DelayedDrag;
import ca.mcgill.sel.ram.ui.events.listeners.ActionListener;
import ca.mcgill.sel.ram.ui.events.listeners.IDragListener;
import ca.mcgill.sel.ram.ui.events.listeners.ITapListener;
import ca.mcgill.sel.ram.ui.layouts.HorizontalLayoutRightAligned;
import ca.mcgill.sel.ram.ui.layouts.HorizontalLayoutVerticallyCentered;
import ca.mcgill.sel.ram.ui.layouts.VerticalLayout;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.utils.Constants;
import ca.mcgill.sel.ram.ui.utils.Fonts;
import ca.mcgill.sel.ram.ui.utils.Icons;
import ca.mcgill.sel.ram.ui.utils.Strings;
import ca.mcgill.sel.ram.ui.views.handler.BaseHandler;

/**
 * The RAM selector shows a vertical lists of elements which can be selected either by tapping on a row element or by
 * typing the name of the element in a text box created for this purpose. Objects wishing to be informed about which
 * element was selected should implement the {@link RamSelectorListener} interface and register.
 *
 * @author vbonnet
 * @author eyildirim
 * @author mschoettle
 * @author Romain
 * @param <T> The type of the elements to be selected.
 */
public class RamSelectorComponent<T> extends RamRoundedRectangleComponent implements RamListListener<T>,
        RamTextListener, RamKeyboardListener {

    private static final float ICON_SIZE = Fonts.FONTSIZE_CLASS_NAME + 2;

    /** The list which will contains the elements. */
    protected RamListComponent<T> scrollableListComponent;

    /** The input field to make a search. */
    protected RamTextComponent inputField;

    /** The rectangle which contains the input field. */
    protected RamRectangleComponent inputContainer;

    /** The rectangle which contains the buttons on the top right corner. */
    protected RamRectangleComponent topRightButtonsContainer;

    /**
     * Hander for the selector which handles {@link DragEvent}, {@link TapEvent} and
     * {@link ca.mcgill.sel.ram.ui.events.WheelEvent}.
     *
     */
    private class InternalSelectorHandler extends BaseHandler implements IDragListener, ITapListener {
        private DelayedDrag dragAction = new DelayedDrag(Constants.DELAYED_DRAG_MIN_DRAG_DISTANCE);

        @Override
        public boolean processDragEvent(DragEvent dragEvent) {

            if (!isDragAvailableOnSelector) {
                return true;
            }

            // If the drag is made from the border of selector (so to move the entire selector)
            if (!scrollableListComponent.containsPointGlobal(dragEvent.getFrom())
                    || dragEvent.getFrom().getY() < scrollableListComponent.getY()
                    || dragEvent.getFrom().getY() > scrollableListComponent.getY()
                            + scrollableListComponent.getHeight()) {

                dragAction.processGestureEvent(dragEvent);
            }

            return true;
        }

        @Override
        public boolean processTapEvent(TapEvent tapEvent) {
            if (tapEvent.isTapped()) {
                if (inputField.containsPointGlobal(tapEvent.getLocationOnScreen())) {
                    displayKeyboard();
                }
            }

            return true;
        }
    }

    private Set<RamSelectorListener<T>> listeners;
    private RamButton closeButton;
    private boolean isDragAvailableOnSelector;

    /**
     * Creates a selector class.
     */
    protected RamSelectorComponent() {
        // CHECKSTYLE:IGNORE MagicNumber: Radius of the arc of corners
        super(10);
        isDragAvailableOnSelector = true;

        setFillColor(Colors.SELECTOR_DEFAULT_BACKGROUND_COLOR);
        setNoFill(false);

        topRightButtonsContainer = new RamRectangleComponent();
        topRightButtonsContainer.setLayout(new HorizontalLayoutRightAligned());
        RamImageComponent closeImage =
                new RamImageComponent(Icons.ICON_DELETE, Colors.ICON_CLOSE_SELECTOR_COLOR, ICON_SIZE, ICON_SIZE);
        closeButton = new RamButton(closeImage);
        showCloseButton(true);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                selectorClosed();
            }
        });
        topRightButtonsContainer.addChild(closeButton);
        addChild(topRightButtonsContainer);

        inputContainer = new RamRectangleComponent();
        final float padding = 10;
        inputContainer.setLayout(new HorizontalLayoutVerticallyCentered(padding));
        inputContainer.setBufferSize(Cardinal.SOUTH, padding);
        addChild(inputContainer);

        inputField = new RamTextComponent();
        inputField.setNoFill(false);
        inputField.setNewlineDisabled(true);
        inputField.setPlaceholderText(Strings.PH_SEARCH);
        inputContainer.addChild(inputField);

        listeners = new HashSet<RamSelectorListener<T>>();

        this.initListComponent();

        super.setLayout(new VerticalLayout());

        InternalSelectorHandler handler = new InternalSelectorHandler();
        registerGestureListeners(handler);
    }

    /**
     * Creates a selector class for the given elements. The names (both for sorting and displaying) are determine by
     * element.toString()
     *
     * @param elements The elements which can be selected.
     */
    public RamSelectorComponent(List<T> elements) {
        this(elements, null);
    }

    /**
     * Creates a selector class for the given elements.
     *
     * @param elements The elements which can be selected.
     * @param namer Determines the sorting and display names for the elements.
     */
    public RamSelectorComponent(List<T> elements, Namer<T> namer) {
        this(namer);
        this.scrollableListComponent.setElements(elements);
    }

    /**
     * Creates an empty selector with the given namer.
     *
     * @param namer The namer to use in displaying elements.
     */
    public RamSelectorComponent(Namer<T> namer) {
        this();
        this.scrollableListComponent.setNamer(namer);
    }

    /**
     * Initialize the {@link RamListComponent}.
     */
    protected void initListComponent() {
        scrollableListComponent = new RamListComponent<T>(true);
        scrollableListComponent.registerListener(this);

        this.addChild(scrollableListComponent);
    }

    /**
     * Adds the element to the selector's list.
     *
     * @param element The element to add.
     */
    public void addElement(T element) {
        this.scrollableListComponent.addElement(element);
    }

    @Override
    public void destroy() {
        // invoke later in order to make sure that it gets executed after all events have been handled
        // otherwise in certain circumstances it is possible that Mt4j tries to process an event even though
        // the component has been already destroyed
        // more specific: this fixes a bug where on Windows the drag event of the selector gets processed after tap (due
        // to ordering the list of registered processors
        // differently). Since everything is destroyed in ComponentInputProcessorSupport line 73 an exception occurs
        // because the list is empty.
        RamApp.getApplication().invokeLater(new Runnable() {
            @Override
            public void run() {
                scrollableListComponent.destroy();
                RamSelectorComponent.super.destroy();

                if (getInputField().getKeyboard() != null) {
                    getInputField().getKeyboard().close();
                }
            }
        });
    }

    /**
     * Displays the keyboard for the input field on request.
     */
    public void displayKeyboard() {
        RamKeyboard keyboard = new RamKeyboard();
        keyboard.registerListener(this);
        inputField.showKeyboard(this, keyboard, KeyboardPosition.TOP);
        inputField.registerListener(this);
    }

    /**
     * Gets the element map.
     *
     * @return The element map.
     */
    public Map<T, RamRectangleComponent> getElementMap() {
        return this.scrollableListComponent.getElementMap();
    }

    /**
     * Gets the input field for search.
     *
     * @return The input field for search
     */
    protected RamTextComponent getInputField() {
        return inputField;
    }

    /**
     * Registers the listener for the selected gestures.
     *
     * @param listener The listener to gesture events.
     */
    private void registerGestureListeners(IGestureEventListener listener) {
        addGestureListener(DragProcessor.class, listener);
        addGestureListener(TapProcessor.class, listener);
    }

    @Override
    protected void registerInputProcessors() {
        registerInputProcessor(new DragProcessor(RamApp.getApplication()));
        registerInputProcessor(new TapProcessor(RamApp.getApplication(), Constants.TAP_MAX_FINGER_UP, true,
                Constants.TAP_DOUBLE_TAP_TIME));

        // enable event propagation to parents
        for (AbstractComponentProcessor processor : getInputProcessors()) {
            processor.setBubbledEventsEnabled(true);
        }
    }

    /**
     * Register the tap and hold processor for the list component.
     */
    public void registerTapAndHoldProcessor() {
        this.scrollableListComponent.registerTapAndHoldProcessor();
    }

    /**
     * Registers a listener for selection events.
     *
     * @param listener The listener.
     */
    public void registerListener(RamSelectorListener<T> listener) {
        listeners.add(listener);
    }

    /**
     * Removes the element from the selector's list.
     *
     * @param element The element to remove.
     */
    public void removeElement(T element) {
        this.scrollableListComponent.removeElement(element);
    }

    /**
     * Call the action to do when the delete button is closed.
     */
    private void selectorClosed() {
        for (RamSelectorListener<T> listener : listeners) {
            listener.closeSelector(this);
        }
    }

    @Override
    public void elementSelected(RamListComponent<T> list, T element) {
        for (RamSelectorListener<T> listener : listeners) {
            listener.elementSelected(this, element);
        }
    }

    @Override
    public void elementDoubleClicked(RamListComponent<T> list, T element) {
        for (RamSelectorListener<T> listener : listeners) {
            listener.elementDoubleClicked(this, element);
        }
    }

    @Override
    public void elementHeld(RamListComponent<T> list, T element) {
        for (RamSelectorListener<T> listener : listeners) {
            listener.elementHeld(this, element);
        }
    }

    /**
     * Sets whether drag should be enabled on the entire selector to move it.
     *
     * @param dragAvailable true, if drag should be enabled, false otherwise
     */
    public void setDragAvailableOnSelector(boolean dragAvailable) {
        this.isDragAvailableOnSelector = dragAvailable;
    }

    /**
     * Adds the given elements to the selector. Removes any already existing elements from the selector.
     *
     * @param elements the elements to be added to the selector
     */
    public synchronized void setElements(List<T> elements) {
        this.scrollableListComponent.setElements(elements);
    }

    /**
     * Sets a new way to sort the list.
     *
     * @param elementsOrder The elementsOrder to set.
     */
    public void setElementsOrder(Comparator<T> elementsOrder) {
        this.scrollableListComponent.setElementsOrder(elementsOrder);
    }

    /**
     * Sets the map of elements.
     *
     * @param someMap The map of elements of the list.
     */
    public void setMap(Map<T, RamRectangleComponent> someMap) {
        this.scrollableListComponent.setMap(someMap);
    }

    @Override
    public void setMaximumSize(float width, float height) {
        this.scrollableListComponent.setMaximumSize(width, height);
    }

    /**
     * Sets the new max number of displayed elements in this selector.
     *
     * @param n The number of elements.
     */
    public void setMaxNumberOfElements(int n) {
        this.scrollableListComponent.setMaxNumberOfElements(n);
    }

    /**
     * Sets the namer to be used for naming components.
     *
     * @param namer the namer to be used
     */
    public void setNamer(Namer<T> namer) {
        this.scrollableListComponent.setNamer(namer);
    }

    /**
     * Sets the new color to the scroll buttons. If color is null, sets no color.
     *
     * @param color The new color. If null, the buttons will have no color.
     */
    public void setScrollButtonColor(MTColor color) {
        this.scrollableListComponent.setScrollButtonColor(color);
    }

    /**
     * Enable the delete button.
     *
     * @param show True to display the delete button, false otherwise
     */
    public void showCloseButton(boolean show) {
        if (show) {
            topRightButtonsContainer.addChild(0, closeButton);
        } else {
            topRightButtonsContainer.removeChild(closeButton);
        }
    }

    /**
     * Shows the text field for keyboard selection.
     *
     * @param show Whether to show the text field for keyboard selection.
     */
    public void showTextField(boolean show) {
        if (show) {
            inputContainer.addChild(0, inputField);
        } else {
            inputContainer.removeChild(inputField);
        }
    }

    /**
     * Returns the number of elements in the selector.
     *
     * @return The number of elements in the selector.
     */
    public int size() {
        return this.scrollableListComponent.size();
    }

    /**
     * Unregisters the listener.
     *
     * @param listener The listener to unregister.
     */
    public void unregisterListener(RamSelectorListener<T> listener) {
        listeners.remove(listener);
    }

    @Override
    public void textModified(RamTextComponent component) {
        if (component == inputField) {
            this.scrollableListComponent.setSearchString(component.getText());
        }
    }

    /**
     * Fill color of the display component of this element with the color given in parameter.
     *
     * @param key the element
     * @param color the color used
     */
    public void selectElement(T key, MTColor color) {
        this.scrollableListComponent.selectElement(key, color);
    }

    /**
     * Fill color of the display component of this element with the default color.
     *
     * @param key the element
     */
    public void deselectElement(T key) {
        this.scrollableListComponent.deselectElement(key);
    }

    @Override
    public void clear() {
    }

    @Override
    public void appendText(String text) {
    }

    @Override
    public void setText(String text) {
    }

    @Override
    public void appendCharByUnicode(String unicode) {
    }

    @Override
    public void removeLastCharacter() {
    }

    @Override
    public void keyboardCancelled() {
    }

    @Override
    public void keyboardClosed(RamKeyboard ramKeyboard) {
    }

    @Override
    public void keyboardOpened() {
    }

    @Override
    public void modifierKeyPressed(int keyCode) {
    }

    @Override
    public boolean verifyKeyboardDismissed() {
        return true;
    }
}
