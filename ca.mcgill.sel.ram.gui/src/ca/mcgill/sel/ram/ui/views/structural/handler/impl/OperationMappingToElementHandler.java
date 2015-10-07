package ca.mcgill.sel.ram.ui.views.structural.handler.impl;

import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;

import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.ram.Operation;
import ca.mcgill.sel.ram.OperationMapping;
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
 * This handler handles the "To Operation" of an operation mapping which is represented by a TextView. It allows users
 * to enter a text and create an operation which will have the same signutare as "From Operation" and maps them
 * together.
 * 
 * @author mschoettle
 */
public class OperationMappingToElementHandler extends TextViewHandler {
    
    private static String validTextRegex;
    
    /**
     * Default constructor for the handler.
     * @param operationMappingView
     */
    public OperationMappingToElementHandler() {
        validTextRegex = MetamodelRegex.REGEX_OPERATION_NAME;
    }
    
    @Override
    public boolean processTapEvent(TapEvent tapEvent) {
        if (tapEvent.isDoubleTap()) {
            final TextView target = (TextView) tapEvent.getTarget();
            final OperationMapping mapping = (OperationMapping) target.getData();
            
            MappingSelectorView selector = new MappingSelectorView(mapping, CorePackage.Literals.CORE_MAPPING__TO);
            selector.setHandler(new IMappingSelectorHandler() {
                
                @Override
                public boolean validTextEntered(String text) {
                    if (!text.matches(validTextRegex)) {
                        return false;
                    }
                    
                    // In the (unlikely) event that the mapping was deleted
                    // while the selector was opened, don't set the value.
                    if (mapping.eContainer() != null) {
                        Operation newOperation = ControllerFactory.INSTANCE.getClassController().createOperationCopy(
                                mapping, text);
                        
                        setValue(target.getData(), target.getFeature(), newOperation);
                    }
                    
                    return true;
                }
            });
            
            RamApp.getActiveAspectScene().addComponent(selector, tapEvent.getLocationOnScreen());
            
            selector.registerListener(new AbstractDefaultRamSelectorListener<Object>() {
                @Override
                public void elementSelected(RamSelectorComponent<Object> selector, Object element) {
                    // In the (unlikely) event that the mapping was deleted
                    // while the selector was opened, don't set the value.
                    if (target.getData().eContainer() != null) {
                        setValue(target.getData(), target.getFeature(), element);
                    }
                }
            });
        }
        
        return true;
    }
    
}
