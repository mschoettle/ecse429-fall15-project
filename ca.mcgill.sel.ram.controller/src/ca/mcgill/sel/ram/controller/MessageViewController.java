package ca.mcgill.sel.ram.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.commons.emf.util.EMFModelUtil;
import ca.mcgill.sel.ram.AbstractMessageView;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.CombinedFragment;
import ca.mcgill.sel.ram.FragmentContainer;
import ca.mcgill.sel.ram.Gate;
import ca.mcgill.sel.ram.ImplementationClass;
import ca.mcgill.sel.ram.Interaction;
import ca.mcgill.sel.ram.InteractionFragment;
import ca.mcgill.sel.ram.LayoutElement;
import ca.mcgill.sel.ram.Lifeline;
import ca.mcgill.sel.ram.Message;
import ca.mcgill.sel.ram.MessageEnd;
import ca.mcgill.sel.ram.MessageOccurrenceSpecification;
import ca.mcgill.sel.ram.MessageSort;
import ca.mcgill.sel.ram.ObjectType;
import ca.mcgill.sel.ram.Operation;
import ca.mcgill.sel.ram.OperationType;
import ca.mcgill.sel.ram.Parameter;
import ca.mcgill.sel.ram.ParameterValueMapping;
import ca.mcgill.sel.ram.RamFactory;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.Reference;
import ca.mcgill.sel.ram.StructuralFeature;
import ca.mcgill.sel.ram.TypedElement;
import ca.mcgill.sel.ram.util.RAMModelUtil;

/**
 * The controller for {@link AbstractMessageView}s.
 * 
 * @author mschoettle
 */
public class MessageViewController extends BaseController {

    /**
     * Creates a new instance of {@link MessageViewController}.
     */
    protected MessageViewController() {
        // Prevent anyone outside this package to instantiate.
    }
    
    /**
     * Creates a new lifeline as well as its layout element at the given position.
     * 
     * @param owner the {@link Interaction} the {@link Lifeline} should be added to
     * @param represents the {@link TypedElement} that represents the lifeline
     * @param x the x position of the lifeline
     * @param y the y position of the lifeline
     */
    public void createLifeline(Interaction owner, TypedElement represents, float x, float y) {
        Command createLifelineCommand = createLifelineCommand(owner, represents, x, y);
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(owner);
        
        doExecute(editingDomain, createLifelineCommand);
    }
    
