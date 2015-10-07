/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ca.mcgill.sel.ram.provider;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IViewerNotification;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import ca.mcgill.sel.commons.emf.util.EMFModelUtil;
import ca.mcgill.sel.ram.InteractionFragment;
import ca.mcgill.sel.ram.Lifeline;
import ca.mcgill.sel.ram.Message;
import ca.mcgill.sel.ram.MessageEnd;
import ca.mcgill.sel.ram.MessageOccurrenceSpecification;
import ca.mcgill.sel.ram.Parameter;
import ca.mcgill.sel.ram.ParameterValue;
import ca.mcgill.sel.ram.RamPackage;

/**
 * This is the item provider adapter for a {@link ca.mcgill.sel.ram.ParameterValue} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ParameterValueItemProvider
    extends ValueSpecificationItemProvider {
    
    class ChangeListener implements INotifyChangedListener {
        public void notifyChanged(Notification notification) {
            
            if(notification.getNotifier() != null &&  getTarget() != null) {
                Object element = ((IViewerNotification) notification).getElement();
                ParameterValue parameterValue = (ParameterValue) getTarget();
                Parameter parameter = parameterValue.getParameter();
                Object notifier = notification.getNotifier();
                
                /**
                 * First make sure that the element is the notifier to filter out invalid requests.
                 * The item provider needs to be stateful due to the target.
                 * We just need to make sure that the update is only done when necessary.
                 * E.g., the notifier needs to be the structural feature itself.
                 */
                if (element == notifier
                        && parameter != null 
                        && notifier == parameter) {
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
    public ParameterValueItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
        
        /** The change listener is required to get notifications about referenced elements
         * and pass the notifications to its own listener.
         * I.e., The values name could change.
         */
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
                 getString("_UI_ParameterValue_parameter_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_ParameterValue_parameter_feature", "_UI_ParameterValue_type"),
                 RamPackage.Literals.PARAMETER_VALUE__PARAMETER,
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
            // CHECKSTYLE:IGNORE AnonInnerLength: Okay here.
            new ItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_ParameterValue_parameter_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_ParameterValue_parameter_feature", 
                             // CHECKSTYLE:IGNORE MultipleStringLiterals: Outcome of code generator.
                             "_UI_ParameterValue_type"),
                 RamPackage.Literals.PARAMETER_VALUE__PARAMETER,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null) {

                @Override
                public Collection<?> getChoiceOfValues(Object object) {
                    ParameterValue parameterValue = (ParameterValue) object;
                    // as of right now ParameterValue can only be used for a ParameterValueMapping
                    // therefore the containing Message can be retrieved
                    Message message = (Message) EMFModelUtil.getRootContainerOfType(parameterValue, 
                            RamPackage.Literals.MESSAGE);
                    
                    // TODO: handle other containers than message as well (and then restructure this code)
                    if (message == null) {
                        return Collections.singleton(null);
                    }
                    // figure out the message that called
                    // in order to get that operations parameters
                    MessageOccurrenceSpecification sendOccurrence = (MessageOccurrenceSpecification) message
                            .getSendEvent();
                    
                    if (sendOccurrence == null || sendOccurrence.getCovered().size() == 0) {
                        return Collections.singleton(null);
                    }
                    
                    // search for receive occurrence on the lifeline
                    // since a call to this lifeline cannot come after the current message
                    // the location of a potential found receive event does not have to be checked
                    Lifeline lifeline = sendOccurrence.getCovered().get(0);
                    
                    Collection<Object> result = new ArrayList<Object>();
                    
                    // Go through all fragments covering this lifeline, because they are not ordered.
                    for (InteractionFragment fragment : lifeline.getCoveredBy()) {
                        if (fragment instanceof MessageOccurrenceSpecification) {
                            // if the receive event of the message this message end belongs to is this fragment
                            // it is the calling message
                            MessageEnd receiveEvent = ((MessageOccurrenceSpecification) fragment).getMessage()
                                    .getReceiveEvent();
                            
                            if (fragment == receiveEvent) {
                                Message callingMessage = receiveEvent.getMessage();
                                // Filter out self messages, otherwise their parameters are added as well.
                                if (callingMessage != null 
                                        && !callingMessage.isSelfMessage() 
                                        && callingMessage.getSignature() != null) {
                                    result.addAll(callingMessage.getSignature().getParameters());
                                }
                            }
                        }
                    }
                    
                    // add null since it is a reference feature and not many
                    // (see ItemPropertyDescriptor line 807)
                    result.add(null);
                    
                    return result;
                }
                
            });
    }

    /**
     * This returns ParameterValue.gif.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/ParameterValue"));
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
        ParameterValue parameterValue = (ParameterValue) object;
        
        String label = null;
        
        if (parameterValue.getParameter() != null) {
            label = parameterValue.getParameter().getName();
        }
        
        return label == null || label.length() == 0 
                ? getString("_UI_ParameterValue_type")
                : getString("_UI_ParameterValue_type") + " " + label;
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

}
