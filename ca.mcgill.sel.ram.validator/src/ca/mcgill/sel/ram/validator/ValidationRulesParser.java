package ca.mcgill.sel.ram.validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.ocl.pivot.CollectionLiteralExp;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.TupleLiteralPart;
import org.eclipse.ocl.pivot.internal.TupleLiteralExpImpl;
import org.eclipse.ocl.pivot.internal.prettyprint.PrettyPrinter;
import org.eclipse.ocl.pivot.internal.resource.StandaloneProjectMap;
import org.eclipse.ocl.pivot.internal.utilities.PivotEnvironmentFactory;
import org.eclipse.ocl.pivot.resource.ProjectManager;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.OCLHelper;
import org.eclipse.ocl.pivot.utilities.ParserException;

import ca.mcgill.sel.commons.ResourceUtil;
import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.validator.ValidationRuleErrorDescription.ValidationErrorType;

/**
 * Imports the constraints for the aspect model from OCL files.
 * 
 * Each rule is parsed and checked for all these components (query, message, etc.).
 * 
 * The enum {@link ValidationMandatoryLiterals} contains all the mandatory literals
 * that each parsed constraint has to define (query, message, message_attributes and severity).
 * Moreover, the enum {@link ValidationOptionalLiterals} contain optional literals, like 'quickfixes'.
 * 
 * @author lmartellotto
 *
 */
public final class ValidationRulesParser {

    private static final String OCL_FOLDER = "constraints/";

    /**
     * Mandatory literals used in OCL files (tuple objects) and used to parse them.
     */
    public enum ValidationMandatoryLiterals {
        /** OCL query. */
        query,
        /** Error message. */
        message,
        /** Attributes for error message. */
        message_attributes,
        /** Severity of error. */
        severity
    }
    
    /**
     * Optional literals used in OCL files (tuple objects) and used to parse them.
     */
    public enum ValidationOptionalLiterals {
        /** List of Quickfixes. */
        quickfixes
    }

    private static Map<EClassifier, List<ValidationRule>> rules;
    private static Map<String, List<String>> parsingErrors;
    private static OCL ocl;
    
    private static boolean inParsing;

    private static ValidationRulesParser instance;

    /**
     * Initialize the map of rules.
     * Launches the import : imports, parses and checks the OCL constraints for each EObject.
     */
    private ValidationRulesParser() {
        ocl = initializeOCLObject();
        importConstraints(); 
    }

    /**
     * Try to get the unique instance. 
     * The method can return null if the parsing is done at the same time in other thread.
     * The rules parsing is launched in an other thread during the launch of TouchCORE, 
     *      so, the main thread has not to be allowed to access the instance before the end of parsing.
     * The rules parsing is launched as soon as a first call to getInstance() is done.
     * @return The instance, or null if the parsing has not be done.
     */
    public static ValidationRulesParser getInstance() {
        if (inParsing) {
            return null;
        }
        
        if (instance == null) {
            inParsing = true;
            instance = new ValidationRulesParser();
            inParsing = false;
        }
        return instance;
    }
    
    /**
     * Returns the singleton OCL instance.
     * 
     * @return the singleton OCL instance
     */
    public static OCL getOCL() {
        if (ocl == null) {
            ocl = initializeOCLObject();
        }
        
        return ocl;
    }

