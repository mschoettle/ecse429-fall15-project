package ca.mcgill.sel.ram.controller;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.commons.emf.util.EMFModelUtil;
import ca.mcgill.sel.core.COREPartialityType;
import ca.mcgill.sel.ram.AbstractMessageView;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.AspectMessageView;
import ca.mcgill.sel.ram.Instantiation;
import ca.mcgill.sel.ram.Interaction;
import ca.mcgill.sel.ram.MessageView;
import ca.mcgill.sel.ram.Operation;
import ca.mcgill.sel.ram.OriginalBehaviorExecution;
import ca.mcgill.sel.ram.RamFactory;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.impl.ContainerMapImpl;
import ca.mcgill.sel.ram.util.RAMModelUtil;

/**
 * The controller for {@link Aspect}s.
 *
 * @author mschoettle
 * @author oalam
 */
public class AspectController extends BaseController {

    /**
     * Creates a new instance of {@link AspectController}.
     */
    protected AspectController() {
        // prevent anyone outside this package to instantiate
    }

    /**
     * Creates a new instantiation for the given aspect.
     *
     * @param owner the aspect the instantiation should be added to
     * @param externalAspect the referenced aspect (external aspect) of the {@link Instantiation}
     */
    public void createInstantiation(Aspect owner, Aspect externalAspect) {
        Instantiation instantiation = RamFactory.eINSTANCE.createInstantiation();
        instantiation.setSource(externalAspect);
        doAdd(owner, RamPackage.Literals.ASPECT__INSTANTIATIONS, instantiation);
    }

    /**
     * Delete the given instantiation.
     *
     * @param instantiation the instantiation to be deleted
     */
    public void deleteInstantiation(Instantiation instantiation) {
        doRemove(instantiation);
    }

    /**
     * Creates a new message view for the given operation.
     * Initializes the message view with the initial call.
     * Furthermore, creates a container map for the layout to be able to save
     * the message views layout.
     *
     * @param owner the owner of the message view
     * @param operation the operation specified by this message view
     */
    public void createMessageView(Aspect owner, Operation operation) {
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(owner);

        MessageView messageView = RAMModelUtil.createMessageView(operation);
        CompoundCommand command = createAddMessageViewCommand(editingDomain, owner, messageView,
                messageView.getSpecification());

        doExecute(editingDomain, command);
    }

    /**
     * Creates a new aspect message view that advices the given operation.
     * Adds the aspect message view to the "affected by" list of the operation's message view.
     * If no message view exists one, an empty message view is created first.
     * If the operation is partial, a message view with no specification is added.
     * Furthermore, creates a container map for the layout to be able to save
     * the message views layout.
     *
     * @param owner the owner of the message view
     * @param operation the operation specified by this message view
     */
    public void createAspectMessageView(Aspect owner, Operation operation) {
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(owner);

        AspectMessageView messageView = RamFactory.eINSTANCE.createAspectMessageView();
        messageView.setPointcut(operation);

        Interaction advice = RAMModelUtil.createInteraction(operation);
        messageView.setAdvice(advice);

        /**
         * Add the original behaviour execution fragment.
         */
        OriginalBehaviorExecution fragment = RamFactory.eINSTANCE.createOriginalBehaviorExecution();
        fragment.getCovered().add(advice.getLifelines().get(0));
        advice.getFragments().add(1, fragment);

        Command addMessageViewCommand = createAddMessageViewCommand(editingDomain, owner, messageView, advice);

        /**
         * Advice the message view. If it doesn't exist yet, create one.
         */
        MessageView existingMessageView = RAMModelUtil.getMessageViewFor(owner, operation);
        Command addActualMessageView = null;
        if (existingMessageView == null) {
            existingMessageView = RAMModelUtil.createMessageView(operation);
            /**
             * Partial operations have empty message views (i.e., no specification).
             */
            if (operation.getPartiality() != COREPartialityType.NONE) {
                existingMessageView.setSpecification(null);
                addActualMessageView = AddCommand.create(editingDomain, owner,
                        RamPackage.Literals.ASPECT__MESSAGE_VIEWS, existingMessageView);
            } else {
                addActualMessageView = createAddMessageViewCommand(editingDomain, owner, existingMessageView,
                        existingMessageView.getSpecification());
            }
        }

        Command adviceMessageViewCommand = AddCommand.create(editingDomain, existingMessageView,
                RamPackage.Literals.ABSTRACT_MESSAGE_VIEW__AFFECTED_BY, messageView);

        CompoundCommand compoundCommand = new CompoundCommand();
        if (addActualMessageView != null) {
            compoundCommand.append(addActualMessageView);
        }
        compoundCommand.append(addMessageViewCommand);
        compoundCommand.append(adviceMessageViewCommand);

        doExecute(editingDomain, compoundCommand);
    }

    /**
     * Creates a command to remove the given message view and its layout.
     *
     * @param messageView the message view to remove
     * @return the command that, when executed, removes the message view and its layout
     */
    public static Command createRemoveMessageViewCommand(AbstractMessageView messageView) {
        Aspect aspect = EMFModelUtil.getRootContainerOfType(messageView, RamPackage.Literals.ASPECT);
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(aspect);

        CompoundCommand compoundCommand = new CompoundCommand();

        compoundCommand.append(RemoveCommand.create(editingDomain, messageView));

        ContainerMapImpl layout = EMFModelUtil.getEntryFromMap(aspect.getLayout().getContainers(), messageView);
        if (layout != null) {
            compoundCommand.append(RemoveCommand.create(editingDomain, layout));
        }

        return compoundCommand;
    }

}
