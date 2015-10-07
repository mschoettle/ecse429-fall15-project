/**
 */
package ca.mcgill.sel.ram.util;

import ca.mcgill.sel.core.COREBinding;
import ca.mcgill.sel.core.CORECompositionSpecification;
import ca.mcgill.sel.core.COREMapping;
import ca.mcgill.sel.core.COREModel;
import ca.mcgill.sel.core.COREModelCompositionSpecification;
import ca.mcgill.sel.core.COREModelElement;
import ca.mcgill.sel.core.CORENamedElement;
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
import ca.mcgill.sel.ram.Interaction;
import ca.mcgill.sel.ram.InteractionFragment;
import ca.mcgill.sel.ram.InteractionOperand;
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
import ca.mcgill.sel.ram.MessageView;
import ca.mcgill.sel.ram.MessageViewReference;
import ca.mcgill.sel.ram.NamedElement;
import ca.mcgill.sel.ram.ObjectType;
import ca.mcgill.sel.ram.OccurrenceSpecification;
import ca.mcgill.sel.ram.OpaqueExpression;
import ca.mcgill.sel.ram.Operation;
import ca.mcgill.sel.ram.OperationMapping;
import ca.mcgill.sel.ram.OriginalBehaviorExecution;
import ca.mcgill.sel.ram.Parameter;
import ca.mcgill.sel.ram.ParameterMapping;
import ca.mcgill.sel.ram.ParameterValue;
import ca.mcgill.sel.ram.ParameterValueMapping;
import ca.mcgill.sel.ram.PrimitiveType;
import ca.mcgill.sel.ram.Property;
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

import java.util.Map;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see ca.mcgill.sel.ram.RamPackage
 * @generated
 */
