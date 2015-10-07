package ca.mcgill.sel.ram.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.Association;
import ca.mcgill.sel.ram.AssociationEnd;
import ca.mcgill.sel.ram.Class;
import ca.mcgill.sel.ram.Classifier;
import ca.mcgill.sel.ram.ImplementationClass;
import ca.mcgill.sel.ram.LayoutElement;
import ca.mcgill.sel.ram.MessageView;
import ca.mcgill.sel.ram.Operation;
import ca.mcgill.sel.ram.REnum;
import ca.mcgill.sel.ram.REnumLiteral;
import ca.mcgill.sel.ram.RamFactory;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.StructuralView;
import ca.mcgill.sel.ram.TypeParameter;
import ca.mcgill.sel.ram.util.RAMModelUtil;

/**
 * The controller for {@link StructuralView}s.
 * 
 * @author mschoettle
 */
public class StructuralViewController extends BaseController {

    /**
     * The default prefix for role names.
     */
    private static final String PREFIX_ROLE_NAME = "my";

    /**
     * Creates a new instance of {@link StructuralViewController}.
     */
    protected StructuralViewController() {
        // Prevent anyone outside this package to instantiate.
    }

    /**
     * Creates a new class as well as its layout element.
     * 
     * @param owner the {@link StructuralView} the class should be added to
     * @param name the name of the class
     * @param x the x position of the class
     * @param y the y position of the class
     */
    public void createNewClass(StructuralView owner, String name, float x, float y) {
        Class newClass = RamFactory.eINSTANCE.createClass();
        newClass.setName(name);

        LayoutElement layoutElement = RamFactory.eINSTANCE.createLayoutElement();
        layoutElement.setX(x);
        layoutElement.setY(y);

        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(owner);

        // Create commands.
        Command addClassCommand = AddCommand.create(editingDomain, owner, RamPackage.Literals.STRUCTURAL_VIEW__CLASSES,
                newClass);
        Command createElementMapCommand = createLayoutElementCommand(editingDomain, owner, newClass, layoutElement);

        // Create compound command.
        CompoundCommand compoundCommand = new CompoundCommand();
        // If the layout element is added first, when the structural view gets notified (about a new class)
        // access to the layout element is already possible.
        compoundCommand.append(addClassCommand);
        compoundCommand.append(createElementMapCommand);

        doExecute(editingDomain, compoundCommand);
    }

    /**
     * Creates a new implementation class.
     * 
     * @param owner the structural view containing the implementation class
     * @param name name of the implementation class.
     * @param generics the type parameters of the implementation class
     * @param x location of the implementation class in the x-axis.
     * @param y location of the implementation class in the y-axis.
     * @param isInterface whether this should be an interface or not
     * @param superTypes the set of super types the class to create has
     * @param subTypes the set of sub types (existing in the structural view) the class to create has
     */
    // CHECKSTYLE:IGNORE ParameterNumberCheck: Can't split, because it is an API method and all information is required.
    public void createImplementationClass(StructuralView owner, String name, List<TypeParameter> generics,
            boolean isInterface, float x, float y, Set<String> superTypes, Set<ImplementationClass> subTypes) {
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(owner);
        CompoundCommand compoundCommand = new CompoundCommand();

        ImplementationClass implementationClass = createImplementationClassCommand(editingDomain, compoundCommand,
                owner, name, generics, isInterface, x, y);

        // If the class has generics, we cannot determine at this point whether there are super or sub types,
        // because the generic type of the TypeParameter's will be unset.
        if (generics.isEmpty()) {
            if (superTypes != null) {
                for (String className : superTypes) {
                    ImplementationClass superType = RAMModelUtil.getImplementationClassByName(owner, className);
                    if (superType != null) {
                        compoundCommand.append(AddCommand.create(editingDomain, implementationClass,
                                RamPackage.Literals.CLASSIFIER__SUPER_TYPES, superType));
                    }
                }
            }

            if (subTypes != null) {
                for (ImplementationClass subType : subTypes) {
                    compoundCommand.append(AddCommand.create(editingDomain, subType,
                            RamPackage.Literals.CLASSIFIER__SUPER_TYPES, implementationClass));
                }
            }
        }

        doExecute(editingDomain, compoundCommand);
    }

