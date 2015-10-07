/**
 */
package ca.mcgill.sel.core.impl;

import ca.mcgill.sel.core.COREConcernConfiguration;
import ca.mcgill.sel.core.COREFeature;
import ca.mcgill.sel.core.COREFeatureModel;
import ca.mcgill.sel.core.CorePackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CORE Feature Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link ca.mcgill.sel.core.impl.COREFeatureModelImpl#getFeatures <em>Features</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.impl.COREFeatureModelImpl#getRoot <em>Root</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.impl.COREFeatureModelImpl#getConfigurations <em>Configurations</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.impl.COREFeatureModelImpl#getDefaultConfiguration <em>Default Configuration</em>}</li>
 * </ul>
 *
 * @generated
 */
public class COREFeatureModelImpl extends COREModelImpl implements COREFeatureModel {
    /**
     * The cached value of the '{@link #getFeatures() <em>Features</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFeatures()
     * @generated
     * @ordered
     */
    protected EList<COREFeature> features;

    /**
     * The cached value of the '{@link #getRoot() <em>Root</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRoot()
     * @generated
     * @ordered
     */
    protected COREFeature root;

    /**
     * The cached value of the '{@link #getConfigurations() <em>Configurations</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getConfigurations()
     * @generated
     * @ordered
     */
    protected EList<COREConcernConfiguration> configurations;

    /**
     * The cached value of the '{@link #getDefaultConfiguration() <em>Default Configuration</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDefaultConfiguration()
     * @generated
     * @ordered
     */
    protected COREConcernConfiguration defaultConfiguration;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected COREFeatureModelImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return CorePackage.Literals.CORE_FEATURE_MODEL;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<COREFeature> getFeatures() {
        if (features == null) {
            features = new EObjectContainmentEList<COREFeature>(COREFeature.class, this, CorePackage.CORE_FEATURE_MODEL__FEATURES);
        }
        return features;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public COREFeature getRoot() {
        if (root != null && root.eIsProxy()) {
            InternalEObject oldRoot = (InternalEObject)root;
            root = (COREFeature)eResolveProxy(oldRoot);
            if (root != oldRoot) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, CorePackage.CORE_FEATURE_MODEL__ROOT, oldRoot, root));
            }
        }
        return root;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public COREFeature basicGetRoot() {
        return root;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRoot(COREFeature newRoot) {
        COREFeature oldRoot = root;
        root = newRoot;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.CORE_FEATURE_MODEL__ROOT, oldRoot, root));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<COREConcernConfiguration> getConfigurations() {
        if (configurations == null) {
            configurations = new EObjectContainmentEList<COREConcernConfiguration>(COREConcernConfiguration.class, this, CorePackage.CORE_FEATURE_MODEL__CONFIGURATIONS);
        }
        return configurations;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public COREConcernConfiguration getDefaultConfiguration() {
        if (defaultConfiguration != null && defaultConfiguration.eIsProxy()) {
            InternalEObject oldDefaultConfiguration = (InternalEObject)defaultConfiguration;
            defaultConfiguration = (COREConcernConfiguration)eResolveProxy(oldDefaultConfiguration);
            if (defaultConfiguration != oldDefaultConfiguration) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, CorePackage.CORE_FEATURE_MODEL__DEFAULT_CONFIGURATION, oldDefaultConfiguration, defaultConfiguration));
            }
        }
        return defaultConfiguration;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public COREConcernConfiguration basicGetDefaultConfiguration() {
        return defaultConfiguration;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDefaultConfiguration(COREConcernConfiguration newDefaultConfiguration) {
        COREConcernConfiguration oldDefaultConfiguration = defaultConfiguration;
        defaultConfiguration = newDefaultConfiguration;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.CORE_FEATURE_MODEL__DEFAULT_CONFIGURATION, oldDefaultConfiguration, defaultConfiguration));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case CorePackage.CORE_FEATURE_MODEL__FEATURES:
                return ((InternalEList<?>)getFeatures()).basicRemove(otherEnd, msgs);
            case CorePackage.CORE_FEATURE_MODEL__CONFIGURATIONS:
                return ((InternalEList<?>)getConfigurations()).basicRemove(otherEnd, msgs);
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
            case CorePackage.CORE_FEATURE_MODEL__FEATURES:
                return getFeatures();
            case CorePackage.CORE_FEATURE_MODEL__ROOT:
                if (resolve) return getRoot();
                return basicGetRoot();
            case CorePackage.CORE_FEATURE_MODEL__CONFIGURATIONS:
                return getConfigurations();
            case CorePackage.CORE_FEATURE_MODEL__DEFAULT_CONFIGURATION:
                if (resolve) return getDefaultConfiguration();
                return basicGetDefaultConfiguration();
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
            case CorePackage.CORE_FEATURE_MODEL__FEATURES:
                getFeatures().clear();
                getFeatures().addAll((Collection<? extends COREFeature>)newValue);
                return;
            case CorePackage.CORE_FEATURE_MODEL__ROOT:
                setRoot((COREFeature)newValue);
                return;
            case CorePackage.CORE_FEATURE_MODEL__CONFIGURATIONS:
                getConfigurations().clear();
                getConfigurations().addAll((Collection<? extends COREConcernConfiguration>)newValue);
                return;
            case CorePackage.CORE_FEATURE_MODEL__DEFAULT_CONFIGURATION:
                setDefaultConfiguration((COREConcernConfiguration)newValue);
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
            case CorePackage.CORE_FEATURE_MODEL__FEATURES:
                getFeatures().clear();
                return;
            case CorePackage.CORE_FEATURE_MODEL__ROOT:
                setRoot((COREFeature)null);
                return;
            case CorePackage.CORE_FEATURE_MODEL__CONFIGURATIONS:
                getConfigurations().clear();
                return;
            case CorePackage.CORE_FEATURE_MODEL__DEFAULT_CONFIGURATION:
                setDefaultConfiguration((COREConcernConfiguration)null);
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
            case CorePackage.CORE_FEATURE_MODEL__FEATURES:
                return features != null && !features.isEmpty();
            case CorePackage.CORE_FEATURE_MODEL__ROOT:
                return root != null;
            case CorePackage.CORE_FEATURE_MODEL__CONFIGURATIONS:
                return configurations != null && !configurations.isEmpty();
            case CorePackage.CORE_FEATURE_MODEL__DEFAULT_CONFIGURATION:
                return defaultConfiguration != null;
        }
        return super.eIsSet(featureID);
    }

} //COREFeatureModelImpl
