/**
 */
package ca.mcgill.sel.ram.impl;

import ca.mcgill.sel.ram.AssignmentStatement;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.StructuralFeature;
import ca.mcgill.sel.ram.ValueSpecification;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Assignment Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ca.mcgill.sel.ram.impl.AssignmentStatementImpl#getAssignTo <em>Assign To</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.impl.AssignmentStatementImpl#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AssignmentStatementImpl extends InteractionFragmentImpl implements AssignmentStatement {
    /**
     * The cached value of the '{@link #getAssignTo() <em>Assign To</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAssignTo()
     * @generated
     * @ordered
     */
    protected StructuralFeature assignTo;

    /**
     * The cached value of the '{@link #getValue() <em>Value</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getValue()
     * @generated
     * @ordered
     */
    protected ValueSpecification value;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected AssignmentStatementImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return RamPackage.Literals.ASSIGNMENT_STATEMENT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public StructuralFeature getAssignTo() {
        if (assignTo != null && assignTo.eIsProxy()) {
            InternalEObject oldAssignTo = (InternalEObject)assignTo;
            assignTo = (StructuralFeature)eResolveProxy(oldAssignTo);
            if (assignTo != oldAssignTo) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, RamPackage.ASSIGNMENT_STATEMENT__ASSIGN_TO, oldAssignTo, assignTo));
            }
        }
        return assignTo;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public StructuralFeature basicGetAssignTo() {
        return assignTo;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setAssignTo(StructuralFeature newAssignTo) {
        StructuralFeature oldAssignTo = assignTo;
        assignTo = newAssignTo;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RamPackage.ASSIGNMENT_STATEMENT__ASSIGN_TO, oldAssignTo, assignTo));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ValueSpecification getValue() {
        return value;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetValue(ValueSpecification newValue, NotificationChain msgs) {
        ValueSpecification oldValue = value;
        value = newValue;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RamPackage.ASSIGNMENT_STATEMENT__VALUE, oldValue, newValue);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setValue(ValueSpecification newValue) {
        if (newValue != value) {
            NotificationChain msgs = null;
            if (value != null)
                msgs = ((InternalEObject)value).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RamPackage.ASSIGNMENT_STATEMENT__VALUE, null, msgs);
            if (newValue != null)
                msgs = ((InternalEObject)newValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RamPackage.ASSIGNMENT_STATEMENT__VALUE, null, msgs);
            msgs = basicSetValue(newValue, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RamPackage.ASSIGNMENT_STATEMENT__VALUE, newValue, newValue));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case RamPackage.ASSIGNMENT_STATEMENT__VALUE:
                return basicSetValue(null, msgs);
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
            case RamPackage.ASSIGNMENT_STATEMENT__ASSIGN_TO:
                if (resolve) return getAssignTo();
                return basicGetAssignTo();
            case RamPackage.ASSIGNMENT_STATEMENT__VALUE:
                return getValue();
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
            case RamPackage.ASSIGNMENT_STATEMENT__ASSIGN_TO:
                setAssignTo((StructuralFeature)newValue);
                return;
            case RamPackage.ASSIGNMENT_STATEMENT__VALUE:
                setValue((ValueSpecification)newValue);
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
            case RamPackage.ASSIGNMENT_STATEMENT__ASSIGN_TO:
                setAssignTo((StructuralFeature)null);
                return;
            case RamPackage.ASSIGNMENT_STATEMENT__VALUE:
                setValue((ValueSpecification)null);
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
            case RamPackage.ASSIGNMENT_STATEMENT__ASSIGN_TO:
                return assignTo != null;
            case RamPackage.ASSIGNMENT_STATEMENT__VALUE:
                return value != null;
        }
        return super.eIsSet(featureID);
    }

} //AssignmentStatementImpl
