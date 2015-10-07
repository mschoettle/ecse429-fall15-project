package ca.mcgill.sel.ram.ui.views.structural.handler;

import ca.mcgill.sel.core.COREPartialityType;
import ca.mcgill.sel.ram.ui.views.structural.ClassView;
import ca.mcgill.sel.ram.ui.views.structural.ClassifierView;

/**
 * This interface is implemented by something that can handle events for a {@link ClassView}.
 * 
 * @author mschoettle
 */
public interface IClassViewHandler extends IClassifierViewHandler {

    /**
     * Handles the creation of an {@link ca.mcgill.sel.ram.Attribute}.
     * 
     * @param classView the affected {@link ClassView}
     */
    void createAttribute(ClassView classView);

    /**
     * Handles the creation of a destructor.
     * 
     * @param clazz - the affected {@link ClassifierView}
     */
    void createDestructor(ClassifierView<?> clazz);

    /**
     * Handles the switch of a {@link ca.mcgill.sel.ram.Classifier} to abstract.
     * 
     * @param clazz - the affected {@link ClassifierView}
     */
    void switchToAbstract(ClassifierView<?> clazz);

    /**
     * Handles the toggle of a {@link ca.mcgill.sel.ram.Classifier} to given partiality.
     * 
     * @param clazz - the affected {@link ClassifierView}
     * @param type - the new {@link COREPartialityType}
     */
    void switchPartiality(ClassifierView<?> clazz, COREPartialityType type);

}
