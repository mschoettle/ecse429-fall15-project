/**
 */
package ca.mcgill.sel.core.util;

import java.util.Map;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;

import ca.mcgill.sel.core.COREBinding;
import ca.mcgill.sel.core.CORECompositionSpecification;
import ca.mcgill.sel.core.COREConcern;
import ca.mcgill.sel.core.COREConcernConfiguration;
import ca.mcgill.sel.core.COREConfiguration;
import ca.mcgill.sel.core.COREReuseConfiguration;
import ca.mcgill.sel.core.COREContribution;
import ca.mcgill.sel.core.COREFeature;
import ca.mcgill.sel.core.COREFeatureImpactNode;
import ca.mcgill.sel.core.COREFeatureModel;
import ca.mcgill.sel.core.COREImpactModel;
import ca.mcgill.sel.core.COREImpactModelBinding;
import ca.mcgill.sel.core.COREImpactNode;
import ca.mcgill.sel.core.COREInterface;
import ca.mcgill.sel.core.COREMapping;
import ca.mcgill.sel.core.COREModel;
import ca.mcgill.sel.core.COREModelCompositionSpecification;
import ca.mcgill.sel.core.COREModelElement;
import ca.mcgill.sel.core.COREModelReuse;
import ca.mcgill.sel.core.CORENamedElement;
import ca.mcgill.sel.core.COREPattern;
import ca.mcgill.sel.core.COREReuse;
import ca.mcgill.sel.core.COREWeightedMapping;
import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.core.LayoutElement;

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
 * @see ca.mcgill.sel.core.CorePackage
 * @generated
 */
