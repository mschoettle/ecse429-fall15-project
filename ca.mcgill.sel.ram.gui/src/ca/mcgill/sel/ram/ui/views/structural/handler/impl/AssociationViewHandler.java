package ca.mcgill.sel.ram.ui.views.structural.handler.impl;

import org.mt4j.input.inputProcessors.componentProcessors.tapAndHoldProcessor.TapAndHoldEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;

import ca.mcgill.sel.ram.AssociationEnd;
import ca.mcgill.sel.ram.Class;
import ca.mcgill.sel.ram.ImplementationClass;
import ca.mcgill.sel.ram.ReferenceType;
import ca.mcgill.sel.ram.controller.AssociationController;
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

/**
 * The default handler for an {@link ca.mcgill.sel.ram.ui.views.structural.AssociationView}.
 * 
 * @author eyildirim
 * @author mschoettle
 */
public class AssociationViewHandler extends BaseHandler implements IRelationshipViewHandler {
    
    /**
     * The options to display for an association end.
     */
    private enum AssociationOptions implements Iconified {
        COMPOSITION(new RamImageComponent(Icons.ICON_COMPOSITION, Colors.ICON_ADD_DEFAULT_COLOR)), 
        AGGREGATION(new RamImageComponent(Icons.ICON_AGGREGATION, Colors.ICON_ADD_DEFAULT_COLOR)),
        REGULAR(new RamImageComponent(Icons.ICON_LINE, Colors.ICON_ADD_DEFAULT_COLOR)),
        DELETE(new RamImageComponent(Icons.ICON_DELETE, Colors.ICON_DELETE_COLOR));
        
        private RamImageComponent icon;
        
        /**
         * Creates a new option literal with the given icon.
         * 
         * @param icon the icon to use for this option
         */
        AssociationOptions(RamImageComponent icon) {
            this.icon = icon;
        }
        
        @Override
        public RamImageComponent getIcon() {
            return icon;
        }
        
    }
    
    @Override
    public boolean processTapAndHold(TapAndHoldEvent tapAndHoldEvent, RamEnd<?, ?> end) {
        AssociationEnd associationEnd = (AssociationEnd) end.getModel();
        // Navigable associations from Implementation Classes to Classes are not allowed.
        // The end that will be made non-navigable cannot be the of the Class.
        // So we need to check that the navigable end (opposite) is not from an Implementation Class.
        // See issue #117.
        AssociationEnd oppositeEnd = associationEnd.getOppositeEnd();
        
        if (oppositeEnd.getClassifier() instanceof Class
                && !(associationEnd.getClassifier() instanceof ImplementationClass)) {
            AssociationController controller = ControllerFactory.INSTANCE.getAssociationController();
            controller.switchNavigable(RamApp.getActiveAspectScene().getAspect(), associationEnd, oppositeEnd);
        }
        
        return true;
    }
    
    @Override
    public boolean processDoubleTap(TapEvent tapEvent, RamEnd<?, ?> end) {
        final AssociationEnd associationEnd = (AssociationEnd) end.getModel();
        
        // Prevent changing the end belonging to an Implementation class (see issue #117).
        // Allow only deleting.
        AssociationOptions[] availableOptions;
        
        if (associationEnd.getClassifier() instanceof Class) {
            availableOptions = AssociationOptions.values();
        } else {
            availableOptions = new AssociationOptions[] { AssociationOptions.DELETE };
        }
        
        OptionSelectorView<AssociationOptions> selector = 
                new OptionSelectorView<AssociationViewHandler.AssociationOptions>(availableOptions);
        
        RamApp.getActiveAspectScene().addComponent(selector, tapEvent.getLocationOnScreen());
        
        selector.registerListener(new AbstractDefaultRamSelectorListener<AssociationViewHandler.AssociationOptions>() {
            @Override
            public void elementSelected(RamSelectorComponent<AssociationOptions> selector, AssociationOptions element) {
                AssociationController controller = ControllerFactory.INSTANCE.getAssociationController();
                
                switch (element) {
                    case COMPOSITION:
                        controller.setReferenceType(associationEnd, ReferenceType.COMPOSITION);
                        break;
                    case AGGREGATION:
                        controller.setReferenceType(associationEnd, ReferenceType.AGGREGATION);
                        break;
                    case REGULAR:
                        controller.setReferenceType(associationEnd, ReferenceType.REGULAR);
                        break;
                    case DELETE:
                        controller.deleteAssociation(RamApp.getActiveAspectScene().getAspect(),
                                associationEnd.getAssoc());
                        break;
                }
            }
            
        });
        
        return true;
    }
}
