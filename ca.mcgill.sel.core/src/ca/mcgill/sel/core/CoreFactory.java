/**
 */
package ca.mcgill.sel.core;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see ca.mcgill.sel.core.CorePackage
 * @generated
 */
public interface CoreFactory extends EFactory {
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    CoreFactory eINSTANCE = ca.mcgill.sel.core.impl.CoreFactoryImpl.init();

    /**
     * Returns a new object of class '<em>CORE Impact Model</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>CORE Impact Model</em>'.
     * @generated
     */
    COREImpactModel createCOREImpactModel();

    /**
     * Returns a new object of class '<em>CORE Concern</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>CORE Concern</em>'.
     * @generated
     */
    COREConcern createCOREConcern();

    /**
     * Returns a new object of class '<em>CORE Feature</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>CORE Feature</em>'.
     * @generated
     */
    COREFeature createCOREFeature();

    /**
     * Returns a new object of class '<em>CORE Interface</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>CORE Interface</em>'.
     * @generated
     */
    COREInterface createCOREInterface();

    /**
     * Returns a new object of class '<em>CORE Reuse</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>CORE Reuse</em>'.
     * @generated
     */
    COREReuse createCOREReuse();

    /**
     * Returns a new object of class '<em>CORE Impact Node</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>CORE Impact Node</em>'.
     * @generated
     */
    COREImpactNode createCOREImpactNode();

    /**
     * Returns a new object of class '<em>CORE Feature Model</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>CORE Feature Model</em>'.
     * @generated
     */
    COREFeatureModel createCOREFeatureModel();

    /**
     * Returns a new object of class '<em>CORE Model Reuse</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>CORE Model Reuse</em>'.
     * @generated
     */
    COREModelReuse createCOREModelReuse();

    /**
     * Returns a new object of class '<em>CORE Contribution</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>CORE Contribution</em>'.
     * @generated
     */
    COREContribution createCOREContribution();

    /**
     * Returns a new object of class '<em>Layout Element</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Layout Element</em>'.
     * @generated
     */
    LayoutElement createLayoutElement();

    /**
     * Returns a new object of class '<em>CORE Feature Impact Node</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>CORE Feature Impact Node</em>'.
     * @generated
     */
    COREFeatureImpactNode createCOREFeatureImpactNode();

    /**
     * Returns a new object of class '<em>CORE Weighted Mapping</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>CORE Weighted Mapping</em>'.
     * @generated
     */
    COREWeightedMapping createCOREWeightedMapping();

    /**
     * Returns a new object of class '<em>CORE Impact Model Binding</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>CORE Impact Model Binding</em>'.
     * @generated
     */
    COREImpactModelBinding createCOREImpactModelBinding();

    /**
     * Returns a new object of class '<em>CORE Concern Configuration</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>CORE Concern Configuration</em>'.
     * @generated
     */
    COREConcernConfiguration createCOREConcernConfiguration();

    /**
     * Returns a new object of class '<em>CORE Reuse Configuration</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>CORE Reuse Configuration</em>'.
     * @generated
     */
    COREReuseConfiguration createCOREReuseConfiguration();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    CorePackage getCorePackage();

} //CoreFactory
