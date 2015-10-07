/**
 */
package ca.mcgill.sel.core.provider;


import ca.mcgill.sel.core.COREFeatureImpactNode;
import ca.mcgill.sel.core.CorePackage;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link ca.mcgill.sel.core.COREFeatureImpactNode} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class COREFeatureImpactNodeItemProvider extends COREImpactNodeItemProvider {
    /**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public COREFeatureImpactNodeItemProvider(AdapterFactory adapterFactory) {
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

            addRelativeFeatureWeightPropertyDescriptor(object);
            addRepresentsPropertyDescriptor(object);
            addWeightedMappingsPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Relative Feature Weight feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addRelativeFeatureWeightPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_COREFeatureImpactNode_relativeFeatureWeight_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_COREFeatureImpactNode_relativeFeatureWeight_feature", "_UI_COREFeatureImpactNode_type"),
                 CorePackage.Literals.CORE_FEATURE_IMPACT_NODE__RELATIVE_FEATURE_WEIGHT,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Represents feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addRepresentsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_COREFeatureImpactNode_represents_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_COREFeatureImpactNode_represents_feature", "_UI_COREFeatureImpactNode_type"),
                 CorePackage.Literals.CORE_FEATURE_IMPACT_NODE__REPRESENTS,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Weighted Mappings feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addWeightedMappingsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_COREFeatureImpactNode_weightedMappings_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_COREFeatureImpactNode_weightedMappings_feature", "_UI_COREFeatureImpactNode_type"),
                 CorePackage.Literals.CORE_FEATURE_IMPACT_NODE__WEIGHTED_MAPPINGS,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null));
    }

    /**
     * This returns COREFeatureImpactNode.gif.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/COREFeatureImpactNode"));
    }

    /**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getText(Object object) {
        String label = ((COREFeatureImpactNode)object).getName();
        return label == null || label.length() == 0 ?
            getString("_UI_COREFeatureImpactNode_type") :
            getString("_UI_COREFeatureImpactNode_type") + " " + label;
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

        switch (notification.getFeatureID(COREFeatureImpactNode.class)) {
            case CorePackage.CORE_FEATURE_IMPACT_NODE__RELATIVE_FEATURE_WEIGHT:
            case CorePackage.CORE_FEATURE_IMPACT_NODE__WEIGHTED_MAPPINGS:
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

}
