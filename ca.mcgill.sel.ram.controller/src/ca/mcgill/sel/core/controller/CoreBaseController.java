package ca.mcgill.sel.core.controller;

import java.util.Collections;
import java.util.Map.Entry;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.ReplaceCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.commons.emf.util.EMFModelUtil;
import ca.mcgill.sel.core.COREImpactNode;
import ca.mcgill.sel.core.COREModelElement;
import ca.mcgill.sel.core.COREPartialityType;
import ca.mcgill.sel.core.COREVisibilityType;
import ca.mcgill.sel.core.CoreFactory;
import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.core.LayoutElement;
import ca.mcgill.sel.core.impl.LayoutContainerMapImpl;
import ca.mcgill.sel.core.impl.LayoutMapImpl;

/**
 * The controller with basic functionality that can be used by any sub-class.
 *
 * @author mschoettle
 */
public abstract class CoreBaseController {

    /**
     * Sets the new value.
     *
     * @param owner the owner
     * @param feature the feature to be changed
     * @param value the new value to be set
     */
    protected void doSet(EObject owner, EStructuralFeature feature, Object value) {
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(owner);

        Command setCommand = SetCommand.create(editingDomain, owner, feature, value);
        doExecute(editingDomain, setCommand);
    }

    /**
     * Adds a new value to the owner.
     *
     * @param owner the owner
     * @param feature the feature
     * @param value the new value to be added
     */
    protected void doAdd(EObject owner, EStructuralFeature feature, Object value) {
        doAdd(owner, feature, value, CommandParameter.NO_INDEX);
    }

    /**
     * Adds a new value to the owner at the given index.
     *
     * @param owner the owner
     * @param feature the feature
     * @param value the new value to be added
     * @param index the index where the value should be added at
     */
    protected void doAdd(EObject owner, EStructuralFeature feature, Object value, int index) {
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(owner);

        Command addCommand = AddCommand.create(editingDomain, owner, feature, value, index);
        doExecute(editingDomain, addCommand);
    }

    /**
     * Removes the given object.
     *
     * @param object the object to remove
     */
    protected void doRemove(EObject object) {
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(object);

        // create remove Command
        Command removeCommand = RemoveCommand.create(editingDomain, object);

        doExecute(editingDomain, removeCommand);
    }

    /**
     * Executes the given command on the given editing domain.
     *
     * @param editingDomain the editing domain the command should be executed on
     * @param command the command to be executed
     */
    protected void doExecute(EditingDomain editingDomain, Command command) {
        if (!command.canExecute()) {
            throw new RuntimeException("Command " + command + " not executable.");
        }

        editingDomain.getCommandStack().execute(command);
    }

    /**
     * Switch the value of a feature with a boolean type.
     *
     * @param owner the owner
     * @param feature the feature
     */
    protected void doSwitch(EObject owner, EStructuralFeature feature) {
        boolean value = (Boolean) owner.eGet(feature);
        value = !value;

        EMFEditUtil.getPropertyDescriptor(owner, feature).setPropertyValue(owner, value);
    }

