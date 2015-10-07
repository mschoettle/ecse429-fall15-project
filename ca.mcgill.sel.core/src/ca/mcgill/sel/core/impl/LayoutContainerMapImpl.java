/**
 */
package ca.mcgill.sel.core.impl;

import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.core.LayoutElement;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Layout Container Map</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link ca.mcgill.sel.core.impl.LayoutContainerMapImpl#getTypedKey <em>Key</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.impl.LayoutContainerMapImpl#getTypedValue <em>Value</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LayoutContainerMapImpl extends EObjectImpl implements BasicEMap.Entry<EObject,EMap<EObject, LayoutElement>> {
    /**
     * The cached value of the '{@link #getTypedKey() <em>Key</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTypedKey()
     * @generated
     * @ordered
     */
    protected EObject key;

    /**
     * The cached value of the '{@link #getTypedValue() <em>Value</em>}' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTypedValue()
     * @generated
     * @ordered
     */
    protected EMap<EObject, LayoutElement> value;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected LayoutContainerMapImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return CorePackage.Literals.LAYOUT_CONTAINER_MAP;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EObject getTypedKey() {
        if (key != null && key.eIsProxy()) {
            InternalEObject oldKey = (InternalEObject)key;
            key = eResolveProxy(oldKey);
            if (key != oldKey) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, CorePackage.LAYOUT_CONTAINER_MAP__KEY, oldKey, key));
            }
        }
        return key;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EObject basicGetTypedKey() {
        return key;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTypedKey(EObject newKey) {
        EObject oldKey = key;
        key = newKey;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.LAYOUT_CONTAINER_MAP__KEY, oldKey, key));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EMap<EObject, LayoutElement> getTypedValue() {
        if (value == null) {
            value = new EcoreEMap<EObject,LayoutElement>(CorePackage.Literals.LAYOUT_MAP, LayoutMapImpl.class, this, CorePackage.LAYOUT_CONTAINER_MAP__VALUE);
        }
        return value;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case CorePackage.LAYOUT_CONTAINER_MAP__VALUE:
                return ((InternalEList<?>)getTypedValue()).basicRemove(otherEnd, msgs);
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
            case CorePackage.LAYOUT_CONTAINER_MAP__KEY:
                if (resolve) return getTypedKey();
                return basicGetTypedKey();
            case CorePackage.LAYOUT_CONTAINER_MAP__VALUE:
                if (coreType) return getTypedValue();
                else return getTypedValue().map();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case CorePackage.LAYOUT_CONTAINER_MAP__KEY:
                setTypedKey((EObject)newValue);
                return;
            case CorePackage.LAYOUT_CONTAINER_MAP__VALUE:
                ((EStructuralFeature.Setting)getTypedValue()).set(newValue);
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
            case CorePackage.LAYOUT_CONTAINER_MAP__KEY:
                setTypedKey((EObject)null);
                return;
            case CorePackage.LAYOUT_CONTAINER_MAP__VALUE:
                getTypedValue().clear();
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
            case CorePackage.LAYOUT_CONTAINER_MAP__KEY:
                return key != null;
            case CorePackage.LAYOUT_CONTAINER_MAP__VALUE:
                return value != null && !value.isEmpty();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected int hash = -1;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getHash() {
        if (hash == -1) {
            Object theKey = getKey();
            hash = (theKey == null ? 0 : theKey.hashCode());
        }
        return hash;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setHash(int hash) {
        this.hash = hash;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EObject getKey() {
        return getTypedKey();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setKey(EObject key) {
        setTypedKey(key);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EMap<EObject, LayoutElement> getValue() {
        return getTypedValue();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EMap<EObject, LayoutElement> setValue(EMap<EObject, LayoutElement> value) {
        EMap<EObject, LayoutElement> oldValue = getValue();
        getTypedValue().clear();
        getTypedValue().addAll(value);
        return oldValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    public EMap<EObject, EMap<EObject, LayoutElement>> getEMap() {
        EObject container = eContainer();
        return container == null ? null : (EMap<EObject, EMap<EObject, LayoutElement>>)container.eGet(eContainmentFeature());
    }

} //LayoutContainerMapImpl
