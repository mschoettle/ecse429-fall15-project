package ca.mcgill.sel.ram.controller;

import java.util.Collections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.commons.emf.util.EMFModelUtil;
import ca.mcgill.sel.core.controller.CoreBaseController;
import ca.mcgill.sel.ram.AbstractMessageView;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.Interaction;
import ca.mcgill.sel.ram.Layout;
import ca.mcgill.sel.ram.LayoutElement;
import ca.mcgill.sel.ram.Lifeline;
import ca.mcgill.sel.ram.RamFactory;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.impl.ContainerMapImpl;
import ca.mcgill.sel.ram.impl.ElementMapImpl;

/**
 * The controller with basic functionality that can be used by any sub-class.
 *
 * @author mschoettle
 */
public class BaseController extends CoreBaseController {

    /**
     * Creates a new instance of {@link BaseController}.
     */
    protected BaseController() {
        // prevent anyone outside this package to instantiate
    }

    /**
     * Creates a new command which adds a new entry to the layout map for a given container.
     *
     * @param editingDomain the {@link EditingDomain}
     * @param container the container of all layouted elements
     * @param object the object that should be layouted
     * @param layoutElement the {@link LayoutElement} for the object
     * @return the command which adds a new entry to the layout map
     */
    protected Command createLayoutElementCommand(EditingDomain editingDomain, EObject container, EObject object,
            LayoutElement layoutElement) {
        Aspect aspect = EMFModelUtil.getRootContainerOfType(container, RamPackage.Literals.ASPECT);

        // Get the ContainerMap.
        ContainerMapImpl containerMap = EMFModelUtil.getEntryFromMap(aspect.getLayout().getContainers(), container);

        // Get the new child descriptor for the entry of the map.
        CommandParameter newChildDescriptor =
                EMFEditUtil.getNewChildDescriptor(containerMap, RamPackage.Literals.CONTAINER_MAP__VALUE);

        // Set the key and value of the ElementMap.
        // We evade using commands here because it is not necessary.
        ElementMapImpl elementMap = (ElementMapImpl) newChildDescriptor.getValue();

        elementMap.setKey(object);
        elementMap.setValue(layoutElement);

        // The newChildDescriptor references the element map we modified, so we can just use
        // the CreateChildCommand.
        return CreateChildCommand.create(editingDomain, containerMap, newChildDescriptor, Collections.EMPTY_LIST);
    }

    /**
     * Creates a new command which moves the given object to the given position.
     *
     * @param editingDomain the {@link EditingDomain}
     * @param container the container the object belongs to
     * @param object the object that should be moved
     * @param x the new x position
     * @param y the new y position
     * @return the command which moves the object to the new position
     */
    protected Command createMoveCommand(EditingDomain editingDomain, EObject container,
            EObject object, float x, float y) {
        Aspect aspect = EMFModelUtil.getRootContainerOfType(container, RamPackage.Literals.ASPECT);

        EMap<EObject, LayoutElement> myMap = aspect.getLayout().getContainers().get(container);
        if (myMap == null) {
            // this is a temporary hack to get stateview to work. Unfortunately, state components (states) are contained
            // in state machines, but the layout is associated to the state view. So we must look for the object
            // not in the state machine, but in the container of the state machine, i.e. the state view
            // TODO: this code should be removed when the state views are properly implemented
            myMap = aspect.getLayout().getContainers().get(container.eContainer());
        }
        LayoutElement layoutElement = myMap.get(object);

        // Use CompoundCommand.
        CompoundCommand moveCompoundCommand = new CompoundCommand();

        // Create SetCommand for x.
        moveCompoundCommand.append(SetCommand.create(editingDomain, layoutElement,
                RamPackage.Literals.LAYOUT_ELEMENT__X, x));
        // Create SetCommand for y.
        moveCompoundCommand.append(SetCommand.create(editingDomain, layoutElement,
                RamPackage.Literals.LAYOUT_ELEMENT__Y, y));

        return moveCompoundCommand;
    }

    /**
     * Creates a new command which removes the entry of the layout map for a given container.
     *
     * @param editingDomain the {@link EditingDomain}
     * @param container the container of all layouted elements
     * @param object the object whose layout should be removed
     * @return the command which adds a new entry to the layout map
     */
    protected static Command createRemoveLayoutElementCommand(EditingDomain editingDomain, EObject container,
            EObject object) {
        Aspect aspect = EMFModelUtil.getRootContainerOfType(container, RamPackage.Literals.ASPECT);

        ContainerMapImpl containerMap = EMFModelUtil.getEntryFromMap(aspect.getLayout().getContainers(), container);
        ElementMapImpl elementMap = EMFModelUtil.getEntryFromMap(containerMap.getValue(), object);

        // Create command for removing ElementMap (includes the layout element).
        return RemoveCommand.create(editingDomain, elementMap);
    }

    /**
     * Creates a command to add the given message view and its initial layout to the aspect.
     * It is assumed that the message view contains one lifeline (target), which is placed at (0, 0).
     *
     * @param editingDomain the {@link EditingDomain}
     * @param owner the aspect to which the message view should be added to
     * @param messageView the message view to add
     * @param specification the message view's specification/interaction
     * @return the command to add the message view and its layout
     */
    protected CompoundCommand createAddMessageViewCommand(EditingDomain editingDomain, Aspect owner,
            AbstractMessageView messageView, Interaction specification) {
        Command addMessageViewCommand = AddCommand.create(editingDomain, owner,
                RamPackage.Literals.ASPECT__MESSAGE_VIEWS, messageView);

        Layout layout = owner.getLayout();

        // Create layout container map for the message view.
        ContainerMapImpl containerMap = (ContainerMapImpl) RamFactory.eINSTANCE
                .create(RamPackage.Literals.CONTAINER_MAP);
        containerMap.setKey(messageView);
        containerMap.setValue(new BasicEMap<EObject, LayoutElement>());

        Command addContainerMapCommand = AddCommand.create(editingDomain, layout,
                RamPackage.Literals.LAYOUT__CONTAINERS, containerMap);

        // Add layout element to layout for first lifeline.
        Lifeline lifeline = specification.getLifelines().get(0);

        LayoutElement layoutElement = RamFactory.eINSTANCE.createLayoutElement();
        layoutElement.setX(0);
        layoutElement.setY(0);

        containerMap.getValue().put(lifeline, layoutElement);

        CompoundCommand compoundCommand = new CompoundCommand();
        compoundCommand.append(addContainerMapCommand);
        compoundCommand.append(addMessageViewCommand);

        return compoundCommand;
    }

}
