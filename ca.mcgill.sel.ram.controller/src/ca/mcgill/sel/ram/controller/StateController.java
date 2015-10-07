package ca.mcgill.sel.ram.controller;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.EditingDomain;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.commons.emf.util.EMFModelUtil;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.State;
import ca.mcgill.sel.ram.StateMachine;

/**
 * The controller for {@link State}s.
 * 
 * @author Abir Ayed
 */
public class StateController extends BaseController {
    
    /**
     * Creates a new instance of {@link StateController}.
     */
    protected StateController() {
        // Prevent anyone outside this package to instantiate.
    }
    
    /**
     * Moves the given state to a new position.
     * 
     * @param state
     *            the state to move
     * @param x
     *            the new x position
     * @param y
     *            the new y position
     */
    public void moveState(State state, float x, float y) {
        Aspect aspect = EMFModelUtil.getRootContainerOfType(state, RamPackage.Literals.ASPECT);
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(aspect);
        
        StateMachine stateMachine = (StateMachine) state.eContainer();
        
        Command moveStateCommand = createMoveCommand(editingDomain, stateMachine, state, x, y);
        
        doExecute(editingDomain, moveStateCommand);
    }
}