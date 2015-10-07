package ca.mcgill.sel.ram.validator;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.values.Value;

import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.validator.quickfixes.Quickfix;

/**
 * Applies OCL constraints on each object of the Aspect instance and saves the errors for each one.
 * Builds a main map of errors (where the key is the EObject in error)
 * and two others maps for newly added and deleted errors compared to the previous validation.
 * Collects the evaluation errors during validation (due to wrong reflexivity for quickfixes for example)
 * 
 * @author lmartellotto
 *
 */
public class Validator {

    private static final String QUICKFIX_PATH = "ca.mcgill.sel.ram.validator.quickfixes.";

    private Map<EClassifier, List<ValidationRule>> rules;
    private  List<ValidatorListener> listeners;

    private Map<EObject, List<ValidationError>> previousErrors;
    private Map<EObject, List<ValidationError>> errors;
    private Map<EObject, List<ValidationError>> errorsAdded;
    private Map<EObject, List<ValidationError>> errorsDismissed;

    private List<ValidatorEvaluationError> evaluationErrors;

    private OCL ocl;

    /**
     * Initializes all the maps and lists, 
     * and retrieves the OCL object from the {@link ValidationRulesParser}.
     */
    public Validator() {
        previousErrors = new HashMap<EObject, List<ValidationError>>();
        errors = new HashMap<EObject, List<ValidationError>>();
        errorsAdded = new HashMap<EObject, List<ValidationError>>();
        errorsDismissed = new HashMap<EObject, List<ValidationError>>();

        listeners = new ArrayList<ValidatorListener>();
        evaluationErrors = new ArrayList<ValidatorEvaluationError>();
        ocl = ValidationRulesParser.getOCL();
    }

    /**
     * Returns if the validator is initialized with the parsed rules of OCL files.
     * @return True if it is, false otherwise.
     */
    public boolean isInitialized() {
    
        // Checks if the rules parsing has be done.
        if (rules == null) {
            if (ValidationRulesParser.getInstance() == null) {
                return false;
            }
            rules = ValidationRulesParser.getInstance().getRules();
        }
    
        return true;
    }

    /**
     * Checks the model in applying OCL constraints on each object of the model.
     * @param aspect The aspect to check
     * @return if the checking has be done.
     */
    public boolean check(Aspect aspect) {

        // Checks if the rules parsing has be done.
        if (!isInitialized() || aspect == null) {
            return false;
        }

        // Clear the lists and keep the previous errors
        previousErrors.clear();
        previousErrors.putAll(errors);
        errors.clear();
        errorsAdded.clear();
        errorsDismissed.clear();
        evaluationErrors.clear();

        // Checks the aspect object
        checkObject(aspect);

        // Checks all children (recursively) 
        TreeIterator<EObject> iter = aspect.eAllContents();
        while (iter.hasNext()) {
            EObject next = iter.next();
            checkObject(next);
        }    

        sortErrors();
        alertListeners();
        return true;
    }

    /**
     * Checks the object for all constraints of its type and its super types.
     * @param obj The object to check
     */
    private void checkObject(EObject obj) {
        // Get all super types and the current type of the EObject
        List<EClass> types = new ArrayList<EClass>(obj.eClass().getEAllSuperTypes());
        types.add(obj.eClass());

        for (EClass cl : types) {

            if (rules.containsKey(cl)) {

                for (ValidationRule r : rules.get(cl)) {
                    try {
                        // Evaluates the query and turns the result into a boolean expression
                        Object resultObj = ocl.evaluate(obj, r.getQuery());
                        boolean result = (Boolean) resultObj;

                        // If the result is false, we have to add a new error in the map
                        if (!result) { 

                            if (!errors.containsKey(obj)) {
                                errors.put(obj, new ArrayList<ValidationError>());
                            }

                            List<Quickfix> quickfixes = getQuickfixesFromExpression(obj, r);

                            // Message attributes for the error string.
                            Value attrValue = (Value) ocl.evaluate(obj, r.getError().getMessageAttributes());
                            @SuppressWarnings("unchecked")
                            ArrayList<String> attributes = (ArrayList<String>) attrValue.asObject();
                            ValidationError error = new ValidationError(
                                    r.getName(),
                                    String.format(r.getError().getMessage().toString(), attributes.toArray()),
                                    r.getError().getSeverity(), 
                                    obj,
                                    quickfixes);

                            errors.get(obj).add(error); 

                        }
                    // CHECKSTYLE:IGNORE IllegalCatch: A Fix to avoid to let pass exceptions 
                    } catch (Exception ex) {
                        evaluationErrors.add(new ValidatorEvaluationError(obj, r, ex));
                    }

                }
            }
        }

    }

