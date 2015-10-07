/**
 */
package ca.mcgill.sel.core;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CORE Contribution</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link ca.mcgill.sel.core.COREContribution#getRelativeWeight <em>Relative Weight</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.COREContribution#getSource <em>Source</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.COREContribution#getImpacts <em>Impacts</em>}</li>
 * </ul>
 *
 * @see ca.mcgill.sel.core.CorePackage#getCOREContribution()
 * @model
 * @generated
 */
public interface COREContribution extends EObject {
    /**
     * Returns the value of the '<em><b>Relative Weight</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Relative Weight</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Relative Weight</em>' attribute.
     * @see #setRelativeWeight(int)
     * @see ca.mcgill.sel.core.CorePackage#getCOREContribution_RelativeWeight()
     * @model required="true"
     * @generated
     */
    int getRelativeWeight();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.core.COREContribution#getRelativeWeight <em>Relative Weight</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Relative Weight</em>' attribute.
     * @see #getRelativeWeight()
     * @generated
     */
    void setRelativeWeight(int value);

    /**
     * Returns the value of the '<em><b>Source</b></em>' reference.
     * It is bidirectional and its opposite is '{@link ca.mcgill.sel.core.COREImpactNode#getOutgoing <em>Outgoing</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Source</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Source</em>' reference.
     * @see #setSource(COREImpactNode)
     * @see ca.mcgill.sel.core.CorePackage#getCOREContribution_Source()
     * @see ca.mcgill.sel.core.COREImpactNode#getOutgoing
     * @model opposite="outgoing" required="true"
     * @generated
     */
    COREImpactNode getSource();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.core.COREContribution#getSource <em>Source</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Source</em>' reference.
     * @see #getSource()
     * @generated
     */
    void setSource(COREImpactNode value);

    /**
     * Returns the value of the '<em><b>Impacts</b></em>' reference.
     * It is bidirectional and its opposite is '{@link ca.mcgill.sel.core.COREImpactNode#getIncoming <em>Incoming</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Impacts</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Impacts</em>' reference.
     * @see #setImpacts(COREImpactNode)
     * @see ca.mcgill.sel.core.CorePackage#getCOREContribution_Impacts()
     * @see ca.mcgill.sel.core.COREImpactNode#getIncoming
     * @model opposite="incoming" required="true"
     * @generated
     */
    COREImpactNode getImpacts();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.core.COREContribution#getImpacts <em>Impacts</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Impacts</em>' reference.
     * @see #getImpacts()
     * @generated
     */
    void setImpacts(COREImpactNode value);

} // COREContribution