    /**
     * Initializes the OCL Object for the RAM context.
     * @return The new instance of OCL object.
     */
    private static OCL initializeOCLObject() {
        /* * /
        // Imports and setup to do when we run the validator test outside TouchCORE.
        String oclDelegateURI = OCLDelegateDomain.OCL_DELEGATE_URI_PIVOT;
        EOperation.Internal.InvocationDelegate.Factory.Registry.INSTANCE.put(oclDelegateURI,
                new OCLInvocationDelegateFactory.Global());
        EStructuralFeature.Internal.SettingDelegate.Factory.Registry.INSTANCE.put(oclDelegateURI,
                new OCLSettingDelegateFactory.Global());
        EValidator.ValidationDelegate.Registry.INSTANCE.put(oclDelegateURI,
                new OCLValidationDelegateFactory.Global());
        /*
    
        OCLinEcoreStandaloneSetup.doSetup();
    
        org.eclipse.ocl.examples.pivot.model.OCLstdlib.install();
        org.eclipse.ocl.examples.xtext.oclstdlib.OCLstdlibStandaloneSetup.doSetup();
        org.eclipse.ocl.examples.xtext.oclinecore.OCLinEcoreStandaloneSetup.doSetup();
        org.eclipse.ocl.examples.xtext.completeocl.CompleteOCLStandaloneSetup.doSetup();
        /* */
        /* The official way for standalone setup. Not sure why it is not needed here. */
        /* ResourceSet resourceSet = new ResourceSetImpl();
        org.eclipse.ocl.examples.pivot.OCL.initialize(resourceSet);
        org.eclipse.ocl.examples.pivot.uml.UML2Pivot.initialize(resourceSet);
        org.eclipse.ocl.examples.pivot.model.OCLstdlib.install();
        org.eclipse.ocl.examples.pivot.delegate.OCLDelegateDomain.initialize(resourceSet);
        org.eclipse.ocl.examples.xtext.completeocl.CompleteOCLStandaloneSetup.doSetup();
        org.eclipse.ocl.examples.xtext.oclinecore.OCLinEcoreStandaloneSetup.doSetup();
        org.eclipse.ocl.examples.xtext.oclstdlib.OCLstdlibStandaloneSetup.doSetup();
        StandaloneProjectMap map = 
            org.eclipse.ocl.examples.domain.utilities.StandaloneProjectMap.getAdapter(resourceSet); */
        
        EPackage.Registry registry = EPackage.Registry.INSTANCE;
        
        /**
         * When running standalone and the OCL file does not import the ecore model by nsURI,
         * there is a problem regarding the generated package. See issue #189.
         * The fix for this issue also fixes the problem of running it from within a JAR, see issue #180,
         * that was previously fixed differently. 
         * This way, no knowledge is necessary of the path where the code is run.
         */
        registry.put("platform:/plugin/ca.mcgill.sel.ram/model/RAM.ecore", RamPackage.eINSTANCE);
        registry.put("platform:/resource/ca.mcgill.sel.ram/model/RAM.ecore", RamPackage.eINSTANCE);
        registry.put("platform:/plugin/ca.mcgill.sel.core/model/CORE.ecore", CorePackage.eINSTANCE);
        registry.put("platform:/resource/ca.mcgill.sel.core/model/CORE.ecore", CorePackage.eINSTANCE);

        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.setPackageRegistry(registry);

        ProjectManager projectManager = new StandaloneProjectMap(true);
        projectManager.initializeResourceSet(resourceSet);
        
        EnvironmentFactory environmentFactory = new PivotEnvironmentFactory(projectManager, null);

        OCL oclInstance = environmentFactory.createOCL();

        return oclInstance;
    }

    /**
     * Parses and check validity of constraints from OCL files.
     * @return If true, all constraints are imported. If false, there are parsing errors.
     */
    private boolean importConstraints() {
        rules = new HashMap<EClassifier, List<ValidationRule>>();
        parsingErrors = new HashMap<String, List<String>>();
        
        List<String> files = ResourceUtil.getResourceListing(OCL_FOLDER, ".ocl");
    
        // Reads and parses all the OCL files
        for (String oclFile : files) {
            try {
                URI fileURI = URI.createURI(oclFile);
                Resource asResource = ocl.parse(fileURI);
    
                for (TreeIterator<EObject> tit = asResource.getAllContents(); tit.hasNext();) {
    
                    EObject nextObject = tit.next();
    
                    if (nextObject instanceof Constraint) {
                        Constraint next = (Constraint) nextObject;
    
                        boolean success = true;
                        LinkedList<ValidationMandatoryLiterals> literals = 
                                new LinkedList<ValidationMandatoryLiterals>(
                                        Arrays.asList(ValidationMandatoryLiterals.values()));
                        
                        EClassifier context = RamPackage.eINSTANCE.getEClassifier(next.getContext().getName());
                        
                        String contextString = context.getName();
                        ExpressionInOCL expressionInOCL = ocl.getSpecification(next);
    
                        TupleLiteralExpImpl tuple = (TupleLiteralExpImpl) expressionInOCL.getOwnedBody();
                        
                        ExpressionInOCL query = null;
                        OCLExpression message = null;
                        ExpressionInOCL messageAttributes = null;
                        ValidationErrorType errorType = null;
                        ExpressionInOCL quickfixes = null;
                        
                        OCLHelper helper = ocl.createOCLHelper(context);
    
                        // Gets all the elements from the tuple
                        for (TupleLiteralPart tp : tuple.getOwnedParts()) {
                            String name = tp.getName();
                            if (name.equalsIgnoreCase(ValidationMandatoryLiterals.query.name())) {
                                // Hack: Use of PrettyPrinter to revert the query back to its initial syntax
                                String originalOCLExpression = PrettyPrinter.print(tp.getOwnedInit());
                                query = helper.createQuery(originalOCLExpression);
                                literals.remove(ValidationMandatoryLiterals.query);
    
                            } else if (name.equalsIgnoreCase(ValidationMandatoryLiterals.message.name())) {
                                message = (OCLExpression) tp.getOwnedInit();
                                literals.remove(ValidationMandatoryLiterals.message);
    
                            } else if (name.equalsIgnoreCase(ValidationMandatoryLiterals.message_attributes.name())) {
                                // Hack: Use of PrettyPrinter to revert the query back to its initial syntax
                                String originalOCLExpression = PrettyPrinter.print(tp.getOwnedInit());
                                messageAttributes = helper.createQuery(originalOCLExpression);
                                literals.remove(ValidationMandatoryLiterals.message_attributes);
    
                            } else if (name.equalsIgnoreCase(ValidationMandatoryLiterals.severity.name())) {
                                errorType = checkErrorType((OCLExpression) tp.getOwnedInit());
                                if (errorType == null) {
                                    saveConstraintError(oclFile, contextString, next.getName(), 
                                            ValidationMandatoryLiterals.severity.name(),
                                            "Wrong format '" + tp.getOwnedInit() + "'");   
                                }
                                literals.remove(ValidationMandatoryLiterals.severity);
    
                            } else if (name.equalsIgnoreCase(ValidationOptionalLiterals.quickfixes.name())) {
                                // Hack: Use of PrettyPrinter to revert the query back to its initial syntax
                                String originalOCLExpression = PrettyPrinter.print(tp.getOwnedInit());
                                quickfixes = helper.createQuery(originalOCLExpression);
                            }
                        }
    
                        ///////////// VERIFICATION OF THE RULE
                        
                        // Checks if all the info are initialized.
                        if (literals.size() > 0) {
                            saveConstraintError(oclFile, contextString, next.getName(), 
                                    "Info", "Missing arguments : " + literals.toString());
                            success = false;
                        }
    
                        // Checks if there are a correct number of argument for the message.
                        if (!checkMessage(message, messageAttributes)) {
                            saveConstraintError(oclFile, contextString, next.getName(), 
                                    ValidationMandatoryLiterals.message_attributes.name(),
                                    "Missing arguments to format the error message.");
                            success = false;
                        }
                        
                        // If there are errors, don't save the rule.
                        if (!success) {
                            continue;
                        }
                        
                        //////////////////////
    
                        ValidationRuleErrorDescription error = new ValidationRuleErrorDescription(
                                errorType, message, messageAttributes);
    
                        ValidationRule rule = new ValidationRule(
                                next.getName(), 
                                context, 
                                query, 
                                error,
                                quickfixes);
    
                        if (!rules.containsKey(context)) {
                            rules.put(context, new ArrayList<ValidationRule>());
                        }
                        rules.get(context).add(rule);
    
                    }
                }
    
                // Errors from parsing file
            } catch (IllegalArgumentException e) {
                saveFileError(oclFile, e.getMessage());
            } catch (ParserException e) {
                saveFileError(oclFile, e.getMessage());
            }
        }
    
        if (parsingErrors.size() > 0) {
            for (String c : parsingErrors.keySet()) {
                System.err.println(c);
                System.err.println(parsingErrors.get(c));
                System.err.println();
            }
            return false;
        }
        return true;
    }

