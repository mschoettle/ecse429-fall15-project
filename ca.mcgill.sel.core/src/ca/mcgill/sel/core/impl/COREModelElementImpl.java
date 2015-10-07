/**
 */
package ca.mcgill.sel.core.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import ca.mcgill.sel.core.COREModelElement;
import ca.mcgill.sel.core.COREPartialityType;
import ca.mcgill.sel.core.COREVisibilityType;
import ca.mcgill.sel.core.CorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CORE Model Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link ca.mcgill.sel.core.impl.COREModelElementImpl#getPartiality <em>Partiality</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.impl.COREModelElementImpl#getVisibility <em>Visibility</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class COREModelElementImpl extends CORENamedElementImpl implements COREModelElement {
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
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected COREModelElementImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return CorePackage.Literals.CORE_MODEL_ELEMENT;
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
            eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.CORE_MODEL_ELEMENT__PARTIALITY, oldPartiality, partiality));
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
            eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.CORE_MODEL_ELEMENT__VISIBILITY, oldVisibility, visibility));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case CorePackage.CORE_MODEL_ELEMENT__PARTIALITY:
                return getPartiality();
            case CorePackage.CORE_MODEL_ELEMENT__VISIBILITY:
                return getVisibility();
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
            case CorePackage.CORE_MODEL_ELEMENT__PARTIALITY:
                setPartiality((COREPartialityType)newValue);
                return;
            case CorePackage.CORE_MODEL_ELEMENT__VISIBILITY:
                setVisibility((COREVisibilityType)newValue);
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
            case CorePackage.CORE_MODEL_ELEMENT__PARTIALITY:
                setPartiality(PARTIALITY_EDEFAULT);
                return;
            case CorePackage.CORE_MODEL_ELEMENT__VISIBILITY:
                setVisibility(VISIBILITY_EDEFAULT);
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
            case CorePackage.CORE_MODEL_ELEMENT__PARTIALITY:
                return partiality != PARTIALITY_EDEFAULT;
            case CorePackage.CORE_MODEL_ELEMENT__VISIBILITY:
                return visibility != VISIBILITY_EDEFAULT;
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
        result.append(" (partiality: ");
        result.append(partiality);
        result.append(", visibility: ");
        result.append(visibility);
        result.append(')');
        return result.toString();
    }

} //COREModelElementImpl
