package ca.mcgill.sel.ram.ui.views.structural.handler.impl;

import ca.mcgill.sel.ram.ui.utils.MetamodelRegex;
import ca.mcgill.sel.ram.ui.views.handler.impl.ValidatingTextViewHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.IAttributeNameHandler;

/**
 * The default handler for a {@link ca.mcgill.sel.ram.ui.views.TextView} representing the name of 
 * an {@link ca.mcgill.sel.ram.Attribute}. The name gets validated in order to only allow valid names.
 * 
 * @author mschoettle
 */
public class AttributeNameHandler extends ValidatingTextViewHandler implements IAttributeNameHandler {

    /**
     * Creates a new {@link AttributeNameHandler}.
     */
    public AttributeNameHandler() {
        super(MetamodelRegex.REGEX_TYPE_NAME);
    }

}
