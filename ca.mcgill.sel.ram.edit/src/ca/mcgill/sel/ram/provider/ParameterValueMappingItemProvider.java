/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ca.mcgill.sel.ram.provider;


import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.ram.Message;
import ca.mcgill.sel.ram.Operation;
import ca.mcgill.sel.ram.ParameterValueMapping;
import ca.mcgill.sel.ram.RamFactory;
import ca.mcgill.sel.ram.RamPackage;

/**
 * This is the item provider adapter for a {@link ca.mcgill.sel.ram.ParameterValueMapping} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ParameterValueMappingItemProvider
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
    public ParameterValueMappingItemProvider(AdapterFactory adapterFactory) {
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

            addParameterPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Parameter feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addParameterPropertyDescriptorGen(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_ParameterValueMapping_parameter_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_ParameterValueMapping_parameter_feature", "_UI_ParameterValueMapping_type"),
                 RamPackage.Literals.PARAMETER_VALUE_MAPPING__PARAMETER,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null));
    }
    
    /**
     * This adds a property descriptor for the Parameter feature.
     * <!-- begin-user-doc -->
     * @param object the object to add a property descriptor for
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addParameterPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(
            new ItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_ParameterValueMapping_parameter_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_ParameterValueMapping_parameter_feature",
                             // CHECKSTYLE:IGNORE MultipleStringLiterals: Outcome of code generator.
                             "_UI_ParameterValueMapping_type"),
                 RamPackage.Literals.PARAMETER_VALUE_MAPPING__PARAMETER,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null) {

                @Override
                public Collection<?> getChoiceOfValues(Object object) {
                    ParameterValueMapping mapping = (ParameterValueMapping) object;
                    Operation operation = ((Message) mapping.eContainer()).getSignature();
                    
                    if (operation == null) {
                        return Collections.singleton(null);
                    }
                    
                    // filter out all parameters that are not from the messages signature
                    Collection<?> result = super.getChoiceOfValues(object);
                    
                    for (Iterator<?> iterator = result.iterator(); iterator.hasNext();) {
                        Object next = iterator.next();
                        
                        if (next != null && !operation.getParameters().contains(next)) {
                            iterator.remove();
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
            childrenFeatures.add(RamPackage.Literals.PARAMETER_VALUE_MAPPING__VALUE);
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
     * This returns ParameterValueMapping.gif.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/ParameterValueMapping"));
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
        ParameterValueMapping parameterMapping = (ParameterValueMapping) object;
        String label = null;
        
        if (parameterMapping.getParameter() != null) {
            label = EMFEditUtil.getTextFor(getAdapterFactory(), parameterMapping.getParameter().getType());
            label += " ";
            label += EMFEditUtil.getTextFor(getAdapterFactory(), parameterMapping.getParameter());
        }
        
        return label == null ? getString("_UI_ParameterValueMapping_type")
                : getString("_UI_ParameterValueMapping_type") + " " + label;
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

        switch (notification.getFeatureID(ParameterValueMapping.class)) {
            case RamPackage.PARAMETER_VALUE_MAPPING__VALUE:
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
                (RamPackage.Literals.PARAMETER_VALUE_MAPPING__VALUE,
                 RamFactory.eINSTANCE.createStructuralFeatureValue()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.PARAMETER_VALUE_MAPPING__VALUE,
                 RamFactory.eINSTANCE.createParameterValue()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.PARAMETER_VALUE_MAPPING__VALUE,
                 RamFactory.eINSTANCE.createOpaqueExpression()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.PARAMETER_VALUE_MAPPING__VALUE,
                 RamFactory.eINSTANCE.createLiteralString()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.PARAMETER_VALUE_MAPPING__VALUE,
                 RamFactory.eINSTANCE.createLiteralInteger()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.PARAMETER_VALUE_MAPPING__VALUE,
                 RamFactory.eINSTANCE.createLiteralBoolean()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.PARAMETER_VALUE_MAPPING__VALUE,
                 RamFactory.eINSTANCE.createLiteralNull()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.PARAMETER_VALUE_MAPPING__VALUE,
                 RamFactory.eINSTANCE.createEnumLiteralValue()));
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
