/**
 */
package ca.mcgill.sel.ram.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import ca.mcgill.sel.commons.emf.util.EMFModelUtil;
import ca.mcgill.sel.core.COREMapping;
import ca.mcgill.sel.core.COREModel;
import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.core.provider.COREMappingItemProvider;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.Class;
import ca.mcgill.sel.ram.ClassifierMapping;
import ca.mcgill.sel.ram.ImplementationClass;
import ca.mcgill.sel.ram.REnum;
import ca.mcgill.sel.ram.RamFactory;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.provider.util.MappingToLabelProvider;
import ca.mcgill.sel.ram.provider.util.RAMEditUtil;
import ca.mcgill.sel.ram.util.RAMModelUtil;

/**
 * This is the item provider adapter for a {@link ca.mcgill.sel.ram.ClassifierMapping} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ClassifierMappingItemProvider
    extends COREMappingItemProvider {
    /**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ClassifierMappingItemProvider(AdapterFactory adapterFactory) {
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
     * This adds a property descriptor for the From Element feature.
     * <!-- begin-user-doc -->
     * @param object the object to add a property descriptor for
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addFromPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(
            new MappingFromItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 // CHECKSTYLE:IGNORE MultipleStringLiterals: Outcome of code generator.
                 getString("_UI_COREMapping_from_feature"),
                 getString("_UI_PropertyDescriptor_description", 
                             "_UI_COREMapping_from_feature", "_UI_COREMapping_type"),
                 CorePackage.Literals.CORE_MAPPING__FROM,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null) {
                
                @Override
                protected boolean shouldFilterValue(COREModel externalModel, COREMapping<?> mapping, EObject object) {
                    return object instanceof ImplementationClass;
                }
                
            });
    }

    /**
     * This adds a property descriptor for the To Element feature.
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
                 getString("_UI_PropertyDescriptor_description", 
                             "_UI_COREMapping_to_feature", "_UI_COREMapping_type"),
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
                protected boolean shouldFilterValue(COREModel model, COREMapping<?> mapping, EObject object) {
                    Aspect container = EMFModelUtil.getRootContainerOfType(object, RamPackage.Literals.ASPECT);
                    
                    return object instanceof REnum 
                            || (model != container 
                                    && container.getStructuralView().getTypes().contains(object)
                            || (object instanceof ImplementationClass
                                    && !RAMModelUtil.isClassEmpty((Class) mapping.getFrom())));
                }
                
                @Override
                public IItemLabelProvider getLabelProvider(Object object) {
                    return new MappingToLabelProvider(getAdapterFactory(), itemDelegator);
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
            childrenFeatures.add(RamPackage.Literals.CLASSIFIER_MAPPING__OPERATION_MAPPINGS);
            childrenFeatures.add(RamPackage.Literals.CLASSIFIER_MAPPING__ATTRIBUTE_MAPPINGS);
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
     * This returns ClassifierMapping.gif.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/ClassifierMapping"));
    }

    /**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getText(Object object) {
        return getString("_UI_ClassifierMapping_type");
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

        switch (notification.getFeatureID(ClassifierMapping.class)) {
            case RamPackage.CLASSIFIER_MAPPING__OPERATION_MAPPINGS:
            case RamPackage.CLASSIFIER_MAPPING__ATTRIBUTE_MAPPINGS:
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
                (RamPackage.Literals.CLASSIFIER_MAPPING__OPERATION_MAPPINGS,
                 RamFactory.eINSTANCE.createOperationMapping()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.CLASSIFIER_MAPPING__ATTRIBUTE_MAPPINGS,
                 RamFactory.eINSTANCE.createAttributeMapping()));
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
