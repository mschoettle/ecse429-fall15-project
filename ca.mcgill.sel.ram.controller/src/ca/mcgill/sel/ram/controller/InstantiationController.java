package ca.mcgill.sel.ram.controller;

import org.eclipse.emf.ecore.EStructuralFeature;

import ca.mcgill.sel.core.COREMapping;
import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.ram.AttributeMapping;
import ca.mcgill.sel.ram.ClassifierMapping;
import ca.mcgill.sel.ram.Instantiation;
import ca.mcgill.sel.ram.InstantiationType;
import ca.mcgill.sel.ram.MappableElement;
import ca.mcgill.sel.ram.OperationMapping;
import ca.mcgill.sel.ram.ParameterMapping;
import ca.mcgill.sel.ram.RamFactory;
import ca.mcgill.sel.ram.RamPackage;

/**
 * The controller for {@link Instantiation}s. Contains functionality for {@link Mapping}s as well.
 * 
 * @author mschoettle
 */
public class InstantiationController extends BaseController {
    
    /**
     * Creates a new instance of {@link InstantiationController}.
     */
    protected InstantiationController() {
        // Prevent anyone outside this package to instantiate.
    }
    
    /**
     * Creates a new mapping between classifiers.
     * 
     * @param instantiation the {@link Instantiation} the mapping should be added to
     * @return {@link ClassifierMapping}
     */
    public ClassifierMapping createClassifierMapping(Instantiation instantiation) {
        // create mapping
        ClassifierMapping mapping = RamFactory.eINSTANCE.createClassifierMapping();
        
        doAdd(instantiation, CorePackage.Literals.CORE_BINDING__MAPPINGS, mapping);
        
        return mapping;
    }
    
    /**
     * Creates a new mapping between attributes.
     * 
     * @param classifierMapping the {@link ClassifierMapping} the mapping should be added to
     * @return the newly created {@link AttributeMapping}
     */
    public AttributeMapping createAttributeMapping(ClassifierMapping classifierMapping) {
        // create mapping
        AttributeMapping mapping = RamFactory.eINSTANCE.createAttributeMapping();
        
        doAdd(classifierMapping, RamPackage.Literals.CLASSIFIER_MAPPING__ATTRIBUTE_MAPPINGS, mapping);
        
        return mapping;
    }
    
    /**
     * Creates a new mapping between operations.
     * 
     * @param classifierMapping the {@link ClassifierMapping} the mapping should be added to
     * @return the newly created {@link OperationMapping}
     */
    public OperationMapping createOperationMapping(ClassifierMapping classifierMapping) {
        // create mapping
        OperationMapping mapping = RamFactory.eINSTANCE.createOperationMapping();
        
        doAdd(classifierMapping, RamPackage.Literals.CLASSIFIER_MAPPING__OPERATION_MAPPINGS, mapping);
        
        return mapping;
    }
    
    /**
     * Creates a new mapping between parameters.
     * 
     * @param operationMapping the {@link OperationMapping} the mapping should be added to
     */
    public void createParameterMapping(OperationMapping operationMapping) {
        // create mapping
        ParameterMapping mapping = RamFactory.eINSTANCE.createParameterMapping();
        
        doAdd(operationMapping, RamPackage.Literals.OPERATION_MAPPING__PARAMETER_MAPPINGS, mapping);
    }
    
    /**
     * Deletes the given mapping.
     * 
     * @param mapping the {@link Mapping} that should be deleted
     */
    public void deleteMapping(COREMapping<?> mapping) {
        doRemove(mapping);
    }
    
    /**
     * Toggles the type ({@link InstantiationType}) of the given instantiation.
     * 
     * @param instantiation the type should be toggled of
     */
    public void toggleType(Instantiation instantiation) {
        InstantiationType type = instantiation.getType();
        
        switch (type) {
            case DEPENDS:
                type = InstantiationType.EXTENDS;
                break;
            case EXTENDS:
                type = InstantiationType.DEPENDS;
                break;
            default:
                // Nothing to do.
                break;
        }
        
        doSet(instantiation, RamPackage.Literals.INSTANTIATION__TYPE, type);
    }
    
    /**
     * Sets the to element of the given mapping to the given element.
     * 
     * @param mapping the mapping
     * @param feature the feature to be set
     * @param mappableElement the element to be set as the to element
     */
    public void setToElement(COREMapping<?> mapping, EStructuralFeature feature, MappableElement mappableElement) {
        doSet(mapping, feature, mappableElement);
    }
    
    /**
     * Sets the from element of the given mapping to the given element.
     * 
     * @param mapping the mapping
     * @param feature the feature to be set
     * @param mappableElement the element to be set as the from element
     */
    public void setFromElement(COREMapping<?> mapping, EStructuralFeature feature, MappableElement mappableElement) {
        // uses the same functionality right now
        setToElement(mapping, feature, mappableElement);
    }
    
}
