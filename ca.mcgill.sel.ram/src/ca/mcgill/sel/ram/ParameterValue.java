/**
 */
package ca.mcgill.sel.ram;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Parameter Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ca.mcgill.sel.ram.ParameterValue#getParameter <em>Parameter</em>}</li>
 * </ul>
 * </p>
 *
 * @see ca.mcgill.sel.ram.RamPackage#getParameterValue()
 * @model
 * @generated
 */
public interface ParameterValue extends ValueSpecification {
    /**
     * Returns the value of the '<em><b>Parameter</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Parameter</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Parameter</em>' reference.
     * @see #setParameter(Parameter)
     * @see ca.mcgill.sel.ram.RamPackage#getParameterValue_Parameter()
     * @model required="true"
     * @generated
     */
    Parameter getParameter();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.ram.ParameterValue#getParameter <em>Parameter</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Parameter</em>' reference.
     * @see #getParameter()
     * @generated
     */
    void setParameter(Parameter value);

} // ParameterValue
