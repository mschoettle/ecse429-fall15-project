package ca.mcgill.sel.ram.ui.components;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mt4j.IMTApplication;
import org.mt4j.components.TransformSpace;
import org.mt4j.components.bounds.IBoundingShape;
import org.mt4j.components.visibleComponents.shapes.AbstractShape;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;
import org.mt4j.components.visibleComponents.widgets.MTTextArea;
import org.mt4j.input.IKeyListener;
import org.mt4j.input.gestureAction.InertiaDragAction;
import org.mt4j.input.inputProcessors.IGestureEventListener;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.tapAndHoldProcessor.TapAndHoldEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapAndHoldProcessor.TapAndHoldProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapProcessor;
import org.mt4j.util.MT4jSettings;
import org.mt4j.util.MTColor;
import org.mt4j.util.animation.Animation;
import org.mt4j.util.animation.AnimationEvent;
import org.mt4j.util.animation.IAnimation;
import org.mt4j.util.animation.IAnimationListener;
import org.mt4j.util.animation.MultiPurposeInterpolator;
import org.mt4j.util.font.FontManager;
import org.mt4j.util.font.IFont;
import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.listeners.RamKeyboardListener;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.utils.MathUtils;

/**
 * The default keyboard for all RAM applications. Only one keyboard will be displayed at any given time. The keyboard
 * can be displayed using the various display(..) methods. Any {@link RamKeyboardListener} can listen to the keyboard by
 * registering itself using the registerListener() method. The characters which will be forwarded to the keyboard's
 * listeners can be set using allowAlpha() allowNumerics() and {allow/disallow}Character().
 * 
 * @author vbonnet
 * @author mschoettle
 * @author tdimeco
 */
public class RamKeyboard extends MTRectangle implements IKeyListener {
    
    /** The double tap delay for keys. **/
    private static final int DOUBLE_TAP_DELAY = 500;
    
    /** The hold delay for keys. **/
    private static final int HOLD_DELAY = 1000;
    
    /** The font size of the keys. **/
    private static final int FONT_SIZE = 18;
    
    /** The font name of the keys. **/
    private static final String FONT_NAME = "arial.bold";
    
    /** The default keyboard opening position. **/
    private static final KeyboardPosition DEFAULT_KEYBOARD_POSITION = KeyboardPosition.BOTTOM;
    
    /**
     * Contains currently displayed {@link RamKeyboard} in the opening order that support the physical keyboard.
     */
    private static List<RamKeyboard> currentHardwareKeyboards = new ArrayList<RamKeyboard>();
    
    /**
     * References the {@link RamKeyboard} which currently have the physical keyboard focus.
     */
    private static RamKeyboard physicalKeyboardFocus;
    
    /**
     * Count the number of ignored enter keys in the current event loop.
     * Necessary to delay the keyboard close at the end when pressing the enter key on a physical keyboard.
     */
    private static int ignoredEnterKeyPressedCount;
    
    /**
     * References the default style instance for keyboards.
     */
    private static RamKeyboardStyle defaultKeyboardStyle = new RamKeyboardStyle();
    
    /**
     * Keyboard opening position.
     */
    public enum KeyboardPosition {
        
        /** The default keyboard position. */
        DEFAULT,
        /** On the top of the parent component. */
        TOP,
        /** On the bottom of the parent component. */
        BOTTOM,
        /** On the left of the parent component. */
        LEFT,
        /** On the right of the parent component. */
        RIGHT
    }
    
    /**
     * The type of a keyboard key.
     */
    protected enum KeyType {
        
        /** A printable char key. */
        CHAR,
        
        /** A special key such as cancel, arrows, backspace. */
        SPECIAL,
        
        /** A modifier key such as shift, symbols. */
        MODIFIER
    }
    
    /**
     * The state of the shift layout.
     */
    private enum ShiftState {
        
        /** The shift key is not pressed. */
        NONE,
        
        /** The shift key is down. */
        DOWN,
        
        /** The shift key is up but locked for the next char only. */
        ONCE,
        
        /** The shift key is down while typing chars. */
        HOLD,
        
        /** The shift key is locked. */
        LOCK
    }
    
    /**
     * The state of the symbols layout.
     */
    private enum SymbolsState {
        DISABLE,
        ENABLE
    }
    
    private List<Key> keys;
    private boolean hardwareInputEnabled;
    private RamKeyboardStyle keyboardStyle;
    private ShiftState shiftState;
    private SymbolsState symbolsState;
    private Set<RamKeyboardListener> listeners;
    private MTRectangle keysContainer;
    private AbstractShape parentComponent;
    private boolean numericsAllowed;
    private boolean alphasAllowed;
    private boolean preferTouchKeyboard;
    private Set<Character> allowedCharacters;
    
    /**
     * Constructs a RAM keyboard with the default style.
     */
    public RamKeyboard() {
        this(getDefaultKeyboardStyle());
    }
    
    /**
     * Constructs a RAM keyboard with the given style.
     * 
     * @param style A keyboard style instance
     */
    public RamKeyboard(RamKeyboardStyle style) {
        
        super(RamApp.getApplication(), 0, 0, 0, style.getDefaultWidth(), style.getDefaultHeight());
        
        this.keyboardStyle = style;
        keys = new ArrayList<Key>();
        shiftState = ShiftState.NONE;
        symbolsState = SymbolsState.DISABLE;
        listeners = new HashSet<RamKeyboardListener>();
        allowedCharacters = new HashSet<Character>();
        numericsAllowed = true;
        alphasAllowed = true;
        preferTouchKeyboard = false;
        
        setName("Unnamed RAM keyboard");
        setVisible(false);
        setNoStroke(true);
        setNoFill(false);
        setFillColor(style.getBackgroundColor());
        setDepthBufferDisabled(true);
        setDrawSmooth(true);
        setHardwareInputEnabled(true);
        
        if (MT4jSettings.getInstance().isOpenGlMode()) {
            setUseDirectGL(true);
        }
        
        float width = style.getDefaultWidth() - style.getLeftMarginThickness() - style.getRightMarginThickness();
        float height = style.getDefaultHeight() - style.getTopMarginThickness() - style.getBottomMarginThickness();
        
        keysContainer = new MTRectangle(this.getRenderer(), width, height);
        keysContainer.unregisterAllInputProcessors();
        keysContainer.setNoFill(true);
        keysContainer.setNoStroke(true);
        keysContainer.setPositionRelativeToParent(
                new Vector3D(style.getLeftMarginThickness() + width / 2, style.getTopMarginThickness() + height / 2));
        addChild(keysContainer);
        
        addGestureListener(DragProcessor.class, new InertiaDragAction());
        initKeys();
        allowDefaultCharacters();
    }
    
