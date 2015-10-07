package ca.mcgill.sel.ram.controller;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.core.COREConcern;
import ca.mcgill.sel.core.COREFeature;
import ca.mcgill.sel.core.COREModelReuse;
import ca.mcgill.sel.core.COREReuse;
import ca.mcgill.sel.core.COREReuseConfiguration;
import ca.mcgill.sel.core.CoreFactory;
import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.core.util.COREModelUtil;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.Association;
import ca.mcgill.sel.ram.AssociationEnd;
import ca.mcgill.sel.ram.Classifier;
import ca.mcgill.sel.ram.ClassifierMapping;
import ca.mcgill.sel.ram.Instantiation;
import ca.mcgill.sel.ram.Operation;
import ca.mcgill.sel.ram.OperationMapping;
import ca.mcgill.sel.ram.RamFactory;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.ReferenceType;
import ca.mcgill.sel.ram.util.AssociationConstants;

/**
 * The controller for {@link Association}s and {@link AssociationEnd}s.
 *
 * @author mschoettle
 * @author cbensoussan
 */
public class AssociationController extends BaseController {

    /**
     * Creates a new instance of {@link AssociationController}.
     */
    protected AssociationController() {
        // Prevent anyone outside this package to instantiate.
    }

    /**
     * Sets the multiplicity of the given {@link AssociationEnd} according to the given lower and upper bound.
     *
     * @param associationEnd the {@link AssociationEnd} the multiplicity should be changed for
     * @param aspect the current aspect
     * @param lowerBound the lower bound of the association end
     * @param upperBound the upper bound of the association end, -1 for many
     */
    public void setMultiplicity(AssociationEnd associationEnd, Aspect aspect, int lowerBound, int upperBound) {
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(associationEnd);

        CompoundCommand compoundCommand = new CompoundCommand();

        compoundCommand.append(SetCommand.create(editingDomain, associationEnd,
                RamPackage.Literals.PROPERTY__LOWER_BOUND, lowerBound));
        compoundCommand.append(SetCommand.create(editingDomain, associationEnd,
                RamPackage.Literals.PROPERTY__UPPER_BOUND, upperBound));

        // If feature selection of association end exists, remove it if upper bound is now 1
        if (associationEnd.getFeatureSelection() != null && upperBound == 1) {
            compoundCommand.append(SetCommand.create(editingDomain, associationEnd,
                    RamPackage.Literals.ASSOCIATION_END__FEATURE_SELECTION, SetCommand.UNSET_VALUE));
            deleteFeatureSelection(aspect, associationEnd, compoundCommand);
        }

        doExecute(editingDomain, compoundCommand);
    }

    /**
     * Sets the selected features of the given {@link AssociationEnd} according to the given set of features.
     *
     * @param associationEnd the {@link AssociationEnd} the selected features should be changed for
     * @param selectedFeatures the set of selected features from the association concern
     * @param concern the association concern
     * @param aspect The current aspect
     * @param wovenAspect The woven aspect
     */
    public void setFeatureSelection(AssociationEnd associationEnd, Set<COREFeature> selectedFeatures,
            COREConcern concern, Aspect aspect, Aspect wovenAspect) {
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(associationEnd);
        CompoundCommand compoundCommand = new CompoundCommand();

        if (selectedFeatures.size() != 0) {
            // Get the list of other reuses (used to generate a unique name for the reuse)
            Set<COREReuse> reuses = new HashSet<COREReuse>();
            for (COREFeature feature : aspect.getRealizes()) {
                reuses.addAll(feature.getReuses());
            }
            COREReuseConfiguration selectedConfiguration = CoreFactory.eINSTANCE.createCOREReuseConfiguration();
            selectedConfiguration.setName("User selection");
            selectedConfiguration.getSelected().addAll(selectedFeatures);
            selectedConfiguration.setSource(concern.getFeatureModel());
            COREReuse reuse = CoreFactory.eINSTANCE.createCOREReuse();
            reuse.setReusedConcern(concern);
            reuse.getConfigurations().add(selectedConfiguration);
            reuse.setSelectedConfiguration(selectedConfiguration);

            // Set the name to Association_FromClass_ToClass[0-9]*
            String name = "";
            for (AssociationEnd end : associationEnd.getAssoc().getEnds()) {
                if (end == associationEnd) {
                    name = end.getClassifier().getName() + "_" + name;
                } else {
                    name += end.getClassifier().getName();
                }
            }
            name = concern.getName() + "_" + name;
            name = COREModelUtil.createUniqueNameFromElements(name, reuses);
            reuse.setName(name);

            COREModelReuse modelReuse = CoreFactory.eINSTANCE.createCOREModelReuse();
            modelReuse.setReuse(reuse);

            compoundCommand.append(AddCommand.create(editingDomain, aspect,
                    CorePackage.Literals.CORE_MODEL__MODEL_REUSES, modelReuse));

            for (COREFeature feature : aspect.getRealizes()) {
                compoundCommand.append(AddCommand.create(editingDomain, feature,
                        CorePackage.Literals.CORE_FEATURE__REUSES, reuse));
            }

            createReuseInstantiation(aspect, wovenAspect, associationEnd, compoundCommand, modelReuse);

            compoundCommand.append(SetCommand.create(editingDomain, associationEnd,
                    RamPackage.Literals.ASSOCIATION_END__FEATURE_SELECTION, modelReuse));

        } else {
            compoundCommand.append(SetCommand.create(editingDomain, associationEnd,
                  RamPackage.Literals.ASSOCIATION_END__FEATURE_SELECTION, SetCommand.UNSET_VALUE));
        }

        if (associationEnd.getFeatureSelection() != null) {
            deleteFeatureSelection(aspect, associationEnd, compoundCommand);
        }

        doExecute(editingDomain, compoundCommand);
    }

