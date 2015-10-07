package ca.mcgill.sel.ram.ui.views.message.handler.impl;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;
import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.commons.emf.util.EMFModelUtil;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.Attribute;
import ca.mcgill.sel.ram.Class;
import ca.mcgill.sel.ram.Classifier;
import ca.mcgill.sel.ram.InteractionFragment;
import ca.mcgill.sel.ram.Lifeline;
import ca.mcgill.sel.ram.LiteralNull;
import ca.mcgill.sel.ram.LiteralSpecification;
import ca.mcgill.sel.ram.Message;
import ca.mcgill.sel.ram.MessageOccurrenceSpecification;
import ca.mcgill.sel.ram.RamFactory;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.TypedElement;
import ca.mcgill.sel.ram.controller.ControllerFactory;
import ca.mcgill.sel.ram.controller.MessageController;
import ca.mcgill.sel.ram.provider.util.RAMEditUtil;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamButton;
import ca.mcgill.sel.ram.ui.components.RamImageComponent;
import ca.mcgill.sel.ram.ui.components.RamListComponent.Namer;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.components.RamSelectorComponent;
import ca.mcgill.sel.ram.ui.components.RamTextComponent;
import ca.mcgill.sel.ram.ui.components.listeners.RamSelectorListener;
import ca.mcgill.sel.ram.ui.events.listeners.ActionListener;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.utils.Icons;
import ca.mcgill.sel.ram.ui.utils.Strings;
import ca.mcgill.sel.ram.ui.views.OptionSelectorView;
import ca.mcgill.sel.ram.ui.views.SelectorView;
import ca.mcgill.sel.ram.ui.views.TextView;
import ca.mcgill.sel.ram.util.RAMModelUtil;

/**
 * The handler for text views representing the contained value specification of an object. It supports providing a
 * choice of selections of possible values, but also to create a literal specification (including changing its value
 * after it is created). Furthermore, a previously chosen value can be changed later. This handler is not stateless, it
 * needs to keep references to specific model objects it handles.
 *
 * @author mschoettle
 */
public class ReferenceAndValueHandler extends ValueSpecificationHandler {

    private static final String ACTION_VALUE_ADD = "value.add";
    private static final String ACTION_VALUE_CHANGE = "value.change";

    /**
     * The selector to support selecting specific values and creating literal specifications (and change their values).
     */
    private class ValueSelector extends RamSelectorComponent<EObject> {

        private RamButton addButton;
        private RamButton changeButton;

        /**
         * Creates a new selector for the given data object and the containment feature of value specification.
         *
         * @param data the data object containing a value specification
         * @param feature the containment feature of the value specification
         */
        public ValueSelector(EObject data, EStructuralFeature feature) {
            super();

            Object value = data.eGet(feature);

            if (value instanceof LiteralSpecification && !(value instanceof LiteralNull)) {
                changeButton = new RamButton(Strings.CHANGE_VALUE);
                changeButton.setActionCommand(ACTION_VALUE_CHANGE);
                inputContainer.addChild(changeButton);
            }

            RamImageComponent addImage =
                    new RamImageComponent(Icons.ICON_ADD, Colors.ICON_ADD_MESSAGEVIEW_COLOR, Icons.ICON_SIZE,
                            Icons.ICON_SIZE);
            addButton = new RamButton(addImage);
            addButton.setActionCommand(ACTION_VALUE_ADD);
            inputContainer.addChild(addButton);

            setNamer(new InternalNamer());
            initialize(data);
        }

        /**
         * Adds the given listener to all extra buttons that this selector contains.
         *
         * @param listener the listener to add
         */
        public void addActionListener(ActionListener listener) {
            addButton.addActionListener(listener);

            if (changeButton != null) {
                changeButton.addActionListener(listener);
            }
        }

