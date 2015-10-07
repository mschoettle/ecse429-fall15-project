package ca.mcgill.sel.ram.ui.views.structural.handler.impl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EContentAdapter;

import ca.mcgill.sel.commons.emf.util.EMFModelUtil;
import ca.mcgill.sel.core.COREPartialityType;
import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.AspectMessageView;
import ca.mcgill.sel.ram.MessageView;
import ca.mcgill.sel.ram.Operation;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.controller.ClassController;
import ca.mcgill.sel.ram.controller.ControllerFactory;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.components.menu.RamLinkedMenu;
import ca.mcgill.sel.ram.ui.scenes.DisplayAspectScene;
import ca.mcgill.sel.ram.ui.utils.Icons;
import ca.mcgill.sel.ram.ui.utils.RamModelUtils;
import ca.mcgill.sel.ram.ui.utils.Strings;
import ca.mcgill.sel.ram.ui.views.handler.BaseHandler;
import ca.mcgill.sel.ram.ui.views.handler.ILinkedMenuListener;
import ca.mcgill.sel.ram.ui.views.structural.OperationView;
import ca.mcgill.sel.ram.ui.views.structural.handler.IOperationViewHandler;
import ca.mcgill.sel.ram.util.RAMModelUtil;

/**
 * The default handler for an {@link OperationView}.
 * 
 * @author mschoettle
 */
public class OperationViewHandler extends BaseHandler implements IOperationViewHandler, ILinkedMenuListener {

    private static final String ACTION_OPERATION_REMOVE = "view.operation.remove";
    private static final String ACTION_PARAMETER_ADD = "view.parameter.add";
    private static final String ACTION_OPERATION_STATIC = "view.operation.static";
    private static final String ACTION_OPERATION_ABSTRACT = "view.operation.abstract";
    private static final String ACTION_OPERATION_CONCERN_PARTIAL = "view.operation.concern.partial";
    private static final String ACTION_OPERATION_PUBLIC_PARTIAL = "view.operation.public.partial";
    private static final String ACTION_OPERATION_NOT_PARTIAL = "view.operation.not.partial";
    private static final String ACTION_OPERATION_GOTO_MESSAGEVIEW = "view.operation.messageview";
    private static final String ACTION_OPERATION_ADVICE = "view.operation.advice";

    private static final String SUBMENU_CLASS_PARTIALITY = "sub.class.partiality";
    private static final String SUBMENU_CLASS_MORE = "sub.class.more";

    @Override
    public void createParameter(final OperationView operationView) {
        RamModelUtils.createParameterEndOperation(operationView);
    }

    @Override
    public void removeOperation(OperationView operationView) {
        ControllerFactory.INSTANCE.getClassController().removeOperation(operationView.getOperation());
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String actionCommand = event.getActionCommand();
        RamRectangleComponent pressedButton = (RamRectangleComponent) event.getTarget();
        RamLinkedMenu linkedMenu = (RamLinkedMenu) pressedButton.getParentOfType(RamLinkedMenu.class);
        if (linkedMenu != null) {

            OperationView operationView = (OperationView) linkedMenu.getLinkedView();

            if (ACTION_OPERATION_REMOVE.equals(actionCommand)) {
                removeOperation(operationView);
            } else if (ACTION_PARAMETER_ADD.equals(actionCommand)) {
                createParameter(operationView);
            } else if (ACTION_OPERATION_STATIC.equals(actionCommand)) {
                setOperationStatic(operationView);
            } else if (ACTION_OPERATION_ABSTRACT.equals(actionCommand)) {
                switchToAbstract(operationView);
            } else if (ACTION_OPERATION_CONCERN_PARTIAL.equals(actionCommand)) {
                switchPartiality(operationView, COREPartialityType.CONCERN);
            } else if (ACTION_OPERATION_PUBLIC_PARTIAL.equals(actionCommand)) {
                switchPartiality(operationView, COREPartialityType.PUBLIC);
            } else if (ACTION_OPERATION_NOT_PARTIAL.equals(actionCommand)) {
                switchPartiality(operationView, COREPartialityType.NONE);
            } else if (ACTION_OPERATION_GOTO_MESSAGEVIEW.equals(actionCommand)) {
                goToMessageView(operationView);
            } else if (ACTION_OPERATION_ADVICE.equals(actionCommand)) {
                goToAdviceView(operationView);
            }
        }
    }

