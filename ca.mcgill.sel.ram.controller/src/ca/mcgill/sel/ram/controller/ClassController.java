package ca.mcgill.sel.ram.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.commons.emf.util.EMFModelUtil;
import ca.mcgill.sel.core.COREPartialityType;
import ca.mcgill.sel.core.COREVisibilityType;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.AssignmentStatement;
import ca.mcgill.sel.ram.Attribute;
import ca.mcgill.sel.ram.AttributeMapping;
import ca.mcgill.sel.ram.Class;
import ca.mcgill.sel.ram.Classifier;
import ca.mcgill.sel.ram.ClassifierMapping;
import ca.mcgill.sel.ram.Gate;
import ca.mcgill.sel.ram.Instantiation;
import ca.mcgill.sel.ram.Interaction;
import ca.mcgill.sel.ram.Lifeline;
import ca.mcgill.sel.ram.Message;
import ca.mcgill.sel.ram.MessageSort;
import ca.mcgill.sel.ram.MessageView;
import ca.mcgill.sel.ram.ObjectType;
import ca.mcgill.sel.ram.OpaqueExpression;
import ca.mcgill.sel.ram.Operation;
import ca.mcgill.sel.ram.OperationMapping;
import ca.mcgill.sel.ram.OperationType;
import ca.mcgill.sel.ram.Parameter;
import ca.mcgill.sel.ram.ParameterValueMapping;
import ca.mcgill.sel.ram.PrimitiveType;
import ca.mcgill.sel.ram.RAMVisibilityType;
import ca.mcgill.sel.ram.RCollection;
import ca.mcgill.sel.ram.RVoid;
import ca.mcgill.sel.ram.RamFactory;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.StructuralFeature;
import ca.mcgill.sel.ram.StructuralFeatureValue;
import ca.mcgill.sel.ram.StructuralView;
import ca.mcgill.sel.ram.Type;
import ca.mcgill.sel.ram.util.RAMModelUtil;

/**
 * The controller for {@link Class}es. Additionally contains operations for attributes, operations and parameters.
 *
 * @author mschoettle
 */
public class ClassController extends BaseController {

    /**
     * Creates a new instance of {@link ClassController}.
     */
    protected ClassController() {
        // Prevent anyone outside this package to instantiate.
    }

    /**
     * Moves the given classifier to a new position.
     *
     * @param classifier the classifier to move
     * @param x the new x position
     * @param y the new y position
     */
    public void moveClassifier(Classifier classifier, float x, float y) {
        Aspect aspect = EMFModelUtil.getRootContainerOfType(classifier, RamPackage.Literals.ASPECT);
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(aspect);

        Command moveClassCommand = createMoveCommand(editingDomain, aspect.getStructuralView(), classifier, x, y);

        doExecute(editingDomain, moveClassCommand);
    }

    /**
     * Removes the given attribute.
     *
     * @param attribute the attribute to be removed
     */
    public void removeAttribute(Attribute attribute) {
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(attribute);

        // Create remove Command.
        Command removeCommand = RemoveCommand.create(editingDomain, attribute);

        doExecute(editingDomain, removeCommand);
    }

    /**
     * Creates a new attribute.
     *
     * @param owner the class the attribute should be added to
     * @param index the index where the attribute should be added at
     * @param name the name of the attribute
     * @param primitiveType the primitive type of the attribute
     * @throws IllegalArgumentException if the attribute already exists
     */
    public void createAttribute(Class owner, int index, String name, PrimitiveType primitiveType) {
        if (!RAMModelUtil.isUniqueName(owner, RamPackage.Literals.CLASS__ATTRIBUTES, name)) {
            throw new IllegalArgumentException("The " + name + " attribute is not unique for attributes");
        }

        // Create the attribute with the given information.
        Attribute attribute = RamFactory.eINSTANCE.createAttribute();
        attribute.setName(name);
        attribute.setType(primitiveType);

        // Execute add command.
        doAdd(owner, RamPackage.Literals.CLASS__ATTRIBUTES, attribute, index);
    }

