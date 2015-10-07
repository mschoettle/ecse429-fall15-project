package ca.mcgill.sel.ram.ui;

import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import ca.mcgill.sel.commons.emf.util.AdapterFactoryRegistry;
import ca.mcgill.sel.commons.emf.util.ResourceManager;
import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.core.provider.CoreItemProviderAdapterFactory;
import ca.mcgill.sel.core.util.CoreResourceFactoryImpl;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.loaders.RamClassLoader;
import ca.mcgill.sel.ram.provider.RamItemProviderAdapterFactory;
import ca.mcgill.sel.ram.ui.utils.Constants;
import ca.mcgill.sel.ram.ui.utils.LoggerUtils;
import ca.mcgill.sel.ram.ui.utils.ResourceUtils;
import ca.mcgill.sel.ram.ui.utils.ResourceUtils.OperatingSystem;
import ca.mcgill.sel.ram.ui.utils.Strings;
import ca.mcgill.sel.ram.util.RamResourceFactoryImpl;
import ca.mcgill.sel.ram.validator.ValidationRulesParser;

/**
 * This class is just here to start a new instance of the TouchCORE application.
 * This is an empty facade that should hopefully
 * make refactoring easier should it ever be needed. NOTE - this MUST be run as an application (and not an applet)
 * otherwise Settings.txt will not be read.
 *
 * @author vbonnet
 * @author mschoettle
 */
public final class TouchCORE {

    /**
     * Creates a new instance of TouchCORE.
     */
    private TouchCORE() {

    }

    /**
     * Starts the application.
     *
     * @param args
     *            arguments, not used
     */
    public static void main(final String[] args) {

        // Temporary workaround to rename the dock icon name in Mac OS X.
        // Once the application is bundled as an .app this is not required anymore.
        // Might only work in Java 6 from Apple.
        if (ResourceUtils.getOperatingSystem() == OperatingSystem.OSX) {
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", Strings.APP_NAME);
        }

        // Initialize ResourceManager (will initialize OCL).
        // See issue #84.
        ResourceManager.initialize();

        // Initialize packages.
        RamPackage.eINSTANCE.eClass();
        CorePackage.eINSTANCE.eClass();

        // Register resource factories.
        ResourceManager.registerExtensionFactory(Constants.ASPECT_FILE_EXTENSION, new RamResourceFactoryImpl());
        ResourceManager.registerExtensionFactory(Constants.CORE_FILE_EXTENSION, new CoreResourceFactoryImpl());
        ResourceManager.registerExtensionFactory(Constants.JUCM_FILE_EXTENSION, new XMIResourceFactoryImpl());

        // Initialize adapter factories.
        AdapterFactoryRegistry.INSTANCE.addAdapterFactory(RamItemProviderAdapterFactory.class);
        AdapterFactoryRegistry.INSTANCE.addAdapterFactory(CoreItemProviderAdapterFactory.class);

        ResourceUtils.loadLibraries();
        // Start the application, the rest will happen by itself.
        RamApp.initialize();

        RamClassLoader.INSTANCE.initializeWithJavaClasses();

        // To parse OCL files and initialize rules.
        Thread tParser = new Thread(new Runnable() {
            @Override
            public void run() {
                ValidationRulesParser.getInstance();
                LoggerUtils.info("OCL files parsing terminated.");
            }
        }, "Rules Parser Initializer");
        tParser.start();

    }
}
