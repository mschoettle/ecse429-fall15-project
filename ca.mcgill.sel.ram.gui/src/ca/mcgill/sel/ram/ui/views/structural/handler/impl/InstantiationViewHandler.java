package ca.mcgill.sel.ram.ui.views.structural.handler.impl;

import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;

import ca.mcgill.sel.ram.Instantiation;
import ca.mcgill.sel.ram.controller.ControllerFactory;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.scenes.DisplayAspectScene;
import ca.mcgill.sel.ram.ui.views.handler.BaseHandler;
import ca.mcgill.sel.ram.ui.views.handler.HandlerFactory;
import ca.mcgill.sel.ram.ui.views.handler.IDisplaySceneHandler;
import ca.mcgill.sel.ram.ui.views.structural.InstantiationSplitEditingView;
import ca.mcgill.sel.ram.ui.views.structural.InstantiationView;
import ca.mcgill.sel.ram.ui.views.structural.handler.IInstantiationViewHandler;

/**
 * The default handler for a {@link InstantiationView}.
 * 
 * @author eyildirim
 */
public class InstantiationViewHandler extends BaseHandler implements IInstantiationViewHandler {
    
    @Override
    public void addClassifierMapping(InstantiationView instantiationView) {
        Instantiation instantiation = instantiationView.getInstantiation();
        ControllerFactory.INSTANCE.getInstantiationController().createClassifierMapping(instantiation);
    }
    
    @Override
    public void hideMappingDetails(InstantiationView instantiationView) {
        instantiationView.hideMappingDetails();
        instantiationView.getMyInstantiationTitleView().showExpandButton();
        instantiationView.getMyInstantiationTitleView().hideAddClassifierMappingButton();
        instantiationView.getMyInstantiationTitleView().hideDeleteInstantiationButton();
        instantiationView.getMyInstantiationTitleView().hideMappingButton();
    }
    
    @Override
    public boolean processTapEvent(TapEvent tapEvent) {
        // we don't want anything to happen when we tap in an empty area in instantiation view
        
        return true;
    }
    
    @Override
    public void showExternalAspectOfInstantiation(InstantiationView instantiationView) {
        Instantiation instantiation = instantiationView.getInstantiation();
        boolean splitInstantiationModeEnabled = isSplitViewEnabled(RamApp.getActiveAspectScene());
        if (!splitInstantiationModeEnabled) {
            RamApp.getApplication().loadAspect(instantiation.getSource());
        } else {
            
            RamApp.getActiveAspectScene().getHandler().back(RamApp.getActiveAspectScene());
            RamApp.getApplication().loadAspect(instantiation.getSource());
        }
        
    }
    
    @Override
    public void showMappingDetails(InstantiationView instantiationView) {
        instantiationView.showMappingDetails();
        instantiationView.getMyInstantiationTitleView().showCollapseButton();
        instantiationView.getMyInstantiationTitleView().showDeleteInstantiationButton();
        instantiationView.getMyInstantiationTitleView().showMappingButton();
        instantiationView.getMyInstantiationTitleView().showAddClassifierMappingButton();
    }
    
    @Override
    public void switchToSplitView(InstantiationView instantiationView) {
        // Get the latest used display aspect before switching into instantiation edit mode
        
        DisplayAspectScene displayAspectScene = RamApp.getActiveAspectScene();
        boolean splitInstantiationModeEnabled = displayAspectScene != null && isSplitViewEnabled(displayAspectScene);
        
        // Get the handler of the display aspect scene to call its function for switching into instantiation edit mode.
        IDisplaySceneHandler handler = HandlerFactory.INSTANCE.getDisplayAspectSceneHandler();
        
        if (splitInstantiationModeEnabled) {
            // we are already in split instantiation editing mode , do the opposite..
            handler.closeSplitView(displayAspectScene);
        } else {
            // trigger the mode switching
            handler.switchToInstantiationEditMode(displayAspectScene, instantiationView);
        }
        
    }
    
    /**
     * Returns whether split view is currently enabled.
     * 
     * @param scene the current {@link DisplayAspectScene}
     * @return true, whether split view is enabled, false otherwise
     */
    private static boolean isSplitViewEnabled(DisplayAspectScene scene) {
        return scene.getCurrentView() instanceof InstantiationSplitEditingView;
    }
    
}
