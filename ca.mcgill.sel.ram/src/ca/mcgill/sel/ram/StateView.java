/**
 */
package ca.mcgill.sel.ram;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>State View</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ca.mcgill.sel.ram.StateView#getStateMachines <em>State Machines</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.StateView#getSpecifies <em>Specifies</em>}</li>
 * </ul>
 * </p>
 *
 * @see ca.mcgill.sel.ram.RamPackage#getStateView()
 * @model
 * @generated
 */
public interface StateView extends NamedElement {
    /**
     * Returns the value of the '<em><b>State Machines</b></em>' containment reference list.
     * The list contents are of type {@link ca.mcgill.sel.ram.StateMachine}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>State Machines</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>State Machines</em>' containment reference list.
     * @see ca.mcgill.sel.ram.RamPackage#getStateView_StateMachines()
     * @model containment="true" required="true"
     * @generated
     */
    EList<StateMachine> getStateMachines();

    /**
     * Returns the value of the '<em><b>Specifies</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Specifies</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Specifies</em>' reference.
     * @see #setSpecifies(Classifier)
     * @see ca.mcgill.sel.ram.RamPackage#getStateView_Specifies()
     * @model required="true"
     * @generated
     */
    Classifier getSpecifies();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.ram.StateView#getSpecifies <em>Specifies</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Specifies</em>' reference.
     * @see #getSpecifies()
     * @generated
     */
    void setSpecifies(Classifier value);

} // StateView
