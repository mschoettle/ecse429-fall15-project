/**
 */
package ca.mcgill.sel.ram.util;

import java.util.Map;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EObjectValidator;

import ca.mcgill.sel.ram.AbstractMessageView;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.AspectMessageView;
import ca.mcgill.sel.ram.AssignmentStatement;
import ca.mcgill.sel.ram.Association;
import ca.mcgill.sel.ram.AssociationEnd;
import ca.mcgill.sel.ram.Attribute;
import ca.mcgill.sel.ram.AttributeMapping;
import ca.mcgill.sel.ram.Classifier;
import ca.mcgill.sel.ram.ClassifierMapping;
import ca.mcgill.sel.ram.CombinedFragment;
import ca.mcgill.sel.ram.Constraint;
import ca.mcgill.sel.ram.DestructionOccurrenceSpecification;
import ca.mcgill.sel.ram.EnumLiteralValue;
import ca.mcgill.sel.ram.ExecutionStatement;
import ca.mcgill.sel.ram.FragmentContainer;
import ca.mcgill.sel.ram.Gate;
import ca.mcgill.sel.ram.ImplementationClass;
import ca.mcgill.sel.ram.Instantiation;
import ca.mcgill.sel.ram.InstantiationType;
import ca.mcgill.sel.ram.Interaction;
import ca.mcgill.sel.ram.InteractionFragment;
import ca.mcgill.sel.ram.InteractionOperand;
import ca.mcgill.sel.ram.InteractionOperatorKind;
import ca.mcgill.sel.ram.Layout;
import ca.mcgill.sel.ram.LayoutElement;
import ca.mcgill.sel.ram.Lifeline;
import ca.mcgill.sel.ram.LiteralBoolean;
import ca.mcgill.sel.ram.LiteralInteger;
import ca.mcgill.sel.ram.LiteralNull;
import ca.mcgill.sel.ram.LiteralSpecification;
import ca.mcgill.sel.ram.LiteralString;
import ca.mcgill.sel.ram.MappableElement;
import ca.mcgill.sel.ram.Message;
import ca.mcgill.sel.ram.MessageEnd;
import ca.mcgill.sel.ram.MessageOccurrenceSpecification;
import ca.mcgill.sel.ram.MessageSort;
import ca.mcgill.sel.ram.MessageView;
import ca.mcgill.sel.ram.MessageViewReference;
import ca.mcgill.sel.ram.NamedElement;
import ca.mcgill.sel.ram.ObjectType;
import ca.mcgill.sel.ram.OccurrenceSpecification;
import ca.mcgill.sel.ram.OpaqueExpression;
import ca.mcgill.sel.ram.Operation;
import ca.mcgill.sel.ram.OperationMapping;
import ca.mcgill.sel.ram.OperationType;
import ca.mcgill.sel.ram.OriginalBehaviorExecution;
import ca.mcgill.sel.ram.Parameter;
import ca.mcgill.sel.ram.ParameterMapping;
import ca.mcgill.sel.ram.ParameterValue;
import ca.mcgill.sel.ram.ParameterValueMapping;
import ca.mcgill.sel.ram.PrimitiveType;
import ca.mcgill.sel.ram.Property;
import ca.mcgill.sel.ram.RAMVisibilityType;
import ca.mcgill.sel.ram.RAny;
import ca.mcgill.sel.ram.RArray;
import ca.mcgill.sel.ram.RBoolean;
import ca.mcgill.sel.ram.RChar;
import ca.mcgill.sel.ram.RCollection;
import ca.mcgill.sel.ram.RDouble;
import ca.mcgill.sel.ram.REnum;
import ca.mcgill.sel.ram.REnumLiteral;
import ca.mcgill.sel.ram.RFloat;
import ca.mcgill.sel.ram.RInt;
import ca.mcgill.sel.ram.RLong;
import ca.mcgill.sel.ram.RSequence;
import ca.mcgill.sel.ram.RSet;
import ca.mcgill.sel.ram.RString;
import ca.mcgill.sel.ram.RVoid;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.Reference;
import ca.mcgill.sel.ram.ReferenceType;
import ca.mcgill.sel.ram.State;
import ca.mcgill.sel.ram.StateMachine;
import ca.mcgill.sel.ram.StateView;
import ca.mcgill.sel.ram.StructuralFeature;
import ca.mcgill.sel.ram.StructuralFeatureValue;
import ca.mcgill.sel.ram.StructuralView;
import ca.mcgill.sel.ram.Substitution;
import ca.mcgill.sel.ram.TemporaryProperty;
import ca.mcgill.sel.ram.Traceable;
import ca.mcgill.sel.ram.Transition;
import ca.mcgill.sel.ram.TransitionSubstitution;
import ca.mcgill.sel.ram.Type;
import ca.mcgill.sel.ram.TypeParameter;
import ca.mcgill.sel.ram.TypedElement;
import ca.mcgill.sel.ram.ValueSpecification;
import ca.mcgill.sel.ram.WovenAspect;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see ca.mcgill.sel.ram.RamPackage
 * @generated
 */
public class RamValidator extends EObjectValidator {
    /**
     * The cached model package
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final RamValidator INSTANCE = new RamValidator();

    /**
     * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.common.util.Diagnostic#getSource()
     * @see org.eclipse.emf.common.util.Diagnostic#getCode()
     * @generated
     */
    public static final String DIAGNOSTIC_SOURCE = "ca.mcgill.sel.ram";

