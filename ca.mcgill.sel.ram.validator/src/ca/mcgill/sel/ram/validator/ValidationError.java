package ca.mcgill.sel.ram.validator;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import ca.mcgill.sel.ram.validator.ValidationRuleErrorDescription.ValidationErrorType;
import ca.mcgill.sel.ram.validator.quickfixes.Quickfix;

/**
 * Contains all the information about a validation error on a EObject target.
 * @author lmartellotto
 */
public class ValidationError {

    
    private String message;
    private ValidationErrorType severity;
    private String name;
    private EObject target;
    private List<Quickfix> quickfixes;
    
    /**
     * Constructor.
     * @param n The name of the error.
     * @param mess The error message.
     * @param sev The severity of the error.
     * @param tar The EObject target.
     * @param quickf The list of quickfixes.
     */
    public ValidationError(String n, String mess, 
            ValidationErrorType sev, EObject tar, List<Quickfix> quickf) {
        name = n;
        message = mess;
        severity = sev;
        target = tar;
        quickfixes = quickf;
    }

    /**
     * Gets the error message.
     * @return The message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets the severity of the error.
     * @return The severity.
     */
    public ValidationErrorType getSeverity() {
        return severity;
    }

    /**
     * Gets the EObject target.
     * @return The target.
     */
    public EObject getTarget() {
        return target;
    }
    
    /**
     * Gets the error name.
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the list of quickfixes.
     * @return the quickfixes
     */
    public List<Quickfix> getQuickfixes() {
        return quickfixes;
    }

    @Override
    public String toString() {
        String str = String.format("%s %s : %s [%s]",
                severity.toString(), name, message, target);
        return str;
    }
    
    @Override
    public int hashCode() {
        // CHECKSTYLE:IGNORE MagicNumber: Multipliers for hash code
        return name.hashCode() * 2 + target.hashCode() * 3;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ValidationError)) {
            return false;
        }
        ValidationError v = (ValidationError) obj;
        return name.equalsIgnoreCase(v.getName()) 
                && v.getTarget().equals(target) 
                && message.equals(v.getMessage());
    }
    
}
