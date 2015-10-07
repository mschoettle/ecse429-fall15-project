package ca.mcgill.sel.ram.ui.views.structural.handler.impl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.mt4j.components.TransformSpace;
import org.mt4j.input.gestureAction.InertiaDragAction;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapAndHoldProcessor.TapAndHoldEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;
import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.ram.Classifier;
import ca.mcgill.sel.ram.controller.ControllerFactory;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.components.menu.RamLinkedMenu;
import ca.mcgill.sel.ram.ui.events.DelayedDrag;
import ca.mcgill.sel.ram.ui.utils.Constants;
import ca.mcgill.sel.ram.ui.utils.Icons;
import ca.mcgill.sel.ram.ui.utils.Strings;
import ca.mcgill.sel.ram.ui.views.handler.BaseHandler;
import ca.mcgill.sel.ram.ui.views.handler.HandlerFactory;
import ca.mcgill.sel.ram.ui.views.handler.ILinkedMenuListener;
import ca.mcgill.sel.ram.ui.views.structural.BaseView;
import ca.mcgill.sel.ram.ui.views.structural.StructuralDiagramView;
import ca.mcgill.sel.ram.ui.views.structural.handler.IBaseViewHandler;

/**
 * The handler for the BaseView.
 *
 * @author Franz
 */
public abstract class BaseViewHandler extends BaseHandler implements IBaseViewHandler, ILinkedMenuListener {

    /**
     * Name of the sub-menu with add actions.
     */
    protected static final String SUBMENU_ADD = "sub.add";

    /**
     * The minimum distance an object has to be dragged before it actually is dragged.
     */
    private static final float MIN_DRAG_DISTANCE = 11.5f;

    /**
     * The amount of time (in milliseconds) that the last events of the drag should be taken into account for inertia.
     */
    private static final int INERTIA_INTEGRATION_TIME = 125;

    /**
     * The factor the velocity of the inertia is scaled by. MT4js default is 0.85, but this causes a longer inertia
     * effect.
     */
    private static final float INERTIA_DAMPING = 0.75f;

    /**
     * The maximum velocity length of the velocity for inertia.
     */
    private static final float INERTIA_MAX_VELOCITY = 25;

    private static final String ACTION_CLASS_REMOVE = "view.classifier.remove";

    private DelayedDrag dragAction = new DelayedDrag(Constants.DELAYED_DRAG_MIN_DRAG_DISTANCE);

    @SuppressWarnings("unused")
    private InertiaDragAction inertiaAction = new InertiaDragAction(INERTIA_INTEGRATION_TIME, INERTIA_DAMPING,
            INERTIA_MAX_VELOCITY);

    @Override
    public boolean processDragEvent(DragEvent dragEvent) {
        boolean skipSuperProcessing = false;

        BaseView<?> target = (BaseView<?>) dragEvent.getTarget();

        if (dragEvent.getId() == MTGestureEvent.GESTURE_STARTED) {
            target.setDragFrom(dragEvent.getFrom());
        }

        if (dragEvent.getId() == MTGestureEvent.GESTURE_UPDATED) {
            float dragDistance = dragEvent.getTo().distance(target.getDragFrom());

            // stop processing this event if the distance hasn't reached the minimum distance yet
            if (dragDistance < MIN_DRAG_DISTANCE) {
                return true;
            }
            target.setWasDragged();

            // The following if statement takes care of moving all selected classes, if the class that is being dragged
            // is selected
            if (target.getIsSelected()) {
                HandlerFactory.INSTANCE.getStructuralViewHandler().dragAllSelectedClasses(
                        target.getStructuralDiagramView(), dragEvent.getTranslationVect());
                skipSuperProcessing = true;
            }
        }

        if (!skipSuperProcessing) {
            dragAction.processGestureEvent(dragEvent);
        }

        if (dragEvent.getId() == MTGestureEvent.GESTURE_ENDED) {
            // move it only if it was dragged enough
            if (target.wasDragged()) {
                // Only perform inertia if drag was performed.
                // Otherwise even small movements trigger an inertia.
                // It is possible to do this in this case,
                // because the inertia action only does something when the gesture ended.
                // TODO: enable inertia again once we correctly save the position with inertia
                // inertiaAction.processGestureEvent(dragEvent);

                Classifier classifier = target.getClassifier();
                // relative position is required in order to correctly set the position independent of zoom level
                Vector3D position = target.getPosition(TransformSpace.RELATIVE_TO_PARENT);

                // use the controller to actually move the EMF classifier instance
                if (target.getIsSelected()) {
                    // the classifier is selected, so we need to move all selected classifiers
                    HandlerFactory.INSTANCE.getStructuralViewHandler().moveAllSelectedClasses(
                            target.getStructuralDiagramView());

                } else {
                    // the classifier was not selected, so just move it
                    // TODO: mschoettle: right now this only moves to the current position, if inertia is activated the
                    // class will get moved visually a bit further
                    // possible solution, create specialized InertiaDragAction that notifies when inertia is finished,
                    // only then will the classes position be updated
                    ControllerFactory.INSTANCE.getClassController().moveClassifier(classifier, position.getX(),
                            position.getY());
                }
            }
        }

        return true;
    }

