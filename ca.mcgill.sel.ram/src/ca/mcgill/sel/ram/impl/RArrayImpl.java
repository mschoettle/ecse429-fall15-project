/**
 */
package ca.mcgill.sel.ram.impl;

import ca.mcgill.sel.ram.ObjectType;
import ca.mcgill.sel.ram.RArray;
import ca.mcgill.sel.ram.RamPackage;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>RArray</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ca.mcgill.sel.ram.impl.RArrayImpl#getSize <em>Size</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.impl.RArrayImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RArrayImpl extends PrimitiveTypeImpl implements RArray {
    /**
     * The default value of the '{@link #getSize() <em>Size</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSize()
     * @generated
     * @ordered
     */
    protected static final int SIZE_EDEFAULT = -1;

    /**
     * The cached value of the '{@link #getSize() <em>Size</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSize()
     * @generated
     * @ordered
     */
    protected int size = SIZE_EDEFAULT;

    /**
     * The cached value of the '{@link #getType() <em>Type</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    protected ObjectType type;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected RArrayImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return RamPackage.Literals.RARRAY;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getSize() {
        return size;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSize(int newSize) {
        int oldSize = size;
        size = newSize;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RamPackage.RARRAY__SIZE, oldSize, size));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ObjectType getType() {
        if (type != null && type.eIsProxy()) {
            InternalEObject oldType = (InternalEObject)type;
            type = (ObjectType)eResolveProxy(oldType);
            if (type != oldType) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, RamPackage.RARRAY__TYPE, oldType, type));
            }
        }
        return type;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ObjectType basicGetType() {
        return type;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setType(ObjectType newType) {
        ObjectType oldType = type;
        type = newType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RamPackage.RARRAY__TYPE, oldType, type));
    }

    /**
     * The cached invocation delegate for the '{@link #getName() <em>Get Name</em>}' operation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final EOperation.Internal.InvocationDelegate GET_NAME__EINVOCATION_DELEGATE = ((EOperation.Internal)RamPackage.Literals.RARRAY.getEOperations().get(0)).getInvocationDelegate();

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getName() {
        try {
            return (String)GET_NAME__EINVOCATION_DELEGATE.dynamicInvoke(this, null);
        }
        catch (InvocationTargetException ite) {
            throw new WrappedException(ite);
        }
    }

    /**
     * The cached invocation delegate for the '{@link #getInstanceClassName() <em>Get Instance Class Name</em>}' operation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getInstanceClassName()
     * @generated
     * @ordered
     */
    protected static final EOperation.Internal.InvocationDelegate GET_INSTANCE_CLASS_NAME__EINVOCATION_DELEGATE = ((EOperation.Internal)RamPackage.Literals.RARRAY.getEOperations().get(1)).getInvocationDelegate();

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getInstanceClassName() {
        try {
            return (String)GET_INSTANCE_CLASS_NAME__EINVOCATION_DELEGATE.dynamicInvoke(this, null);
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
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case RamPackage.RARRAY__SIZE:
                return getSize();
            case RamPackage.RARRAY__TYPE:
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
            case RamPackage.RARRAY__SIZE:
                setSize((Integer)newValue);
                return;
            case RamPackage.RARRAY__TYPE:
                setType((ObjectType)newValue);
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
            case RamPackage.RARRAY__SIZE:
                setSize(SIZE_EDEFAULT);
                return;
            case RamPackage.RARRAY__TYPE:
                setType((ObjectType)null);
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
            case RamPackage.RARRAY__SIZE:
                return size != SIZE_EDEFAULT;
            case RamPackage.RARRAY__TYPE:
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
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (size: ");
        result.append(size);
        result.append(')');
        return result.toString();
    }

} //RArrayImpl
