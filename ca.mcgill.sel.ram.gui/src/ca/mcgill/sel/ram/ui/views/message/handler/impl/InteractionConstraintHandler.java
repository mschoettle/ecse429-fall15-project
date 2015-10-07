package ca.mcgill.sel.ram.ui.views.message.handler.impl;

import java.util.EnumSet;

import org.mt4j.input.inputProcessors.componentProcessors.tapAndHoldProcessor.TapAndHoldEvent;

import ca.mcgill.sel.ram.CombinedFragment;
import ca.mcgill.sel.ram.controller.ControllerFactory;
import ca.mcgill.sel.ram.controller.FragmentsController;
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
 * The handler for text views responsible for visualizing interaction constraints.
 * Allows the text view to be oblivious of the specific value specification used for the constraint.
 * This means that the first time an event is processed, it will figure out the actual object and
 * feature, which will be set for the view.
 * 
 * @author mschoettle
 */
public class InteractionConstraintHandler extends ValueSpecificationHandler implements ITapAndHoldListener {
    
    /**
     * The options to display for an interaction operand.
     */
    private enum OperandOptions implements Iconified {
        ADD_OPERAND(new RamImageComponent(Icons.ICON_ADD, Colors.ICON_ADD_MESSAGEVIEW_COLOR)),
        REMOVE_OPERAND(new RamImageComponent(Icons.ICON_DELETE, Colors.ICON_DELETE_COLOR));
        
        private RamImageComponent icon;
        
        /**
         * Creates a new option literal with the given icon.
         * 
         * @param icon the icon to use for this option
         */
        OperandOptions(RamImageComponent icon) {
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
            TextView target = (TextView) tapAndHoldEvent.getTarget();
            final CombinedFragment combinedFragment = (CombinedFragment) target.getData().eContainer();
            final int index = combinedFragment.getOperands().indexOf(target.getData());
            
            EnumSet<OperandOptions> availableOptions = EnumSet.allOf(OperandOptions.class);
            
            if (index == 0) {
                availableOptions.remove(OperandOptions.REMOVE_OPERAND);
            }
            
            OptionSelectorView<OperandOptions> selector =
                    new OptionSelectorView<OperandOptions>(availableOptions.toArray(new OperandOptions[] {}));
            
            RamApp.getActiveAspectScene().addComponent(selector, tapAndHoldEvent.getLocationOnScreen());
            
            selector.registerListener(new AbstractDefaultRamSelectorListener<OperandOptions>() {
                @Override
                public void elementSelected(RamSelectorComponent<OperandOptions> selector, OperandOptions element) {
                    FragmentsController controller = ControllerFactory.INSTANCE.getFragmentsController();
                    switch (element) {
                        case ADD_OPERAND:
                            controller.createInteractionOperand(combinedFragment, index + 1);
                            break;
                        case REMOVE_OPERAND:
                            controller.removeInteractionOperand(combinedFragment.getOperands().get(index));
                            break;
                    }
                }
            });
        }
        
        return true;
    }
    
}
