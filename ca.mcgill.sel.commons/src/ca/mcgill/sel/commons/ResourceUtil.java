package ca.mcgill.sel.commons;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.eclipse.emf.common.util.URI;

/**
 * A utility class that supports both to locate resources in the file system and JAR files.
 *
 * @author mschoettle
 */
public final class ResourceUtil {

    /**
     * Creates a new instance.
     */
    private ResourceUtil() {

    }

    /**
     * Returns the location of where the given class is executed in.
     * In the IDE, this usually is the bin directory, whereas in a JAR file this
     * is the JAR file.
     *
     * @param clazz the class of interest
     * @return the code execution location
     */
    public static URL getCodeLocation(Class<?> clazz) {
        return clazz.getProtectionDomain().getCodeSource().getLocation();
    }

    /**
     * Returns the absolute path to the directory of where the given class is executed in.
     * In the IDE, this is usually the parent of the bin directory,
     * whereas for a JAR file this is the containing directory of the JAR.
     *
     * @param clazz the class of interest
     * @return the absolute path of the main directory
     * @see #getCodeLocation(Class)
     */
    public static String getMainDirectory(Class<?> clazz) {
        URL codeLocation = getCodeLocation(clazz);
        String mainDirectory = null;

        try {
            // Easier to work with URIs. If the protocol is important, change to codeLocation.toExternalForm().
            URI codeLocationURI = URI.createURI(URLDecoder.decode(codeLocation.getPath(), "UTF-8"));

            if (codeLocationURI.lastSegment().endsWith("jar")) {
                mainDirectory = codeLocationURI.trimSegments(1).toString();
            } else {
                mainDirectory = codeLocationURI.trimSegments(2).toString();
            }

            if (mainDirectory != null) {
                mainDirectory += File.separator;
            }
        } catch (UnsupportedEncodingException e) {
            // Ignore.
            e.printStackTrace();
        }

        return mainDirectory;
    }

    /**
     * Returns the absolute path to the resources directory.
     *
     * @param clazz the class of interest
     * @return the absolute path of the resources directory
     * @see #getMainDirectory(Class)
     */
    public static String getResourcesDirectory(Class<?> clazz) {
        String resourcesDirectory = getMainDirectory(clazz);
        if (resourcesDirectory.contains(".app/Contents/Java/")) {
            resourcesDirectory = resourcesDirectory.replace(".app/Contents/Java/", ".app/Contents/Resources/");
        }
        return resourcesDirectory;
    }

    /**
     * Returns the {@link URL} of the given resource.
     * The resource needs to be located inside a source folder and the resource name given relative to that,
     * including packages.
     *
     * @param resource the resource to find
     * @return the URL of the resource, null if none found
     */
    public static URL getResourceURL(String resource) {
        return ClassLoader.getSystemResource(resource);
    }

    /**
     * Returns the path of the given resource.
     * The resource needs to be located inside a source folder and the resource name given relative to that,
     * including packages.
     *
     * @param resource the resource to find
     * @return the path of the resource, null if none found
     */
    public static String getResourcePath(String resource) {
        return getResourceURL(resource).getPath();
    }

    /**
     * Lists the directory contents for a resource folder non-recursively.
     * This supports both directories in the file system and resources in JARs.
     *
     * @author Greg Briggs, initial version this is based on, see: http://stackoverflow.com/a/6247181
     * @param path the path of the resource
     * @return the list of items with their full path, null if path not found
     */
    public static List<String> getResourceListing(String path) {
        URL dirURL = getResourceURL(path);

        if (dirURL != null) {
            try {
                if ("file".equals(dirURL.getProtocol())) {
                    String[] files = new File(dirURL.toURI()).list();

                    // Create absolute paths.
                    for (int i = 0; i < files.length; i++) {
                        files[i] = new URL(dirURL, files[i]).toExternalForm();
                    }

                    return new ArrayList<String>(Arrays.asList(files));
                } else if ("jar".equals(dirURL.getProtocol())) {
                    URL jarURL = ResourceUtil.class.getProtectionDomain().getCodeSource().getLocation();
                    JarFile jar = new JarFile(URLDecoder.decode(jarURL.getFile(), "UTF-8"));
                    Enumeration<JarEntry> entries = jar.entries();
                    Set<String> result = new HashSet<String>();

                    while (entries.hasMoreElements()) {
                        String name = entries.nextElement().getName();
                        if (name.startsWith(path) && !name.equals(path)) {
                            String entry = name.substring(path.length());
                            // Just use subdirectories, not their contained files
                            // (same behaviour as with regular file directories)
                            int checkSubdir = entry.indexOf("/");
                            if (checkSubdir >= 0) {
                                entry = entry.substring(0, checkSubdir);
                            }

                            String absoluteEntryPath = new URL(dirURL, entry).toExternalForm();

                            result.add(absoluteEntryPath);
                        }
                    }
                    jar.close();

                    return new ArrayList<String>(result);
                }
            } catch (URISyntaxException e) {
                // Shall not happen.
                e.printStackTrace();
            } catch (IOException e) {
                // Shall not happen.
                e.printStackTrace();
            }
        }

        return null;
    }

    /**
     * Lists the directory contents for a resource folder non-recursively.
     * Filters out all files that don't have the given file extension.
     * This supports both directories in the file system and resources in JARs.
     *
     * @param path the path of the resource
     * @param fileExtension the file extension to filter for
     * @return the list of items with their full path, null if path not found
     * @see #getResourceListing(String)
     */
    public static List<String> getResourceListing(String path, String fileExtension) {
        List<String> resources = getResourceListing(path);

        if (resources != null) {
            Iterator<String> iterator = resources.iterator();

            while (iterator.hasNext()) {
                String next = iterator.next();

                if (!next.endsWith(fileExtension)) {
                    iterator.remove();
                }
            }
        }

        return resources;
    }

}
