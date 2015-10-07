/**
 */
package ca.mcgill.sel.core;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CORE Binding</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link ca.mcgill.sel.core.COREBinding#getMappings <em>Mappings</em>}</li>
 * </ul>
 *
 * @see ca.mcgill.sel.core.CorePackage#getCOREBinding()
 * @model abstract="true"
 * @generated
 */
public interface COREBinding<S extends COREModel, M extends COREMapping<?>> extends COREModelCompositionSpecification<S> {
    /**
     * Returns the value of the '<em><b>Mappings</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Mappings</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Mappings</em>' containment reference list.
     * @see ca.mcgill.sel.core.CorePackage#getCOREBinding_Mappings()
     * @model containment="true"
     * @generated
     */
    EList<M> getMappings();

} // COREBinding