    /**
     * Creates a new lifeline and a message to that lifeline.
     * The lifeline also gets a layout at the given position.
     * The message is created from the given "from lifeline" to the newly created lifeline and its message ends added
     * at the specified index inside the container.
     * 
     * @param owner the owner the message and lifeline should be added to
     * @param represents the {@link TypedElement} that represents the lifeline
     * @param x the x position of the lifeline
     * @param y the y position of the lifeline
     * @param lifelineFrom the lifeline from which the message is sent
     * @param container the container where the message ends are added to
     * @param signature the signature of the message
     * @param addAtIndex the index at which the message ends are added in the container
     * @see #createLifeline(Interaction, TypedElement, float, float)
     * @see #createMessage(Interaction, Lifeline, Lifeline, FragmentContainer, Operation, int)
     */
    // CHECKSTYLE:IGNORE ParameterNumber: Unfortunately, all of these parameters are required.
    public void createLifelineWithMessage(Interaction owner, TypedElement represents, float x, float y,
            Lifeline lifelineFrom, FragmentContainer container, Operation signature, int addAtIndex) {
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(owner);
        
        // If we are trying to add the first message into an operand, there is no fragment in the container.
        // Take the CombinedFragment then.
        InteractionFragment previousFragment = null;
        
        if (container.getFragments().size() > 0) {
            previousFragment = container.getFragments().get(addAtIndex);
        } else {
            previousFragment = (InteractionFragment) container.eContainer();
        }
        
        Message initialMessage = RAMModelUtil.findInitialMessage(previousFragment);
        
        // This should only happen when the previousFragment is the very first event and part of the initial message.
        // This is the case when the initial message is a create message (the create message's behaviour is defined).
        // In that case, we try to use that one.
        if (initialMessage == null) {
            initialMessage = ((MessageEnd) previousFragment).getMessage();
        }
        
        TypedElement typedElement = represents;

        CompoundCommand compoundCommand = new CompoundCommand();
        
        // A new metaclass lifeline.
        if (represents instanceof Reference && represents.eContainer() == null) {
            // Check whether it is really a static operation or a constructor.
            boolean staticOperation = signature.getOperationType() == OperationType.NORMAL;
            
            Reference reference = RamFactory.eINSTANCE.createReference();
            reference.setLowerBound(1);
            reference.setType((ObjectType) represents.getType());
            reference.setStatic(staticOperation);
            typedElement = reference;
            
            // In case of a static operation, the reference is added to the properties of the interaction.
            // For constructors, it is added as a local property of the calling lifeline.
            if (staticOperation) {
                compoundCommand.append(AddCommand.create(editingDomain, owner,
                        RamPackage.Literals.INTERACTION__PROPERTIES, reference));
            } else {
                reference.setName(reference.getType().getName().toLowerCase());
                
                compoundCommand.append(AddCommand.create(editingDomain, initialMessage,
                        RamPackage.Literals.MESSAGE__LOCAL_PROPERTIES, reference));
            }
        }
        
        Command createLifelineCommand = createLifelineCommand(owner, typedElement, x, y);
        
        // Get the actual new lifeline object.
        Collection<?> result = createLifelineCommand.getResult();
        Lifeline newLifeline = (Lifeline) result.iterator().next();
        
        Command createMessageCommand = createMessageCommand(editingDomain, owner, lifelineFrom, newLifeline,
                container, signature, addAtIndex);
        
        compoundCommand.append(createLifelineCommand);
        
        /**
         * If the message is going to a lifeline that is not part of the covered combined fragments yet,
         * it needs to be covered by all combined fragments (and vice versa).
         */
        if (owner != container) {
            List<CombinedFragment> coveredCombinedFragments = getCoveredCombinedFragments(owner, container);
            
            for (CombinedFragment combinedFragment : coveredCombinedFragments) {
                if (!newLifeline.getCoveredBy().contains(combinedFragment)) {
                    compoundCommand.append(AddCommand.create(editingDomain, combinedFragment,
                            RamPackage.Literals.INTERACTION_FRAGMENT__COVERED, newLifeline));
                }
            }
        }
        
        compoundCommand.append(createMessageCommand);
        
        doExecute(editingDomain, compoundCommand);
    }
    
    /**
     * Creates a command to create a new lifeline and its layout.
     * 
     * @param owner the {@link Interaction} the {@link Lifeline} should be added to
     * @param represents the {@link TypedElement} that represents the lifeline
     * @param x the x position of the lifeline
     * @param y the y position of the lifeline
     * @return the command that is responsible for creating a new lifeline and its layout
     */
    private Command createLifelineCommand(Interaction owner, TypedElement represents, float x, float y) {
        Aspect aspect = EMFModelUtil.getRootContainerOfType(owner, RamPackage.Literals.ASPECT);
        
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(aspect);
        
        // Create required objects.
        Lifeline lifeline = RamFactory.eINSTANCE.createLifeline();
        lifeline.setRepresents(represents);
        
        LayoutElement layoutElement = RamFactory.eINSTANCE.createLayoutElement();
        layoutElement.setX(x);
        layoutElement.setY(y);
        
        // Create commands.
        Command createElementMapCommand = createLayoutElementCommand(editingDomain, owner.eContainer(), lifeline,
                layoutElement);
        Command addLifelineCommand = AddCommand.create(editingDomain, owner,
                RamPackage.Literals.INTERACTION__LIFELINES, lifeline);
        
        CompoundCommand compoundCommand = new CompoundCommand();
        compoundCommand.append(addLifelineCommand);
        compoundCommand.append(createElementMapCommand);
        
        return compoundCommand;
    }
    
    /**
     * Moves the given lifeline to a new position.
     * 
     * @param lifeline the lifeline to move
     * @param x the new x position
     * @param y the new y position
     */
    public void moveLifeline(Lifeline lifeline, float x, float y) {
        AbstractMessageView messageView = (AbstractMessageView) lifeline.eContainer().eContainer();
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(messageView);
        
        Command moveCommand = createMoveCommand(editingDomain, messageView, lifeline, x, y);
        
        doExecute(editingDomain, moveCommand);
    }
    
