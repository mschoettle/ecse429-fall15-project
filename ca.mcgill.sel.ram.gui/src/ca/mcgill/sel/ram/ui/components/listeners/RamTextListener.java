package ca.mcgill.sel.ram.ui.components.listeners;

import ca.mcgill.sel.ram.ui.components.RamTextComponent;

/**
 * Allows to warn when the text is modified.
 * @author lmartellotto
 */
public interface RamTextListener {
    
    /**
     * Warns that the text has changed.
     * @param component
     *            The component where text has changed
     */
    void textModified(RamTextComponent component);
}
