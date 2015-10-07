/**
 */
package ca.mcgill.sel.ram;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ca.mcgill.sel.ram.TypeParameter#getGenericType <em>Generic Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see ca.mcgill.sel.ram.RamPackage#getTypeParameter()
 * @model
 * @generated
 */
public interface TypeParameter extends Type {
    /**
     * Returns the value of the '<em><b>Generic Type</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Generic Type</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Generic Type</em>' reference.
     * @see #setGenericType(ObjectType)
     * @see ca.mcgill.sel.ram.RamPackage#getTypeParameter_GenericType()
     * @model
     * @generated
     */
    ObjectType getGenericType();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.ram.TypeParameter#getGenericType <em>Generic Type</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Generic Type</em>' reference.
     * @see #getGenericType()
     * @generated
     */
    void setGenericType(ObjectType value);

} // TypeParameter
