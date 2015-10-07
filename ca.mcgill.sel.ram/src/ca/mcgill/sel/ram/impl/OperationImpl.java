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
import org.eclipse.emf.ecore.util.InternalEList;

import ca.mcgill.sel.core.COREModelElement;
import ca.mcgill.sel.core.COREPartialityType;
import ca.mcgill.sel.core.COREVisibilityType;
import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.ram.MappableElement;
import ca.mcgill.sel.ram.Operation;
import ca.mcgill.sel.ram.OperationType;
import ca.mcgill.sel.ram.Parameter;
import ca.mcgill.sel.ram.RAMVisibilityType;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.Traceable;
import ca.mcgill.sel.ram.Type;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Operation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ca.mcgill.sel.ram.impl.OperationImpl#getPartiality <em>Partiality</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.impl.OperationImpl#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.impl.OperationImpl#isAbstract <em>Abstract</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.impl.OperationImpl#getExtendedVisibility <em>Extended Visibility</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.impl.OperationImpl#getReturnType <em>Return Type</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.impl.OperationImpl#getParameters <em>Parameters</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.impl.OperationImpl#isStatic <em>Static</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.impl.OperationImpl#getOperationType <em>Operation Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OperationImpl extends NamedElementImpl implements Operation {
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
     * The default value of the '{@link #isAbstract() <em>Abstract</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isAbstract()
     * @generated
     * @ordered
     */
    protected static final boolean ABSTRACT_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isAbstract() <em>Abstract</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isAbstract()
     * @generated
     * @ordered
     */
    protected boolean abstract_ = ABSTRACT_EDEFAULT;

    /**
     * The default value of the '{@link #getExtendedVisibility() <em>Extended Visibility</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getExtendedVisibility()
     * @generated
     * @ordered
     */
    protected static final RAMVisibilityType EXTENDED_VISIBILITY_EDEFAULT = RAMVisibilityType.PUBLIC;

    /**
     * The cached value of the '{@link #getExtendedVisibility() <em>Extended Visibility</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getExtendedVisibility()
     * @generated
     * @ordered
     */
    protected RAMVisibilityType extendedVisibility = EXTENDED_VISIBILITY_EDEFAULT;

    /**
     * The cached value of the '{@link #getReturnType() <em>Return Type</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getReturnType()
     * @generated
     * @ordered
     */
    protected Type returnType;

    /**
     * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getParameters()
     * @generated
     * @ordered
     */
    protected EList<Parameter> parameters;

    /**
     * The default value of the '{@link #isStatic() <em>Static</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isStatic()
     * @generated
     * @ordered
     */
    protected static final boolean STATIC_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isStatic() <em>Static</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isStatic()
     * @generated
     * @ordered
     */
    protected boolean static_ = STATIC_EDEFAULT;

    /**
     * The default value of the '{@link #getOperationType() <em>Operation Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOperationType()
     * @generated
     * @ordered
     */
    protected static final OperationType OPERATION_TYPE_EDEFAULT = OperationType.NORMAL;

    /**
     * The cached value of the '{@link #getOperationType() <em>Operation Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOperationType()
     * @generated
     * @ordered
     */
    protected OperationType operationType = OPERATION_TYPE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected OperationImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return RamPackage.Literals.OPERATION;
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
            eNotify(new ENotificationImpl(this, Notification.SET, RamPackage.OPERATION__PARTIALITY, oldPartiality, partiality));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isAbstract() {
        return abstract_;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setAbstract(boolean newAbstract) {
        boolean oldAbstract = abstract_;
        abstract_ = newAbstract;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RamPackage.OPERATION__ABSTRACT, oldAbstract, abstract_));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public RAMVisibilityType getExtendedVisibility() {
        return extendedVisibility;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setExtendedVisibility(RAMVisibilityType newExtendedVisibility) {
        RAMVisibilityType oldExtendedVisibility = extendedVisibility;
        extendedVisibility = newExtendedVisibility == null ? EXTENDED_VISIBILITY_EDEFAULT : newExtendedVisibility;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RamPackage.OPERATION__EXTENDED_VISIBILITY, oldExtendedVisibility, extendedVisibility));
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
            eNotify(new ENotificationImpl(this, Notification.SET, RamPackage.OPERATION__VISIBILITY, oldVisibility, visibility));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Type getReturnType() {
        if (returnType != null && returnType.eIsProxy()) {
            InternalEObject oldReturnType = (InternalEObject)returnType;
            returnType = (Type)eResolveProxy(oldReturnType);
            if (returnType != oldReturnType) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, RamPackage.OPERATION__RETURN_TYPE, oldReturnType, returnType));
            }
        }
        return returnType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Type basicGetReturnType() {
        return returnType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setReturnType(Type newReturnType) {
        Type oldReturnType = returnType;
        returnType = newReturnType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RamPackage.OPERATION__RETURN_TYPE, oldReturnType, returnType));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Parameter> getParameters() {
        if (parameters == null) {
            parameters = new EObjectContainmentEList<Parameter>(Parameter.class, this, RamPackage.OPERATION__PARAMETERS);
        }
        return parameters;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isStatic() {
        return static_;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setStatic(boolean newStatic) {
        boolean oldStatic = static_;
        static_ = newStatic;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RamPackage.OPERATION__STATIC, oldStatic, static_));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public OperationType getOperationType() {
        return operationType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setOperationType(OperationType newOperationType) {
        OperationType oldOperationType = operationType;
        operationType = newOperationType == null ? OPERATION_TYPE_EDEFAULT : newOperationType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RamPackage.OPERATION__OPERATION_TYPE, oldOperationType, operationType));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case RamPackage.OPERATION__PARAMETERS:
                return ((InternalEList<?>)getParameters()).basicRemove(otherEnd, msgs);
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
            case RamPackage.OPERATION__PARTIALITY:
                return getPartiality();
            case RamPackage.OPERATION__VISIBILITY:
                return getVisibility();
            case RamPackage.OPERATION__ABSTRACT:
                return isAbstract();
            case RamPackage.OPERATION__EXTENDED_VISIBILITY:
                return getExtendedVisibility();
            case RamPackage.OPERATION__RETURN_TYPE:
                if (resolve) return getReturnType();
                return basicGetReturnType();
            case RamPackage.OPERATION__PARAMETERS:
                return getParameters();
            case RamPackage.OPERATION__STATIC:
                return isStatic();
            case RamPackage.OPERATION__OPERATION_TYPE:
                return getOperationType();
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
            case RamPackage.OPERATION__PARTIALITY:
                setPartiality((COREPartialityType)newValue);
                return;
            case RamPackage.OPERATION__VISIBILITY:
                setVisibility((COREVisibilityType)newValue);
                return;
            case RamPackage.OPERATION__ABSTRACT:
                setAbstract((Boolean)newValue);
                return;
            case RamPackage.OPERATION__EXTENDED_VISIBILITY:
                setExtendedVisibility((RAMVisibilityType)newValue);
                return;
            case RamPackage.OPERATION__RETURN_TYPE:
                setReturnType((Type)newValue);
                return;
            case RamPackage.OPERATION__PARAMETERS:
                getParameters().clear();
                getParameters().addAll((Collection<? extends Parameter>)newValue);
                return;
            case RamPackage.OPERATION__STATIC:
                setStatic((Boolean)newValue);
                return;
            case RamPackage.OPERATION__OPERATION_TYPE:
                setOperationType((OperationType)newValue);
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
            case RamPackage.OPERATION__PARTIALITY:
                setPartiality(PARTIALITY_EDEFAULT);
                return;
            case RamPackage.OPERATION__VISIBILITY:
                setVisibility(VISIBILITY_EDEFAULT);
                return;
            case RamPackage.OPERATION__ABSTRACT:
                setAbstract(ABSTRACT_EDEFAULT);
                return;
            case RamPackage.OPERATION__EXTENDED_VISIBILITY:
                setExtendedVisibility(EXTENDED_VISIBILITY_EDEFAULT);
                return;
            case RamPackage.OPERATION__RETURN_TYPE:
                setReturnType((Type)null);
                return;
            case RamPackage.OPERATION__PARAMETERS:
                getParameters().clear();
                return;
            case RamPackage.OPERATION__STATIC:
                setStatic(STATIC_EDEFAULT);
                return;
            case RamPackage.OPERATION__OPERATION_TYPE:
                setOperationType(OPERATION_TYPE_EDEFAULT);
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
            case RamPackage.OPERATION__PARTIALITY:
                return partiality != PARTIALITY_EDEFAULT;
            case RamPackage.OPERATION__VISIBILITY:
                return visibility != VISIBILITY_EDEFAULT;
            case RamPackage.OPERATION__ABSTRACT:
                return abstract_ != ABSTRACT_EDEFAULT;
            case RamPackage.OPERATION__EXTENDED_VISIBILITY:
                return extendedVisibility != EXTENDED_VISIBILITY_EDEFAULT;
            case RamPackage.OPERATION__RETURN_TYPE:
                return returnType != null;
            case RamPackage.OPERATION__PARAMETERS:
                return parameters != null && !parameters.isEmpty();
            case RamPackage.OPERATION__STATIC:
                return static_ != STATIC_EDEFAULT;
            case RamPackage.OPERATION__OPERATION_TYPE:
                return operationType != OPERATION_TYPE_EDEFAULT;
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
                case RamPackage.OPERATION__PARTIALITY: return CorePackage.CORE_MODEL_ELEMENT__PARTIALITY;
                case RamPackage.OPERATION__VISIBILITY: return CorePackage.CORE_MODEL_ELEMENT__VISIBILITY;
                default: return -1;
            }
        }
        if (baseClass == MappableElement.class) {
            switch (derivedFeatureID) {
                default: return -1;
            }
        }
        if (baseClass == Traceable.class) {
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
                case CorePackage.CORE_MODEL_ELEMENT__PARTIALITY: return RamPackage.OPERATION__PARTIALITY;
                case CorePackage.CORE_MODEL_ELEMENT__VISIBILITY: return RamPackage.OPERATION__VISIBILITY;
                default: return -1;
            }
        }
        if (baseClass == MappableElement.class) {
            switch (baseFeatureID) {
                default: return -1;
            }
        }
        if (baseClass == Traceable.class) {
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
        result.append(", abstract: ");
        result.append(abstract_);
        result.append(", extendedVisibility: ");
        result.append(extendedVisibility);
        result.append(", static: ");
        result.append(static_);
        result.append(", operationType: ");
        result.append(operationType);
        result.append(')');
        return result.toString();
    }

} //OperationImpl
