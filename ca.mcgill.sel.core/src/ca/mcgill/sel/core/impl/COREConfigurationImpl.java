/**
 */
package ca.mcgill.sel.core.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import ca.mcgill.sel.core.CORECompositionSpecification;
import ca.mcgill.sel.core.COREConfiguration;
import ca.mcgill.sel.core.COREFeature;
import ca.mcgill.sel.core.COREFeatureModel;
import ca.mcgill.sel.core.CorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CORE Configuration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link ca.mcgill.sel.core.impl.COREConfigurationImpl#getSource <em>Source</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.impl.COREConfigurationImpl#getSelected <em>Selected</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class COREConfigurationImpl extends CORENamedElementImpl implements COREConfiguration {
    /**
     * The cached value of the '{@link #getSource() <em>Source</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSource()
     * @generated
     * @ordered
     */
    protected COREFeatureModel source;

    /**
     * The cached value of the '{@link #getSelected() <em>Selected</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSelected()
     * @generated
     * @ordered
     */
    protected EList<COREFeature> selected;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected COREConfigurationImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return CorePackage.Literals.CORE_CONFIGURATION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public COREFeatureModel getSource() {
        if (source != null && source.eIsProxy()) {
            InternalEObject oldSource = (InternalEObject)source;
            source = (COREFeatureModel)eResolveProxy(oldSource);
            if (source != oldSource) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, CorePackage.CORE_CONFIGURATION__SOURCE, oldSource, source));
            }
        }
        return source;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public COREFeatureModel basicGetSource() {
        return source;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSource(COREFeatureModel newSource) {
        COREFeatureModel oldSource = source;
        source = newSource;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.CORE_CONFIGURATION__SOURCE, oldSource, source));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<COREFeature> getSelected() {
        if (selected == null) {
            selected = new EObjectResolvingEList<COREFeature>(COREFeature.class, this, CorePackage.CORE_CONFIGURATION__SELECTED);
        }
        return selected;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case CorePackage.CORE_CONFIGURATION__SOURCE:
                if (resolve) return getSource();
                return basicGetSource();
            case CorePackage.CORE_CONFIGURATION__SELECTED:
                return getSelected();
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
            case CorePackage.CORE_CONFIGURATION__SOURCE:
                setSource((COREFeatureModel)newValue);
                return;
            case CorePackage.CORE_CONFIGURATION__SELECTED:
                getSelected().clear();
                getSelected().addAll((Collection<? extends COREFeature>)newValue);
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
            case CorePackage.CORE_CONFIGURATION__SOURCE:
                setSource((COREFeatureModel)null);
                return;
            case CorePackage.CORE_CONFIGURATION__SELECTED:
                getSelected().clear();
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
            case CorePackage.CORE_CONFIGURATION__SOURCE:
                return source != null;
            case CorePackage.CORE_CONFIGURATION__SELECTED:
                return selected != null && !selected.isEmpty();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
        if (baseClass == CORECompositionSpecification.class) {
            switch (derivedFeatureID) {
                case CorePackage.CORE_CONFIGURATION__SOURCE: return CorePackage.CORE_COMPOSITION_SPECIFICATION__SOURCE;
                default: return -1;
            }
        }
        return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
        if (baseClass == CORECompositionSpecification.class) {
            switch (baseFeatureID) {
                case CorePackage.CORE_COMPOSITION_SPECIFICATION__SOURCE: return CorePackage.CORE_CONFIGURATION__SOURCE;
                default: return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
    }

} //COREConfigurationImpl
