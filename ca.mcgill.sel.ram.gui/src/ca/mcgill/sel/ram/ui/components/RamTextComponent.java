package ca.mcgill.sel.ram.ui.components;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.media.opengl.GL2;

import org.mt4j.components.MTComponent;
import org.mt4j.components.visibleComponents.shapes.AbstractShape;
import org.mt4j.util.font.IFont;
import org.mt4j.util.font.IFontCharacter;
import org.mt4j.util.math.Tools3D;

import processing.core.PApplet;
import processing.core.PGraphics;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamKeyboard.KeyboardPosition;
import ca.mcgill.sel.ram.ui.components.listeners.RamKeyboardListener;
import ca.mcgill.sel.ram.ui.components.listeners.RamTextListener;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.utils.Fonts;

/**
 * RamTextField is a rectangular component used to display text to the user. It provides functionality to display the
 * keyboard when tapped and to disable the enter key (to keep the text as one line).
 *
 * @author vbonnet
 */
public class RamTextComponent extends RamRectangleComponent implements RamKeyboardListener {

    /**
     * The default buffer size.
     */
    public static final int DEFAULT_BUFFER_SIZE = 6;

    private static final String NEWLINE = "\n";

    /**
     * The possible alignments to display text inside a text field.
     *
     * @author vbonnet
     */
    public enum Alignment {
        /**
         * Aligns text to the left of the text field.
         */
        LEFT_ALIGN,
        /**
         * Aligns text to the right of the text field.
         */
        RIGHT_ALIGN,
        /**
         * Fixes text to the center of the text field.
         */
        CENTER_ALIGN
    }

    private static final long CARET_TIME = 500;

    private boolean newlineDisabled;

    private boolean keyboardEnabled;
    private boolean caretEnabled;
    private int caretPosition;
    private boolean showCaret;
    private List<IFontCharacter> characterList;
    private List<IFontCharacter> placeholderCharacterList;
    private IFont font;
    private IFont placeholderFont;
    private Alignment textAlignment;
    private boolean expandLeft;
    private long caretTime;
    private IFontCharacter caret;
    private int numberOfLines;
    private PApplet applet;
    private RamKeyboard keyboard;

    private int placeholderLength;

    private Set<RamTextListener> listeners;

    /**
     * Creates an empty text field that will display a keyboard when tapped.
     */
    public RamTextComponent() {
        this("", true);
    }

    /**
     * Creates an empty text component.
     *
     * @param enabled Whether the text field should display a keyboard when tapped.
     */
    public RamTextComponent(boolean enabled) {
        this("", enabled);
    }

    /**
     * Creates a new text component with no text using the given font. This text field is left aligned and has the
     * keyboard disabled by default.
     *
     * @param font the font to use
     */
    public RamTextComponent(IFont font) {
        this(font, "", false);
    }

    /**
     * Creates a new text component with the given text and font. This text field is left aligned and has the keyboard
     * disabled by default.
     *
     * @param font The font to use in displaying the text.
     * @param text The text to display.
     */
    public RamTextComponent(IFont font, String text) {
        this(font, text, false);
    }

    /**
     * Creates a new text component to the given specifications.
     *
     * @param font The font of characters in the text field.
     * @param text The text to display.
     * @param enabled Whether the text field should display a keyboard when tapped.
     */
    private RamTextComponent(IFont font, String text, boolean enabled) {
        this(font, text, enabled, Alignment.LEFT_ALIGN);
    }

