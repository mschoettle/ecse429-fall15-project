package ca.mcgill.sel.ram.ui.views.structural.handler;

import ca.mcgill.sel.ram.ui.events.listeners.ITapListener;
import ca.mcgill.sel.ram.ui.views.structural.InstantiationView;

/**
 * This interface can be implemented by an handler which handles the operations of an instantiation view such as
 * classifier mapping adding/removing.
 * 
 * @author eyildirim
 */
public interface IInstantiationViewHandler extends ITapListener {
    
    /**
     * This function is used to delete an instantiation from the aspect.
     * 
     * @param myInstantiationView the instantiation
     */
    void addClassifierMapping(InstantiationView myInstantiationView);
    
    /**
     * Hides all classifier mapping details.
     * 
     * @param instantiationView the instantiation view
     */
    void hideMappingDetails(InstantiationView instantiationView);
    
    /**
     * Loads the external Aspect in full mode.
     * 
     * @param myInstantiationView the instantiation
     */
    void showExternalAspectOfInstantiation(InstantiationView myInstantiationView);
    
    /**
     * Shows all classifier mapping details.
     * 
     * @param instantiationView the instantiation view
     */
    void showMappingDetails(InstantiationView instantiationView);
    
    /**
     * Switches to the split view for mapping specifications.
     * 
     * @param myInstantiationView the instantiation
     */
    void switchToSplitView(InstantiationView myInstantiationView);
    
}
