/**
 */
package ca.mcgill.sel.ram;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ca.mcgill.sel.ram.Property#getLowerBound <em>Lower Bound</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.Property#getUpperBound <em>Upper Bound</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.Property#getReferenceType <em>Reference Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see ca.mcgill.sel.ram.RamPackage#getProperty()
 * @model abstract="true"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='validUpperBound validLowerBound validMultiplicity'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot validUpperBound='Tuple {\n\tmessage : String = \'The upperbound must be greater than zero or -1 representing *\',\n\tstatus : Boolean = self.upperBound > 0 or self.upperBound = -1\n}.status' validLowerBound='Tuple {\n\tmessage : String = \'The lowerbound must be greater than or equal to zero\',\n\tstatus : Boolean = self.lowerBound >= 0\n}.status' validMultiplicity='Tuple {\n\tmessage : String = \'The lowerBound must be less than or equal to the upperBound\',\n\tstatus : Boolean = self.lowerBound <= self.upperBound or self.upperBound = -1\n}.status'"
 * @generated
 */
public interface Property extends StructuralFeature {
    /**
     * Returns the value of the '<em><b>Lower Bound</b></em>' attribute.
     * The default value is <code>"0"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Lower Bound</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Lower Bound</em>' attribute.
     * @see #setLowerBound(int)
     * @see ca.mcgill.sel.ram.RamPackage#getProperty_LowerBound()
     * @model default="0"
     * @generated
     */
    int getLowerBound();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.ram.Property#getLowerBound <em>Lower Bound</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Lower Bound</em>' attribute.
     * @see #getLowerBound()
     * @generated
     */
    void setLowerBound(int value);

    /**
     * Returns the value of the '<em><b>Upper Bound</b></em>' attribute.
     * The default value is <code>"1"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Upper Bound</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Upper Bound</em>' attribute.
     * @see #setUpperBound(int)
     * @see ca.mcgill.sel.ram.RamPackage#getProperty_UpperBound()
     * @model default="1"
     * @generated
     */
    int getUpperBound();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.ram.Property#getUpperBound <em>Upper Bound</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Upper Bound</em>' attribute.
     * @see #getUpperBound()
     * @generated
     */
    void setUpperBound(int value);

    /**
     * Returns the value of the '<em><b>Reference Type</b></em>' attribute.
     * The default value is <code>"Regular"</code>.
     * The literals are from the enumeration {@link ca.mcgill.sel.ram.ReferenceType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Reference Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Reference Type</em>' attribute.
     * @see ca.mcgill.sel.ram.ReferenceType
     * @see #setReferenceType(ReferenceType)
     * @see ca.mcgill.sel.ram.RamPackage#getProperty_ReferenceType()
     * @model default="Regular"
     * @generated
     */
    ReferenceType getReferenceType();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.ram.Property#getReferenceType <em>Reference Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Reference Type</em>' attribute.
     * @see ca.mcgill.sel.ram.ReferenceType
     * @see #getReferenceType()
     * @generated
     */
    void setReferenceType(ReferenceType value);

} // Property
