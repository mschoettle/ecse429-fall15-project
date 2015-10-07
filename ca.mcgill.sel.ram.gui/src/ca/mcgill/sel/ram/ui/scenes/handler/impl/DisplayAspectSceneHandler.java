package ca.mcgill.sel.ram.ui.scenes.handler.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.mt4j.sceneManagement.transition.SlideTransition;
import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.commons.emf.util.CORECommandStack;
import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.core.COREConcern;
import ca.mcgill.sel.core.COREFeature;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.Instantiation;
import ca.mcgill.sel.ram.generator.cpp.CppGenerator;
import ca.mcgill.sel.ram.generator.java.JavaGenerator;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.ConfirmPopup;
import ca.mcgill.sel.ram.ui.components.RamPopup;
import ca.mcgill.sel.ram.ui.components.RamPopup.PopupType;
import ca.mcgill.sel.ram.ui.components.RamSelectorComponent;
import ca.mcgill.sel.ram.ui.components.browser.AspectFileBrowser;
import ca.mcgill.sel.ram.ui.components.browser.RamFileBrowser;
import ca.mcgill.sel.ram.ui.components.browser.RamFileBrowser.RamFileBrowserType;
import ca.mcgill.sel.ram.ui.components.browser.interfaces.AspectFileBrowserListener;
import ca.mcgill.sel.ram.ui.components.browser.interfaces.RamFileBrowserListener;
import ca.mcgill.sel.ram.ui.components.listeners.AbstractDefaultRamSelectorListener;
import ca.mcgill.sel.ram.ui.scenes.DisplayAspectScene;
import ca.mcgill.sel.ram.ui.utils.BasicActionsUtils;
import ca.mcgill.sel.ram.ui.utils.Constants;
import ca.mcgill.sel.ram.ui.utils.LoggerUtils;
import ca.mcgill.sel.ram.ui.utils.Strings;
import ca.mcgill.sel.ram.ui.views.OptionSelectorView;
import ca.mcgill.sel.ram.ui.views.handler.IDisplaySceneHandler;
import ca.mcgill.sel.ram.ui.views.structural.InstantiationSplitEditingView;
import ca.mcgill.sel.ram.ui.views.structural.InstantiationView;
import ca.mcgill.sel.ram.ui.views.structural.InstantiationsPanel;
import ca.mcgill.sel.ram.ui.views.structural.StructuralDiagramView;
import ca.mcgill.sel.ram.ui.views.structural.handler.IInstantiationsPanelHandler;
import ca.mcgill.sel.ram.util.RAMModelUtil;

/**
 * The default handler for a {@link DisplayAspectScene}.
 *
 * @author mschoettle
 */
public class DisplayAspectSceneHandler extends DefaultRamSceneHandler implements IDisplaySceneHandler {

    /**
     * The listener for the generate code selector.
     */
    private final class GenerateCodeSelectorListener extends AbstractDefaultRamSelectorListener<GenerateCodeOptions> {

        private final DisplayAspectScene scene;

        /**
         * Creates a new generate code selector listener.
         *
         * @param scene the current {@link DisplayAspectScene}
         */
        private GenerateCodeSelectorListener(DisplayAspectScene scene) {
            this.scene = scene;
        }

