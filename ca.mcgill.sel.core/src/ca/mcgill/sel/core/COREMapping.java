/**
 */
package ca.mcgill.sel.core;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CORE Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link ca.mcgill.sel.core.COREMapping#getTo <em>To</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.COREMapping#getFrom <em>From</em>}</li>
 * </ul>
 *
 * @see ca.mcgill.sel.core.CorePackage#getCOREMapping()
 * @model abstract="true"
 * @generated
 */
public interface COREMapping<T extends COREModelElement> extends EObject {
    /**
     * Returns the value of the '<em><b>To</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>To</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>To</em>' reference.
     * @see #setTo(COREModelElement)
     * @see ca.mcgill.sel.core.CorePackage#getCOREMapping_To()
     * @model required="true"
     * @generated
     */
    T getTo();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.core.COREMapping#getTo <em>To</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>To</em>' reference.
     * @see #getTo()
     * @generated
     */
    void setTo(T value);

    /**
     * Returns the value of the '<em><b>From</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>From</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>From</em>' reference.
     * @see #setFrom(COREModelElement)
     * @see ca.mcgill.sel.core.CorePackage#getCOREMapping_From()
     * @model required="true"
     * @generated
     */
    T getFrom();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.core.COREMapping#getFrom <em>From</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>From</em>' reference.
     * @see #getFrom()
     * @generated
     */
    void setFrom(T value);

} // COREMapping
