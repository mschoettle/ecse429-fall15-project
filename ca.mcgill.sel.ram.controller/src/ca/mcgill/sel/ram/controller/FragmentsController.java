package ca.mcgill.sel.ram.controller;

import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.commons.emf.util.EMFModelUtil;
import ca.mcgill.sel.ram.AssignmentStatement;
import ca.mcgill.sel.ram.Attribute;
import ca.mcgill.sel.ram.CombinedFragment;
import ca.mcgill.sel.ram.ExecutionStatement;
import ca.mcgill.sel.ram.FragmentContainer;
import ca.mcgill.sel.ram.Interaction;
import ca.mcgill.sel.ram.InteractionFragment;
import ca.mcgill.sel.ram.InteractionOperand;
import ca.mcgill.sel.ram.InteractionOperatorKind;
import ca.mcgill.sel.ram.Lifeline;
import ca.mcgill.sel.ram.Message;
import ca.mcgill.sel.ram.MessageOccurrenceSpecification;
import ca.mcgill.sel.ram.OpaqueExpression;
import ca.mcgill.sel.ram.PrimitiveType;
import ca.mcgill.sel.ram.RamFactory;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.StructuralFeature;
import ca.mcgill.sel.ram.util.RAMModelUtil;

/**
 * The controller for {@link InteractionFragment}s.
 *
 * @author mschoettle
 */
public class FragmentsController extends BaseController {

    /**
     * Creates a new instance of {@link FragmentsController}.
     */
    protected FragmentsController() {
        // Prevent anyone outside this package to instantiate.
    }

    /**
     * Removes the given interaction fragment from its container and removes the fragment from the list of
     * covered lifelines as well.
     *
     * @param fragment the {@link InteractionFragment} to remove
     */
    public void removeInteractionFragment(InteractionFragment fragment) {
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(fragment);

        CompoundCommand compoundCommand = createRemoveInteractionFragmentCommand(editingDomain, fragment);

        doExecute(editingDomain, compoundCommand);
    }

    /**
     * Creates the appropriate removal commands to remove the given interaction fragment.
     * This also includes the removal of the fragment from the covered list of all covered lifelines.
     * Furthermore, if the fragment is a combined fragment, all messages related to the operands need to be removed.
     *
     * @param editingDomain the {@link EditingDomain} to use for executing commands
     * @param fragment the {@link InteractionFragment} to delete
     * @return the commands to appropriately remove an interaction fragment
     */
    private CompoundCommand createRemoveInteractionFragmentCommand(EditingDomain editingDomain,
            InteractionFragment fragment) {
        CompoundCommand compoundCommand = new CompoundCommand();

        if (fragment instanceof CombinedFragment) {
            CombinedFragment combinedFragment = (CombinedFragment) fragment;

            // Delete from the bottom up.
            for (int index = combinedFragment.getOperands().size() - 1; index >= 0; index--) {
                InteractionOperand operand = combinedFragment.getOperands().get(index);

                if (!operand.getFragments().isEmpty()) {
                    compoundCommand.append(createRemoveOperandContentsCommand(editingDomain, operand));
                }

                // Figure out if there will be empty lifelines as a result and delete them as well.
                Interaction owner = EMFModelUtil.getRootContainerOfType(operand, RamPackage.Literals.INTERACTION);
                List<CombinedFragment> combinedFragments =
                        RAMModelUtil.getCoveredCombinedFragments(owner, operand, true);

                MessageViewController.appendRemoveEmptyLifelinesCommand(editingDomain, compoundCommand, owner,
                        combinedFragments, true);

                compoundCommand.append(RemoveCommand.create(editingDomain, operand));
            }

            /**
             * The lifelines need to be removed from the combined fragment first,
             * because empty lifelines might have not been removed previously
             * (these are not part of the deleted lifelines).
             * This is necessary, because empty lifelines (within a combined fragment)
             * are not removed from the combined fragment automatically.
             * It is difficult to distinguish between the target and other lifelines to be able to do that.
             *
             * Avoid duplicate remove commands for "covered" for already deleted lifelines.
             * It is possible that the covered link of the combined fragment
             * was removed when deleting an empty lifeline.
             */
            Set<Lifeline> deletedLifelines = MessageViewController.getDeletedLifelines(compoundCommand);

            for (Lifeline lifeline : fragment.getCovered()) {
                if (!deletedLifelines.contains(lifeline)) {
                    compoundCommand.append(RemoveCommand.create(editingDomain, fragment,
                            RamPackage.Literals.INTERACTION_FRAGMENT__COVERED, lifeline));
                }
            }
            compoundCommand.append(RemoveCommand.create(editingDomain, fragment));
        } else {
            compoundCommand.append(RemoveCommand.create(editingDomain, fragment));
            compoundCommand.append(RemoveCommand.create(editingDomain, fragment,
                    RamPackage.Literals.INTERACTION_FRAGMENT__COVERED, fragment.getCovered()));
        }

        return compoundCommand;
    }

