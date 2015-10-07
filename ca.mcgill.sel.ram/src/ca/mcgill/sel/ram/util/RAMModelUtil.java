package ca.mcgill.sel.ram.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.EcoreUtil;

import ca.mcgill.sel.commons.StringUtil;
import ca.mcgill.sel.commons.emf.util.EMFModelUtil;
import ca.mcgill.sel.core.COREConcern;
import ca.mcgill.sel.core.COREFeature;
import ca.mcgill.sel.core.COREModelCompositionSpecification;
import ca.mcgill.sel.core.COREModelReuse;
import ca.mcgill.sel.core.CORENamedElement;
import ca.mcgill.sel.core.COREPartialityType;
import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.core.util.COREModelUtil;
import ca.mcgill.sel.ram.AbstractMessageView;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.AssociationEnd;
import ca.mcgill.sel.ram.Class;
import ca.mcgill.sel.ram.Classifier;
import ca.mcgill.sel.ram.ClassifierMapping;
import ca.mcgill.sel.ram.CombinedFragment;
import ca.mcgill.sel.ram.FragmentContainer;
import ca.mcgill.sel.ram.Gate;
import ca.mcgill.sel.ram.ImplementationClass;
import ca.mcgill.sel.ram.Instantiation;
import ca.mcgill.sel.ram.InstantiationType;
import ca.mcgill.sel.ram.Interaction;
import ca.mcgill.sel.ram.InteractionFragment;
import ca.mcgill.sel.ram.InteractionOperand;
import ca.mcgill.sel.ram.Layout;
import ca.mcgill.sel.ram.LayoutElement;
import ca.mcgill.sel.ram.Lifeline;
import ca.mcgill.sel.ram.Message;
import ca.mcgill.sel.ram.MessageOccurrenceSpecification;
import ca.mcgill.sel.ram.MessageSort;
import ca.mcgill.sel.ram.MessageView;
import ca.mcgill.sel.ram.ObjectType;
import ca.mcgill.sel.ram.OpaqueExpression;
import ca.mcgill.sel.ram.Operation;
import ca.mcgill.sel.ram.OperationType;
import ca.mcgill.sel.ram.Parameter;
import ca.mcgill.sel.ram.PrimitiveType;
import ca.mcgill.sel.ram.RAny;
import ca.mcgill.sel.ram.RBoolean;
import ca.mcgill.sel.ram.RSequence;
import ca.mcgill.sel.ram.RVoid;
import ca.mcgill.sel.ram.RamFactory;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.Reference;
import ca.mcgill.sel.ram.StructuralFeature;
import ca.mcgill.sel.ram.StructuralView;
import ca.mcgill.sel.ram.Type;

/**
 * Helper class with convenient static methods for working with RAM EMF model objects.
 *
 * @author mschoettle
 */
public final class RAMModelUtil {

    /**
     * Default name for constructor operation.
     */
    public static final String CONSTRUCTOR_NAME = "create";

    /**
     * Default name for destructor operation.
     */
    public static final String DESTRUCTOR_NAME = "destroy";

    /**
     * The language for opaque expressions.
     */
    private static final String OPAQUE_EXPRESSION_LANGUAGE = "java";

    /**
     * Creates a new instance of {@link RAMModelUtil}.
     */
    private RAMModelUtil() {
        // suppress default constructor
    }

    /**
     * Collects a set of instantiations for aspects that are extended by this aspect.
     * Collects these recursively in case an extended aspect also extends another aspect.
     *
     * @param aspect the current aspect
     * @return the set of instantiations for aspects that are extended this aspect
     */
    public static Set<Instantiation> collectExtendsInstantiations(Aspect aspect) {
        return collectExtendsInstantiations(aspect, new HashSet<Instantiation>());
    }

    /**
     * Collects a set of instantiations for aspects that are extended by this aspect.
     * Collects these recursively in case an extended aspect also extends another aspect.
     *
     * @param aspect - the current aspect
     * @param extendedAspects - the current set of found instantiations.
     *            Needed to avoid infinite loop in case there are cyclic dependencies
     * @return the set of instantiations for aspects that are extended this aspect
     */
    private static Set<Instantiation> collectExtendsInstantiations(Aspect aspect, Set<Instantiation> extendedAspects) {
        for (Instantiation instantiation : aspect.getInstantiations()) {
            if (instantiation.getType() == InstantiationType.EXTENDS) {
                // If it was already added, it is not necessary to search for extended aspects of that aspect again
                // this prevents infinite loops in case of cyclic dependencies between aspects
                if (extendedAspects.add(instantiation)) {
                    collectExtendsInstantiations(instantiation.getSource(), extendedAspects);
                }
            }
        }
        return extendedAspects;
    }

    /**
     * Unloads all external resources that the given aspect references.
     *
     * @param aspect the aspect that has references to external resources
     */
    public static void unloadExternalResources(Aspect aspect) {
        Set<Instantiation> instantiations = new HashSet<Instantiation>();
        instantiations.addAll(collectAllReuseInstantiations(aspect));
        instantiations.addAll(collectExtendsInstantiations(aspect));

        for (Instantiation instantiation : instantiations) {
            COREModelUtil.unloadEObject(instantiation.getSource());
        }
    }

