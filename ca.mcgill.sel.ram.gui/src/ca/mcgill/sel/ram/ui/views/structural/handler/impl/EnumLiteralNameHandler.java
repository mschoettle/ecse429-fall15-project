package ca.mcgill.sel.ram.ui.views.structural.handler.impl;

import ca.mcgill.sel.ram.ui.utils.MetamodelRegex;
import ca.mcgill.sel.ram.ui.views.handler.impl.ValidatingTextViewHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.IEnumLiteralNameHandler;

/**
 * The default handler for the name of {@link ca.mcgill.sel.ram.ui.views.structural.EnumLiteralView}.
 * 
 * @author Franz
 */
public class EnumLiteralNameHandler extends ValidatingTextViewHandler implements IEnumLiteralNameHandler {

    /**
     * Constructor.
     */
    public EnumLiteralNameHandler() {
        super(MetamodelRegex.REGEX_ENUM_LITERAL);
    }

}
