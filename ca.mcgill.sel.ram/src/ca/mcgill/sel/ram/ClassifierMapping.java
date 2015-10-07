/**
 */
package ca.mcgill.sel.ram;

import ca.mcgill.sel.core.COREMapping;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Classifier Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ca.mcgill.sel.ram.ClassifierMapping#getOperationMappings <em>Operation Mappings</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.ClassifierMapping#getAttributeMappings <em>Attribute Mappings</em>}</li>
 * </ul>
 * </p>
 *
 * @see ca.mcgill.sel.ram.RamPackage#getClassifierMapping()
 * @model
 * @generated
 */
public interface ClassifierMapping extends COREMapping<Classifier> {
    /**
     * Returns the value of the '<em><b>Operation Mappings</b></em>' containment reference list.
     * The list contents are of type {@link ca.mcgill.sel.ram.OperationMapping}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Operation Mappings</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Operation Mappings</em>' containment reference list.
     * @see ca.mcgill.sel.ram.RamPackage#getClassifierMapping_OperationMappings()
     * @model containment="true"
     * @generated
     */
    EList<OperationMapping> getOperationMappings();

    /**
     * Returns the value of the '<em><b>Attribute Mappings</b></em>' containment reference list.
     * The list contents are of type {@link ca.mcgill.sel.ram.AttributeMapping}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Attribute Mappings</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Attribute Mappings</em>' containment reference list.
     * @see ca.mcgill.sel.ram.RamPackage#getClassifierMapping_AttributeMappings()
     * @model containment="true"
     * @generated
     */
    EList<AttributeMapping> getAttributeMappings();

} // ClassifierMapping
