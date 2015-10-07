/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ca.mcgill.sel.ram.provider;


import ca.mcgill.sel.ram.RamFactory;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.StructuralView;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link ca.mcgill.sel.ram.StructuralView} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class StructuralViewItemProvider
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
    public StructuralViewItemProvider(AdapterFactory adapterFactory) {
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
            childrenFeatures.add(RamPackage.Literals.STRUCTURAL_VIEW__CLASSES);
            childrenFeatures.add(RamPackage.Literals.STRUCTURAL_VIEW__ASSOCIATIONS);
            childrenFeatures.add(RamPackage.Literals.STRUCTURAL_VIEW__TYPES);
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
     * This returns StructuralView.gif.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/StructuralView"));
    }

    /**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getText(Object object) {
        return getString("_UI_StructuralView_type");
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

        switch (notification.getFeatureID(StructuralView.class)) {
            case RamPackage.STRUCTURAL_VIEW__CLASSES:
            case RamPackage.STRUCTURAL_VIEW__ASSOCIATIONS:
            case RamPackage.STRUCTURAL_VIEW__TYPES:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
                return;
        }
        super.notifyChanged(notification);
    }
    
    /**
     * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
     * that can be created under this object.
     * <!-- begin-user-doc -->
     * @param newChildDescriptors the collection of new child descriptors
     * @param object the object to add a property descriptor for
     * <!-- end-user-doc -->
     * @generated
     */
    protected void collectNewChildDescriptorsGen(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.STRUCTURAL_VIEW__CLASSES,
                 RamFactory.eINSTANCE.createClass()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.STRUCTURAL_VIEW__CLASSES,
                 RamFactory.eINSTANCE.createRBoolean()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.STRUCTURAL_VIEW__CLASSES,
                 RamFactory.eINSTANCE.createRInt()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.STRUCTURAL_VIEW__CLASSES,
                 RamFactory.eINSTANCE.createRChar()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.STRUCTURAL_VIEW__CLASSES,
                 RamFactory.eINSTANCE.createRString()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.STRUCTURAL_VIEW__CLASSES,
                 RamFactory.eINSTANCE.createREnum()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.STRUCTURAL_VIEW__CLASSES,
                 RamFactory.eINSTANCE.createRSet()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.STRUCTURAL_VIEW__CLASSES,
                 RamFactory.eINSTANCE.createRSequence()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.STRUCTURAL_VIEW__CLASSES,
                 RamFactory.eINSTANCE.createImplementationClass()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.STRUCTURAL_VIEW__CLASSES,
                 RamFactory.eINSTANCE.createRDouble()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.STRUCTURAL_VIEW__CLASSES,
                 RamFactory.eINSTANCE.createRFloat()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.STRUCTURAL_VIEW__CLASSES,
                 RamFactory.eINSTANCE.createRLong()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.STRUCTURAL_VIEW__CLASSES,
                 RamFactory.eINSTANCE.createRArray()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.STRUCTURAL_VIEW__ASSOCIATIONS,
                 RamFactory.eINSTANCE.createAssociation()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.STRUCTURAL_VIEW__TYPES,
                 RamFactory.eINSTANCE.createClass()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.STRUCTURAL_VIEW__TYPES,
                 RamFactory.eINSTANCE.createRVoid()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.STRUCTURAL_VIEW__TYPES,
                 RamFactory.eINSTANCE.createRBoolean()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.STRUCTURAL_VIEW__TYPES,
                 RamFactory.eINSTANCE.createRInt()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.STRUCTURAL_VIEW__TYPES,
                 RamFactory.eINSTANCE.createRChar()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.STRUCTURAL_VIEW__TYPES,
                 RamFactory.eINSTANCE.createRString()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.STRUCTURAL_VIEW__TYPES,
                 RamFactory.eINSTANCE.createRAny()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.STRUCTURAL_VIEW__TYPES,
                 RamFactory.eINSTANCE.createREnum()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.STRUCTURAL_VIEW__TYPES,
                 RamFactory.eINSTANCE.createRSet()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.STRUCTURAL_VIEW__TYPES,
                 RamFactory.eINSTANCE.createRSequence()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.STRUCTURAL_VIEW__TYPES,
                 RamFactory.eINSTANCE.createImplementationClass()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.STRUCTURAL_VIEW__TYPES,
                 RamFactory.eINSTANCE.createRDouble()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.STRUCTURAL_VIEW__TYPES,
                 RamFactory.eINSTANCE.createRFloat()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.STRUCTURAL_VIEW__TYPES,
                 RamFactory.eINSTANCE.createTypeParameter()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.STRUCTURAL_VIEW__TYPES,
                 RamFactory.eINSTANCE.createRLong()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.STRUCTURAL_VIEW__TYPES,
                 RamFactory.eINSTANCE.createRArray()));
    }

    /**
     * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
     * that can be created under this object.
     * <!-- begin-user-doc -->
     * @param newChildDescriptors the collection of new child descriptors
     * @param object the object to add a property descriptor for
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);

        newChildDescriptors.add(
            createChildParameter(
                RamPackage.Literals.STRUCTURAL_VIEW__CLASSES,
                 RamFactory.eINSTANCE.createClass()));
        
        newChildDescriptors.add(
            createChildParameter(
                RamPackage.Literals.STRUCTURAL_VIEW__CLASSES,
                 RamFactory.eINSTANCE.createImplementationClass()));

        newChildDescriptors.add(
            createChildParameter(
                RamPackage.Literals.STRUCTURAL_VIEW__ASSOCIATIONS,
                 RamFactory.eINSTANCE.createAssociation()));

        newChildDescriptors.add(
            createChildParameter(
                RamPackage.Literals.STRUCTURAL_VIEW__TYPES,
                 RamFactory.eINSTANCE.createRVoid()));

        newChildDescriptors.add(
            createChildParameter(
                RamPackage.Literals.STRUCTURAL_VIEW__TYPES,
                 RamFactory.eINSTANCE.createRBoolean()));

        newChildDescriptors.add(
            createChildParameter(
                RamPackage.Literals.STRUCTURAL_VIEW__TYPES,
                 RamFactory.eINSTANCE.createRInt()));

        newChildDescriptors.add(
            createChildParameter(
                RamPackage.Literals.STRUCTURAL_VIEW__TYPES,
                 RamFactory.eINSTANCE.createRChar()));

        newChildDescriptors.add(
            createChildParameter(
                RamPackage.Literals.STRUCTURAL_VIEW__TYPES,
                 RamFactory.eINSTANCE.createRString()));

        newChildDescriptors.add(
            createChildParameter(
                RamPackage.Literals.STRUCTURAL_VIEW__TYPES,
                 RamFactory.eINSTANCE.createRAny()));

        newChildDescriptors.add(
            createChildParameter(
                RamPackage.Literals.STRUCTURAL_VIEW__TYPES,
                 RamFactory.eINSTANCE.createREnum()));

        newChildDescriptors.add(
            createChildParameter(
                RamPackage.Literals.STRUCTURAL_VIEW__TYPES,
                 RamFactory.eINSTANCE.createRSet()));

        newChildDescriptors.add(
            createChildParameter(
                RamPackage.Literals.STRUCTURAL_VIEW__TYPES,
                 RamFactory.eINSTANCE.createRSequence()));
        
        newChildDescriptors.add(
            createChildParameter(
                RamPackage.Literals.STRUCTURAL_VIEW__TYPES,
                 RamFactory.eINSTANCE.createRDouble()));
        
        newChildDescriptors.add(
            createChildParameter(
                RamPackage.Literals.STRUCTURAL_VIEW__TYPES,
                 RamFactory.eINSTANCE.createRFloat()));
        
        newChildDescriptors.add(
                createChildParameter(
                    RamPackage.Literals.STRUCTURAL_VIEW__TYPES,
                     RamFactory.eINSTANCE.createRLong()));
        
        newChildDescriptors.add(
                createChildParameter(
                    RamPackage.Literals.STRUCTURAL_VIEW__TYPES,
                     RamFactory.eINSTANCE.createRArray()));
    }

    /**
     * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
        Object childFeature = feature;
        Object childObject = child;

        boolean qualify =
            childFeature == RamPackage.Literals.STRUCTURAL_VIEW__CLASSES ||
            childFeature == RamPackage.Literals.STRUCTURAL_VIEW__TYPES;

        if (qualify) {
            return getString
                ("_UI_CreateChild_text2",
                 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
        }
        return super.getCreateChildText(owner, feature, child, selection);
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
