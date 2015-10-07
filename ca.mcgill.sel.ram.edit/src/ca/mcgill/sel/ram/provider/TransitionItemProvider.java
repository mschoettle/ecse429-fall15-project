/**
 */
package ca.mcgill.sel.ram.provider;


import ca.mcgill.sel.commons.emf.util.EMFModelUtil;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.Operation;
import ca.mcgill.sel.ram.RamFactory;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.StateView;
import ca.mcgill.sel.ram.Transition;
import ca.mcgill.sel.ram.provider.util.RAMEditUtil;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link ca.mcgill.sel.ram.Transition} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class TransitionItemProvider
    extends NamedElementItemProvider {
    /**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TransitionItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /**
     * This returns the property descriptors for the adapted class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
        if (itemPropertyDescriptors == null) {
            super.getPropertyDescriptors(object);

            addEndStatePropertyDescriptor(object);
            addStartStatePropertyDescriptor(object);
            addSignaturePropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the End State feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addEndStatePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Transition_endState_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_Transition_endState_feature", "_UI_Transition_type"),
                 RamPackage.Literals.TRANSITION__END_STATE,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Start State feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addStartStatePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Transition_startState_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_Transition_startState_feature", "_UI_Transition_type"),
                 RamPackage.Literals.TRANSITION__START_STATE,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null));
    }
    
    /**
     * This adds a property descriptor for the Signature feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addSignaturePropertyDescriptorGen(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Transition_signature_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_Transition_signature_feature", "_UI_Transition_type"),
                 RamPackage.Literals.TRANSITION__SIGNATURE,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Signature feature.
     * <!-- begin-user-doc -->
     * @param object the object to add a property descriptor for
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addSignaturePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(
            // CHECKSTYLE:IGNORE AnonInnerLength: Unfortunately necessary here.
            new ItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Transition_signature_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_Transition_signature_feature",
                        "_UI_Transition_type"),
                 RamPackage.Literals.TRANSITION__SIGNATURE,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null) {
                
                @Override
                public Collection<?> getChoiceOfValues(Object object) {
                    Transition transition = (Transition) object;
                    StateView stateView = EMFModelUtil.getRootContainerOfType(transition, 
                                                RamPackage.Literals.STATE_VIEW);
                    Aspect aspect = EMFModelUtil.getRootContainerOfType(stateView, RamPackage.Literals.ASPECT);
                    
                    Collection<?> result = super.getChoiceOfValues(object);
                    RAMEditUtil.filterCallableOperations(result, aspect, stateView.getSpecifies());
                    
                    return result;
                }
                
                @Override
                public IItemLabelProvider getLabelProvider(Object object) {
                    return new IItemLabelProvider() {
                        
                        @Override
                        public String getText(Object object) {
                            if (object instanceof Operation) {
                                return RAMEditUtil.getOperationSignature(getAdapterFactory(), (Operation) object, true);
                            }
                            
                            return itemDelegator.getText(object);
                        }
                        
                        @Override
                        public Object getImage(Object object) {
                            return itemDelegator.getImage(object);
                        }
                    };
                }
                
            });
    }

    /**
     * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
     * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
     * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
        if (childrenFeatures == null) {
            super.getChildrenFeatures(object);
            childrenFeatures.add(RamPackage.Literals.TRANSITION__GUARD);
        }
        return childrenFeatures;
    }

				/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
        // Check the type of the specified child object and return the proper feature to use for
        // adding (see {@link AddCommand}) it as a child.

        return super.getChildFeature(object, child);
    }

				/**
     * This returns Transition.gif.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/Transition"));
    }

    /**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getText(Object object) {
        String label = ((Transition)object).getName();
        return label == null || label.length() == 0 ?
            getString("_UI_Transition_type") :
            getString("_UI_Transition_type") + " " + label;
    }

    /**
     * This handles model notifications by calling {@link #updateChildren} to update any cached
     * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void notifyChanged(Notification notification) {
        updateChildren(notification);

        switch (notification.getFeatureID(Transition.class)) {
            case RamPackage.TRANSITION__GUARD:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
                return;
        }
        super.notifyChanged(notification);
    }

    /**
     * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
     * that can be created under this object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.TRANSITION__GUARD,
                 RamFactory.eINSTANCE.createConstraint()));
    }

}
