/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ca.mcgill.sel.ram.provider;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

import ca.mcgill.sel.commons.emf.util.EMFModelUtil;
import ca.mcgill.sel.ram.FragmentContainer;
import ca.mcgill.sel.ram.Interaction;
import ca.mcgill.sel.ram.InteractionFragment;
import ca.mcgill.sel.ram.Lifeline;
import ca.mcgill.sel.ram.RamPackage;

/**
 * This is the item provider adapter for a {@link ca.mcgill.sel.ram.InteractionFragment} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class InteractionFragmentItemProvider
    extends ItemProviderAdapter
    implements
        IEditingDomainItemProvider,
        IStructuredItemContentProvider,
        ITreeItemContentProvider,
        IItemLabelProvider,
        IItemPropertySource {
    /**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public InteractionFragmentItemProvider(AdapterFactory adapterFactory) {
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

            addCoveredPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }
    
    /**
     * This adds a property descriptor for the Covered feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addCoveredPropertyDescriptorGen(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_InteractionFragment_covered_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_InteractionFragment_covered_feature", "_UI_InteractionFragment_type"),
                 RamPackage.Literals.INTERACTION_FRAGMENT__COVERED,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Covered feature.
     * <!-- begin-user-doc -->
     * @param object the object to add a property descriptor for
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addCoveredPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(
            new ItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_InteractionFragment_covered_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_InteractionFragment_covered_feature", 
                             "_UI_InteractionFragment_type"),
                 RamPackage.Literals.INTERACTION_FRAGMENT__COVERED,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null) {
                
                @Override
                public Collection<?> getChoiceOfValues(Object object) {
                    InteractionFragment interactionFragment = (InteractionFragment) object;
                    
                    // get the interaction enclosing this occurrence
                    Interaction interaction = EMFModelUtil.getRootContainerOfType(interactionFragment, 
                            RamPackage.Literals.INTERACTION);
                    
                    // only Lifelines from the same Interaction are allowed
                    // since it is a many feature no null value is needed in the result
                    Collection<EObject> result = new ArrayList<EObject>();
                    result.addAll(interaction.getLifelines());
                    
                    return result;
                }
                
            });
    }

    /**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
     * @param object the object a textual representation to get for
     * @return the textual representation for the given object
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public String getText(Object object) {
        InteractionFragment fragment = (InteractionFragment) object;
        
        // get the key name of this object for the translated string
        // by using the objects interface name
        String className = fragment.eClass().getName();
        String keyName = "_UI_" + className + "_type";
        
        if (fragment.getCovered().size() > 0) {
            // get the lifeline this InteractionFragment is covered on
            Lifeline lifeline = fragment.getCovered().get(0);
            
            // get the index in the ordered list of fragments (start from 1)
            int index = ((FragmentContainer) fragment.eContainer()).getFragments().indexOf(fragment) + 1;
            
            String label = (lifeline != null && lifeline.getRepresents() != null) 
                    ? " (on " + lifeline.getRepresents().getName() + ")" : "";
            
            return getString(keyName) + " #" + index + label;
        }
        
        return getString(keyName);
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

        switch (notification.getFeatureID(InteractionFragment.class)) {
            case RamPackage.INTERACTION_FRAGMENT__COVERED:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
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
    }

    /**
     * Return the resource locator for this item provider's resources.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ResourceLocator getResourceLocator() {
        return RAMEditPlugin.INSTANCE;
    }

}
