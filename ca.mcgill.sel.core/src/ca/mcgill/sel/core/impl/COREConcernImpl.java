/**
 */
package ca.mcgill.sel.core.impl;

import ca.mcgill.sel.core.COREConcern;
import ca.mcgill.sel.core.COREFeatureModel;
import ca.mcgill.sel.core.COREImpactModel;
import ca.mcgill.sel.core.COREInterface;
import ca.mcgill.sel.core.COREModel;
import ca.mcgill.sel.core.CorePackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CORE Concern</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link ca.mcgill.sel.core.impl.COREConcernImpl#getModels <em>Models</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.impl.COREConcernImpl#getInterface <em>Interface</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.impl.COREConcernImpl#getFeatureModel <em>Feature Model</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.impl.COREConcernImpl#getImpactModel <em>Impact Model</em>}</li>
 * </ul>
 *
 * @generated
 */
public class COREConcernImpl extends CORENamedElementImpl implements COREConcern {
    /**
     * The cached value of the '{@link #getModels() <em>Models</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getModels()
     * @generated
     * @ordered
     */
    protected EList<COREModel> models;

    /**
     * The cached value of the '{@link #getInterface() <em>Interface</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getInterface()
     * @generated
     * @ordered
     */
    protected COREInterface interface_;

    /**
     * The cached value of the '{@link #getFeatureModel() <em>Feature Model</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFeatureModel()
     * @generated
     * @ordered
     */
    protected COREFeatureModel featureModel;

    /**
     * The cached value of the '{@link #getImpactModel() <em>Impact Model</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getImpactModel()
     * @generated
     * @ordered
     */
    protected COREImpactModel impactModel;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected COREConcernImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return CorePackage.Literals.CORE_CONCERN;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<COREModel> getModels() {
        if (models == null) {
            models = new EObjectWithInverseResolvingEList<COREModel>(COREModel.class, this, CorePackage.CORE_CONCERN__MODELS, CorePackage.CORE_MODEL__CORE_CONCERN);
        }
        return models;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public COREInterface getInterface() {
        return interface_;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetInterface(COREInterface newInterface, NotificationChain msgs) {
        COREInterface oldInterface = interface_;
        interface_ = newInterface;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CorePackage.CORE_CONCERN__INTERFACE, oldInterface, newInterface);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setInterface(COREInterface newInterface) {
        if (newInterface != interface_) {
            NotificationChain msgs = null;
            if (interface_ != null)
                msgs = ((InternalEObject)interface_).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CorePackage.CORE_CONCERN__INTERFACE, null, msgs);
            if (newInterface != null)
                msgs = ((InternalEObject)newInterface).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CorePackage.CORE_CONCERN__INTERFACE, null, msgs);
            msgs = basicSetInterface(newInterface, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.CORE_CONCERN__INTERFACE, newInterface, newInterface));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public COREFeatureModel getFeatureModel() {
        return featureModel;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetFeatureModel(COREFeatureModel newFeatureModel, NotificationChain msgs) {
        COREFeatureModel oldFeatureModel = featureModel;
        featureModel = newFeatureModel;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CorePackage.CORE_CONCERN__FEATURE_MODEL, oldFeatureModel, newFeatureModel);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFeatureModel(COREFeatureModel newFeatureModel) {
        if (newFeatureModel != featureModel) {
            NotificationChain msgs = null;
            if (featureModel != null)
                msgs = ((InternalEObject)featureModel).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CorePackage.CORE_CONCERN__FEATURE_MODEL, null, msgs);
            if (newFeatureModel != null)
                msgs = ((InternalEObject)newFeatureModel).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CorePackage.CORE_CONCERN__FEATURE_MODEL, null, msgs);
            msgs = basicSetFeatureModel(newFeatureModel, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.CORE_CONCERN__FEATURE_MODEL, newFeatureModel, newFeatureModel));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public COREImpactModel getImpactModel() {
        return impactModel;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetImpactModel(COREImpactModel newImpactModel, NotificationChain msgs) {
        COREImpactModel oldImpactModel = impactModel;
        impactModel = newImpactModel;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CorePackage.CORE_CONCERN__IMPACT_MODEL, oldImpactModel, newImpactModel);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setImpactModel(COREImpactModel newImpactModel) {
        if (newImpactModel != impactModel) {
            NotificationChain msgs = null;
            if (impactModel != null)
                msgs = ((InternalEObject)impactModel).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CorePackage.CORE_CONCERN__IMPACT_MODEL, null, msgs);
            if (newImpactModel != null)
                msgs = ((InternalEObject)newImpactModel).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CorePackage.CORE_CONCERN__IMPACT_MODEL, null, msgs);
            msgs = basicSetImpactModel(newImpactModel, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.CORE_CONCERN__IMPACT_MODEL, newImpactModel, newImpactModel));
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
            case CorePackage.CORE_CONCERN__MODELS:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getModels()).basicAdd(otherEnd, msgs);
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
            case CorePackage.CORE_CONCERN__MODELS:
                return ((InternalEList<?>)getModels()).basicRemove(otherEnd, msgs);
            case CorePackage.CORE_CONCERN__INTERFACE:
                return basicSetInterface(null, msgs);
            case CorePackage.CORE_CONCERN__FEATURE_MODEL:
                return basicSetFeatureModel(null, msgs);
            case CorePackage.CORE_CONCERN__IMPACT_MODEL:
                return basicSetImpactModel(null, msgs);
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
            case CorePackage.CORE_CONCERN__MODELS:
                return getModels();
            case CorePackage.CORE_CONCERN__INTERFACE:
                return getInterface();
            case CorePackage.CORE_CONCERN__FEATURE_MODEL:
                return getFeatureModel();
            case CorePackage.CORE_CONCERN__IMPACT_MODEL:
                return getImpactModel();
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
            case CorePackage.CORE_CONCERN__MODELS:
                getModels().clear();
                getModels().addAll((Collection<? extends COREModel>)newValue);
                return;
            case CorePackage.CORE_CONCERN__INTERFACE:
                setInterface((COREInterface)newValue);
                return;
            case CorePackage.CORE_CONCERN__FEATURE_MODEL:
                setFeatureModel((COREFeatureModel)newValue);
                return;
            case CorePackage.CORE_CONCERN__IMPACT_MODEL:
                setImpactModel((COREImpactModel)newValue);
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
            case CorePackage.CORE_CONCERN__MODELS:
                getModels().clear();
                return;
            case CorePackage.CORE_CONCERN__INTERFACE:
                setInterface((COREInterface)null);
                return;
            case CorePackage.CORE_CONCERN__FEATURE_MODEL:
                setFeatureModel((COREFeatureModel)null);
                return;
            case CorePackage.CORE_CONCERN__IMPACT_MODEL:
                setImpactModel((COREImpactModel)null);
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
            case CorePackage.CORE_CONCERN__MODELS:
                return models != null && !models.isEmpty();
            case CorePackage.CORE_CONCERN__INTERFACE:
                return interface_ != null;
            case CorePackage.CORE_CONCERN__FEATURE_MODEL:
                return featureModel != null;
            case CorePackage.CORE_CONCERN__IMPACT_MODEL:
                return impactModel != null;
        }
        return super.eIsSet(featureID);
    }

} //COREConcernImpl
