/**
 */
package ca.mcgill.sel.ram;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Opaque Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ca.mcgill.sel.ram.OpaqueExpression#getBody <em>Body</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.OpaqueExpression#getLanguage <em>Language</em>}</li>
 * </ul>
 * </p>
 *
 * @see ca.mcgill.sel.ram.RamPackage#getOpaqueExpression()
 * @model
 * @generated
 */
public interface OpaqueExpression extends ValueSpecification {
    /**
     * Returns the value of the '<em><b>Body</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Body</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Body</em>' attribute.
     * @see #setBody(String)
     * @see ca.mcgill.sel.ram.RamPackage#getOpaqueExpression_Body()
     * @model required="true"
     * @generated
     */
    String getBody();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.ram.OpaqueExpression#getBody <em>Body</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Body</em>' attribute.
     * @see #getBody()
     * @generated
     */
    void setBody(String value);

    /**
     * Returns the value of the '<em><b>Language</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Language</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Language</em>' attribute.
     * @see #setLanguage(String)
     * @see ca.mcgill.sel.ram.RamPackage#getOpaqueExpression_Language()
     * @model required="true"
     * @generated
     */
    String getLanguage();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.ram.OpaqueExpression#getLanguage <em>Language</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Language</em>' attribute.
     * @see #getLanguage()
     * @generated
     */
    void setLanguage(String value);

} // OpaqueExpression