    @Override
    public boolean processTapAndHoldEvent(TapAndHoldEvent tapAndHoldEvent) {
        if (tapAndHoldEvent.isHoldComplete()) {
            BaseView<?> target = (BaseView<?>) tapAndHoldEvent.getTarget();
            return target.getStructuralDiagramView().getHandler()
                    .handleTapAndHoldOnClass(target.getStructuralDiagramView(), target);
        }

        return false;
    }

    @Override
    public boolean processTapEvent(TapEvent tapEvent) {
        // Always flip the selection of the view.
        if (tapEvent.isTapped() || tapEvent.isDoubleTap()) {
            BaseView<?> target = (BaseView<?>) tapEvent.getTarget();
            target.setSelect(!target.getIsSelected());

            // Pass it to the structural view handler to handle more advanced gestures.
            if (tapEvent.isDoubleTap()) {
                StructuralDiagramView structuralDiagramView = target.getStructuralDiagramView();
                return structuralDiagramView.getHandler().handleDoubleTapOnClass(structuralDiagramView, target);
            }
        }

        return false;
    }

    @Override
    public void removeClass(BaseView<?> baseView) {
        ControllerFactory.INSTANCE.getStructuralViewController().removeClassifier(baseView.getClassifier());
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String actionCommand = event.getActionCommand();
        RamRectangleComponent pressedButton = (RamRectangleComponent) event.getTarget();
        RamLinkedMenu linkedMenu = (RamLinkedMenu) pressedButton.getParentOfType(RamLinkedMenu.class);
        if (linkedMenu != null) {

            BaseView<?> clazz = (BaseView<?>) linkedMenu.getLinkedView();

            if (ACTION_CLASS_REMOVE.equals(actionCommand)) {
                removeClass(clazz);
            }
        }
    }

    @Override
    public EObject getEobject(RamRectangleComponent rectangle) {
        return ((BaseView<?>) rectangle).getClassifier();
    }

    @Override
    public void initMenu(RamLinkedMenu menu) {
        menu.addSubMenu(1, SUBMENU_ADD);
        menu.addAction(Strings.MENU_DELETE, Icons.ICON_MENU_TRASH, ACTION_CLASS_REMOVE, this, true);
    }

    @Override
    public List<EObject> getEObjectToListenForUpdateMenu(RamRectangleComponent rectangle) {
        BaseView<?> baseView = (BaseView<?>) rectangle;
        ArrayList<EObject> ret = new ArrayList<EObject>();
        ret.add(baseView.getClassifier());
        return ret;
    }

    @Override
    public void updateMenu(RamLinkedMenu menu, Notification notification) {
    }

    @Override
    public RamRectangleComponent getVisualLinkedComponent(RamRectangleComponent link) {
        return link;
    }

}
