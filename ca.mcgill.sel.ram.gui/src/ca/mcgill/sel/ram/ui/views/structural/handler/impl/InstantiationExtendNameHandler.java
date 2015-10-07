package ca.mcgill.sel.ram.ui.views.structural.handler.impl;

import java.io.File;

import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;

import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.Instantiation;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.ConfirmPopup;
import ca.mcgill.sel.ram.ui.components.ConfirmPopup.OptionType;
import ca.mcgill.sel.ram.ui.components.ConfirmPopup.SelectionListener;
import ca.mcgill.sel.ram.ui.components.browser.interfaces.AspectFileBrowserListener;
import ca.mcgill.sel.ram.ui.scenes.DisplayAspectScene;
import ca.mcgill.sel.ram.ui.utils.BasicActionsUtils;

/**
 * The default handler for an instantiation name of an extended aspect.
 * 
 * @author eyildirim
 */
public class InstantiationExtendNameHandler extends InstantiationDefaultNameHandler {

    @Override
    public boolean processTapEvent(TapEvent tapEvent) {
        // do nothing
        return true;
    }

    @Override
    protected void openAspect(final Instantiation instantiation) {
        final DisplayAspectScene scene = RamApp.getActiveAspectScene();

        // If the aspect is new and has not been saved we have to save it first.
        if (scene != null && scene.getAspect().eResource() == null) {
            SelectionListener selectionListener = new ConfirmPopup.SelectionListener() {
                @Override
                public void optionSelected(int selectedOption) {
                    if (selectedOption == ConfirmPopup.YES_OPTION) {
                        BasicActionsUtils.saveAspect(scene.getAspect(), new AspectFileBrowserListener() {
                            @Override
                            public void aspectSaved(File file) {
                                InstantiationExtendNameHandler.super.openAspect(instantiation);
                            }
                            @Override
                            public void aspectLoaded(Aspect aspect) {
                            }
                        });
                    }
                }
            };
            scene.showCloseConfirmPopup(scene, selectionListener, OptionType.YES_CANCEL);
        } else {
            super.openAspect(instantiation);
        }
    }
}
