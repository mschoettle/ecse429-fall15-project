/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ca.mcgill.sel.ram.provider;


import ca.mcgill.sel.core.CoreFactory;
import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.RamFactory;
import ca.mcgill.sel.ram.RamPackage;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link ca.mcgill.sel.ram.Aspect} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class AspectItemProvider
    extends NamedElementItemProvider {
    /**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AspectItemProvider(AdapterFactory adapterFactory) {
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

            addModelElementsPropertyDescriptor(object);
            addRealizesPropertyDescriptor(object);
            addCoreConcernPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Model Elements feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addModelElementsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_COREModel_modelElements_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_COREModel_modelElements_feature", "_UI_COREModel_type"),
                 CorePackage.Literals.CORE_MODEL__MODEL_ELEMENTS,
                 false,
                 false,
                 false,
                 null,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Realizes feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addRealizesPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_COREModel_realizes_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_COREModel_realizes_feature", "_UI_COREModel_type"),
                 CorePackage.Literals.CORE_MODEL__REALIZES,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Core Concern feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addCoreConcernPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_COREModel_coreConcern_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_COREModel_coreConcern_feature", "_UI_COREModel_type"),
                 CorePackage.Literals.CORE_MODEL__CORE_CONCERN,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null));
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
            childrenFeatures.add(CorePackage.Literals.CORE_MODEL__MODEL_REUSES);
            childrenFeatures.add(RamPackage.Literals.ASPECT__STRUCTURAL_VIEW);
            childrenFeatures.add(RamPackage.Literals.ASPECT__MESSAGE_VIEWS);
            childrenFeatures.add(RamPackage.Literals.ASPECT__INSTANTIATIONS);
            childrenFeatures.add(RamPackage.Literals.ASPECT__LAYOUT);
            childrenFeatures.add(RamPackage.Literals.ASPECT__STATE_VIEWS);
            childrenFeatures.add(RamPackage.Literals.ASPECT__WOVEN_ASPECTS);
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
     * This returns Aspect.gif.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/Aspect"));
    }

    /**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getText(Object object) {
        String label = ((Aspect)object).getName();
        return label == null || label.length() == 0 ?
            getString("_UI_Aspect_type") :
            getString("_UI_Aspect_type") + " " + label;
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

        switch (notification.getFeatureID(Aspect.class)) {
            case RamPackage.ASPECT__CORE_CONCERN:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
                return;
            case RamPackage.ASPECT__MODEL_REUSES:
            case RamPackage.ASPECT__STRUCTURAL_VIEW:
            case RamPackage.ASPECT__MESSAGE_VIEWS:
            case RamPackage.ASPECT__INSTANTIATIONS:
            case RamPackage.ASPECT__LAYOUT:
            case RamPackage.ASPECT__STATE_VIEWS:
            case RamPackage.ASPECT__WOVEN_ASPECTS:
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
                (CorePackage.Literals.CORE_MODEL__MODEL_REUSES,
                 CoreFactory.eINSTANCE.createCOREModelReuse()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.ASPECT__STRUCTURAL_VIEW,
                 RamFactory.eINSTANCE.createStructuralView()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.ASPECT__MESSAGE_VIEWS,
                 RamFactory.eINSTANCE.createMessageView()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.ASPECT__MESSAGE_VIEWS,
                 RamFactory.eINSTANCE.createMessageViewReference()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.ASPECT__MESSAGE_VIEWS,
                 RamFactory.eINSTANCE.createAspectMessageView()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.ASPECT__INSTANTIATIONS,
                 RamFactory.eINSTANCE.createInstantiation()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.ASPECT__LAYOUT,
                 RamFactory.eINSTANCE.createLayout()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.ASPECT__STATE_VIEWS,
                 RamFactory.eINSTANCE.createStateView()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.ASPECT__WOVEN_ASPECTS,
                 RamFactory.eINSTANCE.createWovenAspect()));
    }

}
