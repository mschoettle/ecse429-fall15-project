/**
 */
package ca.mcgill.sel.core;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CORE Impact Model Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link ca.mcgill.sel.core.COREImpactNode#getScalingFactor <em>Scaling Factor</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.COREImpactNode#getOffset <em>Offset</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.COREImpactNode#getOutgoing <em>Outgoing</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.COREImpactNode#getIncoming <em>Incoming</em>}</li>
 * </ul>
 *
 * @see ca.mcgill.sel.core.CorePackage#getCOREImpactNode()
 * @model
 * @generated
 */
public interface COREImpactNode extends COREModelElement {
    /**
     * Returns the value of the '<em><b>Scaling Factor</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Scaling Factor</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Scaling Factor</em>' attribute.
     * @see #setScalingFactor(float)
     * @see ca.mcgill.sel.core.CorePackage#getCOREImpactNode_ScalingFactor()
     * @model required="true"
     * @generated
     */
    float getScalingFactor();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.core.COREImpactNode#getScalingFactor <em>Scaling Factor</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Scaling Factor</em>' attribute.
     * @see #getScalingFactor()
     * @generated
     */
    void setScalingFactor(float value);

    /**
     * Returns the value of the '<em><b>Offset</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Offset</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Offset</em>' attribute.
     * @see #setOffset(float)
     * @see ca.mcgill.sel.core.CorePackage#getCOREImpactNode_Offset()
     * @model required="true"
     * @generated
     */
    float getOffset();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.core.COREImpactNode#getOffset <em>Offset</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Offset</em>' attribute.
     * @see #getOffset()
     * @generated
     */
    void setOffset(float value);

    /**
     * Returns the value of the '<em><b>Outgoing</b></em>' reference list.
     * The list contents are of type {@link ca.mcgill.sel.core.COREContribution}.
     * It is bidirectional and its opposite is '{@link ca.mcgill.sel.core.COREContribution#getSource <em>Source</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Outgoing</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Outgoing</em>' reference list.
     * @see ca.mcgill.sel.core.CorePackage#getCOREImpactNode_Outgoing()
     * @see ca.mcgill.sel.core.COREContribution#getSource
     * @model opposite="source"
     * @generated
     */
    EList<COREContribution> getOutgoing();

    /**
     * Returns the value of the '<em><b>Incoming</b></em>' reference list.
     * The list contents are of type {@link ca.mcgill.sel.core.COREContribution}.
     * It is bidirectional and its opposite is '{@link ca.mcgill.sel.core.COREContribution#getImpacts <em>Impacts</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Incoming</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Incoming</em>' reference list.
     * @see ca.mcgill.sel.core.CorePackage#getCOREImpactNode_Incoming()
     * @see ca.mcgill.sel.core.COREContribution#getImpacts
     * @model opposite="impacts"
     * @generated
     */
    EList<COREContribution> getIncoming();

} // COREImpactModelElement