    /**
     * Creates a new message. Also creates the send and receive message ends.
     * The message is created between the given lifelines.
     * Also, the messages send and receive events are added at the given index. This defines the order at
     * which the messages are invoked in the interaction.
     * 
     * @param owner the {@link Interaction} the message should be added to
     * @param lifelineFrom the lifeline the message is sent from
     * @param lifelineTo the lifeline the message is sent to
     * @param container the container the message ends should be added to
     * @param signature the signature of the message
     * @param addAtIndex the index at which the send and receive events should be added to (fragments of the
     *            interaction)
     */
    public void createMessage(Interaction owner, Lifeline lifelineFrom, Lifeline lifelineTo,
            FragmentContainer container, Operation signature, int addAtIndex) {
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(owner);
        
        CompoundCommand compoundCommand = new CompoundCommand();
        
        Command createMessageCommand = createMessageCommand(editingDomain, owner, lifelineFrom, lifelineTo,
                container, signature, addAtIndex);
        
        /**
         * If the message is going to a lifeline that is not part of the covered combined fragments yet,
         * it needs to be covered by all combined fragments (and vice versa).
         */
        if (owner != container) {
            List<CombinedFragment> coveredCombinedFragments = getCoveredCombinedFragments(owner, container);
            
            for (CombinedFragment combinedFragment : coveredCombinedFragments) {
                if (!lifelineTo.getCoveredBy().contains(combinedFragment)) {
                    compoundCommand.append(AddCommand.create(editingDomain, combinedFragment,
                            RamPackage.Literals.INTERACTION_FRAGMENT__COVERED, lifelineTo));
                }
            }
        }
        
        compoundCommand.append(createMessageCommand);
        
        doExecute(editingDomain, compoundCommand);
    }
    
    /**
     * Creates a new command to create a message.
     * In addition, creates a reply message if the signature has a return type
     * other than {@link ca.mcgill.sel.ram.RVoid}.
     * Depending on the signature, the corresponding kind of message is used.
     * 
     * @param editingDomain the {@link EditingDomain} to use for executing commands
     * @param owner the owner the message and lifeline should be added to
     * @param lifelineFrom the lifeline from which the message is sent
     * @param lifelineTo the lifeline to which the message is sent
     * @param container the container where the message ends are added to
     * @param signature the signature of the message
     * @param addAtIndex the index at which the message ends are added in the container
     * @return the command to create a new message
     */
    private Command createMessageCommand(EditingDomain editingDomain, Interaction owner, Lifeline lifelineFrom,
            Lifeline lifelineTo, FragmentContainer container, Operation signature, int addAtIndex) {
        CompoundCommand compoundCommand = new CompoundCommand();
        
        ArrayList<InteractionFragment> events = new ArrayList<InteractionFragment>();
        
        // Create message commands.
        MessageSort messageSort = MessageSort.SYNCH_CALL;
        
        if (signature != null) {
            switch (signature.getOperationType()) {
                case CONSTRUCTOR:
                    messageSort = MessageSort.CREATE_MESSAGE;
                    break;
                case DESTRUCTOR:
                    messageSort = MessageSort.DELETE_MESSAGE;
                    break;
                default:
                    messageSort = MessageSort.SYNCH_CALL;
                    break;
            }
        }
        
        Command createMessageCallCommand = createSingleMessageCommand(editingDomain, owner, lifelineFrom, lifelineTo,
                container, signature, messageSort, addAtIndex, events);
        
        Command createMessageReplyCommand = null;
        // Create a reply message at the same time if there is no behaviour defined yet.
        if (signature != null
                && signature.getReturnType().eClass() != RamPackage.Literals.RVOID
                && messageSort != MessageSort.CREATE_MESSAGE
                && !(signature.eContainer() instanceof ImplementationClass)
                && lifelineFrom != lifelineTo) {
            Aspect aspect = (Aspect) EcoreUtil.getRootContainer(signature);
            boolean messageViewDefined = RAMModelUtil.isMessageViewDefined(aspect, signature);
            
            if (!messageViewDefined) {
                createMessageReplyCommand = createSingleMessageCommand(editingDomain, owner, lifelineTo, lifelineFrom,
                        container, signature, MessageSort.REPLY, addAtIndex + 2, events);
            }
        }
        
        // Make sure the command for the events is executed first.
        compoundCommand.append(AddCommand.create(editingDomain, container,
                RamPackage.Literals.FRAGMENT_CONTAINER__FRAGMENTS, events, addAtIndex));
        
        compoundCommand.append(createMessageCallCommand);
        // A null command will just be ignored.
        compoundCommand.append(createMessageReplyCommand);
        
        return compoundCommand;
    }
    
