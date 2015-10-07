package ca.mcgill.sel.ram.ui.components.listeners;

import ca.mcgill.sel.ram.ui.components.RamExpendableComponent;

/**
 * Interface for Objects wishing to listen to {@link RamExpendableComponent}s.
 * 
 * @author Romain
 *
 */
public interface RamExpendableListener {
    
    /**
     * Allows to do something when the hideable element of an expendable was expanded.
     * 
     * @param expendable - The expendable who was expanded
     */
    void elementExpanded(RamExpendableComponent expendable);
    
    /**
     * Allows to do something when the hideable element of an expendable was collapsed.
     * 
     * @param expendable - The expendable who was collapsed
     */
    void elementCollapsed(RamExpendableComponent expendable);
}
