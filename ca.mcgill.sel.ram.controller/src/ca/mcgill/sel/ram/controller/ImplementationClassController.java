package ca.mcgill.sel.ram.controller;

import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.ram.ImplementationClass;
import ca.mcgill.sel.ram.ObjectType;
import ca.mcgill.sel.ram.Operation;
import ca.mcgill.sel.ram.OperationType;
import ca.mcgill.sel.ram.Parameter;
import ca.mcgill.sel.ram.RAMVisibilityType;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.StructuralView;
import ca.mcgill.sel.ram.Type;
import ca.mcgill.sel.ram.TypeParameter;
import ca.mcgill.sel.ram.util.RAMModelUtil;

/**
 * Controller for implementation classes.
 * 
 * @author Franz
 */
public class ImplementationClassController extends BaseController {

    /**
     * Adds an operation to an existing implementation class.
     * 
     * @param owner the implementation class where the operation will be added
     * @param index the index where the operation should be added
     * @param name the name of the operation
     * @param ramVisibility the {@link RAMVisibilityType} of the operation
     * @param returnType the return type of the operation
     * @param parameters the parameters of the operation
     * @param isStatic is the operation static or not
     */
    public void addOperation(ImplementationClass owner, int index, String name, RAMVisibilityType ramVisibility,
            Type returnType, List<Parameter> parameters, boolean isStatic) {
        Operation operation = RAMModelUtil.createOperation(owner, name, returnType, parameters);
        if (operation != null) {
            operation.setStatic(isStatic);
            operation.setOperationType(OperationType.NORMAL);

            EditingDomain domain = EMFEditUtil.getEditingDomain(owner);

            CompoundCommand compundCommand = new CompoundCommand();

            // Update visibility for the operation and classifier if necessary
            compundCommand.append(ControllerFactory.INSTANCE.getClassController()
                    .changeOperationAndClassVisibilityCommand(
                            domain, owner, operation, ramVisibility));
            // Add the operation
            compundCommand.append(AddCommand.create(domain, owner, RamPackage.Literals.CLASSIFIER__OPERATIONS,
                    operation,
                    index));
            doExecute(domain, compundCommand);
        }
    }

    /**
     * Adds a constructor to an existing implementation class.
     * 
     * @param owner the implementation class where the constructor will be added
     * @param name the name of the constructor
     * @param ramVisibility the {@link RAMVisibilityType} of the operation
     * @param parameters the parameters of the constructor
     */
    public void addConstructor(ImplementationClass owner, String name, RAMVisibilityType ramVisibility,
            List<Parameter> parameters) {
        Operation operation = RAMModelUtil.createOperation(owner, name, owner, parameters);
        if (operation != null) {
            operation.setOperationType(OperationType.CONSTRUCTOR);
            int index = RAMModelUtil.findConstructorIndexFor(owner, OperationType.CONSTRUCTOR);
            EditingDomain domain = EMFEditUtil.getEditingDomain(owner);

            CompoundCommand compundCommand = new CompoundCommand();

            // Update visibility for the operation and classifier if necessary
            compundCommand.append(ControllerFactory.INSTANCE.getClassController()
                    .changeOperationAndClassVisibilityCommand(
                            domain, owner, operation, ramVisibility));

            // Add the operation
            compundCommand.append(AddCommand.create(domain, owner, RamPackage.Literals.CLASSIFIER__OPERATIONS,
                    operation,
                    index));
            doExecute(domain, compundCommand);
        }
    }

    /**
     * Sets the type parameter's generic type.
     * In addition, if this way existing implementation classes match in type parameters and are a super or sub type,
     * the inheritance link is set accordingly.
     * 
     * @param owner the {@link ImplementationClass} containing the type parameter
     * @param typeParameter the {@link TypeParameter} to update
     * @param genericType the {@link ObjectType} to set
     * @param superTypes the set of super types the class to create has
     * @param subTypes the set of sub types (existing in the structural view) the class to create has
     */
    public void setTypeParameterType(ImplementationClass owner, TypeParameter typeParameter, ObjectType genericType,
            Set<String> superTypes, Set<ImplementationClass> subTypes) {
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(owner);
        CompoundCommand command = new CompoundCommand();

        command.append(SetCommand.create(editingDomain, typeParameter,
                RamPackage.Literals.TYPE_PARAMETER__GENERIC_TYPE, genericType));

        // Temporarily set the type in order that typeParametersMatch works properly.
        ObjectType previousType = typeParameter.getGenericType();
        typeParameter.setGenericType((ObjectType) genericType);

        if (superTypes != null) {
            StructuralView structuralView = (StructuralView) owner.eContainer();
            for (String className : superTypes) {
                ImplementationClass superType = RAMModelUtil.getImplementationClassByName(structuralView, className);
                if (superType != null && RAMModelUtil.typeParametersMatch(owner, superType)) {
                    command.append(AddCommand.create(editingDomain, owner,
                            RamPackage.Literals.CLASSIFIER__SUPER_TYPES, superType));
                }
            }
        }

        if (subTypes != null) {
            for (ImplementationClass subType : subTypes) {
                if (RAMModelUtil.typeParametersMatch(owner, subType)) {
                    command.append(AddCommand.create(editingDomain, subType,
                            RamPackage.Literals.CLASSIFIER__SUPER_TYPES, owner));
                }
            }
        }

        // Undo temporary change.
        typeParameter.setGenericType(previousType);

        doExecute(editingDomain, command);
    }

}