        @Override
        public void elementSelected(
                RamSelectorComponent<GenerateCodeOptions> selector,
                final GenerateCodeOptions element) {

            RamFileBrowser browser = new RamFileBrowser(RamFileBrowserType.FOLDER, "", lastGeneratorSharedFolder);
            browser.setCallbackThreaded(true);
            browser.setCallbackPopupMessage(Strings.POPUP_GENERATING);
            // CHECKSTYLE:IGNORE AnonInnerLength: Okay here.
            browser.addFileBrowserListener(new RamFileBrowserListener() {

                @Override
                public void fileSelected(final File folder, RamFileBrowser fileBrowser) {
                    try {
                        if (!folder.canWrite()) {
                            throw new IOException("Permission denied");
                        }

                        switch (element) {
                            case JAVA:
                                JavaGenerator generator = new JavaGenerator(scene.getAspect(), folder,
                                        new ArrayList<Object>());
                                generator.doGenerate(null);
                                break;
                            case CPP:
                                CppGenerator generator2 = new CppGenerator(scene.getAspect(), folder,
                                        new ArrayList<Object>());
                                generator2.doGenerate(null);
                                break;
                        }

                        RamApp.getApplication().invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                scene.displayPopup(new RamPopup(Strings.popupCodeGenerated(folder.getName()),
                                        true, PopupType.SUCCESS));
                            }
                        });

                        lastGeneratorSharedFolder = folder;
                        // CHECKSTYLE:IGNORE IllegalCatch FOR 1 LINES: Need to catch all types
                    } catch (Exception e) {
                        e.printStackTrace();
                        scene.displayPopup(new RamPopup(Strings.POPUP_GENERATION_ERROR + e.getMessage(),
                                true, PopupType.ERROR));
                    }
                }
            });
            browser.display();
        }
    }

    /**
     * Code generator target languages.
     */
    private enum GenerateCodeOptions {
        JAVA {
            @Override
            public String toString() {
                return Strings.OPT_GENERATE_JAVA;
            }
        },
        CPP {
            @Override
            public String toString() {
                return Strings.OPT_GENERATE_CPP;
            }
        }
    }

    private static File lastGeneratorSharedFolder = new File(Constants.DIRECTORY_MODELS).getAbsoluteFile();

    @Override
    public void generateCode(final DisplayAspectScene scene) {

        // Create the selector
        OptionSelectorView<GenerateCodeOptions> selector =
                new OptionSelectorView<GenerateCodeOptions>(GenerateCodeOptions.values());
        RamApp.getActiveAspectScene().addComponent(selector, scene.getMenu().getCenterPointGlobal());

        selector.registerListener(new GenerateCodeSelectorListener(scene));
    }

    @Override
    public void loadAspect(DisplayAspectScene scene) {

        // Transition to the right
        scene.setTransition(new SlideTransition(RamApp.getApplication(), 700, true));

        // Ask the user to load an aspect
        AspectFileBrowser.loadAspect(new AspectFileBrowserListener() {

            @Override
            public void aspectLoaded(Aspect aspect) {
                RamApp.getApplication().loadAspect(aspect);
            }

            @Override
            public void aspectSaved(File file) {
            }
        });
    }

    @Override
    public void showValidation(DisplayAspectScene scene) {
        scene.showValidation(true);
    }

    @Override
    public void showTracing(DisplayAspectScene scene) {
        scene.showTracing(true);
    }

    @Override
    public void back(DisplayAspectScene scene) {
        scene.switchToPreviousView();
    }

    @Override
    public void switchToInstantiationEditMode(final DisplayAspectScene scene,
            final InstantiationView instantiationView) {
        Instantiation instantiation = instantiationView.getInstantiation();
        // First try to load the scene to make sure it can be loaded, then do the rest.
        RamApp application = RamApp.getApplication();
        DisplayAspectScene loadedScene = application.getExistingOrCreateAspectScene(instantiation.getSource());

        if (loadedScene != null) {
            InstantiationSplitEditingView splitView = new InstantiationSplitEditingView(scene, loadedScene,
                    instantiation);

            scene.switchToView(splitView);

            // Let the container view know about mode change. it will do operations like adding a check button to the
            // instantiations container to get the command of closing split view,
            // showing only the title of the instantiation that it being edited etc..
            InstantiationsPanel container = scene.getInstantiationsContainerViewContainerView();
            IInstantiationsPanelHandler handler = container.getHandler();
            handler.switchToInstantiationSplitEditMode(container, instantiation);

        } else {
            scene.displayPopup(Strings.popupAspectMalformed(instantiation.getSource().getName()), PopupType.ERROR);
        }
    }

    @Override
    public void switchToMenu(DisplayAspectScene scene) {
        // to the left!
        scene.setTransition(new SlideTransition(RamApp.getApplication(), 700, false));
        // go to SelectAspectScene
        RamApp.getApplication().switchToBackground(scene);
    }

    @Override
    public void toggleView(DisplayAspectScene scene) {
        scene.toggleView();
    }

    @Override
    public void weaveAll(DisplayAspectScene scene) {
        
    }

    @Override
    public void weaveStateMachines(DisplayAspectScene scene) {
        
    }

    @Override
    public void weaveAllNoCSPForStateViews(DisplayAspectScene scene) {
        
    }

    /**
     * Removes the {@link InstantiationSplitEditingView} from the scene.
     * In addition it takes care of any additional steps that are required to remove the splitting of two
     * structural views.
     *
     * @param scene the affected {@link DisplayAspectScene}
     */
    private static void removeInstantiationSplitView(DisplayAspectScene scene) {
        InstantiationSplitEditingView removedSplitView = (InstantiationSplitEditingView) scene.getCurrentView();

        // Update istantiation containers
        scene.getInstantiationsContainerViewContainerView().getHandler().switchFromInstantiationSplitEditMode(
                scene.getInstantiationsContainerViewContainerView(), removedSplitView.getInstantiation());

        // --------<Changes related to relocationing of the structural diagram views>

        /**
         * This puts both higher and lower level {@link Aspect}'s {@link StructuralDiagramView}s back to their previous
         * location (to wherever they belonged before enabling the split view. They belong to a layer within their
         * {@link DisplayAspectScene} ).
         */
        removedSplitView.getHandler().doFinalOperationsBeforeClosing();
        DisplayAspectScene lowerLevelAspectScene = removedSplitView.getDisplayAspectSceneLowerLevel();
        StructuralDiagramView higherStructuralDiagramView =
                removedSplitView.getDisplayAspectSceneHigherLevel().getStructuralDiagramView();
        StructuralDiagramView lowerStructuralDiagramView =
                removedSplitView.getDisplayAspectSceneLowerLevel().getStructuralDiagramView();

        // Switch back to the previous views in both scenes.
        scene.switchToPreviousView();
        lowerLevelAspectScene.switchToPreviousView();

        // In the split instantiation edit mode Lower level aspect's structural diagram view repositioned to the half
        // bottom of the
        // screen (or somewhere else if the functionality of moving up and down is added later), so we need to
        // reposition it before putting
        // it back to its original place in display aspect scene.
        lowerStructuralDiagramView.setPositionGlobal(new Vector3D(0, 0));

        // -------- </Changes related to relocationing of the structural diagram views>

        // change to default colors, remove highlights
        lowerStructuralDiagramView.setNoFill(true);

        // TODO: eyildirim: splitView.destroy --> dont forget to remove the listeners related to it..
        // we don't need the split view anymore.

        // Remove the structural views from the split view before it gets destroyed,
        // in case they are still a child.
        // The check whether it is contained as a child is required, because
        // removeChild automatically sets the parent to null, even if it is not
        // a child of that component.
        if (removedSplitView.containsChild(higherStructuralDiagramView)) {
            removedSplitView.removeChild(higherStructuralDiagramView);
        }

        if (removedSplitView.containsChild(lowerStructuralDiagramView)) {
            removedSplitView.removeChild(lowerStructuralDiagramView);
        }

        removedSplitView.destroy();
        RamApp.getApplication().closeAspectScene(lowerLevelAspectScene);
    }

    @Override
    public void switchToConcern(final DisplayAspectScene scene) {
        boolean isSaveNeeded = EMFEditUtil.getCommandStack(scene.getAspect()).isSaveNeeded();
        if (isSaveNeeded) {
            showCloseConfirmPopup(scene);
        } else {
            checkForModelRemoval(scene);
            doSwitchToPreviousScene(scene);
        }
    }

    /**
     * Display a popup for the user to decided whether he wants to save the aspect or leave the scene.
     *
     * @param scene - The {@link DisplayAspectScene} to consider
     */
    protected void showCloseConfirmPopup(final DisplayAspectScene scene) {
        scene.showCloseConfirmPopup(scene, new ConfirmPopup.SelectionListener() {

            @Override
            public void optionSelected(int selectedOption) {
                if (selectedOption == ConfirmPopup.YES_OPTION) {
                    BasicActionsUtils.saveAspect(scene.getAspect(), new AspectFileBrowserListener() {
                        @Override
                        public void aspectSaved(File file) {
                            doSwitchToPreviousScene(scene);
                        }

                        @Override
                        public void aspectLoaded(Aspect aspect) {
                        }
                    });
                } else if (selectedOption == ConfirmPopup.NO_OPTION) {
                    if (EMFEditUtil.getCommandStack(scene.getAspect()) instanceof CORECommandStack) {
                        CORECommandStack stack = (CORECommandStack) EMFEditUtil.getCommandStack(scene.getAspect());
                        stack.goToLastSave();
                    } else {
                        LoggerUtils.warn("The CommandStack for " + scene.getAspect()
                                + " should be an instance of CORECommandStack.");
                    }

                    checkForModelRemoval(scene);
                    doSwitchToPreviousScene(scene);
                }
            }
        });
    }

    /**
     * Performs the switching to the concern scene.
     * Unloads the resource and triggers the scene change to the previous scene.
     *
     * @param scene the current aspect scene
     */
    protected void doSwitchToPreviousScene(DisplayAspectScene scene) {
        // Unload WovenAspect of this aspect when we leave
        RAMModelUtil.unloadExternalResources(scene.getAspect());

        scene.setTransition(new SlideTransition(RamApp.getApplication(), 700, false));

        scene.getApplication().changeScene(scene.getPreviousScene());
        scene.getApplication().destroySceneAfterTransition(scene);
    }

    /**
     * Checks whether the model needs to be removed from the concern.
     * The model is removed, if it was not saved and therefore not contained anywhere.
     *
     * @param scene the current aspect scene
     */
    protected static void checkForModelRemoval(DisplayAspectScene scene) {
        Aspect aspect = scene.getAspect();

        // That is the aspect is not saved at all.
        if (aspect.eResource() == null) {
            // Get all the features realising it
            List<COREFeature> listOfFeatures = aspect.getRealizes();
            List<COREFeature> copyOfListOfFeature = new ArrayList<COREFeature>(listOfFeatures);

            // Loop through all the features and remove the realization
            for (COREFeature feature : copyOfListOfFeature) {
                // Do a pre-mature check to see if the feature realises the aspect,
                // Can exist scenario, where the bi directional link might not be true
                if (feature.getRealizedBy().contains(aspect)) {
                    feature.getRealizedBy().remove(aspect);
                }
            }

            COREConcern concern = aspect.getCoreConcern();

            // Remove from the list of models
            if (concern != null) {
                concern.getModels().remove(aspect);
            }
        }
    }

    @Override
    public void closeSplitView(DisplayAspectScene scene) {
        if (scene.getCurrentView() instanceof InstantiationSplitEditingView) {
            removeInstantiationSplitView(scene);
        }
    }
}
