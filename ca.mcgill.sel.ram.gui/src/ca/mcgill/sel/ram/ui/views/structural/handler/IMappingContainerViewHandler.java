package ca.mcgill.sel.ram.ui.views.structural.handler;

import ca.mcgill.sel.ram.AttributeMapping;
import ca.mcgill.sel.ram.ClassifierMapping;
import ca.mcgill.sel.ram.OperationMapping;

/**
 * This interface is implemented by handlers that handle events related to mappings.
 * 
 * @author eyildirim
 */
public interface IMappingContainerViewHandler {
    
    /**
     * Adds a new {@link AttributeMapping}.
     * 
     * @param classifierMapping the current classifier mapping
     */
    void addAttributeMapping(ClassifierMapping classifierMapping);
    
    /**
     * Adds a new {@link OperationMapping}.
     * 
     * @param classifierMapping the current classifier mapping
     */
    void addOperationMapping(ClassifierMapping classifierMapping);
    
    /**
     * Deletes the given {@link AttributeMapping}.
     * 
     * @param attributeMapping the attribute mapping to delete
     */
    void deleteAttributeMapping(AttributeMapping attributeMapping);
    
    /**
     * Deletes the give {@link ClassifierMapping}.
     * 
     * @param classifierMapping the classifier mapping to delete
     */
    void deleteClassifierMapping(ClassifierMapping classifierMapping);
    
    /**
     * Deletes the given {@link OperationMapping}.
     * 
     * @param operationMapping the operation mapping to delete
     */
    void deleteOperationMapping(OperationMapping operationMapping);
}
