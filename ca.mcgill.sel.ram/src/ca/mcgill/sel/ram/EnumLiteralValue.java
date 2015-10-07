/**
 */
package ca.mcgill.sel.ram;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Enum Literal Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ca.mcgill.sel.ram.EnumLiteralValue#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see ca.mcgill.sel.ram.RamPackage#getEnumLiteralValue()
 * @model
 * @generated
 */
public interface EnumLiteralValue extends ValueSpecification {
    /**
     * Returns the value of the '<em><b>Value</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value</em>' reference.
     * @see #setValue(REnumLiteral)
     * @see ca.mcgill.sel.ram.RamPackage#getEnumLiteralValue_Value()
     * @model required="true"
     * @generated
     */
    REnumLiteral getValue();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.ram.EnumLiteralValue#getValue <em>Value</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value</em>' reference.
     * @see #getValue()
     * @generated
     */
    void setValue(REnumLiteral value);

} // EnumLiteralValue
