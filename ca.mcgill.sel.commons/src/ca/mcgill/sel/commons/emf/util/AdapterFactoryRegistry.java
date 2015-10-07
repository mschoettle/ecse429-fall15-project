package ca.mcgill.sel.commons.emf.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;

/**
 * A registry of all {@link AdapterFactory}s of an application.
 * Contains a mapping from the {@link EObject} to its {@link EditingDomainProvidingComposedAdapterFactory}.
 * The {@link EObject} used to associate is the root container (the root element) of a model.
 * Rather than any {@link AdapterFactory} an {@link EditingDomainProvidingComposedAdapterFactory} is used
 * in order to be able to retrieve the {@link EditingDomain} of the adapter factory.
 * 
 * @see EditingDomainProvidingComposedAdapterFactory
 * @author mschoettle
 */
public final class AdapterFactoryRegistry {
    
    /**
     * The singleton instance of this registry.
     */
    public static final AdapterFactoryRegistry INSTANCE = new AdapterFactoryRegistry();
    
    /**
     * Stores the adapter factory (value) for a root object of a model (key).
     */
    private Map<EObject, EditingDomainProvidingComposedAdapterFactory> adapterFactoryMap;
    
    /**
     * Contains all the {@link AdapterFactory}s to be used for models.
     * Clients can register specific adapter factories here.
     */
    private List<Class<? extends AdapterFactory>> adapterFactories;
    
    /**
     * Creates a new {@link AdapterFactoryRegistry}.
     */
    private AdapterFactoryRegistry() {
        adapterFactoryMap = new HashMap<EObject, EditingDomainProvidingComposedAdapterFactory>();
        
        adapterFactories = new ArrayList<Class<? extends AdapterFactory>>();
        // Add reflective item provider factory as a default.
        adapterFactories.add(ReflectiveItemProviderAdapterFactory.class);
    }
    
    /**
     * Adds the given {@link AdapterFactory} class to the list of adapter factories available for models.
     * A new instance of each {@link AdapterFactory} is registered
     * for the specific adapter factory used for a particular model.
     * 
     * @param adapterFactory the {@link AdapterFactory} to add
     */
    public void addAdapterFactory(Class<? extends AdapterFactory> adapterFactory) {
        if (!adapterFactories.contains(adapterFactory)) {
            adapterFactories.add(adapterFactory);
        }
    }
    
    /**
     * Returns the {@link EditingDomainProvidingComposedAdapterFactory} for the given object.
     * The object can be a children of an {@link Aspect}, where then the {@link Aspect} will be retrieved.
     * If no adapter factory is registered, a new one will be created.
     * 
     * @param object the object the adapter factory is looked for
     * @return the adapter factory of the given object
     */
    public EditingDomainProvidingComposedAdapterFactory getAdapterFactoryFor(EObject object) {
        EObject rootContainer = EcoreUtil.getRootContainer(object);
        
        assert rootContainer != null;
        
        EditingDomainProvidingComposedAdapterFactory adapterFactory = adapterFactoryMap.get(rootContainer);
        
        if (adapterFactory == null) {
            adapterFactory = initializeEditingDomain();
            adapterFactoryMap.put(rootContainer, adapterFactory);
        }
        
        return adapterFactory;
    }
    
    /**
     * Disposes the adapter factory for the complete model of the given {@link EObject}.
     * 
     * @param eObject the eObject the corresponding adapter factory should be disposed for
     */
    public void disposeAdapterFactoryFor(EObject eObject) {
        EObject rootObject = EcoreUtil.getRootContainer(eObject);
        
        EditingDomainProvidingComposedAdapterFactory adapterFactory = adapterFactoryMap.remove(rootObject);
        
        if (adapterFactory != null) {
            adapterFactory.dispose();
        }
    }
    
    /**
     * Initializes a new adapter factory and all corresponding instances, e.g., editing domain and command stack.
     * 
     * @return a new {@link EditingDomainProvidingComposedAdapterFactory}
     */
    private EditingDomainProvidingComposedAdapterFactory initializeEditingDomain() {
        // Create an adapter factory that yields item providers.
        ComposedAdapterFactory actualAdapterFactory = new ComposedAdapterFactory(
                ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
        
        for (Class<? extends AdapterFactory> clazz : adapterFactories) {
            try {
                AdapterFactory adapterFactory = clazz.newInstance();
                actualAdapterFactory.addAdapterFactory(adapterFactory);
            } catch (InstantiationException e) {
                System.err.println("Error instantiating " + clazz.getCanonicalName() + ": " + e.getLocalizedMessage());
            } catch (IllegalAccessException e) {
                System.err.println("Error instantiating " + clazz.getCanonicalName() + ": " + e.getLocalizedMessage());
            }
        }
        
        // create command stack
        CommandStack commandStack = new CORECommandStack();
        
        // create the editing domain
        EditingDomain editingDomain = new AdapterFactoryEditingDomain(actualAdapterFactory, commandStack);
        
        // create adapter factory that knows the editing domain
        return new EditingDomainProvidingComposedAdapterFactory(actualAdapterFactory, editingDomain);
    }
    
}
