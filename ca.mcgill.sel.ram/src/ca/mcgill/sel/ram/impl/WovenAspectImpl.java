/**
 */
package ca.mcgill.sel.ram.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.Traceable;
import ca.mcgill.sel.ram.WovenAspect;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Woven Aspect</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ca.mcgill.sel.ram.impl.WovenAspectImpl#getComesFrom <em>Comes From</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.impl.WovenAspectImpl#getWovenElements <em>Woven Elements</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.impl.WovenAspectImpl#getChildren <em>Children</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WovenAspectImpl extends NamedElementImpl implements WovenAspect {
    /**
     * The cached value of the '{@link #getComesFrom() <em>Comes From</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getComesFrom()
     * @generated
     * @ordered
     */
    protected Aspect comesFrom;

    /**
     * The cached value of the '{@link #getWovenElements() <em>Woven Elements</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getWovenElements()
     * @generated
     * @ordered
     */
    protected EList<Traceable> wovenElements;

    /**
     * The cached value of the '{@link #getChildren() <em>Children</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getChildren()
     * @generated
     * @ordered
     */
    protected EList<WovenAspect> children;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected WovenAspectImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return RamPackage.Literals.WOVEN_ASPECT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Aspect getComesFrom() {
        if (comesFrom != null && comesFrom.eIsProxy()) {
            InternalEObject oldComesFrom = (InternalEObject)comesFrom;
            comesFrom = (Aspect)eResolveProxy(oldComesFrom);
            if (comesFrom != oldComesFrom) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, RamPackage.WOVEN_ASPECT__COMES_FROM, oldComesFrom, comesFrom));
            }
        }
        return comesFrom;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Aspect basicGetComesFrom() {
        return comesFrom;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setComesFrom(Aspect newComesFrom) {
        Aspect oldComesFrom = comesFrom;
        comesFrom = newComesFrom;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RamPackage.WOVEN_ASPECT__COMES_FROM, oldComesFrom, comesFrom));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Traceable> getWovenElements() {
        if (wovenElements == null) {
            wovenElements = new EObjectResolvingEList<Traceable>(Traceable.class, this, RamPackage.WOVEN_ASPECT__WOVEN_ELEMENTS);
        }
        return wovenElements;
    }
    
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<WovenAspect> getChildren() {
        if (children == null) {
            children = new EObjectContainmentEList<WovenAspect>(WovenAspect.class, this, RamPackage.WOVEN_ASPECT__CHILDREN);
        }
        return children;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case RamPackage.WOVEN_ASPECT__CHILDREN:
                return ((InternalEList<?>)getChildren()).basicRemove(otherEnd, msgs);
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
            case RamPackage.WOVEN_ASPECT__COMES_FROM:
                if (resolve) return getComesFrom();
                return basicGetComesFrom();
            case RamPackage.WOVEN_ASPECT__WOVEN_ELEMENTS:
                return getWovenElements();
            case RamPackage.WOVEN_ASPECT__CHILDREN:
                return getChildren();
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
            case RamPackage.WOVEN_ASPECT__COMES_FROM:
                setComesFrom((Aspect)newValue);
                return;
            case RamPackage.WOVEN_ASPECT__WOVEN_ELEMENTS:
                getWovenElements().clear();
                getWovenElements().addAll((Collection<? extends Traceable>)newValue);
                return;
            case RamPackage.WOVEN_ASPECT__CHILDREN:
                getChildren().clear();
                getChildren().addAll((Collection<? extends WovenAspect>)newValue);
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
            case RamPackage.WOVEN_ASPECT__COMES_FROM:
                setComesFrom((Aspect)null);
                return;
            case RamPackage.WOVEN_ASPECT__WOVEN_ELEMENTS:
                getWovenElements().clear();
                return;
            case RamPackage.WOVEN_ASPECT__CHILDREN:
                getChildren().clear();
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
            case RamPackage.WOVEN_ASPECT__COMES_FROM:
                return comesFrom != null;
            case RamPackage.WOVEN_ASPECT__WOVEN_ELEMENTS:
                return wovenElements != null && !wovenElements.isEmpty();
            case RamPackage.WOVEN_ASPECT__CHILDREN:
                return children != null && !children.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //WovenAspectImpl
