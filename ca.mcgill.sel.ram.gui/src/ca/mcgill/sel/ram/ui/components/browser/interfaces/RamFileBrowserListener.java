package ca.mcgill.sel.ram.ui.components.browser.interfaces;

import java.io.File;

import ca.mcgill.sel.ram.ui.components.browser.RamFileBrowser;

/**
 * Interface to handle file browser events.
 * 
 * @author tdimeco
 */
public interface RamFileBrowserListener {
    
    /**
     * Called when a file is selected on a file browser.
     * 
     * @param file The selected file
     * @param fileBrowser The associated file browser
     */
    void fileSelected(File file, RamFileBrowser fileBrowser);
}
