/**
 */
package ca.mcgill.sel.ram.impl;

import ca.mcgill.sel.core.COREModelReuse;
import ca.mcgill.sel.ram.Association;
import ca.mcgill.sel.ram.AssociationEnd;
import ca.mcgill.sel.ram.Classifier;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.Type;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Association End</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ca.mcgill.sel.ram.impl.AssociationEndImpl#isNavigable <em>Navigable</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.impl.AssociationEndImpl#getAssoc <em>Assoc</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.impl.AssociationEndImpl#getClassifier <em>Classifier</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.impl.AssociationEndImpl#getFeatureSelection <em>Feature Selection</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AssociationEndImpl extends PropertyImpl implements AssociationEnd {
    /**
     * The default value of the '{@link #isNavigable() <em>Navigable</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isNavigable()
     * @generated
     * @ordered
     */
    protected static final boolean NAVIGABLE_EDEFAULT = true;

    /**
     * The cached value of the '{@link #isNavigable() <em>Navigable</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isNavigable()
     * @generated
     * @ordered
     */
    protected boolean navigable = NAVIGABLE_EDEFAULT;

    /**
     * The cached value of the '{@link #getAssoc() <em>Assoc</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAssoc()
     * @generated
     * @ordered
     */
    protected Association assoc;

    /**
     * The cached value of the '{@link #getFeatureSelection() <em>Feature Selection</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFeatureSelection()
     * @generated
     * @ordered
     */
    protected COREModelReuse featureSelection;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected AssociationEndImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return RamPackage.Literals.ASSOCIATION_END;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isNavigable() {
        return navigable;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNavigable(boolean newNavigable) {
        boolean oldNavigable = navigable;
        navigable = newNavigable;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RamPackage.ASSOCIATION_END__NAVIGABLE, oldNavigable, navigable));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Association getAssoc() {
        if (assoc != null && assoc.eIsProxy()) {
            InternalEObject oldAssoc = (InternalEObject)assoc;
            assoc = (Association)eResolveProxy(oldAssoc);
            if (assoc != oldAssoc) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, RamPackage.ASSOCIATION_END__ASSOC, oldAssoc, assoc));
            }
        }
        return assoc;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Association basicGetAssoc() {
        return assoc;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetAssoc(Association newAssoc, NotificationChain msgs) {
        Association oldAssoc = assoc;
        assoc = newAssoc;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RamPackage.ASSOCIATION_END__ASSOC, oldAssoc, newAssoc);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setAssoc(Association newAssoc) {
        if (newAssoc != assoc) {
            NotificationChain msgs = null;
            if (assoc != null)
                msgs = ((InternalEObject)assoc).eInverseRemove(this, RamPackage.ASSOCIATION__ENDS, Association.class, msgs);
            if (newAssoc != null)
                msgs = ((InternalEObject)newAssoc).eInverseAdd(this, RamPackage.ASSOCIATION__ENDS, Association.class, msgs);
            msgs = basicSetAssoc(newAssoc, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RamPackage.ASSOCIATION_END__ASSOC, newAssoc, newAssoc));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Classifier getClassifier() {
        if (eContainerFeatureID() != RamPackage.ASSOCIATION_END__CLASSIFIER) return null;
        return (Classifier)eInternalContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetClassifier(Classifier newClassifier, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newClassifier, RamPackage.ASSOCIATION_END__CLASSIFIER, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setClassifier(Classifier newClassifier) {
        if (newClassifier != eInternalContainer() || (eContainerFeatureID() != RamPackage.ASSOCIATION_END__CLASSIFIER && newClassifier != null)) {
            if (EcoreUtil.isAncestor(this, newClassifier))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newClassifier != null)
                msgs = ((InternalEObject)newClassifier).eInverseAdd(this, RamPackage.CLASSIFIER__ASSOCIATION_ENDS, Classifier.class, msgs);
            msgs = basicSetClassifier(newClassifier, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RamPackage.ASSOCIATION_END__CLASSIFIER, newClassifier, newClassifier));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public COREModelReuse getFeatureSelection() {
        if (featureSelection != null && featureSelection.eIsProxy()) {
            InternalEObject oldFeatureSelection = (InternalEObject)featureSelection;
            featureSelection = (COREModelReuse)eResolveProxy(oldFeatureSelection);
            if (featureSelection != oldFeatureSelection) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, RamPackage.ASSOCIATION_END__FEATURE_SELECTION, oldFeatureSelection, featureSelection));
            }
        }
        return featureSelection;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public COREModelReuse basicGetFeatureSelection() {
        return featureSelection;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFeatureSelection(COREModelReuse newFeatureSelection) {
        COREModelReuse oldFeatureSelection = featureSelection;
        featureSelection = newFeatureSelection;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RamPackage.ASSOCIATION_END__FEATURE_SELECTION, oldFeatureSelection, featureSelection));
    }

    /**
     * The cached invocation delegate for the '{@link #getType() <em>Get Type</em>}' operation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    protected static final EOperation.Internal.InvocationDelegate GET_TYPE__EINVOCATION_DELEGATE = ((EOperation.Internal)RamPackage.Literals.ASSOCIATION_END.getEOperations().get(0)).getInvocationDelegate();

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Type getType() {
        try {
            return (Type)GET_TYPE__EINVOCATION_DELEGATE.dynamicInvoke(this, null);
        }
        catch (InvocationTargetException ite) {
            throw new WrappedException(ite);
        }
    }

    /**
     * The cached invocation delegate for the '{@link #getOppositeEnd() <em>Get Opposite End</em>}' operation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOppositeEnd()
     * @generated
     * @ordered
     */
    protected static final EOperation.Internal.InvocationDelegate GET_OPPOSITE_END__EINVOCATION_DELEGATE = ((EOperation.Internal)RamPackage.Literals.ASSOCIATION_END.getEOperations().get(1)).getInvocationDelegate();

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AssociationEnd getOppositeEnd() {
        try {
            return (AssociationEnd)GET_OPPOSITE_END__EINVOCATION_DELEGATE.dynamicInvoke(this, null);
        }
        catch (InvocationTargetException ite) {
            throw new WrappedException(ite);
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case RamPackage.ASSOCIATION_END__ASSOC:
                if (assoc != null)
                    msgs = ((InternalEObject)assoc).eInverseRemove(this, RamPackage.ASSOCIATION__ENDS, Association.class, msgs);
                return basicSetAssoc((Association)otherEnd, msgs);
            case RamPackage.ASSOCIATION_END__CLASSIFIER:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetClassifier((Classifier)otherEnd, msgs);
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
            case RamPackage.ASSOCIATION_END__ASSOC:
                return basicSetAssoc(null, msgs);
            case RamPackage.ASSOCIATION_END__CLASSIFIER:
                return basicSetClassifier(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
        switch (eContainerFeatureID()) {
            case RamPackage.ASSOCIATION_END__CLASSIFIER:
                return eInternalContainer().eInverseRemove(this, RamPackage.CLASSIFIER__ASSOCIATION_ENDS, Classifier.class, msgs);
        }
        return super.eBasicRemoveFromContainerFeature(msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case RamPackage.ASSOCIATION_END__NAVIGABLE:
                return isNavigable();
            case RamPackage.ASSOCIATION_END__ASSOC:
                if (resolve) return getAssoc();
                return basicGetAssoc();
            case RamPackage.ASSOCIATION_END__CLASSIFIER:
                return getClassifier();
            case RamPackage.ASSOCIATION_END__FEATURE_SELECTION:
                if (resolve) return getFeatureSelection();
                return basicGetFeatureSelection();
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
            case RamPackage.ASSOCIATION_END__NAVIGABLE:
                setNavigable((Boolean)newValue);
                return;
            case RamPackage.ASSOCIATION_END__ASSOC:
                setAssoc((Association)newValue);
                return;
            case RamPackage.ASSOCIATION_END__CLASSIFIER:
                setClassifier((Classifier)newValue);
                return;
            case RamPackage.ASSOCIATION_END__FEATURE_SELECTION:
                setFeatureSelection((COREModelReuse)newValue);
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
            case RamPackage.ASSOCIATION_END__NAVIGABLE:
                setNavigable(NAVIGABLE_EDEFAULT);
                return;
            case RamPackage.ASSOCIATION_END__ASSOC:
                setAssoc((Association)null);
                return;
            case RamPackage.ASSOCIATION_END__CLASSIFIER:
                setClassifier((Classifier)null);
                return;
            case RamPackage.ASSOCIATION_END__FEATURE_SELECTION:
                setFeatureSelection((COREModelReuse)null);
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
            case RamPackage.ASSOCIATION_END__NAVIGABLE:
                return navigable != NAVIGABLE_EDEFAULT;
            case RamPackage.ASSOCIATION_END__ASSOC:
                return assoc != null;
            case RamPackage.ASSOCIATION_END__CLASSIFIER:
                return getClassifier() != null;
            case RamPackage.ASSOCIATION_END__FEATURE_SELECTION:
                return featureSelection != null;
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
        result.append(" (navigable: ");
        result.append(navigable);
        result.append(')');
        return result.toString();
    }

} //AssociationEndImpl
