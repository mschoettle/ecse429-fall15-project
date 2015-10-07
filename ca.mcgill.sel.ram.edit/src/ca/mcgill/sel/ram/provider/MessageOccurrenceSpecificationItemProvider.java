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
import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;

import ca.mcgill.sel.commons.emf.util.EMFModelUtil;
import ca.mcgill.sel.ram.Interaction;
import ca.mcgill.sel.ram.MessageEnd;
import ca.mcgill.sel.ram.RamPackage;

/**
 * This is the item provider adapter for a {@link ca.mcgill.sel.ram.MessageOccurrenceSpecification} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class MessageOccurrenceSpecificationItemProvider
    extends OccurrenceSpecificationItemProvider {
    /**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MessageOccurrenceSpecificationItemProvider(AdapterFactory adapterFactory) {
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

            addMessagePropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Message feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addMessagePropertyDescriptorGen(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_MessageEnd_message_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_MessageEnd_message_feature", "_UI_MessageEnd_type"),
                 RamPackage.Literals.MESSAGE_END__MESSAGE,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null));
    }
    
    /**
     * This adds a property descriptor for the Message feature.
     * <!-- begin-user-doc -->
     * @param object the object to add a property descriptor for
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addMessagePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(
            new ItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_MessageEnd_message_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_MessageEnd_message_feature", 
                             "_UI_MessageEnd_type"),
                 RamPackage.Literals.MESSAGE_END__MESSAGE,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null) {
                
                @Override
                public Collection<?> getChoiceOfValues(Object object) {
                    // TODO: avoid code duplication from MessageEndItemProvider
                    MessageEnd messageEnd = (MessageEnd) object;
                    Interaction interaction = EMFModelUtil.getRootContainerOfType(messageEnd, 
                            RamPackage.Literals.INTERACTION);
                    
                    // Filter out all Messages that are not from the current Interaction
                    // null will be also contained
                    // TODO: maybe filter out all messages where send or receive event is already set and not this
                    Collection<?> result = super.getChoiceOfValues(object);
                    
                    for (Iterator<?> iterator = result.iterator(); iterator.hasNext();) {
                        Object next = iterator.next();
                        
                        if (next != null && !interaction.getMessages().contains(next)) {
                            iterator.remove();
                        }
                    }
                    
                    return result;
                }
                
            });
    }

    /**
     * This returns MessageOccurrenceSpecification.gif.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/MessageOccurrenceSpecification"));
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
    
    public Collection<? extends EObject> convert(
            Collection<? extends EObject> eObjects,
            Map<String, String> options, DiagnosticChain diagnostics,
            Map<Object, Object> context) {
        Collection<EPackage> ePackages = null;
        ePackages = EcoreUtil.getObjectsByType(eObjects,
            EcorePackage.Literals.EPACKAGE);
        
        return ePackages;
    }

}
