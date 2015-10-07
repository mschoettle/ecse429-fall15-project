package ca.mcgill.sel.ram.validator;

import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.OCLExpression;

/**
 * Contains all information about an error (type and message)
 * These information could be evaluated on EObject and in OCL context
 *      to be able to create {@link ValidationError} instances.
 *      
 * @author lmartellotto
 */
public class ValidationRuleErrorDescription {

    /**
     * Error types enumeration.
     */
    public enum ValidationErrorType {
        /** Error. */
        ERROR(2),
        /** Warning. */
        WARNING(1), 
        /** Information. */
        INFO(0);
        
        private int index;

        /**
         * Constructor.
         * @param ind
         *      The integer representing the severity.
         */
        ValidationErrorType(int ind) {
            index = ind;
        }
        
        /**
         * Gets the severity.
         * @return
         *      The integer representing the severity.
         */
        public int getIndex() {
            return index;
        }

        /**
         * Returns the ValidationErrorType matching the index of severity.
         * @param sev
         *      The integer representing the severity.
         * @return
         *      The ValidationErrorType matching the index of severity, 
         *      or null if it does not match.
         */
        public static ValidationErrorType valueOf(int sev) {
            for (ValidationErrorType vt : ValidationErrorType.values()) {
                if (vt.getIndex() == sev) {
                    return vt;
                }
            }
            return null;
        }

    }

    private ValidationErrorType severity;
    private OCLExpression message;
    private ExpressionInOCL messageAttributes;

    /**
     * Constructor.
     * @param t The type of the error.
     * @param mess The error message.
     * @param messAttr The attributes to format the error message.
     */
    public ValidationRuleErrorDescription(ValidationErrorType t, OCLExpression mess, ExpressionInOCL messAttr) {
        severity = t;
        message = mess;
        messageAttributes = messAttr;
    }

    /**
     * Gets the error message.
     * @return The message.
     */
    public OCLExpression getMessage() {
        return message;
    }

    /**
     * Gets the attributes of the error message.
     * @return The messageAttributes
     */
    public ExpressionInOCL getMessageAttributes() {
        return messageAttributes;
    }

    /**
     * Gets the type of the error.
     * @return The type.
     */
    public ValidationErrorType getSeverity() {
        return severity;
    }

    @Override
    public String toString() {
        String str = String.format("(%s) %s [%s]",
                severity, message, messageAttributes);
        return str;
    }

}
