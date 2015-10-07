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
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IViewerNotification;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.Class;
import ca.mcgill.sel.ram.MessageView;
import ca.mcgill.sel.ram.MessageViewReference;
import ca.mcgill.sel.ram.NamedElement;
import ca.mcgill.sel.ram.Operation;
import ca.mcgill.sel.ram.Parameter;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.TypeParameter;

/**
 * This is the item provider adapter for a {@link ca.mcgill.sel.ram.MessageViewReference} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class MessageViewReferenceItemProvider
    extends AbstractMessageViewItemProvider {
    
    class ChangeListener implements INotifyChangedListener {
        public void notifyChanged(Notification notification) {
            
            if (notification.getNotifier() != null &&  getTarget() != null) {
                Object element = ((IViewerNotification) notification).getElement();
                MessageView messageView = ((MessageViewReference) getTarget()).getReferences();
                Object notifier = notification.getNotifier();
                
                if ((element instanceof Class || element instanceof Operation || element instanceof Parameter
                        || element instanceof TypeParameter)
                        && messageView != null
                        && (notifier == messageView.eContainer()
                                || notifier == messageView.getSpecifies().eContainer()
                                || notifier == messageView.getSpecifies()
                                || messageView.getSpecifies().getParameters().contains(notification.getNotifier())
                                // Check for TypeParameter as notifier (container of it and the operation must match)
                                || ((EObject) notifier).eContainer() == messageView.getSpecifies().eContainer()
                                // If TypeParameter is changed and the Classifier is a parameter somewhere, an update is required.
                                || notification.getNotifier() instanceof TypeParameter && !messageView.getSpecifies().getParameters().isEmpty())) {
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
    public MessageViewReferenceItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
        
        // The change listener is required to get notifications about referenced elements
        // and pass the notifications to its own listener.
        // I.e., The message views specified operation signature has types which could change.
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

            addReferencesPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }
    
    /**
     * This adds a property descriptor for the References feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addReferencesPropertyDescriptorGen(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_MessageViewReference_references_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_MessageViewReference_references_feature", "_UI_MessageViewReference_type"),
                 RamPackage.Literals.MESSAGE_VIEW_REFERENCE__REFERENCES,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the References feature.
     * <!-- begin-user-doc -->
     * @param object the object to add a property descriptor for
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addReferencesPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(
            new ItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_MessageViewReference_references_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_MessageViewReference_references_feature",
                             // CHECKSTYLE:IGNORE MultipleStringLiterals: Outcome of code generator.
                             "_UI_MessageViewReference_type"),
                 RamPackage.Literals.MESSAGE_VIEW_REFERENCE__REFERENCES,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null) {

                @Override
                public Collection<?> getChoiceOfValues(Object object) {
                    MessageViewReference messageView = (MessageViewReference) object;
                    Aspect aspect = (Aspect) messageView.eContainer();
                    
                    Collection<?> result = super.getChoiceOfValues(object);
                    
                    // Filter out all message views that are from the current aspect
                    // here filtering out is used since the collection also includes a null value
                    for (Iterator<?> iterator = result.iterator(); iterator.hasNext();) {
                        Object next = iterator.next();
                        if (next != null && aspect.getMessageViews().contains(next)) {
                            iterator.remove();
                        }
                    }
                    
                    return result;
                }
                
            });
    }

    /**
     * This returns MessageViewReference.gif.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/MessageViewReference"));
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
        MessageViewReference messageViewReference = (MessageViewReference) object;
        
        String label = null;
        MessageView messageView = messageViewReference.getReferences();
        
        if (messageView != null) {
            label = ((NamedElement) messageView.eContainer()).getName();
            label += ".";
            label += EMFEditUtil.getTextFor(getAdapterFactory(), messageView);
        }
        
        return label == null || label.length() == 0 
                ? getString("_UI_MessageViewReference_type")
                : getString("_UI_MessageViewReference_type") + " " + label;
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

        switch (notification.getFeatureID(MessageViewReference.class)) {
            case RamPackage.MESSAGE_VIEW_REFERENCE__REFERENCES:
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
    
    @Override
    public void dispose() {
        super.dispose();
        
        if (changeListener != null) {
            ((IChangeNotifier)getAdapterFactory()).removeListener(changeListener);
        }
    }

}