    /**
     * Creates a new text component to the given specifications.
     *
     * @param font The font of characters in the text field.
     * @param text The text to display.
     * @param enabled Whether the text field should display a keyboard when tapped.
     * @param align the alignment of the text
     */
    private RamTextComponent(IFont font, String text, boolean enabled, Alignment align) {
        super(0, 0, 0, 0);

        this.font = font;

        textAlignment = align;
        characterList = new ArrayList<IFontCharacter>();
        placeholderCharacterList = new ArrayList<IFontCharacter>();
        placeholderFont = Fonts.DEFAULT_PLACEHOLDER_FONT;
        caretEnabled = false;
        caretPosition = 0;
        listeners = new HashSet<RamTextListener>();
        setBuffers(DEFAULT_BUFFER_SIZE);
        
        setStrokeColor(Colors.DEFAULT_ELEMENT_STROKE_COLOR);
        setFillColor(Colors.DEFAULT_ELEMENT_FILL_COLOR);
        setNoStroke(true);
        setNoFill(true);
        setText(text);

        newlineDisabled = true;
        keyboardEnabled = enabled;
        expandLeft = false;
        caret = font.getFontCharacterByName("|");
        // TODO setBufferSize(Cardinal.WEST, 30);

        applet = RamApp.getApplication();
    }

    /**
     * Creates a text component with the given text. By default it will NOT display a keyboard when tapped.
     *
     * @param text The text to display.
     */
    public RamTextComponent(String text) {
        this(text, false);
    }

    /**
     * Creates a text component with the given text.
     *
     * @param text The text to display.
     * @param enabled Whether the text field should display a keyboard when tapped.
     */
    public RamTextComponent(String text, boolean enabled) {
        this(Fonts.DEFAULT_FONT, text, enabled);
    }

    /**
     * Adds a character at the specified index.
     *
     * @param character The character to insert.
     * @param index The index where the character should be inserted.
     * @precondition index is smaller than the the current text length.
     */
    public void addCharacter(IFontCharacter character, int index) {
        if (character == null) {
            return;
        }
        characterList.add(index, character);
        notifyListeners(characterList);
        characterAdded(character, index);
    }

    private void appendCharacter(IFontCharacter character) {
        characterList.add(caretPosition++, character);
        notifyListeners(characterList);
    }

    @Override
    public synchronized void appendCharByUnicode(String unicode) {
        // Get the character from the font
        if (NEWLINE.equals(unicode) && newlineDisabled) {
            if (keyboard != null) {
                keyboard.dimissKeyboard(true);
            }
            return;
        } else {
            IFontCharacter character = getFont().getFontCharacterByUnicode(unicode);
            appendCharacter(character);
            resize();
        }
    }

    @Override
    public synchronized void appendText(String text) {
        if (text != null) {
            for (char c : text.trim().toCharArray()) {
                characterList.add(getFont().getFontCharacterByUnicode(Character.toString(c)));
            }
            caretPosition += characterList.size();
            notifyListeners(characterList);
            resize();
        }
    }

    private float calculateLineOffset(List<IFontCharacter> characterList, int index) {
        float xOffset;
        float totalLength = 0;
        // placeholder
        IFontCharacter character = getFont().getFontCharacterByName("a");
        switch (getTextAlignment()) {
            case CENTER_ALIGN:
                while (index < characterList.size() && !NEWLINE.equals(character.getUnicode())) {
                    character = characterList.get(index++);
                    float delta =
                            character.getHorizontalDist()
                            + (index >= characterList.size() ? 0 : character.getKerning(characterList
                                    .get(index).getUnicode()));
                    if (totalLength + delta > getMaximumChildWidth()) {
                        break;
                    }
                    totalLength += delta;
                }
                xOffset = getBufferSize(Cardinal.WEST) + (getChildWidth() - totalLength) / 2;
                break;
            case RIGHT_ALIGN:
                while (index < characterList.size() && !NEWLINE.equals(character.getUnicode())) {
                    character = characterList.get(index++);
                    float delta =
                            character.getHorizontalDist()
                            + (index >= characterList.size() ? 0 : character.getKerning(characterList
                                    .get(index).getUnicode()));
                    if (totalLength + delta > getMaximumChildWidth()) {
                        break;
                    }
                    totalLength += delta;
                }
                xOffset = getBufferSize(Cardinal.WEST) + getChildWidth() - totalLength;
                break;
            case LEFT_ALIGN:
            default:
                xOffset = getBufferSize(Cardinal.WEST);
        }

        return xOffset;
    }