    /**
     * Initializes the keyboard layout.
     */
    private void initKeys() {
        
        // Key positioning works with percentages
        final float wKey = 6.6667f;
        final float hKey = 25f;
        final float offset2 = 2f;
        
        // CHECKSTYLE:IGNORE LineLength FOR 52 LINES: Okay here
        // CHECKSTYLE:IGNORE MagicNumber FOR 51 LINES: Okay here
        // CHECKSTYLE:IGNORE MultipleStringLiterals FOR 50 LINES: Okay here
        Key[] keyList = new Key[] {
            new Key(this, new String[]{"~", "~", "`"}, null, KeyType.CHAR, 0 * wKey, 0 * hKey, wKey, hKey),
            new Key(this, new String[]{"q", "Q", "1"}, null, KeyType.CHAR, 1 * wKey, 0 * hKey, wKey, hKey),
            new Key(this, new String[]{"w", "W", "2"}, null, KeyType.CHAR, 2 * wKey, 0 * hKey, wKey, hKey),
            new Key(this, new String[]{"e", "E", "3"}, null, KeyType.CHAR, 3 * wKey, 0 * hKey, wKey, hKey),
            new Key(this, new String[]{"r", "R", "4"}, null, KeyType.CHAR, 4 * wKey, 0 * hKey, wKey, hKey),
            new Key(this, new String[]{"t", "T", "5"}, null, KeyType.CHAR, 5 * wKey, 0 * hKey, wKey, hKey),
            new Key(this, new String[]{"y", "Y", "6"}, null, KeyType.CHAR, 6 * wKey, 0 * hKey, wKey, hKey),
            new Key(this, new String[]{"u", "U", "7"}, null, KeyType.CHAR, 7 * wKey, 0 * hKey, wKey, hKey),
            new Key(this, new String[]{"i", "I", "8"}, null, KeyType.CHAR, 8 * wKey, 0 * hKey, wKey, hKey),
            new Key(this, new String[]{"o", "O", "9"}, null, KeyType.CHAR, 9 * wKey, 0 * hKey, wKey, hKey),
            new Key(this, new String[]{"p", "P", "0"}, null, KeyType.CHAR, 10 * wKey, 0 * hKey, wKey, hKey),
            new Key(this, new String[]{"-", "-", "_"}, null, KeyType.CHAR, 11 * wKey, 0 * hKey, wKey, hKey),
            new Key(this, new String[]{"a", "A", "!"}, null, KeyType.CHAR, offset2 + 1 * wKey, 1 * hKey, wKey, hKey),
            new Key(this, new String[]{"s", "S", "@"}, null, KeyType.CHAR, offset2 + 2 * wKey, 1 * hKey, wKey, hKey),
            new Key(this, new String[]{"d", "D", "#"}, null, KeyType.CHAR, offset2 + 3 * wKey, 1 * hKey, wKey, hKey),
            new Key(this, new String[]{"f", "F", "$"}, null, KeyType.CHAR, offset2 + 4 * wKey, 1 * hKey, wKey, hKey),
            new Key(this, new String[]{"g", "G", "%"}, null, KeyType.CHAR, offset2 + 5 * wKey, 1 * hKey, wKey, hKey),
            new Key(this, new String[]{"h", "H", "^"}, null, KeyType.CHAR, offset2 + 6 * wKey, 1 * hKey, wKey, hKey),
            new Key(this, new String[]{"j", "J", "&"}, null, KeyType.CHAR, offset2 + 7 * wKey, 1 * hKey, wKey, hKey),
            new Key(this, new String[]{"k", "K", "*"}, null, KeyType.CHAR, offset2 + 8 * wKey, 1 * hKey, wKey, hKey),
            new Key(this, new String[]{"l", "L", "\""}, null, KeyType.CHAR, offset2 + 9 * wKey, 1 * hKey, wKey, hKey),
            new Key(this, new String[]{";", ";", "'"}, null, KeyType.CHAR, offset2 + 10 * wKey, 1 * hKey, wKey, hKey),
            new Key(this, new String[]{"+", "+", "="}, null, KeyType.CHAR, offset2 + 11 * wKey, 1 * hKey, wKey, hKey),
            new Key(this, new String[]{"z", "Z", "{"}, null, KeyType.CHAR, 2 * wKey, 2 * hKey, wKey, hKey),
            new Key(this, new String[]{"x", "X", "}"}, null, KeyType.CHAR, 3 * wKey, 2 * hKey, wKey, hKey),
            new Key(this, new String[]{"c", "C", "["}, null, KeyType.CHAR, 4 * wKey, 2 * hKey, wKey, hKey),
            new Key(this, new String[]{"v", "V", "]"}, null, KeyType.CHAR, 5 * wKey, 2 * hKey, wKey, hKey),
            new Key(this, new String[]{"b", "B", "|"}, null, KeyType.CHAR, 6 * wKey, 2 * hKey, wKey, hKey),
            new Key(this, new String[]{"n", "N", "/"}, null, KeyType.CHAR, 7 * wKey, 2 * hKey, wKey, hKey),
            new Key(this, new String[]{"m", "M", ":"}, null, KeyType.CHAR, 8 * wKey, 2 * hKey, wKey, hKey),
            new Key(this, new String[]{",", ",", "?"}, null, KeyType.CHAR, 9 * wKey, 2 * hKey, wKey, hKey),
            new Key(this, new String[]{".", ".", "."}, null, KeyType.CHAR, 10 * wKey, 2 * hKey, wKey, hKey),
            new Key(this, new String[]{"(", "(", "<"}, null, KeyType.CHAR, 11 * wKey, 2 * hKey, wKey, hKey),
            new Key(this, new String[]{")", ")", ">"}, null, KeyType.CHAR, 12 * wKey, 2 * hKey, wKey, hKey),
            new Key(this, new String[]{" ", " ", " "}, new String[]{"space", "space", "space"}, KeyType.CHAR, 3 * wKey, 3 * hKey, 9 * wKey, hKey),
            new Key(this, new String[]{"\t", "\t", "\t"}, new String[]{"\u21E5", "\u21E5", "\u21E5"}, KeyType.CHAR, 0 * wKey, 1 * hKey, 1 * wKey + offset2, hKey),
            new Key(this, new String[]{"\n", "\n", "\n"}, new String[]{"\u21B5", "\u21B5", "\u21B5"}, KeyType.CHAR, offset2 + 12 * wKey, 1 * hKey, 2 * wKey - offset2, hKey),

            new Key(this, new String[]{"shift", "shift", "shift"}, new String[]{"\u21E7", "\u21E7", "\u21E7"}, KeyType.MODIFIER, 0 * wKey, 2 * hKey, 2 * wKey, hKey),
            new Key(this, new String[]{"symbols", "symbols", "symbols"}, new String[]{"&123", "&123", "&123"}, KeyType.MODIFIER, 0 * wKey, 3 * hKey, 3 * wKey, hKey),
            
            new Key(this, new String[]{"back", "back", "back"}, new String[]{"\u2190", "\u2190", "\u2190"}, KeyType.SPECIAL, 12 * wKey, 0 * hKey, 2 * wKey, hKey, true),
            new Key(this, new String[]{"up", "up", "up"}, new String[]{"\u25B2", "\u25B2", "\u25B2"}, KeyType.SPECIAL, 13 * wKey, 2 * hKey, wKey, hKey),
            new Key(this, new String[]{"left", "left", "left"}, new String[]{"\u25C4", "\u25C4", "\u25C4"}, KeyType.SPECIAL, 12 * wKey, 3 * hKey, wKey, hKey),
            new Key(this, new String[]{"bottom", "bottom", "bottom"}, new String[]{"\u25BC", "\u25BC", "\u25BC"}, KeyType.SPECIAL, 13 * wKey, 3 * hKey, wKey, hKey),
            new Key(this, new String[]{"right", "right", "right"}, new String[]{"\u25BA", "\u25BA", "\u25BA"}, KeyType.SPECIAL, 14 * wKey, 3 * hKey, wKey, hKey),
            new Key(this, new String[]{"validate", "validate", "validate"}, new String[]{"\u2713", "\u2713", "\u2713"}, KeyType.SPECIAL, 14 * wKey, 0 * hKey, wKey, 2 * hKey),
            new Key(this, new String[]{"cancel", "cancel", "cancel"}, new String[]{"\u2715", "\u2715", "\u2715"}, KeyType.SPECIAL, 14 * wKey, 2 * hKey, wKey, hKey)
        };
        for (Key k : keyList) {
            keys.add(k);
            k.initialize();
        }
    }
    
