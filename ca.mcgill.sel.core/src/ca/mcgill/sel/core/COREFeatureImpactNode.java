/**
 */
package ca.mcgill.sel.core;

import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CORE Feature Impact</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link ca.mcgill.sel.core.COREFeatureImpactNode#getRelativeFeatureWeight <em>Relative Feature Weight</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.COREFeatureImpactNode#getRepresents <em>Represents</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.COREFeatureImpactNode#getWeightedMappings <em>Weighted Mappings</em>}</li>
 * </ul>
 *
 * @see ca.mcgill.sel.core.CorePackage#getCOREFeatureImpactNode()
 * @model
 * @generated
 */
public interface COREFeatureImpactNode extends COREImpactNode {
    /**
     * Returns the value of the '<em><b>Relative Feature Weight</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Relative Feature Weight</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Relative Feature Weight</em>' attribute.
     * @see #setRelativeFeatureWeight(int)
     * @see ca.mcgill.sel.core.CorePackage#getCOREFeatureImpactNode_RelativeFeatureWeight()
     * @model required="true"
     * @generated
     */
    int getRelativeFeatureWeight();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.core.COREFeatureImpactNode#getRelativeFeatureWeight <em>Relative Feature Weight</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Relative Feature Weight</em>' attribute.
     * @see #getRelativeFeatureWeight()
     * @generated
     */
    void setRelativeFeatureWeight(int value);

    /**
     * Returns the value of the '<em><b>Represents</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Represents</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Represents</em>' reference.
     * @see #setRepresents(COREFeature)
     * @see ca.mcgill.sel.core.CorePackage#getCOREFeatureImpactNode_Represents()
     * @model required="true"
     * @generated
     */
    COREFeature getRepresents();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.core.COREFeatureImpactNode#getRepresents <em>Represents</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Represents</em>' reference.
     * @see #getRepresents()
     * @generated
     */
    void setRepresents(COREFeature value);

    /**
     * Returns the value of the '<em><b>Weighted Mappings</b></em>' reference list.
     * The list contents are of type {@link ca.mcgill.sel.core.COREWeightedMapping}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Weighted Mappings</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Weighted Mappings</em>' reference list.
     * @see ca.mcgill.sel.core.CorePackage#getCOREFeatureImpactNode_WeightedMappings()
     * @model
     * @generated
     */
    EList<COREWeightedMapping> getWeightedMappings();

} // COREFeatureImpact
