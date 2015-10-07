package ca.mcgill.sel.ram.ui.components;

import ca.mcgill.sel.ram.ui.events.listeners.ActionListener;
import ca.mcgill.sel.ram.ui.utils.Strings;

/**
 * A popup showing a question with a possible choice to select. This is meant to
 * be given to a user to confirm a certain action before invoking it.
 * A listener can be registered to receive information about which option was selected.
 * Make sure that your implementation waits for this information before doing anything else.
 * 
 * @author mschoettle
 */
public class ConfirmPopup extends RamPopup implements ActionListener {
    
    /**
     * The listener interface for receiving selected options.
     * The class interested in what option of {@link ConfirmPopup} was selected
     * implements this interface. The {@link ConfirmPopup} will notify the listener
     * added with {@link ConfirmPopup#setListener(SelectionListener)} about the
     * selection made. The option corresponds to the public constants of {@link ConfirmPopup}.
     */
    public interface SelectionListener {
        /**
         * Invoked when a selection was made.
         * 
         * @param selectedOption the option selected
         */
        void optionSelected(int selectedOption);
    }
    
    /**
     * Value when Yes was chosen.
     */
    public static final int YES_OPTION = 0;
    
    /**
     * Value when No was chosen.
     */
    public static final int NO_OPTION = 1;
    
    /**
     * Value when Cancel was chosen.
     */
    public static final int CANCEL_OPTION = 2;
    
    /**
     * The possible types this confirm popup supports.
     */
    public enum OptionType {
        /**
         * Presents a choice of Yes or No.
         */
        YES_NO,
        /**
         * Presents a choice of Yes, No or Cancel.
         */
        YES_NO_CANCEL,
        /**
         * Presents a choice of Yes or Cancel.
         */
        YES_CANCEL
    }
    
    private SelectionListener listener;
    
    /**
     * Creates a new {@link ConfirmPopup}. The given message is presented as the question.
     * The given option type defines which options are made available to select from.
     * 
     * @param message the message to present as a question
     * @param optionType the {@link OptionType} which defines what options are available to select
     */
    public ConfirmPopup(String message, OptionType optionType) {
        super(message, false);
        
        // Remove default cancel button.
        buttonRow.removeAllChildren();
        
        // TODO: maybe buttons should delegate to handler
        if (optionType != OptionType.YES_CANCEL) {
            RamButton noButton = new RamButton(Strings.POPUP_NO);
            noButton.addActionListener(this);
            noButton.setActionCommand(String.valueOf(NO_OPTION));
            addButton(noButton);
        }
        
        if (optionType == OptionType.YES_NO_CANCEL || optionType == OptionType.YES_CANCEL) {
            RamButton cancelButton = new RamButton(Strings.POPUP_CANCEL);
            cancelButton.addActionListener(this);
            cancelButton.setActionCommand(String.valueOf(CANCEL_OPTION));
            addButton(cancelButton);
        }
        
        RamButton yesButton = new RamButton(Strings.POPUP_YES);
        yesButton.addActionListener(this);
        yesButton.setActionCommand(String.valueOf(YES_OPTION));
        addButton(yesButton);
    }
    
    /**
     * Registers the given listener. The listener is informed once an option was
     * selected from the given choices.
     * 
     * @param listener the {@link SelectionListener} to inform
     */
    public void setListener(SelectionListener listener) {
        this.listener = listener;
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        destroy();
        int selectedOption = Integer.valueOf(event.getActionCommand());
        
        if (listener != null) {
            listener.optionSelected(selectedOption);
        }
    }
    
}