    /**
     * Update the layout GUI depending on the state of the shift/symbols key.
     */
    private void updateLayout() {
        
        int layout = 0;
        
        if (!shiftState.equals(ShiftState.NONE)) {
            layout = 1;
        } else if (symbolsState.equals(SymbolsState.ENABLE)) {
            layout = 2;
        }
        
        // Switch keys layout
        for (Key k : keys) {
            k.changeLayout(layout);
            if (isKey(k.getCurrentKey(), "shift")) {
                if (layout == 1) {
                    k.setDownStyle();
                    if (shiftState.equals(ShiftState.LOCK)) {
                        k.changeTag("\u21EA");
                    }
                } else {
                    k.setUpStyle();
                }
            } else if (isKey(k.getCurrentKey(), "symbols")) {
                if (layout == 2) {
                    k.setDownStyle();
                    k.changeTag("ABC");
                } else {
                    k.setUpStyle();
                }
            }
        }
    }
    
    /**
     * Called when a key is down on the virtual keyboard.
     * 
     * @param key The key
     * @param keyType The type of the key
     */
    protected void onKeyboardButtonDown(String key, KeyType keyType) {
        
        if (keyType == KeyType.CHAR) {
            for (RamKeyboardListener listener : listeners) {
                listener.appendCharByUnicode(key);
            }
        } else if (isKey(key, "back")) {
            for (RamKeyboardListener listener : listeners) {
                listener.removeLastCharacter();
            }
        } else if (keyType == KeyType.SPECIAL && isAnyKey(key, "right", "bottom")) {
            for (RamKeyboardListener listener : listeners) {
                listener.modifierKeyPressed(KeyEvent.VK_RIGHT);
            }
        } else if (keyType == KeyType.SPECIAL && isAnyKey(key, "left", "up")) {
            for (RamKeyboardListener listener : listeners) {
                listener.modifierKeyPressed(KeyEvent.VK_LEFT);
            }
        }
        
        // State machines
        if (isKey(key, "shift")) {
            switch (shiftState) {
                case NONE:
                    shiftState = ShiftState.DOWN;
                    break;
                case ONCE:
                case LOCK:
                    shiftState = ShiftState.HOLD;
                    break;
                default:
                    break;
            }
            symbolsState = SymbolsState.DISABLE;
            updateLayout();
            
        } else if (isKey(key, "symbols")) {
            symbolsState = symbolsState.equals(SymbolsState.DISABLE) ? SymbolsState.ENABLE : SymbolsState.DISABLE;
            shiftState = ShiftState.NONE;
            updateLayout();
        }
        
        // Prefer touch over physical keyboard for this keyboard
        if (!preferTouchKeyboard) {
            preferTouchKeyboard = true;
            updatePhysicalKeyboardFocus();
        }
    }
    
    /**
     * Called when a key is up on the virtual keyboard.
     * 
     * @param key The key
     * @param keyType The type of the key
     */
    protected void onKeyboardButtonUp(String key, KeyType keyType) {
        
        // State machines
        if (isKey(key, "shift")) {
            switch (shiftState) {
                case DOWN:
                    shiftState = ShiftState.ONCE;
                    break;
                case HOLD:
                    shiftState = ShiftState.NONE;
                    break;
                default:
                    break;
            }
            updateLayout();
            
        } else if (isKey(key, "symbols")) {
            updateLayout();
        }
    }
    
    /**
     * Called when a key is clicked (down, up, click) on the virtual keyboard.
     * 
     * @param key The key
     * @param keyType The type of the key
     */
    protected void onKeyboardButtonClicked(String key, KeyType keyType) {
        
        // State machines
        if (keyType.equals(KeyType.CHAR)) {
            switch (shiftState) {
                case ONCE:
                    shiftState = ShiftState.NONE;
                    break;
                case DOWN:
                    shiftState = ShiftState.HOLD;
                    break;
                default:
                    break;
            }
            updateLayout();
        } else if (keyType.equals(KeyType.SPECIAL)) {
            if (isKey(key, "validate")) {
                onValidateButtonClicked();
            } else if (isKey(key, "cancel")) {
                onCancelButtonClicked();
            }
        }
    }
    
    /**
     * Called when a key is double-clicked (down, up, click, down, up, double-click) on the virtual keyboard.
     * 
     * @param key The key
     * @param keyType The type of the key
     */
    protected void onKeyboardButtonDoubleClicked(String key, KeyType keyType) {
        
        // Shift state machine
        if (isKey(key, "shift")) {
            shiftState = ShiftState.LOCK;
            updateLayout();
        }
    }
    
