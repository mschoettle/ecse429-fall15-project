/**
 */
package ca.mcgill.sel.core.impl;

import ca.mcgill.sel.core.COREImpactModel;
import ca.mcgill.sel.core.COREImpactModelBinding;
import ca.mcgill.sel.core.COREWeightedMapping;
import ca.mcgill.sel.core.CorePackage;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CORE Impact Model Binding</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class COREImpactModelBindingImpl extends COREBindingImpl<COREImpactModel, COREWeightedMapping> implements COREImpactModelBinding {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected COREImpactModelBindingImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return CorePackage.Literals.CORE_IMPACT_MODEL_BINDING;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * This is specialized for the more specific element type known in this context.
     * @generated
     */
    @Override
    public EList<COREWeightedMapping> getMappings() {
        if (mappings == null) {
            mappings = new EObjectContainmentEList<COREWeightedMapping>(COREWeightedMapping.class, this, CorePackage.CORE_IMPACT_MODEL_BINDING__MAPPINGS);
        }
        return mappings;
    }

} //COREImpactModelBindingImpl