        /**
         * Initializes this selector for the given object.
         *
         * @param data the data object for which to show possible values for
         */
        private void initialize(EObject data) {
            Message message = (Message) EMFModelUtil.getRootContainerOfType(data, RamPackage.Literals.MESSAGE);

            // In case data is already a message...
            if (message == null && data.eClass() == RamPackage.Literals.MESSAGE) {
                message = (Message) data;
            }

            Lifeline callerLifeline = ((MessageOccurrenceSpecification) message.getSendEvent()).getCovered().get(0);

            // TODO: mschoettle: Check types.

            List<EObject> elements = new UniqueEList<EObject>();

            // Find the previous calling message.
            Message initialMessage = RAMModelUtil.findInitialMessage((InteractionFragment) message.getSendEvent());
            Aspect aspect = EMFModelUtil.getRootContainerOfType(message, RamPackage.Literals.ASPECT);
            
            @SuppressWarnings("unchecked")
            Collection<EObject> structuralFeatures =
                    (Collection<EObject>) RAMEditUtil.collectStructuralFeatures(aspect, callerLifeline, 
                            initialMessage, null);
            structuralFeatures.remove(null);
            elements.addAll(structuralFeatures);

            // Collect additional elements.
            if (initialMessage != null) {
                if (initialMessage.getSignature() != null) {
                    elements.addAll(initialMessage.getSignature().getParameters());
                }
            }

            // Collect association ends and attributes of calling lifeline.
            if (callerLifeline.getRepresents().getType() instanceof Classifier) {
                Classifier classifier = (Classifier) callerLifeline.getRepresents().getType();

                // TODO: mschoettle: Should this be restricted for ParameterValueMappings,
                // i.e., only if the parameter type is a primitive type?
                for (Attribute attribute : ((Class) classifier).getAttributes()) {
                    elements.add(attribute);
                }
            }

            // Add the lifeline itself.
            elements.add(callerLifeline.getRepresents());

            setElements(elements);
        }

    }

    /**
     * The internal namer for the value selector. It is capable to properly name typed elements.
     */
    private class InternalNamer implements Namer<EObject> {

        @Override
        public RamRectangleComponent getDisplayComponent(EObject element) {
            String text = EMFEditUtil.getText(element);
            if (element instanceof TypedElement) {
                TypedElement typedElement = (TypedElement) element;
                text += " : ";
                text += EMFEditUtil.getText(typedElement.getType());
            }

            RamTextComponent view = new RamTextComponent(text);
            view.setNoFill(false);
            view.setFillColor(Colors.DEFAULT_ELEMENT_FILL_COLOR);
            view.setNoStroke(false);
            return view;
        }

        @Override
        public String getSortingName(EObject element) {
            String typeName = EMFEditUtil.getTypeName(element);
            String result = typeName + " " + EMFEditUtil.getText(element);

            return result;
        }

        @Override
        public String getSearchingName(EObject element) {
            return getSortingName(element);
        }

    }

    /**
     * The different literal specification options for values.
     */
    private enum ValueOptions {
        STRING, INTEGER, BOOLEAN, NULL, ENUM_LITERAL
    }