    /**
     * Collects a set of aspects that are extended by this aspect.
     * Collects these recursively in case an extended aspect also extends another aspect.
     *
     * @param aspect the current aspect
     * @return the set of aspects that are extended by this aspect
     */
    public static Set<Aspect> collectExtendedAspects(Aspect aspect) {
        Set<Instantiation> instantiations = collectExtendsInstantiations(aspect);
        Set<Aspect> extendedAspects = new HashSet<Aspect>();

        for (Instantiation instantiation : instantiations) {
            extendedAspects.add(instantiation.getSource());
        }

        return extendedAspects;
    }

    /**
     * Look for the closest Aspect realizing a parent feature.
     * Only return non conflict resolution aspects.
     *
     * @param feature - The feature we want the realizing aspect for.
     * @return the closest aspect, or null if none exists or they all are conflict resolution aspects.
     */
    public static Aspect getParentAspect(COREFeature feature) {
        if (feature == null || feature.getParent() == null) {
            return null;
        }
        COREFeature parent = feature.getParent();
        // Collect all realizing aspects

        Collection<Aspect> aspects = EMFModelUtil.collectElementsOfType(parent,
                CorePackage.Literals.CORE_FEATURE__REALIZED_BY, RamPackage.eINSTANCE.getAspect());

        // Look for the first aspect realizing only the parent feature
        for (Aspect aspect : aspects) {
            if (aspect.getRealizes().size() == 1) {
                return aspect;
            }
        }
        // No realizing aspect was found, search in parent
        return getParentAspect(parent);
    }

    /**
     * Returns the set of instantiations belonging to reuses of the aspect.
     *
     * @param aspect the aspect with reuses
     * @return the set of instantiations
     */
    public static Set<Instantiation> getReuseInstantiations(Aspect aspect) {
        Set<Instantiation> instantiations = new HashSet<Instantiation>();

        for (COREModelReuse modelReuse : aspect.getModelReuses()) {
            for (COREModelCompositionSpecification<?> compositionSpecification : modelReuse.getCompositions()) {
                Instantiation instantiation = (Instantiation) compositionSpecification;
                instantiations.add(instantiation);
            }
        }

        return instantiations;
    }

    /**
     * Returns the set of instantiations belong to (model) reuses of the aspect
     * and all its extended aspects in the hierarchy.
     *
     * @param aspect the {@link Aspect}
     * @return the set of model reuse instantiations
     */
    public static Set<Instantiation> collectAllReuseInstantiations(Aspect aspect) {
        Set<Instantiation> instantiations = new HashSet<Instantiation>();

        Set<Aspect> aspects = collectExtendedAspects(aspect);
        aspects.add(aspect);

        for (Aspect currentAspect : aspects) {
            instantiations.addAll(getReuseInstantiations(currentAspect));
        }

        return instantiations;
    }

    /**
     * Creates a new interaction that specifies the given operation.
     * It contains the initial call and a return message if it does not return void.
     *
     * @param operation the operation a message view should be created for
     * @return the {@link Interaction} for the given operation with no behaviour
     */
    public static Interaction createInteraction(Operation operation) {
        RamFactory factory = RamFactory.eINSTANCE;

        // Create interaction.
        Interaction interaction = factory.createInteraction();

        // Create the lifeline.
        Lifeline lifeline = factory.createLifeline();
        interaction.getLifelines().add(lifeline);

        // Create represents.
        Reference represents = factory.createReference();
        represents.setLowerBound(1);
        represents.setName("target");
        represents.setType((Classifier) operation.eContainer());

        interaction.getProperties().add(represents);
        lifeline.setRepresents(represents);

        MessageSort messageSort = null;

        if (operation != null) {
            switch (operation.getOperationType()) {
                case CONSTRUCTOR:
                    messageSort = MessageSort.CREATE_MESSAGE;
                    break;
                case DESTRUCTOR:
                    messageSort = MessageSort.DELETE_MESSAGE;
                    break;
                default:
                    messageSort = MessageSort.SYNCH_CALL;
                    break;
            }
        }

        createInitialMessage(interaction, lifeline, operation, messageSort);

        // Create return message if operation doesn't return void.
        if (!operation.getReturnType().eClass().isInstance(RamPackage.Literals.RVOID)) {
            createInitialMessage(interaction, lifeline, operation, MessageSort.REPLY);
        }

        return interaction;
    }

    /**
     * Creates an initial message in the given interaction calling the given operation.
     * The message is coming from a gate and received by the given lifeline.
     * It is added to the interaction.
     *
     * @param interaction the interaction the message belongs to
     * @param lifeline the lifeline that receives the message call
     * @param operation the operation that is called on the lifeline
     * @param messageSort the kind of the message call
     */
    public static void createInitialMessage(Interaction interaction, Lifeline lifeline, Operation operation,
            MessageSort messageSort) {
        // create gate
        Gate gate = RamFactory.eINSTANCE.createGate();

        String gateName = (messageSort == MessageSort.REPLY) ? "out_" : "in_";
        gateName += operation.getName();

        gate.setName(gateName);
        interaction.getFormalGates().add(gate);

        // create receive event
        MessageOccurrenceSpecification event = RamFactory.eINSTANCE.createMessageOccurrenceSpecification();
        event.getCovered().add(lifeline);
        interaction.getFragments().add(event);

        // create message
        Message message = RamFactory.eINSTANCE.createMessage();
        message.setMessageSort(messageSort);
        message.setSignature(operation);
        interaction.getMessages().add(message);

        // set references
        event.setMessage(message);
        gate.setMessage(message);

        if (messageSort == MessageSort.REPLY) {
            message.setSendEvent(event);
            message.setReceiveEvent(gate);
        } else {
            message.setSendEvent(gate);
            message.setReceiveEvent(event);
        }
    }

