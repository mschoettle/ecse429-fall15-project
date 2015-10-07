package ca.mcgill.sel.ram.controller;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.ram.RamFactory;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.StateMachine;
import ca.mcgill.sel.ram.StateView;

/**
 * The controller for {@link StateMachine} and {@link StateView}.
 * 
 * @author Abir Ayed
 */
public class StateViewViewController extends BaseController {
    
    /**
     * Creates a new instance of {@link StateViewViewController}.
     */
    protected StateViewViewController() {
        // Prevent anyone outside this package to instantiate.
    }
    
    /**
     * Removes the given state machine.
     * 
     * @param stateMachine the state machine that should be removed
     */
    public void removeStateMachine(StateMachine stateMachine) {
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(stateMachine);
        
        // create command for removing state
        Command removeStateCommand = RemoveCommand.create(editingDomain, stateMachine);
        doExecute(editingDomain, removeStateCommand);
    }
    
    /**
     * Creates a new state machine.
     * 
     * @param owner the {@link StateMachine} the state should be added to
     */
    public void createNewStateMachine(StateView owner) {
        
        StateMachine newStateMachine = RamFactory.eINSTANCE.createStateMachine();
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(owner);
        
        // create add command for state
        Command addStateMachineCommand = AddCommand.create(editingDomain, owner,
                RamPackage.Literals.STATE_VIEW__STATE_MACHINES, newStateMachine);
        doExecute(editingDomain, addStateMachineCommand);
    }
}
