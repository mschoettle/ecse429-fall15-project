package ca.mcgill.sel.ram.ui.components.listeners;

import ca.mcgill.sel.ram.ui.components.RamSelectorComponent;

/**
 * Abstract class for Objects wishing to listen to {@link RamSelectorComponent}s. Default behavior for - the delete
 * button : close the selector. - the double clic : do nothing
 *
 * @param <T> The type of the elements being selected.
 *
 * @author lmartellotto
 */
public abstract class AbstractDefaultRamSelectorListener<T> implements RamSelectorListener<T> {

    @Override
    public void closeSelector(RamSelectorComponent<T> selector) {
        selector.destroy();
    }

    @Override
    public void elementDoubleClicked(RamSelectorComponent<T> selector, T element) {
        // Do nothing !
        // Override it, if you want to handle double clic
    }

    @Override
    public void elementHeld(RamSelectorComponent<T> selector, T element) {
        // Do nothing !
        // Override it, if you want to handle double clic
    }
}