    /**
     * Creates a new operation with the given properties if it not already exists. Otherwise it returns null.
     *
     * @param owner - owner of the operation (used to check if operation is unique)
     * @param name the name of the operation
     * @param returnType the return type of the operation
     * @param parameters a list of parameters for the operation
     * @return a new {@link Operation} with the given properties set or null if the operation already exists
     */
    public static Operation createOperation(EObject owner, String name,
            Type returnType, List<Parameter> parameters) {
        return createOperation(owner, name, returnType, parameters, true);
    }

    /**
     * Creates a new operation with the given properties. It can check if operation already exists and returns null in
     * this case
     *
     * @param owner - owner of the operation (used to check if operation is unique)
     * @param name the name of the operation
     * @param returnType the return type of the operation
     * @param parameters a list of parameters for the operation
     * @param checkUnicity - true if you want to check if operation already exists
     * @return a new {@link Operation} with the given properties set or null if the operation already exists
     */
    public static Operation createOperation(EObject owner, String name,
            Type returnType, List<Parameter> parameters, boolean checkUnicity) {

        if (checkUnicity && !RAMModelUtil.isUniqueOperation(owner, name, parameters)) {
            return null;
        }

        Operation operation = RamFactory.eINSTANCE.createOperation();
        operation.setName(name);
        operation.setReturnType(returnType);

        if (parameters != null) {
            operation.getParameters().addAll(parameters);
        }

        return operation;
    }

    /**
     * Returns a new {@link OpaqueExpression} with default language.
     *
     * @return create {@link OpaqueExpression} with default language.
     */
    public static OpaqueExpression createOpaqueExpression() {
        OpaqueExpression specification = RamFactory.eINSTANCE.createOpaqueExpression();
        specification.setLanguage(OPAQUE_EXPRESSION_LANGUAGE);

        return specification;
    }

    /**
     * Creates a message view for the given operation.
     * The message view contains the initial call, but is otherwise empty.
     *
     * @param operation the {@link Operation} to create a message view for
     * @return the message view for the operation
     */
    public static MessageView createMessageView(Operation operation) {
        MessageView messageView = RamFactory.eINSTANCE.createMessageView();
        messageView.setSpecifies(operation);

        Interaction specification = RAMModelUtil.createInteraction(operation);
        messageView.setSpecification(specification);

        return messageView;
    }

