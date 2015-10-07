package ca.mcgill.sel.ram.ui.views.structural.handler.impl;

import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;

import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.components.menu.RamLinkedMenu;
import ca.mcgill.sel.ram.ui.utils.Icons;
import ca.mcgill.sel.ram.ui.utils.Strings;
import ca.mcgill.sel.ram.ui.views.handler.ILinkedMenuListener;
import ca.mcgill.sel.ram.ui.views.structural.ClassifierView;
import ca.mcgill.sel.ram.ui.views.structural.handler.IClassifierViewHandler;

/**
 * The default handler for a {@link ClassifierView}. Handlers for Sub-classes of {@link ca.mcgill.sel.ram.Classifier}
 * may use the default behavior of this handler.
 * 
 * @author mschoettle
 */
public abstract class ClassifierViewHandler extends BaseViewHandler implements IClassifierViewHandler,
        ILinkedMenuListener {

    /**
     * The action to add a new operation.
     */
    protected static final String ACTION_OPERATION_ADD = "view.class.operation.add";
    /**
     * The action to add a new constructor.
     */
    protected static final String ACTION_CONSTRUCTOR_ADD = "view.class.constructor.add";

    /**
     * The sub menu id which contains actions related to operation.
     */
    protected static final String SUBMENU_OPERATION = "sub.operation";

    @Override
    public void actionPerformed(ActionEvent event) {
        String actionCommand = event.getActionCommand();
        RamRectangleComponent pressedButton = (RamRectangleComponent) event.getTarget();
        RamLinkedMenu linkedMenu = (RamLinkedMenu) pressedButton.getParentOfType(RamLinkedMenu.class);
        if (linkedMenu != null) {

            ClassifierView<?> clazz = (ClassifierView<?>) linkedMenu.getLinkedView();

            if (ACTION_OPERATION_ADD.equals(actionCommand)) {
                createOperation(clazz);
            } else if (ACTION_CONSTRUCTOR_ADD.equals(actionCommand)) {
                createConstructor(clazz);
            }

        }
        super.actionPerformed(event);
    }

    @Override
    public List<EObject> getEObjectToListenForUpdateMenu(RamRectangleComponent rectangle) {
        return super.getEObjectToListenForUpdateMenu(rectangle);
    }

    @Override
    public void updateMenu(RamLinkedMenu menu, Notification notification) {
        super.updateMenu(menu, notification);
    }

    @Override
    public void initMenu(RamLinkedMenu menu) {
        super.initMenu(menu);
        menu.addSubMenu(1, SUBMENU_OPERATION);
        menu.addAction(Strings.MENU_OPERATION_ADD, Icons.ICON_MENU_ADD_OPERATION, ACTION_OPERATION_ADD, this,
                SUBMENU_OPERATION, true);
        menu.addAction(Strings.MENU_CONSTRUCTOR_ADD, Icons.ICON_MENU_ADD_CONSTRUCTOR, ACTION_CONSTRUCTOR_ADD, this,
                SUBMENU_OPERATION, true);
    }

}
