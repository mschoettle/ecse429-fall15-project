/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ca.mcgill.sel.ram.provider;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.IViewerNotification;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.commons.emf.util.EMFModelUtil;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.AssociationEnd;
import ca.mcgill.sel.ram.Classifier;
import ca.mcgill.sel.ram.Instantiation;
import ca.mcgill.sel.ram.Interaction;
import ca.mcgill.sel.ram.InteractionFragment;
import ca.mcgill.sel.ram.Lifeline;
import ca.mcgill.sel.ram.Message;
import ca.mcgill.sel.ram.MessageOccurrenceSpecification;
import ca.mcgill.sel.ram.Operation;
import ca.mcgill.sel.ram.OperationType;
import ca.mcgill.sel.ram.Parameter;
import ca.mcgill.sel.ram.PrimitiveType;
import ca.mcgill.sel.ram.RAMVisibilityType;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.Reference;
import ca.mcgill.sel.ram.StructuralFeature;
import ca.mcgill.sel.ram.StructuralView;
import ca.mcgill.sel.ram.TemporaryProperty;
import ca.mcgill.sel.ram.Type;
import ca.mcgill.sel.ram.TypeParameter;
import ca.mcgill.sel.ram.TypedElement;
import ca.mcgill.sel.ram.util.RAMModelUtil;

