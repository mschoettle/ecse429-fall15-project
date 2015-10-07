package ca.mcgill.sel.ram.ui.views.structural.handler.impl;

import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;

import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.ram.Attribute;
import ca.mcgill.sel.ram.AttributeMapping;
import ca.mcgill.sel.ram.controller.ControllerFactory;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamSelectorComponent;
import ca.mcgill.sel.ram.ui.components.listeners.AbstractDefaultRamSelectorListener;
import ca.mcgill.sel.ram.ui.utils.MetamodelRegex;
import ca.mcgill.sel.ram.ui.views.TextView;
import ca.mcgill.sel.ram.ui.views.handler.impl.TextViewHandler;
import ca.mcgill.sel.ram.ui.views.structural.MappingSelectorView;
import ca.mcgill.sel.ram.ui.views.structural.MappingSelectorView.IMappingSelectorHandler;

/**
 * This handler handles the "To Attribute" of an attribute mapping which is represented by a TextView. It allows users
 * to enter a text and create an attribute which will have the same properties as "From Attribute" and maps them
 * together.
 * 
 * @author eyildirim
 */
public class AttributeMappingToElementHandler extends TextViewHandler {
    private static String validTextRegex;
    
    /**
     * This handler handles the "To Attribute" of an attribute mapping which is represented by a TextView. It allows
     * users to enter a text as an attribute name and create
     * an operation which will have the same signature as "From Attribtue" and maps them together.
     */
    public AttributeMappingToElementHandler() {
        validTextRegex = MetamodelRegex.REGEX_OPERATION_NAME;
    }
    
    @Override
    public boolean processTapEvent(TapEvent tapEvent) {
        if (tapEvent.isDoubleTap()) {
            final TextView target = (TextView) tapEvent.getTarget();
            
            final AttributeMapping mapping = (AttributeMapping) target.getData();
            MappingSelectorView selector = new MappingSelectorView(mapping, CorePackage.Literals.CORE_MAPPING__TO);
            
            selector.setHandler(new IMappingSelectorHandler() {
                
                @Override
                public boolean validTextEntered(String text) {
                    if (!text.matches(validTextRegex)) {
                        return false;
                    }
                    Attribute newAttribute = ControllerFactory.INSTANCE.getClassController().createAttributeCopy(
                            mapping, text);
                    
                    setValue(target.getData(), target.getFeature(), newAttribute);
                    return true;
                }
            });
            
            RamApp.getActiveAspectScene().addComponent(selector, tapEvent.getLocationOnScreen());
            
            selector.registerListener(new AbstractDefaultRamSelectorListener<Object>() {
                @Override
                public void elementSelected(RamSelectorComponent<Object> selector, Object element) {
                    setValue(target.getData(), target.getFeature(), element);
                }
                
            });
        }
        
        return true;
    }
    
}
