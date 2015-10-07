package ca.mcgill.sel.ram.ui.views.state;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.mt4j.components.TransformSpace;
import org.mt4j.input.gestureAction.TapAndHoldVisualizer;
import org.mt4j.input.inputProcessors.IGestureEventListener;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.tapAndHoldProcessor.TapAndHoldProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapProcessor;
import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.ram.LayoutElement;
import ca.mcgill.sel.ram.State;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.RamApp.DisplayMode;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.components.RamRoundedRectangleComponent;
import ca.mcgill.sel.ram.ui.layouts.VerticalLayout;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.utils.Constants;
import ca.mcgill.sel.ram.ui.utils.Fonts;
import ca.mcgill.sel.ram.ui.utils.MetamodelRegex;
import ca.mcgill.sel.ram.ui.utils.Strings;
import ca.mcgill.sel.ram.ui.views.RamEnd;
import ca.mcgill.sel.ram.ui.views.RamEnd.Position;
import ca.mcgill.sel.ram.ui.views.RelationshipView;
import ca.mcgill.sel.ram.ui.views.TextView;
import ca.mcgill.sel.ram.ui.views.handler.IHandled;
import ca.mcgill.sel.ram.ui.views.handler.ITextViewHandler;
import ca.mcgill.sel.ram.ui.views.handler.impl.ValidatingTextViewHandler;
import ca.mcgill.sel.ram.ui.views.state.handler.IStateComponentViewHandler;

/**
 * Visual representation of a State.
 * 
 * @author abirayed
 */