    protected void characterAdded(IFontCharacter character, int index) {
        resize();
    }

    protected void characterRemoved(IFontCharacter character, int index) {
        resize();
    }

    @Override
    public synchronized void clear() {
        caretPosition = 0;
        characterList.clear();
        notifyListeners(characterList);
        resize();
    }

    protected void postKeyboardClosed() {
        keyboard = null;
        enableCaret(false);
        caretPosition = getText().length();
    }

    private float drawCarriageReturn(GL2 gl, List<IFontCharacter> characterList, IFont font, float width, int index) {
        float xOffset = calculateLineOffset(characterList, index);
        gl.glTranslatef(xOffset - width, font.getFontAbsoluteHeight(), 0);
        return xOffset;
    }

    private float drawCarriageReturn(PGraphics g, List<IFontCharacter> characterList, IFont iFont, float width,
            int index) {
        float xOffset = calculateLineOffset(characterList, index);
        g.translate(xOffset - width, font.getFontAbsoluteHeight(), 0);
        return xOffset;
    }

    private void drawCharactersGL(GL2 gl, List<IFontCharacter> characterList, Alignment align, IFont iFont) {
        // draw the carriage return on the first line
        float currentX = drawCarriageReturn(gl, characterList, iFont, 0, 0);
        iFont.beginBatchRenderGL(gl, iFont);

        for (int characterIndex = 0; characterIndex < characterList.size(); characterIndex++) {
            IFontCharacter character = characterList.get(characterIndex);
            if (NEWLINE.equals(character.getUnicode())) {
                currentX = drawCarriageReturn(gl, characterList, iFont, currentX, characterIndex + 1);
            } else {
                // draw the character in front of the character if the caret position dictates it
                if (characterIndex == caretPosition && showCaret) {
                    gl.glTranslatef(-3, 0, 0);
                    caret.drawComponent(gl);
                    gl.glTranslatef(3, 0, 0);
                }
                // get the character size (with kerning)
                IFontCharacter nextCharacter =
                        characterIndex == characterList.size() - 1 ? null : characterList.get(characterIndex + 1);
                int delta =
                        character.getHorizontalDist()
                        + (nextCharacter == null ? 0 : character.getKerning(nextCharacter.getUnicode()));
                if (currentX + delta > getMaximumChildWidth()) {
                    currentX = drawCarriageReturn(gl, characterList, iFont, currentX, characterIndex + 1);
                }
                // move the line forward (to check for wrap-around)
                currentX += delta;
                // draw the character
                character.drawComponent(gl);
                // Step to the right by the amount of the last characters x advancement
                gl.glTranslatef(delta, 0, 0);
            }
        }

        // if the caret is at the end of the string, draw it there
        if (characterList.size() == caretPosition && showCaret) {
            gl.glTranslatef(-3, 0, 0);
            caret.drawComponent(gl);
            gl.glTranslatef(3, 0, 0);
        }

        font.endBatchRenderGL(gl, font);
    }

    private void drawCharactersP3D(PGraphics g, List<IFontCharacter> characterList, Alignment textAlignment2,
            IFont iFont) {
        // we don't actually need to draw a carriage return, just offset ourselves
        float currentX = drawCarriageReturn(g, characterList, iFont, 0, 0);

        // draw the characters
        for (int characterIndex = 0; characterIndex < characterList.size(); characterIndex++) {
            IFontCharacter character = characterList.get(characterIndex);
            if (NEWLINE.equals(character.getUnicode())) {
                currentX = drawCarriageReturn(g, characterList, iFont, currentX, characterIndex + 1);
            } else {
                // draw the character in front of the character if the caret position dictates it
                if (characterIndex == caretPosition && showCaret) {
                    caret.drawComponent(g);
                }
                // get the character size (with kerning)
                IFontCharacter nextCharacter =
                        characterIndex == characterList.size() - 1 ? null : characterList.get(characterIndex + 1);
                int delta =
                        character.getHorizontalDist()
                        + (nextCharacter == null ? 0 : character.getKerning(nextCharacter.getUnicode()));
                if (currentX + delta > getMaximumChildWidth()) {
                    currentX = drawCarriageReturn(g, characterList, iFont, currentX, characterIndex + 1);
                }
                // move the line forward (to check for wrap-around)
                currentX += delta;
                // draw the character
                character.drawComponent(g);
                // Step to the right by the amount of the last characters x advancement
                applet.translate(delta, 0, 0);
            }
        }

        // if the caret is at the end of the string, draw it there
        if (characterList.size() == caretPosition && showCaret) {
            caret.drawComponent(g);
        }
    }