    /**
     * Creates a new reply message. Also creates the send and receive message ends.
     * The message is created between the given lifelines.
     * Also, the messages send and receive events are added at the given index. This defines the order at
     * which the messages are invoked in the interaction.
     * If the reply message is going to the outside (i.e., it is a reply for the operation 
     * which behaviour is defined by the message, the lifelineTo needs to be <code>null</code>, which triggers
     * the creation and usage of a gate as the receive event.
     * 
     * @param owner the {@link Interaction} the message should be added to
     * @param lifelineFrom the lifeline the message is sent from
     * @param lifelineTo the lifeline the message is sent to, null if the receiver should be a gate (outside)
     * @param container the container the message ends should be added to
     * @param signature the signature of the message
     * @param addAtIndex the index at which the send and receive events should be added to (fragments of the
     *            interaction)
     */
    public void createReplyMessage(Interaction owner, Lifeline lifelineFrom, Lifeline lifelineTo,
            FragmentContainer container, Operation signature, int addAtIndex) {
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(owner);
        
        ArrayList<InteractionFragment> events = new ArrayList<InteractionFragment>();
        
        Command createMessageReplyCommand = createSingleMessageCommand(editingDomain, owner, lifelineFrom, lifelineTo,
                container, signature, MessageSort.REPLY, addAtIndex, events);
        
        CompoundCommand compoundCommand = new CompoundCommand();
        
        // Make sure the command for the events is executed first.
        compoundCommand.append(AddCommand.create(editingDomain, container,
                RamPackage.Literals.FRAGMENT_CONTAINER__FRAGMENTS, events, addAtIndex));
        compoundCommand.append(createMessageReplyCommand);
        
        doExecute(editingDomain, compoundCommand);
    }

    /**
     * Creates a command to create a single message. The message ends are added to the given list of events and need
     * to be added manually by the caller (using a command preferably).
     * Depending on the message sort, a destruction occurrence might be created (delete message) as the receive event
     * of the message. Furthermore, for regular message calls (no replies), parameter mappings are added for each
     * parameter of the signature, where only the actual parameter is left to be set.
     * For reply messages, <code>null</code> may be used for the to lifeline to force the creation and usage of a Gate.
     * 
     * @param editingDomain the {@link EditingDomain} to use for executing commands
     * @param owner the owner the message and lifeline should be added to
     * @param lifelineFrom the lifeline from which the message is sent
     * @param lifelineTo the lifeline to which the message is sent
     * @param container the container where the message ends are added to
     * @param signature the signature of the message
     * @param messageSort the {@link MessageSort} of the message
     * @param addAtIndex the index at which the message ends are added in the container
     * @param events the list of events to which the message ends of this message are added to
     * @return the command to create a new message
     */
    // CHECKSTYLE:IGNORE ParameterNumber: Unfortunately, all of these parameters are required to build the command.
    private Command createSingleMessageCommand(EditingDomain editingDomain, Interaction owner,
            Lifeline lifelineFrom, Lifeline lifelineTo, FragmentContainer container, Operation signature,
            MessageSort messageSort, int addAtIndex, List<InteractionFragment> events) {
        CompoundCommand compoundCommand = new CompoundCommand();
        
        // Create send event.
        MessageOccurrenceSpecification sendEvent = RamFactory.eINSTANCE.createMessageOccurrenceSpecification();
        events.add(sendEvent);
        compoundCommand.append(AddCommand.create(editingDomain, sendEvent,
                RamPackage.Literals.INTERACTION_FRAGMENT__COVERED, lifelineFrom));
        
        // Create receive event.
        MessageEnd receiveEvent = null;
        
        switch (messageSort) {
            case DELETE_MESSAGE:
                receiveEvent = RamFactory.eINSTANCE.createDestructionOccurrenceSpecification();
                break;
            case REPLY:
                if (lifelineTo == null) {
                    receiveEvent = RamFactory.eINSTANCE.createGate();
                    ((Gate) receiveEvent).setName("out_" + signature.getName());
                    break;
                }
            default:
                receiveEvent = RamFactory.eINSTANCE.createMessageOccurrenceSpecification();
                break;
        }
        
        if (messageSort == MessageSort.REPLY && lifelineTo == null) {
            compoundCommand.append(AddCommand.create(editingDomain, owner,
                    RamPackage.Literals.INTERACTION__FORMAL_GATES, receiveEvent));
        } else {
            compoundCommand.append(AddCommand.create(editingDomain, receiveEvent,
                    RamPackage.Literals.INTERACTION_FRAGMENT__COVERED, lifelineTo));
            events.add((InteractionFragment) receiveEvent);
        }
        
        // Create message.
        Message message = RamFactory.eINSTANCE.createMessage();
        message.setSendEvent(sendEvent);
        message.setReceiveEvent(receiveEvent);
        message.setSignature(signature);
        message.setMessageSort(messageSort);
        
        // Set references.
        sendEvent.setMessage(message);
        receiveEvent.setMessage(message);
        
        if (messageSort != MessageSort.REPLY
                && signature != null
                && signature.getParameters().size() > 0) {
            compoundCommand.append(createParameterMappingCommands(editingDomain, message, signature));
        }
        
        // Set assignTo automatically.
        if (messageSort == MessageSort.CREATE_MESSAGE) {
            Reference reference = (Reference) lifelineTo.getRepresents();
            message.setAssignTo(reference);
        }
        
        compoundCommand.append(AddCommand.create(editingDomain, owner,
                RamPackage.Literals.INTERACTION__MESSAGES, message));
        
        return compoundCommand;
    }

