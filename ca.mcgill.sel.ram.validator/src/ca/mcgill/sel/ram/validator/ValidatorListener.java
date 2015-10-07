package ca.mcgill.sel.ram.validator;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

/**
 * Interface for objects which want to be warned when the {@link Validator}
 *  has terminated the validation of the model, in order to receive the errors.
 *  
 * @author lmartellotto
 *
 */
public interface ValidatorListener {
    
    /**
     * Reports the errors collected during the validation.
     * @param errors Map of the errors for each EObject.
     * @param errorsAdded Map of the new errors for each EObject
     * @param errorsDismissed Map of the deleted errors for each EObject
     */
    void updateValidationErrors(Map<EObject, List<ValidationError>> errors,
            Map<EObject, List<ValidationError>> errorsAdded,
            Map<EObject, List<ValidationError>> errorsDismissed);

}