    @Override
    public synchronized void drawComponent(PGraphics g) {
        super.drawComponent(g);

        // get ready, push
        g.pushMatrix();

        if (isUseDirectGL()) {
            // GL rendering
            GL2 gl = Tools3D.beginGLAndGetGL(g);

            // if character list is empty but caret should be shown drawing has to take place

            if (characterList.isEmpty() && !placeholderCharacterList.isEmpty()) {
                // draw the background text if any
                gl.glTranslatef(0, (getChildHeight() - placeholderFont.getFontAbsoluteHeight()) / 1, 0);
                drawCharactersGL(gl, placeholderCharacterList, textAlignment, placeholderFont);
            } else if (!characterList.isEmpty() || caretEnabled) {
                // draw the text
                gl.glTranslatef(0, (getChildHeight() - font.getFontAbsoluteHeight() * numberOfLines) / 2 - 1, 0);
                drawCharactersGL(gl, characterList, textAlignment, getFont());
            }
            Tools3D.endGL(g);
        }

        // all done, pop
        g.popMatrix();
    }

    /**
     * @param enabled Whether the caret should be displayed by this text field.
     */
    public void enableCaret(boolean enabled) {
        caretEnabled = enabled;
        showCaret = enabled;
    }

    /**
     * @param enabled Whether the text field should display a keyboard when tapped.
     */
    public void enableKeyboard(boolean enabled) {
        keyboardEnabled = enabled;
    }

    /**
     * @return The font being used to display the text in this field.
     */
    public IFont getFont() {
        return font;
    }

    /**
     * @return The text contained in the field.
     */
    public String getText() {
        String returnString = "";
        for (IFontCharacter character : characterList) {
            String unicode = character.getUnicode();
            returnString += unicode;
        }
        return returnString;
    }

    /**
     * Returns the {@link Alignment} of this component.
     *
     * @return the alignment of this component
     */
    public Alignment getTextAlignment() {
        return textAlignment;
    }

    @Override
    protected void handleChildResized(MTComponent child) {
        resize();
    }

    /**
     * @return Whether the caret is enabled for this text field.
     */
    public boolean isCaretEnabled() {
        // Their way doesn't work, let's do it ours
        return caretEnabled;
    }

    @Override
    public void keyboardCancelled() {
        postKeyboardClosed();
    }

    @Override
    public void keyboardClosed(RamKeyboard ramKeyboard) {
        postKeyboardClosed();
    }

    @Override
    public void keyboardOpened() {
        // overwrite by subclass
    }

    @Override
    public void modifierKeyPressed(int keyCode) {
        if (isEnabled() && isCaretEnabled()) {
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    if (caretPosition != 0) {
                        caretPosition--;
                        showCaret = true;
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (caretPosition != getText().length()) {
                        caretPosition++;
                        showCaret = true;
                    }
                    break;
                default:

            }
        }
    }

    /**
     * Sets the previous text (used if the Keyboard is cancelled) and enables the caret. NOTE this must be used before
     * registering a {@link RamTextComponent} as a listener to {@link RamKeyboard}.
     */
    private void prepareForKeyboard() {
        if (!caretEnabled) {
            enableCaret(true);
        }
    }

