/**
 */
package ca.mcgill.sel.ram.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;

import ca.mcgill.sel.core.COREMapping;
import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.core.provider.COREMappingItemProvider;
import ca.mcgill.sel.ram.provider.util.MappingToLabelProvider;
import ca.mcgill.sel.ram.provider.util.RAMEditUtil;

/**
 * This is the item provider adapter for a {@link ca.mcgill.sel.ram.ParameterMapping} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ParameterMappingItemProvider
    extends COREMappingItemProvider {
    /**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ParameterMappingItemProvider(AdapterFactory adapterFactory) {
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

        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the To feature.
     * <!-- begin-user-doc -->
     * @param object the object to add a property descriptor for
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addToPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(
            new MappingToItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_COREMapping_to_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_COREMapping_to_feature", "_UI_COREMapping_type"),
                 CorePackage.Literals.CORE_MAPPING__TO,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null) {
                
                @Override
                protected Collection<?> extendChoiceOfValues(COREMapping<?> mapping, Collection<?> choiceOfValues) {
                    return RAMEditUtil.filterExtendedChoiceOfValues(mapping, choiceOfValues);
                }
                
                @Override
                public IItemLabelProvider getLabelProvider(Object object) {
                    return new MappingToLabelProvider(getAdapterFactory(), itemDelegator);
                }
            });
    }

    /**
     * This returns ParameterMapping.gif.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/ParameterMapping"));
    }

    /**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getText(Object object) {
        return getString("_UI_ParameterMapping_type");
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
