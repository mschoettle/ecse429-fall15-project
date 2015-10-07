package ca.mcgill.sel.ram.ui.views.message.handler;

import ca.mcgill.sel.ram.ui.events.listeners.ITapAndHoldListener;
import ca.mcgill.sel.ram.ui.views.handler.ITextViewHandler;
import ca.mcgill.sel.ram.ui.views.message.handler.impl.AssignmentAssignToHandler;
import ca.mcgill.sel.ram.ui.views.message.handler.impl.AssignmentStatementHandler;
import ca.mcgill.sel.ram.ui.views.message.handler.impl.CombinedFragmentHandler;
import ca.mcgill.sel.ram.ui.views.message.handler.impl.ExecutionStatementHandler;
import ca.mcgill.sel.ram.ui.views.message.handler.impl.InteractionConstraintHandler;
import ca.mcgill.sel.ram.ui.views.message.handler.impl.LifelineViewHandler;
import ca.mcgill.sel.ram.ui.views.message.handler.impl.MessageAssignToHandler;
import ca.mcgill.sel.ram.ui.views.message.handler.impl.MessageHandler;
import ca.mcgill.sel.ram.ui.views.message.handler.impl.MessageViewHandler;
import ca.mcgill.sel.ram.ui.views.message.handler.impl.ReferenceAndValueHandler;
import ca.mcgill.sel.ram.ui.views.message.handler.impl.ValueSpecificationHandler;

/**
 * A factory to obtain all (default) handlers for views of message views.
 * 
 * @author mschoettle
 */
public final class MessageViewHandlerFactory {
    
    /**
     * The singleton instance of this factory.
     */
    public static final MessageViewHandlerFactory INSTANCE = new MessageViewHandlerFactory();
    
    private IMessageViewHandler messageViewHandler;
    private ILifelineViewHandler lifelineViewHandler;
    private ITapAndHoldListener messageHandler;
    private ITextViewHandler combinedFragmentHandler;
    private ITapAndHoldListener assignmentStatementHandler;
    
    /**
     * Creates a new instance of the handler factory.
     */
    private MessageViewHandlerFactory() {
        
    }
    
    /**
     * Returns the default handler for a message view.
     * 
     * @return the default {@link IMessageViewHandler}
     */
    public IMessageViewHandler getMessageViewHandler() {
        if (messageViewHandler == null) {
            messageViewHandler = new MessageViewHandler();
        }
        
        return messageViewHandler;
    }
    
    /**
     * Returns the default handler for a lifeline view.
     * 
     * @return the default {@link ILifelineViewHandler}
     */
    public ILifelineViewHandler getLifelineViewHandler() {
        if (lifelineViewHandler == null) {
            lifelineViewHandler = new LifelineViewHandler();
        }
        
        return lifelineViewHandler;
    }
    
    /**
     * Returns the default handler for a view representing a {@link ca.mcgill.sel.ram.Message}.
     * 
     * @return the default handler for a message
     */
    public ITapAndHoldListener getMessageHandler() {
        if (messageHandler == null) {
            messageHandler = new MessageHandler();
        }
        
        return messageHandler;
    }
    
    /**
     * Returns the stateful handler for a view containing a {@link ca.mcgill.sel.ram.ValueSpecification}.
     * 
     * @return the default {@link IValueSpecificationHandler}
     */
    @SuppressWarnings("static-method")
    public IValueSpecificationHandler getValueSpecificationHandler() {
        return new ValueSpecificationHandler();
    }
    
    /**
     * Returns the stateful handler for a view containing a {@link ca.mcgill.sel.ram.ValueSpecification},
     * that has the ability to adapt by either offering to select a reference
     * or creating or modifying a value specification.
     * 
     * @return the default {@link IValueSpecificationHandler}
     */
    @SuppressWarnings("static-method")
    public IValueSpecificationHandler getReferenceAndValueHandler() {
        return new ReferenceAndValueHandler();
    }
    
    /**
     * Returns the stateful handler for an assignment text view of a message call.
     * 
     * @return the default {@link IAssignToHandler}
     */
    @SuppressWarnings("static-method")
    public IAssignToHandler getMessageAssignToHandler() {
        return new MessageAssignToHandler();
    }
    
    /**
     * Returns the stateful handler for a text view that refers to a {@link ca.mcgill.sel.ram.ValueSpecification}.
     * 
     * @return the handler for value specifications
     */
    @SuppressWarnings("static-method")
    public ITextViewHandler getInteractionConstraintHandler() {
        return new InteractionConstraintHandler();
    }
    
    /**
     * Returns the stateful handler for a text view that refers to an {@link ca.mcgill.sel.ram.ExecutionStatement}.
     * 
     * @return the handler for execution statements
     */
    @SuppressWarnings("static-method")
    public ITextViewHandler getExecutionStatementHandler() {
        return new ExecutionStatementHandler();
    }
    
    /**
     * Returns the default handler for a text view that refers to a {@link ca.mcgill.sel.ram.CombinedFragment}.
     * 
     * @return the default handler for combined fragments
     */
    public ITextViewHandler getCombinedFragmentHandler() {
        if (combinedFragmentHandler == null) {
            combinedFragmentHandler = new CombinedFragmentHandler();
        }
        
        return combinedFragmentHandler;
    }
    
    /**
     * Returns the default handler for a view that refers to an {@link ca.mcgill.sel.ram.AssignmentStatement}.
     * 
     * @return the default handler for assignment statements
     */
    public ITapAndHoldListener getAssignmentStatementHandler() {
        if (assignmentStatementHandler == null) {
            assignmentStatementHandler = new AssignmentStatementHandler();
        }
        
        return assignmentStatementHandler;
    }
    
    /**
     * Returns the stateful handler for an assignment text view of an assignment statement.
     * 
     * @return the default {@link IAssignToHandler}
     */
    @SuppressWarnings("static-method")
    public IAssignToHandler getAssignmentAssignToHandler() {
        return new AssignmentAssignToHandler();
    }
    
}
