package ca.mcgill.sel.ram.ui.views.structural.handler.impl;

import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;

import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.ui.utils.MetamodelRegex;
import ca.mcgill.sel.ram.ui.views.TextView;
import ca.mcgill.sel.ram.ui.views.handler.impl.ValidatingTextViewHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.IImplementationClassNameHandler;

/**
 * Handler for the name field of an ImplementationClassView. When double tapped, it toggles between class name and
 * instance class name.
 * 
 * @author Franz
 * 
 */
public class ImplementationClassNameHandler extends ValidatingTextViewHandler implements
        IImplementationClassNameHandler {
    
    private boolean nameToggle;
    
    /**
     * Creates a new instance of {@link ImplementationClassNameHandler}.
     */
    public ImplementationClassNameHandler() {
        super(MetamodelRegex.REGEX_CLASS_NAME);
        nameToggle = false;
    }
    
    @Override
    public boolean processTapEvent(TapEvent tapEvent) {
        if (tapEvent.isDoubleTap()) {
            TextView target = (TextView) tapEvent.getTarget();
            nameToggle = !nameToggle;
            if (nameToggle) {
                target.setFeature(RamPackage.Literals.IMPLEMENTATION_CLASS__INSTANCE_CLASS_NAME, false);
            } else {
                target.setFeature(CorePackage.Literals.CORE_NAMED_ELEMENT__NAME, false);
            }
        }
        
        return true;
    }
    
    @Override
    public boolean shouldDismissKeyboard(TextView textView) {
        return true;
    }
    
}
