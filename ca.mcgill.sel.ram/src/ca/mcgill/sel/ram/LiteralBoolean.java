/**
 */
package ca.mcgill.sel.ram;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Literal Boolean</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ca.mcgill.sel.ram.LiteralBoolean#isValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see ca.mcgill.sel.ram.RamPackage#getLiteralBoolean()
 * @model
 * @generated
 */
public interface LiteralBoolean extends LiteralSpecification {
    /**
     * Returns the value of the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value</em>' attribute.
     * @see #setValue(boolean)
     * @see ca.mcgill.sel.ram.RamPackage#getLiteralBoolean_Value()
     * @model required="true"
     * @generated
     */
    boolean isValue();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.ram.LiteralBoolean#isValue <em>Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value</em>' attribute.
     * @see #isValue()
     * @generated
     */
    void setValue(boolean value);

} // LiteralBoolean
