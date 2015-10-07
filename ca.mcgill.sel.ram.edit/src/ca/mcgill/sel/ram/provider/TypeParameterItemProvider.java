/**
 */
package ca.mcgill.sel.ram.provider;


import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.commons.emf.util.EMFModelUtil;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.Classifier;
import ca.mcgill.sel.ram.ObjectType;
import ca.mcgill.sel.ram.PrimitiveType;
import ca.mcgill.sel.ram.RCollection;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.Type;
import ca.mcgill.sel.ram.TypeParameter;
import ca.mcgill.sel.ram.provider.util.RAMEditUtil;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IViewerNotification;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link ca.mcgill.sel.ram.TypeParameter} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class TypeParameterItemProvider
    extends TypeItemProvider {
    
    class ChangeListener implements INotifyChangedListener {
        public void notifyChanged(Notification notification) {
            
            if (notification.getNotifier() != null &&  getTarget() != null) {
                Object element = ((IViewerNotification) notification).getElement();
                Type type = ((TypeParameter) getTarget()).getGenericType();
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
    public TypeParameterItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
        
        // The change listener is required to get notifications about referenced elements
        // and pass the notifications to its own listener.
        // I.e., The type parameters generic type could change.
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

            addGenericTypePropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }
    
    /**
     * This adds a property descriptor for the Generic Type feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addGenericTypePropertyDescriptorGen(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_TypeParameter_genericType_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_TypeParameter_genericType_feature", "_UI_TypeParameter_type"),
                 RamPackage.Literals.TYPE_PARAMETER__GENERIC_TYPE,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Generic Type feature.
     * <!-- begin-user-doc -->
     * @param object the object to add a property descriptor for
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addGenericTypePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(
            // CHECKSTYLE:IGNORE AnonInnerLength: Okay here.
            new ItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_TypeParameter_genericType_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_TypeParameter_genericType_feature",
                             // CHECKSTYLE:IGNORE MultipleStringLiterals: Outcome of code generator.
                             "_UI_TypeParameter_type"),
                 RamPackage.Literals.TYPE_PARAMETER__GENERIC_TYPE,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null) {
                
                @Override
                public Collection<?> getChoiceOfValues(Object object) {
                    TypeParameter typeParameter = (TypeParameter) object;
                    EObject aspect = EcoreUtil.getRootContainer(typeParameter);
                    
                    Collection<?> result = RAMEditUtil.filterExtendedChoiceOfValues((EObject) object, 
                            super.getChoiceOfValues(object));
                    
                    for (Iterator<?> iterator = result.iterator(); iterator.hasNext();) {
                        EObject type = (EObject) iterator.next();
                        
                        // null is also contained in the list
                        if (type != null) {
                            EObject objectContainer = EcoreUtil.getRootContainer(type);
                            
                            // Filter out type parameters that are not of the containing classifier.
                            if (type == typeParameter.eContainer()
                                    // Filter out everything that is not a valid type.
                                    || (type instanceof PrimitiveType && aspect != objectContainer)
                                    || !(type instanceof PrimitiveType 
                                            || type instanceof ObjectType
                                            || type instanceof TypeParameter)) {
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
                                
                                // Only do this for actual classifiers 
                                // that are classes in the structural view and not types.
                                // Also restrict it to the current aspect.
                                if (aspect != currentAspect && !(object instanceof PrimitiveType) 
                                        && !(object instanceof RCollection) && object instanceof Classifier) {
                                    
                                    StringBuffer result = new StringBuffer();
                                    
                                    result.append(EMFEditUtil.getTypeName(type));
                                    result.append(" ");
                                    result.append(EMFEditUtil.getTextFor(getAdapterFactory(), aspect));
                                    result.append(".");
                                    result.append(EMFEditUtil.getTextFor(getAdapterFactory(), type));
                                    
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
     * This returns TypeParameter.gif.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/TypeParameter"));
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
        TypeParameter typeParameter = (TypeParameter) object;
        
        String label = null;
        
        // Display the name of the generic type is set.
        // Otherwise just show the type parameter name.
        if (typeParameter.getGenericType() != null) {
            label = EMFEditUtil.getTextFor(getAdapterFactory(), typeParameter.getGenericType());
        } else {
            label = typeParameter.getName();
        }
        
        return label == null || label.length() == 0 
                ? getString("_UI_TypeParameter_type") 
                : getString("_UI_TypeParameter_type") + " " + label;
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

        switch (notification.getFeatureID(TypeParameter.class)) {
            case RamPackage.TYPE_PARAMETER__GENERIC_TYPE:
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