public class CoreSwitch<T1> extends Switch<T1> {
    /**
     * The cached model package
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static CorePackage modelPackage;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CoreSwitch() {
        if (modelPackage == null) {
            modelPackage = CorePackage.eINSTANCE;
        }
    }

    /**
     * Checks whether this is a switch for the given package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param ePackage the package in question.
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
            case CorePackage.CORE_MODEL: {
                COREModel coreModel = (COREModel)theEObject;
                T1 result = caseCOREModel(coreModel);
                if (result == null) result = caseCORENamedElement(coreModel);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case CorePackage.CORE_IMPACT_MODEL: {
                COREImpactModel coreImpactModel = (COREImpactModel)theEObject;
                T1 result = caseCOREImpactModel(coreImpactModel);
                if (result == null) result = caseCOREModel(coreImpactModel);
                if (result == null) result = caseCORENamedElement(coreImpactModel);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case CorePackage.CORE_CONCERN: {
                COREConcern coreConcern = (COREConcern)theEObject;
                T1 result = caseCOREConcern(coreConcern);
                if (result == null) result = caseCORENamedElement(coreConcern);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case CorePackage.CORE_FEATURE: {
                COREFeature coreFeature = (COREFeature)theEObject;
                T1 result = caseCOREFeature(coreFeature);
                if (result == null) result = caseCOREModelElement(coreFeature);
                if (result == null) result = caseCORENamedElement(coreFeature);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case CorePackage.CORE_BINDING: {
                COREBinding<?, ?> coreBinding = (COREBinding<?, ?>)theEObject;
                T1 result = caseCOREBinding(coreBinding);
                if (result == null) result = caseCOREModelCompositionSpecification(coreBinding);
                if (result == null) result = caseCORECompositionSpecification(coreBinding);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case CorePackage.CORE_MODEL_ELEMENT: {
                COREModelElement coreModelElement = (COREModelElement)theEObject;
                T1 result = caseCOREModelElement(coreModelElement);
                if (result == null) result = caseCORENamedElement(coreModelElement);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case CorePackage.CORE_COMPOSITION_SPECIFICATION: {
                CORECompositionSpecification<?> coreCompositionSpecification = (CORECompositionSpecification<?>)theEObject;
                T1 result = caseCORECompositionSpecification(coreCompositionSpecification);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case CorePackage.CORE_MAPPING: {
                COREMapping<?> coreMapping = (COREMapping<?>)theEObject;
                T1 result = caseCOREMapping(coreMapping);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case CorePackage.CORE_NAMED_ELEMENT: {
                CORENamedElement coreNamedElement = (CORENamedElement)theEObject;
                T1 result = caseCORENamedElement(coreNamedElement);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case CorePackage.CORE_INTERFACE: {
                COREInterface coreInterface = (COREInterface)theEObject;
                T1 result = caseCOREInterface(coreInterface);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case CorePackage.CORE_REUSE: {
                COREReuse coreReuse = (COREReuse)theEObject;
                T1 result = caseCOREReuse(coreReuse);
                if (result == null) result = caseCORENamedElement(coreReuse);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case CorePackage.CORE_PATTERN: {
                COREPattern<?> corePattern = (COREPattern<?>)theEObject;
                T1 result = caseCOREPattern(corePattern);
                if (result == null) result = caseCOREModelCompositionSpecification(corePattern);
                if (result == null) result = caseCORECompositionSpecification(corePattern);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case CorePackage.CORE_IMPACT_NODE: {
                COREImpactNode coreImpactNode = (COREImpactNode)theEObject;
                T1 result = caseCOREImpactNode(coreImpactNode);
                if (result == null) result = caseCOREModelElement(coreImpactNode);
                if (result == null) result = caseCORENamedElement(coreImpactNode);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case CorePackage.CORE_CONFIGURATION: {
                COREConfiguration coreConfiguration = (COREConfiguration)theEObject;
                T1 result = caseCOREConfiguration(coreConfiguration);
                if (result == null) result = caseCORENamedElement(coreConfiguration);
                if (result == null) result = caseCORECompositionSpecification(coreConfiguration);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case CorePackage.CORE_FEATURE_MODEL: {
                COREFeatureModel coreFeatureModel = (COREFeatureModel)theEObject;
                T1 result = caseCOREFeatureModel(coreFeatureModel);
                if (result == null) result = caseCOREModel(coreFeatureModel);
                if (result == null) result = caseCORENamedElement(coreFeatureModel);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case CorePackage.CORE_MODEL_REUSE: {
                COREModelReuse coreModelReuse = (COREModelReuse)theEObject;
                T1 result = caseCOREModelReuse(coreModelReuse);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case CorePackage.CORE_CONTRIBUTION: {
                COREContribution coreContribution = (COREContribution)theEObject;
                T1 result = caseCOREContribution(coreContribution);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case CorePackage.LAYOUT_MAP: {
                @SuppressWarnings("unchecked") Map.Entry<EObject, LayoutElement> layoutMap = (Map.Entry<EObject, LayoutElement>)theEObject;
                T1 result = caseLayoutMap(layoutMap);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case CorePackage.LAYOUT_ELEMENT: {
                LayoutElement layoutElement = (LayoutElement)theEObject;
                T1 result = caseLayoutElement(layoutElement);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case CorePackage.LAYOUT_CONTAINER_MAP: {
                @SuppressWarnings("unchecked") Map.Entry<EObject, EMap<EObject, LayoutElement>> layoutContainerMap = (Map.Entry<EObject, EMap<EObject, LayoutElement>>)theEObject;
                T1 result = caseLayoutContainerMap(layoutContainerMap);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case CorePackage.CORE_FEATURE_IMPACT_NODE: {
                COREFeatureImpactNode coreFeatureImpactNode = (COREFeatureImpactNode)theEObject;
                T1 result = caseCOREFeatureImpactNode(coreFeatureImpactNode);
                if (result == null) result = caseCOREImpactNode(coreFeatureImpactNode);
                if (result == null) result = caseCOREModelElement(coreFeatureImpactNode);
                if (result == null) result = caseCORENamedElement(coreFeatureImpactNode);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case CorePackage.CORE_MODEL_COMPOSITION_SPECIFICATION: {
                COREModelCompositionSpecification<?> coreModelCompositionSpecification = (COREModelCompositionSpecification<?>)theEObject;
                T1 result = caseCOREModelCompositionSpecification(coreModelCompositionSpecification);
                if (result == null) result = caseCORECompositionSpecification(coreModelCompositionSpecification);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case CorePackage.CORE_WEIGHTED_MAPPING: {
                COREWeightedMapping coreWeightedMapping = (COREWeightedMapping)theEObject;
                T1 result = caseCOREWeightedMapping(coreWeightedMapping);
                if (result == null) result = caseCOREMapping(coreWeightedMapping);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case CorePackage.CORE_IMPACT_MODEL_BINDING: {
                COREImpactModelBinding coreImpactModelBinding = (COREImpactModelBinding)theEObject;
                T1 result = caseCOREImpactModelBinding(coreImpactModelBinding);
                if (result == null) result = caseCOREBinding(coreImpactModelBinding);
                if (result == null) result = caseCOREModelCompositionSpecification(coreImpactModelBinding);
                if (result == null) result = caseCORECompositionSpecification(coreImpactModelBinding);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case CorePackage.CORE_CONCERN_CONFIGURATION: {
                COREConcernConfiguration coreConcernConfiguration = (COREConcernConfiguration)theEObject;
                T1 result = caseCOREConcernConfiguration(coreConcernConfiguration);
                if (result == null) result = caseCOREConfiguration(coreConcernConfiguration);
                if (result == null) result = caseCORENamedElement(coreConcernConfiguration);
                if (result == null) result = caseCORECompositionSpecification(coreConcernConfiguration);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case CorePackage.CORE_REUSE_CONFIGURATION: {
                COREReuseConfiguration coreReuseConfiguration = (COREReuseConfiguration)theEObject;
                T1 result = caseCOREReuseConfiguration(coreReuseConfiguration);
                if (result == null) result = caseCOREConfiguration(coreReuseConfiguration);
                if (result == null) result = caseCORENamedElement(coreReuseConfiguration);
                if (result == null) result = caseCORECompositionSpecification(coreReuseConfiguration);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
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
     * Returns the result of interpreting the object as an instance of '<em>CORE Impact Model</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>CORE Impact Model</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseCOREImpactModel(COREImpactModel object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>CORE Concern</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>CORE Concern</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseCOREConcern(COREConcern object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>CORE Feature</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>CORE Feature</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseCOREFeature(COREFeature object) {
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
     * Returns the result of interpreting the object as an instance of '<em>CORE Interface</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>CORE Interface</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseCOREInterface(COREInterface object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>CORE Reuse</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>CORE Reuse</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseCOREReuse(COREReuse object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>CORE Pattern</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>CORE Pattern</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public <S extends COREModel> T1 caseCOREPattern(COREPattern<S> object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>CORE Impact Node</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>CORE Impact Node</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseCOREImpactNode(COREImpactNode object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>CORE Configuration</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>CORE Configuration</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseCOREConfiguration(COREConfiguration object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>CORE Feature Model</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>CORE Feature Model</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseCOREFeatureModel(COREFeatureModel object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>CORE Model Reuse</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>CORE Model Reuse</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseCOREModelReuse(COREModelReuse object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>CORE Contribution</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>CORE Contribution</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseCOREContribution(COREContribution object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Layout Map</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Layout Map</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseLayoutMap(Map.Entry<EObject, LayoutElement> object) {
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
     * Returns the result of interpreting the object as an instance of '<em>Layout Container Map</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Layout Container Map</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseLayoutContainerMap(Map.Entry<EObject, EMap<EObject, LayoutElement>> object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>CORE Feature Impact Node</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>CORE Feature Impact Node</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseCOREFeatureImpactNode(COREFeatureImpactNode object) {
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
     * Returns the result of interpreting the object as an instance of '<em>CORE Weighted Mapping</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>CORE Weighted Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseCOREWeightedMapping(COREWeightedMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>CORE Impact Model Binding</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>CORE Impact Model Binding</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseCOREImpactModelBinding(COREImpactModelBinding object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>CORE Concern Configuration</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>CORE Concern Configuration</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseCOREConcernConfiguration(COREConcernConfiguration object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>CORE Reuse Configuration</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>CORE Reuse Configuration</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseCOREReuseConfiguration(COREReuseConfiguration object) {
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

} //CoreSwitch
