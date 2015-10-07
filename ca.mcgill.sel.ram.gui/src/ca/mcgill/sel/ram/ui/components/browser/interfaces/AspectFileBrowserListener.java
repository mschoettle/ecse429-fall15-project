package ca.mcgill.sel.ram.ui.components.browser.interfaces;

import java.io.File;

import ca.mcgill.sel.ram.Aspect;

/**
 * Interface to handle aspect file browser events.
 * 
 * @author tdimeco
 */
public interface AspectFileBrowserListener {
    
    /**
     * Called when an aspect is loaded on an aspect file browser.
     * 
     * @param aspect The loaded aspect
     */
    void aspectLoaded(Aspect aspect);
    
    /**
     * Called when an aspect is saved on an aspect file browser.
     * 
     * @param file The saved aspect file
     */
    void aspectSaved(File file);
}
