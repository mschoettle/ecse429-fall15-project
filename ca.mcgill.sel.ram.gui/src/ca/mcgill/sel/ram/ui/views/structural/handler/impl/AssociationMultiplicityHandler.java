package ca.mcgill.sel.ram.ui.views.structural.handler.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;

import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.AssociationEnd;
import ca.mcgill.sel.ram.controller.AssociationController;
import ca.mcgill.sel.ram.controller.ControllerFactory;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.utils.MetamodelRegex;
import ca.mcgill.sel.ram.ui.views.TextView;
import ca.mcgill.sel.ram.ui.views.handler.impl.TextViewHandler;

/**
 * Handler for the multiplicity of an association. Handles the validation of multiplicity input and setting of
 * multiplicity to the model in the form of upper and lower bound.
 * 
 * @author eyildirim
 * @author mschoettle
 */
public class AssociationMultiplicityHandler extends TextViewHandler {
    
    /**
     * The index of the upper bound in the multiplicity string.
     */
    private static final int UPPER_BOUND_INDEX = 3;
    
    @Override
    public void keyboardOpened(TextView textView) {
        // do nothing
    }
    
    @Override
    public boolean processTapEvent(TapEvent tapEvent) {
        if (tapEvent.isDoubleTap()) {
            TextView target = (TextView) tapEvent.getTarget();
            target.showKeyboard();
            
            return true;
        }
        
        return true;
    }
    
    /**
     * Set the multiplicity of the association end.
     * @param multiplicityString the string modified by user
     * @param associationEnd the association end being modified
     * @return true if the multiplicity string is valid
     */
    private static boolean setMultiplicity(String multiplicityString, AssociationEnd associationEnd) {
        // For example: 0..* --> group(0) = 0..*, group(1) = 0, group(2)= .. ,group(3) = *
        Matcher matcher = Pattern.compile(MetamodelRegex.REGEX_GROUP_MULTIPLICITY).matcher(multiplicityString);
        if (!matcher.matches()) {
            return false;
        }
        
        // it may be null because allowed inputs can be : number OR number..number OR number..*
        String upperBoundString = matcher.group(UPPER_BOUND_INDEX);
        int upperBound;
        int lowerBound = Integer.parseInt(matcher.group(1));
        AssociationController controller = ControllerFactory.INSTANCE.getAssociationController();
        Aspect aspect = RamApp.getActiveAspectScene().getAspect();
        
        // upper and lower bounds have same value
        if (upperBoundString == null) {
            controller.setMultiplicity(associationEnd, aspect, lowerBound, lowerBound);
        } else {
            if ("*".equals(upperBoundString)) {
                upperBound = -1;
            } else {
                upperBound = Integer.parseInt(upperBoundString);
                if (upperBound < lowerBound) {
                    return false;
                }
            }
            
            controller.setMultiplicity(associationEnd, aspect, lowerBound, upperBound);
        }
        return true;
    }
    
    @Override
    public boolean shouldDismissKeyboard(TextView textView) {
        String text = textView.getText();
        
        if (!text.matches(MetamodelRegex.REGEX_MULTIPLICITY)) {
            // TODO: show error message?
            return false;
        }
        
        return setMultiplicity(text, (AssociationEnd) textView.getData());
    }
}
