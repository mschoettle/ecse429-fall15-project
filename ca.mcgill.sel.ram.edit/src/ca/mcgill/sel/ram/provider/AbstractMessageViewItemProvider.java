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
import java.util.Set;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
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

import ca.mcgill.sel.ram.AbstractMessageView;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.AspectMessageView;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.util.RAMModelUtil;

/**
 * This is the item provider adapter for a {@link ca.mcgill.sel.ram.AbstractMessageView} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class AbstractMessageViewItemProvider
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
    public AbstractMessageViewItemProvider(AdapterFactory adapterFactory) {
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

            addAffectedByPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }
    
    /**
     * This adds a property descriptor for the Affected By feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addAffectedByPropertyDescriptorGen(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_AbstractMessageView_affectedBy_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_AbstractMessageView_affectedBy_feature", "_UI_AbstractMessageView_type"),
                 RamPackage.Literals.ABSTRACT_MESSAGE_VIEW__AFFECTED_BY,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Affected By feature.
     * <!-- begin-user-doc -->
     * @param object the object to add a property descriptor for
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addAffectedByPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(
            new ItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_AbstractMessageView_affectedBy_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_AbstractMessageView_affectedBy_feature", 
                             "_UI_AbstractMessageView_type"),
                 RamPackage.Literals.ABSTRACT_MESSAGE_VIEW__AFFECTED_BY,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null) {

                @Override
                public Collection<?> getChoiceOfValues(Object object) {
                    AbstractMessageView messageView = (AbstractMessageView) object;
                    Aspect aspect = (Aspect) messageView.eContainer();
                    Set<Aspect> aspects = RAMModelUtil.collectExtendedAspects(aspect);
                    aspects.add(aspect);
                    
                    // since it is a many feature no null value is needed in the result
                    Collection<EObject> result = new ArrayList<EObject>();
                    
                    for (Aspect currentAspect : aspects) {
                        Collection<AspectMessageView> aspectMessageViews = 
                                EcoreUtil.getObjectsByType(currentAspect.getMessageViews(), 
                                                RamPackage.Literals.ASPECT_MESSAGE_VIEW);
                        result.addAll(aspectMessageViews);
                    }
                    
                    return result;
                }
                
            });
    }

    /**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getText(Object object) {
        return getString("_UI_AbstractMessageView_type");
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

        switch (notification.getFeatureID(AbstractMessageView.class)) {
            case RamPackage.ABSTRACT_MESSAGE_VIEW__AFFECTED_BY:
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