    /**
     * Creates a new implementation class.
     * 
     * @param editingDomain the {@link EditingDomain} domain
     * @param command the {@link CompoundCommand} additional commands should be appended to
     * @param owner the structural view containing the implementation class
     * @param name name of the implementation class.
     * @param generics the type parameters of the implementation class
     * @param x location of the implementation class in the x-axis.
     * @param y location of the implementation class in the y-axis.
     * @param isInterface whether this should be an interface or not
     * @return the {@link ImplementationClass} that will be added by the command
     */
    // CHECKSTYLE:IGNORE ParameterNumberCheck: Splitting operation into several methods will affect readability.
    private ImplementationClass createImplementationClassCommand(EditingDomain editingDomain, CompoundCommand command,
            StructuralView owner, String name, List<TypeParameter> generics, boolean isInterface,
            float x, float y) {
        ImplementationClass newImpClass = RamFactory.eINSTANCE.createImplementationClass();

        String[] splitName = name.split("\\.");
        newImpClass.setName(splitName[Math.max(splitName.length - 1, 0)]);
        newImpClass.setInstanceClassName(name);
        newImpClass.setInterface(isInterface);

        newImpClass.getTypeParameters().addAll(generics);

        LayoutElement layoutElement = RamFactory.eINSTANCE.createLayoutElement();
        layoutElement.setX(x);
        layoutElement.setY(y);

        Command addImpClassCommand = AddCommand.create(editingDomain, owner,
                RamPackage.Literals.STRUCTURAL_VIEW__CLASSES, newImpClass);
        Command createElementMapCommand = createLayoutElementCommand(editingDomain, owner, newImpClass, layoutElement);

        // If the layout element is added first, when the structural view gets notified (about a new class)
        // access to the layout element is already possible.
        command.append(addImpClassCommand);
        command.append(createElementMapCommand);

        return newImpClass;
    }

    /**
     * Removes the given classifier. Furthermore, removes this classifier from all other classes where this class is a
     * super type of. If the classifier is a {@link Class}, also all associations and corresponding association ends are
     * removed. Furthermore, removes the layout element.
     * 
     * @param classifier the classifier that should be removed
     */
    // TODO: remove affected instantiations/mapping too?
    // TODO: remove affected collections too?
    public void removeClassifier(Classifier classifier) {
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(classifier);
        StructuralView structuralView = (StructuralView) classifier.eContainer();
        Aspect aspect = (Aspect) structuralView.eContainer();

        CompoundCommand compoundCommand = new CompoundCommand();

        // Create commands for removing all message views defining behaviour of operations of this class.
        for (Operation operation : classifier.getOperations()) {
            MessageView messageView = RAMModelUtil.getMessageViewFor(aspect, operation);

            if (messageView != null) {
                compoundCommand.append(AspectController.createRemoveMessageViewCommand(messageView));
            }
        }

        // Create commands for removing this class as super type of other classes or vice versa.
        for (Classifier currentClassifier : structuralView.getClasses()) {
            if (currentClassifier.getSuperTypes().contains(classifier)) {
                compoundCommand.append(RemoveCommand.create(editingDomain, currentClassifier,
                        RamPackage.Literals.CLASSIFIER__SUPER_TYPES, classifier));
            } else if (classifier.getSuperTypes().contains(currentClassifier)) {
                compoundCommand.append(RemoveCommand.create(editingDomain, classifier,
                        RamPackage.Literals.CLASSIFIER__SUPER_TYPES, currentClassifier));
            }
        }

        List<Association> seenAssociations = new ArrayList<Association>();

        // Create commands for removing associations.
        for (AssociationEnd associationEnd : classifier.getAssociationEnds()) {
            Association association = associationEnd.getAssoc();
            associationEnd = association.getEnds().get(0);
            AssociationEnd otherAssociationEnd = association.getEnds().get(1);

            // Check if we already removed the association.
            // This only happens if we have self-associations.
            boolean alreadySeen = false;
            for (Association otherAssociation : seenAssociations) {
                if (otherAssociation.getEnds().get(0) == associationEnd
                        && otherAssociation.getEnds().get(1) == otherAssociationEnd) {
                    alreadySeen = true;
                    break;
                } else if (otherAssociation.getEnds().get(1) == associationEnd
                        && otherAssociation.getEnds().get(0) == otherAssociationEnd) {
                    alreadySeen = true;
                    break;
                }
            }

            seenAssociations.add(associationEnd.getAssoc());

            if (!alreadySeen) {
                ControllerFactory.INSTANCE.getAssociationController()
                        .deleteAssociation(aspect, association, compoundCommand);
            }
        }

        // Create command for removing ElementMap (includes the layout element).
        Command removeElementMapCommand = createRemoveLayoutElementCommand(editingDomain, structuralView, classifier);
        compoundCommand.append(removeElementMapCommand);

        // Create command for removing class.
        Command removeClassCommand = RemoveCommand.create(editingDomain, classifier);
        compoundCommand.append(removeClassCommand);

        doExecute(editingDomain, compoundCommand);
    }

