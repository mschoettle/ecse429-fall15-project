/**
 */
package ca.mcgill.sel.ram.impl;

import ca.mcgill.sel.core.impl.COREBindingImpl;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.ClassifierMapping;
import ca.mcgill.sel.ram.Instantiation;
import ca.mcgill.sel.ram.InstantiationType;
import ca.mcgill.sel.ram.RamPackage;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Instantiation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ca.mcgill.sel.ram.impl.InstantiationImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InstantiationImpl extends COREBindingImpl<Aspect, ClassifierMapping> implements Instantiation {
    /**
     * The cached setting delegate for the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    protected EStructuralFeature.Internal.SettingDelegate TYPE__ESETTING_DELEGATE = ((EStructuralFeature.Internal)RamPackage.Literals.INSTANTIATION__TYPE).getSettingDelegate();

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected InstantiationImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return RamPackage.Literals.INSTANTIATION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * This is specialized for the more specific element type known in this context.
     * @generated
     */
    @Override
    public EList<ClassifierMapping> getMappings() {
        if (mappings == null) {
            mappings = new EObjectContainmentEList<ClassifierMapping>(ClassifierMapping.class, this, RamPackage.INSTANTIATION__MAPPINGS);
        }
        return mappings;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public InstantiationType getType() {
        return (InstantiationType)TYPE__ESETTING_DELEGATE.dynamicGet(this, null, 0, true, false);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case RamPackage.INSTANTIATION__TYPE:
                return getType();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case RamPackage.INSTANTIATION__TYPE:
                return TYPE__ESETTING_DELEGATE.dynamicIsSet(this, null, 0);
        }
        return super.eIsSet(featureID);
    }

} //InstantiationImpl
