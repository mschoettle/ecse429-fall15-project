package ca.mcgill.sel.ram.loaders;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;

import ca.mcgill.sel.ram.loaders.exceptions.MissingJarException;
import ca.mcgill.sel.ram.loaders.interfaces.IRamClassLoader;

/**
 * Loads interfaces and classes from a JAR file. Lazy implementation. Loads class with the URLLoader only when needed.
 * Meta-information of every class is gathered directly from the .class file using the ASM library.
 * 
 * @author Franz
 * 
 */
public final class RamClassLoader implements IRamClassLoader {
    
    /**
     * Singleton.
     */
    public static final RamClassLoader INSTANCE = new RamClassLoader();
    
    private Set<String> pathsToJarFiles;
    private Set<String> loadableClasses;
    private Set<String> loadableInterfaces;
    private Set<String> loadableEnums;
    
    /**
     * Constructor. Loads all java classes.
     */
    private RamClassLoader() {
        pathsToJarFiles = new HashSet<String>();
        loadableClasses = new HashSet<String>();
        loadableInterfaces = new HashSet<String>();
        loadableEnums = new HashSet<String>();
    }
    
    /**
     * Initializes the loader with the java classes.
     */
    public void initializeWithJavaClasses() {
        String javaJarPath = getJavaJarPath();
        try {
            if (javaJarPath != null) {
                addJarFile(javaJarPath);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Gets the path of the appropriate jar file.
     * @return the path of the jar file
     */
    private String getJavaJarPath() {
        String paths = System.getProperty("sun.boot.class.path");
        // Unix and windows have different separators
        String separator = System.getProperty("path.separator");
        for (String path : paths.split(separator)) {
            if (path.endsWith(File.separatorChar + "rt.jar")) {
                return path;
            } else if (path.endsWith(File.separatorChar + "classes.jar")) {
                return path;
            }
        }
        return null;
    }
    
    @Override
    public Class<?> retrieveClass(final String name) throws ClassNotFoundException, MissingJarException {
        // reload if invalid jars
        refreshLoadableLists();
        return findClass(name);
    }
    
    @Override
    public List<String> getAllLoadableClasses() {
        refreshLoadableLists();
        return new ArrayList<String>(loadableClasses);
    }
    
    @Override
    public void addJarFile(final String path) throws FileNotFoundException {
        File loadedFile = new File(path);
        if (loadedFile.exists()) {
            pathsToJarFiles.add(path);
            loadJarFile(loadedFile);
        } else {
            throw new FileNotFoundException("File at " + path + " does not exists.");
        }
    }
    
    @Override
    public boolean isLoadableClass(final String name) {
        return getAllLoadableClasses().contains(name);
    }
    
    @Override
    public boolean isLoadableInterface(final String name) {
        return getAllLoadableInterfaces().contains(name);
    }
    
    @Override
    public boolean isLoadableEnum(final String name) {
        return getAllLoadableEnums().contains(name);
    }
    
    @Override
    public List<String> getAllLoadableInterfaces() {
        refreshLoadableLists();
        return new ArrayList<String>(loadableInterfaces);
    }
    
    @Override
    public List<String> getAllLoadableEnums() {
        refreshLoadableLists();
        return new ArrayList<String>(loadableEnums);
    }
    
    @Override
    public List<String> getAllLoadable() {
        refreshLoadableLists();
        List<String> allLoadable = new ArrayList<String>();
        allLoadable.addAll(loadableClasses);
        allLoadable.addAll(loadableInterfaces);
        allLoadable.addAll(loadableEnums);
        return allLoadable;
    }
    
    @Override
    public Set<String> getAllSuperClassesFor(String name) {
        Set<String> result = new HashSet<String>();
        
        try {
            Class<?> clazz = retrieveClass(name);
            
            if (clazz.getSuperclass() != null && clazz.getSuperclass() != Object.class) {
                result.add(clazz.getSuperclass().getCanonicalName());
            }
            
            for (Class<?> interfaceClazz : clazz.getInterfaces()) {
                result.add(interfaceClazz.getCanonicalName());
            }
        } catch (ClassNotFoundException e) {
            System.out.println("[RamClassLoader] " + e.getLocalizedMessage());
        } catch (MissingJarException e) {
            System.out.println("[RamClassLoader] " + e.getLocalizedMessage());
        }
        
        return result;
    }
    
    /**
     * Clears the list with classes that are loadable and repopulates it.
     */
    private void reloadAll() {
        loadableInterfaces.clear();
        loadableClasses.clear();
        // Create list of class that are loadable from that path
        for (String path : pathsToJarFiles) {
            loadJarFile(new File(path));
        }
    }
    
    /**
     * Finds class with that name.
     * @param name of the class we are looking for.
     * @return Class object.
     * @throws ClassNotFoundException thrown if class not found
     * @throws MissingJarException thrown if a Jar file is missing.
     */
    private Class<?> findClass(final String name) throws ClassNotFoundException, MissingJarException {
        try {
            // gather a list of urls to jar files
            List<URL> urls = new ArrayList<URL>();
            for (String path : pathsToJarFiles) {
                File jarFile = new File(path);
                try {
                    urls.add(jarFile.toURI().toURL());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
            // create a loader and use it to load the specified class
            URLClassLoader loader = null;
            try {
                URL[] urlsArray = urls.toArray(new URL[Math.max(0, urls.size() - 1)]);
                loader = new URLClassLoader(urlsArray);
                return loader.loadClass(name);
            } finally {
                if (loader != null) {
                    try {
                        loader.close();
                    } catch (IOException e) {
                        System.out.println("[RamClassLoader] Can't close loader: " + e.getLocalizedMessage());
                    }
                }
            }
            
        } catch (NoClassDefFoundError error) {
            // CHECKSTYLE:IGNORE MultipleStringLiterals: Doesn't really make sense to create a constant for ".".
            String missingClass = error.getMessage().replaceAll("//", ".");
            throw new MissingJarException(missingClass);
        }
    }
    
    /**
     * Checks if a jar file does not exist anymore at the saved location. If a jar file has disappeared we reload
     * everything. This is important because some jar files could have moved or have been deleted. We do not want to
     * keep those classes in our list because we cannot load those classes anymore. Could be optimized by only removing
     * the classes from that jar but we would need a mapping from jarpath -> list of loadable classes. But we assume
     * that most of the time Jars will not be moved or deleted.
     */
    private void refreshLoadableLists() {
        if (!checkIfJarsStillExists()) {
            removeAllInvalidJars();
            reloadAll();
        }
    }
    
    /**
     * Checks if all jar files in the list are still at the specified path.
     * 
     * @return true if there is all files are valid (they are still at the specified location), false otherwise
     */
    private boolean checkIfJarsStillExists() {
        boolean exists = true;
        for (String path : pathsToJarFiles) {
            File jarFile = new File(path);
            // check if the file still exists
            if (!jarFile.exists()) {
                exists = false;
            }
        }
        return exists;
    }
    
    /**
     * Remove from set all jars that are not at the specified location anymore.
     */
    private void removeAllInvalidJars() {
        List<String> invalidJarFiles = new ArrayList<String>();
        for (String path : pathsToJarFiles) {
            File jarFile = new File(path);
            // if jar doesn't exists add to the list of invalid jars
            if (!jarFile.exists()) {
                invalidJarFiles.add(path);
            }
        }
        // remove all invalid jars
        pathsToJarFiles.removeAll(invalidJarFiles);
    }
    
    /**
     * Load a jar file. Adds all the classes and interface to this loader. These classes are now considered loadable.
     * Note: that these classes are not actually loaded into the JVM, it only means that they can be loaded. Must call
     * the retrieveClass(final String name) to actually load it.
     * 
     * @param loadedFile Jar file that should be loaded.
     */
    private void loadJarFile(final File loadedFile) {
        // If a .jar go through every .class files that it contains
        if (loadedFile.getName().endsWith(".jar")) {
            try {
                JarFile jarFile = new JarFile(loadedFile);
                Enumeration<JarEntry> entries = jarFile.entries();
                // Go through every JarEntry in the JarFile
                // and accumulate the .class
                while (entries.hasMoreElements()) {
                    JarEntry entry = entries.nextElement();
                    String entryName = entry.getName();
                    if (entryName.endsWith(".class") && !isAnonymousClass(entryName)) {
                        // Replace the \ by . to get the packagename.X.class
                        String className = entryName.replaceAll("[\\\\]", ".");
                        className = entryName.replaceAll("[\\/]", ".");
                        className = className.substring(0, className.length() - ".class".length());
                        
                        // Read the class file in the jar
                        ClassReader reader = new ClassReader(jarFile.getInputStream(entry));
                        int access = reader.getAccess();
                        
                        // add to list if interface or class
                        if ((access & Opcodes.ACC_INTERFACE) != 0) {
                            loadableInterfaces.add(className);
                        } else if ((access & Opcodes.ACC_ANNOTATION) == 0 && (access & Opcodes.ACC_ENUM) == 0) {
                            loadableClasses.add(className);
                        } else if ((access & Opcodes.ACC_ENUM) != 0) {
                            loadableEnums.add(className);
                        }
                    }
                }
                jarFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Checks if a class is anonymous or not. The name of an anonymous classes ends with $[number].
     * 
     * @param name name of the class
     * @return true if it is anonymous class, false otherwise
     */
    private static boolean isAnonymousClass(final String name) {
        return name.matches("(.*)(\\$(\\d)+).class");
    }
    
}
