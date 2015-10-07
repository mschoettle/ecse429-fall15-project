/**
 */
package ca.mcgill.sel.ram.provider;


import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import ca.mcgill.sel.core.COREMapping;
import ca.mcgill.sel.core.COREPartialityType;
import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.core.provider.COREMappingItemProvider;
import ca.mcgill.sel.ram.Classifier;
import ca.mcgill.sel.ram.ClassifierMapping;
import ca.mcgill.sel.ram.Instantiation;
import ca.mcgill.sel.ram.Operation;
import ca.mcgill.sel.ram.OperationMapping;
import ca.mcgill.sel.ram.RamFactory;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.provider.util.MappingToLabelProvider;
import ca.mcgill.sel.ram.provider.util.RAMEditUtil;
import ca.mcgill.sel.ram.util.RAMModelUtil;

/**
 * This is the item provider adapter for a {@link ca.mcgill.sel.ram.OperationMapping} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class OperationMappingItemProvider
    extends COREMappingItemProvider {
    /**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public OperationMappingItemProvider(AdapterFactory adapterFactory) {
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
     * This adds a property descriptor for the From feature.
     * <!-- begin-user-doc -->
     * @param object the object to add a property descriptor for
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    protected void addFromPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(
            // CHECKSTYLE:IGNORE AnonInnerLength: Okay here.
            new MappingFromItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
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
                public Collection<?> getChoiceOfValues(Object object) {
                    @SuppressWarnings("unchecked")
                    Collection<EObject> result = (Collection<EObject>) super.getChoiceOfValues(object);
                    
                    // Add all concern partial operations from higher-level extended aspects to allow mapping them.
                    OperationMapping mapping = (OperationMapping) object;
                    ClassifierMapping parent = (ClassifierMapping) mapping.eContainer();
                    Instantiation currentInstantiation = (Instantiation) mapping.eContainer().eContainer();
                    
                    Set<Classifier> classifiers = 
                            RAMModelUtil.collectClassifiersFor(currentInstantiation.getSource(), parent.getFrom());
                    classifiers.remove(parent.getFrom());

                    for (Classifier classifier : classifiers) {
                        for (Operation operation : classifier.getOperations()) {
                            if (operation.getPartiality() == COREPartialityType.CONCERN) {
                                result.add(operation);
                            }
                        }
                    }
                    
                    return result;
                }
                
                @Override
                public IItemLabelProvider getLabelProvider(Object object) {
                    return new IItemLabelProvider() {
                        
                        @Override
                        public String getText(Object object) {
                            if (object instanceof Operation) {
                                Operation operation = (Operation) object;
                                return RAMEditUtil.getOperationSignature(getAdapterFactory(), operation, false);
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
            childrenFeatures.add(RamPackage.Literals.OPERATION_MAPPING__PARAMETER_MAPPINGS);
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
     * This returns OperationMapping.gif.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/OperationMapping"));
    }

    /**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getText(Object object) {
        return getString("_UI_OperationMapping_type");
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

        switch (notification.getFeatureID(OperationMapping.class)) {
            case RamPackage.OPERATION_MAPPING__PARAMETER_MAPPINGS:
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
                (RamPackage.Literals.OPERATION_MAPPING__PARAMETER_MAPPINGS,
                 RamFactory.eINSTANCE.createParameterMapping()));
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
