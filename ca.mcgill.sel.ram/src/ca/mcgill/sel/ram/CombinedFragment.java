/**
 */
package ca.mcgill.sel.ram;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Combined Fragment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ca.mcgill.sel.ram.CombinedFragment#getInteractionOperator <em>Interaction Operator</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.CombinedFragment#getOperands <em>Operands</em>}</li>
 * </ul>
 * </p>
 *
 * @see ca.mcgill.sel.ram.RamPackage#getCombinedFragment()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='validOperands'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot validOperands='Tuple {\n\tmessage : String = \'opt and loop fragments may only have one operand\',\n\tstatus : Boolean = if self.interactionOperator = InteractionOperatorKind::opt or self.interactionOperator = InteractionOperatorKind::loop or self.interactionOperator = InteractionOperatorKind::critical then self.operands->size() = 1 else true endif\n}.status'"
 * @generated
 */
public interface CombinedFragment extends InteractionFragment {
    /**
     * Returns the value of the '<em><b>Interaction Operator</b></em>' attribute.
     * The literals are from the enumeration {@link ca.mcgill.sel.ram.InteractionOperatorKind}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Interaction Operator</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Interaction Operator</em>' attribute.
     * @see ca.mcgill.sel.ram.InteractionOperatorKind
     * @see #setInteractionOperator(InteractionOperatorKind)
     * @see ca.mcgill.sel.ram.RamPackage#getCombinedFragment_InteractionOperator()
     * @model required="true"
     * @generated
     */
    InteractionOperatorKind getInteractionOperator();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.ram.CombinedFragment#getInteractionOperator <em>Interaction Operator</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Interaction Operator</em>' attribute.
     * @see ca.mcgill.sel.ram.InteractionOperatorKind
     * @see #getInteractionOperator()
     * @generated
     */
    void setInteractionOperator(InteractionOperatorKind value);

    /**
     * Returns the value of the '<em><b>Operands</b></em>' containment reference list.
     * The list contents are of type {@link ca.mcgill.sel.ram.InteractionOperand}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Operands</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Operands</em>' containment reference list.
     * @see ca.mcgill.sel.ram.RamPackage#getCombinedFragment_Operands()
     * @model containment="true" required="true"
     * @generated
     */
    EList<InteractionOperand> getOperands();

} // CombinedFragment
