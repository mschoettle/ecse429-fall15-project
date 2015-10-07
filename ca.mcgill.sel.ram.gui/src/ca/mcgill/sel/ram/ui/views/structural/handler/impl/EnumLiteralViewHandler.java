package ca.mcgill.sel.ram.ui.views.structural.handler.impl;

import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;

import ca.mcgill.sel.ram.controller.ControllerFactory;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.components.menu.RamLinkedMenu;
import ca.mcgill.sel.ram.ui.utils.Icons;
import ca.mcgill.sel.ram.ui.utils.Strings;
import ca.mcgill.sel.ram.ui.views.handler.BaseHandler;
import ca.mcgill.sel.ram.ui.views.handler.ILinkedMenuListener;
import ca.mcgill.sel.ram.ui.views.structural.EnumLiteralView;
import ca.mcgill.sel.ram.ui.views.structural.handler.IEnumLiteralViewHandler;

/**
 * The default handler for a {@link EnumLiteralView}.
 * 
 * @author Franz
 */
public class EnumLiteralViewHandler extends BaseHandler implements IEnumLiteralViewHandler, ILinkedMenuListener {

    private static final String ACTION_LITERAL_REMOVE = "view.literal.remove";

    @Override
    public void removeLiteral(EnumLiteralView eLiteralView) {
        ControllerFactory.INSTANCE.getEnumController().removeLiteral(eLiteralView.getLiteral());
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String actionCommand = event.getActionCommand();

        RamRectangleComponent pressedButton = (RamRectangleComponent) event.getTarget();
        RamLinkedMenu linkedMenu = (RamLinkedMenu) pressedButton.getParentOfType(RamLinkedMenu.class);
        if (linkedMenu != null) {

            EnumLiteralView literalView = (EnumLiteralView) linkedMenu.getLinkedView();

            if (ACTION_LITERAL_REMOVE.equals(actionCommand)) {
                removeLiteral(literalView);
            }
        }
    }

    @Override
    public EObject getEobject(RamRectangleComponent rectangle) {
        return ((EnumLiteralView) rectangle).getLiteral();
    }

    @Override
    public void initMenu(RamLinkedMenu menu) {
        menu.addAction(Strings.MENU_DELETE, Icons.ICON_MENU_TRASH, ACTION_LITERAL_REMOVE, this, true);

        menu.setLinkInCorners(false);
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
        return link;
    }

}
