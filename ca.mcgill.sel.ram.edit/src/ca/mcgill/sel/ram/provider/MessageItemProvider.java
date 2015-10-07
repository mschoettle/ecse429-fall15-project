/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ca.mcgill.sel.ram.provider;


import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.IViewerNotification;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.commons.emf.util.EMFModelUtil;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.Class;
import ca.mcgill.sel.ram.Classifier;
import ca.mcgill.sel.ram.Interaction;
import ca.mcgill.sel.ram.Lifeline;
import ca.mcgill.sel.ram.Message;
import ca.mcgill.sel.ram.MessageEnd;
import ca.mcgill.sel.ram.MessageOccurrenceSpecification;
import ca.mcgill.sel.ram.MessageSort;
import ca.mcgill.sel.ram.Operation;
import ca.mcgill.sel.ram.OperationType;
import ca.mcgill.sel.ram.Parameter;
import ca.mcgill.sel.ram.ParameterValueMapping;
import ca.mcgill.sel.ram.RamFactory;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.StructuralFeature;
import ca.mcgill.sel.ram.TypeParameter;
import ca.mcgill.sel.ram.TypedElement;
import ca.mcgill.sel.ram.provider.util.RAMEditUtil;
import ca.mcgill.sel.ram.util.RAMModelUtil;