    /**
     * Moves each {@link Classifier} in a list to a new position. Each classifier has its own (independent) position.
     * 
     * @param structuralView the {@link StructuralView} that contains the classifiers
     * @param positionMap a map from {@link Classifier} to {@link LayoutElement}, which contains the new position
     */
    public void moveClassifiers(StructuralView structuralView, Map<Classifier, LayoutElement> positionMap) {
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(structuralView);

        // Create compound command.
        CompoundCommand moveClassifiersCommand = new CompoundCommand();

        for (Entry<Classifier, LayoutElement> entry : positionMap.entrySet()) {
            // get all required values
            Classifier classifier = entry.getKey();
            float x = entry.getValue().getX();
            float y = entry.getValue().getY();

            Command classifierMoveCommand = createMoveCommand(editingDomain, structuralView, classifier, x, y);
            moveClassifiersCommand.append(classifierMoveCommand);
        }

        doExecute(editingDomain, moveClassifiersCommand);
    }

    /**
     * Creates an association between the two given classes. The association ends have default values.
     * 
     * @param owner the {@link StructuralView} the association should be added to
     * @param from the first class
     * @param to the second class
     * @param bidirectional whether the association should be bidirectional or not. If to or the from
     *            is an implementation class, this is not taken in account.
     */
    public void createAssociation(StructuralView owner, Classifier from, Classifier to, boolean bidirectional) {
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(owner);

        // create compound command
        CompoundCommand compoundCommand = new CompoundCommand();

        // create association
        Association association = RamFactory.eINSTANCE.createAssociation();
        association.setName(from.getName() + "_" + to.getName());

        boolean toIsNavigable = to instanceof ImplementationClass ? false : bidirectional;
        boolean fromIsNavigable = from instanceof ImplementationClass ? false : bidirectional || !toIsNavigable;

        // create from association end
        AssociationEnd fromEnd = this.createAssociationEnd(association, from, to, fromIsNavigable);

        compoundCommand.append(AddCommand.create(editingDomain, from, RamPackage.Literals.CLASSIFIER__ASSOCIATION_ENDS,
                fromEnd));

        // create to association end
        AssociationEnd toEnd = this.createAssociationEnd(association, to, from, toIsNavigable);

        compoundCommand.append(AddCommand.create(editingDomain, to, RamPackage.Literals.CLASSIFIER__ASSOCIATION_ENDS,
                toEnd));

        // create command for association
        compoundCommand.append(AddCommand.create(editingDomain, owner,
                RamPackage.Literals.STRUCTURAL_VIEW__ASSOCIATIONS, association));

        // execute commands
        doExecute(editingDomain, compoundCommand);
    }

