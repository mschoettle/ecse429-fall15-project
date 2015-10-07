package ca.mcgill.sel.ram.ui.scenes.handler;

import org.eclipse.emf.ecore.EObject;

/**
 * Handler interface for basic actions of the menu.
 * 
 * @author g.Nicolas
 *
 */
public interface IRamAbstractSceneHandler {

    /**
     * Interface call to save the current aspect/concern.
     * 
     * @param element - the aspect/concern to save
     */
    void save(EObject element);

    /**
     * Interface call for calling the undo command on the stack.
     * 
     * @param element - the aspect/concern to save
     */
    void undo(EObject element);

    /**
     * Interface call for calling the redo command on the stack.
     * 
     * @param element - the aspect/concern to save
     */
    void redo(EObject element);

}