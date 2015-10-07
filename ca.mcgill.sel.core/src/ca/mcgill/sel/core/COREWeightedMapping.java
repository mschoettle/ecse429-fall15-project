/**
 */
package ca.mcgill.sel.core;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CORE Weighted Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link ca.mcgill.sel.core.COREWeightedMapping#getWeight <em>Weight</em>}</li>
 * </ul>
 *
 * @see ca.mcgill.sel.core.CorePackage#getCOREWeightedMapping()
 * @model
 * @generated
 */
public interface COREWeightedMapping extends COREMapping<COREImpactNode> {
    /**
     * Returns the value of the '<em><b>Weight</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Weight</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Weight</em>' attribute.
     * @see #setWeight(int)
     * @see ca.mcgill.sel.core.CorePackage#getCOREWeightedMapping_Weight()
     * @model required="true"
     * @generated
     */
    int getWeight();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.core.COREWeightedMapping#getWeight <em>Weight</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Weight</em>' attribute.
     * @see #getWeight()
     * @generated
     */
    void setWeight(int value);

} // COREWeightedMapping
