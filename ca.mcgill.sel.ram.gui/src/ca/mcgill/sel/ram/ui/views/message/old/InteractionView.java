package ca.mcgill.sel.ram.ui.views.message.old;

import java.util.HashMap;
import java.util.Map;

import org.mt4j.components.TransformSpace;
import org.mt4j.util.font.IFont;
import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.ram.CombinedFragment;
import ca.mcgill.sel.ram.DestructionOccurrenceSpecification;
import ca.mcgill.sel.ram.ExecutionStatement;
import ca.mcgill.sel.ram.Gate;
import ca.mcgill.sel.ram.Interaction;
import ca.mcgill.sel.ram.InteractionFragment;
import ca.mcgill.sel.ram.InteractionOperand;
import ca.mcgill.sel.ram.Lifeline;
import ca.mcgill.sel.ram.Message;
import ca.mcgill.sel.ram.MessageOccurrenceSpecification;
import ca.mcgill.sel.ram.MessageSort;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.ui.components.RamLineComponent;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.components.RamTextComponent;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.utils.Fonts;
import ca.mcgill.sel.ram.ui.views.TextView;

public class InteractionView extends RamRectangleComponent {
    
    public static final int PADDING_SMALL = 8;
    public static final int PADDING = 10;
    public static final int PADDING_BIG = 20;
    public static final int PADDING_LARGE = 30;
    
    protected static final IFont DEFAULT_FONT = Fonts.DEFAULT_FONT_INTERACTION;
    // set the line stipple manually since CSS doesn't seem to work here
    // number gotten from CSSStyle#getBorderStylePattern
    protected static final short LINE_STIPPLE_DASHED = (short) 0x00FF;
    
    protected static final float LIFELINE_START_X = 100;
    protected static final float LIFELINE_SPACE = 10;
    protected static final float MESSAGE_SPACE = 8;
    
    private static final String ORIGINAL_BEHAVIOR_TEXT = "*";
    
    // assumed value of lifeline height plus some space
    protected float currentMessagePositionY = 40;
    
    private Interaction interaction;
    
    private Map<Lifeline, LifelineView> lifelines;
    private Map<Message, MessageCallView> messages;
    
    private float currentLifelinePositionX = LIFELINE_START_X;
    
    public InteractionView(Interaction interaction) {
        this.interaction = interaction;
        
        lifelines = new HashMap<Lifeline, LifelineView>();
        messages = new HashMap<Message, MessageCallView>();
        
        // for debug purposes
        // setNoFill(false);
        // setFillColor(MTColor.GREEN);
        
        // would be possible here as well (only if width won't be needed)
        // buildInteraction();
    }
    
    public void build() {
        // go through all fragments as they define how things are built through their order
        for (InteractionFragment fragment : interaction.getFragments()) {
            buildFragment(fragment);
        }
        
        setMinimumHeight(currentMessagePositionY + PADDING_BIG);
        // this has to be called at the end if this component has been added to its parent already
        // updateParent();
    }
    
    protected void buildCombinedFragment(CombinedFragment fragment) {
        CombinedFragmentView combinedFragmentView = new CombinedFragmentView(fragment, this);
        
        // determine the width and position of the combined fragment depending on which lifelines it covers
        float x = Float.MAX_VALUE;
        float xEnd = 0;
        
        for (Lifeline lifeline : fragment.getCovered()) {
            // TODO: here instead of calling getLifelineView in all cases it could be checked whether it exists, if not,
            // add LIFELINE_START_X to xEnd
            // this allows to have lifelines in combined fragments, the only problem is lifelines that need to be in
            // several operands
            LifelineView lifelineView = getLifelineView(lifeline);
            
            float lifelineX = lifelineView.getPosition(TransformSpace.RELATIVE_TO_PARENT).getX();
            x = Math.min(lifelineX, x);
            xEnd = Math.max(lifelineX + (lifelineView.getWidth() / 2.0f), xEnd);
        }
        
        float width = xEnd - x + PADDING_LARGE;
        
        // indent a bit if it is a nested combined fragment
        // TODO: for more than 2 nested combined fragments it won't work again :/ containing UI component has to be
        // considered
        if (fragment.getContainer() != interaction) {
            x += PADDING_SMALL;
        }
        
        combinedFragmentView.translate(x, currentMessagePositionY);
        combinedFragmentView.setWidthLocal(width);
        
        // build after width has been set and position determined
        combinedFragmentView.build();
        
        addChild(combinedFragmentView);
        
        currentMessagePositionY += MESSAGE_SPACE;
    }
    
    protected void buildDestructionOccurrence(Lifeline lifeline) {
        LifelineView lifelineView = getLifelineView(lifeline);
        
        Vector3D lifelineLinePosition = lifelineView.getLinePosition();
        
        float x1 = lifelineLinePosition.getX() - MESSAGE_SPACE;
        float x2 = lifelineLinePosition.getX() + MESSAGE_SPACE;
        float y2 = currentMessagePositionY + (2 * MESSAGE_SPACE);
        
        RamLineComponent line = new RamLineComponent(x1, currentMessagePositionY, x2, y2);
        addChild(line);
        
        line = new RamLineComponent(x2, currentMessagePositionY, x1, y2);
        addChild(line);
        
        currentMessagePositionY += MESSAGE_SPACE;
    }
    
