package ca.mcgill.sel.ram.ui.views.message;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.mt4j.input.gestureAction.TapAndHoldVisualizer;
import org.mt4j.input.inputProcessors.componentProcessors.tapAndHoldProcessor.TapAndHoldProcessor;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.ram.CombinedFragment;
import ca.mcgill.sel.ram.InteractionFragment;
import ca.mcgill.sel.ram.InteractionOperand;
import ca.mcgill.sel.ram.Lifeline;
import ca.mcgill.sel.ram.Message;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.components.RamSpacerComponent;
import ca.mcgill.sel.ram.ui.components.RamTextComponent;
import ca.mcgill.sel.ram.ui.layouts.HorizontalLayout;
import ca.mcgill.sel.ram.ui.layouts.VerticalLayout;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.utils.Constants;
import ca.mcgill.sel.ram.ui.utils.Strings;
import ca.mcgill.sel.ram.ui.views.RelationshipView.LineStyle;
import ca.mcgill.sel.ram.ui.views.TextView;
import ca.mcgill.sel.ram.ui.views.handler.ITextViewHandler;
import ca.mcgill.sel.ram.ui.views.message.handler.MessageViewHandlerFactory;
import ca.mcgill.sel.ram.util.RAMModelUtil;

/**
 * The view responsible for visualizing {@link CombinedFragment}s.
 * This view consists of the operator kind and the operands with their constraints.
 * 
 * @author mschoettle
 */
public class CombinedFragmentView extends RamRectangleComponent implements INotifyChangedListener {
    
    private CombinedFragment combinedFragment;
    private MessageViewView messageViewView;
    
    private Map<InteractionOperand, RamRectangleComponent> operandContainers;
    
    /**
     * Creates a new view for the given combined fragment that is part of the message view view.
     * 
     * @param messageViewView the view of the {@link ca.mcgill.sel.ram.MessageView} this combined fragment is part of
     * @param combinedFragment the {@link CombinedFragment} to create the view for
     */
    public CombinedFragmentView(MessageViewView messageViewView, CombinedFragment combinedFragment) {
        super();
        
        this.messageViewView = messageViewView;
        this.combinedFragment = combinedFragment;
        
        operandContainers = new HashMap<InteractionOperand, RamRectangleComponent>();
        
        setNoStroke(false);
        setStrokeColor(Colors.DEFAULT_ELEMENT_STROKE_COLOR);
        // For debug purposes.
        // setNoFill(false);
        // setFillColor(MTColor.RED);
        setPickable(false);
        
        setLayout(new VerticalLayout());
        build();
        
        EMFEditUtil.addListenerFor(combinedFragment, this);
    }
    
    /**
     * Builds the view based on the lifelines that are covered.
     * The operator kind and operands are built andfor each lifeline, this view is added to it.
     * 
     * @see #addToLifeline(Lifeline)
     */
    private void build() {
        for (Lifeline lifeline : combinedFragment.getCovered()) {
            addToLifeline(lifeline);
        }
    }
    
    /**
     * Adds the given lifeline to this view. I.e., the lifeline is covered by the combined fragment.
     * Also, all operands of this combined fragment are added to the lifeline.
     * 
     * @param lifeline the lifeline to add to this view
     * @see #buildOperand(LifelineView, InteractionOperand, int)
     */
    private void addToLifeline(Lifeline lifeline) {
        int modelIndex = combinedFragment.getContainer().getFragments().indexOf(combinedFragment);
        LifelineView lifelineView = messageViewView.getLifelineView(lifeline);
        
        lifelineView.addCombinedFragment(this, combinedFragment, modelIndex);
        
        for (int index = 0; index < combinedFragment.getOperands().size(); index++) {
            InteractionOperand operand = combinedFragment.getOperands().get(index);
            
            buildOperand(lifelineView, operand, index);
        }
    }
    
    /**
     * Removes this view and its operands from the given lifeline.
     * 
     * @param lifeline the lifeline this view should be removed from
     */
    private void removeFromLifeline(Lifeline lifeline) {
        LifelineView lifelineView = messageViewView.getLifelineView(lifeline);
        
        for (InteractionOperand operand : combinedFragment.getOperands()) {
            lifelineView.removeInteractionOperand(operand);
        }
        
        lifelineView.removeCombinedFragment(combinedFragment);
    }
    
