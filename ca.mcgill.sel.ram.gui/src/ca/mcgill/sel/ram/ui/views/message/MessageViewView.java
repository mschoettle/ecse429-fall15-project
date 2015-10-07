package ca.mcgill.sel.ram.ui.views.message;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.mt4j.components.TransformSpace;
import org.mt4j.input.gestureAction.TapAndHoldVisualizer;
import org.mt4j.input.inputProcessors.componentProcessors.panProcessor.PanProcessorTwoFingers;
import org.mt4j.input.inputProcessors.componentProcessors.tapAndHoldProcessor.TapAndHoldProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.zoomProcessor.ZoomProcessor;
import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.commons.emf.util.EMFModelUtil;
import ca.mcgill.sel.ram.AbstractMessageView;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.AspectMessageView;
import ca.mcgill.sel.ram.AssignmentStatement;
import ca.mcgill.sel.ram.CombinedFragment;
import ca.mcgill.sel.ram.ExecutionStatement;
import ca.mcgill.sel.ram.FragmentContainer;
import ca.mcgill.sel.ram.ImplementationClass;
import ca.mcgill.sel.ram.Interaction;
import ca.mcgill.sel.ram.InteractionFragment;
import ca.mcgill.sel.ram.InteractionOperand;
import ca.mcgill.sel.ram.LayoutElement;
import ca.mcgill.sel.ram.Lifeline;
import ca.mcgill.sel.ram.Message;
import ca.mcgill.sel.ram.MessageEnd;
import ca.mcgill.sel.ram.MessageOccurrenceSpecification;
import ca.mcgill.sel.ram.MessageSort;
import ca.mcgill.sel.ram.MessageView;
import ca.mcgill.sel.ram.Operation;
import ca.mcgill.sel.ram.OriginalBehaviorExecution;
import ca.mcgill.sel.ram.RamFactory;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.StructuralFeature;
import ca.mcgill.sel.ram.Type;
import ca.mcgill.sel.ram.impl.ContainerMapImpl;
import ca.mcgill.sel.ram.impl.ElementMapImpl;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.components.RamTextComponent;
import ca.mcgill.sel.ram.ui.events.MouseWheelProcessor;
import ca.mcgill.sel.ram.ui.events.RightClickDragProcessor;
import ca.mcgill.sel.ram.ui.layouts.AbstractBaseLayout;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.utils.Constants;
import ca.mcgill.sel.ram.ui.utils.Strings;
import ca.mcgill.sel.ram.ui.views.AbstractView;
import ca.mcgill.sel.ram.ui.views.IRelationshipEndView;
import ca.mcgill.sel.ram.ui.views.RamEnd;
import ca.mcgill.sel.ram.ui.views.RamEnd.Position;
import ca.mcgill.sel.ram.ui.views.TextView;
import ca.mcgill.sel.ram.ui.views.handler.ITextViewHandler;
import ca.mcgill.sel.ram.ui.views.message.handler.MessageViewHandlerFactory;
import ca.mcgill.sel.ram.ui.views.message.handler.IMessageViewHandler;
import ca.mcgill.sel.ram.util.RAMModelUtil;
import ca.mcgill.sel.ram.util.RamSwitch;

/**
 * The view responsible for visualizing {@link MessageView}s.
 * It is responsible for maintaining its children (lifelines, messages and fragments)
 * and layouting the message view as a whole. Layouting is done from top down in the order of how fragments are ordered.
 * 
 * @author mschoettle
 */
