/**
 */
package ca.mcgill.sel.ram.impl;

import ca.mcgill.sel.core.COREConcern;
import ca.mcgill.sel.core.COREFeature;
import ca.mcgill.sel.core.COREModel;
import ca.mcgill.sel.core.COREModelElement;
import ca.mcgill.sel.core.COREModelReuse;
import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.ram.AbstractMessageView;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.Instantiation;
import ca.mcgill.sel.ram.Layout;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.StateView;
import ca.mcgill.sel.ram.StructuralView;
import ca.mcgill.sel.ram.WovenAspect;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Aspect</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ca.mcgill.sel.ram.impl.AspectImpl#getModelReuses <em>Model Reuses</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.impl.AspectImpl#getModelElements <em>Model Elements</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.impl.AspectImpl#getRealizes <em>Realizes</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.impl.AspectImpl#getCoreConcern <em>Core Concern</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.impl.AspectImpl#getStructuralView <em>Structural View</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.impl.AspectImpl#getMessageViews <em>Message Views</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.impl.AspectImpl#getInstantiations <em>Instantiations</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.impl.AspectImpl#getLayout <em>Layout</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.impl.AspectImpl#getStateViews <em>State Views</em>}</li>
 *   <li>{@link ca.mcgill.sel.ram.impl.AspectImpl#getWovenAspects <em>Woven Aspects</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AspectImpl extends NamedElementImpl implements Aspect {
    /**
     * The cached value of the '{@link #getModelReuses() <em>Model Reuses</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getModelReuses()
     * @generated
     * @ordered
     */
    protected EList<COREModelReuse> modelReuses;

    /**
     * The cached value of the '{@link #getModelElements() <em>Model Elements</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getModelElements()
     * @generated
     * @ordered
     */
    protected EList<COREModelElement> modelElements;

    /**
     * The cached value of the '{@link #getRealizes() <em>Realizes</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRealizes()
     * @generated
     * @ordered
     */
    protected EList<COREFeature> realizes;

    /**
     * The cached value of the '{@link #getCoreConcern() <em>Core Concern</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCoreConcern()
     * @generated
     * @ordered
     */
    protected COREConcern coreConcern;

    /**
     * The cached value of the '{@link #getStructuralView() <em>Structural View</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStructuralView()
     * @generated
     * @ordered
     */
    protected StructuralView structuralView;

    /**
     * The cached value of the '{@link #getMessageViews() <em>Message Views</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMessageViews()
     * @generated
     * @ordered
     */
    protected EList<AbstractMessageView> messageViews;

    /**
     * The cached value of the '{@link #getInstantiations() <em>Instantiations</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getInstantiations()
     * @generated
     * @ordered
     */
    protected EList<Instantiation> instantiations;

    /**
     * The cached value of the '{@link #getLayout() <em>Layout</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLayout()
     * @generated
     * @ordered
     */
    protected Layout layout;

    /**
     * The cached value of the '{@link #getStateViews() <em>State Views</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStateViews()
     * @generated
     * @ordered
     */
    protected EList<StateView> stateViews;

    /**
     * The cached value of the '{@link #getWovenAspects() <em>Woven Aspects</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getWovenAspects()
     * @generated
     * @ordered
     */
    protected EList<WovenAspect> wovenAspects;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected AspectImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return RamPackage.Literals.ASPECT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<COREModelReuse> getModelReuses() {
        if (modelReuses == null) {
            modelReuses = new EObjectContainmentEList<COREModelReuse>(COREModelReuse.class, this, RamPackage.ASPECT__MODEL_REUSES);
        }
        return modelReuses;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<COREModelElement> getModelElements() {
        if (modelElements == null) {
            modelElements = new EObjectResolvingEList<COREModelElement>(COREModelElement.class, this, RamPackage.ASPECT__MODEL_ELEMENTS);
        }
        return modelElements;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<COREFeature> getRealizes() {
        if (realizes == null) {
            realizes = new EObjectWithInverseResolvingEList.ManyInverse<COREFeature>(COREFeature.class, this, RamPackage.ASPECT__REALIZES, CorePackage.CORE_FEATURE__REALIZED_BY);
        }
        return realizes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public COREConcern getCoreConcern() {
        if (coreConcern != null && coreConcern.eIsProxy()) {
            InternalEObject oldCoreConcern = (InternalEObject)coreConcern;
            coreConcern = (COREConcern)eResolveProxy(oldCoreConcern);
            if (coreConcern != oldCoreConcern) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, RamPackage.ASPECT__CORE_CONCERN, oldCoreConcern, coreConcern));
            }
        }
        return coreConcern;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public COREConcern basicGetCoreConcern() {
        return coreConcern;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetCoreConcern(COREConcern newCoreConcern, NotificationChain msgs) {
        COREConcern oldCoreConcern = coreConcern;
        coreConcern = newCoreConcern;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RamPackage.ASPECT__CORE_CONCERN, oldCoreConcern, newCoreConcern);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCoreConcern(COREConcern newCoreConcern) {
        if (newCoreConcern != coreConcern) {
            NotificationChain msgs = null;
            if (coreConcern != null)
                msgs = ((InternalEObject)coreConcern).eInverseRemove(this, CorePackage.CORE_CONCERN__MODELS, COREConcern.class, msgs);
            if (newCoreConcern != null)
                msgs = ((InternalEObject)newCoreConcern).eInverseAdd(this, CorePackage.CORE_CONCERN__MODELS, COREConcern.class, msgs);
            msgs = basicSetCoreConcern(newCoreConcern, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RamPackage.ASPECT__CORE_CONCERN, newCoreConcern, newCoreConcern));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public StructuralView getStructuralView() {
        return structuralView;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetStructuralView(StructuralView newStructuralView, NotificationChain msgs) {
        StructuralView oldStructuralView = structuralView;
        structuralView = newStructuralView;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RamPackage.ASPECT__STRUCTURAL_VIEW, oldStructuralView, newStructuralView);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setStructuralView(StructuralView newStructuralView) {
        if (newStructuralView != structuralView) {
            NotificationChain msgs = null;
            if (structuralView != null)
                msgs = ((InternalEObject)structuralView).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RamPackage.ASPECT__STRUCTURAL_VIEW, null, msgs);
            if (newStructuralView != null)
                msgs = ((InternalEObject)newStructuralView).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RamPackage.ASPECT__STRUCTURAL_VIEW, null, msgs);
            msgs = basicSetStructuralView(newStructuralView, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RamPackage.ASPECT__STRUCTURAL_VIEW, newStructuralView, newStructuralView));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<AbstractMessageView> getMessageViews() {
        if (messageViews == null) {
            messageViews = new EObjectContainmentEList<AbstractMessageView>(AbstractMessageView.class, this, RamPackage.ASPECT__MESSAGE_VIEWS);
        }
        return messageViews;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Instantiation> getInstantiations() {
        if (instantiations == null) {
            instantiations = new EObjectContainmentEList<Instantiation>(Instantiation.class, this, RamPackage.ASPECT__INSTANTIATIONS);
        }
        return instantiations;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Layout getLayout() {
        return layout;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetLayout(Layout newLayout, NotificationChain msgs) {
        Layout oldLayout = layout;
        layout = newLayout;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RamPackage.ASPECT__LAYOUT, oldLayout, newLayout);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLayout(Layout newLayout) {
        if (newLayout != layout) {
            NotificationChain msgs = null;
            if (layout != null)
                msgs = ((InternalEObject)layout).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RamPackage.ASPECT__LAYOUT, null, msgs);
            if (newLayout != null)
                msgs = ((InternalEObject)newLayout).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RamPackage.ASPECT__LAYOUT, null, msgs);
            msgs = basicSetLayout(newLayout, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RamPackage.ASPECT__LAYOUT, newLayout, newLayout));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<StateView> getStateViews() {
        if (stateViews == null) {
            stateViews = new EObjectContainmentEList<StateView>(StateView.class, this, RamPackage.ASPECT__STATE_VIEWS);
        }
        return stateViews;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<WovenAspect> getWovenAspects() {
        if (wovenAspects == null) {
            wovenAspects = new EObjectContainmentEList<WovenAspect>(WovenAspect.class, this, RamPackage.ASPECT__WOVEN_ASPECTS);
        }
        return wovenAspects;
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
            case RamPackage.ASPECT__REALIZES:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getRealizes()).basicAdd(otherEnd, msgs);
            case RamPackage.ASPECT__CORE_CONCERN:
                if (coreConcern != null)
                    msgs = ((InternalEObject)coreConcern).eInverseRemove(this, CorePackage.CORE_CONCERN__MODELS, COREConcern.class, msgs);
                return basicSetCoreConcern((COREConcern)otherEnd, msgs);
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
            case RamPackage.ASPECT__MODEL_REUSES:
                return ((InternalEList<?>)getModelReuses()).basicRemove(otherEnd, msgs);
            case RamPackage.ASPECT__REALIZES:
                return ((InternalEList<?>)getRealizes()).basicRemove(otherEnd, msgs);
            case RamPackage.ASPECT__CORE_CONCERN:
                return basicSetCoreConcern(null, msgs);
            case RamPackage.ASPECT__STRUCTURAL_VIEW:
                return basicSetStructuralView(null, msgs);
            case RamPackage.ASPECT__MESSAGE_VIEWS:
                return ((InternalEList<?>)getMessageViews()).basicRemove(otherEnd, msgs);
            case RamPackage.ASPECT__INSTANTIATIONS:
                return ((InternalEList<?>)getInstantiations()).basicRemove(otherEnd, msgs);
            case RamPackage.ASPECT__LAYOUT:
                return basicSetLayout(null, msgs);
            case RamPackage.ASPECT__STATE_VIEWS:
                return ((InternalEList<?>)getStateViews()).basicRemove(otherEnd, msgs);
            case RamPackage.ASPECT__WOVEN_ASPECTS:
                return ((InternalEList<?>)getWovenAspects()).basicRemove(otherEnd, msgs);
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
            case RamPackage.ASPECT__MODEL_REUSES:
                return getModelReuses();
            case RamPackage.ASPECT__MODEL_ELEMENTS:
                return getModelElements();
            case RamPackage.ASPECT__REALIZES:
                return getRealizes();
            case RamPackage.ASPECT__CORE_CONCERN:
                if (resolve) return getCoreConcern();
                return basicGetCoreConcern();
            case RamPackage.ASPECT__STRUCTURAL_VIEW:
                return getStructuralView();
            case RamPackage.ASPECT__MESSAGE_VIEWS:
                return getMessageViews();
            case RamPackage.ASPECT__INSTANTIATIONS:
                return getInstantiations();
            case RamPackage.ASPECT__LAYOUT:
                return getLayout();
            case RamPackage.ASPECT__STATE_VIEWS:
                return getStateViews();
            case RamPackage.ASPECT__WOVEN_ASPECTS:
                return getWovenAspects();
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
            case RamPackage.ASPECT__MODEL_REUSES:
                getModelReuses().clear();
                getModelReuses().addAll((Collection<? extends COREModelReuse>)newValue);
                return;
            case RamPackage.ASPECT__REALIZES:
                getRealizes().clear();
                getRealizes().addAll((Collection<? extends COREFeature>)newValue);
                return;
            case RamPackage.ASPECT__CORE_CONCERN:
                setCoreConcern((COREConcern)newValue);
                return;
            case RamPackage.ASPECT__STRUCTURAL_VIEW:
                setStructuralView((StructuralView)newValue);
                return;
            case RamPackage.ASPECT__MESSAGE_VIEWS:
                getMessageViews().clear();
                getMessageViews().addAll((Collection<? extends AbstractMessageView>)newValue);
                return;
            case RamPackage.ASPECT__INSTANTIATIONS:
                getInstantiations().clear();
                getInstantiations().addAll((Collection<? extends Instantiation>)newValue);
                return;
            case RamPackage.ASPECT__LAYOUT:
                setLayout((Layout)newValue);
                return;
            case RamPackage.ASPECT__STATE_VIEWS:
                getStateViews().clear();
                getStateViews().addAll((Collection<? extends StateView>)newValue);
                return;
            case RamPackage.ASPECT__WOVEN_ASPECTS:
                getWovenAspects().clear();
                getWovenAspects().addAll((Collection<? extends WovenAspect>)newValue);
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
            case RamPackage.ASPECT__MODEL_REUSES:
                getModelReuses().clear();
                return;
            case RamPackage.ASPECT__REALIZES:
                getRealizes().clear();
                return;
            case RamPackage.ASPECT__CORE_CONCERN:
                setCoreConcern((COREConcern)null);
                return;
            case RamPackage.ASPECT__STRUCTURAL_VIEW:
                setStructuralView((StructuralView)null);
                return;
            case RamPackage.ASPECT__MESSAGE_VIEWS:
                getMessageViews().clear();
                return;
            case RamPackage.ASPECT__INSTANTIATIONS:
                getInstantiations().clear();
                return;
            case RamPackage.ASPECT__LAYOUT:
                setLayout((Layout)null);
                return;
            case RamPackage.ASPECT__STATE_VIEWS:
                getStateViews().clear();
                return;
            case RamPackage.ASPECT__WOVEN_ASPECTS:
                getWovenAspects().clear();
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
            case RamPackage.ASPECT__MODEL_REUSES:
                return modelReuses != null && !modelReuses.isEmpty();
            case RamPackage.ASPECT__MODEL_ELEMENTS:
                return modelElements != null && !modelElements.isEmpty();
            case RamPackage.ASPECT__REALIZES:
                return realizes != null && !realizes.isEmpty();
            case RamPackage.ASPECT__CORE_CONCERN:
                return coreConcern != null;
            case RamPackage.ASPECT__STRUCTURAL_VIEW:
                return structuralView != null;
            case RamPackage.ASPECT__MESSAGE_VIEWS:
                return messageViews != null && !messageViews.isEmpty();
            case RamPackage.ASPECT__INSTANTIATIONS:
                return instantiations != null && !instantiations.isEmpty();
            case RamPackage.ASPECT__LAYOUT:
                return layout != null;
            case RamPackage.ASPECT__STATE_VIEWS:
                return stateViews != null && !stateViews.isEmpty();
            case RamPackage.ASPECT__WOVEN_ASPECTS:
                return wovenAspects != null && !wovenAspects.isEmpty();
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
        if (baseClass == COREModel.class) {
            switch (derivedFeatureID) {
                case RamPackage.ASPECT__MODEL_REUSES: return CorePackage.CORE_MODEL__MODEL_REUSES;
                case RamPackage.ASPECT__MODEL_ELEMENTS: return CorePackage.CORE_MODEL__MODEL_ELEMENTS;
                case RamPackage.ASPECT__REALIZES: return CorePackage.CORE_MODEL__REALIZES;
                case RamPackage.ASPECT__CORE_CONCERN: return CorePackage.CORE_MODEL__CORE_CONCERN;
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
        if (baseClass == COREModel.class) {
            switch (baseFeatureID) {
                case CorePackage.CORE_MODEL__MODEL_REUSES: return RamPackage.ASPECT__MODEL_REUSES;
                case CorePackage.CORE_MODEL__MODEL_ELEMENTS: return RamPackage.ASPECT__MODEL_ELEMENTS;
                case CorePackage.CORE_MODEL__REALIZES: return RamPackage.ASPECT__REALIZES;
                case CorePackage.CORE_MODEL__CORE_CONCERN: return RamPackage.ASPECT__CORE_CONCERN;
                default: return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
    }

} //AspectImpl
