/**
 */
package ca.mcgill.sel.core.impl;

import ca.mcgill.sel.core.COREConcern;
import ca.mcgill.sel.core.COREFeature;
import ca.mcgill.sel.core.COREModel;
import ca.mcgill.sel.core.COREModelElement;
import ca.mcgill.sel.core.COREModelReuse;
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
 * An implementation of the model object '<em><b>CORE Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link ca.mcgill.sel.core.impl.COREModelImpl#getModelReuses <em>Model Reuses</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.impl.COREModelImpl#getModelElements <em>Model Elements</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.impl.COREModelImpl#getRealizes <em>Realizes</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.impl.COREModelImpl#getCoreConcern <em>Core Concern</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class COREModelImpl extends CORENamedElementImpl implements COREModel {
    /**
     * The cached value of the '{@link #getModelReuses() <em>Model Reuses</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getModelReuses()
     * @generated
     * @ordered
     */
    protected EList<COREModelReuse> modelReuses;

    /**
     * The cached value of the '{@link #getModelElements() <em>Model Elements</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getModelElements()
     * @generated
     * @ordered
     */
    protected EList<COREModelElement> modelElements;

    /**
     * The cached value of the '{@link #getRealizes() <em>Realizes</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRealizes()
     * @generated
     * @ordered
     */
    protected EList<COREFeature> realizes;

    /**
     * The cached value of the '{@link #getCoreConcern() <em>Core Concern</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCoreConcern()
     * @generated
     * @ordered
     */
    protected COREConcern coreConcern;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected COREModelImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return CorePackage.Literals.CORE_MODEL;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<COREModelReuse> getModelReuses() {
        if (modelReuses == null) {
            modelReuses = new EObjectContainmentEList<COREModelReuse>(COREModelReuse.class, this, CorePackage.CORE_MODEL__MODEL_REUSES);
        }
        return modelReuses;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<COREModelElement> getModelElements() {
        if (modelElements == null) {
            modelElements = new EObjectResolvingEList<COREModelElement>(COREModelElement.class, this, CorePackage.CORE_MODEL__MODEL_ELEMENTS);
        }
        return modelElements;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<COREFeature> getRealizes() {
        if (realizes == null) {
            realizes = new EObjectWithInverseResolvingEList.ManyInverse<COREFeature>(COREFeature.class, this, CorePackage.CORE_MODEL__REALIZES, CorePackage.CORE_FEATURE__REALIZED_BY);
        }
        return realizes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public COREConcern getCoreConcern() {
        if (coreConcern != null && coreConcern.eIsProxy()) {
            InternalEObject oldCoreConcern = (InternalEObject)coreConcern;
            coreConcern = (COREConcern)eResolveProxy(oldCoreConcern);
            if (coreConcern != oldCoreConcern) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, CorePackage.CORE_MODEL__CORE_CONCERN, oldCoreConcern, coreConcern));
            }
        }
        return coreConcern;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public COREConcern basicGetCoreConcern() {
        return coreConcern;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetCoreConcern(COREConcern newCoreConcern, NotificationChain msgs) {
        COREConcern oldCoreConcern = coreConcern;
        coreConcern = newCoreConcern;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CorePackage.CORE_MODEL__CORE_CONCERN, oldCoreConcern, newCoreConcern);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCoreConcern(COREConcern newCoreConcern) {
        if (newCoreConcern != coreConcern) {
            NotificationChain msgs = null;
            if (coreConcern != null)
                msgs = ((InternalEObject)coreConcern).eInverseRemove(this, CorePackage.CORE_CONCERN__MODELS, COREConcern.class, msgs);
            if (newCoreConcern != null)
                msgs = ((InternalEObject)newCoreConcern).eInverseAdd(this, CorePackage.CORE_CONCERN__MODELS, COREConcern.class, msgs);
            msgs = basicSetCoreConcern(newCoreConcern, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.CORE_MODEL__CORE_CONCERN, newCoreConcern, newCoreConcern));
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
            case CorePackage.CORE_MODEL__REALIZES:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getRealizes()).basicAdd(otherEnd, msgs);
            case CorePackage.CORE_MODEL__CORE_CONCERN:
                if (coreConcern != null)
                    msgs = ((InternalEObject)coreConcern).eInverseRemove(this, CorePackage.CORE_CONCERN__MODELS, COREConcern.class, msgs);
                return basicSetCoreConcern((COREConcern)otherEnd, msgs);
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
            case CorePackage.CORE_MODEL__MODEL_REUSES:
                return ((InternalEList<?>)getModelReuses()).basicRemove(otherEnd, msgs);
            case CorePackage.CORE_MODEL__REALIZES:
                return ((InternalEList<?>)getRealizes()).basicRemove(otherEnd, msgs);
            case CorePackage.CORE_MODEL__CORE_CONCERN:
                return basicSetCoreConcern(null, msgs);
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
            case CorePackage.CORE_MODEL__MODEL_REUSES:
                return getModelReuses();
            case CorePackage.CORE_MODEL__MODEL_ELEMENTS:
                return getModelElements();
            case CorePackage.CORE_MODEL__REALIZES:
                return getRealizes();
            case CorePackage.CORE_MODEL__CORE_CONCERN:
                if (resolve) return getCoreConcern();
                return basicGetCoreConcern();
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
            case CorePackage.CORE_MODEL__MODEL_REUSES:
                getModelReuses().clear();
                getModelReuses().addAll((Collection<? extends COREModelReuse>)newValue);
                return;
            case CorePackage.CORE_MODEL__REALIZES:
                getRealizes().clear();
                getRealizes().addAll((Collection<? extends COREFeature>)newValue);
                return;
            case CorePackage.CORE_MODEL__CORE_CONCERN:
                setCoreConcern((COREConcern)newValue);
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
            case CorePackage.CORE_MODEL__MODEL_REUSES:
                getModelReuses().clear();
                return;
            case CorePackage.CORE_MODEL__REALIZES:
                getRealizes().clear();
                return;
            case CorePackage.CORE_MODEL__CORE_CONCERN:
                setCoreConcern((COREConcern)null);
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
            case CorePackage.CORE_MODEL__MODEL_REUSES:
                return modelReuses != null && !modelReuses.isEmpty();
            case CorePackage.CORE_MODEL__MODEL_ELEMENTS:
                return modelElements != null && !modelElements.isEmpty();
            case CorePackage.CORE_MODEL__REALIZES:
                return realizes != null && !realizes.isEmpty();
            case CorePackage.CORE_MODEL__CORE_CONCERN:
                return coreConcern != null;
        }
        return super.eIsSet(featureID);
    }

} //COREModelImpl
