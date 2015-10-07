/**
 */
package ca.mcgill.sel.ram;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Interaction Operand</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ca.mcgill.sel.ram.InteractionOperand#getInteractionConstraint <em>Interaction Constraint</em>}</li>
 * </ul>
 * </p>
 *
 * @see ca.mcgill.sel.ram.RamPackage#getInteractionOperand()
 * @model
 * @generated
 */
public interface InteractionOperand extends FragmentContainer {
    /**
     * Returns the value of the '<em><b>Interaction Constraint</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Interaction Constraint</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Interaction Constraint</em>' containment reference.
     * @see #setInteractionConstraint(ValueSpecification)
     * @see ca.mcgill.sel.ram.RamPackage#getInteractionOperand_InteractionConstraint()
     * @model containment="true"
     * @generated
     */
    ValueSpecification getInteractionConstraint();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.ram.InteractionOperand#getInteractionConstraint <em>Interaction Constraint</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Interaction Constraint</em>' containment reference.
     * @see #getInteractionConstraint()
     * @generated
     */
    void setInteractionConstraint(ValueSpecification value);

} // InteractionOperand
