package ca.mcgill.sel.ram.ui.utils;

import java.io.File;
import java.lang.reflect.Field;

import ca.mcgill.sel.commons.ResourceUtil;

/**
 * A util class with a bunch of static methods to find the code on disk and then return paths relative to it.
 * This is done efficiently by storing the path after the first class.
 * It assumes that all the paths are correct relative to the code's location on disk.
 * It should work for jars and direct .class files. Note that it will not work
 * for nested jars, nor will it work for a jar that is not named correctly.
 * 
 * @author vbonnet
 */
public final class ResourceUtils {
    
    /**
     * An enumeration of recognized operating systems.
     * 
     * @author vbonnet
     */
    public enum OperatingSystem {
        /**
         * Windows Operating System.
         */
        WINDOWS,
        /**
         * Mac operating system.
         */
        OSX,
        /**
         * Linux based operating system.
         */
        LINUX,
        /**
         * Solaris operating system.
         */
        SOLARIS,
        /**
         * Unrecognized operating system.
         */
        UNKNOWN;
    }
    
    /**
     * Hides the constructor, this'll just be used to find the code source.
     */
    private ResourceUtils() {
        
    }
    
    /**
     * Returns the operating system literal.
     * 
     * @return the operating system for the local machine
     */
    public static OperatingSystem getOperatingSystem() {
        String os = System.getProperty("os.name");
        OperatingSystem operatingSystem = OperatingSystem.UNKNOWN;
        if (os.toLowerCase().contains("win")) {
            operatingSystem = OperatingSystem.WINDOWS;
        } else if (os.toLowerCase().contains("mac")) {
            operatingSystem = OperatingSystem.OSX;
        } else if (os.toLowerCase().contains("nix") || os.toLowerCase().contains("nux")) {
            // could be either Linux or Unix
            operatingSystem = OperatingSystem.LINUX;
        }
        
        return operatingSystem;
    }
    
    /**
     * Loads all libraries in the /lib directory for the running operating system and architecture.
     */
    public static void loadLibraries() {
        try {
            // we are loading libraries from "./lib"
            String libraryPath = ResourceUtil.getMainDirectory(ResourceUtils.class) + "lib/";
            /**
             * Construct the library path based on the operating system and architecture.
             * Right now, only windows needs special treatment (for Win7Touch).
             */
            OperatingSystem os = getOperatingSystem();
            boolean skipArchitecture = false;
            switch (os) {
                case WINDOWS:
                    libraryPath += "win";
                    break;
                case OSX:
                case LINUX:
                    skipArchitecture = true;
                    break;
                default:
                    LoggerUtils.warn("Operating System " + System.getProperty("os.name") + " not supported");
                    System.exit(-1);
                    return;
            }
            
            // OS architecture
            String arch = System.getProperty("os.arch");
            if (!skipArchitecture) {
                if (arch.contains("64")) {
                    libraryPath += "_64";
                } else if ("i586".equals(arch) || "x86".equals(arch) || "i386".equals(arch)) {
                    libraryPath += "_32";
                } else {
                    LoggerUtils.error("architecture " + arch + " not supported");
                    System.exit(-1);
                }
            }
            
            LoggerUtils.info("Detected operating system " + getOperatingSystem() + " and architecture " + arch);
            
            // check if lib folder os_arc/ exists
            if (!new File(libraryPath).exists()) {
                LoggerUtils.error("lib directory " + libraryPath + " not found on disk ");
                System.exit(-1);
            }
            
            LoggerUtils.info("Setting java.library.path to " + libraryPath);
            changeLibraryPath(libraryPath);
            // CHECKSTYLE:IGNORE IllegalCatch: changeLibraryPath can throw many different exceptions, makes it easier.
        } catch (Exception e) {
            // something went wrong, fail
            System.err.println("Setting java.library.path failed:" + e);
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    /**
     * Sets the library path to the given one.
     * 
     * @param libraryPath the new library path
     * @throws Exception any exception that occurs during this operation
     * @see <a href="http://blog.cedarsoft.com/2010/11/setting-java-library-path-programmatically/"> Setting
     *      java.library.path programmatically</a>
     */
    private static void changeLibraryPath(String libraryPath) throws Exception {
        System.setProperty("java.library.path", libraryPath);
        Field fieldSysPath;
        
        fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
        fieldSysPath.setAccessible(true);
        fieldSysPath.set(null, null);
    }
    
}
