/**
 */
package ca.mcgill.sel.core.impl;

import ca.mcgill.sel.core.COREModelCompositionSpecification;
import ca.mcgill.sel.core.COREModelReuse;
import ca.mcgill.sel.core.COREReuse;
import ca.mcgill.sel.core.CorePackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CORE Model Reuse</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link ca.mcgill.sel.core.impl.COREModelReuseImpl#getCompositions <em>Compositions</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.impl.COREModelReuseImpl#getReuse <em>Reuse</em>}</li>
 * </ul>
 *
 * @generated
 */
public class COREModelReuseImpl extends EObjectImpl implements COREModelReuse {
    /**
     * The cached value of the '{@link #getCompositions() <em>Compositions</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCompositions()
     * @generated
     * @ordered
     */
    protected EList<COREModelCompositionSpecification<?>> compositions;

    /**
     * The cached value of the '{@link #getReuse() <em>Reuse</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getReuse()
     * @generated
     * @ordered
     */
    protected COREReuse reuse;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected COREModelReuseImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return CorePackage.Literals.CORE_MODEL_REUSE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<COREModelCompositionSpecification<?>> getCompositions() {
        if (compositions == null) {
            compositions = new EObjectContainmentEList<COREModelCompositionSpecification<?>>(COREModelCompositionSpecification.class, this, CorePackage.CORE_MODEL_REUSE__COMPOSITIONS);
        }
        return compositions;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public COREReuse getReuse() {
        if (reuse != null && reuse.eIsProxy()) {
            InternalEObject oldReuse = (InternalEObject)reuse;
            reuse = (COREReuse)eResolveProxy(oldReuse);
            if (reuse != oldReuse) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, CorePackage.CORE_MODEL_REUSE__REUSE, oldReuse, reuse));
            }
        }
        return reuse;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public COREReuse basicGetReuse() {
        return reuse;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setReuse(COREReuse newReuse) {
        COREReuse oldReuse = reuse;
        reuse = newReuse;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.CORE_MODEL_REUSE__REUSE, oldReuse, reuse));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case CorePackage.CORE_MODEL_REUSE__COMPOSITIONS:
                return ((InternalEList<?>)getCompositions()).basicRemove(otherEnd, msgs);
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
            case CorePackage.CORE_MODEL_REUSE__COMPOSITIONS:
                return getCompositions();
            case CorePackage.CORE_MODEL_REUSE__REUSE:
                if (resolve) return getReuse();
                return basicGetReuse();
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
            case CorePackage.CORE_MODEL_REUSE__COMPOSITIONS:
                getCompositions().clear();
                getCompositions().addAll((Collection<? extends COREModelCompositionSpecification<?>>)newValue);
                return;
            case CorePackage.CORE_MODEL_REUSE__REUSE:
                setReuse((COREReuse)newValue);
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
            case CorePackage.CORE_MODEL_REUSE__COMPOSITIONS:
                getCompositions().clear();
                return;
            case CorePackage.CORE_MODEL_REUSE__REUSE:
                setReuse((COREReuse)null);
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
            case CorePackage.CORE_MODEL_REUSE__COMPOSITIONS:
                return compositions != null && !compositions.isEmpty();
            case CorePackage.CORE_MODEL_REUSE__REUSE:
                return reuse != null;
        }
        return super.eIsSet(featureID);
    }

} //COREModelReuseImpl
