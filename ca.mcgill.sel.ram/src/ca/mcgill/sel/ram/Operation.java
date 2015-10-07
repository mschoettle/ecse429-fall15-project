/**
 */
package ca.mcgill.sel.ram;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ca.mcgill.sel.ram.Operation#isAbstract <em>Abstract</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.Operation#getExtendedVisibility <em>Extended Visibility</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.Operation#getReturnType <em>Return Type</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.Operation#getParameters <em>Parameters</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.Operation#isStatic <em>Static</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.Operation#getOperationType <em>Operation Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see ca.mcgill.sel.ram.RamPackage#getOperation()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='messageViewDefined correctVisibility'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot messageViewDefined='Tuple {\n\tmessage : String = \'MessageView missing for public operation\',\n\tstatus : Boolean = if extendedVisibility = RAMVisibilityType::public and self.Classifier.oclIsTypeOf(Class) and self.partiality = core::COREPartialityType::none then self.Classifier.oclContainer().oclAsType(StructuralView).Aspect.messageViews->select(messageView : AbstractMessageView | messageView.oclIsTypeOf(MessageView))->one(messageView : AbstractMessageView | messageView.oclAsType(MessageView).specifies = self) else true endif\n}.status' correctVisibility='Tuple {\n\tmessage : String = \'COREVisibility and RAMVisibility attributes are not in sync\',\n\tstatus : Boolean = if visibility = core::COREVisibilityType::public then extendedVisibility = RAMVisibilityType::public else extendedVisibility <> RAMVisibilityType::public endif\n}.status'"
 * @generated
 */
public interface Operation extends NamedElement, MappableElement, Traceable {
    /**
     * Returns the value of the '<em><b>Abstract</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Abstract</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Abstract</em>' attribute.
     * @see #setAbstract(boolean)
     * @see ca.mcgill.sel.ram.RamPackage#getOperation_Abstract()
     * @model default="false"
     * @generated
     */
    boolean isAbstract();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.ram.Operation#isAbstract <em>Abstract</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Abstract</em>' attribute.
     * @see #isAbstract()
     * @generated
     */
    void setAbstract(boolean value);

    /**
     * Returns the value of the '<em><b>Extended Visibility</b></em>' attribute.
     * The default value is <code>"public"</code>.
     * The literals are from the enumeration {@link ca.mcgill.sel.ram.RAMVisibilityType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Extended Visibility</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Extended Visibility</em>' attribute.
     * @see ca.mcgill.sel.ram.RAMVisibilityType
     * @see #setExtendedVisibility(RAMVisibilityType)
     * @see ca.mcgill.sel.ram.RamPackage#getOperation_ExtendedVisibility()
     * @model default="public"
     * @generated
     */
    RAMVisibilityType getExtendedVisibility();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.ram.Operation#getExtendedVisibility <em>Extended Visibility</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Extended Visibility</em>' attribute.
     * @see ca.mcgill.sel.ram.RAMVisibilityType
     * @see #getExtendedVisibility()
     * @generated
     */
    void setExtendedVisibility(RAMVisibilityType value);

    /**
     * Returns the value of the '<em><b>Return Type</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Return Type</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Return Type</em>' reference.
     * @see #setReturnType(Type)
     * @see ca.mcgill.sel.ram.RamPackage#getOperation_ReturnType()
     * @model required="true"
     * @generated
     */
    Type getReturnType();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.ram.Operation#getReturnType <em>Return Type</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Return Type</em>' reference.
     * @see #getReturnType()
     * @generated
     */
    void setReturnType(Type value);

    /**
     * Returns the value of the '<em><b>Parameters</b></em>' containment reference list.
     * The list contents are of type {@link ca.mcgill.sel.ram.Parameter}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Parameters</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Parameters</em>' containment reference list.
     * @see ca.mcgill.sel.ram.RamPackage#getOperation_Parameters()
     * @model containment="true"
     * @generated
     */
    EList<Parameter> getParameters();

    /**
     * Returns the value of the '<em><b>Static</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Static</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Static</em>' attribute.
     * @see #setStatic(boolean)
     * @see ca.mcgill.sel.ram.RamPackage#getOperation_Static()
     * @model default="false"
     * @generated
     */
    boolean isStatic();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.ram.Operation#isStatic <em>Static</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Static</em>' attribute.
     * @see #isStatic()
     * @generated
     */
    void setStatic(boolean value);

    /**
     * Returns the value of the '<em><b>Operation Type</b></em>' attribute.
     * The default value is <code>"Normal"</code>.
     * The literals are from the enumeration {@link ca.mcgill.sel.ram.OperationType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Operation Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Operation Type</em>' attribute.
     * @see ca.mcgill.sel.ram.OperationType
     * @see #setOperationType(OperationType)
     * @see ca.mcgill.sel.ram.RamPackage#getOperation_OperationType()
     * @model default="Normal" required="true"
     * @generated
     */
    OperationType getOperationType();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.ram.Operation#getOperationType <em>Operation Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Operation Type</em>' attribute.
     * @see ca.mcgill.sel.ram.OperationType
     * @see #getOperationType()
     * @generated
     */
    void setOperationType(OperationType value);

} // Operation
