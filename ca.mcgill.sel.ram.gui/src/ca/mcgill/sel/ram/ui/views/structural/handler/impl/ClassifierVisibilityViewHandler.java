package ca.mcgill.sel.ram.ui.views.structural.handler.impl;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import ca.mcgill.sel.core.COREVisibilityType;
import ca.mcgill.sel.ram.Classifier;
import ca.mcgill.sel.ram.Operation;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.utils.Strings;
import ca.mcgill.sel.ram.ui.views.handler.impl.TextViewHandler;

/**
 * The default handler for a {@link ClassifierVisibilityViewHandler}. It shows a selector with the different
 * visibilities as defined in {@link COREVisibilityType}.
 * 
 * @author oalam
 */
public class ClassifierVisibilityViewHandler extends TextViewHandler {

    /**
     * Sets the value on the data object for the given feature.
     * 
     * @param data the object containing the feature
     * @param feature the feature of which the value should be changed
     * @param value the new value of the feature
     */
    @Override
    protected void setValue(EObject data, EStructuralFeature feature, Object value) {
        // TODO tmp: this tests should probably be done in the controller.
        // Does not take in account mapping between classes (see #320)
        Classifier clazz = (Classifier) data;
        COREVisibilityType newVisibility = (COREVisibilityType) value;
        if (newVisibility != COREVisibilityType.PUBLIC) {
            for (Operation op : clazz.getOperations()) {
                if (op.getVisibility() == COREVisibilityType.PUBLIC) {
                    RamApp.getActiveScene().displayPopup(Strings.POPUP_PUBLIC_CLASS_NO_SWITCH);
                    return;
                }
            }
        }
        super.setValue(data, feature, value);
    }

}