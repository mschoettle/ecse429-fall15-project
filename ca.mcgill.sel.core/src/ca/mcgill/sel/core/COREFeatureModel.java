/**
 */
package ca.mcgill.sel.core;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CORE Feature Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link ca.mcgill.sel.core.COREFeatureModel#getFeatures <em>Features</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.COREFeatureModel#getRoot <em>Root</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.COREFeatureModel#getConfigurations <em>Configurations</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.COREFeatureModel#getDefaultConfiguration <em>Default Configuration</em>}</li>
 * </ul>
 *
 * @see ca.mcgill.sel.core.CorePackage#getCOREFeatureModel()
 * @model
 * @generated
 */
public interface COREFeatureModel extends COREModel {
    /**
     * Returns the value of the '<em><b>Features</b></em>' containment reference list.
     * The list contents are of type {@link ca.mcgill.sel.core.COREFeature}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Features</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Features</em>' containment reference list.
     * @see ca.mcgill.sel.core.CorePackage#getCOREFeatureModel_Features()
     * @model containment="true"
     * @generated
     */
    EList<COREFeature> getFeatures();

    /**
     * Returns the value of the '<em><b>Root</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Root</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Root</em>' reference.
     * @see #setRoot(COREFeature)
     * @see ca.mcgill.sel.core.CorePackage#getCOREFeatureModel_Root()
     * @model required="true"
     * @generated
     */
    COREFeature getRoot();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.core.COREFeatureModel#getRoot <em>Root</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Root</em>' reference.
     * @see #getRoot()
     * @generated
     */
    void setRoot(COREFeature value);

    /**
     * Returns the value of the '<em><b>Configurations</b></em>' containment reference list.
     * The list contents are of type {@link ca.mcgill.sel.core.COREConcernConfiguration}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Configurations</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Configurations</em>' containment reference list.
     * @see ca.mcgill.sel.core.CorePackage#getCOREFeatureModel_Configurations()
     * @model containment="true"
     * @generated
     */
    EList<COREConcernConfiguration> getConfigurations();

    /**
     * Returns the value of the '<em><b>Default Configuration</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Default Configuration</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Default Configuration</em>' reference.
     * @see #setDefaultConfiguration(COREConcernConfiguration)
     * @see ca.mcgill.sel.core.CorePackage#getCOREFeatureModel_DefaultConfiguration()
     * @model
     * @generated
     */
    COREConcernConfiguration getDefaultConfiguration();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.core.COREFeatureModel#getDefaultConfiguration <em>Default Configuration</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Default Configuration</em>' reference.
     * @see #getDefaultConfiguration()
     * @generated
     */
    void setDefaultConfiguration(COREConcernConfiguration value);

} // COREFeatureModel
