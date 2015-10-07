package ca.mcgill.sel.ram.ui.views.state.handler.impl;

import java.util.Collection;

import org.eclipse.emf.ecore.EAttribute;
import org.mt4j.input.inputProcessors.componentProcessors.tapAndHoldProcessor.TapAndHoldEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;

import ca.mcgill.sel.ram.Classifier;
import ca.mcgill.sel.ram.Constraint;
import ca.mcgill.sel.ram.LiteralString;
import ca.mcgill.sel.ram.Operation;
import ca.mcgill.sel.ram.RamFactory;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.StateView;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamSelectorComponent;
import ca.mcgill.sel.ram.ui.components.listeners.AbstractDefaultRamSelectorListener;
import ca.mcgill.sel.ram.ui.views.SelectorView;
import ca.mcgill.sel.ram.ui.views.TextView;
import ca.mcgill.sel.ram.ui.views.handler.impl.ValidatingTextViewHandler;
import ca.mcgill.sel.ram.ui.views.state.TransitionView;
import ca.mcgill.sel.ram.ui.views.structural.handler.IClassNameHandler;

/**
 * The default handler for a {@link TextView} representing the name of a {@link ca.mcgill.sel.ram.Class}.
 * The name gets validated in order to only allow valid names.
 * 
 * @author mschoettle
 * @author oalam
 */
public class TransitionNameHandler extends ValidatingTextViewHandler implements IClassNameHandler {
    
    /**
     * Creates a new instance of {@link TransitionNameHandler}.
     */
    public TransitionNameHandler() {
        super(null);
    }
    
    @Override
    public boolean processTapAndHoldEvent(TapAndHoldEvent tapAndHoldEvent) {
        if (tapAndHoldEvent.isHoldComplete()) {
            final TextView target = (TextView) tapAndHoldEvent.getTarget();
            target.showKeyboard();
            
        }
        return true;
    }
    
    @Override
    public boolean processTapEvent(TapEvent tapEvent) {
        if (tapEvent.isDoubleTap()) {
            
            final TextView target = (TextView) tapEvent.getTarget();
            final TransitionView transitionView = (TransitionView) target.getParent();
            
            if (target.getFeature() instanceof EAttribute) {
                // || target.getFeature().getEType() instanceof EEnum) {
                RamSelectorComponent<Object> selector = new SelectorView(target.getData(),
                        RamPackage.Literals.TRANSITION__SIGNATURE);
                
                RamApp.getActiveAspectScene().addComponent(selector, tapEvent.getLocationOnScreen());
                
                selector.registerListener(new AbstractDefaultRamSelectorListener<Object>() {
                    @Override
                    public void elementSelected(RamSelectorComponent<Object> selector, Object element) {
                        setValue(target.getData(), target.getFeature(), element);
                        transitionView.setSignature((Operation) element, transitionView.getTransition().getGuard());
                    }
                });
            } else {
                target.showKeyboard();
            }
            
            return true;
        }
        
        return true;
    }
    
    @Override
    public boolean shouldDismissKeyboard(TextView textView) {
        
        String text = textView.getText();
        
        int posSlash = text.indexOf("/");
        int posBracket = text.indexOf("[");
        String operationName = "";
        String constraintStr = "";
        if (posSlash != -1) {
            // if First Time setting a guard look for "/"
            operationName = text.substring(0, posSlash);
            constraintStr = text.substring(posSlash + 1, text.length());
        } else if (posBracket != -1) {
            operationName = text.substring(0, posBracket);
            constraintStr = text.substring(posBracket + 1, text.length() - 1);
        } else {
            operationName = text;
        }
        
        TransitionView transitionView = (TransitionView) textView.getParent();
        // Validate operation name
        Operation op = null;
        if (operationName.length() != 0) {
            Classifier clazz = ((StateView) transitionView.getTransition().eContainer().eContainer()).getSpecifies();
            Collection<Operation> operations = clazz.getOperations();
            
            for (Operation operation : operations) {
                if (operation.getName().equals(operationName)) {
                    op = operation;
                    break;
                }
            }
            if (op == null) {
                return false;
            }
        }
        
        Constraint guard = null;
        if (constraintStr.length() != 0) {
            guard = RamFactory.eINSTANCE.createConstraint();
            LiteralString specification = RamFactory.eINSTANCE.createLiteralString();
            specification.setValue(constraintStr);
            guard.setSpecification(specification);
            
        }
        
        transitionView.setSignature(op, guard);
        return true;
    }
    
}
