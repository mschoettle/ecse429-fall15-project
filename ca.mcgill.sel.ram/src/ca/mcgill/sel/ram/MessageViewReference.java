/**
 */
package ca.mcgill.sel.ram;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Message View Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ca.mcgill.sel.ram.MessageViewReference#getReferences <em>References</em>}</li>
 * </ul>
 * </p>
 *
 * @see ca.mcgill.sel.ram.RamPackage#getMessageViewReference()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='isAffectedBy'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot isAffectedBy='Tuple {\n\tmessage : String = \'The MessageViewReference must have at least one affectedBy\',\n\tstatus : Boolean = self.affectedBy->size() > 0\n}.status'"
 * @generated
 */
public interface MessageViewReference extends AbstractMessageView {
    /**
     * Returns the value of the '<em><b>References</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>References</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>References</em>' reference.
     * @see #setReferences(MessageView)
     * @see ca.mcgill.sel.ram.RamPackage#getMessageViewReference_References()
     * @model required="true"
     * @generated
     */
    MessageView getReferences();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.ram.MessageViewReference#getReferences <em>References</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>References</em>' reference.
     * @see #getReferences()
     * @generated
     */
    void setReferences(MessageView value);

} // MessageViewReference
