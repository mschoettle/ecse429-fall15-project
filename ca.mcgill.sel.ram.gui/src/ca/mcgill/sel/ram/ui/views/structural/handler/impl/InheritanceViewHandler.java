package ca.mcgill.sel.ram.ui.views.structural.handler.impl;

import org.mt4j.input.inputProcessors.componentProcessors.tapAndHoldProcessor.TapAndHoldEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;

import ca.mcgill.sel.ram.Class;
import ca.mcgill.sel.ram.controller.ClassController;
import ca.mcgill.sel.ram.controller.ControllerFactory;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamImageComponent;
import ca.mcgill.sel.ram.ui.components.RamSelectorComponent;
import ca.mcgill.sel.ram.ui.components.listeners.AbstractDefaultRamSelectorListener;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.utils.Icons;
import ca.mcgill.sel.ram.ui.views.OptionSelectorView;
import ca.mcgill.sel.ram.ui.views.OptionSelectorView.Iconified;
import ca.mcgill.sel.ram.ui.views.RamEnd;
import ca.mcgill.sel.ram.ui.views.handler.BaseHandler;
import ca.mcgill.sel.ram.ui.views.handler.IRelationshipViewHandler;
import ca.mcgill.sel.ram.ui.views.structural.ClassView;

/**
 * Default handler for inheritance view.
 * 
 * @author eyildirim
 * @author mschoettle
 */
public class InheritanceViewHandler extends BaseHandler implements IRelationshipViewHandler {
    
    /**
     * The options for inheritance views.
     */
    private enum InheritanceOptions implements Iconified {
        DELETE(new RamImageComponent(Icons.ICON_DELETE, Colors.ICON_DELETE_COLOR));
        
        private RamImageComponent icon;
        
        /**
         * Creates a new option.
         * 
         * @param icon the icon to use for this option
         */
        InheritanceOptions(RamImageComponent icon) {
            this.icon = icon;
        }
        
        @Override
        public RamImageComponent getIcon() {
            return icon;
        }
        
    }
    
    @Override
    public boolean processTapAndHold(TapAndHoldEvent tapAndHoldEvent, RamEnd<?, ?> end) {
        // do nothing right now
        return true;
    }
    
    @Override
    public boolean processDoubleTap(TapEvent tapEvent, RamEnd<?, ?> end) {
        @SuppressWarnings("unchecked")
        final RamEnd<Class, ClassView> ramEnd1 = (RamEnd<Class, ClassView>) end;
        final RamEnd<Class, ClassView> ramEnd2 = ramEnd1.getOpposite();
        
        OptionSelectorView<InheritanceOptions> selector = 
                new OptionSelectorView<InheritanceViewHandler.InheritanceOptions>(InheritanceOptions.values());
        
        RamApp.getActiveAspectScene().addComponent(selector, tapEvent.getLocationOnScreen());
        
        selector.registerListener(new AbstractDefaultRamSelectorListener<InheritanceViewHandler.InheritanceOptions>() {
            @Override
            public void elementSelected(RamSelectorComponent<InheritanceOptions> selector, InheritanceOptions element) {
                ClassController controller = ControllerFactory.INSTANCE.getClassController();
                
                switch (element) {
                    case DELETE:
                        controller.removeSuperType(ramEnd1.getModel(), ramEnd2.getModel());
                        break;
                }
            }
        });
        
        return true;
    }
    
}
