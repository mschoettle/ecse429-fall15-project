/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ca.mcgill.sel.ram.provider;


import ca.mcgill.sel.core.CorePackage;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IViewerNotification;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import ca.mcgill.sel.ram.RCollection;
import ca.mcgill.sel.ram.RamFactory;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.Type;

/**
 * This is the item provider adapter for a {@link ca.mcgill.sel.ram.RCollection} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class RCollectionItemProvider
    extends TypeItemProvider {
    
    class ChangeListener implements INotifyChangedListener {
        public void notifyChanged(Notification notification) {
            
            if (notification.getNotifier() != null &&  getTarget() != null) {
                Object element = ((IViewerNotification) notification).getElement();
                Type type = ((RCollection) getTarget()).getType();
                Object notifier = notification.getNotifier();
                
                if (element instanceof Type && notifier == type) {
                    ((IChangeNotifier) getAdapterFactory()).removeListener(this);
                    fireNotifyChanged(new ViewerNotification(notification, getTarget(), false, true));
                    ((IChangeNotifier) getAdapterFactory()).addListener(this);                
                }
                
            }
        }
    }
    
    private ChangeListener changeListener;
    
    /**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc -->
     * @param adapterFactory the adapter factory for this item provider
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public RCollectionItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
        
        // The change listener is required to get notifications about referenced elements
        // and pass the notifications to its own listener.
        // I.e., The collections type could change.
        if (adapterFactory instanceof IChangeNotifier) {
            IChangeNotifier changeNotifier = (IChangeNotifier) adapterFactory;
            changeListener = new ChangeListener();
            changeNotifier.addListener(changeListener);
        }
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

            addPartialityPropertyDescriptor(object);
            addVisibilityPropertyDescriptor(object);
            addSuperTypesPropertyDescriptor(object);
            addInstanceClassNamePropertyDescriptor(object);
            addInterfacePropertyDescriptor(object);
            addTypePropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Partiality feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addPartialityPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_COREModelElement_partiality_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_COREModelElement_partiality_feature", "_UI_COREModelElement_type"),
                 CorePackage.Literals.CORE_MODEL_ELEMENT__PARTIALITY,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Visibility feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addVisibilityPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_COREModelElement_visibility_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_COREModelElement_visibility_feature", "_UI_COREModelElement_type"),
                 CorePackage.Literals.CORE_MODEL_ELEMENT__VISIBILITY,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Super Types feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addSuperTypesPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Classifier_superTypes_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_Classifier_superTypes_feature", "_UI_Classifier_type"),
                 RamPackage.Literals.CLASSIFIER__SUPER_TYPES,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Instance Class Name feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addInstanceClassNamePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_ImplementationClass_instanceClassName_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_ImplementationClass_instanceClassName_feature", "_UI_ImplementationClass_type"),
                 RamPackage.Literals.IMPLEMENTATION_CLASS__INSTANCE_CLASS_NAME,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Interface feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addInterfacePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_ImplementationClass_interface_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_ImplementationClass_interface_feature", "_UI_ImplementationClass_type"),
                 RamPackage.Literals.IMPLEMENTATION_CLASS__INTERFACE,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Type feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addTypePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_RCollection_type_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_RCollection_type_feature", "_UI_RCollection_type"),
                 RamPackage.Literals.RCOLLECTION__TYPE,
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
            childrenFeatures.add(RamPackage.Literals.CLASSIFIER__OPERATIONS);
            childrenFeatures.add(RamPackage.Literals.CLASSIFIER__ASSOCIATION_ENDS);
            childrenFeatures.add(RamPackage.Literals.CLASSIFIER__TYPE_PARAMETERS);
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
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
     * @param object the object a textual representation to get for
     * @return the textual representation for the given object
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public String getText(Object object) {
        return super.getText(object);
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

        switch (notification.getFeatureID(RCollection.class)) {
            case RamPackage.RCOLLECTION__PARTIALITY:
            case RamPackage.RCOLLECTION__VISIBILITY:
            case RamPackage.RCOLLECTION__SUPER_TYPES:
            case RamPackage.RCOLLECTION__INSTANCE_CLASS_NAME:
            case RamPackage.RCOLLECTION__INTERFACE:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
                return;
            case RamPackage.RCOLLECTION__OPERATIONS:
            case RamPackage.RCOLLECTION__ASSOCIATION_ENDS:
            case RamPackage.RCOLLECTION__TYPE_PARAMETERS:
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
                (RamPackage.Literals.CLASSIFIER__OPERATIONS,
                 RamFactory.eINSTANCE.createOperation()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.CLASSIFIER__ASSOCIATION_ENDS,
                 RamFactory.eINSTANCE.createAssociationEnd()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.CLASSIFIER__TYPE_PARAMETERS,
                 RamFactory.eINSTANCE.createTypeParameter()));
    }
    
    @Override
    public void dispose() {
        super.dispose();
        
        if (changeListener != null) {
            ((IChangeNotifier) getAdapterFactory()).removeListener(changeListener);
        }
    }

}
