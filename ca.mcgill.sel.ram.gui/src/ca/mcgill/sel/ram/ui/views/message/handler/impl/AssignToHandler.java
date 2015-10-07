package ca.mcgill.sel.ram.ui.views.message.handler.impl;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;
import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.StructuralFeature;
import ca.mcgill.sel.ram.TemporaryProperty;
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
import ca.mcgill.sel.ram.ui.views.SelectorView;
import ca.mcgill.sel.ram.ui.views.TextView;
import ca.mcgill.sel.ram.ui.views.handler.impl.TextViewHandler;
import ca.mcgill.sel.ram.ui.views.message.handler.IAssignToHandler;

/**
 * The handler for handling everything related to "assignTo" of objects that have this feature. It supports providing a
 * choice of selections of possible values, but also to create a temporary property (including renaming it after it is
 * created). Furthermore, a previously chosen value can be changed later. Sub-classes need to handle these two cases.
 * This handler is not stateless, it needs to keep references to specific model objects it handles.
 *
 * @author mschoettle
 */
public abstract class AssignToHandler extends TextViewHandler implements IAssignToHandler {

    private static final String ACTION_TEMPORARY_CREATE = "assign.temporary.create";
    private static final String ACTION_TEMPORARY_RENAME = "assign.temporary.rename";

    /**
     * The selector to support selecting all possible assignTo values.
     */
    private class AssignToSelector extends SelectorView {

        /**
         * The internal namer for the assignTo selector. It is capable to properly name the structural features.
         */
        private class InternalNamer implements Namer<Object> {

            @Override
            public RamRectangleComponent getDisplayComponent(Object element) {
                RamTextComponent view = null;

                if (element instanceof StructuralFeature) {
                    StructuralFeature structuralFeature = (StructuralFeature) element;

                    String text = EMFEditUtil.getText(structuralFeature);
                    text += " : ";
                    text += EMFEditUtil.getText(structuralFeature.getType());
                    view = new RamTextComponent(text);
                } else {
                    view = new RamTextComponent(element.toString());
                }

                view.setNoFill(false);
                view.setFillColor(Colors.DEFAULT_ELEMENT_FILL_COLOR);
                view.setNoStroke(false);
                return view;
            }

            @Override
            public String getSortingName(Object element) {
                String result = null;

                if (element instanceof StructuralFeature) {
                    StructuralFeature structuralFeature = (StructuralFeature) element;

                    String typeName = EMFEditUtil.getTypeName(structuralFeature);
                    result = typeName + " " + EMFEditUtil.getText(structuralFeature);
                } else {
                    result = element.toString();
                }

                return result;
            }

            @Override
            public String getSearchingName(Object element) {
                return getSortingName(element);
            }

        }

        private RamButton addButton;
        private RamButton renameButton;

        /**
         * Creates a new selector for assignTo for the given message (data) and feature.
         *
         * @param data the message an assignTo should be selected for
         * @param feature the assignTo feature
         */
        public AssignToSelector(EObject data, EStructuralFeature feature) {
            super(data, feature);

            Object value = data.eGet(feature);

            if (value instanceof TemporaryProperty) {
                TemporaryProperty property = (TemporaryProperty) value;

                if (property.eContainingFeature() == RamPackage.Literals.MESSAGE__LOCAL_PROPERTIES) {
                    renameButton = new RamButton(Strings.RENAME_PROPERTY);
                    renameButton.setActionCommand(ACTION_TEMPORARY_RENAME);
                    inputContainer.addChild(renameButton);
                }
            }

            RamImageComponent addImage =
                    new RamImageComponent(Icons.ICON_ADD, Colors.ICON_ADD_MESSAGEVIEW_COLOR, Icons.ICON_SIZE,
                            Icons.ICON_SIZE);
            addButton = new RamButton(addImage);
            addButton.setActionCommand(ACTION_TEMPORARY_CREATE);
            inputContainer.addChild(addButton);

            setNamer(new InternalNamer());
            initialize();
        }

        /**
         * Adds the given listener to all extra buttons that this selector contains.
         *
         * @param listener the listener to add
         */
        public void addActionListener(ActionListener listener) {
            addButton.addActionListener(listener);

            if (renameButton != null) {
                renameButton.addActionListener(listener);
            }
        }

    }

    /**
     * The original data the handled text view is using.
     */
    protected EObject data;
    /**
     * The original feature the handled text view is using.
     */
    protected EStructuralFeature feature;

    @Override
    public boolean processTapEvent(TapEvent tapEvent) {
        if (tapEvent.isDoubleTap()) {
            final Vector3D selectorPosition = tapEvent.getLocationOnScreen();
            final TextView target = (TextView) tapEvent.getTarget();

            this.data = target.getData();
            this.feature = target.getFeature();

            final AssignToSelector selector = new AssignToSelector(data, target.getFeature());
            selector.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    if (ACTION_TEMPORARY_CREATE.equals(event.getActionCommand())) {
                        handleTemporaryCreate(target, selectorPosition);
                        selector.destroy();
                    } else if (ACTION_TEMPORARY_RENAME.equals(event.getActionCommand())) {
                        target.showKeyboard();
                        selector.destroy();
                    }
                }
            });

            RamApp.getActiveAspectScene().addComponent(selector, selectorPosition);

            selector.registerListener(new RamSelectorListener<Object>() {
                @Override
                public void elementSelected(RamSelectorComponent<Object> selector, Object element) {
                    StructuralFeature structuralFeature = (StructuralFeature) element;
                    handleElementSelected(structuralFeature);
                }

                @Override
                public void closeSelector(RamSelectorComponent<Object> selector) {
                    // Ignore.
                }

                @Override
                public void elementDoubleClicked(RamSelectorComponent<Object> selector, Object element) {

                }

                @Override
                public void elementHeld(RamSelectorComponent<Object> selector, Object element) {

                }
            });
        }

        return true;
    }

    /**
     * Handles the request to create a temporary property.
     *
     * @param target the text view the request is requested on
     * @param position the position of the occurred event
     */
    public abstract void handleTemporaryCreate(TextView target, Vector3D position);

    /**
     * Handles the selection of an existing structural feature.
     *
     * @param element the structural feature selected
     */
    public abstract void handleElementSelected(StructuralFeature element);

    @Override
    public void keyboardCancelled(TextView textView) {
        resetTextView(textView);

        super.keyboardCancelled(textView);
    }

    @Override
    public void keyboardOpened(TextView textView) {
        EObject currentData = textView.getData();
        EStructuralFeature currentFeature = textView.getFeature();
        EObject realData = (EObject) currentData.eGet(currentFeature);

        textView.setData(realData);
        textView.setFeature(CorePackage.Literals.CORE_NAMED_ELEMENT__NAME, true);

        super.keyboardOpened(textView);
    }

    @Override
    public boolean shouldDismissKeyboard(TextView textView) {
        if (super.shouldDismissKeyboard(textView)) {
            resetTextView(textView);

            return true;
        }

        return false;
    }

    /**
     * Resets the text views data and feature to the intended one.
     *
     * @param textView the {@link TextView} to reset
     */
    private void resetTextView(TextView textView) {
        textView.setData(data);
        textView.setFeature(feature, false);
    }

}
