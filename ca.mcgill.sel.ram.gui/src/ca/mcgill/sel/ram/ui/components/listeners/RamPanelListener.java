package ca.mcgill.sel.ram.ui.components.listeners;

import ca.mcgill.sel.ram.ui.components.RamPanelComponent;

/**
 * Interface for Objects wishing to listen to {@link RamPanelComponent}s.
 * 
 * @author lmartellotto
 */
public interface RamPanelListener {
    
    /**
     * Allows to do something when the {@link RamPanelComponent} is closed.
     * @param panel
     *            The closed {@link RamPanelComponent}.
     */
    void panelClosed(RamPanelComponent panel);
}
