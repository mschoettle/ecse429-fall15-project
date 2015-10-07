/**
 */
package ca.mcgill.sel.ram.impl;

import ca.mcgill.sel.ram.EnumLiteralValue;
import ca.mcgill.sel.ram.REnumLiteral;
import ca.mcgill.sel.ram.RamPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Enum Literal Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ca.mcgill.sel.ram.impl.EnumLiteralValueImpl#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EnumLiteralValueImpl extends ValueSpecificationImpl implements EnumLiteralValue {
    /**
     * The cached value of the '{@link #getValue() <em>Value</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getValue()
     * @generated
     * @ordered
     */
    protected REnumLiteral value;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EnumLiteralValueImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return RamPackage.Literals.ENUM_LITERAL_VALUE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public REnumLiteral getValue() {
        if (value != null && value.eIsProxy()) {
            InternalEObject oldValue = (InternalEObject)value;
            value = (REnumLiteral)eResolveProxy(oldValue);
            if (value != oldValue) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, RamPackage.ENUM_LITERAL_VALUE__VALUE, oldValue, value));
            }
        }
        return value;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public REnumLiteral basicGetValue() {
        return value;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setValue(REnumLiteral newValue) {
        REnumLiteral oldValue = value;
        value = newValue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RamPackage.ENUM_LITERAL_VALUE__VALUE, oldValue, value));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case RamPackage.ENUM_LITERAL_VALUE__VALUE:
                if (resolve) return getValue();
                return basicGetValue();
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
            case RamPackage.ENUM_LITERAL_VALUE__VALUE:
                setValue((REnumLiteral)newValue);
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
            case RamPackage.ENUM_LITERAL_VALUE__VALUE:
                setValue((REnumLiteral)null);
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
            case RamPackage.ENUM_LITERAL_VALUE__VALUE:
                return value != null;
        }
        return super.eIsSet(featureID);
    }

} //EnumLiteralValueImpl
