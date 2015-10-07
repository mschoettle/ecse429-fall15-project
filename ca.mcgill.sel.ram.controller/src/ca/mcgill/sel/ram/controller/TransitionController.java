package ca.mcgill.sel.ram.controller;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.commons.emf.util.EMFModelUtil;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.Transition;

/**
 * The controller for {@link Transition}s and {@link Transition}s.
 * 
 * @author aayed2 (Abir Ayed)
 */
public class TransitionController extends BaseController {
    
    /**
     * Creates a new instance of {@link TransitionController}.
     */
    protected TransitionController() {
        // prevent anyone outside this package to instantiate
    }
    
    /**
     * Deletes the given {@link Transition}.
     * 
     * @param transition
     *            the {@link Transition} to delete
     */
    public void deleteTransition(Transition transition) {
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(transition);
        
        // create compound command
        CompoundCommand compoundCommand = new CompoundCommand();
        
        // create remove command for transition
        compoundCommand.append(RemoveCommand.create(editingDomain, transition));
        
        // create commands for removing this transition as outcoming and incoming transition of a state
        compoundCommand.append(RemoveCommand.create(editingDomain, transition.getEndState(),
                RamPackage.Literals.STATE__INCOMINGS, transition));
        compoundCommand.append(RemoveCommand.create(editingDomain, transition.getStartState(),
                RamPackage.Literals.STATE__OUTGOINGS, transition));
        
        doExecute(editingDomain, compoundCommand);
    }
    
    /**
     * Switches the navigable property of the given transition end.
     * 
     * @param transition the {@link Transition} the navigable property should be switched of
     */
    public void switchNavigable(Transition transition) {
        Aspect aspect = EMFModelUtil.getRootContainerOfType(transition, RamPackage.Literals.ASPECT);
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(aspect);
        
        // use CompoundCommand
        CompoundCommand switchDirection = new CompoundCommand();
        
        // create SetCommand for endState
        switchDirection.append(SetCommand.create(editingDomain, transition,
                RamPackage.Literals.TRANSITION__END_STATE, transition.getStartState()));
        
        // create SetCommand for startState
        switchDirection.append(SetCommand.create(editingDomain, transition,
                RamPackage.Literals.TRANSITION__START_STATE, transition.getEndState()));
        
        doExecute(editingDomain, switchDirection);
    }
    
}
