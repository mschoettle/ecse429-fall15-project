/**
 */
package ca.mcgill.sel.ram;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Class</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ca.mcgill.sel.ram.Class#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.Class#isAbstract <em>Abstract</em>}</li>
 * </ul>
 * </p>
 *
 * @see ca.mcgill.sel.ram.RamPackage#getClass_()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='notSelfSuperType'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot notSelfSuperType='Tuple {\n\tmessage : String = \'A class may not be it\\\'s own supertype\',\n\tstatus : Boolean = not self.superTypes->includes(self)\n}.status'"
 * @generated
 */
public interface Class extends Classifier {
    /**
     * Returns the value of the '<em><b>Attributes</b></em>' containment reference list.
     * The list contents are of type {@link ca.mcgill.sel.ram.Attribute}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Attributes</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Attributes</em>' containment reference list.
     * @see ca.mcgill.sel.ram.RamPackage#getClass_Attributes()
     * @model containment="true"
     * @generated
     */
    EList<Attribute> getAttributes();

    /**
     * Returns the value of the '<em><b>Abstract</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Abstract</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Abstract</em>' attribute.
     * @see #setAbstract(boolean)
     * @see ca.mcgill.sel.ram.RamPackage#getClass_Abstract()
     * @model
     * @generated
     */
    boolean isAbstract();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.ram.Class#isAbstract <em>Abstract</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Abstract</em>' attribute.
     * @see #isAbstract()
     * @generated
     */
    void setAbstract(boolean value);

} // Class