    @Override
    public void setOperationStatic(OperationView operationView) {
        ClassController controller = ControllerFactory.INSTANCE.getClassController();
        controller.switchOperationStatic(operationView.getOperation());
    }

    @Override
    public void switchToAbstract(OperationView operationView) {
        ClassController controller = ControllerFactory.INSTANCE.getClassController();
        controller.switchOperationAbstract(operationView.getOperation());
    }

    @Override
    public void switchPartiality(OperationView operationView, COREPartialityType type) {
        ClassController controller = ControllerFactory.INSTANCE.getClassController();
        controller.setPartialityType(operationView.getOperation(), type);
    }

    @Override
    public void goToMessageView(OperationView operationView) {
        Operation operation = operationView.getOperation();
        final DisplayAspectScene displayAspectScene = RamApp.getActiveAspectScene();
        final Aspect aspect = EMFModelUtil.getRootContainerOfType(operation, RamPackage.Literals.ASPECT);

        boolean isPartial = operation.getPartiality() != COREPartialityType.NONE;
        boolean isAbstract = operation.isAbstract();

        if (!isPartial && !isAbstract) {
            if (!RAMModelUtil.isMessageViewDefined(aspect, operation)) {
                // Register a listener so that the message view can be displayed
                // when it was successfully added.
                aspect.eAdapters().add(new EContentAdapter() {
                    @Override
                    public void notifyChanged(Notification notification) {
                        if (notification.getFeature() == RamPackage.Literals.ASPECT__MESSAGE_VIEWS
                                && notification.getEventType() == Notification.ADD) {
                            if (notification.getNewValue() instanceof MessageView) {
                                MessageView messageView = (MessageView) notification.getNewValue();
                                displayAspectScene.showMessageView(messageView);
                                aspect.eAdapters().remove(this);
                            }
                        }
                    }
                });

                ControllerFactory.INSTANCE.getAspectController().createMessageView(aspect, operation);
            } else {
                MessageView messageView = RAMModelUtil.getMessageViewFor(aspect, operation);
                displayAspectScene.showMessageView(messageView);
            }
        } else {
            displayAspectScene.displayPopup(Strings.POPUP_PARTIAL_ABSTRACT_NO_BEHAVIOR);
        }
    }

    @Override
    public void goToAdviceView(OperationView operationView) {
        Operation operation = operationView.getOperation();
        final DisplayAspectScene displayAspectScene = RamApp.getActiveAspectScene();
        final Aspect aspect = EMFModelUtil.getRootContainerOfType(operation, RamPackage.Literals.ASPECT);

        // TODO: Temporary: In case there is already an aspect message view that advices this operation, show that one.
        MessageView messageView = RAMModelUtil.getMessageViewFor(aspect, operation);
        if (messageView != null && messageView.getAffectedBy().size() > 0) {
            displayAspectScene.showMessageView(messageView.getAffectedBy().get(0));
        } else {
            // Register a listener so that the message view can be displayed
            // when it was successfully added.
            aspect.eAdapters().add(new EContentAdapter() {
                @Override
                public void notifyChanged(Notification notification) {
                    if (notification.getFeature() == RamPackage.Literals.ASPECT__MESSAGE_VIEWS
                            && notification.getEventType() == Notification.ADD) {
                        if (notification.getNewValue() instanceof AspectMessageView) {
                            AspectMessageView messageView = (AspectMessageView) notification.getNewValue();
                            displayAspectScene.showMessageView(messageView);
                            aspect.eAdapters().remove(this);
                        }
                    }
                }
            });

            ControllerFactory.INSTANCE.getAspectController().createAspectMessageView(aspect, operation);
        }

    }

    @Override
    public EObject getEobject(RamRectangleComponent rectangle) {
        return ((OperationView) rectangle).getOperation();
    }

