package ca.mcgill.sel.ram.ui.views.structural.handler.impl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;

import ca.mcgill.sel.core.COREPartialityType;
import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.ram.Attribute;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.controller.ClassController;
import ca.mcgill.sel.ram.controller.ControllerFactory;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.components.menu.RamLinkedMenu;
import ca.mcgill.sel.ram.ui.utils.Icons;
import ca.mcgill.sel.ram.ui.utils.Strings;
import ca.mcgill.sel.ram.ui.views.handler.BaseHandler;
import ca.mcgill.sel.ram.ui.views.handler.ILinkedMenuListener;
import ca.mcgill.sel.ram.ui.views.structural.AttributeView;
import ca.mcgill.sel.ram.ui.views.structural.handler.IAttributeViewHandler;
import ca.mcgill.sel.ram.util.RAMModelUtil;

/**
 * The default handler for an {@link AttributeView}.
 * 
 * @author mschoettle
 */
public class AttributeViewHandler extends BaseHandler implements IAttributeViewHandler, ILinkedMenuListener {

    private static final String ACTION_ATTRIBUTE_REMOVE = "view.attribute.remove";
    private static final String ACTION_ATTRIBUTE_STATIC = "view.attribute.static";
    private static final String ACTION_ATTRIBUTE_GET = "view.attribute.get";
    private static final String ACTION_ATTRIBUTE_SET = "view.attribute.set";
    private static final String ACTION_ATTRIBUTE_CONCERN_PARTIAL = "view.attribute.partial.concern";
    private static final String ACTION_ATTRIBUTE_PUBLIC_PARTIAL = "view.attribute.partial.public";
    private static final String ACTION_ATTRIBUTE_NOT_PARTIAL = "view.attribute.partial.none";

    private static final String SUBMENU_GET_SET = "sub.getset";
    private static final String SUBMENU_PARTIALITY = "sub.partiality";

    @Override
    public void removeAttribute(AttributeView attributeView) {
        ControllerFactory.INSTANCE.getClassController().removeAttribute(attributeView.getAttribute());
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String actionCommand = event.getActionCommand();
        RamRectangleComponent pressedButton = (RamRectangleComponent) event.getTarget();
        RamLinkedMenu linkedMenu = (RamLinkedMenu) pressedButton.getParentOfType(RamLinkedMenu.class);
        if (linkedMenu != null) {

            AttributeView attributeView = (AttributeView) linkedMenu.getLinkedView();

            if (ACTION_ATTRIBUTE_REMOVE.equals(actionCommand)) {
                removeAttribute(attributeView);
            } else if (ACTION_ATTRIBUTE_STATIC.equals(actionCommand)) {
                setAttributeStatic(attributeView);
            } else if (ACTION_ATTRIBUTE_GET.equals(actionCommand)) {
                generateGetter(attributeView);
            } else if (ACTION_ATTRIBUTE_SET.equals(actionCommand)) {
                generateSetter(attributeView);
            } else if (ACTION_ATTRIBUTE_CONCERN_PARTIAL.equals(actionCommand)) {
                switchPartiality(attributeView, COREPartialityType.CONCERN);
            } else if (ACTION_ATTRIBUTE_PUBLIC_PARTIAL.equals(actionCommand)) {
                switchPartiality(attributeView, COREPartialityType.PUBLIC);
            } else if (ACTION_ATTRIBUTE_NOT_PARTIAL.equals(actionCommand)) {
                switchPartiality(attributeView, COREPartialityType.NONE);
            }
        }
    }

    @Override
    public EObject getEobject(RamRectangleComponent rectangle) {
        return ((AttributeView) rectangle).getAttribute();
    }

    @Override
    public void initMenu(RamLinkedMenu menu) {
        menu.addAction(Strings.MENU_DELETE, Icons.ICON_MENU_TRASH, ACTION_ATTRIBUTE_REMOVE, this, true);

        AttributeView attributeView = (AttributeView) menu.getLinkedView();

        menu.addAction(Strings.MENU_STATIC, Strings.MENU_NO_STATIC,
                Icons.ICON_MENU_STATIC, Icons.ICON_MENU_NOT_STATIC, ACTION_ATTRIBUTE_STATIC, this,
                true, attributeView.getAttribute().isStatic());

        // sub menu for getter/setter
        menu.addSubMenu(1, SUBMENU_GET_SET);
        menu.addAction(Strings.MENU_ATTRIBUTE_GETTER, Icons.ICON_MENU_ATTRIBUTE_GETTER, ACTION_ATTRIBUTE_GET, this,
                SUBMENU_GET_SET, true);
        menu.addAction(Strings.MENU_ATTRIBUTE_SETTER, Icons.ICON_MENU_ATTRIBUTE_SETTER, ACTION_ATTRIBUTE_SET, this,
                SUBMENU_GET_SET, true);

        // sub menu for partiality
        menu.addSubMenu(1, SUBMENU_PARTIALITY);
        menu.addAction(Strings.MENU_PUBLIC_PARTIAL, Icons.ICON_MENU_PUBLIC_PARTIAL, ACTION_ATTRIBUTE_PUBLIC_PARTIAL,
                this, SUBMENU_PARTIALITY, true);
        menu.addAction(Strings.MENU_CONCERN_PARTIAL, Icons.ICON_MENU_CONCERN_PARTIAL, ACTION_ATTRIBUTE_CONCERN_PARTIAL,
                this, SUBMENU_PARTIALITY, true);
        menu.addAction(Strings.MENU_NO_PARTIAL, Icons.ICON_MENU_NOT_PARTIAL, ACTION_ATTRIBUTE_NOT_PARTIAL,
                this, SUBMENU_PARTIALITY, true);

        updateGetterSetterButton(menu);
        updateStaticButton(menu);
        updatePartialityButtons(menu);
        menu.setLinkInCorners(false);
    }

