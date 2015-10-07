/**
 */
package ca.mcgill.sel.core;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CORE Configuration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link ca.mcgill.sel.core.COREConfiguration#getSelected <em>Selected</em>}</li>
 * </ul>
 *
 * @see ca.mcgill.sel.core.CorePackage#getCOREConfiguration()
 * @model abstract="true"
 * @generated
 */
public interface COREConfiguration extends CORENamedElement, CORECompositionSpecification<COREFeatureModel> {
    /**
     * Returns the value of the '<em><b>Selected</b></em>' reference list.
     * The list contents are of type {@link ca.mcgill.sel.core.COREFeature}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Selected</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Selected</em>' reference list.
     * @see ca.mcgill.sel.core.CorePackage#getCOREConfiguration_Selected()
     * @model
     * @generated
     */
    EList<COREFeature> getSelected();

} // COREConfiguration