    /**
     * Adds the given operand to the lifeline and builds the operands container (if it hasn't been created yet).
     * 
     * @param lifelineView the lifeline view to which to add the operand to
     * @param operand the operand to add
     * @param modelIndex the index in the model of the operand
     */
    private void buildOperand(LifelineView lifelineView, InteractionOperand operand, int modelIndex) {
        if (!operandContainers.containsKey(operand)) {
            buildOperandContainer(operand, modelIndex);
        }
        
        // Messages can only created from (the first part of) this operand
        // if the initial message is received on the same lifeline.
        Message initialMessage = RAMModelUtil.findInitialMessage(combinedFragment);
        InteractionFragment receiveEvent = (InteractionFragment) initialMessage.getReceiveEvent();
        boolean allowCreation = receiveEvent.getCovered().get(0) == lifelineView.getLifeline();
        
        lifelineView.addInteractionOperand(combinedFragment, operand, modelIndex, allowCreation);
    }
    
    /**
     * Builds a container for the given operand at the given index.
     * If it is the first operand, also view for the interaction operator kind is created.
     * 
     * @param operand the operand for which to create a container for
     * @param modelIndex the index in the model at which the operand is positioned
     */
    private void buildOperandContainer(InteractionOperand operand, int modelIndex) {
        RamRectangleComponent operandContainer = new RamRectangleComponent();
        operandContainer.setPickable(false);
        operandContainer.setNoStroke(false);
        operandContainer.setLineStipple(LineStyle.DASHED.getStylePattern());
        
        // For Debug purposes.
        // operandContainer.setFillColor(new MTColor(200, 200, 200, 180));
        // operandContainer.setNoFill(false);
        
        RamRectangleComponent typeContainer = new RamRectangleComponent();
        
        // The operator is only shown once at the very top.
        if (modelIndex == 0) {
            TextView typeField = new TextView(combinedFragment,
                    RamPackage.Literals.COMBINED_FRAGMENT__INTERACTION_OPERATOR);
            
            ITextViewHandler handler = MessageViewHandlerFactory.INSTANCE.getCombinedFragmentHandler();
            typeField.registerTapProcessor(handler);
            typeField.registerInputProcessor(new TapAndHoldProcessor(RamApp.getApplication(),
                    Constants.TAP_AND_HOLD_DURATION));
            typeField.addGestureListener(TapAndHoldProcessor.class, handler);
            typeField.addGestureListener(TapAndHoldProcessor.class,
                    new TapAndHoldVisualizer(RamApp.getApplication(), RamApp.getActiveScene().getCanvas()));
            
            typeField.setNoStroke(false);
            typeContainer.addChild(typeField);
            typeContainer.addChild(new RamSpacerComponent(20, typeField.getHeight()));
        }
        
        typeContainer.addChild(new RamTextComponent("["));
        
        TextView initialOperandConstraint = new TextView(operand,
                RamPackage.Literals.INTERACTION_OPERAND__INTERACTION_CONSTRAINT);
        ITextViewHandler handler = MessageViewHandlerFactory.INSTANCE.getInteractionConstraintHandler();
        initialOperandConstraint.registerTapProcessor(handler);
        initialOperandConstraint.registerInputProcessor(new TapAndHoldProcessor(RamApp.getApplication(),
                Constants.TAP_AND_HOLD_DURATION));
        initialOperandConstraint.addGestureListener(TapAndHoldProcessor.class, handler);
        initialOperandConstraint.addGestureListener(TapAndHoldProcessor.class,
                new TapAndHoldVisualizer(RamApp.getApplication(), RamApp.getActiveScene().getCanvas()));
        
        initialOperandConstraint.setBufferSize(Cardinal.EAST, 0);
        initialOperandConstraint.setBufferSize(Cardinal.WEST, 0);
        initialOperandConstraint.setPlaceholderText(Strings.PH_SPECIFY_CONSTRAINT);
        typeContainer.addChild(initialOperandConstraint);
        
        typeContainer.addChild(new RamTextComponent("]"));
        
        typeContainer.setLayout(new HorizontalLayout());
        operandContainer.setLayout(new VerticalLayout());
        operandContainer.addChild(typeContainer);
        addChild(modelIndex, operandContainer);
        
        operandContainers.put(operand, operandContainer);
    }
    
