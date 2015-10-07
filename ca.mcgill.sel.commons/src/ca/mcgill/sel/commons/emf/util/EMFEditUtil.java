package ca.mcgill.sel.commons.emf.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.MissingResourceException;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;

/**
 * Helper class with convenient static methods for working with EMF objects,
 * the adapter factory, item providers and property descriptors.
 * 
 * @author mschoettle
 */
public final class EMFEditUtil {
    
    /**
     * Creates a new EMF Edit Util instance.
     */
    private EMFEditUtil() {
        // Suppress and hide default constructor.
    }
    
    /**
     * Returns the property descriptor for the feature of the given object.
     * 
     * @param object the object containing the feature
     * @param feature the feature/property
     * @return the {@link IItemPropertyDescriptor} for the feature of the given object
     */
    public static IItemPropertyDescriptor getPropertyDescriptor(EObject object, EStructuralFeature feature) {
        ItemProviderAdapter itemProvider = getItemProvider(object);
        
        return itemProvider.getPropertyDescriptor(object, feature);
    }
    
    /**
     * Returns the item provider for the given object.
     * 
     * @param object the object the item provider is requested for
     * @return the {@link ItemProviderAdapter} for the given object
     */
    public static ItemProviderAdapter getItemProvider(EObject object) {
        return (ItemProviderAdapter) getAdapterFactory(object).adapt(object, IItemPropertySource.class);
    }
    
    /**
     * Returns the adapter factory for the given object.
     * 
     * @param object the object the associated adapter factory is requested of
     * @return the {@link EditingDomainProvidingComposedAdapterFactory} associated with the given object
     */
    public static EditingDomainProvidingComposedAdapterFactory getAdapterFactory(EObject object) {
        return AdapterFactoryRegistry.INSTANCE.getAdapterFactoryFor(object);
    }
    
    /**
     * Returns the editing domain for the given object.
     * 
     * @param object the object the associated editing domain is requested of
     * @return the {@link EditingDomain} associated with the given object
     */
    public static EditingDomain getEditingDomain(EObject object) {
        EditingDomainProvidingComposedAdapterFactory adapterFactory = getAdapterFactory(object);
        
        return adapterFactory.getEditingDomain();
    }
    
    /**
     * Returns the textual representation of the given feature of the given object.
     * 
     * @param object the object containing the feature
     * @param feature the requested feature
     * @return the textual representation of the given feature of the given object
     */
    public static String getFeatureText(EObject object, EStructuralFeature feature) {
        IItemPropertySource propertySource = (IItemPropertySource) getPropertyDescriptor(object,
                feature).getPropertyValue(object);
        return (propertySource != null) ? String.valueOf(propertySource.getEditableValue(object)) : "";
    }
    
    /**
     * Adds the given listener to the item provider of the given object.
     * Make sure to remove the listener when not needed anymore.
     * 
     * @param object the object
     * @param listener the listener
     */
    public static void addListenerFor(EObject object, INotifyChangedListener listener) {
        ItemProviderAdapter itemProvider = getItemProvider(object);
        itemProvider.addListener(listener);
    }
    
    /**
     * Removes the given listener from the item provider of the given object.
     * 
     * @param object the object
     * @param listener the listener
     */
    public static void removeListenerFor(EObject object, INotifyChangedListener listener) {
        // Temporary workaround, see issue #141.
        // This is a trick by going the other way through the adapters of the object,
        // knowing that the item provider is registered there.
        for (Adapter adapter : object.eAdapters()) {
            if (adapter instanceof IChangeNotifier) {
                IChangeNotifier changeNotifier = (IChangeNotifier) adapter;
                
                changeNotifier.removeListener(listener);
            }
        }
    }
    
    /**
     * Returns the textual representation of the given object.
     * 
     * @param object the object
     * @return the textual representation of the given object
     */
    public static String getText(EObject object) {
        return EMFEditUtil.getTextFor(getAdapterFactory(object), object);
    }
    
    /**
     * Returns the textual representation of the given object, which is a valid element of the feature of data.
     * This allows, for example, to get the textual representation of an element retrieved by
     * {@link #getChoiceOfValuesFor(EObject, EStructuralFeature)}. Instead of using {@link #getText(EObject)},
     * which will return the general textual representation of the element.
     * The property descriptor, which describes the {@link EStructuralFeature} of the given object,
     * might have a different textual representation of elements for this property.
     * 
     * @param data the object containing the feature
     * @param feature the requested feature
     * @param object the object, which is a valid element of the feature
     * @return the textual representation of the given object, in regards to the property (feature of data),
     *         if there is a property descriptor; null otherwise
     */
    public static String getPropertyText(EObject data, EStructuralFeature feature, Object object) {
        IItemPropertyDescriptor propertyDescriptor = getPropertyDescriptor(data, feature);
        
        if (propertyDescriptor == null || object == null) {
            return null;
        }
        
        String result = propertyDescriptor.getLabelProvider(object).getText(object);
        
        if (object instanceof EObject) {
            result = EMFEditUtil.stripTypeName((EObject) object, result);
        }
        
        return result;
    }
    
