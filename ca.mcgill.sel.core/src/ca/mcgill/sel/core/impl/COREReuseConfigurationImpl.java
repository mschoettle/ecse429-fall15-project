/**
 */
package ca.mcgill.sel.core.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import ca.mcgill.sel.core.COREConcernConfiguration;
import ca.mcgill.sel.core.COREReuse;
import ca.mcgill.sel.core.COREReuseConfiguration;
import ca.mcgill.sel.core.CorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CORE Configuration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link ca.mcgill.sel.core.impl.COREReuseConfigurationImpl#getReusedConfiguration <em>Reused Configuration</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.impl.COREReuseConfigurationImpl#getReuse <em>Reuse</em>}</li>
 * </ul>
 *
 * @generated
 */
public class COREReuseConfigurationImpl extends COREConfigurationImpl implements COREReuseConfiguration {
    /**
     * The cached value of the '{@link #getReusedConfiguration() <em>Reused Configuration</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getReusedConfiguration()
     * @generated
     * @ordered
     */
    protected COREConcernConfiguration reusedConfiguration;

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
    protected COREReuseConfigurationImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return CorePackage.Literals.CORE_REUSE_CONFIGURATION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public COREConcernConfiguration getReusedConfiguration() {
        if (reusedConfiguration != null && reusedConfiguration.eIsProxy()) {
            InternalEObject oldReusedConfiguration = (InternalEObject)reusedConfiguration;
            reusedConfiguration = (COREConcernConfiguration)eResolveProxy(oldReusedConfiguration);
            if (reusedConfiguration != oldReusedConfiguration) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, CorePackage.CORE_REUSE_CONFIGURATION__REUSED_CONFIGURATION, oldReusedConfiguration, reusedConfiguration));
            }
        }
        return reusedConfiguration;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public COREConcernConfiguration basicGetReusedConfiguration() {
        return reusedConfiguration;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setReusedConfiguration(COREConcernConfiguration newReusedConfiguration) {
        COREConcernConfiguration oldReusedConfiguration = reusedConfiguration;
        reusedConfiguration = newReusedConfiguration;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.CORE_REUSE_CONFIGURATION__REUSED_CONFIGURATION, oldReusedConfiguration, reusedConfiguration));
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
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, CorePackage.CORE_REUSE_CONFIGURATION__REUSE, oldReuse, reuse));
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
            eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.CORE_REUSE_CONFIGURATION__REUSE, oldReuse, reuse));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case CorePackage.CORE_REUSE_CONFIGURATION__REUSED_CONFIGURATION:
                if (resolve) return getReusedConfiguration();
                return basicGetReusedConfiguration();
            case CorePackage.CORE_REUSE_CONFIGURATION__REUSE:
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
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case CorePackage.CORE_REUSE_CONFIGURATION__REUSED_CONFIGURATION:
                setReusedConfiguration((COREConcernConfiguration)newValue);
                return;
            case CorePackage.CORE_REUSE_CONFIGURATION__REUSE:
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
            case CorePackage.CORE_REUSE_CONFIGURATION__REUSED_CONFIGURATION:
                setReusedConfiguration((COREConcernConfiguration)null);
                return;
            case CorePackage.CORE_REUSE_CONFIGURATION__REUSE:
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
            case CorePackage.CORE_REUSE_CONFIGURATION__REUSED_CONFIGURATION:
                return reusedConfiguration != null;
            case CorePackage.CORE_REUSE_CONFIGURATION__REUSE:
                return reuse != null;
        }
        return super.eIsSet(featureID);
    }

} //COREConfigurationImpl