    /**
     * Instantiates the list of {@link Quickfix} from the list of strings, thanks to Java reflexivity.
     * @param obj The object concerned by these quickfixes
     * @param r The rule containing the quickfixes string
     * @return the list of {@link Quickfix}
     */
    private List<Quickfix> getQuickfixesFromExpression(EObject obj, ValidationRule r) {

        ExpressionInOCL expr = r.getQuickfixes();

        // Instantiation of the quickfixes.
        List<Quickfix> quickfixes = new ArrayList<Quickfix>();
        if (expr == null) {
            return quickfixes;
        }
        
        Value qfsValue = (Value) ocl.evaluate(obj, expr);

        @SuppressWarnings("unchecked")
        ArrayList<String> qfs = (ArrayList<String>) qfsValue.asObject();
        for (String qf : qfs) {
            try {
                
                // Retrieves the name of the quickfix class and the optionnal list of parameters from the string
                String nameClass;
                String[] params = null;
                int indexOfParenthesis = qf.indexOf("(");

                if (qf.contains("(")) {
                    nameClass = qf.substring(0, indexOfParenthesis);
                    if (qf.endsWith(")")) {
                        String paramsStr = qf.substring(indexOfParenthesis + 1,
                                qf.length() - 1);
                        params = paramsStr.split(",");
                    }

                } else {
                    nameClass = qf;
                }

                /* USE OF JAVA REFLEXIVITY */
                
                // CLASS NAME
                // Find the quickfix class in the path folder
                Class<?> clQf = Class.forName(QUICKFIX_PATH + nameClass);

                // PARAMETERS
                ArrayList<Class<?>> typesToGetCstr = new ArrayList<Class<?>>();
                ArrayList<Object> objectsToInitCstr = new ArrayList<Object>();

                // The first parameter is always the EObject target. See Quickfix interface.
                typesToGetCstr.add(EObject.class);
                objectsToInitCstr.add(obj);

                // After, other String parameters are added depends from the OCL file
                if (params != null) {
                    for (int i = 0; i < params.length; i++) {
                        typesToGetCstr.add(String.class);
                        objectsToInitCstr.add(params[i]);
                    }
                }

                Constructor<?> ct = clQf.getConstructor(typesToGetCstr.toArray(new Class[0]));
                Object oQf = ct.newInstance(objectsToInitCstr.toArray());

                quickfixes.add((Quickfix) oQf);

            } catch (ClassNotFoundException e) {
                evaluationErrors.add(new ValidatorEvaluationError(obj, r, e));
            } catch (NoSuchMethodException e) {
                evaluationErrors.add(new ValidatorEvaluationError(obj, r, e));
            } catch (IllegalArgumentException e) {
                evaluationErrors.add(new ValidatorEvaluationError(obj, r, e));
            } catch (InstantiationException e) {
                evaluationErrors.add(new ValidatorEvaluationError(obj, r, e));
            } catch (IllegalAccessException e) {
                evaluationErrors.add(new ValidatorEvaluationError(obj, r, e));
            } catch (InvocationTargetException e) {
                evaluationErrors.add(new ValidatorEvaluationError(obj, r, e));
            }

        }

        return quickfixes;
    }

    /**
     * Gets the list of current errors.
     * @return The errors
     */
    public Map<EObject, List<ValidationError>> getErrors() {
        return errors;
    }

    /**
     * Gets the list of current added errors.
     * @return The errors
     */
    public Map<EObject, List<ValidationError>> getAddedErrors() {
        return errorsAdded;
    }

    /**
     * Gets the list of current dismissed errors.
     * @return The errors.
     */
    public Map<EObject, List<ValidationError>> getDismissedErrors() {
        return errorsDismissed;
    }

    /**
     * Gets the evaluation errors.
     * @return the evaluationErrors
     */
    public List<ValidatorEvaluationError> getEvaluationErrors() {
        return evaluationErrors;
    }

