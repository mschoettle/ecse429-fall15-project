package ca.mcgill.sel.ram.ui.components.browser.interfaces;

import java.io.File;

/**
 * Interface browser for creating the Concern.
 * @author Nishanth
 *
 */
public interface IConcernCreatorBrowserListener {
    /**
     * Called when a call is made to create a Concern, The selected directory is passed a parameter.
     * @param file Java treats directory as a file, Hence the directory is passed as a parameter.
     */
    void getConcern(File file);
}
