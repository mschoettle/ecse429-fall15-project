/**
 */
package ca.mcgill.sel.core.impl;

import java.util.Map;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import ca.mcgill.sel.core.COREConcern;
import ca.mcgill.sel.core.COREConcernConfiguration;
import ca.mcgill.sel.core.COREReuseConfiguration;
import ca.mcgill.sel.core.COREContribution;
import ca.mcgill.sel.core.COREFeature;
import ca.mcgill.sel.core.COREFeatureImpactNode;
import ca.mcgill.sel.core.COREFeatureModel;
import ca.mcgill.sel.core.COREFeatureRelationshipType;
import ca.mcgill.sel.core.COREImpactModel;
import ca.mcgill.sel.core.COREImpactModelBinding;
import ca.mcgill.sel.core.COREImpactNode;
import ca.mcgill.sel.core.COREInterface;
import ca.mcgill.sel.core.COREModelReuse;
import ca.mcgill.sel.core.COREPartialityType;
import ca.mcgill.sel.core.COREReuse;
import ca.mcgill.sel.core.COREVisibilityType;
import ca.mcgill.sel.core.COREWeightedMapping;
import ca.mcgill.sel.core.CoreFactory;
import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.core.LayoutElement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CoreFactoryImpl extends EFactoryImpl implements CoreFactory {
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static CoreFactory init() {
        try {
            CoreFactory theCoreFactory = (CoreFactory)EPackage.Registry.INSTANCE.getEFactory(CorePackage.eNS_URI);
            if (theCoreFactory != null) {
                return theCoreFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new CoreFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CoreFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case CorePackage.CORE_IMPACT_MODEL: return createCOREImpactModel();
            case CorePackage.CORE_CONCERN: return createCOREConcern();
            case CorePackage.CORE_FEATURE: return createCOREFeature();
            case CorePackage.CORE_INTERFACE: return createCOREInterface();
            case CorePackage.CORE_REUSE: return createCOREReuse();
            case CorePackage.CORE_IMPACT_NODE: return createCOREImpactNode();
            case CorePackage.CORE_FEATURE_MODEL: return createCOREFeatureModel();
            case CorePackage.CORE_MODEL_REUSE: return createCOREModelReuse();
            case CorePackage.CORE_CONTRIBUTION: return createCOREContribution();
            case CorePackage.LAYOUT_MAP: return (EObject)createLayoutMap();
            case CorePackage.LAYOUT_ELEMENT: return createLayoutElement();
            case CorePackage.LAYOUT_CONTAINER_MAP: return (EObject)createLayoutContainerMap();
            case CorePackage.CORE_FEATURE_IMPACT_NODE: return createCOREFeatureImpactNode();
            case CorePackage.CORE_WEIGHTED_MAPPING: return createCOREWeightedMapping();
            case CorePackage.CORE_IMPACT_MODEL_BINDING: return createCOREImpactModelBinding();
            case CorePackage.CORE_CONCERN_CONFIGURATION: return createCOREConcernConfiguration();
            case CorePackage.CORE_REUSE_CONFIGURATION: return createCOREReuseConfiguration();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object createFromString(EDataType eDataType, String initialValue) {
        switch (eDataType.getClassifierID()) {
            case CorePackage.CORE_FEATURE_RELATIONSHIP_TYPE:
                return createCOREFeatureRelationshipTypeFromString(eDataType, initialValue);
            case CorePackage.CORE_VISIBILITY_TYPE:
                return createCOREVisibilityTypeFromString(eDataType, initialValue);
            case CorePackage.CORE_PARTIALITY_TYPE:
                return createCOREPartialityTypeFromString(eDataType, initialValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String convertToString(EDataType eDataType, Object instanceValue) {
        switch (eDataType.getClassifierID()) {
            case CorePackage.CORE_FEATURE_RELATIONSHIP_TYPE:
                return convertCOREFeatureRelationshipTypeToString(eDataType, instanceValue);
            case CorePackage.CORE_VISIBILITY_TYPE:
                return convertCOREVisibilityTypeToString(eDataType, instanceValue);
            case CorePackage.CORE_PARTIALITY_TYPE:
                return convertCOREPartialityTypeToString(eDataType, instanceValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public COREImpactModel createCOREImpactModel() {
        COREImpactModelImpl coreImpactModel = new COREImpactModelImpl();
        return coreImpactModel;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public COREConcern createCOREConcern() {
        COREConcernImpl coreConcern = new COREConcernImpl();
        return coreConcern;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public COREFeature createCOREFeature() {
        COREFeatureImpl coreFeature = new COREFeatureImpl();
        return coreFeature;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public COREInterface createCOREInterface() {
        COREInterfaceImpl coreInterface = new COREInterfaceImpl();
        return coreInterface;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public COREReuse createCOREReuse() {
        COREReuseImpl coreReuse = new COREReuseImpl();
        return coreReuse;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public COREImpactNode createCOREImpactNode() {
        COREImpactNodeImpl coreImpactNode = new COREImpactNodeImpl();
        return coreImpactNode;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public COREFeatureModel createCOREFeatureModel() {
        COREFeatureModelImpl coreFeatureModel = new COREFeatureModelImpl();
        return coreFeatureModel;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public COREModelReuse createCOREModelReuse() {
        COREModelReuseImpl coreModelReuse = new COREModelReuseImpl();
        return coreModelReuse;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public COREContribution createCOREContribution() {
        COREContributionImpl coreContribution = new COREContributionImpl();
        return coreContribution;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Map.Entry<EObject, LayoutElement> createLayoutMap() {
        LayoutMapImpl layoutMap = new LayoutMapImpl();
        return layoutMap;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LayoutElement createLayoutElement() {
        LayoutElementImpl layoutElement = new LayoutElementImpl();
        return layoutElement;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Map.Entry<EObject, EMap<EObject, LayoutElement>> createLayoutContainerMap() {
        LayoutContainerMapImpl layoutContainerMap = new LayoutContainerMapImpl();
        return layoutContainerMap;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public COREFeatureImpactNode createCOREFeatureImpactNode() {
        COREFeatureImpactNodeImpl coreFeatureImpactNode = new COREFeatureImpactNodeImpl();
        return coreFeatureImpactNode;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public COREWeightedMapping createCOREWeightedMapping() {
        COREWeightedMappingImpl coreWeightedMapping = new COREWeightedMappingImpl();
        return coreWeightedMapping;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public COREImpactModelBinding createCOREImpactModelBinding() {
        COREImpactModelBindingImpl coreImpactModelBinding = new COREImpactModelBindingImpl();
        return coreImpactModelBinding;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public COREConcernConfiguration createCOREConcernConfiguration() {
        COREConcernConfigurationImpl coreConcernConfiguration = new COREConcernConfigurationImpl();
        return coreConcernConfiguration;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public COREReuseConfiguration createCOREReuseConfiguration() {
        COREReuseConfigurationImpl coreReuseConfiguration = new COREReuseConfigurationImpl();
        return coreReuseConfiguration;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public COREFeatureRelationshipType createCOREFeatureRelationshipTypeFromString(EDataType eDataType, String initialValue) {
        COREFeatureRelationshipType result = COREFeatureRelationshipType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertCOREFeatureRelationshipTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public COREVisibilityType createCOREVisibilityTypeFromString(EDataType eDataType, String initialValue) {
        COREVisibilityType result = COREVisibilityType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertCOREVisibilityTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public COREPartialityType createCOREPartialityTypeFromString(EDataType eDataType, String initialValue) {
        COREPartialityType result = COREPartialityType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertCOREPartialityTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CorePackage getCorePackage() {
        return (CorePackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static CorePackage getPackage() {
        return CorePackage.eINSTANCE;
    }

} //CoreFactoryImpl
