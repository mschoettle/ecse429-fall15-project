package ca.mcgill.sel.ram.ui.utils.validation;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import ca.mcgill.sel.ram.ui.utils.validation.ValidationManager.AspectValidationThreadState;
import ca.mcgill.sel.ram.validator.ValidationError;

/**
 * Interface for objects which want to be warned when the {@link ValidationManager} has retrieved new validation errors.
 * @author lmartellotto
 *
 */
public interface ValidationManagerListener {
    
    /**
     * Reports the errors collected during the validation.
     * @param errors Map of the objects with their errors.
     * @param errorsAdded Map of the news errors, comparing to the previous validation.
     * @param errorsDismissed Map of the deleted errors, comparing to the previous validation.
     */
    void updateValidationErrors(Map<EObject, List<ValidationError>> errors,
            Map<EObject, List<ValidationError>> errorsAdded,
            Map<EObject, List<ValidationError>> errorsDismissed);
    
    /**
     * Gets the state of the validation. See {@link AspectValidationThreadState}.
     * @param state the state.
     */
    void validationState(AspectValidationThreadState state);
    
}