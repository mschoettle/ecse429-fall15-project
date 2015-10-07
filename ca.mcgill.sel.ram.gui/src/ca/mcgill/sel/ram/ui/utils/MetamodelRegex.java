package ca.mcgill.sel.ram.ui.utils;

/**
 * Utility class that contains the regular expressions for evaluating text input by users.
 * 
 * @author vbonnet
 * @author mschoettle
 */
public final class MetamodelRegex {
    
    /**
     * Regex for valid class names.
     */
    // (?:...) ignores the group, i.e. it won't be captured
    public static final String REGEX_CLASS_NAME = "[A-Z][A-Za-z0-9]*(?:<[A-Za-z][A-Za-z0-9]*>)?";
    
    /**
     * Regex for valid type expressions (name of the type).
     */
    // (?:...) ignores the group, i.e. it won't be captured
    public static final String REGEX_TYPE = "[A-Za-z][A-Za-z0-9]*(?:<[A-Za-z][A-Za-z0-9]*>)?";
    
    /**
     * Regex for valid names (attributes or parameters).
     */
    public static final String REGEX_TYPE_NAME = "[A-Za-z][A-Za-z0-9]*";
    
    /**
     * Regex for valid enum literals.
     */
    public static final String REGEX_ENUM_LITERAL = "^([A-Za-z][A-Za-z0-9_]*(?<!_)\\s*,\\s*)*"
            + "[A-Za-z][A-Za-z0-9_]*(?<!_)$";
    
    /**
     * Regex for valid visibility types. I.e., the one character representation of visibilities:
     * <ul>
     * <li>"-": private</li>
     * <li>"+": public</li>
     * <li>"#": protected</li>
     * <li>"~": package protected</li>
     * </ul>
     */
    public static final String REGEX_VISIBILITY = "[~#\\-\\+]";
    
    /**
     * Regex for valid operation names.
     */
    public static final String REGEX_OPERATION_NAME = "[a-z][A-Za-z0-9]*";
    
    /**
     * Regex with groups (type name and attribute name) for attribute declarations.
     * I.e., "<type name> <attribute name>".
     */
    // CHECKSTYLE:IGNORE MultipleStringLiterals FOR 2 LINES: Okay in this case (part of regex).
    public static final String REGEX_ATTRIBUTE_DECLARATION = "(" + REGEX_TYPE + "(\\[\\d*\\])*) ("
            + REGEX_TYPE_NAME + ")";
    
    /**
     * Regex with groups (type name and parameter name) for parameter declarations.
     * I.e., "<type name> <parameter name>".
     */
    public static final String REGEX_PARAMETER_DECLARATION = "(" + REGEX_TYPE + "(\\[\\])*) (" + REGEX_TYPE_NAME + ")";
    
    /**
     * Regex with groups (visibility, return type name, operation name and optional parameters)
     * for operation declarations.
     * I.e., "<visiblity> <type name> <operation name>(<optional parameter declarations separated by comma>)".
     */
    public static final String REGEX_OPERATION_DECLARATION = "(" + REGEX_VISIBILITY + ") ("
            + REGEX_TYPE + "(\\[\\])*) (" + REGEX_OPERATION_NAME + ")\\(([a-zA-z0-9<>,(\\[\\])* ]*)\\)";
    
    /**
     * Regex for natural numbers with a max digits of 9, no zero allowed.
     */
    public static final String REGEX_NUMBER_NO_ZERO = "([1-9][0-9]{0,8})";
    
    /**
     * Regex for natural numbers with a max digits of 9.
     */
    public static final String REGEX_NUMBER = "(0|" + REGEX_NUMBER_NO_ZERO + ")";
    
    /**
     * Regex for multiplicities. This can be "<number>..<number>", "<number>..*" or "<number>".
     */
    public static final String REGEX_MULTIPLICITY = "^" + REGEX_NUMBER_NO_ZERO
            + "$|^" + REGEX_NUMBER + "\\.\\.(\\*|[1-9][0-9]{0,8})$";
    
    /**
     * Regex with groups for multiplicities.
     * Should only be used after a string has bee validated with {@link #REGEX_MULTIPLICITY}.
     */
    public static final String REGEX_GROUP_MULTIPLICITY = "(\\d+)(\\.\\.)*(\\d+|\\*)*";
    
    /**
     * Creates a new instance.
     */
    private MetamodelRegex() {
        
    }
}