    @Override
    public void setAttributeStatic(AttributeView attributeView) {
        ClassController controller = ControllerFactory.INSTANCE.getClassController();
        controller.switchAttributeStatic(attributeView.getAttribute());
    }

    @Override
    public void generateGetter(AttributeView attributeView) {
        ClassController controller = ControllerFactory.INSTANCE.getClassController();
        controller.createGetterOperation(attributeView.getAttribute());
    }

    @Override
    public void generateSetter(AttributeView attributeView) {
        ClassController controller = ControllerFactory.INSTANCE.getClassController();
        controller.createSetterOperation(attributeView.getAttribute());
    }

    @Override
    public void updateMenu(RamLinkedMenu menu, Notification notification) {

        if (notification.getEventType() == Notification.ADD || notification.getEventType() == Notification.REMOVE) {
            updateGetterSetterButton(menu);

        } else if (notification.getEventType() == Notification.SET
                || notification.getEventType() == Notification.UNSET) {
            if (notification.getFeature() == RamPackage.Literals.STRUCTURAL_FEATURE__STATIC) {
                updateStaticButton(menu);
            } else if (notification.getFeature() == CorePackage.Literals.CORE_MODEL_ELEMENT__PARTIALITY) {
                updatePartialityButtons(menu);
            }
        }
    }

    @Override
    public void switchPartiality(AttributeView attributeView, COREPartialityType type) {
        ClassController controller = ControllerFactory.INSTANCE.getClassController();
        controller.setPartialityType(attributeView.getAttribute(), type);
    }

    /**
     * Updates static button inside the menu.
     * 
     * @param menu - the menu which contains the static button.
     */
    private static void updateStaticButton(RamLinkedMenu menu) {
        Attribute attribute = (Attribute) menu.geteObject();
        menu.toggleAction(attribute.isStatic(), ACTION_ATTRIBUTE_STATIC);
    }
    
    /**
     * Updates buttons for partiality inside the menu.
     * 
     * @param menu - the menu which contains the static button.
     */
    private static void updatePartialityButtons(RamLinkedMenu menu) {
        Attribute attribute = (Attribute) menu.geteObject();
        
        Object obj = attribute.eGet(CorePackage.Literals.CORE_MODEL_ELEMENT__PARTIALITY);
        menu.enableAction(!obj.equals(COREPartialityType.NONE), ACTION_ATTRIBUTE_NOT_PARTIAL);
        menu.enableAction(!obj.equals(COREPartialityType.CONCERN), ACTION_ATTRIBUTE_CONCERN_PARTIAL);
        menu.enableAction(!obj.equals(COREPartialityType.PUBLIC), ACTION_ATTRIBUTE_PUBLIC_PARTIAL);
    }

    /**
     * Updates getter and setter buttons inside the menu.
     * 
     * @param menu - the menu which contains the getter and setter buttons
     */
    private static void updateGetterSetterButton(RamLinkedMenu menu) {
        Attribute attribute = (Attribute) menu.geteObject();
        // Check if get exists
        menu.enableAction(!RAMModelUtil.isGetterUnique(attribute), ACTION_ATTRIBUTE_GET);
        // Check if set exists
        menu.enableAction(!RAMModelUtil.isSetterUnique(attribute), ACTION_ATTRIBUTE_SET);
    }

    @Override
    public List<EObject> getEObjectToListenForUpdateMenu(RamRectangleComponent rectangle) {
        AttributeView attribute = (AttributeView) rectangle;
        ArrayList<EObject> ret = new ArrayList<EObject>();
        ret.add(attribute.getAttribute().eContainer());
        ret.add(attribute.getAttribute());
        return ret;
    }

    @Override
    public RamRectangleComponent getVisualLinkedComponent(RamRectangleComponent link) {
        return link;
    }

}