    /**
     * Deletes the feature selection of the given {@link AssociationEnd}, removes the model reuse from the aspect and
     * the feature reuses from the features that realize the aspect.
     *
     * @param aspect The current aspect
     * @param associationEnd the {@link AssociationEnd} the selected features should be changed for
     * @param compoundCommand The compound command for updating/deleting the feature selection
     */
    private void deleteFeatureSelection(Aspect aspect, AssociationEnd associationEnd, CompoundCommand compoundCommand) {
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(associationEnd);
        compoundCommand.append(RemoveCommand.create(editingDomain, associationEnd.getFeatureSelection()));
        deleteReuseInstantiation(aspect, associationEnd, compoundCommand);
        compoundCommand.append(RemoveCommand.create(editingDomain,
                associationEnd.getFeatureSelection().getReuse()));
    }

    /**
     * Sets the key mapping and updates all operations with the proper mapping.
     * @param keyMapping The mapping for which to set the key
     * @param value The value of the key
     */
    public void setKeySelection(ClassifierMapping keyMapping, Object value) {
        Instantiation instantiation = (Instantiation) keyMapping.eContainer();
        ClassController controller = ControllerFactory.INSTANCE.getClassController();
        Classifier previousClassifier = keyMapping.getTo();

        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(keyMapping);
        CompoundCommand compoundCommand = new CompoundCommand();

        compoundCommand.append(SetCommand.create(editingDomain, keyMapping,
                CorePackage.Literals.CORE_MAPPING__TO, value));
        keyMapping.setTo((Classifier) value);

        for (ClassifierMapping classifierMapping : instantiation.getMappings()) {
            if (AssociationConstants.DATA_CLASS_NAME.equals(classifierMapping.getFrom().getName())) {
                for (OperationMapping operationMapping : classifierMapping.getOperationMappings()) {
                    compoundCommand.append(RemoveCommand.create(editingDomain, operationMapping.getTo()));

                    Operation mapped = controller.createOperationCopyWithoutCommand(operationMapping,
                            operationMapping.getTo().getName(),
                            instantiation.getSource().getStructuralView());

                    compoundCommand.append(SetCommand.create(editingDomain, operationMapping,
                            CorePackage.Literals.CORE_MAPPING__TO, mapped));

                    compoundCommand.append(AddCommand.create(editingDomain, classifierMapping.getTo(),
                            RamPackage.Literals.CLASSIFIER__OPERATIONS, mapped));
                }
            }
        }
        keyMapping.setTo(previousClassifier);
        doExecute(editingDomain, compoundCommand);
    }

