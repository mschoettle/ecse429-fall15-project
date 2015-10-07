/**
 */
package ca.mcgill.sel.core.impl;

import ca.mcgill.sel.core.COREBinding;
import ca.mcgill.sel.core.COREMapping;
import ca.mcgill.sel.core.COREModel;
import ca.mcgill.sel.core.CorePackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CORE Binding</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link ca.mcgill.sel.core.impl.COREBindingImpl#getMappings <em>Mappings</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class COREBindingImpl<S extends COREModel, M extends COREMapping<?>> extends COREModelCompositionSpecificationImpl<S> implements COREBinding<S, M> {
    /**
     * The cached value of the '{@link #getMappings() <em>Mappings</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMappings()
     * @generated
     * @ordered
     */
    protected EList<M> mappings;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected COREBindingImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return CorePackage.Literals.CORE_BINDING;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<M> getMappings() {
        if (mappings == null) {
            mappings = new EObjectContainmentEList<M>(COREMapping.class, this, CorePackage.CORE_BINDING__MAPPINGS);
        }
        return mappings;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case CorePackage.CORE_BINDING__MAPPINGS:
                return ((InternalEList<?>)getMappings()).basicRemove(otherEnd, msgs);
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
            case CorePackage.CORE_BINDING__MAPPINGS:
                return getMappings();
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
            case CorePackage.CORE_BINDING__MAPPINGS:
                getMappings().clear();
                getMappings().addAll((Collection<? extends M>)newValue);
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
            case CorePackage.CORE_BINDING__MAPPINGS:
                getMappings().clear();
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
            case CorePackage.CORE_BINDING__MAPPINGS:
                return mappings != null && !mappings.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //COREBindingImpl