    /**
     * Returns whether the given sub-class has the given super class as a super type.
     * I.e., the sub-class inherits from the super class.
     *
     * @param subType the class that is considered a sub-class
     * @param superType the classifier that is considered a super-type
     * @return true, if the sub-type class has the super-type classifier as a super type, false otherwise
     */
    public static boolean hasSuperClass(Class subType, Classifier superType) {
        for (Classifier classifier : subType.getSuperTypes()) {
            if (classifier == superType) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks whether the given name is unique inside the owners feature.
     *
     * @param owner the owner containing {@link CORENamedElement}s
     * @param feature the feature of the owner containing {@link CORENamedElement}s
     * @param name the name to be checked for uniqueness
     * @return true, if the given name is unique among the {@link CORENamedElement}s of the owners feature,
     *         false otherwise
     */
    public static boolean isUniqueName(EObject owner, EStructuralFeature feature, String name) {
        Collection<CORENamedElement> children =
                EMFModelUtil.collectElementsOfType(owner, feature, CorePackage.eINSTANCE.getCORENamedElement());

        for (CORENamedElement namedElement : children) {
            if (namedElement.getName() != null && namedElement.getName().equalsIgnoreCase(name)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks whether the given object is unique inside the owners feature.
     * Call different methods depends on the type object to be checked
     *
     * @param owner the owner containing {@link CoreNamedElement}s
     * @param feature the feature of the owner containing {@link CoreNamedElement}s
     * @param name the name to be checked for uniqueness
     * @param object the object to be checked for uniqueness (used for signature operation checking)
     * @return true, if the given name is unique among the {@link CoreNamedElement}s of the owners feature,
     *         false otherwise
     */
    public static boolean isUnique(EObject owner, EStructuralFeature feature, String name, EObject object) {
        if (feature == RamPackage.Literals.CLASSIFIER__OPERATIONS) {
            Operation operation = (Operation) object;
            return isUniqueOperation(owner, name, operation.getParameters());
        } else {
            return isUniqueName(owner, feature, name);
        }
    }

    /**
     * Checks whether the given operation is unique inside the owners operations.
     * (Check name and the list of parameters)
     *
     * @param owner the owner containing {@link CORENamedElement}s
     * @param name the name to be checked for uniqueness
     * @param parameters the list of parameters to be checked for uniqueness
     * @return true, if the given name (and this list of parameters) is unique
     *         among the {@link Operation}s of the owners feature, false otherwise
     */
    public static boolean isUniqueOperation(EObject owner, String name, List<Parameter> parameters) {
        @SuppressWarnings("unchecked")
        List<Operation> children = (List<Operation>) owner.eGet(RamPackage.Literals.CLASSIFIER__OPERATIONS);

        for (Operation operationElement : children) {
            if (operationElement.getName() != null && operationElement.getName().equalsIgnoreCase(name)) {

                // If the one of two operations have null parameters and the other 0 parameters
                // Or if both operations have null parameters
                if ((parameters == null && operationElement.getParameters() == null)
                        || (parameters == null && operationElement.getParameters().size() == 0)
                        || (operationElement.getParameters() == null && parameters.size() == 0)) {
                    return false;
                }

                // If operations have parameters
                if (parameters != null && operationElement.getParameters() != null
                        && parameters.size() == operationElement.getParameters().size()) {
                    boolean equal = true;
                    for (int i = 0; i < parameters.size() && equal; i++) {
                        if (parameters.get(i).getType() != operationElement.getParameters().get(i).getType()) {
                            equal = false;
                        }
                    }
                    if (equal) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * Constructs and returns related getter of the given attribute. If the operation already has a getter, it will
     * return a copy of its getter.
     *
     *
     * @param attribute - the attribute to extract its getter
     * @return the getter of the attribute
     */
    public static Operation extractGetter(StructuralFeature attribute) {
        Class owner = (Class) attribute.eContainer();

        if (owner == null) {
            return null;
        }

        StructuralView structuralView = (StructuralView) owner.eContainer();
        String attributeName = StringUtil.toUpperCaseFirst(attribute.getName());
        Type attributeType = attribute.getType();

        // If the attribute parameter is an AssociationEnd object,
        // we have to check the multiplicity
        if (attribute instanceof AssociationEnd) {
            attributeType =
                    RAMModelUtil.getTypeDependingMultiplicityOfAssociationEnd((AssociationEnd) attribute,
                            structuralView);
        }

        String prefix = (attribute.getType() instanceof RBoolean) ? "is" : "get";

        return RAMModelUtil.createOperation(owner, prefix + attributeName, attributeType, null);
    }

    /**
     * Constructs and returns related setter of the given attribute. If the operation already has a setter, it will
     * return a copy of its setter.
     *
     *
     * @param attribute - the attribute to extract its setter
     * @return the setter of the attribute
     */
    public static Operation extractSetter(StructuralFeature attribute) {
        Class owner = (Class) attribute.eContainer();

        if (owner == null) {
            return null;
        }

        // get the void type from the structural view
        StructuralView structuralView = (StructuralView) owner.eContainer();
        RVoid voidType = RAMModelUtil.getVoidType(structuralView);
        String attributeName = StringUtil.toUpperCaseFirst(attribute.getName());

        Parameter parameter = RamFactory.eINSTANCE.createParameter();
        Type attributeType = attribute.getType();

        // If the attribute parameter is an AssociationEnd object,
        // we have to check the multiplicity
        if (attribute instanceof AssociationEnd) {
            attributeType =
                    RAMModelUtil.getTypeDependingMultiplicityOfAssociationEnd((AssociationEnd) attribute,
                            structuralView);
        }

        parameter.setType(attributeType);
        parameter.setName(attribute.getName());
        List<Parameter> parameters = new ArrayList<Parameter>();
        parameters.add(parameter);

        return RAMModelUtil.createOperation(owner, "set" + attributeName, voidType, parameters);
    }

    /**
     * Check if the given attribute has already its getter into its owner.
     *
     * @param attribute - the attribute to check
     * @return true if the attribute has its getter into its owner
     */
    public static boolean isGetterUnique(StructuralFeature attribute) {
        Operation getter = extractGetter(attribute);
        return getter == null;
    }

    /**
     * Check if the given attribute has already its setter into its owner.
     *
     * @param attribute - the attribute to check
     * @return true if the attribute has its setter into its owner
     */
    public static boolean isSetterUnique(StructuralFeature attribute) {
        Operation setter = extractSetter(attribute);
        return setter == null;
    }

    /**
     * Checks the multiplicity of the {@link AssociationEnd} in order to create or not an array type rather than a
     * simple type. Checks the upperBound : if it is upper than 1, it is an array of the {@link AssociationEnd} type.
     *
     * @param attribute {@link AssociationEnd} which have to be checked for multiplicity.
     * @param structuralView The corresponding {@link StructuralView}.
     * @return The new type of the attribute (array of the {@link AssociationEnd} type or just the
     *         {@link AssociationEnd} type.
     */
    public static Type getTypeDependingMultiplicityOfAssociationEnd(AssociationEnd attribute,
            StructuralView structuralView) {
        Type attributeType = attribute.getType();
        AssociationEnd asso = attribute;

        // If the upperBound is not 1, we need an array
        if (asso.getUpperBound() != 1) {
            RSequence rSeq = null;
            for (Type t : structuralView.getTypes()) {
                if (t instanceof RSequence && ((RSequence) t).getType() == attributeType) {
                    rSeq = (RSequence) t;
                }
            }
            if (rSeq == null) {
                rSeq = RamFactory.eINSTANCE.createRSequence();
                rSeq.setType((ObjectType) attributeType);
                structuralView.getTypes().add(rSeq);
            }
            attributeType = rSeq;
        }

        return attributeType;
    }

    /**
     * Gets the void type from the structural view.
     *
     * @param structuralView the structuralView
     * @return the void type, null if not found
     */
    public static RVoid getVoidType(StructuralView structuralView) {
        for (Type type : structuralView.getTypes()) {
            if (RVoid.class.isInstance(type)) {
                return (RVoid) type;
            }
        }

        return null;
    }

    /**
     * Returns a list of all primitive types contained in the given structural view.
     *
     * @param structuralView the {@link StructuralView}
     * @return a list of all primitive types of the given structural view
     */
    public static List<PrimitiveType> getPrimitiveTypes(StructuralView structuralView) {
        List<PrimitiveType> primitiveTypes = new ArrayList<PrimitiveType>();

        for (Type type : structuralView.getTypes()) {
            if (type instanceof PrimitiveType) {
                primitiveTypes.add((PrimitiveType) type);
            }
        }

        return primitiveTypes;
    }

    /**
     * Creates a new {@link Aspect} with the given name, and an empty {@link StructuralView} containing default types.
     *
     * @param name - The name to give to the aspect
     * @return the newly created {@link Aspect}
     */
    public static Aspect createAspect(String name) {
        return createAspect(name, null);
    }

    /**
     * Creates a new {@link Aspect} with an empty {@link StructuralView} containing default types.
     *
     * @param baseName - The baseName to give to the aspect.
     * @param concern - The concern containing the aspect. Only used to create a unique name.
     * @return the newly created {@link Aspect}
     */
    public static Aspect createAspect(String baseName, COREConcern concern) {
        Aspect aspect = RamFactory.eINSTANCE.createAspect();
        Collection<Aspect> aspects =
                EMFModelUtil.collectElementsOfType(concern, CorePackage.Literals.CORE_CONCERN__MODELS,
                        RamPackage.eINSTANCE.getAspect());
        aspect.setName(COREModelUtil.createUniqueNameFromElements(baseName, aspects));

        // create structural view as it is needed
        StructuralView structuralView = RamFactory.eINSTANCE.createStructuralView();
        aspect.setStructuralView(structuralView);

        // create default types
        createDefaultTypes(structuralView);

        // create empty layout
        createLayout(aspect, structuralView);

        return aspect;
    }

    /**
     * Creates all the primitive types that do not currently exist in the structural view (and sets them...).
     *
     * @param structuralView the structural view of interest
     */
    private static void createDefaultTypes(StructuralView structuralView) {
        if (getTypeInstance(structuralView, RVoid.class) == null) {
            structuralView.getTypes().add(RamFactory.eINSTANCE.createRVoid());
        }

        if (getTypeInstance(structuralView, RAny.class) == null) {
            structuralView.getTypes().add(RamFactory.eINSTANCE.createRAny());
        }

        // add all primitive types
        EClass primitiveTypeClass = RamPackage.eINSTANCE.getPrimitiveType();

        for (EClassifier classifier : RamPackage.eINSTANCE.getEClassifiers()) {
            if (classifier instanceof EClass) {
                EClass clazz = (EClass) classifier;

                // is it a PrimitiveType but not an Enum or Array
                if (!clazz.isAbstract() && primitiveTypeClass.isSuperTypeOf(clazz)
                        && !RamPackage.eINSTANCE.getREnum().equals(clazz)
                        && !RamPackage.eINSTANCE.getRArray().equals(clazz)) {

                    boolean alreadyExists = false;

                    // if the type already exists we don't want to add it another time
                    for (Type type : structuralView.getTypes()) {

                        if (type.eClass().equals(clazz)) {
                            alreadyExists = true;
                            break;
                        }

                    }

                    if (!alreadyExists) {
                        Type newObject = (Type) RamFactory.eINSTANCE.create(clazz);
                        structuralView.getTypes().add(newObject);
                    }
                }
            }
        }
    }

    /**
     * Returns the type instance of the given class that is a type of the given structural view.
     * If no such type is found, null is returned.
     *
     * @param structuralView the {@link StructuralView} containing the types
     * @param typeClass the {@link java.lang.Class} of which an instance is requested
     * @param <T> the type of the requested type
     * @return the type instance of the given class, null if none is found
     */
    private static <T extends Type> T getTypeInstance(StructuralView structuralView, java.lang.Class<T> typeClass) {
        for (final Type type : structuralView.getTypes()) {
            if (typeClass.isInstance(type)) {
                @SuppressWarnings("unchecked")
                T typed = (T) type;
                return typed;
            }
        }
        return null;
    }

    /**
     * Creates a new layout for a given {@link StructuralView}.
     * The layout is the {@link ca.mcgill.sel.ram.impl.ContainerMapImpl} specifically
     * that holds all {@link LayoutElement} for children of the given {@link StructuralView}.
     *
     * @param aspect the aspect
     * @param structuralView the {@link StructuralView} holding the {@link LayoutElement} for its children
     */
    public static void createLayout(Aspect aspect, StructuralView structuralView) {
        Layout layout = RamFactory.eINSTANCE.createLayout();

        // workaround used here since creating the map, adding the values and then putting it doesn't work
        // EMF somehow does some magic with the passed map instance
        layout.getContainers().put(structuralView, new BasicEMap<EObject, LayoutElement>());

        aspect.setLayout(layout);
    }

    /**
     * Returns whether a message view for the given operation exists.
     *
     * @param aspect the aspect containing the operation and message views
     * @param operation the {@link Operation}
     * @return true, if a {@link MessageView} exists, false otherwise
     */
    public static boolean isMessageViewDefined(Aspect aspect, Operation operation) {
        for (MessageView messageView : getMessageViewsOfType(aspect, MessageView.class)) {
            if (messageView instanceof MessageView && ((MessageView) messageView).getSpecifies() == operation) {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns a list of message views from the given aspect
     * that are an instance of the given type. It filters out all message views
     * that do not conform to the type given.
     *
     * @param aspect the aspect that contains message views
     * @param type the class of the message view type to be retrieved
     * @param <T> the type of the message view, i.e., a sub-class of {@link AbstractMessageView}
     * @return a list of message views containing only the given type
     */
    public static <T extends EObject> List<T> getMessageViewsOfType(Aspect aspect, java.lang.Class<T> type) {
        List<T> filteredMessageViews = new ArrayList<T>();

        for (AbstractMessageView messageView : aspect.getMessageViews()) {
            if (type.isInstance(messageView)) {
                @SuppressWarnings("unchecked")
                T typed = (T) messageView;
                filteredMessageViews.add(typed);
            }
        }

        return filteredMessageViews;
    }

    /**
     * Returns the message view that specifies the given operation.
     * Returns null if no message view could be found.
     *
     * @param aspect the aspect that contains the operation
     * @param operation the {@link Operation}
     * @return the {@link MessageView} that specifies the operation, null otherwise
     */
    public static MessageView getMessageViewFor(Aspect aspect, Operation operation) {
        for (MessageView messageView : getMessageViewsOfType(aspect, MessageView.class)) {
            if (messageView.getSpecifies() == operation) {
                return messageView;
            }
        }

        return null;
    }

    /**
     * Collects a set of classifiers that contains the classifier, its super types and all other classifiers
     * that this classifiers is mapped to. Performs this "bottom-up", i.e., starting at the current aspect up the
     * extended aspects hierarchy.
     * If the given classifier is not from the given aspect, the classifier is resolved
     * to the one from the given aspect so that the "bottom-up" approach can be used.
     *
     * @param aspect the aspect containing the classifier
     * @param classifier the classifier all classifiers to retrieve for
     * @return a set of classifiers that this classifier is mapped to, including the classifier itself
     */
    public static Set<Classifier> collectClassifiersFor(Aspect aspect, Classifier classifier) {
        Classifier actualClassifier = resolveClassifier(aspect, classifier);

        Set<Classifier> classifiers = new HashSet<Classifier>();

        collectClassifiersRecursive(classifiers, aspect, actualClassifier);
        // Finally, when extending another aspect and a classifier of the other aspect is called,
        // but a classifier in the current aspect with the same name exists, it has to be considered as well.
        for (Classifier ownClassifier : aspect.getStructuralView().getClasses()) {
            for (Classifier extendedClassifier : new HashSet<Classifier>(classifiers)) {
                if (isNameEqual(ownClassifier, extendedClassifier)) {
                    classifiers.add(ownClassifier);
                }
            }
        }

        return classifiers;
    }

    /**
     * Checks whether the name of two given classifiers are the same.
     * In case of implementation classes with generics, their type parameters have to match as well.
     *
     * @param classifier1 the first classifier
     * @param classifier2 the second classifier
     * @return true, if the name matches, false otherwise
     */
    private static boolean isNameEqual(Classifier classifier1, Classifier classifier2) {
        if (classifier2.getName().equals(classifier1.getName())) {
            if (classifier2 instanceof ImplementationClass
                    && classifier1 instanceof ImplementationClass) {
                ImplementationClass c1 = (ImplementationClass) classifier2;
                ImplementationClass c2 = (ImplementationClass) classifier1;
                if (isNameEqual(c1, c2)) {
                    return true;
                }
            } else if (classifier1 instanceof Class
                    && classifier2 instanceof Class) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks whether the two given implementation classes are the same.
     * Two implementation classes are the same if besides their name,
     * also the type parameters match in type and name.
     *
     * @param class1 the first {@link ImplementationClass}
     * @param class2 the second {@link ImplementationClass}
     * @return true, if they are the same, false otherwise
     */
    private static boolean isNameEqual(ImplementationClass class1, ImplementationClass class2) {
        if (class1.getInstanceClassName().equals(class2.getInstanceClassName())) {
            typeParametersMatch(class1, class2);
        }

        return false;
    }

    /**
     * Returns whether the type parameters of two given implementation classes are the same.
     *
     * @param class1 the first {@link ImplementationClass}
     * @param class2 the second {@link ImplementationClass}
     * @return true, if they are the same, false otherwise
     */
    public static boolean typeParametersMatch(ImplementationClass class1, ImplementationClass class2) {
        if (class1.getTypeParameters().size() == class2.getTypeParameters().size()) {
            for (int index = 0; index < class1.getTypeParameters().size(); index++) {
                Type type = class1.getTypeParameters().get(index).getGenericType();
                Type type2 = class2.getTypeParameters().get(index).getGenericType();
                if ((type != null ^ type2 != null)
                        || (type != null && type2 != null && !type.getName().equals(type2.getName()))) {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    /**
     * Recursively collects a set of classifiers that contains the classifier, its super types and all other classifiers
     * that this classifiers is mapped to.
     * Checks the directly extended aspects and the model reuses of the given aspect.
     * Performs this recursively for every classifier found, in order to retrieve the complete set of classifiers.
     *
     * @param classifiers the current set of found classifiers, which classifiers are added to
     * @param aspect the aspect containing the classifier
     * @param classifier the classifier all classifiers to retrieve for
     */
    private static void collectClassifiersRecursive(Set<Classifier> classifiers, Aspect aspect, Classifier classifier) {
        classifiers.add(classifier);

        Set<Instantiation> instantiations = new HashSet<Instantiation>();
        instantiations.addAll(aspect.getInstantiations());
        instantiations.addAll(getReuseInstantiations(aspect));

        for (Instantiation instantiation : instantiations) {
            for (ClassifierMapping mapping : instantiation.getMappings()) {
                if (mapping.getTo() == classifier) {
                    collectClassifiersRecursive(classifiers, instantiation.getSource(), mapping.getFrom());
                }
            }
        }

        // Classifiers from extended aspects with the same name as the called classifier
        // need to be considered, because they are implicitly mapped.
        // At the same time, if the extendedClassifier is
        // an Implementation Class (independent of the type of instantiation), it needs to be considered as well.
        instantiations.addAll(collectExtendsInstantiations(aspect));
        for (Instantiation instantiation : instantiations) {
            StructuralView structuralView = instantiation.getSource().getStructuralView();

            for (Classifier extendedClassifier : structuralView.getClasses()) {
                if (instantiation.getType() == InstantiationType.EXTENDS
                        && !(extendedClassifier instanceof ImplementationClass)) {
                    if (classifier.getName().equals(extendedClassifier.getName())) {
                        collectClassifiersRecursive(classifiers, instantiation.getSource(), extendedClassifier);
                    }
                } else if (extendedClassifier instanceof ImplementationClass
                        && classifier instanceof ImplementationClass) {
                    ImplementationClass c1 = (ImplementationClass) extendedClassifier;
                    ImplementationClass c2 = (ImplementationClass) classifier;
                    if (isNameEqual(c1, c2)) {
                        collectClassifiersRecursive(classifiers, instantiation.getSource(), extendedClassifier);
                    }
                }
            }
        }

        // Add the super types of each possible classifier.
        for (Classifier superType : classifier.getSuperTypes()) {
            collectClassifiersRecursive(classifiers, aspect, superType);
        }
    }

    /**
     * Resolves the given classifier to the corresponding classifier in the given aspect.
     * Returns the given classifier if it is already contained in the aspect.
     * For instance, mapping the classifier to another classifier B in the given aspect
     * would result in B being returned.
     *
     * @param aspect the {@link Aspect}
     * @param classifier the {@link Classifier} to find the corresponding one in the aspect for
     * @return the given classifier, if it is already contained in the aspect,
     *         otherwise the located classifier in the aspect corresponding to the given classifier
     */

    public static Classifier resolveClassifier(Aspect aspect, Classifier classifier) {
        if (EcoreUtil.getRootContainer(classifier) != aspect) {
            Set<Instantiation> instantiations = collectExtendsInstantiations(aspect);
            instantiations.addAll(collectAllReuseInstantiations(aspect));

            Collection<Setting> crossReferences =
                    EcoreUtil.UsageCrossReferencer.find(classifier, classifier.eResource().getResourceSet());

            for (Setting crossReference : crossReferences) {
                if (crossReference.getEStructuralFeature() == CorePackage.Literals.CORE_MAPPING__FROM) {
                    ClassifierMapping classifierMapping = (ClassifierMapping) crossReference.getEObject();
                    Instantiation instantiation = (Instantiation) classifierMapping.eContainer();
                    if (instantiations.contains(instantiation) && classifierMapping.getTo() != null) {
                        return resolveClassifier(aspect, classifierMapping.getTo());
                    }
                }
            }

            for (Classifier currentClassifier : aspect.getStructuralView().getClasses()) {
                if (isNameEqual(classifier, currentClassifier)) {
                    return currentClassifier;
                }
            }
        }

        return classifier;
    }

    /**
     * Finds the index of where to add a new operation to the class.
     * Constructors are added at the beginning, but after existing constructors.
     * Destructors are added after the constructors, but also after existing destructors.
     *
     * @param owner the class to add an operation to
     * @param type the type of the operation
     * @return the index of where to add a new operation for the given type
     */
    public static int findConstructorIndexFor(Classifier owner, OperationType type) {
        int index = 0;

        for (Operation operation : owner.getOperations()) {
            switch (operation.getOperationType()) {
                case CONSTRUCTOR:
                    index++;
                    break;
                case DESTRUCTOR:
                    if (type == OperationType.DESTRUCTOR) {
                        index++;
                    }
                    break;
                case NORMAL:
                    return index;
            }
        }

        return index;
    }

    /**
     * Finds the initial message the given fragment is part of.
     * The initial message is the one that represents the operation being defined.
     * It contains the local properties of the defined behaviour.
     * Usually this is the first message of a message view, sent from a gate.
     * However, with nested behaviour definition it could be a message somewhere inside the message view.
     *
     * @param fragment the current fragment for which to find the initial message
     * @return the initial message, null if none found
     */
    public static Message findInitialMessage(InteractionFragment fragment) {
        FragmentContainer container = fragment.getContainer();

        if (fragment.getCovered().size() > 0) {
            // CombinedFragments have more than one, but we assume that the initial lifeline was added first.
            Lifeline coveredLifeline = fragment.getCovered().get(0);

            int index = container.getFragments().indexOf(fragment);

            for (int i = index; i >= 0; i--) {
                InteractionFragment currentFragment = container.getFragments().get(i);

                if (currentFragment.getCovered().contains(coveredLifeline)
                        && currentFragment instanceof MessageOccurrenceSpecification) {
                    MessageOccurrenceSpecification messageEnd = (MessageOccurrenceSpecification) currentFragment;
                    Message message = messageEnd.getMessage();

                    if (message != null
                            && message.getReceiveEvent() == messageEnd
                            && !message.isSelfMessage()
                            && message.getMessageSort() != MessageSort.REPLY) {
                        return message;
                    }
                }
            }

            // No message was found so far. If the fragment is within a CombinedFragment,
            // we need to continue the search in its container.
            if (container instanceof InteractionOperand) {
                return findInitialMessage((InteractionFragment) container.eContainer());
            }
        }

        return null;
    }

    /**
     * Returns a list of all combined fragments that the given fragment container "covers".
     * If the container is the owner, an empty list is returned. Otherwise all combined fragments
     * in the containment hierarchy will be returned.
     * The order of the retrieved list is top down from the most combined fragment down to its children etc.,
     * if <code>reverse</code> is <code>false</code>. If <code>true</code> is supplied, the reverse order is returned,
     * i.e., from bottom up.
     *
     * @param owner the {@link Interaction} that contains everything
     * @param container the {@link FragmentContainer} for which to retrieve all combined fragments
     * @param reverse whether the order of combined fragments should be reversed, bottom-up if true, top down otherwise
     * @return a list of all combined fragments the given fragment container "covers"
     */
    public static List<CombinedFragment> getCoveredCombinedFragments(Interaction owner, FragmentContainer container,
            boolean reverse) {
        List<CombinedFragment> result = new ArrayList<CombinedFragment>();

        FragmentContainer currentContainer = container;

        while (owner != currentContainer) {
            CombinedFragment combinedFragment = (CombinedFragment) currentContainer.eContainer();
            // We need a reverse order, so that the parent combined fragment is first considered before its child.
            result.add(0, combinedFragment);

            currentContainer = combinedFragment.getContainer();
        }

        if (reverse) {
            Collections.reverse(result);
        }

        return result;
    }

    /**
     * Returns whether the given class is empty.
     * A class is empty if it has no (or only partial) operations and attributes and no outgoing associations.
     * It is okay if there are incoming associations.
     *
     * @param clazz the {@link Class} to check
     * @return true, if the class is empty, false otherwise
     */
    public static boolean isClassEmpty(Class clazz) {
        for (AssociationEnd end : clazz.getAssociationEnds()) {
            if (end.isNavigable()) {
                return false;
            }
        }

        for (Operation operation : clazz.getOperations()) {
            if (operation.getPartiality() != COREPartialityType.CONCERN) {
                return false;
            }
        }

        return clazz.getAttributes().size() == 0;
    }

    /**
     * Returns true if the given class can be not abstract. The class can't be not abstract if it contains at least one
     * abstract operation.
     *
     * @param clazz - the class asked
     * @return true if the class can be not abstract, false otherwise.
     */
    public static boolean classCanBeNotAbstract(Class clazz) {
        for (Operation op : clazz.getOperations()) {
            if (op.isAbstract()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the implementation class contained in the structural view that has the given instance class name.
     * Returns null if it doesn't exist.
     *
     * @param structuralView the {@link StructuralView} that contains the classifiers
     * @param instanceClassName the fully qualified name of the implementation class
     * @return the {@link ImplementationClass}, null if none found
     */
    public static ImplementationClass getImplementationClassByName(StructuralView structuralView,
            String instanceClassName) {
        Collection<ImplementationClass> implClasses =
                EcoreUtil.getObjectsByType(structuralView.getClasses(), RamPackage.Literals.IMPLEMENTATION_CLASS);
        for (ImplementationClass implClass : implClasses) {
            if (instanceClassName.equals(implClass.getInstanceClassName())) {
                return implClass;
            }
        }

        return null;
    }

    /**
     * Returns true if the given operation is a constructor or a destructor.
     *
     * @param operation - the operation to test.
     * @return true if the given operation is a constructor or a destructor.
     */
    public static boolean isConstructorOrDestructor(Operation operation) {
        OperationType op = operation.getOperationType();
        return op.equals(OperationType.CONSTRUCTOR) || op.equals(OperationType.DESTRUCTOR);
    }

}