    /**
     * Called when a key is held on the virtual keyboard.
     * 
     * @param key The key
     * @param keyType The type of the key
     */
    protected void onKeyboardButtonHeld(String key, KeyType keyType) {
        
        // Backspace held
        if (isKey(key, "back")) {
            for (RamKeyboardListener listener : listeners) {
                listener.clear();
            }
        }
    }
    
    /**
     * Called when the cancel key is clicked on the virtual keyboard.
     */
    protected void onCancelButtonClicked() {
        dimissKeyboard(false);
    }
    
    /**
     * Called when the validate key is clicked on the virtual keyboard.
     */
    protected void onValidateButtonClicked() {
        dimissKeyboard(true);
    }
    
    /**
     * Called when a key is pressed on the physical keyboard.
     * 
     * @param key The key char
     * @param keyCode The key code
     */
    @Override
    public void keyPressed(char key, int keyCode) {
        
        /*
         * Because the enter key can trigger keyboard close and disorder the current opened keyboards list,
         * we delay the keyPressed event of the focused keyboard at the end of the event loop.
         */
        if (keyCode == KeyEvent.VK_ENTER && ignoredEnterKeyPressedCount < currentHardwareKeyboards.size()) {
            
            ignoredEnterKeyPressedCount++;
            
            if (ignoredEnterKeyPressedCount == currentHardwareKeyboards.size() && physicalKeyboardFocus != null) {
                physicalKeyboardFocus.keyPressed(key, keyCode);
            }
            
            return;
            
        } else if (keyCode == KeyEvent.VK_ENTER) {
            ignoredEnterKeyPressedCount = 0;
        } else if (physicalKeyboardFocus != this) {
            // For other keys, if this keyboard has not the focus of the physical keyboard, we stop the event here
            return;
        }
        
        // This allows us to use the physical keyboard in the same way as the virtual one
        switch (keyCode) {
            case KeyEvent.VK_CONTROL:
                break;
            case KeyEvent.VK_SHIFT:
                break;
            case KeyEvent.VK_BACK_SPACE:
                for (RamKeyboardListener listener : listeners) {
                    listener.removeLastCharacter();
                }
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_DOWN:
                for (RamKeyboardListener listener : listeners) {
                    listener.modifierKeyPressed(KeyEvent.VK_LEFT);
                }
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_UP:
                for (RamKeyboardListener listener : listeners) {
                    listener.modifierKeyPressed(KeyEvent.VK_RIGHT);
                }
                break;
            default:
                String character = Character.toString(key);
                if (acceptedCharacter(character.charAt(0))) {
                    for (RamKeyboardListener listener : listeners) {
                        listener.appendCharByUnicode(character);
                    }
                }
        }
    }
    
    /**
     * Called when a key is released on the physical keyboard.
     * 
     * @param key The key char
     * @param keyCode The key code
     */
    @Override
    public void keyRleased(char key, int keyCode) {
    }
    
    /**
     * Try to position the keyboard on the left.
     * 
     * @param min The minimum point
     * @param max The maximum point
     * @param kb The keyboard size (x = width, y = height)
     * @param bounds The parent bounds
     * @param extraSpace The extra optional space between the keyboard and the parent
     * @return The keyboard position if success, null otherwise
     */
    private static Vector3D tryPositionLeft(Vector3D min, Vector3D max, Vector3D kb,
            IBoundingShape bounds, float extraSpace) {
        float x = MathUtils.getXFromBounds(bounds) - kb.getX();
        if (x >= min.getX()) {
            x = x + kb.getX() / 2 - extraSpace;
            float y = MathUtils.getYFromBounds(bounds) + MathUtils.getHeightLocal(bounds) / 2;
            return new Vector3D(x, y);
        }
        return null;
    }
    
    /**
     * Try to position the keyboard on the right.
     * 
     * @param min The minimum point
     * @param max The maximum point
     * @param kb The keyboard size (x = width, y = height)
     * @param bounds The parent bounds
     * @param extraSpace The extra optional space between the keyboard and the parent
     * @return The keyboard position if success, null otherwise
     */
    private static Vector3D tryPositionRight(Vector3D min, Vector3D max, Vector3D kb,
            IBoundingShape bounds, float extraSpace) {
        float x = MathUtils.getXFromBounds(bounds) + MathUtils.getWidthLocal(bounds) + kb.getX();
        if (x <= max.getX()) {
            x = x - kb.getX() / 2 + extraSpace;
            float y = MathUtils.getYFromBounds(bounds) + MathUtils.getHeightLocal(bounds) / 2;
            return new Vector3D(x, y);
        }
        return null;
    }
    
    /**
     * Try to position the keyboard on the top.
     * 
     * @param min The minimum point
     * @param max The maximum point
     * @param kb The keyboard size (x = width, y = height)
     * @param bounds The parent bounds
     * @param extraSpace The extra optional space between the keyboard and the parent
     * @return The keyboard position if success, null otherwise
     */
    private static Vector3D tryPositionTop(Vector3D min, Vector3D max, Vector3D kb,
            IBoundingShape bounds, float extraSpace) {
        float y = MathUtils.getYFromBounds(bounds) - kb.getY();
        if (y >= min.getY()) {
            y = y + kb.getY() / 2 - extraSpace;
            float x = MathUtils.getXFromBounds(bounds) + MathUtils.getWidthLocal(bounds) / 2;
            return new Vector3D(x, y);
        }
        return null;
    }
    
    /**
     * Try to position the keyboard on the bottom.
     * 
     * @param min The minimum point
     * @param max The maximum point
     * @param kb The keyboard size (x = width, y = height)
     * @param bounds The parent bounds
     * @param extraSpace The extra optional space between the keyboard and the parent
     * @return The keyboard position if success, null otherwise
     */
    private static Vector3D tryPositionBottom(Vector3D min, Vector3D max, Vector3D kb,
            IBoundingShape bounds, float extraSpace) {
        float y = MathUtils.getYFromBounds(bounds) + MathUtils.getHeightLocal(bounds) + kb.getY();
        if (y <= max.getY()) {
            y = y - kb.getY() / 2 + extraSpace;
            float x = MathUtils.getXFromBounds(bounds) + MathUtils.getWidthLocal(bounds) / 2;
            return new Vector3D(x, y);
        }
        return null;
    }
    
    /**
     * Displays the keyboard on the bottom and informs the parent about key events.
     * 
     * @param parent The parent component
     * @return Whether the keyboard was successfully displayed
     */
    public boolean display(AbstractShape parent) {
        return display(parent, KeyboardPosition.BOTTOM);
    }
    