    @Override
    public void initMenu(RamLinkedMenu menu) {
        menu.addAction(Strings.MENU_DELETE, Icons.ICON_MENU_TRASH, ACTION_OPERATION_REMOVE, this, true);

        OperationView operationView = (OperationView) menu.getLinkedView();
        if (operationView.isMutable()) {

            menu.addAction(Strings.MENU_PARAMETER_ADD, Icons.ICON_MENU_ADD_PARAMETER, ACTION_PARAMETER_ADD, this, true);

            menu.addSubMenu(1, SUBMENU_CLASS_PARTIALITY);
            menu.addAction(Strings.MENU_PUBLIC_PARTIAL, Icons.ICON_MENU_PUBLIC_PARTIAL, ACTION_OPERATION_PUBLIC_PARTIAL,
                    this, SUBMENU_CLASS_PARTIALITY, true);
            menu.addAction(Strings.MENU_CONCERN_PARTIAL, Icons.ICON_MENU_CONCERN_PARTIAL,
                    ACTION_OPERATION_CONCERN_PARTIAL, this, SUBMENU_CLASS_PARTIALITY, true);
            menu.addAction(Strings.MENU_NO_PARTIAL, Icons.ICON_MENU_NOT_PARTIAL, ACTION_OPERATION_NOT_PARTIAL,
                    this, SUBMENU_CLASS_PARTIALITY, true);

            menu.addSubMenu(1, SUBMENU_CLASS_MORE);

            Operation operation = operationView.getOperation();
            if (!RAMModelUtil.isConstructorOrDestructor(operation)) {
                menu.addAction(Strings.MENU_STATIC, Strings.MENU_NO_STATIC, Icons.ICON_MENU_STATIC,
                        Icons.ICON_MENU_NOT_STATIC,
                        ACTION_OPERATION_STATIC, this, SUBMENU_CLASS_MORE, true, false);
            }

            menu.addAction(Strings.MENU_ABSTRACT, Strings.MENU_NOT_ABSTRACT, Icons.ICON_MENU_ABSTRACT,
                    Icons.ICON_MENU_NOT_ABSTRACT, ACTION_OPERATION_ABSTRACT, this, SUBMENU_CLASS_MORE, true,
                    false);

            menu.addAction(Strings.MENU_GOTO_MESSAGEVIEW, Icons.ICON_MENU_MESSAGE_VIEW,
                    ACTION_OPERATION_GOTO_MESSAGEVIEW,
                    this, true);
            menu.addAction(Strings.MENU_ADVICE, Icons.ICON_MENU_ADVICE, ACTION_OPERATION_ADVICE, this, true);
            menu.setLinkInCorners(false);
            updateButtons(menu);
        }

    }

    @Override
    public List<EObject> getEObjectToListenForUpdateMenu(RamRectangleComponent rectangle) {
        OperationView operationView = (OperationView) rectangle;
        List<EObject> ret = new ArrayList<EObject>();
        ret.add(operationView.getOperation());
        return ret;
    }

    @Override
    public void updateMenu(RamLinkedMenu menu, Notification notification) {
        if (notification.getEventType() == Notification.SET || notification.getEventType() == Notification.UNSET) {
            updateButtons(menu);
        }
    }

    /**
     * Updates buttons inside the menu.
     * 
     * @param menu - the menu which contains buttons.
     */
    private static void updateButtons(RamLinkedMenu menu) {
        Operation operation = (Operation) menu.geteObject();

        Object obj = operation.eGet(CorePackage.Literals.CORE_MODEL_ELEMENT__PARTIALITY);
        menu.enableAction(!obj.equals(COREPartialityType.NONE), ACTION_OPERATION_NOT_PARTIAL);
        menu.enableAction(!obj.equals(COREPartialityType.CONCERN), ACTION_OPERATION_CONCERN_PARTIAL);
        menu.enableAction(!obj.equals(COREPartialityType.PUBLIC), ACTION_OPERATION_PUBLIC_PARTIAL);

        menu.toggleAction(operation.isStatic(), ACTION_OPERATION_STATIC);
        menu.toggleAction(operation.isAbstract(), ACTION_OPERATION_ABSTRACT);
    }

    @Override
    public RamRectangleComponent getVisualLinkedComponent(RamRectangleComponent link) {
        OperationView view = (OperationView) link;
        return view.getNameField();
    }

}