    /**
     * Creates an execution statement at the given index.
     * The execution statement is using an {@link OpaqueExpression} with a default language as the specification.
     *
     * @param owner the container for the execution statement
     * @param lifeline the lifeline that the execution statement covers/is placed on
     * @param addAtIndex the index at which the statement should be added to inside the containers fragments
     */
    public void createExecutionStatement(FragmentContainer owner, Lifeline lifeline, int addAtIndex) {
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(owner);

        ExecutionStatement executionStatement = RamFactory.eINSTANCE.createExecutionStatement();
        OpaqueExpression specification = RAMModelUtil.createOpaqueExpression();
        executionStatement.setSpecification(specification);

        CompoundCommand compoundCommand = new CompoundCommand();

        compoundCommand.append(AddCommand.create(editingDomain, executionStatement,
                RamPackage.Literals.INTERACTION_FRAGMENT__COVERED, lifeline));
        compoundCommand.append(AddCommand.create(editingDomain, owner,
                RamPackage.Literals.FRAGMENT_CONTAINER__FRAGMENTS, executionStatement, addAtIndex));

        doExecute(editingDomain, compoundCommand);
    }

    /**
     * Creates a combined fragment that covers the given lifeline and adds at the given index inside the container.
     * The combined fragment has the default {@link ca.mcgill.sel.ram.InteractionOperatorKind} "alt".
     * Also, each operand's interaction constraint is an {@link OpaqueExpression} with a default language.
     *
     * @param owner the container for the combined fragment
     * @param lifeline the lifeline that the combined fragment covers/is placed on
     * @param addAtIndex the index at which the combined fragment should be added to inside the containers fragments
     */
    public void createCombinedFragment(FragmentContainer owner, Lifeline lifeline, int addAtIndex) {
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(owner);

        CombinedFragment combinedFragment = RamFactory.eINSTANCE.createCombinedFragment();
        combinedFragment.setInteractionOperator(InteractionOperatorKind.OPT);

        InteractionOperand operand = createInteractionOperand();

        combinedFragment.getOperands().add(operand);

        CompoundCommand compoundCommand = new CompoundCommand();

        compoundCommand.append(AddCommand.create(editingDomain, combinedFragment,
                RamPackage.Literals.INTERACTION_FRAGMENT__COVERED, lifeline));
        compoundCommand.append(AddCommand.create(editingDomain, owner,
                RamPackage.Literals.FRAGMENT_CONTAINER__FRAGMENTS, combinedFragment, addAtIndex));

        doExecute(editingDomain, compoundCommand);
    }

    /**
     * Creates a new empty operand with a constraint that is a {@link OpaqueExpression} with a default language.
     *
     * @param combinedFragment the {@link CombinedFragment} that the new operand should be added to
     * @param index the index at which to add the operand to
     */
    public void createInteractionOperand(CombinedFragment combinedFragment, int index) {
        InteractionOperand operand = createInteractionOperand();

        doAdd(combinedFragment, RamPackage.Literals.COMBINED_FRAGMENT__OPERANDS, operand, index);
    }

