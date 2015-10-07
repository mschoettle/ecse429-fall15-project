/**
 */
package ca.mcgill.sel.core;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CORE Composition Specification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link ca.mcgill.sel.core.CORECompositionSpecification#getSource <em>Source</em>}</li>
 * </ul>
 *
 * @see ca.mcgill.sel.core.CorePackage#getCORECompositionSpecification()
 * @model abstract="true"
 * @generated
 */
public interface CORECompositionSpecification<S extends COREModel> extends EObject {
    /**
     * Returns the value of the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Source</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Source</em>' reference.
     * @see #setSource(COREModel)
     * @see ca.mcgill.sel.core.CorePackage#getCORECompositionSpecification_Source()
     * @model required="true"
     * @generated
     */
    S getSource();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.core.CORECompositionSpecification#getSource <em>Source</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Source</em>' reference.
     * @see #getSource()
     * @generated
     */
    void setSource(S value);

} // CORECompositionSpecification
