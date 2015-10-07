/**
 */
package ca.mcgill.sel.ram;

import ca.mcgill.sel.core.COREBinding;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Instantiation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ca.mcgill.sel.ram.Instantiation#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see ca.mcgill.sel.ram.RamPackage#getInstantiation()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='aspectCannotMapSelf'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot aspectCannotMapSelf='Tuple {\n\tmessage : String = \'Aspect may not depend on itself\',\n\tstatus : Boolean = not (self.source = self.Aspect)\n}.status'"
 * @generated
 */
public interface Instantiation extends COREBinding<Aspect, ClassifierMapping> {
    /**
     * Returns the value of the '<em><b>Type</b></em>' attribute.
     * The default value is <code>"Depends"</code>.
     * The literals are from the enumeration {@link ca.mcgill.sel.ram.InstantiationType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Type</em>' attribute.
     * @see ca.mcgill.sel.ram.InstantiationType
     * @see ca.mcgill.sel.ram.RamPackage#getInstantiation_Type()
     * @model default="Depends" required="true" changeable="false" derived="true"
     *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot derivation='if (self.oclContainer().oclIsTypeOf(ram::Aspect)) then InstantiationType::Extends else InstantiationType::Depends endif'"
     * @generated
     */
    InstantiationType getType();

} // Instantiation
