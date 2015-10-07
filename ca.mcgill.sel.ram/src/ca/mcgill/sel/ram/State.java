/**
 */
package ca.mcgill.sel.ram;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>State</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ca.mcgill.sel.ram.State#getIncomings <em>Incomings</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.State#getOutgoings <em>Outgoings</em>}</li>
 * </ul>
 * </p>
 *
 * @see ca.mcgill.sel.ram.RamPackage#getState()
 * @model
 * @generated
 */
public interface State extends NamedElement {
    /**
     * Returns the value of the '<em><b>Incomings</b></em>' reference list.
     * The list contents are of type {@link ca.mcgill.sel.ram.Transition}.
     * It is bidirectional and its opposite is '{@link ca.mcgill.sel.ram.Transition#getEndState <em>End State</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Incomings</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Incomings</em>' reference list.
     * @see ca.mcgill.sel.ram.RamPackage#getState_Incomings()
     * @see ca.mcgill.sel.ram.Transition#getEndState
     * @model opposite="endState"
     * @generated
     */
    EList<Transition> getIncomings();

    /**
     * Returns the value of the '<em><b>Outgoings</b></em>' reference list.
     * The list contents are of type {@link ca.mcgill.sel.ram.Transition}.
     * It is bidirectional and its opposite is '{@link ca.mcgill.sel.ram.Transition#getStartState <em>Start State</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Outgoings</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Outgoings</em>' reference list.
     * @see ca.mcgill.sel.ram.RamPackage#getState_Outgoings()
     * @see ca.mcgill.sel.ram.Transition#getStartState
     * @model opposite="startState"
     * @generated
     */
    EList<Transition> getOutgoings();

} // State
