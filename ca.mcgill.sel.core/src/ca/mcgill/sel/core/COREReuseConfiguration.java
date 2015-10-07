/**
 */
package ca.mcgill.sel.core;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CORE Configuration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link ca.mcgill.sel.core.COREReuseConfiguration#getReusedConfiguration <em>Reused Configuration</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.COREReuseConfiguration#getReuse <em>Reuse</em>}</li>
 * </ul>
 *
 * @see ca.mcgill.sel.core.CorePackage#getCOREReuseConfiguration()
 * @model
 * @generated
 */
public interface COREReuseConfiguration extends COREConfiguration {
    /**
     * Returns the value of the '<em><b>Reused Configuration</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Reused Configuration</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Reused Configuration</em>' reference.
     * @see #setReusedConfiguration(COREConcernConfiguration)
     * @see ca.mcgill.sel.core.CorePackage#getCOREReuseConfiguration_ReusedConfiguration()
     * @model
     * @generated
     */
    COREConcernConfiguration getReusedConfiguration();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.core.COREReuseConfiguration#getReusedConfiguration <em>Reused Configuration</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Reused Configuration</em>' reference.
     * @see #getReusedConfiguration()
     * @generated
     */
    void setReusedConfiguration(COREConcernConfiguration value);

    /**
     * Returns the value of the '<em><b>Reuse</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Reuse</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Reuse</em>' reference.
     * @see #setReuse(COREReuse)
     * @see ca.mcgill.sel.core.CorePackage#getCOREReuseConfiguration_Reuse()
     * @model
     * @generated
     */
    COREReuse getReuse();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.core.COREReuseConfiguration#getReuse <em>Reuse</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Reuse</em>' reference.
     * @see #getReuse()
     * @generated
     */
    void setReuse(COREReuse value);

} // COREConfiguration
