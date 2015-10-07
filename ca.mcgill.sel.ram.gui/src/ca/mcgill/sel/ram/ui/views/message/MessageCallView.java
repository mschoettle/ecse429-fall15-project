package ca.mcgill.sel.ram.ui.views.message;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.mt4j.components.visibleComponents.shapes.MTPolygon;
import org.mt4j.input.inputProcessors.IGestureEventListener;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.ram.Gate;
import ca.mcgill.sel.ram.Message;
import ca.mcgill.sel.ram.MessageEnd;
import ca.mcgill.sel.ram.MessageSort;
import ca.mcgill.sel.ram.OperationType;
import ca.mcgill.sel.ram.ParameterValueMapping;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.ui.components.ContainerComponent;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.components.RamTextComponent;
import ca.mcgill.sel.ram.ui.events.listeners.ITapAndHoldListener;
import ca.mcgill.sel.ram.ui.layouts.HorizontalLayout;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.utils.Strings;
import ca.mcgill.sel.ram.ui.views.RamEnd;
import ca.mcgill.sel.ram.ui.views.RamEnd.Position;
import ca.mcgill.sel.ram.ui.views.RelationshipView;
import ca.mcgill.sel.ram.ui.views.TextView;
import ca.mcgill.sel.ram.ui.views.handler.IHandled;
import ca.mcgill.sel.ram.ui.views.message.handler.MessageViewHandlerFactory;
import ca.mcgill.sel.ram.ui.views.message.handler.IValueSpecificationHandler;

/**
 * The view responsible for visualizing a message call.
 * Depending on the type of the call, different information is shown.
 * The initial call just shows the full message signature.
 * Regular synchronous call shows separate views for each part of the message.
 * This allows to process events on them to modify the information.
 * The reply message just contains the return value (if something is returned).
 * 
 * @author mschoettle
 */
