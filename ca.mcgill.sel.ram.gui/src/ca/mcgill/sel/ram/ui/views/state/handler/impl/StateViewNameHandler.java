package ca.mcgill.sel.ram.ui.views.state.handler.impl;

import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EReference;
import org.mt4j.input.inputProcessors.componentProcessors.tapAndHoldProcessor.TapAndHoldEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;

import ca.mcgill.sel.ram.Classifier;
import ca.mcgill.sel.ram.StateView;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamSelectorComponent;
import ca.mcgill.sel.ram.ui.components.listeners.AbstractDefaultRamSelectorListener;
import ca.mcgill.sel.ram.ui.utils.MetamodelRegex;
import ca.mcgill.sel.ram.ui.views.SelectorView;
import ca.mcgill.sel.ram.ui.views.TextView;
import ca.mcgill.sel.ram.ui.views.handler.impl.ValidatingTextViewHandler;
import ca.mcgill.sel.ram.ui.views.state.StateViewView;
import ca.mcgill.sel.ram.ui.views.structural.handler.IClassNameHandler;

/**
 * The default handler for a {@link TextView} representing the name of a {@link ca.mcgill.sel.ram.Class}.
 * The name gets validated in order to only allow valid names.
 * 
 * @author mschoettle
 * @author oalam
 */
public class StateViewNameHandler extends ValidatingTextViewHandler implements IClassNameHandler {
    
    /**
     * Creates a new instance of {@link StateViewNameHandler}.
     */
    public StateViewNameHandler() {
        super(MetamodelRegex.REGEX_CLASS_NAME);
    }
    
    @Override
    public boolean processTapAndHoldEvent(TapAndHoldEvent tapAndHoldEvent) {
        
        return true;
    }
    
    @Override
    public boolean processTapEvent(TapEvent tapEvent) {
        if (tapEvent.isDoubleTap()) {
            
            final TextView target = (TextView) tapEvent.getTarget();
            final StateViewView stateViewView = (StateViewView) target.getParent().getParent().getParent();
            
            if (target.getFeature() instanceof EReference || target.getFeature().getEType() instanceof EEnum) {
                RamSelectorComponent<Object> selector = new SelectorView(target.getData(), target.getFeature());
                
                RamApp.getActiveAspectScene().addComponent(selector, tapEvent.getLocationOnScreen());
                
                selector.registerListener(new AbstractDefaultRamSelectorListener<Object>() {
                    @Override
                    public void elementSelected(RamSelectorComponent<Object> selector, Object element) {
                        String text = ((Classifier) element).getName();
                        if (!isNotOnlyClassStateView(stateViewView, text)) {
                            setValue(target.getData(), target.getFeature(), element);
                            stateViewView.setSpecifies((Classifier) element);
                        }
                    }
                });
            } else {
                target.showKeyboard();
            }
            
            return true;
        }
        
        return true;
    }
    
    /**
     * Checks if the first letter of {@link StateViewNameHandler} is capital.
     */
    @Override
    public boolean shouldDismissKeyboard(TextView textView) {
        
        String text = textView.getText();
        if (text.trim().length() != 0) {
            // Put the first letter to upperCase
            text = Character.toUpperCase(text.charAt(0)) + text.substring(1);
            textView.setText(text);
        }
        StateViewView stateViewView = (StateViewView) textView.getParent().getParent().getParent();
        
        // The name should be a class Name
        // Should be the only state view for the class
        Classifier clazz = getClassifier(stateViewView, text);
        if (clazz == null || isNotOnlyClassStateView(stateViewView, text)) {
            return false;
        }
        
        stateViewView.setSpecifies(clazz);
        return super.shouldDismissKeyboard(textView);
    }
    
    @SuppressWarnings("static-method")
    private boolean isNotOnlyClassStateView(StateViewView stateViewView, String text) {
        boolean isNotOnlyClassStateView = false;
        
        for (StateView stateView : stateViewView.getAspect().getStateViews()) {
            if (stateView.getSpecifies() != null && stateView.getSpecifies().getName().equals(text)) {
                isNotOnlyClassStateView = true;
                break;
            }
        }
        
        return isNotOnlyClassStateView;
    }
    
    @SuppressWarnings("static-method")
    private Classifier getClassifier(StateViewView stateViewView, String text) {
        Classifier clazz = null;
        
        for (Classifier classifier : stateViewView.getAspect().getStructuralView().getClasses()) {
            if (text.equals(classifier.getName())) {
                clazz = classifier;
                
                break;
            }
        }
        return clazz;
    }
}
