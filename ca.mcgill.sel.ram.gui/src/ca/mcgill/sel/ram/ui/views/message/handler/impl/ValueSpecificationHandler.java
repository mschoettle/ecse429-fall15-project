package ca.mcgill.sel.ram.ui.views.message.handler.impl;

import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.ui.utils.LoggerUtils;
import ca.mcgill.sel.ram.ui.views.TextView;
import ca.mcgill.sel.ram.ui.views.handler.impl.TextViewHandler;
import ca.mcgill.sel.ram.ui.views.message.handler.IValueSpecificationHandler;

/**
 * The handler for text views representing the contained value specification of an object.
 * It supports providing a choice of selections of possible values, but also
 * to create a literal specification (including changing its value after it is created).
 * Furthermore, a previously chosen value can be changed later.
 * This handler is <b>not stateless</b>, it needs to keep references to specific model objects it handles.
 * <b>Note:</b> Sub-classes that overwrite {@link #processTapEvent(TapEvent)} need to make sure
 * to set {@link #valueContainer} and {@link #feature} with the data and feature that the text view normally represents.
 * 
 * @author mschoettle
 */
public class ValueSpecificationHandler extends TextViewHandler implements IValueSpecificationHandler {
    
    /**
     * The container (data) the text view normally represents.
     */
    protected EObject valueContainer;
    
    /**
     * The feature the text view normally represents.
     */
    protected EStructuralFeature feature;
    
    @Override
    public boolean processTapEvent(TapEvent tapEvent) {
        if (tapEvent.isDoubleTap()) {
            TextView target = (TextView) tapEvent.getTarget();
            
            valueContainer = target.getData();
            feature = target.getFeature();
            
            handleChangeValue(target);
        }
        
        return true;
    }
    
    /**
     * Handles the change of a literal specification value.
     * A boolean value is directly toggled, whereas for all others a keyboard is displayed.
     * 
     * @param textView the text view responsible for displaying the value
     */
    protected void handleChangeValue(TextView textView) {
        EObject realData = (EObject) valueContainer.eGet(feature);
        EStructuralFeature actualFeature = getValueFeature(realData);
        
        // A boolean value can just be toggled.
        if (realData.eClass() == RamPackage.Literals.LITERAL_BOOLEAN) {
            Boolean currentValue = (Boolean) realData.eGet(actualFeature);
            currentValue = !currentValue;
            EMFEditUtil.getPropertyDescriptor(realData, RamPackage.Literals.LITERAL_BOOLEAN__VALUE).setPropertyValue(
                    realData, currentValue);
        } else {
            textView.showKeyboard();
        }
    }
    
    @Override
    public void keyboardCancelled(TextView textView) {
        resetTextView(textView);
        
        super.keyboardCancelled(textView);
    }
    
    @Override
    public void keyboardOpened(TextView textView) {
        EObject realData = (EObject) valueContainer.eGet(feature);
        EStructuralFeature actualFeature = getValueFeature(realData);
        
        textView.setData(realData);
        textView.setFeature(actualFeature, false);
        
        super.keyboardOpened(textView);
    }
    
    @Override
    public boolean shouldDismissKeyboard(TextView textView) {
        EDataType dataType = (EDataType) textView.getFeature().getEType();
        
        try {
            // This will validate the given string.
            Object actualValue = EcoreUtil.createFromString(dataType, textView.getText());
            setValue(textView.getData(), textView.getFeature(), actualValue);
            
            resetTextView(textView);
            
            return true;
        } catch (NumberFormatException e) {
            LoggerUtils.info("No valid number supplied.");
        } catch (IllegalArgumentException e) {
            LoggerUtils.info("No valid argument supplied");
        }
        
        return false;
    }
    
    /**
     * Resets the text views data and feature to the intended one.
     * 
     * @param textView the {@link TextView} to reset
     */
    private void resetTextView(TextView textView) {
        textView.setData(valueContainer);
        textView.setFeature(feature, false);
    }
    
    /**
     * Returns the feature that contains the value of the specific value specification class.
     * 
     * @param data the specific value specification object
     * @return the feature for the value of the value specification
     */
    @SuppressWarnings("static-method")
    private EStructuralFeature getValueFeature(EObject data) {
        return data.eClass().getEAllStructuralFeatures().get(0);
    }
    
}
