package ca.mcgill.sel.ram.ui.scenes.handler.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mt4j.sceneManagement.transition.SlideTransition;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.core.COREConcern;
import ca.mcgill.sel.core.COREFeature;
import ca.mcgill.sel.core.COREModel;
import ca.mcgill.sel.core.controller.ControllerFactory;
import ca.mcgill.sel.core.util.COREModelUtil;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.Instantiation;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.ConfirmPopup;
import ca.mcgill.sel.ram.ui.scenes.DisplayConcernEditScene;
import ca.mcgill.sel.ram.ui.scenes.handler.IConcernEditSceneHandler;
import ca.mcgill.sel.ram.ui.utils.Strings;
import ca.mcgill.sel.ram.util.RAMModelUtil;

/**
 * Handler implementation to catch actions on the concern scene in edit mode.
 *
 * @author Nishanth
 */
public class ConcernEditSceneHandler extends DefaultRamSceneHandler implements IConcernEditSceneHandler {

    @Override
    public void switchToHome(final DisplayConcernEditScene scene) {
        boolean isSaveNeeded = EMFEditUtil.getCommandStack(scene.getConcern()).isSaveNeeded();
        if (isSaveNeeded) {
            scene.showCloseConfirmPopup(scene, new ConfirmPopup.SelectionListener() {

                @Override
                public void optionSelected(int selectedOption) {
                    if (selectedOption == ConfirmPopup.YES_OPTION) {
                        save(scene.getConcern());
                        doSwitchToHome(scene);
                    } else if (selectedOption == ConfirmPopup.NO_OPTION) {
                        doSwitchToHome(scene);
                    }
                }
            });
        } else {
            doSwitchToHome(scene);
        }
    }

    /**
     * Performs the switching to home. Unloads the resources and switches the scene to the background scene.
     *
     * @param scene the current scene
     */
    @SuppressWarnings("static-method")
    private void doSwitchToHome(DisplayConcernEditScene scene) {
        scene.unLoadAllResources();
        scene.setTransition(new SlideTransition(RamApp.getApplication(), 700, false));
        scene.getApplication().switchToBackground(scene);
        scene.getApplication().destroySceneAfterTransition(scene);
    }

    @Override
    public void createImpactModel(final DisplayConcernEditScene scene) {
        
    }

    @Override
    public void createAspect(DisplayConcernEditScene scene) {

        Aspect aspect = RAMModelUtil.createAspect(Strings.DEFAULT_ASPECT_NAME, scene.getConcern());

        ControllerFactory.INSTANCE.getConcernController().addModelToConcern(scene.getConcern(), aspect);

        scene.setTransition(new SlideTransition(RamApp.getApplication(), 700, true));

        RamApp.getApplication().loadAspect(aspect);

    }

    @Override
    public void deleteAspect(DisplayConcernEditScene scene, Aspect aspect) {
        COREConcern concern = scene.getConcern();

        // Remove aspect from the concern
        concern.getModels().remove(aspect);

        // Remove realizations for this aspect
        for (COREFeature feature : concern.getFeatureModel().getFeatures()) {
            if (feature.getRealizedBy().contains(aspect)) {
                feature.getRealizedBy().remove(aspect);
            }
        }

        // Remove model reuses
        for (COREModel model : concern.getModels()) {
            model.getModelReuses().removeAll(aspect.getModelReuses());
        }

        // Remove instantiations for this aspect
        for (COREModel model : concern.getModels()) {
            if (model instanceof Aspect) {
                Aspect a = (Aspect) model;
                List<Instantiation> toRemove = new ArrayList<Instantiation>();
                for (Instantiation instantiation : a.getInstantiations()) {
                    if (instantiation.getSource().equals(aspect)) {
                        toRemove.add(instantiation);
                    }
                }
                a.getInstantiations().removeAll(toRemove);
            }
        }

        File fileToBeDeleted = new File(aspect.eResource().getURI().toFileString());
        fileToBeDeleted.delete();

        COREModelUtil.unloadEObject(aspect);

        // save everything
        save(scene.getConcern());
    }

}