    /**
     * Removes the given operation. Also removes the corresponding {@link MessageView} if it exists.
     *
     * @param operation the operation to be removed
     */
    public void removeOperation(Operation operation) {
        Aspect aspect = EMFModelUtil.getRootContainerOfType(operation, RamPackage.Literals.ASPECT);
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(aspect);
        CompoundCommand compoundCommand = new CompoundCommand();

        MessageView messageView = RAMModelUtil.getMessageViewFor(aspect, operation);

        if (messageView != null) {
            compoundCommand.append(AspectController.createRemoveMessageViewCommand(messageView));
        }

        compoundCommand.append(RemoveCommand.create(editingDomain, operation));

        doExecute(editingDomain, compoundCommand);
    }

    /**
     * Creates a new operation.
     *
     * @param owner the class the operation should be added to
     * @param index the index the operation should be added at
     * @param name the name of the operation
     * @param ramVisibility the {@link RAMVisibilityType} to set for the operation
     * @param returnType the return type
     * @param parameters a list of parameters
     * @throws IllegalArgumentException if the operation already exists
     *
     */
    public void createOperation(Class owner, int index, String name, RAMVisibilityType ramVisibility,
            Type returnType, List<Parameter> parameters) {

        // Create the operation with the given information.
        Operation operation = RAMModelUtil.createOperation(owner, name, returnType, parameters);

        if (operation != null) {
            addOperationToEditingDomain(owner, index, operation, ramVisibility);
        }
    }

    /**
     * Link the given operation to the Editing Domain with the command.
     * 
     * @param owner - the class the operation should be added to
     * @param index - the index the operation should be added at
     * @param operation - the operation to add
     * @param ramVisibility - the {@link RAMVisibilityType} to set for the operation
     */
    private void addOperationToEditingDomain(Class owner, int index, Operation operation,
            RAMVisibilityType ramVisibility) {

        EditingDomain domain = EMFEditUtil.getEditingDomain(owner);
        Command command = createAddOperationCommand(domain, owner, operation, ramVisibility, index);

        doExecute(domain, command);
    }

    /**
     * Create a command that add a new operation.
     *
     * @param domain the {@link EditingDomain}
     * @param owner the class the operation should be added to
     * @param operation the operation to add
     * @param ramVisibility the {@link RAMVisibilityType} to set for the operation
     * @param index the index the operation should be added at
     * @throws IllegalArgumentException if the operation already exists
     *
     * @return The {@link Command} created
     */
    private Command createAddOperationCommand(EditingDomain domain, Classifier owner, Operation operation,
            RAMVisibilityType ramVisibility, int index) {
        CompoundCommand compoundCommand = new CompoundCommand();
        compoundCommand.append(
                changeOperationAndClassVisibilityCommand(domain, owner, operation, ramVisibility));
        compoundCommand.append(AddCommand.create(domain, owner, RamPackage.Literals.CLASSIFIER__OPERATIONS, operation,
                index));

        return compoundCommand;
    }

    /**
     * Changes the visibility of the classifier accordingly when the visibility of an operation is changed to public.
     *
     * @param owner the classifier the operation belongs to
     * @param operation which visibility is changed
     * @param visibility the value of operation's {@link RAMVisibilityType}
     */
    public void changeOperationAndClassVisibility(Classifier owner, Operation operation, RAMVisibilityType visibility) {
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(owner);
        Command command = changeOperationAndClassVisibilityCommand(editingDomain, owner, operation, visibility);
        doExecute(editingDomain, command);
    }

    /**
     * Returns the command that changes the visibility of the classifier accordingly when the visibility of an operation
     * is changed. One of the two parameter for visibility given is supposed to be null.
     * Only the other one will be taken into account (in case both are set, only coreVisibility is taken into account).
     *
     * @param editingDomain - The {@link EditingDomain} for the command
     * @param owner - The {@link Classifier} the operation belongs to
     * @param operation - The {@link Operation} which visibility is changed
     * @param ramVisibility - The value of operation's {@link RAMVisibilityType}
     * @return {@link Command} which is created.
     */
    Command changeOperationAndClassVisibilityCommand(EditingDomain editingDomain, Classifier owner,
            Operation operation,
            RAMVisibilityType ramVisibility) {
        CompoundCommand compoundCommand = new CompoundCommand();
        // If adding a public operation to a non public class, update the class visibility
        if (ramVisibility == RAMVisibilityType.PUBLIC && owner.getVisibility() != COREVisibilityType.PUBLIC) {
            // Update the classifier visibility
            compoundCommand.append(changeCoreVisibilityCommand(editingDomain, owner, COREVisibilityType.PUBLIC));
        }
        // Update the operation visibility
        compoundCommand.append(changeOperationVisibilityCommand(editingDomain, operation, ramVisibility));
        return compoundCommand;
    }