public class MessageCallView extends RelationshipView<MessageEnd, RamRectangleComponent>
        implements IHandled<ITapAndHoldListener>, INotifyChangedListener {
    
    /**
     * The internal handler that delegates the gesture events from children to the handler of this view.
     */
    private class DelegationHandler implements IGestureEventListener {
        
        @Override
        public boolean processGestureEvent(MTGestureEvent ge) {
            ge.setTarget(MessageCallView.this);
            return handler.processGestureEvent(ge);
        }
        
    }
    
    private static final float BUFFER_LEFT = 10f;
    
    private Message message;
    
    private ContainerComponent<IGestureEventListener> signatureView;
    private RamRectangleComponent parameterContainer;
    
    private ITapAndHoldListener handler;
    
    /**
     * Creates a new message call view for the given message. The from and to view are the views representing
     * the ends of the message call.
     * 
     * @param message the message to be visualized
     * @param fromView the view representing the origin of the call
     * @param toView the view representing the receiver of the call
     */
    public MessageCallView(Message message, RamRectangleComponent fromView, RamRectangleComponent toView) {
        super(message.getSendEvent(), fromView, message.getReceiveEvent(), toView);
        
        this.message = message;
        
        fromEnd.setIsAlone(true);
        toEnd.setIsAlone(true);
        
        if (message.getMessageSort() == MessageSort.REPLY) {
            setLineStyle(LineStyle.DASHED);
        }
        
        signatureView = new ContainerComponent<IGestureEventListener>();
        signatureView.setAnchor(PositionAnchor.LOWER_LEFT);
        signatureView.setLayout(new HorizontalLayout());
        addChild(signatureView);
        
        signatureView.setHandler(new DelegationHandler());
        
        // For Debug purposes.
        // signatureView.setNoStroke(false);
        // signatureView.setStrokeColor(MTColor.RED);
        
        build();
        
        EMFEditUtil.addListenerFor(message, this);
    }
    
    @Override
    public void destroy() {
        super.destroy();
        
        EMFEditUtil.removeListenerFor(message, this);
    }
    
    /**
     * Builds the parts of this view depending on the type of the message.
     */
    private void build() {
        if (message.getSendEvent() instanceof Gate) {
            // Incoming message, just show the full label.
            TextView textView = new TextView(message, CorePackage.Literals.CORE_NAMED_ELEMENT__NAME, true);
            signatureView.addChild(textView);
        } else if (message.getSignature() != null && message.getSignature().eIsProxy()) {
            TextView textView = new TextView(message, RamPackage.Literals.MESSAGE__SIGNATURE, true);
            textView.setFillColor(Colors.VALIDATION_ERROR_FILL_COLOR);
            textView.setNoFill(false);
            signatureView.addChild(textView);
        } else if (message.getMessageSort() == MessageSort.REPLY 
                && message.getSignature() != null
                && message.getSignature().getOperationType() == OperationType.NORMAL
                && message.getSignature().getReturnType().eClass() != RamPackage.Literals.RVOID) {
            // Replace message, need a view for returns.
            TextView textView = new TextView(message, RamPackage.Literals.MESSAGE__RETURNS);
            textView.setPlaceholderText(Strings.PH_SELECT_RETURN);
            textView.setHandler(MessageViewHandlerFactory.INSTANCE.getReferenceAndValueHandler());
            signatureView.addChild(textView);
        } else if (message.getMessageSort() != MessageSort.REPLY) {
            // Make sure that the signature exists to prevent a NPE.
            if (message.getSignature() != null
                    && message.getSignature().getReturnType().eClass() != RamPackage.Literals.RVOID) {
                // Call message with a return.
                TextView assignToView = new TextView(message, RamPackage.Literals.MESSAGE__ASSIGN_TO);
                assignToView.setPlaceholderText(Strings.PH_SELECT_ASSIGNMENT);
                signatureView.addChild(assignToView);
                assignToView.registerTapProcessor(MessageViewHandlerFactory.INSTANCE.getMessageAssignToHandler());
                
                RamTextComponent assignmentText = new RamTextComponent(Strings.SYMBOL_ASSIGNMENT);
                resetSideBuffers(assignmentText);
                signatureView.addChild(assignmentText);
            }
            
            TextView signatureNameView = new TextView(message.getSignature(),
                    CorePackage.Literals.CORE_NAMED_ELEMENT__NAME, false);
            signatureNameView.setPlaceholderText(Strings.PH_SELECT_MESSAGE);
            signatureNameView.setBufferSize(Cardinal.EAST, 0);
            
            signatureView.addChild(signatureNameView);
            signatureView.addChild(createText("("));
            
            parameterContainer = new RamRectangleComponent(new HorizontalLayout());
            resetSideBuffers(parameterContainer);
            signatureView.addChild(parameterContainer);
            
            for (int index = 0; index < message.getArguments().size(); index++) {
                ParameterValueMapping parameterMapping = message.getArguments().get(index);
                addParameterMapping(parameterMapping, index);
            }
            
            signatureView.addChild(createText(")"));
        }
    }
    
    /**
     * Adds a parameter value mapping at the given model index.
     * Each mapping is separated by a separator, unless the first one is added.
     * 
     * @param parameterMapping the {@link ParameterValueMapping} to add
     * @param index the index at which the mapping should be added at
     */
    private void addParameterMapping(ParameterValueMapping parameterMapping, int index) {
        int viewIndex = index * 2;
        int numArguments = message.getArguments().size();
        
        /**
         * A separator is needed for every additional parameter value mapping.
         * And in case one is added after at the beginning in front of others.
         */
        if (index > 0 || numArguments > 1 && index == 0 && parameterContainer.getChildCount() > 0) {
            int separatorIndex = (index > 0) ? viewIndex - 1 : viewIndex;
            RamTextComponent separator = createText(",");
            separator.setBufferSize(Cardinal.EAST, 5);
            parameterContainer.addChild(separatorIndex, separator);
        }
        
        TextView parameterMappingView = new TextView(parameterMapping,
                RamPackage.Literals.PARAMETER_VALUE_MAPPING__VALUE);
        parameterMappingView.setPlaceholderText(EMFEditUtil.getText(parameterMapping));
        resetSideBuffers(parameterMappingView);
        parameterContainer.addChild(viewIndex, parameterMappingView);
        
        IValueSpecificationHandler valueHandler = MessageViewHandlerFactory.INSTANCE.getReferenceAndValueHandler();
        parameterMappingView.registerTapProcessor(valueHandler);
    }
    
    /**
     * Removes the parameter value mapping at the given model index.
     * In case there is a separator, it will be removed as well.
     * 
     * @param index the index of the parameter value mapping in the model
     */
    private void removeParameterMapping(int index) {
        int viewIndex = index * 2;
        
        parameterContainer.removeChild(viewIndex);
        
        /**
         * Remove the separator if necessary.
         * Only if there is one parameter left no separator is to be removed.
         */
        if (index > 0 || message.getArguments().size() > 1) {
            int separatorIndex = (index > 0) ? viewIndex - 1 : viewIndex;
            parameterContainer.removeChild(separatorIndex);
        }
    }
    
    /**
     * Creates a text component with the given text.
     * 
     * @param text the text to display
     * @return the text component visualizing the given text
     */
    private RamTextComponent createText(String text) {
        RamTextComponent parenthesisText = new RamTextComponent(text);
        resetSideBuffers(parenthesisText);
        
        return parenthesisText;
    }
    
    /**
     * Resets the side buffers of the given component to zero (0).
     * 
     * @param component the component
     */
    @SuppressWarnings("static-method")
    private void resetSideBuffers(RamRectangleComponent component) {
        component.setBufferSize(Cardinal.EAST, 0);
        component.setBufferSize(Cardinal.WEST, 0);
    }
    
    @Override
    protected void update() {
        if (!message.isSelfMessage()) {
            drawAllLines();
        } else {
            drawSelfMessage();
        }
        
        drawMessageEnd(getToEnd());
        moveSignature(getFromEnd());
    }
    
    /**
     * Draws the arrow for a self message. I.e., it starts and ends on the same x position.
     * The arrow for a self message consists of two horizontal and one vertical line.
     */
    private void drawSelfMessage() {
        float fromX = fromEnd.getLocation().getX();
        float fromY = fromEnd.getLocation().getY();
        Position positionFrom = fromEnd.getPosition();
        float toX = toEnd.getLocation().getX();
        float toY = toEnd.getLocation().getY();
        Position positionTo = toEnd.getPosition();
        
        float newToX = fromX + DISTANCE_SELF_REFERENCE;
        
        drawLine(fromX, fromY, positionFrom, newToX, fromY);
        drawLine(toX, toY, positionTo, newToX, toY);
        drawLine(newToX, fromY, Position.TOP, newToX, toY);
    }
    
    /**
     * Draws the visualizing of an end of a message for the given end.
     * A synchronous call ends with a closed (filled) arrow, whereas a reply message has an open arrow.
     * 
     * @param end the end of a message to visualize
     */
    private void drawMessageEnd(RamEnd<MessageEnd, RamRectangleComponent> end) {
        float x = end.getLocation().getX();
        float y = end.getLocation().getY();
        
        MTPolygon arrowPolygon = new ArrowPolygon(x, y, drawColor);
        rotateShape(arrowPolygon, x, y, end.getPosition());
        
        // Only the sync call should have a closed/filled arrow.
        if (message.getMessageSort() != MessageSort.REPLY) {
            arrowPolygon.setNoFill(false);
            arrowPolygon.setFillColor(drawColor);
        }
        
        addChild(arrowPolygon);
    }
    
    /**
     * Moves the signature view of the message call to the appropriate position.
     * A regular (synchronous) call is located at the originating end.
     * A reply message is aligned right to the from end.
     * 
     * @param end the from end of the message
     */
    private void moveSignature(RamEnd<MessageEnd, RamRectangleComponent> end) {
        float x = end.getLocation().getX();
        float y = end.getLocation().getY();
        
        if (message.getMessageSort() == MessageSort.REPLY) {
            signatureView.setAnchor(PositionAnchor.LOWER_RIGHT);
            x -= BUFFER_LEFT;
        } else if (x > end.getOpposite().getLocation().getX()) {
            // If the message goes from right to left, adjust the position.
            x = end.getOpposite().getLocation().getX();
        }
        
        signatureView.setPositionRelativeToParent(new Vector3D(x, y));
    }
    
    /**
     * Returns the message represented by this view.
     * 
     * @return the {@link Message} represented by this view
     */
    public Message getMessage() {
        return message;
    }
    
    @Override
    public ITapAndHoldListener getHandler() {
        return handler;
    }
    
    @Override
    public void setHandler(ITapAndHoldListener handler) {
        this.handler = handler;
    }
    
    @Override
    public void notifyChanged(Notification notification) {
        if (notification.getNotifier() == message) {
            if (notification.getFeature() == RamPackage.Literals.MESSAGE__ARGUMENTS) {
                switch (notification.getEventType()) {
                    case Notification.ADD:
                        ParameterValueMapping mapping = (ParameterValueMapping) notification.getNewValue();
                        int index = notification.getPosition();
                        addParameterMapping(mapping, index);
                        break;
                    case Notification.REMOVE:
                        index = notification.getPosition();
                        removeParameterMapping(index);
                        break;
                }
            }
        }
    }
}
