package ca.mcgill.sel.ram.loaders.interfaces;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Set;

import ca.mcgill.sel.ram.loaders.exceptions.MissingJarException;

/**
 * Loads interfaces and classes from a JAR file.
 * @author Franz
 */
public interface IRamClassLoader {

    /**
     * Retrieves the class with this name.
     * @param name name of the class
     * @return Class object with that name
     * @throws ClassNotFoundException If no class with that name is not found.
     * @throws MissingJarException If another class in a different jar
     *                             is needed to load this class
     */
    Class<?> retrieveClass(String name) throws ClassNotFoundException, MissingJarException;
    
    /**
     * Get all classes that are loadable from this loader.
     * @return list of all loadable classes
     */
    List<String> getAllLoadableClasses();

    /**
     * Get all interfaces that are loadable from this loader.
     * @return list of all loadable interfaces
     */
    List<String> getAllLoadableInterfaces();

    /**
     * Get all loadable elements (classes and interfaces) from this loader.
     * @return all loadable elements.
     */
    List<String> getAllLoadable();

    /**
     * Add a jar file to this loader. When a jar file is added,
     * all classes from that jar are now loadable from this loader.
     * @param path path to jar file
     * @throws FileNotFoundException If the jar file cannot be found
     */
    void addJarFile(String path) throws FileNotFoundException;

    /**
     * Checks if class is loadable from this loader.
     * @param fullname fullname of the class including packages
     * @return true if loadable, false otherwise
     */
    boolean isLoadableClass(String fullname);

    /**
     * Checks if interface is loadable from this loader.
     * @param fullname fullname of the interface including packages
     * @return true if loadable, false otherwise
     */
    boolean isLoadableInterface(String fullname);

    /**
     * Returns a list of loadable enums.
     * 
     * @return a list of loadable enums
     */
    List<String> getAllLoadableEnums();

    /**
     * Returns whether the given name of an enum is loadable.
     * 
     * @param name the name of the enum
     * @return true, if the enum is loadable, false otherwise
     */
    boolean isLoadableEnum(String name);
    
    /**
     * Returns a set of class names that are a super class or implemented interface of the given class.
     * The set will not contain the {@link Object} class name. In case there are none, an empty set is returned.
     * 
     * @param name the fully qualified name of the class
     * @return a set of fully qualified class names that the given class extends or implements, 
     *          empty set if there are none
     */
    Set<String> getAllSuperClassesFor(String name);

}