/**
 * This is the item provider adapter for a {@link ca.mcgill.sel.ram.Message} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class MessageItemProvider
    extends ItemProviderAdapter
    implements
        IEditingDomainItemProvider,
        IStructuredItemContentProvider,
        ITreeItemContentProvider,
        IItemLabelProvider,
        IItemPropertySource {
    
    class ChangeListener implements INotifyChangedListener {
        public void notifyChanged(Notification notification) {
            
            if(notification.getNotifier() != null &&  getTarget() != null) {
                Object element = ((IViewerNotification) notification).getElement();
                Message message = (Message) getTarget();
                Operation operation = message.getSignature();
                Object notifier = notification.getNotifier();
                
                if ((element instanceof Class || element instanceof Operation || element instanceof Parameter
                        || element instanceof TypeParameter)
                        && operation != null
                            && (notifier == operation 
                                || notifier == operation.getReturnType()
                                || operation.getParameters().contains(notifier)
                                // not optimal but otherwise arguments have to be searched for ParameterValues referencing the changed parameter
                                || (element instanceof Parameter && message.getArguments().size() > 0)
                                // Check for TypeParameter as notifier (container of it and the operation must match)
                                || ((EObject) notifier).eContainer() == message.getSignature().eContainer()
                                // If TypeParameter is changed and the Classifier is a parameter somewhere, an update is required.
                                || notification.getNotifier() instanceof TypeParameter && !operation.getParameters().isEmpty())) {
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
    public MessageItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
        
        // The change listener is required to get notifications about referenced elements
        // and pass the notifications to its own listener.
        // I.e., The messages signature has types which could change.
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

            addSendEventPropertyDescriptor(object);
            addReceiveEventPropertyDescriptor(object);
            addSignaturePropertyDescriptor(object);
            addAssignToPropertyDescriptor(object);
            addMessageSortPropertyDescriptor(object);
            addSelfMessagePropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Send Event feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addSendEventPropertyDescriptorGen(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Message_sendEvent_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_Message_sendEvent_feature", "_UI_Message_type"),
                 RamPackage.Literals.MESSAGE__SEND_EVENT,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null));
    }
    
    /**
     * This adds a property descriptor for the Send Event feature.
     * <!-- begin-user-doc -->
     * @param object the object to add a property descriptor for
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addSendEventPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(
            createEventItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Message_sendEvent_feature"),
                 // CHECKSTYLE:IGNORE MultipleStringLiterals FOR 2 LINES: Outcome of code generator.
                 getString("_UI_PropertyDescriptor_description", "_UI_Message_sendEvent_feature", 
                             "_UI_Message_type"),
                 RamPackage.Literals.MESSAGE__SEND_EVENT,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Receive Event feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addReceiveEventPropertyDescriptorGen(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Message_receiveEvent_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_Message_receiveEvent_feature", "_UI_Message_type"),
                 RamPackage.Literals.MESSAGE__RECEIVE_EVENT,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null));
    }
    
    /**
     * This adds a property descriptor for the Receive Event feature.
     * <!-- begin-user-doc -->
     * @param object the object to add a property descriptor for
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addReceiveEventPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(
            createEventItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Message_receiveEvent_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_Message_receiveEvent_feature", 
                             "_UI_Message_type"),
                 RamPackage.Literals.MESSAGE__RECEIVE_EVENT,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Signature feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addSignaturePropertyDescriptorGen(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Message_signature_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_Message_signature_feature", "_UI_Message_type"),
                 RamPackage.Literals.MESSAGE__SIGNATURE,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null));
    }
    
    /**
     * This adds a property descriptor for the Signature feature.
     * <!-- begin-user-doc -->
     * @param object the object to add a property descriptor for
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addSignaturePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(
            // CHECKSTYLE:IGNORE AnonInnerLength: Okay here.
            new ItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Message_signature_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_Message_signature_feature", "_UI_Message_type"),
                 RamPackage.Literals.MESSAGE__SIGNATURE,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null) {
              
                @Override
                public Collection<?> getChoiceOfValues(Object object) {
                    Message message = (Message) object;
                    Aspect aspect = (Aspect) EcoreUtil.getRootContainer(message);
                    
                    MessageEnd receiveEvent = message.getReceiveEvent();
                    boolean staticCalls = false;
                    Classifier callReceiver = null;
                    MessageOccurrenceSpecification event = null;
                    
                    // If there is a receive event we can find the valid operations that can be called on the lifeline.
                    // Find out whether it is a metaclass and the classifier.
                    // Then filtering can take place after.
                    if (receiveEvent != null && receiveEvent instanceof MessageOccurrenceSpecification) {
                        event = (MessageOccurrenceSpecification) receiveEvent;
                    } else if (message.getSendEvent() != null 
                            && message.getSendEvent() instanceof MessageOccurrenceSpecification
                            && message.getMessageSort() == MessageSort.REPLY) {
                        event = (MessageOccurrenceSpecification) message.getSendEvent();
                    }
                    
                    if (event != null && event.getCovered().size() > 0) {
                        Lifeline lifelineTo = event.getCovered().get(0);
                        TypedElement represents = lifelineTo.getRepresents();
                        
                        if (represents != null 
                                && represents.getType() instanceof Classifier) {
                            if (represents instanceof StructuralFeature) {
                                staticCalls = ((StructuralFeature) represents).isStatic();
                            }
                            
                            callReceiver = (Classifier) lifelineTo.getRepresents().getType();
                        }
                    }
                    
                    Collection<?> result = super.getChoiceOfValues(object);
                    RAMEditUtil.filterCallableOperations(result, aspect, callReceiver);
                    
                    // Filter out operations from different aspects.
                    // And if there is a callReceiver, filter out all other operations.
                    for (Iterator<?> iterator = result.iterator(); iterator.hasNext();) {
                        Operation value = (Operation) iterator.next();
                        
                        // null is also contained in the list
                        if (value != null) {
                            // Allow constructors and normal static operations when a static call is wanted.
                            boolean staticOperation = value.getOperationType() == OperationType.CONSTRUCTOR
                                    || (value.getOperationType() == OperationType.NORMAL && value.isStatic());
                            // Allow only constructors from the actual class, not from super types etc.
                            boolean invalidConstructor = value.getOperationType() == OperationType.CONSTRUCTOR
                                    && value.eContainer() != callReceiver;
                            
                            // XOR: staticCalls && !staticOperation || !staticCalls && staticOperation
                            if (staticCalls ^ staticOperation || invalidConstructor) {
                                iterator.remove();
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
                                return RAMEditUtil.getOperationSignature(getAdapterFactory(), (Operation) object, true);
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
     * This adds a property descriptor for the Assign To feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addAssignToPropertyDescriptorGen(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Message_assignTo_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_Message_assignTo_feature", "_UI_Message_type"),
                 RamPackage.Literals.MESSAGE__ASSIGN_TO,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null));
    }
    
    /**
     * This adds a property descriptor for the Assign To feature.
     * <!-- begin-user-doc -->
     * @param object the object to add a property descriptor for
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addAssignToPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(
            // CHECKSTYLE:IGNORE AnonInnerLength: Okay here.
            new ItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Message_assignTo_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_Message_assignTo_feature", "_UI_Message_type"),
                 RamPackage.Literals.MESSAGE__ASSIGN_TO,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null) {

                @Override
                public Collection<?> getChoiceOfValues(Object object) {
                    Message message = (Message) object;
                    StructuralFeature assignTo = message.getAssignTo();
                    
                    // sendEvent could also be a Gate, don't show anything in that case
                    if (!(message.getSendEvent() instanceof MessageOccurrenceSpecification)) {
                        return Collections.singleton(null);
                    }
                    
                    // possible values are local properties from the lifeline of the send event
                    // or structural features of the calling class (caller)
                    MessageOccurrenceSpecification sendEvent = (MessageOccurrenceSpecification) message.getSendEvent();
                    
                    Message initialMessage = RAMModelUtil.findInitialMessage(sendEvent);
                    
                    Lifeline lifeline = null;
                    if (sendEvent != null && sendEvent.getCovered().size() > 0) {
                        lifeline = sendEvent.getCovered().get(0);
                    }
                    
                    Aspect aspect = EMFModelUtil.getRootContainerOfType(message, RamPackage.Literals.ASPECT);
                    
                    return RAMEditUtil.collectStructuralFeatures(aspect, lifeline, initialMessage, assignTo);
                }
                
            });
    }

    /**
     * This adds a property descriptor for the Message Sort feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addMessageSortPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Message_messageSort_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_Message_messageSort_feature", "_UI_Message_type"),
                 RamPackage.Literals.MESSAGE__MESSAGE_SORT,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Self Message feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addSelfMessagePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Message_selfMessage_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_Message_selfMessage_feature", "_UI_Message_type"),
                 RamPackage.Literals.MESSAGE__SELF_MESSAGE,
                 false,
                 false,
                 false,
                 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
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
            childrenFeatures.add(RamPackage.Literals.MESSAGE__ARGUMENTS);
            childrenFeatures.add(RamPackage.Literals.MESSAGE__RETURNS);
            childrenFeatures.add(RamPackage.Literals.MESSAGE__LOCAL_PROPERTIES);
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
     * This returns Message.gif.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/Message"));
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
        Message message = (Message) object;
        String label = null;
        
        // if it is anything but a reply message
        // show the signature including assign to and return type
        // if it has arguments show them instead of the formal parameters
        if (message.getMessageSort() != MessageSort.REPLY) {
            if (message.getAssignTo() != null) {
                label = message.getAssignTo().getName();
                label += " := ";
            }
            
            Operation signature = message.getSignature();
            
            // this can be time consuming (only if the structural view hasn't been viewed yet)
            // probably this comes from initializing item providers etc.
            if (signature != null) {
                
                String signatureText;
                if (message.getArguments().isEmpty()) {
                    signatureText = EMFEditUtil.stripTypeName(signature, 
                            RAMEditUtil.getOperationSignature(getAdapterFactory(), signature, false));
                } else {
                    signatureText = signature.getName();
                    
                    signatureText += "(";
                    
                    // go through operations parameters because they define the order
                    // arguments are not ordered
                    // build up a map from parameter to mapping first
                    Map<Parameter, ParameterValueMapping> mappings = new HashMap<Parameter, ParameterValueMapping>();
                    
                    for (ParameterValueMapping mapping : message.getArguments()) {
                        mappings.put(mapping.getParameter(), mapping);
                    }
                    for (Parameter parameter : signature.getParameters()) {
                        // get the ParameterValueMapping for the current parameter
                        ParameterValueMapping mapping = mappings.get(parameter);
                        
                        if (mapping != null && mapping.getValue() != null) {
                            if (signature.getParameters().indexOf(parameter) > 0) {
                                signatureText += ", ";
                            }
                            
                            signatureText += EMFEditUtil.getTextFor(getAdapterFactory(), mapping.getValue());
                        }
                    }
                    signatureText += ")";
                }
                label = (label == null) ? signatureText : label + signatureText;
                
                // show return type only if first message
                if (((Interaction) message.eContainer()).getFragments().indexOf(message.getReceiveEvent()) == 0) {
                    if (signature.getReturnType() != null) {
                        label += ": ";
                        label += EMFEditUtil.getTextFor(getAdapterFactory(), signature.getReturnType());
                    }                    
                }
            }
        } else {
            if (message.getReturns() != null) {
                label = EMFEditUtil.getTextFor(getAdapterFactory(), message.getReturns());
            } else {
                // otherwise don't show anything
                label = "";
            }
        }
        
        // disabled for reply message
        // || label.length() == 0 ? 
        return label == null ? getString("_UI_Message_type") 
                : getString("_UI_Message_type") + " " + label;
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

        switch (notification.getFeatureID(Message.class)) {
            case RamPackage.MESSAGE__SIGNATURE:
            case RamPackage.MESSAGE__ASSIGN_TO:
            case RamPackage.MESSAGE__MESSAGE_SORT:
            case RamPackage.MESSAGE__SELF_MESSAGE:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
                return;
            case RamPackage.MESSAGE__ARGUMENTS:
            case RamPackage.MESSAGE__RETURNS:
            case RamPackage.MESSAGE__LOCAL_PROPERTIES:
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
                (RamPackage.Literals.MESSAGE__ARGUMENTS,
                 RamFactory.eINSTANCE.createParameterValueMapping()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.MESSAGE__RETURNS,
                 RamFactory.eINSTANCE.createStructuralFeatureValue()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.MESSAGE__RETURNS,
                 RamFactory.eINSTANCE.createParameterValue()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.MESSAGE__RETURNS,
                 RamFactory.eINSTANCE.createOpaqueExpression()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.MESSAGE__RETURNS,
                 RamFactory.eINSTANCE.createLiteralString()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.MESSAGE__RETURNS,
                 RamFactory.eINSTANCE.createLiteralInteger()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.MESSAGE__RETURNS,
                 RamFactory.eINSTANCE.createLiteralBoolean()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.MESSAGE__RETURNS,
                 RamFactory.eINSTANCE.createLiteralNull()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.MESSAGE__RETURNS,
                 RamFactory.eINSTANCE.createEnumLiteralValue()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.MESSAGE__LOCAL_PROPERTIES,
                 RamFactory.eINSTANCE.createAttribute()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.MESSAGE__LOCAL_PROPERTIES,
                 RamFactory.eINSTANCE.createReference()));
    }

    /**
     * Adds text for features to distinguish between them in the view. In this
     * case this is necessary, because a message can have parameter value
     * mappings (feature: arguments), but also different value specifications
     * (feature: returns) and in the menu this is not very clear with the
     * default texts.
     */
    @Override
    public String getCreateChildText(Object owner, Object feature,
            Object child, Collection<?> selection) {
        StringBuffer result = new StringBuffer();
        
        result.append(getFeatureText(feature));
        result.append(" ");
        result.append(super.getCreateChildText(owner, feature, child, selection));
        
        return result.toString();
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
    
    @Override
    public void dispose() {
        super.dispose();
        
        if (changeListener != null) {
            ((IChangeNotifier)getAdapterFactory()).removeListener(changeListener);
        }
    }
    
    private ItemPropertyDescriptor createEventItemPropertyDescriptor(
            AdapterFactory adapterFactory,
            ResourceLocator resourceLocator,
            String displayName,
            String description,
            EStructuralFeature feature,
            boolean isSettable,
            boolean multiLine,
            boolean sortChoices,
            Object staticImage,
            String category,
            String[] filterFlags) {
        return new ItemPropertyDescriptor(
                adapterFactory,
                resourceLocator,
                displayName,
                description,
                feature,
                isSettable,
                multiLine,
                sortChoices,
                staticImage,
                category,
                filterFlags) {
            
            @Override
            public Collection<?> getChoiceOfValues(Object object) {
                Message message = (Message) object;
                EObject interaction = message.eContainer();
                
                // Filter out all occurrences that are not part of the current interaction
                // TODO: if occurrences have message set, filter out those where message is not this one
                Collection<?> result = super.getChoiceOfValues(object);
                
                for (Iterator<?> iterator = result.iterator(); iterator.hasNext(); ) {
                    EObject next = (EObject) iterator.next();
                    
                    if (next != null && !EMFModelUtil.getRootContainerOfType(next, RamPackage.Literals.INTERACTION).equals(interaction)) {
                        iterator.remove();
                    }
                }
                
                return result;
            }
            
        };
    }

}
