/**
 */
package ca.mcgill.sel.ram.impl;

import ca.mcgill.sel.core.impl.COREMappingImpl;
import ca.mcgill.sel.ram.Operation;
import ca.mcgill.sel.ram.OperationMapping;
import ca.mcgill.sel.ram.ParameterMapping;
import ca.mcgill.sel.ram.RamPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Operation Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ca.mcgill.sel.ram.impl.OperationMappingImpl#getParameterMappings <em>Parameter Mappings</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OperationMappingImpl extends COREMappingImpl<Operation> implements OperationMapping {
    /**
     * The cached value of the '{@link #getParameterMappings() <em>Parameter Mappings</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getParameterMappings()
     * @generated
     * @ordered
     */
    protected EList<ParameterMapping> parameterMappings;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected OperationMappingImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return RamPackage.Literals.OPERATION_MAPPING;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * This is specialized for the more specific type known in this context.
     * @generated
     */
    @Override
    public void setTo(Operation newTo) {
        super.setTo(newTo);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * This is specialized for the more specific type known in this context.
     * @generated
     */
    @Override
    public void setFrom(Operation newFrom) {
        super.setFrom(newFrom);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<ParameterMapping> getParameterMappings() {
        if (parameterMappings == null) {
            parameterMappings = new EObjectContainmentEList<ParameterMapping>(ParameterMapping.class, this, RamPackage.OPERATION_MAPPING__PARAMETER_MAPPINGS);
        }
        return parameterMappings;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case RamPackage.OPERATION_MAPPING__PARAMETER_MAPPINGS:
                return ((InternalEList<?>)getParameterMappings()).basicRemove(otherEnd, msgs);
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
            case RamPackage.OPERATION_MAPPING__PARAMETER_MAPPINGS:
                return getParameterMappings();
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
            case RamPackage.OPERATION_MAPPING__PARAMETER_MAPPINGS:
                getParameterMappings().clear();
                getParameterMappings().addAll((Collection<? extends ParameterMapping>)newValue);
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
            case RamPackage.OPERATION_MAPPING__PARAMETER_MAPPINGS:
                getParameterMappings().clear();
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
            case RamPackage.OPERATION_MAPPING__PARAMETER_MAPPINGS:
                return parameterMappings != null && !parameterMappings.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //OperationMappingImpl
