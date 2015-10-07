/**
 */
package ca.mcgill.sel.core.impl;

import ca.mcgill.sel.core.COREFeature;
import ca.mcgill.sel.core.COREFeatureImpactNode;
import ca.mcgill.sel.core.COREWeightedMapping;
import ca.mcgill.sel.core.CorePackage;

import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CORE Feature Impact</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link ca.mcgill.sel.core.impl.COREFeatureImpactNodeImpl#getRelativeFeatureWeight <em>Relative Feature Weight</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.impl.COREFeatureImpactNodeImpl#getRepresents <em>Represents</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.impl.COREFeatureImpactNodeImpl#getWeightedMappings <em>Weighted Mappings</em>}</li>
 * </ul>
 *
 * @generated
 */
public class COREFeatureImpactNodeImpl extends COREImpactNodeImpl implements COREFeatureImpactNode {
    /**
     * The default value of the '{@link #getRelativeFeatureWeight() <em>Relative Feature Weight</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRelativeFeatureWeight()
     * @generated
     * @ordered
     */
    protected static final int RELATIVE_FEATURE_WEIGHT_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getRelativeFeatureWeight() <em>Relative Feature Weight</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRelativeFeatureWeight()
     * @generated
     * @ordered
     */
    protected int relativeFeatureWeight = RELATIVE_FEATURE_WEIGHT_EDEFAULT;

    /**
     * The cached value of the '{@link #getRepresents() <em>Represents</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRepresents()
     * @generated
     * @ordered
     */
    protected COREFeature represents;

    /**
     * The cached value of the '{@link #getWeightedMappings() <em>Weighted Mappings</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getWeightedMappings()
     * @generated
     * @ordered
     */
    protected EList<COREWeightedMapping> weightedMappings;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected COREFeatureImpactNodeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return CorePackage.Literals.CORE_FEATURE_IMPACT_NODE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getRelativeFeatureWeight() {
        return relativeFeatureWeight;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRelativeFeatureWeight(int newRelativeFeatureWeight) {
        int oldRelativeFeatureWeight = relativeFeatureWeight;
        relativeFeatureWeight = newRelativeFeatureWeight;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.CORE_FEATURE_IMPACT_NODE__RELATIVE_FEATURE_WEIGHT, oldRelativeFeatureWeight, relativeFeatureWeight));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public COREFeature getRepresents() {
        if (represents != null && represents.eIsProxy()) {
            InternalEObject oldRepresents = (InternalEObject)represents;
            represents = (COREFeature)eResolveProxy(oldRepresents);
            if (represents != oldRepresents) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, CorePackage.CORE_FEATURE_IMPACT_NODE__REPRESENTS, oldRepresents, represents));
            }
        }
        return represents;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public COREFeature basicGetRepresents() {
        return represents;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRepresents(COREFeature newRepresents) {
        COREFeature oldRepresents = represents;
        represents = newRepresents;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.CORE_FEATURE_IMPACT_NODE__REPRESENTS, oldRepresents, represents));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<COREWeightedMapping> getWeightedMappings() {
        if (weightedMappings == null) {
            weightedMappings = new EObjectResolvingEList<COREWeightedMapping>(COREWeightedMapping.class, this, CorePackage.CORE_FEATURE_IMPACT_NODE__WEIGHTED_MAPPINGS);
        }
        return weightedMappings;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case CorePackage.CORE_FEATURE_IMPACT_NODE__RELATIVE_FEATURE_WEIGHT:
                return getRelativeFeatureWeight();
            case CorePackage.CORE_FEATURE_IMPACT_NODE__REPRESENTS:
                if (resolve) return getRepresents();
                return basicGetRepresents();
            case CorePackage.CORE_FEATURE_IMPACT_NODE__WEIGHTED_MAPPINGS:
                return getWeightedMappings();
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
            case CorePackage.CORE_FEATURE_IMPACT_NODE__RELATIVE_FEATURE_WEIGHT:
                setRelativeFeatureWeight((Integer)newValue);
                return;
            case CorePackage.CORE_FEATURE_IMPACT_NODE__REPRESENTS:
                setRepresents((COREFeature)newValue);
                return;
            case CorePackage.CORE_FEATURE_IMPACT_NODE__WEIGHTED_MAPPINGS:
                getWeightedMappings().clear();
                getWeightedMappings().addAll((Collection<? extends COREWeightedMapping>)newValue);
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
            case CorePackage.CORE_FEATURE_IMPACT_NODE__RELATIVE_FEATURE_WEIGHT:
                setRelativeFeatureWeight(RELATIVE_FEATURE_WEIGHT_EDEFAULT);
                return;
            case CorePackage.CORE_FEATURE_IMPACT_NODE__REPRESENTS:
                setRepresents((COREFeature)null);
                return;
            case CorePackage.CORE_FEATURE_IMPACT_NODE__WEIGHTED_MAPPINGS:
                getWeightedMappings().clear();
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
            case CorePackage.CORE_FEATURE_IMPACT_NODE__RELATIVE_FEATURE_WEIGHT:
                return relativeFeatureWeight != RELATIVE_FEATURE_WEIGHT_EDEFAULT;
            case CorePackage.CORE_FEATURE_IMPACT_NODE__REPRESENTS:
                return represents != null;
            case CorePackage.CORE_FEATURE_IMPACT_NODE__WEIGHTED_MAPPINGS:
                return weightedMappings != null && !weightedMappings.isEmpty();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (relativeFeatureWeight: ");
        result.append(relativeFeatureWeight);
        result.append(')');
        return result.toString();
    }

} //COREFeatureImpactImpl
