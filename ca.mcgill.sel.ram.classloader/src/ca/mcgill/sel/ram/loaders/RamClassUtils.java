package ca.mcgill.sel.ram.loaders;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import ca.mcgill.sel.ram.loaders.exceptions.MissingJarException;

/**
 * Utils for classes loaded into ram.
 * 
 * @author Franz
 * 
 */
public final class RamClassUtils {
    
    /**
     * The beginning delimiter of generics. E.g., List&lt;Object> uses &lt; as the beginning delimiter.
     */
    private static final String GENERICS_START = "<";

    /**
     * Prevent construction.
     */
    private RamClassUtils() {

    }

    /**
     * Class name with the parent if one exists. This happens
     * when the class is an anonymous class. We want
     * class name:Parent$Child.
     * @param fullClassName class name including packages
     * @return name of class with parent separated with "$".
     */
    public static String extractClassNameWithParent(String fullClassName) {
        String[] splitName = fullClassName.split("\\.");
        return splitName[Math.max(splitName.length - 1, 0)];
    }

    /**
     * Extract the name of a class from the full name (includes packages).
     * (Without generics and parent)
     * @param fullClassName class name including packages
     * @return name of the class
     */
    public static String extractClassName(final String fullClassName) {
        return extractClassNameWithGeneric(fullClassName).replaceAll("<(.*)>", "");
    }

    /**
     * Extract class name with generics.
     * @param fullClassName class name including packages
     * @return name of class with generics.
     */
    public static String extractClassNameWithGeneric(final String fullClassName) {
        String[] genericSplit = fullClassName.split(GENERICS_START);
        String[] splitName = genericSplit[0].split("\\.");
        String generic = extractGeneric(fullClassName);
        if (!generic.isEmpty()) {
            generic = GENERICS_START + generic + ">";
        }
        return splitName[Math.max(splitName.length - 1, 0)] + generic;
    }

    /**
     * Extract only the generic type parameters of a class.
     * @param fullClassName class name including packages
     * @return generic type parameters of class.
     */
    public static String extractGeneric(final String fullClassName) {
        String[] splitGeneric = fullClassName.replaceAll("\\$", ".").trim().split(GENERICS_START);
        String genericPart = "";
        if (splitGeneric.length > 1) {
            for (int i = 1; i < splitGeneric.length; i++) {
                genericPart += splitGeneric[i];
                if (i != splitGeneric.length) {
                    genericPart += GENERICS_START;
                }
            }
            genericPart = extractClassNameWithGeneric(genericPart.substring(0, genericPart.length() - 2));
        }
        return genericPart;
    }

    /**
     * Extract generics and return a list.
     * @param fullClassName class name including packages.
     * @return list of type parameters.
     */
    public static List<String> extractGenerics(final String fullClassName) {
        List<String> generics = new ArrayList<String>();
        String genericString = extractGeneric(fullClassName).trim();
        int counter = 0;
        int lastFound = 0;
        for (int i = 0; i < genericString.length(); i++) {
            if (genericString.charAt(i) == '<') {
                counter++;
            } else if (genericString.charAt(i) == '>') {
                counter--;
            } else if (genericString.charAt(i) == ',' && counter == 0) {
                generics.add(genericString.substring(lastFound, i).trim());
                lastFound = i + 1;
            }
        }
        if (lastFound < genericString.length()) {
            generics.add(genericString.substring(lastFound, genericString.length()).trim());
        }
        return generics;
    }

    /**
     * Get class name of a java.lang.reflect.Type object.
     * @param someType some type.
     * @return class name but does not include the generics
     */
    public static String getClassName(java.lang.reflect.Type someType) {
        String className = someType.toString();
        if (someType instanceof ParameterizedType) {
            className = ((ParameterizedType) someType).getRawType().toString();
        }
        className = className.replaceAll("(interface|class|enum) ", "");
        return className;
    }

    /**
     * Retrieve class from type.
     * @param someType some type.
     * @return Class associated to this type.
     * @throws ClassNotFoundException If class not found.
     */
    public static Class<?> getClassFromType(java.lang.reflect.Type someType) throws ClassNotFoundException {
        try {
            return RamClassLoader.INSTANCE.retrieveClass(getClassName(someType));
        } catch (MissingJarException e) {
            throw new ClassNotFoundException();     
        }
    }
}
