/**
 */
package ca.mcgill.sel.ram;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Interaction</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ca.mcgill.sel.ram.Interaction#getLifelines <em>Lifelines</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.Interaction#getMessages <em>Messages</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.Interaction#getProperties <em>Properties</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.Interaction#getFormalGates <em>Formal Gates</em>}</li>
 * </ul>
 * </p>
 *
 * @see ca.mcgill.sel.ram.RamPackage#getInteraction()
 * @model
 * @generated
 */
public interface Interaction extends FragmentContainer {
    /**
     * Returns the value of the '<em><b>Lifelines</b></em>' containment reference list.
     * The list contents are of type {@link ca.mcgill.sel.ram.Lifeline}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Lifelines</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Lifelines</em>' containment reference list.
     * @see ca.mcgill.sel.ram.RamPackage#getInteraction_Lifelines()
     * @model containment="true" required="true" ordered="false"
     * @generated
     */
    EList<Lifeline> getLifelines();

    /**
     * Returns the value of the '<em><b>Messages</b></em>' containment reference list.
     * The list contents are of type {@link ca.mcgill.sel.ram.Message}.
     * It is bidirectional and its opposite is '{@link ca.mcgill.sel.ram.Message#getInteraction <em>Interaction</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Messages</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Messages</em>' containment reference list.
     * @see ca.mcgill.sel.ram.RamPackage#getInteraction_Messages()
     * @see ca.mcgill.sel.ram.Message#getInteraction
     * @model opposite="interaction" containment="true" required="true" ordered="false"
     * @generated
     */
    EList<Message> getMessages();

    /**
     * Returns the value of the '<em><b>Properties</b></em>' containment reference list.
     * The list contents are of type {@link ca.mcgill.sel.ram.Reference}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Properties</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Properties</em>' containment reference list.
     * @see ca.mcgill.sel.ram.RamPackage#getInteraction_Properties()
     * @model containment="true"
     * @generated
     */
    EList<Reference> getProperties();

    /**
     * Returns the value of the '<em><b>Formal Gates</b></em>' containment reference list.
     * The list contents are of type {@link ca.mcgill.sel.ram.Gate}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Formal Gates</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Formal Gates</em>' containment reference list.
     * @see ca.mcgill.sel.ram.RamPackage#getInteraction_FormalGates()
     * @model containment="true"
     * @generated
     */
    EList<Gate> getFormalGates();

} // Interaction
