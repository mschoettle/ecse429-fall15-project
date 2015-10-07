package ca.mcgill.sel.ram.loaders.exceptions;

/**
 * Thrown when trying to use a class whose Jar is missing.
 * 
 * @author Franz
 *
 */
@SuppressWarnings("serial")
public class MissingJarException extends Exception {

    /**
     * Constructor.
     * @param missingClass Name of the missing class.
     */
    public MissingJarException(String missingClass) {
        super("Missing a jar file.\n Containing the class: " + missingClass);
    }
}
