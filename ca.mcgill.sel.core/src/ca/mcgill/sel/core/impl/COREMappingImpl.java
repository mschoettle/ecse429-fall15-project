/**
 */
package ca.mcgill.sel.core.impl;

import ca.mcgill.sel.core.COREMapping;
import ca.mcgill.sel.core.COREModelElement;
import ca.mcgill.sel.core.CorePackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CORE Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link ca.mcgill.sel.core.impl.COREMappingImpl#getTo <em>To</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.impl.COREMappingImpl#getFrom <em>From</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class COREMappingImpl<T extends COREModelElement> extends EObjectImpl implements COREMapping<T> {
    /**
     * The cached value of the '{@link #getTo() <em>To</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTo()
     * @generated
     * @ordered
     */
    protected T to;

    /**
     * The cached value of the '{@link #getFrom() <em>From</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFrom()
     * @generated
     * @ordered
     */
    protected T from;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected COREMappingImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return CorePackage.Literals.CORE_MAPPING;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    public T getTo() {
        if (to != null && to.eIsProxy()) {
            InternalEObject oldTo = (InternalEObject)to;
            to = (T)eResolveProxy(oldTo);
            if (to != oldTo) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, CorePackage.CORE_MAPPING__TO, oldTo, to));
            }
        }
        return to;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public T basicGetTo() {
        return to;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTo(T newTo) {
        T oldTo = to;
        to = newTo;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.CORE_MAPPING__TO, oldTo, to));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    public T getFrom() {
        if (from != null && from.eIsProxy()) {
            InternalEObject oldFrom = (InternalEObject)from;
            from = (T)eResolveProxy(oldFrom);
            if (from != oldFrom) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, CorePackage.CORE_MAPPING__FROM, oldFrom, from));
            }
        }
        return from;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public T basicGetFrom() {
        return from;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFrom(T newFrom) {
        T oldFrom = from;
        from = newFrom;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.CORE_MAPPING__FROM, oldFrom, from));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case CorePackage.CORE_MAPPING__TO:
                if (resolve) return getTo();
                return basicGetTo();
            case CorePackage.CORE_MAPPING__FROM:
                if (resolve) return getFrom();
                return basicGetFrom();
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
            case CorePackage.CORE_MAPPING__TO:
                setTo((T)newValue);
                return;
            case CorePackage.CORE_MAPPING__FROM:
                setFrom((T)newValue);
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
            case CorePackage.CORE_MAPPING__TO:
                setTo((T)null);
                return;
            case CorePackage.CORE_MAPPING__FROM:
                setFrom((T)null);
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
            case CorePackage.CORE_MAPPING__TO:
                return to != null;
            case CorePackage.CORE_MAPPING__FROM:
                return from != null;
        }
        return super.eIsSet(featureID);
    }

} //COREMappingImpl