    /**
     * Creates a new empty operand with an empty constraint as an {@link OpaqueExpression}.
     *
     * @return a new empty {@link InteractionOperand}
     */
    private InteractionOperand createInteractionOperand() {
        InteractionOperand operand = RamFactory.eINSTANCE.createInteractionOperand();
        OpaqueExpression constraint = RAMModelUtil.createOpaqueExpression();
        operand.setInteractionConstraint(constraint);
        return operand;
    }

    /**
     * Removes the given operand and its contents.
     * The contents are messages and their events or other {@link InteractionFragment}s.
     *
     * @param operand the operand to remove
     */
    public void removeInteractionOperand(InteractionOperand operand) {
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(operand);

        CompoundCommand compoundCommand = new CompoundCommand();

        if (!operand.getFragments().isEmpty()) {
            compoundCommand.append(createRemoveOperandContentsCommand(editingDomain, operand));

            // Figure out if there will be empty lifelines as a result and delete them as well.
            Interaction owner = EMFModelUtil.getRootContainerOfType(operand, RamPackage.Literals.INTERACTION);
            List<CombinedFragment> combinedFragments = RAMModelUtil.getCoveredCombinedFragments(owner, operand, true);

            MessageViewController.appendRemoveEmptyLifelinesCommand(editingDomain, compoundCommand, owner,
                    combinedFragments, true);
        }
        compoundCommand.append(RemoveCommand.create(editingDomain, operand));

        doExecute(editingDomain, compoundCommand);
    }

    /**
     * Creates the commands to remove the contents of the given operand.
     * The contents are messages and their events or other {@link InteractionFragment}s.
     * Note that if the operand has no commands, a non-executable command will be returned.
     *
     * @param editingDomain the {@link EditingDomain} to use for executing commands
     * @param operand the {@link InteractionOperand} to remove
     * @return the commands to remove the contents of the operand
     */
    private CompoundCommand createRemoveOperandContentsCommand(EditingDomain editingDomain,
            InteractionOperand operand) {
        Interaction interaction = EMFModelUtil.getRootContainerOfType(operand, RamPackage.Literals.INTERACTION);
        CompoundCommand compoundCommand = new CompoundCommand();

        // Assume that the first lifeline is the one that stuff is happening from.
        CombinedFragment combinedFragment = (CombinedFragment) operand.eContainer();
        Lifeline firstLifeline = combinedFragment.getCovered().get(0);

        // Delete from the bottom up.
        for (int index = operand.getFragments().size() - 1; index >= 0; index--) {
            InteractionFragment currentFragment = operand.getFragments().get(index);

            if (currentFragment instanceof MessageOccurrenceSpecification) {
                MessageOccurrenceSpecification messageEnd = (MessageOccurrenceSpecification) currentFragment;

                // Only if the message end is a send event and is sent from the first lifeline
                // we want to trigger the removal of it and its subsequent messages.
                if (messageEnd.getMessage().getSendEvent() == messageEnd
                        && messageEnd.getCovered().contains(firstLifeline)) {
                    Command command = MessageViewController.createRemoveMessagesCommand(editingDomain, interaction,
                            operand, messageEnd);
                    compoundCommand.append(command);
                }
            } else {
                compoundCommand.append(createRemoveInteractionFragmentCommand(editingDomain, currentFragment));
            }
        }

        return compoundCommand;
    }

    /**
     * Creates an assignment statement at the given index.
     * The assignment statement is using an {@link OpaqueExpression} with a default language as the value.
     * The "assignTo" is unset.
     *
     * @param owner the container for the assignment statement
     * @param lifeline the lifeline that the assignment statement covers/is placed on
     * @param addAtIndex the index at which the statement should be added to inside the containers fragments
     */
    public void createAssignmentStatement(FragmentContainer owner, Lifeline lifeline, int addAtIndex) {
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(owner);

        AssignmentStatement assignmentStatement = RamFactory.eINSTANCE.createAssignmentStatement();
        OpaqueExpression specification = RAMModelUtil.createOpaqueExpression();
        assignmentStatement.setValue(specification);

        CompoundCommand compoundCommand = new CompoundCommand();

        compoundCommand.append(AddCommand.create(editingDomain, assignmentStatement,
                RamPackage.Literals.INTERACTION_FRAGMENT__COVERED, lifeline));
        compoundCommand.append(AddCommand.create(editingDomain, owner,
                RamPackage.Literals.FRAGMENT_CONTAINER__FRAGMENTS, assignmentStatement, addAtIndex));

        doExecute(editingDomain, compoundCommand);
    }

