/**
 */
package ca.mcgill.sel.core;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CORE Model Reuse</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link ca.mcgill.sel.core.COREModelReuse#getCompositions <em>Compositions</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.COREModelReuse#getReuse <em>Reuse</em>}</li>
 * </ul>
 *
 * @see ca.mcgill.sel.core.CorePackage#getCOREModelReuse()
 * @model
 * @generated
 */
public interface COREModelReuse extends EObject {
    /**
     * Returns the value of the '<em><b>Compositions</b></em>' containment reference list.
     * The list contents are of type {@link ca.mcgill.sel.core.COREModelCompositionSpecification}&lt;?>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Compositions</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Compositions</em>' containment reference list.
     * @see ca.mcgill.sel.core.CorePackage#getCOREModelReuse_Compositions()
     * @model containment="true"
     * @generated
     */
    EList<COREModelCompositionSpecification<?>> getCompositions();

    /**
     * Returns the value of the '<em><b>Reuse</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Reuse</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Reuse</em>' reference.
     * @see #setReuse(COREReuse)
     * @see ca.mcgill.sel.core.CorePackage#getCOREModelReuse_Reuse()
     * @model required="true"
     * @generated
     */
    COREReuse getReuse();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.core.COREModelReuse#getReuse <em>Reuse</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Reuse</em>' reference.
     * @see #getReuse()
     * @generated
     */
    void setReuse(COREReuse value);

} // COREModelReuse
