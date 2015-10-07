/**
 */
package ca.mcgill.sel.core;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see ca.mcgill.sel.core.CoreFactory
 * @model kind="package"
 * @generated
 */
public interface CorePackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "core";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://cs.mcgill.ca/sel/core/2.0";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "core";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    CorePackage eINSTANCE = ca.mcgill.sel.core.impl.CorePackageImpl.init();

    /**
     * The meta object id for the '{@link ca.mcgill.sel.core.impl.CORENamedElementImpl <em>CORE Named Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.core.impl.CORENamedElementImpl
     * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCORENamedElement()
     * @generated
     */
    int CORE_NAMED_ELEMENT = 8;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_NAMED_ELEMENT__NAME = 0;

    /**
     * The number of structural features of the '<em>CORE Named Element</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_NAMED_ELEMENT_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.core.impl.COREModelImpl <em>CORE Model</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.core.impl.COREModelImpl
     * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCOREModel()
     * @generated
     */
    int CORE_MODEL = 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_MODEL__NAME = CORE_NAMED_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Model Reuses</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_MODEL__MODEL_REUSES = CORE_NAMED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Model Elements</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_MODEL__MODEL_ELEMENTS = CORE_NAMED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Realizes</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_MODEL__REALIZES = CORE_NAMED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Core Concern</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_MODEL__CORE_CONCERN = CORE_NAMED_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>CORE Model</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_MODEL_FEATURE_COUNT = CORE_NAMED_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.core.impl.COREImpactModelImpl <em>CORE Impact Model</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.core.impl.COREImpactModelImpl
     * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCOREImpactModel()
     * @generated
     */
    int CORE_IMPACT_MODEL = 1;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_IMPACT_MODEL__NAME = CORE_MODEL__NAME;

    /**
     * The feature id for the '<em><b>Model Reuses</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_IMPACT_MODEL__MODEL_REUSES = CORE_MODEL__MODEL_REUSES;

    /**
     * The feature id for the '<em><b>Model Elements</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_IMPACT_MODEL__MODEL_ELEMENTS = CORE_MODEL__MODEL_ELEMENTS;

    /**
     * The feature id for the '<em><b>Realizes</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_IMPACT_MODEL__REALIZES = CORE_MODEL__REALIZES;

    /**
     * The feature id for the '<em><b>Core Concern</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_IMPACT_MODEL__CORE_CONCERN = CORE_MODEL__CORE_CONCERN;

    /**
     * The feature id for the '<em><b>Impact Model Elements</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_IMPACT_MODEL__IMPACT_MODEL_ELEMENTS = CORE_MODEL_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Layouts</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_IMPACT_MODEL__LAYOUTS = CORE_MODEL_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Contributions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_IMPACT_MODEL__CONTRIBUTIONS = CORE_MODEL_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>CORE Impact Model</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_IMPACT_MODEL_FEATURE_COUNT = CORE_MODEL_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.core.impl.COREConcernImpl <em>CORE Concern</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.core.impl.COREConcernImpl
     * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCOREConcern()
     * @generated
     */
    int CORE_CONCERN = 2;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_CONCERN__NAME = CORE_NAMED_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Models</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_CONCERN__MODELS = CORE_NAMED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Interface</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_CONCERN__INTERFACE = CORE_NAMED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Feature Model</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_CONCERN__FEATURE_MODEL = CORE_NAMED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Impact Model</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_CONCERN__IMPACT_MODEL = CORE_NAMED_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>CORE Concern</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_CONCERN_FEATURE_COUNT = CORE_NAMED_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.core.impl.COREModelElementImpl <em>CORE Model Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.core.impl.COREModelElementImpl
     * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCOREModelElement()
     * @generated
     */
    int CORE_MODEL_ELEMENT = 5;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_MODEL_ELEMENT__NAME = CORE_NAMED_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Partiality</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_MODEL_ELEMENT__PARTIALITY = CORE_NAMED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Visibility</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_MODEL_ELEMENT__VISIBILITY = CORE_NAMED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>CORE Model Element</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_MODEL_ELEMENT_FEATURE_COUNT = CORE_NAMED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.core.impl.COREFeatureImpl <em>CORE Feature</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.core.impl.COREFeatureImpl
     * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCOREFeature()
     * @generated
     */
    int CORE_FEATURE = 3;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_FEATURE__NAME = CORE_MODEL_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Partiality</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_FEATURE__PARTIALITY = CORE_MODEL_ELEMENT__PARTIALITY;

    /**
     * The feature id for the '<em><b>Visibility</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_FEATURE__VISIBILITY = CORE_MODEL_ELEMENT__VISIBILITY;

    /**
     * The feature id for the '<em><b>Realized By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_FEATURE__REALIZED_BY = CORE_MODEL_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Reuses</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_FEATURE__REUSES = CORE_MODEL_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Children</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_FEATURE__CHILDREN = CORE_MODEL_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Parent</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_FEATURE__PARENT = CORE_MODEL_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Parent Relationship</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_FEATURE__PARENT_RELATIONSHIP = CORE_MODEL_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Requires</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_FEATURE__REQUIRES = CORE_MODEL_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Excludes</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_FEATURE__EXCLUDES = CORE_MODEL_ELEMENT_FEATURE_COUNT + 6;

    /**
     * The number of structural features of the '<em>CORE Feature</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_FEATURE_FEATURE_COUNT = CORE_MODEL_ELEMENT_FEATURE_COUNT + 7;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.core.impl.CORECompositionSpecificationImpl <em>CORE Composition Specification</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.core.impl.CORECompositionSpecificationImpl
     * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCORECompositionSpecification()
     * @generated
     */
    int CORE_COMPOSITION_SPECIFICATION = 6;

    /**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_COMPOSITION_SPECIFICATION__SOURCE = 0;

    /**
     * The number of structural features of the '<em>CORE Composition Specification</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_COMPOSITION_SPECIFICATION_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.core.impl.COREModelCompositionSpecificationImpl <em>CORE Model Composition Specification</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.core.impl.COREModelCompositionSpecificationImpl
     * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCOREModelCompositionSpecification()
     * @generated
     */
    int CORE_MODEL_COMPOSITION_SPECIFICATION = 21;

    /**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_MODEL_COMPOSITION_SPECIFICATION__SOURCE = CORE_COMPOSITION_SPECIFICATION__SOURCE;

    /**
     * The number of structural features of the '<em>CORE Model Composition Specification</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_MODEL_COMPOSITION_SPECIFICATION_FEATURE_COUNT = CORE_COMPOSITION_SPECIFICATION_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.core.impl.COREBindingImpl <em>CORE Binding</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.core.impl.COREBindingImpl
     * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCOREBinding()
     * @generated
     */
    int CORE_BINDING = 4;

    /**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_BINDING__SOURCE = CORE_MODEL_COMPOSITION_SPECIFICATION__SOURCE;

    /**
     * The feature id for the '<em><b>Mappings</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_BINDING__MAPPINGS = CORE_MODEL_COMPOSITION_SPECIFICATION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>CORE Binding</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_BINDING_FEATURE_COUNT = CORE_MODEL_COMPOSITION_SPECIFICATION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.core.impl.COREMappingImpl <em>CORE Mapping</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.core.impl.COREMappingImpl
     * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCOREMapping()
     * @generated
     */
    int CORE_MAPPING = 7;

    /**
     * The feature id for the '<em><b>To</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_MAPPING__TO = 0;

    /**
     * The feature id for the '<em><b>From</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_MAPPING__FROM = 1;

    /**
     * The number of structural features of the '<em>CORE Mapping</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_MAPPING_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.core.impl.COREInterfaceImpl <em>CORE Interface</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.core.impl.COREInterfaceImpl
     * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCOREInterface()
     * @generated
     */
    int CORE_INTERFACE = 9;

    /**
     * The feature id for the '<em><b>Selectable</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_INTERFACE__SELECTABLE = 0;

    /**
     * The feature id for the '<em><b>Customizable</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_INTERFACE__CUSTOMIZABLE = 1;

    /**
     * The feature id for the '<em><b>Usable</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_INTERFACE__USABLE = 2;

    /**
     * The feature id for the '<em><b>Impacted</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_INTERFACE__IMPACTED = 3;

    /**
     * The feature id for the '<em><b>Defaults</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_INTERFACE__DEFAULTS = 4;

    /**
     * The number of structural features of the '<em>CORE Interface</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_INTERFACE_FEATURE_COUNT = 5;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.core.impl.COREReuseImpl <em>CORE Reuse</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.core.impl.COREReuseImpl
     * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCOREReuse()
     * @generated
     */
    int CORE_REUSE = 10;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_REUSE__NAME = CORE_NAMED_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Reused Concern</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_REUSE__REUSED_CONCERN = CORE_NAMED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Configurations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_REUSE__CONFIGURATIONS = CORE_NAMED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Selected Configuration</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_REUSE__SELECTED_CONFIGURATION = CORE_NAMED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>CORE Reuse</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_REUSE_FEATURE_COUNT = CORE_NAMED_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.core.impl.COREPatternImpl <em>CORE Pattern</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.core.impl.COREPatternImpl
     * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCOREPattern()
     * @generated
     */
    int CORE_PATTERN = 11;

    /**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_PATTERN__SOURCE = CORE_MODEL_COMPOSITION_SPECIFICATION__SOURCE;

    /**
     * The number of structural features of the '<em>CORE Pattern</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_PATTERN_FEATURE_COUNT = CORE_MODEL_COMPOSITION_SPECIFICATION_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.core.impl.COREImpactNodeImpl <em>CORE Impact Node</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.core.impl.COREImpactNodeImpl
     * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCOREImpactNode()
     * @generated
     */
    int CORE_IMPACT_NODE = 12;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_IMPACT_NODE__NAME = CORE_MODEL_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Partiality</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_IMPACT_NODE__PARTIALITY = CORE_MODEL_ELEMENT__PARTIALITY;

    /**
     * The feature id for the '<em><b>Visibility</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_IMPACT_NODE__VISIBILITY = CORE_MODEL_ELEMENT__VISIBILITY;

    /**
     * The feature id for the '<em><b>Scaling Factor</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_IMPACT_NODE__SCALING_FACTOR = CORE_MODEL_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Offset</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_IMPACT_NODE__OFFSET = CORE_MODEL_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_IMPACT_NODE__OUTGOING = CORE_MODEL_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Incoming</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_IMPACT_NODE__INCOMING = CORE_MODEL_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>CORE Impact Node</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_IMPACT_NODE_FEATURE_COUNT = CORE_MODEL_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.core.impl.COREConfigurationImpl <em>CORE Configuration</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.core.impl.COREConfigurationImpl
     * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCOREConfiguration()
     * @generated
     */
    int CORE_CONFIGURATION = 13;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_CONFIGURATION__NAME = CORE_NAMED_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_CONFIGURATION__SOURCE = CORE_NAMED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Selected</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_CONFIGURATION__SELECTED = CORE_NAMED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>CORE Configuration</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_CONFIGURATION_FEATURE_COUNT = CORE_NAMED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.core.impl.COREFeatureModelImpl <em>CORE Feature Model</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.core.impl.COREFeatureModelImpl
     * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCOREFeatureModel()
     * @generated
     */
    int CORE_FEATURE_MODEL = 14;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_FEATURE_MODEL__NAME = CORE_MODEL__NAME;

    /**
     * The feature id for the '<em><b>Model Reuses</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_FEATURE_MODEL__MODEL_REUSES = CORE_MODEL__MODEL_REUSES;

    /**
     * The feature id for the '<em><b>Model Elements</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_FEATURE_MODEL__MODEL_ELEMENTS = CORE_MODEL__MODEL_ELEMENTS;

    /**
     * The feature id for the '<em><b>Realizes</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_FEATURE_MODEL__REALIZES = CORE_MODEL__REALIZES;

    /**
     * The feature id for the '<em><b>Core Concern</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_FEATURE_MODEL__CORE_CONCERN = CORE_MODEL__CORE_CONCERN;

    /**
     * The feature id for the '<em><b>Features</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_FEATURE_MODEL__FEATURES = CORE_MODEL_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Root</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_FEATURE_MODEL__ROOT = CORE_MODEL_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Configurations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_FEATURE_MODEL__CONFIGURATIONS = CORE_MODEL_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Default Configuration</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_FEATURE_MODEL__DEFAULT_CONFIGURATION = CORE_MODEL_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>CORE Feature Model</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_FEATURE_MODEL_FEATURE_COUNT = CORE_MODEL_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.core.impl.COREModelReuseImpl <em>CORE Model Reuse</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.core.impl.COREModelReuseImpl
     * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCOREModelReuse()
     * @generated
     */
    int CORE_MODEL_REUSE = 15;

    /**
     * The feature id for the '<em><b>Compositions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_MODEL_REUSE__COMPOSITIONS = 0;

    /**
     * The feature id for the '<em><b>Reuse</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_MODEL_REUSE__REUSE = 1;

    /**
     * The number of structural features of the '<em>CORE Model Reuse</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_MODEL_REUSE_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.core.impl.COREContributionImpl <em>CORE Contribution</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.core.impl.COREContributionImpl
     * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCOREContribution()
     * @generated
     */
    int CORE_CONTRIBUTION = 16;

    /**
     * The feature id for the '<em><b>Relative Weight</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_CONTRIBUTION__RELATIVE_WEIGHT = 0;

    /**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_CONTRIBUTION__SOURCE = 1;

    /**
     * The feature id for the '<em><b>Impacts</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_CONTRIBUTION__IMPACTS = 2;

    /**
     * The number of structural features of the '<em>CORE Contribution</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_CONTRIBUTION_FEATURE_COUNT = 3;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.core.impl.LayoutMapImpl <em>Layout Map</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.core.impl.LayoutMapImpl
     * @see ca.mcgill.sel.core.impl.CorePackageImpl#getLayoutMap()
     * @generated
     */
    int LAYOUT_MAP = 17;

    /**
     * The feature id for the '<em><b>Key</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LAYOUT_MAP__KEY = 0;

    /**
     * The feature id for the '<em><b>Value</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LAYOUT_MAP__VALUE = 1;

    /**
     * The number of structural features of the '<em>Layout Map</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LAYOUT_MAP_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.core.impl.LayoutElementImpl <em>Layout Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.core.impl.LayoutElementImpl
     * @see ca.mcgill.sel.core.impl.CorePackageImpl#getLayoutElement()
     * @generated
     */
    int LAYOUT_ELEMENT = 18;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LAYOUT_ELEMENT__X = 0;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LAYOUT_ELEMENT__Y = 1;

    /**
     * The number of structural features of the '<em>Layout Element</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LAYOUT_ELEMENT_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.core.impl.LayoutContainerMapImpl <em>Layout Container Map</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.core.impl.LayoutContainerMapImpl
     * @see ca.mcgill.sel.core.impl.CorePackageImpl#getLayoutContainerMap()
     * @generated
     */
    int LAYOUT_CONTAINER_MAP = 19;

    /**
     * The feature id for the '<em><b>Key</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LAYOUT_CONTAINER_MAP__KEY = 0;

    /**
     * The feature id for the '<em><b>Value</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LAYOUT_CONTAINER_MAP__VALUE = 1;

    /**
     * The number of structural features of the '<em>Layout Container Map</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LAYOUT_CONTAINER_MAP_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.core.impl.COREFeatureImpactNodeImpl <em>CORE Feature Impact Node</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.core.impl.COREFeatureImpactNodeImpl
     * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCOREFeatureImpactNode()
     * @generated
     */
    int CORE_FEATURE_IMPACT_NODE = 20;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_FEATURE_IMPACT_NODE__NAME = CORE_IMPACT_NODE__NAME;

    /**
     * The feature id for the '<em><b>Partiality</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_FEATURE_IMPACT_NODE__PARTIALITY = CORE_IMPACT_NODE__PARTIALITY;

    /**
     * The feature id for the '<em><b>Visibility</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_FEATURE_IMPACT_NODE__VISIBILITY = CORE_IMPACT_NODE__VISIBILITY;

    /**
     * The feature id for the '<em><b>Scaling Factor</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_FEATURE_IMPACT_NODE__SCALING_FACTOR = CORE_IMPACT_NODE__SCALING_FACTOR;

    /**
     * The feature id for the '<em><b>Offset</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_FEATURE_IMPACT_NODE__OFFSET = CORE_IMPACT_NODE__OFFSET;

    /**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_FEATURE_IMPACT_NODE__OUTGOING = CORE_IMPACT_NODE__OUTGOING;

    /**
     * The feature id for the '<em><b>Incoming</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_FEATURE_IMPACT_NODE__INCOMING = CORE_IMPACT_NODE__INCOMING;

    /**
     * The feature id for the '<em><b>Relative Feature Weight</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_FEATURE_IMPACT_NODE__RELATIVE_FEATURE_WEIGHT = CORE_IMPACT_NODE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Represents</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_FEATURE_IMPACT_NODE__REPRESENTS = CORE_IMPACT_NODE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Weighted Mappings</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_FEATURE_IMPACT_NODE__WEIGHTED_MAPPINGS = CORE_IMPACT_NODE_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>CORE Feature Impact Node</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_FEATURE_IMPACT_NODE_FEATURE_COUNT = CORE_IMPACT_NODE_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.core.impl.COREWeightedMappingImpl <em>CORE Weighted Mapping</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.core.impl.COREWeightedMappingImpl
     * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCOREWeightedMapping()
     * @generated
     */
    int CORE_WEIGHTED_MAPPING = 22;

    /**
     * The feature id for the '<em><b>To</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_WEIGHTED_MAPPING__TO = CORE_MAPPING__TO;

    /**
     * The feature id for the '<em><b>From</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_WEIGHTED_MAPPING__FROM = CORE_MAPPING__FROM;

    /**
     * The feature id for the '<em><b>Weight</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_WEIGHTED_MAPPING__WEIGHT = CORE_MAPPING_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>CORE Weighted Mapping</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_WEIGHTED_MAPPING_FEATURE_COUNT = CORE_MAPPING_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.core.impl.COREImpactModelBindingImpl <em>CORE Impact Model Binding</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.core.impl.COREImpactModelBindingImpl
     * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCOREImpactModelBinding()
     * @generated
     */
    int CORE_IMPACT_MODEL_BINDING = 23;

    /**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_IMPACT_MODEL_BINDING__SOURCE = CORE_BINDING__SOURCE;

    /**
     * The feature id for the '<em><b>Mappings</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_IMPACT_MODEL_BINDING__MAPPINGS = CORE_BINDING__MAPPINGS;

    /**
     * The number of structural features of the '<em>CORE Impact Model Binding</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_IMPACT_MODEL_BINDING_FEATURE_COUNT = CORE_BINDING_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.core.impl.COREConcernConfigurationImpl <em>CORE Concern Configuration</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.core.impl.COREConcernConfigurationImpl
     * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCOREConcernConfiguration()
     * @generated
     */
    int CORE_CONCERN_CONFIGURATION = 24;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_CONCERN_CONFIGURATION__NAME = CORE_CONFIGURATION__NAME;

    /**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_CONCERN_CONFIGURATION__SOURCE = CORE_CONFIGURATION__SOURCE;

    /**
     * The feature id for the '<em><b>Selected</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_CONCERN_CONFIGURATION__SELECTED = CORE_CONFIGURATION__SELECTED;

    /**
     * The number of structural features of the '<em>CORE Concern Configuration</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_CONCERN_CONFIGURATION_FEATURE_COUNT = CORE_CONFIGURATION_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.core.impl.COREReuseConfigurationImpl <em>CORE Reuse Configuration</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.core.impl.COREReuseConfigurationImpl
     * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCOREReuseConfiguration()
     * @generated
     */
    int CORE_REUSE_CONFIGURATION = 25;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_REUSE_CONFIGURATION__NAME = CORE_CONFIGURATION__NAME;

    /**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_REUSE_CONFIGURATION__SOURCE = CORE_CONFIGURATION__SOURCE;

    /**
     * The feature id for the '<em><b>Selected</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_REUSE_CONFIGURATION__SELECTED = CORE_CONFIGURATION__SELECTED;

    /**
     * The feature id for the '<em><b>Reused Configuration</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_REUSE_CONFIGURATION__REUSED_CONFIGURATION = CORE_CONFIGURATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Reuse</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_REUSE_CONFIGURATION__REUSE = CORE_CONFIGURATION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>CORE Reuse Configuration</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CORE_REUSE_CONFIGURATION_FEATURE_COUNT = CORE_CONFIGURATION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.core.COREFeatureRelationshipType <em>CORE Feature Relationship Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.core.COREFeatureRelationshipType
     * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCOREFeatureRelationshipType()
     * @generated
     */
    int CORE_FEATURE_RELATIONSHIP_TYPE = 26;


    /**
     * The meta object id for the '{@link ca.mcgill.sel.core.COREVisibilityType <em>CORE Visibility Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.core.COREVisibilityType
     * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCOREVisibilityType()
     * @generated
     */
    int CORE_VISIBILITY_TYPE = 27;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.core.COREPartialityType <em>CORE Partiality Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.core.COREPartialityType
     * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCOREPartialityType()
     * @generated
     */
    int CORE_PARTIALITY_TYPE = 28;


    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.core.COREModel <em>CORE Model</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>CORE Model</em>'.
     * @see ca.mcgill.sel.core.COREModel
     * @generated
     */
    EClass getCOREModel();

    /**
     * Returns the meta object for the containment reference list '{@link ca.mcgill.sel.core.COREModel#getModelReuses <em>Model Reuses</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Model Reuses</em>'.
     * @see ca.mcgill.sel.core.COREModel#getModelReuses()
     * @see #getCOREModel()
     * @generated
     */
    EReference getCOREModel_ModelReuses();

    /**
     * Returns the meta object for the reference list '{@link ca.mcgill.sel.core.COREModel#getModelElements <em>Model Elements</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Model Elements</em>'.
     * @see ca.mcgill.sel.core.COREModel#getModelElements()
     * @see #getCOREModel()
     * @generated
     */
    EReference getCOREModel_ModelElements();

    /**
     * Returns the meta object for the reference list '{@link ca.mcgill.sel.core.COREModel#getRealizes <em>Realizes</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Realizes</em>'.
     * @see ca.mcgill.sel.core.COREModel#getRealizes()
     * @see #getCOREModel()
     * @generated
     */
    EReference getCOREModel_Realizes();

    /**
     * Returns the meta object for the reference '{@link ca.mcgill.sel.core.COREModel#getCoreConcern <em>Core Concern</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Core Concern</em>'.
     * @see ca.mcgill.sel.core.COREModel#getCoreConcern()
     * @see #getCOREModel()
     * @generated
     */
    EReference getCOREModel_CoreConcern();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.core.COREImpactModel <em>CORE Impact Model</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>CORE Impact Model</em>'.
     * @see ca.mcgill.sel.core.COREImpactModel
     * @generated
     */
    EClass getCOREImpactModel();

    /**
     * Returns the meta object for the containment reference list '{@link ca.mcgill.sel.core.COREImpactModel#getImpactModelElements <em>Impact Model Elements</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Impact Model Elements</em>'.
     * @see ca.mcgill.sel.core.COREImpactModel#getImpactModelElements()
     * @see #getCOREImpactModel()
     * @generated
     */
    EReference getCOREImpactModel_ImpactModelElements();

    /**
     * Returns the meta object for the map '{@link ca.mcgill.sel.core.COREImpactModel#getLayouts <em>Layouts</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the map '<em>Layouts</em>'.
     * @see ca.mcgill.sel.core.COREImpactModel#getLayouts()
     * @see #getCOREImpactModel()
     * @generated
     */
    EReference getCOREImpactModel_Layouts();

    /**
     * Returns the meta object for the containment reference list '{@link ca.mcgill.sel.core.COREImpactModel#getContributions <em>Contributions</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Contributions</em>'.
     * @see ca.mcgill.sel.core.COREImpactModel#getContributions()
     * @see #getCOREImpactModel()
     * @generated
     */
    EReference getCOREImpactModel_Contributions();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.core.COREConcern <em>CORE Concern</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>CORE Concern</em>'.
     * @see ca.mcgill.sel.core.COREConcern
     * @generated
     */
    EClass getCOREConcern();

    /**
     * Returns the meta object for the reference list '{@link ca.mcgill.sel.core.COREConcern#getModels <em>Models</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Models</em>'.
     * @see ca.mcgill.sel.core.COREConcern#getModels()
     * @see #getCOREConcern()
     * @generated
     */
    EReference getCOREConcern_Models();

    /**
     * Returns the meta object for the containment reference '{@link ca.mcgill.sel.core.COREConcern#getInterface <em>Interface</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Interface</em>'.
     * @see ca.mcgill.sel.core.COREConcern#getInterface()
     * @see #getCOREConcern()
     * @generated
     */
    EReference getCOREConcern_Interface();

    /**
     * Returns the meta object for the containment reference '{@link ca.mcgill.sel.core.COREConcern#getFeatureModel <em>Feature Model</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Feature Model</em>'.
     * @see ca.mcgill.sel.core.COREConcern#getFeatureModel()
     * @see #getCOREConcern()
     * @generated
     */
    EReference getCOREConcern_FeatureModel();

    /**
     * Returns the meta object for the containment reference '{@link ca.mcgill.sel.core.COREConcern#getImpactModel <em>Impact Model</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Impact Model</em>'.
     * @see ca.mcgill.sel.core.COREConcern#getImpactModel()
     * @see #getCOREConcern()
     * @generated
     */
    EReference getCOREConcern_ImpactModel();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.core.COREFeature <em>CORE Feature</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>CORE Feature</em>'.
     * @see ca.mcgill.sel.core.COREFeature
     * @generated
     */
    EClass getCOREFeature();

    /**
     * Returns the meta object for the reference list '{@link ca.mcgill.sel.core.COREFeature#getRealizedBy <em>Realized By</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Realized By</em>'.
     * @see ca.mcgill.sel.core.COREFeature#getRealizedBy()
     * @see #getCOREFeature()
     * @generated
     */
    EReference getCOREFeature_RealizedBy();

    /**
     * Returns the meta object for the containment reference list '{@link ca.mcgill.sel.core.COREFeature#getReuses <em>Reuses</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Reuses</em>'.
     * @see ca.mcgill.sel.core.COREFeature#getReuses()
     * @see #getCOREFeature()
     * @generated
     */
    EReference getCOREFeature_Reuses();

    /**
     * Returns the meta object for the reference list '{@link ca.mcgill.sel.core.COREFeature#getChildren <em>Children</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Children</em>'.
     * @see ca.mcgill.sel.core.COREFeature#getChildren()
     * @see #getCOREFeature()
     * @generated
     */
    EReference getCOREFeature_Children();

    /**
     * Returns the meta object for the reference '{@link ca.mcgill.sel.core.COREFeature#getParent <em>Parent</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Parent</em>'.
     * @see ca.mcgill.sel.core.COREFeature#getParent()
     * @see #getCOREFeature()
     * @generated
     */
    EReference getCOREFeature_Parent();

    /**
     * Returns the meta object for the attribute '{@link ca.mcgill.sel.core.COREFeature#getParentRelationship <em>Parent Relationship</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Parent Relationship</em>'.
     * @see ca.mcgill.sel.core.COREFeature#getParentRelationship()
     * @see #getCOREFeature()
     * @generated
     */
    EAttribute getCOREFeature_ParentRelationship();

    /**
     * Returns the meta object for the reference list '{@link ca.mcgill.sel.core.COREFeature#getRequires <em>Requires</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Requires</em>'.
     * @see ca.mcgill.sel.core.COREFeature#getRequires()
     * @see #getCOREFeature()
     * @generated
     */
    EReference getCOREFeature_Requires();

    /**
     * Returns the meta object for the reference list '{@link ca.mcgill.sel.core.COREFeature#getExcludes <em>Excludes</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Excludes</em>'.
     * @see ca.mcgill.sel.core.COREFeature#getExcludes()
     * @see #getCOREFeature()
     * @generated
     */
    EReference getCOREFeature_Excludes();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.core.COREBinding <em>CORE Binding</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>CORE Binding</em>'.
     * @see ca.mcgill.sel.core.COREBinding
     * @generated
     */
    EClass getCOREBinding();

    /**
     * Returns the meta object for the containment reference list '{@link ca.mcgill.sel.core.COREBinding#getMappings <em>Mappings</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Mappings</em>'.
     * @see ca.mcgill.sel.core.COREBinding#getMappings()
     * @see #getCOREBinding()
     * @generated
     */
    EReference getCOREBinding_Mappings();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.core.COREModelElement <em>CORE Model Element</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>CORE Model Element</em>'.
     * @see ca.mcgill.sel.core.COREModelElement
     * @generated
     */
    EClass getCOREModelElement();

    /**
     * Returns the meta object for the attribute '{@link ca.mcgill.sel.core.COREModelElement#getPartiality <em>Partiality</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Partiality</em>'.
     * @see ca.mcgill.sel.core.COREModelElement#getPartiality()
     * @see #getCOREModelElement()
     * @generated
     */
    EAttribute getCOREModelElement_Partiality();

    /**
     * Returns the meta object for the attribute '{@link ca.mcgill.sel.core.COREModelElement#getVisibility <em>Visibility</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Visibility</em>'.
     * @see ca.mcgill.sel.core.COREModelElement#getVisibility()
     * @see #getCOREModelElement()
     * @generated
     */
    EAttribute getCOREModelElement_Visibility();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.core.CORECompositionSpecification <em>CORE Composition Specification</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>CORE Composition Specification</em>'.
     * @see ca.mcgill.sel.core.CORECompositionSpecification
     * @generated
     */
    EClass getCORECompositionSpecification();

    /**
     * Returns the meta object for the reference '{@link ca.mcgill.sel.core.CORECompositionSpecification#getSource <em>Source</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Source</em>'.
     * @see ca.mcgill.sel.core.CORECompositionSpecification#getSource()
     * @see #getCORECompositionSpecification()
     * @generated
     */
    EReference getCORECompositionSpecification_Source();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.core.COREMapping <em>CORE Mapping</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>CORE Mapping</em>'.
     * @see ca.mcgill.sel.core.COREMapping
     * @generated
     */
    EClass getCOREMapping();

    /**
     * Returns the meta object for the reference '{@link ca.mcgill.sel.core.COREMapping#getTo <em>To</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>To</em>'.
     * @see ca.mcgill.sel.core.COREMapping#getTo()
     * @see #getCOREMapping()
     * @generated
     */
    EReference getCOREMapping_To();

    /**
     * Returns the meta object for the reference '{@link ca.mcgill.sel.core.COREMapping#getFrom <em>From</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>From</em>'.
     * @see ca.mcgill.sel.core.COREMapping#getFrom()
     * @see #getCOREMapping()
     * @generated
     */
    EReference getCOREMapping_From();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.core.CORENamedElement <em>CORE Named Element</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>CORE Named Element</em>'.
     * @see ca.mcgill.sel.core.CORENamedElement
     * @generated
     */
    EClass getCORENamedElement();

    /**
     * Returns the meta object for the attribute '{@link ca.mcgill.sel.core.CORENamedElement#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see ca.mcgill.sel.core.CORENamedElement#getName()
     * @see #getCORENamedElement()
     * @generated
     */
    EAttribute getCORENamedElement_Name();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.core.COREInterface <em>CORE Interface</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>CORE Interface</em>'.
     * @see ca.mcgill.sel.core.COREInterface
     * @generated
     */
    EClass getCOREInterface();

    /**
     * Returns the meta object for the reference list '{@link ca.mcgill.sel.core.COREInterface#getSelectable <em>Selectable</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Selectable</em>'.
     * @see ca.mcgill.sel.core.COREInterface#getSelectable()
     * @see #getCOREInterface()
     * @generated
     */
    EReference getCOREInterface_Selectable();

    /**
     * Returns the meta object for the reference list '{@link ca.mcgill.sel.core.COREInterface#getCustomizable <em>Customizable</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Customizable</em>'.
     * @see ca.mcgill.sel.core.COREInterface#getCustomizable()
     * @see #getCOREInterface()
     * @generated
     */
    EReference getCOREInterface_Customizable();

    /**
     * Returns the meta object for the reference list '{@link ca.mcgill.sel.core.COREInterface#getUsable <em>Usable</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Usable</em>'.
     * @see ca.mcgill.sel.core.COREInterface#getUsable()
     * @see #getCOREInterface()
     * @generated
     */
    EReference getCOREInterface_Usable();

    /**
     * Returns the meta object for the reference list '{@link ca.mcgill.sel.core.COREInterface#getImpacted <em>Impacted</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Impacted</em>'.
     * @see ca.mcgill.sel.core.COREInterface#getImpacted()
     * @see #getCOREInterface()
     * @generated
     */
    EReference getCOREInterface_Impacted();

    /**
     * Returns the meta object for the reference list '{@link ca.mcgill.sel.core.COREInterface#getDefaults <em>Defaults</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Defaults</em>'.
     * @see ca.mcgill.sel.core.COREInterface#getDefaults()
     * @see #getCOREInterface()
     * @generated
     */
    EReference getCOREInterface_Defaults();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.core.COREReuse <em>CORE Reuse</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>CORE Reuse</em>'.
     * @see ca.mcgill.sel.core.COREReuse
     * @generated
     */
    EClass getCOREReuse();

    /**
     * Returns the meta object for the reference '{@link ca.mcgill.sel.core.COREReuse#getReusedConcern <em>Reused Concern</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Reused Concern</em>'.
     * @see ca.mcgill.sel.core.COREReuse#getReusedConcern()
     * @see #getCOREReuse()
     * @generated
     */
    EReference getCOREReuse_ReusedConcern();

    /**
     * Returns the meta object for the containment reference list '{@link ca.mcgill.sel.core.COREReuse#getConfigurations <em>Configurations</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Configurations</em>'.
     * @see ca.mcgill.sel.core.COREReuse#getConfigurations()
     * @see #getCOREReuse()
     * @generated
     */
    EReference getCOREReuse_Configurations();

    /**
     * Returns the meta object for the reference '{@link ca.mcgill.sel.core.COREReuse#getSelectedConfiguration <em>Selected Configuration</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Selected Configuration</em>'.
     * @see ca.mcgill.sel.core.COREReuse#getSelectedConfiguration()
     * @see #getCOREReuse()
     * @generated
     */
    EReference getCOREReuse_SelectedConfiguration();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.core.COREPattern <em>CORE Pattern</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>CORE Pattern</em>'.
     * @see ca.mcgill.sel.core.COREPattern
     * @generated
     */
    EClass getCOREPattern();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.core.COREImpactNode <em>CORE Impact Node</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>CORE Impact Node</em>'.
     * @see ca.mcgill.sel.core.COREImpactNode
     * @generated
     */
    EClass getCOREImpactNode();

    /**
     * Returns the meta object for the attribute '{@link ca.mcgill.sel.core.COREImpactNode#getScalingFactor <em>Scaling Factor</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Scaling Factor</em>'.
     * @see ca.mcgill.sel.core.COREImpactNode#getScalingFactor()
     * @see #getCOREImpactNode()
     * @generated
     */
    EAttribute getCOREImpactNode_ScalingFactor();

    /**
     * Returns the meta object for the attribute '{@link ca.mcgill.sel.core.COREImpactNode#getOffset <em>Offset</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Offset</em>'.
     * @see ca.mcgill.sel.core.COREImpactNode#getOffset()
     * @see #getCOREImpactNode()
     * @generated
     */
    EAttribute getCOREImpactNode_Offset();

    /**
     * Returns the meta object for the reference list '{@link ca.mcgill.sel.core.COREImpactNode#getOutgoing <em>Outgoing</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Outgoing</em>'.
     * @see ca.mcgill.sel.core.COREImpactNode#getOutgoing()
     * @see #getCOREImpactNode()
     * @generated
     */
    EReference getCOREImpactNode_Outgoing();

    /**
     * Returns the meta object for the reference list '{@link ca.mcgill.sel.core.COREImpactNode#getIncoming <em>Incoming</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Incoming</em>'.
     * @see ca.mcgill.sel.core.COREImpactNode#getIncoming()
     * @see #getCOREImpactNode()
     * @generated
     */
    EReference getCOREImpactNode_Incoming();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.core.COREConfiguration <em>CORE Configuration</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>CORE Configuration</em>'.
     * @see ca.mcgill.sel.core.COREConfiguration
     * @generated
     */
    EClass getCOREConfiguration();

    /**
     * Returns the meta object for the reference list '{@link ca.mcgill.sel.core.COREConfiguration#getSelected <em>Selected</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Selected</em>'.
     * @see ca.mcgill.sel.core.COREConfiguration#getSelected()
     * @see #getCOREConfiguration()
     * @generated
     */
    EReference getCOREConfiguration_Selected();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.core.COREFeatureModel <em>CORE Feature Model</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>CORE Feature Model</em>'.
     * @see ca.mcgill.sel.core.COREFeatureModel
     * @generated
     */
    EClass getCOREFeatureModel();

    /**
     * Returns the meta object for the containment reference list '{@link ca.mcgill.sel.core.COREFeatureModel#getFeatures <em>Features</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Features</em>'.
     * @see ca.mcgill.sel.core.COREFeatureModel#getFeatures()
     * @see #getCOREFeatureModel()
     * @generated
     */
    EReference getCOREFeatureModel_Features();

    /**
     * Returns the meta object for the reference '{@link ca.mcgill.sel.core.COREFeatureModel#getRoot <em>Root</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Root</em>'.
     * @see ca.mcgill.sel.core.COREFeatureModel#getRoot()
     * @see #getCOREFeatureModel()
     * @generated
     */
    EReference getCOREFeatureModel_Root();

    /**
     * Returns the meta object for the containment reference list '{@link ca.mcgill.sel.core.COREFeatureModel#getConfigurations <em>Configurations</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Configurations</em>'.
     * @see ca.mcgill.sel.core.COREFeatureModel#getConfigurations()
     * @see #getCOREFeatureModel()
     * @generated
     */
    EReference getCOREFeatureModel_Configurations();

    /**
     * Returns the meta object for the reference '{@link ca.mcgill.sel.core.COREFeatureModel#getDefaultConfiguration <em>Default Configuration</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Default Configuration</em>'.
     * @see ca.mcgill.sel.core.COREFeatureModel#getDefaultConfiguration()
     * @see #getCOREFeatureModel()
     * @generated
     */
    EReference getCOREFeatureModel_DefaultConfiguration();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.core.COREModelReuse <em>CORE Model Reuse</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>CORE Model Reuse</em>'.
     * @see ca.mcgill.sel.core.COREModelReuse
     * @generated
     */
    EClass getCOREModelReuse();

    /**
     * Returns the meta object for the containment reference list '{@link ca.mcgill.sel.core.COREModelReuse#getCompositions <em>Compositions</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Compositions</em>'.
     * @see ca.mcgill.sel.core.COREModelReuse#getCompositions()
     * @see #getCOREModelReuse()
     * @generated
     */
    EReference getCOREModelReuse_Compositions();

    /**
     * Returns the meta object for the reference '{@link ca.mcgill.sel.core.COREModelReuse#getReuse <em>Reuse</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Reuse</em>'.
     * @see ca.mcgill.sel.core.COREModelReuse#getReuse()
     * @see #getCOREModelReuse()
     * @generated
     */
    EReference getCOREModelReuse_Reuse();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.core.COREContribution <em>CORE Contribution</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>CORE Contribution</em>'.
     * @see ca.mcgill.sel.core.COREContribution
     * @generated
     */
    EClass getCOREContribution();

    /**
     * Returns the meta object for the attribute '{@link ca.mcgill.sel.core.COREContribution#getRelativeWeight <em>Relative Weight</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Relative Weight</em>'.
     * @see ca.mcgill.sel.core.COREContribution#getRelativeWeight()
     * @see #getCOREContribution()
     * @generated
     */
    EAttribute getCOREContribution_RelativeWeight();

    /**
     * Returns the meta object for the reference '{@link ca.mcgill.sel.core.COREContribution#getSource <em>Source</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Source</em>'.
     * @see ca.mcgill.sel.core.COREContribution#getSource()
     * @see #getCOREContribution()
     * @generated
     */
    EReference getCOREContribution_Source();

    /**
     * Returns the meta object for the reference '{@link ca.mcgill.sel.core.COREContribution#getImpacts <em>Impacts</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Impacts</em>'.
     * @see ca.mcgill.sel.core.COREContribution#getImpacts()
     * @see #getCOREContribution()
     * @generated
     */
    EReference getCOREContribution_Impacts();

    /**
     * Returns the meta object for class '{@link java.util.Map.Entry <em>Layout Map</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Layout Map</em>'.
     * @see java.util.Map.Entry
     * @model keyType="org.eclipse.emf.ecore.EObject" keyRequired="true"
     *        valueType="ca.mcgill.sel.core.LayoutElement" valueContainment="true" valueRequired="true"
     * @generated
     */
    EClass getLayoutMap();

    /**
     * Returns the meta object for the reference '{@link java.util.Map.Entry <em>Key</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Key</em>'.
     * @see java.util.Map.Entry
     * @see #getLayoutMap()
     * @generated
     */
    EReference getLayoutMap_Key();

    /**
     * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Value</em>'.
     * @see java.util.Map.Entry
     * @see #getLayoutMap()
     * @generated
     */
    EReference getLayoutMap_Value();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.core.LayoutElement <em>Layout Element</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Layout Element</em>'.
     * @see ca.mcgill.sel.core.LayoutElement
     * @generated
     */
    EClass getLayoutElement();

    /**
     * Returns the meta object for the attribute '{@link ca.mcgill.sel.core.LayoutElement#getX <em>X</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>X</em>'.
     * @see ca.mcgill.sel.core.LayoutElement#getX()
     * @see #getLayoutElement()
     * @generated
     */
    EAttribute getLayoutElement_X();

    /**
     * Returns the meta object for the attribute '{@link ca.mcgill.sel.core.LayoutElement#getY <em>Y</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Y</em>'.
     * @see ca.mcgill.sel.core.LayoutElement#getY()
     * @see #getLayoutElement()
     * @generated
     */
    EAttribute getLayoutElement_Y();

    /**
     * Returns the meta object for class '{@link java.util.Map.Entry <em>Layout Container Map</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Layout Container Map</em>'.
     * @see java.util.Map.Entry
     * @model keyType="org.eclipse.emf.ecore.EObject" keyRequired="true"
     *        valueMapType="ca.mcgill.sel.core.LayoutMap<org.eclipse.emf.ecore.EObject, ca.mcgill.sel.core.LayoutElement>"
     * @generated
     */
    EClass getLayoutContainerMap();

    /**
     * Returns the meta object for the reference '{@link java.util.Map.Entry <em>Key</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Key</em>'.
     * @see java.util.Map.Entry
     * @see #getLayoutContainerMap()
     * @generated
     */
    EReference getLayoutContainerMap_Key();

    /**
     * Returns the meta object for the map '{@link java.util.Map.Entry <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the map '<em>Value</em>'.
     * @see java.util.Map.Entry
     * @see #getLayoutContainerMap()
     * @generated
     */
    EReference getLayoutContainerMap_Value();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.core.COREFeatureImpactNode <em>CORE Feature Impact Node</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>CORE Feature Impact Node</em>'.
     * @see ca.mcgill.sel.core.COREFeatureImpactNode
     * @generated
     */
    EClass getCOREFeatureImpactNode();

    /**
     * Returns the meta object for the attribute '{@link ca.mcgill.sel.core.COREFeatureImpactNode#getRelativeFeatureWeight <em>Relative Feature Weight</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Relative Feature Weight</em>'.
     * @see ca.mcgill.sel.core.COREFeatureImpactNode#getRelativeFeatureWeight()
     * @see #getCOREFeatureImpactNode()
     * @generated
     */
    EAttribute getCOREFeatureImpactNode_RelativeFeatureWeight();

    /**
     * Returns the meta object for the reference '{@link ca.mcgill.sel.core.COREFeatureImpactNode#getRepresents <em>Represents</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Represents</em>'.
     * @see ca.mcgill.sel.core.COREFeatureImpactNode#getRepresents()
     * @see #getCOREFeatureImpactNode()
     * @generated
     */
    EReference getCOREFeatureImpactNode_Represents();

    /**
     * Returns the meta object for the reference list '{@link ca.mcgill.sel.core.COREFeatureImpactNode#getWeightedMappings <em>Weighted Mappings</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Weighted Mappings</em>'.
     * @see ca.mcgill.sel.core.COREFeatureImpactNode#getWeightedMappings()
     * @see #getCOREFeatureImpactNode()
     * @generated
     */
    EReference getCOREFeatureImpactNode_WeightedMappings();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.core.COREModelCompositionSpecification <em>CORE Model Composition Specification</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>CORE Model Composition Specification</em>'.
     * @see ca.mcgill.sel.core.COREModelCompositionSpecification
     * @generated
     */
    EClass getCOREModelCompositionSpecification();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.core.COREWeightedMapping <em>CORE Weighted Mapping</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>CORE Weighted Mapping</em>'.
     * @see ca.mcgill.sel.core.COREWeightedMapping
     * @generated
     */
    EClass getCOREWeightedMapping();

    /**
     * Returns the meta object for the attribute '{@link ca.mcgill.sel.core.COREWeightedMapping#getWeight <em>Weight</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Weight</em>'.
     * @see ca.mcgill.sel.core.COREWeightedMapping#getWeight()
     * @see #getCOREWeightedMapping()
     * @generated
     */
    EAttribute getCOREWeightedMapping_Weight();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.core.COREImpactModelBinding <em>CORE Impact Model Binding</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>CORE Impact Model Binding</em>'.
     * @see ca.mcgill.sel.core.COREImpactModelBinding
     * @generated
     */
    EClass getCOREImpactModelBinding();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.core.COREConcernConfiguration <em>CORE Concern Configuration</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>CORE Concern Configuration</em>'.
     * @see ca.mcgill.sel.core.COREConcernConfiguration
     * @generated
     */
    EClass getCOREConcernConfiguration();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.core.COREReuseConfiguration <em>CORE Reuse Configuration</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>CORE Reuse Configuration</em>'.
     * @see ca.mcgill.sel.core.COREReuseConfiguration
     * @generated
     */
    EClass getCOREReuseConfiguration();

    /**
     * Returns the meta object for the reference '{@link ca.mcgill.sel.core.COREReuseConfiguration#getReusedConfiguration <em>Reused Configuration</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Reused Configuration</em>'.
     * @see ca.mcgill.sel.core.COREReuseConfiguration#getReusedConfiguration()
     * @see #getCOREReuseConfiguration()
     * @generated
     */
    EReference getCOREReuseConfiguration_ReusedConfiguration();

    /**
     * Returns the meta object for the reference '{@link ca.mcgill.sel.core.COREReuseConfiguration#getReuse <em>Reuse</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Reuse</em>'.
     * @see ca.mcgill.sel.core.COREReuseConfiguration#getReuse()
     * @see #getCOREReuseConfiguration()
     * @generated
     */
    EReference getCOREReuseConfiguration_Reuse();

    /**
     * Returns the meta object for enum '{@link ca.mcgill.sel.core.COREFeatureRelationshipType <em>CORE Feature Relationship Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>CORE Feature Relationship Type</em>'.
     * @see ca.mcgill.sel.core.COREFeatureRelationshipType
     * @generated
     */
    EEnum getCOREFeatureRelationshipType();

    /**
     * Returns the meta object for enum '{@link ca.mcgill.sel.core.COREVisibilityType <em>CORE Visibility Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>CORE Visibility Type</em>'.
     * @see ca.mcgill.sel.core.COREVisibilityType
     * @generated
     */
    EEnum getCOREVisibilityType();

    /**
     * Returns the meta object for enum '{@link ca.mcgill.sel.core.COREPartialityType <em>CORE Partiality Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>CORE Partiality Type</em>'.
     * @see ca.mcgill.sel.core.COREPartialityType
     * @generated
     */
    EEnum getCOREPartialityType();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    CoreFactory getCoreFactory();

    /**
     * <!-- begin-user-doc -->
     * Defines literals for the meta objects that represent
     * <ul>
     *   <li>each class,</li>
     *   <li>each feature of each class,</li>
     *   <li>each enum,</li>
     *   <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '{@link ca.mcgill.sel.core.impl.COREModelImpl <em>CORE Model</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.core.impl.COREModelImpl
         * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCOREModel()
         * @generated
         */
        EClass CORE_MODEL = eINSTANCE.getCOREModel();

        /**
         * The meta object literal for the '<em><b>Model Reuses</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CORE_MODEL__MODEL_REUSES = eINSTANCE.getCOREModel_ModelReuses();

        /**
         * The meta object literal for the '<em><b>Model Elements</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CORE_MODEL__MODEL_ELEMENTS = eINSTANCE.getCOREModel_ModelElements();

        /**
         * The meta object literal for the '<em><b>Realizes</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CORE_MODEL__REALIZES = eINSTANCE.getCOREModel_Realizes();

        /**
         * The meta object literal for the '<em><b>Core Concern</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CORE_MODEL__CORE_CONCERN = eINSTANCE.getCOREModel_CoreConcern();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.core.impl.COREImpactModelImpl <em>CORE Impact Model</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.core.impl.COREImpactModelImpl
         * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCOREImpactModel()
         * @generated
         */
        EClass CORE_IMPACT_MODEL = eINSTANCE.getCOREImpactModel();

        /**
         * The meta object literal for the '<em><b>Impact Model Elements</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CORE_IMPACT_MODEL__IMPACT_MODEL_ELEMENTS = eINSTANCE.getCOREImpactModel_ImpactModelElements();

        /**
         * The meta object literal for the '<em><b>Layouts</b></em>' map feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CORE_IMPACT_MODEL__LAYOUTS = eINSTANCE.getCOREImpactModel_Layouts();

        /**
         * The meta object literal for the '<em><b>Contributions</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CORE_IMPACT_MODEL__CONTRIBUTIONS = eINSTANCE.getCOREImpactModel_Contributions();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.core.impl.COREConcernImpl <em>CORE Concern</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.core.impl.COREConcernImpl
         * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCOREConcern()
         * @generated
         */
        EClass CORE_CONCERN = eINSTANCE.getCOREConcern();

        /**
         * The meta object literal for the '<em><b>Models</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CORE_CONCERN__MODELS = eINSTANCE.getCOREConcern_Models();

        /**
         * The meta object literal for the '<em><b>Interface</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CORE_CONCERN__INTERFACE = eINSTANCE.getCOREConcern_Interface();

        /**
         * The meta object literal for the '<em><b>Feature Model</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CORE_CONCERN__FEATURE_MODEL = eINSTANCE.getCOREConcern_FeatureModel();

        /**
         * The meta object literal for the '<em><b>Impact Model</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CORE_CONCERN__IMPACT_MODEL = eINSTANCE.getCOREConcern_ImpactModel();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.core.impl.COREFeatureImpl <em>CORE Feature</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.core.impl.COREFeatureImpl
         * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCOREFeature()
         * @generated
         */
        EClass CORE_FEATURE = eINSTANCE.getCOREFeature();

        /**
         * The meta object literal for the '<em><b>Realized By</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CORE_FEATURE__REALIZED_BY = eINSTANCE.getCOREFeature_RealizedBy();

        /**
         * The meta object literal for the '<em><b>Reuses</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CORE_FEATURE__REUSES = eINSTANCE.getCOREFeature_Reuses();

        /**
         * The meta object literal for the '<em><b>Children</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CORE_FEATURE__CHILDREN = eINSTANCE.getCOREFeature_Children();

        /**
         * The meta object literal for the '<em><b>Parent</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CORE_FEATURE__PARENT = eINSTANCE.getCOREFeature_Parent();

        /**
         * The meta object literal for the '<em><b>Parent Relationship</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute CORE_FEATURE__PARENT_RELATIONSHIP = eINSTANCE.getCOREFeature_ParentRelationship();

        /**
         * The meta object literal for the '<em><b>Requires</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CORE_FEATURE__REQUIRES = eINSTANCE.getCOREFeature_Requires();

        /**
         * The meta object literal for the '<em><b>Excludes</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CORE_FEATURE__EXCLUDES = eINSTANCE.getCOREFeature_Excludes();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.core.impl.COREBindingImpl <em>CORE Binding</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.core.impl.COREBindingImpl
         * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCOREBinding()
         * @generated
         */
        EClass CORE_BINDING = eINSTANCE.getCOREBinding();

        /**
         * The meta object literal for the '<em><b>Mappings</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CORE_BINDING__MAPPINGS = eINSTANCE.getCOREBinding_Mappings();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.core.impl.COREModelElementImpl <em>CORE Model Element</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.core.impl.COREModelElementImpl
         * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCOREModelElement()
         * @generated
         */
        EClass CORE_MODEL_ELEMENT = eINSTANCE.getCOREModelElement();

        /**
         * The meta object literal for the '<em><b>Partiality</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute CORE_MODEL_ELEMENT__PARTIALITY = eINSTANCE.getCOREModelElement_Partiality();

        /**
         * The meta object literal for the '<em><b>Visibility</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute CORE_MODEL_ELEMENT__VISIBILITY = eINSTANCE.getCOREModelElement_Visibility();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.core.impl.CORECompositionSpecificationImpl <em>CORE Composition Specification</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.core.impl.CORECompositionSpecificationImpl
         * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCORECompositionSpecification()
         * @generated
         */
        EClass CORE_COMPOSITION_SPECIFICATION = eINSTANCE.getCORECompositionSpecification();

        /**
         * The meta object literal for the '<em><b>Source</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CORE_COMPOSITION_SPECIFICATION__SOURCE = eINSTANCE.getCORECompositionSpecification_Source();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.core.impl.COREMappingImpl <em>CORE Mapping</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.core.impl.COREMappingImpl
         * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCOREMapping()
         * @generated
         */
        EClass CORE_MAPPING = eINSTANCE.getCOREMapping();

        /**
         * The meta object literal for the '<em><b>To</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CORE_MAPPING__TO = eINSTANCE.getCOREMapping_To();

        /**
         * The meta object literal for the '<em><b>From</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CORE_MAPPING__FROM = eINSTANCE.getCOREMapping_From();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.core.impl.CORENamedElementImpl <em>CORE Named Element</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.core.impl.CORENamedElementImpl
         * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCORENamedElement()
         * @generated
         */
        EClass CORE_NAMED_ELEMENT = eINSTANCE.getCORENamedElement();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute CORE_NAMED_ELEMENT__NAME = eINSTANCE.getCORENamedElement_Name();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.core.impl.COREInterfaceImpl <em>CORE Interface</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.core.impl.COREInterfaceImpl
         * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCOREInterface()
         * @generated
         */
        EClass CORE_INTERFACE = eINSTANCE.getCOREInterface();

        /**
         * The meta object literal for the '<em><b>Selectable</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CORE_INTERFACE__SELECTABLE = eINSTANCE.getCOREInterface_Selectable();

        /**
         * The meta object literal for the '<em><b>Customizable</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CORE_INTERFACE__CUSTOMIZABLE = eINSTANCE.getCOREInterface_Customizable();

        /**
         * The meta object literal for the '<em><b>Usable</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CORE_INTERFACE__USABLE = eINSTANCE.getCOREInterface_Usable();

        /**
         * The meta object literal for the '<em><b>Impacted</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CORE_INTERFACE__IMPACTED = eINSTANCE.getCOREInterface_Impacted();

        /**
         * The meta object literal for the '<em><b>Defaults</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CORE_INTERFACE__DEFAULTS = eINSTANCE.getCOREInterface_Defaults();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.core.impl.COREReuseImpl <em>CORE Reuse</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.core.impl.COREReuseImpl
         * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCOREReuse()
         * @generated
         */
        EClass CORE_REUSE = eINSTANCE.getCOREReuse();

        /**
         * The meta object literal for the '<em><b>Reused Concern</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CORE_REUSE__REUSED_CONCERN = eINSTANCE.getCOREReuse_ReusedConcern();

        /**
         * The meta object literal for the '<em><b>Configurations</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CORE_REUSE__CONFIGURATIONS = eINSTANCE.getCOREReuse_Configurations();

        /**
         * The meta object literal for the '<em><b>Selected Configuration</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CORE_REUSE__SELECTED_CONFIGURATION = eINSTANCE.getCOREReuse_SelectedConfiguration();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.core.impl.COREPatternImpl <em>CORE Pattern</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.core.impl.COREPatternImpl
         * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCOREPattern()
         * @generated
         */
        EClass CORE_PATTERN = eINSTANCE.getCOREPattern();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.core.impl.COREImpactNodeImpl <em>CORE Impact Node</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.core.impl.COREImpactNodeImpl
         * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCOREImpactNode()
         * @generated
         */
        EClass CORE_IMPACT_NODE = eINSTANCE.getCOREImpactNode();

        /**
         * The meta object literal for the '<em><b>Scaling Factor</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute CORE_IMPACT_NODE__SCALING_FACTOR = eINSTANCE.getCOREImpactNode_ScalingFactor();

        /**
         * The meta object literal for the '<em><b>Offset</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute CORE_IMPACT_NODE__OFFSET = eINSTANCE.getCOREImpactNode_Offset();

        /**
         * The meta object literal for the '<em><b>Outgoing</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CORE_IMPACT_NODE__OUTGOING = eINSTANCE.getCOREImpactNode_Outgoing();

        /**
         * The meta object literal for the '<em><b>Incoming</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CORE_IMPACT_NODE__INCOMING = eINSTANCE.getCOREImpactNode_Incoming();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.core.impl.COREConfigurationImpl <em>CORE Configuration</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.core.impl.COREConfigurationImpl
         * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCOREConfiguration()
         * @generated
         */
        EClass CORE_CONFIGURATION = eINSTANCE.getCOREConfiguration();

        /**
         * The meta object literal for the '<em><b>Selected</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CORE_CONFIGURATION__SELECTED = eINSTANCE.getCOREConfiguration_Selected();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.core.impl.COREFeatureModelImpl <em>CORE Feature Model</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.core.impl.COREFeatureModelImpl
         * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCOREFeatureModel()
         * @generated
         */
        EClass CORE_FEATURE_MODEL = eINSTANCE.getCOREFeatureModel();

        /**
         * The meta object literal for the '<em><b>Features</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CORE_FEATURE_MODEL__FEATURES = eINSTANCE.getCOREFeatureModel_Features();

        /**
         * The meta object literal for the '<em><b>Root</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CORE_FEATURE_MODEL__ROOT = eINSTANCE.getCOREFeatureModel_Root();

        /**
         * The meta object literal for the '<em><b>Configurations</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CORE_FEATURE_MODEL__CONFIGURATIONS = eINSTANCE.getCOREFeatureModel_Configurations();

        /**
         * The meta object literal for the '<em><b>Default Configuration</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CORE_FEATURE_MODEL__DEFAULT_CONFIGURATION = eINSTANCE.getCOREFeatureModel_DefaultConfiguration();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.core.impl.COREModelReuseImpl <em>CORE Model Reuse</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.core.impl.COREModelReuseImpl
         * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCOREModelReuse()
         * @generated
         */
        EClass CORE_MODEL_REUSE = eINSTANCE.getCOREModelReuse();

        /**
         * The meta object literal for the '<em><b>Compositions</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CORE_MODEL_REUSE__COMPOSITIONS = eINSTANCE.getCOREModelReuse_Compositions();

        /**
         * The meta object literal for the '<em><b>Reuse</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CORE_MODEL_REUSE__REUSE = eINSTANCE.getCOREModelReuse_Reuse();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.core.impl.COREContributionImpl <em>CORE Contribution</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.core.impl.COREContributionImpl
         * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCOREContribution()
         * @generated
         */
        EClass CORE_CONTRIBUTION = eINSTANCE.getCOREContribution();

        /**
         * The meta object literal for the '<em><b>Relative Weight</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute CORE_CONTRIBUTION__RELATIVE_WEIGHT = eINSTANCE.getCOREContribution_RelativeWeight();

        /**
         * The meta object literal for the '<em><b>Source</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CORE_CONTRIBUTION__SOURCE = eINSTANCE.getCOREContribution_Source();

        /**
         * The meta object literal for the '<em><b>Impacts</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CORE_CONTRIBUTION__IMPACTS = eINSTANCE.getCOREContribution_Impacts();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.core.impl.LayoutMapImpl <em>Layout Map</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.core.impl.LayoutMapImpl
         * @see ca.mcgill.sel.core.impl.CorePackageImpl#getLayoutMap()
         * @generated
         */
        EClass LAYOUT_MAP = eINSTANCE.getLayoutMap();

        /**
         * The meta object literal for the '<em><b>Key</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference LAYOUT_MAP__KEY = eINSTANCE.getLayoutMap_Key();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference LAYOUT_MAP__VALUE = eINSTANCE.getLayoutMap_Value();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.core.impl.LayoutElementImpl <em>Layout Element</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.core.impl.LayoutElementImpl
         * @see ca.mcgill.sel.core.impl.CorePackageImpl#getLayoutElement()
         * @generated
         */
        EClass LAYOUT_ELEMENT = eINSTANCE.getLayoutElement();

        /**
         * The meta object literal for the '<em><b>X</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LAYOUT_ELEMENT__X = eINSTANCE.getLayoutElement_X();

        /**
         * The meta object literal for the '<em><b>Y</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LAYOUT_ELEMENT__Y = eINSTANCE.getLayoutElement_Y();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.core.impl.LayoutContainerMapImpl <em>Layout Container Map</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.core.impl.LayoutContainerMapImpl
         * @see ca.mcgill.sel.core.impl.CorePackageImpl#getLayoutContainerMap()
         * @generated
         */
        EClass LAYOUT_CONTAINER_MAP = eINSTANCE.getLayoutContainerMap();

        /**
         * The meta object literal for the '<em><b>Key</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference LAYOUT_CONTAINER_MAP__KEY = eINSTANCE.getLayoutContainerMap_Key();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' map feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference LAYOUT_CONTAINER_MAP__VALUE = eINSTANCE.getLayoutContainerMap_Value();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.core.impl.COREFeatureImpactNodeImpl <em>CORE Feature Impact Node</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.core.impl.COREFeatureImpactNodeImpl
         * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCOREFeatureImpactNode()
         * @generated
         */
        EClass CORE_FEATURE_IMPACT_NODE = eINSTANCE.getCOREFeatureImpactNode();

        /**
         * The meta object literal for the '<em><b>Relative Feature Weight</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute CORE_FEATURE_IMPACT_NODE__RELATIVE_FEATURE_WEIGHT = eINSTANCE.getCOREFeatureImpactNode_RelativeFeatureWeight();

        /**
         * The meta object literal for the '<em><b>Represents</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CORE_FEATURE_IMPACT_NODE__REPRESENTS = eINSTANCE.getCOREFeatureImpactNode_Represents();

        /**
         * The meta object literal for the '<em><b>Weighted Mappings</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CORE_FEATURE_IMPACT_NODE__WEIGHTED_MAPPINGS = eINSTANCE.getCOREFeatureImpactNode_WeightedMappings();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.core.impl.COREModelCompositionSpecificationImpl <em>CORE Model Composition Specification</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.core.impl.COREModelCompositionSpecificationImpl
         * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCOREModelCompositionSpecification()
         * @generated
         */
        EClass CORE_MODEL_COMPOSITION_SPECIFICATION = eINSTANCE.getCOREModelCompositionSpecification();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.core.impl.COREWeightedMappingImpl <em>CORE Weighted Mapping</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.core.impl.COREWeightedMappingImpl
         * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCOREWeightedMapping()
         * @generated
         */
        EClass CORE_WEIGHTED_MAPPING = eINSTANCE.getCOREWeightedMapping();

        /**
         * The meta object literal for the '<em><b>Weight</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute CORE_WEIGHTED_MAPPING__WEIGHT = eINSTANCE.getCOREWeightedMapping_Weight();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.core.impl.COREImpactModelBindingImpl <em>CORE Impact Model Binding</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.core.impl.COREImpactModelBindingImpl
         * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCOREImpactModelBinding()
         * @generated
         */
        EClass CORE_IMPACT_MODEL_BINDING = eINSTANCE.getCOREImpactModelBinding();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.core.impl.COREConcernConfigurationImpl <em>CORE Concern Configuration</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.core.impl.COREConcernConfigurationImpl
         * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCOREConcernConfiguration()
         * @generated
         */
        EClass CORE_CONCERN_CONFIGURATION = eINSTANCE.getCOREConcernConfiguration();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.core.impl.COREReuseConfigurationImpl <em>CORE Reuse Configuration</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.core.impl.COREReuseConfigurationImpl
         * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCOREReuseConfiguration()
         * @generated
         */
        EClass CORE_REUSE_CONFIGURATION = eINSTANCE.getCOREReuseConfiguration();

        /**
         * The meta object literal for the '<em><b>Reused Configuration</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CORE_REUSE_CONFIGURATION__REUSED_CONFIGURATION = eINSTANCE.getCOREReuseConfiguration_ReusedConfiguration();

        /**
         * The meta object literal for the '<em><b>Reuse</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CORE_REUSE_CONFIGURATION__REUSE = eINSTANCE.getCOREReuseConfiguration_Reuse();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.core.COREFeatureRelationshipType <em>CORE Feature Relationship Type</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.core.COREFeatureRelationshipType
         * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCOREFeatureRelationshipType()
         * @generated
         */
        EEnum CORE_FEATURE_RELATIONSHIP_TYPE = eINSTANCE.getCOREFeatureRelationshipType();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.core.COREVisibilityType <em>CORE Visibility Type</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.core.COREVisibilityType
         * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCOREVisibilityType()
         * @generated
         */
        EEnum CORE_VISIBILITY_TYPE = eINSTANCE.getCOREVisibilityType();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.core.COREPartialityType <em>CORE Partiality Type</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.core.COREPartialityType
         * @see ca.mcgill.sel.core.impl.CorePackageImpl#getCOREPartialityType()
         * @generated
         */
        EEnum CORE_PARTIALITY_TYPE = eINSTANCE.getCOREPartialityType();

    }

} //CorePackage
