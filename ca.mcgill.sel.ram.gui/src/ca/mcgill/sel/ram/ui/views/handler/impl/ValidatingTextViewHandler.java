package ca.mcgill.sel.ram.ui.views.handler.impl;

import ca.mcgill.sel.ram.ui.views.TextView;

/**
 * Handler that extends the default {@link TextViewHandler} by validating the entered text based on given regular
 * expression.
 * 
 * @author mschoettle
 */
public class ValidatingTextViewHandler extends TextViewHandler {
    
    private String validNameRegex;
    
    /**
     * Creates a new handler instance with the given regular expression.
     * 
     * @param validTextRegex
     *            the regular expression to be used to validate the text
     */
    public ValidatingTextViewHandler(String validTextRegex) {
        this.validNameRegex = validTextRegex;
    }
    
    @Override
    public boolean shouldDismissKeyboard(TextView textView) {
        String text = textView.getText();
        
        // Checks if the text is not empty and if it does not match the regex
        if (text.isEmpty() || !text.matches(validNameRegex)) {
            // TODO: show error message?
            return false;
        }
        
        // check for unique name and proceed with setting the value
        return super.shouldDismissKeyboard(textView);
    }
    
}