    /**
     * Get the command used to change the {@link RAMVisibilityType} of an {@link Operation}.
     * This does update the {@link COREVisibilityType} of the operation if necessary as well.
     *
     * @param editingDomain - the editing domain
     * @param operation - the operation
     * @param visibilityType - the new visibility to set
     * @return the created command
     */
    private Command changeOperationVisibilityCommand(EditingDomain editingDomain, Operation operation,
            RAMVisibilityType visibilityType) {
        CompoundCommand command = new CompoundCommand();
        // Change the CORE visibility if necessary
        if (visibilityType == RAMVisibilityType.PUBLIC) {
            if (operation.getVisibility() != COREVisibilityType.PUBLIC) {
                command.append(changeCoreVisibilityCommand(editingDomain, operation, COREVisibilityType.PUBLIC));
            }
        } else {
            if (operation.getVisibility() != COREVisibilityType.CONCERN) {
                command.append(changeCoreVisibilityCommand(editingDomain, operation, COREVisibilityType.CONCERN));
            }
        }
        // Change only the RAM visibility
        command.append(SetCommand.create(editingDomain, operation, RamPackage.Literals.OPERATION__EXTENDED_VISIBILITY,
                visibilityType));
        return command;
    }

    /**
     * Creates a copy of the given operation with the same signature and with a desired name. Adds it to the proper
     * class as well.
     *
     * @param operationMapping the operation mapping which the operation we copy belongs to
     * @param newName the name of the operation that is going to be created from the copy.
     * @return {@link Operation} which is created as a clone of the given operation with the parameters that are
     *         accordingly changed depending on the signature
     */
    public Operation createOperationCopy(OperationMapping operationMapping, String newName) {
        Aspect aspect = EMFModelUtil.getRootContainerOfType(operationMapping, RamPackage.Literals.ASPECT);
        ClassifierMapping classifierMapping = (ClassifierMapping) operationMapping.eContainer();
        Operation copyOperation =
                createOperationCopyWithoutCommand(operationMapping, newName, aspect.getStructuralView());

        doAdd(classifierMapping.getTo(), RamPackage.Literals.CLASSIFIER__OPERATIONS, copyOperation);

        return copyOperation;
    }

    /**
     * Creates a copy of the given operation with the same signature and with a desired name. Adds it to the proper
     * class as well.
     *
     * @param operationMapping the operation mapping which the operation we copy belongs to
     * @param newName the name of the operation that is going to be created from the copy.
     * @param structuralView the structural view of the current aspect
     * @return {@link Operation} which is created as a clone of the given operation with the parameters that are
     *         accordingly changed depending on the signature
     */
    public Operation createOperationCopyWithoutCommand(OperationMapping operationMapping, String newName,
            StructuralView structuralView) {
        ClassifierMapping classifierMapping = (ClassifierMapping) operationMapping.eContainer();
        Instantiation instantiation = (Instantiation) classifierMapping.eContainer();

        Operation oldOperation = operationMapping.getFrom();

        // First we copy the entire operation then we'll change the types of the parameters depending on the signature.
        Operation copyOperation = EcoreUtil.copy(oldOperation);
        // See issue #259. Remove once #170 resolved.
        // TODO NOM copyOperation.getComesFrom().clear();

        // Then we change the name of the operation
        copyOperation.setName(newName);
        copyOperation.setPartiality(COREPartialityType.NONE);

        Type returnType = copyOperation.getReturnType();
        copyOperation.setReturnType(getType(returnType, instantiation, structuralView));

        // These parameters are exact same copy of the old operation. So we need to change them. We chose this way
        // because we didn't want to create all the parameters one at a time.
        EList<Parameter> parametersOfNewOperation = copyOperation.getParameters();

        // For each original parameter check if it is mapped as a classifier mapping's from element
        // so that we can get the signature of to element if it is mapped to anything.
        for (Parameter parameter : parametersOfNewOperation) {
            Type newType = getType(parameter.getType(), instantiation, structuralView);
            parameter.setType(newType);
        }

        return copyOperation;
    }

