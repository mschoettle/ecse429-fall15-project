package ca.mcgill.sel.ram.ui.views.structural.handler.impl;

import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;

import ca.mcgill.sel.ram.Operation;
import ca.mcgill.sel.ram.OperationType;
import ca.mcgill.sel.ram.ui.utils.MetamodelRegex;
import ca.mcgill.sel.ram.ui.views.TextView;
import ca.mcgill.sel.ram.ui.views.handler.impl.ValidatingTextViewHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.IOperationNameHandler;

/**
 * The default handler for a {@link TextView} representing the name of an {@link Operation}. The name gets validated in
 * order to only allow valid names.
 *
 * @author mschoettle
 */
public class OperationNameHandler extends ValidatingTextViewHandler implements IOperationNameHandler {

    /**
     * Creates a new {@link OperationNameHandler}.
     */
    public OperationNameHandler() {
        super(MetamodelRegex.REGEX_OPERATION_NAME);
    }

    @Override
    public boolean processTapEvent(TapEvent tapEvent) {
        TextView target = (TextView) tapEvent.getTarget();
        Operation operation = (Operation) target.getData();

        // Suppress further processing for constructor and destructor.
        if (operation.getOperationType() == OperationType.NORMAL) {
            return super.processTapEvent(tapEvent);
        }

        return true;
    }

}
