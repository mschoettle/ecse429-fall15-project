package ca.mcgill.sel.ram.ui.components.listeners;

import ca.mcgill.sel.ram.ui.components.RamListComponent;

/**
 * Interface for Objects wishing to listen to {@link RamListComponent}s.
 *
 * @author Romain
 *
 * @param <T> The type of the elements being selected.
 */
public interface RamListListener<T> {

    /**
     * Informs that an element was selected.
     *
     * @param list The list from which the selection was made.
     * @param element The element selected
     */
    void elementSelected(RamListComponent<T> list, T element);

    /**
     * Allows to do something when the list was double clicked.
     *
     * @param list - The list from which the selection was made.
     * @param element - The element double clicked.
     */
    void elementDoubleClicked(RamListComponent<T> list, T element);

    /**
     * Informs that an element was held.
     *
     * @param list The list from which the selection was made.
     * @param element The held element
     */
    void elementHeld(RamListComponent<T> list, T element);
}