    /**
     * Creates an operation which gets the name of an attribute.
     *
     * @param attribute the attribute.
     */
    public void createGetterOperation(StructuralFeature attribute) {
        Aspect aspect = EMFModelUtil.getRootContainerOfType(attribute, RamPackage.Literals.ASPECT);

        EditingDomain domain = EMFEditUtil.getEditingDomain(aspect);
        Command command = this.createGetterOperationCommand(domain, aspect, attribute);
        if (command != null) {
            doExecute(domain, command);
        }
    }

    /**
     * Returns a command that creates an operation which gets the name of an attribute.
     *
     * @param domain the {@link EditingDomain} to use for executing commands
     * @param aspect The current aspect
     * @param attribute the attribute.
     *
     * @return the created {@link Command}
     */
    public Command createGetterOperationCommand(EditingDomain domain, Aspect aspect, StructuralFeature attribute) {
        Class owner = (Class) attribute.eContainer();
        int index = owner.getOperations().size();

        Operation operation = RAMModelUtil.extractGetter(attribute);

        if (operation == null) {
            return null;
        }

        RAMVisibilityType ramVisibility = RAMVisibilityType.PUBLIC;

        CompoundCommand compoundCommand = new CompoundCommand();

        compoundCommand.appendAndExecute(createAddOperationCommand(domain, owner, operation, ramVisibility, index));

        MessageView messageView = RAMModelUtil.createMessageView(operation);
        for (Message message : messageView.getSpecification().getMessages()) {
            if (message.getSignature() == operation && message.getMessageSort() == MessageSort.REPLY) {
                StructuralFeatureValue structuralFeatureValue = RamFactory.eINSTANCE.createStructuralFeatureValue();
                structuralFeatureValue.setValue(attribute);

                message.setReturns(structuralFeatureValue);
            }
        }

        compoundCommand.appendAndExecute(createAddMessageViewCommand(domain, aspect, messageView,
                messageView.getSpecification()));

        return compoundCommand;
    }

    /**
     * Creates an operation which sets the name of an attribute.
     *
     * @param attribute the attribute.
     */
    public void createSetterOperation(StructuralFeature attribute) {
        Aspect aspect = EMFModelUtil.getRootContainerOfType(attribute, RamPackage.Literals.ASPECT);

        EditingDomain domain = EMFEditUtil.getEditingDomain(aspect);

        Command command = this.createSetterOperationCommand(domain, aspect, attribute);

        if (command != null) {
            doExecute(domain, command);
        }
    }

    /**
     * Creates an operation which sets the name of an attribute.
     *
     * @param domain the {@link EditingDomain} to use for executing commands
     * @param aspect The current aspect
     * @param attribute the attribute.
     *
     * @return The created {@link Command}
     */
    public Command createSetterOperationCommand(EditingDomain domain, Aspect aspect, StructuralFeature attribute) {

        Class owner = (Class) attribute.eContainer();
        int index = owner.getOperations().size();

        Operation operation = RAMModelUtil.extractSetter(attribute);
        if (operation == null) {
            return null;
        }

        RAMVisibilityType ramVisibility = RAMVisibilityType.PUBLIC;

        CompoundCommand compoundCommand = new CompoundCommand();

        // we need to execute the command because we will need the result of this command in the
        // CreateAddMessageViewCommand operation.
        compoundCommand.appendAndExecute(createAddOperationCommand(domain, owner, operation, ramVisibility, index));

        // Create Message view
        MessageView messageView = RAMModelUtil.createMessageView(operation);
        Interaction interaction = messageView.getSpecification();

        // We just create the messageView, so it contains only one lifeline.
        Lifeline lifeline = interaction.getLifelines().get(0);

        // Create Assignment statement
        AssignmentStatement assignmentStatement = RamFactory.eINSTANCE.createAssignmentStatement();
        OpaqueExpression specification = RAMModelUtil.createOpaqueExpression();
        specification.setBody(attribute.getName());
        assignmentStatement.setValue(specification);
        assignmentStatement.setAssignTo(attribute);
        assignmentStatement.getCovered().add(lifeline);

        // getFragments already contains two fragments (the two initials messages).
        // So we add at the position one the assignment fragment.
        interaction.getFragments().add(1, assignmentStatement);

        compoundCommand.appendAndExecute(createAddMessageViewCommand(domain, aspect, messageView,
                messageView.getSpecification()));

        return compoundCommand;
    }

