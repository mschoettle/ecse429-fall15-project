package ca.mcgill.sel.ram.ui.components.browser.interfaces;

import ca.mcgill.sel.core.COREConcern;

/**
 * Interface to handle core file browser events.
 * 
 * @author tdimeco
 */
public interface CoreFileBrowserListener {
    
    /**
     * Called when a core file is loaded on a core file browser.
     * 
     * @param concern The loaded concern
     */
    void concernLoaded(COREConcern concern);
}
