/**
 */
package ca.mcgill.sel.core;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CORE Model Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link ca.mcgill.sel.core.COREModelElement#getPartiality <em>Partiality</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.COREModelElement#getVisibility <em>Visibility</em>}</li>
 * </ul>
 *
 * @see ca.mcgill.sel.core.CorePackage#getCOREModelElement()
 * @model abstract="true"
 * @generated
 */
public interface COREModelElement extends CORENamedElement {

    /**
     * Returns the value of the '<em><b>Partiality</b></em>' attribute.
     * The default value is <code>"none"</code>.
     * The literals are from the enumeration {@link ca.mcgill.sel.core.COREPartialityType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Partiality</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Partiality</em>' attribute.
     * @see ca.mcgill.sel.core.COREPartialityType
     * @see #setPartiality(COREPartialityType)
     * @see ca.mcgill.sel.core.CorePackage#getCOREModelElement_Partiality()
     * @model default="none"
     * @generated
     */
    COREPartialityType getPartiality();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.core.COREModelElement#getPartiality <em>Partiality</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Partiality</em>' attribute.
     * @see ca.mcgill.sel.core.COREPartialityType
     * @see #getPartiality()
     * @generated
     */
    void setPartiality(COREPartialityType value);

    /**
     * Returns the value of the '<em><b>Visibility</b></em>' attribute.
     * The default value is <code>"concern"</code>.
     * The literals are from the enumeration {@link ca.mcgill.sel.core.COREVisibilityType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Visibility</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Visibility</em>' attribute.
     * @see ca.mcgill.sel.core.COREVisibilityType
     * @see #setVisibility(COREVisibilityType)
     * @see ca.mcgill.sel.core.CorePackage#getCOREModelElement_Visibility()
     * @model default="concern"
     * @generated
     */
    COREVisibilityType getVisibility();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.core.COREModelElement#getVisibility <em>Visibility</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Visibility</em>' attribute.
     * @see ca.mcgill.sel.core.COREVisibilityType
     * @see #getVisibility()
     * @generated
     */
    void setVisibility(COREVisibilityType value);
} // COREModelElement
