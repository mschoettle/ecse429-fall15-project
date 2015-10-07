/**
 */
package ca.mcgill.sel.core.impl;

import ca.mcgill.sel.core.COREContribution;
import ca.mcgill.sel.core.COREImpactModel;
import ca.mcgill.sel.core.COREImpactNode;
import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.core.LayoutElement;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CORE Impact Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link ca.mcgill.sel.core.impl.COREImpactModelImpl#getImpactModelElements <em>Impact Model Elements</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.impl.COREImpactModelImpl#getLayouts <em>Layouts</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.impl.COREImpactModelImpl#getContributions <em>Contributions</em>}</li>
 * </ul>
 *
 * @generated
 */
public class COREImpactModelImpl extends COREModelImpl implements COREImpactModel {
    /**
     * The cached value of the '{@link #getImpactModelElements() <em>Impact Model Elements</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getImpactModelElements()
     * @generated
     * @ordered
     */
    protected EList<COREImpactNode> impactModelElements;

    /**
     * The cached value of the '{@link #getLayouts() <em>Layouts</em>}' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLayouts()
     * @generated
     * @ordered
     */
    protected EMap<EObject, EMap<EObject, LayoutElement>> layouts;

    /**
     * The cached value of the '{@link #getContributions() <em>Contributions</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getContributions()
     * @generated
     * @ordered
     */
    protected EList<COREContribution> contributions;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected COREImpactModelImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return CorePackage.Literals.CORE_IMPACT_MODEL;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<COREImpactNode> getImpactModelElements() {
        if (impactModelElements == null) {
            impactModelElements = new EObjectContainmentEList<COREImpactNode>(COREImpactNode.class, this, CorePackage.CORE_IMPACT_MODEL__IMPACT_MODEL_ELEMENTS);
        }
        return impactModelElements;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EMap<EObject, EMap<EObject, LayoutElement>> getLayouts() {
        if (layouts == null) {
            layouts = new EcoreEMap<EObject,EMap<EObject, LayoutElement>>(CorePackage.Literals.LAYOUT_CONTAINER_MAP, LayoutContainerMapImpl.class, this, CorePackage.CORE_IMPACT_MODEL__LAYOUTS);
        }
        return layouts;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<COREContribution> getContributions() {
        if (contributions == null) {
            contributions = new EObjectContainmentEList<COREContribution>(COREContribution.class, this, CorePackage.CORE_IMPACT_MODEL__CONTRIBUTIONS);
        }
        return contributions;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case CorePackage.CORE_IMPACT_MODEL__IMPACT_MODEL_ELEMENTS:
                return ((InternalEList<?>)getImpactModelElements()).basicRemove(otherEnd, msgs);
            case CorePackage.CORE_IMPACT_MODEL__LAYOUTS:
                return ((InternalEList<?>)getLayouts()).basicRemove(otherEnd, msgs);
            case CorePackage.CORE_IMPACT_MODEL__CONTRIBUTIONS:
                return ((InternalEList<?>)getContributions()).basicRemove(otherEnd, msgs);
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
            case CorePackage.CORE_IMPACT_MODEL__IMPACT_MODEL_ELEMENTS:
                return getImpactModelElements();
            case CorePackage.CORE_IMPACT_MODEL__LAYOUTS:
                if (coreType) return getLayouts();
                else return getLayouts().map();
            case CorePackage.CORE_IMPACT_MODEL__CONTRIBUTIONS:
                return getContributions();
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
            case CorePackage.CORE_IMPACT_MODEL__IMPACT_MODEL_ELEMENTS:
                getImpactModelElements().clear();
                getImpactModelElements().addAll((Collection<? extends COREImpactNode>)newValue);
                return;
            case CorePackage.CORE_IMPACT_MODEL__LAYOUTS:
                ((EStructuralFeature.Setting)getLayouts()).set(newValue);
                return;
            case CorePackage.CORE_IMPACT_MODEL__CONTRIBUTIONS:
                getContributions().clear();
                getContributions().addAll((Collection<? extends COREContribution>)newValue);
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
            case CorePackage.CORE_IMPACT_MODEL__IMPACT_MODEL_ELEMENTS:
                getImpactModelElements().clear();
                return;
            case CorePackage.CORE_IMPACT_MODEL__LAYOUTS:
                getLayouts().clear();
                return;
            case CorePackage.CORE_IMPACT_MODEL__CONTRIBUTIONS:
                getContributions().clear();
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
            case CorePackage.CORE_IMPACT_MODEL__IMPACT_MODEL_ELEMENTS:
                return impactModelElements != null && !impactModelElements.isEmpty();
            case CorePackage.CORE_IMPACT_MODEL__LAYOUTS:
                return layouts != null && !layouts.isEmpty();
            case CorePackage.CORE_IMPACT_MODEL__CONTRIBUTIONS:
                return contributions != null && !contributions.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //COREImpactModelImpl
