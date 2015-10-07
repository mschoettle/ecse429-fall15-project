package ca.mcgill.sel.ram.ui.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.core.COREConcern;
import ca.mcgill.sel.core.COREModel;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamPopup.PopupType;
import ca.mcgill.sel.ram.ui.components.browser.AspectFileBrowser;
import ca.mcgill.sel.ram.ui.components.browser.interfaces.AspectFileBrowserListener;

/**
 * Implementation of stateless basic action.
 * 
 * @author g.Nicolas
 *
 */
public final class BasicActionsUtils {

    /**
     * Creates a new instance.
     */
    private BasicActionsUtils() {

    }

    /**
     * Call the undo command on the stack.
     * 
     * @param element - the aspect/concern to save
     */
    public static void undo(EObject element) {
        if (element != null) {
            EMFEditUtil.getCommandStack(element).undo();
        }
    }

    /**
     * Call the redo command on the stack.
     * 
     * @param element - the aspect/concern to save
     */
    public static void redo(EObject element) {
        if (element != null) {
            EMFEditUtil.getCommandStack(element).redo();
        }
    }

    /**
     * Saves a concern with all its models.
     *
     * @param concern the concern to save
     */
    public static void saveConcern(COREConcern concern) {
        BasicCommandStack commandStack = EMFEditUtil.getCommandStack(concern);

        Map<Object, Object> saveOptions = new HashMap<Object, Object>();
        saveOptions.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED, Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);

        try {
            // Save the concern
            concern.eResource().save(saveOptions);
            commandStack.saveIsDone();

            // Build a list of concern models and realized by of the feature.
            Set<COREModel> models = new HashSet<COREModel>(concern.getModels());
            for (COREModel model : models) {
                // Save only if changed.
                try {
                    model.eResource().save(saveOptions);
                    BasicCommandStack modelStack = EMFEditUtil.getCommandStack(model);
                    modelStack.saveIsDone();
                } catch (NullPointerException npe) {
                    // TODO temp fix to prevent errors during save if models can't be saved.
                    // Should handle it by removing all references to this model in the concern.
                    LoggerUtils.error("Model '" + model + "' couldn't be saved. The file may not have been found.");
                }
            }
            // CHECKSTYLE:IGNORE IllegalCatch: Many exceptions can occur and we don't want to crash the application.
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves an aspect. Presents a file browser to allow to specify the file if it has not been saved before already.
     * Otherwise just override the file. Optionally, a listener can be passed in order to be informed when the aspect
     * was saved. If the listener doesn't get called, the file browser was cancelled.
     *
     * @param aspect the aspect to save
     * @param listener the {@link ca.mcgill.sel.ram.ui.components.browser.interfaces.RamFileBrowserListener} that should
     *            be informed when the aspect was saved, null if not interested
     */
    public static void saveAspect(final Aspect aspect, final AspectFileBrowserListener listener) {

        // Already saved
        if (aspect.eResource() != null) {
            try {
                File file = new File(aspect.eResource().getURI().toFileString());

                final Map<Object, Object> saveOptions = new HashMap<Object, Object>();
                saveOptions.put(
                        Resource.OPTION_SAVE_ONLY_IF_CHANGED,
                        Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);

                aspect.eResource().save(saveOptions);

                BasicCommandStack commandStack = EMFEditUtil.getCommandStack(aspect);
                commandStack.saveIsDone();
                
                // Save the concern
                COREConcern concern = aspect.getCoreConcern();
                if (concern != null) {
                    concern.eResource().save(saveOptions);
                    BasicCommandStack concernCommandStack = EMFEditUtil.getCommandStack(concern);
                    concernCommandStack.saveIsDone();
                }

                // Inform the listener to be able to do some additional tasks.
                if (listener != null) {
                    listener.aspectSaved(file);
                }
            } catch (IOException e) {
                RamApp.getActiveAspectScene()
                        .displayPopup(Strings.POPUP_ERROR_FILE_SAVE + e.getMessage(), PopupType.ERROR);
            }
        } else {
            saveAspectAs(aspect, listener);
        }
    }

    /**
     * Saves an aspect. Presents a file browser to allow to specify the file. Optionally, a listener can be passed in
     * order to be informed when the aspect was saved. If the listener doesn't get called, the file browser was
     * cancelled.
     *
     * @param aspect - the aspect to save
     * @param listener the {@link ca.mcgill.sel.ram.ui.components.browser.interfaces.RamFileBrowserListener} that should
     *            be informed when the aspect was saved, null if not interested
     */
    public static void saveAspectAs(final Aspect aspect, final AspectFileBrowserListener listener) {

        AspectFileBrowser.saveAspect(aspect, new AspectFileBrowserListener() {
            @Override
            public void aspectSaved(File file) {
                try {
                    // Tell the command stack that the aspect was saved.
                    BasicCommandStack commandStack = EMFEditUtil.getCommandStack(aspect);
                    commandStack.saveIsDone();
                    // Save the concern
                    COREConcern concern = aspect.getCoreConcern();
                    if (concern != null) {
                        Map<Object, Object> saveOptions = new HashMap<Object, Object>();
                        saveOptions.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED,
                                Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);
                        concern.eResource().save(saveOptions);
                        BasicCommandStack concernCommandStack = EMFEditUtil.getCommandStack(concern);
                        concernCommandStack.saveIsDone();
                    }
                    // CHECKSTYLE:IGNORE IllegalCatch: Many exceptions can occur and we don't want to crash the app
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // Inform the listener to be able to do some additional tasks.
                if (listener != null) {
                    listener.aspectSaved(file);
                }
            }
            @Override
            public void aspectLoaded(Aspect aspect) {
            }
        });
    }

}
