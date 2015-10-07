/**
 */
package ca.mcgill.sel.ram.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import ca.mcgill.sel.ram.AssociationEnd;
import ca.mcgill.sel.ram.Classifier;
import ca.mcgill.sel.ram.Operation;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.TypeParameter;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Classifier</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ca.mcgill.sel.ram.impl.ClassifierImpl#getOperations <em>Operations</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.impl.ClassifierImpl#getAssociationEnds <em>Association Ends</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.impl.ClassifierImpl#getTypeParameters <em>Type Parameters</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.impl.ClassifierImpl#getSuperTypes <em>Super Types</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ClassifierImpl extends ObjectTypeImpl implements Classifier {
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
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ClassifierImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return RamPackage.Literals.CLASSIFIER;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Operation> getOperations() {
        if (operations == null) {
            operations = new EObjectContainmentEList<Operation>(Operation.class, this, RamPackage.CLASSIFIER__OPERATIONS);
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
            associationEnds = new EObjectContainmentWithInverseEList<AssociationEnd>(AssociationEnd.class, this, RamPackage.CLASSIFIER__ASSOCIATION_ENDS, RamPackage.ASSOCIATION_END__CLASSIFIER);
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
            typeParameters = new EObjectContainmentEList<TypeParameter>(TypeParameter.class, this, RamPackage.CLASSIFIER__TYPE_PARAMETERS);
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
            superTypes = new EObjectResolvingEList<Classifier>(Classifier.class, this, RamPackage.CLASSIFIER__SUPER_TYPES);
        }
        return superTypes;
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
            case RamPackage.CLASSIFIER__ASSOCIATION_ENDS:
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
            case RamPackage.CLASSIFIER__OPERATIONS:
                return ((InternalEList<?>)getOperations()).basicRemove(otherEnd, msgs);
            case RamPackage.CLASSIFIER__ASSOCIATION_ENDS:
                return ((InternalEList<?>)getAssociationEnds()).basicRemove(otherEnd, msgs);
            case RamPackage.CLASSIFIER__TYPE_PARAMETERS:
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
            case RamPackage.CLASSIFIER__OPERATIONS:
                return getOperations();
            case RamPackage.CLASSIFIER__ASSOCIATION_ENDS:
                return getAssociationEnds();
            case RamPackage.CLASSIFIER__TYPE_PARAMETERS:
                return getTypeParameters();
            case RamPackage.CLASSIFIER__SUPER_TYPES:
                return getSuperTypes();
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
            case RamPackage.CLASSIFIER__OPERATIONS:
                getOperations().clear();
                getOperations().addAll((Collection<? extends Operation>)newValue);
                return;
            case RamPackage.CLASSIFIER__ASSOCIATION_ENDS:
                getAssociationEnds().clear();
                getAssociationEnds().addAll((Collection<? extends AssociationEnd>)newValue);
                return;
            case RamPackage.CLASSIFIER__TYPE_PARAMETERS:
                getTypeParameters().clear();
                getTypeParameters().addAll((Collection<? extends TypeParameter>)newValue);
                return;
            case RamPackage.CLASSIFIER__SUPER_TYPES:
                getSuperTypes().clear();
                getSuperTypes().addAll((Collection<? extends Classifier>)newValue);
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
            case RamPackage.CLASSIFIER__OPERATIONS:
                getOperations().clear();
                return;
            case RamPackage.CLASSIFIER__ASSOCIATION_ENDS:
                getAssociationEnds().clear();
                return;
            case RamPackage.CLASSIFIER__TYPE_PARAMETERS:
                getTypeParameters().clear();
                return;
            case RamPackage.CLASSIFIER__SUPER_TYPES:
                getSuperTypes().clear();
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
            case RamPackage.CLASSIFIER__OPERATIONS:
                return operations != null && !operations.isEmpty();
            case RamPackage.CLASSIFIER__ASSOCIATION_ENDS:
                return associationEnds != null && !associationEnds.isEmpty();
            case RamPackage.CLASSIFIER__TYPE_PARAMETERS:
                return typeParameters != null && !typeParameters.isEmpty();
            case RamPackage.CLASSIFIER__SUPER_TYPES:
                return superTypes != null && !superTypes.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //ClassifierImpl