    protected void buildExecutionStatement(ExecutionStatement executionStatement) {
        TextView executionText = new TextView(executionStatement,
                RamPackage.Literals.EXECUTION_STATEMENT__SPECIFICATION);
        executionText.setFont(DEFAULT_FONT);
        executionText.setNoStroke(false);
        executionText.setNoFill(false);
        executionText.setFillColor(Colors.DEFAULT_ELEMENT_FILL_COLOR);
        addChild(executionText);
        
        // move it to the correct position
        // cannot be centered because it can get quite long depending on the statement
        LifelineView lifelineView = getLifelineView(executionStatement.getCovered().get(0));
        float x = lifelineView.getLinePosition().getX() - PADDING_BIG;
        executionText.translate(x, currentMessagePositionY);
        currentMessagePositionY += executionText.getHeight() + MESSAGE_SPACE;
    }
    
    protected void buildFragment(InteractionFragment fragment) {
        RamPackage ramPackage = RamPackage.eINSTANCE;
        
        if (fragment.eClass() == ramPackage.getMessageOccurrenceSpecification()) {
            MessageOccurrenceSpecification messageOccurrenceSpecification = (MessageOccurrenceSpecification) fragment;
            
            // create a lifeline if it doesn't exist yet
            buildLifeline(messageOccurrenceSpecification.getCovered().get(0));
            // create the message
            buildMessage(messageOccurrenceSpecification.getMessage());
        } else if (fragment.eClass() == ramPackage.getOriginalBehaviorExecution()) {
            buildOriginalBehaviourExecution(fragment.getCovered().get(0));
        } else if (fragment.eClass() == ramPackage.getDestructionOccurrenceSpecification()) {
            DestructionOccurrenceSpecification destructionOccurrenceSpecification =
                    (DestructionOccurrenceSpecification) fragment;
            
            // create the message if it doesn't exist yet
            buildMessage(destructionOccurrenceSpecification.getMessage());
            
            buildDestructionOccurrence(destructionOccurrenceSpecification.getCovered().get(0));
        } else if (fragment.eClass() == ramPackage.getExecutionStatement()) {
            buildExecutionStatement((ExecutionStatement) fragment);
        } else if (fragment.eClass() == ramPackage.getCombinedFragment()) {
            buildCombinedFragment((CombinedFragment) fragment);
        }
    }
    
    protected void buildInteractionOperand(InteractionOperand interactionOperand) {
        // go through all fragments as they define how things are built through their order
        for (InteractionFragment fragment : interactionOperand.getFragments()) {
            buildFragment(fragment);
        }
    }
    
    protected void buildLifeline(Lifeline lifeline) {
        // create lifeline view if it hasn't been created yet
        if (!lifelines.containsKey(lifeline)) {
            LifelineView lifelineView = new LifelineView(lifeline);
            addChild(lifelineView);
            
            // move the lifelines along the x axis
            // TODO: y not always the same (e.g., in case of a create message)
            lifelineView.translate(currentLifelinePositionX, PADDING);
            currentLifelinePositionX += lifelineView.getWidth() + LIFELINE_SPACE;
            currentMessagePositionY = Math.max(lifelineView.getHeight() + (2 * MESSAGE_SPACE), currentMessagePositionY);
            
            lifelines.put(lifeline, lifelineView);
        }
    }
    