    /**
     * Creates an operation which gets and sets the name of an attribute.
     *
     * @param attribute the attribute.
     */
    public void createGetterAndSetterOperation(StructuralFeature attribute) {
        Aspect aspect = EMFModelUtil.getRootContainerOfType(attribute, RamPackage.Literals.ASPECT);

        EditingDomain domain = EMFEditUtil.getEditingDomain(aspect);

        CompoundCommand compoundCommand = new CompoundCommand();

        Command getterCommand = this.createGetterOperationCommand(domain, aspect, attribute);
        if (getterCommand != null) {
            compoundCommand.append(getterCommand);
        }

        Command setterCommand = this.createSetterOperationCommand(domain, aspect, attribute);
        if (setterCommand != null) {
            compoundCommand.append(setterCommand);
        }

        doExecute(domain, compoundCommand);
    }

    /**
     * Creates a new constructor for the given class.
     *
     * @param owner the class to add a constructor to
     */
    public void createConstructor(Class owner) {

        Operation operation = RAMModelUtil.createOperation(owner, RAMModelUtil.CONSTRUCTOR_NAME, owner, null, false);
        if (operation != null) {
            operation.setOperationType(OperationType.CONSTRUCTOR);
            int index = RAMModelUtil.findConstructorIndexFor(owner, OperationType.CONSTRUCTOR);
            EditingDomain domain = EMFEditUtil.getEditingDomain(owner);
            Command command =
                    changeOperationAndClassVisibilityCommand(domain, owner, operation, RAMVisibilityType.PUBLIC);
            CompoundCommand compundCommand = new CompoundCommand();
            compundCommand.append(command);
            compundCommand.append(AddCommand.create(domain, owner, RamPackage.Literals.CLASSIFIER__OPERATIONS,
                    operation,
                    index));
            doExecute(domain, compundCommand);
        }

    }

    /**
     * Creates a new destructor for the given class.
     *
     * @param owner the class to add a destructor to
     */
    public void createDestructor(Class owner) {
        RVoid voidType = RAMModelUtil.getVoidType((StructuralView) owner.eContainer());
        Operation operation = RAMModelUtil.createOperation(owner, RAMModelUtil.DESTRUCTOR_NAME, voidType, null, false);
        if (operation != null) {
            operation.setOperationType(OperationType.DESTRUCTOR);
            int index = RAMModelUtil.findConstructorIndexFor(owner, OperationType.DESTRUCTOR);

            EditingDomain domain = EMFEditUtil.getEditingDomain(owner);
            Command command =
                    changeOperationAndClassVisibilityCommand(domain, owner, operation, RAMVisibilityType.PUBLIC);
            CompoundCommand compundCommand = new CompoundCommand();
            compundCommand.append(command);
            compundCommand.append(AddCommand.create(domain, owner, RamPackage.Literals.CLASSIFIER__OPERATIONS,
                    operation,
                    index));
            doExecute(domain, compundCommand);
        }
    }

    /**
     * Creates a copy of the given attribute with the same signature and with a desired name. Adds it to the proper
     * class as well.
     *
     * @param attributeMapping the attribute mapping which the attribute we copy belongs to
     * @param newName the name of the attribute that is going to be created from the copy.
     * @return {@link Attribute} which is created as a clone of the given attribute
     */
    public Attribute createAttributeCopy(AttributeMapping attributeMapping, String newName) {
        Aspect aspect = EMFModelUtil.getRootContainerOfType(attributeMapping, RamPackage.Literals.ASPECT);
        ClassifierMapping classifierMapping = (ClassifierMapping) attributeMapping.eContainer();
        Instantiation instantiation = (Instantiation) classifierMapping.eContainer();

        Attribute oldAttribute = attributeMapping.getFrom();

        // First we copy the attribute
        Attribute copyAttribute = EcoreUtil.copy(oldAttribute);

        // get the type of the original attribute
        PrimitiveType type = oldAttribute.getType();

        // get the corresponding type from the higher-level aspect
        copyAttribute.setType((PrimitiveType) getType(type, instantiation, aspect.getStructuralView()));

        // set name
        copyAttribute.setName(newName);

        doAdd(classifierMapping.getTo(), RamPackage.Literals.CLASS__ATTRIBUTES, copyAttribute);

        return copyAttribute;
    }

