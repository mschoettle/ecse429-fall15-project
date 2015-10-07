package ca.mcgill.sel.ram.ui.scenes.handler.impl;

import java.io.File;
import java.io.IOException;

import org.mt4j.sceneManagement.transition.SlideTransition;

import ca.mcgill.sel.commons.StringUtil;
import ca.mcgill.sel.commons.emf.util.ResourceManager;
import ca.mcgill.sel.core.COREConcern;
import ca.mcgill.sel.core.util.COREModelUtil;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.browser.ConcernCreatorBrowser;
import ca.mcgill.sel.ram.ui.components.browser.CoreFileBrowser;
import ca.mcgill.sel.ram.ui.components.browser.interfaces.CoreFileBrowserListener;
import ca.mcgill.sel.ram.ui.components.browser.interfaces.IConcernCreatorBrowserListener;
import ca.mcgill.sel.ram.ui.scenes.SelectAspectScene;
import ca.mcgill.sel.ram.ui.scenes.handler.ISelectSceneHandler;
import ca.mcgill.sel.ram.ui.utils.Constants;

/**
 * The default handler for a {@link SelectAspectScene}.
 * 
 * @author mschoettle
 */
public class SelectSceneHandler extends DefaultRamSceneHandler implements ISelectSceneHandler {
    
    @Override
    public void createConcernDisplay(SelectAspectScene scene) {
        scene.setTransition(new SlideTransition(RamApp.getApplication(), 700, true));
        
        ConcernCreatorBrowser.displayBrowser(new IConcernCreatorBrowserListener() {
            @Override
            public void getConcern(File file) {
                COREConcern concern = COREModelUtil.createConcern(StringUtil.toUpperCaseFirst(file.getName()));
                
                String fileName = file.getAbsolutePath().concat(File.separator).concat(concern.getName());
                
                try {
                    ResourceManager.saveModel(concern, fileName.concat("." + Constants.CORE_FILE_EXTENSION));
                } catch (IOException e) {
                    // Shouldn't happen.
                    e.printStackTrace();
                }
                
                RamApp.getApplication().displayConcernFM(concern);
            }
            
        });
        
    }
    
    @Override
    public void loadConcernDisplay(SelectAspectScene scene) {
        scene.setTransition(new SlideTransition(RamApp.getApplication(), 700, true));
        
        CoreFileBrowser.loadCoreFile(new CoreFileBrowserListener() {
            @Override
            public void concernLoaded(COREConcern concern) {
                RamApp.getApplication().displayConcernFM(concern);
            }
        });
    }
    
}
