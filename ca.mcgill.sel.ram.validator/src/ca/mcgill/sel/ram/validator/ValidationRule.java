package ca.mcgill.sel.ram.validator;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.ocl.pivot.ExpressionInOCL;


/**
 * Rule object is identified by its name, contains a OCL constraint and its context.
 * 
 * It contains also : a list of quickfixes and error information.
 * These two information will be used for initialize future {@link ValidationError}
 *  with the EObject target when the rule is not satisfied.
 *  
 * @author lmartellotto
 *
 */
public class ValidationRule {
    
    private String name;
    private EClassifier context;
    private ExpressionInOCL query;
    private ValidationRuleErrorDescription error;
    private ExpressionInOCL quickfixes;

    /**
     * Constructor.
     * @param name The name of the rule.
     * @param context The context (EClass) wherein the constraint has to be evaluated.
     * @param query The OCL Query.
     * @param error The error message which is thrown when the rule is not satisfied. 
     * @param quickfixes The expression for the list of quickfixes.
     */
    public ValidationRule(String name, EClassifier context, ExpressionInOCL query,
            ValidationRuleErrorDescription error, ExpressionInOCL quickfixes) {
        this.name = name;
        this.context = context;
        this.query = query;
        this.error = error;
        this.quickfixes = quickfixes;
    }
    
    /**
     * Gets the error object.
     * @return The error object.
     */
    public ValidationRuleErrorDescription getError() {
        return error;
    }

    /**
     * Gets the name.
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the query.
     * @return The query.
     */
    public ExpressionInOCL getQuery() {
        return query;
    }

    /**
     * Gets the quickfixes.
     * @return the quickfixes
     */
    public ExpressionInOCL getQuickfixes() {
        return quickfixes;
    }

    @Override
    public String toString() {
        String str = String.format("%s %s: %s (%s)",
                context.getInstanceTypeName(), name, query, error);
        return str;
    }
    
}
