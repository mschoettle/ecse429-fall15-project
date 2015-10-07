package ca.mcgill.sel.ram.ui.views.structural.handler.impl;

import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;

import ca.mcgill.sel.ram.controller.ControllerFactory;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.components.menu.RamLinkedMenu;
import ca.mcgill.sel.ram.ui.utils.Icons;
import ca.mcgill.sel.ram.ui.utils.RamModelUtils;
import ca.mcgill.sel.ram.ui.utils.Strings;
import ca.mcgill.sel.ram.ui.views.handler.BaseHandler;
import ca.mcgill.sel.ram.ui.views.handler.ILinkedMenuListener;
import ca.mcgill.sel.ram.ui.views.structural.ParameterView;
import ca.mcgill.sel.ram.ui.views.structural.handler.IParameterViewHandler;

/**
 * The default handler for {@link ParameterView}.
 * 
 * @author g.Nicolas
 *
 */
public class ParameterViewHandler extends BaseHandler implements IParameterViewHandler, ILinkedMenuListener {

    private static final String ACTION_PARAMETER_REMOVE = "view.parameter.remove";
    private static final String ACTION_PARAMETER_ADD_BEFORE = "view.parameter.add.before";
    private static final String ACTION_PARAMETER_ADD_AFTER = "view.parameter.add.after";

    private static final String SUBMENU_ADD_PARAMETER = "sub.parameter.add";

    @Override
    public void removeParameter(ParameterView parameterView) {
        ControllerFactory.INSTANCE.getClassController().removeParameter(parameterView.getParameter());
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
        RamRectangleComponent pressedButton = (RamRectangleComponent) event.getTarget();
        RamLinkedMenu linkedMenu = (RamLinkedMenu) pressedButton.getParentOfType(RamLinkedMenu.class);
        if (linkedMenu != null) {

            ParameterView parameterView = (ParameterView) linkedMenu.getLinkedView();
            if (command.equals(ACTION_PARAMETER_REMOVE)) {
                removeParameter(parameterView);
            } else if (command.equals(ACTION_PARAMETER_ADD_BEFORE)) {
                addParameterBefore(parameterView);
            } else if (command.equals(ACTION_PARAMETER_ADD_AFTER)) {
                addParameterAfter(parameterView);
            }
        }
    }

    @Override
    public EObject getEobject(RamRectangleComponent rectangle) {
        return ((ParameterView) rectangle).getParameter();
    }

    @Override
    public void initMenu(RamLinkedMenu menu) {
        menu.addAction(Strings.MENU_DELETE, Icons.ICON_MENU_TRASH, ACTION_PARAMETER_REMOVE, this, true);

        menu.addSubMenu(1, SUBMENU_ADD_PARAMETER);
        menu.addAction(Strings.MENU_PARAMETER_ADD_BEFORE, Icons.ICON_MENU_ADD_PARAMETER_BEFORE,
                ACTION_PARAMETER_ADD_BEFORE, this, true);
        menu.addAction(Strings.MENU_PARAMETER_ADD_AFTER, Icons.ICON_MENU_ADD_PARAMETER_AFTER,
                ACTION_PARAMETER_ADD_AFTER, this, true);
    }

    @Override
    public List<EObject> getEObjectToListenForUpdateMenu(RamRectangleComponent rectangle) {
        return null;
    }

    @Override
    public void updateMenu(RamLinkedMenu menu, Notification notification) {
    }

    @Override
    public RamRectangleComponent getVisualLinkedComponent(RamRectangleComponent link) {
        ParameterView parameterView = (ParameterView) link;
        return parameterView.getNameField();
    }

    @Override
    public void addParameterBefore(ParameterView parameterView) {
        RamModelUtils.createParameterAroundParameter(true, parameterView);
    }

    @Override
    public void addParameterAfter(ParameterView parameterView) {
        RamModelUtils.createParameterAroundParameter(false, parameterView);
    }

}
