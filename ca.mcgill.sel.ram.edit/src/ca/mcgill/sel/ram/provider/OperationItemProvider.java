/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ca.mcgill.sel.ram.provider;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.commons.emf.util.EMFModelUtil;
import ca.mcgill.sel.core.COREPartialityType;
import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.Classifier;
import ca.mcgill.sel.ram.ImplementationClass;
import ca.mcgill.sel.ram.ObjectType;
import ca.mcgill.sel.ram.Operation;
import ca.mcgill.sel.ram.PrimitiveType;
import ca.mcgill.sel.ram.RCollection;
import ca.mcgill.sel.ram.REnum;
import ca.mcgill.sel.ram.RamFactory;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.Type;
import ca.mcgill.sel.ram.TypeParameter;
import ca.mcgill.sel.ram.provider.util.RAMEditUtil;

/**
 * This is the item provider adapter for a {@link ca.mcgill.sel.ram.Operation} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class OperationItemProvider
    extends NamedElementItemProvider {
    /**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public OperationItemProvider(AdapterFactory adapterFactory) {
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

            addPartialityPropertyDescriptor(object);
            addVisibilityPropertyDescriptor(object);
            addAbstractPropertyDescriptor(object);
            addExtendedVisibilityPropertyDescriptor(object);
            addReturnTypePropertyDescriptor(object);
            addStaticPropertyDescriptor(object);
            addOperationTypePropertyDescriptor(object);
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
     * This adds a property descriptor for the Abstract feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addAbstractPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Operation_abstract_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_Operation_abstract_feature", "_UI_Operation_type"),
                 RamPackage.Literals.OPERATION__ABSTRACT,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Extended Visibility feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addExtendedVisibilityPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Operation_extendedVisibility_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_Operation_extendedVisibility_feature", "_UI_Operation_type"),
                 RamPackage.Literals.OPERATION__EXTENDED_VISIBILITY,
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
     * This adds a property descriptor for the Return Type feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addReturnTypePropertyDescriptorGen(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Operation_returnType_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_Operation_returnType_feature", "_UI_Operation_type"),
                 RamPackage.Literals.OPERATION__RETURN_TYPE,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Return Type feature.
     * <!-- begin-user-doc -->
     * @param object the object to add a property descriptor for
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addReturnTypePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(
            // CHECKSTYLE:IGNORE AnonInnerLength: Okay here.
            new ItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Operation_returnType_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_Operation_returnType_feature", 
                             "_UI_Operation_type"),
                 RamPackage.Literals.OPERATION__RETURN_TYPE,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null) {

                @Override
                public Collection<?> getChoiceOfValues(Object object) {
                    Operation operation = (Operation) object;
                    // Set the current object as the target in order for the label provider to use.
                    // Since the item providers are state-less, 
                    // the target could be any object of the loaded models.
                    setTarget(operation);
                    Aspect aspect = EMFModelUtil.getRootContainerOfType(operation, RamPackage.Literals.ASPECT);
                    
                    Collection<?> choiceOfValues = super.getChoiceOfValues(operation);
                    Collection<?> result = RAMEditUtil.filterExtendedChoiceOfValues(operation, choiceOfValues);
                    
                    for (Iterator<?> iterator = result.iterator(); iterator.hasNext();) {
                        EObject value = (EObject) iterator.next();
                        
                        // null is also contained in the list
                        if (value != null) {
                            EObject objectContainer = EcoreUtil.getRootContainer(value);
                            
                            // Filter out all types that are not part of the current aspect.
                            // Allow enums but not any other primitive types.
                            if ((value instanceof PrimitiveType
                                    && !(value instanceof REnum)
                                    && aspect != objectContainer)
                                    // Filter out type parameters that are not of the containing classifier.
                                    || (value instanceof TypeParameter && value.eContainer() 
                                            != operation.eContainer())
                                    // Filter out all types that are not primitive or object types 
                                    // and not from this aspect.
                                    || value instanceof Type 
                                            && !(value instanceof PrimitiveType) 
                                            && !(value instanceof ObjectType) 
                                            &&  aspect != objectContainer) {
                                iterator.remove();
                            }                             
                        }
                    }
                    
                    return result;
                }
                    
                @Override
                public IItemLabelProvider getLabelProvider(Object object) {
                    // CHECKSTYLE:IGNORE AnonInnerLength: Okay here.
                    return new IItemLabelProvider() {
                        
                        @Override
                        public String getText(Object object) {
                            if (object != null) {
                                Type type = (Type) object;
                                // The target is one of the operations in the aspect.
                                // It is not necessarily the operation we are currently looking at,
                                // but regardless it allows us to retrieve the current aspect.
                                EObject target = (EObject) getTarget();
                                Aspect currentAspect = EMFModelUtil.getRootContainerOfType(target, 
                                        RamPackage.Literals.ASPECT);
                                Aspect aspect = EMFModelUtil.getRootContainerOfType(type, RamPackage.Literals.ASPECT);
                                
                                // Only do this for types not from the current aspect.
                                if (aspect != currentAspect
                                        /**
                                         * It is possible that on removal of an operation, 
                                         * the target is not contained anymore,
                                         * i.e., the currentAspect (container) is null, 
                                         * which would lead to the prepending of the external aspect, 
                                         * even though this cannot be guaranteed. Hence, it needs to be prevented.
                                         */
                                        && currentAspect != null
                                        && !(object instanceof RCollection) && object instanceof Classifier) {
                                    
                                    StringBuffer result = new StringBuffer();
                                    
                                    result.append(EMFEditUtil.getTypeName(type));
                                    result.append(" ");
                                    
                                    if (!(type instanceof ImplementationClass)) {
                                        result.append(EMFEditUtil.getTextFor(getAdapterFactory(), aspect));
                                        result.append(".");
                                    }
                                    
                                    result.append(RAMEditUtil.getTypeName(getAdapterFactory(), 
                                                    currentAspect, type));
                                    
                                    return result.toString();
                                }
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
     * This adds a property descriptor for the Static feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addStaticPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Operation_static_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_Operation_static_feature", "_UI_Operation_type"),
                 RamPackage.Literals.OPERATION__STATIC,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Operation Type feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addOperationTypePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Operation_operationType_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_Operation_operationType_feature", "_UI_Operation_type"),
                 RamPackage.Literals.OPERATION__OPERATION_TYPE,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
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
            childrenFeatures.add(RamPackage.Literals.OPERATION__PARAMETERS);
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
     * This returns Operation.gif.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/Operation"));
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
        Operation operation = (Operation) object;
        
        String label = operation.getName();
        
        if (operation.getPartiality() == COREPartialityType.PUBLIC) {
            label = "|" + label;
        } else if (operation.getPartiality() == COREPartialityType.CONCERN) {
            label = "\u00A6" + label;
        }
        
        return label == null || label.length() == 0 
                ? getString("_UI_Operation_type") 
                : getString("_UI_Operation_type") + " " + label;
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

        switch (notification.getFeatureID(Operation.class)) {
            case RamPackage.OPERATION__PARTIALITY:
            case RamPackage.OPERATION__VISIBILITY:
            case RamPackage.OPERATION__ABSTRACT:
            case RamPackage.OPERATION__EXTENDED_VISIBILITY:
            case RamPackage.OPERATION__RETURN_TYPE:
            case RamPackage.OPERATION__STATIC:
            case RamPackage.OPERATION__OPERATION_TYPE:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
                return;
            case RamPackage.OPERATION__PARAMETERS:
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
                (RamPackage.Literals.OPERATION__PARAMETERS,
                 RamFactory.eINSTANCE.createParameter()));
    }

}