public class RamSwitch<T1> extends Switch<T1> {
    /**
     * The cached model package
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static RamPackage modelPackage;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public RamSwitch() {
        if (modelPackage == null) {
            modelPackage = RamPackage.eINSTANCE;
        }
    }

    /**
     * Checks whether this is a switch for the given package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @parameter ePackage the package in question.
     * @return whether this is a switch for the given package.
     * @generated
     */
    @Override
    protected boolean isSwitchFor(EPackage ePackage) {
        return ePackage == modelPackage;
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    @Override
    protected T1 doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
            case RamPackage.ASPECT: {
                Aspect aspect = (Aspect)theEObject;
                T1 result = caseAspect(aspect);
                if (result == null) result = caseNamedElement(aspect);
                if (result == null) result = caseCOREModel(aspect);
                if (result == null) result = caseCORENamedElement(aspect);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.MAPPABLE_ELEMENT: {
                MappableElement mappableElement = (MappableElement)theEObject;
                T1 result = caseMappableElement(mappableElement);
                if (result == null) result = caseNamedElement(mappableElement);
                if (result == null) result = caseCOREModelElement(mappableElement);
                if (result == null) result = caseCORENamedElement(mappableElement);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.STRUCTURAL_VIEW: {
                StructuralView structuralView = (StructuralView)theEObject;
                T1 result = caseStructuralView(structuralView);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.CLASS: {
                ca.mcgill.sel.ram.Class class_ = (ca.mcgill.sel.ram.Class)theEObject;
                T1 result = caseClass(class_);
                if (result == null) result = caseClassifier(class_);
                if (result == null) result = caseObjectType(class_);
                if (result == null) result = caseTraceable(class_);
                if (result == null) result = caseType(class_);
                if (result == null) result = caseMappableElement(class_);
                if (result == null) result = caseNamedElement(class_);
                if (result == null) result = caseCOREModelElement(class_);
                if (result == null) result = caseCORENamedElement(class_);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.ASSOCIATION_END: {
                AssociationEnd associationEnd = (AssociationEnd)theEObject;
                T1 result = caseAssociationEnd(associationEnd);
                if (result == null) result = caseProperty(associationEnd);
                if (result == null) result = caseStructuralFeature(associationEnd);
                if (result == null) result = caseTypedElement(associationEnd);
                if (result == null) result = caseNamedElement(associationEnd);
                if (result == null) result = caseCORENamedElement(associationEnd);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.ASSOCIATION: {
                Association association = (Association)theEObject;
                T1 result = caseAssociation(association);
                if (result == null) result = caseNamedElement(association);
                if (result == null) result = caseCORENamedElement(association);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.NAMED_ELEMENT: {
                NamedElement namedElement = (NamedElement)theEObject;
                T1 result = caseNamedElement(namedElement);
                if (result == null) result = caseCORENamedElement(namedElement);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.INSTANTIATION: {
                Instantiation instantiation = (Instantiation)theEObject;
                T1 result = caseInstantiation(instantiation);
                if (result == null) result = caseCOREBinding(instantiation);
                if (result == null) result = caseCOREModelCompositionSpecification(instantiation);
                if (result == null) result = caseCORECompositionSpecification(instantiation);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.OPERATION: {
                Operation operation = (Operation)theEObject;
                T1 result = caseOperation(operation);
                if (result == null) result = caseMappableElement(operation);
                if (result == null) result = caseTraceable(operation);
                if (result == null) result = caseNamedElement(operation);
                if (result == null) result = caseCOREModelElement(operation);
                if (result == null) result = caseCORENamedElement(operation);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.ATTRIBUTE: {
                Attribute attribute = (Attribute)theEObject;
                T1 result = caseAttribute(attribute);
                if (result == null) result = caseStructuralFeature(attribute);
                if (result == null) result = caseTemporaryProperty(attribute);
                if (result == null) result = caseMappableElement(attribute);
                if (result == null) result = caseTraceable(attribute);
                if (result == null) result = caseTypedElement(attribute);
                if (result == null) result = caseCOREModelElement(attribute);
                if (result == null) result = caseNamedElement(attribute);
                if (result == null) result = caseCORENamedElement(attribute);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.TYPE: {
                Type type = (Type)theEObject;
                T1 result = caseType(type);
                if (result == null) result = caseNamedElement(type);
                if (result == null) result = caseCORENamedElement(type);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.PARAMETER: {
                Parameter parameter = (Parameter)theEObject;
                T1 result = caseParameter(parameter);
                if (result == null) result = caseTypedElement(parameter);
                if (result == null) result = caseMappableElement(parameter);
                if (result == null) result = caseNamedElement(parameter);
                if (result == null) result = caseCOREModelElement(parameter);
                if (result == null) result = caseCORENamedElement(parameter);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.PRIMITIVE_TYPE: {
                PrimitiveType primitiveType = (PrimitiveType)theEObject;
                T1 result = casePrimitiveType(primitiveType);
                if (result == null) result = caseImplementationClass(primitiveType);
                if (result == null) result = caseType(primitiveType);
                if (result == null) result = caseMappableElement(primitiveType);
                if (result == null) result = caseClassifier(primitiveType);
                if (result == null) result = caseObjectType(primitiveType);
                if (result == null) result = caseNamedElement(primitiveType);
                if (result == null) result = caseCOREModelElement(primitiveType);
                if (result == null) result = caseTraceable(primitiveType);
                if (result == null) result = caseCORENamedElement(primitiveType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.OBJECT_TYPE: {
                ObjectType objectType = (ObjectType)theEObject;
                T1 result = caseObjectType(objectType);
                if (result == null) result = caseType(objectType);
                if (result == null) result = caseMappableElement(objectType);
                if (result == null) result = caseNamedElement(objectType);
                if (result == null) result = caseCOREModelElement(objectType);
                if (result == null) result = caseCORENamedElement(objectType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.RVOID: {
                RVoid rVoid = (RVoid)theEObject;
                T1 result = caseRVoid(rVoid);
                if (result == null) result = caseType(rVoid);
                if (result == null) result = caseNamedElement(rVoid);
                if (result == null) result = caseCORENamedElement(rVoid);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.RBOOLEAN: {
                RBoolean rBoolean = (RBoolean)theEObject;
                T1 result = caseRBoolean(rBoolean);
                if (result == null) result = casePrimitiveType(rBoolean);
                if (result == null) result = caseImplementationClass(rBoolean);
                if (result == null) result = caseType(rBoolean);
                if (result == null) result = caseMappableElement(rBoolean);
                if (result == null) result = caseClassifier(rBoolean);
                if (result == null) result = caseObjectType(rBoolean);
                if (result == null) result = caseNamedElement(rBoolean);
                if (result == null) result = caseCOREModelElement(rBoolean);
                if (result == null) result = caseTraceable(rBoolean);
                if (result == null) result = caseCORENamedElement(rBoolean);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.RINT: {
                RInt rInt = (RInt)theEObject;
                T1 result = caseRInt(rInt);
                if (result == null) result = casePrimitiveType(rInt);
                if (result == null) result = caseImplementationClass(rInt);
                if (result == null) result = caseType(rInt);
                if (result == null) result = caseMappableElement(rInt);
                if (result == null) result = caseClassifier(rInt);
                if (result == null) result = caseObjectType(rInt);
                if (result == null) result = caseNamedElement(rInt);
                if (result == null) result = caseCOREModelElement(rInt);
                if (result == null) result = caseTraceable(rInt);
                if (result == null) result = caseCORENamedElement(rInt);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.RCHAR: {
                RChar rChar = (RChar)theEObject;
                T1 result = caseRChar(rChar);
                if (result == null) result = casePrimitiveType(rChar);
                if (result == null) result = caseImplementationClass(rChar);
                if (result == null) result = caseType(rChar);
                if (result == null) result = caseMappableElement(rChar);
                if (result == null) result = caseClassifier(rChar);
                if (result == null) result = caseObjectType(rChar);
                if (result == null) result = caseNamedElement(rChar);
                if (result == null) result = caseCOREModelElement(rChar);
                if (result == null) result = caseTraceable(rChar);
                if (result == null) result = caseCORENamedElement(rChar);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.RSTRING: {
                RString rString = (RString)theEObject;
                T1 result = caseRString(rString);
                if (result == null) result = casePrimitiveType(rString);
                if (result == null) result = caseImplementationClass(rString);
                if (result == null) result = caseType(rString);
                if (result == null) result = caseMappableElement(rString);
                if (result == null) result = caseClassifier(rString);
                if (result == null) result = caseObjectType(rString);
                if (result == null) result = caseNamedElement(rString);
                if (result == null) result = caseCOREModelElement(rString);
                if (result == null) result = caseTraceable(rString);
                if (result == null) result = caseCORENamedElement(rString);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.RANY: {
                RAny rAny = (RAny)theEObject;
                T1 result = caseRAny(rAny);
                if (result == null) result = caseType(rAny);
                if (result == null) result = caseNamedElement(rAny);
                if (result == null) result = caseCORENamedElement(rAny);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.RENUM: {
                REnum rEnum = (REnum)theEObject;
                T1 result = caseREnum(rEnum);
                if (result == null) result = casePrimitiveType(rEnum);
                if (result == null) result = caseImplementationClass(rEnum);
                if (result == null) result = caseType(rEnum);
                if (result == null) result = caseMappableElement(rEnum);
                if (result == null) result = caseClassifier(rEnum);
                if (result == null) result = caseObjectType(rEnum);
                if (result == null) result = caseNamedElement(rEnum);
                if (result == null) result = caseCOREModelElement(rEnum);
                if (result == null) result = caseTraceable(rEnum);
                if (result == null) result = caseCORENamedElement(rEnum);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.RENUM_LITERAL: {
                REnumLiteral rEnumLiteral = (REnumLiteral)theEObject;
                T1 result = caseREnumLiteral(rEnumLiteral);
                if (result == null) result = caseNamedElement(rEnumLiteral);
                if (result == null) result = caseCORENamedElement(rEnumLiteral);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.ABSTRACT_MESSAGE_VIEW: {
                AbstractMessageView abstractMessageView = (AbstractMessageView)theEObject;
                T1 result = caseAbstractMessageView(abstractMessageView);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.MESSAGE_VIEW: {
                MessageView messageView = (MessageView)theEObject;
                T1 result = caseMessageView(messageView);
                if (result == null) result = caseAbstractMessageView(messageView);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.MESSAGE_VIEW_REFERENCE: {
                MessageViewReference messageViewReference = (MessageViewReference)theEObject;
                T1 result = caseMessageViewReference(messageViewReference);
                if (result == null) result = caseAbstractMessageView(messageViewReference);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.INTERACTION: {
                Interaction interaction = (Interaction)theEObject;
                T1 result = caseInteraction(interaction);
                if (result == null) result = caseFragmentContainer(interaction);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.ASPECT_MESSAGE_VIEW: {
                AspectMessageView aspectMessageView = (AspectMessageView)theEObject;
                T1 result = caseAspectMessageView(aspectMessageView);
                if (result == null) result = caseAbstractMessageView(aspectMessageView);
                if (result == null) result = caseNamedElement(aspectMessageView);
                if (result == null) result = caseCORENamedElement(aspectMessageView);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.LIFELINE: {
                Lifeline lifeline = (Lifeline)theEObject;
                T1 result = caseLifeline(lifeline);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.MESSAGE: {
                Message message = (Message)theEObject;
                T1 result = caseMessage(message);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.MESSAGE_END: {
                MessageEnd messageEnd = (MessageEnd)theEObject;
                T1 result = caseMessageEnd(messageEnd);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.MESSAGE_OCCURRENCE_SPECIFICATION: {
                MessageOccurrenceSpecification messageOccurrenceSpecification = (MessageOccurrenceSpecification)theEObject;
                T1 result = caseMessageOccurrenceSpecification(messageOccurrenceSpecification);
                if (result == null) result = caseOccurrenceSpecification(messageOccurrenceSpecification);
                if (result == null) result = caseMessageEnd(messageOccurrenceSpecification);
                if (result == null) result = caseInteractionFragment(messageOccurrenceSpecification);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.OCCURRENCE_SPECIFICATION: {
                OccurrenceSpecification occurrenceSpecification = (OccurrenceSpecification)theEObject;
                T1 result = caseOccurrenceSpecification(occurrenceSpecification);
                if (result == null) result = caseInteractionFragment(occurrenceSpecification);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.DESTRUCTION_OCCURRENCE_SPECIFICATION: {
                DestructionOccurrenceSpecification destructionOccurrenceSpecification = (DestructionOccurrenceSpecification)theEObject;
                T1 result = caseDestructionOccurrenceSpecification(destructionOccurrenceSpecification);
                if (result == null) result = caseMessageOccurrenceSpecification(destructionOccurrenceSpecification);
                if (result == null) result = caseOccurrenceSpecification(destructionOccurrenceSpecification);
                if (result == null) result = caseMessageEnd(destructionOccurrenceSpecification);
                if (result == null) result = caseInteractionFragment(destructionOccurrenceSpecification);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.INTERACTION_FRAGMENT: {
                InteractionFragment interactionFragment = (InteractionFragment)theEObject;
                T1 result = caseInteractionFragment(interactionFragment);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.COMBINED_FRAGMENT: {
                CombinedFragment combinedFragment = (CombinedFragment)theEObject;
                T1 result = caseCombinedFragment(combinedFragment);
                if (result == null) result = caseInteractionFragment(combinedFragment);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.ORIGINAL_BEHAVIOR_EXECUTION: {
                OriginalBehaviorExecution originalBehaviorExecution = (OriginalBehaviorExecution)theEObject;
                T1 result = caseOriginalBehaviorExecution(originalBehaviorExecution);
                if (result == null) result = caseInteractionFragment(originalBehaviorExecution);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.EXECUTION_STATEMENT: {
                ExecutionStatement executionStatement = (ExecutionStatement)theEObject;
                T1 result = caseExecutionStatement(executionStatement);
                if (result == null) result = caseInteractionFragment(executionStatement);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.INTERACTION_OPERAND: {
                InteractionOperand interactionOperand = (InteractionOperand)theEObject;
                T1 result = caseInteractionOperand(interactionOperand);
                if (result == null) result = caseFragmentContainer(interactionOperand);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.VALUE_SPECIFICATION: {
                ValueSpecification valueSpecification = (ValueSpecification)theEObject;
                T1 result = caseValueSpecification(valueSpecification);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.STRUCTURAL_FEATURE_VALUE: {
                StructuralFeatureValue structuralFeatureValue = (StructuralFeatureValue)theEObject;
                T1 result = caseStructuralFeatureValue(structuralFeatureValue);
                if (result == null) result = caseValueSpecification(structuralFeatureValue);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.PARAMETER_VALUE_MAPPING: {
                ParameterValueMapping parameterValueMapping = (ParameterValueMapping)theEObject;
                T1 result = caseParameterValueMapping(parameterValueMapping);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.PARAMETER_VALUE: {
                ParameterValue parameterValue = (ParameterValue)theEObject;
                T1 result = caseParameterValue(parameterValue);
                if (result == null) result = caseValueSpecification(parameterValue);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.OPAQUE_EXPRESSION: {
                OpaqueExpression opaqueExpression = (OpaqueExpression)theEObject;
                T1 result = caseOpaqueExpression(opaqueExpression);
                if (result == null) result = caseValueSpecification(opaqueExpression);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.LITERAL_SPECIFICATION: {
                LiteralSpecification literalSpecification = (LiteralSpecification)theEObject;
                T1 result = caseLiteralSpecification(literalSpecification);
                if (result == null) result = caseValueSpecification(literalSpecification);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.LITERAL_STRING: {
                LiteralString literalString = (LiteralString)theEObject;
                T1 result = caseLiteralString(literalString);
                if (result == null) result = caseLiteralSpecification(literalString);
                if (result == null) result = caseValueSpecification(literalString);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.LITERAL_INTEGER: {
                LiteralInteger literalInteger = (LiteralInteger)theEObject;
                T1 result = caseLiteralInteger(literalInteger);
                if (result == null) result = caseLiteralSpecification(literalInteger);
                if (result == null) result = caseValueSpecification(literalInteger);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.FRAGMENT_CONTAINER: {
                FragmentContainer fragmentContainer = (FragmentContainer)theEObject;
                T1 result = caseFragmentContainer(fragmentContainer);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.RCOLLECTION: {
                RCollection rCollection = (RCollection)theEObject;
                T1 result = caseRCollection(rCollection);
                if (result == null) result = caseImplementationClass(rCollection);
                if (result == null) result = caseClassifier(rCollection);
                if (result == null) result = caseObjectType(rCollection);
                if (result == null) result = caseTraceable(rCollection);
                if (result == null) result = caseType(rCollection);
                if (result == null) result = caseMappableElement(rCollection);
                if (result == null) result = caseNamedElement(rCollection);
                if (result == null) result = caseCOREModelElement(rCollection);
                if (result == null) result = caseCORENamedElement(rCollection);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.RSET: {
                RSet rSet = (RSet)theEObject;
                T1 result = caseRSet(rSet);
                if (result == null) result = caseRCollection(rSet);
                if (result == null) result = caseImplementationClass(rSet);
                if (result == null) result = caseClassifier(rSet);
                if (result == null) result = caseObjectType(rSet);
                if (result == null) result = caseTraceable(rSet);
                if (result == null) result = caseType(rSet);
                if (result == null) result = caseMappableElement(rSet);
                if (result == null) result = caseNamedElement(rSet);
                if (result == null) result = caseCOREModelElement(rSet);
                if (result == null) result = caseCORENamedElement(rSet);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.RSEQUENCE: {
                RSequence rSequence = (RSequence)theEObject;
                T1 result = caseRSequence(rSequence);
                if (result == null) result = caseRCollection(rSequence);
                if (result == null) result = caseImplementationClass(rSequence);
                if (result == null) result = caseClassifier(rSequence);
                if (result == null) result = caseObjectType(rSequence);
                if (result == null) result = caseTraceable(rSequence);
                if (result == null) result = caseType(rSequence);
                if (result == null) result = caseMappableElement(rSequence);
                if (result == null) result = caseNamedElement(rSequence);
                if (result == null) result = caseCOREModelElement(rSequence);
                if (result == null) result = caseCORENamedElement(rSequence);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.LAYOUT: {
                Layout layout = (Layout)theEObject;
                T1 result = caseLayout(layout);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.CONTAINER_MAP: {
                @SuppressWarnings("unchecked") Map.Entry<EObject, EMap<EObject, LayoutElement>> containerMap = (Map.Entry<EObject, EMap<EObject, LayoutElement>>)theEObject;
                T1 result = caseContainerMap(containerMap);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.ELEMENT_MAP: {
                @SuppressWarnings("unchecked") Map.Entry<EObject, LayoutElement> elementMap = (Map.Entry<EObject, LayoutElement>)theEObject;
                T1 result = caseElementMap(elementMap);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.LAYOUT_ELEMENT: {
                LayoutElement layoutElement = (LayoutElement)theEObject;
                T1 result = caseLayoutElement(layoutElement);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.CLASSIFIER: {
                Classifier classifier = (Classifier)theEObject;
                T1 result = caseClassifier(classifier);
                if (result == null) result = caseObjectType(classifier);
                if (result == null) result = caseTraceable(classifier);
                if (result == null) result = caseType(classifier);
                if (result == null) result = caseMappableElement(classifier);
                if (result == null) result = caseNamedElement(classifier);
                if (result == null) result = caseCOREModelElement(classifier);
                if (result == null) result = caseCORENamedElement(classifier);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.IMPLEMENTATION_CLASS: {
                ImplementationClass implementationClass = (ImplementationClass)theEObject;
                T1 result = caseImplementationClass(implementationClass);
                if (result == null) result = caseClassifier(implementationClass);
                if (result == null) result = caseObjectType(implementationClass);
                if (result == null) result = caseTraceable(implementationClass);
                if (result == null) result = caseType(implementationClass);
                if (result == null) result = caseMappableElement(implementationClass);
                if (result == null) result = caseNamedElement(implementationClass);
                if (result == null) result = caseCOREModelElement(implementationClass);
                if (result == null) result = caseCORENamedElement(implementationClass);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.TEMPORARY_PROPERTY: {
                TemporaryProperty temporaryProperty = (TemporaryProperty)theEObject;
                T1 result = caseTemporaryProperty(temporaryProperty);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.STRUCTURAL_FEATURE: {
                StructuralFeature structuralFeature = (StructuralFeature)theEObject;
                T1 result = caseStructuralFeature(structuralFeature);
                if (result == null) result = caseTypedElement(structuralFeature);
                if (result == null) result = caseNamedElement(structuralFeature);
                if (result == null) result = caseCORENamedElement(structuralFeature);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.REFERENCE: {
                Reference reference = (Reference)theEObject;
                T1 result = caseReference(reference);
                if (result == null) result = caseProperty(reference);
                if (result == null) result = caseTemporaryProperty(reference);
                if (result == null) result = caseStructuralFeature(reference);
                if (result == null) result = caseTypedElement(reference);
                if (result == null) result = caseNamedElement(reference);
                if (result == null) result = caseCORENamedElement(reference);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.PROPERTY: {
                Property property = (Property)theEObject;
                T1 result = caseProperty(property);
                if (result == null) result = caseStructuralFeature(property);
                if (result == null) result = caseTypedElement(property);
                if (result == null) result = caseNamedElement(property);
                if (result == null) result = caseCORENamedElement(property);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.TYPED_ELEMENT: {
                TypedElement typedElement = (TypedElement)theEObject;
                T1 result = caseTypedElement(typedElement);
                if (result == null) result = caseNamedElement(typedElement);
                if (result == null) result = caseCORENamedElement(typedElement);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.GATE: {
                Gate gate = (Gate)theEObject;
                T1 result = caseGate(gate);
                if (result == null) result = caseMessageEnd(gate);
                if (result == null) result = caseNamedElement(gate);
                if (result == null) result = caseCORENamedElement(gate);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.LITERAL_BOOLEAN: {
                LiteralBoolean literalBoolean = (LiteralBoolean)theEObject;
                T1 result = caseLiteralBoolean(literalBoolean);
                if (result == null) result = caseLiteralSpecification(literalBoolean);
                if (result == null) result = caseValueSpecification(literalBoolean);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.CLASSIFIER_MAPPING: {
                ClassifierMapping classifierMapping = (ClassifierMapping)theEObject;
                T1 result = caseClassifierMapping(classifierMapping);
                if (result == null) result = caseCOREMapping(classifierMapping);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.ATTRIBUTE_MAPPING: {
                AttributeMapping attributeMapping = (AttributeMapping)theEObject;
                T1 result = caseAttributeMapping(attributeMapping);
                if (result == null) result = caseCOREMapping(attributeMapping);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.OPERATION_MAPPING: {
                OperationMapping operationMapping = (OperationMapping)theEObject;
                T1 result = caseOperationMapping(operationMapping);
                if (result == null) result = caseCOREMapping(operationMapping);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.PARAMETER_MAPPING: {
                ParameterMapping parameterMapping = (ParameterMapping)theEObject;
                T1 result = caseParameterMapping(parameterMapping);
                if (result == null) result = caseCOREMapping(parameterMapping);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.STATE_VIEW: {
                StateView stateView = (StateView)theEObject;
                T1 result = caseStateView(stateView);
                if (result == null) result = caseNamedElement(stateView);
                if (result == null) result = caseCORENamedElement(stateView);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.STATE_MACHINE: {
                StateMachine stateMachine = (StateMachine)theEObject;
                T1 result = caseStateMachine(stateMachine);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.TRANSITION: {
                Transition transition = (Transition)theEObject;
                T1 result = caseTransition(transition);
                if (result == null) result = caseNamedElement(transition);
                if (result == null) result = caseCORENamedElement(transition);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.STATE: {
                State state = (State)theEObject;
                T1 result = caseState(state);
                if (result == null) result = caseNamedElement(state);
                if (result == null) result = caseCORENamedElement(state);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.RDOUBLE: {
                RDouble rDouble = (RDouble)theEObject;
                T1 result = caseRDouble(rDouble);
                if (result == null) result = casePrimitiveType(rDouble);
                if (result == null) result = caseImplementationClass(rDouble);
                if (result == null) result = caseType(rDouble);
                if (result == null) result = caseMappableElement(rDouble);
                if (result == null) result = caseClassifier(rDouble);
                if (result == null) result = caseObjectType(rDouble);
                if (result == null) result = caseNamedElement(rDouble);
                if (result == null) result = caseCOREModelElement(rDouble);
                if (result == null) result = caseTraceable(rDouble);
                if (result == null) result = caseCORENamedElement(rDouble);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.RFLOAT: {
                RFloat rFloat = (RFloat)theEObject;
                T1 result = caseRFloat(rFloat);
                if (result == null) result = casePrimitiveType(rFloat);
                if (result == null) result = caseImplementationClass(rFloat);
                if (result == null) result = caseType(rFloat);
                if (result == null) result = caseMappableElement(rFloat);
                if (result == null) result = caseClassifier(rFloat);
                if (result == null) result = caseObjectType(rFloat);
                if (result == null) result = caseNamedElement(rFloat);
                if (result == null) result = caseCOREModelElement(rFloat);
                if (result == null) result = caseTraceable(rFloat);
                if (result == null) result = caseCORENamedElement(rFloat);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.CONSTRAINT: {
                Constraint constraint = (Constraint)theEObject;
                T1 result = caseConstraint(constraint);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.SUBSTITUTION: {
                Substitution substitution = (Substitution)theEObject;
                T1 result = caseSubstitution(substitution);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.TRANSITION_SUBSTITUTION: {
                TransitionSubstitution transitionSubstitution = (TransitionSubstitution)theEObject;
                T1 result = caseTransitionSubstitution(transitionSubstitution);
                if (result == null) result = caseSubstitution(transitionSubstitution);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.TYPE_PARAMETER: {
                TypeParameter typeParameter = (TypeParameter)theEObject;
                T1 result = caseTypeParameter(typeParameter);
                if (result == null) result = caseType(typeParameter);
                if (result == null) result = caseNamedElement(typeParameter);
                if (result == null) result = caseCORENamedElement(typeParameter);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.RLONG: {
                RLong rLong = (RLong)theEObject;
                T1 result = caseRLong(rLong);
                if (result == null) result = casePrimitiveType(rLong);
                if (result == null) result = caseImplementationClass(rLong);
                if (result == null) result = caseType(rLong);
                if (result == null) result = caseMappableElement(rLong);
                if (result == null) result = caseClassifier(rLong);
                if (result == null) result = caseObjectType(rLong);
                if (result == null) result = caseNamedElement(rLong);
                if (result == null) result = caseCOREModelElement(rLong);
                if (result == null) result = caseTraceable(rLong);
                if (result == null) result = caseCORENamedElement(rLong);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.RARRAY: {
                RArray rArray = (RArray)theEObject;
                T1 result = caseRArray(rArray);
                if (result == null) result = casePrimitiveType(rArray);
                if (result == null) result = caseImplementationClass(rArray);
                if (result == null) result = caseType(rArray);
                if (result == null) result = caseMappableElement(rArray);
                if (result == null) result = caseClassifier(rArray);
                if (result == null) result = caseObjectType(rArray);
                if (result == null) result = caseNamedElement(rArray);
                if (result == null) result = caseCOREModelElement(rArray);
                if (result == null) result = caseTraceable(rArray);
                if (result == null) result = caseCORENamedElement(rArray);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.TRACEABLE: {
                Traceable traceable = (Traceable)theEObject;
                T1 result = caseTraceable(traceable);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.WOVEN_ASPECT: {
                WovenAspect wovenAspect = (WovenAspect)theEObject;
                T1 result = caseWovenAspect(wovenAspect);
                if (result == null) result = caseNamedElement(wovenAspect);
                if (result == null) result = caseCORENamedElement(wovenAspect);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.LITERAL_NULL: {
                LiteralNull literalNull = (LiteralNull)theEObject;
                T1 result = caseLiteralNull(literalNull);
                if (result == null) result = caseLiteralSpecification(literalNull);
                if (result == null) result = caseValueSpecification(literalNull);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.ENUM_LITERAL_VALUE: {
                EnumLiteralValue enumLiteralValue = (EnumLiteralValue)theEObject;
                T1 result = caseEnumLiteralValue(enumLiteralValue);
                if (result == null) result = caseValueSpecification(enumLiteralValue);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RamPackage.ASSIGNMENT_STATEMENT: {
                AssignmentStatement assignmentStatement = (AssignmentStatement)theEObject;
                T1 result = caseAssignmentStatement(assignmentStatement);
                if (result == null) result = caseInteractionFragment(assignmentStatement);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Aspect</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Aspect</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseAspect(Aspect object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Mappable Element</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Mappable Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseMappableElement(MappableElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Structural View</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Structural View</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseStructuralView(StructuralView object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Class</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Class</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseClass(ca.mcgill.sel.ram.Class object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Association End</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Association End</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseAssociationEnd(AssociationEnd object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Association</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Association</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseAssociation(Association object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Named Element</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Named Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseNamedElement(NamedElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Instantiation</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Instantiation</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseInstantiation(Instantiation object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Operation</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Operation</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseOperation(Operation object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Attribute</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Attribute</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseAttribute(Attribute object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseType(Type object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Parameter</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Parameter</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseParameter(Parameter object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Primitive Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Primitive Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 casePrimitiveType(PrimitiveType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Object Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Object Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseObjectType(ObjectType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>RVoid</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>RVoid</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseRVoid(RVoid object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>RBoolean</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>RBoolean</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseRBoolean(RBoolean object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>RInt</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>RInt</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseRInt(RInt object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>RChar</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>RChar</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseRChar(RChar object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>RString</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>RString</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseRString(RString object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>RAny</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>RAny</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseRAny(RAny object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>REnum</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>REnum</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseREnum(REnum object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>REnum Literal</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>REnum Literal</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseREnumLiteral(REnumLiteral object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Message View</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Message View</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseAbstractMessageView(AbstractMessageView object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Message View</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Message View</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseMessageView(MessageView object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Message View Reference</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Message View Reference</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseMessageViewReference(MessageViewReference object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Interaction</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Interaction</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseInteraction(Interaction object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Aspect Message View</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Aspect Message View</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseAspectMessageView(AspectMessageView object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Lifeline</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Lifeline</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseLifeline(Lifeline object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Message</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Message</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseMessage(Message object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Message End</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Message End</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseMessageEnd(MessageEnd object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Message Occurrence Specification</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Message Occurrence Specification</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseMessageOccurrenceSpecification(MessageOccurrenceSpecification object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Occurrence Specification</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Occurrence Specification</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseOccurrenceSpecification(OccurrenceSpecification object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Destruction Occurrence Specification</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Destruction Occurrence Specification</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseDestructionOccurrenceSpecification(DestructionOccurrenceSpecification object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Interaction Fragment</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Interaction Fragment</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseInteractionFragment(InteractionFragment object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Combined Fragment</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Combined Fragment</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseCombinedFragment(CombinedFragment object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Original Behavior Execution</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Original Behavior Execution</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseOriginalBehaviorExecution(OriginalBehaviorExecution object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Execution Statement</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Execution Statement</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseExecutionStatement(ExecutionStatement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Interaction Operand</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Interaction Operand</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseInteractionOperand(InteractionOperand object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Value Specification</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Value Specification</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseValueSpecification(ValueSpecification object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Structural Feature Value</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Structural Feature Value</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseStructuralFeatureValue(StructuralFeatureValue object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Parameter Value Mapping</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Parameter Value Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseParameterValueMapping(ParameterValueMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Parameter Value</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Parameter Value</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseParameterValue(ParameterValue object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Opaque Expression</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Opaque Expression</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseOpaqueExpression(OpaqueExpression object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Literal Specification</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Literal Specification</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseLiteralSpecification(LiteralSpecification object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Literal String</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Literal String</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseLiteralString(LiteralString object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Literal Integer</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Literal Integer</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseLiteralInteger(LiteralInteger object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Fragment Container</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Fragment Container</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseFragmentContainer(FragmentContainer object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>RCollection</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>RCollection</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseRCollection(RCollection object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>RSet</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>RSet</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseRSet(RSet object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>RSequence</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>RSequence</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseRSequence(RSequence object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Layout</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Layout</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseLayout(Layout object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Container Map</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Container Map</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseContainerMap(Map.Entry<EObject, EMap<EObject, LayoutElement>> object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Element Map</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Element Map</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseElementMap(Map.Entry<EObject, LayoutElement> object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Layout Element</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Layout Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseLayoutElement(LayoutElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Classifier</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Classifier</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseClassifier(Classifier object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Implementation Class</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Implementation Class</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseImplementationClass(ImplementationClass object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Temporary Property</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Temporary Property</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseTemporaryProperty(TemporaryProperty object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Structural Feature</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Structural Feature</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseStructuralFeature(StructuralFeature object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Reference</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Reference</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseReference(Reference object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Property</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Property</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseProperty(Property object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Typed Element</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Typed Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseTypedElement(TypedElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Gate</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Gate</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseGate(Gate object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Literal Boolean</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Literal Boolean</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseLiteralBoolean(LiteralBoolean object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Classifier Mapping</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Classifier Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseClassifierMapping(ClassifierMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Attribute Mapping</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Attribute Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseAttributeMapping(AttributeMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Operation Mapping</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Operation Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseOperationMapping(OperationMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Parameter Mapping</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Parameter Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseParameterMapping(ParameterMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>State View</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>State View</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseStateView(StateView object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>State Machine</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>State Machine</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseStateMachine(StateMachine object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Transition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Transition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseTransition(Transition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>State</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>State</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseState(State object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>RDouble</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>RDouble</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseRDouble(RDouble object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>RFloat</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>RFloat</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseRFloat(RFloat object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Constraint</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Constraint</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseConstraint(Constraint object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Substitution</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Substitution</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseSubstitution(Substitution object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Transition Substitution</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Transition Substitution</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseTransitionSubstitution(TransitionSubstitution object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Type Parameter</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Type Parameter</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseTypeParameter(TypeParameter object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>RLong</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>RLong</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseRLong(RLong object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>RArray</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>RArray</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseRArray(RArray object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Traceable</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Traceable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseTraceable(Traceable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Woven Aspect</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Woven Aspect</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseWovenAspect(WovenAspect object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Literal Null</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Literal Null</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseLiteralNull(LiteralNull object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Enum Literal Value</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Enum Literal Value</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseEnumLiteralValue(EnumLiteralValue object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Assignment Statement</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Assignment Statement</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseAssignmentStatement(AssignmentStatement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>CORE Named Element</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>CORE Named Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseCORENamedElement(CORENamedElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>CORE Model</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>CORE Model</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseCOREModel(COREModel object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>CORE Model Element</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>CORE Model Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseCOREModelElement(COREModelElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>CORE Composition Specification</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>CORE Composition Specification</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public <S extends COREModel> T1 caseCORECompositionSpecification(CORECompositionSpecification<S> object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>CORE Model Composition Specification</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>CORE Model Composition Specification</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public <S extends COREModel> T1 caseCOREModelCompositionSpecification(COREModelCompositionSpecification<S> object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>CORE Binding</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>CORE Binding</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public <S extends COREModel, M extends COREMapping<?>> T1 caseCOREBinding(COREBinding<S, M> object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>CORE Mapping</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>CORE Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public <T extends COREModelElement> T1 caseCOREMapping(COREMapping<T> object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch, but this is the last case anyway.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    @Override
    public T1 defaultCase(EObject object) {
        return null;
    }

} //RamSwitch
