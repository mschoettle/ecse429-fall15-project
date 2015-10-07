package ca.mcgill.sel.ram.ui.components.browser;

import java.io.File;

import ca.mcgill.sel.ram.ui.components.browser.RamFileBrowser.RamFileBrowserType;
import ca.mcgill.sel.ram.ui.components.browser.interfaces.JarFileBrowserListener;
import ca.mcgill.sel.ram.ui.components.browser.interfaces.RamFileBrowserListener;
import ca.mcgill.sel.ram.ui.utils.Constants;

/**
 * Utility class to load a JAR with a RamFileBrowser.
 * 
 * @author tdimeco
 */
public final class JarFileBrowser {
    
    private static File lastSharedFolder = new File(Constants.DIRECTORY_MODELS).getAbsoluteFile();
    
    /**
     * Private constructor.
     */
    private JarFileBrowser() {
    }
    
    /**
     * Load a JAR with the file browser.
     * 
     * @param listener The callback to handle the loaded JAR
     */
    public static void loadJarFile(final JarFileBrowserListener listener) {
        
        RamFileBrowser browser = new RamFileBrowser(RamFileBrowserType.LOAD, Constants.JAR_FILE_EXTENSION, 
                lastSharedFolder);
        // TODO: Threaded loading can cause MT4J to crash during drawAndUpdate
        // browser.setCallbackThreaded(true);
        browser.addFileBrowserListener(new RamFileBrowserListener() {
            
            @Override
            public void fileSelected(File file, RamFileBrowser fileBrowser) {
                if (file.canRead()) {
                    lastSharedFolder = file.getParentFile();
                    listener.jarLoaded(file);
                }
            }
        });
        browser.display();
    }
}