    protected void buildMessage(Message message) {
        // for each message this can be called twice as there are two events associated
        if (!messages.containsKey(message)) {
            // figure out the width of the message view first
            float x = 0;
            float width = 0;
            LifelineView receivinglifelineView = null;
            
            if (message.getSendEvent() instanceof MessageOccurrenceSpecification) {
                MessageOccurrenceSpecification sendEvent = (MessageOccurrenceSpecification) message.getSendEvent();
                LifelineView lifelineView = getLifelineView(sendEvent.getCovered().get(0));
                
                // only if it is not a reply message it should be moved on the x axis
                // otherwise it has to start at 0 and the messages arrow will be drawn in the opposite direction by the
                // message view itself
                switch (message.getMessageSort()) {
                    case SYNCH_CALL:
                    case DELETE_MESSAGE:
                    case CREATE_MESSAGE:
                        x = lifelineView.getLinePosition().getX();
                        break;
                    case REPLY:
                        if (!(message.getReceiveEvent() instanceof Gate)) {
                            x = lifelineView.getLinePosition().getX();
                        } else {
                            width = lifelineView.getLinePosition().getX() - x;
                        }
                        break;
                }
            }
            
            if (message.getReceiveEvent() instanceof MessageOccurrenceSpecification) {
                MessageOccurrenceSpecification receiveEvent = (MessageOccurrenceSpecification) message
                        .getReceiveEvent();
                receivinglifelineView = getLifelineView(receiveEvent.getCovered().get(0));
                if (receivinglifelineView != null) {
                    // in case its a create message the width has to be reduced because it will be ending at the
                    // lifeline box
                    if (message.getMessageSort() == MessageSort.CREATE_MESSAGE) {
                        width = receivinglifelineView.getPosition(TransformSpace.RELATIVE_TO_PARENT).getX() - x;
                    } else if (message.getMessageSort() == MessageSort.REPLY) {
                        width = x - receivinglifelineView.getLinePosition().getX();
                        x = receivinglifelineView.getLinePosition().getX();
                    } else {
                        width = receivinglifelineView.getLinePosition().getX() - x;
                    }
                }
                
                // if it is a self message reset the width so the message view will determine its width based on the
                // signature field
                if (message.isSelfMessage()) {
                    width = 0;
                }
            }
            
            MessageCallView messageView = new MessageCallView(width, message);
            addChild(messageView);
            
            // if it is the first message, move it a bit to the top, otherwise it there will be a lot of space on the
            // top
            if (message.getMessageSort() == MessageSort.CREATE_MESSAGE && messages.isEmpty()) {
                currentMessagePositionY = PADDING;
            }
            
            messageView.translate(x, currentMessagePositionY);
            
            // in case its a create message it has to be moved up to the lifeline
            if (message.getMessageSort() == MessageSort.CREATE_MESSAGE) {
                receivinglifelineView.translate(receivinglifelineView.getPosition(TransformSpace.LOCAL).getX(),
                        currentMessagePositionY);
                currentMessagePositionY += 2 * MESSAGE_SPACE;
            }
            
            currentMessagePositionY += messageView.getHeight() + MESSAGE_SPACE;
            
            // move the lifeline if the message is wider than the lifelines position is
            if (!message.isSelfMessage() && receivinglifelineView != null && width != messageView.getWidth()) {
                Vector3D position = receivinglifelineView.getPosition(TransformSpace.RELATIVE_TO_PARENT);
                position.setX(x + messageView.getWidth());
                if (message.getMessageSort() != MessageSort.CREATE_MESSAGE) {
                    position.setX(position.getX() - (receivinglifelineView.getWidth() / 2.0f));
                }
                receivinglifelineView.setPositionRelativeToParent(position);
                // update x for next ifeline
                currentLifelinePositionX = position.getX() + receivinglifelineView.getWidth() + LIFELINE_SPACE;
            }
            
            if (message.isSelfMessage()) {
                // revert the adding after adding a lifeline
                float tempX = currentLifelinePositionX - (receivinglifelineView.getWidth() + LIFELINE_START_X);
                // and just add the message views width
                tempX += messageView.getWidth();
                
                // take the max of both (otherwise the lifeline might clash with an existing one)
                currentLifelinePositionX = Math.max(currentLifelinePositionX, tempX);
            }
            
            messages.put(message, messageView);
        }
    }
    
    protected void buildOriginalBehaviourExecution(Lifeline lifeline) {
        RamTextComponent originalBehaviourText = 
                new RamTextComponent(Fonts.DEFAULT_FONT_MEDIUM, ORIGINAL_BEHAVIOR_TEXT);
        
        originalBehaviourText.setNoStroke(false);
        originalBehaviourText.setNoFill(false);
        originalBehaviourText.setFillColor(Colors.DEFAULT_ELEMENT_FILL_COLOR);
        // for some reason some space on the bottom is already there
        originalBehaviourText.setBufferSize(Cardinal.NORTH, PADDING);
        originalBehaviourText.setBufferSize(Cardinal.EAST, PADDING_SMALL);
        originalBehaviourText.setBufferSize(Cardinal.WEST, PADDING_SMALL);
        originalBehaviourText.setBufferSize(Cardinal.SOUTH, 0);
        
        addChild(originalBehaviourText);
        
        // now move it to the appropriate position
        moveFragmentField(originalBehaviourText, lifeline);
    }
    
    protected Map<Lifeline, LifelineView> getLifelines() {
        return lifelines;
    }
    
    protected LifelineView getLifelineView(Lifeline lifeline) {
        LifelineView lifelineView = lifelines.get(lifeline);
        
        if (lifelineView == null) {
            buildLifeline(lifeline);
            lifelineView = lifelines.get(lifeline);
        }
        
        return lifelineView;
    }
    
    protected void increasePositionY(float height) {
        currentMessagePositionY += height;
    }
    
    protected void moveFragmentField(RamTextComponent fragmentField, Lifeline lifeline) {
        // now move it to the appropriate position (center of the lifelines line)
        LifelineView lifelineView = getLifelineView(lifeline);
        float x = lifelineView.getLinePosition().getX() - (fragmentField.getWidth() / 2.0f);
        fragmentField.translate(x, currentMessagePositionY);
        currentMessagePositionY += fragmentField.getHeight() + (MESSAGE_SPACE / 2.0f);
    }
    
}
