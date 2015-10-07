package ca.mcgill.sel.ram.ui.views.structural.handler;

import ca.mcgill.sel.ram.Instantiation;
import ca.mcgill.sel.ram.ui.events.listeners.IDragListener;
import ca.mcgill.sel.ram.ui.events.listeners.ITapListener;
import ca.mcgill.sel.ram.ui.views.structural.InstantiationsPanel;

/**
 * This interface can be implemented by a handler that can handle events for the container of
 * {@link ca.mcgill.sel.ram.ui.views.structural.InstantiationsContainerView} which is showing the list of instantiations
 * in an aspect.
 * 
 * @author eyildirim
 */
public interface IInstantiationsPanelHandler extends ITapListener, IDragListener {

    /**
     * Handles switching back from the instantiation split mode.
     * This function is used to rolling back the changes of instantiation container view
     * (such as adding a mode switch button, showing only the title of the instantiation
     * which is being edited) due to switching the mode of the scene to split instantiation editing mode.
     * 
     * @param instantiationsContainerView
     *            the view for which we are doing all changes
     * @param instantiation
     *            the {@link Instantiation} which is going to be put into edit mode.
     */
    void switchFromInstantiationSplitEditMode(InstantiationsPanel instantiationsContainerView,
            Instantiation instantiation);

    /**
     * Handles switching to the instantiation split mode.
     * This function is used to make changes to instantiation container view
     * (such as adding a mode switch button, showing only the title of the instantiation which is
     * being edited) while switching the mode of the scene to split instantiation editing mode.
     * 
     * @param instantiationsContainerView
     *            the view for which we are doing all changes
     * @param instantiation
     *            the {@link Instantiation} which is not going to be in the edit mode anymore.
     */
    void switchToInstantiationSplitEditMode(InstantiationsPanel instantiationsContainerView,
            Instantiation instantiation);
}
