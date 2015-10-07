package ca.mcgill.sel.ram.ui.views.message.handler.impl;

import org.mt4j.input.inputProcessors.componentProcessors.tapAndHoldProcessor.TapAndHoldEvent;

import ca.mcgill.sel.ram.InteractionFragment;
import ca.mcgill.sel.ram.controller.ControllerFactory;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamImageComponent;
import ca.mcgill.sel.ram.ui.components.RamSelectorComponent;
import ca.mcgill.sel.ram.ui.components.listeners.AbstractDefaultRamSelectorListener;
import ca.mcgill.sel.ram.ui.events.listeners.ITapAndHoldListener;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.utils.Icons;
import ca.mcgill.sel.ram.ui.views.OptionSelectorView;
import ca.mcgill.sel.ram.ui.views.OptionSelectorView.Iconified;
import ca.mcgill.sel.ram.ui.views.TextView;

/**
 * The default handler for a {@link TextView} representing an {@link ca.mcgill.sel.ram.ExecutionStatement}.
 * 
 * @author mschoettle
 */
public class ExecutionStatementHandler extends ValueSpecificationHandler implements ITapAndHoldListener {
    
    /**
     * The options to display for an execution statement.
     */
    private enum FragmentOptions implements Iconified {
        DELETE(new RamImageComponent(Icons.ICON_DELETE, Colors.ICON_DELETE_COLOR));
        
        private RamImageComponent icon;
        
        /**
         * Creates a new option literal with the given icon.
         * 
         * @param icon the icon to use for this option
         */
        FragmentOptions(RamImageComponent icon) {
            this.icon = icon;
        }
        
        @Override
        public RamImageComponent getIcon() {
            return icon;
        }
    }
    
    @Override
    public boolean processTapAndHoldEvent(TapAndHoldEvent tapAndHoldEvent) {
        if (tapAndHoldEvent.isHoldComplete()) {
            TextView textView = (TextView) tapAndHoldEvent.getTarget();
            final InteractionFragment statement = (InteractionFragment) textView.getData();
            
            OptionSelectorView<FragmentOptions> selector =
                    new OptionSelectorView<FragmentOptions>(FragmentOptions.values());
            
            RamApp.getActiveAspectScene().addComponent(selector, tapAndHoldEvent.getLocationOnScreen());
            
            selector.registerListener(new AbstractDefaultRamSelectorListener<FragmentOptions>() {
                @Override
                public void elementSelected(RamSelectorComponent<FragmentOptions> selector, FragmentOptions element) {
                    switch (element) {
                        case DELETE:
                            ControllerFactory.INSTANCE.getFragmentsController().removeInteractionFragment(statement);
                            break;
                    }
                }
            });
            
        }
        
        return true;
    }
    
}
