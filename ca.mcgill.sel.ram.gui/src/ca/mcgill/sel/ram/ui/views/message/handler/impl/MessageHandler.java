package ca.mcgill.sel.ram.ui.views.message.handler.impl;

import org.eclipse.emf.ecore.EObject;
import org.mt4j.input.inputProcessors.componentProcessors.tapAndHoldProcessor.TapAndHoldEvent;

import ca.mcgill.sel.ram.FragmentContainer;
import ca.mcgill.sel.ram.Gate;
import ca.mcgill.sel.ram.Interaction;
import ca.mcgill.sel.ram.InteractionFragment;
import ca.mcgill.sel.ram.Message;
import ca.mcgill.sel.ram.MessageOccurrenceSpecification;
import ca.mcgill.sel.ram.MessageSort;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.controller.ControllerFactory;
import ca.mcgill.sel.ram.controller.MessageViewController;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamImageComponent;
import ca.mcgill.sel.ram.ui.components.RamSelectorComponent;
import ca.mcgill.sel.ram.ui.components.listeners.AbstractDefaultRamSelectorListener;
import ca.mcgill.sel.ram.ui.events.listeners.ITapAndHoldListener;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.utils.Icons;
import ca.mcgill.sel.ram.ui.views.OptionSelectorView;
import ca.mcgill.sel.ram.ui.views.OptionSelectorView.Iconified;
import ca.mcgill.sel.ram.ui.views.handler.BaseHandler;
import ca.mcgill.sel.ram.ui.views.message.MessageCallView;

/**
 * The default handler for {@link ca.mcgill.sel.ram.Message}s.
 * 
 * @author mschoettle
 */
public class MessageHandler extends BaseHandler implements ITapAndHoldListener {
    
    /**
     * The options to display for a message call.
     */
    private enum MessageOptions implements Iconified {
        DELETE(new RamImageComponent(Icons.ICON_DELETE, Colors.ICON_DELETE_COLOR));
        
        private RamImageComponent icon;
        
        /**
         * Creates a new option literal with the given icon.
         * 
         * @param icon the icon to use for this option
         */
        MessageOptions(RamImageComponent icon) {
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
            final MessageCallView messageCallView = (MessageCallView) tapAndHoldEvent.getTarget();
            if (shouldProcessTapAndHold(messageCallView.getMessage())) {
                final MessageOccurrenceSpecification sendEvent =
                        (MessageOccurrenceSpecification) messageCallView.getFromEnd().getModel();
                final FragmentContainer container = (FragmentContainer) sendEvent.eContainer();
                final Interaction interaction = sendEvent.getMessage().getInteraction();
                
                OptionSelectorView<MessageOptions> selector =
                        new OptionSelectorView<MessageOptions>(MessageOptions.values());
                
                RamApp.getActiveAspectScene().addComponent(selector, tapAndHoldEvent.getLocationOnScreen());
                
                selector.registerListener(new AbstractDefaultRamSelectorListener<MessageOptions>() {
                    @Override
                    public void elementSelected(RamSelectorComponent<MessageOptions> selector, MessageOptions element) {
                        switch (element) {
                            case DELETE:
                                // Deleting of reply messages not supported yet.
                                if (messageCallView.getMessage().getMessageSort() != MessageSort.REPLY) {
                                    MessageViewController controller =
                                            ControllerFactory.INSTANCE.getMessageViewController();
                                    controller.removeMessages(interaction, container, sendEvent);
                                    break;
                                }
                        }
                    }
                });
            }
        }
        
        return true;
    }
    
    /**
     * Returns whether a tap-and-hold event should be processed.
     * An event should be processed only for those messages that are not the initial message
     * or the last reply message, i.e., the very first and last messages of a message view.
     * 
     * @param message the message on whose view the event occurred
     * @return true, whether the event should be further processed, false otherwise
     */
    private static boolean shouldProcessTapAndHold(Message message) {
        if (!(message.getSendEvent() instanceof Gate)) {
            /**
             * Make sure that only those reply messages can be handled
             * that are additional (not the last ones) and not nested behaviour ones.
             */
            InteractionFragment sendEvent = (InteractionFragment) message.getSendEvent();
            EObject interaction = message.eContainer();
            if (message.getMessageSort() != MessageSort.REPLY
                    || (sendEvent.getContainer() != interaction
                    && message.getReceiveEvent().eClass() == RamPackage.Literals.GATE)) {
                return true;
            }
        }
        
        return false;
    }
    
}