    /**
     * Creates a new temporary property with a primitive type and sets it as the "assignTo" of the assignment statement.
     * Also adds the temporary property to the initial message.
     * If there is an existing temporary property, it is deleted in addition, if it is not referenced anywhere else.
     *
     * @param assignmentStatement the {@link AssignmentStatement} for which the "assignTo" to set for
     * @param name the name of the temporary property
     * @param type the {@link PrimitiveType} of the temporary property
     */
    public void createTemporaryAssignment(AssignmentStatement assignmentStatement, String name, PrimitiveType type) {
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(assignmentStatement);
        Message initialMessage = RAMModelUtil.findInitialMessage(assignmentStatement);

        Attribute attribute = RamFactory.eINSTANCE.createAttribute();
        attribute.setType(type);
        attribute.setName(name);

        CompoundCommand compoundCommand = new CompoundCommand();

        StructuralFeature structuralFeature = assignmentStatement.getAssignTo();
        appendRemoveTemporaryPropertyCommand(editingDomain, compoundCommand, assignmentStatement, structuralFeature);

        compoundCommand.append(AddCommand.create(editingDomain, initialMessage,
                RamPackage.Literals.MESSAGE__LOCAL_PROPERTIES, attribute));
        compoundCommand.append(SetCommand.create(editingDomain, assignmentStatement,
                RamPackage.Literals.ASSIGNMENT_STATEMENT__ASSIGN_TO, attribute));

        doExecute(editingDomain, compoundCommand);
    }

    /**
     * Changes the "assignTo" of the assignment statement to the given one.
     * It might not be set yet, if it is and it is a temporary property, the existing one is deleted if it is not
     * referenced anywhere else.
     *
     * @param assignmentStatement the {@link AssignmentStatement} the "assignTo" to change for
     * @param assignTo the new "assignTo" value
     */
    public void changeAssignmentAssignTo(AssignmentStatement assignmentStatement, StructuralFeature assignTo) {
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(assignmentStatement);

        CompoundCommand compoundCommand = new CompoundCommand();

        StructuralFeature structuralFeature = assignmentStatement.getAssignTo();
        appendRemoveTemporaryPropertyCommand(editingDomain, compoundCommand, assignmentStatement, structuralFeature);

        compoundCommand.append(SetCommand.create(editingDomain, assignmentStatement,
                RamPackage.Literals.ASSIGNMENT_STATEMENT__ASSIGN_TO, assignTo));

        doExecute(editingDomain, compoundCommand);
    }

    /**
     * Determines whether the existing structural feature is temporary
     * and never referenced as the assignTo of a message
     * and if so, appends a command that removes it from its container.
     * Otherwise, no command is appended.
     *
     * @param editingDomain the {@link EditingDomain}
     * @param command the {@link CompoundCommand} a command should be added to
     * @param user the {@link AssignmentStatement} that uses the structural feature
     * @param structuralFeature the {@link StructuralFeature}
     */
    private void appendRemoveTemporaryPropertyCommand(EditingDomain editingDomain, CompoundCommand command,
            AssignmentStatement user, StructuralFeature structuralFeature) {
        // If there is an existing assign to and it is a temporary property, we have to delete it first.
        // Assume that it was created for this purpose.
        Message initialMessage = RAMModelUtil.findInitialMessage(user);

        if (structuralFeature != null && structuralFeature.eContainer() == initialMessage
                && !EMFModelUtil.referencedInFeature(structuralFeature, RamPackage.Literals.MESSAGE__ASSIGN_TO)) {
            command.append(RemoveCommand.create(editingDomain, structuralFeature));
        }
    }

}