    /**
     * Gets the list or parsing errors.
     * @return The parsing errors.
     */
    public Map<String, List<String>> getParsingErrors() {
        return parsingErrors;
    }

    /**
     * Gets the imported rules.
     * @return The map of rules depending on the object.
     */
    public Map<EClassifier, List<ValidationRule>> getRules() {
        return rules;
    }

    /**
     * Checks the parse of the error type 
     *      and checks if the parsing in integer match with the severity range.
     * @param type The OCLExpression representing the error type.
     * @return The error type.
     */
    private ValidationErrorType checkErrorType(OCLExpression type) {
        ValidationErrorType errorType = null;
        try {
            errorType = ValidationErrorType.valueOf(Integer.parseInt(type.toString()));
        } catch (NumberFormatException e) {
            errorType = null;
        }
        return errorType;
    }

    /**
     * Checks if the count of attributes matches the count of message parameters.
     * @param message The string message to format.
     * @param attributes The list of attributes.
     * @return True if it's correct, false otherwise.
     */
    private boolean checkMessage(OCLExpression message, ExpressionInOCL attributes) {
    
        if (message == null || attributes == null) {
            return true;
        }
    
        String carac = "%s";
        int nbOccurs = message.toString().endsWith(carac) ? message.toString().split(carac).length 
                : (message.toString().split(carac).length - 1);
    
        int attrSize = ((CollectionLiteralExp) attributes.getOwnedBody()).getOwnedParts().size();
        return attrSize >= nbOccurs;
    }

    /**
     * Saves the error parsing on the OCL file.
     * @param path The path of the file.
     * @param message The message to display.
     */
    private void saveFileError(String path, String message) {
        if (!parsingErrors.containsKey(path)) {
            parsingErrors.put(path, new ArrayList<String>());
        }

        String error = String.format("File parsing error : %s", message);
        parsingErrors.get(path).add(error);
    }

    /**
     * Saves the error parsing on the OCL constraint.
     * @param path The path of the file.
     * @param context The context of constraint.
     * @param constraint The name of constraint.
     * @param element The element on what the parsing fails.
     * @param message The message to display.
     */
    private void saveConstraintError(String path, String context, String constraint, String element, String message) {
        if (!parsingErrors.containsKey(path)) {
            parsingErrors.put(path, new ArrayList<String>());
        }

        String error = String.format("Context '%s' > '%s' > '%s': %s", context, constraint, element, message);
        parsingErrors.get(path).add(error);
    }
}