    /**
     * Creates an association end for the classifier "from" and "to" given.
     * 
     * @param association the {@link StructuralView} the association should be added to
     * @param from the first class
     * @param to the second class
     * @param navigable indicate if we want the end to be navigable.
     * 
     * @return the association end created
     */
    private AssociationEnd createAssociationEnd(Association association, Classifier from, Classifier to,
            boolean navigable) {
        // create an association end
        AssociationEnd associationEnd = RamFactory.eINSTANCE.createAssociationEnd();
        associationEnd.setAssoc(association);
        associationEnd.setLowerBound(1);
        associationEnd.setNavigable(navigable);

        // The class could have no name.
        if (to.getName() != null || to.getName().isEmpty()) {
            // The default association end name is "my" + name of the target class
            // To assign a unique name to the association end
            // we must make sure that the class currently does not have already an
            // association end with that name
            String potentialNamePrefix = PREFIX_ROLE_NAME + to.getName();
            String potentialName = potentialNamePrefix;
            int index = 1;

            EList<AssociationEnd> ends = from.getAssociationEnds();
            if (ends.size() > 0) {
                int i = 0;
                AssociationEnd e;
                while (i < ends.size()) {
                    e = ends.get(i);
                    if (e.getName().equals(potentialName)) {
                        potentialName = potentialNamePrefix + index;
                        index++;
                        i = 0;
                    } else {
                        i++;
                    }
                }
            }
            associationEnd.setName(potentialName);
        }

        return associationEnd;
    }

    /**
     * Create an enum and its layout element.
     * 
     * @param owner the {@link StructuralView} the enum should be added to
     * @param name Name of the enum
     * @param x Position in the Structural view
     * @param y Position in the Structural view
     */
    public void createEnum(StructuralView owner, String name, float x, float y) {
        createImplementationEnum(owner, name, null, x, y, null);
    }

    /**
     * Creates a new implementation enum.
     * 
     * @param owner the {@link StructuralView} the enum should be added to
     * @param name the name of the enum
     * @param instanceClassName the fully qualified name of the enum
     * @param x the x position of the enum
     * @param y the y position of the enum
     * @param literals the list of literals the enum contains
     */
    public void createImplementationEnum(StructuralView owner, String name, String instanceClassName, float x, float y,
            List<String> literals) {
        // Create an REnum object
        REnum newEnum = RamFactory.eINSTANCE.createREnum();
        newEnum.setName(name);

        if (instanceClassName != null && !instanceClassName.isEmpty()) {
            newEnum.setInstanceClassName(instanceClassName);
        }

        if (literals != null) {
            for (String literal : literals) {
                REnumLiteral newLiteral = RamFactory.eINSTANCE.createREnumLiteral();
                newLiteral.setName(literal);
                newEnum.getLiterals().add(newLiteral);
            }
        }

        // Create the layout element
        LayoutElement layoutElement = RamFactory.eINSTANCE.createLayoutElement();
        layoutElement.setX(x);
        layoutElement.setY(y);

        // Get editing domain
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(owner);

        // Create commands.
        Command addEnumCommand = AddCommand.create(editingDomain, owner, RamPackage.Literals.STRUCTURAL_VIEW__TYPES,
                newEnum);
        Command createElementMapCommand = createLayoutElementCommand(editingDomain, owner, newEnum, layoutElement);

        // Create compound command.
        CompoundCommand compoundCommand = new CompoundCommand();
        compoundCommand.append(addEnumCommand);
        compoundCommand.append(createElementMapCommand);

        doExecute(editingDomain, compoundCommand);
        owner.getTypes().add(newEnum);
    }
}
