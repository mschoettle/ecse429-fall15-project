/**
 */
package ca.mcgill.sel.core.impl;

import ca.mcgill.sel.core.COREContribution;
import ca.mcgill.sel.core.COREImpactNode;
import ca.mcgill.sel.core.CorePackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CORE Impact Model Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link ca.mcgill.sel.core.impl.COREImpactNodeImpl#getScalingFactor <em>Scaling Factor</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.impl.COREImpactNodeImpl#getOffset <em>Offset</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.impl.COREImpactNodeImpl#getOutgoing <em>Outgoing</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.impl.COREImpactNodeImpl#getIncoming <em>Incoming</em>}</li>
 * </ul>
 *
 * @generated
 */
public class COREImpactNodeImpl extends COREModelElementImpl implements COREImpactNode {
    /**
     * The default value of the '{@link #getScalingFactor() <em>Scaling Factor</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getScalingFactor()
     * @generated
     * @ordered
     */
    protected static final float SCALING_FACTOR_EDEFAULT = 0.0F;

    /**
     * The cached value of the '{@link #getScalingFactor() <em>Scaling Factor</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getScalingFactor()
     * @generated
     * @ordered
     */
    protected float scalingFactor = SCALING_FACTOR_EDEFAULT;

    /**
     * The default value of the '{@link #getOffset() <em>Offset</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOffset()
     * @generated
     * @ordered
     */
    protected static final float OFFSET_EDEFAULT = 0.0F;

    /**
     * The cached value of the '{@link #getOffset() <em>Offset</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOffset()
     * @generated
     * @ordered
     */
    protected float offset = OFFSET_EDEFAULT;

    /**
     * The cached value of the '{@link #getOutgoing() <em>Outgoing</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOutgoing()
     * @generated
     * @ordered
     */
    protected EList<COREContribution> outgoing;

    /**
     * The cached value of the '{@link #getIncoming() <em>Incoming</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getIncoming()
     * @generated
     * @ordered
     */
    protected EList<COREContribution> incoming;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected COREImpactNodeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return CorePackage.Literals.CORE_IMPACT_NODE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public float getScalingFactor() {
        return scalingFactor;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setScalingFactor(float newScalingFactor) {
        float oldScalingFactor = scalingFactor;
        scalingFactor = newScalingFactor;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.CORE_IMPACT_NODE__SCALING_FACTOR, oldScalingFactor, scalingFactor));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public float getOffset() {
        return offset;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setOffset(float newOffset) {
        float oldOffset = offset;
        offset = newOffset;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.CORE_IMPACT_NODE__OFFSET, oldOffset, offset));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<COREContribution> getOutgoing() {
        if (outgoing == null) {
            outgoing = new EObjectWithInverseResolvingEList<COREContribution>(COREContribution.class, this, CorePackage.CORE_IMPACT_NODE__OUTGOING, CorePackage.CORE_CONTRIBUTION__SOURCE);
        }
        return outgoing;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<COREContribution> getIncoming() {
        if (incoming == null) {
            incoming = new EObjectWithInverseResolvingEList<COREContribution>(COREContribution.class, this, CorePackage.CORE_IMPACT_NODE__INCOMING, CorePackage.CORE_CONTRIBUTION__IMPACTS);
        }
        return incoming;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case CorePackage.CORE_IMPACT_NODE__OUTGOING:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutgoing()).basicAdd(otherEnd, msgs);
            case CorePackage.CORE_IMPACT_NODE__INCOMING:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getIncoming()).basicAdd(otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case CorePackage.CORE_IMPACT_NODE__OUTGOING:
                return ((InternalEList<?>)getOutgoing()).basicRemove(otherEnd, msgs);
            case CorePackage.CORE_IMPACT_NODE__INCOMING:
                return ((InternalEList<?>)getIncoming()).basicRemove(otherEnd, msgs);
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
            case CorePackage.CORE_IMPACT_NODE__SCALING_FACTOR:
                return getScalingFactor();
            case CorePackage.CORE_IMPACT_NODE__OFFSET:
                return getOffset();
            case CorePackage.CORE_IMPACT_NODE__OUTGOING:
                return getOutgoing();
            case CorePackage.CORE_IMPACT_NODE__INCOMING:
                return getIncoming();
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
            case CorePackage.CORE_IMPACT_NODE__SCALING_FACTOR:
                setScalingFactor((Float)newValue);
                return;
            case CorePackage.CORE_IMPACT_NODE__OFFSET:
                setOffset((Float)newValue);
                return;
            case CorePackage.CORE_IMPACT_NODE__OUTGOING:
                getOutgoing().clear();
                getOutgoing().addAll((Collection<? extends COREContribution>)newValue);
                return;
            case CorePackage.CORE_IMPACT_NODE__INCOMING:
                getIncoming().clear();
                getIncoming().addAll((Collection<? extends COREContribution>)newValue);
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
            case CorePackage.CORE_IMPACT_NODE__SCALING_FACTOR:
                setScalingFactor(SCALING_FACTOR_EDEFAULT);
                return;
            case CorePackage.CORE_IMPACT_NODE__OFFSET:
                setOffset(OFFSET_EDEFAULT);
                return;
            case CorePackage.CORE_IMPACT_NODE__OUTGOING:
                getOutgoing().clear();
                return;
            case CorePackage.CORE_IMPACT_NODE__INCOMING:
                getIncoming().clear();
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
            case CorePackage.CORE_IMPACT_NODE__SCALING_FACTOR:
                return scalingFactor != SCALING_FACTOR_EDEFAULT;
            case CorePackage.CORE_IMPACT_NODE__OFFSET:
                return offset != OFFSET_EDEFAULT;
            case CorePackage.CORE_IMPACT_NODE__OUTGOING:
                return outgoing != null && !outgoing.isEmpty();
            case CorePackage.CORE_IMPACT_NODE__INCOMING:
                return incoming != null && !incoming.isEmpty();
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
        result.append(" (scalingFactor: ");
        result.append(scalingFactor);
        result.append(", offset: ");
        result.append(offset);
        result.append(')');
        return result.toString();
    }

} //COREImpactModelElementImpl
