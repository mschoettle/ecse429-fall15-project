package ca.mcgill.sel.ram.controller;

import java.util.Collections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.commons.emf.util.EMFModelUtil;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.Layout;
import ca.mcgill.sel.ram.LayoutElement;
import ca.mcgill.sel.ram.RamFactory;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.StateView;
import ca.mcgill.sel.ram.impl.ContainerMapImpl;

/**
 * The controller for {@link StateView}s.
 * 
 * @author Abir Ayed
 */
public class StateViewController extends BaseController {
    
    /**
     * Creates a new instance of {@link StateViewController}.
     */
    protected StateViewController() {
        // Prevent anyone outside this package to instantiate.
    }
    
    /**
     * Creates a new state view as well as its layout element.
     * 
     * @param owner
     *            the {@link Aspect} the state view should be added to
     * @param x
     *            the x position of the state view
     * @param y
     *            the y position of the state view
     */
    public void createNewStateView(Aspect owner, float x, float y) {
        StateView newStateView = RamFactory.eINSTANCE.createStateView();
        
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(owner);
        
        // create add command for state view
        Command addStateViewCommand = AddCommand.create(editingDomain, owner,
                RamPackage.Literals.ASPECT__STATE_VIEWS, newStateView);
        
        Layout layout = owner.getLayout();
        
        // create command for setting the layouts object (key)
        ItemProviderAdapter itemProvider = EMFEditUtil.getItemProvider(layout);
        
        // get the new child descriptor for a new element map
        CommandParameter newChildDescriptor = null;
        
        for (Object object : itemProvider.getNewChildDescriptors(layout, editingDomain, null)) {
            CommandParameter childDescriptor = (CommandParameter) object;
            if (childDescriptor.getFeature() == RamPackage.Literals.LAYOUT__CONTAINERS) {
                newChildDescriptor = childDescriptor;
            }
        }
        
        // set the key and value of the ElementMap
        // we evade using commands here because it is not necessary
        ContainerMapImpl containerMap = (ContainerMapImpl) newChildDescriptor.getValue();
        
        containerMap.setKey(newStateView);
        containerMap.setValue(new BasicEMap<EObject, LayoutElement>());
        
        Command createElementMapCommand = CreateChildCommand.create(editingDomain, layout,
                newChildDescriptor, Collections.EMPTY_LIST);
        
        // create compound command
        CompoundCommand compoundCommand = new CompoundCommand();
        
        compoundCommand.append(addStateViewCommand);
        compoundCommand.append(createElementMapCommand);
        
        doExecute(editingDomain, compoundCommand);
    }
    
    /**
     * Removes the given state view. and removes the layout element.
     * 
     * @param stateView
     *            the state view that should be removed
     */
    public void removeStateView(StateView stateView) {
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(stateView);
        
        // create compound command
        CompoundCommand compoundCommand = new CompoundCommand();
        
        // create command for removing state
        Command removeStateViewCommand = RemoveCommand.create(editingDomain, stateView);
        
        // create command for removing layout element
        // find the element map
        Aspect aspect = EMFModelUtil.getRootContainerOfType(stateView, RamPackage.Literals.ASPECT);
        
        ContainerMapImpl containerMap = (ContainerMapImpl) aspect.getLayout().getContainers()
                .get(aspect.getLayout().getContainers().indexOfKey(stateView));
        
        // create command for removing ElementMap (includes the layout element)
        Command removeElementMapCommand = RemoveCommand.create(editingDomain, containerMap);
        
        compoundCommand.append(removeElementMapCommand);
        compoundCommand.append(removeStateViewCommand);
        
        doExecute(editingDomain, compoundCommand);
    }
}
