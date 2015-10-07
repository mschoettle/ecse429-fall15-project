/**
 */
package ca.mcgill.sel.core;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CORE Concern</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link ca.mcgill.sel.core.COREConcern#getModels <em>Models</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.COREConcern#getInterface <em>Interface</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.COREConcern#getFeatureModel <em>Feature Model</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.COREConcern#getImpactModel <em>Impact Model</em>}</li>
 * </ul>
 *
 * @see ca.mcgill.sel.core.CorePackage#getCOREConcern()
 * @model
 * @generated
 */
public interface COREConcern extends CORENamedElement {
    /**
     * Returns the value of the '<em><b>Models</b></em>' reference list.
     * The list contents are of type {@link ca.mcgill.sel.core.COREModel}.
     * It is bidirectional and its opposite is '{@link ca.mcgill.sel.core.COREModel#getCoreConcern <em>Core Concern</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Models</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Models</em>' reference list.
     * @see ca.mcgill.sel.core.CorePackage#getCOREConcern_Models()
     * @see ca.mcgill.sel.core.COREModel#getCoreConcern
     * @model opposite="coreConcern" required="true"
     * @generated
     */
    EList<COREModel> getModels();

    /**
     * Returns the value of the '<em><b>Interface</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Interface</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Interface</em>' containment reference.
     * @see #setInterface(COREInterface)
     * @see ca.mcgill.sel.core.CorePackage#getCOREConcern_Interface()
     * @model containment="true" required="true"
     * @generated
     */
    COREInterface getInterface();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.core.COREConcern#getInterface <em>Interface</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Interface</em>' containment reference.
     * @see #getInterface()
     * @generated
     */
    void setInterface(COREInterface value);

    /**
     * Returns the value of the '<em><b>Feature Model</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Feature Model</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Feature Model</em>' containment reference.
     * @see #setFeatureModel(COREFeatureModel)
     * @see ca.mcgill.sel.core.CorePackage#getCOREConcern_FeatureModel()
     * @model containment="true" required="true"
     * @generated
     */
    COREFeatureModel getFeatureModel();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.core.COREConcern#getFeatureModel <em>Feature Model</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Feature Model</em>' containment reference.
     * @see #getFeatureModel()
     * @generated
     */
    void setFeatureModel(COREFeatureModel value);

    /**
     * Returns the value of the '<em><b>Impact Model</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Impact Model</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Impact Model</em>' containment reference.
     * @see #setImpactModel(COREImpactModel)
     * @see ca.mcgill.sel.core.CorePackage#getCOREConcern_ImpactModel()
     * @model containment="true"
     * @generated
     */
    COREImpactModel getImpactModel();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.core.COREConcern#getImpactModel <em>Impact Model</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Impact Model</em>' containment reference.
     * @see #getImpactModel()
     * @generated
     */
    void setImpactModel(COREImpactModel value);

} // COREConcern
