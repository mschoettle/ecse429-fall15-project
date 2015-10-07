package ca.mcgill.sel.ram.ui.views.structural.handler;

import ca.mcgill.sel.ram.ui.views.structural.ClassifierView;

/**
 * This interface is implemented by something that can handle events for a {@link ClassifierView}.
 * 
 * @author mschoettle
 */
public interface IClassifierViewHandler extends IBaseViewHandler {

    /**
     * Handles the creation of an {@link Operation} at the given index.
     * 
     * @param classifierView the affected {@link ClassifierView}
     */
    void createOperation(ClassifierView<?> classifierView);

    /**
     * Handles the creation of a constructor.
     * 
     * @param classifierView the affected {@link ClassifierView}
     */
    void createConstructor(ClassifierView<?> classifierView);

}