    /**
     * Create a command that create and add a {@link LayoutElement} to this {@link COREImpactNode}.
     *
     * @param editingDomain the {@link EditingDomain}
     * @param layoutContainerMap the containerLayout of all layouted elements
     * @param root the key of the {@link LayoutContainerMapImpl}
     * @param element the {@link COREImpactNode} that will used this {@link LayoutElement}
     * @param x the x of the {@link LayoutElement}
     * @param y the y of the {@link LayoutElement}
     * @return the {@link Command} created
     */
    protected Command createLayoutElementCommand(EditingDomain editingDomain,
            LayoutContainerMapImpl layoutContainerMap, COREImpactNode root,
            COREImpactNode element, float x, float y) {

        LayoutElement layoutElement = CoreFactory.eINSTANCE.createLayoutElement();
        layoutElement.setX(x);
        layoutElement.setY(y);

        LayoutMapImpl layoutEntry = (LayoutMapImpl) CoreFactory.eINSTANCE.create(CorePackage.Literals.LAYOUT_MAP);
        layoutEntry.setKey(element);
        layoutEntry.setValue(layoutElement);

        Command layoutEntryCommand;

        int index = layoutContainerMap.getValue().indexOfKey(element);
        if (index != -1) {
            Entry<EObject, LayoutElement> existingEntry = layoutContainerMap.getValue().get(index);

            layoutEntryCommand =
                    ReplaceCommand.create(editingDomain, layoutContainerMap,
                            CorePackage.Literals.LAYOUT_CONTAINER_MAP__VALUE, existingEntry,
                            Collections.singleton(layoutEntry));
        } else {
            layoutEntryCommand =
                    AddCommand.create(editingDomain, layoutContainerMap,
                            CorePackage.Literals.LAYOUT_CONTAINER_MAP__VALUE, layoutEntry);
        }

        return layoutEntryCommand;
    }

    /**
     * Creates a new command which moves the given object to the given position.
     *
     * @param editingDomain the {@link EditingDomain}
     * @param containerLayout the containerLayout of all layouted elements
     * @param cont the container the object belongs to
     * @param o the object that should be moved
     * @param x the new x position
     * @param y the new y position
     * @return the command which moves the object to the new position
     */
    protected Command createMoveCommand(EditingDomain editingDomain,
            EMap<EObject, EMap<EObject, LayoutElement>> containerLayout, EObject cont, EObject o, float x, float y) {
        EMap<EObject, LayoutElement> myMap = containerLayout.get(cont);
        LayoutElement layoutElement = myMap.get(o);

        // Use CompoundCommand.
        CompoundCommand moveCompoundCommand = new CompoundCommand();

        // Create SetCommand for x.
        moveCompoundCommand.append(SetCommand.create(editingDomain, layoutElement,
                CorePackage.Literals.LAYOUT_ELEMENT__X, x));
        // Create SetCommand for y.
        moveCompoundCommand.append(SetCommand.create(editingDomain, layoutElement,
                CorePackage.Literals.LAYOUT_ELEMENT__Y, y));

        return moveCompoundCommand;
    }

    /**
     * Creates a new command which removes the entry of the layout map for a given container.
     *
     * @param editingDomain the {@link EditingDomain}
     * @param containerLayout the containerLayout of all layouted elements
     * @param container the container of all layouted elements
     * @param object the object whose layout should be removed
     * @return the command which adds a new entry to the layout map
     */
    protected Command createRemoveLayoutCommand(EditingDomain editingDomain,
            EMap<EObject, EMap<EObject, LayoutElement>> containerLayout, EObject container, EObject object) {
        LayoutContainerMapImpl containerMap = EMFModelUtil.getEntryFromMap(containerLayout, container);
        LayoutMapImpl elementMap = EMFModelUtil.getEntryFromMap(containerMap.getValue(), object);

        // Create command for removing ElementMap (includes the layout element).
        return RemoveCommand.create(editingDomain, elementMap);
    }

    /**
     * Sets the partial type of the given {@link COREModelElement}.
     *
     * @param element the {@link COREModelElement} the reference type should be changed of
     * @param partialityType the new {@link COREPartialityType} to set
     */
    public void setPartialityType(COREModelElement element, COREPartialityType partialityType) {
        doSet(element, CorePackage.Literals.CORE_MODEL_ELEMENT__PARTIALITY, partialityType);
    }

    /**
     * Get the command for updating a {@link COREModelElement} visibility.
     *
     * @param editingDomain - the eidting domain
     * @param element - the {@link COREModelElement} to update
     * @param visibilityType - the new value to set
     * @return The created command
     */
    protected Command changeCoreVisibilityCommand(EditingDomain editingDomain, COREModelElement element,
            COREVisibilityType visibilityType) {
        return SetCommand.create(editingDomain, element, CorePackage.Literals.CORE_MODEL_ELEMENT__VISIBILITY,
                visibilityType);
    }
}
