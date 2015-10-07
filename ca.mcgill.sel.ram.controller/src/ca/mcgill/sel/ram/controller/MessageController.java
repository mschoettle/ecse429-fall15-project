package ca.mcgill.sel.ram.controller;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import ca.mcgill.sel.commons.StringUtil;
import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.commons.emf.util.EMFModelUtil;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.Attribute;
import ca.mcgill.sel.ram.Classifier;
import ca.mcgill.sel.ram.EnumLiteralValue;
import ca.mcgill.sel.ram.InteractionFragment;
import ca.mcgill.sel.ram.Lifeline;
import ca.mcgill.sel.ram.Message;
import ca.mcgill.sel.ram.MessageOccurrenceSpecification;
import ca.mcgill.sel.ram.MessageSort;
import ca.mcgill.sel.ram.ObjectType;
import ca.mcgill.sel.ram.Parameter;
import ca.mcgill.sel.ram.ParameterValue;
import ca.mcgill.sel.ram.PrimitiveType;
import ca.mcgill.sel.ram.REnumLiteral;
import ca.mcgill.sel.ram.RamFactory;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.Reference;
import ca.mcgill.sel.ram.StructuralFeature;
import ca.mcgill.sel.ram.StructuralFeatureValue;
import ca.mcgill.sel.ram.TemporaryProperty;
import ca.mcgill.sel.ram.Type;
import ca.mcgill.sel.ram.ValueSpecification;
import ca.mcgill.sel.ram.util.RAMModelUtil;

/**
 * The controller for {@link Message}s.
 * 
 * @author mschoettle
 */
public class MessageController extends BaseController {
    
    /**
     * Creates a new instance of {@link MessageController}.
     */
    protected MessageController() {
        // Prevent anyone outside this package to instantiate.
    }
    
    /**
     * Creates a new specific value specification and adds it as the value of the given owner.
     * The value specification intended to be added are of type {@link ca.mcgill.sel.ram.LiteralSpecification}.
     * In case a literal specification is already set as the value specification, it is deleted first.
     * 
     * @param owner the object to add the value to
     * @param containingFeature the feature containing the value specification
     * @param eClass the {@link EClass} of the specific value specification subclass
     */
    public void addValueSpecification(EObject owner, EStructuralFeature containingFeature, EClass eClass) {
        EObject valueSpecification = RamFactory.eINSTANCE.create(eClass);
        
        executeAddValueSpecification(owner, containingFeature, valueSpecification);
    }
    
    /**
     * Creates and adds a specific value specification ({@link StructuralFeatureValue}, {@link ParameterValue})
     * or {@link EnumLiteralValue} to the given owner and sets the given value as the value of the value specification.
     * The specific type to create depends on the actual type of the given value.
     * In case a literal specification is already set as the value specification, it is removed first.
     * 
     * @param owner the object to add the value to
     * @param containingFeature the feature containing the value specification
     * @param value the {@link EObject} to set as the value of the value specification to create
     */
    public void addReferenceValueSpecification(EObject owner, EStructuralFeature containingFeature,
            EObject value) {
        ValueSpecification valueSpecification = null;
        
        if (value instanceof Parameter) {
            ParameterValue parameterValue = RamFactory.eINSTANCE.createParameterValue();
            parameterValue.setParameter((Parameter) value);
            
            valueSpecification = parameterValue;
        } else if (value instanceof StructuralFeature) {
            StructuralFeatureValue structuralFeatureValue = RamFactory.eINSTANCE.createStructuralFeatureValue();
            structuralFeatureValue.setValue((StructuralFeature) value);
            
            valueSpecification = structuralFeatureValue;
        } else if (value instanceof REnumLiteral) {
            EnumLiteralValue enumLiteralValue = RamFactory.eINSTANCE.createEnumLiteralValue();
            enumLiteralValue.setValue((REnumLiteral) value);
            
            valueSpecification = enumLiteralValue;
        }
        
        executeAddValueSpecification(owner, containingFeature, valueSpecification);
    }
    
    /**
     * Adds the given value specification to the given owner and executes the command.
     * In case a value exists already, it is removed first.
     * 
     * @param owner the object to add the value to
     * @param containingFeature the feature containing the value specification
     * @param valueSpecification the specific {@link ValueSpecification} to add
     */
    private void executeAddValueSpecification(EObject owner, EStructuralFeature containingFeature,
            EObject valueSpecification) {
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(owner);
        CompoundCommand compoundCommand = new CompoundCommand();
        
        // Check if there is an existing value.
        Object currentValue = owner.eGet(containingFeature);
        
        // Delete existing value specifications
        // Note: This is not totally necessary, because the set command just overwrites.
        if (currentValue != null) {
            compoundCommand.append(RemoveCommand.create(editingDomain, currentValue));
        }
        
        compoundCommand.append(SetCommand.create(editingDomain, owner, containingFeature, valueSpecification));
        
        doExecute(editingDomain, compoundCommand);
    }
    
