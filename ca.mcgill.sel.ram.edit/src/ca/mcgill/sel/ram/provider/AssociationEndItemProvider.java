/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ca.mcgill.sel.ram.provider;


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

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.core.COREFeature;
import ca.mcgill.sel.core.COREModelReuse;
import ca.mcgill.sel.core.CoreFactory;
import ca.mcgill.sel.core.util.COREConfigurationUtil;
import ca.mcgill.sel.ram.AssociationEnd;
import ca.mcgill.sel.ram.RamPackage;
/**
 * This is the item provider adapter for a {@link ca.mcgill.sel.ram.AssociationEnd} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class AssociationEndItemProvider
    extends PropertyItemProvider {
    /**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AssociationEndItemProvider(AdapterFactory adapterFactory) {
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

            addNavigablePropertyDescriptor(object);
            addAssocPropertyDescriptor(object);
            addFeatureSelectionPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Navigable feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addNavigablePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_AssociationEnd_navigable_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_AssociationEnd_navigable_feature", "_UI_AssociationEnd_type"),
                 RamPackage.Literals.ASSOCIATION_END__NAVIGABLE,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
                 null,
                 null));
    }
    
    /**
     * This adds a property descriptor for the Assoc feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addAssocPropertyDescriptorGen(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_AssociationEnd_assoc_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_AssociationEnd_assoc_feature", "_UI_AssociationEnd_type"),
                 RamPackage.Literals.ASSOCIATION_END__ASSOC,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Assoc feature.
     * <!-- begin-user-doc -->
     * @param object the object to add a property descriptor for
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addAssocPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(
            new ItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_AssociationEnd_assoc_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_AssociationEnd_assoc_feature", 
                             "_UI_AssociationEnd_type"),
                 RamPackage.Literals.ASSOCIATION_END__ASSOC,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null) {

                @Override
                public Collection<?> getChoiceOfValues(Object object) {
                    return EMFEditUtil.filterChoiceOfValues(object, super.getChoiceOfValues(object));
                }
                
            });
    }
    
    /**
     * This adds a property descriptor for the Feature Selection feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addFeatureSelectionPropertyDescriptorGen(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_AssociationEnd_featureSelection_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_AssociationEnd_featureSelection_feature", "_UI_AssociationEnd_type"),
                 RamPackage.Literals.ASSOCIATION_END__FEATURE_SELECTION,
                 true,
                 false,
                 false,
                 null,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Feature Selection feature.
     * <!-- begin-user-doc -->
     * @param object the object to add a property descriptor for
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addFeatureSelectionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(
             // CHECKSTYLE:IGNORE AnonInnerLength: Okay here.
            new ItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_AssociationEnd_featureSelection_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_AssociationEnd_featureSelection_feature", 
                         "_UI_AssociationEnd_type"),
                 RamPackage.Literals.ASSOCIATION_END__FEATURE_SELECTION,
                 true,
                 false,
                 false,
                 null,
                 null,
                 null) {
                
                @Override
                public IItemLabelProvider getLabelProvider(Object object) {
                    return new IItemLabelProvider() {
                        @Override
                        public String getText(Object object) {
                            if (object != null) {
                                COREModelReuse modelReuse = (COREModelReuse) object;
                                StringBuffer result = new StringBuffer();
                                result.append(EMFEditUtil.getTypeName(modelReuse));
                                if (modelReuse.getReuse() != null 
                                        && modelReuse.getReuse().getSelectedConfiguration() != null) {
                                    result.append("{");
                                    for (COREFeature feature : COREConfigurationUtil.getSelectedLeaves(
                                            modelReuse.getReuse().getSelectedConfiguration())) {
                                        result.append(feature.getName().toLowerCase() + ", ");
                                    }
                                    result.replace(result.length() - 2, result.length() - 1, "}");
                                }
                                return result.toString();
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
            childrenFeatures.add(RamPackage.Literals.ASSOCIATION_END__FEATURE_SELECTION);
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
     * This returns AssociationEnd.gif.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/AssociationEnd"));
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

        switch (notification.getFeatureID(AssociationEnd.class)) {
            case RamPackage.ASSOCIATION_END__NAVIGABLE:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
                return;
            case RamPackage.ASSOCIATION_END__FEATURE_SELECTION:
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
                (RamPackage.Literals.ASSOCIATION_END__FEATURE_SELECTION,
                 CoreFactory.eINSTANCE.createCOREModelReuse()));
    }

}
