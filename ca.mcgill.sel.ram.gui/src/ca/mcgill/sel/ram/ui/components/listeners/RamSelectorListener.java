package ca.mcgill.sel.ram.ui.components.listeners;

import ca.mcgill.sel.ram.ui.components.RamSelectorComponent;

/**
 * Interface for Objects wishing to listen to {@link RamSelectorComponent}s.
 *
 * @author Nishanth
 * @author vbonnet
 *
 * @param <T> The type of the elements being selected.
 */
public interface RamSelectorListener<T> {

    /**
     * Informs that an element was selected.
     *
     * @param selector The selector from which the selection was made.
     * @param element The element selected, null if the selector was closed without selecting an element.
     */
    void elementSelected(RamSelectorComponent<T> selector, T element);

    /**
     * Allows to do something when the selector was double clicked.
     *
     * @param selector - The selector from which the selection was made.
     * @param element - The element double clicked.
     */
    void elementDoubleClicked(RamSelectorComponent<T> selector, T element);

    /**
     * Informs that an element was held.
     *
     * @param selector The selector from which the selection was made.
     * @param element The held element
     */
    void elementHeld(RamSelectorComponent<T> selector, T element);

    /**
     * Allows to do something when the selector is closed.
     *
     * @param selector The selector from which the selection was made.
     */
    void closeSelector(RamSelectorComponent<T> selector);
}