    /**
     * Create the instantiation for the COREReuse.
     * @param owner The current aspect
     * @param externalAspect the woven aspect
     * @param associationEnd The association end for which a selection was done
     * @param compoundCommand the compound command for creating/updating the feature selection
     * @param modelReuse the model reuse to set the mappings for
     */
    private void createReuseInstantiation(Aspect owner, Aspect externalAspect, AssociationEnd associationEnd,
            CompoundCommand compoundCommand, COREModelReuse modelReuse) {

        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(associationEnd);
        Classifier associatedClassifier = null;
        Classifier dataClassifier = null;
        Classifier keyClassifier = null;

        AssociationEnd oppositeEnd = associationEnd.getOppositeEnd();

        for (Classifier classifier : externalAspect.getStructuralView().getClasses()) {
            if (classifier.getName().equals(AssociationConstants.ASSOCIATED_CLASS_NAME)
                    || classifier.getName().equals(AssociationConstants.VALUE_CLASS_NAME)) {
                associatedClassifier = classifier;
            } else if (AssociationConstants.DATA_CLASS_NAME.equals(classifier.getName())) {
                dataClassifier = classifier;
            } else if (AssociationConstants.KEY_CLASS_NAME.equals(classifier.getName())) {
                keyClassifier = classifier;
            }
        }

        Instantiation instantiation = RamFactory.eINSTANCE.createInstantiation();
        instantiation.setSource(externalAspect);

        ClassifierMapping associatedClassifierMapping = RamFactory.eINSTANCE.createClassifierMapping();
        associatedClassifierMapping.setFrom(associatedClassifier);
        associatedClassifierMapping.setTo(oppositeEnd.getClassifier());

        ClassifierMapping dataClassifierMapping = RamFactory.eINSTANCE.createClassifierMapping();
        dataClassifierMapping.setFrom(dataClassifier);
        dataClassifierMapping.setTo(associationEnd.getClassifier());

        if (keyClassifier != null) {
            ClassifierMapping keyClassifierMapping = RamFactory.eINSTANCE.createClassifierMapping();
            keyClassifierMapping.setFrom(keyClassifier);
            instantiation.getMappings().add(keyClassifierMapping);
        }

        instantiation.getMappings().add(associatedClassifierMapping);
        instantiation.getMappings().add(dataClassifierMapping);

        modelReuse.getCompositions().add(instantiation);

        for (Operation operation : dataClassifier.getOperations()) {
            OperationMapping operationMapping = RamFactory.eINSTANCE.createOperationMapping();
            dataClassifierMapping.getOperationMappings().add(operationMapping);

            operationMapping.setFrom(operation);
            Operation mapped = ControllerFactory.INSTANCE.getClassController().createOperationCopyWithoutCommand(
                    operationMapping, getOperationName(operation.getName(), associationEnd.getName()),
                    owner.getStructuralView());
            operationMapping.setTo(mapped);

            compoundCommand.append(AddCommand.create(editingDomain, associationEnd.getClassifier(),
                    RamPackage.Literals.CLASSIFIER__OPERATIONS, mapped));
        }
    }

    /**
     * Gets the name of the mapped operation.
     * @param operationName the name of the operation
     * @param name the name of the association end
     * @return the name of the mapped operation
     */
    private String getOperationName(String operationName, String name) {
        String associationName = name.substring(0, 1).toUpperCase() + name.substring(1);
        String mappedName = "";

        if ("getAssociated".equals(operationName)) {
            mappedName = "get" + associationName;
        } else if ("add".equals(operationName)) {
            mappedName = operationName + "To" + associationName;
        } else if ("remove".equals(operationName)) {
            mappedName = operationName + "From" + associationName;
        } else if ("containsKey".equals(operationName)) {
            mappedName = name + operationName.substring(0, 1).toUpperCase() + operationName.substring(1);
        } else if ("keySet".equals(operationName) || "values".equals(operationName)
                || "size".equals(operationName)) {
            mappedName = operationName + "Of" + associationName;
        } else {
            mappedName = operationName + associationName;
        }

        return mappedName;
    }

    /**
     * Delete the instantiation for the COREReuse.
     * @param aspect The current aspect
     * @param associationEnd The association end for which a selection was done
     * @param compoundCommand the compound command for creating/updating the feature selection
     */
    private void deleteReuseInstantiation(Aspect aspect, AssociationEnd associationEnd,
            CompoundCommand compoundCommand) {
        Instantiation toDelete = (Instantiation) associationEnd.getFeatureSelection().getCompositions().get(0);

        if (toDelete != null) {
            EditingDomain editingDomain = EMFEditUtil.getEditingDomain(associationEnd);

            compoundCommand.append(RemoveCommand.create(editingDomain, toDelete));

            for (ClassifierMapping classifierMapping : toDelete.getMappings()) {
                if (associationEnd.getClassifier().equals(classifierMapping.getTo())) {
                    for (OperationMapping operationMapping : classifierMapping.getOperationMappings()) {
                        compoundCommand.append(RemoveCommand.create(editingDomain, operationMapping.getTo()));
                    }
                }
            }
        }
    }