    /**
     * Displays the keyboard on the given position and informs the parent about key events.
     * 
     * @param parent The parent component
     * @param position The position relative to the parent
     * @return Whether the keyboard was successfully displayed
     */
    public boolean display(AbstractShape parent, KeyboardPosition position) {
        
        final float extraSpace = 5f;
        IBoundingShape bounds = parent.getBounds();
        
        Vector3D min = new Vector3D(0, 0);
        Vector3D max = new Vector3D(getRenderer().getWidth(), getRenderer().getHeight());
        Vector3D kb = new Vector3D(getWidthXYGlobal(), getHeightXYGlobal());
        
        Vector3D result = new Vector3D();
        KeyboardPosition pos = position;
        
        if (pos == KeyboardPosition.DEFAULT) {
            pos = DEFAULT_KEYBOARD_POSITION;
        }
        
        if (pos == KeyboardPosition.BOTTOM || pos == KeyboardPosition.TOP) {
            if (pos == KeyboardPosition.BOTTOM) {
                result = tryPositionBottom(min, max, kb, bounds, extraSpace);
                if (result == null) {
                    result = tryPositionTop(min, max, kb, bounds, extraSpace);
                }
            } else if (pos == KeyboardPosition.TOP) {
                result = tryPositionTop(min, max, kb, bounds, extraSpace);
                if (result == null) {
                    result = tryPositionBottom(min, max, kb, bounds, extraSpace);
                }
            }
            if (result == null) {
                result = tryPositionLeft(min, max, kb, bounds, extraSpace);
            }
            if (result == null) {
                result = tryPositionRight(min, max, kb, bounds, extraSpace);
            }
        } else if (pos == KeyboardPosition.LEFT || pos == KeyboardPosition.RIGHT) {
            if (pos == KeyboardPosition.LEFT) {
                result = tryPositionLeft(min, max, kb, bounds, extraSpace);
                if (result == null) {
                    result = tryPositionRight(min, max, kb, bounds, extraSpace);
                }
            } else if (pos == KeyboardPosition.RIGHT) {
                result = tryPositionRight(min, max, kb, bounds, extraSpace);
                if (result == null) {
                    result = tryPositionLeft(min, max, kb, bounds, extraSpace);
                }
            }
            if (result == null) {
                result = tryPositionBottom(min, max, kb, bounds, extraSpace);
            }
            if (result == null) {
                result = tryPositionTop(min, max, kb, bounds, extraSpace);
            }
        }
        
        if (result == null) {
            result = bounds.getCenterPointGlobal();
        }
        
        final float x = MathUtils.clampFloat(min.getX() + kb.getX() / 2, result.getX(), max.getX() - kb.getX() / 2);
        final float y = MathUtils.clampFloat(min.getY() + kb.getY() / 2, result.getY(), max.getY() - kb.getY() / 2);
        
        return display(parent, new Vector3D(x, y));
    }
    
    /**
     * Displays the keyboard at the given position and informs the parents about key events.
     * 
     * @param parent The parent component
     * @param position The position at which the keyboard will be displayed
     * @return Whether the keyboard was successfully displayed
     */
    public boolean display(AbstractShape parent, Vector3D position) {
        
        boolean result = setParent(parent, position);
        setVisible(true);
        
        for (RamKeyboardListener listener : listeners) {
            listener.keyboardOpened();
        }
        
        if (isHardwareInputEnabled()) {
            addToCurrentlyOpenedHardwareKeyboardList();
        }
        
        return result;
    }
    
    /**
     * Removes the keyboard from the screen.
     * 
     * @param success Whether the close was successful, this determines if closed() will be called on
     *            {@link RamKeyboardListener}.
     */
    public void dimissKeyboard(boolean success) {
        
        if (success) {
            boolean dismiss = true;
            for (RamKeyboardListener listener : listeners) {
                dismiss &= listener.verifyKeyboardDismissed();
            }
            if (dismiss) {
                for (RamKeyboardListener listener : listeners) {
                    listener.keyboardClosed(this);
                }
                close();
            }
        } else {
            for (RamKeyboardListener listener : listeners) {
                listener.keyboardCancelled();
            }
            close();
        }
    }
    
    /**
     * Close and destroy the keyboard with an animation.
     */
    public void close() {
        
        removeFromCurrentlyOpenedHardwareKeyboardList();
        
        // Animate closing
        final float from = this.getWidthXY(TransformSpace.RELATIVE_TO_PARENT);
        final float to = 1f;
        final float duration = 300f;
        final float endAcceleration = 0.2f;
        final float startAcceleration = 0.5f;
        final int loopCount = 1;
        
        IAnimation kbCloseAnimation = new Animation("Keyboard Fade",
                new MultiPurposeInterpolator(from, to, duration, endAcceleration, startAcceleration, loopCount), this);
        
        kbCloseAnimation.addAnimationListener(new IAnimationListener() {
            @Override
            public void processAnimationEvent(AnimationEvent ae) {
                switch (ae.getId()) {
                    case AnimationEvent.ANIMATION_STARTED:
                    case AnimationEvent.ANIMATION_UPDATED:
                        float value = ae.getAnimation().getValue();
                        MathUtils.setWidthRelativeToParent(RamKeyboard.this, value);
                        break;
                    case AnimationEvent.ANIMATION_ENDED:
                        setVisible(false);
                        destroy();
                        break;
                    default:
                        break;
                }
            }
        });
        kbCloseAnimation.start();
    }
    
    @Override
    protected void destroyComponent() {
        listeners.clear();
        setHardwareInputEnabled(false);
        super.destroyComponent();
    }
    
    /**
     * Registers a listener to the current {@link RamKeyboard} instance.
     * 
     * @param listener The listener to register.
     */
    public void registerListener(RamKeyboardListener listener) {
        listeners.add(listener);
    }
    
    /**
     * Unregisters a listener from the current {@link RamKeyboard} instance.
     * 
     * @param listener The listener to unregister.
     */
    public void unregisterListener(RamKeyboardListener listener) {
        listeners.remove(listener);
    }
    
    /**
     * Checks whether the character should be sent to listeners based on current state.
     * 
     * @param character The character to check
     * @return Whether the character is accepted or not
     */
    private boolean acceptedCharacter(Character character) {
        
        if ('a' <= Character.toLowerCase(character) && Character.toLowerCase(character) <= 'z') {
            // alpha
            return alphasAllowed;
        } else if ('0' <= character && character <= '9') {
            // numerics
            return numericsAllowed;
        } else {
            return allowedCharacters.contains(character);
        }
    }
    
