/**
 */
package ca.mcgill.sel.core;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CORE Impact Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link ca.mcgill.sel.core.COREImpactModel#getImpactModelElements <em>Impact Model Elements</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.COREImpactModel#getLayouts <em>Layouts</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.COREImpactModel#getContributions <em>Contributions</em>}</li>
 * </ul>
 *
 * @see ca.mcgill.sel.core.CorePackage#getCOREImpactModel()
 * @model
 * @generated
 */
public interface COREImpactModel extends COREModel {
    /**
     * Returns the value of the '<em><b>Impact Model Elements</b></em>' containment reference list.
     * The list contents are of type {@link ca.mcgill.sel.core.COREImpactNode}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Impact Model Elements</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Impact Model Elements</em>' containment reference list.
     * @see ca.mcgill.sel.core.CorePackage#getCOREImpactModel_ImpactModelElements()
     * @model containment="true"
     * @generated
     */
    EList<COREImpactNode> getImpactModelElements();

    /**
     * Returns the value of the '<em><b>Layouts</b></em>' map.
     * The key is of type {@link org.eclipse.emf.ecore.EObject},
     * and the value is of type list of {@link java.util.Map.Entry<org.eclipse.emf.ecore.EObject, ca.mcgill.sel.core.LayoutElement>},
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Layouts</em>' map isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Layouts</em>' map.
     * @see ca.mcgill.sel.core.CorePackage#getCOREImpactModel_Layouts()
     * @model mapType="ca.mcgill.sel.core.LayoutContainerMap<org.eclipse.emf.ecore.EObject, ca.mcgill.sel.core.LayoutMap>"
     * @generated
     */
    EMap<EObject, EMap<EObject, LayoutElement>> getLayouts();

    /**
     * Returns the value of the '<em><b>Contributions</b></em>' containment reference list.
     * The list contents are of type {@link ca.mcgill.sel.core.COREContribution}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Contributions</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Contributions</em>' containment reference list.
     * @see ca.mcgill.sel.core.CorePackage#getCOREImpactModel_Contributions()
     * @model containment="true"
     * @generated
     */
    EList<COREContribution> getContributions();

} // COREImpactModel
