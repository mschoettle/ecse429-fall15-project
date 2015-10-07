package ca.mcgill.sel.commons.emf.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * Helper class with convenient static methods for working with EMF objects.
 *
 * @author mschoettle
 */
public final class EMFModelUtil {
    /**
     * Creates a new EMF Model Util instance.
     */
    private EMFModelUtil() {
        // Suppress and hide default constructor.
    }

    /**
     * Returns the next container object in the hierarchy of the given object that {@link EClassifier#isInstance is an
     * instance} of the type.
     *
     * @param eObject the child object to check
     * @param type the type of container to find
     * @param <T> the expected return type
     *
     * @return the first container object of the given type, null if none found
     */
    public static <T extends EObject> T getRootContainerOfType(EObject eObject, EClassifier type) {
        if (eObject != null) {
            EObject currentObject = eObject;

            while (currentObject.eContainer() != null) {
                currentObject = currentObject.eContainer();

                if (type.isInstance(currentObject)) {
                    @SuppressWarnings("unchecked")
                    T typed = (T) currentObject;
                    return typed;
                }
            }
        }

        return null;
    }

    /**
     * Retrieves the entry for the given container from the given {@link EMap}.
     *
     * @param map the map
     * @param container the container the entry is searched for
     * @param <T> the expected return type
     *
     * @return the {@link Entry} of the given container
     */
    public static <T extends EObject> T getEntryFromMap(EMap<?, ?> map, EObject container) {
        for (Entry<?, ?> entry : map.entrySet()) {
            if (entry.getKey() == container) {
                if (entry instanceof EObject) {
                    @SuppressWarnings("unchecked")
                    T mapType = (T) entry;
                    return mapType;
                }
            }
        }

        return null;
    }

    /**
     * Collect all the elements of a given type for the given feature of the given element.
     *
     * @param container - The containing object.
     * @param feature - The feature to get the objects from.
     * @param type - The wished type.
     * @param <T> - Generic type of returned list of elements
     * @return The set of models of the concern.
     */
    public static <T> Collection<T> collectElementsOfType(EObject container, EStructuralFeature feature,
            EClassifier type) {
        if (container == null || feature == null || type == null) {
            return new HashSet<T>();
        }
        Object elements = container.eGet(feature);
        if (elements instanceof Collection<?>) {
            return EcoreUtil.getObjectsByType((Collection<?>) elements, type);
        }
        return new HashSet<T>();
    }

    /**
     * Returns whether the given object of interest is referenced somewhere as a value of the given feature.
     * Checks the complete hierarchy that the object is contained in.
     *
     * @param objectOfInterest the object of interest
     * @param feature the feature the object of interest is referenced in
     * @return true, if at least one reference to object of interest exists, false it is not referenced
     */
    public static boolean referencedInFeature(EObject objectOfInterest, EStructuralFeature feature) {
        EObject root = EcoreUtil.getRootContainer(objectOfInterest);

        Collection<Setting> crossReferences = EcoreUtil.UsageCrossReferencer.find(objectOfInterest, root);

        for (Setting crossReference : crossReferences) {
            if (crossReference.getEStructuralFeature() == feature) {
                return true;
            }
        }

        return false;
    }

}
