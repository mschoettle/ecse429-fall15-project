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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see ca.mcgill.sel.ram.RamPackage
 * @generated
 */
public class RamAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static RamPackage modelPackage;

    /**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public RamAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = RamPackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc -->
     * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
     * <!-- end-user-doc -->
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(Object object) {
        if (object == modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject)object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected RamSwitch<Adapter> modelSwitch =
        new RamSwitch<Adapter>() {
            @Override
            public Adapter caseAspect(Aspect object) {
                return createAspectAdapter();
            }
            @Override
            public Adapter caseMappableElement(MappableElement object) {
                return createMappableElementAdapter();
            }
            @Override
            public Adapter caseStructuralView(StructuralView object) {
                return createStructuralViewAdapter();
            }
            @Override
            public Adapter caseClass(ca.mcgill.sel.ram.Class object) {
                return createClassAdapter();
            }
            @Override
            public Adapter caseAssociationEnd(AssociationEnd object) {
                return createAssociationEndAdapter();
            }
            @Override
            public Adapter caseAssociation(Association object) {
                return createAssociationAdapter();
            }
            @Override
            public Adapter caseNamedElement(NamedElement object) {
                return createNamedElementAdapter();
            }
            @Override
            public Adapter caseInstantiation(Instantiation object) {
                return createInstantiationAdapter();
            }
            @Override
            public Adapter caseOperation(Operation object) {
                return createOperationAdapter();
            }
            @Override
            public Adapter caseAttribute(Attribute object) {
                return createAttributeAdapter();
            }
            @Override
            public Adapter caseType(Type object) {
                return createTypeAdapter();
            }
            @Override
            public Adapter caseParameter(Parameter object) {
                return createParameterAdapter();
            }
            @Override
            public Adapter casePrimitiveType(PrimitiveType object) {
                return createPrimitiveTypeAdapter();
            }
            @Override
            public Adapter caseObjectType(ObjectType object) {
                return createObjectTypeAdapter();
            }
            @Override
            public Adapter caseRVoid(RVoid object) {
                return createRVoidAdapter();
            }
            @Override
            public Adapter caseRBoolean(RBoolean object) {
                return createRBooleanAdapter();
            }
            @Override
            public Adapter caseRInt(RInt object) {
                return createRIntAdapter();
            }
            @Override
            public Adapter caseRChar(RChar object) {
                return createRCharAdapter();
            }
            @Override
            public Adapter caseRString(RString object) {
                return createRStringAdapter();
            }
            @Override
            public Adapter caseRAny(RAny object) {
                return createRAnyAdapter();
            }
            @Override
            public Adapter caseREnum(REnum object) {
                return createREnumAdapter();
            }
            @Override
            public Adapter caseREnumLiteral(REnumLiteral object) {
                return createREnumLiteralAdapter();
            }
            @Override
            public Adapter caseAbstractMessageView(AbstractMessageView object) {
                return createAbstractMessageViewAdapter();
            }
            @Override
            public Adapter caseMessageView(MessageView object) {
                return createMessageViewAdapter();
            }
            @Override
            public Adapter caseMessageViewReference(MessageViewReference object) {
                return createMessageViewReferenceAdapter();
            }
            @Override
            public Adapter caseInteraction(Interaction object) {
                return createInteractionAdapter();
            }
            @Override
            public Adapter caseAspectMessageView(AspectMessageView object) {
                return createAspectMessageViewAdapter();
            }
            @Override
            public Adapter caseLifeline(Lifeline object) {
                return createLifelineAdapter();
            }
            @Override
            public Adapter caseMessage(Message object) {
                return createMessageAdapter();
            }
            @Override
            public Adapter caseMessageEnd(MessageEnd object) {
                return createMessageEndAdapter();
            }
            @Override
            public Adapter caseMessageOccurrenceSpecification(MessageOccurrenceSpecification object) {
                return createMessageOccurrenceSpecificationAdapter();
            }
            @Override
            public Adapter caseOccurrenceSpecification(OccurrenceSpecification object) {
                return createOccurrenceSpecificationAdapter();
            }
            @Override
            public Adapter caseDestructionOccurrenceSpecification(DestructionOccurrenceSpecification object) {
                return createDestructionOccurrenceSpecificationAdapter();
            }
            @Override
            public Adapter caseInteractionFragment(InteractionFragment object) {
                return createInteractionFragmentAdapter();
            }
            @Override
            public Adapter caseCombinedFragment(CombinedFragment object) {
                return createCombinedFragmentAdapter();
            }
            @Override
            public Adapter caseOriginalBehaviorExecution(OriginalBehaviorExecution object) {
                return createOriginalBehaviorExecutionAdapter();
            }
            @Override
            public Adapter caseExecutionStatement(ExecutionStatement object) {
                return createExecutionStatementAdapter();
            }
            @Override
            public Adapter caseInteractionOperand(InteractionOperand object) {
                return createInteractionOperandAdapter();
            }
            @Override
            public Adapter caseValueSpecification(ValueSpecification object) {
                return createValueSpecificationAdapter();
            }
            @Override
            public Adapter caseStructuralFeatureValue(StructuralFeatureValue object) {
                return createStructuralFeatureValueAdapter();
            }
            @Override
            public Adapter caseParameterValueMapping(ParameterValueMapping object) {
                return createParameterValueMappingAdapter();
            }
            @Override
            public Adapter caseParameterValue(ParameterValue object) {
                return createParameterValueAdapter();
            }
            @Override
            public Adapter caseOpaqueExpression(OpaqueExpression object) {
                return createOpaqueExpressionAdapter();
            }
            @Override
            public Adapter caseLiteralSpecification(LiteralSpecification object) {
                return createLiteralSpecificationAdapter();
            }
            @Override
            public Adapter caseLiteralString(LiteralString object) {
                return createLiteralStringAdapter();
            }
            @Override
            public Adapter caseLiteralInteger(LiteralInteger object) {
                return createLiteralIntegerAdapter();
            }
            @Override
            public Adapter caseFragmentContainer(FragmentContainer object) {
                return createFragmentContainerAdapter();
            }
            @Override
            public Adapter caseRCollection(RCollection object) {
                return createRCollectionAdapter();
            }
            @Override
            public Adapter caseRSet(RSet object) {
                return createRSetAdapter();
            }
            @Override
            public Adapter caseRSequence(RSequence object) {
                return createRSequenceAdapter();
            }
            @Override
            public Adapter caseLayout(Layout object) {
                return createLayoutAdapter();
            }
            @Override
            public Adapter caseContainerMap(Map.Entry<EObject, EMap<EObject, LayoutElement>> object) {
                return createContainerMapAdapter();
            }
            @Override
            public Adapter caseElementMap(Map.Entry<EObject, LayoutElement> object) {
                return createElementMapAdapter();
            }
            @Override
            public Adapter caseLayoutElement(LayoutElement object) {
                return createLayoutElementAdapter();
            }
            @Override
            public Adapter caseClassifier(Classifier object) {
                return createClassifierAdapter();
            }
            @Override
            public Adapter caseImplementationClass(ImplementationClass object) {
                return createImplementationClassAdapter();
            }
            @Override
            public Adapter caseTemporaryProperty(TemporaryProperty object) {
                return createTemporaryPropertyAdapter();
            }
            @Override
            public Adapter caseStructuralFeature(StructuralFeature object) {
                return createStructuralFeatureAdapter();
            }
            @Override
            public Adapter caseReference(Reference object) {
                return createReferenceAdapter();
            }
            @Override
            public Adapter caseProperty(Property object) {
                return createPropertyAdapter();
            }
            @Override
            public Adapter caseTypedElement(TypedElement object) {
                return createTypedElementAdapter();
            }
            @Override
            public Adapter caseGate(Gate object) {
                return createGateAdapter();
            }
            @Override
            public Adapter caseLiteralBoolean(LiteralBoolean object) {
                return createLiteralBooleanAdapter();
            }
            @Override
            public Adapter caseClassifierMapping(ClassifierMapping object) {
                return createClassifierMappingAdapter();
            }
            @Override
            public Adapter caseAttributeMapping(AttributeMapping object) {
                return createAttributeMappingAdapter();
            }
            @Override
            public Adapter caseOperationMapping(OperationMapping object) {
                return createOperationMappingAdapter();
            }
            @Override
            public Adapter caseParameterMapping(ParameterMapping object) {
                return createParameterMappingAdapter();
            }
            @Override
            public Adapter caseStateView(StateView object) {
                return createStateViewAdapter();
            }
            @Override
            public Adapter caseStateMachine(StateMachine object) {
                return createStateMachineAdapter();
            }
            @Override
            public Adapter caseTransition(Transition object) {
                return createTransitionAdapter();
            }
            @Override
            public Adapter caseState(State object) {
                return createStateAdapter();
            }
            @Override
            public Adapter caseRDouble(RDouble object) {
                return createRDoubleAdapter();
            }
            @Override
            public Adapter caseRFloat(RFloat object) {
                return createRFloatAdapter();
            }
            @Override
            public Adapter caseConstraint(Constraint object) {
                return createConstraintAdapter();
            }
            @Override
            public Adapter caseSubstitution(Substitution object) {
                return createSubstitutionAdapter();
            }
            @Override
            public Adapter caseTransitionSubstitution(TransitionSubstitution object) {
                return createTransitionSubstitutionAdapter();
            }
            @Override
            public Adapter caseTypeParameter(TypeParameter object) {
                return createTypeParameterAdapter();
            }
            @Override
            public Adapter caseRLong(RLong object) {
                return createRLongAdapter();
            }
            @Override
            public Adapter caseRArray(RArray object) {
                return createRArrayAdapter();
            }
            @Override
            public Adapter caseTraceable(Traceable object) {
                return createTraceableAdapter();
            }
            @Override
            public Adapter caseWovenAspect(WovenAspect object) {
                return createWovenAspectAdapter();
            }
            @Override
            public Adapter caseLiteralNull(LiteralNull object) {
                return createLiteralNullAdapter();
            }
            @Override
            public Adapter caseEnumLiteralValue(EnumLiteralValue object) {
                return createEnumLiteralValueAdapter();
            }
            @Override
            public Adapter caseAssignmentStatement(AssignmentStatement object) {
                return createAssignmentStatementAdapter();
            }
            @Override
            public Adapter caseCORENamedElement(CORENamedElement object) {
                return createCORENamedElementAdapter();
            }
            @Override
            public Adapter caseCOREModel(COREModel object) {
                return createCOREModelAdapter();
            }
            @Override
            public Adapter caseCOREModelElement(COREModelElement object) {
                return createCOREModelElementAdapter();
            }
            @Override
            public <S extends COREModel> Adapter caseCORECompositionSpecification(CORECompositionSpecification<S> object) {
                return createCORECompositionSpecificationAdapter();
            }
            @Override
            public <S extends COREModel> Adapter caseCOREModelCompositionSpecification(COREModelCompositionSpecification<S> object) {
                return createCOREModelCompositionSpecificationAdapter();
            }
            @Override
            public <S extends COREModel, M extends COREMapping<?>> Adapter caseCOREBinding(COREBinding<S, M> object) {
                return createCOREBindingAdapter();
            }
            @Override
            public <T extends COREModelElement> Adapter caseCOREMapping(COREMapping<T> object) {
                return createCOREMappingAdapter();
            }
            @Override
            public Adapter defaultCase(EObject object) {
                return createEObjectAdapter();
            }
        };

    /**
     * Creates an adapter for the <code>target</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param target the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject)target);
    }


    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.Aspect <em>Aspect</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.Aspect
     * @generated
     */
    public Adapter createAspectAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.MappableElement <em>Mappable Element</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.MappableElement
     * @generated
     */
    public Adapter createMappableElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.StructuralView <em>Structural View</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.StructuralView
     * @generated
     */
    public Adapter createStructuralViewAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.Class <em>Class</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.Class
     * @generated
     */
    public Adapter createClassAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.AssociationEnd <em>Association End</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.AssociationEnd
     * @generated
     */
    public Adapter createAssociationEndAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.Association <em>Association</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.Association
     * @generated
     */
    public Adapter createAssociationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.NamedElement <em>Named Element</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.NamedElement
     * @generated
     */
    public Adapter createNamedElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.Instantiation <em>Instantiation</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.Instantiation
     * @generated
     */
    public Adapter createInstantiationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.Operation <em>Operation</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.Operation
     * @generated
     */
    public Adapter createOperationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.Attribute <em>Attribute</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.Attribute
     * @generated
     */
    public Adapter createAttributeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.Type <em>Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.Type
     * @generated
     */
    public Adapter createTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.Parameter <em>Parameter</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.Parameter
     * @generated
     */
    public Adapter createParameterAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.PrimitiveType <em>Primitive Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.PrimitiveType
     * @generated
     */
    public Adapter createPrimitiveTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.ObjectType <em>Object Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.ObjectType
     * @generated
     */
    public Adapter createObjectTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.RVoid <em>RVoid</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.RVoid
     * @generated
     */
    public Adapter createRVoidAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.RBoolean <em>RBoolean</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.RBoolean
     * @generated
     */
    public Adapter createRBooleanAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.RInt <em>RInt</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.RInt
     * @generated
     */
    public Adapter createRIntAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.RChar <em>RChar</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.RChar
     * @generated
     */
    public Adapter createRCharAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.RString <em>RString</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.RString
     * @generated
     */
    public Adapter createRStringAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.RAny <em>RAny</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.RAny
     * @generated
     */
    public Adapter createRAnyAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.REnum <em>REnum</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.REnum
     * @generated
     */
    public Adapter createREnumAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.REnumLiteral <em>REnum Literal</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.REnumLiteral
     * @generated
     */
    public Adapter createREnumLiteralAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.AbstractMessageView <em>Abstract Message View</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.AbstractMessageView
     * @generated
     */
    public Adapter createAbstractMessageViewAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.MessageView <em>Message View</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.MessageView
     * @generated
     */
    public Adapter createMessageViewAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.MessageViewReference <em>Message View Reference</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.MessageViewReference
     * @generated
     */
    public Adapter createMessageViewReferenceAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.Interaction <em>Interaction</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.Interaction
     * @generated
     */
    public Adapter createInteractionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.AspectMessageView <em>Aspect Message View</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.AspectMessageView
     * @generated
     */
    public Adapter createAspectMessageViewAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.Lifeline <em>Lifeline</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.Lifeline
     * @generated
     */
    public Adapter createLifelineAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.Message <em>Message</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.Message
     * @generated
     */
    public Adapter createMessageAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.MessageEnd <em>Message End</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.MessageEnd
     * @generated
     */
    public Adapter createMessageEndAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.MessageOccurrenceSpecification <em>Message Occurrence Specification</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.MessageOccurrenceSpecification
     * @generated
     */
    public Adapter createMessageOccurrenceSpecificationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.OccurrenceSpecification <em>Occurrence Specification</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.OccurrenceSpecification
     * @generated
     */
    public Adapter createOccurrenceSpecificationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.DestructionOccurrenceSpecification <em>Destruction Occurrence Specification</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.DestructionOccurrenceSpecification
     * @generated
     */
    public Adapter createDestructionOccurrenceSpecificationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.InteractionFragment <em>Interaction Fragment</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.InteractionFragment
     * @generated
     */
    public Adapter createInteractionFragmentAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.CombinedFragment <em>Combined Fragment</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.CombinedFragment
     * @generated
     */
    public Adapter createCombinedFragmentAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.OriginalBehaviorExecution <em>Original Behavior Execution</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.OriginalBehaviorExecution
     * @generated
     */
    public Adapter createOriginalBehaviorExecutionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.ExecutionStatement <em>Execution Statement</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.ExecutionStatement
     * @generated
     */
    public Adapter createExecutionStatementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.InteractionOperand <em>Interaction Operand</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.InteractionOperand
     * @generated
     */
    public Adapter createInteractionOperandAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.ValueSpecification <em>Value Specification</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.ValueSpecification
     * @generated
     */
    public Adapter createValueSpecificationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.StructuralFeatureValue <em>Structural Feature Value</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.StructuralFeatureValue
     * @generated
     */
    public Adapter createStructuralFeatureValueAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.ParameterValueMapping <em>Parameter Value Mapping</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.ParameterValueMapping
     * @generated
     */
    public Adapter createParameterValueMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.ParameterValue <em>Parameter Value</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.ParameterValue
     * @generated
     */
    public Adapter createParameterValueAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.OpaqueExpression <em>Opaque Expression</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.OpaqueExpression
     * @generated
     */
    public Adapter createOpaqueExpressionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.LiteralSpecification <em>Literal Specification</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.LiteralSpecification
     * @generated
     */
    public Adapter createLiteralSpecificationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.LiteralString <em>Literal String</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.LiteralString
     * @generated
     */
    public Adapter createLiteralStringAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.LiteralInteger <em>Literal Integer</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.LiteralInteger
     * @generated
     */
    public Adapter createLiteralIntegerAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.FragmentContainer <em>Fragment Container</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.FragmentContainer
     * @generated
     */
    public Adapter createFragmentContainerAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.RCollection <em>RCollection</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.RCollection
     * @generated
     */
    public Adapter createRCollectionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.RSet <em>RSet</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.RSet
     * @generated
     */
    public Adapter createRSetAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.RSequence <em>RSequence</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.RSequence
     * @generated
     */
    public Adapter createRSequenceAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.Layout <em>Layout</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.Layout
     * @generated
     */
    public Adapter createLayoutAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>Container Map</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see java.util.Map.Entry
     * @generated
     */
    public Adapter createContainerMapAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>Element Map</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see java.util.Map.Entry
     * @generated
     */
    public Adapter createElementMapAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.LayoutElement <em>Layout Element</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.LayoutElement
     * @generated
     */
    public Adapter createLayoutElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.Classifier <em>Classifier</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.Classifier
     * @generated
     */
    public Adapter createClassifierAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.ImplementationClass <em>Implementation Class</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.ImplementationClass
     * @generated
     */
    public Adapter createImplementationClassAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.TemporaryProperty <em>Temporary Property</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.TemporaryProperty
     * @generated
     */
    public Adapter createTemporaryPropertyAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.StructuralFeature <em>Structural Feature</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.StructuralFeature
     * @generated
     */
    public Adapter createStructuralFeatureAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.Reference <em>Reference</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.Reference
     * @generated
     */
    public Adapter createReferenceAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.Property <em>Property</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.Property
     * @generated
     */
    public Adapter createPropertyAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.TypedElement <em>Typed Element</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.TypedElement
     * @generated
     */
    public Adapter createTypedElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.Gate <em>Gate</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.Gate
     * @generated
     */
    public Adapter createGateAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.LiteralBoolean <em>Literal Boolean</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.LiteralBoolean
     * @generated
     */
    public Adapter createLiteralBooleanAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.ClassifierMapping <em>Classifier Mapping</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.ClassifierMapping
     * @generated
     */
    public Adapter createClassifierMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.AttributeMapping <em>Attribute Mapping</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.AttributeMapping
     * @generated
     */
    public Adapter createAttributeMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.OperationMapping <em>Operation Mapping</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.OperationMapping
     * @generated
     */
    public Adapter createOperationMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.ParameterMapping <em>Parameter Mapping</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.ParameterMapping
     * @generated
     */
    public Adapter createParameterMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.StateView <em>State View</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.StateView
     * @generated
     */
    public Adapter createStateViewAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.StateMachine <em>State Machine</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.StateMachine
     * @generated
     */
    public Adapter createStateMachineAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.Transition <em>Transition</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.Transition
     * @generated
     */
    public Adapter createTransitionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.State <em>State</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.State
     * @generated
     */
    public Adapter createStateAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.RDouble <em>RDouble</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.RDouble
     * @generated
     */
    public Adapter createRDoubleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.RFloat <em>RFloat</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.RFloat
     * @generated
     */
    public Adapter createRFloatAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.Constraint <em>Constraint</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.Constraint
     * @generated
     */
    public Adapter createConstraintAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.Substitution <em>Substitution</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.Substitution
     * @generated
     */
    public Adapter createSubstitutionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.TransitionSubstitution <em>Transition Substitution</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.TransitionSubstitution
     * @generated
     */
    public Adapter createTransitionSubstitutionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.TypeParameter <em>Type Parameter</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.TypeParameter
     * @generated
     */
    public Adapter createTypeParameterAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.RLong <em>RLong</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.RLong
     * @generated
     */
    public Adapter createRLongAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.RArray <em>RArray</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.RArray
     * @generated
     */
    public Adapter createRArrayAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.Traceable <em>Traceable</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.Traceable
     * @generated
     */
    public Adapter createTraceableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.WovenAspect <em>Woven Aspect</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.WovenAspect
     * @generated
     */
    public Adapter createWovenAspectAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.LiteralNull <em>Literal Null</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.LiteralNull
     * @generated
     */
    public Adapter createLiteralNullAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.EnumLiteralValue <em>Enum Literal Value</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.EnumLiteralValue
     * @generated
     */
    public Adapter createEnumLiteralValueAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.ram.AssignmentStatement <em>Assignment Statement</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.ram.AssignmentStatement
     * @generated
     */
    public Adapter createAssignmentStatementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.core.CORENamedElement <em>CORE Named Element</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.core.CORENamedElement
     * @generated
     */
    public Adapter createCORENamedElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.core.COREModel <em>CORE Model</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.core.COREModel
     * @generated
     */
    public Adapter createCOREModelAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.core.COREModelElement <em>CORE Model Element</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.core.COREModelElement
     * @generated
     */
    public Adapter createCOREModelElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.core.CORECompositionSpecification <em>CORE Composition Specification</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.core.CORECompositionSpecification
     * @generated
     */
    public Adapter createCORECompositionSpecificationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.core.COREModelCompositionSpecification <em>CORE Model Composition Specification</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.core.COREModelCompositionSpecification
     * @generated
     */
    public Adapter createCOREModelCompositionSpecificationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.core.COREBinding <em>CORE Binding</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.core.COREBinding
     * @generated
     */
    public Adapter createCOREBindingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.core.COREMapping <em>CORE Mapping</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.core.COREMapping
     * @generated
     */
    public Adapter createCOREMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case.
     * <!-- begin-user-doc -->
     * This default implementation returns null.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} //RamAdapterFactory
