package ca.mcgill.sel.ram.ui.components.menu;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.mt4j.components.MTComponent;
import org.mt4j.components.StateChange;
import org.mt4j.components.StateChangeEvent;
import org.mt4j.components.StateChangeListener;
import org.mt4j.components.TransformSpace;
import org.mt4j.input.inputProcessors.componentProcessors.AbstractComponentProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapProcessor;
import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.events.listeners.ActionListener;
import ca.mcgill.sel.ram.ui.utils.Icons;
import ca.mcgill.sel.ram.ui.views.DefaultRelationshipView;
import ca.mcgill.sel.ram.ui.views.IRelationshipEndView;
import ca.mcgill.sel.ram.ui.views.RamEnd;
import ca.mcgill.sel.ram.ui.views.RelationshipView.LineStyle;

/**
 * This class is equivalent to {@link RamMenu} but is linked to a RamRectangle given in parameter.
 * The menu can't be tapped and has a default action which is close.
 * 
 * @author g.Nicolas
 */
public class RamLinkedMenu extends RamMenu implements ActionListener, IRelationshipEndView {

    private static final String ACTION_CLOSE_MENU = "menu.close";

    private DefaultRelationshipView<EObject> link;
    private EObject eObject;
    private RamRectangleComponent rectangle;
    private RamRectangleComponent visualLinkedRectangle;
    // Listener on the view, to notify view deletion.
    private StateChangeListener stateChangeListener;
    // Listener on the EObject changes
    private INotifyChangedListener notifyListener;
    // EObject listened for changes.
    private List<EObject> eObjectToListenForUpdateMenu;
    private boolean linkedInCorners;

    /**
     * Constructs a RamMenu with a link and remove the tapProcessor.
     * 
     * @param eobject - the EObject linked to rectangle
     * @param rectangle - component
     * @param linkedRectangle - view visually linked to the menu.
     */
    public RamLinkedMenu(EObject eobject, RamRectangleComponent rectangle, RamRectangleComponent linkedRectangle) {

        super(DEFAULT_SMALL_MENU_RADIUS, true);

        this.eObject = eobject;
        this.rectangle = rectangle;
        this.visualLinkedRectangle = linkedRectangle;
        this.linkedInCorners = true;

        Vector3D startPosition = rectangle.getPosition(TransformSpace.GLOBAL);
        startPosition.x -= getWidthXY(TransformSpace.GLOBAL);
        startPosition.y -= getHeightXY(TransformSpace.GLOBAL) / 2;
        this.setPositionGlobal(startPosition);

        this.addAction("Close menu", Icons.ICON_MENU_CLOSE, ACTION_CLOSE_MENU, this, true);

        for (AbstractComponentProcessor processor : getInputProcessors()) {
            if (processor instanceof TapProcessor) {
                this.unregisterInputProcessor(processor);
                break;
            }
        }

        stateChangeListener = new StateChangeListener() {
            @Override
            public void stateChanged(StateChangeEvent evt) {

                switch (evt.getState()) {
                    case REMOVED_FROM_PARENT:
                        destroy();
                        break;
                    case TRANSLATED:
                    case RESIZED:
                        draw();
                        break;
                }
            }
        };
        rectangle.addStateChangeListener(StateChange.REMOVED_FROM_PARENT, stateChangeListener);
        rectangle.addStateChangeListener(StateChange.TRANSLATED, stateChangeListener);
        rectangle.addStateChangeListener(StateChange.RESIZED, stateChangeListener);

    }

    @Override
    protected void draw() {
        MTComponent parent = getParent();
        if (link == null && parent != null) {
            link = new DefaultRelationshipView<EObject>(this.eObject, this, this.visualLinkedRectangle,
                    this.linkedInCorners);
            link.setLineStyle(LineStyle.DOTTED);
            parent.addChild(1, link);
        }
        if (link != null) {
            link.updateLines();
            link.shouldUpdate();
        }
        super.draw();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String action = event.getActionCommand();
        if (action.equals(ACTION_CLOSE_MENU)) {
            this.destroy();
        }
    }

    @Override
    public void destroy() {
        if (link != null) {
            link.destroy();
            this.removeStateChangeListener(StateChange.REMOVED_FROM_PARENT, stateChangeListener);
            if (this.eObjectToListenForUpdateMenu != null) {
                for (EObject obj : this.eObjectToListenForUpdateMenu) {
                    EMFEditUtil.removeListenerFor(obj, notifyListener);
                }
            }
        }
        super.destroy();
    }

    @Override
    public void moveRelationshipEnd(RamEnd<?, ?> end, ca.mcgill.sel.ram.ui.views.RamEnd.Position oldPosition,
            ca.mcgill.sel.ram.ui.views.RamEnd.Position newPosition) {
        updateRelationshipEnd(end);
    }

    @Override
    public void updateRelationshipEnd(RamEnd<?, ?> end) {
        Vector3D location = end.getComponentView().getPosition(TransformSpace.GLOBAL);
        // The anchor is in middle top of the component, so it's not necessary.
        // location.x += this.getWidth() / 2;
        location.y += this.getHeight() / 2;
        location = getGlobalVecToParentRelativeSpace(this, location);
        end.setLocation(location);
    }

    @Override
    public void removeRelationshipEnd(RamEnd<?, ?> end) {
    }

    /**
     * Get the eObject related to the linked view.
     * 
     * @return - the eObject related to the linked view.
     */
    public EObject geteObject() {
        return eObject;
    }

    /**
     * Returns the linked view.
     * 
     * @return the linked view
     */
    public RamRectangleComponent getLinkedView() {
        return rectangle;
    }

    /**
     * Register the listener to all of the given eObjects.
     * 
     * @param notifListener - listener which notify on change
     * @param eObjectsToListen - list of eObject to listen.
     */
    public void registerNotifyChangeListener(INotifyChangedListener notifListener,
            List<EObject> eObjectsToListen) {
        this.notifyListener = notifListener;
        this.eObjectToListenForUpdateMenu = eObjectsToListen;
    }

    /**
     * True to set the link on the target rectangle in corners.
     * 
     * @param inCorners - true will set the link on the target rectangle in corners. False will put in the middle
     *            of left and right edges.
     */
    public void setLinkInCorners(boolean inCorners) {
        this.linkedInCorners = inCorners;
    }

}