    @Override
    public boolean processTapEvent(TapEvent tapEvent) {
        if (tapEvent.isDoubleTap()) {
            final Vector3D selectorPosition = tapEvent.getLocationOnScreen();
            final TextView target = (TextView) tapEvent.getTarget();

            valueContainer = target.getData();
            feature = target.getFeature();

            final ValueSelector selector = new ValueSelector(valueContainer, feature);
            selector.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    if (ACTION_VALUE_ADD.equals(event.getActionCommand())) {
                        showCreateNewValue(selectorPosition);
                        selector.destroy();
                    } else if (ACTION_VALUE_CHANGE.equals(event.getActionCommand())) {
                        handleChangeValue(target);
                        selector.destroy();
                    }
                }
            });

            RamApp.getActiveAspectScene().addComponent(selector, selectorPosition);

            selector.registerListener(new RamSelectorListener<EObject>() {
                @Override
                public void elementSelected(RamSelectorComponent<EObject> selector, EObject element) {
                    MessageController controller = ControllerFactory.INSTANCE.getMessageController();
                    controller.addReferenceValueSpecification(valueContainer, feature, element);
                    selector.destroy();
                }

                @Override
                public void closeSelector(RamSelectorComponent<EObject> selector) {
                    selector.destroy();
                }

                @Override
                public void elementDoubleClicked(RamSelectorComponent<EObject> selector, EObject element) {

                }

                @Override
                public void elementHeld(RamSelectorComponent<EObject> selector, EObject element) {

                }
            });
        }

        return true;
    }

    /**
     * Shows a selector for all {@link ValueOptions}.
     *
     * @param selectorPosition the position for the selector
     */
    private void showCreateNewValue(final Vector3D selectorPosition) {
        OptionSelectorView<ValueOptions> selector = new OptionSelectorView<ValueOptions>(ValueOptions.values());

        RamApp.getActiveAspectScene().addComponent(selector, selectorPosition);

        selector.registerListener(new RamSelectorListener<ValueOptions>() {
            @Override
            public void elementSelected(RamSelectorComponent<ValueOptions> selector, ValueOptions element) {
                handleValueOptionSelection(selectorPosition, element);
            }

            @Override
            public void closeSelector(RamSelectorComponent<ValueOptions> selector) {
            }

            @Override
            public void elementDoubleClicked(RamSelectorComponent<ValueOptions> selector, ValueOptions element) {
            }

            @Override
            public void elementHeld(RamSelectorComponent<ValueOptions> selector, ValueOptions element) {

            }
        });
    }
    
    /**
     * Handles the selection of a value option by the user.
     * 
     * @param selectorPosition the position of the selector
     * @param element the selected option
     */
    private void handleValueOptionSelection(final Vector3D selectorPosition, ValueOptions element) {
        MessageController controller = ControllerFactory.INSTANCE.getMessageController();
        switch (element) {
            case STRING:
                controller.addValueSpecification(valueContainer, feature, RamPackage.Literals.LITERAL_STRING);
                break;
            case BOOLEAN:
                controller.addValueSpecification(valueContainer, feature, RamPackage.Literals.LITERAL_BOOLEAN);
                break;
            case INTEGER:
                controller.addValueSpecification(valueContainer, feature, RamPackage.Literals.LITERAL_INTEGER);
                break;
            case NULL:
                controller.addValueSpecification(valueContainer, feature, RamPackage.Literals.LITERAL_NULL);
                break;
            case ENUM_LITERAL:
                showCreateEnumLiteralValue(selectorPosition);
                break;
        }
    }

    /**
     * Shows a selector to choose a specific enum literal. The chosen literal is set as the
     * {@link ca.mcgill.sel.ram.EnumLiteralValue}'s value, which is newly created and set as the value specification.
     *
     * @param selectorPosition the position at which the selector should be displayed
     */
    private void showCreateEnumLiteralValue(Vector3D selectorPosition) {
        /**
         * In order to be able to use the SelectorView, which uses the property descriptor and its choice of values, a
         * temporary EnumLiteralValue needs to be created. It also needs to be added to the model, otherwise no choice
         * of values are retrieved, because it is not contained anywhere. In all cases, the temporary data needs to be
         * removed. In case of an abort, the previous value specification needs to be reverted back.
         */
        valueContainer.eSetDeliver(false);
        EObject temporaryEnumValue = RamFactory.eINSTANCE.createEnumLiteralValue();
        final Object previousValueSpecification = valueContainer.eGet(feature);
        valueContainer.eSet(feature, temporaryEnumValue);
        EStructuralFeature enumValueFeature = RamPackage.Literals.ENUM_LITERAL_VALUE__VALUE;

        RamSelectorComponent<Object> selector = new SelectorView(temporaryEnumValue, enumValueFeature);

        RamApp.getActiveAspectScene().addComponent(selector, selectorPosition);

        selector.registerListener(new RamSelectorListener<Object>() {

            @Override
            public void elementSelected(RamSelectorComponent<Object> selector, Object element) {
                resetTemporaryObjects(previousValueSpecification);
                MessageController controller = ControllerFactory.INSTANCE.getMessageController();
                controller.addReferenceValueSpecification(valueContainer, feature, (EObject) element);
            }

            @Override
            public void closeSelector(RamSelectorComponent<Object> selector) {
                resetTemporaryObjects(previousValueSpecification);
            }

            @Override
            public void elementDoubleClicked(RamSelectorComponent<Object> selector, Object element) {

            }

            @Override
            public void elementHeld(RamSelectorComponent<Object> selector, Object element) {

            }

        });
    }

    /**
     * Reverts back the previous data for the feature of the value container. Also enables delivery of notifications
     * again.
     *
     * @param previousData the previous data to set back
     */
    private void resetTemporaryObjects(Object previousData) {
        valueContainer.eSet(feature, previousData);
        valueContainer.eSetDeliver(true);
    }

}
