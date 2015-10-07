package ca.mcgill.sel.ram.ui.views.message.old;

import ca.mcgill.sel.ram.MessageViewReference;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.ui.views.TextView;

public class MessageViewReferenceView extends AbstractMessageViewView<MessageViewReference> {
    
    private TextView nameField;
    
    public MessageViewReferenceView(MessageViewReference messageView) {
        super(messageView);
    }
    
    @Override
    protected void build() {
        // build general stuff
        super.build();
        
        nameField = new TextView(messageView, RamPackage.Literals.MESSAGE_VIEW_REFERENCE__REFERENCES, true);
        nameField.setFont(DEFAULT_FONT);
        addNameField(nameField);
    }
    
}
