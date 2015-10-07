package ca.mcgill.sel.core.util;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import ca.mcgill.sel.commons.FileManagerUtil;
import ca.mcgill.sel.commons.StringUtil;
import ca.mcgill.sel.commons.emf.util.AdapterFactoryRegistry;
import ca.mcgill.sel.commons.emf.util.EMFModelUtil;
import ca.mcgill.sel.commons.emf.util.ResourceManager;
import ca.mcgill.sel.core.COREConcern;
import ca.mcgill.sel.core.COREFeature;
import ca.mcgill.sel.core.COREFeatureModel;
import ca.mcgill.sel.core.COREFeatureRelationshipType;
import ca.mcgill.sel.core.COREModel;
import ca.mcgill.sel.core.COREModelReuse;
import ca.mcgill.sel.core.CORENamedElement;
import ca.mcgill.sel.core.COREReuse;
import ca.mcgill.sel.core.CoreFactory;
import ca.mcgill.sel.core.CorePackage;

/**
 * Helper class with convenient static methods for working with CORE EMF model objects.
 *
 * @author mschoettle
 */
public final class COREModelUtil {

    private static final String REUSED_PREFIX = "Reused_";

    /**
     * Creates a new instance of {@link COREModelUtil}.
     */
    private COREModelUtil() {
        // suppress default constructor
    }

    /**
     * Creates a new {@link COREConcern} with an empty {@link COREFeatureModel} (one root feature).
     *
     * @param name the name of the concern
     * @return the newly created {@link Aspect}
     */
    public static COREConcern createConcern(String name) {
        COREConcern concern = CoreFactory.eINSTANCE.createCOREConcern();
        concern.setName(name);

        // Create a Feature Model with a root feature.
        COREFeatureModel featureModel = CoreFactory.eINSTANCE.createCOREFeatureModel();

        COREFeature rootFeature = CoreFactory.eINSTANCE.createCOREFeature();
        rootFeature.setName(name);
        rootFeature.setParentRelationship(COREFeatureRelationshipType.NONE);

        featureModel.getFeatures().add(rootFeature);
        featureModel.setRoot(rootFeature);

        concern.setFeatureModel(featureModel);
        concern.getModels().add(featureModel);

        return concern;
    }

    /**
     * Create concern in local folder.
     *
     * @param concern The concern being reused
     * @param uri The uri to the folder
     * @return The core concern to assign
     */
    public static COREConcern createConcernCopy(COREConcern concern, URI uri) {
        String destDirectory = uri.trimSegments(1).toFileString();
        String srcDirectory = concern.eResource().getURI().trimSegments(1).toFileString();

        if (srcDirectory.equals(destDirectory)) {
            return null;
        }

        String mainFolder = concern.eResource().getURI().trimSegments(2).toFileString();

        if (!mainFolder.equals(destDirectory)) {
            return copyDirectory(concern, destDirectory);
        } else {
            return concern;
        }
    }

