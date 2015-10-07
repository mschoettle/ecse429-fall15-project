package ca.mcgill.sel.ram.ui.views.structural.handler;

import org.mt4j.input.inputProcessors.IGestureEventListener;

import ca.mcgill.sel.core.COREPartialityType;
import ca.mcgill.sel.ram.ui.views.structural.OperationView;

/**
 * This interface is implemented by something that can handle events for a {@link OperationView}.
 * 
 * @author mschoettle
 */
public interface IOperationViewHandler extends IGestureEventListener {

    /**
     * Handles creating a new {@link ca.mcgill.sel.ram.Parameter} at the given model index and visual index.
     * 
     * @param operationView
     *            the affected {@link OperationView}
     */
    void createParameter(OperationView operationView);

    /**
     * Handles the removal of an {@link ca.mcgill.sel.ram.Operation}.
     * 
     * @param operationView
     *            the affected {@link OperationView}
     */
    void removeOperation(OperationView operationView);

    /**
     * Handles the switch of an {@link ca.mcgill.sel.ram.Operation} to abstract.
     * 
     * @param operationView - the affected {@link OperationView}
     */
    void switchToAbstract(OperationView operationView);

    /**
     * Handles the toggle of a {@link ca.mcgill.sel.ram.Operation} to given partiality.
     * 
     * @param operation - the affected {@link OperationView}
     * @param type - the new {@link COREPartialityType}
     */
    void switchPartiality(OperationView operation, COREPartialityType type);

    /**
     * Handles the toggle of an {@link ca.mcgill.sel.ram.Operation} to static.
     * 
     * @param operationView - the affected {@link OperationView}
     */
    void setOperationStatic(OperationView operationView);

    /**
     * Handles the selection of the message view option.
     * Creates a message view for the given operation. If a message view already exists, displays that one instead.
     * 
     * @param operationView the current operation view
     */
    void goToMessageView(OperationView operationView);

    /**
     * Handles the selection of the advice option.
     * Creates an aspect message view to advice the given operation.
     * 
     * @param operationView the current operation view
     */
    void goToAdviceView(OperationView operationView);

}
