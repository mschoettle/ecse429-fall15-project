package ca.mcgill.sel.ram.ui.views.structural.handler.impl;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import ca.mcgill.sel.ram.Classifier;
import ca.mcgill.sel.ram.Operation;
import ca.mcgill.sel.ram.RAMVisibilityType;
import ca.mcgill.sel.ram.controller.ControllerFactory;
import ca.mcgill.sel.ram.ui.views.handler.impl.TextViewHandler;

/**
 * The default handler for a {@link OperationVisibilityViewHandler}. It shows a selector whith the different 
 * visibilities as defined in {@link RAMVisibilityType}
 * 
 * @author oalam
 * */

public class OperationVisibilityViewHandler extends TextViewHandler {
    
    /**
     * Sets the value on the data object for the given feature.
     * 
     * @param data the object containing the feature
     * @param feature the feature of which the value should be changed
     * @param value the new value of the feature
     */
    @Override
    protected void setValue(EObject data, EStructuralFeature feature, Object value) {
        Operation op = (Operation) data;
        RAMVisibilityType visibility = (RAMVisibilityType) value;
        ControllerFactory.INSTANCE.getClassController().changeOperationAndClassVisibility(
                (Classifier) op.eContainer(), op, visibility);
    }
    
}
