package ca.mcgill.sel.ram.ui.components.listeners;

import org.mt4j.components.visibleComponents.widgets.keyboard.ITextInputListener;

import ca.mcgill.sel.ram.ui.components.RamKeyboard;

/**
 * An interface for classes wishing to respond to input events on a {@link RamKeyboard}.
 * 
 * @author vbonnet
 */
public interface RamKeyboardListener extends ITextInputListener {
    
    /**
     * Called when the keyboard is dismissed without selection.
     * Currently this can only be done by clicking the X button.
     */
    void keyboardCancelled();
    
    /**
     * Called when the keyboard is closed with success (enter key was hit).
     * 
     * @param ramKeyboard
     *            The keyboard closed.
     */
    void keyboardClosed(RamKeyboard ramKeyboard);
    
    /**
     * Called when the keyboard is opened.
     */
    void keyboardOpened();
    
    /**
     * Called when a key is pressed that is not one of the characters accepted by the keyboard.
     * 
     * @param keyCode
     *            The code of the key pressed. These correspond with the constants in {@link java.awt.event.KeyEvent}.
     */
    void modifierKeyPressed(int keyCode);
    
    /**
     * Called before dismissing.
     * @return true if we want to dismiss, false otherwise
     */
    boolean verifyKeyboardDismissed();
}