    /**
     * Allows or disallows alpha characters (a-z and A-Z) from being passed to the keyboard's listeners.
     * 
     * @param allow Whether alpha characters are allowed
     */
    public void allowAlphas(boolean allow) {
        alphasAllowed = allow;
    }
    
    /**
     * Allows or disallows numerics (0-9) from being passed to the keyboard's listeners.
     * 
     * @param allow Whether numerics are allowed
     */
    public void allowNumerics(boolean allow) {
        numericsAllowed = allow;
    }
    
    /**
     * Allows a character to be passed to the keyboard's listeners.
     * NOTE: this will allow alphas and numerics even if allow{Alphas|Numerics}(false) or has been called.
     * 
     * @param character The character to allow
     */
    public void allowCharacter(Character character) {
        allowedCharacters.add(character);
    }
    
    /**
     * Allows a default character set (excluding alphas and numerics, use allow{Aphas|Numerics}() for those) to be
     * passed to to the keyboard's listeners.
     */
    public void allowDefaultCharacters() {
        allowedCharacters.clear();
        for (Key key : keys) {
            if (key.getKeyType() == KeyType.CHAR) {
                for (String c : key.getKeys()) {
                    allowedCharacters.add(c.charAt(0));
                }
            }
        }
    }
    
    /**
     * Disallows a character from being passed to the keyboard's listeners.
     * NOTE: that this cannot disallow alpha character is allowAlpha(true) is set, the same is true for numerics and
     * allowNumerics(true).
     * 
     * @param character The character to disallow
     */
    public void disallowCharacter(Character character) {
        allowedCharacters.remove(character);
    }
    
    /**
     * Set the parent of the keyboard.
     * 
     * @param parent The component to which keyboard events will be sent
     * @param position The position at which to display the keyboard
     * @return Whether the parent was successfully set
     */
    public boolean setParent(AbstractShape parent, Vector3D position) {
        
        if (parentComponent != null) {
            close();
        }
        
        setVisible(true);
        setPositionGlobal(position);
        RamApp.getApplication().getCurrentScene().getCanvas().addChild(this);
        parentComponent = parent;
        return true;
    }
    
    /**
     * Set the position of the keyboard.
     * 
     * @param vector3d The position at which the keyboard should be displayed
     */
    public void setPosition(Vector3D vector3d) {
        setPositionRelativeToParent(vector3d);
    }
    
    /**
     * Set symbols state.
     * 
     * @param enabled Whether the symbols state should be enabled.
     */
    public void setSymbolsState(boolean enabled) {
        if (enabled) {
            this.symbolsState = SymbolsState.ENABLE;
        } else {
            this.symbolsState = SymbolsState.DISABLE;
        }
        this.updateLayout();
    }
    
    /**
     * Update the keyboard which have the focus of the physical keyboard by
     * fetching the best candidate between currently opened keyboards.
     */
    private static void updatePhysicalKeyboardFocus() {
        
        RamKeyboard candidateKeyboard = currentHardwareKeyboards.size() > 0 ? currentHardwareKeyboards.get(0) : null;
        
        for (RamKeyboard kb : currentHardwareKeyboards) {
            if (!kb.preferTouchKeyboard) {
                candidateKeyboard = kb;
                break;
            }
        }
        
        physicalKeyboardFocus = candidateKeyboard;
    }
    
    /**
     * Enable or disable the physical keyboard support (default is enabled).
     * 
     * @param hardwareInputEnabled true to enable, false to disable.
     */
    public void setHardwareInputEnabled(boolean hardwareInputEnabled) {
        this.hardwareInputEnabled = hardwareInputEnabled;
        IMTApplication app = (IMTApplication) getRenderer();
        if (hardwareInputEnabled) {
            app.addKeyListener(this);
            if (isVisible()) {
                addToCurrentlyOpenedHardwareKeyboardList();
            }
        } else {
            app.removeKeyListener(this);
            removeFromCurrentlyOpenedHardwareKeyboardList();
        }
    }
    
    /**
     * Get the physical keyboard support.
     * 
     * @return Whether the physical keyboard support is enabled (true) or disable (false).
     */
    public boolean isHardwareInputEnabled() {
        return hardwareInputEnabled;
    }
    
    /**
     * Add this keyboard to the static list of currently opened keyboards that support hardware inputs.
     */
    private void addToCurrentlyOpenedHardwareKeyboardList() {
        if (!currentHardwareKeyboards.contains(this)) {
            currentHardwareKeyboards.add(this);
            updatePhysicalKeyboardFocus();
        }
    }
    
    /**
     * Remove this keyboard from the static list of currently opened keyboards that support hardware inputs.
     */
    private void removeFromCurrentlyOpenedHardwareKeyboardList() {
        currentHardwareKeyboards.remove(this);
        updatePhysicalKeyboardFocus();
    }
    
    /**
     * Check the key name.
     * 
     * @param key The key
     * @param v1 The key name
     * @return Whether it is the same key or not
     */
    private static boolean isKey(String key, String v1) {
        return key.equals(v1);
    }
    
    /**
     * Check if the key is one of both.
     * 
     * @param key The key
     * @param v1 The first key name
     * @param v2 The second key name
     * @return Whether the key is one of both
     */
    private static boolean isAnyKey(String key, String v1, String v2) {
        return key.equals(v1) || key.equals(v2);
    }
    
    /**
     * Get the keyboard style instance.
     * 
     * @return The keyboard style instance
     */
    public RamKeyboardStyle getKeyboardStyle() {
        return keyboardStyle;
    }
    
    /**
     * Get the default keyboard style instance for future keyboard instances.
     * 
     * @param defaultKeyboardStyle The keyboard style instance
     */
    public static void setDefaultKeyboardStyle(RamKeyboardStyle defaultKeyboardStyle) {
        RamKeyboard.defaultKeyboardStyle = defaultKeyboardStyle;
    }
    
    /**
     * Get the default keyboard style instance.
     * 
     * @return The default keyboard style instance
     */
    public static RamKeyboardStyle getDefaultKeyboardStyle() {
        return defaultKeyboardStyle;
    }
    
    /**
     * The keyboard style class allows the user to customize the keyboard aspect.
     */
    @SuppressWarnings("javadoc")
    public static class RamKeyboardStyle {
        
