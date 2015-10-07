package ca.mcgill.sel.ram.ui.components.listeners;

import ca.mcgill.sel.ram.ui.components.RamKeyboard;

/**
 * An abstract class written only to make it easier to listen to keyboard. This way when you create an anonymous
 * {@link RamKeyboardListener} you can just override the
 * functions you care about instead of ALL OF THEM.
 * 
 * @author vbonnet
 */
public abstract class DefaultRamKeyboardListener implements RamKeyboardListener {
    @Override
    public void appendCharByUnicode(final String unicode) {
    }
    
    @Override
    public void appendText(final String text) {
    }
    
    @Override
    public void clear() {
    }
    
    @Override
    public void keyboardCancelled() {
    }
    
    @Override
    public void keyboardClosed(final RamKeyboard ramKeyboard) {
    }
    
    @Override
    public void keyboardOpened() {
    }
    
    @Override
    public void modifierKeyPressed(final int keyCode) {
    }
    
    @Override
    public void removeLastCharacter() {
    }
    
    @Override
    public void setText(final String text) {
    }
    
    @Override
    public boolean verifyKeyboardDismissed() {
        return true;
    }
}
