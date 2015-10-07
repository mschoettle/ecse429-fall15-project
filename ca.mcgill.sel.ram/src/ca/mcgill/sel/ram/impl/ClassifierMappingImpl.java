/**
 */
package ca.mcgill.sel.ram.impl;

import ca.mcgill.sel.core.impl.COREMappingImpl;
import ca.mcgill.sel.ram.AttributeMapping;
import ca.mcgill.sel.ram.Classifier;
import ca.mcgill.sel.ram.ClassifierMapping;
import ca.mcgill.sel.ram.OperationMapping;
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
 * An implementation of the model object '<em><b>Classifier Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ca.mcgill.sel.ram.impl.ClassifierMappingImpl#getOperationMappings <em>Operation Mappings</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.impl.ClassifierMappingImpl#getAttributeMappings <em>Attribute Mappings</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ClassifierMappingImpl extends COREMappingImpl<Classifier> implements ClassifierMapping {
    /**
     * The cached value of the '{@link #getOperationMappings() <em>Operation Mappings</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOperationMappings()
     * @generated
     * @ordered
     */
    protected EList<OperationMapping> operationMappings;

    /**
     * The cached value of the '{@link #getAttributeMappings() <em>Attribute Mappings</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAttributeMappings()
     * @generated
     * @ordered
     */
    protected EList<AttributeMapping> attributeMappings;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ClassifierMappingImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return RamPackage.Literals.CLASSIFIER_MAPPING;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * This is specialized for the more specific type known in this context.
     * @generated
     */
    @Override
    public void setTo(Classifier newTo) {
        super.setTo(newTo);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * This is specialized for the more specific type known in this context.
     * @generated
     */
    @Override
    public void setFrom(Classifier newFrom) {
        super.setFrom(newFrom);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<OperationMapping> getOperationMappings() {
        if (operationMappings == null) {
            operationMappings = new EObjectContainmentEList<OperationMapping>(OperationMapping.class, this, RamPackage.CLASSIFIER_MAPPING__OPERATION_MAPPINGS);
        }
        return operationMappings;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<AttributeMapping> getAttributeMappings() {
        if (attributeMappings == null) {
            attributeMappings = new EObjectContainmentEList<AttributeMapping>(AttributeMapping.class, this, RamPackage.CLASSIFIER_MAPPING__ATTRIBUTE_MAPPINGS);
        }
        return attributeMappings;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case RamPackage.CLASSIFIER_MAPPING__OPERATION_MAPPINGS:
                return ((InternalEList<?>)getOperationMappings()).basicRemove(otherEnd, msgs);
            case RamPackage.CLASSIFIER_MAPPING__ATTRIBUTE_MAPPINGS:
                return ((InternalEList<?>)getAttributeMappings()).basicRemove(otherEnd, msgs);
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
            case RamPackage.CLASSIFIER_MAPPING__OPERATION_MAPPINGS:
                return getOperationMappings();
            case RamPackage.CLASSIFIER_MAPPING__ATTRIBUTE_MAPPINGS:
                return getAttributeMappings();
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
            case RamPackage.CLASSIFIER_MAPPING__OPERATION_MAPPINGS:
                getOperationMappings().clear();
                getOperationMappings().addAll((Collection<? extends OperationMapping>)newValue);
                return;
            case RamPackage.CLASSIFIER_MAPPING__ATTRIBUTE_MAPPINGS:
                getAttributeMappings().clear();
                getAttributeMappings().addAll((Collection<? extends AttributeMapping>)newValue);
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
            case RamPackage.CLASSIFIER_MAPPING__OPERATION_MAPPINGS:
                getOperationMappings().clear();
                return;
            case RamPackage.CLASSIFIER_MAPPING__ATTRIBUTE_MAPPINGS:
                getAttributeMappings().clear();
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
            case RamPackage.CLASSIFIER_MAPPING__OPERATION_MAPPINGS:
                return operationMappings != null && !operationMappings.isEmpty();
            case RamPackage.CLASSIFIER_MAPPING__ATTRIBUTE_MAPPINGS:
                return attributeMappings != null && !attributeMappings.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //ClassifierMappingImpl
