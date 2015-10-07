package ca.mcgill.sel.ram.ui.views.message.old;

import ca.mcgill.sel.ram.MessageView;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.ui.views.TextView;

/**
 * The view visualizing a message view.
 * It just consists of the header and its specification is ommitted.
 * 
 * @author mschoettle
 */
public class MessageViewView extends AbstractMessageViewView<MessageView> {
    
    private TextView nameField;
    
    /**
     * Creates a new view for the given message view.
     * 
     * @param messageView the {@link MessageView} to visualize
     */
    public MessageViewView(MessageView messageView) {
        super(messageView);
    }
    
    @Override
    protected void build() {
        super.build();
        
        nameField = new TextView(messageView, RamPackage.Literals.MESSAGE_VIEW__SPECIFIES, true);
        nameField.setFont(DEFAULT_FONT);
        addNameField(nameField);
    }
    
}
