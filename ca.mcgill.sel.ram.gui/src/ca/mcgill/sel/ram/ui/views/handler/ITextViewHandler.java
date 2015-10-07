package ca.mcgill.sel.ram.ui.views.handler;

import ca.mcgill.sel.ram.ui.events.listeners.ITapListener;
import ca.mcgill.sel.ram.ui.views.TextView;

/**
 * This interface is implemented by something that can handle events for a {@link TextView}.
 * 
 * @author mschoettle
 */
public interface ITextViewHandler extends ITapListener {
    
    /**
     * Handles taks that should be performed when the user has cancelled (closed) the keyboard.
     * 
     * @param textView
     *            the affected {@link TextView}
     */
    void keyboardCancelled(TextView textView);
    
    /**
     * Handles tasks that should be performed after the keyboard has been opened.
     * 
     * @param textView
     *            the affected {@link TextView}
     */
    void keyboardOpened(TextView textView);
    
    /**
     * Handles whether the keyboard can be dismissed.
     * Performs checks on the text provided by the user to determine that.
     * 
     * @param textView
     *            the affected {@link TextView}
     * @return true, if the keyboard can be dismissed, false otherwise
     */
    boolean shouldDismissKeyboard(TextView textView);
    
}