/**
 * This is the item provider adapter for a {@link ca.mcgill.sel.ram.Lifeline} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class LifelineItemProvider
    extends ItemProviderAdapter
    implements
        IEditingDomainItemProvider,
        IStructuredItemContentProvider,
        ITreeItemContentProvider,
        IItemLabelProvider,
        IItemPropertySource {
    
    class ChangeListener implements INotifyChangedListener {
        public void notifyChanged(Notification notification) {
            
            if (notification.getNotifier() != null &&  getTarget() != null) {
                Object element = ((IViewerNotification) notification).getElement();
                TypedElement typedElement = ((Lifeline) getTarget()).getRepresents();
                
                
                if ((element instanceof TypedElement || element instanceof Type
                        || element instanceof TypeParameter)
                        && typedElement != null
                        && (notification.getNotifier() == typedElement 
                                || notification.getNotifier() == typedElement.getType()
                                || ((EObject) notification.getNotifier()).eContainer() == typedElement.getType())) {
                    ((IChangeNotifier) getAdapterFactory()).removeListener(this);
                    fireNotifyChanged(new ViewerNotification(notification, getTarget(), false, true));
                    ((IChangeNotifier) getAdapterFactory()).addListener(this);                
                }
                
            }
        }
    }
    
    private ChangeListener changeListener;
    
    /**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc -->
     * @param adapterFactory the adapter factory for this item provider
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public LifelineItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
        
        // The change listener is required to get notifications about referenced elements
        // and pass the notifications to its own listener.
        // I.e., The lifeline represents a type which could change its name.
        if (adapterFactory instanceof IChangeNotifier) {
            IChangeNotifier changeNotifier = (IChangeNotifier) adapterFactory;
            changeListener = new ChangeListener();
            changeNotifier.addListener(changeListener);
        }
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

            addRepresentsPropertyDescriptor(object);
            addCoveredByPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }
    
    /**
     * This adds a property descriptor for the Represents feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addRepresentsPropertyDescriptorGen(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Lifeline_represents_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_Lifeline_represents_feature", "_UI_Lifeline_type"),
                 RamPackage.Literals.LIFELINE__REPRESENTS,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Represents feature.
     * <!-- begin-user-doc -->
     * @param object the object to add a property descriptor for
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addRepresentsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(
            // CHECKSTYLE:IGNORE AnonInnerLength: Okay here.
            new ItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Lifeline_represents_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_Lifeline_represents_feature", 
                             // CHECKSTYLE:IGNORE MultipleStringLiterals: Outcome of code generator.
                             "_UI_Lifeline_type"),
                 RamPackage.Literals.LIFELINE__REPRESENTS,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null) {

                @Override
                public Collection<?> getChoiceOfValues(Object object) {
                    Lifeline lifeline = (Lifeline) object;
                    Interaction interaction = (Interaction) lifeline.eContainer();
                    Aspect aspect = EMFModelUtil.getRootContainerOfType(lifeline, RamPackage.Literals.ASPECT);
                    
                    Collection<EObject> result = collectChoiceOfRepresents(aspect, interaction, lifeline);
                    
                    if (result != null) {
                        return result;
                    }
                    
                    // There's no receive event, so do the regular stuff with filtering.
                    // TODO: mschoettle: Only show valid choices 
                    // (accessible from all current lifelines, including their local properties) 
                    // and minus already existing lifelines
                    // TODO: maybe filter out parameters that are not coming from any message that is called 
                    // (look at receive events)
                    // TODO: should non-navigable association ends be filtered out?
                    Collection<?> choiceOfValues = super.getChoiceOfValues(object);
                    
                    // filter out all objects that don't belong to this interaction
                    for (Iterator<?> iterator = choiceOfValues.iterator(); iterator.hasNext();) {
                        EObject next = (EObject) iterator.next();
                        
                        if (next != null) {
                            // the container can be either the interaction or the structural view
                            EObject container = EMFModelUtil.getRootContainerOfType(next, 
                                    RamPackage.Literals.INTERACTION);
                            
                            // if the TypedElement is contained by the interaction
                            // filter out all elements that don't belong to this interaction
                            if (container != null && interaction != container) {
                                iterator.remove();
                            } else if (container == null) {
                                // if the TypedElement is contained by the StructuralView
                                // filter out all elements that don't belong to this aspect
                                container = EMFModelUtil.getRootContainerOfType(next, RamPackage.Literals.ASPECT);
                                
                                if (container != null && aspect != container) {
                                    iterator.remove();
                                }
                            }
                        }
                    }
                    
                    return choiceOfValues;
                }
                
                @Override
                public IItemLabelProvider getLabelProvider(Object object) {
                    return new IItemLabelProvider() {
                        
                        @Override
                        public String getText(Object object) {
                            if (object != null) {
                                String text = EMFEditUtil.getTypeName((EObject) object) + " ";
                                Type type;
                                
                                if (object instanceof Classifier) {
                                    type = (Classifier) object;
                                    text += "<<metaclass>> ";
                                } else {
                                    TypedElement typedElement = (TypedElement) object;
                                    type = typedElement.getType();
                                    text += typedElement.getName();
                                }
                                
                                text += ":" + EMFEditUtil.getTextFor(getAdapterFactory(), type);
    
                                return text;
                            }
                            
                            return itemDelegator.getText(object);
                        }
                        
                        @Override
                        public Object getImage(Object object) {
                            return itemDelegator.getImage(object);
                        }
                    };
                }
                
            });
    }
    
    /**
     * This adds a property descriptor for the Covered By feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addCoveredByPropertyDescriptorGen(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Lifeline_coveredBy_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_Lifeline_coveredBy_feature", "_UI_Lifeline_type"),
                 RamPackage.Literals.LIFELINE__COVERED_BY,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Covered By feature.
     * <!-- begin-user-doc -->
     * @param object the object to add a property descriptor for
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addCoveredByPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(
            new ItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Lifeline_coveredBy_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_Lifeline_coveredBy_feature", "_UI_Lifeline_type"),
                 RamPackage.Literals.LIFELINE__COVERED_BY,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null) {

                @Override
                public Collection<?> getChoiceOfValues(Object object) {
                    Lifeline lifeline = (Lifeline) object;
                    
                    Interaction interaction = (Interaction) lifeline.eContainer();
                    
                    // since it is a many feature no null value is needed in the result
                    Collection<EObject> result = new ArrayList<EObject>();
                    TreeIterator<EObject> treeIterator = EcoreUtil.getAllProperContents(interaction, true);
                    
                    while (treeIterator.hasNext()) {
                        EObject next = treeIterator.next();
                        
                        if (RamPackage.Literals.INTERACTION_FRAGMENT.isInstance(next)) {
                            result.add(next);
                        }
                    }
                    
                    return result;
                }
                
            });
    }

    /**
     * This returns Lifeline.gif.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/Lifeline"));
    }

    /**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
     * @param object the object a textual representation to get for
     * @return the textual representation for the given object
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public String getText(Object object) {
        Lifeline lifeline = (Lifeline) object;
        String label = "";
        TypedElement typedElement = lifeline.getRepresents();
        
        if (typedElement != null) {
            // If the Lifeline is represented by a static feature 
            // we don't want to see the name of it, but have "<<metaclass>>".
            boolean metaClass = typedElement instanceof StructuralFeature 
                                    && ((StructuralFeature) typedElement).isStatic();
            
            if (!metaClass) {
                label = typedElement.getName();
            }
            
            label += ":";
            
            Type type = lifeline.getRepresents().getType();
            if (type != null) {
                label += EMFEditUtil.getTextFor(getAdapterFactory(), type);
            } else {
                label += "null";
            }
        }
        
        return label == null || label.length() == 0 
                ? getString("_UI_Lifeline_type") 
                : getString("_UI_Lifeline_type") + " " + label;
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

        switch (notification.getFeatureID(Lifeline.class)) {
            case RamPackage.LIFELINE__REPRESENTS:
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
        return RAMEditPlugin.INSTANCE;
    }
    
    @Override
    public void dispose() {
        super.dispose();
        
        if (changeListener != null) {
            ((IChangeNotifier)getAdapterFactory()).removeListener(changeListener);
        }
    }
    
    /**
     * Collects all valid choices of represents for the given lifeline.
     * There will only be a valid choice if the lifeline receives a message.
     * Otherwise it cannot be determined which choices are valid or not.
     * Collects association ends, passed parameters, local properties and classes with static operations.
     * 
     * @param aspect the aspect containing all objects
     * @param interaction the interaction of the lifeline
     * @param lifeline the lifeline valid choices for represents to find
     * @return a collection of valid choices, null if there are none
     */
    private Collection<EObject> collectChoiceOfRepresents(Aspect aspect, Interaction interaction, Lifeline lifeline) {
        Collection<EObject> result = new UniqueEList<EObject>();
        
        // See if there is a receive event on this lifeline.
        if (lifeline.getCoveredBy().size() > 0) {
            InteractionFragment fragment = lifeline.getCoveredBy().get(0);
            
            if (fragment instanceof MessageOccurrenceSpecification) {
                MessageOccurrenceSpecification messageEnd = (MessageOccurrenceSpecification) fragment;
                
                if (messageEnd.getMessage() != null
                        && messageEnd.getMessage().getReceiveEvent() == messageEnd) {
                    MessageOccurrenceSpecification sendEvent = 
                            (MessageOccurrenceSpecification) messageEnd.getMessage().getSendEvent();
                    Lifeline lifelineFrom = sendEvent.getCovered().get(0);
                    
                    // Collect accessible types from represents.
                    if (lifelineFrom.getRepresents() != null
                            && lifelineFrom.getRepresents().getType() instanceof Classifier) {
                        Classifier classifier = (Classifier) lifelineFrom.getRepresents().getType();
                        
                        // Collect lower level classes that are mapped to this classifier.
                        Set<Classifier> classifiers = RAMModelUtil.collectClassifiersFor(aspect, classifier);
                        classifiers.add(classifier);
                        
                        for (Classifier currentClassifier : classifiers) {
                            for (AssociationEnd associationEnd : currentClassifier.getAssociationEnds()) {
                                if (associationEnd.isNavigable()) {
                                    result.add(associationEnd);
                                }
                            }
                        }
                    }
                    
                    // Collect passed parameters.
                    Message initialMessage = RAMModelUtil.findInitialMessage(sendEvent);
                    
                    if (initialMessage != null) {
                        if (initialMessage.getSignature() != null) {
                            for (Parameter parameter : initialMessage.getSignature().getParameters()) {
                                if (!(parameter.getType() instanceof PrimitiveType)) {
                                    result.add(parameter);
                                }
                            }
                        }
                        
                        // Collect local properties.
                        for (TemporaryProperty property : initialMessage.getLocalProperties()) {
                            if (property instanceof Reference) {
                                result.add(property);
                            }
                        }
                    }
                    
                    // Collect metaclasses.
                    Set<Aspect> includedAspects = RAMModelUtil.collectExtendedAspects(aspect);
                    includedAspects.add(aspect);
                    
                    for (Aspect currentAspect : includedAspects) {
                        for (Classifier classifier : currentAspect.getStructuralView().getClasses()) {
                            for (Operation operation : classifier.getOperations()) {
                                if (operation.isStatic() || operation.getOperationType() == OperationType.CONSTRUCTOR) {
                                    Classifier realClassifier = RAMModelUtil.resolveClassifier(aspect, classifier);
                                    result.add(realClassifier);
                                    break;
                                }
                            }
                        }
                    }
                    
                    // Filter out existing lifelines.
                    for (Lifeline existingLifeline : interaction.getLifelines()) {
                        result.remove(existingLifeline.getRepresents());
                    }
                    
                    // Add classes from reused concerns with public operations.
                    for (Instantiation instantiation : RAMModelUtil.collectAllReuseInstantiations(aspect)) {
                        StructuralView structuralView = instantiation.getSource().getStructuralView();
                        for (Classifier classifier : structuralView.getClasses()) {
                            for (Operation operation : classifier.getOperations()) {
                                if (operation.getExtendedVisibility() == RAMVisibilityType.PUBLIC
                                        && (operation.isStatic()
                                                || operation.getOperationType() == OperationType.CONSTRUCTOR)) {
                                    Classifier realClassifier = RAMModelUtil.resolveClassifier(aspect, classifier);
                                    result.add(realClassifier);
                                    break;
                                }
                            }
                        }
                    }
                    
                    result.add(null);
                    
                    return result;
                }
            }
        }
        
        return null;
    }

}
