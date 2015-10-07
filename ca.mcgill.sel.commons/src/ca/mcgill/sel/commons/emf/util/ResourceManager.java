package ca.mcgill.sel.commons.emf.util;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Factory;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.ocl.pivot.utilities.PivotStandaloneSetup;
import org.eclipse.ocl.xtext.completeocl.CompleteOCLStandaloneSetup;

/**
 * ResourceManager is a helper class to load and save any model based on an
 * Ecore metamodel. {@link #initialize()} should be called first, which will
 * perform all the necessary initialization steps, but it will be lazy
 * initialized if it wasn't explicitly called (when loading a model). By
 * default, OCL (Pivot) is loaded as well, which requires OCLInEcore to be
 * available, and caching of loaded resources (based on their URI) is enabled.
 * If one of each or both should be used, use
 * {@link #initialize(boolean, boolean)} to explicitly initialize without.
 * <br />
 * <br />
 * If caching is enabled, all loaded resources will be cached in order to only
 * load them once. Therefore, in order to reload a model, the resource has to be
 * unloaded first ({@link #unloadResource(Resource)}. <br />
 * <b>Note:</b> To work with your own resource factory and file extensions you
 * have to call {@link #registerExtensionFactory(String, Factory)} first to
 * register your own resource factory. Furthermore, you will need to register
 * the package of your model, for example:
 * <code>EcorePackage.INSTANCE.eClass();</code>.
 * 
 * @author mschoettle
 */
public final class ResourceManager {

    private static ResourceSet resourceSet;
    private static boolean cachingEnabled;

    /**
     * Creates a new resource manager.
     */
    private ResourceManager() {
        // Suppress.
    }

    /**
     * Loads the root {@link EObject} from the given file.
     * 
     * @param file
     *            the file the object should be loaded from
     * @return the loaded {@link EObject}
     * @see #loadModel(File)
     */
    public static EObject loadModel(String file) {
        return loadModel(new File(file));
    }

    /**
     * Loads the root {@link EObject} from the given file. Makes sure that the
     * given file exists. Throws an {@link IllegalArgumentException} if it
     * doesn't otherwise. A {@link RuntimeException} might be thrown in case the
     * resource factory for the file extension was not registered beforehand.
     * See {@link #registerExtensionFactory(String, Factory)}. A
     * {@link org.eclipse.ecore.xmi.PackageNotFoundException} is thrown when the
     * corresponding package of the metamodel wasn't registered. To register,
     * call <code>eClass()</code> on the package instance (e.g.,
     * <code>EcorePackage.INSTANCE.eClass();</code>).
     * 
     * @param file
     *            the file the object should be loaded from
     * @return the loaded {@link EObject}
     */
    public static EObject loadModel(File file) {
        if (!file.exists()) {
            throw new IllegalArgumentException("Given file does not exist: ".concat(file.getAbsolutePath()));
        }

        // Initialize resource set on demand.
        if (resourceSet == null) {
            initialize();
        }

        // Get the resource.
        Resource resource = resourceSet.getResource(URI.createFileURI(file.getAbsolutePath()), true);
        // Get the first model element, which is the root element.
        return resource.getContents().get(0);
    }

    /**
     * Saves the given model to the given path.
     * 
     * @param object
     *            the {@link EObject} to save as a file
     * @param file
     *            the full path of the file where the object should be saved to
     * @throws IOException
     *             if an error during saving occurs
     */
    public static void saveModel(EObject object, String file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Supplied file is invalid: " + file);
        }

        // Create a resource
        Resource resource = resourceSet.createResource(URI.createFileURI(file));

        // Add the resources to the resource to be saved.
        resource.getContents().add(object);

        // Now save the content.
        resource.save(Collections.EMPTY_MAP);
    }

    /**
     * Removes a loaded resource from the loaded resources in order to be able
     * to load it from the file again.
     * 
     * @param resource
     *            the resource to be unloaded
     */
    public static void unloadResource(Resource resource) {
        if (resourceSet != null && resource != null) {
            // Remove the resource from resource map (cache).
            ResourceSetImpl resourceSetImpl = (ResourceSetImpl) resourceSet;

            if (cachingEnabled) {
                resourceSetImpl.getURIResourceMap().remove(resource.getURI());
            }

            resourceSet.getResources().remove(resource);
            resource.unload();
        }
    }

    /**
     * Registers a given resource factory for a given file extension. The
     * factory is used to handle files with this extension from a metamodel that
     * the factory is intended for.
     * 
     * @param extension
     *            the file extension for the resource factory
     * @param factory
     *            the resource factory to use to handle files of the file
     *            extension
     */
    public static void registerExtensionFactory(String extension, Factory factory) {
        Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
        Map<String, Object> extensionToFactoryMap = reg.getExtensionToFactoryMap();

        extensionToFactoryMap.put(extension, factory);
    }

    /**
     * Initializes the required registries and resources. Enables OCL support
     * and caching of loaded resources.
     * 
     * This has to be called before any loading or saving is performed.
     */
    public static void initialize() {
        initialize(true, true);
    }

    /**
     * Initializes the required registries and resources. This has to be called
     * before any loading or saving is performed. If OCL (Pivot) is used, the
     * plug-in org.eclipse.ocl.examples.xtext.oclinecore is required. If caching
     * is enabled, it is the callers responsibility to unload resources (
     * {@link #unloadResource(Resource)} once not needed anymore.
     * 
     * @param initializeOCL
     *            true, if OCL should be initialized, false otherwise
     * @param enableCaching
     *            true, if loaded resources should be cached, false otherwise
     */
    public static void initialize(boolean initializeOCL, boolean enableCaching) {
        // Avoid initializing multiple times.
        if (resourceSet == null) {
            if (initializeOCL) {
                initializeOCL();
            }

            ResourceSetImpl resourceSetImpl = new ResourceSetImpl();
            if (enableCaching) {
                resourceSetImpl.setURIResourceMap(new HashMap<URI, Resource>());
                cachingEnabled = true;
            }
            resourceSet = resourceSetImpl;
        }
    }

    /**
     * Initializes OCL Pivot in case this is used by a metamodel.
     */
    private static void initializeOCL() {
        PivotStandaloneSetup.doSetup();
        // Register Pivot globally (resourceSet == null).
        // OCL.initialize(null);

        // String oclDelegateURI = PivotConstants.OCL_DELEGATE_URI_PIVOT;
        // EOperation.Internal.InvocationDelegate.Factory.Registry.INSTANCE.put(oclDelegateURI,
        // new OCLInvocationDelegateFactory.Global());
        // EStructuralFeature.Internal.SettingDelegate.Factory.Registry.INSTANCE.put(oclDelegateURI,
        // new OCLSettingDelegateFactory.Global());
        // EValidator.ValidationDelegate.Registry.INSTANCE.put(oclDelegateURI,
        // new OCLValidationDelegateFactory.Global());

        // OCLinEcoreStandaloneSetup.doSetup();
        // OCLstdlib.install();

        // OCLstdlibStandaloneSetup.doSetup();
        // OCLinEcoreStandaloneSetup.doSetup();
        CompleteOCLStandaloneSetup.doSetup();
    }

}
