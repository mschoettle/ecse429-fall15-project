/**
 */
package ca.mcgill.sel.ram;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ca.mcgill.sel.ram.Parameter#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see ca.mcgill.sel.ram.RamPackage#getParameter()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='notVoid'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot notVoid='Tuple {\n\tmessage : String = \'The type of the parameter may not be void\',\n\tstatus : Boolean = not self.type.oclIsTypeOf(RVoid)\n}.status'"
 * @generated
 */
public interface Parameter extends TypedElement, MappableElement {
    /**
     * Returns the value of the '<em><b>Type</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Type</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Type</em>' reference.
     * @see #setType(Type)
     * @see ca.mcgill.sel.ram.RamPackage#getParameter_Type()
     * @model required="true"
     * @generated
     */
    Type getType();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.ram.Parameter#getType <em>Type</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' reference.
     * @see #getType()
     * @generated
     */
    void setType(Type value);

} // Parameter
