/**
 */
package ca.mcgill.sel.ram;

import ca.mcgill.sel.core.COREMapping;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operation Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ca.mcgill.sel.ram.OperationMapping#getParameterMappings <em>Parameter Mappings</em>}</li>
 * </ul>
 * </p>
 *
 * @see ca.mcgill.sel.ram.RamPackage#getOperationMapping()
 * @model
 * @generated
 */
public interface OperationMapping extends COREMapping<Operation> {
    /**
     * Returns the value of the '<em><b>Parameter Mappings</b></em>' containment reference list.
     * The list contents are of type {@link ca.mcgill.sel.ram.ParameterMapping}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Parameter Mappings</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Parameter Mappings</em>' containment reference list.
     * @see ca.mcgill.sel.ram.RamPackage#getOperationMapping_ParameterMappings()
     * @model containment="true"
     * @generated
     */
    EList<ParameterMapping> getParameterMappings();

} // OperationMapping
