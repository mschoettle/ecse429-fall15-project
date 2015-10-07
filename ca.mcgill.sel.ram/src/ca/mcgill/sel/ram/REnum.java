/**
 */
package ca.mcgill.sel.ram;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>REnum</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ca.mcgill.sel.ram.REnum#getLiterals <em>Literals</em>}</li>
 * </ul>
 * </p>
 *
 * @see ca.mcgill.sel.ram.RamPackage#getREnum()
 * @model
 * @generated
 */
public interface REnum extends PrimitiveType {
    /**
     * Returns the value of the '<em><b>Literals</b></em>' containment reference list.
     * The list contents are of type {@link ca.mcgill.sel.ram.REnumLiteral}.
     * It is bidirectional and its opposite is '{@link ca.mcgill.sel.ram.REnumLiteral#getEnum <em>Enum</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Literals</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Literals</em>' containment reference list.
     * @see ca.mcgill.sel.ram.RamPackage#getREnum_Literals()
     * @see ca.mcgill.sel.ram.REnumLiteral#getEnum
     * @model opposite="enum" containment="true" required="true"
     * @generated
     */
    EList<REnumLiteral> getLiterals();

} // REnum
