package ca.mcgill.sel.ram.ui.scenes.handler.impl;

import org.eclipse.emf.ecore.EObject;

import ca.mcgill.sel.core.COREConcern;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.ui.scenes.handler.IRamAbstractSceneHandler;
import ca.mcgill.sel.ram.ui.utils.BasicActionsUtils;
import ca.mcgill.sel.ram.ui.views.handler.BaseHandler;

/**
 * Handler implementation to define actions on the basics commands in the menu.
 * 
 * @author g.Nicolas
 *
 */
public class DefaultRamSceneHandler extends BaseHandler implements IRamAbstractSceneHandler {

    @Override
    public void undo(EObject element) {
        BasicActionsUtils.undo(element);
    }

    @Override
    public void redo(EObject element) {
        BasicActionsUtils.redo(element);
    }

    @Override
    public void save(EObject element) {
        if (element instanceof COREConcern) {
            BasicActionsUtils.saveConcern((COREConcern) element);
        } else if (element instanceof Aspect) {
            BasicActionsUtils.saveAspect((Aspect) element, null);
        }
    }

}
