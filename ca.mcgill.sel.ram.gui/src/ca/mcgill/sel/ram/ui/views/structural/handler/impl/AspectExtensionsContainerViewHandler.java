package ca.mcgill.sel.ram.ui.views.structural.handler.impl;

import java.io.File;

import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.Instantiation;
import ca.mcgill.sel.ram.InstantiationType;
import ca.mcgill.sel.ram.controller.AspectController;
import ca.mcgill.sel.ram.controller.ControllerFactory;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamPopup.PopupType;
import ca.mcgill.sel.ram.ui.components.browser.AspectFileBrowser;
import ca.mcgill.sel.ram.ui.components.browser.interfaces.AspectFileBrowserListener;
import ca.mcgill.sel.ram.ui.utils.Strings;
import ca.mcgill.sel.ram.ui.views.structural.InstantiationSplitEditingView;
import ca.mcgill.sel.ram.ui.views.structural.handler.IInstantiationsContainerViewHandler;
import ca.mcgill.sel.ram.util.RAMModelUtil;

/**
 * Handles events for a {@link ca.mcgill.sel.ram.ui.views.structural.InstantiationsContainerView} which is showing the
 * list of instantiations in an aspect.
 * 
 * @author eyildirim
 * @author oalam
 */
public class AspectExtensionsContainerViewHandler implements IInstantiationsContainerViewHandler {
    /**
     * This method deletes a given instantiation. If the instantiation is of type
     * DEPENDS, it finds its {@link ca.mcgill.sel.core.COREReuse} and calls {@link deleteReuseInstantiation}.
     * Otherwise, if the instantiation is of type EXTENDs, it simply deletes the instantiation.
     * 
     * @param instantiation the instantiation to be deleted.
     */
    @Override
    public void deleteInstantiation(Instantiation instantiation) {
        // Disallow deleting the instantiation if split view is enabled.
        boolean splitModeEnabled =
                RamApp.getActiveAspectScene().getCurrentView() instanceof InstantiationSplitEditingView;
        if (!splitModeEnabled) {
            AspectController controller = ControllerFactory.INSTANCE.getAspectController();
            controller.deleteInstantiation(instantiation);
        } else {
            RamApp.getActiveAspectScene().displayPopup(Strings.POPUP_CANT_DELETE_INST_EDIT);
        }
    }

    @Override
    public void loadBrowser(final Aspect mainAspect) {

        // Ask the user to load an aspect
        AspectFileBrowser.loadAspect(new AspectFileBrowserListener() {

            @Override
            public void aspectLoaded(final Aspect aspect) {
                extendAspect(mainAspect, aspect);
            }

            @Override
            public void aspectSaved(File file) {
            }
        });
    }

    /**
     * Create an extend relationship between the given aspect.
     * Does nothing if the creation is not valid.
     *
     * @param extendingAspect - The aspect that extends
     * @param aspect - The aspect to extend.
     */
    private static void extendAspect(final Aspect extendingAspect, final Aspect aspect) {
        RamApp.getApplication().invokeLater(new Runnable() {
            @Override
            public void run() {
                String errorMessage = null;
                // Look for error cases.
                if (aspect.equals(extendingAspect)) {
                    errorMessage = Strings.POPUP_ERROR_SELF_EXTENDS;
                } else if (RAMModelUtil.collectExtendedAspects(aspect).contains(extendingAspect)) {
                    errorMessage = Strings.POPUP_ERROR_CYCLIC_EXTENDS;
                }
                // Check if this aspect is already extended. Cannot use collectExtendedAspects
                // because we don't want the check to be recursive here.
                for (Instantiation instantiation : extendingAspect.getInstantiations()) {
                    if (instantiation.getType() == InstantiationType.EXTENDS
                            && instantiation.getSource() == aspect) {
                        errorMessage = Strings.POPUP_ERROR_SAME_EXTENDS;
                        break;
                    }
                }

                if (errorMessage != null) {
                    RamApp.getActiveScene().displayPopup(errorMessage, PopupType.ERROR);
                    return;
                }

                // We can create the instantiation.
                AspectController controller = ControllerFactory.INSTANCE.getAspectController();
                controller.createInstantiation(extendingAspect, aspect);
            }
        });
    }

}
