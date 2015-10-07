package ca.mcgill.sel.ram.ui.views.message.handler.impl;

import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.ram.Message;
import ca.mcgill.sel.ram.StructuralFeature;
import ca.mcgill.sel.ram.controller.ControllerFactory;
import ca.mcgill.sel.ram.ui.views.TextView;

/**
 * The handler for handling everything related to "assignTo" of a message.
 * It supports providing a choice of selections of possible values, but also
 * to create a temporary property (including renaming it after it is created).
 * Furthermore, a previously chosen value can be changed later.
 * This handler is not stateless, it needs to keep references to specific model objects it handles.
 * 
 * @author mschoettle
 */
public class MessageAssignToHandler extends AssignToHandler {
    
    @Override
    public void handleTemporaryCreate(TextView target, Vector3D position) {
        ControllerFactory.INSTANCE.getMessageController().createTemporaryAssignment((Message) data);
    }
    
    @Override
    public void handleElementSelected(StructuralFeature structuralFeature) {
        ControllerFactory.INSTANCE.getMessageController().changeAssignTo((Message) data, structuralFeature);
    }
    
}