    /**
     * Registers a listener for selection events.
     *
     * @param listener The listener.
     */
    public void registerListener(RamTextListener listener) {
        listeners.add(listener);
    }

    /**
     * Unregisters a listener for selection events.
     *
     * @param listener The listener.
     */
    public void unregisterListener(RamTextListener listener) {
        listeners.remove(listener);
    }

    @Override
    public synchronized void removeLastCharacter() {
        // ITextInputListener calls it remove last character, but really it's remove character in front of caret
        if (caretPosition != 0) {
            characterList.remove(--caretPosition);
        }
        notifyListeners(characterList);
        resize();
    }

    private synchronized void resize() {
        float totalWidth = 0;
        float currentWidth = 0;
        float maxWidth = getMaximumChildWidth();
        numberOfLines = 1;
        if (!characterList.isEmpty()) {
            // find the size of the text
            for (int i = 0; i < characterList.size() - 1; i++) {
                IFontCharacter character = characterList.get(i);
                float delta =
                        character.getHorizontalDist() + character.getKerning(characterList.get(i + 1).getUnicode());
                if (NEWLINE.equals(character.getUnicode()) || currentWidth + delta > maxWidth) {
                    totalWidth = Math.max(totalWidth, currentWidth);
                    currentWidth = delta;
                    numberOfLines++;
                } else {
                    currentWidth += delta;
                }
            }
            currentWidth += characterList.get(characterList.size() - 1).getHorizontalDist();
            totalWidth =
                    getBufferSize(Cardinal.WEST) + Math.max(totalWidth, currentWidth) + getBufferSize(Cardinal.EAST);
        } else if (!placeholderCharacterList.isEmpty()) {
            // use placeholder instead
            totalWidth = getBufferSize(Cardinal.WEST) + placeholderLength + getBufferSize(Cardinal.EAST);
        }

        // snap down around the text
        float totalHeight =
                numberOfLines * font.getFontAbsoluteHeight() + getBufferSize(Cardinal.NORTH)
                + getBufferSize(Cardinal.SOUTH);

        if (expandLeft) {
            float prevWidth = getWidth();
            setMinimumSize(totalWidth, totalHeight);
            translate(prevWidth - getWidth(), 0);
        } else if (autoMinimizes()) {
            setMinimumSize(totalWidth, totalHeight);
        } else if (autoMaximizes()) {
            // expand if it is bigger now
            if (totalWidth >= getWidth()) {
                setMinimumSize(totalWidth, totalHeight);
            }
        }

        // warn parent in case they care
        updateParent();
    }

    /**
     * Sets the character alignment for displaying text.
     *
     * @param align The alignment.
     */
    public void setAlignment(Alignment align) {
        textAlignment = align;
    }

    @Override
    public void setBufferSize(Cardinal cardinal, float size) {
        // whenever the buffer size changes the component has to be resized
        super.setBufferSize(cardinal, size);
        resize();
    }

    /**
     * @param left Whether the text component expands left, default is false.
     */
    public void setExpandsLeft(boolean left) {
        expandLeft = left;
    }

    /**
     * Sets the font for the text in this class.
     *
     * @param font The new font to set.
     */
    public void setFont(IFont font) {
        this.font = font;
        setText(getText());
        resize();
        caret = font.getFontCharacterByName("|");
    }

    @Override
    public void setMaximumSize(float width, float height) {
        super.setMaximumSize(width, height);
        resize();
    }

    /**
     * @param disabled Whether the keyboard should accept the newline character. If disabled hitting enter will dismiss
     *            the keyboard.
     */
    public void setNewlineDisabled(boolean disabled) {
        newlineDisabled = disabled;
    }

    /**
     * Sets the new font of the placeholder.
     *
     * @param font the new placeholder font
     */
    public void setPlaceholderFont(IFont font) {
        placeholderFont = font;
    }

