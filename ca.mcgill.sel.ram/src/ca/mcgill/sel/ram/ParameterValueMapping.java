/**
 */
package ca.mcgill.sel.ram;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Parameter Value Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ca.mcgill.sel.ram.ParameterValueMapping#getParameter <em>Parameter</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.ParameterValueMapping#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see ca.mcgill.sel.ram.RamPackage#getParameterValueMapping()
 * @model
 * @generated
 */
public interface ParameterValueMapping extends EObject {
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
     * @see ca.mcgill.sel.ram.RamPackage#getParameterValueMapping_Parameter()
     * @model required="true"
     * @generated
     */
    Parameter getParameter();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.ram.ParameterValueMapping#getParameter <em>Parameter</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Parameter</em>' reference.
     * @see #getParameter()
     * @generated
     */
    void setParameter(Parameter value);

    /**
     * Returns the value of the '<em><b>Value</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value</em>' containment reference.
     * @see #setValue(ValueSpecification)
     * @see ca.mcgill.sel.ram.RamPackage#getParameterValueMapping_Value()
     * @model containment="true" required="true"
     * @generated
     */
    ValueSpecification getValue();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.ram.ParameterValueMapping#getValue <em>Value</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value</em>' containment reference.
     * @see #getValue()
     * @generated
     */
    void setValue(ValueSpecification value);

} // ParameterValueMapping
