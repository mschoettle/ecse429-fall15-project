package ca.mcgill.sel.ram.ui.components;

import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.AbstractComponentProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapProcessor;

import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamKeyboard.KeyboardPosition;
import ca.mcgill.sel.ram.ui.components.listeners.RamKeyboardListener;
import ca.mcgill.sel.ram.ui.components.listeners.RamTextListener;
import ca.mcgill.sel.ram.ui.events.listeners.ITapListener;
import ca.mcgill.sel.ram.ui.layouts.VerticalLayout;
import ca.mcgill.sel.ram.ui.layouts.Layout.HorizontalAlignment;
import ca.mcgill.sel.ram.ui.layouts.Layout.VerticalAlignment;
import ca.mcgill.sel.ram.ui.utils.Constants;
import ca.mcgill.sel.ram.ui.views.handler.BaseHandler;

/**
 * A text field component is basically a text component with a fixed width by default.
 * 
 * @author tdimeco
 */
public class RamTextFieldComponent extends RamRectangleComponent {
    
    /**
     * Handler for tap events on the text field.
     */
    private class TapHandler extends BaseHandler implements ITapListener {
        
        @Override
        public boolean processTapEvent(TapEvent tapEvent) {
            if (tapEvent.isTapped() && isEnabled()) {
                showKeyboard();
            }
            return true;
        }
    }
    
    private RamTextComponent textComponent;
    private KeyboardPosition keyboardPosition;
    private TapHandler tapHandler;
    
    /**
     * Constructs an editable text field from a RamTextComponent.
     * 
     * @param textComponent The text component
     */
    public RamTextFieldComponent(RamTextComponent textComponent) {
        this(textComponent, true);
    }
    
    /**
     * Constructs a text field from a RamTextComponent.
     * 
     * @param textComponent The text component
     * @param editable Whether the text is editable with a keyboard
     */
    public RamTextFieldComponent(RamTextComponent textComponent, boolean editable) {
        this.textComponent = textComponent;
        this.keyboardPosition = KeyboardPosition.DEFAULT;
        this.tapHandler = new TapHandler();
        setForceToKeepSize(true);
        addChild(textComponent);
        
        if (editable) {
            // register tap processor
            // stop the event propagation so now children will get tapped for sure
            AbstractComponentProcessor tapProcessor = new TapProcessor(RamApp.getApplication(),
                    Constants.TAP_MAX_FINGER_UP, false, Constants.TAP_DOUBLE_TAP_LONG_TIME, true);
            // this will allow the button to receive a tap event even if a child was tapped
            tapProcessor.setBubbledEventsEnabled(true);
            registerInputProcessor(tapProcessor);
        }
    }
    
    /**
     * Display the keyboard.
     */
    public void showKeyboard() {
        textComponent.showKeyboard(this, null, keyboardPosition);
    }
    
    /**
     * Display the keyboard with the given listener.
     * 
     * @param ramKeyboardListener the {@link RamKeyboardListener} to associate to the keyboard
     */
    public void showKeyboard(RamKeyboardListener ramKeyboardListener) {
        RamKeyboard keyboard = new RamKeyboard(); 
        keyboard.registerListener(ramKeyboardListener);
        textComponent.showKeyboard(this, keyboard, keyboardPosition);
    }
    
    /**
     * Close the keyboard.
     */
    public void closeKeyboard() {
        textComponent.closeKeyboard();
    }
    
    /**
     * Sets the keyboard position.
     * 
     * @param keyboardPosition The keyboard position
     */
    public void setKeyboardPosition(KeyboardPosition keyboardPosition) {
        this.keyboardPosition = keyboardPosition;
    }
    
    /**
     * Adds a text listener to the text component.
     * 
     * @param listener The listener
     */
    public void registerListener(RamTextListener listener) {
        textComponent.registerListener(listener);
    }
    
    /**
     * Removes a text listener from the text component.
     * 
     * @param listener The listener
     */
    public void unregisterListener(RamTextListener listener) {
        textComponent.unregisterListener(listener);
    }
    
    /**
     * Sets whether the text field have to keep its size and ignore layouting or not.
     * 
     * @param force true to force (default), false otherwise
     */
    public void setForceToKeepSize(boolean force) {
        VerticalLayout layout = new VerticalLayout(HorizontalAlignment.LEFT);
        layout.setForceToKeepSize(force);
        layout.setVerticalAlignment(VerticalAlignment.MIDDLE);
        setLayout(layout);
    }
    
    /**
     * Returns the text component.
     * 
     * @return The text component
     */
    public RamTextComponent getTextComponent() {
        return textComponent;
    }
    
    @Override
    public boolean processGestureEvent(MTGestureEvent gestureEvent) {
        return tapHandler.processGestureEvent(gestureEvent);
    }
}
