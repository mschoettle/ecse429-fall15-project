package ca.mcgill.sel.ram.ui.views.handler.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.core.COREModel;
import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamSelectorComponent;
import ca.mcgill.sel.ram.ui.components.listeners.AbstractDefaultRamSelectorListener;
import ca.mcgill.sel.ram.ui.views.SelectorView;
import ca.mcgill.sel.ram.ui.views.TextView;
import ca.mcgill.sel.ram.ui.views.handler.BaseHandler;
import ca.mcgill.sel.ram.ui.views.handler.ITextViewHandler;
import ca.mcgill.sel.ram.util.RAMModelUtil;

/**
 * The default handler for a {@link TextView}. Depending on the type of feature it shows a selector (if it is a
 * {@link EReference} or an {@link EEnum}) or the keyboard (in all other cases).
 *
 * @author mschoettle
 */
public class TextViewHandler extends BaseHandler implements ITextViewHandler {

    @Override
    public void keyboardCancelled(TextView textView) {
        textView.updateText();
    }

    @Override
    public void keyboardOpened(TextView textView) {
        EObject data = textView.getData();
        EStructuralFeature feature = textView.getFeature();

        textView.setText(EMFEditUtil.getFeatureText(data, feature));
    }

    @Override
    public boolean processTapEvent(TapEvent tapEvent) {
        if (tapEvent.isDoubleTap()) {
            final TextView target = (TextView) tapEvent.getTarget();

            if (target.getFeature() instanceof EReference || target.getFeature().getEType() instanceof EEnum) {
                RamSelectorComponent<Object> selector = new SelectorView(target.getData(), target.getFeature());

                RamApp.getActiveAspectScene().addComponent(selector, tapEvent.getLocationOnScreen());

                // TODO: mschoettle: this could be done by the selector itself. but does it make sense?
                selector.registerListener(new AbstractDefaultRamSelectorListener<Object>() {
                    @Override
                    public void elementSelected(RamSelectorComponent<Object> selector, Object element) {
                        setValue(target.getData(), target.getFeature(), element);
                    }
                });
            } else {
                target.showKeyboard();
            }

            return true;
        }

        return false;
    }

    /**
     * Sets the value on the data object for the given feature.
     *
     * @param data the object containing the feature
     * @param feature the feature of which the value should be changed
     * @param value the new value of the feature
     */
    protected void setValue(EObject data, EStructuralFeature feature, Object value) {
        EMFEditUtil.getPropertyDescriptor(data, feature).setPropertyValue(data, value);
    }

    @Override
    public boolean shouldDismissKeyboard(TextView textView) {
        EObject data = textView.getData();
        EAttribute feature = (EAttribute) textView.getFeature();
        String text = textView.getText();

        // Replaces the text by the old name if the current text is empty
        if (text.isEmpty()) {
            text = EMFEditUtil.getFeatureText(data, feature);
            textView.setText(text);
        }

        // Checks if the name has changed
        String oldText = EMFEditUtil.getFeatureText(data, feature);
        boolean nameChanged = (oldText.equalsIgnoreCase(text)) ? false : true;

        if (textView.isUniqueName() && nameChanged) {
            EObject container = data.eContainer();
            EStructuralFeature containingFeature = data.eContainingFeature();
            if (data instanceof COREModel) {
                container = ((COREModel) data).getCoreConcern();
                containingFeature = CorePackage.Literals.CORE_CONCERN__MODELS;
            }
            if (!RAMModelUtil.isUnique(container, containingFeature, text, data)) {
                // TODO: show an error message?
                return false;
            }
        }

        // Convert the value to the type of the feature.
        try {
            Object newValue = EcoreUtil.createFromString(feature.getEAttributeType(), text);
            setValue(data, feature, newValue);

            return true;
        } catch (IllegalArgumentException e) {
            // The use did not give a correct format. For example, if the Type is Integer, and the user say "foo", A
            // IllegalArgument Exception will be thrown.
            // In that case, we won't dismiss the keyboard.

            // TODO Show an error message to the user. Maybe put that in RamKeyboard>dimissKeyboard(boolean success)
            // when dismiss boolean is false.
            return false;
        }
    }

}
