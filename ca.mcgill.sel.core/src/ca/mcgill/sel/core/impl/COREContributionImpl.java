/**
 */
package ca.mcgill.sel.core.impl;

import ca.mcgill.sel.core.COREContribution;
import ca.mcgill.sel.core.COREImpactNode;
import ca.mcgill.sel.core.CorePackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CORE Contribution</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link ca.mcgill.sel.core.impl.COREContributionImpl#getRelativeWeight <em>Relative Weight</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.impl.COREContributionImpl#getSource <em>Source</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.impl.COREContributionImpl#getImpacts <em>Impacts</em>}</li>
 * </ul>
 *
 * @generated
 */
public class COREContributionImpl extends EObjectImpl implements COREContribution {
    /**
     * The default value of the '{@link #getRelativeWeight() <em>Relative Weight</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRelativeWeight()
     * @generated
     * @ordered
     */
    protected static final int RELATIVE_WEIGHT_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getRelativeWeight() <em>Relative Weight</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRelativeWeight()
     * @generated
     * @ordered
     */
    protected int relativeWeight = RELATIVE_WEIGHT_EDEFAULT;

    /**
     * The cached value of the '{@link #getSource() <em>Source</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSource()
     * @generated
     * @ordered
     */
    protected COREImpactNode source;

    /**
     * The cached value of the '{@link #getImpacts() <em>Impacts</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getImpacts()
     * @generated
     * @ordered
     */
    protected COREImpactNode impacts;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected COREContributionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return CorePackage.Literals.CORE_CONTRIBUTION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getRelativeWeight() {
        return relativeWeight;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRelativeWeight(int newRelativeWeight) {
        int oldRelativeWeight = relativeWeight;
        relativeWeight = newRelativeWeight;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.CORE_CONTRIBUTION__RELATIVE_WEIGHT, oldRelativeWeight, relativeWeight));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public COREImpactNode getSource() {
        if (source != null && source.eIsProxy()) {
            InternalEObject oldSource = (InternalEObject)source;
            source = (COREImpactNode)eResolveProxy(oldSource);
            if (source != oldSource) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, CorePackage.CORE_CONTRIBUTION__SOURCE, oldSource, source));
            }
        }
        return source;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public COREImpactNode basicGetSource() {
        return source;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetSource(COREImpactNode newSource, NotificationChain msgs) {
        COREImpactNode oldSource = source;
        source = newSource;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CorePackage.CORE_CONTRIBUTION__SOURCE, oldSource, newSource);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSource(COREImpactNode newSource) {
        if (newSource != source) {
            NotificationChain msgs = null;
            if (source != null)
                msgs = ((InternalEObject)source).eInverseRemove(this, CorePackage.CORE_IMPACT_NODE__OUTGOING, COREImpactNode.class, msgs);
            if (newSource != null)
                msgs = ((InternalEObject)newSource).eInverseAdd(this, CorePackage.CORE_IMPACT_NODE__OUTGOING, COREImpactNode.class, msgs);
            msgs = basicSetSource(newSource, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.CORE_CONTRIBUTION__SOURCE, newSource, newSource));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public COREImpactNode getImpacts() {
        if (impacts != null && impacts.eIsProxy()) {
            InternalEObject oldImpacts = (InternalEObject)impacts;
            impacts = (COREImpactNode)eResolveProxy(oldImpacts);
            if (impacts != oldImpacts) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, CorePackage.CORE_CONTRIBUTION__IMPACTS, oldImpacts, impacts));
            }
        }
        return impacts;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public COREImpactNode basicGetImpacts() {
        return impacts;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetImpacts(COREImpactNode newImpacts, NotificationChain msgs) {
        COREImpactNode oldImpacts = impacts;
        impacts = newImpacts;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CorePackage.CORE_CONTRIBUTION__IMPACTS, oldImpacts, newImpacts);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setImpacts(COREImpactNode newImpacts) {
        if (newImpacts != impacts) {
            NotificationChain msgs = null;
            if (impacts != null)
                msgs = ((InternalEObject)impacts).eInverseRemove(this, CorePackage.CORE_IMPACT_NODE__INCOMING, COREImpactNode.class, msgs);
            if (newImpacts != null)
                msgs = ((InternalEObject)newImpacts).eInverseAdd(this, CorePackage.CORE_IMPACT_NODE__INCOMING, COREImpactNode.class, msgs);
            msgs = basicSetImpacts(newImpacts, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.CORE_CONTRIBUTION__IMPACTS, newImpacts, newImpacts));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case CorePackage.CORE_CONTRIBUTION__SOURCE:
                if (source != null)
                    msgs = ((InternalEObject)source).eInverseRemove(this, CorePackage.CORE_IMPACT_NODE__OUTGOING, COREImpactNode.class, msgs);
                return basicSetSource((COREImpactNode)otherEnd, msgs);
            case CorePackage.CORE_CONTRIBUTION__IMPACTS:
                if (impacts != null)
                    msgs = ((InternalEObject)impacts).eInverseRemove(this, CorePackage.CORE_IMPACT_NODE__INCOMING, COREImpactNode.class, msgs);
                return basicSetImpacts((COREImpactNode)otherEnd, msgs);
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
            case CorePackage.CORE_CONTRIBUTION__SOURCE:
                return basicSetSource(null, msgs);
            case CorePackage.CORE_CONTRIBUTION__IMPACTS:
                return basicSetImpacts(null, msgs);
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
            case CorePackage.CORE_CONTRIBUTION__RELATIVE_WEIGHT:
                return getRelativeWeight();
            case CorePackage.CORE_CONTRIBUTION__SOURCE:
                if (resolve) return getSource();
                return basicGetSource();
            case CorePackage.CORE_CONTRIBUTION__IMPACTS:
                if (resolve) return getImpacts();
                return basicGetImpacts();
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
            case CorePackage.CORE_CONTRIBUTION__RELATIVE_WEIGHT:
                setRelativeWeight((Integer)newValue);
                return;
            case CorePackage.CORE_CONTRIBUTION__SOURCE:
                setSource((COREImpactNode)newValue);
                return;
            case CorePackage.CORE_CONTRIBUTION__IMPACTS:
                setImpacts((COREImpactNode)newValue);
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
            case CorePackage.CORE_CONTRIBUTION__RELATIVE_WEIGHT:
                setRelativeWeight(RELATIVE_WEIGHT_EDEFAULT);
                return;
            case CorePackage.CORE_CONTRIBUTION__SOURCE:
                setSource((COREImpactNode)null);
                return;
            case CorePackage.CORE_CONTRIBUTION__IMPACTS:
                setImpacts((COREImpactNode)null);
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
            case CorePackage.CORE_CONTRIBUTION__RELATIVE_WEIGHT:
                return relativeWeight != RELATIVE_WEIGHT_EDEFAULT;
            case CorePackage.CORE_CONTRIBUTION__SOURCE:
                return source != null;
            case CorePackage.CORE_CONTRIBUTION__IMPACTS:
                return impacts != null;
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
        result.append(" (relativeWeight: ");
        result.append(relativeWeight);
        result.append(')');
        return result.toString();
    }

} //COREContributionImpl