    /**
     * Deletes the given {@link Association}.
     *
     * @param aspect the current {@link Aspect}
     * @param association the {@link Association} to delete
     */
    public void deleteAssociation(Aspect aspect, Association association) {
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(association);
        CompoundCommand compoundCommand = new CompoundCommand();

        deleteAssociation(aspect, association, compoundCommand);

        doExecute(editingDomain, compoundCommand);
    }

    /**
     * Deletes the given {@link Association}.
     *
     * @param aspect the current {@link Aspect}
     * @param association the {@link Association} to delete
     * @param compoundCommand the compound command for deleting the association
     */
    public void deleteAssociation(Aspect aspect, Association association, CompoundCommand compoundCommand) {
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(association);

        // create remove command for association
        compoundCommand.append(RemoveCommand.create(editingDomain, association));

        // create remove commands for association ends
        for (AssociationEnd associationEnd : association.getEnds()) {
            if (associationEnd.getFeatureSelection() != null) {
                deleteFeatureSelection(aspect, associationEnd, compoundCommand);
            }
            compoundCommand.append(RemoveCommand.create(editingDomain, associationEnd));
        }
    }

    /**
     * Switches the navigable property of the given association end.
     * In case the opposite association end isn't navigable, both of the ends are switched
     *
     * @param aspect the current {@link Aspect}
     * @param associationEnd the {@link AssociationEnd} the navigable property should be switched of
     * @param oppositeEnd the opposite {@link AssociationEnd} from which the navigable property should be switched of
     */
    public void switchNavigable(Aspect aspect, AssociationEnd associationEnd, AssociationEnd oppositeEnd) {
        // If the opposite is navigable, just switch
        // Else switch both ends so that at least one end stays navigable
        // See issue #277
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(associationEnd);
        CompoundCommand compoundCommand = new CompoundCommand();

        if (oppositeEnd.isNavigable()) {
            if (associationEnd.getFeatureSelection() != null) {
                compoundCommand.append(SetCommand.create(editingDomain, associationEnd,
                        RamPackage.Literals.ASSOCIATION_END__FEATURE_SELECTION, SetCommand.UNSET_VALUE));
                deleteFeatureSelection(aspect, associationEnd, compoundCommand);
            }
            compoundCommand.append(SetCommand.create(editingDomain, associationEnd,
                    RamPackage.Literals.ASSOCIATION_END__NAVIGABLE, !associationEnd.isNavigable()));
        } else {
            switchAssociationNavigability(aspect, associationEnd.getAssoc(), compoundCommand);
        }

        doExecute(editingDomain, compoundCommand);
    }

    /**
     * Switches the navigable property of both ends of given association.
     *
     * @param aspect the current {@link Aspect}
     * @param association the {@link Association} the navigable properties should be switched of
     * @param compoundCommand the compound command for deleting the association
     */
    private void switchAssociationNavigability(Aspect aspect, Association association,
            CompoundCommand compoundCommand) {
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(association);

        // create switch commands for association ends
        for (AssociationEnd associationEnd : association.getEnds()) {
            boolean value = !associationEnd.isNavigable();
            if (value && associationEnd.getFeatureSelection() != null) {
                compoundCommand.append(SetCommand.create(editingDomain, associationEnd,
                        RamPackage.Literals.ASSOCIATION_END__FEATURE_SELECTION, SetCommand.UNSET_VALUE));
                deleteFeatureSelection(aspect, associationEnd, compoundCommand);
            }
            compoundCommand.append(SetCommand.create(editingDomain, associationEnd,
                    RamPackage.Literals.ASSOCIATION_END__NAVIGABLE, value));
        }
    }

    /**
     * Sets the reference type of the given association end to the new reference type.
     *
     * @param associationEnd the {@link AssociationEnd} the reference type should be changed of
     * @param referenceType the new {@link ReferenceType} to set
     */
    public void setReferenceType(AssociationEnd associationEnd, ReferenceType referenceType) {
        doSet(associationEnd, RamPackage.Literals.PROPERTY__REFERENCE_TYPE, referenceType);
    }

    /**
     * Switches the static property of the given association end.
     *
     * @param associationEnd the {@link AssociationEnd} the static property should be switched of
     */
    public void switchStatic(AssociationEnd associationEnd) {
        doSwitch(associationEnd, RamPackage.Literals.STRUCTURAL_FEATURE__STATIC);
    }
}