    /**
     * Creates a new parameter. In case the affected operation is used in message views, all messages are updated by
     * adding a corresponding parameter value mapping.
     *
     * @param owner the operation the parameter should be added to
     * @param index the index the parameter should be added at
     * @param name the name of the parameter
     * @param type the type of the parameter
     */
    public void createParameter(Operation owner, int index, String name, Type type) {
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(owner);
        CompoundCommand compoundCommand = new CompoundCommand();

        Parameter parameter = RamFactory.eINSTANCE.createParameter();
        parameter.setName(name);
        parameter.setType(type);

        compoundCommand.append(AddCommand.create(editingDomain, owner, RamPackage.Literals.OPERATION__PARAMETERS,
                parameter, index));

        // Find all messages where the operation is used as a signature.
        Collection<Message> affectedMessages = getAffectedMessages(owner);

        for (Message message : affectedMessages) {
            ParameterValueMapping parameterMapping = RamFactory.eINSTANCE.createParameterValueMapping();
            parameterMapping.setParameter(parameter);
            compoundCommand.append(AddCommand.create(editingDomain, message, RamPackage.Literals.MESSAGE__ARGUMENTS,
                    parameterMapping, index));
        }

        doExecute(editingDomain, compoundCommand);
    }

    /**
     * Removes the given parameter. In case the affected operation is used in message views, all messages are updated by
     * removing the corresponding parameter value mapping.
     *
     * @param parameter the parameter to be removed
     */
    public void removeParameter(Parameter parameter) {
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(parameter.eContainer());
        CompoundCommand compoundCommand = new CompoundCommand();

        compoundCommand.append(RemoveCommand.create(editingDomain, parameter));

        Collection<Message> affectedMessages = getAffectedMessages(parameter.eContainer());

        for (Message message : affectedMessages) {
            for (ParameterValueMapping parameterMapping : message.getArguments()) {
                if (parameterMapping.getParameter() == parameter) {
                    compoundCommand.append(RemoveCommand.create(editingDomain, parameterMapping));
                }
            }
        }

        doExecute(editingDomain, compoundCommand);
    }

    /**
     * Returns a collection of messages that have the given object set as the message's signature.
     *
     * @param objectOfInterest the object to search for
     * @return a collection of messages with the object as the signature
     */
    private Collection<Message> getAffectedMessages(EObject objectOfInterest) {
        EObject root = EcoreUtil.getRootContainer(objectOfInterest);

        Collection<Message> affectedMessages = new ArrayList<Message>();
        Collection<Setting> crossReferences = EcoreUtil.UsageCrossReferencer.find(objectOfInterest, root);

        for (Setting crossReference : crossReferences) {
            if (crossReference.getEStructuralFeature() == RamPackage.Literals.MESSAGE__SIGNATURE) {
                Message message = (Message) crossReference.getEObject();

                if (message.getMessageSort() == MessageSort.CREATE_MESSAGE
                        || message.getMessageSort() == MessageSort.SYNCH_CALL
                        && !(message.getSendEvent() instanceof Gate)) {
                    affectedMessages.add(message);
                }
            }
        }

        return affectedMessages;
    }

    /**
     * Adds the given classifier as a super type to the given class. Makes sure that the given super type can actually
     * be added, i.e., there is no existing inheritance between the two classes in either direction.
     *
     * @param subType the sub class the super type should be added to
     * @param superType the super type of the sub class
     */
    public void addSuperType(Class subType, Classifier superType) {
        // Avoid if same inheritance already exists.
        if (!RAMModelUtil.hasSuperClass(subType, superType)
                // and inheritance on the same class
                && (superType != subType)
                // and inheritance in the opposite direction
                && !((superType instanceof Class) && RAMModelUtil.hasSuperClass((Class) superType, subType))) {
            doAdd(subType, RamPackage.Literals.CLASSIFIER__SUPER_TYPES, superType);
        }
    }

    /**
     * Removes the given classifier as the super type of the given class. Determines which of the given classes is the
     * sub-type and the super-type, i.e., it is not necessary to determine this beforehand.
     *
     * @param classifier1 the first classifier
     * @param classifier2 the second classifier
     */
    public void removeSuperType(Classifier classifier1, Classifier classifier2) {
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(classifier1);

        Class subType;
        Classifier superType;

        // figure out which is the super type
        if (classifier1 instanceof Class && RAMModelUtil.hasSuperClass((Class) classifier1, classifier2)) {
            subType = (Class) classifier1;
            superType = classifier2;
        } else {
            subType = (Class) classifier2;
            superType = classifier1;
        }

        // Create remove command.
        Command removeCommand =
                RemoveCommand.create(editingDomain, subType, RamPackage.Literals.CLASSIFIER__SUPER_TYPES, superType);
        doExecute(editingDomain, removeCommand);
    }