    /**
     * Creates a command to create all parameter value mappings for all parameters of the given operation.
     * These mappings are added to the given message.
     * 
     * @param editingDomain the {@link EditingDomain} to use for executing commands
     * @param message the owner where the parameter value mappings should be added to
     * @param operation the operation which is represented by the message (message's signature)
     * @return the command to create {@link ParameterValueMapping}'s for all parameters
     */
    private Command createParameterMappingCommands(EditingDomain editingDomain, Message message, Operation operation) {
        CompoundCommand compoundCommand = new CompoundCommand();
        
        for (Parameter parameter : operation.getParameters()) {
            ParameterValueMapping parameterMapping = RamFactory.eINSTANCE.createParameterValueMapping();
            parameterMapping.setParameter(parameter);
            compoundCommand.append(AddCommand.create(editingDomain, message,
                    RamPackage.Literals.MESSAGE__ARGUMENTS, parameterMapping));
        }
        
        return compoundCommand;
    }

    /**
     * Removes all messages starting from the given send event that follow the corresponding message call.
     * This might include nested message behaviour (i.e., the message's signature behaviour is defined inside this
     * interaction defining the behaviour of an additional operation) and/or reply messages.
     * Furthermore, besides removing messages and related events, lifelines that are not covered anymore by
     * fragments as a result of this are removed as well.
     * 
     * @param owner the interaction that contains messages and lifelines
     * @param container the container of the fragments
     * @param sendEvent the send event related to the message to remove
     */
    public void removeMessages(Interaction owner, FragmentContainer container,
            MessageOccurrenceSpecification sendEvent) {
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(owner);
        
        CompoundCommand compoundCommand = createRemoveMessagesCommand(editingDomain, owner, container, sendEvent);
        
        /**
         * Figure out if there will be empty lifelines as a result and delete them as well.
         * When determining if the lifeline will be empty,
         * it also needs to be considered that the lifeline is covered by the combined fragment as well.
         */
        List<CombinedFragment> combinedFragments = getCoveredCombinedFragments(owner, container);
        // The list needs to be reversed so that the inner most combined fragment is treated first
        // when removing "covered by".
        Collections.reverse(combinedFragments);
        
        appendRemoveEmptyLifelinesCommand(editingDomain, compoundCommand, owner, combinedFragments,
                !combinedFragments.isEmpty());
        
        doExecute(editingDomain, compoundCommand);
    }
    
