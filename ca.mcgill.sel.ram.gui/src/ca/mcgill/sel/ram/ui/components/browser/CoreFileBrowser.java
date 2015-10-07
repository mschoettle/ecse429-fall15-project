package ca.mcgill.sel.ram.ui.components.browser;

import java.io.File;

import ca.mcgill.sel.commons.emf.util.ResourceManager;
import ca.mcgill.sel.core.COREConcern;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamPopup.PopupType;
import ca.mcgill.sel.ram.ui.components.browser.RamFileBrowser.RamFileBrowserType;
import ca.mcgill.sel.ram.ui.components.browser.interfaces.CoreFileBrowserListener;
import ca.mcgill.sel.ram.ui.components.browser.interfaces.RamFileBrowserListener;
import ca.mcgill.sel.ram.ui.utils.Constants;
import ca.mcgill.sel.ram.ui.utils.Strings;

/**
 * Utility class to load a core file with a RamFileBrowser.
 * 
 * @author tdimeco
 */
public final class CoreFileBrowser {
    
    private static File lastSharedFolder = new File(Constants.DIRECTORY_MODELS).getAbsoluteFile();
    
    /**
     * Private constructor.
     */
    private CoreFileBrowser() {
    }
    
    /**
     * Load a core file with the file browser.
     * 
     * @param listener The callback to handle the loaded core file
     */
    public static void loadCoreFile(final CoreFileBrowserListener listener) {
        RamFileBrowser browser = new RamFileBrowser(RamFileBrowserType.LOAD, Constants.CORE_FILE_EXTENSION,
                lastSharedFolder);
        // TODO: Threaded loading can cause MT4J to crash during drawAndUpdate
        // browser.setCallbackThreaded(true);
        browser.addFileBrowserListener(new RamFileBrowserListener() {
            
            @Override
            public void fileSelected(File file, RamFileBrowser fileBrowser) {
                try {
                    if (file.canRead()) {
                        lastSharedFolder = file.getParentFile();
                        COREConcern concern = (COREConcern) ResourceManager.loadModel(file);
                        listener.concernLoaded(concern);
                    }
                    // CHECKSTYLE:IGNORE IllegalCatch FOR 1 LINES: Need to catch RuntimeException
                } catch (Exception e) {
                    RamApp.getActiveScene().displayPopup(Strings.POPUP_ERROR_FILE_LOAD_SYNTAX, PopupType.ERROR);
                    e.printStackTrace();
                }
            }
        });
        browser.display();
    }
    
    /**
     * Sets the initial folder that is displayed first by the file browser.
     * The path needs to be a directory and readable.
     * 
     * @param path the new path
     */
    public static void setInitialFolder(String path) {
        File newPath = new File(path);
        
        if (newPath.isDirectory() && newPath.canRead()) {
            lastSharedFolder = newPath;
        }
    }
}
