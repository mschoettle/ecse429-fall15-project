package ca.mcgill.sel.ram.ui.components;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.mt4j.components.MTComponent;
import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamListComponent.Namer;
import ca.mcgill.sel.ram.ui.components.listeners.RamListListener;

/**
 * Container for a list which cans be closed thanks to the close button in the header.
 * The header can be customized with other information. The component can be dragged but not outside the screen.
 *
 *
 * @author CCamillieri
 * @param <T> - Type of the objects initAndDisplayed by the {@link RamListComponent}
 */
public abstract class RamPanelListComponent<T> extends RamPanelComponent {

    /**
     * The selectable list containing the items.
     */
    protected RamListComponent<T> list;
    /**
     * Max number of element to show in the list.
     */
    protected int maxNumberOfElements = 5;

    /**
     * The {@link Namer} used to build the {@link RamListComponent}.
     */
    private Namer<T> namer;

    /**
     * The {@link RamListListener} used to handle event on the {@link RamListComponent}.
     */
    private RamListListener<T> listener;

    private Vector3D startingPosition;

    /**
     * Create a new {@link RamPanelListComponent}, placed at the top left of the screen.
     *
     * @param label - The name to initAndDisplay for the component
     */
    public RamPanelListComponent(String label) {
        this(5, label, HorizontalStick.LEFT, VerticalStick.TOP);
    }

    /**
     * Constructor for {@link RamPanelListComponent}.
     *
     * @param arcRadius - Radius for the edges of the panel
     * @param label - Text to be initAndDisplayed in the label
     * @param horStick - Specification of {@link HorizontalStick} for the panel
     * @param vStick - Specification of {@link VerticalStick} for the panel
     */
    public RamPanelListComponent(int arcRadius, String label, HorizontalStick horStick, VerticalStick vStick) {
        super(arcRadius);
        setVerticalAlign(vStick);
        setHorizontalAlign(horStick);
        setLabel(label);
        showCloseButton(false);
        // compute default starting position
        float x = getHorizontalAlign() == HorizontalStick.RIGHT ? RamApp.getApplication().getWidth() : 0;
        float y = getVerticalAlign() == VerticalStick.BOTTOM
                ? RamApp.getApplication().getHeight() : 0;
        this.startingPosition = new Vector3D(x, y);
    }

    /**
     * Constructor for {@link RamPanelListComponent}.
     *
     * @param arcRadius - Radius for the edges of the panel
     * @param label - Text to be initAndDisplayed in the label
     * @param horStick - Specification of {@link HorizontalStick} for the panel
     * @param vStick - Specification of {@link VerticalStick} for the panel
     * @param namer - The namer for the list
     * @param listener - The listener for the list
     */
    public RamPanelListComponent(int arcRadius, String label, HorizontalStick horStick, VerticalStick vStick,
            Namer<T> namer, RamListListener<T> listener) {
        this(arcRadius, label, horStick, vStick);
        setNamer(namer);
        setListener(listener);
    }

    /**
     * Constructor for {@link RamPanelListComponent}.
     * Call initAndDisplay() at the end of Constructor to build the view and list.
     *
     * @param arcRadius - Radius for the edges of the panel
     * @param label - Text to be initAndDisplayed in the label
     * @param horStick - Specification of {@link HorizontalStick} for the panel
     * @param vStick - Specification of {@link VerticalStick} for the panel
     * @param namer - The namer for the list
     * @param listener - The listener for the list
     * @param elements - The elements populating the list
     */
    public RamPanelListComponent(int arcRadius, String label, HorizontalStick horStick, VerticalStick vStick,
            Namer<T> namer, RamListListener<T> listener, List<T> elements) {
        this(arcRadius, label, horStick, vStick, namer, listener);
        setElements(elements);
    }

    /**
     * Change the max number of elements displayed in the list.
     *
     * @param maxNumberOfElements - the max number of elements
     */
    public void setMaxNumberOfElements(int maxNumberOfElements) {
        this.maxNumberOfElements = maxNumberOfElements;
        if (list != null) {
            list.setMaxNumberOfElements(maxNumberOfElements);
        }
    }

    /**
     * Used to initAndDisplay the component. Must be called after object has been created.
     */
    protected void initAndDisplay() {
        if (namer != null) {
            list = new RamListComponent<T>(namer, true);
        } else {
            list = new RamListComponent<T>(true);
        }
        list.setMaxNumberOfElements(maxNumberOfElements);
        if (listener != null) {
            list.registerListener(listener);
        }
        setContent(list);
        showContent(true);

        updateLayout();
        if (startingPosition != null) {
            setPositionGlobal(startingPosition);
        }
    }

    /**
     * Set the default position for the component when creating it.
     *
     * @param position - the default starting position
     */
    public void setStartingPosition(Vector3D position) {
        this.startingPosition = position;
        setPositionGlobal(startingPosition);
    }

    /**
     * Remove a displayed element from the list.
     *
     * @param element - the element to remove
     * @return true if the element was contained and thus removed, false otherwise
     */
    public final boolean removeElement(T element) {
        return list.removeElement(element);
    }

    /**
     * Add a new element in the list.
     *
     * @param element - the element to add
     */
    public final void addElement(T element) {
        if (list == null) {
            initAndDisplay();
        }
        list.addElement(element);
    }

    /**
     * Set the {@link Namer} used to populate the list.
     *
     * @param namer - The namer
     */
    public void setNamer(Namer<T> namer) {
        this.namer = namer;
    }

    /**
     * Set the {@link RamListListener} used to populate the list.
     *
     * @param listener - The listener
     */
    public void setListener(RamListListener<T> listener) {
        this.listener = listener;
    }

    /**
     * Set the {@link RamListListener} used to populate the list.
     *
     * @param elements - The elements used to populate the list
     */
    public void setElements(Collection<T> elements) {
        if (list == null) {
            initAndDisplay();
        }
        list.setElements(new LinkedList<T>(elements));
    }

    @Override
    protected void handleChildResized(MTComponent component) {
        super.handleChildResized(component);
        setPositionGlobal(getCorrectPosition(this, getX(), getY(), true));
    }

    /**
     * Check whether the list of components is empty.
     *
     * @return - true if the list is empty
     */
    public final boolean isEmpty() {
        if (list == null || list.getElementMap() == null) {
            return true;
        }
        return list.getElementMap().isEmpty();
    }

}
