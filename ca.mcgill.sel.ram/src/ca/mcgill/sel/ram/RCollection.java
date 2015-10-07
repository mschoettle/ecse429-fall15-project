/**
 */
package ca.mcgill.sel.ram;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>RCollection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ca.mcgill.sel.ram.RCollection#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see ca.mcgill.sel.ram.RamPackage#getRCollection()
 * @model abstract="true"
 * @generated
 */
public interface RCollection extends Type, ImplementationClass {
    /**
     * Returns the value of the '<em><b>Type</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Type</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Type</em>' reference.
     * @see #setType(ObjectType)
     * @see ca.mcgill.sel.ram.RamPackage#getRCollection_Type()
     * @model required="true"
     * @generated
     */
    ObjectType getType();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.ram.RCollection#getType <em>Type</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' reference.
     * @see #getType()
     * @generated
     */
    void setType(ObjectType value);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model kind="operation" required="true"
     *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot body='let partial : String = if type.oclIsTypeOf(Class) or type.oclIsTypeOf(Operation) and type.oclAsType(core::COREModelElement).partiality = core::COREPartialityType::public then \'|\' else if type.oclIsTypeOf(Class) or type.oclIsTypeOf(Operation) and type.oclAsType(core::COREModelElement).partiality = core::COREPartialityType::concern then \'\\u00A6\' else \'\' endif endif in let typeName : String = self.oclType().toString() in let name : String = typeName.substring(typeName.lastIndexOf(\':\') + 2, typeName.size()) in if type.name.oclIsUndefined() then name else name + \'<\' + partial + type.name + \'>\' endif'"
     * @generated
     */
    String getName();

} // RCollection
