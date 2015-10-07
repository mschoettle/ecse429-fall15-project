package ca.mcgill.sel.ram.ui.views.handler;

import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;

import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.components.menu.RamLinkedMenu;
import ca.mcgill.sel.ram.ui.events.listeners.ActionListener;

/**
 * The interface define actions to override to add a {@link RamLinkedMenu} to a component.
 * 
 * @author g.Nicolas
 *
 */
public interface ILinkedMenuListener extends ActionListener {

    /**
     * Returns the eObject to link with a menu.
     * 
     * @param rectangle - the linked component
     * @return the eObject to link with a menu.
     */
    EObject getEobject(RamRectangleComponent rectangle);

    /**
     * Define all the actions and sub-menus to add at the initialization.
     * 
     * @param menu - the linked menu reference to add actions and sub-menus
     */
    void initMenu(RamLinkedMenu menu);

    /**
     * Returns a list of eObject to listen for update the menu. EObject will send notifications when they will be
     * modified and call the updateMenu method.
     * 
     * @param rectangle - the linked component view.
     * @return the list of EObject to listen.
     */
    List<EObject> getEObjectToListenForUpdateMenu(RamRectangleComponent rectangle);

    /**
     * Update menu actions in function of received notification. EObjects registered in the method
     * getEObjectToListenForUpdateMenu will call this method when they will be modified. To not update at each EObject
     * modification, you can use the Notification.eventType.
     *
     * @param menu - the menu containing actions to update
     * @param notification - notification sent by a registered EObject which is modified.
     */
    void updateMenu(RamLinkedMenu menu, Notification notification);

    /**
     * Returns the visually linked component. It can be the given parameter or any related component (child or parent).
     * 
     * @param link - the linked component related to the linked EObject
     * @return the visually linked component
     */
    RamRectangleComponent getVisualLinkedComponent(RamRectangleComponent link);

}