    /**
     * Function called to delete the folder of a reused concern in the file system.
     * This should only on a concern that was previously obtained through CoreModelUtil.createConcernCopy.
     * Before attempting the deletion, the method checks if the concern is still used by a
     * reuse in the concern that made the reuse in the first place.
     *
     * @param concern - The concern whose directory we want to delete.
     * @param reusingConcern - The concern that created the folder we want to delete.
     * @return true if the directory was deleted, false otherwise.
     */
    public static boolean deleteReusedConcernDirectory(COREConcern concern, COREConcern reusingConcern) {
        File toDeleteDirectory = new File(concern.eResource().getURI().toFileString()).getParentFile();

        // Check for basic invalid parameters.
        if (concern == null || reusingConcern == null || EcoreUtil.equals(concern, reusingConcern)
                || concern.eResource() == null || !toDeleteDirectory.getName().startsWith(REUSED_PREFIX)) {
            return false;
        }

        // Check if the concern to delete is used somewhere in the reusingConcern.
        for (COREFeature feature : reusingConcern.getFeatureModel().getFeatures()) {
            for (COREReuse reuse : feature.getReuses()) {
                if (EcoreUtil.equals(reuse.getReusedConcern(), concern)) {
                    return false;
                }
            }
        }

        // We can delete the concern's folder.
        boolean success = false;
        try {
            FileManagerUtil.deleteFile(toDeleteDirectory, true);
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return success;
    }

    /**
     * Function called when a director is to be called when a reuse is made.
     *
     * @param concern - The concern whose feature is reusing another concern.
     * @param destPath - The path to the folder.
     * @return concernCopied - The concern which was copied.
     */
    public static COREConcern copyDirectory(COREConcern concern, String destPath) {

        File destDirectory = new File(destPath.concat(File.separator).concat(REUSED_PREFIX).concat(concern.getName()));

        URI concernUri = concern.eResource().getURI();
        File srcDirectory = new File(concernUri.trimSegments(1).toFileString());
        String fileName = concernUri.toFileString().replace(srcDirectory.getAbsolutePath() + File.separator, "");

        try {
            FileManagerUtil.copyDirectory(srcDirectory, destDirectory);

            COREConcern concernToReturn = (COREConcern) ResourceManager.loadModel(destDirectory.getAbsolutePath()
                    .concat(File.separator + fileName));

            return concernToReturn;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Get all the model reuses for a given {@link COREReuse} in a concern.
     *
     * @param coreReuse - The {@link COREReuse} to delete.
     * @return the set of modelReuses of this reuse.
     */
    public static Set<COREModelReuse> getModelReuses(COREReuse coreReuse) {
        COREConcern reusingConcern = (COREConcern) coreReuse.eContainer().eContainer().eContainer();
        return getModelReuses(reusingConcern, coreReuse);
    }

    /**
     * Get all the model reuses for a given {@link COREReuse} in a concern.
     *
     * @param concern - The concern that made the {@link COREReuse}.
     * @param coreReuse - The {@link COREReuse} to delete.
     * @return the set of modelReuses of this reuse.
     */
    public static Set<COREModelReuse> getModelReuses(COREConcern concern, COREReuse coreReuse) {
        Set<COREModelReuse> modelReuses = new HashSet<COREModelReuse>();
        if (concern == null || coreReuse == null) {
            return modelReuses;
        }
        for (COREModel model : concern.getModels()) {
            for (COREModelReuse modelReuse : model.getModelReuses()) {
                if (EcoreUtil.equals(modelReuse.getReuse(), coreReuse)) {
                    modelReuses.add(modelReuse);
                }
            }
        }
        return modelReuses;
    }

    /**
     * Generate a unique name based on the given name and the name of already existing elements.
     *
     * @param baseName - The default name to give.
     * @param elements - The elements to compare the name to.
     * @return The newly created name. Of form baseName{int}
     *
     * @param <T> The type of the elements to compare the name to.
     */
    public static <T extends CORENamedElement> String createUniqueNameFromElements(String baseName,
            Collection<T> elements) {
        Set<String> names = new HashSet<String>();

        for (CORENamedElement namedElement : elements) {
            names.add(namedElement.getName());
        }

        return StringUtil.createUniqueName(baseName, names);
    }

    /**
     * Function called to collect all models realized by the selected Features in the diagram.
     *
     * @param selectedFeatures The set of selected features
     * @param modelType The type of COREModel (e.g. Aspect)
     * @param <T> The type of COREModel
     * @return SetofModels
     */
    public static <T extends COREModel> Set<T> getRealizationModels(Set<COREFeature> selectedFeatures,
            EClassifier modelType) {
        Set<T> result = new HashSet<T>();
        for (COREFeature feature : selectedFeatures) {
            Collection<T> realizingModels = EMFModelUtil.collectElementsOfType(feature,
                    CorePackage.Literals.CORE_FEATURE__REALIZED_BY, modelType);
            loop: for (T model : realizingModels) {
                for (COREFeature realizedFeature : model.getRealizes()) {
                    if (!selectedFeatures.contains(realizedFeature)) {
                        continue loop;
                    }
                }
                result.add(model);
            }
        }
        return resolveConflicts(result);
    }

    /**
     * Resolves conflicts when there are models that realize more than
     * one feature. It loops through the selected models and finds for
     * each model if its set of realizing features includes the set of
     * realizing features for another model. If this is the case, it
     * removes the other model.
     *
     * @param selectedModels the selected features of the concern.
     * @param <T> The type of COREModel
     * @return set of selected aspects after resolving conflicts.
     */
    private static <T extends COREModel> Set<T> resolveConflicts(Set<T> selectedModels) {
        Set<T> resolvedModels = new HashSet<T>();
        outerloop: for (T selectedModel : selectedModels) {
            Iterator<T> iterator = resolvedModels.iterator();
            // Intermediate set used to not add to the set while iterating through it
            Set<T> intermediate = new HashSet<T>();
            while (iterator.hasNext()) {
                T resolvedModel = iterator.next();
                EList<COREFeature> selectedFeatures = selectedModel.getRealizes();
                EList<COREFeature> resolvedFeatures = resolvedModel.getRealizes();
                if (selectedFeatures.containsAll(resolvedFeatures)) {
                    iterator.remove();
                    intermediate.add(selectedModel);
                } else if (resolvedFeatures.containsAll(selectedFeatures)) {
                    continue outerloop;
                } else {
                    /**
                     * This means that the user made a bad configuration, because
                     * some of the features that are realized by a model are also realized
                     * by another model.
                     */
                    for (COREFeature unresolved : resolvedFeatures) {
                        if (selectedFeatures.contains(unresolved)) {
                            return null;
                        }
                    }
                    for (COREFeature unresolved : selectedFeatures) {
                        if (resolvedFeatures.contains(unresolved)) {
                            return null;
                        }
                    }
                }
            }
            resolvedModels.addAll(intermediate);
            intermediate.clear();
            resolvedModels.add(selectedModel);
        }
        return resolvedModels;
    }

    /**
     * Dispose the adapter factory for this EObject and unload his resource.
     *
     * @param eobjectToUnload The EObject to unload.
     */
    public static void unloadEObject(EObject eobjectToUnload) {
        AdapterFactoryRegistry.INSTANCE.disposeAdapterFactoryFor(eobjectToUnload);
        ResourceManager.unloadResource(eobjectToUnload.eResource());
    }

}