    /**
     * Switched the abstract property of the given operation, and if its class is not abstract, set it abstract.
     *
     * @param operation the operation
     */
    public void switchOperationAbstract(Operation operation) {
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(operation);

        CompoundCommand compoundCommand = new CompoundCommand();

        boolean isOperationAbstract = operation.isAbstract();
        compoundCommand.append(SetCommand.create(editingDomain, operation, RamPackage.Literals.OPERATION__ABSTRACT,
                !isOperationAbstract));

        if (!isOperationAbstract) {
            EObject clazz = operation.eContainer();
            boolean isClassAbstract = (Boolean) clazz.eGet(RamPackage.Literals.CLASS__ABSTRACT);
            if (!isClassAbstract) {
                compoundCommand.append(SetCommand.create(editingDomain, clazz, RamPackage.Literals.CLASS__ABSTRACT,
                        !isClassAbstract));
            }
        }

        doExecute(editingDomain, compoundCommand);
    }

    /**
     * Switches the abstract property of the given class.
     *
     * @param clazz the class
     * @return true if the class has been switched. False otherwise.
     */
    public boolean switchAbstract(Class clazz) {
        if (!RAMModelUtil.classCanBeNotAbstract(clazz)) {
            return false;
        }
        doSwitch(clazz, RamPackage.Literals.CLASS__ABSTRACT);
        return true;
    }

    /**
     * Switches the static property of the given operation.
     *
     * @param operation the operation
     */
    public void switchOperationStatic(Operation operation) {
        doSwitch(operation, RamPackage.Literals.OPERATION__STATIC);
    }

    /**
     * Switches the static property of the given attribute.
     *
     * @param attribute the attribute
     */
    public void switchAttributeStatic(Attribute attribute) {
        doSwitch(attribute, RamPackage.Literals.STRUCTURAL_FEATURE__STATIC);
    }

    /**
     * Finds the corresponding type of the given type for the given {@link Instantiation}.
     *
     * @param oldType the type from the instantiated aspect
     * @param instantiation the instantiation
     * @param structuralView the structural view of the current aspect
     * @return the type from the higher-level aspect
     */
    // TODO: mschoettle: Move to RAMModelUtil. This method is messy, should be improved (maybe splitted).
    private Type getType(Type oldType, Instantiation instantiation, StructuralView structuralView) {
        Type newType = oldType;

        // handle collections
        if (oldType instanceof RCollection) {
            RCollection oldCollection = (RCollection) oldType;
            boolean found = false;

            // see if the collection already exists
            for (Type type : structuralView.getTypes()) {
                if (type.eClass().isInstance(oldType)) {
                    RCollection collection = (RCollection) type;
                    // check if there exists an appropriate type in the higher-level aspect
                    // e.g., if the type was mapped
                    Type collectionType = getType(oldCollection.getType(), instantiation, structuralView);

                    if (collectionType == collection.getType()) {
                        newType = type;
                        found = true;
                        break;
                    }
                }
            }

            // if no collection is found, create a new one
            if (!found) {
                RCollection newCollection = EcoreUtil.copy(oldCollection);
                Type oldCollectionType = newCollection.getType();
                newCollection.setType((ObjectType) getType(oldCollectionType, instantiation, structuralView));

                // TODO: Should use a command here
                // (but might cause confusion because the user doesn't see anything visual)
                structuralView.getTypes().add(newCollection);
                newType = newCollection;
            }
        } else if (!(oldType instanceof Class)) {
            // handle everything else, except classes (e.g., primitive types and special types)
            for (Type type : structuralView.getTypes()) {
                if (type.getName().equals(oldType.getName())) {
                    newType = type;
                }
            }
        }
        // if no types have been found yet, check the mappings
        if (oldType instanceof Classifier && newType == oldType) {
            for (ClassifierMapping classifierMapping : instantiation.getMappings()) {

                if (classifierMapping.getFrom() == oldType && classifierMapping.getTo() != null) {
                    newType = classifierMapping.getTo();
                    // TODO: if a class is mapped to 2 or more classes we are now using the first one as the signature.
                    // you can inform the user about this.
                    break;
                }
            }
        }

        return newType;
    }

}
