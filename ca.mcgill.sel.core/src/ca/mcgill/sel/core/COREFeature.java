/**
 */
package ca.mcgill.sel.core;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CORE Feature</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link ca.mcgill.sel.core.COREFeature#getRealizedBy <em>Realized By</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.COREFeature#getReuses <em>Reuses</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.COREFeature#getChildren <em>Children</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.COREFeature#getParent <em>Parent</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.COREFeature#getParentRelationship <em>Parent Relationship</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.COREFeature#getRequires <em>Requires</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.COREFeature#getExcludes <em>Excludes</em>}</li>
 * </ul>
 *
 * @see ca.mcgill.sel.core.CorePackage#getCOREFeature()
 * @model
 * @generated
 */
public interface COREFeature extends COREModelElement {
    /**
     * Returns the value of the '<em><b>Realized By</b></em>' reference list.
     * The list contents are of type {@link ca.mcgill.sel.core.COREModel}.
     * It is bidirectional and its opposite is '{@link ca.mcgill.sel.core.COREModel#getRealizes <em>Realizes</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Realized By</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Realized By</em>' reference list.
     * @see ca.mcgill.sel.core.CorePackage#getCOREFeature_RealizedBy()
     * @see ca.mcgill.sel.core.COREModel#getRealizes
     * @model opposite="realizes"
     * @generated
     */
    EList<COREModel> getRealizedBy();

    /**
     * Returns the value of the '<em><b>Reuses</b></em>' containment reference list.
     * The list contents are of type {@link ca.mcgill.sel.core.COREReuse}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Reuses</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Reuses</em>' containment reference list.
     * @see ca.mcgill.sel.core.CorePackage#getCOREFeature_Reuses()
     * @model containment="true"
     * @generated
     */
    EList<COREReuse> getReuses();

    /**
     * Returns the value of the '<em><b>Children</b></em>' reference list.
     * The list contents are of type {@link ca.mcgill.sel.core.COREFeature}.
     * It is bidirectional and its opposite is '{@link ca.mcgill.sel.core.COREFeature#getParent <em>Parent</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Children</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Children</em>' reference list.
     * @see ca.mcgill.sel.core.CorePackage#getCOREFeature_Children()
     * @see ca.mcgill.sel.core.COREFeature#getParent
     * @model opposite="parent"
     * @generated
     */
    EList<COREFeature> getChildren();

    /**
     * Returns the value of the '<em><b>Parent</b></em>' reference.
     * It is bidirectional and its opposite is '{@link ca.mcgill.sel.core.COREFeature#getChildren <em>Children</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Parent</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Parent</em>' reference.
     * @see #setParent(COREFeature)
     * @see ca.mcgill.sel.core.CorePackage#getCOREFeature_Parent()
     * @see ca.mcgill.sel.core.COREFeature#getChildren
     * @model opposite="children"
     * @generated
     */
    COREFeature getParent();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.core.COREFeature#getParent <em>Parent</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Parent</em>' reference.
     * @see #getParent()
     * @generated
     */
    void setParent(COREFeature value);

    /**
     * Returns the value of the '<em><b>Parent Relationship</b></em>' attribute.
     * The default value is <code>"None"</code>.
     * The literals are from the enumeration {@link ca.mcgill.sel.core.COREFeatureRelationshipType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Parent Relationship</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Parent Relationship</em>' attribute.
     * @see ca.mcgill.sel.core.COREFeatureRelationshipType
     * @see #setParentRelationship(COREFeatureRelationshipType)
     * @see ca.mcgill.sel.core.CorePackage#getCOREFeature_ParentRelationship()
     * @model default="None" required="true"
     * @generated
     */
    COREFeatureRelationshipType getParentRelationship();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.core.COREFeature#getParentRelationship <em>Parent Relationship</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Parent Relationship</em>' attribute.
     * @see ca.mcgill.sel.core.COREFeatureRelationshipType
     * @see #getParentRelationship()
     * @generated
     */
    void setParentRelationship(COREFeatureRelationshipType value);

    /**
     * Returns the value of the '<em><b>Requires</b></em>' reference list.
     * The list contents are of type {@link ca.mcgill.sel.core.COREFeature}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Requires</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Requires</em>' reference list.
     * @see ca.mcgill.sel.core.CorePackage#getCOREFeature_Requires()
     * @model
     * @generated
     */
    EList<COREFeature> getRequires();

    /**
     * Returns the value of the '<em><b>Excludes</b></em>' reference list.
     * The list contents are of type {@link ca.mcgill.sel.core.COREFeature}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Excludes</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Excludes</em>' reference list.
     * @see ca.mcgill.sel.core.CorePackage#getCOREFeature_Excludes()
     * @model
     * @generated
     */
    EList<COREFeature> getExcludes();

} // COREFeature
