package ca.mcgill.sel.ram.ui.views.state.handler.impl;

import org.mt4j.input.inputProcessors.componentProcessors.tapAndHoldProcessor.TapAndHoldEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;

import ca.mcgill.sel.ram.controller.ControllerFactory;
import ca.mcgill.sel.ram.controller.TransitionController;
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
import ca.mcgill.sel.ram.ui.views.state.TransitionView;

/**
 * The default handler for an {@link TransitionView}.
 * 
 * @author abirayed
 */
public class TransitionViewHandler extends BaseHandler implements IRelationshipViewHandler {
    
    /**
     * The options for a transition.
     */
    private enum TransitionOptions implements Iconified {
        DELETE(new RamImageComponent(Icons.ICON_DELETE, Colors.ICON_DELETE_COLOR));
        
        private RamImageComponent icon;
        
        /**
         * Creates a new literal with the given icon.
         * 
         * @param icon the icon for this literal
         */
        TransitionOptions(RamImageComponent icon) {
            this.icon = icon;
        }
        
        @Override
        public RamImageComponent getIcon() {
            return icon;
        }
        
    }
    
    @Override
    public boolean processTapAndHold(TapAndHoldEvent tapAndHoldEvent, RamEnd<?, ?> end) {
        
        if (tapAndHoldEvent.isHoldComplete()) {
            
            final TransitionView view = (TransitionView) end.getRelationshipView();
            
            TransitionController controller = ControllerFactory.INSTANCE.getTransitionController();
            controller.switchNavigable(view.getTransition());
            
        }
        return true;
    }
    
    @Override
    public boolean processDoubleTap(TapEvent tapEvent, RamEnd<?, ?> end) {
        
        final TransitionView view = (TransitionView) end.getRelationshipView();
        
        OptionSelectorView<TransitionOptions> selector =
                new OptionSelectorView<TransitionViewHandler.TransitionOptions>(TransitionOptions.values());
        
        RamApp.getActiveAspectScene().addComponent(selector, tapEvent.getLocationOnScreen());
        
        selector.registerListener(new AbstractDefaultRamSelectorListener<TransitionViewHandler.TransitionOptions>() {
            @Override
            public void elementSelected(RamSelectorComponent<TransitionOptions> selector, TransitionOptions element) {
                TransitionController controller = ControllerFactory.INSTANCE.getTransitionController();
                
                switch (element) {
                    case DELETE:
                        controller.deleteTransition(view.getTransition());
                        break;
                    default:
                        break;
                        
                }
            }
        });
        return true;
    }
}
