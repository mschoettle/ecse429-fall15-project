package ca.mcgill.sel.ram.ui.views.handler;

import ca.mcgill.sel.ram.ui.scenes.DisplayAspectScene;
import ca.mcgill.sel.ram.ui.scenes.handler.IRamAbstractSceneHandler;
import ca.mcgill.sel.ram.ui.views.structural.InstantiationView;

/**
 * This interface is implemented by something that can handle events for a {@link DisplayAspectScene}.
 * 
 * @author mschoettle
 */
public interface IDisplaySceneHandler extends IRamAbstractSceneHandler {

    /**
     * Generates code.
     * 
     * @param displayAspectScene the affected {@link DisplayAspectScene}
     */
    void generateCode(DisplayAspectScene displayAspectScene);

    /**
     * Handles loading of a new aspect.
     * 
     * @param scene
     *            the affected {@link DisplayAspectScene}
     */
    void loadAspect(DisplayAspectScene scene);

    /**
     * Opens or closes the automatic validation for the {@link DisplayAspectScene}.
     * 
     * @param scene
     *            the affected {@link DisplayAspectScene}
     */
    void showValidation(DisplayAspectScene scene);

    /**
     * Opens or closes the tracing for the {@link DisplayAspectScene}.
     * 
     * @param scene
     *            the affected {@link DisplayAspectScene}
     */
    void showTracing(DisplayAspectScene scene);


    /**
     * Handles going back from the current view to the previous view.
     * 
     * @param scene
     *            the affected {@link DisplayAspectScene}
     */
    void back(DisplayAspectScene scene);

    /**
     * Handles switching into split instantiation edit mode (the split view where you can do mappings).
     * 
     * @param scene
     *            the affected
     * @param instantiationView
     *            the instantiation view for which we will create an edit mode.
     */
    void switchToInstantiationEditMode(DisplayAspectScene scene, InstantiationView instantiationView);

    /**
     * Handles switching to the menu.
     * 
     * @param scene
     *            the affected {@link DisplayAspectScene}
     */
    void switchToMenu(DisplayAspectScene scene);

    /**
     * Handles toggling of the shown views.
     * 
     * @param scene
     *            the affected {@link DisplayAspectScene}
     */
    void toggleView(DisplayAspectScene scene);

    /**
     * Handles weaving of the complete aspect hierarchy.
     * 
     * @param scene
     *            the affected {@link DisplayAspectScene}
     */
    void weaveAll(DisplayAspectScene scene);

    /**
     * Handles weaving of the state machines inside an aspect.
     *
     * @param scene - The affected {@link DisplayAspectScene}
     */
    void weaveStateMachines(DisplayAspectScene scene);

    /**
     * Handles weaving of the complete aspect hierarchy Without applying CSP composition to the state machines of the
     * state views.
     *
     * @param scene the affected {@link DisplayAspectScene}
     */
    void weaveAllNoCSPForStateViews(DisplayAspectScene scene);

    /**
     * Handles the switching to concern when the user wants to return back
     * to the concern scene he was editing.
     *
     * @param displayAspectScene the affected {@link DisplayAspectScene}
     */
    void switchToConcern(DisplayAspectScene displayAspectScene);

    /**
     * Handles the closinh of the split view.
     * 
     * @param displayAspectScene - The current scene
     */
    void closeSplitView(DisplayAspectScene displayAspectScene);

}
