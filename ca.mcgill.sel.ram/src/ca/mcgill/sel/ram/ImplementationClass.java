/**
 */
package ca.mcgill.sel.ram;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Implementation Class</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ca.mcgill.sel.ram.ImplementationClass#getInstanceClassName <em>Instance Class Name</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.ImplementationClass#isInterface <em>Interface</em>}</li>
 * </ul>
 * </p>
 *
 * @see ca.mcgill.sel.ram.RamPackage#getImplementationClass()
 * @model
 * @generated
 */
public interface ImplementationClass extends Classifier {
    /**
     * Returns the value of the '<em><b>Instance Class Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Instance Class Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Instance Class Name</em>' attribute.
     * @see #setInstanceClassName(String)
     * @see ca.mcgill.sel.ram.RamPackage#getImplementationClass_InstanceClassName()
     * @model required="true"
     * @generated
     */
    String getInstanceClassName();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.ram.ImplementationClass#getInstanceClassName <em>Instance Class Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Instance Class Name</em>' attribute.
     * @see #getInstanceClassName()
     * @generated
     */
    void setInstanceClassName(String value);

    /**
     * Returns the value of the '<em><b>Interface</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Interface</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Interface</em>' attribute.
     * @see #setInterface(boolean)
     * @see ca.mcgill.sel.ram.RamPackage#getImplementationClass_Interface()
     * @model required="true"
     * @generated
     */
    boolean isInterface();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.ram.ImplementationClass#isInterface <em>Interface</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Interface</em>' attribute.
     * @see #isInterface()
     * @generated
     */
    void setInterface(boolean value);

} // ImplementationClass