        // CHECKSTYLE:IGNORE Javadoc FOR 27 LINES: Self documented
        // CHECKSTYLE:IGNORE MagicNumber FOR 26 LINES: Self documented numbers and colors
        // CHECKSTYLE:IGNORE LeftCurly FOR 25 LINES: Easier to read.
        public float getDefaultWidth()                      { return 690f; }
        public float getDefaultHeight()                     { return 208f; }
        public float getTopMarginThickness()                { return 25f; }
        public float getRightMarginThickness()              { return 50f; }
        public float getBottomMarginThickness()             { return 25f; }
        public float getLeftMarginThickness()               { return 50f; }
        public float getKeyMarginThickness()                { return 3f; }
        public MTColor getBackgroundColor()                 { return Colors.KEYBOARD_BACKGROUND_COLOR; }
        public MTColor getKeyBackgroundColor()              { return Colors.KEYBOARD_KEY_UP_COLOR; }
        public MTColor getModifierKeyBackgroundColor()      { return Colors.KEYBOARD_MODIFIER_KEY_UP_COLOR; }
        public MTColor getValidateKeyBackgroundColor()      { return Colors.KEYBOARD_VALIDATE_KEY_UP_COLOR; }
        public MTColor getCancelKeyBackgroundColor()        { return Colors.KEYBOARD_CANCEL_KEY_UP_COLOR; }
        public MTColor getKeyDownBackgroundColor()          { return Colors.KEYBOARD_KEY_DOWN_COLOR; }
        public MTColor getModifierKeyDownBackgroundColor()  { return Colors.KEYBOARD_MODIFIER_KEY_DOWN_COLOR; }
        public MTColor getValidateKeyDownBackgroundColor()  { return Colors.KEYBOARD_VALIDATE_KEY_DOWN_COLOR; }
        public MTColor getCancelKeyDownBackgroundColor()    { return Colors.KEYBOARD_CANCEL_KEY_DOWN_COLOR; }
        public MTColor getKeyFontColor()                    { return Colors.KEYBOARD_DEFAULT_FONT_COLOR; }
        public MTColor getModifierKeyFontColor()            { return Colors.KEYBOARD_DEFAULT_FONT_COLOR; }
        public MTColor getValidateKeyFontColor()            { return Colors.KEYBOARD_DEFAULT_FONT_COLOR; }
        public MTColor getCancelKeyFontColor()              { return Colors.KEYBOARD_DEFAULT_FONT_COLOR; }
        public MTColor getKeyDownFontColor()                { return Colors.KEYBOARD_PRESSED_FONT_COLOR; }
        public MTColor getModifierKeyDownFontColor()        { return Colors.KEYBOARD_DEFAULT_FONT_COLOR; }
        public MTColor getValidateKeyDownFontColor()        { return Colors.KEYBOARD_DEFAULT_FONT_COLOR; }
        public MTColor getCancelKeyDownFontColor()          { return Colors.KEYBOARD_DEFAULT_FONT_COLOR; }
    }
    
    /**
     * The key class represents a touch key which responds to touch events.
     */
    private class Key extends MTRectangle implements IGestureEventListener {
        
        private RamKeyboard keyboard;
        private String[] keys;
        private String[] tags;
        private KeyType keyType;
        private float leftPercent;
        private float topPercent;
        private float widthPercent;
        private float heightPercent;
        private boolean enableHold;
        private int layout;
        private MTTextArea label;
        
        /**
         * Create a key.
         * 
         * @param keyboard The associated keyboard.
         * @param keys An array of chars, one value for each layout level
         * @param tags An array of tags, the visual text on the keyboard
         * @param keyType The key type (char, special, modifier...)
         * @param leftPercent The left align percentage on the keyboard layout
         * @param topPercent The top align percentage on the keyboard layout
         * @param widthPercent The width of the key in percentage on the keyboard layout
         * @param heightPercent The height of the key in percentage on the keyboard layout
         */
        // CHECKSTYLE:IGNORE ParameterNumber: Internal class constructor
        public Key(RamKeyboard keyboard, String[] keys, String[] tags, KeyType keyType,
                float leftPercent, float topPercent, float widthPercent, float heightPercent) {
            this(keyboard, keys, tags, keyType, leftPercent, topPercent, widthPercent, heightPercent, false);
        }
        
        /**
         * Create a key.
         * 
         * @param keyboard The associated keyboard.
         * @param keys An array of chars, one value for each layout level
         * @param tags An array of tags, the visual text on the keyboard
         * @param keyType The key type (char, special, modifier...)
         * @param leftPercent The left align percentage on the keyboard layout
         * @param topPercent The top align percentage on the keyboard layout
         * @param widthPercent The width of the key in percentage on the keyboard layout
         * @param heightPercent The height of the key in percentage on the keyboard layout
         * @param enableHold True to enable key hold event, false otherwise
         */
        // CHECKSTYLE:IGNORE ParameterNumber: Internal class constructor
        public Key(RamKeyboard keyboard, String[] keys, String[] tags, KeyType keyType,
                float leftPercent, float topPercent, float widthPercent, float heightPercent, boolean enableHold) {
            
            super(keyboard.getRenderer(), 0, 0, 0, 0);
            
            final float percent = 100f;
            
            this.keyboard = keyboard;
            this.keys = keys;
            this.tags = tags;
            this.keyType = keyType;
            this.leftPercent = leftPercent / percent;
            this.topPercent = topPercent / percent;
            this.widthPercent = widthPercent / percent;
            this.heightPercent = heightPercent / percent;
            this.enableHold = enableHold;
            this.layout = 0;
        }
        
        /**
         * Initializes the key on the keyboard.
         */
        public void initialize() {
            
            final RamKeyboardStyle style = keyboard.getKeyboardStyle();
            
            // Label
            label = new MTTextArea(keyboard.getRenderer(), getKeyFont());
            label.setPickable(false);
            label.setNoFill(true);
            label.setNoStroke(true);
            label.setFontColor(getKeyFontColor());
            
            addChild(label);
            keysContainer.addChild(this);
            
            // Key
            setNoStroke(true);
            setNoFill(false);
            setFillColor(getKeyBackgroundColor());
            
            final float keyMargin = style.getKeyMarginThickness();
            final float layoutWidth = keysContainer.getWidthXY(TransformSpace.LOCAL);
            final float layoutHeight = keysContainer.getHeightXY(TransformSpace.LOCAL);
            
            final float width = widthPercent * layoutWidth - keyMargin;
            final float height = heightPercent * layoutHeight - keyMargin;
            final float x = leftPercent * layoutWidth + width / 2 + keyMargin / 2;
            final float y = topPercent * layoutHeight + height / 2 + keyMargin / 2;
            
            setSizeLocal(width, height);
            setPositionRelativeToParent(new Vector3D(x, y));
            
            // Events
            unregisterAllInputProcessors();
            
            TapProcessor tp = new TapProcessor(RamApp.getApplication());
            if (keyType.equals(KeyType.MODIFIER)) {
                tp.setEnableDoubleTap(true);
                tp.setDoubleTapTime(DOUBLE_TAP_DELAY);
            }
            registerInputProcessor(tp);
            addGestureListener(TapProcessor.class, this);
            
            if (enableHold) {
                registerInputProcessor(new TapAndHoldProcessor(RamApp.getApplication(), HOLD_DELAY));
                addGestureListener(TapAndHoldProcessor.class, this);
            }
            
            changeLayout(layout);
        }
        
