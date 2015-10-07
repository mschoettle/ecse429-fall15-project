package ca.mcgill.sel.ram.ui.views.structural.handler;

import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.Instantiation;

/**
 * This interface can be implemented by a handler that can handle events for a
 * {@link ca.mcgill.sel.ram.ui.views.structural.InstantiationsContainerView} which is showing the list of
 * instantiations in an aspect.
 * 
 * @author eyildirim
 */
public interface IInstantiationsContainerViewHandler {

    /**
     * Deletes an instantiation from the aspect.
     * 
     * @param instantiation the instantiation to delete
     */
    void deleteInstantiation(Instantiation instantiation);

    /**
     * Loads the aspect browser.
     * 
     * @param aspect the current aspect
     */
    void loadBrowser(Aspect aspect);

}
