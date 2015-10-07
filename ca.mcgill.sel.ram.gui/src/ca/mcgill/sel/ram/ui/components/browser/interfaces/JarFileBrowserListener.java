package ca.mcgill.sel.ram.ui.components.browser.interfaces;

import java.io.File;

/**
 * Interface to handle JAR file browser events.
 * 
 * @author tdimeco
 */
public interface JarFileBrowserListener {
    
    /**
     * Called when a JAR is loaded on a JAR file browser.
     * 
     * @param file The loaded JAR file
     */
    void jarLoaded(File file);
}