        @Override
        public boolean processGestureEvent(MTGestureEvent ge) {
            if (ge instanceof TapEvent) {
                TapEvent tapEvent = (TapEvent) ge;
                switch (tapEvent.getTapID()) {
                    case TapEvent.TAP_DOWN:
                        setDownStyle();
                        keyboard.onKeyboardButtonDown(getCurrentKey(), keyType);
                        break;
                    case TapEvent.TAP_UP:
                        setUpStyle();
                        keyboard.onKeyboardButtonUp(getCurrentKey(), keyType);
                        break;
                    case TapEvent.TAPPED:
                        setUpStyle();
                        keyboard.onKeyboardButtonUp(getCurrentKey(), keyType);
                        keyboard.onKeyboardButtonClicked(getCurrentKey(), keyType);
                        break;
                    case TapEvent.DOUBLE_TAPPED:
                        setUpStyle();
                        keyboard.onKeyboardButtonUp(getCurrentKey(), keyType);
                        keyboard.onKeyboardButtonDoubleClicked(getCurrentKey(), keyType);
                        break;
                    default:
                        break;
                }
            } else if (ge instanceof TapAndHoldEvent) {
                TapAndHoldEvent tapAndHoldEvent = (TapAndHoldEvent) ge;
                if (tapAndHoldEvent.isHoldComplete()) {
                    keyboard.onKeyboardButtonHeld(getCurrentKey(), keyType);
                }
            }
            return false;
        }
        
        /**
         * Change the layout of the key.
         * 
         * @param layoutLevel The new layout level
         */
        public void changeLayout(int layoutLevel) {
            this.layout = layoutLevel;
            changeTag(tags != null && layout < tags.length ? tags[layout] : keys[layout]);
        }
        
        /**
         * Force to change the tag of the key.
         * 
         * @param text The new tag
         */
        public void changeTag(String text) {
            label.setText(text);
            label.setPositionRelativeToParent(getCenterPointLocal());
        }
        
        /**
         * Get the current key name.
         * 
         * @return The current key name
         */
        public String getCurrentKey() {
            return layout < keys.length ? keys[layout] : "";
        }
        
        /**
         * Get the list of the keys.
         * 
         * @return The list of the keys
         */
        public String[] getKeys() {
            return keys;
        }
        
        /**
         * Get the type of the key.
         * 
         * @return The type of the key
         */
        public KeyType getKeyType() {
            return keyType;
        }
        
        /**
         * Force the key to be in the down style.
         */
        public void setDownStyle() {
            setFillColor(getKeyDownBackgroundColor());
            label.setFontColor(getKeyDownFontColor());
        }
        
        /**
         * Force the key to be in the up style.
         */
        public void setUpStyle() {
            setFillColor(getKeyBackgroundColor());
            label.setFontColor(getKeyFontColor());
        }
        
        /**
         * Get the key background color depending on the key.
         * 
         * @return The key background color
         */
        private MTColor getKeyBackgroundColor() {
            
            MTColor color = keyboard.getKeyboardStyle().getKeyBackgroundColor();
            
            if (keyType.equals(KeyType.SPECIAL)) {
                if (isKey(getCurrentKey(), "validate")) {
                    color = keyboard.getKeyboardStyle().getValidateKeyBackgroundColor();
                } else if (isKey(getCurrentKey(), "cancel")) {
                    color = keyboard.getKeyboardStyle().getCancelKeyBackgroundColor();
                }
            } else if (keyType.equals(KeyType.MODIFIER)) {
                color = keyboard.getKeyboardStyle().getModifierKeyBackgroundColor();
            }
            
            return color;
        }
        
        /**
         * Get the key down background color depending on the key.
         * 
         * @return The key down background color
         */
        private MTColor getKeyDownBackgroundColor() {
            
            MTColor color = keyboard.getKeyboardStyle().getKeyDownBackgroundColor();
            
            if (keyType.equals(KeyType.SPECIAL)) {
                if (isKey(getCurrentKey(), "validate")) {
                    color = keyboard.getKeyboardStyle().getValidateKeyDownBackgroundColor();
                } else if (isKey(getCurrentKey(), "cancel")) {
                    color = keyboard.getKeyboardStyle().getCancelKeyDownBackgroundColor();
                }
            } else if (keyType.equals(KeyType.MODIFIER)) {
                color = keyboard.getKeyboardStyle().getModifierKeyDownBackgroundColor();
            }
            
            return color;
        }
        
        /**
         * Get the key font color depending on the key.
         * 
         * @return The key font color
         */
        private MTColor getKeyFontColor() {
            
            MTColor color = keyboard.getKeyboardStyle().getKeyFontColor();
            
            if (keyType.equals(KeyType.SPECIAL)) {
                if (isKey(getCurrentKey(), "validate")) {
                    color = keyboard.getKeyboardStyle().getValidateKeyFontColor();
                } else if (isKey(getCurrentKey(), "cancel")) {
                    color = keyboard.getKeyboardStyle().getCancelKeyFontColor();
                }
            } else if (keyType.equals(KeyType.MODIFIER)) {
                color = keyboard.getKeyboardStyle().getModifierKeyFontColor();
            }
            
            return color;
        }
        
        /**
         * Get the key down font color depending on the key.
         * 
         * @return The key down font color
         */
        private MTColor getKeyDownFontColor() {
            
            MTColor color = keyboard.getKeyboardStyle().getKeyDownFontColor();
            
            if (keyType.equals(KeyType.SPECIAL)) {
                if (isKey(getCurrentKey(), "validate")) {
                    color = keyboard.getKeyboardStyle().getValidateKeyDownFontColor();
                } else if (isKey(getCurrentKey(), "cancel")) {
                    color = keyboard.getKeyboardStyle().getCancelKeyDownFontColor();
                }
            } else if (keyType.equals(KeyType.MODIFIER)) {
                color = keyboard.getKeyboardStyle().getModifierKeyDownFontColor();
            }
            
            return color;
        }
        
        /**
         * Get the shared font for keys and make a light copy to allow to change the font color.
         * 
         * @return The new key font
         */
        private IFont getKeyFont() {
            return FontManager.getInstance().createFont(
                    RamApp.getApplication(),
                    FONT_NAME,
                    FONT_SIZE);
        }
    }
}
