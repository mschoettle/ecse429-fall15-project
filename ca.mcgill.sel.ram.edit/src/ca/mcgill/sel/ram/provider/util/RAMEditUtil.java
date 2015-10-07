package ca.mcgill.sel.ram.provider.util;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.commons.emf.util.EMFModelUtil;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.Classifier;
import ca.mcgill.sel.ram.ClassifierMapping;
import ca.mcgill.sel.ram.Instantiation;
import ca.mcgill.sel.ram.Lifeline;
import ca.mcgill.sel.ram.Message;
import ca.mcgill.sel.ram.Operation;
import ca.mcgill.sel.ram.OperationMapping;
import ca.mcgill.sel.ram.Parameter;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.StructuralFeature;
import ca.mcgill.sel.ram.TemporaryProperty;
import ca.mcgill.sel.ram.Type;
import ca.mcgill.sel.ram.TypeParameter;
import ca.mcgill.sel.ram.TypedElement;
import ca.mcgill.sel.ram.provider.RAMEditPlugin;
import ca.mcgill.sel.ram.util.RAMModelUtil;

/**
 * Helper class with convenient static methods for working with EMF objects.
 * 
 * @author mschoettle
 */
public final class RAMEditUtil {
    
    /**
     * Creates a new instance of {@link RAMEditUtil}.
     */
    private RAMEditUtil() {
        // suppress default constructor         
    }
    
    /**
     * Returns the complete signature of the given {@link Operation} including parameters.
     * 
     * @param adapterFactory the {@link AdapterFactory} to use
     * @param operation the {@link Operation} a signature should be returned for
     * @param includeClassName whether the class name should be included in the signature (at the beginning)
     * @return the complete signature of the given {@link Operation}
     */
    public static String getOperationSignature(AdapterFactory adapterFactory, Operation operation, 
                                                boolean includeClassName) {
        StringBuffer stringBuffer = new StringBuffer();
        
        stringBuffer.append(RAMEditPlugin.INSTANCE.getString("_UI_Operation_type"));
        stringBuffer.append(" ");
        
        if (includeClassName) {
            stringBuffer.append(EMFEditUtil.getTextFor(adapterFactory, operation.eContainer()));
            stringBuffer.append(".");
        }
        
        stringBuffer.append(EMFEditUtil.getTextFor(adapterFactory, operation));
        stringBuffer.append("(");
        
        for (Parameter parameter : operation.getParameters()) {
            // if it is not the first one, add a ","
            if (operation.getParameters().indexOf(parameter) > 0) {
                stringBuffer.append(", ");
            }
            
            stringBuffer.append(EMFEditUtil.getTextFor(adapterFactory, parameter.getType()));
            stringBuffer.append(" ");
            stringBuffer.append(EMFEditUtil.getTextFor(adapterFactory, parameter));
        }
        
        stringBuffer.append(")");
        
        return stringBuffer.toString();
    }
    
    /**
     * Filters the choice of values for the given object. 
     * Removes all values that are not part of the same aspect
     * or any other aspect that is extended by the aspect of the given object. 
     * 
     * @see RAMModelUtil#collectExtendedAspects(Aspect)
     * @param object the object that contains a property with the choices
     * @param choiceOfValues the choice of values to be filtered
     * @return the filtered choice of values
     */
    public static Collection<?> filterExtendedChoiceOfValues(EObject object, Collection<?> choiceOfValues) {
        Aspect aspect = EMFModelUtil.getRootContainerOfType(object, RamPackage.Literals.ASPECT);
        if (aspect == null) {
            return choiceOfValues;
        }
        // Create a set of aspects where elements are allowed from.
        // Besides the current aspect, add instantiated aspects 
        // where the type of the instantiation is 'extends'.
        Set<Aspect> includedAspects = RAMModelUtil.collectExtendedAspects(aspect);
        includedAspects.add(aspect);
        
        // Filter out all classes that are not contained in one of the possible aspects.
        // Also filter out all types that are not part of the current aspect.
        for (Iterator<?> iterator = choiceOfValues.iterator(); iterator.hasNext();) {
            EObject value = (EObject) iterator.next();
            
            // null is also contained in the list
            if (value != null) {
                EObject objectContainer = EcoreUtil.getRootContainer(value);
                
                if (!includedAspects.contains(objectContainer)) {
                    iterator.remove();
                }                             
            }
        }
        
        return choiceOfValues;
    }
    
