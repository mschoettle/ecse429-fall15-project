package ca.mcgill.sel.ram.ui.views.message.handler.impl;

import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.ram.AssignmentStatement;
import ca.mcgill.sel.ram.Attribute;
import ca.mcgill.sel.ram.Message;
import ca.mcgill.sel.ram.PrimitiveType;
import ca.mcgill.sel.ram.RamFactory;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.StructuralFeature;
import ca.mcgill.sel.ram.controller.ControllerFactory;
import ca.mcgill.sel.ram.controller.FragmentsController;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamSelectorComponent;
import ca.mcgill.sel.ram.ui.components.listeners.RamSelectorListener;
import ca.mcgill.sel.ram.ui.utils.Strings;
import ca.mcgill.sel.ram.ui.views.SelectorView;
import ca.mcgill.sel.ram.ui.views.TextView;
import ca.mcgill.sel.ram.util.RAMModelUtil;

/**
 * The handler for handling everything related to "assignTo" of a message. It supports providing a choice of selections
 * of possible values, but also to create a temporary property (including renaming it after it is created). Furthermore,
 * a previously chosen value can be changed later. This handler is not stateless, it needs to keep references to
 * specific model objects it handles.
 *
 * @author mschoettle
 */
public class AssignmentAssignToHandler extends AssignToHandler {

    @Override
    public void handleTemporaryCreate(TextView target, Vector3D position) {
        final AssignmentStatement assignmentStatement = (AssignmentStatement) data;

        /**
         * In order to be able to use the SelectorView, which uses the property descriptor and its choice of values, a
         * temporary Attribute needs to be created. It also needs to be added to the model, otherwise no choice of
         * values are retrieved, because it is not contained anywhere. In all cases, the temporary data needs to be
         * removed.
         */
        final Attribute attribute = RamFactory.eINSTANCE.createAttribute();
        final Message initialMessage = RAMModelUtil.findInitialMessage(assignmentStatement);
        initialMessage.eSetDeliver(false);
        initialMessage.getLocalProperties().add(attribute);

        SelectorView typeSelector = new SelectorView(attribute, RamPackage.Literals.ATTRIBUTE__TYPE);
        typeSelector.registerListener(new RamSelectorListener<Object>() {

            @Override
            public void elementSelected(RamSelectorComponent<Object> selector, Object element) {
                resetTemporaryObjects(initialMessage, attribute);
                FragmentsController controller = ControllerFactory.INSTANCE.getFragmentsController();
                controller.createTemporaryAssignment(assignmentStatement, Strings.DEFAULT_PROPERTY_NAME,
                        (PrimitiveType) element);

                selector.destroy();
            }

            @Override
            public void closeSelector(RamSelectorComponent<Object> selector) {
                resetTemporaryObjects(initialMessage, attribute);
            }

            @Override
            public void elementDoubleClicked(RamSelectorComponent<Object> selector, Object element) {

            }

            @Override
            public void elementHeld(RamSelectorComponent<Object> selector, Object element) {

            }
        });

        RamApp.getActiveAspectScene().addComponent(typeSelector, position);
    }

    /**
     * Reverts back the previously added attribute. Also enables delivery of notifications again.
     *
     * @param initialMessage the message containing the attribute as a local property
     * @param attribute the temporarily added attribute that should be removed
     */
    private static void resetTemporaryObjects(Message initialMessage, Attribute attribute) {
        initialMessage.getLocalProperties().remove(attribute);
        initialMessage.eSetDeliver(true);
    }

    @Override
    public void handleElementSelected(StructuralFeature structuralFeature) {
        AssignmentStatement assignmentStatement = (AssignmentStatement) data;
        FragmentsController controller = ControllerFactory.INSTANCE.getFragmentsController();

        controller.changeAssignmentAssignTo(assignmentStatement, structuralFeature);
    }

}
