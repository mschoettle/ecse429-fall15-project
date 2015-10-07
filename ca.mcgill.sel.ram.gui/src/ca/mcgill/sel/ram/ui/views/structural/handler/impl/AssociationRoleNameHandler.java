package ca.mcgill.sel.ram.ui.views.structural.handler.impl;

import org.mt4j.input.inputProcessors.componentProcessors.tapAndHoldProcessor.TapAndHoldEvent;

import ca.mcgill.sel.ram.AssociationEnd;
import ca.mcgill.sel.ram.controller.AssociationController;
import ca.mcgill.sel.ram.controller.ClassController;
import ca.mcgill.sel.ram.controller.ControllerFactory;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamSelectorComponent;
import ca.mcgill.sel.ram.ui.components.listeners.AbstractDefaultRamSelectorListener;
import ca.mcgill.sel.ram.ui.utils.MetamodelRegex;
import ca.mcgill.sel.ram.ui.views.OptionSelectorView;
import ca.mcgill.sel.ram.ui.views.TextView;
import ca.mcgill.sel.ram.ui.views.handler.impl.ValidatingTextViewHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.IAssociationRoleNameHandler;

/**
 * The default handler for a {@link TextView} representing the role name of an {@link AssociationEnd}.
 * The name gets validated in order to only allow valid names.
 * 
 * @author lmartellotto
 *
 */
public class AssociationRoleNameHandler extends ValidatingTextViewHandler implements IAssociationRoleNameHandler {
    
    /**
     * Creates a new {@link AssociationRoleNameHandler}.
     */
    public AssociationRoleNameHandler() {
        super(MetamodelRegex.REGEX_TYPE_NAME);
    }
    
    /**
     * Features that can be used on the role name of an {@link AssociationEnd}.
     */
    private enum AssociationRoleFeatures {
        STATIC,
        CREATE_GETTER,
        CREATE_SETTER,
        CREATE_GETTER_AND_SETTER;
    }
    
    @Override
    public boolean processTapAndHoldEvent(TapAndHoldEvent tapAndHoldEvent) {
        if (tapAndHoldEvent.isHoldComplete()) {
            TextView target = (TextView) tapAndHoldEvent.getTarget();
            
            final AssociationEnd attribute = (AssociationEnd) target.getData();
            
            OptionSelectorView<AssociationRoleFeatures> selector =
                    new OptionSelectorView<AssociationRoleNameHandler.AssociationRoleFeatures>(
                            AssociationRoleFeatures.values());
            RamApp.getActiveAspectScene().addComponent(selector, tapAndHoldEvent.getLocationOnScreen());
            
            selector.registerListener(
                    new AbstractDefaultRamSelectorListener<AssociationRoleNameHandler.AssociationRoleFeatures>() {
                        @Override
                        public void elementSelected(RamSelectorComponent<AssociationRoleFeatures> selector,
                                AssociationRoleFeatures element) {
                            ClassController classController = ControllerFactory.INSTANCE.getClassController();
                            AssociationController assocController = ControllerFactory.INSTANCE
                                    .getAssociationController();
                            
                            switch (element) {
                                case STATIC:
                                    assocController.switchStatic(attribute);
                                    break;
                                case CREATE_GETTER:
                                    classController.createGetterOperation(attribute);
                                    break;
                                case CREATE_SETTER:
                                    classController.createSetterOperation(attribute);
                                    break;
                                case CREATE_GETTER_AND_SETTER:
                                    classController.createGetterAndSetterOperation(attribute);
                                    break;
                            }
                        }
                        
                    });
        }
        
        return true;
    }
    
}
