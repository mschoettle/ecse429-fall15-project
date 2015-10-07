package ca.mcgill.sel.ram.ui.views.handler;

import org.mt4j.input.inputProcessors.IGestureEventListener;

/**
 * This interface is implemented by components who wish to be handled by a handler.
 * 
 * @author mschoettle
 * @param <T>
 *            the interface of the specific handler
 */
public interface IHandled<T extends IGestureEventListener> {
    
    /**
     * Returns the handler.
     * 
     * @return the handler
     */
    T getHandler();
    
    /**
     * Sets the handler for this view.
     * <b>Important:</b> Has to be called after addChild in order to have a parent in case the implementing
     * component needs to know the parent
     * (for example, when registering a tap and hold visualizer that displays itself on the parent).
     * 
     * @param handler the handler
     */
    void setHandler(T handler);
    
}
