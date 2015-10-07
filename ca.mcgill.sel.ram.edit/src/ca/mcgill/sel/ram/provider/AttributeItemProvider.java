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
import ca.mcgill.sel.ram.Attribute;
import ca.mcgill.sel.ram.PrimitiveType;
import ca.mcgill.sel.ram.RArray;
import ca.mcgill.sel.ram.REnum;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.Type;
import ca.mcgill.sel.ram.provider.util.RAMEditUtil;

/**
 * This is the item provider adapter for a {@link ca.mcgill.sel.ram.Attribute} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class AttributeItemProvider
        extends StructuralFeatureItemProvider {
    /**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public AttributeItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /**
     * This returns the property descriptors for the adapted class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
        if (itemPropertyDescriptors == null) {
            super.getPropertyDescriptors(object);

            addPartialityPropertyDescriptor(object);
            addVisibilityPropertyDescriptor(object);
            addTypePropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Partiality feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addPartialityPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(),
                        getString("_UI_COREModelElement_partiality_feature"),
                        getString("_UI_PropertyDescriptor_description", "_UI_COREModelElement_partiality_feature",
                                "_UI_COREModelElement_type"),
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
     * 
     * @generated
     */
    protected void addVisibilityPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(),
                        getString("_UI_COREModelElement_visibility_feature"),
                        getString("_UI_PropertyDescriptor_description", "_UI_COREModelElement_visibility_feature",
                                "_UI_COREModelElement_type"),
                        CorePackage.Literals.CORE_MODEL_ELEMENT__VISIBILITY,
                        true,
                        false,
                        false,
                        ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                        null,
                        null));
    }

    /**
     * This adds a property descriptor for the Type feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addTypePropertyDescriptorGen(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(),
                        getString("_UI_Attribute_type_feature"),
                        getString("_UI_PropertyDescriptor_description", "_UI_Attribute_type_feature",
                                "_UI_Attribute_type"),
                        RamPackage.Literals.ATTRIBUTE__TYPE,
                        true,
                        false,
                        true,
                        null,
                        null,
                        null));
    }

    /**
     * This adds a property descriptor for the Type feature.
     * <!-- begin-user-doc -->
     * 
     * @param object the object to add a property descriptor for
     *            <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addTypePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(
                // CHECKSTYLE:IGNORE AnonInnerLength: Okay here.
                new ItemPropertyDescriptor(
                        ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(),
                        getString("_UI_Attribute_type_feature"),
                        getString("_UI_PropertyDescriptor_description", "_UI_Attribute_type_feature",
                                "_UI_Attribute_type"),
                        RamPackage.Literals.ATTRIBUTE__TYPE,
                        true,
                        false,
                        true,
                        null,
                        null,
                        null) {

                    @Override
                    public Collection<?> getChoiceOfValues(Object object) {
                        Attribute attribute = (Attribute) object;
                        // Set the current object as the target in order for the label provider to use.
                        // Since the item providers are state-less,
                        // the target could be any object of the loaded models.
                        setTarget(attribute);
                        Aspect aspect = EMFModelUtil.getRootContainerOfType(attribute, RamPackage.Literals.ASPECT);

                        Collection<?> choiceOfValues = super.getChoiceOfValues(attribute);
                        Collection<?> result = RAMEditUtil.filterExtendedChoiceOfValues(attribute, choiceOfValues);

                        for (Iterator<?> iterator = result.iterator(); iterator.hasNext();) {
                            EObject value = (EObject) iterator.next();

                            // null is also contained in the list
                            if (value != null) {
                                EObject objectContainer = EcoreUtil.getRootContainer(value);

                                // Filter out all types that are not part of the current aspect.
                                // Allow enums but not any other primitive types.
                                // Also filter out arrays of a non primitive type type,
                                // attributes may only have arrays of primitive types.
                                if ((value instanceof RArray
                                        && objectContainer == aspect
                                        && !(((RArray) value).getType() instanceof PrimitiveType))
                                        || (!(value instanceof REnum) && aspect != objectContainer)) {
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
                                    // The target is one of the parameters in the aspect.
                                    // It is not necessarily the parameter we are currently looking at,
                                    // but regardless it allows us to retrieve the current aspect.
                                    EObject target = (EObject) getTarget();
                                    Aspect currentAspect = EMFModelUtil.getRootContainerOfType(target,
                                            RamPackage.Literals.ASPECT);
                                    Aspect aspect =
                                            EMFModelUtil.getRootContainerOfType(type, RamPackage.Literals.ASPECT);

                                    // Only do this for types not from the current aspect.
                                    if (aspect != currentAspect) {

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
     * This returns Attribute.gif.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/Attribute"));
    }

    /**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
     * 
     * @param object the object a textual representation to get for
     * @return the textual representation for the given object
     *         <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public String getText(Object object) {
        Attribute attribute = (Attribute) object;
        String result = attribute.getName();
        if (attribute.getPartiality() == COREPartialityType.PUBLIC) {
            result = "|" + result;
        } else if (attribute.getPartiality() == COREPartialityType.CONCERN) {
            result = "\u00A6" + result;
        }
        return result == null || result.length() == 0
                ? getString("_UI_Attribute_type")
                : getString("_UI_Attribute_type") + " " + result;
    }

    /**
     * This handles model notifications by calling {@link #updateChildren} to update any cached
     * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void notifyChanged(Notification notification) {
        updateChildren(notification);

        switch (notification.getFeatureID(Attribute.class)) {
            case RamPackage.ATTRIBUTE__PARTIALITY:
            case RamPackage.ATTRIBUTE__VISIBILITY:
            case RamPackage.ATTRIBUTE__TYPE:
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
     * 
     * @generated
     */
    @Override
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);
    }

}
