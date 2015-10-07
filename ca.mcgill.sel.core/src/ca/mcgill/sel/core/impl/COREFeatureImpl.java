/**
 */
package ca.mcgill.sel.core.impl;

import ca.mcgill.sel.core.COREFeature;
import ca.mcgill.sel.core.COREFeatureRelationshipType;
import ca.mcgill.sel.core.COREModel;
import ca.mcgill.sel.core.COREReuse;
import ca.mcgill.sel.core.CorePackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CORE Feature</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link ca.mcgill.sel.core.impl.COREFeatureImpl#getRealizedBy <em>Realized By</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.impl.COREFeatureImpl#getReuses <em>Reuses</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.impl.COREFeatureImpl#getChildren <em>Children</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.impl.COREFeatureImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.impl.COREFeatureImpl#getParentRelationship <em>Parent Relationship</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.impl.COREFeatureImpl#getRequires <em>Requires</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.impl.COREFeatureImpl#getExcludes <em>Excludes</em>}</li>
 * </ul>
 *
 * @generated
 */
public class COREFeatureImpl extends COREModelElementImpl implements COREFeature {
    /**
     * The cached value of the '{@link #getRealizedBy() <em>Realized By</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRealizedBy()
     * @generated
     * @ordered
     */
    protected EList<COREModel> realizedBy;

    /**
     * The cached value of the '{@link #getReuses() <em>Reuses</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getReuses()
     * @generated
     * @ordered
     */
    protected EList<COREReuse> reuses;

    /**
     * The cached value of the '{@link #getChildren() <em>Children</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getChildren()
     * @generated
     * @ordered
     */
    protected EList<COREFeature> children;

    /**
     * The cached value of the '{@link #getParent() <em>Parent</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getParent()
     * @generated
     * @ordered
     */
    protected COREFeature parent;

    /**
     * The default value of the '{@link #getParentRelationship() <em>Parent Relationship</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getParentRelationship()
     * @generated
     * @ordered
     */
    protected static final COREFeatureRelationshipType PARENT_RELATIONSHIP_EDEFAULT = COREFeatureRelationshipType.NONE;

    /**
     * The cached value of the '{@link #getParentRelationship() <em>Parent Relationship</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getParentRelationship()
     * @generated
     * @ordered
     */
    protected COREFeatureRelationshipType parentRelationship = PARENT_RELATIONSHIP_EDEFAULT;

    /**
     * The cached value of the '{@link #getRequires() <em>Requires</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRequires()
     * @generated
     * @ordered
     */
    protected EList<COREFeature> requires;

