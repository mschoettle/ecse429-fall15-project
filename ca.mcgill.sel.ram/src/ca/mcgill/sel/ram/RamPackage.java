/**
 */
package ca.mcgill.sel.ram;

import ca.mcgill.sel.core.CorePackage;

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
 * @see ca.mcgill.sel.ram.RamFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/OCL/Import core='../../ca.mcgill.sel.core/model/CORE.ecore#/' ecore='http://www.eclipse.org/emf/2002/Ecore'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore invocationDelegates='http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot' settingDelegates='http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot' validationDelegates='http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot'"
 * @generated
 */
public interface RamPackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "ram";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://cs.mcgill.ca/sel/ram/3.0";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "ram";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    RamPackage eINSTANCE = ca.mcgill.sel.ram.impl.RamPackageImpl.init();

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.NamedElementImpl <em>Named Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.NamedElementImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getNamedElement()
     * @generated
     */
    int NAMED_ELEMENT = 6;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NAMED_ELEMENT__NAME = CorePackage.CORE_NAMED_ELEMENT__NAME;

    /**
     * The number of structural features of the '<em>Named Element</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NAMED_ELEMENT_FEATURE_COUNT = CorePackage.CORE_NAMED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.AspectImpl <em>Aspect</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.AspectImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getAspect()
     * @generated
     */
    int ASPECT = 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ASPECT__NAME = NAMED_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Model Reuses</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ASPECT__MODEL_REUSES = NAMED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Model Elements</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ASPECT__MODEL_ELEMENTS = NAMED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Realizes</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ASPECT__REALIZES = NAMED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Core Concern</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ASPECT__CORE_CONCERN = NAMED_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Structural View</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ASPECT__STRUCTURAL_VIEW = NAMED_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Message Views</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ASPECT__MESSAGE_VIEWS = NAMED_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Instantiations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ASPECT__INSTANTIATIONS = NAMED_ELEMENT_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Layout</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ASPECT__LAYOUT = NAMED_ELEMENT_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>State Views</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ASPECT__STATE_VIEWS = NAMED_ELEMENT_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Woven Aspects</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ASPECT__WOVEN_ASPECTS = NAMED_ELEMENT_FEATURE_COUNT + 9;

    /**
     * The number of structural features of the '<em>Aspect</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ASPECT_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 10;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.MappableElementImpl <em>Mappable Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.MappableElementImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getMappableElement()
     * @generated
     */
    int MAPPABLE_ELEMENT = 1;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MAPPABLE_ELEMENT__NAME = NAMED_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Partiality</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MAPPABLE_ELEMENT__PARTIALITY = NAMED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Visibility</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MAPPABLE_ELEMENT__VISIBILITY = NAMED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Mappable Element</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MAPPABLE_ELEMENT_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.StructuralViewImpl <em>Structural View</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.StructuralViewImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getStructuralView()
     * @generated
     */
    int STRUCTURAL_VIEW = 2;

    /**
     * The feature id for the '<em><b>Classes</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STRUCTURAL_VIEW__CLASSES = 0;

    /**
     * The feature id for the '<em><b>Associations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STRUCTURAL_VIEW__ASSOCIATIONS = 1;

    /**
     * The feature id for the '<em><b>Types</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STRUCTURAL_VIEW__TYPES = 2;

    /**
     * The number of structural features of the '<em>Structural View</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STRUCTURAL_VIEW_FEATURE_COUNT = 3;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.TypeImpl <em>Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.TypeImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getType()
     * @generated
     */
    int TYPE = 10;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TYPE__NAME = NAMED_ELEMENT__NAME;

    /**
     * The number of structural features of the '<em>Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TYPE_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.ObjectTypeImpl <em>Object Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.ObjectTypeImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getObjectType()
     * @generated
     */
    int OBJECT_TYPE = 13;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OBJECT_TYPE__NAME = TYPE__NAME;

    /**
     * The feature id for the '<em><b>Partiality</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OBJECT_TYPE__PARTIALITY = TYPE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Visibility</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OBJECT_TYPE__VISIBILITY = TYPE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Object Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OBJECT_TYPE_FEATURE_COUNT = TYPE_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.ClassifierImpl <em>Classifier</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.ClassifierImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getClassifier()
     * @generated
     */
    int CLASSIFIER = 54;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASSIFIER__NAME = OBJECT_TYPE__NAME;

    /**
     * The feature id for the '<em><b>Partiality</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASSIFIER__PARTIALITY = OBJECT_TYPE__PARTIALITY;

    /**
     * The feature id for the '<em><b>Visibility</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASSIFIER__VISIBILITY = OBJECT_TYPE__VISIBILITY;

    /**
     * The feature id for the '<em><b>Operations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASSIFIER__OPERATIONS = OBJECT_TYPE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Association Ends</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASSIFIER__ASSOCIATION_ENDS = OBJECT_TYPE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Type Parameters</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASSIFIER__TYPE_PARAMETERS = OBJECT_TYPE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Super Types</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASSIFIER__SUPER_TYPES = OBJECT_TYPE_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Classifier</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASSIFIER_FEATURE_COUNT = OBJECT_TYPE_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.ClassImpl <em>Class</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.ClassImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getClass_()
     * @generated
     */
    int CLASS = 3;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS__NAME = CLASSIFIER__NAME;

    /**
     * The feature id for the '<em><b>Partiality</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS__PARTIALITY = CLASSIFIER__PARTIALITY;

    /**
     * The feature id for the '<em><b>Visibility</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS__VISIBILITY = CLASSIFIER__VISIBILITY;

    /**
     * The feature id for the '<em><b>Operations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS__OPERATIONS = CLASSIFIER__OPERATIONS;

    /**
     * The feature id for the '<em><b>Association Ends</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS__ASSOCIATION_ENDS = CLASSIFIER__ASSOCIATION_ENDS;

    /**
     * The feature id for the '<em><b>Type Parameters</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS__TYPE_PARAMETERS = CLASSIFIER__TYPE_PARAMETERS;

    /**
     * The feature id for the '<em><b>Super Types</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS__SUPER_TYPES = CLASSIFIER__SUPER_TYPES;

    /**
     * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS__ATTRIBUTES = CLASSIFIER_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Abstract</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS__ABSTRACT = CLASSIFIER_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Class</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_FEATURE_COUNT = CLASSIFIER_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.TypedElementImpl <em>Typed Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.TypedElementImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getTypedElement()
     * @generated
     */
    int TYPED_ELEMENT = 60;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TYPED_ELEMENT__NAME = NAMED_ELEMENT__NAME;

    /**
     * The number of structural features of the '<em>Typed Element</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TYPED_ELEMENT_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.StructuralFeatureImpl <em>Structural Feature</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.StructuralFeatureImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getStructuralFeature()
     * @generated
     */
    int STRUCTURAL_FEATURE = 57;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STRUCTURAL_FEATURE__NAME = TYPED_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Static</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STRUCTURAL_FEATURE__STATIC = TYPED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Structural Feature</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STRUCTURAL_FEATURE_FEATURE_COUNT = TYPED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.PropertyImpl <em>Property</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.PropertyImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getProperty()
     * @generated
     */
    int PROPERTY = 59;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROPERTY__NAME = STRUCTURAL_FEATURE__NAME;

    /**
     * The feature id for the '<em><b>Static</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROPERTY__STATIC = STRUCTURAL_FEATURE__STATIC;

    /**
     * The feature id for the '<em><b>Lower Bound</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROPERTY__LOWER_BOUND = STRUCTURAL_FEATURE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Upper Bound</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROPERTY__UPPER_BOUND = STRUCTURAL_FEATURE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Reference Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROPERTY__REFERENCE_TYPE = STRUCTURAL_FEATURE_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Property</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROPERTY_FEATURE_COUNT = STRUCTURAL_FEATURE_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.AssociationEndImpl <em>Association End</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.AssociationEndImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getAssociationEnd()
     * @generated
     */
    int ASSOCIATION_END = 4;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ASSOCIATION_END__NAME = PROPERTY__NAME;

    /**
     * The feature id for the '<em><b>Static</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ASSOCIATION_END__STATIC = PROPERTY__STATIC;

    /**
     * The feature id for the '<em><b>Lower Bound</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ASSOCIATION_END__LOWER_BOUND = PROPERTY__LOWER_BOUND;

    /**
     * The feature id for the '<em><b>Upper Bound</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ASSOCIATION_END__UPPER_BOUND = PROPERTY__UPPER_BOUND;

    /**
     * The feature id for the '<em><b>Reference Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ASSOCIATION_END__REFERENCE_TYPE = PROPERTY__REFERENCE_TYPE;

    /**
     * The feature id for the '<em><b>Navigable</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ASSOCIATION_END__NAVIGABLE = PROPERTY_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Assoc</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ASSOCIATION_END__ASSOC = PROPERTY_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Classifier</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ASSOCIATION_END__CLASSIFIER = PROPERTY_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Feature Selection</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ASSOCIATION_END__FEATURE_SELECTION = PROPERTY_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Association End</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ASSOCIATION_END_FEATURE_COUNT = PROPERTY_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.AssociationImpl <em>Association</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.AssociationImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getAssociation()
     * @generated
     */
    int ASSOCIATION = 5;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ASSOCIATION__NAME = NAMED_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Ends</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ASSOCIATION__ENDS = NAMED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Association</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ASSOCIATION_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.InstantiationImpl <em>Instantiation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.InstantiationImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getInstantiation()
     * @generated
     */
    int INSTANTIATION = 7;

    /**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INSTANTIATION__SOURCE = CorePackage.CORE_BINDING__SOURCE;

    /**
     * The feature id for the '<em><b>Mappings</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INSTANTIATION__MAPPINGS = CorePackage.CORE_BINDING__MAPPINGS;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INSTANTIATION__TYPE = CorePackage.CORE_BINDING_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Instantiation</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INSTANTIATION_FEATURE_COUNT = CorePackage.CORE_BINDING_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.OperationImpl <em>Operation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.OperationImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getOperation()
     * @generated
     */
    int OPERATION = 8;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OPERATION__NAME = NAMED_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Partiality</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OPERATION__PARTIALITY = NAMED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Visibility</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OPERATION__VISIBILITY = NAMED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Abstract</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OPERATION__ABSTRACT = NAMED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Extended Visibility</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OPERATION__EXTENDED_VISIBILITY = NAMED_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Return Type</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OPERATION__RETURN_TYPE = NAMED_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OPERATION__PARAMETERS = NAMED_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Static</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OPERATION__STATIC = NAMED_ELEMENT_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Operation Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OPERATION__OPERATION_TYPE = NAMED_ELEMENT_FEATURE_COUNT + 7;

    /**
     * The number of structural features of the '<em>Operation</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OPERATION_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 8;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.AttributeImpl <em>Attribute</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.AttributeImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getAttribute()
     * @generated
     */
    int ATTRIBUTE = 9;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ATTRIBUTE__NAME = STRUCTURAL_FEATURE__NAME;

    /**
     * The feature id for the '<em><b>Static</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ATTRIBUTE__STATIC = STRUCTURAL_FEATURE__STATIC;

    /**
     * The feature id for the '<em><b>Partiality</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ATTRIBUTE__PARTIALITY = STRUCTURAL_FEATURE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Visibility</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ATTRIBUTE__VISIBILITY = STRUCTURAL_FEATURE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Type</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ATTRIBUTE__TYPE = STRUCTURAL_FEATURE_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Attribute</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ATTRIBUTE_FEATURE_COUNT = STRUCTURAL_FEATURE_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.ParameterImpl <em>Parameter</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.ParameterImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getParameter()
     * @generated
     */
    int PARAMETER = 11;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PARAMETER__NAME = TYPED_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Partiality</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PARAMETER__PARTIALITY = TYPED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Visibility</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PARAMETER__VISIBILITY = TYPED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Type</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PARAMETER__TYPE = TYPED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Parameter</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PARAMETER_FEATURE_COUNT = TYPED_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.PrimitiveTypeImpl <em>Primitive Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.PrimitiveTypeImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getPrimitiveType()
     * @generated
     */
    int PRIMITIVE_TYPE = 12;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PRIMITIVE_TYPE__NAME = OBJECT_TYPE__NAME;

    /**
     * The feature id for the '<em><b>Partiality</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PRIMITIVE_TYPE__PARTIALITY = OBJECT_TYPE__PARTIALITY;

    /**
     * The feature id for the '<em><b>Visibility</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PRIMITIVE_TYPE__VISIBILITY = OBJECT_TYPE__VISIBILITY;

    /**
     * The feature id for the '<em><b>Operations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PRIMITIVE_TYPE__OPERATIONS = OBJECT_TYPE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Association Ends</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PRIMITIVE_TYPE__ASSOCIATION_ENDS = OBJECT_TYPE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Type Parameters</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PRIMITIVE_TYPE__TYPE_PARAMETERS = OBJECT_TYPE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Super Types</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PRIMITIVE_TYPE__SUPER_TYPES = OBJECT_TYPE_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Instance Class Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PRIMITIVE_TYPE__INSTANCE_CLASS_NAME = OBJECT_TYPE_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Interface</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PRIMITIVE_TYPE__INTERFACE = OBJECT_TYPE_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '<em>Primitive Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PRIMITIVE_TYPE_FEATURE_COUNT = OBJECT_TYPE_FEATURE_COUNT + 6;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.RVoidImpl <em>RVoid</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.RVoidImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getRVoid()
     * @generated
     */
    int RVOID = 14;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RVOID__NAME = TYPE__NAME;

    /**
     * The number of structural features of the '<em>RVoid</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RVOID_FEATURE_COUNT = TYPE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.RBooleanImpl <em>RBoolean</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.RBooleanImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getRBoolean()
     * @generated
     */
    int RBOOLEAN = 15;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RBOOLEAN__NAME = PRIMITIVE_TYPE__NAME;

    /**
     * The feature id for the '<em><b>Partiality</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RBOOLEAN__PARTIALITY = PRIMITIVE_TYPE__PARTIALITY;

    /**
     * The feature id for the '<em><b>Visibility</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RBOOLEAN__VISIBILITY = PRIMITIVE_TYPE__VISIBILITY;

    /**
     * The feature id for the '<em><b>Operations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RBOOLEAN__OPERATIONS = PRIMITIVE_TYPE__OPERATIONS;

    /**
     * The feature id for the '<em><b>Association Ends</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RBOOLEAN__ASSOCIATION_ENDS = PRIMITIVE_TYPE__ASSOCIATION_ENDS;

    /**
     * The feature id for the '<em><b>Type Parameters</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RBOOLEAN__TYPE_PARAMETERS = PRIMITIVE_TYPE__TYPE_PARAMETERS;

    /**
     * The feature id for the '<em><b>Super Types</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RBOOLEAN__SUPER_TYPES = PRIMITIVE_TYPE__SUPER_TYPES;

    /**
     * The feature id for the '<em><b>Instance Class Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RBOOLEAN__INSTANCE_CLASS_NAME = PRIMITIVE_TYPE__INSTANCE_CLASS_NAME;

    /**
     * The feature id for the '<em><b>Interface</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RBOOLEAN__INTERFACE = PRIMITIVE_TYPE__INTERFACE;

    /**
     * The number of structural features of the '<em>RBoolean</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RBOOLEAN_FEATURE_COUNT = PRIMITIVE_TYPE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.RIntImpl <em>RInt</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.RIntImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getRInt()
     * @generated
     */
    int RINT = 16;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RINT__NAME = PRIMITIVE_TYPE__NAME;

    /**
     * The feature id for the '<em><b>Partiality</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RINT__PARTIALITY = PRIMITIVE_TYPE__PARTIALITY;

    /**
     * The feature id for the '<em><b>Visibility</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RINT__VISIBILITY = PRIMITIVE_TYPE__VISIBILITY;

    /**
     * The feature id for the '<em><b>Operations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RINT__OPERATIONS = PRIMITIVE_TYPE__OPERATIONS;

    /**
     * The feature id for the '<em><b>Association Ends</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RINT__ASSOCIATION_ENDS = PRIMITIVE_TYPE__ASSOCIATION_ENDS;

    /**
     * The feature id for the '<em><b>Type Parameters</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RINT__TYPE_PARAMETERS = PRIMITIVE_TYPE__TYPE_PARAMETERS;

    /**
     * The feature id for the '<em><b>Super Types</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RINT__SUPER_TYPES = PRIMITIVE_TYPE__SUPER_TYPES;

    /**
     * The feature id for the '<em><b>Instance Class Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RINT__INSTANCE_CLASS_NAME = PRIMITIVE_TYPE__INSTANCE_CLASS_NAME;

    /**
     * The feature id for the '<em><b>Interface</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RINT__INTERFACE = PRIMITIVE_TYPE__INTERFACE;

    /**
     * The number of structural features of the '<em>RInt</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RINT_FEATURE_COUNT = PRIMITIVE_TYPE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.RCharImpl <em>RChar</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.RCharImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getRChar()
     * @generated
     */
    int RCHAR = 17;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RCHAR__NAME = PRIMITIVE_TYPE__NAME;

    /**
     * The feature id for the '<em><b>Partiality</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RCHAR__PARTIALITY = PRIMITIVE_TYPE__PARTIALITY;

    /**
     * The feature id for the '<em><b>Visibility</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RCHAR__VISIBILITY = PRIMITIVE_TYPE__VISIBILITY;

    /**
     * The feature id for the '<em><b>Operations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RCHAR__OPERATIONS = PRIMITIVE_TYPE__OPERATIONS;

    /**
     * The feature id for the '<em><b>Association Ends</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RCHAR__ASSOCIATION_ENDS = PRIMITIVE_TYPE__ASSOCIATION_ENDS;

    /**
     * The feature id for the '<em><b>Type Parameters</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RCHAR__TYPE_PARAMETERS = PRIMITIVE_TYPE__TYPE_PARAMETERS;

    /**
     * The feature id for the '<em><b>Super Types</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RCHAR__SUPER_TYPES = PRIMITIVE_TYPE__SUPER_TYPES;

    /**
     * The feature id for the '<em><b>Instance Class Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RCHAR__INSTANCE_CLASS_NAME = PRIMITIVE_TYPE__INSTANCE_CLASS_NAME;

    /**
     * The feature id for the '<em><b>Interface</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RCHAR__INTERFACE = PRIMITIVE_TYPE__INTERFACE;

    /**
     * The number of structural features of the '<em>RChar</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RCHAR_FEATURE_COUNT = PRIMITIVE_TYPE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.RStringImpl <em>RString</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.RStringImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getRString()
     * @generated
     */
    int RSTRING = 18;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RSTRING__NAME = PRIMITIVE_TYPE__NAME;

    /**
     * The feature id for the '<em><b>Partiality</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RSTRING__PARTIALITY = PRIMITIVE_TYPE__PARTIALITY;

    /**
     * The feature id for the '<em><b>Visibility</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RSTRING__VISIBILITY = PRIMITIVE_TYPE__VISIBILITY;

    /**
     * The feature id for the '<em><b>Operations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RSTRING__OPERATIONS = PRIMITIVE_TYPE__OPERATIONS;

    /**
     * The feature id for the '<em><b>Association Ends</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RSTRING__ASSOCIATION_ENDS = PRIMITIVE_TYPE__ASSOCIATION_ENDS;

    /**
     * The feature id for the '<em><b>Type Parameters</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RSTRING__TYPE_PARAMETERS = PRIMITIVE_TYPE__TYPE_PARAMETERS;

    /**
     * The feature id for the '<em><b>Super Types</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RSTRING__SUPER_TYPES = PRIMITIVE_TYPE__SUPER_TYPES;

    /**
     * The feature id for the '<em><b>Instance Class Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RSTRING__INSTANCE_CLASS_NAME = PRIMITIVE_TYPE__INSTANCE_CLASS_NAME;

    /**
     * The feature id for the '<em><b>Interface</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RSTRING__INTERFACE = PRIMITIVE_TYPE__INTERFACE;

    /**
     * The number of structural features of the '<em>RString</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RSTRING_FEATURE_COUNT = PRIMITIVE_TYPE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.RAnyImpl <em>RAny</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.RAnyImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getRAny()
     * @generated
     */
    int RANY = 19;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RANY__NAME = TYPE__NAME;

    /**
     * The number of structural features of the '<em>RAny</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RANY_FEATURE_COUNT = TYPE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.REnumImpl <em>REnum</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.REnumImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getREnum()
     * @generated
     */
    int RENUM = 20;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RENUM__NAME = PRIMITIVE_TYPE__NAME;

    /**
     * The feature id for the '<em><b>Partiality</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RENUM__PARTIALITY = PRIMITIVE_TYPE__PARTIALITY;

    /**
     * The feature id for the '<em><b>Visibility</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RENUM__VISIBILITY = PRIMITIVE_TYPE__VISIBILITY;

    /**
     * The feature id for the '<em><b>Operations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RENUM__OPERATIONS = PRIMITIVE_TYPE__OPERATIONS;

    /**
     * The feature id for the '<em><b>Association Ends</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RENUM__ASSOCIATION_ENDS = PRIMITIVE_TYPE__ASSOCIATION_ENDS;

    /**
     * The feature id for the '<em><b>Type Parameters</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RENUM__TYPE_PARAMETERS = PRIMITIVE_TYPE__TYPE_PARAMETERS;

    /**
     * The feature id for the '<em><b>Super Types</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RENUM__SUPER_TYPES = PRIMITIVE_TYPE__SUPER_TYPES;

    /**
     * The feature id for the '<em><b>Instance Class Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RENUM__INSTANCE_CLASS_NAME = PRIMITIVE_TYPE__INSTANCE_CLASS_NAME;

    /**
     * The feature id for the '<em><b>Interface</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RENUM__INTERFACE = PRIMITIVE_TYPE__INTERFACE;

    /**
     * The feature id for the '<em><b>Literals</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RENUM__LITERALS = PRIMITIVE_TYPE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>REnum</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RENUM_FEATURE_COUNT = PRIMITIVE_TYPE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.REnumLiteralImpl <em>REnum Literal</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.REnumLiteralImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getREnumLiteral()
     * @generated
     */
    int RENUM_LITERAL = 21;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RENUM_LITERAL__NAME = NAMED_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Enum</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RENUM_LITERAL__ENUM = NAMED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>REnum Literal</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RENUM_LITERAL_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.AbstractMessageViewImpl <em>Abstract Message View</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.AbstractMessageViewImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getAbstractMessageView()
     * @generated
     */
    int ABSTRACT_MESSAGE_VIEW = 22;

    /**
     * The feature id for the '<em><b>Affected By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_MESSAGE_VIEW__AFFECTED_BY = 0;

    /**
     * The number of structural features of the '<em>Abstract Message View</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_MESSAGE_VIEW_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.MessageViewImpl <em>Message View</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.MessageViewImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getMessageView()
     * @generated
     */
    int MESSAGE_VIEW = 23;

    /**
     * The feature id for the '<em><b>Affected By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE_VIEW__AFFECTED_BY = ABSTRACT_MESSAGE_VIEW__AFFECTED_BY;

    /**
     * The feature id for the '<em><b>Specifies</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE_VIEW__SPECIFIES = ABSTRACT_MESSAGE_VIEW_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Specification</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE_VIEW__SPECIFICATION = ABSTRACT_MESSAGE_VIEW_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Message View</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE_VIEW_FEATURE_COUNT = ABSTRACT_MESSAGE_VIEW_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.MessageViewReferenceImpl <em>Message View Reference</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.MessageViewReferenceImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getMessageViewReference()
     * @generated
     */
    int MESSAGE_VIEW_REFERENCE = 24;

    /**
     * The feature id for the '<em><b>Affected By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE_VIEW_REFERENCE__AFFECTED_BY = ABSTRACT_MESSAGE_VIEW__AFFECTED_BY;

    /**
     * The feature id for the '<em><b>References</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE_VIEW_REFERENCE__REFERENCES = ABSTRACT_MESSAGE_VIEW_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Message View Reference</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE_VIEW_REFERENCE_FEATURE_COUNT = ABSTRACT_MESSAGE_VIEW_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.FragmentContainerImpl <em>Fragment Container</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.FragmentContainerImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getFragmentContainer()
     * @generated
     */
    int FRAGMENT_CONTAINER = 46;

    /**
     * The feature id for the '<em><b>Fragments</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FRAGMENT_CONTAINER__FRAGMENTS = 0;

    /**
     * The number of structural features of the '<em>Fragment Container</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FRAGMENT_CONTAINER_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.InteractionImpl <em>Interaction</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.InteractionImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getInteraction()
     * @generated
     */
    int INTERACTION = 25;

    /**
     * The feature id for the '<em><b>Fragments</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTERACTION__FRAGMENTS = FRAGMENT_CONTAINER__FRAGMENTS;

    /**
     * The feature id for the '<em><b>Lifelines</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTERACTION__LIFELINES = FRAGMENT_CONTAINER_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Messages</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTERACTION__MESSAGES = FRAGMENT_CONTAINER_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Properties</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTERACTION__PROPERTIES = FRAGMENT_CONTAINER_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Formal Gates</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTERACTION__FORMAL_GATES = FRAGMENT_CONTAINER_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Interaction</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTERACTION_FEATURE_COUNT = FRAGMENT_CONTAINER_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.AspectMessageViewImpl <em>Aspect Message View</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.AspectMessageViewImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getAspectMessageView()
     * @generated
     */
    int ASPECT_MESSAGE_VIEW = 26;

    /**
     * The feature id for the '<em><b>Affected By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ASPECT_MESSAGE_VIEW__AFFECTED_BY = ABSTRACT_MESSAGE_VIEW__AFFECTED_BY;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ASPECT_MESSAGE_VIEW__NAME = ABSTRACT_MESSAGE_VIEW_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Pointcut</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ASPECT_MESSAGE_VIEW__POINTCUT = ABSTRACT_MESSAGE_VIEW_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Advice</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ASPECT_MESSAGE_VIEW__ADVICE = ABSTRACT_MESSAGE_VIEW_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Aspect Message View</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ASPECT_MESSAGE_VIEW_FEATURE_COUNT = ABSTRACT_MESSAGE_VIEW_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.LifelineImpl <em>Lifeline</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.LifelineImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getLifeline()
     * @generated
     */
    int LIFELINE = 27;

    /**
     * The feature id for the '<em><b>Represents</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LIFELINE__REPRESENTS = 0;

    /**
     * The feature id for the '<em><b>Covered By</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LIFELINE__COVERED_BY = 1;

    /**
     * The number of structural features of the '<em>Lifeline</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LIFELINE_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.MessageImpl <em>Message</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.MessageImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getMessage()
     * @generated
     */
    int MESSAGE = 28;

    /**
     * The feature id for the '<em><b>Send Event</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE__SEND_EVENT = 0;

    /**
     * The feature id for the '<em><b>Receive Event</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE__RECEIVE_EVENT = 1;

    /**
     * The feature id for the '<em><b>Signature</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE__SIGNATURE = 2;

    /**
     * The feature id for the '<em><b>Assign To</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE__ASSIGN_TO = 3;

    /**
     * The feature id for the '<em><b>Message Sort</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE__MESSAGE_SORT = 4;

    /**
     * The feature id for the '<em><b>Arguments</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE__ARGUMENTS = 5;

    /**
     * The feature id for the '<em><b>Interaction</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE__INTERACTION = 6;

    /**
     * The feature id for the '<em><b>Returns</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE__RETURNS = 7;

    /**
     * The feature id for the '<em><b>Self Message</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE__SELF_MESSAGE = 8;

    /**
     * The feature id for the '<em><b>Local Properties</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE__LOCAL_PROPERTIES = 9;

    /**
     * The number of structural features of the '<em>Message</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE_FEATURE_COUNT = 10;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.MessageEndImpl <em>Message End</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.MessageEndImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getMessageEnd()
     * @generated
     */
    int MESSAGE_END = 29;

    /**
     * The feature id for the '<em><b>Message</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE_END__MESSAGE = 0;

    /**
     * The number of structural features of the '<em>Message End</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE_END_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.InteractionFragmentImpl <em>Interaction Fragment</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.InteractionFragmentImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getInteractionFragment()
     * @generated
     */
    int INTERACTION_FRAGMENT = 33;

    /**
     * The feature id for the '<em><b>Covered</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTERACTION_FRAGMENT__COVERED = 0;

    /**
     * The feature id for the '<em><b>Container</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTERACTION_FRAGMENT__CONTAINER = 1;

    /**
     * The number of structural features of the '<em>Interaction Fragment</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTERACTION_FRAGMENT_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.OccurrenceSpecificationImpl <em>Occurrence Specification</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.OccurrenceSpecificationImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getOccurrenceSpecification()
     * @generated
     */
    int OCCURRENCE_SPECIFICATION = 31;

    /**
     * The feature id for the '<em><b>Covered</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OCCURRENCE_SPECIFICATION__COVERED = INTERACTION_FRAGMENT__COVERED;

    /**
     * The feature id for the '<em><b>Container</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OCCURRENCE_SPECIFICATION__CONTAINER = INTERACTION_FRAGMENT__CONTAINER;

    /**
     * The number of structural features of the '<em>Occurrence Specification</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OCCURRENCE_SPECIFICATION_FEATURE_COUNT = INTERACTION_FRAGMENT_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.MessageOccurrenceSpecificationImpl <em>Message Occurrence Specification</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.MessageOccurrenceSpecificationImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getMessageOccurrenceSpecification()
     * @generated
     */
    int MESSAGE_OCCURRENCE_SPECIFICATION = 30;

    /**
     * The feature id for the '<em><b>Covered</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE_OCCURRENCE_SPECIFICATION__COVERED = OCCURRENCE_SPECIFICATION__COVERED;

    /**
     * The feature id for the '<em><b>Container</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE_OCCURRENCE_SPECIFICATION__CONTAINER = OCCURRENCE_SPECIFICATION__CONTAINER;

    /**
     * The feature id for the '<em><b>Message</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE_OCCURRENCE_SPECIFICATION__MESSAGE = OCCURRENCE_SPECIFICATION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Message Occurrence Specification</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE_OCCURRENCE_SPECIFICATION_FEATURE_COUNT = OCCURRENCE_SPECIFICATION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.DestructionOccurrenceSpecificationImpl <em>Destruction Occurrence Specification</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.DestructionOccurrenceSpecificationImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getDestructionOccurrenceSpecification()
     * @generated
     */
    int DESTRUCTION_OCCURRENCE_SPECIFICATION = 32;

    /**
     * The feature id for the '<em><b>Covered</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DESTRUCTION_OCCURRENCE_SPECIFICATION__COVERED = MESSAGE_OCCURRENCE_SPECIFICATION__COVERED;

    /**
     * The feature id for the '<em><b>Container</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DESTRUCTION_OCCURRENCE_SPECIFICATION__CONTAINER = MESSAGE_OCCURRENCE_SPECIFICATION__CONTAINER;

    /**
     * The feature id for the '<em><b>Message</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DESTRUCTION_OCCURRENCE_SPECIFICATION__MESSAGE = MESSAGE_OCCURRENCE_SPECIFICATION__MESSAGE;

    /**
     * The number of structural features of the '<em>Destruction Occurrence Specification</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DESTRUCTION_OCCURRENCE_SPECIFICATION_FEATURE_COUNT = MESSAGE_OCCURRENCE_SPECIFICATION_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.CombinedFragmentImpl <em>Combined Fragment</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.CombinedFragmentImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getCombinedFragment()
     * @generated
     */
    int COMBINED_FRAGMENT = 34;

    /**
     * The feature id for the '<em><b>Covered</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT__COVERED = INTERACTION_FRAGMENT__COVERED;

    /**
     * The feature id for the '<em><b>Container</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT__CONTAINER = INTERACTION_FRAGMENT__CONTAINER;

    /**
     * The feature id for the '<em><b>Interaction Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT__INTERACTION_OPERATOR = INTERACTION_FRAGMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Operands</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT__OPERANDS = INTERACTION_FRAGMENT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Combined Fragment</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_FEATURE_COUNT = INTERACTION_FRAGMENT_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.OriginalBehaviorExecutionImpl <em>Original Behavior Execution</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.OriginalBehaviorExecutionImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getOriginalBehaviorExecution()
     * @generated
     */
    int ORIGINAL_BEHAVIOR_EXECUTION = 35;

    /**
     * The feature id for the '<em><b>Covered</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ORIGINAL_BEHAVIOR_EXECUTION__COVERED = INTERACTION_FRAGMENT__COVERED;

    /**
     * The feature id for the '<em><b>Container</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ORIGINAL_BEHAVIOR_EXECUTION__CONTAINER = INTERACTION_FRAGMENT__CONTAINER;

    /**
     * The number of structural features of the '<em>Original Behavior Execution</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ORIGINAL_BEHAVIOR_EXECUTION_FEATURE_COUNT = INTERACTION_FRAGMENT_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.ExecutionStatementImpl <em>Execution Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.ExecutionStatementImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getExecutionStatement()
     * @generated
     */
    int EXECUTION_STATEMENT = 36;

    /**
     * The feature id for the '<em><b>Covered</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXECUTION_STATEMENT__COVERED = INTERACTION_FRAGMENT__COVERED;

    /**
     * The feature id for the '<em><b>Container</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXECUTION_STATEMENT__CONTAINER = INTERACTION_FRAGMENT__CONTAINER;

    /**
     * The feature id for the '<em><b>Specification</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXECUTION_STATEMENT__SPECIFICATION = INTERACTION_FRAGMENT_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Execution Statement</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXECUTION_STATEMENT_FEATURE_COUNT = INTERACTION_FRAGMENT_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.InteractionOperandImpl <em>Interaction Operand</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.InteractionOperandImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getInteractionOperand()
     * @generated
     */
    int INTERACTION_OPERAND = 37;

    /**
     * The feature id for the '<em><b>Fragments</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTERACTION_OPERAND__FRAGMENTS = FRAGMENT_CONTAINER__FRAGMENTS;

    /**
     * The feature id for the '<em><b>Interaction Constraint</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTERACTION_OPERAND__INTERACTION_CONSTRAINT = FRAGMENT_CONTAINER_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Interaction Operand</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTERACTION_OPERAND_FEATURE_COUNT = FRAGMENT_CONTAINER_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.ValueSpecificationImpl <em>Value Specification</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.ValueSpecificationImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getValueSpecification()
     * @generated
     */
    int VALUE_SPECIFICATION = 38;

    /**
     * The number of structural features of the '<em>Value Specification</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_SPECIFICATION_FEATURE_COUNT = 0;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.StructuralFeatureValueImpl <em>Structural Feature Value</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.StructuralFeatureValueImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getStructuralFeatureValue()
     * @generated
     */
    int STRUCTURAL_FEATURE_VALUE = 39;

    /**
     * The feature id for the '<em><b>Value</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STRUCTURAL_FEATURE_VALUE__VALUE = VALUE_SPECIFICATION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Structural Feature Value</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STRUCTURAL_FEATURE_VALUE_FEATURE_COUNT = VALUE_SPECIFICATION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.ParameterValueMappingImpl <em>Parameter Value Mapping</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.ParameterValueMappingImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getParameterValueMapping()
     * @generated
     */
    int PARAMETER_VALUE_MAPPING = 40;

    /**
     * The feature id for the '<em><b>Parameter</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PARAMETER_VALUE_MAPPING__PARAMETER = 0;

    /**
     * The feature id for the '<em><b>Value</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PARAMETER_VALUE_MAPPING__VALUE = 1;

    /**
     * The number of structural features of the '<em>Parameter Value Mapping</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PARAMETER_VALUE_MAPPING_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.ParameterValueImpl <em>Parameter Value</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.ParameterValueImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getParameterValue()
     * @generated
     */
    int PARAMETER_VALUE = 41;

    /**
     * The feature id for the '<em><b>Parameter</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PARAMETER_VALUE__PARAMETER = VALUE_SPECIFICATION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Parameter Value</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PARAMETER_VALUE_FEATURE_COUNT = VALUE_SPECIFICATION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.OpaqueExpressionImpl <em>Opaque Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.OpaqueExpressionImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getOpaqueExpression()
     * @generated
     */
    int OPAQUE_EXPRESSION = 42;

    /**
     * The feature id for the '<em><b>Body</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OPAQUE_EXPRESSION__BODY = VALUE_SPECIFICATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Language</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OPAQUE_EXPRESSION__LANGUAGE = VALUE_SPECIFICATION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Opaque Expression</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OPAQUE_EXPRESSION_FEATURE_COUNT = VALUE_SPECIFICATION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.LiteralSpecificationImpl <em>Literal Specification</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.LiteralSpecificationImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getLiteralSpecification()
     * @generated
     */
    int LITERAL_SPECIFICATION = 43;

    /**
     * The number of structural features of the '<em>Literal Specification</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LITERAL_SPECIFICATION_FEATURE_COUNT = VALUE_SPECIFICATION_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.LiteralStringImpl <em>Literal String</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.LiteralStringImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getLiteralString()
     * @generated
     */
    int LITERAL_STRING = 44;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LITERAL_STRING__VALUE = LITERAL_SPECIFICATION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Literal String</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LITERAL_STRING_FEATURE_COUNT = LITERAL_SPECIFICATION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.LiteralIntegerImpl <em>Literal Integer</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.LiteralIntegerImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getLiteralInteger()
     * @generated
     */
    int LITERAL_INTEGER = 45;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LITERAL_INTEGER__VALUE = LITERAL_SPECIFICATION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Literal Integer</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LITERAL_INTEGER_FEATURE_COUNT = LITERAL_SPECIFICATION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.RCollectionImpl <em>RCollection</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.RCollectionImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getRCollection()
     * @generated
     */
    int RCOLLECTION = 47;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RCOLLECTION__NAME = TYPE__NAME;

    /**
     * The feature id for the '<em><b>Partiality</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RCOLLECTION__PARTIALITY = TYPE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Visibility</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RCOLLECTION__VISIBILITY = TYPE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Operations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RCOLLECTION__OPERATIONS = TYPE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Association Ends</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RCOLLECTION__ASSOCIATION_ENDS = TYPE_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Type Parameters</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RCOLLECTION__TYPE_PARAMETERS = TYPE_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Super Types</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RCOLLECTION__SUPER_TYPES = TYPE_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Instance Class Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RCOLLECTION__INSTANCE_CLASS_NAME = TYPE_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Interface</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RCOLLECTION__INTERFACE = TYPE_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Type</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RCOLLECTION__TYPE = TYPE_FEATURE_COUNT + 8;

    /**
     * The number of structural features of the '<em>RCollection</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RCOLLECTION_FEATURE_COUNT = TYPE_FEATURE_COUNT + 9;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.RSetImpl <em>RSet</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.RSetImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getRSet()
     * @generated
     */
    int RSET = 48;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RSET__NAME = RCOLLECTION__NAME;

    /**
     * The feature id for the '<em><b>Partiality</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RSET__PARTIALITY = RCOLLECTION__PARTIALITY;

    /**
     * The feature id for the '<em><b>Visibility</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RSET__VISIBILITY = RCOLLECTION__VISIBILITY;

    /**
     * The feature id for the '<em><b>Operations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RSET__OPERATIONS = RCOLLECTION__OPERATIONS;

    /**
     * The feature id for the '<em><b>Association Ends</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RSET__ASSOCIATION_ENDS = RCOLLECTION__ASSOCIATION_ENDS;

    /**
     * The feature id for the '<em><b>Type Parameters</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RSET__TYPE_PARAMETERS = RCOLLECTION__TYPE_PARAMETERS;

    /**
     * The feature id for the '<em><b>Super Types</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RSET__SUPER_TYPES = RCOLLECTION__SUPER_TYPES;

    /**
     * The feature id for the '<em><b>Instance Class Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RSET__INSTANCE_CLASS_NAME = RCOLLECTION__INSTANCE_CLASS_NAME;

    /**
     * The feature id for the '<em><b>Interface</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RSET__INTERFACE = RCOLLECTION__INTERFACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RSET__TYPE = RCOLLECTION__TYPE;

    /**
     * The number of structural features of the '<em>RSet</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RSET_FEATURE_COUNT = RCOLLECTION_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.RSequenceImpl <em>RSequence</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.RSequenceImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getRSequence()
     * @generated
     */
    int RSEQUENCE = 49;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RSEQUENCE__NAME = RCOLLECTION__NAME;

    /**
     * The feature id for the '<em><b>Partiality</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RSEQUENCE__PARTIALITY = RCOLLECTION__PARTIALITY;

    /**
     * The feature id for the '<em><b>Visibility</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RSEQUENCE__VISIBILITY = RCOLLECTION__VISIBILITY;

    /**
     * The feature id for the '<em><b>Operations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RSEQUENCE__OPERATIONS = RCOLLECTION__OPERATIONS;

    /**
     * The feature id for the '<em><b>Association Ends</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RSEQUENCE__ASSOCIATION_ENDS = RCOLLECTION__ASSOCIATION_ENDS;

    /**
     * The feature id for the '<em><b>Type Parameters</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RSEQUENCE__TYPE_PARAMETERS = RCOLLECTION__TYPE_PARAMETERS;

    /**
     * The feature id for the '<em><b>Super Types</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RSEQUENCE__SUPER_TYPES = RCOLLECTION__SUPER_TYPES;

    /**
     * The feature id for the '<em><b>Instance Class Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RSEQUENCE__INSTANCE_CLASS_NAME = RCOLLECTION__INSTANCE_CLASS_NAME;

    /**
     * The feature id for the '<em><b>Interface</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RSEQUENCE__INTERFACE = RCOLLECTION__INTERFACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RSEQUENCE__TYPE = RCOLLECTION__TYPE;

    /**
     * The number of structural features of the '<em>RSequence</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RSEQUENCE_FEATURE_COUNT = RCOLLECTION_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.LayoutImpl <em>Layout</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.LayoutImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getLayout()
     * @generated
     */
    int LAYOUT = 50;

    /**
     * The feature id for the '<em><b>Containers</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LAYOUT__CONTAINERS = 0;

    /**
     * The number of structural features of the '<em>Layout</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LAYOUT_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.ContainerMapImpl <em>Container Map</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.ContainerMapImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getContainerMap()
     * @generated
     */
    int CONTAINER_MAP = 51;

    /**
     * The feature id for the '<em><b>Key</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONTAINER_MAP__KEY = 0;

    /**
     * The feature id for the '<em><b>Value</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONTAINER_MAP__VALUE = 1;

    /**
     * The number of structural features of the '<em>Container Map</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONTAINER_MAP_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.ElementMapImpl <em>Element Map</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.ElementMapImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getElementMap()
     * @generated
     */
    int ELEMENT_MAP = 52;

    /**
     * The feature id for the '<em><b>Key</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ELEMENT_MAP__KEY = 0;

    /**
     * The feature id for the '<em><b>Value</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ELEMENT_MAP__VALUE = 1;

    /**
     * The number of structural features of the '<em>Element Map</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ELEMENT_MAP_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.LayoutElementImpl <em>Layout Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.LayoutElementImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getLayoutElement()
     * @generated
     */
    int LAYOUT_ELEMENT = 53;

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
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.ImplementationClassImpl <em>Implementation Class</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.ImplementationClassImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getImplementationClass()
     * @generated
     */
    int IMPLEMENTATION_CLASS = 55;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPLEMENTATION_CLASS__NAME = CLASSIFIER__NAME;

    /**
     * The feature id for the '<em><b>Partiality</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPLEMENTATION_CLASS__PARTIALITY = CLASSIFIER__PARTIALITY;

    /**
     * The feature id for the '<em><b>Visibility</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPLEMENTATION_CLASS__VISIBILITY = CLASSIFIER__VISIBILITY;

    /**
     * The feature id for the '<em><b>Operations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPLEMENTATION_CLASS__OPERATIONS = CLASSIFIER__OPERATIONS;

    /**
     * The feature id for the '<em><b>Association Ends</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPLEMENTATION_CLASS__ASSOCIATION_ENDS = CLASSIFIER__ASSOCIATION_ENDS;

    /**
     * The feature id for the '<em><b>Type Parameters</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPLEMENTATION_CLASS__TYPE_PARAMETERS = CLASSIFIER__TYPE_PARAMETERS;

    /**
     * The feature id for the '<em><b>Super Types</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPLEMENTATION_CLASS__SUPER_TYPES = CLASSIFIER__SUPER_TYPES;

    /**
     * The feature id for the '<em><b>Instance Class Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPLEMENTATION_CLASS__INSTANCE_CLASS_NAME = CLASSIFIER_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Interface</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPLEMENTATION_CLASS__INTERFACE = CLASSIFIER_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Implementation Class</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPLEMENTATION_CLASS_FEATURE_COUNT = CLASSIFIER_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.TemporaryPropertyImpl <em>Temporary Property</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.TemporaryPropertyImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getTemporaryProperty()
     * @generated
     */
    int TEMPORARY_PROPERTY = 56;

    /**
     * The number of structural features of the '<em>Temporary Property</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEMPORARY_PROPERTY_FEATURE_COUNT = 0;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.ReferenceImpl <em>Reference</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.ReferenceImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getReference()
     * @generated
     */
    int REFERENCE = 58;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REFERENCE__NAME = PROPERTY__NAME;

    /**
     * The feature id for the '<em><b>Static</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REFERENCE__STATIC = PROPERTY__STATIC;

    /**
     * The feature id for the '<em><b>Lower Bound</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REFERENCE__LOWER_BOUND = PROPERTY__LOWER_BOUND;

    /**
     * The feature id for the '<em><b>Upper Bound</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REFERENCE__UPPER_BOUND = PROPERTY__UPPER_BOUND;

    /**
     * The feature id for the '<em><b>Reference Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REFERENCE__REFERENCE_TYPE = PROPERTY__REFERENCE_TYPE;

    /**
     * The feature id for the '<em><b>Type</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REFERENCE__TYPE = PROPERTY_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Reference</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REFERENCE_FEATURE_COUNT = PROPERTY_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.GateImpl <em>Gate</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.GateImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getGate()
     * @generated
     */
    int GATE = 61;

    /**
     * The feature id for the '<em><b>Message</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATE__MESSAGE = MESSAGE_END__MESSAGE;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATE__NAME = MESSAGE_END_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Gate</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATE_FEATURE_COUNT = MESSAGE_END_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.LiteralBooleanImpl <em>Literal Boolean</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.LiteralBooleanImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getLiteralBoolean()
     * @generated
     */
    int LITERAL_BOOLEAN = 62;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LITERAL_BOOLEAN__VALUE = LITERAL_SPECIFICATION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Literal Boolean</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LITERAL_BOOLEAN_FEATURE_COUNT = LITERAL_SPECIFICATION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.ClassifierMappingImpl <em>Classifier Mapping</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.ClassifierMappingImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getClassifierMapping()
     * @generated
     */
    int CLASSIFIER_MAPPING = 63;

    /**
     * The feature id for the '<em><b>To</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASSIFIER_MAPPING__TO = CorePackage.CORE_MAPPING__TO;

    /**
     * The feature id for the '<em><b>From</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASSIFIER_MAPPING__FROM = CorePackage.CORE_MAPPING__FROM;

    /**
     * The feature id for the '<em><b>Operation Mappings</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASSIFIER_MAPPING__OPERATION_MAPPINGS = CorePackage.CORE_MAPPING_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Attribute Mappings</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASSIFIER_MAPPING__ATTRIBUTE_MAPPINGS = CorePackage.CORE_MAPPING_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Classifier Mapping</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASSIFIER_MAPPING_FEATURE_COUNT = CorePackage.CORE_MAPPING_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.AttributeMappingImpl <em>Attribute Mapping</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.AttributeMappingImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getAttributeMapping()
     * @generated
     */
    int ATTRIBUTE_MAPPING = 64;

    /**
     * The feature id for the '<em><b>To</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ATTRIBUTE_MAPPING__TO = CorePackage.CORE_MAPPING__TO;

    /**
     * The feature id for the '<em><b>From</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ATTRIBUTE_MAPPING__FROM = CorePackage.CORE_MAPPING__FROM;

    /**
     * The number of structural features of the '<em>Attribute Mapping</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ATTRIBUTE_MAPPING_FEATURE_COUNT = CorePackage.CORE_MAPPING_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.OperationMappingImpl <em>Operation Mapping</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.OperationMappingImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getOperationMapping()
     * @generated
     */
    int OPERATION_MAPPING = 65;

    /**
     * The feature id for the '<em><b>To</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OPERATION_MAPPING__TO = CorePackage.CORE_MAPPING__TO;

    /**
     * The feature id for the '<em><b>From</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OPERATION_MAPPING__FROM = CorePackage.CORE_MAPPING__FROM;

    /**
     * The feature id for the '<em><b>Parameter Mappings</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OPERATION_MAPPING__PARAMETER_MAPPINGS = CorePackage.CORE_MAPPING_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Operation Mapping</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OPERATION_MAPPING_FEATURE_COUNT = CorePackage.CORE_MAPPING_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.ParameterMappingImpl <em>Parameter Mapping</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.ParameterMappingImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getParameterMapping()
     * @generated
     */
    int PARAMETER_MAPPING = 66;

    /**
     * The feature id for the '<em><b>To</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PARAMETER_MAPPING__TO = CorePackage.CORE_MAPPING__TO;

    /**
     * The feature id for the '<em><b>From</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PARAMETER_MAPPING__FROM = CorePackage.CORE_MAPPING__FROM;

    /**
     * The number of structural features of the '<em>Parameter Mapping</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PARAMETER_MAPPING_FEATURE_COUNT = CorePackage.CORE_MAPPING_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.StateViewImpl <em>State View</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.StateViewImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getStateView()
     * @generated
     */
    int STATE_VIEW = 67;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STATE_VIEW__NAME = NAMED_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>State Machines</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STATE_VIEW__STATE_MACHINES = NAMED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Specifies</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STATE_VIEW__SPECIFIES = NAMED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>State View</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STATE_VIEW_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.StateMachineImpl <em>State Machine</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.StateMachineImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getStateMachine()
     * @generated
     */
    int STATE_MACHINE = 68;

    /**
     * The feature id for the '<em><b>Start</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STATE_MACHINE__START = 0;

    /**
     * The feature id for the '<em><b>States</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STATE_MACHINE__STATES = 1;

    /**
     * The feature id for the '<em><b>Transitions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STATE_MACHINE__TRANSITIONS = 2;

    /**
     * The feature id for the '<em><b>Substitutions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STATE_MACHINE__SUBSTITUTIONS = 3;

    /**
     * The number of structural features of the '<em>State Machine</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STATE_MACHINE_FEATURE_COUNT = 4;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.TransitionImpl <em>Transition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.TransitionImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getTransition()
     * @generated
     */
    int TRANSITION = 69;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TRANSITION__NAME = NAMED_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>End State</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TRANSITION__END_STATE = NAMED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Start State</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TRANSITION__START_STATE = NAMED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Signature</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TRANSITION__SIGNATURE = NAMED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Guard</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TRANSITION__GUARD = NAMED_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Transition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TRANSITION_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.StateImpl <em>State</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.StateImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getState()
     * @generated
     */
    int STATE = 70;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STATE__NAME = NAMED_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Incomings</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STATE__INCOMINGS = NAMED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Outgoings</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STATE__OUTGOINGS = NAMED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>State</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STATE_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.RDoubleImpl <em>RDouble</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.RDoubleImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getRDouble()
     * @generated
     */
    int RDOUBLE = 71;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RDOUBLE__NAME = PRIMITIVE_TYPE__NAME;

    /**
     * The feature id for the '<em><b>Partiality</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RDOUBLE__PARTIALITY = PRIMITIVE_TYPE__PARTIALITY;

    /**
     * The feature id for the '<em><b>Visibility</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RDOUBLE__VISIBILITY = PRIMITIVE_TYPE__VISIBILITY;

    /**
     * The feature id for the '<em><b>Operations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RDOUBLE__OPERATIONS = PRIMITIVE_TYPE__OPERATIONS;

    /**
     * The feature id for the '<em><b>Association Ends</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RDOUBLE__ASSOCIATION_ENDS = PRIMITIVE_TYPE__ASSOCIATION_ENDS;

    /**
     * The feature id for the '<em><b>Type Parameters</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RDOUBLE__TYPE_PARAMETERS = PRIMITIVE_TYPE__TYPE_PARAMETERS;

    /**
     * The feature id for the '<em><b>Super Types</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RDOUBLE__SUPER_TYPES = PRIMITIVE_TYPE__SUPER_TYPES;

    /**
     * The feature id for the '<em><b>Instance Class Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RDOUBLE__INSTANCE_CLASS_NAME = PRIMITIVE_TYPE__INSTANCE_CLASS_NAME;

    /**
     * The feature id for the '<em><b>Interface</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RDOUBLE__INTERFACE = PRIMITIVE_TYPE__INTERFACE;

    /**
     * The number of structural features of the '<em>RDouble</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RDOUBLE_FEATURE_COUNT = PRIMITIVE_TYPE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.RFloatImpl <em>RFloat</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.RFloatImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getRFloat()
     * @generated
     */
    int RFLOAT = 72;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RFLOAT__NAME = PRIMITIVE_TYPE__NAME;

    /**
     * The feature id for the '<em><b>Partiality</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RFLOAT__PARTIALITY = PRIMITIVE_TYPE__PARTIALITY;

    /**
     * The feature id for the '<em><b>Visibility</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RFLOAT__VISIBILITY = PRIMITIVE_TYPE__VISIBILITY;

    /**
     * The feature id for the '<em><b>Operations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RFLOAT__OPERATIONS = PRIMITIVE_TYPE__OPERATIONS;

    /**
     * The feature id for the '<em><b>Association Ends</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RFLOAT__ASSOCIATION_ENDS = PRIMITIVE_TYPE__ASSOCIATION_ENDS;

    /**
     * The feature id for the '<em><b>Type Parameters</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RFLOAT__TYPE_PARAMETERS = PRIMITIVE_TYPE__TYPE_PARAMETERS;

    /**
     * The feature id for the '<em><b>Super Types</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RFLOAT__SUPER_TYPES = PRIMITIVE_TYPE__SUPER_TYPES;

    /**
     * The feature id for the '<em><b>Instance Class Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RFLOAT__INSTANCE_CLASS_NAME = PRIMITIVE_TYPE__INSTANCE_CLASS_NAME;

    /**
     * The feature id for the '<em><b>Interface</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RFLOAT__INTERFACE = PRIMITIVE_TYPE__INTERFACE;

    /**
     * The number of structural features of the '<em>RFloat</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RFLOAT_FEATURE_COUNT = PRIMITIVE_TYPE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.ConstraintImpl <em>Constraint</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.ConstraintImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getConstraint()
     * @generated
     */
    int CONSTRAINT = 73;

    /**
     * The feature id for the '<em><b>Specification</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONSTRAINT__SPECIFICATION = 0;

    /**
     * The number of structural features of the '<em>Constraint</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONSTRAINT_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.SubstitutionImpl <em>Substitution</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.SubstitutionImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getSubstitution()
     * @generated
     */
    int SUBSTITUTION = 74;

    /**
     * The number of structural features of the '<em>Substitution</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SUBSTITUTION_FEATURE_COUNT = 0;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.TransitionSubstitutionImpl <em>Transition Substitution</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.TransitionSubstitutionImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getTransitionSubstitution()
     * @generated
     */
    int TRANSITION_SUBSTITUTION = 75;

    /**
     * The feature id for the '<em><b>From</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TRANSITION_SUBSTITUTION__FROM = SUBSTITUTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>To</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TRANSITION_SUBSTITUTION__TO = SUBSTITUTION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Transition Substitution</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TRANSITION_SUBSTITUTION_FEATURE_COUNT = SUBSTITUTION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.TypeParameterImpl <em>Type Parameter</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.TypeParameterImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getTypeParameter()
     * @generated
     */
    int TYPE_PARAMETER = 76;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TYPE_PARAMETER__NAME = TYPE__NAME;

    /**
     * The feature id for the '<em><b>Generic Type</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TYPE_PARAMETER__GENERIC_TYPE = TYPE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Type Parameter</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TYPE_PARAMETER_FEATURE_COUNT = TYPE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.RLongImpl <em>RLong</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.RLongImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getRLong()
     * @generated
     */
    int RLONG = 77;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RLONG__NAME = PRIMITIVE_TYPE__NAME;

    /**
     * The feature id for the '<em><b>Partiality</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RLONG__PARTIALITY = PRIMITIVE_TYPE__PARTIALITY;

    /**
     * The feature id for the '<em><b>Visibility</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RLONG__VISIBILITY = PRIMITIVE_TYPE__VISIBILITY;

    /**
     * The feature id for the '<em><b>Operations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RLONG__OPERATIONS = PRIMITIVE_TYPE__OPERATIONS;

    /**
     * The feature id for the '<em><b>Association Ends</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RLONG__ASSOCIATION_ENDS = PRIMITIVE_TYPE__ASSOCIATION_ENDS;

    /**
     * The feature id for the '<em><b>Type Parameters</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RLONG__TYPE_PARAMETERS = PRIMITIVE_TYPE__TYPE_PARAMETERS;

    /**
     * The feature id for the '<em><b>Super Types</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RLONG__SUPER_TYPES = PRIMITIVE_TYPE__SUPER_TYPES;

    /**
     * The feature id for the '<em><b>Instance Class Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RLONG__INSTANCE_CLASS_NAME = PRIMITIVE_TYPE__INSTANCE_CLASS_NAME;

    /**
     * The feature id for the '<em><b>Interface</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RLONG__INTERFACE = PRIMITIVE_TYPE__INTERFACE;

    /**
     * The number of structural features of the '<em>RLong</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RLONG_FEATURE_COUNT = PRIMITIVE_TYPE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.RArrayImpl <em>RArray</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.RArrayImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getRArray()
     * @generated
     */
    int RARRAY = 78;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RARRAY__NAME = PRIMITIVE_TYPE__NAME;

    /**
     * The feature id for the '<em><b>Partiality</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RARRAY__PARTIALITY = PRIMITIVE_TYPE__PARTIALITY;

    /**
     * The feature id for the '<em><b>Visibility</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RARRAY__VISIBILITY = PRIMITIVE_TYPE__VISIBILITY;

    /**
     * The feature id for the '<em><b>Operations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RARRAY__OPERATIONS = PRIMITIVE_TYPE__OPERATIONS;

    /**
     * The feature id for the '<em><b>Association Ends</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RARRAY__ASSOCIATION_ENDS = PRIMITIVE_TYPE__ASSOCIATION_ENDS;

    /**
     * The feature id for the '<em><b>Type Parameters</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RARRAY__TYPE_PARAMETERS = PRIMITIVE_TYPE__TYPE_PARAMETERS;

    /**
     * The feature id for the '<em><b>Super Types</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RARRAY__SUPER_TYPES = PRIMITIVE_TYPE__SUPER_TYPES;

    /**
     * The feature id for the '<em><b>Instance Class Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RARRAY__INSTANCE_CLASS_NAME = PRIMITIVE_TYPE__INSTANCE_CLASS_NAME;

    /**
     * The feature id for the '<em><b>Interface</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RARRAY__INTERFACE = PRIMITIVE_TYPE__INTERFACE;

    /**
     * The feature id for the '<em><b>Size</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RARRAY__SIZE = PRIMITIVE_TYPE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Type</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RARRAY__TYPE = PRIMITIVE_TYPE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>RArray</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RARRAY_FEATURE_COUNT = PRIMITIVE_TYPE_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.TraceableImpl <em>Traceable</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.TraceableImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getTraceable()
     * @generated
     */
    int TRACEABLE = 79;

    /**
     * The number of structural features of the '<em>Traceable</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TRACEABLE_FEATURE_COUNT = 0;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.WovenAspectImpl <em>Woven Aspect</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.WovenAspectImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getWovenAspect()
     * @generated
     */
    int WOVEN_ASPECT = 80;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int WOVEN_ASPECT__NAME = NAMED_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Comes From</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int WOVEN_ASPECT__COMES_FROM = NAMED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Woven Elements</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int WOVEN_ASPECT__WOVEN_ELEMENTS = NAMED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int WOVEN_ASPECT__CHILDREN = NAMED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Woven Aspect</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int WOVEN_ASPECT_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.LiteralNullImpl <em>Literal Null</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.LiteralNullImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getLiteralNull()
     * @generated
     */
    int LITERAL_NULL = 81;

    /**
     * The number of structural features of the '<em>Literal Null</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LITERAL_NULL_FEATURE_COUNT = LITERAL_SPECIFICATION_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.EnumLiteralValueImpl <em>Enum Literal Value</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.EnumLiteralValueImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getEnumLiteralValue()
     * @generated
     */
    int ENUM_LITERAL_VALUE = 82;

    /**
     * The feature id for the '<em><b>Value</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENUM_LITERAL_VALUE__VALUE = VALUE_SPECIFICATION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Enum Literal Value</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENUM_LITERAL_VALUE_FEATURE_COUNT = VALUE_SPECIFICATION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.impl.AssignmentStatementImpl <em>Assignment Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.impl.AssignmentStatementImpl
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getAssignmentStatement()
     * @generated
     */
    int ASSIGNMENT_STATEMENT = 83;

    /**
     * The feature id for the '<em><b>Covered</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ASSIGNMENT_STATEMENT__COVERED = INTERACTION_FRAGMENT__COVERED;

    /**
     * The feature id for the '<em><b>Container</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ASSIGNMENT_STATEMENT__CONTAINER = INTERACTION_FRAGMENT__CONTAINER;

    /**
     * The feature id for the '<em><b>Assign To</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ASSIGNMENT_STATEMENT__ASSIGN_TO = INTERACTION_FRAGMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Value</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ASSIGNMENT_STATEMENT__VALUE = INTERACTION_FRAGMENT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Assignment Statement</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ASSIGNMENT_STATEMENT_FEATURE_COUNT = INTERACTION_FRAGMENT_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.RAMVisibilityType <em>RAM Visibility Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.RAMVisibilityType
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getRAMVisibilityType()
     * @generated
     */
    int RAM_VISIBILITY_TYPE = 84;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.ReferenceType <em>Reference Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.ReferenceType
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getReferenceType()
     * @generated
     */
    int REFERENCE_TYPE = 85;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.MessageSort <em>Message Sort</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.MessageSort
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getMessageSort()
     * @generated
     */
    int MESSAGE_SORT = 86;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.InteractionOperatorKind <em>Interaction Operator Kind</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.InteractionOperatorKind
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getInteractionOperatorKind()
     * @generated
     */
    int INTERACTION_OPERATOR_KIND = 87;

    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.InstantiationType <em>Instantiation Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.InstantiationType
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getInstantiationType()
     * @generated
     */
    int INSTANTIATION_TYPE = 88;


    /**
     * The meta object id for the '{@link ca.mcgill.sel.ram.OperationType <em>Operation Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ca.mcgill.sel.ram.OperationType
     * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getOperationType()
     * @generated
     */
    int OPERATION_TYPE = 89;


    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.Aspect <em>Aspect</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Aspect</em>'.
     * @see ca.mcgill.sel.ram.Aspect
     * @generated
     */
    EClass getAspect();

    /**
     * Returns the meta object for the containment reference '{@link ca.mcgill.sel.ram.Aspect#getStructuralView <em>Structural View</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Structural View</em>'.
     * @see ca.mcgill.sel.ram.Aspect#getStructuralView()
     * @see #getAspect()
     * @generated
     */
    EReference getAspect_StructuralView();

    /**
     * Returns the meta object for the containment reference list '{@link ca.mcgill.sel.ram.Aspect#getMessageViews <em>Message Views</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Message Views</em>'.
     * @see ca.mcgill.sel.ram.Aspect#getMessageViews()
     * @see #getAspect()
     * @generated
     */
    EReference getAspect_MessageViews();

    /**
     * Returns the meta object for the containment reference list '{@link ca.mcgill.sel.ram.Aspect#getInstantiations <em>Instantiations</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Instantiations</em>'.
     * @see ca.mcgill.sel.ram.Aspect#getInstantiations()
     * @see #getAspect()
     * @generated
     */
    EReference getAspect_Instantiations();

    /**
     * Returns the meta object for the containment reference '{@link ca.mcgill.sel.ram.Aspect#getLayout <em>Layout</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Layout</em>'.
     * @see ca.mcgill.sel.ram.Aspect#getLayout()
     * @see #getAspect()
     * @generated
     */
    EReference getAspect_Layout();

    /**
     * Returns the meta object for the containment reference list '{@link ca.mcgill.sel.ram.Aspect#getStateViews <em>State Views</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>State Views</em>'.
     * @see ca.mcgill.sel.ram.Aspect#getStateViews()
     * @see #getAspect()
     * @generated
     */
    EReference getAspect_StateViews();

    /**
     * Returns the meta object for the containment reference list '{@link ca.mcgill.sel.ram.Aspect#getWovenAspects <em>Woven Aspects</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Woven Aspects</em>'.
     * @see ca.mcgill.sel.ram.Aspect#getWovenAspects()
     * @see #getAspect()
     * @generated
     */
    EReference getAspect_WovenAspects();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.MappableElement <em>Mappable Element</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Mappable Element</em>'.
     * @see ca.mcgill.sel.ram.MappableElement
     * @generated
     */
    EClass getMappableElement();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.StructuralView <em>Structural View</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Structural View</em>'.
     * @see ca.mcgill.sel.ram.StructuralView
     * @generated
     */
    EClass getStructuralView();

    /**
     * Returns the meta object for the containment reference list '{@link ca.mcgill.sel.ram.StructuralView#getClasses <em>Classes</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Classes</em>'.
     * @see ca.mcgill.sel.ram.StructuralView#getClasses()
     * @see #getStructuralView()
     * @generated
     */
    EReference getStructuralView_Classes();

    /**
     * Returns the meta object for the containment reference list '{@link ca.mcgill.sel.ram.StructuralView#getAssociations <em>Associations</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Associations</em>'.
     * @see ca.mcgill.sel.ram.StructuralView#getAssociations()
     * @see #getStructuralView()
     * @generated
     */
    EReference getStructuralView_Associations();

    /**
     * Returns the meta object for the containment reference list '{@link ca.mcgill.sel.ram.StructuralView#getTypes <em>Types</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Types</em>'.
     * @see ca.mcgill.sel.ram.StructuralView#getTypes()
     * @see #getStructuralView()
     * @generated
     */
    EReference getStructuralView_Types();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.Class <em>Class</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Class</em>'.
     * @see ca.mcgill.sel.ram.Class
     * @generated
     */
    EClass getClass_();

    /**
     * Returns the meta object for the containment reference list '{@link ca.mcgill.sel.ram.Class#getAttributes <em>Attributes</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Attributes</em>'.
     * @see ca.mcgill.sel.ram.Class#getAttributes()
     * @see #getClass_()
     * @generated
     */
    EReference getClass_Attributes();

    /**
     * Returns the meta object for the attribute '{@link ca.mcgill.sel.ram.Class#isAbstract <em>Abstract</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Abstract</em>'.
     * @see ca.mcgill.sel.ram.Class#isAbstract()
     * @see #getClass_()
     * @generated
     */
    EAttribute getClass_Abstract();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.AssociationEnd <em>Association End</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Association End</em>'.
     * @see ca.mcgill.sel.ram.AssociationEnd
     * @generated
     */
    EClass getAssociationEnd();

    /**
     * Returns the meta object for the attribute '{@link ca.mcgill.sel.ram.AssociationEnd#isNavigable <em>Navigable</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Navigable</em>'.
     * @see ca.mcgill.sel.ram.AssociationEnd#isNavigable()
     * @see #getAssociationEnd()
     * @generated
     */
    EAttribute getAssociationEnd_Navigable();

    /**
     * Returns the meta object for the reference '{@link ca.mcgill.sel.ram.AssociationEnd#getAssoc <em>Assoc</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Assoc</em>'.
     * @see ca.mcgill.sel.ram.AssociationEnd#getAssoc()
     * @see #getAssociationEnd()
     * @generated
     */
    EReference getAssociationEnd_Assoc();

    /**
     * Returns the meta object for the container reference '{@link ca.mcgill.sel.ram.AssociationEnd#getClassifier <em>Classifier</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Classifier</em>'.
     * @see ca.mcgill.sel.ram.AssociationEnd#getClassifier()
     * @see #getAssociationEnd()
     * @generated
     */
    EReference getAssociationEnd_Classifier();

    /**
     * Returns the meta object for the reference '{@link ca.mcgill.sel.ram.AssociationEnd#getFeatureSelection <em>Feature Selection</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Feature Selection</em>'.
     * @see ca.mcgill.sel.ram.AssociationEnd#getFeatureSelection()
     * @see #getAssociationEnd()
     * @generated
     */
    EReference getAssociationEnd_FeatureSelection();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.Association <em>Association</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Association</em>'.
     * @see ca.mcgill.sel.ram.Association
     * @generated
     */
    EClass getAssociation();

    /**
     * Returns the meta object for the reference list '{@link ca.mcgill.sel.ram.Association#getEnds <em>Ends</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Ends</em>'.
     * @see ca.mcgill.sel.ram.Association#getEnds()
     * @see #getAssociation()
     * @generated
     */
    EReference getAssociation_Ends();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.NamedElement <em>Named Element</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Named Element</em>'.
     * @see ca.mcgill.sel.ram.NamedElement
     * @generated
     */
    EClass getNamedElement();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.Instantiation <em>Instantiation</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Instantiation</em>'.
     * @see ca.mcgill.sel.ram.Instantiation
     * @generated
     */
    EClass getInstantiation();

    /**
     * Returns the meta object for the attribute '{@link ca.mcgill.sel.ram.Instantiation#getType <em>Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see ca.mcgill.sel.ram.Instantiation#getType()
     * @see #getInstantiation()
     * @generated
     */
    EAttribute getInstantiation_Type();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.Operation <em>Operation</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Operation</em>'.
     * @see ca.mcgill.sel.ram.Operation
     * @generated
     */
    EClass getOperation();

    /**
     * Returns the meta object for the attribute '{@link ca.mcgill.sel.ram.Operation#isAbstract <em>Abstract</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Abstract</em>'.
     * @see ca.mcgill.sel.ram.Operation#isAbstract()
     * @see #getOperation()
     * @generated
     */
    EAttribute getOperation_Abstract();

    /**
     * Returns the meta object for the attribute '{@link ca.mcgill.sel.ram.Operation#getExtendedVisibility <em>Extended Visibility</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Extended Visibility</em>'.
     * @see ca.mcgill.sel.ram.Operation#getExtendedVisibility()
     * @see #getOperation()
     * @generated
     */
    EAttribute getOperation_ExtendedVisibility();

    /**
     * Returns the meta object for the reference '{@link ca.mcgill.sel.ram.Operation#getReturnType <em>Return Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Return Type</em>'.
     * @see ca.mcgill.sel.ram.Operation#getReturnType()
     * @see #getOperation()
     * @generated
     */
    EReference getOperation_ReturnType();

    /**
     * Returns the meta object for the containment reference list '{@link ca.mcgill.sel.ram.Operation#getParameters <em>Parameters</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Parameters</em>'.
     * @see ca.mcgill.sel.ram.Operation#getParameters()
     * @see #getOperation()
     * @generated
     */
    EReference getOperation_Parameters();

    /**
     * Returns the meta object for the attribute '{@link ca.mcgill.sel.ram.Operation#isStatic <em>Static</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Static</em>'.
     * @see ca.mcgill.sel.ram.Operation#isStatic()
     * @see #getOperation()
     * @generated
     */
    EAttribute getOperation_Static();

    /**
     * Returns the meta object for the attribute '{@link ca.mcgill.sel.ram.Operation#getOperationType <em>Operation Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Operation Type</em>'.
     * @see ca.mcgill.sel.ram.Operation#getOperationType()
     * @see #getOperation()
     * @generated
     */
    EAttribute getOperation_OperationType();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.Attribute <em>Attribute</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Attribute</em>'.
     * @see ca.mcgill.sel.ram.Attribute
     * @generated
     */
    EClass getAttribute();

    /**
     * Returns the meta object for the reference '{@link ca.mcgill.sel.ram.Attribute#getType <em>Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Type</em>'.
     * @see ca.mcgill.sel.ram.Attribute#getType()
     * @see #getAttribute()
     * @generated
     */
    EReference getAttribute_Type();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.Type <em>Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Type</em>'.
     * @see ca.mcgill.sel.ram.Type
     * @generated
     */
    EClass getType();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.Parameter <em>Parameter</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Parameter</em>'.
     * @see ca.mcgill.sel.ram.Parameter
     * @generated
     */
    EClass getParameter();

    /**
     * Returns the meta object for the reference '{@link ca.mcgill.sel.ram.Parameter#getType <em>Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Type</em>'.
     * @see ca.mcgill.sel.ram.Parameter#getType()
     * @see #getParameter()
     * @generated
     */
    EReference getParameter_Type();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.PrimitiveType <em>Primitive Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Primitive Type</em>'.
     * @see ca.mcgill.sel.ram.PrimitiveType
     * @generated
     */
    EClass getPrimitiveType();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.ObjectType <em>Object Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Object Type</em>'.
     * @see ca.mcgill.sel.ram.ObjectType
     * @generated
     */
    EClass getObjectType();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.RVoid <em>RVoid</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>RVoid</em>'.
     * @see ca.mcgill.sel.ram.RVoid
     * @generated
     */
    EClass getRVoid();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.RBoolean <em>RBoolean</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>RBoolean</em>'.
     * @see ca.mcgill.sel.ram.RBoolean
     * @generated
     */
    EClass getRBoolean();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.RInt <em>RInt</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>RInt</em>'.
     * @see ca.mcgill.sel.ram.RInt
     * @generated
     */
    EClass getRInt();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.RChar <em>RChar</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>RChar</em>'.
     * @see ca.mcgill.sel.ram.RChar
     * @generated
     */
    EClass getRChar();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.RString <em>RString</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>RString</em>'.
     * @see ca.mcgill.sel.ram.RString
     * @generated
     */
    EClass getRString();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.RAny <em>RAny</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>RAny</em>'.
     * @see ca.mcgill.sel.ram.RAny
     * @generated
     */
    EClass getRAny();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.REnum <em>REnum</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>REnum</em>'.
     * @see ca.mcgill.sel.ram.REnum
     * @generated
     */
    EClass getREnum();

    /**
     * Returns the meta object for the containment reference list '{@link ca.mcgill.sel.ram.REnum#getLiterals <em>Literals</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Literals</em>'.
     * @see ca.mcgill.sel.ram.REnum#getLiterals()
     * @see #getREnum()
     * @generated
     */
    EReference getREnum_Literals();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.REnumLiteral <em>REnum Literal</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>REnum Literal</em>'.
     * @see ca.mcgill.sel.ram.REnumLiteral
     * @generated
     */
    EClass getREnumLiteral();

    /**
     * Returns the meta object for the container reference '{@link ca.mcgill.sel.ram.REnumLiteral#getEnum <em>Enum</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Enum</em>'.
     * @see ca.mcgill.sel.ram.REnumLiteral#getEnum()
     * @see #getREnumLiteral()
     * @generated
     */
    EReference getREnumLiteral_Enum();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.AbstractMessageView <em>Abstract Message View</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Abstract Message View</em>'.
     * @see ca.mcgill.sel.ram.AbstractMessageView
     * @generated
     */
    EClass getAbstractMessageView();

    /**
     * Returns the meta object for the reference list '{@link ca.mcgill.sel.ram.AbstractMessageView#getAffectedBy <em>Affected By</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Affected By</em>'.
     * @see ca.mcgill.sel.ram.AbstractMessageView#getAffectedBy()
     * @see #getAbstractMessageView()
     * @generated
     */
    EReference getAbstractMessageView_AffectedBy();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.MessageView <em>Message View</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Message View</em>'.
     * @see ca.mcgill.sel.ram.MessageView
     * @generated
     */
    EClass getMessageView();

    /**
     * Returns the meta object for the reference '{@link ca.mcgill.sel.ram.MessageView#getSpecifies <em>Specifies</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Specifies</em>'.
     * @see ca.mcgill.sel.ram.MessageView#getSpecifies()
     * @see #getMessageView()
     * @generated
     */
    EReference getMessageView_Specifies();

    /**
     * Returns the meta object for the containment reference '{@link ca.mcgill.sel.ram.MessageView#getSpecification <em>Specification</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Specification</em>'.
     * @see ca.mcgill.sel.ram.MessageView#getSpecification()
     * @see #getMessageView()
     * @generated
     */
    EReference getMessageView_Specification();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.MessageViewReference <em>Message View Reference</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Message View Reference</em>'.
     * @see ca.mcgill.sel.ram.MessageViewReference
     * @generated
     */
    EClass getMessageViewReference();

    /**
     * Returns the meta object for the reference '{@link ca.mcgill.sel.ram.MessageViewReference#getReferences <em>References</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>References</em>'.
     * @see ca.mcgill.sel.ram.MessageViewReference#getReferences()
     * @see #getMessageViewReference()
     * @generated
     */
    EReference getMessageViewReference_References();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.Interaction <em>Interaction</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Interaction</em>'.
     * @see ca.mcgill.sel.ram.Interaction
     * @generated
     */
    EClass getInteraction();

    /**
     * Returns the meta object for the containment reference list '{@link ca.mcgill.sel.ram.Interaction#getLifelines <em>Lifelines</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Lifelines</em>'.
     * @see ca.mcgill.sel.ram.Interaction#getLifelines()
     * @see #getInteraction()
     * @generated
     */
    EReference getInteraction_Lifelines();

    /**
     * Returns the meta object for the containment reference list '{@link ca.mcgill.sel.ram.Interaction#getMessages <em>Messages</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Messages</em>'.
     * @see ca.mcgill.sel.ram.Interaction#getMessages()
     * @see #getInteraction()
     * @generated
     */
    EReference getInteraction_Messages();

    /**
     * Returns the meta object for the containment reference list '{@link ca.mcgill.sel.ram.Interaction#getProperties <em>Properties</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Properties</em>'.
     * @see ca.mcgill.sel.ram.Interaction#getProperties()
     * @see #getInteraction()
     * @generated
     */
    EReference getInteraction_Properties();

    /**
     * Returns the meta object for the containment reference list '{@link ca.mcgill.sel.ram.Interaction#getFormalGates <em>Formal Gates</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Formal Gates</em>'.
     * @see ca.mcgill.sel.ram.Interaction#getFormalGates()
     * @see #getInteraction()
     * @generated
     */
    EReference getInteraction_FormalGates();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.AspectMessageView <em>Aspect Message View</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Aspect Message View</em>'.
     * @see ca.mcgill.sel.ram.AspectMessageView
     * @generated
     */
    EClass getAspectMessageView();

    /**
     * Returns the meta object for the reference '{@link ca.mcgill.sel.ram.AspectMessageView#getPointcut <em>Pointcut</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Pointcut</em>'.
     * @see ca.mcgill.sel.ram.AspectMessageView#getPointcut()
     * @see #getAspectMessageView()
     * @generated
     */
    EReference getAspectMessageView_Pointcut();

    /**
     * Returns the meta object for the containment reference '{@link ca.mcgill.sel.ram.AspectMessageView#getAdvice <em>Advice</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Advice</em>'.
     * @see ca.mcgill.sel.ram.AspectMessageView#getAdvice()
     * @see #getAspectMessageView()
     * @generated
     */
    EReference getAspectMessageView_Advice();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.Lifeline <em>Lifeline</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Lifeline</em>'.
     * @see ca.mcgill.sel.ram.Lifeline
     * @generated
     */
    EClass getLifeline();

    /**
     * Returns the meta object for the reference '{@link ca.mcgill.sel.ram.Lifeline#getRepresents <em>Represents</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Represents</em>'.
     * @see ca.mcgill.sel.ram.Lifeline#getRepresents()
     * @see #getLifeline()
     * @generated
     */
    EReference getLifeline_Represents();

    /**
     * Returns the meta object for the reference list '{@link ca.mcgill.sel.ram.Lifeline#getCoveredBy <em>Covered By</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Covered By</em>'.
     * @see ca.mcgill.sel.ram.Lifeline#getCoveredBy()
     * @see #getLifeline()
     * @generated
     */
    EReference getLifeline_CoveredBy();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.Message <em>Message</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Message</em>'.
     * @see ca.mcgill.sel.ram.Message
     * @generated
     */
    EClass getMessage();

    /**
     * Returns the meta object for the reference '{@link ca.mcgill.sel.ram.Message#getSendEvent <em>Send Event</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Send Event</em>'.
     * @see ca.mcgill.sel.ram.Message#getSendEvent()
     * @see #getMessage()
     * @generated
     */
    EReference getMessage_SendEvent();

    /**
     * Returns the meta object for the reference '{@link ca.mcgill.sel.ram.Message#getReceiveEvent <em>Receive Event</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Receive Event</em>'.
     * @see ca.mcgill.sel.ram.Message#getReceiveEvent()
     * @see #getMessage()
     * @generated
     */
    EReference getMessage_ReceiveEvent();

    /**
     * Returns the meta object for the reference '{@link ca.mcgill.sel.ram.Message#getSignature <em>Signature</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Signature</em>'.
     * @see ca.mcgill.sel.ram.Message#getSignature()
     * @see #getMessage()
     * @generated
     */
    EReference getMessage_Signature();

    /**
     * Returns the meta object for the reference '{@link ca.mcgill.sel.ram.Message#getAssignTo <em>Assign To</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Assign To</em>'.
     * @see ca.mcgill.sel.ram.Message#getAssignTo()
     * @see #getMessage()
     * @generated
     */
    EReference getMessage_AssignTo();

    /**
     * Returns the meta object for the attribute '{@link ca.mcgill.sel.ram.Message#getMessageSort <em>Message Sort</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Message Sort</em>'.
     * @see ca.mcgill.sel.ram.Message#getMessageSort()
     * @see #getMessage()
     * @generated
     */
    EAttribute getMessage_MessageSort();

    /**
     * Returns the meta object for the containment reference list '{@link ca.mcgill.sel.ram.Message#getArguments <em>Arguments</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Arguments</em>'.
     * @see ca.mcgill.sel.ram.Message#getArguments()
     * @see #getMessage()
     * @generated
     */
    EReference getMessage_Arguments();

    /**
     * Returns the meta object for the container reference '{@link ca.mcgill.sel.ram.Message#getInteraction <em>Interaction</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Interaction</em>'.
     * @see ca.mcgill.sel.ram.Message#getInteraction()
     * @see #getMessage()
     * @generated
     */
    EReference getMessage_Interaction();

    /**
     * Returns the meta object for the containment reference '{@link ca.mcgill.sel.ram.Message#getReturns <em>Returns</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Returns</em>'.
     * @see ca.mcgill.sel.ram.Message#getReturns()
     * @see #getMessage()
     * @generated
     */
    EReference getMessage_Returns();

    /**
     * Returns the meta object for the attribute '{@link ca.mcgill.sel.ram.Message#isSelfMessage <em>Self Message</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Self Message</em>'.
     * @see ca.mcgill.sel.ram.Message#isSelfMessage()
     * @see #getMessage()
     * @generated
     */
    EAttribute getMessage_SelfMessage();

    /**
     * Returns the meta object for the containment reference list '{@link ca.mcgill.sel.ram.Message#getLocalProperties <em>Local Properties</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Local Properties</em>'.
     * @see ca.mcgill.sel.ram.Message#getLocalProperties()
     * @see #getMessage()
     * @generated
     */
    EReference getMessage_LocalProperties();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.MessageEnd <em>Message End</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Message End</em>'.
     * @see ca.mcgill.sel.ram.MessageEnd
     * @generated
     */
    EClass getMessageEnd();

    /**
     * Returns the meta object for the reference '{@link ca.mcgill.sel.ram.MessageEnd#getMessage <em>Message</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Message</em>'.
     * @see ca.mcgill.sel.ram.MessageEnd#getMessage()
     * @see #getMessageEnd()
     * @generated
     */
    EReference getMessageEnd_Message();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.MessageOccurrenceSpecification <em>Message Occurrence Specification</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Message Occurrence Specification</em>'.
     * @see ca.mcgill.sel.ram.MessageOccurrenceSpecification
     * @generated
     */
    EClass getMessageOccurrenceSpecification();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.OccurrenceSpecification <em>Occurrence Specification</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Occurrence Specification</em>'.
     * @see ca.mcgill.sel.ram.OccurrenceSpecification
     * @generated
     */
    EClass getOccurrenceSpecification();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.DestructionOccurrenceSpecification <em>Destruction Occurrence Specification</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Destruction Occurrence Specification</em>'.
     * @see ca.mcgill.sel.ram.DestructionOccurrenceSpecification
     * @generated
     */
    EClass getDestructionOccurrenceSpecification();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.InteractionFragment <em>Interaction Fragment</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Interaction Fragment</em>'.
     * @see ca.mcgill.sel.ram.InteractionFragment
     * @generated
     */
    EClass getInteractionFragment();

    /**
     * Returns the meta object for the reference list '{@link ca.mcgill.sel.ram.InteractionFragment#getCovered <em>Covered</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Covered</em>'.
     * @see ca.mcgill.sel.ram.InteractionFragment#getCovered()
     * @see #getInteractionFragment()
     * @generated
     */
    EReference getInteractionFragment_Covered();

    /**
     * Returns the meta object for the container reference '{@link ca.mcgill.sel.ram.InteractionFragment#getContainer <em>Container</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Container</em>'.
     * @see ca.mcgill.sel.ram.InteractionFragment#getContainer()
     * @see #getInteractionFragment()
     * @generated
     */
    EReference getInteractionFragment_Container();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.CombinedFragment <em>Combined Fragment</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Combined Fragment</em>'.
     * @see ca.mcgill.sel.ram.CombinedFragment
     * @generated
     */
    EClass getCombinedFragment();

    /**
     * Returns the meta object for the attribute '{@link ca.mcgill.sel.ram.CombinedFragment#getInteractionOperator <em>Interaction Operator</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Interaction Operator</em>'.
     * @see ca.mcgill.sel.ram.CombinedFragment#getInteractionOperator()
     * @see #getCombinedFragment()
     * @generated
     */
    EAttribute getCombinedFragment_InteractionOperator();

    /**
     * Returns the meta object for the containment reference list '{@link ca.mcgill.sel.ram.CombinedFragment#getOperands <em>Operands</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Operands</em>'.
     * @see ca.mcgill.sel.ram.CombinedFragment#getOperands()
     * @see #getCombinedFragment()
     * @generated
     */
    EReference getCombinedFragment_Operands();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.OriginalBehaviorExecution <em>Original Behavior Execution</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Original Behavior Execution</em>'.
     * @see ca.mcgill.sel.ram.OriginalBehaviorExecution
     * @generated
     */
    EClass getOriginalBehaviorExecution();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.ExecutionStatement <em>Execution Statement</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Execution Statement</em>'.
     * @see ca.mcgill.sel.ram.ExecutionStatement
     * @generated
     */
    EClass getExecutionStatement();

    /**
     * Returns the meta object for the containment reference '{@link ca.mcgill.sel.ram.ExecutionStatement#getSpecification <em>Specification</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Specification</em>'.
     * @see ca.mcgill.sel.ram.ExecutionStatement#getSpecification()
     * @see #getExecutionStatement()
     * @generated
     */
    EReference getExecutionStatement_Specification();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.InteractionOperand <em>Interaction Operand</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Interaction Operand</em>'.
     * @see ca.mcgill.sel.ram.InteractionOperand
     * @generated
     */
    EClass getInteractionOperand();

    /**
     * Returns the meta object for the containment reference '{@link ca.mcgill.sel.ram.InteractionOperand#getInteractionConstraint <em>Interaction Constraint</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Interaction Constraint</em>'.
     * @see ca.mcgill.sel.ram.InteractionOperand#getInteractionConstraint()
     * @see #getInteractionOperand()
     * @generated
     */
    EReference getInteractionOperand_InteractionConstraint();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.ValueSpecification <em>Value Specification</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Value Specification</em>'.
     * @see ca.mcgill.sel.ram.ValueSpecification
     * @generated
     */
    EClass getValueSpecification();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.StructuralFeatureValue <em>Structural Feature Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Structural Feature Value</em>'.
     * @see ca.mcgill.sel.ram.StructuralFeatureValue
     * @generated
     */
    EClass getStructuralFeatureValue();

    /**
     * Returns the meta object for the reference '{@link ca.mcgill.sel.ram.StructuralFeatureValue#getValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Value</em>'.
     * @see ca.mcgill.sel.ram.StructuralFeatureValue#getValue()
     * @see #getStructuralFeatureValue()
     * @generated
     */
    EReference getStructuralFeatureValue_Value();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.ParameterValueMapping <em>Parameter Value Mapping</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Parameter Value Mapping</em>'.
     * @see ca.mcgill.sel.ram.ParameterValueMapping
     * @generated
     */
    EClass getParameterValueMapping();

    /**
     * Returns the meta object for the reference '{@link ca.mcgill.sel.ram.ParameterValueMapping#getParameter <em>Parameter</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Parameter</em>'.
     * @see ca.mcgill.sel.ram.ParameterValueMapping#getParameter()
     * @see #getParameterValueMapping()
     * @generated
     */
    EReference getParameterValueMapping_Parameter();

    /**
     * Returns the meta object for the containment reference '{@link ca.mcgill.sel.ram.ParameterValueMapping#getValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Value</em>'.
     * @see ca.mcgill.sel.ram.ParameterValueMapping#getValue()
     * @see #getParameterValueMapping()
     * @generated
     */
    EReference getParameterValueMapping_Value();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.ParameterValue <em>Parameter Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Parameter Value</em>'.
     * @see ca.mcgill.sel.ram.ParameterValue
     * @generated
     */
    EClass getParameterValue();

    /**
     * Returns the meta object for the reference '{@link ca.mcgill.sel.ram.ParameterValue#getParameter <em>Parameter</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Parameter</em>'.
     * @see ca.mcgill.sel.ram.ParameterValue#getParameter()
     * @see #getParameterValue()
     * @generated
     */
    EReference getParameterValue_Parameter();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.OpaqueExpression <em>Opaque Expression</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Opaque Expression</em>'.
     * @see ca.mcgill.sel.ram.OpaqueExpression
     * @generated
     */
    EClass getOpaqueExpression();

    /**
     * Returns the meta object for the attribute '{@link ca.mcgill.sel.ram.OpaqueExpression#getBody <em>Body</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Body</em>'.
     * @see ca.mcgill.sel.ram.OpaqueExpression#getBody()
     * @see #getOpaqueExpression()
     * @generated
     */
    EAttribute getOpaqueExpression_Body();

    /**
     * Returns the meta object for the attribute '{@link ca.mcgill.sel.ram.OpaqueExpression#getLanguage <em>Language</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Language</em>'.
     * @see ca.mcgill.sel.ram.OpaqueExpression#getLanguage()
     * @see #getOpaqueExpression()
     * @generated
     */
    EAttribute getOpaqueExpression_Language();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.LiteralSpecification <em>Literal Specification</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Literal Specification</em>'.
     * @see ca.mcgill.sel.ram.LiteralSpecification
     * @generated
     */
    EClass getLiteralSpecification();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.LiteralString <em>Literal String</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Literal String</em>'.
     * @see ca.mcgill.sel.ram.LiteralString
     * @generated
     */
    EClass getLiteralString();

    /**
     * Returns the meta object for the attribute '{@link ca.mcgill.sel.ram.LiteralString#getValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see ca.mcgill.sel.ram.LiteralString#getValue()
     * @see #getLiteralString()
     * @generated
     */
    EAttribute getLiteralString_Value();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.LiteralInteger <em>Literal Integer</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Literal Integer</em>'.
     * @see ca.mcgill.sel.ram.LiteralInteger
     * @generated
     */
    EClass getLiteralInteger();

    /**
     * Returns the meta object for the attribute '{@link ca.mcgill.sel.ram.LiteralInteger#getValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see ca.mcgill.sel.ram.LiteralInteger#getValue()
     * @see #getLiteralInteger()
     * @generated
     */
    EAttribute getLiteralInteger_Value();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.FragmentContainer <em>Fragment Container</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Fragment Container</em>'.
     * @see ca.mcgill.sel.ram.FragmentContainer
     * @generated
     */
    EClass getFragmentContainer();

    /**
     * Returns the meta object for the containment reference list '{@link ca.mcgill.sel.ram.FragmentContainer#getFragments <em>Fragments</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Fragments</em>'.
     * @see ca.mcgill.sel.ram.FragmentContainer#getFragments()
     * @see #getFragmentContainer()
     * @generated
     */
    EReference getFragmentContainer_Fragments();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.RCollection <em>RCollection</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>RCollection</em>'.
     * @see ca.mcgill.sel.ram.RCollection
     * @generated
     */
    EClass getRCollection();

    /**
     * Returns the meta object for the reference '{@link ca.mcgill.sel.ram.RCollection#getType <em>Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Type</em>'.
     * @see ca.mcgill.sel.ram.RCollection#getType()
     * @see #getRCollection()
     * @generated
     */
    EReference getRCollection_Type();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.RSet <em>RSet</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>RSet</em>'.
     * @see ca.mcgill.sel.ram.RSet
     * @generated
     */
    EClass getRSet();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.RSequence <em>RSequence</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>RSequence</em>'.
     * @see ca.mcgill.sel.ram.RSequence
     * @generated
     */
    EClass getRSequence();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.Layout <em>Layout</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Layout</em>'.
     * @see ca.mcgill.sel.ram.Layout
     * @generated
     */
    EClass getLayout();

    /**
     * Returns the meta object for the map '{@link ca.mcgill.sel.ram.Layout#getContainers <em>Containers</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the map '<em>Containers</em>'.
     * @see ca.mcgill.sel.ram.Layout#getContainers()
     * @see #getLayout()
     * @generated
     */
    EReference getLayout_Containers();

    /**
     * Returns the meta object for class '{@link java.util.Map.Entry <em>Container Map</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Container Map</em>'.
     * @see java.util.Map.Entry
     * @model keyType="org.eclipse.emf.ecore.EObject" keyRequired="true"
     *        valueMapType="ca.mcgill.sel.ram.ElementMap<org.eclipse.emf.ecore.EObject, ca.mcgill.sel.ram.LayoutElement>"
     * @generated
     */
    EClass getContainerMap();

    /**
     * Returns the meta object for the reference '{@link java.util.Map.Entry <em>Key</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Key</em>'.
     * @see java.util.Map.Entry
     * @see #getContainerMap()
     * @generated
     */
    EReference getContainerMap_Key();

    /**
     * Returns the meta object for the map '{@link java.util.Map.Entry <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the map '<em>Value</em>'.
     * @see java.util.Map.Entry
     * @see #getContainerMap()
     * @generated
     */
    EReference getContainerMap_Value();

    /**
     * Returns the meta object for class '{@link java.util.Map.Entry <em>Element Map</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Element Map</em>'.
     * @see java.util.Map.Entry
     * @model keyType="org.eclipse.emf.ecore.EObject" keyRequired="true"
     *        valueType="ca.mcgill.sel.ram.LayoutElement" valueContainment="true" valueRequired="true"
     * @generated
     */
    EClass getElementMap();

    /**
     * Returns the meta object for the reference '{@link java.util.Map.Entry <em>Key</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Key</em>'.
     * @see java.util.Map.Entry
     * @see #getElementMap()
     * @generated
     */
    EReference getElementMap_Key();

    /**
     * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Value</em>'.
     * @see java.util.Map.Entry
     * @see #getElementMap()
     * @generated
     */
    EReference getElementMap_Value();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.LayoutElement <em>Layout Element</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Layout Element</em>'.
     * @see ca.mcgill.sel.ram.LayoutElement
     * @generated
     */
    EClass getLayoutElement();

    /**
     * Returns the meta object for the attribute '{@link ca.mcgill.sel.ram.LayoutElement#getX <em>X</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>X</em>'.
     * @see ca.mcgill.sel.ram.LayoutElement#getX()
     * @see #getLayoutElement()
     * @generated
     */
    EAttribute getLayoutElement_X();

    /**
     * Returns the meta object for the attribute '{@link ca.mcgill.sel.ram.LayoutElement#getY <em>Y</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Y</em>'.
     * @see ca.mcgill.sel.ram.LayoutElement#getY()
     * @see #getLayoutElement()
     * @generated
     */
    EAttribute getLayoutElement_Y();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.Classifier <em>Classifier</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Classifier</em>'.
     * @see ca.mcgill.sel.ram.Classifier
     * @generated
     */
    EClass getClassifier();

    /**
     * Returns the meta object for the containment reference list '{@link ca.mcgill.sel.ram.Classifier#getOperations <em>Operations</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Operations</em>'.
     * @see ca.mcgill.sel.ram.Classifier#getOperations()
     * @see #getClassifier()
     * @generated
     */
    EReference getClassifier_Operations();

    /**
     * Returns the meta object for the containment reference list '{@link ca.mcgill.sel.ram.Classifier#getAssociationEnds <em>Association Ends</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Association Ends</em>'.
     * @see ca.mcgill.sel.ram.Classifier#getAssociationEnds()
     * @see #getClassifier()
     * @generated
     */
    EReference getClassifier_AssociationEnds();

    /**
     * Returns the meta object for the containment reference list '{@link ca.mcgill.sel.ram.Classifier#getTypeParameters <em>Type Parameters</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Type Parameters</em>'.
     * @see ca.mcgill.sel.ram.Classifier#getTypeParameters()
     * @see #getClassifier()
     * @generated
     */
    EReference getClassifier_TypeParameters();

    /**
     * Returns the meta object for the reference list '{@link ca.mcgill.sel.ram.Classifier#getSuperTypes <em>Super Types</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Super Types</em>'.
     * @see ca.mcgill.sel.ram.Classifier#getSuperTypes()
     * @see #getClassifier()
     * @generated
     */
    EReference getClassifier_SuperTypes();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.ImplementationClass <em>Implementation Class</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Implementation Class</em>'.
     * @see ca.mcgill.sel.ram.ImplementationClass
     * @generated
     */
    EClass getImplementationClass();

    /**
     * Returns the meta object for the attribute '{@link ca.mcgill.sel.ram.ImplementationClass#getInstanceClassName <em>Instance Class Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Instance Class Name</em>'.
     * @see ca.mcgill.sel.ram.ImplementationClass#getInstanceClassName()
     * @see #getImplementationClass()
     * @generated
     */
    EAttribute getImplementationClass_InstanceClassName();

    /**
     * Returns the meta object for the attribute '{@link ca.mcgill.sel.ram.ImplementationClass#isInterface <em>Interface</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Interface</em>'.
     * @see ca.mcgill.sel.ram.ImplementationClass#isInterface()
     * @see #getImplementationClass()
     * @generated
     */
    EAttribute getImplementationClass_Interface();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.TemporaryProperty <em>Temporary Property</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Temporary Property</em>'.
     * @see ca.mcgill.sel.ram.TemporaryProperty
     * @generated
     */
    EClass getTemporaryProperty();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.StructuralFeature <em>Structural Feature</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Structural Feature</em>'.
     * @see ca.mcgill.sel.ram.StructuralFeature
     * @generated
     */
    EClass getStructuralFeature();

    /**
     * Returns the meta object for the attribute '{@link ca.mcgill.sel.ram.StructuralFeature#isStatic <em>Static</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Static</em>'.
     * @see ca.mcgill.sel.ram.StructuralFeature#isStatic()
     * @see #getStructuralFeature()
     * @generated
     */
    EAttribute getStructuralFeature_Static();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.Reference <em>Reference</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Reference</em>'.
     * @see ca.mcgill.sel.ram.Reference
     * @generated
     */
    EClass getReference();

    /**
     * Returns the meta object for the reference '{@link ca.mcgill.sel.ram.Reference#getType <em>Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Type</em>'.
     * @see ca.mcgill.sel.ram.Reference#getType()
     * @see #getReference()
     * @generated
     */
    EReference getReference_Type();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.Property <em>Property</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Property</em>'.
     * @see ca.mcgill.sel.ram.Property
     * @generated
     */
    EClass getProperty();

    /**
     * Returns the meta object for the attribute '{@link ca.mcgill.sel.ram.Property#getLowerBound <em>Lower Bound</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Lower Bound</em>'.
     * @see ca.mcgill.sel.ram.Property#getLowerBound()
     * @see #getProperty()
     * @generated
     */
    EAttribute getProperty_LowerBound();

    /**
     * Returns the meta object for the attribute '{@link ca.mcgill.sel.ram.Property#getUpperBound <em>Upper Bound</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Upper Bound</em>'.
     * @see ca.mcgill.sel.ram.Property#getUpperBound()
     * @see #getProperty()
     * @generated
     */
    EAttribute getProperty_UpperBound();

    /**
     * Returns the meta object for the attribute '{@link ca.mcgill.sel.ram.Property#getReferenceType <em>Reference Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Reference Type</em>'.
     * @see ca.mcgill.sel.ram.Property#getReferenceType()
     * @see #getProperty()
     * @generated
     */
    EAttribute getProperty_ReferenceType();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.TypedElement <em>Typed Element</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Typed Element</em>'.
     * @see ca.mcgill.sel.ram.TypedElement
     * @generated
     */
    EClass getTypedElement();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.Gate <em>Gate</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Gate</em>'.
     * @see ca.mcgill.sel.ram.Gate
     * @generated
     */
    EClass getGate();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.LiteralBoolean <em>Literal Boolean</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Literal Boolean</em>'.
     * @see ca.mcgill.sel.ram.LiteralBoolean
     * @generated
     */
    EClass getLiteralBoolean();

    /**
     * Returns the meta object for the attribute '{@link ca.mcgill.sel.ram.LiteralBoolean#isValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see ca.mcgill.sel.ram.LiteralBoolean#isValue()
     * @see #getLiteralBoolean()
     * @generated
     */
    EAttribute getLiteralBoolean_Value();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.ClassifierMapping <em>Classifier Mapping</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Classifier Mapping</em>'.
     * @see ca.mcgill.sel.ram.ClassifierMapping
     * @generated
     */
    EClass getClassifierMapping();

    /**
     * Returns the meta object for the containment reference list '{@link ca.mcgill.sel.ram.ClassifierMapping#getOperationMappings <em>Operation Mappings</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Operation Mappings</em>'.
     * @see ca.mcgill.sel.ram.ClassifierMapping#getOperationMappings()
     * @see #getClassifierMapping()
     * @generated
     */
    EReference getClassifierMapping_OperationMappings();

    /**
     * Returns the meta object for the containment reference list '{@link ca.mcgill.sel.ram.ClassifierMapping#getAttributeMappings <em>Attribute Mappings</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Attribute Mappings</em>'.
     * @see ca.mcgill.sel.ram.ClassifierMapping#getAttributeMappings()
     * @see #getClassifierMapping()
     * @generated
     */
    EReference getClassifierMapping_AttributeMappings();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.AttributeMapping <em>Attribute Mapping</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Attribute Mapping</em>'.
     * @see ca.mcgill.sel.ram.AttributeMapping
     * @generated
     */
    EClass getAttributeMapping();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.OperationMapping <em>Operation Mapping</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Operation Mapping</em>'.
     * @see ca.mcgill.sel.ram.OperationMapping
     * @generated
     */
    EClass getOperationMapping();

    /**
     * Returns the meta object for the containment reference list '{@link ca.mcgill.sel.ram.OperationMapping#getParameterMappings <em>Parameter Mappings</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Parameter Mappings</em>'.
     * @see ca.mcgill.sel.ram.OperationMapping#getParameterMappings()
     * @see #getOperationMapping()
     * @generated
     */
    EReference getOperationMapping_ParameterMappings();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.ParameterMapping <em>Parameter Mapping</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Parameter Mapping</em>'.
     * @see ca.mcgill.sel.ram.ParameterMapping
     * @generated
     */
    EClass getParameterMapping();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.StateView <em>State View</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>State View</em>'.
     * @see ca.mcgill.sel.ram.StateView
     * @generated
     */
    EClass getStateView();

    /**
     * Returns the meta object for the containment reference list '{@link ca.mcgill.sel.ram.StateView#getStateMachines <em>State Machines</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>State Machines</em>'.
     * @see ca.mcgill.sel.ram.StateView#getStateMachines()
     * @see #getStateView()
     * @generated
     */
    EReference getStateView_StateMachines();

    /**
     * Returns the meta object for the reference '{@link ca.mcgill.sel.ram.StateView#getSpecifies <em>Specifies</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Specifies</em>'.
     * @see ca.mcgill.sel.ram.StateView#getSpecifies()
     * @see #getStateView()
     * @generated
     */
    EReference getStateView_Specifies();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.StateMachine <em>State Machine</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>State Machine</em>'.
     * @see ca.mcgill.sel.ram.StateMachine
     * @generated
     */
    EClass getStateMachine();

    /**
     * Returns the meta object for the reference '{@link ca.mcgill.sel.ram.StateMachine#getStart <em>Start</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Start</em>'.
     * @see ca.mcgill.sel.ram.StateMachine#getStart()
     * @see #getStateMachine()
     * @generated
     */
    EReference getStateMachine_Start();

    /**
     * Returns the meta object for the containment reference list '{@link ca.mcgill.sel.ram.StateMachine#getStates <em>States</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>States</em>'.
     * @see ca.mcgill.sel.ram.StateMachine#getStates()
     * @see #getStateMachine()
     * @generated
     */
    EReference getStateMachine_States();

    /**
     * Returns the meta object for the containment reference list '{@link ca.mcgill.sel.ram.StateMachine#getTransitions <em>Transitions</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Transitions</em>'.
     * @see ca.mcgill.sel.ram.StateMachine#getTransitions()
     * @see #getStateMachine()
     * @generated
     */
    EReference getStateMachine_Transitions();

    /**
     * Returns the meta object for the containment reference list '{@link ca.mcgill.sel.ram.StateMachine#getSubstitutions <em>Substitutions</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Substitutions</em>'.
     * @see ca.mcgill.sel.ram.StateMachine#getSubstitutions()
     * @see #getStateMachine()
     * @generated
     */
    EReference getStateMachine_Substitutions();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.Transition <em>Transition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Transition</em>'.
     * @see ca.mcgill.sel.ram.Transition
     * @generated
     */
    EClass getTransition();

    /**
     * Returns the meta object for the reference '{@link ca.mcgill.sel.ram.Transition#getEndState <em>End State</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>End State</em>'.
     * @see ca.mcgill.sel.ram.Transition#getEndState()
     * @see #getTransition()
     * @generated
     */
    EReference getTransition_EndState();

    /**
     * Returns the meta object for the reference '{@link ca.mcgill.sel.ram.Transition#getStartState <em>Start State</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Start State</em>'.
     * @see ca.mcgill.sel.ram.Transition#getStartState()
     * @see #getTransition()
     * @generated
     */
    EReference getTransition_StartState();

    /**
     * Returns the meta object for the reference '{@link ca.mcgill.sel.ram.Transition#getSignature <em>Signature</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Signature</em>'.
     * @see ca.mcgill.sel.ram.Transition#getSignature()
     * @see #getTransition()
     * @generated
     */
    EReference getTransition_Signature();

    /**
     * Returns the meta object for the containment reference '{@link ca.mcgill.sel.ram.Transition#getGuard <em>Guard</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Guard</em>'.
     * @see ca.mcgill.sel.ram.Transition#getGuard()
     * @see #getTransition()
     * @generated
     */
    EReference getTransition_Guard();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.State <em>State</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>State</em>'.
     * @see ca.mcgill.sel.ram.State
     * @generated
     */
    EClass getState();

    /**
     * Returns the meta object for the reference list '{@link ca.mcgill.sel.ram.State#getIncomings <em>Incomings</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Incomings</em>'.
     * @see ca.mcgill.sel.ram.State#getIncomings()
     * @see #getState()
     * @generated
     */
    EReference getState_Incomings();

    /**
     * Returns the meta object for the reference list '{@link ca.mcgill.sel.ram.State#getOutgoings <em>Outgoings</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Outgoings</em>'.
     * @see ca.mcgill.sel.ram.State#getOutgoings()
     * @see #getState()
     * @generated
     */
    EReference getState_Outgoings();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.RDouble <em>RDouble</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>RDouble</em>'.
     * @see ca.mcgill.sel.ram.RDouble
     * @generated
     */
    EClass getRDouble();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.RFloat <em>RFloat</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>RFloat</em>'.
     * @see ca.mcgill.sel.ram.RFloat
     * @generated
     */
    EClass getRFloat();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.Constraint <em>Constraint</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Constraint</em>'.
     * @see ca.mcgill.sel.ram.Constraint
     * @generated
     */
    EClass getConstraint();

    /**
     * Returns the meta object for the containment reference '{@link ca.mcgill.sel.ram.Constraint#getSpecification <em>Specification</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Specification</em>'.
     * @see ca.mcgill.sel.ram.Constraint#getSpecification()
     * @see #getConstraint()
     * @generated
     */
    EReference getConstraint_Specification();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.Substitution <em>Substitution</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Substitution</em>'.
     * @see ca.mcgill.sel.ram.Substitution
     * @generated
     */
    EClass getSubstitution();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.TransitionSubstitution <em>Transition Substitution</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Transition Substitution</em>'.
     * @see ca.mcgill.sel.ram.TransitionSubstitution
     * @generated
     */
    EClass getTransitionSubstitution();

    /**
     * Returns the meta object for the reference '{@link ca.mcgill.sel.ram.TransitionSubstitution#getFrom <em>From</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>From</em>'.
     * @see ca.mcgill.sel.ram.TransitionSubstitution#getFrom()
     * @see #getTransitionSubstitution()
     * @generated
     */
    EReference getTransitionSubstitution_From();

    /**
     * Returns the meta object for the reference list '{@link ca.mcgill.sel.ram.TransitionSubstitution#getTo <em>To</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>To</em>'.
     * @see ca.mcgill.sel.ram.TransitionSubstitution#getTo()
     * @see #getTransitionSubstitution()
     * @generated
     */
    EReference getTransitionSubstitution_To();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.TypeParameter <em>Type Parameter</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Type Parameter</em>'.
     * @see ca.mcgill.sel.ram.TypeParameter
     * @generated
     */
    EClass getTypeParameter();

    /**
     * Returns the meta object for the reference '{@link ca.mcgill.sel.ram.TypeParameter#getGenericType <em>Generic Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Generic Type</em>'.
     * @see ca.mcgill.sel.ram.TypeParameter#getGenericType()
     * @see #getTypeParameter()
     * @generated
     */
    EReference getTypeParameter_GenericType();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.RLong <em>RLong</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>RLong</em>'.
     * @see ca.mcgill.sel.ram.RLong
     * @generated
     */
    EClass getRLong();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.RArray <em>RArray</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>RArray</em>'.
     * @see ca.mcgill.sel.ram.RArray
     * @generated
     */
    EClass getRArray();

    /**
     * Returns the meta object for the attribute '{@link ca.mcgill.sel.ram.RArray#getSize <em>Size</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Size</em>'.
     * @see ca.mcgill.sel.ram.RArray#getSize()
     * @see #getRArray()
     * @generated
     */
    EAttribute getRArray_Size();

    /**
     * Returns the meta object for the reference '{@link ca.mcgill.sel.ram.RArray#getType <em>Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Type</em>'.
     * @see ca.mcgill.sel.ram.RArray#getType()
     * @see #getRArray()
     * @generated
     */
    EReference getRArray_Type();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.Traceable <em>Traceable</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Traceable</em>'.
     * @see ca.mcgill.sel.ram.Traceable
     * @generated
     */
    EClass getTraceable();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.WovenAspect <em>Woven Aspect</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Woven Aspect</em>'.
     * @see ca.mcgill.sel.ram.WovenAspect
     * @generated
     */
    EClass getWovenAspect();

    /**
     * Returns the meta object for the reference '{@link ca.mcgill.sel.ram.WovenAspect#getComesFrom <em>Comes From</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Comes From</em>'.
     * @see ca.mcgill.sel.ram.WovenAspect#getComesFrom()
     * @see #getWovenAspect()
     * @generated
     */
    EReference getWovenAspect_ComesFrom();

    /**
     * Returns the meta object for the reference list '{@link ca.mcgill.sel.ram.WovenAspect#getWovenElements <em>Woven Elements</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Woven Elements</em>'.
     * @see ca.mcgill.sel.ram.WovenAspect#getWovenElements()
     * @see #getWovenAspect()
     * @generated
     */
    EReference getWovenAspect_WovenElements();

    /**
     * Returns the meta object for the containment reference list '{@link ca.mcgill.sel.ram.WovenAspect#getChildren <em>Children</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Children</em>'.
     * @see ca.mcgill.sel.ram.WovenAspect#getChildren()
     * @see #getWovenAspect()
     * @generated
     */
    EReference getWovenAspect_Children();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.LiteralNull <em>Literal Null</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Literal Null</em>'.
     * @see ca.mcgill.sel.ram.LiteralNull
     * @generated
     */
    EClass getLiteralNull();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.EnumLiteralValue <em>Enum Literal Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Enum Literal Value</em>'.
     * @see ca.mcgill.sel.ram.EnumLiteralValue
     * @generated
     */
    EClass getEnumLiteralValue();

    /**
     * Returns the meta object for the reference '{@link ca.mcgill.sel.ram.EnumLiteralValue#getValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Value</em>'.
     * @see ca.mcgill.sel.ram.EnumLiteralValue#getValue()
     * @see #getEnumLiteralValue()
     * @generated
     */
    EReference getEnumLiteralValue_Value();

    /**
     * Returns the meta object for class '{@link ca.mcgill.sel.ram.AssignmentStatement <em>Assignment Statement</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Assignment Statement</em>'.
     * @see ca.mcgill.sel.ram.AssignmentStatement
     * @generated
     */
    EClass getAssignmentStatement();

    /**
     * Returns the meta object for the reference '{@link ca.mcgill.sel.ram.AssignmentStatement#getAssignTo <em>Assign To</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Assign To</em>'.
     * @see ca.mcgill.sel.ram.AssignmentStatement#getAssignTo()
     * @see #getAssignmentStatement()
     * @generated
     */
    EReference getAssignmentStatement_AssignTo();

    /**
     * Returns the meta object for the containment reference '{@link ca.mcgill.sel.ram.AssignmentStatement#getValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Value</em>'.
     * @see ca.mcgill.sel.ram.AssignmentStatement#getValue()
     * @see #getAssignmentStatement()
     * @generated
     */
    EReference getAssignmentStatement_Value();

    /**
     * Returns the meta object for enum '{@link ca.mcgill.sel.ram.RAMVisibilityType <em>RAM Visibility Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>RAM Visibility Type</em>'.
     * @see ca.mcgill.sel.ram.RAMVisibilityType
     * @generated
     */
    EEnum getRAMVisibilityType();

    /**
     * Returns the meta object for enum '{@link ca.mcgill.sel.ram.ReferenceType <em>Reference Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Reference Type</em>'.
     * @see ca.mcgill.sel.ram.ReferenceType
     * @generated
     */
    EEnum getReferenceType();

    /**
     * Returns the meta object for enum '{@link ca.mcgill.sel.ram.MessageSort <em>Message Sort</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Message Sort</em>'.
     * @see ca.mcgill.sel.ram.MessageSort
     * @generated
     */
    EEnum getMessageSort();

    /**
     * Returns the meta object for enum '{@link ca.mcgill.sel.ram.InteractionOperatorKind <em>Interaction Operator Kind</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Interaction Operator Kind</em>'.
     * @see ca.mcgill.sel.ram.InteractionOperatorKind
     * @generated
     */
    EEnum getInteractionOperatorKind();

    /**
     * Returns the meta object for enum '{@link ca.mcgill.sel.ram.InstantiationType <em>Instantiation Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Instantiation Type</em>'.
     * @see ca.mcgill.sel.ram.InstantiationType
     * @generated
     */
    EEnum getInstantiationType();

    /**
     * Returns the meta object for enum '{@link ca.mcgill.sel.ram.OperationType <em>Operation Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Operation Type</em>'.
     * @see ca.mcgill.sel.ram.OperationType
     * @generated
     */
    EEnum getOperationType();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    RamFactory getRamFactory();

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
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.AspectImpl <em>Aspect</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.AspectImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getAspect()
         * @generated
         */
        EClass ASPECT = eINSTANCE.getAspect();

        /**
         * The meta object literal for the '<em><b>Structural View</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ASPECT__STRUCTURAL_VIEW = eINSTANCE.getAspect_StructuralView();

        /**
         * The meta object literal for the '<em><b>Message Views</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ASPECT__MESSAGE_VIEWS = eINSTANCE.getAspect_MessageViews();

        /**
         * The meta object literal for the '<em><b>Instantiations</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ASPECT__INSTANTIATIONS = eINSTANCE.getAspect_Instantiations();

        /**
         * The meta object literal for the '<em><b>Layout</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ASPECT__LAYOUT = eINSTANCE.getAspect_Layout();

        /**
         * The meta object literal for the '<em><b>State Views</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ASPECT__STATE_VIEWS = eINSTANCE.getAspect_StateViews();

        /**
         * The meta object literal for the '<em><b>Woven Aspects</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ASPECT__WOVEN_ASPECTS = eINSTANCE.getAspect_WovenAspects();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.MappableElementImpl <em>Mappable Element</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.MappableElementImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getMappableElement()
         * @generated
         */
        EClass MAPPABLE_ELEMENT = eINSTANCE.getMappableElement();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.StructuralViewImpl <em>Structural View</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.StructuralViewImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getStructuralView()
         * @generated
         */
        EClass STRUCTURAL_VIEW = eINSTANCE.getStructuralView();

        /**
         * The meta object literal for the '<em><b>Classes</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference STRUCTURAL_VIEW__CLASSES = eINSTANCE.getStructuralView_Classes();

        /**
         * The meta object literal for the '<em><b>Associations</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference STRUCTURAL_VIEW__ASSOCIATIONS = eINSTANCE.getStructuralView_Associations();

        /**
         * The meta object literal for the '<em><b>Types</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference STRUCTURAL_VIEW__TYPES = eINSTANCE.getStructuralView_Types();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.ClassImpl <em>Class</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.ClassImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getClass_()
         * @generated
         */
        EClass CLASS = eINSTANCE.getClass_();

        /**
         * The meta object literal for the '<em><b>Attributes</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CLASS__ATTRIBUTES = eINSTANCE.getClass_Attributes();

        /**
         * The meta object literal for the '<em><b>Abstract</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute CLASS__ABSTRACT = eINSTANCE.getClass_Abstract();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.AssociationEndImpl <em>Association End</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.AssociationEndImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getAssociationEnd()
         * @generated
         */
        EClass ASSOCIATION_END = eINSTANCE.getAssociationEnd();

        /**
         * The meta object literal for the '<em><b>Navigable</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ASSOCIATION_END__NAVIGABLE = eINSTANCE.getAssociationEnd_Navigable();

        /**
         * The meta object literal for the '<em><b>Assoc</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ASSOCIATION_END__ASSOC = eINSTANCE.getAssociationEnd_Assoc();

        /**
         * The meta object literal for the '<em><b>Classifier</b></em>' container reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ASSOCIATION_END__CLASSIFIER = eINSTANCE.getAssociationEnd_Classifier();

        /**
         * The meta object literal for the '<em><b>Feature Selection</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ASSOCIATION_END__FEATURE_SELECTION = eINSTANCE.getAssociationEnd_FeatureSelection();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.AssociationImpl <em>Association</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.AssociationImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getAssociation()
         * @generated
         */
        EClass ASSOCIATION = eINSTANCE.getAssociation();

        /**
         * The meta object literal for the '<em><b>Ends</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ASSOCIATION__ENDS = eINSTANCE.getAssociation_Ends();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.NamedElementImpl <em>Named Element</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.NamedElementImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getNamedElement()
         * @generated
         */
        EClass NAMED_ELEMENT = eINSTANCE.getNamedElement();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.InstantiationImpl <em>Instantiation</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.InstantiationImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getInstantiation()
         * @generated
         */
        EClass INSTANTIATION = eINSTANCE.getInstantiation();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute INSTANTIATION__TYPE = eINSTANCE.getInstantiation_Type();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.OperationImpl <em>Operation</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.OperationImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getOperation()
         * @generated
         */
        EClass OPERATION = eINSTANCE.getOperation();

        /**
         * The meta object literal for the '<em><b>Abstract</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute OPERATION__ABSTRACT = eINSTANCE.getOperation_Abstract();

        /**
         * The meta object literal for the '<em><b>Extended Visibility</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute OPERATION__EXTENDED_VISIBILITY = eINSTANCE.getOperation_ExtendedVisibility();

        /**
         * The meta object literal for the '<em><b>Return Type</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference OPERATION__RETURN_TYPE = eINSTANCE.getOperation_ReturnType();

        /**
         * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference OPERATION__PARAMETERS = eINSTANCE.getOperation_Parameters();

        /**
         * The meta object literal for the '<em><b>Static</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute OPERATION__STATIC = eINSTANCE.getOperation_Static();

        /**
         * The meta object literal for the '<em><b>Operation Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute OPERATION__OPERATION_TYPE = eINSTANCE.getOperation_OperationType();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.AttributeImpl <em>Attribute</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.AttributeImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getAttribute()
         * @generated
         */
        EClass ATTRIBUTE = eINSTANCE.getAttribute();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ATTRIBUTE__TYPE = eINSTANCE.getAttribute_Type();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.TypeImpl <em>Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.TypeImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getType()
         * @generated
         */
        EClass TYPE = eINSTANCE.getType();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.ParameterImpl <em>Parameter</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.ParameterImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getParameter()
         * @generated
         */
        EClass PARAMETER = eINSTANCE.getParameter();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference PARAMETER__TYPE = eINSTANCE.getParameter_Type();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.PrimitiveTypeImpl <em>Primitive Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.PrimitiveTypeImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getPrimitiveType()
         * @generated
         */
        EClass PRIMITIVE_TYPE = eINSTANCE.getPrimitiveType();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.ObjectTypeImpl <em>Object Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.ObjectTypeImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getObjectType()
         * @generated
         */
        EClass OBJECT_TYPE = eINSTANCE.getObjectType();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.RVoidImpl <em>RVoid</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.RVoidImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getRVoid()
         * @generated
         */
        EClass RVOID = eINSTANCE.getRVoid();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.RBooleanImpl <em>RBoolean</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.RBooleanImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getRBoolean()
         * @generated
         */
        EClass RBOOLEAN = eINSTANCE.getRBoolean();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.RIntImpl <em>RInt</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.RIntImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getRInt()
         * @generated
         */
        EClass RINT = eINSTANCE.getRInt();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.RCharImpl <em>RChar</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.RCharImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getRChar()
         * @generated
         */
        EClass RCHAR = eINSTANCE.getRChar();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.RStringImpl <em>RString</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.RStringImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getRString()
         * @generated
         */
        EClass RSTRING = eINSTANCE.getRString();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.RAnyImpl <em>RAny</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.RAnyImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getRAny()
         * @generated
         */
        EClass RANY = eINSTANCE.getRAny();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.REnumImpl <em>REnum</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.REnumImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getREnum()
         * @generated
         */
        EClass RENUM = eINSTANCE.getREnum();

        /**
         * The meta object literal for the '<em><b>Literals</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference RENUM__LITERALS = eINSTANCE.getREnum_Literals();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.REnumLiteralImpl <em>REnum Literal</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.REnumLiteralImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getREnumLiteral()
         * @generated
         */
        EClass RENUM_LITERAL = eINSTANCE.getREnumLiteral();

        /**
         * The meta object literal for the '<em><b>Enum</b></em>' container reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference RENUM_LITERAL__ENUM = eINSTANCE.getREnumLiteral_Enum();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.AbstractMessageViewImpl <em>Abstract Message View</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.AbstractMessageViewImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getAbstractMessageView()
         * @generated
         */
        EClass ABSTRACT_MESSAGE_VIEW = eINSTANCE.getAbstractMessageView();

        /**
         * The meta object literal for the '<em><b>Affected By</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ABSTRACT_MESSAGE_VIEW__AFFECTED_BY = eINSTANCE.getAbstractMessageView_AffectedBy();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.MessageViewImpl <em>Message View</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.MessageViewImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getMessageView()
         * @generated
         */
        EClass MESSAGE_VIEW = eINSTANCE.getMessageView();

        /**
         * The meta object literal for the '<em><b>Specifies</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference MESSAGE_VIEW__SPECIFIES = eINSTANCE.getMessageView_Specifies();

        /**
         * The meta object literal for the '<em><b>Specification</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference MESSAGE_VIEW__SPECIFICATION = eINSTANCE.getMessageView_Specification();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.MessageViewReferenceImpl <em>Message View Reference</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.MessageViewReferenceImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getMessageViewReference()
         * @generated
         */
        EClass MESSAGE_VIEW_REFERENCE = eINSTANCE.getMessageViewReference();

        /**
         * The meta object literal for the '<em><b>References</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference MESSAGE_VIEW_REFERENCE__REFERENCES = eINSTANCE.getMessageViewReference_References();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.InteractionImpl <em>Interaction</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.InteractionImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getInteraction()
         * @generated
         */
        EClass INTERACTION = eINSTANCE.getInteraction();

        /**
         * The meta object literal for the '<em><b>Lifelines</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference INTERACTION__LIFELINES = eINSTANCE.getInteraction_Lifelines();

        /**
         * The meta object literal for the '<em><b>Messages</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference INTERACTION__MESSAGES = eINSTANCE.getInteraction_Messages();

        /**
         * The meta object literal for the '<em><b>Properties</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference INTERACTION__PROPERTIES = eINSTANCE.getInteraction_Properties();

        /**
         * The meta object literal for the '<em><b>Formal Gates</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference INTERACTION__FORMAL_GATES = eINSTANCE.getInteraction_FormalGates();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.AspectMessageViewImpl <em>Aspect Message View</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.AspectMessageViewImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getAspectMessageView()
         * @generated
         */
        EClass ASPECT_MESSAGE_VIEW = eINSTANCE.getAspectMessageView();

        /**
         * The meta object literal for the '<em><b>Pointcut</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ASPECT_MESSAGE_VIEW__POINTCUT = eINSTANCE.getAspectMessageView_Pointcut();

        /**
         * The meta object literal for the '<em><b>Advice</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ASPECT_MESSAGE_VIEW__ADVICE = eINSTANCE.getAspectMessageView_Advice();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.LifelineImpl <em>Lifeline</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.LifelineImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getLifeline()
         * @generated
         */
        EClass LIFELINE = eINSTANCE.getLifeline();

        /**
         * The meta object literal for the '<em><b>Represents</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference LIFELINE__REPRESENTS = eINSTANCE.getLifeline_Represents();

        /**
         * The meta object literal for the '<em><b>Covered By</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference LIFELINE__COVERED_BY = eINSTANCE.getLifeline_CoveredBy();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.MessageImpl <em>Message</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.MessageImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getMessage()
         * @generated
         */
        EClass MESSAGE = eINSTANCE.getMessage();

        /**
         * The meta object literal for the '<em><b>Send Event</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference MESSAGE__SEND_EVENT = eINSTANCE.getMessage_SendEvent();

        /**
         * The meta object literal for the '<em><b>Receive Event</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference MESSAGE__RECEIVE_EVENT = eINSTANCE.getMessage_ReceiveEvent();

        /**
         * The meta object literal for the '<em><b>Signature</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference MESSAGE__SIGNATURE = eINSTANCE.getMessage_Signature();

        /**
         * The meta object literal for the '<em><b>Assign To</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference MESSAGE__ASSIGN_TO = eINSTANCE.getMessage_AssignTo();

        /**
         * The meta object literal for the '<em><b>Message Sort</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute MESSAGE__MESSAGE_SORT = eINSTANCE.getMessage_MessageSort();

        /**
         * The meta object literal for the '<em><b>Arguments</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference MESSAGE__ARGUMENTS = eINSTANCE.getMessage_Arguments();

        /**
         * The meta object literal for the '<em><b>Interaction</b></em>' container reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference MESSAGE__INTERACTION = eINSTANCE.getMessage_Interaction();

        /**
         * The meta object literal for the '<em><b>Returns</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference MESSAGE__RETURNS = eINSTANCE.getMessage_Returns();

        /**
         * The meta object literal for the '<em><b>Self Message</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute MESSAGE__SELF_MESSAGE = eINSTANCE.getMessage_SelfMessage();

        /**
         * The meta object literal for the '<em><b>Local Properties</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference MESSAGE__LOCAL_PROPERTIES = eINSTANCE.getMessage_LocalProperties();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.MessageEndImpl <em>Message End</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.MessageEndImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getMessageEnd()
         * @generated
         */
        EClass MESSAGE_END = eINSTANCE.getMessageEnd();

        /**
         * The meta object literal for the '<em><b>Message</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference MESSAGE_END__MESSAGE = eINSTANCE.getMessageEnd_Message();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.MessageOccurrenceSpecificationImpl <em>Message Occurrence Specification</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.MessageOccurrenceSpecificationImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getMessageOccurrenceSpecification()
         * @generated
         */
        EClass MESSAGE_OCCURRENCE_SPECIFICATION = eINSTANCE.getMessageOccurrenceSpecification();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.OccurrenceSpecificationImpl <em>Occurrence Specification</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.OccurrenceSpecificationImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getOccurrenceSpecification()
         * @generated
         */
        EClass OCCURRENCE_SPECIFICATION = eINSTANCE.getOccurrenceSpecification();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.DestructionOccurrenceSpecificationImpl <em>Destruction Occurrence Specification</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.DestructionOccurrenceSpecificationImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getDestructionOccurrenceSpecification()
         * @generated
         */
        EClass DESTRUCTION_OCCURRENCE_SPECIFICATION = eINSTANCE.getDestructionOccurrenceSpecification();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.InteractionFragmentImpl <em>Interaction Fragment</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.InteractionFragmentImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getInteractionFragment()
         * @generated
         */
        EClass INTERACTION_FRAGMENT = eINSTANCE.getInteractionFragment();

        /**
         * The meta object literal for the '<em><b>Covered</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference INTERACTION_FRAGMENT__COVERED = eINSTANCE.getInteractionFragment_Covered();

        /**
         * The meta object literal for the '<em><b>Container</b></em>' container reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference INTERACTION_FRAGMENT__CONTAINER = eINSTANCE.getInteractionFragment_Container();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.CombinedFragmentImpl <em>Combined Fragment</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.CombinedFragmentImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getCombinedFragment()
         * @generated
         */
        EClass COMBINED_FRAGMENT = eINSTANCE.getCombinedFragment();

        /**
         * The meta object literal for the '<em><b>Interaction Operator</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute COMBINED_FRAGMENT__INTERACTION_OPERATOR = eINSTANCE.getCombinedFragment_InteractionOperator();

        /**
         * The meta object literal for the '<em><b>Operands</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference COMBINED_FRAGMENT__OPERANDS = eINSTANCE.getCombinedFragment_Operands();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.OriginalBehaviorExecutionImpl <em>Original Behavior Execution</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.OriginalBehaviorExecutionImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getOriginalBehaviorExecution()
         * @generated
         */
        EClass ORIGINAL_BEHAVIOR_EXECUTION = eINSTANCE.getOriginalBehaviorExecution();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.ExecutionStatementImpl <em>Execution Statement</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.ExecutionStatementImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getExecutionStatement()
         * @generated
         */
        EClass EXECUTION_STATEMENT = eINSTANCE.getExecutionStatement();

        /**
         * The meta object literal for the '<em><b>Specification</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference EXECUTION_STATEMENT__SPECIFICATION = eINSTANCE.getExecutionStatement_Specification();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.InteractionOperandImpl <em>Interaction Operand</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.InteractionOperandImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getInteractionOperand()
         * @generated
         */
        EClass INTERACTION_OPERAND = eINSTANCE.getInteractionOperand();

        /**
         * The meta object literal for the '<em><b>Interaction Constraint</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference INTERACTION_OPERAND__INTERACTION_CONSTRAINT = eINSTANCE.getInteractionOperand_InteractionConstraint();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.ValueSpecificationImpl <em>Value Specification</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.ValueSpecificationImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getValueSpecification()
         * @generated
         */
        EClass VALUE_SPECIFICATION = eINSTANCE.getValueSpecification();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.StructuralFeatureValueImpl <em>Structural Feature Value</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.StructuralFeatureValueImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getStructuralFeatureValue()
         * @generated
         */
        EClass STRUCTURAL_FEATURE_VALUE = eINSTANCE.getStructuralFeatureValue();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference STRUCTURAL_FEATURE_VALUE__VALUE = eINSTANCE.getStructuralFeatureValue_Value();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.ParameterValueMappingImpl <em>Parameter Value Mapping</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.ParameterValueMappingImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getParameterValueMapping()
         * @generated
         */
        EClass PARAMETER_VALUE_MAPPING = eINSTANCE.getParameterValueMapping();

        /**
         * The meta object literal for the '<em><b>Parameter</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference PARAMETER_VALUE_MAPPING__PARAMETER = eINSTANCE.getParameterValueMapping_Parameter();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference PARAMETER_VALUE_MAPPING__VALUE = eINSTANCE.getParameterValueMapping_Value();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.ParameterValueImpl <em>Parameter Value</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.ParameterValueImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getParameterValue()
         * @generated
         */
        EClass PARAMETER_VALUE = eINSTANCE.getParameterValue();

        /**
         * The meta object literal for the '<em><b>Parameter</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference PARAMETER_VALUE__PARAMETER = eINSTANCE.getParameterValue_Parameter();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.OpaqueExpressionImpl <em>Opaque Expression</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.OpaqueExpressionImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getOpaqueExpression()
         * @generated
         */
        EClass OPAQUE_EXPRESSION = eINSTANCE.getOpaqueExpression();

        /**
         * The meta object literal for the '<em><b>Body</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute OPAQUE_EXPRESSION__BODY = eINSTANCE.getOpaqueExpression_Body();

        /**
         * The meta object literal for the '<em><b>Language</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute OPAQUE_EXPRESSION__LANGUAGE = eINSTANCE.getOpaqueExpression_Language();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.LiteralSpecificationImpl <em>Literal Specification</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.LiteralSpecificationImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getLiteralSpecification()
         * @generated
         */
        EClass LITERAL_SPECIFICATION = eINSTANCE.getLiteralSpecification();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.LiteralStringImpl <em>Literal String</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.LiteralStringImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getLiteralString()
         * @generated
         */
        EClass LITERAL_STRING = eINSTANCE.getLiteralString();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LITERAL_STRING__VALUE = eINSTANCE.getLiteralString_Value();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.LiteralIntegerImpl <em>Literal Integer</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.LiteralIntegerImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getLiteralInteger()
         * @generated
         */
        EClass LITERAL_INTEGER = eINSTANCE.getLiteralInteger();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LITERAL_INTEGER__VALUE = eINSTANCE.getLiteralInteger_Value();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.FragmentContainerImpl <em>Fragment Container</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.FragmentContainerImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getFragmentContainer()
         * @generated
         */
        EClass FRAGMENT_CONTAINER = eINSTANCE.getFragmentContainer();

        /**
         * The meta object literal for the '<em><b>Fragments</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference FRAGMENT_CONTAINER__FRAGMENTS = eINSTANCE.getFragmentContainer_Fragments();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.RCollectionImpl <em>RCollection</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.RCollectionImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getRCollection()
         * @generated
         */
        EClass RCOLLECTION = eINSTANCE.getRCollection();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference RCOLLECTION__TYPE = eINSTANCE.getRCollection_Type();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.RSetImpl <em>RSet</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.RSetImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getRSet()
         * @generated
         */
        EClass RSET = eINSTANCE.getRSet();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.RSequenceImpl <em>RSequence</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.RSequenceImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getRSequence()
         * @generated
         */
        EClass RSEQUENCE = eINSTANCE.getRSequence();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.LayoutImpl <em>Layout</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.LayoutImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getLayout()
         * @generated
         */
        EClass LAYOUT = eINSTANCE.getLayout();

        /**
         * The meta object literal for the '<em><b>Containers</b></em>' map feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference LAYOUT__CONTAINERS = eINSTANCE.getLayout_Containers();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.ContainerMapImpl <em>Container Map</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.ContainerMapImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getContainerMap()
         * @generated
         */
        EClass CONTAINER_MAP = eINSTANCE.getContainerMap();

        /**
         * The meta object literal for the '<em><b>Key</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CONTAINER_MAP__KEY = eINSTANCE.getContainerMap_Key();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' map feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CONTAINER_MAP__VALUE = eINSTANCE.getContainerMap_Value();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.ElementMapImpl <em>Element Map</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.ElementMapImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getElementMap()
         * @generated
         */
        EClass ELEMENT_MAP = eINSTANCE.getElementMap();

        /**
         * The meta object literal for the '<em><b>Key</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ELEMENT_MAP__KEY = eINSTANCE.getElementMap_Key();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ELEMENT_MAP__VALUE = eINSTANCE.getElementMap_Value();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.LayoutElementImpl <em>Layout Element</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.LayoutElementImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getLayoutElement()
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
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.ClassifierImpl <em>Classifier</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.ClassifierImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getClassifier()
         * @generated
         */
        EClass CLASSIFIER = eINSTANCE.getClassifier();

        /**
         * The meta object literal for the '<em><b>Operations</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CLASSIFIER__OPERATIONS = eINSTANCE.getClassifier_Operations();

        /**
         * The meta object literal for the '<em><b>Association Ends</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CLASSIFIER__ASSOCIATION_ENDS = eINSTANCE.getClassifier_AssociationEnds();

        /**
         * The meta object literal for the '<em><b>Type Parameters</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CLASSIFIER__TYPE_PARAMETERS = eINSTANCE.getClassifier_TypeParameters();

        /**
         * The meta object literal for the '<em><b>Super Types</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CLASSIFIER__SUPER_TYPES = eINSTANCE.getClassifier_SuperTypes();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.ImplementationClassImpl <em>Implementation Class</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.ImplementationClassImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getImplementationClass()
         * @generated
         */
        EClass IMPLEMENTATION_CLASS = eINSTANCE.getImplementationClass();

        /**
         * The meta object literal for the '<em><b>Instance Class Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute IMPLEMENTATION_CLASS__INSTANCE_CLASS_NAME = eINSTANCE.getImplementationClass_InstanceClassName();

        /**
         * The meta object literal for the '<em><b>Interface</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute IMPLEMENTATION_CLASS__INTERFACE = eINSTANCE.getImplementationClass_Interface();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.TemporaryPropertyImpl <em>Temporary Property</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.TemporaryPropertyImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getTemporaryProperty()
         * @generated
         */
        EClass TEMPORARY_PROPERTY = eINSTANCE.getTemporaryProperty();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.StructuralFeatureImpl <em>Structural Feature</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.StructuralFeatureImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getStructuralFeature()
         * @generated
         */
        EClass STRUCTURAL_FEATURE = eINSTANCE.getStructuralFeature();

        /**
         * The meta object literal for the '<em><b>Static</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute STRUCTURAL_FEATURE__STATIC = eINSTANCE.getStructuralFeature_Static();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.ReferenceImpl <em>Reference</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.ReferenceImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getReference()
         * @generated
         */
        EClass REFERENCE = eINSTANCE.getReference();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference REFERENCE__TYPE = eINSTANCE.getReference_Type();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.PropertyImpl <em>Property</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.PropertyImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getProperty()
         * @generated
         */
        EClass PROPERTY = eINSTANCE.getProperty();

        /**
         * The meta object literal for the '<em><b>Lower Bound</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute PROPERTY__LOWER_BOUND = eINSTANCE.getProperty_LowerBound();

        /**
         * The meta object literal for the '<em><b>Upper Bound</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute PROPERTY__UPPER_BOUND = eINSTANCE.getProperty_UpperBound();

        /**
         * The meta object literal for the '<em><b>Reference Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute PROPERTY__REFERENCE_TYPE = eINSTANCE.getProperty_ReferenceType();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.TypedElementImpl <em>Typed Element</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.TypedElementImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getTypedElement()
         * @generated
         */
        EClass TYPED_ELEMENT = eINSTANCE.getTypedElement();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.GateImpl <em>Gate</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.GateImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getGate()
         * @generated
         */
        EClass GATE = eINSTANCE.getGate();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.LiteralBooleanImpl <em>Literal Boolean</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.LiteralBooleanImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getLiteralBoolean()
         * @generated
         */
        EClass LITERAL_BOOLEAN = eINSTANCE.getLiteralBoolean();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LITERAL_BOOLEAN__VALUE = eINSTANCE.getLiteralBoolean_Value();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.ClassifierMappingImpl <em>Classifier Mapping</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.ClassifierMappingImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getClassifierMapping()
         * @generated
         */
        EClass CLASSIFIER_MAPPING = eINSTANCE.getClassifierMapping();

        /**
         * The meta object literal for the '<em><b>Operation Mappings</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CLASSIFIER_MAPPING__OPERATION_MAPPINGS = eINSTANCE.getClassifierMapping_OperationMappings();

        /**
         * The meta object literal for the '<em><b>Attribute Mappings</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CLASSIFIER_MAPPING__ATTRIBUTE_MAPPINGS = eINSTANCE.getClassifierMapping_AttributeMappings();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.AttributeMappingImpl <em>Attribute Mapping</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.AttributeMappingImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getAttributeMapping()
         * @generated
         */
        EClass ATTRIBUTE_MAPPING = eINSTANCE.getAttributeMapping();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.OperationMappingImpl <em>Operation Mapping</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.OperationMappingImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getOperationMapping()
         * @generated
         */
        EClass OPERATION_MAPPING = eINSTANCE.getOperationMapping();

        /**
         * The meta object literal for the '<em><b>Parameter Mappings</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference OPERATION_MAPPING__PARAMETER_MAPPINGS = eINSTANCE.getOperationMapping_ParameterMappings();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.ParameterMappingImpl <em>Parameter Mapping</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.ParameterMappingImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getParameterMapping()
         * @generated
         */
        EClass PARAMETER_MAPPING = eINSTANCE.getParameterMapping();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.StateViewImpl <em>State View</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.StateViewImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getStateView()
         * @generated
         */
        EClass STATE_VIEW = eINSTANCE.getStateView();

        /**
         * The meta object literal for the '<em><b>State Machines</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference STATE_VIEW__STATE_MACHINES = eINSTANCE.getStateView_StateMachines();

        /**
         * The meta object literal for the '<em><b>Specifies</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference STATE_VIEW__SPECIFIES = eINSTANCE.getStateView_Specifies();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.StateMachineImpl <em>State Machine</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.StateMachineImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getStateMachine()
         * @generated
         */
        EClass STATE_MACHINE = eINSTANCE.getStateMachine();

        /**
         * The meta object literal for the '<em><b>Start</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference STATE_MACHINE__START = eINSTANCE.getStateMachine_Start();

        /**
         * The meta object literal for the '<em><b>States</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference STATE_MACHINE__STATES = eINSTANCE.getStateMachine_States();

        /**
         * The meta object literal for the '<em><b>Transitions</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference STATE_MACHINE__TRANSITIONS = eINSTANCE.getStateMachine_Transitions();

        /**
         * The meta object literal for the '<em><b>Substitutions</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference STATE_MACHINE__SUBSTITUTIONS = eINSTANCE.getStateMachine_Substitutions();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.TransitionImpl <em>Transition</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.TransitionImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getTransition()
         * @generated
         */
        EClass TRANSITION = eINSTANCE.getTransition();

        /**
         * The meta object literal for the '<em><b>End State</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference TRANSITION__END_STATE = eINSTANCE.getTransition_EndState();

        /**
         * The meta object literal for the '<em><b>Start State</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference TRANSITION__START_STATE = eINSTANCE.getTransition_StartState();

        /**
         * The meta object literal for the '<em><b>Signature</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference TRANSITION__SIGNATURE = eINSTANCE.getTransition_Signature();

        /**
         * The meta object literal for the '<em><b>Guard</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference TRANSITION__GUARD = eINSTANCE.getTransition_Guard();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.StateImpl <em>State</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.StateImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getState()
         * @generated
         */
        EClass STATE = eINSTANCE.getState();

        /**
         * The meta object literal for the '<em><b>Incomings</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference STATE__INCOMINGS = eINSTANCE.getState_Incomings();

        /**
         * The meta object literal for the '<em><b>Outgoings</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference STATE__OUTGOINGS = eINSTANCE.getState_Outgoings();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.RDoubleImpl <em>RDouble</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.RDoubleImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getRDouble()
         * @generated
         */
        EClass RDOUBLE = eINSTANCE.getRDouble();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.RFloatImpl <em>RFloat</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.RFloatImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getRFloat()
         * @generated
         */
        EClass RFLOAT = eINSTANCE.getRFloat();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.ConstraintImpl <em>Constraint</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.ConstraintImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getConstraint()
         * @generated
         */
        EClass CONSTRAINT = eINSTANCE.getConstraint();

        /**
         * The meta object literal for the '<em><b>Specification</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CONSTRAINT__SPECIFICATION = eINSTANCE.getConstraint_Specification();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.SubstitutionImpl <em>Substitution</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.SubstitutionImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getSubstitution()
         * @generated
         */
        EClass SUBSTITUTION = eINSTANCE.getSubstitution();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.TransitionSubstitutionImpl <em>Transition Substitution</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.TransitionSubstitutionImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getTransitionSubstitution()
         * @generated
         */
        EClass TRANSITION_SUBSTITUTION = eINSTANCE.getTransitionSubstitution();

        /**
         * The meta object literal for the '<em><b>From</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference TRANSITION_SUBSTITUTION__FROM = eINSTANCE.getTransitionSubstitution_From();

        /**
         * The meta object literal for the '<em><b>To</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference TRANSITION_SUBSTITUTION__TO = eINSTANCE.getTransitionSubstitution_To();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.TypeParameterImpl <em>Type Parameter</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.TypeParameterImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getTypeParameter()
         * @generated
         */
        EClass TYPE_PARAMETER = eINSTANCE.getTypeParameter();

        /**
         * The meta object literal for the '<em><b>Generic Type</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference TYPE_PARAMETER__GENERIC_TYPE = eINSTANCE.getTypeParameter_GenericType();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.RLongImpl <em>RLong</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.RLongImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getRLong()
         * @generated
         */
        EClass RLONG = eINSTANCE.getRLong();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.RArrayImpl <em>RArray</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.RArrayImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getRArray()
         * @generated
         */
        EClass RARRAY = eINSTANCE.getRArray();

        /**
         * The meta object literal for the '<em><b>Size</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute RARRAY__SIZE = eINSTANCE.getRArray_Size();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference RARRAY__TYPE = eINSTANCE.getRArray_Type();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.TraceableImpl <em>Traceable</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.TraceableImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getTraceable()
         * @generated
         */
        EClass TRACEABLE = eINSTANCE.getTraceable();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.WovenAspectImpl <em>Woven Aspect</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.WovenAspectImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getWovenAspect()
         * @generated
         */
        EClass WOVEN_ASPECT = eINSTANCE.getWovenAspect();

        /**
         * The meta object literal for the '<em><b>Comes From</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference WOVEN_ASPECT__COMES_FROM = eINSTANCE.getWovenAspect_ComesFrom();

        /**
         * The meta object literal for the '<em><b>Woven Elements</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference WOVEN_ASPECT__WOVEN_ELEMENTS = eINSTANCE.getWovenAspect_WovenElements();

        /**
         * The meta object literal for the '<em><b>Children</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference WOVEN_ASPECT__CHILDREN = eINSTANCE.getWovenAspect_Children();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.LiteralNullImpl <em>Literal Null</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.LiteralNullImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getLiteralNull()
         * @generated
         */
        EClass LITERAL_NULL = eINSTANCE.getLiteralNull();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.EnumLiteralValueImpl <em>Enum Literal Value</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.EnumLiteralValueImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getEnumLiteralValue()
         * @generated
         */
        EClass ENUM_LITERAL_VALUE = eINSTANCE.getEnumLiteralValue();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ENUM_LITERAL_VALUE__VALUE = eINSTANCE.getEnumLiteralValue_Value();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.impl.AssignmentStatementImpl <em>Assignment Statement</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.impl.AssignmentStatementImpl
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getAssignmentStatement()
         * @generated
         */
        EClass ASSIGNMENT_STATEMENT = eINSTANCE.getAssignmentStatement();

        /**
         * The meta object literal for the '<em><b>Assign To</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ASSIGNMENT_STATEMENT__ASSIGN_TO = eINSTANCE.getAssignmentStatement_AssignTo();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ASSIGNMENT_STATEMENT__VALUE = eINSTANCE.getAssignmentStatement_Value();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.RAMVisibilityType <em>RAM Visibility Type</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.RAMVisibilityType
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getRAMVisibilityType()
         * @generated
         */
        EEnum RAM_VISIBILITY_TYPE = eINSTANCE.getRAMVisibilityType();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.ReferenceType <em>Reference Type</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.ReferenceType
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getReferenceType()
         * @generated
         */
        EEnum REFERENCE_TYPE = eINSTANCE.getReferenceType();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.MessageSort <em>Message Sort</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.MessageSort
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getMessageSort()
         * @generated
         */
        EEnum MESSAGE_SORT = eINSTANCE.getMessageSort();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.InteractionOperatorKind <em>Interaction Operator Kind</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.InteractionOperatorKind
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getInteractionOperatorKind()
         * @generated
         */
        EEnum INTERACTION_OPERATOR_KIND = eINSTANCE.getInteractionOperatorKind();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.InstantiationType <em>Instantiation Type</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.InstantiationType
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getInstantiationType()
         * @generated
         */
        EEnum INSTANTIATION_TYPE = eINSTANCE.getInstantiationType();

        /**
         * The meta object literal for the '{@link ca.mcgill.sel.ram.OperationType <em>Operation Type</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see ca.mcgill.sel.ram.OperationType
         * @see ca.mcgill.sel.ram.impl.RamPackageImpl#getOperationType()
         * @generated
         */
        EEnum OPERATION_TYPE = eINSTANCE.getOperationType();

    }

} //RamPackage
