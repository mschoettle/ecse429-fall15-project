package ca.mcgill.sel.ram.ui.views.structural.handler.impl;

import ca.mcgill.sel.ram.AttributeMapping;
import ca.mcgill.sel.ram.ClassifierMapping;
import ca.mcgill.sel.ram.OperationMapping;
import ca.mcgill.sel.ram.controller.ControllerFactory;
import ca.mcgill.sel.ram.controller.InstantiationController;
import ca.mcgill.sel.ram.ui.views.structural.handler.IMappingContainerViewHandler;

/**
 * Default handler for {@link ca.mcgill.sel.ram.ui.views.structural.MappingContainerView}.
 * 
 * @author eyildirim
 */
public class MappingContainerViewHandler implements IMappingContainerViewHandler {
    
    @Override
    public void addAttributeMapping(ClassifierMapping classifierMapping) {
        if (classifierMapping.getFrom() != null && classifierMapping.getTo() != null) {
            
            InstantiationController instantiationController = ControllerFactory.INSTANCE.getInstantiationController();
            instantiationController.createAttributeMapping(classifierMapping);
        }
        
    }
    
    @Override
    public void addOperationMapping(ClassifierMapping classifierMapping) {
        if (classifierMapping.getFrom() != null && classifierMapping.getTo() != null) {
            InstantiationController instantiationController = ControllerFactory.INSTANCE.getInstantiationController();
            instantiationController.createOperationMapping(classifierMapping);
        }
    }
    
    @Override
    public void deleteAttributeMapping(AttributeMapping attributeMapping) {
        InstantiationController instantiationController = ControllerFactory.INSTANCE.getInstantiationController();
        instantiationController.deleteMapping(attributeMapping);
    }
    
    @Override
    public void deleteClassifierMapping(ClassifierMapping classifierMapping) {
        InstantiationController instantiationController = ControllerFactory.INSTANCE.getInstantiationController();
        instantiationController.deleteMapping(classifierMapping);
    }
    
    @Override
    public void deleteOperationMapping(OperationMapping operationMapping) {
        InstantiationController instantiationController = ControllerFactory.INSTANCE.getInstantiationController();
        instantiationController.deleteMapping(operationMapping);
    }
    
}
