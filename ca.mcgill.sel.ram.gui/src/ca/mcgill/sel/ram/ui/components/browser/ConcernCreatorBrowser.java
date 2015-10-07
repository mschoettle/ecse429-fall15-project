package ca.mcgill.sel.ram.ui.components.browser;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;

import ca.mcgill.sel.commons.emf.util.ResourceManager;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamPopup.PopupType;
import ca.mcgill.sel.ram.ui.components.browser.RamFileBrowser.RamFileBrowserType;
import ca.mcgill.sel.ram.ui.components.browser.interfaces.AspectFileBrowserListener;
import ca.mcgill.sel.ram.ui.components.browser.interfaces.IConcernCreatorBrowserListener;
import ca.mcgill.sel.ram.ui.components.browser.interfaces.RamFileBrowserListener;
import ca.mcgill.sel.ram.ui.utils.Constants;
import ca.mcgill.sel.ram.ui.utils.Strings;

/**
 * Class for creating a concern.
 * @author Nishanth
 *
 */
public final class ConcernCreatorBrowser {
    private static final String FILE_EXTENSION = "Directory";
    private static File lastSharedFolder = new File(Constants.DIRECTORY_MODELS).getAbsoluteFile();
    
    /**
     * Private constructor.
     */
    private ConcernCreatorBrowser() {
    }
    
    /**
     * Load an aspect with the file browser.
     * 
     * @param listener The callback to handle the loaded aspect
     */
    public static void displayBrowser(final IConcernCreatorBrowserListener listener) {
        
        RamFileBrowser browser = new RamFileBrowser(RamFileBrowserType.FOLDER, FILE_EXTENSION, lastSharedFolder);
        browser.addFileBrowserListener(new RamFileBrowserListener() {
            
            @Override
            public void fileSelected(File file, RamFileBrowser fileBrowser) {
                try {
                    if (file.canRead()) {
                        lastSharedFolder = file.getParentFile();
                        // COREConcern concern = (COREConcern) ResourceManager.loadModel(file);
                        listener.getConcern(file);
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
     * Save an aspect with the file browser.
     * @param aspect The aspect the user wants to save
     * @param listener The callback to handle the saved file
     */
    public static void saveAspect(final Aspect aspect, final AspectFileBrowserListener listener) {
        
        File suggestedFile;
        
        // Set the selected file to suggest a name.
        // If the aspect is already contained in a resource, choose that file.
        if (aspect.eResource() != null) {
            suggestedFile = new File(aspect.eResource().getURI().toFileString());
        } else {
            suggestedFile = new File(lastSharedFolder, aspect.getName());
        }
        
        RamFileBrowser browser = new RamFileBrowser(RamFileBrowserType.SAVE, FILE_EXTENSION, suggestedFile);
        
        // CHECKSTYLE:IGNORE AnonInnerLength FOR 1 LINES: Okay here
        browser.addFileBrowserListener(new RamFileBrowserListener() {
            
            @Override
            public void fileSelected(File file, RamFileBrowser fileBrowser) {
                if (!file.exists()) {
                    try {
                        if (!file.getParentFile().exists()) {
                            // create parent directory if someone's trying to be tricky.
                            file.getParentFile().mkdirs();
                        }
                        file.createNewFile();
                    } catch (final IOException e) {
                        // TODO display something to user
                        e.printStackTrace();
                    }
                }
                try {
                    
                    // Check permission
                    if (!file.canWrite()) {
                        throw new IOException("Permission denied");
                    }
                    
                    Aspect aspectToSave = aspect;
                    // if the file/aspect is already saved in a resource it should be copied
                    // but only if the file name differs
                    // otherwise the old resource will be empty
                    // if the aspect was created new it means it has no eResource because
                    // it has never been saved
                    // if we are overwriting a file then dont create a new resource
                    if (aspect.eResource() != null
                            && aspect.eResource().getURI().equals(URI.createFileURI(file.getAbsolutePath()))) {
                        // Save only resources that have actually changed.
                        final Map<Object, Object> saveOptions = new HashMap<Object, Object>();
                        saveOptions.put(
                                Resource.OPTION_SAVE_ONLY_IF_CHANGED,
                                Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);
                        aspect.eResource().save(saveOptions);
                        listener.aspectSaved(file);
                    } else {
                        if (aspect.eResource() != null) {
                            aspectToSave = EcoreUtil.copy(aspect);
                        }
                        
                        ResourceManager.saveModel(aspectToSave, file.getAbsolutePath());
                        if (listener != null) {
                            lastSharedFolder = file.getParentFile();
                            listener.aspectSaved(file);
                        }
                    }
                } catch (IOException e) {
                    RamApp.getActiveAspectScene()
                            .displayPopup(Strings.POPUP_ERROR_FILE_SAVE + e.getMessage(), PopupType.ERROR);
                }
            }
        });
        browser.display();
    }
}
