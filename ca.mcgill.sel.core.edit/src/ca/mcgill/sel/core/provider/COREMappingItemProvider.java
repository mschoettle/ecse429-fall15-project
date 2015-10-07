/**
 */
package ca.mcgill.sel.core.provider;


import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;

import org.eclipse.emf.edit.provider.ViewerNotification;
import ca.mcgill.sel.commons.emf.util.EMFModelUtil;
import ca.mcgill.sel.core.COREBinding;
import ca.mcgill.sel.core.COREMapping;
import ca.mcgill.sel.core.COREModel;
import ca.mcgill.sel.core.COREModelElement;
import ca.mcgill.sel.core.CorePackage;

/**
 * This is the item provider adapter for a {@link ca.mcgill.sel.core.COREMapping} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class COREMappingItemProvider
    extends ItemProviderAdapter
    implements
        IEditingDomainItemProvider,
        IStructuredItemContentProvider,
        ITreeItemContentProvider,
        IItemLabelProvider,
        IItemPropertySource {
    
    /**
     * A (more or less) generic {@link ItemPropertyDescriptor} for from elements which can be used by sub-classes.
     * Overrides {@link ItemPropertyDescriptor#getChoiceOfValues(Object)} and filters out all objects that are not from the instantiated model
     * as well as elements not contained in the element that is mapped in the containing mapping (e.g., operations not belonging to the mapped class).
     * 
     * @author mschoettle
     */
    protected class MappingFromItemPropertyDescriptor extends ItemPropertyDescriptor {
        
        public MappingFromItemPropertyDescriptor
        (AdapterFactory adapterFactory,
         ResourceLocator resourceLocator,
         String displayName,
         String description,
         EStructuralFeature feature, 
         boolean isSettable,
         boolean multiLine,
         boolean sortChoices,
         Object staticImage,
         String category,
         String [] filterFlags) {
            super(adapterFactory, 
                    resourceLocator, 
                    displayName, 
                    description, 
                    feature, 
                    isSettable, 
                    multiLine, 
                    sortChoices, 
                    staticImage, 
                    category, 
                    filterFlags);
        }
        
        @Override
        public Collection<?> getChoiceOfValues(Object object) {
            EObject mapping = (EObject) object;
            // The generic type of the Mapping this concrete Mapping is using...
            EClassifier genericType = getGenericType(mapping.eClass());
            COREBinding<?, ?> binding = EMFModelUtil.getRootContainerOfType(mapping, CorePackage.Literals.CORE_BINDING);
            COREModel externalModel = binding.getSource();
            
            // Get only all reachable objects of the correct type for this mapping...
            Collection<?> result = super.getReachableObjectsOfType(mapping, genericType);
            
            COREModelElement parentFromElement = null;
            if (mapping.eContainer() instanceof COREMapping<?>) {
                COREMapping<?> parentMapping = (COREMapping<?>) mapping.eContainer();
                parentFromElement = parentMapping.getFrom();
            }
            
            // Filter out all objects not contained in the external model...
            for (Iterator<?> iterator = result.iterator(); iterator.hasNext(); ) {
                EObject value = (EObject) iterator.next();
                
                // Null is also contained in the list...
                if (value != null) {
                    EObject objectContainer = EMFModelUtil.getRootContainerOfType(value, CorePackage.Literals.CORE_MODEL);
                    
                    // Make sure that the containing feature is part of this the class,
                    // to avoid exceptions.
                    boolean parentMapped = true;
                    
                    if (parentFromElement != null
                            && parentFromElement.eClass().getEAllStructuralFeatures()
                                    .contains(value.eContainingFeature())) {
                        parentMapped = ((List<?>) parentFromElement.eGet(value.eContainingFeature())).contains(value);
                    }
                    
                    // If it is not from the external model it should be filtered out...
                    if (externalModel != objectContainer
                            || !parentMapped
                            || shouldFilterValue(externalModel, (COREMapping<?>) mapping, value)) {
                        iterator.remove();
                    }
                }
            }
            
            return result;
        }
        
        /**
         * Returns whether the given object should be filtered out from the choice of values.
         * May be overridden by sub-classes to provide custom filtering.
         * 
         * @param externalModel the reused model
         * @param mapping the mapping
         * @param object the object
         * @return true, if the object should be filtered, false otherwise
         */
        protected boolean shouldFilterValue(COREModel externalModel, COREMapping<?> mapping, EObject object) {
            return false;
        }
        
    }
    
    /**
     * A (more or less) generic {@link ItemPropertyDescriptor} for to elements which can be used by sub-classes.
     * Overrides {@link ItemPropertyDescriptor#getChoiceOfValues(Object)} and filters out all objects that are not from the current aspect
     * as well as elements not contained in the element that is mapped in the containing mapping (e.g., operations not belonging to the mapped class).
     * 
     * @author mschoettle
     */
    public class MappingToItemPropertyDescriptor extends ItemPropertyDescriptor {
        
        public MappingToItemPropertyDescriptor(
                AdapterFactory adapterFactory,
                ResourceLocator resourceLocator,
                String displayName,
                String description,
                EStructuralFeature feature, 
                boolean isSettable,
                boolean multiLine,
                boolean sortChoices,
                Object staticImage,
                String category,
                String [] filterFlags) {
                    super(adapterFactory, 
                            resourceLocator, 
                            displayName, 
                            description, 
                            feature, 
                            isSettable, 
                            multiLine, 
                            sortChoices, 
                            staticImage, 
                            category, 
                            filterFlags);
        }
        
        @Override
        public Collection<?> getChoiceOfValues(Object object) {
            COREMapping<?> currentMapping = (COREMapping<?>) object;
            COREModel model = EMFModelUtil.getRootContainerOfType(currentMapping, CorePackage.Literals.CORE_MODEL);
            COREModelElement fromElement = currentMapping.getFrom();
            // The generic type of the Mapping this concrete Mapping is using...
            EClassifier genericType = getGenericType(currentMapping.eClass());
            
            if (fromElement == null) {
                return Collections.EMPTY_LIST;
            }
            
            // Get only all reachable objects of the correct type for this mapping...
            Collection<?> choiceOfValues = super.getReachableObjectsOfType(currentMapping, genericType);
            Collection<?> result = extendChoiceOfValues(currentMapping, choiceOfValues);
            
            COREModelElement parentToElement = null;
            if (currentMapping.eContainer() instanceof COREMapping<?>) {                
                COREMapping<?> parentMapping = (COREMapping<?>) currentMapping.eContainer();
                parentToElement = parentMapping.getTo();
            }
            
            for (Iterator<?> iterator = result.iterator(); iterator.hasNext();) {
                EObject value = (EObject) iterator.next();
                
                // Null is also contained in the list...
                if (value != null) {
                    // Make sure that the containing feature is part of this the class,
                    // to avoid exceptions.
                    boolean parentMapped = true;
                    
                    if (parentToElement != null
                            && parentToElement.eClass().getEAllStructuralFeatures()
                                    .contains(value.eContainingFeature())) {
                        parentMapped = ((List<?>) parentToElement.eGet(value.eContainingFeature())).contains(value);
                    }
                    
                    // Provide possibility for custom filter...
                    if (!parentMapped || shouldFilterValue(model, currentMapping, value)) {
                        iterator.remove();
                    }
                }
            }
            
            return result;
        }
        
        protected Collection<?> extendChoiceOfValues(COREMapping<?> mapping, Collection<?> choiceOfValues) {
            return choiceOfValues;
        }
        
        /**
         * Returns whether the given object should be filtered out from the choice of values.
         * May be overridden by sub-classes to provide custom filtering.
         * 
         * @param model the model the mapping is part of
         * @param mapping the mapping
         * @param object the object
         * @return true, if the object should be filtered, false otherwise
         */
        protected boolean shouldFilterValue(COREModel model, COREMapping<?> mapping, EObject object) {
            return false;
        }
        
    }    
    
    /**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public COREMappingItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /**
     * This returns the property descriptors for the adapted class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
        if (itemPropertyDescriptors == null) {
            super.getPropertyDescriptors(object);

            addToPropertyDescriptor(object);
            addFromPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the To feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addToPropertyDescriptorGen(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_COREMapping_to_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_COREMapping_to_feature", "_UI_COREMapping_type"),
                 CorePackage.Literals.CORE_MAPPING__TO,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null));
    }
    
    /**
     * This adds a property descriptor for the To feature.
     * <!-- begin-user-doc -->
     * @param object the object to add a property descriptor for
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addToPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(
            new MappingToItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_COREMapping_to_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_COREMapping_to_feature", "_UI_COREMapping_type"),
                 CorePackage.Literals.CORE_MAPPING__TO,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the From feature.
     * <!-- begin-user-doc -->
     * @param object the object to add a property descriptor for
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addFromPropertyDescriptorGen(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_COREMapping_from_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_COREMapping_from_feature", "_UI_COREMapping_type"),
                 CorePackage.Literals.CORE_MAPPING__FROM,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null));
    }
    
    /**
     * This adds a property descriptor for the From feature.
     * <!-- begin-user-doc -->
     * @param object the object to add a property descriptor for
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addFromPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(
            new MappingFromItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_COREMapping_from_feature"),
                 getString("_UI_PropertyDescriptor_description", 
                             "_UI_COREMapping_from_feature", "_UI_COREMapping_type"),
                 CorePackage.Literals.CORE_MAPPING__FROM,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null));
    }
    
    /**
     * Returns the first generic type of the given class's supertype.
     * 
     * @param eClass the {@link EClass} to retrieve the first generic type for
     * @return the first generic type of the given class's super type
     */
    private EClassifier getGenericType(EClass eClass) {
        // we know that there is only one super type 
        // and one type argument (of course it has to be adjusted if the meta-model gets changed)
        return eClass.getEGenericSuperTypes().get(0).getETypeArguments().get(0).getEClassifier();
    }

    /**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getText(Object object) {
        return getString("_UI_COREMapping_type");
    }

    /**
     * This handles model notifications by calling {@link #updateChildren} to update any cached
     * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void notifyChanged(Notification notification) {
        updateChildren(notification);

        switch (notification.getFeatureID(COREMapping.class)) {
            case CorePackage.CORE_MAPPING__TO:
            case CorePackage.CORE_MAPPING__FROM:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
                return;
        }
        super.notifyChanged(notification);
    }

    /**
     * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
     * that can be created under this object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);
    }

    /**
     * Return the resource locator for this item provider's resources.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ResourceLocator getResourceLocator() {
        return COREEditPlugin.INSTANCE;
    }

}
