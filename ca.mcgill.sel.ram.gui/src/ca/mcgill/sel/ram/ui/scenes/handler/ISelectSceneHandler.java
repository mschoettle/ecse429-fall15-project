package ca.mcgill.sel.ram.ui.scenes.handler;

import ca.mcgill.sel.ram.ui.scenes.SelectAspectScene;

/**
 * This interface is implemented by something that can handle events for a {@link SelectAspectScene}.
 * 
 * @author mschoettle
 */
public interface ISelectSceneHandler extends IRamAbstractSceneHandler {
    
    /**
     * Handles creating a new concern {@link Concern}.
     * @param scene
     *            the affected {@link SelectAspectScene}
     */
    void createConcernDisplay(SelectAspectScene scene);
    
    /**
     * Handles loading an existing concern to edit it. {@link Concern}
     * @param scene
     *            the affected {@link SelectAspectScene}.
     */
    void loadConcernDisplay(SelectAspectScene scene);
    
}
