/**
 */
package ca.mcgill.sel.ram.impl;

import ca.mcgill.sel.core.COREModelElement;
import ca.mcgill.sel.core.COREPartialityType;
import ca.mcgill.sel.core.COREVisibilityType;
import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.ram.MappableElement;
import ca.mcgill.sel.ram.Parameter;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.Type;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Parameter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ca.mcgill.sel.ram.impl.ParameterImpl#getPartiality <em>Partiality</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.impl.ParameterImpl#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.impl.ParameterImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ParameterImpl extends TypedElementImpl implements Parameter {
    /**
     * The default value of the '{@link #getPartiality() <em>Partiality</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPartiality()
     * @generated
     * @ordered
     */
    protected static final COREPartialityType PARTIALITY_EDEFAULT = COREPartialityType.NONE;
    /**
     * The cached value of the '{@link #getPartiality() <em>Partiality</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPartiality()
     * @generated
     * @ordered
     */
    protected COREPartialityType partiality = PARTIALITY_EDEFAULT;
    /**
     * The default value of the '{@link #getVisibility() <em>Visibility</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVisibility()
     * @generated
     * @ordered
     */
    protected static final COREVisibilityType VISIBILITY_EDEFAULT = COREVisibilityType.CONCERN;
    /**
     * The cached value of the '{@link #getVisibility() <em>Visibility</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVisibility()
     * @generated
     * @ordered
     */
    protected COREVisibilityType visibility = VISIBILITY_EDEFAULT;
    /**
     * The cached value of the '{@link #getType() <em>Type</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    protected Type type;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ParameterImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return RamPackage.Literals.PARAMETER;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public COREPartialityType getPartiality() {
        return partiality;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPartiality(COREPartialityType newPartiality) {
        COREPartialityType oldPartiality = partiality;
        partiality = newPartiality == null ? PARTIALITY_EDEFAULT : newPartiality;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RamPackage.PARAMETER__PARTIALITY, oldPartiality, partiality));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public COREVisibilityType getVisibility() {
        return visibility;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setVisibility(COREVisibilityType newVisibility) {
        COREVisibilityType oldVisibility = visibility;
        visibility = newVisibility == null ? VISIBILITY_EDEFAULT : newVisibility;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RamPackage.PARAMETER__VISIBILITY, oldVisibility, visibility));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Type getType() {
        if (type != null && type.eIsProxy()) {
            InternalEObject oldType = (InternalEObject)type;
            type = (Type)eResolveProxy(oldType);
            if (type != oldType) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, RamPackage.PARAMETER__TYPE, oldType, type));
            }
        }
        return type;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Type basicGetType() {
        return type;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setType(Type newType) {
        Type oldType = type;
        type = newType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RamPackage.PARAMETER__TYPE, oldType, type));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case RamPackage.PARAMETER__PARTIALITY:
                return getPartiality();
            case RamPackage.PARAMETER__VISIBILITY:
                return getVisibility();
            case RamPackage.PARAMETER__TYPE:
                if (resolve) return getType();
                return basicGetType();
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
            case RamPackage.PARAMETER__PARTIALITY:
                setPartiality((COREPartialityType)newValue);
                return;
            case RamPackage.PARAMETER__VISIBILITY:
                setVisibility((COREVisibilityType)newValue);
                return;
            case RamPackage.PARAMETER__TYPE:
                setType((Type)newValue);
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
            case RamPackage.PARAMETER__PARTIALITY:
                setPartiality(PARTIALITY_EDEFAULT);
                return;
            case RamPackage.PARAMETER__VISIBILITY:
                setVisibility(VISIBILITY_EDEFAULT);
                return;
            case RamPackage.PARAMETER__TYPE:
                setType((Type)null);
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
            case RamPackage.PARAMETER__PARTIALITY:
                return partiality != PARTIALITY_EDEFAULT;
            case RamPackage.PARAMETER__VISIBILITY:
                return visibility != VISIBILITY_EDEFAULT;
            case RamPackage.PARAMETER__TYPE:
                return type != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
        if (baseClass == COREModelElement.class) {
            switch (derivedFeatureID) {
                case RamPackage.PARAMETER__PARTIALITY: return CorePackage.CORE_MODEL_ELEMENT__PARTIALITY;
                case RamPackage.PARAMETER__VISIBILITY: return CorePackage.CORE_MODEL_ELEMENT__VISIBILITY;
                default: return -1;
            }
        }
        if (baseClass == MappableElement.class) {
            switch (derivedFeatureID) {
                default: return -1;
            }
        }
        return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
        if (baseClass == COREModelElement.class) {
            switch (baseFeatureID) {
                case CorePackage.CORE_MODEL_ELEMENT__PARTIALITY: return RamPackage.PARAMETER__PARTIALITY;
                case CorePackage.CORE_MODEL_ELEMENT__VISIBILITY: return RamPackage.PARAMETER__VISIBILITY;
                default: return -1;
            }
        }
        if (baseClass == MappableElement.class) {
            switch (baseFeatureID) {
                default: return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
        result.append(" (partiality: ");
        result.append(partiality);
        result.append(", visibility: ");
        result.append(visibility);
        result.append(')');
        return result.toString();
    }

} //ParameterImpl
