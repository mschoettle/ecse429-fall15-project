package ca.mcgill.sel.ram.ui.views.structural.handler.impl;

import java.util.Collection;
import java.util.Map;

import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;
import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.ram.Instantiation;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.views.handler.BaseHandler;
import ca.mcgill.sel.ram.ui.views.structural.InstantiationView;
import ca.mcgill.sel.ram.ui.views.structural.InstantiationsPanel;
import ca.mcgill.sel.ram.ui.views.structural.InstantiationsContainerView;
import ca.mcgill.sel.ram.ui.views.structural.handler.IInstantiationsPanelHandler;

/**
 * Handles events for a {@link InstantiationsPanel} which is showing the list {@link InstantiationsContainerView} in an
 * aspect.
 * 
 * @author eyildirim
 * @author oalam
 */
public class InstantiationsPanelHandler extends BaseHandler implements IInstantiationsPanelHandler {

    @Override
    public boolean processDragEvent(DragEvent dragEvent) {

        InstantiationsPanel target =
                (InstantiationsPanel) dragEvent.getTarget();

        switch (dragEvent.getId()) {
            case MTGestureEvent.GESTURE_CANCELED:
                break;
            case MTGestureEvent.GESTURE_ENDED:
                break;
            case MTGestureEvent.GESTURE_UPDATED:
                // we just want it along y axis
                float translation = dragEvent.getTranslationVect().y;
                
                if (translation < 0) {
                    target.setBottomStick(false);
                }
                
                float currentY = target.getY();

                float correctY = target.getCorrectYPosition(currentY + translation);
                translation = -currentY + correctY;
                
                if (correctY == RamApp.getApplication().getSize().height - target.getHeight()) {
                    target.setBottomStick(true);
                }

                Vector3D translationVector = new Vector3D(0, translation, 0);
                target.translate(translationVector);
                break;
        }

        return true;

    }

    @Override
    public boolean processTapEvent(TapEvent tapEvent) {

        if (tapEvent.isTapped()) {
            InstantiationsPanel target =
                    (InstantiationsPanel) tapEvent.getTarget();
            target.doSlideInOut();
        }

        return true;
    }

    @Override
    public void switchFromInstantiationSplitEditMode(
            InstantiationsPanel instantiationsContainerContainer,
            Instantiation instantiation) {

        for (InstantiationsContainerView instantiationsContainerView : instantiationsContainerContainer
                .getInstantiationsContainers()) {
            Map<Instantiation, InstantiationView> instantiations = instantiationsContainerView.getInstantiationViews();
            if (!instantiations.containsKey(instantiation)) {
                // Display given container again
                instantiationsContainerContainer.showInstantiationsContainerView(instantiationsContainerView);
            } else {
                // Add plus button.
                instantiationsContainerView.initButtons();
                // Remember, everything we keep in instantiationsContainerView is kept in the
                // roundInstantiationsContainer which is a child of it.
                RamRectangleComponent instantiationsContainer =
                        instantiationsContainerView.getInstantiationsContainer();

                // First remove all children including the buttonContainer
                instantiationsContainer.removeAllChildren();

                Collection<InstantiationView> instantiationsToInstantiationViews = instantiationsContainerView
                        .getInstantiationViews().values();

                for (InstantiationView instantiationView : instantiationsToInstantiationViews) {
                    instantiationsContainer.addChild(instantiationView);
                }
            }
        }

    }

    @Override
    public void switchToInstantiationSplitEditMode(InstantiationsPanel container,
            Instantiation instantiation) {
        for (InstantiationsContainerView instantiationsContainerView : container
                .getInstantiationsContainers()) {
            Map<Instantiation, InstantiationView> instantiations = instantiationsContainerView.getInstantiationViews();
            if (!instantiations.containsKey(instantiation)) {
                // Display given container again
                container.hideInstantiationsContainerView(instantiationsContainerView);
            } else {
                // Remember, everything we keep in instantiationsContainerView is kept in the container
                // which is a child of it.
                RamRectangleComponent containerView = instantiationsContainerView.getInstantiationsContainer();

                // Remove the instantiation adding button
                RamRectangleComponent buttonContainer = instantiationsContainerView.getButtonContainer();
                buttonContainer.removeAllChildren();

                // First remove all children including the buttonContainer
                containerView.removeAllChildren();

                // get the instantiation view that we want to show while the split instantiation editing view is active
                InstantiationView instantiationViewBeingEdited = instantiations.get(instantiation);

                containerView.addChild(instantiationViewBeingEdited);
                instantiationViewBeingEdited.showMappingDetails();
            }
        }
    }
}
