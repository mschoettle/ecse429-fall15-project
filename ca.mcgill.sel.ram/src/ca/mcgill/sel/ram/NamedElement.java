/**
 */
package ca.mcgill.sel.ram;

import ca.mcgill.sel.core.CORENamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Named Element</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see ca.mcgill.sel.ram.RamPackage#getNamedElement()
 * @model abstract="true"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='validName'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot validName='Tuple {\n\tmessage : String = \'Name of RAM elements may not be empty\',\n\tstatus : Boolean = if self.oclIsTypeOf(AssociationEnd) and self.oclAsType(AssociationEnd).navigable = false then true else self.name <> \'\' endif\n}.status'"
 * @generated
 */
public interface NamedElement extends CORENamedElement {
} // NamedElement
