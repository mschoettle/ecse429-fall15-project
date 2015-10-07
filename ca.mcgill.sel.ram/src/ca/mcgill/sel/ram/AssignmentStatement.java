/**
 */
package ca.mcgill.sel.ram;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Assignment Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ca.mcgill.sel.ram.AssignmentStatement#getAssignTo <em>Assign To</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.AssignmentStatement#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see ca.mcgill.sel.ram.RamPackage#getAssignmentStatement()
 * @model
 * @generated
 */
public interface AssignmentStatement extends InteractionFragment {
    /**
     * Returns the value of the '<em><b>Assign To</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Assign To</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Assign To</em>' reference.
     * @see #setAssignTo(StructuralFeature)
     * @see ca.mcgill.sel.ram.RamPackage#getAssignmentStatement_AssignTo()
     * @model required="true"
     * @generated
     */
    StructuralFeature getAssignTo();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.ram.AssignmentStatement#getAssignTo <em>Assign To</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Assign To</em>' reference.
     * @see #getAssignTo()
     * @generated
     */
    void setAssignTo(StructuralFeature value);

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
     * @see ca.mcgill.sel.ram.RamPackage#getAssignmentStatement_Value()
     * @model containment="true" required="true"
     * @generated
     */
    ValueSpecification getValue();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.ram.AssignmentStatement#getValue <em>Value</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value</em>' containment reference.
     * @see #getValue()
     * @generated
     */
    void setValue(ValueSpecification value);

} // AssignmentStatement