    /**
     * Sets the placeholder text of this component to be shown when there is no text available.
     *
     * @param text the placeholder text of this component
     */
    public void setPlaceholderText(String text) {
        placeholderCharacterList.clear();
        IFont font = placeholderFont;

        char[] charArray = text.trim().toCharArray();
        int i = 0;
        IFontCharacter prev = font.getFontCharacterByUnicode(Character.toString(charArray[i++]));
        placeholderCharacterList.add(prev);
        int lenght = prev.getHorizontalDist();
        IFontCharacter curr;
        while (i < charArray.length) {
            String currentCharacter = Character.toString(charArray[i++]);
            curr = font.getFontCharacterByUnicode(currentCharacter);
            placeholderCharacterList.add(curr);
            lenght += prev.getKerning(currentCharacter) + curr.getHorizontalDist();
            prev = curr;
        }

        placeholderLength = lenght;
        // TODO this might span multiple lines; damnit all....
        resize();
    }

    @Override
    public synchronized void setText(String text) {
        characterList.clear();
        caretPosition = 0;
        appendText(text);
    }

    /**
     * Show the keyboard for this component.
     */
    public void showKeyboard() {
        showKeyboard(this);
    }

    /**
     * Show the keyboard for this component.
     *
     * @param parent the parent component for the keyboard
     */
    public void showKeyboard(AbstractShape parent) {
        showKeyboard(parent, null);
    }

    /**
     * Show the given keyboard for this component.
     *
     * @param keyboard the keyboard that should be displayed
     */
    public void showKeyboard(RamKeyboard keyboard) {
        showKeyboard(this, keyboard);
    }

    /**
     * Show the given keyboard for this component. The given parent acts as the parent of this component.
     *
     * @param parent the parent of this component
     * @param keyboard the keyboard that should be displayed
     */
    public void showKeyboard(AbstractShape parent, RamKeyboard keyboard) {
        showKeyboard(parent, keyboard, KeyboardPosition.DEFAULT);
    }

    /**
     * Show the given keyboard for this component. The given parent acts as the parent of this component. You can
     * customize the preferred opening position of the keyboard.
     *
     * @param parent the parent of this component
     * @param keyboard the keyboard that should be displayed
     * @param position the preferred position of the keyboard
     */
    public void showKeyboard(AbstractShape parent, RamKeyboard keyboard, KeyboardPosition position) {
        // ignore if keyboard is already opened
        if (keyboardEnabled && !caretEnabled) {
            // Create keyboard if no instance was provided.
            if (keyboard == null) {
                keyboard = new RamKeyboard();
            }

            this.keyboard = keyboard;
            keyboard.registerListener(this);
            keyboard.display(parent, position);
            prepareForKeyboard();
        }
    }

    /**
     * Closes the keyboard.
     */
    public void closeKeyboard() {
        if (keyboard != null) {
            keyboard.close();
        }
        postKeyboardClosed();
    }

    /**
     * Warn all listeners that the text has changed.
     *
     * @param newCharactersList The new characters list
     */
    private void notifyListeners(List<IFontCharacter> newCharactersList) {
        for (RamTextListener listener : listeners) {
            listener.textModified(this);
        }
    }

    /**
     * Returns the current keyboard that is used to modify the text of this component. May be <code>null</code> if there
     * is currently no keyboard in use.
     *
     * @return the keyboard used to modify the text, null if no keyboard currently in use
     */
    public RamKeyboard getKeyboard() {
        return keyboard;
    }

    @Override
    public void updateComponent(long timeDelta) {
        super.updateComponent(timeDelta);
        if (caretEnabled) {
            caretTime -= timeDelta;
            if (caretTime < 0) {
                caretTime = CARET_TIME;
                showCaret = !showCaret;
            }
        }
    }

    @Override
    public boolean verifyKeyboardDismissed() {
        // don't see why we want to deny
        return true;
    }

    @Override
    public void destroy() {
        closeKeyboard();
        super.destroy();
    }
}
