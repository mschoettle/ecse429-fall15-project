package ca.mcgill.sel.ram.ui.views.structural.handler.impl;

import org.mt4j.input.inputProcessors.componentProcessors.tapAndHoldProcessor.TapAndHoldEvent;

import ca.mcgill.sel.commons.StringUtil;
import ca.mcgill.sel.ram.ui.utils.MetamodelRegex;
import ca.mcgill.sel.ram.ui.views.TextView;
import ca.mcgill.sel.ram.ui.views.handler.impl.ValidatingTextViewHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.IClassNameHandler;

/**
 * The default handler for a {@link TextView} representing the name of a {@link ca.mcgill.sel.ram.Class}. 
 * The name gets validated in order to only allow valid names.
 *
 * @author mschoettle
 * @author oalam
 */
public class ClassNameHandler extends ValidatingTextViewHandler implements IClassNameHandler {

    /**
     * Creates a new instance of {@link ClassNameHandler}.
     */
    public ClassNameHandler() {
        super(MetamodelRegex.REGEX_CLASS_NAME);
    }

    @Override
    public boolean shouldDismissKeyboard(TextView textView) {
        String text = textView.getText();

        if (!text.isEmpty()) {
            textView.setText(StringUtil.toUpperCaseFirst(text));
        }

        return super.shouldDismissKeyboard(textView);
    }

    @Override
    public boolean processTapAndHoldEvent(TapAndHoldEvent tapAndHoldEvent) {
        return false;
    }

}
