/**
 */
package ca.mcgill.sel.core.util;

import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;

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
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see ca.mcgill.sel.core.CorePackage
 * @generated
 */
public class CoreAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static CorePackage modelPackage;

    /**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CoreAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = CorePackage.eINSTANCE;
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
    protected CoreSwitch<Adapter> modelSwitch =
        new CoreSwitch<Adapter>() {
            @Override
            public Adapter caseCOREModel(COREModel object) {
                return createCOREModelAdapter();
            }
            @Override
            public Adapter caseCOREImpactModel(COREImpactModel object) {
                return createCOREImpactModelAdapter();
            }
            @Override
            public Adapter caseCOREConcern(COREConcern object) {
                return createCOREConcernAdapter();
            }
            @Override
            public Adapter caseCOREFeature(COREFeature object) {
                return createCOREFeatureAdapter();
            }
            @Override
            public <S extends COREModel, M extends COREMapping<?>> Adapter caseCOREBinding(COREBinding<S, M> object) {
                return createCOREBindingAdapter();
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
            public <T extends COREModelElement> Adapter caseCOREMapping(COREMapping<T> object) {
                return createCOREMappingAdapter();
            }
            @Override
            public Adapter caseCORENamedElement(CORENamedElement object) {
                return createCORENamedElementAdapter();
            }
            @Override
            public Adapter caseCOREInterface(COREInterface object) {
                return createCOREInterfaceAdapter();
            }
            @Override
            public Adapter caseCOREReuse(COREReuse object) {
                return createCOREReuseAdapter();
            }
            @Override
            public <S extends COREModel> Adapter caseCOREPattern(COREPattern<S> object) {
                return createCOREPatternAdapter();
            }
            @Override
            public Adapter caseCOREImpactNode(COREImpactNode object) {
                return createCOREImpactNodeAdapter();
            }
            @Override
            public Adapter caseCOREConfiguration(COREConfiguration object) {
                return createCOREConfigurationAdapter();
            }
            @Override
            public Adapter caseCOREFeatureModel(COREFeatureModel object) {
                return createCOREFeatureModelAdapter();
            }
            @Override
            public Adapter caseCOREModelReuse(COREModelReuse object) {
                return createCOREModelReuseAdapter();
            }
            @Override
            public Adapter caseCOREContribution(COREContribution object) {
                return createCOREContributionAdapter();
            }
            @Override
            public Adapter caseLayoutMap(Map.Entry<EObject, LayoutElement> object) {
                return createLayoutMapAdapter();
            }
            @Override
            public Adapter caseLayoutElement(LayoutElement object) {
                return createLayoutElementAdapter();
            }
            @Override
            public Adapter caseLayoutContainerMap(Map.Entry<EObject, EMap<EObject, LayoutElement>> object) {
                return createLayoutContainerMapAdapter();
            }
            @Override
            public Adapter caseCOREFeatureImpactNode(COREFeatureImpactNode object) {
                return createCOREFeatureImpactNodeAdapter();
            }
            @Override
            public <S extends COREModel> Adapter caseCOREModelCompositionSpecification(COREModelCompositionSpecification<S> object) {
                return createCOREModelCompositionSpecificationAdapter();
            }
            @Override
            public Adapter caseCOREWeightedMapping(COREWeightedMapping object) {
                return createCOREWeightedMappingAdapter();
            }
            @Override
            public Adapter caseCOREImpactModelBinding(COREImpactModelBinding object) {
                return createCOREImpactModelBindingAdapter();
            }
            @Override
            public Adapter caseCOREConcernConfiguration(COREConcernConfiguration object) {
                return createCOREConcernConfigurationAdapter();
            }
            @Override
            public Adapter caseCOREReuseConfiguration(COREReuseConfiguration object) {
                return createCOREReuseConfigurationAdapter();
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
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.core.COREImpactModel <em>CORE Impact Model</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.core.COREImpactModel
     * @generated
     */
    public Adapter createCOREImpactModelAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.core.COREConcern <em>CORE Concern</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.core.COREConcern
     * @generated
     */
    public Adapter createCOREConcernAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.core.COREFeature <em>CORE Feature</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.core.COREFeature
     * @generated
     */
    public Adapter createCOREFeatureAdapter() {
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
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.core.COREInterface <em>CORE Interface</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.core.COREInterface
     * @generated
     */
    public Adapter createCOREInterfaceAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.core.COREReuse <em>CORE Reuse</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.core.COREReuse
     * @generated
     */
    public Adapter createCOREReuseAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.core.COREPattern <em>CORE Pattern</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.core.COREPattern
     * @generated
     */
    public Adapter createCOREPatternAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.core.COREImpactNode <em>CORE Impact Node</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.core.COREImpactNode
     * @generated
     */
    public Adapter createCOREImpactNodeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.core.COREConfiguration <em>CORE Configuration</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.core.COREConfiguration
     * @generated
     */
    public Adapter createCOREConfigurationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.core.COREFeatureModel <em>CORE Feature Model</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.core.COREFeatureModel
     * @generated
     */
    public Adapter createCOREFeatureModelAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.core.COREModelReuse <em>CORE Model Reuse</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.core.COREModelReuse
     * @generated
     */
    public Adapter createCOREModelReuseAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.core.COREContribution <em>CORE Contribution</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.core.COREContribution
     * @generated
     */
    public Adapter createCOREContributionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>Layout Map</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see java.util.Map.Entry
     * @generated
     */
    public Adapter createLayoutMapAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.core.LayoutElement <em>Layout Element</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.core.LayoutElement
     * @generated
     */
    public Adapter createLayoutElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>Layout Container Map</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see java.util.Map.Entry
     * @generated
     */
    public Adapter createLayoutContainerMapAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.core.COREFeatureImpactNode <em>CORE Feature Impact Node</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.core.COREFeatureImpactNode
     * @generated
     */
    public Adapter createCOREFeatureImpactNodeAdapter() {
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
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.core.COREWeightedMapping <em>CORE Weighted Mapping</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.core.COREWeightedMapping
     * @generated
     */
    public Adapter createCOREWeightedMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.core.COREImpactModelBinding <em>CORE Impact Model Binding</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.core.COREImpactModelBinding
     * @generated
     */
    public Adapter createCOREImpactModelBindingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.core.COREConcernConfiguration <em>CORE Concern Configuration</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.core.COREConcernConfiguration
     * @generated
     */
    public Adapter createCOREConcernConfigurationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link ca.mcgill.sel.core.COREReuseConfiguration <em>CORE Reuse Configuration</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see ca.mcgill.sel.core.COREReuseConfiguration
     * @generated
     */
    public Adapter createCOREReuseConfigurationAdapter() {
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

} //CoreAdapterFactory
