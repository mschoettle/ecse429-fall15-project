package ca.mcgill.sel.ram.ui.components;

import ca.mcgill.sel.ram.ui.components.RamTextComponent.Alignment;
import ca.mcgill.sel.ram.ui.events.listeners.ActionListener;
import ca.mcgill.sel.ram.ui.layouts.HorizontalLayoutRightAligned;
import ca.mcgill.sel.ram.ui.layouts.VerticalLayout;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.utils.Strings;

/**
 * A semi-transparent popup window to overlay on top of other elements when a warning is necessary.
 * 
 * @author vbonnet
 */
public class RamPopup extends RamRoundedRectangleComponent {
    
    public class Splitter {
        
        // the given string will be split into substrings no longer than maxLen
        private int maxLen;
        
        // the default constructor uses this value to initialize maxLen
        private int defaultMaxLen = 100;
        
        // default constructor: make new Splitter with default maxLen value
        public Splitter() {
            maxLen = defaultMaxLen;
        }
        
        // custom constructor: make new Splitter with specified maxLen value
        public Splitter(int maxLen) {
            this.maxLen = maxLen;
        }
        
        // accessor method: get the current maxLen value
        public int getMaxLen() {
            return maxLen;
        }
        
        // mutator method: set maxLen to specified value
        public void setMaxLen(int maxLen) {
            this.maxLen = maxLen;
        }
        
        // the real work gets done here:
        // -- split the given string into substrings no longer than maxLen
        // -- return as an array of strings
        public String[] split(String str) {
            // get the length of the original string
            int origLen = str.length();
            
            // calculate the number of substrings we'll need to make
            int splitNum = origLen / maxLen;
            if (origLen % maxLen > 0) {
                splitNum += 1;
            }
            
            // initialize the result array
            String[] splits = new String[splitNum];
            
            // for each substring...
            for (int i = 0; i < splitNum; i++) {
                
                // the substring starts here
                int startPos = i * maxLen;
                
                // the substring ends here
                int endPos = startPos + maxLen;
                
                // make sure we don't cause an IndexOutOfBoundsException
                if (endPos > origLen) {
                    endPos = origLen;
                }
                
                // make the substring
                String substr = str.substring(startPos, endPos);
                
                // stick it in the result array
                splits[i] = substr;
            }
            
            // return the result array
            return splits;
        }
    }
    
    /**
     * The popup type.
     */
    public enum PopupType {
        /** Success popup with green colors. */
        SUCCESS,
        /** Error popup with red colors. */
        ERROR,
        /** Normal default popup. */
        NORMAL
    }
    
    /**
     * The component that holds the buttons.
     */
    protected RamRectangleComponent buttonRow;
    
    /**
     * The close button if it is required, null otherwise.
     */
    protected RamButton closeButton;
    
    /**
     * Creates a new normal popup with the given title and an optional close button.
     * 
     * @param title The title for the popup.
     * @param showClose yes, if a close button should be displayed, false otherwise
     */
    public RamPopup(String title, boolean showClose) {
        this(title, showClose, PopupType.NORMAL);
    }
    
    /**
     * Creates a new popup with the given title, an optional close button and a type.
     * 
     * @param title The title for the popup.
     * @param showClose yes, if a close button should be displayed, false otherwise
     * @param popupType The popup type
     */
    public RamPopup(String title, boolean showClose, PopupType popupType) {
        super(10);
        setNoFill(false);
        
        switch (popupType) {
            case SUCCESS:
                setFillColor(Colors.POPUP_SUCCESS_FILL_COLOR);
                setStrokeColor(Colors.POPUP_SUCCESS_STROKE_COLOR);
                break;
            case ERROR:
                setFillColor(Colors.POPUP_ERROR_FILL_COLOR);
                setStrokeColor(Colors.POPUP_ERROR_STROKE_COLOR);
                break;
            default:
                setFillColor(Colors.POPUP_DEFAULT_FILL_COLOR);
                break;
        }
        
        String[] result = new Splitter().split(title);
        
        for (String element : result) {
            RamTextComponent text = new RamTextComponent(element);
            text.setAlignment(Alignment.CENTER_ALIGN);
            addChild(text);
        }
        
        buttonRow = new RamRectangleComponent();
        
        buttonRow.setLayout(new HorizontalLayoutRightAligned(10));
        addChild(buttonRow);
        // Layout has to be set before adding a button. See issue #107.
        setLayout(new VerticalLayout());
        
        // TODO: mschoettle: Make InformPopup for this case.
        if (showClose) {
            closeButton = new RamButton(Strings.POPUP_CLOSE);
            closeButton.addActionListener(new ActionListener() {
                
                @Override
                public void actionPerformed(ActionEvent event) {
                    destroy();
                }
            });
            
            addButton(closeButton);
        }
    }
    
    /**
     * Adds the given button to this popup.
     * 
     * @param button the button to add
     */
    public void addButton(RamButton button) {
        buttonRow.addChild(button);
    }
    
    /**
     * Removes this popup from its parent. The component can be reused later.
     * <b>Note:</b> Make sure that this component is destroyed when not used anymore.
     */
    public void dismiss() {
        getParent().removeChild(this);
    }
    
}
