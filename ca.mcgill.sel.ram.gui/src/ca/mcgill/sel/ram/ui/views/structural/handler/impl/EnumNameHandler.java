package ca.mcgill.sel.ram.ui.views.structural.handler.impl;

import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;

import ca.mcgill.sel.commons.StringUtil;
import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.ui.utils.Strings;
import ca.mcgill.sel.ram.ui.views.TextView;
import ca.mcgill.sel.ram.ui.views.handler.impl.ValidatingTextViewHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.IEnumNameHandler;

/**
 * The default handler for the name of {@link ca.mcgill.sel.ram.ui.views.structural.EnumView}.
 * 
 * @author Franz
 */
public class EnumNameHandler extends ValidatingTextViewHandler implements IEnumNameHandler {

    private boolean isImplementationEnum;
    private boolean nameToggle;

    /**
     * Constructor.
     * 
     * @param pIsImplementationEnum is implementation enum.
     */
    public EnumNameHandler(boolean pIsImplementationEnum) {
        super(".*");
        isImplementationEnum = pIsImplementationEnum;
        nameToggle = false;
    }

    @Override
    public boolean shouldDismissKeyboard(TextView textView) {
        // If implementation enum do not allow for text view modifications
        if (!isImplementationEnum) {
            String text = textView.getText();

            if (!text.isEmpty() && !text.matches(Strings.DEFAULT_ENUM_NAME + "\\d+")) {
                textView.setText(StringUtil.toUpperCaseFirst(text));
            }
            return super.shouldDismissKeyboard(textView);
        } else {
            return true;
        }

    }

    @Override
    public boolean processTapEvent(TapEvent tapEvent) {
        // if implementation enum allow for toggle between instance class name and name.
        if (isImplementationEnum) {
            if (tapEvent.isDoubleTap()) {
                TextView target = (TextView) tapEvent.getTarget();
                nameToggle = !nameToggle;
                if (nameToggle) {
                    target.setFeature(RamPackage.Literals.IMPLEMENTATION_CLASS__INSTANCE_CLASS_NAME, false);
                } else {
                    target.setFeature(CorePackage.Literals.CORE_NAMED_ELEMENT__NAME, false);
                }
            }
        } else {
            super.processTapEvent(tapEvent);
        }
        return false;
    }
}