    /**
     * Appends commands to the given compound command that takes care of removing empty lifelines as a result of
     * executing the given compound command. The commands are only appended if empty lifelines would result, i.e.,
     * otherwise no command will be appended. If the fragments that will be deleted as part of the given command are
     * part of one or more combined fragments, the combined fragments have to be removed from the lifeline (covered), 
     * this is optional and can be specified using <code>removeFromCovered</code>.
     * 
     * @param editingDomain the {@link EditingDomain} to use for executing commands
     * @param compoundCommand the {@link CompoundCommand} to append the commands to and which contains other commands
     * @param owner the {@link Interaction} that contains the lifelines
     * @param combinedFragments the list of {@link CombinedFragment},
     *              if the fragments deleted by the given command are part of one or more, empty otherwise
     * @param removeFromCovered if <code>combinedFragment</code> is not null, 
     *              determines whether to be deleted lifelines also need to be removed from the given fragment
     */
    protected static void appendRemoveEmptyLifelinesCommand(EditingDomain editingDomain,
            CompoundCommand compoundCommand, Interaction owner, List<CombinedFragment> combinedFragments,
            boolean removeFromCovered) {
        Set<Lifeline> affectedLifelines = new HashSet<Lifeline>();
        Set<InteractionFragment> fragmentsToDelete = new HashSet<InteractionFragment>();
        /**
         * When determining if the lifeline will be empty,
         * it also needs to be considered that the lifeline is covered by the combined fragment as well. 
         */
        fragmentsToDelete.addAll(combinedFragments);
        
        for (Object object : compoundCommand.getResult()) {
            if (object instanceof InteractionFragment) {
                InteractionFragment currentFragment = (InteractionFragment) object;
                fragmentsToDelete.add(currentFragment);
                affectedLifelines.addAll(currentFragment.getCovered());
            }
        }
        
        for (Lifeline lifeline : affectedLifelines) {
            int deletedFragments = 0;
            for (InteractionFragment currentFragment : lifeline.getCoveredBy()) {
                if (fragmentsToDelete.contains(currentFragment)) {
                    deletedFragments++;
                }
            }
            
            if (lifeline.getCoveredBy().size() == deletedFragments) {
                if (removeFromCovered) {
                    /**
                     * Since the combined fragment itself is not deleted,
                     * we need to remove it from the lifeline specifically.
                     */
                    for (CombinedFragment combinedFragment : combinedFragments) {
                        compoundCommand.append(RemoveCommand.create(editingDomain, combinedFragment,
                                RamPackage.Literals.INTERACTION_FRAGMENT__COVERED, lifeline));
                    }
                }
                compoundCommand.append(createRemoveLayoutElementCommand(editingDomain, owner.eContainer(), lifeline));
                compoundCommand.append(RemoveCommand.create(editingDomain, lifeline));
            }
        }
    }
    
    /**
     * Returns the set of lifelines that are going to be deleted by the given command.
     * 
     * @param compoundCommand the {@link CompoundCommand} consisting of other commands
     * @return the lifelines that are to be deleted
     */
    protected static Set<Lifeline> getDeletedLifelines(CompoundCommand compoundCommand) {
        Set<Lifeline> result = new HashSet<Lifeline>();
        
        for (Command command : compoundCommand.getCommandList()) {
            if (command.getClass() == RemoveCommand.class) {
                RemoveCommand removeCommand = (RemoveCommand) command;
                if (removeCommand.getFeature() == RamPackage.Literals.INTERACTION__LIFELINES) {
                    @SuppressWarnings("unchecked")
                    Collection<Lifeline> lifelines = (Collection<Lifeline>) removeCommand.getCollection();
                    result.addAll(lifelines);
                }
            } else if (command.getClass() == CompoundCommand.class) {
                result.addAll(getDeletedLifelines((CompoundCommand) command));
            }
        }
        
        return result;
    }