public class MessageViewView extends AbstractView<IMessageViewHandler>
        implements INotifyChangedListener {
    
    /**
     * The width for the invisible box that contains events and the line.
     */
    public static final float BOX_WIDTH = 25f;
    
    /**
     * The height for the invisible box that contains events and the line.
     */
    public static final float BOX_HEIGHT = 20f;
    
    /**
     * The view representing a {@link ca.mcgill.sel.ram.Gate}.
     * It is an invisible component that can be used for a relationship view.
     * 
     * @see IRelationshipEndView
     * @author mschoettle
     */
    private class GateView extends RamRectangleComponent implements IRelationshipEndView {
        
        /**
         * Creates a new view for a gate at the given position and dimensions.
         * The positions and dimension should reflect the corresponding ones to the opposite end.
         * 
         * @param x the x position
         * @param y the y position
         * @param width the width
         * @param height the height
         */
        public GateView(float x, float y, float width, float height) {
            super(x, y, width, height);
        }
        
        @Override
        public void moveRelationshipEnd(RamEnd<?, ?> end, Position oldPosition, Position newPosition) {
            updateRelationshipEnd(end);
        }
        
        @Override
        public void updateRelationshipEnd(RamEnd<?, ?> end) {
            RamEnd<?, ?> otherEnd = end.getOpposite();
            
            if (end.getLocation().getY() != otherEnd.getLocation().getY()) {
                Vector3D location = new Vector3D(otherEnd.getLocation());
                location.setX(end.getLocation().getX());
                end.setLocation(location);
                // Always force the position to be right.
                end.setPosition(Position.RIGHT);
                setPositionRelativeToParent(location);
            }
        }
        
        @Override
        public void removeRelationshipEnd(RamEnd<?, ?> end) {
            destroy();
        }
        
    }
    
    /**
     * The internal layout of this view.
     * It receives layout requests from children and performs appropriate layouting.
     * 
     * @author mschoettle
     */
    private class InternalLayout extends AbstractBaseLayout {
        
        @Override
        public void layout(RamRectangleComponent component, LayoutUpdatePhase updatePhase) {
            layoutCombinedFragments();
        }
        
    }
    
    /**
     * The fixed y position of lifelines.
     */
    public static final float LIFELINE_Y = 100;
    private static final float LIFELINE_START_X = 100;
    private static final float LIFEINE_SPACING = 20;
    
    private AbstractMessageView messageView;
    private Interaction specification;
    private Operation specifies;
    private ContainerMapImpl layout;
    
    private Map<Lifeline, LifelineView> lifelines;
    private Map<Message, MessageCallView> messages;
    private Map<CombinedFragment, CombinedFragmentView> combinedFragments;
    private Map<InteractionFragment, RamRectangleComponent> fragments;
    
    private boolean building;
    
    /**
     * Creates a new view for the given message view.
     * 
     * @param messageView the {@link MessageView} to visualize
     * @param layout the layout of this message view
     * @param width the width to use
     * @param height the height to use
     */
    public MessageViewView(AbstractMessageView messageView, ContainerMapImpl layout, float width, float height) {
        super(width, height);
        this.messageView = messageView;
        this.layout = layout;
        if (messageView instanceof MessageView) {
            MessageView actualMessageView = (MessageView) messageView;
            specification = actualMessageView.getSpecification();
            specifies = actualMessageView.getSpecifies();
        } else {
            AspectMessageView actualMessageView = (AspectMessageView) messageView;
            specification = actualMessageView.getAdvice();
            specifies = actualMessageView.getPointcut();
        }
        
        lifelines = new HashMap<Lifeline, LifelineView>();
        messages = new HashMap<Message, MessageCallView>();
        combinedFragments = new HashMap<CombinedFragment, CombinedFragmentView>();
        fragments = new HashMap<InteractionFragment, RamRectangleComponent>();
        
        setNoFill(true);
        setLayout(new InternalLayout());
        
        build();
        layoutMessageView();
        
        EMFEditUtil.addListenerFor(specification, this);
        EMFEditUtil.addListenerFor(layout, this);
    }
    
    @Override
    public void destroy() {
        EMFEditUtil.removeListenerFor(specification, this);
        EMFEditUtil.removeListenerFor(layout, this);
        
        super.destroy();
    }
    
    @Override
    protected void registerInputProcessors() {
        registerInputProcessor(new PanProcessorTwoFingers(RamApp.getApplication()));
        registerInputProcessor(new RightClickDragProcessor(RamApp.getApplication()));
        registerInputProcessor(new ZoomProcessor(RamApp.getApplication()));
        registerInputProcessor(new MouseWheelProcessor(RamApp.getApplication()));
    }
    
    /**
     * Builds the visualization of this view.
     * Takes care of initializing all contents and the actual elements of the message view.
     */
    private void build() {
        building = true;
        
        for (Lifeline lifeline : specification.getLifelines()) {
            LayoutElement layoutElement = layout.getValue().get(lifeline);
            
            // This should only happen for lifelines created outside TouchCORE, e.g., in the Eclipse editor.
            if (layoutElement == null) {
                layoutElement = RamFactory.eINSTANCE.createLayoutElement();
                layout.getValue().put(lifeline, layoutElement);
            }
            
            addLifelineView(lifeline);
        }
        
        buildFragments(specification);
        
        building = false;
    }
    
    /**
     * Builds all fragments in the appropriate order to display in this view.
     * Can be called recursively for different containers.
     * 
     * @param container the {@link FragmentContainer} containing the fragments
     */
    private void buildFragments(FragmentContainer container) {
        for (InteractionFragment fragment : container.getFragments()) {
            switch (fragment.eClass().getClassifierID()) {
                case RamPackage.MESSAGE_OCCURRENCE_SPECIFICATION:
                    MessageOccurrenceSpecification messageEvent = (MessageOccurrenceSpecification) fragment;
                    
                    if (!messages.containsKey(messageEvent.getMessage())) {
                        addMessageView(messageEvent.getMessage());
                    }
                    break;
                case RamPackage.EXECUTION_STATEMENT:
                    ExecutionStatement executionStatement = (ExecutionStatement) fragment;
                    addStatementView(executionStatement);
                    break;
                case RamPackage.COMBINED_FRAGMENT:
                    CombinedFragment combinedFragment = (CombinedFragment) fragment;
                    addCombinedFragment(combinedFragment);
                    
                    for (InteractionOperand operand : combinedFragment.getOperands()) {
                        buildFragments(operand);
                    }
                    break;
                case RamPackage.ASSIGNMENT_STATEMENT:
                    AssignmentStatement assignmentStatement = (AssignmentStatement) fragment;
                    addAssignmentStatement(assignmentStatement);
                    break;
                case RamPackage.ORIGINAL_BEHAVIOR_EXECUTION:
                    OriginalBehaviorExecution originalBehaviorExecution = (OriginalBehaviorExecution) fragment;
                    addOriginalBehaviourView(originalBehaviorExecution);
                    break;
            }
        }
    }
    
    /**
     * Adds a new view for the given lifeline to this view.
     * 
     * @param lifeline the {@link Lifeline} a view is required for
     */
    private void addLifelineView(Lifeline lifeline) {
        LayoutElement layoutElement = layout.getValue().get(lifeline);
        
        LifelineView lifelineView = new LifelineView(this, lifeline, layoutElement);
        addChild(lifelineView);
        lifelineView.setHandler(MessageViewHandlerFactory.INSTANCE.getLifelineViewHandler());
        
        lifelines.put(lifeline, lifelineView);
    }
    
    /**
     * Removes the given lifeline from this view.
     * 
     * @param lifeline the {@link Lifeline} to remove
     */
    private void removeLifelineView(Lifeline lifeline) {
        LifelineView lifelineView = lifelines.remove(lifeline);
        
        removeChild(lifelineView);
        lifelineView.destroy();
    }
    
    /**
     * Adds a new view for the given message to this view.
     * A message is represented by a view on each end and a view representing the actual call.
     * The end views are added to the corresponding lifelines.
     * In case an end is a gate and not placed on a lifeline,
     * a {@link GateView} is used instead and added to this view at the left side.
     * 
     * @param message the {@link Message} to add
     */
    private void addMessageView(Message message) {
        LifelineView toView = null;
        LifelineView fromView = null;
        RamRectangleComponent sendEventView = null;
        RamRectangleComponent receiveEventView = null;
        MessageEnd sendEvent = message.getSendEvent();
        MessageEnd receiveEvent = message.getReceiveEvent();
        
        Operation signature = message.getSignature();
        boolean messageViewDefined = false;
        
        if (signature != null && specifies != signature) {
            Aspect aspect = EMFModelUtil.getRootContainerOfType(signature, RamPackage.Literals.ASPECT);
            /**
             * Make sure that the signature still exists in the aspect.
             * It could have been deleted and would then cause problems here.
             */
            if (aspect != null) {
                messageViewDefined = RAMModelUtil.isMessageViewDefined(aspect, signature);
            }
        }
        
        if (sendEvent instanceof MessageOccurrenceSpecification) {
            MessageOccurrenceSpecification event = (MessageOccurrenceSpecification) sendEvent;
            
            fromView = lifelines.get(event.getCovered().get(0));
            int modelIndex = event.getContainer().getFragments().indexOf(sendEvent);
            
            if (fromView != null) {
                boolean allowMessageCreation = false;
                // Allow creation after sending a message where no return is expected.
                if (signature != null && signature.getReturnType() != null) {
                    Type returnType = signature.getReturnType();
                    allowMessageCreation = message.getMessageSort() != MessageSort.REPLY
                            && !message.isSelfMessage()
                            && (returnType.eClass() == RamPackage.Literals.RVOID
                                    || message.getMessageSort() == MessageSort.CREATE_MESSAGE
                                    || signature.eContainer() instanceof ImplementationClass
                                    // When a message view is defined, there will be no reply, so we need to allow it.
                                    || messageViewDefined);
                }
                
                sendEventView = fromView.addMessageEnd(event, modelIndex, allowMessageCreation);
            }
        }
        
        if (receiveEvent instanceof MessageOccurrenceSpecification) {
            MessageOccurrenceSpecification event = (MessageOccurrenceSpecification) receiveEvent;
            
            toView = lifelines.get(event.getCovered().get(0));
            int modelIndex = event.getContainer().getFragments().indexOf(receiveEvent);
            
            if (toView != null) {
                if (message.getMessageSort() == MessageSort.CREATE_MESSAGE) {
                    receiveEventView = toView;
                } else {
                    boolean allowMessageCreation = message.getMessageSort() != MessageSort.DELETE_MESSAGE;
                    if (signature != null) {
                        // Don't allow message creation if the receiving end is on an implementation class.
                        // If it is a self message it cannot be on an implementation class and is allowed.
                        allowMessageCreation = allowMessageCreation
                                && !(signature.eContainer() instanceof ImplementationClass)
                                && (!messageViewDefined || message.isSelfMessage());
                    }
                    
                    receiveEventView = toView.addMessageEnd(event, modelIndex, allowMessageCreation);
                }
            }
        }
        
        if (sendEventView == null) {
            Vector3D oppositePosition = receiveEventView.getPosition(TransformSpace.GLOBAL);
            sendEventView = new GateView(0, oppositePosition.getY(), BOX_WIDTH, BOX_HEIGHT);
            addChild(sendEventView);
        } else if (receiveEventView == null) {
            Vector3D oppositePosition = sendEventView.getPosition(TransformSpace.GLOBAL);
            receiveEventView = new GateView(0, oppositePosition.getY(), BOX_WIDTH, BOX_HEIGHT);
            addChild(receiveEventView);
        }
        
        MessageCallView messageCallView = new MessageCallView(message, sendEventView, receiveEventView);
        addChild(messageCallView);
        messageCallView.setHandler(MessageViewHandlerFactory.INSTANCE.getMessageHandler());
        messages.put(message, messageCallView);
        
        layoutMessageView();
        
        messageCallView.updateLines();
    }
    
    /**
     * Removes the given message from this view.
     * The ends are removed from their attached views.
     * 
     * @param message the {@link Message} to remove
     */
    private void removeMessageView(Message message) {
        MessageCallView messageCallView = messages.remove(message);
        
        IRelationshipEndView fromView = (IRelationshipEndView) messageCallView.getFromEnd().getComponentView();
        IRelationshipEndView toView = (IRelationshipEndView) messageCallView.getToEnd().getComponentView();
        fromView.removeRelationshipEnd(messageCallView.getFromEnd());
        toView.removeRelationshipEnd(messageCallView.getToEnd());
        
        removeChild(messageCallView);
        messageCallView.destroy();
        
        layoutMessageView();
    }
    
    /**
     * Requests that all message lines are updated.
     * This triggers a recalculation and redrawing of each message call.
     */
    public void updateMessageViews() {
        for (MessageCallView messageCall : messages.values()) {
            messageCall.updateLines();
        }
    }
    
    /**
     * Adds the given execution statement to the lifeline view it covers.
     * 
     * @param executionStatement the {@link ExecutionStatement} to add
     */
    private void addStatementView(ExecutionStatement executionStatement) {
        Lifeline lifeline = executionStatement.getCovered().get(0);
        LifelineView lifelineView = lifelines.get(lifeline);
        int modelIndex = getModelIndex(executionStatement);
        
        // Currently we assume that the statements specification is always an opaque expression.
        // If this changes we need to adaptively get the actual value.
        TextView textView = new TextView(executionStatement, RamPackage.Literals.EXECUTION_STATEMENT__SPECIFICATION);
        textView.setNoFill(false);
        textView.setNoStroke(false);
        textView.setFillColor(Colors.MESSAGE_STATEMENT_FILL_COLOR);
        
        ITextViewHandler textViewHandler = MessageViewHandlerFactory.INSTANCE.getExecutionStatementHandler();
        
        textView.registerTapProcessor(textViewHandler);
        textView.registerInputProcessor(new TapAndHoldProcessor(RamApp.getApplication(),
                Constants.TAP_AND_HOLD_DURATION));
        textView.addGestureListener(TapAndHoldProcessor.class, textViewHandler);
        textView.addGestureListener(TapAndHoldProcessor.class,
                new TapAndHoldVisualizer(RamApp.getApplication(), RamApp.getActiveScene().getCanvas()));
        textView.setPlaceholderText(Strings.PH_SPECIFY_STATEMENT);
        addChild(textView);
        
        lifelineView.addStatement(textView, executionStatement, modelIndex);
        fragments.put(executionStatement, textView);
        
        layoutMessageView();
    }
    
    /**
     * Creates a view for the given assignment statement and adds it to the lifeline view it covers.
     * 
     * @param assignmentStatement the {@link AssignmentStatement} to add
     */
    private void addAssignmentStatement(AssignmentStatement assignmentStatement) {
        Lifeline lifeline = assignmentStatement.getCovered().get(0);
        LifelineView lifelineView = lifelines.get(lifeline);
        int modelIndex = getModelIndex(assignmentStatement);
        
        AssignmentStatementView assignmentView = new AssignmentStatementView(assignmentStatement);
        addChild(assignmentView);
        assignmentView.setHandler(MessageViewHandlerFactory.INSTANCE.getAssignmentStatementHandler());
        
        lifelineView.addAssignment(assignmentView, assignmentStatement, modelIndex);
        fragments.put(assignmentStatement, assignmentView);
        
        layoutMessageView();
    }
    
    /**
     * Adds the given original behaviour execution fragment to the lifeline view it covers.
     * 
     * @param originalBehaviorExecution the {@link OriginalBehaviorExecution} to add
     */
    private void addOriginalBehaviourView(OriginalBehaviorExecution originalBehaviorExecution) {
        Lifeline lifeline = originalBehaviorExecution.getCovered().get(0);
        LifelineView lifelineView = lifelines.get(lifeline);
        int modelIndex = getModelIndex(originalBehaviorExecution);
        
        // Currently we assume that the statements specification is always an opaque expression.
        // If this changes we need to adaptively get the actual value.
        RamTextComponent textView = new RamTextComponent("*");
        textView.setNoFill(false);
        textView.setNoStroke(false);
        textView.setFillColor(Colors.DEFAULT_ELEMENT_FILL_COLOR);
        textView.setBufferSize(Cardinal.NORTH, 10);
        textView.setBufferSize(Cardinal.EAST, 10);
        textView.setBufferSize(Cardinal.WEST, 10);
        textView.setBufferSize(Cardinal.SOUTH, 0);
        
        addChild(textView);
        
        lifelineView.addOriginalBehaviour(textView, originalBehaviorExecution, modelIndex);
        fragments.put(originalBehaviorExecution, textView);
        
        layoutMessageView();
    }
    
    /**
     * Returns the index of the given fragment in the model.
     * 
     * @param fragment the {@link InteractionFragment}
     * @return the index in the model of the fragment
     */
    private static int getModelIndex(InteractionFragment fragment) {
        FragmentContainer container = fragment.getContainer();
        return container.getFragments().indexOf(fragment);
    }
    
    /**
     * Removes the interaction fragment from its covered lifeline view.
     * 
     * @param interactionFragment the {@link InteractionFragment} to remove
     */
    private void removeInteractionFragment(InteractionFragment interactionFragment) {
        Lifeline lifeline = interactionFragment.getCovered().get(0);
        LifelineView lifelineView = lifelines.get(lifeline);
        
        lifelineView.removeInteractionFragment(interactionFragment);
        layoutMessageView();
    }
    
    /**
     * Adds the given combined fragment to this view.
     * 
     * @param combinedFragment the {@link CombinedFragment} to add
     */
    private void addCombinedFragment(CombinedFragment combinedFragment) {
        CombinedFragmentView combinedFragmentView = new CombinedFragmentView(this, combinedFragment);
        addChild(combinedFragmentView);
        
        combinedFragments.put(combinedFragment, combinedFragmentView);
        
        layoutMessageView();
    }
    
    /**
     * Removes the combined fragment from this view.
     * Removes the combined fragment from all covered lifeline views.
     * 
     * @param combinedFragment the {@link CombinedFragment} to remove
     */
    private void removeCombinedFragment(CombinedFragment combinedFragment) {
        CombinedFragmentView combinedFragmentView = combinedFragments.remove(combinedFragment);
        
        // For debug purpose only.
        if (combinedFragmentView == null) {
            System.err.println("Should not happen: Something went wrong in the order of removing objects.");
        }
        
        for (Lifeline lifeline : combinedFragment.getCovered()) {
            LifelineView lifelineView = lifelines.get(lifeline);
            lifelineView.removeCombinedFragment(combinedFragment);
        }
        
        removeChild(combinedFragmentView);
        combinedFragmentView.destroy();
        
        layoutMessageView();
    }
    
    /**
     * Returns a list of all lifeline views this view visualizes.
     * 
     * @return the list of all lifeline views
     */
    public Collection<LifelineView> getLifelineViews() {
        return lifelines.values();
    }
    
    /**
     * Returns the lifeline view for the given lifeline.
     * If there is no view for the lifeline, null is returned.
     * 
     * @param lifeline the {@link Lifeline}
     * @return the lifeline view for the given lifeline, null if no view exists
     */
    public LifelineView getLifelineView(Lifeline lifeline) {
        return lifelines.get(lifeline);
    }
    
    /**
     * Returns the {@link AbstractMessageView} visualized by this view.
     * 
     * @return the message view
     */
    public AbstractMessageView getMessageView() {
        return messageView;
    }
    
    /**
     * Returns the {@link Interaction} visualized by this view.
     * 
     * @return the interaction
     */
    public Interaction getSpecification() {
        return specification;
    }
    
    /**
     * Returns the operation this message view specifies.
     * In case of an {@link AspectMessageView}, it is the pointcut.
     * 
     * @return the operation this message view specifies.
     */
    public Operation getSpecifies() {
        return specifies;
    }
    
    /**
     * Layouts the complete view.
     * This includes lifelines, combined fragments, other fragments and the message calls.
     */
    public void layoutMessageView() {
        if (building) {
            return;
        }
        
        layoutLifelines();
        layoutCombinedFragments();
        
        layoutFragments(specification, LIFELINE_Y);
        
        updateMessageViews();
    }
    
    /**
     * Layouts all lifelines.
     * The lifelines are placed at a certain height and along the x-axis with a specified distance between them.
     * Lifelines that contain a stereotype in their name are placed slightly higher such that the bottom of each name
     * is flush among all lifelines.
     */
    private void layoutLifelines() {
        float currentLifelineX = LIFELINE_START_X;
        Set<Lifeline> layoutedLifelines = new HashSet<Lifeline>();
        
        // Layout lifelines if they have no position. Otherwise they might have been moved on the x-axis,
        // which we allow to do.
        for (InteractionFragment fragment : specification.getFragments()) {
            // In case the message add event hasn't been received yet there will be no lifeline that is covered.
            // This is the case when a message and a reply are added.
            // The structure exists, but not all commands have been executed.
            for (Lifeline lifeline : fragment.getCovered()) {
                if (!layoutedLifelines.contains(lifeline)) {
                    LifelineView lifelineView = lifelines.get(lifeline);
                    LayoutElement layoutElement = lifelineView.getLayoutElement();
                    
                    if (layoutElement.getX() == 0) {
                        lifelineView.getLayoutElement().setX(currentLifelineX);
                        lifelineView.getLayoutElement().setY(LIFELINE_Y);
                    }
                    
                    /**
                     * Lifelines of metaclasses are double as high as regular lifelines.
                     * This causes problems when dealing with combined fragments or when drawing a message
                     * from a message view of a create message to one (as the first message).
                     * Therefore, these lifelines are moved up so that all lifelines are flush on the bottom
                     * of their name container.
                     * @see issue #230
                     */
                    if (lifeline.getRepresents() instanceof StructuralFeature) {
                        StructuralFeature structuralFeature = (StructuralFeature) lifeline.getRepresents();
                        
                        if (structuralFeature.isStatic()) {
                            float y = LIFELINE_Y - (lifelineView.getNameHeight() / 2);
                            if (y != lifelineView.getLayoutElement().getY()) {
                                lifelineView.getLayoutElement().setY(y);
                            }
                        }
                    }
                    
                    currentLifelineX = currentLifelineX + lifelineView.getWidth() + LIFEINE_SPACING;
                    layoutedLifelines.add(lifeline);
                }
            }
        }
    }
    
    /**
     * Layouts all fragments along this view.
     * Can be called recursively for different fragment containers.
     * 
     * @param container the {@link FragmentContainer} for which fragments should be layouted
     * @param nextfragmentY the y-position for the next fragment
     * @return the y-position for the next fragment
     */
    private float layoutFragments(FragmentContainer container, float nextfragmentY) {
        float previousFragmentY = nextfragmentY;
        float currentFragmentY = nextfragmentY;
        boolean receiveEventNext = false;
        
        // Make sure that all messages are updated, since this might be necessary due to moving the lifelines around.
        for (InteractionFragment fragment : container.getFragments()) {
            int index = fragment.getContainer().getFragments().indexOf(fragment);
            
            for (Lifeline lifeline : fragment.getCovered()) {
                LifelineView lifelineView = lifelines.get(lifeline);
                
                // In case of a create message the lifeline has to be moved down depending on the name height.
                if (fragment instanceof MessageOccurrenceSpecification) {
                    MessageOccurrenceSpecification messageEnd = (MessageOccurrenceSpecification) fragment;
                    
                    // If the message was removed, but the message ends are still there,
                    // ignore them.
                    if (!messages.containsKey(messageEnd.getMessage())) {
                        continue;
                    }
                    
                    if (messageEnd.getMessage().getMessageSort() == MessageSort.CREATE_MESSAGE
                            && messageEnd.getMessage().getReceiveEvent() == messageEnd) {
                        float lifelineNameHeight = lifelineView.getNameHeight();
                        float difference = lifelineNameHeight - BOX_HEIGHT;
                        float lifelineY = currentFragmentY - (difference / 2f);
                        /**
                         * Convert global position to relative one.
                         */
                        lifelineY = getGlobalVecToParentRelativeSpace(lifelineView, new Vector3D(0, lifelineY)).y;
                        lifelineView.getLayoutElement().setY(lifelineY);
                        currentFragmentY += lifelineNameHeight;
                        receiveEventNext = false;
                        continue;
                    }
                }
                
                RamRectangleComponent fragmentView = lifelineView.getFragmentView(fragment, index);
                // view is null when message was just removed
                if (fragmentView != null) {
                    RamRectangleComponent spacer = lifelineView.getSpacerForFragmentAt(fragment, index);
                    
                    // Start with the position of the first spacer.
                    if (currentFragmentY == LIFELINE_Y) {
                        currentFragmentY = spacer.getPosition(TransformSpace.GLOBAL).getY();
                    }
                    
                    if (!receiveEventNext) {
                        currentFragmentY += BOX_HEIGHT;
                    }
                    
                    Vector3D currentPosition = fragmentView.getPosition(TransformSpace.GLOBAL);
                    
                    if (currentPosition.getY() != currentFragmentY) {
                        float difference = currentFragmentY - currentPosition.getY();
                        float height = spacer.getHeight() + difference;
                        // Fix the height to not go below the minimum size.
                        if (height < BOX_HEIGHT) {
                            height = BOX_HEIGHT;
                        }
                        
                        spacer.setMinimumHeight(height);
                        // Need to inform parent manually.
                        spacer.updateParent();
                    }
                    
                    if (fragment instanceof MessageOccurrenceSpecification) {
                        MessageOccurrenceSpecification messageEnd = (MessageOccurrenceSpecification) fragment;
                        Message message = messageEnd.getMessage();
                        
                        /**
                         * Prevent moving downwards if the next event will be a receive event.
                         * However, if it is a self message we need to move downwards.
                         * If it is the send event of a reply that ends in a gate, there will be no next event.
                         */
                        if (!message.isSelfMessage()
                                && message.getSendEvent() == messageEnd
                                && message.getReceiveEvent().eClass() != RamPackage.Literals.GATE) {
                            receiveEventNext = true;
                        } else {
                            receiveEventNext = false;
                        }
                    }
                    
                    if (fragment instanceof CombinedFragment) {
                        CombinedFragment combinedFragment = (CombinedFragment) fragment;
                        CombinedFragmentView combinedFragmentView = combinedFragments.get(combinedFragment);
                        
                        if (combinedFragment.getCovered().indexOf(lifeline) == 0) {
                            Vector3D position = combinedFragmentView.getPosition(TransformSpace.GLOBAL);
                            position.setY(currentFragmentY);
                            combinedFragmentView.setPositionGlobal(position);
                            
                            receiveEventNext = true;
                        }
                        
                        // Only increase y if it is the last lifeline.
                        int lastIndex = combinedFragment.getCovered().size() - 1;
                        if (combinedFragment.getCovered().indexOf(lifeline) == lastIndex) {
                            for (InteractionOperand operand : combinedFragment.getOperands()) {
                                float operandHeight = combinedFragmentView.getOperandMinimumHeight(operand);
                                
                                if (operand.getFragments().size() > 0) {
                                    operandHeight += layoutFragments(operand, currentFragmentY + operandHeight);
                                }
                                
                                // Add an additional space due to the additional spacer in the operand.
                                // This also supports empty operands.
                                operandHeight += BOX_HEIGHT;
                                combinedFragmentView.setOperandHeight(operand, operandHeight);
                                
                                currentFragmentY += operandHeight;
                            }
                            
                            combinedFragmentView.updateLayout();
                            receiveEventNext = false;
                        }
                    } else if (!receiveEventNext) {
                        currentFragmentY += fragmentView.getHeight();
                    }
                }
            }
        }
        
        return currentFragmentY - previousFragmentY;
    }
    
    /**
     * Layouts all combined fragments of this view.
     * The width is determined by the position of the covered lifelines.
     */
    private void layoutCombinedFragments() {
        for (Entry<CombinedFragment, CombinedFragmentView> entry : combinedFragments.entrySet()) {
            CombinedFragmentView combinedFragmentView = entry.getValue();
            
            float minX = Float.MAX_VALUE;
            float maxX = -1;
            
            for (Lifeline lifeline : entry.getKey().getCovered()) {
                LifelineView lifelineView = lifelines.get(lifeline);
                
                Vector3D position = lifelineView.getPosition(TransformSpace.GLOBAL);
                minX = Math.min(minX, position.getX());
                maxX = Math.max(maxX, position.getX() + lifelineView.getWidth() + LIFEINE_SPACING);
                
                /**
                 * Take into consideration the size of other fragments as well,
                 * since they could be quite wide.
                 */
                for (InteractionFragment fragment : lifeline.getCoveredBy()) {
                    /**
                     * Check whether the fragment is part of this combined fragment
                     * in its containment hierarchy.
                     */
                    if (EcoreUtil.isAncestor(entry.getKey(), fragment)) {
                        RamRectangleComponent fragmentView = fragments.get(fragment);
                        if (fragmentView != null) {
                            Vector3D fragmentPosition = fragmentView.getPosition(TransformSpace.GLOBAL);
                            maxX = Math.max(maxX, fragmentPosition.getX() + fragmentView.getWidth() + LIFEINE_SPACING);
                        }
                    }
                }
            }
            
            Vector3D position = combinedFragmentView.getPosition(TransformSpace.GLOBAL);
            position.setX(minX);
            combinedFragmentView.setPositionGlobal(position);
            combinedFragmentView.setMinimumWidth(maxX - minX);
        }
    }
    
    @Override
    public void notifyChanged(Notification notification) {
        EObject notifier = (EObject) notification.getNotifier();
        Object feature = notification.getFeature();
        
        Interaction interaction = EMFModelUtil.getRootContainerOfType(notifier, RamPackage.Literals.INTERACTION);
        
        // Notification could come from the interaction or an operand.
        if (notifier == specification
                || interaction == specification) {
            if (feature == RamPackage.Literals.FRAGMENT_CONTAINER__FRAGMENTS) {
                if (notification.getEventType() == Notification.ADD) {
                    EObject newValue = (EObject) notification.getNewValue();
                    
                    RamSwitch<Object> switcher = new RamSwitch<Object>() {
                        @Override
                        public Object caseExecutionStatement(ExecutionStatement object) {
                            addStatementView(object);
                            return Boolean.TRUE;
                        }
                        
                        @Override
                        public Object caseCombinedFragment(CombinedFragment object) {
                            addCombinedFragment(object);
                            return Boolean.TRUE;
                        }
                        
                        @Override
                        public Object caseAssignmentStatement(AssignmentStatement object) {
                            addAssignmentStatement(object);
                            return Boolean.TRUE;
                        };
                    };
                    
                    switcher.doSwitch(newValue);
                } else if (notification.getEventType() == Notification.REMOVE) {
                    EObject oldValue = (EObject) notification.getOldValue();
                    
                    RamSwitch<Object> switcher = new RamSwitch<Object>() {
                        @Override
                        public Object caseExecutionStatement(ExecutionStatement object) {
                            removeInteractionFragment(object);
                            return Boolean.TRUE;
                        }
                        
                        @Override
                        public Object caseCombinedFragment(CombinedFragment object) {
                            removeCombinedFragment(object);
                            return Boolean.TRUE;
                        }
                        
                        @Override
                        public Object caseAssignmentStatement(AssignmentStatement object) {
                            removeInteractionFragment(object);
                            return Boolean.TRUE;
                        }
                    };
                    
                    switcher.doSwitch(oldValue);
                }
            } else if (feature == RamPackage.Literals.INTERACTION__LIFELINES) {
                switch (notification.getEventType()) {
                    case Notification.ADD:
                        Lifeline lifeline = (Lifeline) notification.getNewValue();
                        addLifelineView(lifeline);
                        break;
                    case Notification.REMOVE:
                        lifeline = (Lifeline) notification.getOldValue();
                        removeLifelineView(lifeline);
                        break;
                }
            } else if (feature == RamPackage.Literals.INTERACTION__MESSAGES) {
                switch (notification.getEventType()) {
                    case Notification.ADD:
                        Message message = (Message) notification.getNewValue();
                        addMessageView(message);
                        break;
                    case Notification.REMOVE:
                        message = (Message) notification.getOldValue();
                        removeMessageView(message);
                        break;
                }
            }
        } else if (notifier == layout) {
            if (feature == RamPackage.Literals.CONTAINER_MAP__VALUE) {
                if (notification.getEventType() == Notification.ADD) {
                    ElementMapImpl elementMap = (ElementMapImpl) notification.getNewValue();
                    LifelineView lifelineView = lifelines.get(elementMap.getKey());
                    lifelineView.setLayoutElement(elementMap.getValue());
                }
            }
        }
    }
    
}
