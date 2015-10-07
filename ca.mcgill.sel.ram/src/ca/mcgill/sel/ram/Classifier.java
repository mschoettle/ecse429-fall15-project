/**
 */
package ca.mcgill.sel.ram;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Classifier</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ca.mcgill.sel.ram.Classifier#getOperations <em>Operations</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.Classifier#getAssociationEnds <em>Association Ends</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.Classifier#getTypeParameters <em>Type Parameters</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.Classifier#getSuperTypes <em>Super Types</em>}</li>
 * </ul>
 * </p>
 *
 * @see ca.mcgill.sel.ram.RamPackage#getClassifier()
 * @model abstract="true"
 * @generated
 */
public interface Classifier extends ObjectType, Traceable {
    /**
     * Returns the value of the '<em><b>Operations</b></em>' containment reference list.
     * The list contents are of type {@link ca.mcgill.sel.ram.Operation}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Operations</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Operations</em>' containment reference list.
     * @see ca.mcgill.sel.ram.RamPackage#getClassifier_Operations()
     * @model containment="true"
     * @generated
     */
    EList<Operation> getOperations();

    /**
     * Returns the value of the '<em><b>Association Ends</b></em>' containment reference list.
     * The list contents are of type {@link ca.mcgill.sel.ram.AssociationEnd}.
     * It is bidirectional and its opposite is '{@link ca.mcgill.sel.ram.AssociationEnd#getClassifier <em>Classifier</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Association Ends</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Association Ends</em>' containment reference list.
     * @see ca.mcgill.sel.ram.RamPackage#getClassifier_AssociationEnds()
     * @see ca.mcgill.sel.ram.AssociationEnd#getClassifier
     * @model opposite="classifier" containment="true"
     * @generated
     */
    EList<AssociationEnd> getAssociationEnds();

    /**
     * Returns the value of the '<em><b>Type Parameters</b></em>' containment reference list.
     * The list contents are of type {@link ca.mcgill.sel.ram.TypeParameter}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Type Parameters</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Type Parameters</em>' containment reference list.
     * @see ca.mcgill.sel.ram.RamPackage#getClassifier_TypeParameters()
     * @model containment="true"
     * @generated
     */
    EList<TypeParameter> getTypeParameters();

    /**
     * Returns the value of the '<em><b>Super Types</b></em>' reference list.
     * The list contents are of type {@link ca.mcgill.sel.ram.Classifier}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Super Types</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Super Types</em>' reference list.
     * @see ca.mcgill.sel.ram.RamPackage#getClassifier_SuperTypes()
     * @model
     * @generated
     */
    EList<Classifier> getSuperTypes();

} // Classifier
