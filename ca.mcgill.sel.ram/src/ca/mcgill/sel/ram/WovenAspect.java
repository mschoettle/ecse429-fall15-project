/**
 */
package ca.mcgill.sel.ram;

import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Woven Aspect</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ca.mcgill.sel.ram.WovenAspect#getComesFrom <em>Comes From</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.WovenAspect#getWovenElements <em>Woven Elements</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.WovenAspect#getChildren <em>Children</em>}</li>
 * </ul>
 * </p>
 *
 * @see ca.mcgill.sel.ram.RamPackage#getWovenAspect()
 * @model
 * @generated
 */
public interface WovenAspect extends NamedElement {
    /**
     * Returns the value of the '<em><b>Comes From</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Comes From</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Comes From</em>' reference.
     * @see #setComesFrom(Aspect)
     * @see ca.mcgill.sel.ram.RamPackage#getWovenAspect_ComesFrom()
     * @model required="true"
     * @generated
     */
    Aspect getComesFrom();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.ram.WovenAspect#getComesFrom <em>Comes From</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Comes From</em>' reference.
     * @see #getComesFrom()
     * @generated
     */
    void setComesFrom(Aspect value);

    /**
     * Returns the value of the '<em><b>Woven Elements</b></em>' reference list.
     * The list contents are of type {@link ca.mcgill.sel.ram.Traceable}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Woven Elements</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Woven Elements</em>' reference list.
     * @see ca.mcgill.sel.ram.RamPackage#getWovenAspect_WovenElements()
     * @model
     * @generated
     */
    EList<Traceable> getWovenElements();

    /**
     * Returns the value of the '<em><b>Children</b></em>' containment reference list.
     * The list contents are of type {@link ca.mcgill.sel.ram.WovenAspect}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Children</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Children</em>' containment reference list.
     * @see ca.mcgill.sel.ram.RamPackage#getWovenAspect_Children()
     * @model containment="true"
     * @generated
     */
    EList<WovenAspect> getChildren();

} // WovenAspect
