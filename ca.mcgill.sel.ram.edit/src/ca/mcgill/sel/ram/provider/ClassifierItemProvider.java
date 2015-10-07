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
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IViewerNotification;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.commons.emf.util.EMFModelUtil;
import ca.mcgill.sel.ram.Class;
import ca.mcgill.sel.ram.Classifier;
import ca.mcgill.sel.ram.ImplementationClass;
import ca.mcgill.sel.ram.PrimitiveType;
import ca.mcgill.sel.ram.RamFactory;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.TypeParameter;

/**
 * This is the item provider adapter for a {@link ca.mcgill.sel.ram.Classifier} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ClassifierItemProvider
    extends ObjectTypeItemProvider {
    
    class ChangeListener implements INotifyChangedListener {
        public void notifyChanged(Notification notification) {
            
            if (notification.getNotifier() != null && getTarget() != null) {
                Object element = ((IViewerNotification) notification).getElement();
                Classifier classifier = (Classifier) getTarget();
                EObject notifier = (EObject) notification.getNotifier();
                
                if (element instanceof TypeParameter
                        && classifier == notifier.eContainer()) {
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
    public ClassifierItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
        
        // The change listener is required to get notifications about referenced elements
        // and pass the notifications to its own listener.
        // I.e., The type parameters name or generic type could change.
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

            addSuperTypesPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Super Types feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addSuperTypesPropertyDescriptorGen(Object object) {
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
     * This adds a property descriptor for the Super Types feature.
     * <!-- begin-user-doc -->
     * @param object the object to add a property descriptor for
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addSuperTypesPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(
            new ItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_Classifier_superTypes_feature"),
                getString("_UI_PropertyDescriptor_description", 
                            "_UI_Classifier_superTypes_feature", 
                            "_UI_Classifier_type"),
                RamPackage.Literals.CLASSIFIER__SUPER_TYPES,
                true,
                false,
                true,
                null,
                null,
                null) {

                @Override
                public Collection<?> getChoiceOfValues(Object object) {
                    Classifier classifier = (Classifier) object;
                    EObject structuralView = classifier.eContainer();
                    
                    Collection<?> result = super.getChoiceOfValues(object);
                    
                    for (Iterator<?> iterator = result.iterator(); iterator.hasNext();) {
                        EObject value = (EObject) iterator.next();
                        
                        if (value != null) {
                            EObject objectContainer = EMFModelUtil.getRootContainerOfType(value, 
                                    RamPackage.Literals.STRUCTURAL_VIEW);
                            
                            // If it is not from the current aspect it shouldn't be displayed. Also, avoid that an 
                            // ImplementationClass can have a super type that's not another ImplementationClass.
                            if (structuralView != objectContainer
                                    || classifier == value
                                    || value instanceof PrimitiveType
                                    || (classifier instanceof ImplementationClass && value instanceof Class)) {
                                iterator.remove();
                            }                                
                        }
                    }
                    
                    return result;
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
        Classifier classifier = (Classifier) object;
        String label = super.getText(object);
        
        if (classifier.getName() != null && classifier.getName().length() > 0) {
            if (!classifier.getTypeParameters().isEmpty()) {
                label += "<";
                
                for (int i = 0; i < classifier.getTypeParameters().size(); i++) {
                    if (i > 0) {
                        label += ", ";
                    }
                    
                    label += EMFEditUtil.getTextFor(getAdapterFactory(), classifier.getTypeParameters().get(i));
                }
                
                label += ">";
            }
        }
        
        return label;
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

        switch (notification.getFeatureID(Classifier.class)) {
            case RamPackage.CLASSIFIER__SUPER_TYPES:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
                return;
            case RamPackage.CLASSIFIER__OPERATIONS:
            case RamPackage.CLASSIFIER__ASSOCIATION_ENDS:
            case RamPackage.CLASSIFIER__TYPE_PARAMETERS:
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

}