    /**
     * Creates a command that removes all messages starting from the given send event
     * that follow the corresponding message call.
     * This might include nested message behaviour (i.e., the message's signature behaviour is defined inside this
     * interaction defining the behaviour of an additional operation) and/or reply messages.
     * 
     * @param editingDomain the {@link EditingDomain} to use for executing commands
     * @param owner the interaction that contains messages and lifelines
     * @param container the container of the fragments
     * @param sendEvent the send event related to the message to remove
     * @return the commands that remove messages, events and potentially lifelines
     */
    protected static CompoundCommand createRemoveMessagesCommand(EditingDomain editingDomain, Interaction owner,
            FragmentContainer container, MessageOccurrenceSpecification sendEvent) {
        CompoundCommand compoundCommand = new CompoundCommand();
        
        int fromIndex = container.getFragments().indexOf(sendEvent);
        /**
         * In case the message is a reply and it the receiving end is a gate, only one fragment needs to be deleted,
         * so the toIndex needs to be fromIndex.
         */
        int toIndex = fromIndex + 1;
        
        if (sendEvent.getMessage().getMessageSort() == MessageSort.REPLY
                && sendEvent.getMessage().getReceiveEvent().eClass() == RamPackage.Literals.GATE) {
            toIndex = fromIndex;
        }
        
        for (int index = fromIndex + 1; index < container.getFragments().size(); index++) {
            InteractionFragment currentFragment = container.getFragments().get(index);
            
            if (sendEvent.getCovered().containsAll(currentFragment.getCovered())) {
                if (currentFragment instanceof MessageOccurrenceSpecification) {
                    MessageOccurrenceSpecification messageEnd = (MessageOccurrenceSpecification) currentFragment;
                    
                    if (messageEnd.getMessage().getReceiveEvent() == messageEnd) {
                        toIndex = index;
                    }
                    
                    break;
                } else {
                    toIndex = index - 1;
                    break;
                }
            }
        }
        
        List<InteractionFragment> fragmentsToDelete = new ArrayList<InteractionFragment>();
        List<Message> messagesToDelete = new ArrayList<Message>();
        Set<Lifeline> affectedLifelines = new HashSet<Lifeline>();
        
        // Delete messages from the bottom up.
        for (int i = toIndex; i >= fromIndex; i--) {
            InteractionFragment currentFragment = container.getFragments().get(i);
            fragmentsToDelete.add(currentFragment);
            affectedLifelines.addAll(currentFragment.getCovered());
            
            if (currentFragment instanceof MessageOccurrenceSpecification) {
                MessageOccurrenceSpecification messageEnd = (MessageOccurrenceSpecification) currentFragment;
                
                // Remove the message only once.
                if (messageEnd.getMessage().getSendEvent() == messageEnd) {
                    Message message = messageEnd.getMessage();
                    Message initialMessage = RAMModelUtil.findInitialMessage(messageEnd);
                    
                    messagesToDelete.add(message);
                    
                    // Also delete temporary properties that were used as assign to.
                    // This assumes that there were created just for this purpose.
                    StructuralFeature structuralFeature = message.getAssignTo();
                    if (structuralFeature != null && structuralFeature.eContainer() == initialMessage) {
                        compoundCommand.append(RemoveCommand.create(editingDomain, structuralFeature));
                    }
                }
            }
        }
        
        compoundCommand.append(RemoveCommand.create(editingDomain, messagesToDelete));
        compoundCommand.append(DeleteCommand.create(editingDomain, fragmentsToDelete));
        
        // Remove the occurrence from the lifeline (coveredBy) last to retain the same order as the add/create commands.
        for (InteractionFragment fragment : fragmentsToDelete) {
            // Use the reference of the occurrence (covered),
            // because the opposite (coveredBy) will be removed automatically then.
            compoundCommand.append(RemoveCommand.create(editingDomain, fragment,
                    RamPackage.Literals.INTERACTION_FRAGMENT__COVERED, fragment.getCovered()));
        }
        
        return compoundCommand;
    }

    /**
     * Returns a list of all combined fragments that the given fragment container "covers".
     * If the container is the owner, an empty list is returned. Otherwise all combined fragments
     * in the containment hierarchy will be returned.
     * The order of the retrieved list is top down from the most combined fragment down to its children etc.
     * If the reverse order is required, the caller needs to do this manually.
     * 
     * @param owner the {@link Interaction} that contains everything
     * @param container the {@link FragmentContainer} for which to retrieve all combined fragments
     * @return a list of all combined fragments the given fragment container "covers"
     */
    private List<CombinedFragment> getCoveredCombinedFragments(Interaction owner, FragmentContainer container) {
        List<CombinedFragment> result = new ArrayList<CombinedFragment>();
        
        FragmentContainer currentContainer = container;
        
        while (owner != currentContainer) {
            CombinedFragment combinedFragment = (CombinedFragment) currentContainer.eContainer();
            // We need a reverse order, so that the parent combined fragment is first considered before its child.
            result.add(0, combinedFragment);
            
            currentContainer = combinedFragment.getContainer();
        }
        
        return result;
    }

}