    /**
     * A constant with a fixed name that can be used as the base value for additional hand written constants.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;

    /**
     * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public RamValidator() {
        super();
    }

    /**
     * Returns the package of this validator switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EPackage getEPackage() {
      return RamPackage.eINSTANCE;
    }

    /**
     * Calls <code>validateXXX</code> for the corresponding classifier of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
        switch (classifierID) {
            case RamPackage.ASPECT:
                return validateAspect((Aspect)value, diagnostics, context);
            case RamPackage.MAPPABLE_ELEMENT:
                return validateMappableElement((MappableElement)value, diagnostics, context);
            case RamPackage.STRUCTURAL_VIEW:
                return validateStructuralView((StructuralView)value, diagnostics, context);
            case RamPackage.CLASS:
                return validateClass((ca.mcgill.sel.ram.Class)value, diagnostics, context);
            case RamPackage.ASSOCIATION_END:
                return validateAssociationEnd((AssociationEnd)value, diagnostics, context);
            case RamPackage.ASSOCIATION:
                return validateAssociation((Association)value, diagnostics, context);
            case RamPackage.NAMED_ELEMENT:
                return validateNamedElement((NamedElement)value, diagnostics, context);
            case RamPackage.INSTANTIATION:
                return validateInstantiation((Instantiation)value, diagnostics, context);
            case RamPackage.OPERATION:
                return validateOperation((Operation)value, diagnostics, context);
            case RamPackage.ATTRIBUTE:
                return validateAttribute((Attribute)value, diagnostics, context);
            case RamPackage.TYPE:
                return validateType((Type)value, diagnostics, context);
            case RamPackage.PARAMETER:
                return validateParameter((Parameter)value, diagnostics, context);
            case RamPackage.PRIMITIVE_TYPE:
                return validatePrimitiveType((PrimitiveType)value, diagnostics, context);
            case RamPackage.OBJECT_TYPE:
                return validateObjectType((ObjectType)value, diagnostics, context);
            case RamPackage.RVOID:
                return validateRVoid((RVoid)value, diagnostics, context);
            case RamPackage.RBOOLEAN:
                return validateRBoolean((RBoolean)value, diagnostics, context);
            case RamPackage.RINT:
                return validateRInt((RInt)value, diagnostics, context);
            case RamPackage.RCHAR:
                return validateRChar((RChar)value, diagnostics, context);
            case RamPackage.RSTRING:
                return validateRString((RString)value, diagnostics, context);
            case RamPackage.RANY:
                return validateRAny((RAny)value, diagnostics, context);
            case RamPackage.RENUM:
                return validateREnum((REnum)value, diagnostics, context);
            case RamPackage.RENUM_LITERAL:
                return validateREnumLiteral((REnumLiteral)value, diagnostics, context);
            case RamPackage.ABSTRACT_MESSAGE_VIEW:
                return validateAbstractMessageView((AbstractMessageView)value, diagnostics, context);
            case RamPackage.MESSAGE_VIEW:
                return validateMessageView((MessageView)value, diagnostics, context);
            case RamPackage.MESSAGE_VIEW_REFERENCE:
                return validateMessageViewReference((MessageViewReference)value, diagnostics, context);
            case RamPackage.INTERACTION:
                return validateInteraction((Interaction)value, diagnostics, context);
            case RamPackage.ASPECT_MESSAGE_VIEW:
                return validateAspectMessageView((AspectMessageView)value, diagnostics, context);
            case RamPackage.LIFELINE:
                return validateLifeline((Lifeline)value, diagnostics, context);
            case RamPackage.MESSAGE:
                return validateMessage((Message)value, diagnostics, context);
            case RamPackage.MESSAGE_END:
                return validateMessageEnd((MessageEnd)value, diagnostics, context);
            case RamPackage.MESSAGE_OCCURRENCE_SPECIFICATION:
                return validateMessageOccurrenceSpecification((MessageOccurrenceSpecification)value, diagnostics, context);
            case RamPackage.OCCURRENCE_SPECIFICATION:
                return validateOccurrenceSpecification((OccurrenceSpecification)value, diagnostics, context);
            case RamPackage.DESTRUCTION_OCCURRENCE_SPECIFICATION:
                return validateDestructionOccurrenceSpecification((DestructionOccurrenceSpecification)value, diagnostics, context);
            case RamPackage.INTERACTION_FRAGMENT:
                return validateInteractionFragment((InteractionFragment)value, diagnostics, context);
            case RamPackage.COMBINED_FRAGMENT:
                return validateCombinedFragment((CombinedFragment)value, diagnostics, context);
            case RamPackage.ORIGINAL_BEHAVIOR_EXECUTION:
                return validateOriginalBehaviorExecution((OriginalBehaviorExecution)value, diagnostics, context);
            case RamPackage.EXECUTION_STATEMENT:
                return validateExecutionStatement((ExecutionStatement)value, diagnostics, context);
            case RamPackage.INTERACTION_OPERAND:
                return validateInteractionOperand((InteractionOperand)value, diagnostics, context);
            case RamPackage.VALUE_SPECIFICATION:
                return validateValueSpecification((ValueSpecification)value, diagnostics, context);
            case RamPackage.STRUCTURAL_FEATURE_VALUE:
                return validateStructuralFeatureValue((StructuralFeatureValue)value, diagnostics, context);
            case RamPackage.PARAMETER_VALUE_MAPPING:
                return validateParameterValueMapping((ParameterValueMapping)value, diagnostics, context);
            case RamPackage.PARAMETER_VALUE:
                return validateParameterValue((ParameterValue)value, diagnostics, context);
            case RamPackage.OPAQUE_EXPRESSION:
                return validateOpaqueExpression((OpaqueExpression)value, diagnostics, context);
            case RamPackage.LITERAL_SPECIFICATION:
                return validateLiteralSpecification((LiteralSpecification)value, diagnostics, context);
            case RamPackage.LITERAL_STRING:
                return validateLiteralString((LiteralString)value, diagnostics, context);
            case RamPackage.LITERAL_INTEGER:
                return validateLiteralInteger((LiteralInteger)value, diagnostics, context);
            case RamPackage.FRAGMENT_CONTAINER:
                return validateFragmentContainer((FragmentContainer)value, diagnostics, context);
            case RamPackage.RCOLLECTION:
                return validateRCollection((RCollection)value, diagnostics, context);
            case RamPackage.RSET:
                return validateRSet((RSet)value, diagnostics, context);
            case RamPackage.RSEQUENCE:
                return validateRSequence((RSequence)value, diagnostics, context);
            case RamPackage.LAYOUT:
                return validateLayout((Layout)value, diagnostics, context);
            case RamPackage.CONTAINER_MAP:
                return validateContainerMap((Map.Entry<?, ?>)value, diagnostics, context);
            case RamPackage.ELEMENT_MAP:
                return validateElementMap((Map.Entry<?, ?>)value, diagnostics, context);
            case RamPackage.LAYOUT_ELEMENT:
                return validateLayoutElement((LayoutElement)value, diagnostics, context);
            case RamPackage.CLASSIFIER:
                return validateClassifier((Classifier)value, diagnostics, context);
            case RamPackage.IMPLEMENTATION_CLASS:
                return validateImplementationClass((ImplementationClass)value, diagnostics, context);
            case RamPackage.TEMPORARY_PROPERTY:
                return validateTemporaryProperty((TemporaryProperty)value, diagnostics, context);
            case RamPackage.STRUCTURAL_FEATURE:
                return validateStructuralFeature((StructuralFeature)value, diagnostics, context);
            case RamPackage.REFERENCE:
                return validateReference((Reference)value, diagnostics, context);
            case RamPackage.PROPERTY:
                return validateProperty((Property)value, diagnostics, context);
            case RamPackage.TYPED_ELEMENT:
                return validateTypedElement((TypedElement)value, diagnostics, context);
            case RamPackage.GATE:
                return validateGate((Gate)value, diagnostics, context);
            case RamPackage.LITERAL_BOOLEAN:
                return validateLiteralBoolean((LiteralBoolean)value, diagnostics, context);
            case RamPackage.CLASSIFIER_MAPPING:
                return validateClassifierMapping((ClassifierMapping)value, diagnostics, context);
            case RamPackage.ATTRIBUTE_MAPPING:
                return validateAttributeMapping((AttributeMapping)value, diagnostics, context);
            case RamPackage.OPERATION_MAPPING:
                return validateOperationMapping((OperationMapping)value, diagnostics, context);
            case RamPackage.PARAMETER_MAPPING:
                return validateParameterMapping((ParameterMapping)value, diagnostics, context);
            case RamPackage.STATE_VIEW:
                return validateStateView((StateView)value, diagnostics, context);
            case RamPackage.STATE_MACHINE:
                return validateStateMachine((StateMachine)value, diagnostics, context);
            case RamPackage.TRANSITION:
                return validateTransition((Transition)value, diagnostics, context);
            case RamPackage.STATE:
                return validateState((State)value, diagnostics, context);
            case RamPackage.RDOUBLE:
                return validateRDouble((RDouble)value, diagnostics, context);
            case RamPackage.RFLOAT:
                return validateRFloat((RFloat)value, diagnostics, context);
            case RamPackage.CONSTRAINT:
                return validateConstraint((Constraint)value, diagnostics, context);
            case RamPackage.SUBSTITUTION:
                return validateSubstitution((Substitution)value, diagnostics, context);
            case RamPackage.TRANSITION_SUBSTITUTION:
                return validateTransitionSubstitution((TransitionSubstitution)value, diagnostics, context);
            case RamPackage.TYPE_PARAMETER:
                return validateTypeParameter((TypeParameter)value, diagnostics, context);
            case RamPackage.RLONG:
                return validateRLong((RLong)value, diagnostics, context);
            case RamPackage.RARRAY:
                return validateRArray((RArray)value, diagnostics, context);
            case RamPackage.TRACEABLE:
                return validateTraceable((Traceable)value, diagnostics, context);
            case RamPackage.WOVEN_ASPECT:
                return validateWovenAspect((WovenAspect)value, diagnostics, context);
            case RamPackage.LITERAL_NULL:
                return validateLiteralNull((LiteralNull)value, diagnostics, context);
            case RamPackage.ENUM_LITERAL_VALUE:
                return validateEnumLiteralValue((EnumLiteralValue)value, diagnostics, context);
            case RamPackage.ASSIGNMENT_STATEMENT:
                return validateAssignmentStatement((AssignmentStatement)value, diagnostics, context);
            case RamPackage.RAM_VISIBILITY_TYPE:
                return validateRAMVisibilityType((RAMVisibilityType)value, diagnostics, context);
            case RamPackage.REFERENCE_TYPE:
                return validateReferenceType((ReferenceType)value, diagnostics, context);
            case RamPackage.MESSAGE_SORT:
                return validateMessageSort((MessageSort)value, diagnostics, context);
            case RamPackage.INTERACTION_OPERATOR_KIND:
                return validateInteractionOperatorKind((InteractionOperatorKind)value, diagnostics, context);
            case RamPackage.INSTANTIATION_TYPE:
                return validateInstantiationType((InstantiationType)value, diagnostics, context);
            case RamPackage.OPERATION_TYPE:
                return validateOperationType((OperationType)value, diagnostics, context);
            default:
                return true;
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateAspect(Aspect aspect, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(aspect, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(aspect, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(aspect, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(aspect, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(aspect, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(aspect, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(aspect, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(aspect, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(aspect, diagnostics, context);
        if (result || diagnostics != null) result &= validateNamedElement_validName(aspect, diagnostics, context);
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateMappableElement(MappableElement mappableElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(mappableElement, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(mappableElement, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(mappableElement, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(mappableElement, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(mappableElement, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(mappableElement, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(mappableElement, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(mappableElement, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(mappableElement, diagnostics, context);
        if (result || diagnostics != null) result &= validateNamedElement_validName(mappableElement, diagnostics, context);
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateStructuralView(StructuralView structuralView, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(structuralView, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(structuralView, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(structuralView, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(structuralView, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(structuralView, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(structuralView, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(structuralView, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(structuralView, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(structuralView, diagnostics, context);
        if (result || diagnostics != null) result &= validateStructuralView_uniqueTypes(structuralView, diagnostics, context);
        if (result || diagnostics != null) result &= validateStructuralView_noTwoClassesWithSameName(structuralView, diagnostics, context);
        return result;
    }

    /**
     * The cached validation expression for the uniqueTypes constraint of '<em>Structural View</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static final String STRUCTURAL_VIEW__UNIQUE_TYPES__EEXPRESSION = "Tuple {\n" +
        "\tmessage : String = 'There may be only one type of the same type',\n" +
        "\tstatus : Boolean = self.types->isUnique(name)\n" +
        "}.status";

    /**
     * Validates the uniqueTypes constraint of '<em>Structural View</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateStructuralView_uniqueTypes(StructuralView structuralView, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return
            validate
                (RamPackage.Literals.STRUCTURAL_VIEW,
                 structuralView,
                 diagnostics,
                 context,
                 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
                 "uniqueTypes",
                 STRUCTURAL_VIEW__UNIQUE_TYPES__EEXPRESSION,
                 Diagnostic.ERROR,
                 DIAGNOSTIC_SOURCE,
                 0);
    }

    /**
     * The cached validation expression for the noTwoClassesWithSameName constraint of '<em>Structural View</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static final String STRUCTURAL_VIEW__NO_TWO_CLASSES_WITH_SAME_NAME__EEXPRESSION = "Tuple {\n" +
        "\tmessage : String = 'Name of a class has to be unique',\n" +
        "\tstatus : Boolean = self.classes->isUnique(name)\n" +
        "}.status";

    /**
     * Validates the noTwoClassesWithSameName constraint of '<em>Structural View</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateStructuralView_noTwoClassesWithSameName(StructuralView structuralView, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return
            validate
                (RamPackage.Literals.STRUCTURAL_VIEW,
                 structuralView,
                 diagnostics,
                 context,
                 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
                 "noTwoClassesWithSameName",
                 STRUCTURAL_VIEW__NO_TWO_CLASSES_WITH_SAME_NAME__EEXPRESSION,
                 Diagnostic.ERROR,
                 DIAGNOSTIC_SOURCE,
                 0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateClass(ca.mcgill.sel.ram.Class class_, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(class_, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(class_, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(class_, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(class_, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(class_, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(class_, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(class_, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(class_, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(class_, diagnostics, context);
        if (result || diagnostics != null) result &= validateNamedElement_validName(class_, diagnostics, context);
        if (result || diagnostics != null) result &= validateClass_notSelfSuperType(class_, diagnostics, context);
        return result;
    }

    /**
     * The cached validation expression for the notSelfSuperType constraint of '<em>Class</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static final String CLASS__NOT_SELF_SUPER_TYPE__EEXPRESSION = "Tuple {\n" +
        "\tmessage : String = 'A class may not be it\\'s own supertype',\n" +
        "\tstatus : Boolean = not self.superTypes->includes(self)\n" +
        "}.status";

    /**
     * Validates the notSelfSuperType constraint of '<em>Class</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateClass_notSelfSuperType(ca.mcgill.sel.ram.Class class_, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return
            validate
                (RamPackage.Literals.CLASS,
                 class_,
                 diagnostics,
                 context,
                 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
                 "notSelfSuperType",
                 CLASS__NOT_SELF_SUPER_TYPE__EEXPRESSION,
                 Diagnostic.ERROR,
                 DIAGNOSTIC_SOURCE,
                 0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateAssociationEnd(AssociationEnd associationEnd, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(associationEnd, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(associationEnd, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(associationEnd, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(associationEnd, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(associationEnd, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(associationEnd, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(associationEnd, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(associationEnd, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(associationEnd, diagnostics, context);
        if (result || diagnostics != null) result &= validateNamedElement_validName(associationEnd, diagnostics, context);
        if (result || diagnostics != null) result &= validateProperty_validUpperBound(associationEnd, diagnostics, context);
        if (result || diagnostics != null) result &= validateProperty_validLowerBound(associationEnd, diagnostics, context);
        if (result || diagnostics != null) result &= validateProperty_validMultiplicity(associationEnd, diagnostics, context);
        if (result || diagnostics != null) result &= validateAssociationEnd_uniqueName(associationEnd, diagnostics, context);
        return result;
    }

    /**
     * The cached validation expression for the uniqueName constraint of '<em>Association End</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static final String ASSOCIATION_END__UNIQUE_NAME__EEXPRESSION = "Tuple {\n" +
        "\tmessage : String = 'AssociationEnds of a class must be unique',\n" +
        "\tstatus : Boolean = self.classifier.associationEnds->select(associationEnd : AssociationEnd | associationEnd.name <> null and associationEnd.name <> '')->isUnique(name)\n" +
        "}.status";

    /**
     * Validates the uniqueName constraint of '<em>Association End</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateAssociationEnd_uniqueName(AssociationEnd associationEnd, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return
            validate
                (RamPackage.Literals.ASSOCIATION_END,
                 associationEnd,
                 diagnostics,
                 context,
                 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
                 "uniqueName",
                 ASSOCIATION_END__UNIQUE_NAME__EEXPRESSION,
                 Diagnostic.ERROR,
                 DIAGNOSTIC_SOURCE,
                 0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateAssociation(Association association, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(association, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(association, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(association, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(association, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(association, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(association, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(association, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(association, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(association, diagnostics, context);
        if (result || diagnostics != null) result &= validateNamedElement_validName(association, diagnostics, context);
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateNamedElement(NamedElement namedElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(namedElement, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(namedElement, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(namedElement, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(namedElement, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(namedElement, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(namedElement, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(namedElement, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(namedElement, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(namedElement, diagnostics, context);
        if (result || diagnostics != null) result &= validateNamedElement_validName(namedElement, diagnostics, context);
        return result;
    }

    /**
     * The cached validation expression for the validName constraint of '<em>Named Element</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static final String NAMED_ELEMENT__VALID_NAME__EEXPRESSION = "Tuple {\n" +
        "\tmessage : String = 'Name of RAM elements may not be empty',\n" +
        "\tstatus : Boolean = if self.oclIsTypeOf(AssociationEnd) and self.oclAsType(AssociationEnd).navigable = false then true else self.name <> '' endif\n" +
        "}.status";

    /**
     * Validates the validName constraint of '<em>Named Element</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateNamedElement_validName(NamedElement namedElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return
            validate
                (RamPackage.Literals.NAMED_ELEMENT,
                 namedElement,
                 diagnostics,
                 context,
                 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
                 "validName",
                 NAMED_ELEMENT__VALID_NAME__EEXPRESSION,
                 Diagnostic.ERROR,
                 DIAGNOSTIC_SOURCE,
                 0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateInstantiation(Instantiation instantiation, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(instantiation, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(instantiation, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(instantiation, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(instantiation, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(instantiation, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(instantiation, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(instantiation, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(instantiation, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(instantiation, diagnostics, context);
        if (result || diagnostics != null) result &= validateInstantiation_aspectCannotMapSelf(instantiation, diagnostics, context);
        return result;
    }

    /**
     * The cached validation expression for the aspectCannotMapSelf constraint of '<em>Instantiation</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static final String INSTANTIATION__ASPECT_CANNOT_MAP_SELF__EEXPRESSION = "Tuple {\n" +
        "\tmessage : String = 'Aspect may not depend on itself',\n" +
        "\tstatus : Boolean = not (self.source = self.Aspect)\n" +
        "}.status";

    /**
     * Validates the aspectCannotMapSelf constraint of '<em>Instantiation</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateInstantiation_aspectCannotMapSelf(Instantiation instantiation, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return
            validate
                (RamPackage.Literals.INSTANTIATION,
                 instantiation,
                 diagnostics,
                 context,
                 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
                 "aspectCannotMapSelf",
                 INSTANTIATION__ASPECT_CANNOT_MAP_SELF__EEXPRESSION,
                 Diagnostic.ERROR,
                 DIAGNOSTIC_SOURCE,
                 0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateOperation(Operation operation, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(operation, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(operation, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(operation, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(operation, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(operation, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(operation, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(operation, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(operation, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(operation, diagnostics, context);
        if (result || diagnostics != null) result &= validateNamedElement_validName(operation, diagnostics, context);
        if (result || diagnostics != null) result &= validateOperation_messageViewDefined(operation, diagnostics, context);
        if (result || diagnostics != null) result &= validateOperation_correctVisibility(operation, diagnostics, context);
        return result;
    }

    /**
     * The cached validation expression for the messageViewDefined constraint of '<em>Operation</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static final String OPERATION__MESSAGE_VIEW_DEFINED__EEXPRESSION = "Tuple {\n" +
        "\tmessage : String = 'MessageView missing for public operation',\n" +
        "\tstatus : Boolean = if extendedVisibility = RAMVisibilityType::public and self.Classifier.oclIsTypeOf(Class) and self.partiality = core::COREPartialityType::none then self.Classifier.oclContainer().oclAsType(StructuralView).Aspect.messageViews->select(messageView : AbstractMessageView | messageView.oclIsTypeOf(MessageView))->one(messageView : AbstractMessageView | messageView.oclAsType(MessageView).specifies = self) else true endif\n" +
        "}.status";

    /**
     * Validates the messageViewDefined constraint of '<em>Operation</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateOperation_messageViewDefined(Operation operation, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return
            validate
                (RamPackage.Literals.OPERATION,
                 operation,
                 diagnostics,
                 context,
                 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
                 "messageViewDefined",
                 OPERATION__MESSAGE_VIEW_DEFINED__EEXPRESSION,
                 Diagnostic.ERROR,
                 DIAGNOSTIC_SOURCE,
                 0);
    }

    /**
     * The cached validation expression for the correctVisibility constraint of '<em>Operation</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static final String OPERATION__CORRECT_VISIBILITY__EEXPRESSION = "Tuple {\n" +
        "\tmessage : String = 'COREVisibility and RAMVisibility attributes are not in sync',\n" +
        "\tstatus : Boolean = if visibility = core::COREVisibilityType::public then extendedVisibility = RAMVisibilityType::public else extendedVisibility <> RAMVisibilityType::public endif\n" +
        "}.status";

    /**
     * Validates the correctVisibility constraint of '<em>Operation</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateOperation_correctVisibility(Operation operation, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return
            validate
                (RamPackage.Literals.OPERATION,
                 operation,
                 diagnostics,
                 context,
                 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
                 "correctVisibility",
                 OPERATION__CORRECT_VISIBILITY__EEXPRESSION,
                 Diagnostic.ERROR,
                 DIAGNOSTIC_SOURCE,
                 0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateAttribute(Attribute attribute, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(attribute, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(attribute, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(attribute, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(attribute, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(attribute, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(attribute, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(attribute, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(attribute, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(attribute, diagnostics, context);
        if (result || diagnostics != null) result &= validateNamedElement_validName(attribute, diagnostics, context);
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateType(Type type, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(type, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(type, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(type, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(type, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(type, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(type, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(type, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(type, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(type, diagnostics, context);
        if (result || diagnostics != null) result &= validateNamedElement_validName(type, diagnostics, context);
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateParameter(Parameter parameter, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(parameter, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(parameter, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(parameter, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(parameter, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(parameter, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(parameter, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(parameter, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(parameter, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(parameter, diagnostics, context);
        if (result || diagnostics != null) result &= validateNamedElement_validName(parameter, diagnostics, context);
        if (result || diagnostics != null) result &= validateParameter_notVoid(parameter, diagnostics, context);
        return result;
    }

    /**
     * The cached validation expression for the notVoid constraint of '<em>Parameter</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static final String PARAMETER__NOT_VOID__EEXPRESSION = "Tuple {\n" +
        "\tmessage : String = 'The type of the parameter may not be void',\n" +
        "\tstatus : Boolean = not self.type.oclIsTypeOf(RVoid)\n" +
        "}.status";

    /**
     * Validates the notVoid constraint of '<em>Parameter</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateParameter_notVoid(Parameter parameter, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return
            validate
                (RamPackage.Literals.PARAMETER,
                 parameter,
                 diagnostics,
                 context,
                 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
                 "notVoid",
                 PARAMETER__NOT_VOID__EEXPRESSION,
                 Diagnostic.ERROR,
                 DIAGNOSTIC_SOURCE,
                 0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validatePrimitiveType(PrimitiveType primitiveType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(primitiveType, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(primitiveType, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(primitiveType, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(primitiveType, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(primitiveType, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(primitiveType, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(primitiveType, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(primitiveType, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(primitiveType, diagnostics, context);
        if (result || diagnostics != null) result &= validateNamedElement_validName(primitiveType, diagnostics, context);
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateObjectType(ObjectType objectType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(objectType, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(objectType, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(objectType, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(objectType, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(objectType, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(objectType, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(objectType, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(objectType, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(objectType, diagnostics, context);
        if (result || diagnostics != null) result &= validateNamedElement_validName(objectType, diagnostics, context);
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateRVoid(RVoid rVoid, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(rVoid, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(rVoid, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(rVoid, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(rVoid, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(rVoid, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(rVoid, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(rVoid, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(rVoid, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(rVoid, diagnostics, context);
        if (result || diagnostics != null) result &= validateNamedElement_validName(rVoid, diagnostics, context);
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateRBoolean(RBoolean rBoolean, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(rBoolean, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(rBoolean, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(rBoolean, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(rBoolean, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(rBoolean, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(rBoolean, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(rBoolean, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(rBoolean, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(rBoolean, diagnostics, context);
        if (result || diagnostics != null) result &= validateNamedElement_validName(rBoolean, diagnostics, context);
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateRInt(RInt rInt, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(rInt, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(rInt, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(rInt, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(rInt, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(rInt, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(rInt, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(rInt, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(rInt, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(rInt, diagnostics, context);
        if (result || diagnostics != null) result &= validateNamedElement_validName(rInt, diagnostics, context);
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateRChar(RChar rChar, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(rChar, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(rChar, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(rChar, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(rChar, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(rChar, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(rChar, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(rChar, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(rChar, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(rChar, diagnostics, context);
        if (result || diagnostics != null) result &= validateNamedElement_validName(rChar, diagnostics, context);
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateRString(RString rString, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(rString, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(rString, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(rString, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(rString, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(rString, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(rString, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(rString, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(rString, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(rString, diagnostics, context);
        if (result || diagnostics != null) result &= validateNamedElement_validName(rString, diagnostics, context);
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateRAny(RAny rAny, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(rAny, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(rAny, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(rAny, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(rAny, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(rAny, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(rAny, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(rAny, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(rAny, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(rAny, diagnostics, context);
        if (result || diagnostics != null) result &= validateNamedElement_validName(rAny, diagnostics, context);
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateREnum(REnum rEnum, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(rEnum, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(rEnum, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(rEnum, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(rEnum, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(rEnum, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(rEnum, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(rEnum, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(rEnum, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(rEnum, diagnostics, context);
        if (result || diagnostics != null) result &= validateNamedElement_validName(rEnum, diagnostics, context);
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateREnumLiteral(REnumLiteral rEnumLiteral, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(rEnumLiteral, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(rEnumLiteral, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(rEnumLiteral, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(rEnumLiteral, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(rEnumLiteral, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(rEnumLiteral, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(rEnumLiteral, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(rEnumLiteral, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(rEnumLiteral, diagnostics, context);
        if (result || diagnostics != null) result &= validateNamedElement_validName(rEnumLiteral, diagnostics, context);
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateAbstractMessageView(AbstractMessageView abstractMessageView, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(abstractMessageView, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateMessageView(MessageView messageView, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(messageView, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(messageView, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(messageView, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(messageView, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(messageView, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(messageView, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(messageView, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(messageView, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(messageView, diagnostics, context);
        if (result || diagnostics != null) result &= validateMessageView_specifiesIsFirstMessage(messageView, diagnostics, context);
        if (result || diagnostics != null) result &= validateMessageView_specificationDefined(messageView, diagnostics, context);
        if (result || diagnostics != null) result &= validateMessageView_isAffectedBy(messageView, diagnostics, context);
        return result;
    }

    /**
     * The cached validation expression for the specifiesIsFirstMessage constraint of '<em>Message View</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static final String MESSAGE_VIEW__SPECIFIES_IS_FIRST_MESSAGE__EEXPRESSION = "Tuple {\n" +
        "\tmessage : String = 'The first message in the MessageView must match specifies of the MessageView',\n" +
        "\tstatus : Boolean = if not self.specification.oclIsUndefined() and self.specification.messages->size() > 0 then self.specifies = self.specification.messages->select(message : Message | not message.sendEvent.oclIsTypeOf(MessageOccurrenceSpecification))->asOrderedSet()->at(1).signature else true endif\n" +
        "}.status";

    /**
     * Validates the specifiesIsFirstMessage constraint of '<em>Message View</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateMessageView_specifiesIsFirstMessage(MessageView messageView, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return
            validate
                (RamPackage.Literals.MESSAGE_VIEW,
                 messageView,
                 diagnostics,
                 context,
                 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
                 "specifiesIsFirstMessage",
                 MESSAGE_VIEW__SPECIFIES_IS_FIRST_MESSAGE__EEXPRESSION,
                 Diagnostic.ERROR,
                 DIAGNOSTIC_SOURCE,
                 0);
    }

    /**
     * The cached validation expression for the specificationDefined constraint of '<em>Message View</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static final String MESSAGE_VIEW__SPECIFICATION_DEFINED__EEXPRESSION = "Tuple {\n" +
        "\tmessage : String = 'A MessageView for a non-partial operation must have a specification',\n" +
        "\tstatus : Boolean = if not self.specifies.oclIsUndefined() then if self.specifies.partiality = core::COREPartialityType::none then not self.specification.oclIsUndefined() else true endif else true endif\n" +
        "}.status";

    /**
     * Validates the specificationDefined constraint of '<em>Message View</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateMessageView_specificationDefined(MessageView messageView, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return
            validate
                (RamPackage.Literals.MESSAGE_VIEW,
                 messageView,
                 diagnostics,
                 context,
                 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
                 "specificationDefined",
                 MESSAGE_VIEW__SPECIFICATION_DEFINED__EEXPRESSION,
                 Diagnostic.ERROR,
                 DIAGNOSTIC_SOURCE,
                 0);
    }

    /**
     * The cached validation expression for the isAffectedBy constraint of '<em>Message View</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static final String MESSAGE_VIEW__IS_AFFECTED_BY__EEXPRESSION = "Tuple {\n" +
        "\tmessage : String = 'An empty MessageView must have at least one affectedBy',\n" +
        "\tstatus : Boolean = if self.specification.oclIsUndefined() and self.specifies.partiality <> core::COREPartialityType::none then self.affectedBy->notEmpty() else true endif\n" +
        "}.status";

    /**
     * Validates the isAffectedBy constraint of '<em>Message View</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateMessageView_isAffectedBy(MessageView messageView, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return
            validate
                (RamPackage.Literals.MESSAGE_VIEW,
                 messageView,
                 diagnostics,
                 context,
                 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
                 "isAffectedBy",
                 MESSAGE_VIEW__IS_AFFECTED_BY__EEXPRESSION,
                 Diagnostic.ERROR,
                 DIAGNOSTIC_SOURCE,
                 0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateMessageViewReference(MessageViewReference messageViewReference, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(messageViewReference, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(messageViewReference, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(messageViewReference, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(messageViewReference, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(messageViewReference, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(messageViewReference, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(messageViewReference, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(messageViewReference, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(messageViewReference, diagnostics, context);
        if (result || diagnostics != null) result &= validateMessageViewReference_isAffectedBy(messageViewReference, diagnostics, context);
        return result;
    }

    /**
     * The cached validation expression for the isAffectedBy constraint of '<em>Message View Reference</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static final String MESSAGE_VIEW_REFERENCE__IS_AFFECTED_BY__EEXPRESSION = "Tuple {\n" +
        "\tmessage : String = 'The MessageViewReference must have at least one affectedBy',\n" +
        "\tstatus : Boolean = self.affectedBy->size() > 0\n" +
        "}.status";

    /**
     * Validates the isAffectedBy constraint of '<em>Message View Reference</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateMessageViewReference_isAffectedBy(MessageViewReference messageViewReference, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return
            validate
                (RamPackage.Literals.MESSAGE_VIEW_REFERENCE,
                 messageViewReference,
                 diagnostics,
                 context,
                 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
                 "isAffectedBy",
                 MESSAGE_VIEW_REFERENCE__IS_AFFECTED_BY__EEXPRESSION,
                 Diagnostic.ERROR,
                 DIAGNOSTIC_SOURCE,
                 0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateInteraction(Interaction interaction, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(interaction, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateAspectMessageView(AspectMessageView aspectMessageView, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(aspectMessageView, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(aspectMessageView, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(aspectMessageView, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(aspectMessageView, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(aspectMessageView, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(aspectMessageView, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(aspectMessageView, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(aspectMessageView, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(aspectMessageView, diagnostics, context);
        if (result || diagnostics != null) result &= validateNamedElement_validName(aspectMessageView, diagnostics, context);
        if (result || diagnostics != null) result &= validateAspectMessageView_notAffectedBySelf(aspectMessageView, diagnostics, context);
        return result;
    }

    /**
     * The cached validation expression for the notAffectedBySelf constraint of '<em>Aspect Message View</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static final String ASPECT_MESSAGE_VIEW__NOT_AFFECTED_BY_SELF__EEXPRESSION = "Tuple {\n" +
        "\tmessage : String = 'Message View may not be affected by itself',\n" +
        "\tstatus : Boolean = not self.affectedBy->includes(self)\n" +
        "}.status";

    /**
     * Validates the notAffectedBySelf constraint of '<em>Aspect Message View</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateAspectMessageView_notAffectedBySelf(AspectMessageView aspectMessageView, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return
            validate
                (RamPackage.Literals.ASPECT_MESSAGE_VIEW,
                 aspectMessageView,
                 diagnostics,
                 context,
                 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
                 "notAffectedBySelf",
                 ASPECT_MESSAGE_VIEW__NOT_AFFECTED_BY_SELF__EEXPRESSION,
                 Diagnostic.ERROR,
                 DIAGNOSTIC_SOURCE,
                 0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateLifeline(Lifeline lifeline, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(lifeline, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateMessage(Message message, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(message, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(message, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(message, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(message, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(message, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(message, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(message, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(message, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(message, diagnostics, context);
        if (result || diagnostics != null) result &= validateMessage_validSelfMessage(message, diagnostics, context);
        if (result || diagnostics != null) result &= validateMessage_argumentsSpecified(message, diagnostics, context);
        if (result || diagnostics != null) result &= validateMessage_createMessageIsFirst(message, diagnostics, context);
        if (result || diagnostics != null) result &= validateMessage_returnsSpecified(message, diagnostics, context);
        if (result || diagnostics != null) result &= validateMessage_boundariesNotCrossed(message, diagnostics, context);
        if (result || diagnostics != null) result &= validateMessage_validReturns(message, diagnostics, context);
        if (result || diagnostics != null) result &= validateMessage_deleteMessageIsLast(message, diagnostics, context);
        if (result || diagnostics != null) result &= validateMessage_assignToAllowed(message, diagnostics, context);
        if (result || diagnostics != null) result &= validateMessage_noCrossingMessages(message, diagnostics, context);
        return result;
    }

    /**
     * The cached validation expression for the validSelfMessage constraint of '<em>Message</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static final String MESSAGE__VALID_SELF_MESSAGE__EEXPRESSION = "Tuple {\n" +
        "\tmessage : String = 'receiveEvent of a self message may not come before the sendEvent',\n" +
        "\tstatus : Boolean = if not self.receiveEvent.oclIsUndefined() and self.receiveEvent.oclIsKindOf(MessageOccurrenceSpecification) and not self.sendEvent.oclIsUndefined() and self.sendEvent.oclIsKindOf(MessageOccurrenceSpecification) then let sendEvent : InteractionFragment = self.sendEvent.oclAsType(InteractionFragment) in let receiveEvent : InteractionFragment = self.receiveEvent.oclAsType(InteractionFragment) in if sendEvent.covered->asOrderedSet()->at(1) = receiveEvent.covered->asOrderedSet()->at(1) then sendEvent.container.fragments->indexOf(self.sendEvent) < receiveEvent.container.fragments->indexOf(self.receiveEvent) else true endif else true endif\n" +
        "}.status";

    /**
     * Validates the validSelfMessage constraint of '<em>Message</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateMessage_validSelfMessage(Message message, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return
            validate
                (RamPackage.Literals.MESSAGE,
                 message,
                 diagnostics,
                 context,
                 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
                 "validSelfMessage",
                 MESSAGE__VALID_SELF_MESSAGE__EEXPRESSION,
                 Diagnostic.ERROR,
                 DIAGNOSTIC_SOURCE,
                 0);
    }

    /**
     * The cached validation expression for the argumentsSpecified constraint of '<em>Message</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static final String MESSAGE__ARGUMENTS_SPECIFIED__EEXPRESSION = "Tuple {\n" +
        "\tmessage : String = 'All arguments of the operation must be specified',\n" +
        "\tstatus : Boolean = if self.messageSort <> MessageSort::reply and not self.sendEvent.oclIsUndefined() and self.sendEvent.oclIsKindOf(MessageOccurrenceSpecification) and not self.signature.oclIsUndefined() then let container : FragmentContainer = self.sendEvent.oclAsType(MessageOccurrenceSpecification).container in if container.fragments->includes(self.sendEvent) then self.signature.parameters->size() = self.arguments->size() and self.signature.parameters->forAll(currentParameter : Parameter | self.arguments->one(argument : ParameterValueMapping | argument.parameter = currentParameter)) else true endif else true endif\n" +
        "}.status";

    /**
     * Validates the argumentsSpecified constraint of '<em>Message</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateMessage_argumentsSpecified(Message message, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return
            validate
                (RamPackage.Literals.MESSAGE,
                 message,
                 diagnostics,
                 context,
                 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
                 "argumentsSpecified",
                 MESSAGE__ARGUMENTS_SPECIFIED__EEXPRESSION,
                 Diagnostic.ERROR,
                 DIAGNOSTIC_SOURCE,
                 0);
    }

    /**
     * The cached validation expression for the createMessageIsFirst constraint of '<em>Message</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static final String MESSAGE__CREATE_MESSAGE_IS_FIRST__EEXPRESSION = "Tuple {\n" +
        "\tmessage : String = 'The create message occurrence may not come after any other occurrence on this lifeline',\n" +
        "\tstatus : Boolean = if self.messageSort = MessageSort::createMessage then if not self.receiveEvent.oclIsUndefined() and not self.receiveEvent.oclAsType(InteractionFragment).covered->isEmpty() then let event : InteractionFragment = self.receiveEvent.oclAsType(InteractionFragment) in event.covered->asOrderedSet()->at(1).coveredBy->forAll(fragment : InteractionFragment | if event.container.fragments->includes(fragment) then event.container.fragments->indexOf(fragment) >= event.container.fragments->indexOf(event) else true endif) else true endif else true endif\n" +
        "}.status";

    /**
     * Validates the createMessageIsFirst constraint of '<em>Message</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateMessage_createMessageIsFirst(Message message, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return
            validate
                (RamPackage.Literals.MESSAGE,
                 message,
                 diagnostics,
                 context,
                 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
                 "createMessageIsFirst",
                 MESSAGE__CREATE_MESSAGE_IS_FIRST__EEXPRESSION,
                 Diagnostic.ERROR,
                 DIAGNOSTIC_SOURCE,
                 0);
    }

    /**
     * The cached validation expression for the returnsSpecified constraint of '<em>Message</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static final String MESSAGE__RETURNS_SPECIFIED__EEXPRESSION = "Tuple {\n" +
        "\tmessage : String = 'Reply message must have returns specified if return type is not void',\n" +
        "\tstatus : Boolean = if self.messageSort = MessageSort::reply then if not self.signature.returnType.oclIsTypeOf(RVoid) and self.signature.operationType = OperationType::Normal then not self.returns.oclIsUndefined() else self.returns.oclIsUndefined() endif else true endif\n" +
        "}.status";

    /**
     * Validates the returnsSpecified constraint of '<em>Message</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateMessage_returnsSpecified(Message message, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return
            validate
                (RamPackage.Literals.MESSAGE,
                 message,
                 diagnostics,
                 context,
                 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
                 "returnsSpecified",
                 MESSAGE__RETURNS_SPECIFIED__EEXPRESSION,
                 Diagnostic.ERROR,
                 DIAGNOSTIC_SOURCE,
                 0);
    }

    /**
     * The cached validation expression for the boundariesNotCrossed constraint of '<em>Message</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static final String MESSAGE__BOUNDARIES_NOT_CROSSED__EEXPRESSION = "Tuple {\n" +
        "\tmessage : String = 'Messages may not cross boundaries of CombinedFragments or their operands',\n" +
        "\tstatus : Boolean = let send : MessageOccurrenceSpecification = self.sendEvent in let receive : MessageOccurrenceSpecification = self.receiveEvent in if send.container.oclIsTypeOf(InteractionOperand) and receive.container.oclIsTypeOf(InteractionOperand) then send.container.oclAsType(InteractionOperand).CombinedFragment.covered->includes(send.covered->asOrderedSet()->at(1)) and receive.container.oclAsType(InteractionOperand).CombinedFragment.covered->includes(receive.covered->asOrderedSet()->at(1)) else true endif\n" +
        "}.status";

    /**
     * Validates the boundariesNotCrossed constraint of '<em>Message</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateMessage_boundariesNotCrossed(Message message, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return
            validate
                (RamPackage.Literals.MESSAGE,
                 message,
                 diagnostics,
                 context,
                 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
                 "boundariesNotCrossed",
                 MESSAGE__BOUNDARIES_NOT_CROSSED__EEXPRESSION,
                 Diagnostic.ERROR,
                 DIAGNOSTIC_SOURCE,
                 0);
    }

    /**
     * The cached validation expression for the validReturns constraint of '<em>Message</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static final String MESSAGE__VALID_RETURNS__EEXPRESSION = "Tuple {\n" +
        "\tmessage : String = 'Returns may only be specified if message sort is reply message',\n" +
        "\tstatus : Boolean = if not self.returns.oclIsUndefined() then self.messageSort = MessageSort::reply else true endif\n" +
        "}.status";

    /**
     * Validates the validReturns constraint of '<em>Message</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateMessage_validReturns(Message message, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return
            validate
                (RamPackage.Literals.MESSAGE,
                 message,
                 diagnostics,
                 context,
                 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
                 "validReturns",
                 MESSAGE__VALID_RETURNS__EEXPRESSION,
                 Diagnostic.ERROR,
                 DIAGNOSTIC_SOURCE,
                 0);
    }

    /**
     * The cached validation expression for the deleteMessageIsLast constraint of '<em>Message</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static final String MESSAGE__DELETE_MESSAGE_IS_LAST__EEXPRESSION = "Tuple {\n" +
        "\tmessage : String = 'The destruction occurrence may not come before any other occurrence on this lifeline',\n" +
        "\tstatus : Boolean = if self.messageSort = MessageSort::deleteMessage then if not self.receiveEvent.oclIsUndefined() and not self.receiveEvent.oclAsType(InteractionFragment).covered->isEmpty() then let event : InteractionFragment = self.receiveEvent.oclAsType(InteractionFragment) in event.covered->asOrderedSet()->at(1).coveredBy->forAll(fragment : InteractionFragment | if event.container.fragments->includes(fragment) then event.container.fragments->indexOf(fragment) <= event.container.fragments->indexOf(event) else true endif) else true endif else true endif\n" +
        "}.status";

    /**
     * Validates the deleteMessageIsLast constraint of '<em>Message</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateMessage_deleteMessageIsLast(Message message, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return
            validate
                (RamPackage.Literals.MESSAGE,
                 message,
                 diagnostics,
                 context,
                 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
                 "deleteMessageIsLast",
                 MESSAGE__DELETE_MESSAGE_IS_LAST__EEXPRESSION,
                 Diagnostic.ERROR,
                 DIAGNOSTIC_SOURCE,
                 0);
    }

    /**
     * The cached validation expression for the assignToAllowed constraint of '<em>Message</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static final String MESSAGE__ASSIGN_TO_ALLOWED__EEXPRESSION = "Tuple {\n" +
        "\tmessage : String = 'assignTo may not be specified for operations whose return type is void',\n" +
        "\tstatus : Boolean = if self.signature.returnType.oclIsTypeOf(RVoid) then self.assignTo = null else true endif\n" +
        "}.status";

    /**
     * Validates the assignToAllowed constraint of '<em>Message</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateMessage_assignToAllowed(Message message, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return
            validate
                (RamPackage.Literals.MESSAGE,
                 message,
                 diagnostics,
                 context,
                 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
                 "assignToAllowed",
                 MESSAGE__ASSIGN_TO_ALLOWED__EEXPRESSION,
                 Diagnostic.ERROR,
                 DIAGNOSTIC_SOURCE,
                 0);
    }

    /**
     * The cached validation expression for the noCrossingMessages constraint of '<em>Message</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static final String MESSAGE__NO_CROSSING_MESSAGES__EEXPRESSION = "Tuple {\n" +
        "\tmessage : String = 'Messages may not cross each other, MessageEnds of one message must come one after the other (also, send must come before receive)',\n" +
        "\tstatus : Boolean = if not self.receiveEvent.oclIsUndefined() and self.receiveEvent.oclIsKindOf(MessageOccurrenceSpecification) and not self.sendEvent.oclIsUndefined() and self.sendEvent.oclIsKindOf(MessageOccurrenceSpecification) then let container : FragmentContainer = self.receiveEvent.oclAsType(MessageOccurrenceSpecification).container in let indexDifference : Integer = container.fragments->indexOf(self.receiveEvent) - container.fragments->indexOf(self.sendEvent) in indexDifference = 1 else true endif\n" +
        "}.status";

    /**
     * Validates the noCrossingMessages constraint of '<em>Message</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateMessage_noCrossingMessages(Message message, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return
            validate
                (RamPackage.Literals.MESSAGE,
                 message,
                 diagnostics,
                 context,
                 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
                 "noCrossingMessages",
                 MESSAGE__NO_CROSSING_MESSAGES__EEXPRESSION,
                 Diagnostic.ERROR,
                 DIAGNOSTIC_SOURCE,
                 0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateMessageEnd(MessageEnd messageEnd, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(messageEnd, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateMessageOccurrenceSpecification(MessageOccurrenceSpecification messageOccurrenceSpecification, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(messageOccurrenceSpecification, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(messageOccurrenceSpecification, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(messageOccurrenceSpecification, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(messageOccurrenceSpecification, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(messageOccurrenceSpecification, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(messageOccurrenceSpecification, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(messageOccurrenceSpecification, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(messageOccurrenceSpecification, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(messageOccurrenceSpecification, diagnostics, context);
        if (result || diagnostics != null) result &= validateInteractionFragment_validCoveredMultiplicity(messageOccurrenceSpecification, diagnostics, context);
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateOccurrenceSpecification(OccurrenceSpecification occurrenceSpecification, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(occurrenceSpecification, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(occurrenceSpecification, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(occurrenceSpecification, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(occurrenceSpecification, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(occurrenceSpecification, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(occurrenceSpecification, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(occurrenceSpecification, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(occurrenceSpecification, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(occurrenceSpecification, diagnostics, context);
        if (result || diagnostics != null) result &= validateInteractionFragment_validCoveredMultiplicity(occurrenceSpecification, diagnostics, context);
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateDestructionOccurrenceSpecification(DestructionOccurrenceSpecification destructionOccurrenceSpecification, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(destructionOccurrenceSpecification, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(destructionOccurrenceSpecification, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(destructionOccurrenceSpecification, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(destructionOccurrenceSpecification, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(destructionOccurrenceSpecification, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(destructionOccurrenceSpecification, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(destructionOccurrenceSpecification, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(destructionOccurrenceSpecification, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(destructionOccurrenceSpecification, diagnostics, context);
        if (result || diagnostics != null) result &= validateInteractionFragment_validCoveredMultiplicity(destructionOccurrenceSpecification, diagnostics, context);
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateInteractionFragment(InteractionFragment interactionFragment, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(interactionFragment, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(interactionFragment, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(interactionFragment, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(interactionFragment, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(interactionFragment, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(interactionFragment, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(interactionFragment, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(interactionFragment, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(interactionFragment, diagnostics, context);
        if (result || diagnostics != null) result &= validateInteractionFragment_validCoveredMultiplicity(interactionFragment, diagnostics, context);
        return result;
    }

    /**
     * The cached validation expression for the validCoveredMultiplicity constraint of '<em>Interaction Fragment</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static final String INTERACTION_FRAGMENT__VALID_COVERED_MULTIPLICITY__EEXPRESSION = "Tuple {\n" +
        "\tmessage : String = 'InteractionFragment may only have one Lifeline it covers (except CombinedFragment)',\n" +
        "\tstatus : Boolean = if not self.oclIsTypeOf(CombinedFragment) and covered->notEmpty() then covered->size() = 1 else true endif\n" +
        "}.status";

    /**
     * Validates the validCoveredMultiplicity constraint of '<em>Interaction Fragment</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateInteractionFragment_validCoveredMultiplicity(InteractionFragment interactionFragment, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return
            validate
                (RamPackage.Literals.INTERACTION_FRAGMENT,
                 interactionFragment,
                 diagnostics,
                 context,
                 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
                 "validCoveredMultiplicity",
                 INTERACTION_FRAGMENT__VALID_COVERED_MULTIPLICITY__EEXPRESSION,
                 Diagnostic.ERROR,
                 DIAGNOSTIC_SOURCE,
                 0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateCombinedFragment(CombinedFragment combinedFragment, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(combinedFragment, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(combinedFragment, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(combinedFragment, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(combinedFragment, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(combinedFragment, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(combinedFragment, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(combinedFragment, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(combinedFragment, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(combinedFragment, diagnostics, context);
        if (result || diagnostics != null) result &= validateInteractionFragment_validCoveredMultiplicity(combinedFragment, diagnostics, context);
        if (result || diagnostics != null) result &= validateCombinedFragment_validOperands(combinedFragment, diagnostics, context);
        return result;
    }

    /**
     * The cached validation expression for the validOperands constraint of '<em>Combined Fragment</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static final String COMBINED_FRAGMENT__VALID_OPERANDS__EEXPRESSION = "Tuple {\n" +
        "\tmessage : String = 'opt and loop fragments may only have one operand',\n" +
        "\tstatus : Boolean = if self.interactionOperator = InteractionOperatorKind::opt or self.interactionOperator = InteractionOperatorKind::loop or self.interactionOperator = InteractionOperatorKind::critical then self.operands->size() = 1 else true endif\n" +
        "}.status";

    /**
     * Validates the validOperands constraint of '<em>Combined Fragment</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateCombinedFragment_validOperands(CombinedFragment combinedFragment, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return
            validate
                (RamPackage.Literals.COMBINED_FRAGMENT,
                 combinedFragment,
                 diagnostics,
                 context,
                 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
                 "validOperands",
                 COMBINED_FRAGMENT__VALID_OPERANDS__EEXPRESSION,
                 Diagnostic.ERROR,
                 DIAGNOSTIC_SOURCE,
                 0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateOriginalBehaviorExecution(OriginalBehaviorExecution originalBehaviorExecution, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(originalBehaviorExecution, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(originalBehaviorExecution, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(originalBehaviorExecution, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(originalBehaviorExecution, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(originalBehaviorExecution, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(originalBehaviorExecution, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(originalBehaviorExecution, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(originalBehaviorExecution, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(originalBehaviorExecution, diagnostics, context);
        if (result || diagnostics != null) result &= validateInteractionFragment_validCoveredMultiplicity(originalBehaviorExecution, diagnostics, context);
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateExecutionStatement(ExecutionStatement executionStatement, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(executionStatement, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(executionStatement, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(executionStatement, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(executionStatement, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(executionStatement, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(executionStatement, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(executionStatement, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(executionStatement, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(executionStatement, diagnostics, context);
        if (result || diagnostics != null) result &= validateInteractionFragment_validCoveredMultiplicity(executionStatement, diagnostics, context);
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateInteractionOperand(InteractionOperand interactionOperand, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(interactionOperand, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateValueSpecification(ValueSpecification valueSpecification, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(valueSpecification, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateStructuralFeatureValue(StructuralFeatureValue structuralFeatureValue, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(structuralFeatureValue, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateParameterValueMapping(ParameterValueMapping parameterValueMapping, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(parameterValueMapping, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateParameterValue(ParameterValue parameterValue, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(parameterValue, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateOpaqueExpression(OpaqueExpression opaqueExpression, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(opaqueExpression, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateLiteralSpecification(LiteralSpecification literalSpecification, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(literalSpecification, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateLiteralString(LiteralString literalString, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(literalString, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateLiteralInteger(LiteralInteger literalInteger, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(literalInteger, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateFragmentContainer(FragmentContainer fragmentContainer, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(fragmentContainer, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateRCollection(RCollection rCollection, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(rCollection, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(rCollection, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(rCollection, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(rCollection, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(rCollection, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(rCollection, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(rCollection, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(rCollection, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(rCollection, diagnostics, context);
        if (result || diagnostics != null) result &= validateNamedElement_validName(rCollection, diagnostics, context);
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateRSet(RSet rSet, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(rSet, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(rSet, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(rSet, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(rSet, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(rSet, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(rSet, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(rSet, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(rSet, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(rSet, diagnostics, context);
        if (result || diagnostics != null) result &= validateNamedElement_validName(rSet, diagnostics, context);
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateRSequence(RSequence rSequence, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(rSequence, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(rSequence, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(rSequence, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(rSequence, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(rSequence, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(rSequence, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(rSequence, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(rSequence, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(rSequence, diagnostics, context);
        if (result || diagnostics != null) result &= validateNamedElement_validName(rSequence, diagnostics, context);
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateLayout(Layout layout, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(layout, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateContainerMap(Map.Entry<?, ?> containerMap, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint((EObject)containerMap, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateElementMap(Map.Entry<?, ?> elementMap, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint((EObject)elementMap, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateLayoutElement(LayoutElement layoutElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(layoutElement, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateClassifier(Classifier classifier, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(classifier, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(classifier, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(classifier, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(classifier, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(classifier, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(classifier, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(classifier, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(classifier, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(classifier, diagnostics, context);
        if (result || diagnostics != null) result &= validateNamedElement_validName(classifier, diagnostics, context);
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateImplementationClass(ImplementationClass implementationClass, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(implementationClass, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(implementationClass, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(implementationClass, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(implementationClass, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(implementationClass, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(implementationClass, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(implementationClass, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(implementationClass, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(implementationClass, diagnostics, context);
        if (result || diagnostics != null) result &= validateNamedElement_validName(implementationClass, diagnostics, context);
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateTemporaryProperty(TemporaryProperty temporaryProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(temporaryProperty, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateStructuralFeature(StructuralFeature structuralFeature, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(structuralFeature, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(structuralFeature, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(structuralFeature, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(structuralFeature, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(structuralFeature, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(structuralFeature, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(structuralFeature, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(structuralFeature, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(structuralFeature, diagnostics, context);
        if (result || diagnostics != null) result &= validateNamedElement_validName(structuralFeature, diagnostics, context);
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateReference(Reference reference, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(reference, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(reference, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(reference, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(reference, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(reference, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(reference, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(reference, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(reference, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(reference, diagnostics, context);
        if (result || diagnostics != null) result &= validateNamedElement_validName(reference, diagnostics, context);
        if (result || diagnostics != null) result &= validateProperty_validUpperBound(reference, diagnostics, context);
        if (result || diagnostics != null) result &= validateProperty_validLowerBound(reference, diagnostics, context);
        if (result || diagnostics != null) result &= validateProperty_validMultiplicity(reference, diagnostics, context);
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateProperty(Property property, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(property, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(property, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(property, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(property, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(property, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(property, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(property, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(property, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(property, diagnostics, context);
        if (result || diagnostics != null) result &= validateNamedElement_validName(property, diagnostics, context);
        if (result || diagnostics != null) result &= validateProperty_validUpperBound(property, diagnostics, context);
        if (result || diagnostics != null) result &= validateProperty_validLowerBound(property, diagnostics, context);
        if (result || diagnostics != null) result &= validateProperty_validMultiplicity(property, diagnostics, context);
        return result;
    }

    /**
     * The cached validation expression for the validUpperBound constraint of '<em>Property</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static final String PROPERTY__VALID_UPPER_BOUND__EEXPRESSION = "Tuple {\n" +
        "\tmessage : String = 'The upperbound must be greater than zero or -1 representing *',\n" +
        "\tstatus : Boolean = self.upperBound > 0 or self.upperBound = -1\n" +
        "}.status";

    /**
     * Validates the validUpperBound constraint of '<em>Property</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateProperty_validUpperBound(Property property, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return
            validate
                (RamPackage.Literals.PROPERTY,
                 property,
                 diagnostics,
                 context,
                 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
                 "validUpperBound",
                 PROPERTY__VALID_UPPER_BOUND__EEXPRESSION,
                 Diagnostic.ERROR,
                 DIAGNOSTIC_SOURCE,
                 0);
    }

    /**
     * The cached validation expression for the validLowerBound constraint of '<em>Property</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static final String PROPERTY__VALID_LOWER_BOUND__EEXPRESSION = "Tuple {\n" +
        "\tmessage : String = 'The lowerbound must be greater than or equal to zero',\n" +
        "\tstatus : Boolean = self.lowerBound >= 0\n" +
        "}.status";

    /**
     * Validates the validLowerBound constraint of '<em>Property</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateProperty_validLowerBound(Property property, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return
            validate
                (RamPackage.Literals.PROPERTY,
                 property,
                 diagnostics,
                 context,
                 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
                 "validLowerBound",
                 PROPERTY__VALID_LOWER_BOUND__EEXPRESSION,
                 Diagnostic.ERROR,
                 DIAGNOSTIC_SOURCE,
                 0);
    }

    /**
     * The cached validation expression for the validMultiplicity constraint of '<em>Property</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static final String PROPERTY__VALID_MULTIPLICITY__EEXPRESSION = "Tuple {\n" +
        "\tmessage : String = 'The lowerBound must be less than or equal to the upperBound',\n" +
        "\tstatus : Boolean = self.lowerBound <= self.upperBound or self.upperBound = -1\n" +
        "}.status";

    /**
     * Validates the validMultiplicity constraint of '<em>Property</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateProperty_validMultiplicity(Property property, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return
            validate
                (RamPackage.Literals.PROPERTY,
                 property,
                 diagnostics,
                 context,
                 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
                 "validMultiplicity",
                 PROPERTY__VALID_MULTIPLICITY__EEXPRESSION,
                 Diagnostic.ERROR,
                 DIAGNOSTIC_SOURCE,
                 0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateTypedElement(TypedElement typedElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(typedElement, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(typedElement, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(typedElement, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(typedElement, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(typedElement, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(typedElement, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(typedElement, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(typedElement, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(typedElement, diagnostics, context);
        if (result || diagnostics != null) result &= validateNamedElement_validName(typedElement, diagnostics, context);
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateGate(Gate gate, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(gate, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(gate, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(gate, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(gate, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(gate, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(gate, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(gate, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(gate, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(gate, diagnostics, context);
        if (result || diagnostics != null) result &= validateNamedElement_validName(gate, diagnostics, context);
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateLiteralBoolean(LiteralBoolean literalBoolean, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(literalBoolean, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateClassifierMapping(ClassifierMapping classifierMapping, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(classifierMapping, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateAttributeMapping(AttributeMapping attributeMapping, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(attributeMapping, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateOperationMapping(OperationMapping operationMapping, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(operationMapping, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateParameterMapping(ParameterMapping parameterMapping, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(parameterMapping, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateStateView(StateView stateView, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(stateView, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(stateView, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(stateView, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(stateView, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(stateView, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(stateView, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(stateView, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(stateView, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(stateView, diagnostics, context);
        if (result || diagnostics != null) result &= validateNamedElement_validName(stateView, diagnostics, context);
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateStateMachine(StateMachine stateMachine, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(stateMachine, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateTransition(Transition transition, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(transition, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(transition, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(transition, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(transition, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(transition, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(transition, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(transition, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(transition, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(transition, diagnostics, context);
        if (result || diagnostics != null) result &= validateNamedElement_validName(transition, diagnostics, context);
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateState(State state, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(state, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(state, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(state, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(state, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(state, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(state, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(state, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(state, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(state, diagnostics, context);
        if (result || diagnostics != null) result &= validateNamedElement_validName(state, diagnostics, context);
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateRDouble(RDouble rDouble, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(rDouble, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(rDouble, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(rDouble, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(rDouble, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(rDouble, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(rDouble, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(rDouble, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(rDouble, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(rDouble, diagnostics, context);
        if (result || diagnostics != null) result &= validateNamedElement_validName(rDouble, diagnostics, context);
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateRFloat(RFloat rFloat, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(rFloat, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(rFloat, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(rFloat, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(rFloat, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(rFloat, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(rFloat, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(rFloat, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(rFloat, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(rFloat, diagnostics, context);
        if (result || diagnostics != null) result &= validateNamedElement_validName(rFloat, diagnostics, context);
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateConstraint(Constraint constraint, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(constraint, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateSubstitution(Substitution substitution, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(substitution, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateTransitionSubstitution(TransitionSubstitution transitionSubstitution, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(transitionSubstitution, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateTypeParameter(TypeParameter typeParameter, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(typeParameter, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(typeParameter, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(typeParameter, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(typeParameter, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(typeParameter, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(typeParameter, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(typeParameter, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(typeParameter, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(typeParameter, diagnostics, context);
        if (result || diagnostics != null) result &= validateNamedElement_validName(typeParameter, diagnostics, context);
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateRLong(RLong rLong, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(rLong, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(rLong, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(rLong, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(rLong, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(rLong, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(rLong, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(rLong, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(rLong, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(rLong, diagnostics, context);
        if (result || diagnostics != null) result &= validateNamedElement_validName(rLong, diagnostics, context);
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateRArray(RArray rArray, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(rArray, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(rArray, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(rArray, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(rArray, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(rArray, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(rArray, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(rArray, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(rArray, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(rArray, diagnostics, context);
        if (result || diagnostics != null) result &= validateNamedElement_validName(rArray, diagnostics, context);
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateTraceable(Traceable traceable, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(traceable, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateWovenAspect(WovenAspect wovenAspect, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(wovenAspect, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(wovenAspect, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(wovenAspect, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(wovenAspect, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(wovenAspect, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(wovenAspect, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(wovenAspect, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(wovenAspect, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(wovenAspect, diagnostics, context);
        if (result || diagnostics != null) result &= validateNamedElement_validName(wovenAspect, diagnostics, context);
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateLiteralNull(LiteralNull literalNull, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(literalNull, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateEnumLiteralValue(EnumLiteralValue enumLiteralValue, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(enumLiteralValue, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateAssignmentStatement(AssignmentStatement assignmentStatement, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(assignmentStatement, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(assignmentStatement, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(assignmentStatement, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(assignmentStatement, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(assignmentStatement, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(assignmentStatement, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(assignmentStatement, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(assignmentStatement, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(assignmentStatement, diagnostics, context);
        if (result || diagnostics != null) result &= validateInteractionFragment_validCoveredMultiplicity(assignmentStatement, diagnostics, context);
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateRAMVisibilityType(RAMVisibilityType ramVisibilityType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return true;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateReferenceType(ReferenceType referenceType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return true;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateMessageSort(MessageSort messageSort, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return true;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateInteractionOperatorKind(InteractionOperatorKind interactionOperatorKind, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return true;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateInstantiationType(InstantiationType instantiationType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return true;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateOperationType(OperationType operationType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return true;
    }

    /**
     * Returns the resource locator that will be used to fetch messages for this validator's diagnostics.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ResourceLocator getResourceLocator() {
        // TODO
        // Specialize this to return a resource locator for messages specific to this validator.
        // Ensure that you remove @generated or mark it @generated NOT
        return super.getResourceLocator();
    }

} //RamValidator
