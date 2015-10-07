package ca.mcgill.sel.ram.ui.events;

import java.awt.event.KeyEvent;

import org.mt4j.AbstractMTApplication;
import org.mt4j.input.inputData.AbstractCursorInputEvt;
import org.mt4j.input.inputData.ActiveCursorPool;
import org.mt4j.input.inputData.InputCursor;
import org.mt4j.input.inputData.MTFingerInputEvt;
import org.mt4j.input.inputSources.AbstractInputSource;

import processing.core.PApplet;

public class RamKeyboardInputSource extends AbstractInputSource {
    
    private long lastUsedKeybID;
    
    private int locationX;
    private int locationY;
    
    private final PApplet applet;
    private int fingerDownKeyCode;
    
    private boolean fingerDown;
    
    /**
     * Instantiates a new keyboard input source.
     * 
     * @param pa
     *            the pa
     */
    public RamKeyboardInputSource(final AbstractMTApplication pa) {
        super(pa);
        applet = pa;
        applet.registerMethod("keyEvent", this);
        
        locationX = 0;
        locationY = 0;
        
        fingerDownKeyCode = KeyEvent.VK_ALT;
        fingerDown = false;
    }
    
    private void fingerDown(final KeyEvent e) {
        if (!fingerDown) {
            locationX = applet.mouseX;
            locationY = applet.mouseY;
            
            System.out.println("down! " + locationX + " " + locationY);
            
            final InputCursor m = new InputCursor();
            final MTFingerInputEvt touchEvt = new MTFingerInputEvt(this, locationX, locationY,
                    AbstractCursorInputEvt.INPUT_STARTED, m);
            
            lastUsedKeybID = m.getId();
            ActiveCursorPool.getInstance().putActiveCursor(lastUsedKeybID, m);
            
            // FIRE
            enqueueInputEvent(touchEvt);
        } else {
            final InputCursor m = ActiveCursorPool.getInstance().getActiveCursorByID(lastUsedKeybID);
            
            final MTFingerInputEvt te = new MTFingerInputEvt(this, locationX, locationY,
                    AbstractCursorInputEvt.INPUT_UPDATED, m);
            enqueueInputEvent(te);
        }
        fingerDown = true;
    }
    
    private void fingerUp(final KeyEvent e) {
        fingerDown = false;
        
        final InputCursor m = ActiveCursorPool.getInstance().getActiveCursorByID(lastUsedKeybID);
        final MTFingerInputEvt te = new MTFingerInputEvt(this, locationX, locationY,
                AbstractCursorInputEvt.INPUT_ENDED, m);
        
        enqueueInputEvent(te);
        
        ActiveCursorPool.getInstance().removeCursor(lastUsedKeybID);
        
    }
    
    /**
     * Key event.
     * 
     * @param e
     *            the e
     */
    public void keyEvent(final processing.event.KeyEvent e) {
        
        final int eventID = e.getAction();
        final int keyCode = e.getKeyCode();
        
        if (eventID == KeyEvent.KEY_PRESSED) {
            if (keyCode == fingerDownKeyCode) {
                // fingerDown(e);
            }
        } else if (eventID == KeyEvent.KEY_RELEASED) {
            if (e.getKeyCode() == fingerDownKeyCode) {
                // fingerUp(e);
            }
        }
    }
    
    public void setFingerDownKeyCode(final int keyCode) {
        fingerDownKeyCode = keyCode;
    }
    
}