    /**
     * Alerts all listeners about errors.
     */
    private void alertListeners() {
        for (ValidatorListener list : listeners) {
            list.updateValidationErrors(errors, errorsAdded, errorsDismissed);
        }
    }

    /**
     * Adds a listener to this Validator instance.
     * @param listener The added listener
     */
    public void addListener(ValidatorListener listener) {
        if (listener != null) {
            listeners.add(listener);
        }
    }

    /**
     * Adds a listener to this Validator instance.
     * @param listener The added listener
     */
    public void removeListener(ValidatorListener listener) {
        if (listener != null) {
            listeners.remove(listener);
        }
    }

    /**
     * Sorts errors depending on the severity. 
     * Retrieves the errors which are added and dismissed.
     */
    private void sortErrors() {

        // To retrieve errors which are added
        for (EObject obj : errors.keySet()) {

            // Object is newly in error, all errors added
            if (!previousErrors.containsKey(obj)) {
                errorsAdded.put(obj, new ArrayList<ValidationError>());
                errorsAdded.get(obj).addAll(errors.get(obj));

                // Object already in error previously, need to sort the possible new errors on it
            } else {
                for (ValidationError e : errors.get(obj)) {
                    if (!previousErrors.get(obj).contains(e)) {
                        if (!errorsAdded.containsKey(obj)) {
                            errorsAdded.put(obj, new ArrayList<ValidationError>());
                        }
                        errorsAdded.get(obj).add(e);
                    }
                }
            }
        }

        // To retrieve errors which are dismissed
        for (EObject obj : previousErrors.keySet()) {

            // Object is not anymore in error, all errors dismissed
            if (!errors.containsKey(obj)) {
                errorsDismissed.put(obj, new ArrayList<ValidationError>());
                errorsDismissed.get(obj).addAll(previousErrors.get(obj));

                // Object already in error previously, need to sort the possible dismissed errors on it
            } else {
                for (ValidationError e : previousErrors.get(obj)) {
                    if (!errors.get(obj).contains(e)) {
                        if (!errorsDismissed.containsKey(obj)) {
                            errorsDismissed.put(obj, new ArrayList<ValidationError>());
                        }
                        errorsDismissed.get(obj).add(e);
                    }
                }
            }
        }

        // SORTING depend on the severity
        for (EObject obj : errorsDismissed.keySet()) {
            Collections.sort(errorsDismissed.get(obj), new ValidationErrorComparator());
        }

        for (EObject obj : errorsAdded.keySet()) {
            Collections.sort(errorsAdded.get(obj), new ValidationErrorComparator());
        }

        for (EObject obj : errors.keySet()) {
            Collections.sort(errors.get(obj), new ValidationErrorComparator());
        }
    }

    /**
     * To create and collect errors during the validation for rules which 
     * throw errors (evaluation or parsing error).
     * Contains the object and the rule for what the exception occurred
     * when the {@Validator} tried to evaluate it.
     */
    public class ValidatorEvaluationError {
        private EObject obj;
        private ValidationRule rule;
        private Exception exc;

        /**
         * Basic constructor.
         * @param o the object.
         * @param r the rule.
         * @param e the exception.
         */
        public ValidatorEvaluationError(EObject o, ValidationRule r, Exception e) {
            obj = o;
            rule = r;
            exc = e;
        }

        /**
         * Gets the object.
         * @return the object
         */
        public EObject getObj() {
            return obj;
        }

        /**
         * Gets the rule.
         * @return the rule
         */
        public ValidationRule getRule() {
            return rule;
        }

        /**
         * Gets the exception.
         * @return the exception.
         */
        public Exception getExc() {
            return exc;
        }

        @Override
        public String toString() {
            String str = String.format("%s : %s (%s)\n%s", 
                    obj, rule.getName(), rule.getQuery(), exc.getMessage());
            return str;
        }

    }

    /**
     * Compares two {@ValidationError} depending on their severity.
     * Errors with the bigger severity is prior.
     */
    private class ValidationErrorComparator implements Comparator<ValidationError> {
        @Override
        public int compare(ValidationError a, ValidationError b) {
            return b.getSeverity().getIndex() - a.getSeverity().getIndex();
        }
    }

}
