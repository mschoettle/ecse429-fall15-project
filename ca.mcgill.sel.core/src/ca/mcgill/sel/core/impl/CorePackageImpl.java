/**
 */
package ca.mcgill.sel.core.impl;

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
import ca.mcgill.sel.core.COREFeatureRelationshipType;
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
import ca.mcgill.sel.core.COREPartialityType;
import ca.mcgill.sel.core.COREPattern;
import ca.mcgill.sel.core.COREReuse;
import ca.mcgill.sel.core.COREVisibilityType;
import ca.mcgill.sel.core.COREWeightedMapping;
import ca.mcgill.sel.core.CoreFactory;
import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.core.LayoutElement;

import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypeParameter;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CorePackageImpl extends EPackageImpl implements CorePackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass coreModelEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass coreImpactModelEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass coreConcernEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass coreFeatureEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass coreBindingEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass coreModelElementEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass coreCompositionSpecificationEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass coreMappingEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass coreNamedElementEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass coreInterfaceEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass coreReuseEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass corePatternEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass coreImpactNodeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass coreConfigurationEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass coreFeatureModelEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass coreModelReuseEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass coreContributionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass layoutMapEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass layoutElementEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass layoutContainerMapEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass coreFeatureImpactNodeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass coreModelCompositionSpecificationEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass coreWeightedMappingEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass coreImpactModelBindingEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass coreConcernConfigurationEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass coreReuseConfigurationEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum coreFeatureRelationshipTypeEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum coreVisibilityTypeEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum corePartialityTypeEEnum = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
     * package URI value.
     * <p>Note: the correct way to create the package is via the static
     * factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package,
     * if one already exists.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see ca.mcgill.sel.core.CorePackage#eNS_URI
     * @see #init()
     * @generated
     */
    private CorePackageImpl() {
        super(eNS_URI, CoreFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     * 
     * <p>This method is used to initialize {@link CorePackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static CorePackage init() {
        if (isInited) return (CorePackage)EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI);

        // Obtain or create and register package
        CorePackageImpl theCorePackage = (CorePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof CorePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new CorePackageImpl());

        isInited = true;

        // Create package meta-data objects
        theCorePackage.createPackageContents();

        // Initialize created meta-data
        theCorePackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theCorePackage.freeze();

  
        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(CorePackage.eNS_URI, theCorePackage);
        return theCorePackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCOREModel() {
        return coreModelEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCOREModel_ModelReuses() {
        return (EReference)coreModelEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCOREModel_ModelElements() {
        return (EReference)coreModelEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCOREModel_Realizes() {
        return (EReference)coreModelEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCOREModel_CoreConcern() {
        return (EReference)coreModelEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCOREImpactModel() {
        return coreImpactModelEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCOREImpactModel_ImpactModelElements() {
        return (EReference)coreImpactModelEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCOREImpactModel_Layouts() {
        return (EReference)coreImpactModelEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCOREImpactModel_Contributions() {
        return (EReference)coreImpactModelEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCOREConcern() {
        return coreConcernEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCOREConcern_Models() {
        return (EReference)coreConcernEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCOREConcern_Interface() {
        return (EReference)coreConcernEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCOREConcern_FeatureModel() {
        return (EReference)coreConcernEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCOREConcern_ImpactModel() {
        return (EReference)coreConcernEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCOREFeature() {
        return coreFeatureEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCOREFeature_RealizedBy() {
        return (EReference)coreFeatureEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCOREFeature_Reuses() {
        return (EReference)coreFeatureEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCOREFeature_Children() {
        return (EReference)coreFeatureEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCOREFeature_Parent() {
        return (EReference)coreFeatureEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getCOREFeature_ParentRelationship() {
        return (EAttribute)coreFeatureEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCOREFeature_Requires() {
        return (EReference)coreFeatureEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCOREFeature_Excludes() {
        return (EReference)coreFeatureEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCOREBinding() {
        return coreBindingEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCOREBinding_Mappings() {
        return (EReference)coreBindingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCOREModelElement() {
        return coreModelElementEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getCOREModelElement_Partiality() {
        return (EAttribute)coreModelElementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getCOREModelElement_Visibility() {
        return (EAttribute)coreModelElementEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCORECompositionSpecification() {
        return coreCompositionSpecificationEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCORECompositionSpecification_Source() {
        return (EReference)coreCompositionSpecificationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCOREMapping() {
        return coreMappingEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCOREMapping_To() {
        return (EReference)coreMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCOREMapping_From() {
        return (EReference)coreMappingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCORENamedElement() {
        return coreNamedElementEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getCORENamedElement_Name() {
        return (EAttribute)coreNamedElementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCOREInterface() {
        return coreInterfaceEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCOREInterface_Selectable() {
        return (EReference)coreInterfaceEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCOREInterface_Customizable() {
        return (EReference)coreInterfaceEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCOREInterface_Usable() {
        return (EReference)coreInterfaceEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCOREInterface_Impacted() {
        return (EReference)coreInterfaceEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCOREInterface_Defaults() {
        return (EReference)coreInterfaceEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCOREReuse() {
        return coreReuseEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCOREReuse_ReusedConcern() {
        return (EReference)coreReuseEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCOREReuse_Configurations() {
        return (EReference)coreReuseEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCOREReuse_SelectedConfiguration() {
        return (EReference)coreReuseEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCOREPattern() {
        return corePatternEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCOREImpactNode() {
        return coreImpactNodeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getCOREImpactNode_ScalingFactor() {
        return (EAttribute)coreImpactNodeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getCOREImpactNode_Offset() {
        return (EAttribute)coreImpactNodeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCOREImpactNode_Outgoing() {
        return (EReference)coreImpactNodeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCOREImpactNode_Incoming() {
        return (EReference)coreImpactNodeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCOREConfiguration() {
        return coreConfigurationEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCOREConfiguration_Selected() {
        return (EReference)coreConfigurationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCOREFeatureModel() {
        return coreFeatureModelEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCOREFeatureModel_Features() {
        return (EReference)coreFeatureModelEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCOREFeatureModel_Root() {
        return (EReference)coreFeatureModelEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCOREFeatureModel_Configurations() {
        return (EReference)coreFeatureModelEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCOREFeatureModel_DefaultConfiguration() {
        return (EReference)coreFeatureModelEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCOREModelReuse() {
        return coreModelReuseEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCOREModelReuse_Compositions() {
        return (EReference)coreModelReuseEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCOREModelReuse_Reuse() {
        return (EReference)coreModelReuseEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCOREContribution() {
        return coreContributionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getCOREContribution_RelativeWeight() {
        return (EAttribute)coreContributionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCOREContribution_Source() {
        return (EReference)coreContributionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCOREContribution_Impacts() {
        return (EReference)coreContributionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getLayoutMap() {
        return layoutMapEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getLayoutMap_Key() {
        return (EReference)layoutMapEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getLayoutMap_Value() {
        return (EReference)layoutMapEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getLayoutElement() {
        return layoutElementEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLayoutElement_X() {
        return (EAttribute)layoutElementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLayoutElement_Y() {
        return (EAttribute)layoutElementEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getLayoutContainerMap() {
        return layoutContainerMapEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getLayoutContainerMap_Key() {
        return (EReference)layoutContainerMapEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getLayoutContainerMap_Value() {
        return (EReference)layoutContainerMapEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCOREFeatureImpactNode() {
        return coreFeatureImpactNodeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getCOREFeatureImpactNode_RelativeFeatureWeight() {
        return (EAttribute)coreFeatureImpactNodeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCOREFeatureImpactNode_Represents() {
        return (EReference)coreFeatureImpactNodeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCOREFeatureImpactNode_WeightedMappings() {
        return (EReference)coreFeatureImpactNodeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCOREModelCompositionSpecification() {
        return coreModelCompositionSpecificationEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCOREWeightedMapping() {
        return coreWeightedMappingEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getCOREWeightedMapping_Weight() {
        return (EAttribute)coreWeightedMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCOREImpactModelBinding() {
        return coreImpactModelBindingEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCOREConcernConfiguration() {
        return coreConcernConfigurationEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCOREReuseConfiguration() {
        return coreReuseConfigurationEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCOREReuseConfiguration_ReusedConfiguration() {
        return (EReference)coreReuseConfigurationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCOREReuseConfiguration_Reuse() {
        return (EReference)coreReuseConfigurationEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getCOREFeatureRelationshipType() {
        return coreFeatureRelationshipTypeEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getCOREVisibilityType() {
        return coreVisibilityTypeEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getCOREPartialityType() {
        return corePartialityTypeEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CoreFactory getCoreFactory() {
        return (CoreFactory)getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package.  This method is
     * guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void createPackageContents() {
        if (isCreated) return;
        isCreated = true;

        // Create classes and their features
        coreModelEClass = createEClass(CORE_MODEL);
        createEReference(coreModelEClass, CORE_MODEL__MODEL_REUSES);
        createEReference(coreModelEClass, CORE_MODEL__MODEL_ELEMENTS);
        createEReference(coreModelEClass, CORE_MODEL__REALIZES);
        createEReference(coreModelEClass, CORE_MODEL__CORE_CONCERN);

        coreImpactModelEClass = createEClass(CORE_IMPACT_MODEL);
        createEReference(coreImpactModelEClass, CORE_IMPACT_MODEL__IMPACT_MODEL_ELEMENTS);
        createEReference(coreImpactModelEClass, CORE_IMPACT_MODEL__LAYOUTS);
        createEReference(coreImpactModelEClass, CORE_IMPACT_MODEL__CONTRIBUTIONS);

        coreConcernEClass = createEClass(CORE_CONCERN);
        createEReference(coreConcernEClass, CORE_CONCERN__MODELS);
        createEReference(coreConcernEClass, CORE_CONCERN__INTERFACE);
        createEReference(coreConcernEClass, CORE_CONCERN__FEATURE_MODEL);
        createEReference(coreConcernEClass, CORE_CONCERN__IMPACT_MODEL);

        coreFeatureEClass = createEClass(CORE_FEATURE);
        createEReference(coreFeatureEClass, CORE_FEATURE__REALIZED_BY);
        createEReference(coreFeatureEClass, CORE_FEATURE__REUSES);
        createEReference(coreFeatureEClass, CORE_FEATURE__CHILDREN);
        createEReference(coreFeatureEClass, CORE_FEATURE__PARENT);
        createEAttribute(coreFeatureEClass, CORE_FEATURE__PARENT_RELATIONSHIP);
        createEReference(coreFeatureEClass, CORE_FEATURE__REQUIRES);
        createEReference(coreFeatureEClass, CORE_FEATURE__EXCLUDES);

        coreBindingEClass = createEClass(CORE_BINDING);
        createEReference(coreBindingEClass, CORE_BINDING__MAPPINGS);

        coreModelElementEClass = createEClass(CORE_MODEL_ELEMENT);
        createEAttribute(coreModelElementEClass, CORE_MODEL_ELEMENT__PARTIALITY);
        createEAttribute(coreModelElementEClass, CORE_MODEL_ELEMENT__VISIBILITY);

        coreCompositionSpecificationEClass = createEClass(CORE_COMPOSITION_SPECIFICATION);
        createEReference(coreCompositionSpecificationEClass, CORE_COMPOSITION_SPECIFICATION__SOURCE);

        coreMappingEClass = createEClass(CORE_MAPPING);
        createEReference(coreMappingEClass, CORE_MAPPING__TO);
        createEReference(coreMappingEClass, CORE_MAPPING__FROM);

        coreNamedElementEClass = createEClass(CORE_NAMED_ELEMENT);
        createEAttribute(coreNamedElementEClass, CORE_NAMED_ELEMENT__NAME);

        coreInterfaceEClass = createEClass(CORE_INTERFACE);
        createEReference(coreInterfaceEClass, CORE_INTERFACE__SELECTABLE);
        createEReference(coreInterfaceEClass, CORE_INTERFACE__CUSTOMIZABLE);
        createEReference(coreInterfaceEClass, CORE_INTERFACE__USABLE);
        createEReference(coreInterfaceEClass, CORE_INTERFACE__IMPACTED);
        createEReference(coreInterfaceEClass, CORE_INTERFACE__DEFAULTS);

        coreReuseEClass = createEClass(CORE_REUSE);
        createEReference(coreReuseEClass, CORE_REUSE__REUSED_CONCERN);
        createEReference(coreReuseEClass, CORE_REUSE__CONFIGURATIONS);
        createEReference(coreReuseEClass, CORE_REUSE__SELECTED_CONFIGURATION);

        corePatternEClass = createEClass(CORE_PATTERN);

        coreImpactNodeEClass = createEClass(CORE_IMPACT_NODE);
        createEAttribute(coreImpactNodeEClass, CORE_IMPACT_NODE__SCALING_FACTOR);
        createEAttribute(coreImpactNodeEClass, CORE_IMPACT_NODE__OFFSET);
        createEReference(coreImpactNodeEClass, CORE_IMPACT_NODE__OUTGOING);
        createEReference(coreImpactNodeEClass, CORE_IMPACT_NODE__INCOMING);

        coreConfigurationEClass = createEClass(CORE_CONFIGURATION);
        createEReference(coreConfigurationEClass, CORE_CONFIGURATION__SELECTED);

        coreFeatureModelEClass = createEClass(CORE_FEATURE_MODEL);
        createEReference(coreFeatureModelEClass, CORE_FEATURE_MODEL__FEATURES);
        createEReference(coreFeatureModelEClass, CORE_FEATURE_MODEL__ROOT);
        createEReference(coreFeatureModelEClass, CORE_FEATURE_MODEL__CONFIGURATIONS);
        createEReference(coreFeatureModelEClass, CORE_FEATURE_MODEL__DEFAULT_CONFIGURATION);

        coreModelReuseEClass = createEClass(CORE_MODEL_REUSE);
        createEReference(coreModelReuseEClass, CORE_MODEL_REUSE__COMPOSITIONS);
        createEReference(coreModelReuseEClass, CORE_MODEL_REUSE__REUSE);

        coreContributionEClass = createEClass(CORE_CONTRIBUTION);
        createEAttribute(coreContributionEClass, CORE_CONTRIBUTION__RELATIVE_WEIGHT);
        createEReference(coreContributionEClass, CORE_CONTRIBUTION__SOURCE);
        createEReference(coreContributionEClass, CORE_CONTRIBUTION__IMPACTS);

        layoutMapEClass = createEClass(LAYOUT_MAP);
        createEReference(layoutMapEClass, LAYOUT_MAP__KEY);
        createEReference(layoutMapEClass, LAYOUT_MAP__VALUE);

        layoutElementEClass = createEClass(LAYOUT_ELEMENT);
        createEAttribute(layoutElementEClass, LAYOUT_ELEMENT__X);
        createEAttribute(layoutElementEClass, LAYOUT_ELEMENT__Y);

        layoutContainerMapEClass = createEClass(LAYOUT_CONTAINER_MAP);
        createEReference(layoutContainerMapEClass, LAYOUT_CONTAINER_MAP__KEY);
        createEReference(layoutContainerMapEClass, LAYOUT_CONTAINER_MAP__VALUE);

        coreFeatureImpactNodeEClass = createEClass(CORE_FEATURE_IMPACT_NODE);
        createEAttribute(coreFeatureImpactNodeEClass, CORE_FEATURE_IMPACT_NODE__RELATIVE_FEATURE_WEIGHT);
        createEReference(coreFeatureImpactNodeEClass, CORE_FEATURE_IMPACT_NODE__REPRESENTS);
        createEReference(coreFeatureImpactNodeEClass, CORE_FEATURE_IMPACT_NODE__WEIGHTED_MAPPINGS);

        coreModelCompositionSpecificationEClass = createEClass(CORE_MODEL_COMPOSITION_SPECIFICATION);

        coreWeightedMappingEClass = createEClass(CORE_WEIGHTED_MAPPING);
        createEAttribute(coreWeightedMappingEClass, CORE_WEIGHTED_MAPPING__WEIGHT);

        coreImpactModelBindingEClass = createEClass(CORE_IMPACT_MODEL_BINDING);

        coreConcernConfigurationEClass = createEClass(CORE_CONCERN_CONFIGURATION);

        coreReuseConfigurationEClass = createEClass(CORE_REUSE_CONFIGURATION);
        createEReference(coreReuseConfigurationEClass, CORE_REUSE_CONFIGURATION__REUSED_CONFIGURATION);
        createEReference(coreReuseConfigurationEClass, CORE_REUSE_CONFIGURATION__REUSE);

        // Create enums
        coreFeatureRelationshipTypeEEnum = createEEnum(CORE_FEATURE_RELATIONSHIP_TYPE);
        coreVisibilityTypeEEnum = createEEnum(CORE_VISIBILITY_TYPE);
        corePartialityTypeEEnum = createEEnum(CORE_PARTIALITY_TYPE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model.  This
     * method is guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void initializePackageContents() {
        if (isInitialized) return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Create type parameters
        ETypeParameter coreBindingEClass_S = addETypeParameter(coreBindingEClass, "S");
        ETypeParameter coreBindingEClass_M = addETypeParameter(coreBindingEClass, "M");
        ETypeParameter coreCompositionSpecificationEClass_S = addETypeParameter(coreCompositionSpecificationEClass, "S");
        ETypeParameter coreMappingEClass_T = addETypeParameter(coreMappingEClass, "T");
        ETypeParameter corePatternEClass_S = addETypeParameter(corePatternEClass, "S");
        ETypeParameter coreModelCompositionSpecificationEClass_S = addETypeParameter(coreModelCompositionSpecificationEClass, "S");

        // Set bounds for type parameters
        EGenericType g1 = createEGenericType(this.getCOREModel());
        coreBindingEClass_S.getEBounds().add(g1);
        g1 = createEGenericType(this.getCOREMapping());
        EGenericType g2 = createEGenericType();
        g1.getETypeArguments().add(g2);
        coreBindingEClass_M.getEBounds().add(g1);
        g1 = createEGenericType(this.getCOREModel());
        coreCompositionSpecificationEClass_S.getEBounds().add(g1);
        g1 = createEGenericType(this.getCOREModelElement());
        coreMappingEClass_T.getEBounds().add(g1);
        g1 = createEGenericType(this.getCOREModel());
        corePatternEClass_S.getEBounds().add(g1);
        g1 = createEGenericType(this.getCOREModel());
        coreModelCompositionSpecificationEClass_S.getEBounds().add(g1);

        // Add supertypes to classes
        coreModelEClass.getESuperTypes().add(this.getCORENamedElement());
        coreImpactModelEClass.getESuperTypes().add(this.getCOREModel());
        coreConcernEClass.getESuperTypes().add(this.getCORENamedElement());
        coreFeatureEClass.getESuperTypes().add(this.getCOREModelElement());
        g1 = createEGenericType(this.getCOREModelCompositionSpecification());
        g2 = createEGenericType(coreBindingEClass_S);
        g1.getETypeArguments().add(g2);
        coreBindingEClass.getEGenericSuperTypes().add(g1);
        coreModelElementEClass.getESuperTypes().add(this.getCORENamedElement());
        coreReuseEClass.getESuperTypes().add(this.getCORENamedElement());
        g1 = createEGenericType(this.getCOREModelCompositionSpecification());
        g2 = createEGenericType(corePatternEClass_S);
        g1.getETypeArguments().add(g2);
        corePatternEClass.getEGenericSuperTypes().add(g1);
        coreImpactNodeEClass.getESuperTypes().add(this.getCOREModelElement());
        g1 = createEGenericType(this.getCORENamedElement());
        coreConfigurationEClass.getEGenericSuperTypes().add(g1);
        g1 = createEGenericType(this.getCORECompositionSpecification());
        g2 = createEGenericType(this.getCOREFeatureModel());
        g1.getETypeArguments().add(g2);
        coreConfigurationEClass.getEGenericSuperTypes().add(g1);
        coreFeatureModelEClass.getESuperTypes().add(this.getCOREModel());
        coreFeatureImpactNodeEClass.getESuperTypes().add(this.getCOREImpactNode());
        g1 = createEGenericType(this.getCORECompositionSpecification());
        g2 = createEGenericType(coreModelCompositionSpecificationEClass_S);
        g1.getETypeArguments().add(g2);
        coreModelCompositionSpecificationEClass.getEGenericSuperTypes().add(g1);
        g1 = createEGenericType(this.getCOREMapping());
        g2 = createEGenericType(this.getCOREImpactNode());
        g1.getETypeArguments().add(g2);
        coreWeightedMappingEClass.getEGenericSuperTypes().add(g1);
        g1 = createEGenericType(this.getCOREBinding());
        g2 = createEGenericType(this.getCOREImpactModel());
        g1.getETypeArguments().add(g2);
        g2 = createEGenericType(this.getCOREWeightedMapping());
        g1.getETypeArguments().add(g2);
        coreImpactModelBindingEClass.getEGenericSuperTypes().add(g1);
        coreConcernConfigurationEClass.getESuperTypes().add(this.getCOREConfiguration());
        coreReuseConfigurationEClass.getESuperTypes().add(this.getCOREConfiguration());

        // Initialize classes and features; add operations and parameters
        initEClass(coreModelEClass, COREModel.class, "COREModel", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getCOREModel_ModelReuses(), this.getCOREModelReuse(), null, "modelReuses", null, 0, -1, COREModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getCOREModel_ModelElements(), this.getCOREModelElement(), null, "modelElements", null, 0, -1, COREModel.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getCOREModel_Realizes(), this.getCOREFeature(), this.getCOREFeature_RealizedBy(), "realizes", null, 0, -1, COREModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getCOREModel_CoreConcern(), this.getCOREConcern(), this.getCOREConcern_Models(), "coreConcern", null, 1, 1, COREModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(coreImpactModelEClass, COREImpactModel.class, "COREImpactModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getCOREImpactModel_ImpactModelElements(), this.getCOREImpactNode(), null, "impactModelElements", null, 0, -1, COREImpactModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getCOREImpactModel_Layouts(), this.getLayoutContainerMap(), null, "layouts", null, 0, -1, COREImpactModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getCOREImpactModel_Contributions(), this.getCOREContribution(), null, "contributions", null, 0, -1, COREImpactModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(coreConcernEClass, COREConcern.class, "COREConcern", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getCOREConcern_Models(), this.getCOREModel(), this.getCOREModel_CoreConcern(), "models", null, 1, -1, COREConcern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getCOREConcern_Interface(), this.getCOREInterface(), null, "interface", null, 1, 1, COREConcern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getCOREConcern_FeatureModel(), this.getCOREFeatureModel(), null, "featureModel", null, 1, 1, COREConcern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getCOREConcern_ImpactModel(), this.getCOREImpactModel(), null, "impactModel", null, 0, 1, COREConcern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(coreFeatureEClass, COREFeature.class, "COREFeature", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getCOREFeature_RealizedBy(), this.getCOREModel(), this.getCOREModel_Realizes(), "realizedBy", null, 0, -1, COREFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getCOREFeature_Reuses(), this.getCOREReuse(), null, "reuses", null, 0, -1, COREFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getCOREFeature_Children(), this.getCOREFeature(), this.getCOREFeature_Parent(), "children", null, 0, -1, COREFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getCOREFeature_Parent(), this.getCOREFeature(), this.getCOREFeature_Children(), "parent", null, 0, 1, COREFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getCOREFeature_ParentRelationship(), this.getCOREFeatureRelationshipType(), "parentRelationship", "None", 1, 1, COREFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getCOREFeature_Requires(), this.getCOREFeature(), null, "requires", null, 0, -1, COREFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getCOREFeature_Excludes(), this.getCOREFeature(), null, "excludes", null, 0, -1, COREFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(coreBindingEClass, COREBinding.class, "COREBinding", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        g1 = createEGenericType(coreBindingEClass_M);
        initEReference(getCOREBinding_Mappings(), g1, null, "mappings", null, 0, -1, COREBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(coreModelElementEClass, COREModelElement.class, "COREModelElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getCOREModelElement_Partiality(), this.getCOREPartialityType(), "partiality", "none", 0, 1, COREModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getCOREModelElement_Visibility(), this.getCOREVisibilityType(), "visibility", "concern", 0, 1, COREModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(coreCompositionSpecificationEClass, CORECompositionSpecification.class, "CORECompositionSpecification", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        g1 = createEGenericType(coreCompositionSpecificationEClass_S);
        initEReference(getCORECompositionSpecification_Source(), g1, null, "source", null, 1, 1, CORECompositionSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(coreMappingEClass, COREMapping.class, "COREMapping", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        g1 = createEGenericType(coreMappingEClass_T);
        initEReference(getCOREMapping_To(), g1, null, "to", null, 1, 1, COREMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        g1 = createEGenericType(coreMappingEClass_T);
        initEReference(getCOREMapping_From(), g1, null, "from", null, 1, 1, COREMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(coreNamedElementEClass, CORENamedElement.class, "CORENamedElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getCORENamedElement_Name(), ecorePackage.getEString(), "name", null, 1, 1, CORENamedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(coreInterfaceEClass, COREInterface.class, "COREInterface", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getCOREInterface_Selectable(), this.getCOREFeature(), null, "selectable", null, 0, -1, COREInterface.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getCOREInterface_Customizable(), this.getCOREModelElement(), null, "customizable", null, 0, -1, COREInterface.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getCOREInterface_Usable(), this.getCOREModelElement(), null, "usable", null, 0, -1, COREInterface.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getCOREInterface_Impacted(), this.getCOREImpactNode(), null, "impacted", null, 0, -1, COREInterface.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getCOREInterface_Defaults(), this.getCOREConfiguration(), null, "defaults", null, 0, -1, COREInterface.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(coreReuseEClass, COREReuse.class, "COREReuse", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getCOREReuse_ReusedConcern(), this.getCOREConcern(), null, "reusedConcern", null, 1, 1, COREReuse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getCOREReuse_Configurations(), this.getCOREReuseConfiguration(), null, "configurations", null, 0, -1, COREReuse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getCOREReuse_SelectedConfiguration(), this.getCOREReuseConfiguration(), null, "selectedConfiguration", null, 0, 1, COREReuse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(corePatternEClass, COREPattern.class, "COREPattern", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(coreImpactNodeEClass, COREImpactNode.class, "COREImpactNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getCOREImpactNode_ScalingFactor(), ecorePackage.getEFloat(), "scalingFactor", null, 1, 1, COREImpactNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getCOREImpactNode_Offset(), ecorePackage.getEFloat(), "offset", null, 1, 1, COREImpactNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getCOREImpactNode_Outgoing(), this.getCOREContribution(), this.getCOREContribution_Source(), "outgoing", null, 0, -1, COREImpactNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getCOREImpactNode_Incoming(), this.getCOREContribution(), this.getCOREContribution_Impacts(), "incoming", null, 0, -1, COREImpactNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(coreConfigurationEClass, COREConfiguration.class, "COREConfiguration", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getCOREConfiguration_Selected(), this.getCOREFeature(), null, "selected", null, 0, -1, COREConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(coreFeatureModelEClass, COREFeatureModel.class, "COREFeatureModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getCOREFeatureModel_Features(), this.getCOREFeature(), null, "features", null, 0, -1, COREFeatureModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getCOREFeatureModel_Root(), this.getCOREFeature(), null, "root", null, 1, 1, COREFeatureModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getCOREFeatureModel_Configurations(), this.getCOREConcernConfiguration(), null, "configurations", null, 0, -1, COREFeatureModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getCOREFeatureModel_DefaultConfiguration(), this.getCOREConcernConfiguration(), null, "defaultConfiguration", null, 0, 1, COREFeatureModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(coreModelReuseEClass, COREModelReuse.class, "COREModelReuse", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        g1 = createEGenericType(this.getCOREModelCompositionSpecification());
        g2 = createEGenericType();
        g1.getETypeArguments().add(g2);
        initEReference(getCOREModelReuse_Compositions(), g1, null, "compositions", null, 0, -1, COREModelReuse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getCOREModelReuse_Reuse(), this.getCOREReuse(), null, "reuse", null, 1, 1, COREModelReuse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(coreContributionEClass, COREContribution.class, "COREContribution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getCOREContribution_RelativeWeight(), ecorePackage.getEInt(), "relativeWeight", null, 1, 1, COREContribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getCOREContribution_Source(), this.getCOREImpactNode(), this.getCOREImpactNode_Outgoing(), "source", null, 1, 1, COREContribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getCOREContribution_Impacts(), this.getCOREImpactNode(), this.getCOREImpactNode_Incoming(), "impacts", null, 1, 1, COREContribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(layoutMapEClass, Map.Entry.class, "LayoutMap", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
        initEReference(getLayoutMap_Key(), ecorePackage.getEObject(), null, "key", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getLayoutMap_Value(), this.getLayoutElement(), null, "value", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(layoutElementEClass, LayoutElement.class, "LayoutElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getLayoutElement_X(), ecorePackage.getEFloat(), "x", "0.0", 0, 1, LayoutElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLayoutElement_Y(), ecorePackage.getEFloat(), "y", "0.0", 0, 1, LayoutElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(layoutContainerMapEClass, Map.Entry.class, "LayoutContainerMap", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
        initEReference(getLayoutContainerMap_Key(), ecorePackage.getEObject(), null, "key", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getLayoutContainerMap_Value(), this.getLayoutMap(), null, "value", null, 1, -1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(coreFeatureImpactNodeEClass, COREFeatureImpactNode.class, "COREFeatureImpactNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getCOREFeatureImpactNode_RelativeFeatureWeight(), ecorePackage.getEInt(), "relativeFeatureWeight", null, 1, 1, COREFeatureImpactNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getCOREFeatureImpactNode_Represents(), this.getCOREFeature(), null, "represents", null, 1, 1, COREFeatureImpactNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getCOREFeatureImpactNode_WeightedMappings(), this.getCOREWeightedMapping(), null, "weightedMappings", null, 0, -1, COREFeatureImpactNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(coreModelCompositionSpecificationEClass, COREModelCompositionSpecification.class, "COREModelCompositionSpecification", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(coreWeightedMappingEClass, COREWeightedMapping.class, "COREWeightedMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getCOREWeightedMapping_Weight(), ecorePackage.getEInt(), "weight", null, 1, 1, COREWeightedMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(coreImpactModelBindingEClass, COREImpactModelBinding.class, "COREImpactModelBinding", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(coreConcernConfigurationEClass, COREConcernConfiguration.class, "COREConcernConfiguration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(coreReuseConfigurationEClass, COREReuseConfiguration.class, "COREReuseConfiguration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getCOREReuseConfiguration_ReusedConfiguration(), this.getCOREConcernConfiguration(), null, "reusedConfiguration", null, 0, 1, COREReuseConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getCOREReuseConfiguration_Reuse(), this.getCOREReuse(), null, "reuse", null, 0, 1, COREReuseConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(coreFeatureRelationshipTypeEEnum, COREFeatureRelationshipType.class, "COREFeatureRelationshipType");
        addEEnumLiteral(coreFeatureRelationshipTypeEEnum, COREFeatureRelationshipType.NONE);
        addEEnumLiteral(coreFeatureRelationshipTypeEEnum, COREFeatureRelationshipType.OPTIONAL);
        addEEnumLiteral(coreFeatureRelationshipTypeEEnum, COREFeatureRelationshipType.MANDATORY);
        addEEnumLiteral(coreFeatureRelationshipTypeEEnum, COREFeatureRelationshipType.XOR);
        addEEnumLiteral(coreFeatureRelationshipTypeEEnum, COREFeatureRelationshipType.OR);

        initEEnum(coreVisibilityTypeEEnum, COREVisibilityType.class, "COREVisibilityType");
        addEEnumLiteral(coreVisibilityTypeEEnum, COREVisibilityType.PUBLIC);
        addEEnumLiteral(coreVisibilityTypeEEnum, COREVisibilityType.CONCERN);

        initEEnum(corePartialityTypeEEnum, COREPartialityType.class, "COREPartialityType");
        addEEnumLiteral(corePartialityTypeEEnum, COREPartialityType.NONE);
        addEEnumLiteral(corePartialityTypeEEnum, COREPartialityType.PUBLIC);
        addEEnumLiteral(corePartialityTypeEEnum, COREPartialityType.CONCERN);

        // Create resource
        createResource(eNS_URI);

        // Create annotations
        // http:///org/eclipse/emf/ecore/util/ExtendedMetaData
        createExtendedMetaDataAnnotations();
    }

    /**
     * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void createExtendedMetaDataAnnotations() {
        String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";	
        addAnnotation
          (coreModelEClass, 
           source, 
           new String[] {
             "name", "COREFeatureModel"
           });
    }

} //CorePackageImpl