    /**
     * Creates a new temporary property and sets it as the "assignTo" of the message.
     * Also adds the temporary property to the initial message.
     * If there is an existing temporary property, it is deleted in addition.
     * 
     * @param message the message for which the "assignTo" to set for
     */
    public void createTemporaryAssignment(Message message) {
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(message);
        
        MessageOccurrenceSpecification sendEvent = (MessageOccurrenceSpecification) message.getSendEvent();
        Message initialMessage = RAMModelUtil.findInitialMessage(sendEvent);
        
        Type returnType = message.getSignature().getReturnType();
        if (returnType instanceof Classifier) {
            Aspect aspect = EMFModelUtil.getRootContainerOfType(message, RamPackage.Literals.ASPECT);
            returnType = RAMModelUtil.resolveClassifier(aspect, (Classifier) returnType);
        }
        
        // Determine proper initial name of property.
        String name = message.getSignature().getName().replaceFirst("^get", "");
        // If the message is called "get" the name will be empty.
        if (name.isEmpty()) {
            name = returnType.getName();
        }
        
        name = StringUtil.toLowerCaseFirst(name);
        
        // Create temporary property.
        TemporaryProperty temporaryProperty = null;
        
        if (returnType instanceof PrimitiveType) {
            Attribute attribute = RamFactory.eINSTANCE.createAttribute();
            attribute.setType((PrimitiveType) returnType);
            attribute.setName(name);
            temporaryProperty = attribute;
        } else if (returnType instanceof Classifier) {
            Reference reference = RamFactory.eINSTANCE.createReference();
            reference.setType((ObjectType) returnType);
            reference.setName(name);
            reference.setLowerBound(1);
            temporaryProperty = reference;
        }
        
        CompoundCommand compoundCommand = new CompoundCommand();
        
        // If there is an existing assign to and it is a temporary property, we have to delete it first.
        StructuralFeature structuralFeature = message.getAssignTo();
        if (structuralFeature != null && structuralFeature.eContainer() == initialMessage) {
            compoundCommand.append(RemoveCommand.create(editingDomain, structuralFeature));
        }
        
        compoundCommand.append(AddCommand.create(editingDomain, initialMessage,
                RamPackage.Literals.MESSAGE__LOCAL_PROPERTIES, temporaryProperty));
        compoundCommand.append(SetCommand.create(editingDomain, message,
                RamPackage.Literals.MESSAGE__ASSIGN_TO, temporaryProperty));
        
        // Create messages require that the lifelines represent is replaced with the new assignment,
        // because the lifeline is represented with what the new object is assigned to.
        // E.g., foo := create() [on lifeline foo : Foo]
        if (message.getMessageSort() == MessageSort.CREATE_MESSAGE) {
            InteractionFragment receiveEvent = (InteractionFragment) message.getReceiveEvent();
            Lifeline lifeline = receiveEvent.getCovered().get(0);
            
            compoundCommand.append(SetCommand.create(editingDomain, lifeline,
                    RamPackage.Literals.LIFELINE__REPRESENTS, temporaryProperty));
        }
        
        doExecute(editingDomain, compoundCommand);
    }
    
    /**
     * Changes the "assignTo" of the message to the given.
     * It might not be set yet, if it is and it is a temporary property, the existing one is deleted.
     * In case of a create message the represents of the lifeline is updated to the new value.
     * 
     * @param message the message the "assignTo" to change of
     * @param assignTo the new "assignTo" value to change to
     */
    public void changeAssignTo(Message message, StructuralFeature assignTo) {
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(message);
        
        MessageOccurrenceSpecification sendEvent = (MessageOccurrenceSpecification) message.getSendEvent();
        Message initialMessage = RAMModelUtil.findInitialMessage(sendEvent);
        
        CompoundCommand compoundCommand = new CompoundCommand();
        
        // If there is an existing assign to and it is a temporary property, we have to delete it first.
        // Assume that it was created for this purpose.
        StructuralFeature structuralFeature = message.getAssignTo();
        if (structuralFeature != null && structuralFeature.eContainer() == initialMessage) {
            compoundCommand.append(RemoveCommand.create(editingDomain, structuralFeature));
        }
        
        compoundCommand.append(SetCommand.create(editingDomain, message,
                RamPackage.Literals.MESSAGE__ASSIGN_TO, assignTo));
        
        // Create messages require that the lifelines represent is replaced with the new assignment,
        // because the lifeline is represented with what the new object is assigned to.
        // E.g., foo := create() [on lifeline foo : Foo]
        if (message.getMessageSort() == MessageSort.CREATE_MESSAGE) {
            InteractionFragment receiveEvent = (InteractionFragment) message.getReceiveEvent();
            Lifeline lifeline = receiveEvent.getCovered().get(0);
            
            compoundCommand.append(SetCommand.create(editingDomain, lifeline,
                    RamPackage.Literals.LIFELINE__REPRESENTS, assignTo));
        }
        
        doExecute(editingDomain, compoundCommand);
    }
    
}