    /**
     * The cached value of the '{@link #getExcludes() <em>Excludes</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getExcludes()
     * @generated
     * @ordered
     */
    protected EList<COREFeature> excludes;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected COREFeatureImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return CorePackage.Literals.CORE_FEATURE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<COREModel> getRealizedBy() {
        if (realizedBy == null) {
            realizedBy = new EObjectWithInverseResolvingEList.ManyInverse<COREModel>(COREModel.class, this, CorePackage.CORE_FEATURE__REALIZED_BY, CorePackage.CORE_MODEL__REALIZES);
        }
        return realizedBy;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<COREReuse> getReuses() {
        if (reuses == null) {
            reuses = new EObjectContainmentEList<COREReuse>(COREReuse.class, this, CorePackage.CORE_FEATURE__REUSES);
        }
        return reuses;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<COREFeature> getChildren() {
        if (children == null) {
            children = new EObjectWithInverseResolvingEList<COREFeature>(COREFeature.class, this, CorePackage.CORE_FEATURE__CHILDREN, CorePackage.CORE_FEATURE__PARENT);
        }
        return children;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public COREFeature getParent() {
        if (parent != null && parent.eIsProxy()) {
            InternalEObject oldParent = (InternalEObject)parent;
            parent = (COREFeature)eResolveProxy(oldParent);
            if (parent != oldParent) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, CorePackage.CORE_FEATURE__PARENT, oldParent, parent));
            }
        }
        return parent;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public COREFeature basicGetParent() {
        return parent;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetParent(COREFeature newParent, NotificationChain msgs) {
        COREFeature oldParent = parent;
        parent = newParent;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CorePackage.CORE_FEATURE__PARENT, oldParent, newParent);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setParent(COREFeature newParent) {
        if (newParent != parent) {
            NotificationChain msgs = null;
            if (parent != null)
                msgs = ((InternalEObject)parent).eInverseRemove(this, CorePackage.CORE_FEATURE__CHILDREN, COREFeature.class, msgs);
            if (newParent != null)
                msgs = ((InternalEObject)newParent).eInverseAdd(this, CorePackage.CORE_FEATURE__CHILDREN, COREFeature.class, msgs);
            msgs = basicSetParent(newParent, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.CORE_FEATURE__PARENT, newParent, newParent));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public COREFeatureRelationshipType getParentRelationship() {
        return parentRelationship;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setParentRelationship(COREFeatureRelationshipType newParentRelationship) {
        COREFeatureRelationshipType oldParentRelationship = parentRelationship;
        parentRelationship = newParentRelationship == null ? PARENT_RELATIONSHIP_EDEFAULT : newParentRelationship;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.CORE_FEATURE__PARENT_RELATIONSHIP, oldParentRelationship, parentRelationship));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<COREFeature> getRequires() {
        if (requires == null) {
            requires = new EObjectResolvingEList<COREFeature>(COREFeature.class, this, CorePackage.CORE_FEATURE__REQUIRES);
        }
        return requires;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<COREFeature> getExcludes() {
        if (excludes == null) {
            excludes = new EObjectResolvingEList<COREFeature>(COREFeature.class, this, CorePackage.CORE_FEATURE__EXCLUDES);
        }
        return excludes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case CorePackage.CORE_FEATURE__REALIZED_BY:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getRealizedBy()).basicAdd(otherEnd, msgs);
            case CorePackage.CORE_FEATURE__CHILDREN:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getChildren()).basicAdd(otherEnd, msgs);
            case CorePackage.CORE_FEATURE__PARENT:
                if (parent != null)
                    msgs = ((InternalEObject)parent).eInverseRemove(this, CorePackage.CORE_FEATURE__CHILDREN, COREFeature.class, msgs);
                return basicSetParent((COREFeature)otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case CorePackage.CORE_FEATURE__REALIZED_BY:
                return ((InternalEList<?>)getRealizedBy()).basicRemove(otherEnd, msgs);
            case CorePackage.CORE_FEATURE__REUSES:
                return ((InternalEList<?>)getReuses()).basicRemove(otherEnd, msgs);
            case CorePackage.CORE_FEATURE__CHILDREN:
                return ((InternalEList<?>)getChildren()).basicRemove(otherEnd, msgs);
            case CorePackage.CORE_FEATURE__PARENT:
                return basicSetParent(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case CorePackage.CORE_FEATURE__REALIZED_BY:
                return getRealizedBy();
            case CorePackage.CORE_FEATURE__REUSES:
                return getReuses();
            case CorePackage.CORE_FEATURE__CHILDREN:
                return getChildren();
            case CorePackage.CORE_FEATURE__PARENT:
                if (resolve) return getParent();
                return basicGetParent();
            case CorePackage.CORE_FEATURE__PARENT_RELATIONSHIP:
                return getParentRelationship();
            case CorePackage.CORE_FEATURE__REQUIRES:
                return getRequires();
            case CorePackage.CORE_FEATURE__EXCLUDES:
                return getExcludes();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case CorePackage.CORE_FEATURE__REALIZED_BY:
                getRealizedBy().clear();
                getRealizedBy().addAll((Collection<? extends COREModel>)newValue);
                return;
            case CorePackage.CORE_FEATURE__REUSES:
                getReuses().clear();
                getReuses().addAll((Collection<? extends COREReuse>)newValue);
                return;
            case CorePackage.CORE_FEATURE__CHILDREN:
                getChildren().clear();
                getChildren().addAll((Collection<? extends COREFeature>)newValue);
                return;
            case CorePackage.CORE_FEATURE__PARENT:
                setParent((COREFeature)newValue);
                return;
            case CorePackage.CORE_FEATURE__PARENT_RELATIONSHIP:
                setParentRelationship((COREFeatureRelationshipType)newValue);
                return;
            case CorePackage.CORE_FEATURE__REQUIRES:
                getRequires().clear();
                getRequires().addAll((Collection<? extends COREFeature>)newValue);
                return;
            case CorePackage.CORE_FEATURE__EXCLUDES:
                getExcludes().clear();
                getExcludes().addAll((Collection<? extends COREFeature>)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case CorePackage.CORE_FEATURE__REALIZED_BY:
                getRealizedBy().clear();
                return;
            case CorePackage.CORE_FEATURE__REUSES:
                getReuses().clear();
                return;
            case CorePackage.CORE_FEATURE__CHILDREN:
                getChildren().clear();
                return;
            case CorePackage.CORE_FEATURE__PARENT:
                setParent((COREFeature)null);
                return;
            case CorePackage.CORE_FEATURE__PARENT_RELATIONSHIP:
                setParentRelationship(PARENT_RELATIONSHIP_EDEFAULT);
                return;
            case CorePackage.CORE_FEATURE__REQUIRES:
                getRequires().clear();
                return;
            case CorePackage.CORE_FEATURE__EXCLUDES:
                getExcludes().clear();
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case CorePackage.CORE_FEATURE__REALIZED_BY:
                return realizedBy != null && !realizedBy.isEmpty();
            case CorePackage.CORE_FEATURE__REUSES:
                return reuses != null && !reuses.isEmpty();
            case CorePackage.CORE_FEATURE__CHILDREN:
                return children != null && !children.isEmpty();
            case CorePackage.CORE_FEATURE__PARENT:
                return parent != null;
            case CorePackage.CORE_FEATURE__PARENT_RELATIONSHIP:
                return parentRelationship != PARENT_RELATIONSHIP_EDEFAULT;
            case CorePackage.CORE_FEATURE__REQUIRES:
                return requires != null && !requires.isEmpty();
            case CorePackage.CORE_FEATURE__EXCLUDES:
                return excludes != null && !excludes.isEmpty();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (parentRelationship: ");
        result.append(parentRelationship);
        result.append(')');
        return result.toString();
    }

} //COREFeatureImpl
