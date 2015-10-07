/**
 */
package ca.mcgill.sel.ram;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Association</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ca.mcgill.sel.ram.Association#getEnds <em>Ends</em>}</li>
 * </ul>
 * </p>
 *
 * @see ca.mcgill.sel.ram.RamPackage#getAssociation()
 * @model
 * @generated
 */
public interface Association extends NamedElement {
    /**
     * Returns the value of the '<em><b>Ends</b></em>' reference list.
     * The list contents are of type {@link ca.mcgill.sel.ram.AssociationEnd}.
     * It is bidirectional and its opposite is '{@link ca.mcgill.sel.ram.AssociationEnd#getAssoc <em>Assoc</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Ends</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Ends</em>' reference list.
     * @see ca.mcgill.sel.ram.RamPackage#getAssociation_Ends()
     * @see ca.mcgill.sel.ram.AssociationEnd#getAssoc
     * @model opposite="assoc" lower="2" upper="2"
     * @generated
     */
    EList<AssociationEnd> getEnds();

} // Association
