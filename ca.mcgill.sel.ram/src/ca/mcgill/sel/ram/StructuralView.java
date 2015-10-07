/**
 */
package ca.mcgill.sel.ram;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Structural View</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ca.mcgill.sel.ram.StructuralView#getClasses <em>Classes</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.StructuralView#getAssociations <em>Associations</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.StructuralView#getTypes <em>Types</em>}</li>
 * </ul>
 * </p>
 *
 * @see ca.mcgill.sel.ram.RamPackage#getStructuralView()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='uniqueTypes noTwoClassesWithSameName'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot uniqueTypes='Tuple {\n\tmessage : String = \'There may be only one type of the same type\',\n\tstatus : Boolean = self.types->isUnique(name)\n}.status' noTwoClassesWithSameName='Tuple {\n\tmessage : String = \'Name of a class has to be unique\',\n\tstatus : Boolean = self.classes->isUnique(name)\n}.status'"
 * @generated
 */
public interface StructuralView extends EObject {
    /**
     * Returns the value of the '<em><b>Classes</b></em>' containment reference list.
     * The list contents are of type {@link ca.mcgill.sel.ram.Classifier}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Classes</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Classes</em>' containment reference list.
     * @see ca.mcgill.sel.ram.RamPackage#getStructuralView_Classes()
     * @model containment="true"
     * @generated
     */
    EList<Classifier> getClasses();

    /**
     * Returns the value of the '<em><b>Associations</b></em>' containment reference list.
     * The list contents are of type {@link ca.mcgill.sel.ram.Association}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Associations</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Associations</em>' containment reference list.
     * @see ca.mcgill.sel.ram.RamPackage#getStructuralView_Associations()
     * @model containment="true"
     * @generated
     */
    EList<Association> getAssociations();

    /**
     * Returns the value of the '<em><b>Types</b></em>' containment reference list.
     * The list contents are of type {@link ca.mcgill.sel.ram.Type}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Types</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Types</em>' containment reference list.
     * @see ca.mcgill.sel.ram.RamPackage#getStructuralView_Types()
     * @model containment="true" ordered="false"
     * @generated
     */
    EList<Type> getTypes();

} // StructuralView