    /**
     * Returns the available choice of values for the feature of the given object.
     * This only works for {@link org.eclipse.emf.ecore.EReference}s
     * and certain {@link org.eclipse.emf.ecore.EAttribute}s
     * (i.e., the type is an enum or an enumeratable data type, like a boolean).
     * To retrieve the textual representation for the collection, consider using
     * {@link #getPropertyText(EObject, EStructuralFeature, Object)} instead of {@link #getText(EObject)}, because the
     * representation might differ
     * depending on the specific property (feature).
     * 
     * @param object the object containing the feature
     * @param feature the feature/property
     * @return the choice of all possible values
     */
    public static Collection<?> getChoiceOfValuesFor(EObject object, EStructuralFeature feature) {
        // get the property descriptor
        IItemPropertyDescriptor propertyDescriptor = getPropertyDescriptor(object, feature);
        
        // get elements
        Collection<?> choiceOfValues = propertyDescriptor.getChoiceOfValues(object);
        
        if (choiceOfValues != null) {
            choiceOfValues.remove(null);
        }
        
        return choiceOfValues;
    }
    
    /**
     * Returns the command stack related to the given object.
     * Assumes that the command stack is a {@link BasicCommandStack}.
     * 
     * @param object the {@link EObject} to get the {@link BasicCommandStack} for
     * @return the {@link BasicCommandStack} for the given {@link EObject}
     */
    public static BasicCommandStack getCommandStack(EObject object) {
        EditingDomain editingDomain = getEditingDomain(object);
        
        return (BasicCommandStack) editingDomain.getCommandStack();
    }
    
    /**
     * Returns the new child descriptor for creating a new child of the given feature for the given object.
     * 
     * @param eObject the object a child should created for
     * @param childFeature the feature of the children
     * @return the {@link CommandParameter} (new child descriptor)
     */
    public static CommandParameter getNewChildDescriptor(EObject eObject, EStructuralFeature childFeature) {
        EditingDomain editingDomain = getEditingDomain(eObject);
        ItemProviderAdapter itemProvider = getItemProvider(eObject);
        
        for (Object object : itemProvider.getNewChildDescriptors(eObject, editingDomain, null)) {
            CommandParameter childDescriptor = (CommandParameter) object;
            if (childDescriptor.getFeature() == childFeature) {
                return childDescriptor;
            }
        }
        
        return null;
    }
    
    /**
     * Returns the type name for the given object.
     * The type name is retrieved from the edit plug-in,
     * which provides type names for all types (classes).
     * 
     * @param eObject the object the type name to get for
     * @return the type name for the given object, null if an error occurred
     */
    public static String getTypeName(EObject eObject) {
        // Get the key name of this object for the translated string
        // by using the objects interface name.
        String className = eObject.eClass().getName();
        String key = "_UI_" + className + "_type";
        
        String result = null;
        
        try {
            ItemProviderAdapter itemProvider = getItemProvider(eObject);
            result = itemProvider.getString(key);
        } catch (MissingResourceException e) {
            System.err.println("Error: Missing Resource for " + className
                    + "! Did you forget to register the adapter factory?");
        }
        
        return result;
    }
    
    /**
     * Strips the type name of the given object from the given text.
     * This allows for compatibility with the generated EMF Editor and EMF.Edit
     * as by default the type name is added at the beginning.
     * 
     * @param eObject the object
     * @param text the text to strip the type name from
     * @return the stripped text
     */
    public static String stripTypeName(EObject eObject, String text) {
        String result = null;
        String typeName = getTypeName(eObject);
        
        if (text != null) {
            // Strip the type from the beginning of the text.
            if (typeName != null) {
                result = text.substring(typeName.length()).trim();
            } else {
                // Fall back in case the resource wasn't found.
                result = text;
            }
        }
        
        return result;
    }
    
    /**
     * Retrieves the text for the given object from its {@link IItemLabelProvider}.
     * Also strips the type name from the retrieved text.
     * 
     * @param adapterFactory the {@link AdapterFactory} to get the {@link IItemLabelProvider} from
     * @param eObject the object the text should be retrieved for
     * @return the text for the given object from its {@link IItemLabelProvider}
     */
    public static String getTextFor(AdapterFactory adapterFactory, EObject eObject) {
        if (eObject != null) {
            // Get the adapter from the factory.
            IItemLabelProvider itemLabelProvider = (IItemLabelProvider) adapterFactory.adapt(eObject,
                    IItemLabelProvider.class);
            
            // Strip the type name for compatibility with generated EMF.Editor.
            return (itemLabelProvider != null) ? stripTypeName(eObject, itemLabelProvider.getText(eObject))
                    : eObject == null ? "" : eObject.toString();
        }
        
        return "null";
    }
    
    /**
     * Filters the choice of values for the given object.
     * Removes all values that are not part of the same aspect (i.e., not contained by the same {@link StructuralView}).
     * 
     * @param object the object that contains a property with the choices
     * @param choiceOfValues the choice of values to be filtered
     * @return the filtered choice of values
     */
    public static Collection<?> filterChoiceOfValues(Object object, Collection<?> choiceOfValues) {
        EObject eObject = (EObject) object;
        EObject rootObject = EcoreUtil.getRootContainer(eObject);
        
        for (Iterator<?> iterator = choiceOfValues.iterator(); iterator.hasNext();) {
            EObject value = (EObject) iterator.next();
            
            // null is also contained in the list
            if (value != null) {
                EObject objectContainer = EcoreUtil.getRootContainer(value);
                
                // If it is not from the current model it should be filtered.
                if (rootObject != objectContainer) {
                    iterator.remove();
                }
            }
        }
        
        return choiceOfValues;
    }
    
}