public class StateComponentView extends RamRoundedRectangleComponent
        implements IHandled<IStateComponentViewHandler>, INotifyChangedListener {
    
    private State state;
    private TextView nameField;
    
    private LayoutElement layoutElement;
    
    private Map<Position, List<RamEnd<?, ? extends RamRectangleComponent>>> transitionEndByPosition;
    
    private IStateComponentViewHandler handler;
    private StateMachineView stateMachineView;
    
    public StateComponentView(State state, StateMachineView stateMachineView, LayoutElement layoutElement) {
        super(10);
        this.stateMachineView = stateMachineView;
        this.state = state;
        this.layoutElement = layoutElement;
        
    }
    
    public void build() {
        setEnabled(true);
        setNoStroke(false);
        
        // if it is the start state
        if (stateMachineView.getStateMachine().getStart() != null
                && stateMachineView.getStateMachine().getStart().equals(state)) {
            setStartState(true);
        }
        
        nameField = new TextView(state, CorePackage.Literals.CORE_NAMED_ELEMENT__NAME, true);
        nameField.setPlaceholderText(Strings.PH_ENTER_STATE_NAME);
        nameField.setPositionRelativeToParent(new Vector3D(getX(), getY(), 0));
        nameField.setFont(Fonts.DEFAULT_FONT_MEDIUM);
        
        ITextViewHandler roleNameHandler = new ValidatingTextViewHandler(MetamodelRegex.REGEX_TYPE_NAME);
        nameField.registerTapProcessor(roleNameHandler);
        
        addChild(nameField);
        
        // create the transition views
        transitionEndByPosition = new HashMap<Position, List<RamEnd<?, ? extends RamRectangleComponent>>>();
        for (Position position : Position.values()) {
            transitionEndByPosition.put(position, new ArrayList<RamEnd<?, ? extends RamRectangleComponent>>());
        }
        
        // translate the state based on the meta-model
        if (layoutElement != null) {
            setLayoutElement(layoutElement);
        }
        setLayout(new VerticalLayout());
        EMFEditUtil.addListenerFor(state, this);
    }
    
    @Override
    protected void destroyComponent() {
        EMFEditUtil.removeListenerFor(state, this);
    }
    
    public void setStartState(boolean isStartState) {
        if (isStartState) {
            setFillColor(Colors.STATE_START_FILL_COLOR);
            setNoFill(false);
        } else {
            setNoFill(true);
        }
    }
    
    public void addTransitionEndAtPosition(RamEnd<?, ? extends RamRectangleComponent> end) {
        List<RamEnd<?, ? extends RamRectangleComponent>> list = transitionEndByPosition.get(end.getPosition());
        list.add(end);
        setCorrectPosition(list, end.getPosition());
    }
    
    private void changePosition(RamEnd<?, ?> association, Vector3D classLocation, int number, float space) {
        switch (association.getPosition()) {
            case TOP:
                association.setLocation(new Vector3D(classLocation.getX() + number * space, classLocation.getY()));
                break;
            case BOTTOM:
                association.setLocation(new Vector3D(classLocation.getX() + number * space, classLocation.getY()
                        + getHeightXY(TransformSpace.RELATIVE_TO_PARENT)));
                break;
            case RIGHT:
                association.setLocation(new Vector3D(classLocation.getX()
                        + getWidthXY(TransformSpace.RELATIVE_TO_PARENT), classLocation.getY() + number * space));
                break;
            case LEFT:
                association.setLocation(new Vector3D(classLocation.getX(), classLocation.getY() + number * space));
                break;
        }
    }
    
    @Override
    public void destroy() {
        // unregister
        EMFEditUtil.removeListenerFor(layoutElement, this);
        EMFEditUtil.removeListenerFor(state, this);
        
        // destroy relationships
        for (RelationshipView<?, ?> view : getAllRelationshipViews()) {
            view.destroy();
        }
        transitionEndByPosition.clear();
        // destroy rest
        super.destroy();
    }
    
    /**
     * Disables edit mode for the state view and resets to the default display state.
     */
    public void disableEditMode() {
        // reset state
        setStrokeColor(Colors.DEFAULT_ELEMENT_STROKE_COLOR);
    }
    
    /**
     * Enables edit mode, highlights state red, adds buttons for editing state view.
     */
    public void enableEditMode() {
        setStrokeColor(Colors.STATE_EDIT_MODE_STROKE_COLOR);
    }
    
    /**
     * @return All relationship for this view.
     */
    public Set<RelationshipView<?, ? extends RamRectangleComponent>> getAllRelationshipViews() {
        Set<RelationshipView<?, ? extends RamRectangleComponent>> allRelationships =
                new HashSet<RelationshipView<?, ? extends RamRectangleComponent>>();
        for (List<RamEnd<?, ? extends RamRectangleComponent>> list : transitionEndByPosition.values()) {
            for (RamEnd<?, ? extends RamRectangleComponent> end : list) {
                allRelationships.add(end.getRelationshipView());
            }
        }
        return allRelationships;
    }
    
    /**
     * Removes the transition from the state component view.
     * 
     * @param end
     *            The end to remove.
     * @precondition The end's position is the same position as it is in the class view's list.
     */
    public void removeRelationshipEnd(RamEnd<?, ? extends RamRectangleComponent> end) {
        List<RamEnd<?, ? extends RamRectangleComponent>> list = transitionEndByPosition.get(end.getPosition());
        list.remove(end);
        if (list.size() == 1) {
            list.get(0).setIsAlone(true);
        }
        setCorrectPosition(list, end.getPosition());
    }
    
    @Override
    public IStateComponentViewHandler getHandler() {
        return handler;
    }
    
    public State getState() {
        return state;
    }
    
    /**
     * @return the stateMachineView
     */
    public StateMachineView getStateMachineView() {
        return stateMachineView;
    }
    
    public void moveTransitionEnd(RamEnd<?, ? extends RamRectangleComponent> end, Position newPosition) {
        if (end.getPosition() == null) {
            // it updates the position and calls getRelationshipView().shouldUpdate();
            end.setPosition(newPosition);
            addTransitionEndAtPosition(end);
        } else {
            moveTransitionEnd(end, end.getPosition(), newPosition);
            end.setPosition(newPosition);
        }
    }
    
    public void moveTransitionEnd(RamEnd<?, ? extends RamRectangleComponent> end,
            Position oldPosition, Position newPosition) {
        List<RamEnd<?, ? extends RamRectangleComponent>> oldList = transitionEndByPosition.get(oldPosition);
        oldList.remove(end);
        setCorrectPosition(oldList, oldPosition);
        
        List<RamEnd<?, ? extends RamRectangleComponent>> newList = transitionEndByPosition.get(newPosition);
        newList.add(end);
        setCorrectPosition(newList, newPosition);
    }
    
    @Override
    public void notifyChanged(Notification notification) {
        if (notification.getNotifier() == layoutElement) {
            setPositionGlobal(new Vector3D(layoutElement.getX(), layoutElement.getY()));
        }
        
    }
    
    protected void registerGestureListeners(IGestureEventListener listener) {
        registerInputProcessor(new DragProcessor(RamApp.getApplication()));
        registerInputProcessor(new TapProcessor(RamApp.getApplication(), Constants.TAP_MAX_FINGER_UP,
                true, Constants.TAP_DOUBLE_TAP_TIME));
        registerInputProcessor(new TapAndHoldProcessor(RamApp.getApplication(), Constants.TAP_AND_HOLD_DURATION));
        
        addGestureListener(DragProcessor.class, listener);
        addGestureListener(TapProcessor.class, listener);
        addGestureListener(TapAndHoldProcessor.class, listener);
        addGestureListener(TapAndHoldProcessor.class, new TapAndHoldVisualizer(RamApp.getApplication(), getParent()));
    }
    
    @Override
    public void scale(float x, float y, float z, Vector3D scalingPoint, TransformSpace transformSpace) {
        super.scale(x, y, z, scalingPoint, transformSpace);
        updateTransitions();
    }
    
    private void setCorrectPosition(List<RamEnd<?, ? extends RamRectangleComponent>> list, Position position) {
        // the top left position is needed to determine the new ends position along the side of the state
        Vector3D stateLocation = getLocalVecToParentRelativeSpace(this, getBounds().getVectorsLocal()[0]);
        float availableSpace = 0;
        switch (position) {
            case TOP:
            case BOTTOM:
                availableSpace = getWidthXY(TransformSpace.RELATIVE_TO_PARENT);
                break;
            case LEFT:
            case RIGHT:
                availableSpace = getHeightXY(TransformSpace.RELATIVE_TO_PARENT);
                break;
            default:
        }
        int numberOfEnds = list.size();
        float dividerDistance = availableSpace / (numberOfEnds + 1);
        if (RamApp.getDisplayMode() == DisplayMode.PRETTY) {
            // we want a pretty drawing, reorder the ends so they're less likely to overlap
            Comparator<RamEnd<?, ?>> comparator;
            switch (position) {
                case TOP:
                case BOTTOM:
                    comparator = RamEnd.HORIZONTAL_COMPARATOR;
                    break;
                case LEFT:
                case RIGHT:
                    comparator = RamEnd.VERTICAL_COMPARATOR;
                    break;
                case OFFSCREEN:
                    // set as null
                default:
                    comparator = null;
            }
            if (comparator != null) {
                Collections.sort(list, comparator);
            }
        }
        for (int index = 0; index < list.size(); index++) {
            RamEnd<?, ?> associationEnd = list.get(index);
            changePosition(associationEnd, stateLocation, index + 1, dividerDistance);
        }
    }
    
    public void setCorrectPosition(RamEnd<?, ? extends RamRectangleComponent> end) {
        List<RamEnd<?, ? extends RamRectangleComponent>> list = transitionEndByPosition.get(end.getPosition());
        setCorrectPosition(list, end.getPosition());
    }
    
    @Override
    public void setHandler(IStateComponentViewHandler handler) {
        if (RamApp.getApplication().getCanvas() == null || getParent() == null) {
            throw new RuntimeException(
                    "Component "
                            + this
                            + " has no parent, handler may only be addded once the component has a parent"
                            + " (i.e., after adding it to another component)");
        }
        
        this.handler = handler;
        
        // unregister previous handlers
        removeAllGestureEventListeners();
        
        registerGestureListeners(handler);
    }
    
    /**
     * Sets the layout element for the corresponding state.
     * 
     * @param layoutElement
     *            the {@link LayoutElement} to set
     */
    public void setLayoutElement(LayoutElement layoutElement) {
        this.layoutElement = layoutElement;
        setPositionGlobal(new Vector3D(layoutElement.getX(), layoutElement.getY()));
        
        EMFEditUtil.addListenerFor(layoutElement, this);
    }
    
    @Override
    public void setSizeLocal(float width, float height) {
        super.setSizeLocal(width, height);
        updateTransitions();
    }
    
    /**
     * Displays the keyboard.
     */
    public void showKeyboard() {
        nameField.showKeyboard();
        
    }
    
    @Override
    public void translate(Vector3D dirVect) {
        super.translate(dirVect);
        updateTransitions();
        updateParent();
    }
    
    /**
     * Updates the view for all this state' transitions.
     */
    protected void updateTransitions() {
        if (transitionEndByPosition != null) {
            for (RelationshipView<?, ? extends RamRectangleComponent> view : getAllRelationshipViews()) {
                view.updateLines();
            }
        }
    }
    
}