    /**
     * Filters the list of all operations to a list of callable operations on the given receiver.
     * The given collection is directly modified and all operations that cannot be called on the receiver are
     * removed from it.
     *  
     * @param operations the list of all operations found
     * @param aspect the current aspect
     * @param receiver the receiver the operation is called on
     */
    public static void filterCallableOperations(Collection<?> operations, Aspect aspect, Classifier receiver) {
        Set<Classifier> callReceivers = Collections.emptySet();
        
        if (receiver != null) {
            callReceivers = RAMModelUtil.collectClassifiersFor(aspect, receiver);            
        }
        
        
        Set<Instantiation> instantiations = RAMModelUtil.collectExtendsInstantiations(aspect);
        instantiations.addAll(RAMModelUtil.collectAllReuseInstantiations(aspect));
        Set<Operation> mappedFromOperations = new HashSet<Operation>();
        
        for (Instantiation instantiation : instantiations) {
            for (ClassifierMapping mapping : instantiation.getMappings()) {
                if (callReceivers.contains(mapping.getFrom())) {
                    for (OperationMapping operationMapping : mapping.getOperationMappings()) {
                        mappedFromOperations.add(operationMapping.getFrom());
                    }
                }
            }
        }
        
        // Filter out all operations that are not part of the classifiers in the callReceivers list.
        // The call receivers are the given receiver itself, super types, mapped classes and extended classes.
        for (Iterator<?> iterator = operations.iterator(); iterator.hasNext();) {
            Operation value = (Operation) iterator.next();
            
            // null is also contained in the list
            if (value != null) {
                if (!callReceivers.contains(value.eContainer())
                        || mappedFromOperations.contains(value)) {
                    iterator.remove();
                }
            }
        }        
    }
    
    /**
     * Collects all possible structural features.
     * Valid choices must be a {@link StructuralFeature} and be a structural feature of the
     * classifier that is represented by the lifeline (or any classifier that is the same classifier,
     * e.g., through inheritance, mapping etc.) a fragment is located on.
     * Also, local properties of the initial message are valid as well.
     * 
     * @param aspect the {@link Aspect} containing the given objects
     * @param lifeline the lifeline for whose represented classifier the structural features are of interest
     * @param initialMessage the message that is defined, which contains the local properties
     * @param currentValue the current structural feature that is set
     * @return the filtered list of choices
     */
    public static Collection<?> collectStructuralFeatures(Aspect aspect, Lifeline lifeline, 
                    Message initialMessage, StructuralFeature currentValue) {
        Collection<Object> result = new UniqueEList<Object>();
        result.add(null);
        
        if (lifeline == null || initialMessage == null) {
            return result;
        }
        
        /**
         * Build a list of structural features of all represented classes.
         */        
        TypedElement representedType = lifeline.getRepresents();
        
        if (representedType != null && representedType.getType() != null 
                && representedType.getType() instanceof Classifier) {
            Classifier type = (Classifier) representedType.getType();
            
            Set<Classifier> classifiers = RAMModelUtil.collectClassifiersFor(aspect, type);
            
            for (Classifier classifier : classifiers) {
                TreeIterator<EObject> contents = EcoreUtil.getAllProperContents(classifier, true);
                
                while (contents.hasNext()) {
                    Object next = contents.next();
                    
                    if (next instanceof StructuralFeature) {
                        result.add(next);
                    }
                }                                
                
            }
        }
        
        for (TemporaryProperty property : initialMessage.getLocalProperties()) {
            result.add(property);
        }
        
        return result;
    }
    
    /**
     * Returns the name of the given type.
     * In case of a classifier with generics, they are included. In addition, if a generic type was mapped,
     * the "mapped to" type is used instead for nicer visualization.
     * 
     * @param adapterFactory the {@link AdapterFactory} to use
     * @param aspect the current {@link Aspect}
     * @param type the {@link Type} to get the name for
     * @return the name of the given type, including generics in case of a {@link Classifier}
     */
    public static String getTypeName(AdapterFactory adapterFactory, Aspect aspect, Type type) {
        if (type instanceof Classifier) {
            Classifier classifier = (Classifier) type;
            StringBuffer result = new StringBuffer(classifier.getName());
            
            if (!classifier.getTypeParameters().isEmpty()) {
                result.append("<");
                
                for (int i = 0; i < classifier.getTypeParameters().size(); i++) {
                    if (i > 0) {
                        result.append(", ");
                    }
                    
                    TypeParameter typeParameter = classifier.getTypeParameters().get(i);
                    if (typeParameter.getGenericType() != null) {
                        Type genericType = typeParameter.getGenericType();
                        if (genericType instanceof Classifier) {
                            genericType = RAMModelUtil.resolveClassifier(aspect, (Classifier) genericType);
                        }
                        result.append(EMFEditUtil.getTextFor(adapterFactory, genericType));
                    } else {
                        result.append(typeParameter.getName());
                    }
                }
                
                result.append(">");
            }
            
            return result.toString();
        }
        
        return EMFEditUtil.getTextFor(adapterFactory, type);
    }
    
}
