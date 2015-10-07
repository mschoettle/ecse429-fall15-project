/**
 */
package ca.mcgill.sel.ram;

import ca.mcgill.sel.core.COREModel;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Aspect</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ca.mcgill.sel.ram.Aspect#getStructuralView <em>Structural View</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.Aspect#getMessageViews <em>Message Views</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.Aspect#getInstantiations <em>Instantiations</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.Aspect#getLayout <em>Layout</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.Aspect#getStateViews <em>State Views</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.Aspect#getWovenAspects <em>Woven Aspects</em>}</li>
 * </ul>
 * </p>
 *
 * @see ca.mcgill.sel.ram.RamPackage#getAspect()
 * @model
 * @generated
 */
public interface Aspect extends NamedElement, COREModel {
    /**
     * Returns the value of the '<em><b>Structural View</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Structural View</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Structural View</em>' containment reference.
     * @see #setStructuralView(StructuralView)
     * @see ca.mcgill.sel.ram.RamPackage#getAspect_StructuralView()
     * @model containment="true" required="true"
     * @generated
     */
    StructuralView getStructuralView();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.ram.Aspect#getStructuralView <em>Structural View</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Structural View</em>' containment reference.
     * @see #getStructuralView()
     * @generated
     */
    void setStructuralView(StructuralView value);

    /**
     * Returns the value of the '<em><b>Message Views</b></em>' containment reference list.
     * The list contents are of type {@link ca.mcgill.sel.ram.AbstractMessageView}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Message Views</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Message Views</em>' containment reference list.
     * @see ca.mcgill.sel.ram.RamPackage#getAspect_MessageViews()
     * @model containment="true" ordered="false"
     * @generated
     */
    EList<AbstractMessageView> getMessageViews();

    /**
     * Returns the value of the '<em><b>Instantiations</b></em>' containment reference list.
     * The list contents are of type {@link ca.mcgill.sel.ram.Instantiation}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Instantiations</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Instantiations</em>' containment reference list.
     * @see ca.mcgill.sel.ram.RamPackage#getAspect_Instantiations()
     * @model containment="true"
     * @generated
     */
    EList<Instantiation> getInstantiations();

    /**
     * Returns the value of the '<em><b>Layout</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Layout</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Layout</em>' containment reference.
     * @see #setLayout(Layout)
     * @see ca.mcgill.sel.ram.RamPackage#getAspect_Layout()
     * @model containment="true"
     * @generated
     */
    Layout getLayout();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.ram.Aspect#getLayout <em>Layout</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Layout</em>' containment reference.
     * @see #getLayout()
     * @generated
     */
    void setLayout(Layout value);

    /**
     * Returns the value of the '<em><b>State Views</b></em>' containment reference list.
     * The list contents are of type {@link ca.mcgill.sel.ram.StateView}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>State Views</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>State Views</em>' containment reference list.
     * @see ca.mcgill.sel.ram.RamPackage#getAspect_StateViews()
     * @model containment="true"
     * @generated
     */
    EList<StateView> getStateViews();

    /**
     * Returns the value of the '<em><b>Woven Aspects</b></em>' containment reference list.
     * The list contents are of type {@link ca.mcgill.sel.ram.WovenAspect}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Woven Aspects</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Woven Aspects</em>' containment reference list.
     * @see ca.mcgill.sel.ram.RamPackage#getAspect_WovenAspects()
     * @model containment="true"
     * @generated
     */
    EList<WovenAspect> getWovenAspects();

} // Aspect
