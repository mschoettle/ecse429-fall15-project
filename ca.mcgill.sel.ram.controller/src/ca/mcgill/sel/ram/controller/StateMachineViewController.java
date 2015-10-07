package ca.mcgill.sel.ram.controller;

import java.util.Collections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.commons.emf.util.EMFModelUtil;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.LayoutElement;
import ca.mcgill.sel.ram.RamFactory;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.State;
import ca.mcgill.sel.ram.StateMachine;
import ca.mcgill.sel.ram.StateView;
import ca.mcgill.sel.ram.Transition;
import ca.mcgill.sel.ram.impl.ContainerMapImpl;
import ca.mcgill.sel.ram.impl.ElementMapImpl;

/**
 * The controller for {@link StateMachine}s.
 * 
 * @author Abir Ayed
 */
public class StateMachineViewController extends BaseController {
    
    /**
     * Creates a new instance of {@link StateMachineViewController}.
     */
    protected StateMachineViewController() {
        // prevent anyone outside this package to instantiate
    }
    
    /**
     * Removes the given state.
     * and removes the layout element.
     * 
     * @param state the state that should be removed
     */
    public void removeState(State state) {
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(state);
        
        // create compound command
        CompoundCommand compoundCommand = new CompoundCommand();
        
        // create command for removing state
        Command removeStateCommand = RemoveCommand.create(editingDomain, state);
        
        // create commands for removing transitions
        for (Transition transition : state.getIncomings()) {
            compoundCommand.append(RemoveCommand.create(editingDomain, transition));
        }
        for (Transition transition : state.getOutgoings()) {
            // Check if its a self transition so that we don't remove it twice
            if (!state.getIncomings().contains(transition)) {
                compoundCommand.append(RemoveCommand.create(editingDomain, transition));
            }
        }
        
        StateMachine stateMachine = (StateMachine) state.eContainer();
        StateView stateView = (StateView) stateMachine.eContainer();
        
        // Set state machine start state
        if (stateMachine.getStart() != null && stateMachine.getStart().equals(state)) {
            doSet(stateMachine, RamPackage.Literals.STATE_MACHINE__START, null);
        }
        
        // if last state in the state machine remove state machine
        if (stateMachine.getStates().size() == 1) {
            ControllerFactory.INSTANCE.getStateViewViewController().removeStateMachine(stateMachine);
        }
        
        // create command for removing ElementMap (includes the layout element)
        Command removeElementMapCommand = createRemoveLayoutElementCommand(editingDomain, stateView, state);
        
        compoundCommand.append(removeElementMapCommand);
        compoundCommand.append(removeStateCommand);
        
        doExecute(editingDomain, compoundCommand);
    }
    
    /**
     * Creates a new state as well as its layout element.
     * 
     * @param owner the {@link StateMachine} the state should be added to
     * @param name the name of the state
     * @param x the x position of the state
     * @param y the y position of the state
     */
    public void createNewState(StateMachine owner, String name, float x, float y) {
        State newState = RamFactory.eINSTANCE.createState();
        newState.setName(name);
        
        LayoutElement layoutElement = RamFactory.eINSTANCE.createLayoutElement();
        layoutElement.setX(x);
        layoutElement.setY(y);
        
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(owner);
        
        // create add command for state
        Command addStateCommand = AddCommand.create(editingDomain, owner, RamPackage.Literals.STATE_MACHINE__STATES,
                newState);
        
        // create commands for layout element
        StateView stateView = (StateView) owner.eContainer();
        Aspect aspect = (Aspect) stateView.eContainer();
        
        // get the container map
        ContainerMapImpl containerMap = EMFModelUtil.getEntryFromMap(aspect.getLayout().getContainers(), stateView);
        
        // create command for setting the layouts object (key)
        ItemProviderAdapter itemProvider = EMFEditUtil.getItemProvider(containerMap);
        
        // get the new child descriptor for a new element map
        CommandParameter newChildDescriptor = null;
        
        for (Object object : itemProvider.getNewChildDescriptors(containerMap, editingDomain, null)) {
            CommandParameter childDescriptor = (CommandParameter) object;
            if (childDescriptor.getFeature() == RamPackage.Literals.CONTAINER_MAP__VALUE) {
                newChildDescriptor = childDescriptor;
            }
        }
        
        // set the key and value of the ElementMap
        // we evade using commands here because it is not necessary
        ElementMapImpl elementMap = (ElementMapImpl) newChildDescriptor.getValue();
        
        elementMap.setKey(newState);
        elementMap.setValue(layoutElement);
        
        Command createElementMapCommand = CreateChildCommand.create(editingDomain, containerMap, newChildDescriptor,
                Collections.EMPTY_LIST);
        
        CompoundCommand compoundCommand = new CompoundCommand();
        // if the layoutelement is added first, when the state machine view gets notified (about a new state) access to
        // the layout element is already possible
        compoundCommand.append(addStateCommand);
        compoundCommand.append(createElementMapCommand);
        
        // Add this state as the start state if there is none yet (then this will probably be the first state).
        if (owner.getStart() == null) {
            Command setStartCommand = SetCommand.create(editingDomain, owner,
                    RamPackage.Literals.STATE_MACHINE__START, newState);
            compoundCommand.append(setStartCommand);
        }
        
        doExecute(editingDomain, compoundCommand);
        
    }
    
    /**
     * Creates a Transition between the two given states.
     * 
     * @param owner the {@link StateMachine} the transition should be added to
     * @param from the first state
     * @param to the second state
     */
    public void createTransition(StateMachine owner, State from, State to) {
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(owner);
        
        CompoundCommand compoundCommand = new CompoundCommand();
        Transition transition = RamFactory.eINSTANCE.createTransition();
        
        // create commands for add this transition as outcoming and incoming transition of a state
        compoundCommand.append(AddCommand.create(editingDomain, to,
                RamPackage.Literals.STATE__INCOMINGS, transition));
        compoundCommand.append(AddCommand.create(editingDomain, from,
                RamPackage.Literals.STATE__OUTGOINGS, transition));
        
        // create add command for transition
        compoundCommand.append(AddCommand.create(editingDomain, owner,
                RamPackage.Literals.STATE_MACHINE__TRANSITIONS, transition));
        
        doExecute(editingDomain, compoundCommand);
    }
    
    /**
     * Sets the initial state to the given {@link State}.
     * 
     * @param stateMachine the {@link StateMachine} the initial state to set for
     * @param state the state to be the initial state
     */
    public void setStartState(StateMachine stateMachine, State state) {
        if (stateMachine.getStart() != state) {
            doSet(stateMachine, RamPackage.Literals.STATE_MACHINE__START, state);
        }
    }
}
