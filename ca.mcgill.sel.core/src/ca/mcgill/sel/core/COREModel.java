/**
 */
package ca.mcgill.sel.core;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CORE Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link ca.mcgill.sel.core.COREModel#getModelReuses <em>Model Reuses</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.COREModel#getModelElements <em>Model Elements</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.COREModel#getRealizes <em>Realizes</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.COREModel#getCoreConcern <em>Core Concern</em>}</li>
 * </ul>
 *
 * @see ca.mcgill.sel.core.CorePackage#getCOREModel()
 * @model abstract="true"
 *        extendedMetaData="name='COREFeatureModel'"
 * @generated
 */
public interface COREModel extends CORENamedElement {
    /**
     * Returns the value of the '<em><b>Model Reuses</b></em>' containment reference list.
     * The list contents are of type {@link ca.mcgill.sel.core.COREModelReuse}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Model Reuses</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Model Reuses</em>' containment reference list.
     * @see ca.mcgill.sel.core.CorePackage#getCOREModel_ModelReuses()
     * @model containment="true"
     * @generated
     */
    EList<COREModelReuse> getModelReuses();

    /**
     * Returns the value of the '<em><b>Model Elements</b></em>' reference list.
     * The list contents are of type {@link ca.mcgill.sel.core.COREModelElement}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Model Elements</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Model Elements</em>' reference list.
     * @see ca.mcgill.sel.core.CorePackage#getCOREModel_ModelElements()
     * @model transient="true" changeable="false" derived="true"
     * @generated
     */
    EList<COREModelElement> getModelElements();

    /**
     * Returns the value of the '<em><b>Realizes</b></em>' reference list.
     * The list contents are of type {@link ca.mcgill.sel.core.COREFeature}.
     * It is bidirectional and its opposite is '{@link ca.mcgill.sel.core.COREFeature#getRealizedBy <em>Realized By</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Realizes</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Realizes</em>' reference list.
     * @see ca.mcgill.sel.core.CorePackage#getCOREModel_Realizes()
     * @see ca.mcgill.sel.core.COREFeature#getRealizedBy
     * @model opposite="realizedBy"
     * @generated
     */
    EList<COREFeature> getRealizes();

    /**
     * Returns the value of the '<em><b>Core Concern</b></em>' reference.
     * It is bidirectional and its opposite is '{@link ca.mcgill.sel.core.COREConcern#getModels <em>Models</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Core Concern</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Core Concern</em>' reference.
     * @see #setCoreConcern(COREConcern)
     * @see ca.mcgill.sel.core.CorePackage#getCOREModel_CoreConcern()
     * @see ca.mcgill.sel.core.COREConcern#getModels
     * @model opposite="models" required="true"
     * @generated
     */
    COREConcern getCoreConcern();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.core.COREModel#getCoreConcern <em>Core Concern</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Core Concern</em>' reference.
     * @see #getCoreConcern()
     * @generated
     */
    void setCoreConcern(COREConcern value);

} // COREModel
