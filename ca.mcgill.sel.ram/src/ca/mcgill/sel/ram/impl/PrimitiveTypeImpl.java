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
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import ca.mcgill.sel.ram.AssociationEnd;
import ca.mcgill.sel.ram.Classifier;
import ca.mcgill.sel.ram.ImplementationClass;
import ca.mcgill.sel.ram.Operation;
import ca.mcgill.sel.ram.PrimitiveType;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.Traceable;
import ca.mcgill.sel.ram.TypeParameter;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Primitive Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ca.mcgill.sel.ram.impl.PrimitiveTypeImpl#getOperations <em>Operations</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.impl.PrimitiveTypeImpl#getAssociationEnds <em>Association Ends</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.impl.PrimitiveTypeImpl#getTypeParameters <em>Type Parameters</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.impl.PrimitiveTypeImpl#getSuperTypes <em>Super Types</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.impl.PrimitiveTypeImpl#getInstanceClassName <em>Instance Class Name</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.impl.PrimitiveTypeImpl#isInterface <em>Interface</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class PrimitiveTypeImpl extends ObjectTypeImpl implements PrimitiveType {
    /**
     * The cached value of the '{@link #getOperations() <em>Operations</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOperations()
     * @generated
     * @ordered
     */
    protected EList<Operation> operations;

    /**
     * The cached value of the '{@link #getAssociationEnds() <em>Association Ends</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAssociationEnds()
     * @generated
     * @ordered
     */
    protected EList<AssociationEnd> associationEnds;

    /**
     * The cached value of the '{@link #getTypeParameters() <em>Type Parameters</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTypeParameters()
     * @generated
     * @ordered
     */
    protected EList<TypeParameter> typeParameters;

    /**
     * The cached value of the '{@link #getSuperTypes() <em>Super Types</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSuperTypes()
     * @generated
     * @ordered
     */
    protected EList<Classifier> superTypes;

    /**
     * The default value of the '{@link #getInstanceClassName() <em>Instance Class Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getInstanceClassName()
     * @generated
     * @ordered
     */
    protected static final String INSTANCE_CLASS_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getInstanceClassName() <em>Instance Class Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getInstanceClassName()
     * @generated
     * @ordered
     */
    protected String instanceClassName = INSTANCE_CLASS_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #isInterface() <em>Interface</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isInterface()
     * @generated
     * @ordered
     */
    protected static final boolean INTERFACE_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isInterface() <em>Interface</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isInterface()
     * @generated
     * @ordered
     */
    protected boolean interface_ = INTERFACE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected PrimitiveTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return RamPackage.Literals.PRIMITIVE_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Operation> getOperations() {
        if (operations == null) {
            operations = new EObjectContainmentEList<Operation>(Operation.class, this, RamPackage.PRIMITIVE_TYPE__OPERATIONS);
        }
        return operations;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<AssociationEnd> getAssociationEnds() {
        if (associationEnds == null) {
            associationEnds = new EObjectContainmentWithInverseEList<AssociationEnd>(AssociationEnd.class, this, RamPackage.PRIMITIVE_TYPE__ASSOCIATION_ENDS, RamPackage.ASSOCIATION_END__CLASSIFIER);
        }
        return associationEnds;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<TypeParameter> getTypeParameters() {
        if (typeParameters == null) {
            typeParameters = new EObjectContainmentEList<TypeParameter>(TypeParameter.class, this, RamPackage.PRIMITIVE_TYPE__TYPE_PARAMETERS);
        }
        return typeParameters;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Classifier> getSuperTypes() {
        if (superTypes == null) {
            superTypes = new EObjectResolvingEList<Classifier>(Classifier.class, this, RamPackage.PRIMITIVE_TYPE__SUPER_TYPES);
        }
        return superTypes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getInstanceClassName() {
        return instanceClassName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setInstanceClassName(String newInstanceClassName) {
        String oldInstanceClassName = instanceClassName;
        instanceClassName = newInstanceClassName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RamPackage.PRIMITIVE_TYPE__INSTANCE_CLASS_NAME, oldInstanceClassName, instanceClassName));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isInterface() {
        return interface_;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setInterface(boolean newInterface) {
        boolean oldInterface = interface_;
        interface_ = newInterface;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RamPackage.PRIMITIVE_TYPE__INTERFACE, oldInterface, interface_));
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
            case RamPackage.PRIMITIVE_TYPE__ASSOCIATION_ENDS:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getAssociationEnds()).basicAdd(otherEnd, msgs);
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
            case RamPackage.PRIMITIVE_TYPE__OPERATIONS:
                return ((InternalEList<?>)getOperations()).basicRemove(otherEnd, msgs);
            case RamPackage.PRIMITIVE_TYPE__ASSOCIATION_ENDS:
                return ((InternalEList<?>)getAssociationEnds()).basicRemove(otherEnd, msgs);
            case RamPackage.PRIMITIVE_TYPE__TYPE_PARAMETERS:
                return ((InternalEList<?>)getTypeParameters()).basicRemove(otherEnd, msgs);
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
            case RamPackage.PRIMITIVE_TYPE__OPERATIONS:
                return getOperations();
            case RamPackage.PRIMITIVE_TYPE__ASSOCIATION_ENDS:
                return getAssociationEnds();
            case RamPackage.PRIMITIVE_TYPE__TYPE_PARAMETERS:
                return getTypeParameters();
            case RamPackage.PRIMITIVE_TYPE__SUPER_TYPES:
                return getSuperTypes();
            case RamPackage.PRIMITIVE_TYPE__INSTANCE_CLASS_NAME:
                return getInstanceClassName();
            case RamPackage.PRIMITIVE_TYPE__INTERFACE:
                return isInterface();
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
            case RamPackage.PRIMITIVE_TYPE__OPERATIONS:
                getOperations().clear();
                getOperations().addAll((Collection<? extends Operation>)newValue);
                return;
            case RamPackage.PRIMITIVE_TYPE__ASSOCIATION_ENDS:
                getAssociationEnds().clear();
                getAssociationEnds().addAll((Collection<? extends AssociationEnd>)newValue);
                return;
            case RamPackage.PRIMITIVE_TYPE__TYPE_PARAMETERS:
                getTypeParameters().clear();
                getTypeParameters().addAll((Collection<? extends TypeParameter>)newValue);
                return;
            case RamPackage.PRIMITIVE_TYPE__SUPER_TYPES:
                getSuperTypes().clear();
                getSuperTypes().addAll((Collection<? extends Classifier>)newValue);
                return;
            case RamPackage.PRIMITIVE_TYPE__INSTANCE_CLASS_NAME:
                setInstanceClassName((String)newValue);
                return;
            case RamPackage.PRIMITIVE_TYPE__INTERFACE:
                setInterface((Boolean)newValue);
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
            case RamPackage.PRIMITIVE_TYPE__OPERATIONS:
                getOperations().clear();
                return;
            case RamPackage.PRIMITIVE_TYPE__ASSOCIATION_ENDS:
                getAssociationEnds().clear();
                return;
            case RamPackage.PRIMITIVE_TYPE__TYPE_PARAMETERS:
                getTypeParameters().clear();
                return;
            case RamPackage.PRIMITIVE_TYPE__SUPER_TYPES:
                getSuperTypes().clear();
                return;
            case RamPackage.PRIMITIVE_TYPE__INSTANCE_CLASS_NAME:
                setInstanceClassName(INSTANCE_CLASS_NAME_EDEFAULT);
                return;
            case RamPackage.PRIMITIVE_TYPE__INTERFACE:
                setInterface(INTERFACE_EDEFAULT);
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
            case RamPackage.PRIMITIVE_TYPE__OPERATIONS:
                return operations != null && !operations.isEmpty();
            case RamPackage.PRIMITIVE_TYPE__ASSOCIATION_ENDS:
                return associationEnds != null && !associationEnds.isEmpty();
            case RamPackage.PRIMITIVE_TYPE__TYPE_PARAMETERS:
                return typeParameters != null && !typeParameters.isEmpty();
            case RamPackage.PRIMITIVE_TYPE__SUPER_TYPES:
                return superTypes != null && !superTypes.isEmpty();
            case RamPackage.PRIMITIVE_TYPE__INSTANCE_CLASS_NAME:
                return INSTANCE_CLASS_NAME_EDEFAULT == null ? instanceClassName != null : !INSTANCE_CLASS_NAME_EDEFAULT.equals(instanceClassName);
            case RamPackage.PRIMITIVE_TYPE__INTERFACE:
                return interface_ != INTERFACE_EDEFAULT;
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
        if (baseClass == Traceable.class) {
            switch (derivedFeatureID) {
                default: return -1;
            }
        }
        if (baseClass == Classifier.class) {
            switch (derivedFeatureID) {
                case RamPackage.PRIMITIVE_TYPE__OPERATIONS: return RamPackage.CLASSIFIER__OPERATIONS;
                case RamPackage.PRIMITIVE_TYPE__ASSOCIATION_ENDS: return RamPackage.CLASSIFIER__ASSOCIATION_ENDS;
                case RamPackage.PRIMITIVE_TYPE__TYPE_PARAMETERS: return RamPackage.CLASSIFIER__TYPE_PARAMETERS;
                case RamPackage.PRIMITIVE_TYPE__SUPER_TYPES: return RamPackage.CLASSIFIER__SUPER_TYPES;
                default: return -1;
            }
        }
        if (baseClass == ImplementationClass.class) {
            switch (derivedFeatureID) {
                case RamPackage.PRIMITIVE_TYPE__INSTANCE_CLASS_NAME: return RamPackage.IMPLEMENTATION_CLASS__INSTANCE_CLASS_NAME;
                case RamPackage.PRIMITIVE_TYPE__INTERFACE: return RamPackage.IMPLEMENTATION_CLASS__INTERFACE;
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
        if (baseClass == Traceable.class) {
            switch (baseFeatureID) {
                default: return -1;
            }
        }
        if (baseClass == Classifier.class) {
            switch (baseFeatureID) {
                case RamPackage.CLASSIFIER__OPERATIONS: return RamPackage.PRIMITIVE_TYPE__OPERATIONS;
                case RamPackage.CLASSIFIER__ASSOCIATION_ENDS: return RamPackage.PRIMITIVE_TYPE__ASSOCIATION_ENDS;
                case RamPackage.CLASSIFIER__TYPE_PARAMETERS: return RamPackage.PRIMITIVE_TYPE__TYPE_PARAMETERS;
                case RamPackage.CLASSIFIER__SUPER_TYPES: return RamPackage.PRIMITIVE_TYPE__SUPER_TYPES;
                default: return -1;
            }
        }
        if (baseClass == ImplementationClass.class) {
            switch (baseFeatureID) {
                case RamPackage.IMPLEMENTATION_CLASS__INSTANCE_CLASS_NAME: return RamPackage.PRIMITIVE_TYPE__INSTANCE_CLASS_NAME;
                case RamPackage.IMPLEMENTATION_CLASS__INTERFACE: return RamPackage.PRIMITIVE_TYPE__INTERFACE;
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
        result.append(" (instanceClassName: ");
        result.append(instanceClassName);
        result.append(", interface: ");
        result.append(interface_);
        result.append(')');
        return result.toString();
    }

} //PrimitiveTypeImpl
