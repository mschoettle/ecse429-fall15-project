package ca.mcgill.sel.ram.ui.views.message.old;

import org.eclipse.emf.ecore.util.EcoreUtil;

import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.ram.AspectMessageView;
import ca.mcgill.sel.ram.Gate;
import ca.mcgill.sel.ram.Interaction;
import ca.mcgill.sel.ram.InteractionFragment;
import ca.mcgill.sel.ram.Lifeline;
import ca.mcgill.sel.ram.Message;
import ca.mcgill.sel.ram.MessageOccurrenceSpecification;
import ca.mcgill.sel.ram.MessageSort;
import ca.mcgill.sel.ram.Operation;
import ca.mcgill.sel.ram.RamFactory;
import ca.mcgill.sel.ram.Reference;
import ca.mcgill.sel.ram.TypedElement;
import ca.mcgill.sel.ram.ui.components.RamTextComponent;
import ca.mcgill.sel.ram.ui.utils.Fonts;
import ca.mcgill.sel.ram.ui.utils.Strings;
import ca.mcgill.sel.ram.ui.views.TextView;
import ca.mcgill.sel.ram.ui.views.handler.HandlerFactory;

/**
 * The view that visualizes an aspect message view.
 * It consists of a visualization of the pointcut and advice.
 * 
 * @author mschoettle
 */
public class AspectMessageViewView extends AbstractMessageViewView<AspectMessageView> {
    
    private TextView nameField;
    
    /**
     * Creates a new view for the given aspect message view.
     * 
     * @param messageView the {@link AspectMessageView} to create a view for
     */
    public AspectMessageViewView(AspectMessageView messageView) {
        super(messageView);
    }
    
    @Override
    protected void build() {
        // build general stuff
        super.build();
        
        nameField = new TextView(messageView, CorePackage.Literals.CORE_NAMED_ELEMENT__NAME);
        nameField.setFont(DEFAULT_FONT);
        nameField.registerTapProcessor(HandlerFactory.INSTANCE.getTextViewHandler());
        addNameField(nameField);
        
        float width = (getWidth() - PADDING) / 4.0f;
        
        Interaction pointcut = createPointcut(messageView.getPointcut(), messageView.getAdvice());
        InteractionView pointcutView = new InteractionView(pointcut);
        pointcutView.setMinimumWidth(width);
        pointcutView.setNoStroke(false);
        addInteractionView(pointcutView);
        pointcutView.build();
        
        RamTextComponent pointcutText = new RamTextComponent(Fonts.DEFAULT_FONT_INTERACTION, Strings.POINTCUT);
        pointcutView.addChild(pointcutText);
        
        InteractionView adviceView = new InteractionView(messageView.getAdvice());
        adviceView.setMinimumWidth(getWidth());
        adviceView.setNoStroke(false);
        // if build is called after addInteractionView
        // then updateParent has to be called when the interaction view changes its height
        adviceView.build();
        addInteractionView(adviceView);
        
        RamTextComponent adviceText = new RamTextComponent(Fonts.DEFAULT_FONT_INTERACTION, Strings.ADVICE);
        adviceView.addChild(adviceText);
    }
    
    /**
     * Creates the visualization of the pointcut.
     * 
     * @param operation the operation acting as the pointcut
     * @param advice the advice of the message view
     * @return the interaction representing the pointcut in sequence diagram form
     */
    private static Interaction createPointcut(Operation operation, Interaction advice) {
        RamFactory ramFactory = RamFactory.eINSTANCE;
        
        Interaction pointcut = ramFactory.createInteraction();
        
        // get the first occurrence of the interaction
        // assume its a MessageOccurrence
        MessageOccurrenceSpecification oldReceiveEvent = (MessageOccurrenceSpecification) advice.getFragments().get(0);
        // copy the receive event
        MessageOccurrenceSpecification receiveEvent = EcoreUtil.copy(oldReceiveEvent);
        pointcut.getFragments().add(receiveEvent);
        
        // create the new lifeline
        Lifeline lifeline = EcoreUtil.copy(oldReceiveEvent.getCovered().get(0));
        pointcut.getLifelines().add(lifeline);
        
        lifeline.getCoveredBy().add(receiveEvent);
        
        // copy represents
        TypedElement represents = EcoreUtil.copy(lifeline.getRepresents());
        pointcut.getProperties().add((Reference) represents);
        
        lifeline.setRepresents(represents);
        
        // create the message
        Message message = EcoreUtil.copy(receiveEvent.getMessage());
        pointcut.getMessages().add(message);
        
        // create the send event
        Gate sendEvent = (Gate) EcoreUtil.copy(message.getSendEvent());
        pointcut.getFormalGates().add(sendEvent);
        
        // set the references
        receiveEvent.setMessage(message);
        sendEvent.setMessage(message);
        message.setSendEvent(sendEvent);
        message.setReceiveEvent(receiveEvent);
        
        // create the original behaviour execution fragment
        InteractionFragment originalBehavior = ramFactory.createOriginalBehaviorExecution();
        pointcut.getFragments().add(originalBehavior);
        
        originalBehavior.getCovered().add(lifeline);
        
        // create reply message and occurrences
        Message replyMessage = ramFactory.createMessage();
        pointcut.getMessages().add(replyMessage);
        
        MessageOccurrenceSpecification replySend = ramFactory.createMessageOccurrenceSpecification();
        replySend.getCovered().add(lifeline);
        replySend.setMessage(replyMessage);
        pointcut.getFragments().add(replySend);
        
        Gate replyReceive = ramFactory.createGate();
        replyReceive.setName("out_" + operation.getName());
        replyReceive.setMessage(replyMessage);
        pointcut.getFormalGates().add(replyReceive);
        
        replyMessage.setMessageSort(MessageSort.REPLY);
        replyMessage.setSendEvent(replySend);
        replyMessage.setReceiveEvent(replyReceive);
        replyMessage.setSignature(operation);
        
        return pointcut;
    }
}