    @Override
    public void destroy() {
        super.destroy();
        
        EMFEditUtil.removeListenerFor(combinedFragment, this);
    }
    
    /**
     * Returns the minimum height required to display the given operand.
     * The minimum height consists of the height required to display the constraint.
     * 
     * @param operand the operand for which to get the minimum height for
     * @return the minimum height required to display just the operand
     */
    public float getOperandMinimumHeight(InteractionOperand operand) {
        RamRectangleComponent operandContainer = operandContainers.get(operand);
        
        return ((RamRectangleComponent) operandContainer.getChildByIndex(0)).getMinimumHeight();
    }
    
    /**
     * Returns the component that is the container of the given operand.
     * 
     * @param operand the {@link InteractionOperand} for which the container is requested
     * @return the component for the operand
     */
    public RamRectangleComponent getOperandContainer(InteractionOperand operand) {
        return operandContainers.get(operand);
    }
    
    /**
     * Sets the height of the operand to the given height.
     * In addition, informs all covered lifelines in case height adjustments need to be made as a result
     * 
     * @param operand the operand to set the height for
     * @param height the height for the operand
     */
    public void setOperandHeight(InteractionOperand operand, float height) {
        RamRectangleComponent operandContainer = operandContainers.get(operand);
        
        operandContainer.setMinimumHeight(height);
        
        // All containers of the lifelines have to be the same height.
        for (Lifeline lifeline : combinedFragment.getCovered()) {
            LifelineView lifelineView = messageViewView.getLifelineView(lifeline);
            lifelineView.setOperandHeight(operand, height);
        }
        
    }
    
    @Override
    public void notifyChanged(Notification notification) {
        if (notification.getNotifier() == combinedFragment) {
            if (notification.getFeature() == RamPackage.Literals.INTERACTION_FRAGMENT__COVERED) {
                switch (notification.getEventType()) {
                    case Notification.ADD:
                        Lifeline lifeline = (Lifeline) notification.getNewValue();
                        addToLifeline(lifeline);
                        break;
                    case Notification.REMOVE:
                        Lifeline oldLifeline = (Lifeline) notification.getOldValue();
                        removeFromLifeline(oldLifeline);
                        break;
                }
            } else if (notification.getFeature() == RamPackage.Literals.COMBINED_FRAGMENT__OPERANDS) {
                switch (notification.getEventType()) {
                    case Notification.ADD:
                        InteractionOperand operand = (InteractionOperand) notification.getNewValue();
                        addOperand(operand, notification.getPosition());
                        break;
                    case Notification.REMOVE:
                        InteractionOperand oldOperand = (InteractionOperand) notification.getOldValue();
                        removeOperand(oldOperand);
                        break;
                }
            }
        }
    }
    
    /**
     * Adds a view for the given operand to this view at the given index and all the covered lifelines.
     * 
     * @param operand the {@link InteractionOperand} to add a view for
     * @param modelIndex the index in the model where the operand was added
     */
    private void addOperand(InteractionOperand operand, int modelIndex) {
        for (Lifeline lifeline : combinedFragment.getCovered()) {
            LifelineView lifelineView = messageViewView.getLifelineView(lifeline);
            buildOperand(lifelineView, operand, modelIndex);
        }
        
        messageViewView.layoutMessageView();
    }
    
    /**
     * Removes the given operand's view from this view and all covered lifelines.
     * 
     * @param operand the {@link InteractionOperand} whose view to remove
     */
    private void removeOperand(InteractionOperand operand) {
        for (Lifeline lifeline : combinedFragment.getCovered()) {
            LifelineView lifelineView = messageViewView.getLifelineView(lifeline);
            lifelineView.removeInteractionOperand(operand);
        }
        
        RamRectangleComponent operandContainer = operandContainers.remove(operand);
        removeChild(operandContainer);
        operandContainer.destroy();
        
        messageViewView.layoutMessageView();
    }
    
}
