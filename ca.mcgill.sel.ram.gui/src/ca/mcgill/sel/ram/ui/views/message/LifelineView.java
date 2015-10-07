package ca.mcgill.sel.ram.ui.views.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.mt4j.components.MTComponent;
import org.mt4j.components.PickResult;
import org.mt4j.components.TransformSpace;
import org.mt4j.input.gestureAction.TapAndHoldVisualizer;
import org.mt4j.input.inputProcessors.IGestureEventListener;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapAndHoldProcessor.TapAndHoldProcessor;
import org.mt4j.util.MTColor;
import org.mt4j.util.math.Vector3D;
import org.mt4j.util.math.Vertex;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.ram.AssignmentStatement;
import ca.mcgill.sel.ram.CombinedFragment;
import ca.mcgill.sel.ram.ExecutionStatement;
import ca.mcgill.sel.ram.FragmentContainer;
import ca.mcgill.sel.ram.InteractionFragment;
import ca.mcgill.sel.ram.InteractionOperand;
import ca.mcgill.sel.ram.LayoutElement;
import ca.mcgill.sel.ram.Lifeline;
import ca.mcgill.sel.ram.Message;
import ca.mcgill.sel.ram.MessageEnd;
import ca.mcgill.sel.ram.MessageOccurrenceSpecification;
import ca.mcgill.sel.ram.MessageSort;
import ca.mcgill.sel.ram.OriginalBehaviorExecution;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.StructuralFeature;
import ca.mcgill.sel.ram.TypedElement;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.ContainerComponent;
import ca.mcgill.sel.ram.ui.components.RamLineComponent;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.components.RamTextComponent;
import ca.mcgill.sel.ram.ui.events.Axis;
import ca.mcgill.sel.ram.ui.events.LockedAxisUnistrokeProcessor;
import ca.mcgill.sel.ram.ui.layouts.Layout;
import ca.mcgill.sel.ram.ui.layouts.VerticalLayout;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.utils.Constants;
import ca.mcgill.sel.ram.ui.utils.Strings;
import ca.mcgill.sel.ram.ui.views.IRelationshipEndView;
import ca.mcgill.sel.ram.ui.views.RamEnd;
import ca.mcgill.sel.ram.ui.views.RamEnd.Position;
import ca.mcgill.sel.ram.ui.views.RelationshipView.LineStyle;
import ca.mcgill.sel.ram.ui.views.TextView;
import ca.mcgill.sel.ram.ui.views.handler.IHandled;
import ca.mcgill.sel.ram.ui.views.message.handler.ILifelineViewHandler;

/**
 * The view responsible for visualizing a lifeline. 
 * A lifeline view consists of the name container, which contains the information of the "represents" property, 
 * a dashed line as heigh as the lifeline and fragments that cover this lifeline. 
 * Since the fragments might cover more than one lifeline, placeholders are placed along the lifeline and the
 * actual view is referred to (it is contained somewhere else).
 *
 * @author mschoettle
 */
public class LifelineView extends RamRectangleComponent implements INotifyChangedListener,
        IHandled<ILifelineViewHandler>, IRelationshipEndView {

    /**
     * A view which represents an {@link InteractionFragment}.
     *
     * @author mschoettle
     */
    private abstract class FragmentView extends RamRectangleComponent {

        private InteractionFragment fragment;

        /**
         * Creates a new view that represents a fragment.
         *
         * @param fragment the {@link InteractionFragment} to represent
         */
        public FragmentView(InteractionFragment fragment) {
            super();

            this.fragment = fragment;
        }

    }

    /**
     * The view representing a message end.
     *
     * @author mschoettle
     */
    private class MessageEndView extends FragmentView implements IRelationshipEndView {

        /**
         * Creates a new view for the given message end and dimensions.
         *
         * @param messageEnd the {@link MessageOccurrenceSpecification} to represent
         * @param width the width of this view
         * @param height the height of this view
         */
        public MessageEndView(MessageOccurrenceSpecification messageEnd, float width, float height) {
            super(messageEnd);

            // For Debug purposes.
            // setNoFill(false);
            // setFillColor(new MTColor(128, 128, 128, 255));

            setMinimumSize(width, height);
        }

        @Override
        public void moveRelationshipEnd(RamEnd<?, ?> end, Position oldPosition, Position newPosition) {
            LifelineView.this.moveRelationshipEnd(end, oldPosition, newPosition);
        }

        @Override
        public void updateRelationshipEnd(RamEnd<?, ?> end) {
            LifelineView.this.updateRelationshipEnd(end);
        }

        @Override
        public void removeRelationshipEnd(RamEnd<?, ?> end) {
            LifelineView.this.removeRelationshipEnd(end);
        }

    }

    /**
     * A spacer that can be placed along the lifeline between fragments. 
     * It supports the creation of new fragments at its location. 
     * The fact that creation is possible is visualized to assist the user.
     *
     * @author mschoettle
     */
    private class Spacer extends RamRectangleComponent {

        /**
         * Creates a new spacer.
         *
         * @param allowCreation true, if fragments can be created at this place, false otherwise
         * @see #setAllowCreation(boolean)
         */
        public Spacer(boolean allowCreation) {
            setMinimumSize(MessageViewView.BOX_WIDTH, MessageViewView.BOX_HEIGHT);

            // For Debug.
            // setNoStroke(false);
            // setNoFill(false);
            setFillColor(Colors.MESSAGE_SPACER_FILL_COLOR);

            setAllowCreation(allowCreation);
        }

        /**
         * Sets whether creation of new fragments is possible at this place.
         * The fact that it is possible is visually distinguished from non-permitted spacers.
         *
         * @param allowCreation true, if fragments can be created at this place, false otherwise
         */
        public void setAllowCreation(boolean allowCreation) {
            if (allowCreation) {
                unregisterEventProcessors();
                registerEventProcessors();
                setNoFill(false);
            } else {
                unregisterEventProcessors();
                setNoFill(true);
            }
        }

        /**
         * Registers the supported processors and registers a delegate handler for these processors.
         */
        private void registerEventProcessors() {
            IGestureEventListener delegate = new IGestureEventListener() {

                @Override
                public boolean processGestureEvent(MTGestureEvent ge) {
                    ge.setTarget(LifelineView.this);
                    return handler.processGestureEvent(ge);
                }
            };

            registerInputProcessor(new LockedAxisUnistrokeProcessor(RamApp.getApplication(), Axis.Y));
            addGestureListener(LockedAxisUnistrokeProcessor.class, delegate);

            registerInputProcessor(new TapAndHoldProcessor(RamApp.getApplication(), Constants.TAP_AND_HOLD_DURATION));
            addGestureListener(TapAndHoldProcessor.class, delegate);
            addGestureListener(TapAndHoldProcessor.class, new TapAndHoldVisualizer(RamApp.getApplication(), RamApp
                    .getActiveScene().getCanvas()));
        }

        /**
         * Unregisters all processors and gesture listeners.
         */
        private void unregisterEventProcessors() {
            unregisterAllInputProcessors();
            removeAllGestureEventListeners();
        }

    }

    /**
     * The container view that holds fragment views and spacers or other event containers. 
     * An event container is used for every {@link FragmentContainer} in the model.
     *
     * @author mschoettle
     */
    private class EventContainer extends RamRectangleComponent {

        private RamLineComponent line;
        private int initialChildren;

        /**
         * Creates a new event container.
         *
         * @param createLine true, if the lifeline line should be created, false otherwise
         */
        public EventContainer(boolean createLine) {
            // For Debug purposes.
            // setNoStroke(false);

            setAutoMaximizes(false);
            setMinimumSize(MessageViewView.BOX_WIDTH, MessageViewView.BOX_HEIGHT);
            setLayout(new VerticalLayout());

            if (createLine) {
                line = new RamLineComponent(0, 0, 0, 0);
                line.setLineStipple(LineStyle.DASHED.getStylePattern());
                line.setPickable(false);
                super.addChild(line);
            }

            super.addChild(new Spacer(false));

            initialChildren = getChildCount();
        }

        @Override
        protected void handleChildResized(MTComponent component) {
            super.handleChildResized(component);

            if (line != null) {
                updateLine();
            }
        }

        @Override
        public MTComponent getChildByPoint(Vector3D point) {
            // Go from the last to the first child.
            for (int index = getChildCount() - 1; index >= 0; index--) {
                MTComponent child = getChildByIndex(index);

                if (child.containsPointGlobal(point)) {
                    return child;
                }
            }

            return null;
        }

        /**
         * Allows the creation of fragments at the very beginning (the first spacer) of this container.
         */
        public void allowCreation() {
            Spacer spacer = (Spacer) getChildByIndex(initialChildren - 1);
            spacer.setAllowCreation(true);
        }

        /**
         * Updates the line's end points.
         */
        private void updateLine() {
            Vertex[] vertices = line.getVerticesLocal();
            vertices[0].x = getWidth() / 2;
            vertices[1].x = vertices[0].x;
            vertices[1].y = getHeight();
            line.setVertices(vertices);
        }

    }

    /**
     * A view for a fragment that acts as a placeholder for the actual view. 
     * The actual view might not be placed directly on the lifeline or span multiple lifelines, 
     * so only a placeholder is required on the lifeline itself that occupies that space.
     *
     * @author mschoettle
     */
    private class PlaceholderView extends FragmentView {

        /**
         * An adaptive layout (vertically) that adjusts the actual views position based on the position of the
         * placeholder component.
         *
         * @author mschoettle
         */
        private class AdaptiveLayout extends VerticalLayout {

            @Override
            public void layout(RamRectangleComponent component, LayoutUpdatePhase updatePhase) {
                super.layout(component, updatePhase);

                /**
                 * We need to convert the global position of the placeholder into a relative position, to be able to
                 * position the actual view accordingly.
                 */
                Vector3D componentPosition = component.getPosition(TransformSpace.GLOBAL);
                componentPosition.addLocal(viewOffset);
                Vector3D relativePosition = convertGlobalToRelativePosition(componentPosition);

                actualView.setPositionRelativeToParent(relativePosition);
                actualView.sendToFront();
            }

        }

        private RamRectangleComponent actualView;
        private Vector3D viewOffset;

        /**
         * Creates a new placeholder view.
         *
         * @param actualView the actual view that this view is a placeholder for
         * @param fragment the {@link InteractionFragment} this view represents
         * @param offsetX the x offset the actual view is placed at
         * @param offSetY the y offset the actual view is placed at
         */
        public PlaceholderView(RamRectangleComponent actualView, InteractionFragment fragment, float offsetX,
                float offSetY) {
            super(fragment);

            this.actualView = actualView;
            this.viewOffset = new Vector3D(offsetX, offSetY);

            setLayout(new AdaptiveLayout());
        }

    }

    /**
     * The view representing an interaction operand. 
     * It is an event container itself, because an interaction operand is a fragment container.
     *
     * @author mschoettle
     */
    private class OperandView extends EventContainer implements INotifyChangedListener {

        private InteractionOperand operand;

        /**
         * The container for fragments of interaction operands. 
         * This container can be placed inside another event container. 
         * It delegates notifications about fragments to the containing message view.
         *
         * @param operand the {@link InteractionOperand}
         * @param allowCreation whether fragment creation is allowed initially in this operand
         */
        public OperandView(InteractionOperand operand, boolean allowCreation) {
            super(false);

            this.operand = operand;

            // For debug purposes.
            // setNoStroke(false);
            // setStrokeColor(MTColor.AQUA);

            setLayout(new VerticalLayout());

            if (allowCreation) {
                allowCreation();
            }

            EMFEditUtil.addListenerFor(operand, this);
        }

        @Override
        public void destroy() {
            super.destroy();

            EMFEditUtil.removeListenerFor(operand, this);
        }

        @Override
        protected void handleChildResized(MTComponent component) {
            // Do nothing.
        }

        @Override
        public void notifyChanged(Notification notification) {
            // Each lifeline will get notified even if the fragment that was added
            // doesn't belong to it (ExecutionStatement). In that case several calls
            // would be made to the message view, which results in multiple fragments on the
            // same lifeline.
            if (notification.getNotifier() == operand) {
                if (notification.getFeature() == RamPackage.Literals.FRAGMENT_CONTAINER__FRAGMENTS) {
                    Object value = (notification.getEventType() == Notification.REMOVE) 
                                        ? notification.getOldValue()
                                        : notification.getNewValue();
                    // This doesn't affect a list of message ends.
                    if (value instanceof InteractionFragment) {
                        InteractionFragment fragment = (InteractionFragment) value;
                        if (!fragment.getCovered().contains(lifeline)) {
                            return;
                        }
                    }
                }

                messageViewView.notifyChanged(notification);
            }
        }

    }

    /**
     * The offset to the position on the lifeline for fragments when placed into a placeholder view.
     */
    private static final float FRAGMENT_OFFSET = -20f;

    private Lifeline lifeline;
    private LayoutElement layoutElement;

    private MessageViewView messageViewView;
    private ContainerComponent<IGestureEventListener> nameContainer;
    private TextView nameField;
    private EventContainer baseEventContainer;
    private List<MessageCallView> messages;
    private Map<InteractionFragment, PlaceholderView> fragments;
    private Map<FragmentContainer, EventContainer> eventContainers;
    private Map<InteractionFragment, EventContainer> eventContainersMap;

    private ILifelineViewHandler handler;

    /**
     * Tracks whether this lifeline starts with a create message.
     */
    private boolean startsWithCreate;
    /**
     * Tracks whether this lifeline starts with a create message and inside an interaction operand (i.e., combined
     * fragment).
     */
    private boolean createdInOperand;

    /**
     * Creates a new view for a lifeline.
     *
     * @param messageViewView the {@link MessageViewView} that is the parent of this view
     * @param lifeline the {@link Lifeline} to display
     * @param layoutElement the {@link LayoutElement} containing the layout for the lifeline
     */
    public LifelineView(MessageViewView messageViewView, Lifeline lifeline, LayoutElement layoutElement) {
        this.messageViewView = messageViewView;
        this.lifeline = lifeline;
        this.layoutElement = layoutElement;
        messages = new ArrayList<MessageCallView>();
        fragments = new HashMap<InteractionFragment, PlaceholderView>();
        eventContainers = new HashMap<FragmentContainer, LifelineView.EventContainer>();
        eventContainersMap = new HashMap<InteractionFragment, LifelineView.EventContainer>();

        if (layoutElement != null) {
            setLayoutElement(layoutElement);
        }

        // Make it non-pickable, because the size is not a rectangular shape
        // and underlying components should be picked instead.
        setPickable(false);

        // For debug purposes.
        // setNoStroke(false);
        // setStrokeColor(MTColor.AQUA);

        build();

        setLayout(new VerticalLayout(Layout.HorizontalAlignment.CENTER));
    }

    /**
     * Builds the child components.
     */
    private void build() {
        nameContainer = new ContainerComponent<IGestureEventListener>();
        nameContainer.setLayout(new VerticalLayout(Layout.HorizontalAlignment.CENTER));
        nameContainer.setNoStroke(false);
        nameContainer.setNoFill(false);
        nameContainer.setFillColor(Colors.MESSAGE_LIFELINE_LABEL_FILL_COLOR);
        addChild(nameContainer);

        if (lifeline.getRepresents() != null) {
            TypedElement typedElement = lifeline.getRepresents();
            if (typedElement instanceof StructuralFeature && ((StructuralFeature) typedElement).isStatic()) {
                RamTextComponent metaclassText = new RamTextComponent(Strings.TAG_META_CLASS);
                metaclassText.setAutoMaximizes(false);
                nameContainer.addChild(metaclassText);
            }
        }

        nameField = new TextView(lifeline, RamPackage.Literals.LIFELINE__REPRESENTS, true);
        nameField.setAutoMaximizes(false);
        // nameField.setPlaceholderText("Choose instance...");
        nameContainer.addChild(nameField);

        baseEventContainer = new EventContainer(true);
        addChild(baseEventContainer);
        eventContainers.put((FragmentContainer) lifeline.eContainer(), baseEventContainer);
    }

    @Override
    public void destroy() {
        super.destroy();

        EMFEditUtil.removeListenerFor(layoutElement, this);
    }

    /**
     * Sets the layout element for the corresponding lifeline.
     *
     * @param layoutElement the {@link LayoutElement} to set
     */
    public void setLayoutElement(LayoutElement layoutElement) {
        this.layoutElement = layoutElement;
        setPositionRelativeToParent(new Vector3D(layoutElement.getX(), layoutElement.getY()));

        EMFEditUtil.addListenerFor(layoutElement, this);
    }

    /**
     * Returns the layout element for the visualized lifeline.
     *
     * @return the {@link LayoutElement} for the {@link Lifeline}
     */
    public LayoutElement getLayoutElement() {
        return layoutElement;
    }

    /**
     * Returns the lifeline represented by this view.
     *
     * @return the {@link Lifeline} represented by this view
     */
    public Lifeline getLifeline() {
        return lifeline;
    }

    /**
     * Returns the message view view that represents the complete message view.
     *
     * @return the {@link MessageViewView} representing the message view
     */
    public MessageViewView getMessageViewView() {
        return messageViewView;
    }

    @Override
    public void notifyChanged(Notification notification) {
        if (notification.getNotifier() == layoutElement) {
            setPositionRelativeToParent(new Vector3D(layoutElement.getX(), layoutElement.getY()));
        }
    }

    @Override
    public ILifelineViewHandler getHandler() {
        return handler;
    }

    @Override
    public void setHandler(ILifelineViewHandler handler) {
        this.handler = handler;

        nameContainer.setHandler(handler);
    }

    @Override
    public boolean containsPointGlobal(Vector3D testPoint) {
        boolean containsPoint = baseEventContainer.containsPointGlobal(testPoint)
                || nameContainer.containsPointGlobal(testPoint);

        return containsPoint;
    }

    /**
     * Adds the messag end to this lifeline view at the given position and returns its view. 
     * The position is based on the model. A spacer is added in addition after the fragment, 
     * which can be configured to allow creation of fragments using the <code>allowCreation</code> parameter. 
     * In case the fragment is part of a delete message, 
     * destruction lines are added to the spacer following it to visualize this.
     *
     * @param messageEnd the {@link MessageOccurrenceSpecification} to add to this lifeline view
     * @param modelIndex the index in the model of this message end
     * @param allowCreation true, if fragments can be created after this end, false otherwise
     * @return the view that represents the message end
     */
    public RamRectangleComponent addMessageEnd(MessageOccurrenceSpecification messageEnd, int modelIndex,
            boolean allowCreation) {
        FragmentView messageEndView = new MessageEndView(messageEnd, MessageViewView.BOX_WIDTH,
                MessageViewView.BOX_HEIGHT);
        addFragment(messageEnd, modelIndex, allowCreation, messageEndView);

        // Add lines to spacer after fragment for destruction.
        if (messageEnd.getMessage().getMessageSort() == MessageSort.DELETE_MESSAGE
                && messageEnd.getMessage().getReceiveEvent() == messageEnd) {
            int viewIndex = getViewIndex(messageEnd, modelIndex);
            Spacer spacer = (Spacer) getEventContainerFor(messageEnd).getChildByIndex(viewIndex + 1);

            float x1 = 0;
            float y1 = 0;
            float x2 = spacer.getMinimumWidth();
            float y2 = spacer.getMinimumHeight();

            spacer.addChild(new RamLineComponent(x1, y1, x2, y2));
            spacer.addChild(new RamLineComponent(x1, y2, x2, y1));
        }

        return messageEndView;
    }

    /**
     * Adds the view of the execution statement to this lifeline view. 
     * The view added to this view is a placeholder, 
     * because its size might be larger than the lifelines event container.
     *
     * @param statementView the view representing the statement
     * @param statement the {@link ExecutionStatement} to add
     * @param modelIndex the index in the model of the statement
     */
    public void addStatement(TextView statementView, ExecutionStatement statement, int modelIndex) {
        PlaceholderView placeHolder = new PlaceholderView(statementView, statement, FRAGMENT_OFFSET, 0);
        placeHolder.setMinimumSize(MessageViewView.BOX_WIDTH, MessageViewView.BOX_HEIGHT);
        placeHolder.setMinimumHeight(statementView.getHeight());

        addFragment(statement, modelIndex, true, placeHolder);
        fragments.put(statement, placeHolder);
    }

    /**
     * Adds the assignment statement view to this lifeline. 
     * The view added to this view is a placeholder, because its
     * size might be larger than the lifelines event container.
     *
     * @param assignmentView the view for the assignment statement
     * @param assignment the {@link AssignmentStatement} represented by the view
     * @param modelIndex the index in the model of the statement
     */
    public void addAssignment(AssignmentStatementView assignmentView, AssignmentStatement assignment, int modelIndex) {
        PlaceholderView placeHolder = new PlaceholderView(assignmentView, assignment, FRAGMENT_OFFSET, 0);
        placeHolder.setMinimumSize(MessageViewView.BOX_WIDTH, MessageViewView.BOX_HEIGHT);
        placeHolder.setMinimumHeight(assignmentView.getHeight());

        addFragment(assignment, modelIndex, true, placeHolder);
        fragments.put(assignment, placeHolder);
    }
    
    /**
     * Adds the original behaviour view to this lifeline. 
     * The view added to this view is a placeholder, because its
     * size might be larger than the lifelines event container.
     * 
     * @param view the view for the original behaviour execution
     * @param originalBehaviorExecution the {@link OriginalBehaviorExecution} represented by the view
     * @param modelIndex the index of the fragment in the model
     */
    public void addOriginalBehaviour(RamRectangleComponent view, OriginalBehaviorExecution originalBehaviorExecution,
                    int modelIndex) {
        PlaceholderView placeHolder = new PlaceholderView(view, originalBehaviorExecution, -2, 0);
        placeHolder.setMinimumSize(MessageViewView.BOX_WIDTH, MessageViewView.BOX_HEIGHT);
        placeHolder.setMinimumHeight(view.getHeight());
        
        addFragment(originalBehaviorExecution, modelIndex, true, placeHolder);
        fragments.put(originalBehaviorExecution, placeHolder);
    }

    /**
     * Adds the view for the combined fragment at the given model position. 
     * A placeholder is placed on this view for the given view.
     *
     * @param combinedFragmentView the view of the {@link CombinedFragment}
     * @param combinedFragment the {@link CombinedFragment} visualized by the {@link CombinedFragmentView}
     * @param modelIndex the index in the model of the {@link CombinedFragment}
     */
    public void addCombinedFragment(CombinedFragmentView combinedFragmentView, CombinedFragment combinedFragment,
            int modelIndex) {
        PlaceholderView placeHolder = new PlaceholderView(combinedFragmentView, combinedFragment, FRAGMENT_OFFSET, 0);
        /**
         * CombinedFragments are layouted from the message view itself, otherwise the positioning is off, because the
         * combined fragment is located on several lifelines.
         */
        placeHolder.setLayout(new VerticalLayout());

        boolean allowMessageCreation = combinedFragment.getCovered().indexOf(lifeline) == 0;

        addFragment(combinedFragment, modelIndex, allowMessageCreation, placeHolder);

        // Spacer after combined fragment needs to be removed if the lifeline starts with a create message.
        // However, this information can only be obtained once we know what messages there are.
        // See moveRelationshipEnd(...).

        // For debug purposes only.
        if (fragments.get(combinedFragment) != null) {
            System.err.println("Should not happen: Something went wrong in the order of removal/adding.");
        }

        fragments.put(combinedFragment, placeHolder);
    }

    /**
     * Adds the view of the given fragment to this view. 
     * In addition, a spacer is added after the fragment view to separate multiple fragments. 
     * The spacer can be configured to allow or disallow to create new fragments at that
     * position (i.e., after the given fragment.
     *
     * @param fragment the {@link InteractionFragment} of which the view should be added
     * @param modelIndex the index in the model of the fragment
     * @param allowCreation true, if fragment creation should be allowed after this fragment, false otherwise
     * @param fragmentView the {@link FragmentView} of the fragment
     */
    private void addFragment(InteractionFragment fragment, int modelIndex, boolean allowCreation,
            FragmentView fragmentView) {
        int viewIndex = getViewIndex(fragment, modelIndex);

        EventContainer eventContainer = getEventContainerFor(fragment);

        // Undoing the removal of messages is not done in the order in which they are (wanted) to be removed.
        // Therefore, it is possible that the view index is greater.
        // To avoid an out of bounds exception we use the maximum index possible.
        if (viewIndex > eventContainer.getChildCount()) {
            viewIndex = eventContainer.getChildCount();
        } else if (startsWithCreate && !createdInOperand && eventContainer == baseEventContainer) {
            // The index is greater than it is supposed to be if there is a create message at the beginning.
            // Always remove 2 from the view index in that case.
            viewIndex -= 2;
        }

        eventContainer.addChild(viewIndex, fragmentView, false);
        eventContainer.addChild(viewIndex + 1, new Spacer(allowCreation));

        eventContainersMap.put(fragment, eventContainer);
    }

    /**
     * Adds the interaction operand to this view. 
     * The operand is represented as a container that can contain views of
     * fragments that are part of the operand.
     *
     * @param combinedFragment the {@link CombinedFragment} the operand is part of
     * @param operand the {@link InteractionOperand} to add
     * @param modelIndex the index of the operand in the model
     * @param allowCreation true, if fragment creation is allowed initially in this operand, false otherwise
     */
    public void addInteractionOperand(CombinedFragment combinedFragment, InteractionOperand operand, int modelIndex,
            boolean allowCreation) {
        PlaceholderView placeHolder = fragments.get(combinedFragment);

        OperandView operandView = new OperandView(operand, allowCreation);

        // We need to adjust the height of the single spacer in the operand.
        CombinedFragmentView actualView = (CombinedFragmentView) placeHolder.actualView;
        Spacer spacer = (Spacer) operandView.getChildByIndex(0);
        spacer.setMinimumHeight(actualView.getOperandMinimumHeight(operand) + MessageViewView.BOX_HEIGHT);
        spacer.updateParent();

        placeHolder.addChild(operandView);
        eventContainers.put(operand, operandView);
    }

    /**
     * Removes the given operand from this lifeline view.
     *
     * @param operand the {@link InteractionOperand} to remove from this lifeline
     */
    public void removeInteractionOperand(InteractionOperand operand) {
        EventContainer operandView = eventContainers.remove(operand);

        // Could be null if the lifeline was created in an operand, but in a different one.
        if (operandView != null) {
            // The operand's eContainer is null, so we can't get it from the fragments map.
            PlaceholderView placeHolder = (PlaceholderView) operandView.getParent();
            placeHolder.removeChild(operandView);
            operandView.destroy();
        }
    }

    /**
     * Requests the operands height to a be specific height. 
     * This means that depending on the difference to the actual
     * height, the bottom spacer's height will be adjusted accordingly.
     *
     * @param operand the {@link InteractionOperand} to adjust the height for
     * @param height the requested height
     */
    public void setOperandHeight(InteractionOperand operand, float height) {
        EventContainer operandView = eventContainers.get(operand);

        /**
         * Operand view can be null during re-layouting as a result of notifications.
         */
        if (operandView != null) {
            float currentHeight = operandView.getHeight();
            float heightDiff = height - currentHeight;

            /**
             * If the lifeline was created using a create message, we need to take into considering that the lifeline
             * was moved down. This decreases the operand height. The amount to decrease the height by is the space
             * between the top operand y position and the bottom y position of the lifeline's name container.
             */
            if (startsWithCreate) {
                float currentY = layoutElement.getY();

                PlaceholderView combinedFragmentPlaceholder = fragments.get(operand.eContainer());
                CombinedFragmentView combinedView = (CombinedFragmentView) combinedFragmentPlaceholder.actualView;
                RamRectangleComponent operandContainer = combinedView.getOperandContainer(operand);
                float operandY = operandContainer.getPosition(TransformSpace.RELATIVE_TO_PARENT).getY();
                float diff = currentY - operandY + getNameHeight();
                if (diff > 0) {
                    heightDiff -= diff;
                }
            }

            // Change the last spacers height. Negative values are okay since they are set to 0.
            if (heightDiff != 0) {
                Spacer spacer = (Spacer) operandView.getChildByIndex(operandView.getChildCount() - 1);
                float newHeight = spacer.getHeight() + heightDiff;
                spacer.setMinimumHeight(newHeight);
                spacer.updateParent();
            }
        }
    }

    /**
     * Removes the interaction fragment from this lifeline and destroys all corresponding views. 
     * The interaction fragment must be one that is placed only on this lifeline using a placeholder, 
     * i.e., its parent is not this lifeline view.
     *
     * @param interactionFragment the {@link InteractionFragment} to remove
     */
    public void removeInteractionFragment(InteractionFragment interactionFragment) {
        PlaceholderView placeholder = fragments.remove(interactionFragment);

        if (placeholder != null) {
            // Also delete the actual view.
            placeholder.actualView.destroy();

            removeFragment(placeholder);
        }
    }

    /**
     * Removes the combined fragment from this view.
     *
     * @param combinedFragment the {@link CombinedFragment} to remove
     */
    public void removeCombinedFragment(CombinedFragment combinedFragment) {
        PlaceholderView placeholder = fragments.remove(combinedFragment);

        removeFragment(placeholder);
    }

    /**
     * Removes the given fragment view from this view. Also removes the spacer that follows the view.
     *
     * @param fragmentView the {@link FragmentView} to remove
     */
    private void removeFragment(FragmentView fragmentView) {
        EventContainer eventContainer = eventContainersMap.remove(fragmentView.fragment);

        int index = eventContainer.getChildIndexOf(fragmentView);

        // Remove spacer as well.
        MTComponent spacer = eventContainer.getChildByIndex(index + 1);
        eventContainer.removeChild(spacer, false);
        spacer.destroy();

        eventContainer.removeChild(fragmentView);
        fragmentView.destroy();
    }

    /**
     * Highlights or de-highlights this view visually. The visual highlighting is done around the lifeline view.
     *
     * @param shouldHighlight true, if the view should be highlighted, false if it should be de-highlighted
     */
    public void highlight(boolean shouldHighlight) {
        MTColor highlightColor = shouldHighlight ? Colors.HIGHLIGHT_ELEMENT_STROKE_COLOR 
                : Colors.DEFAULT_ELEMENT_STROKE_COLOR;
        // By default, there is no stroke on the base container.
        baseEventContainer.setNoStroke(!shouldHighlight);
        baseEventContainer.setStrokeColor(highlightColor);
        nameContainer.setStrokeColor(highlightColor);
    }

    @Override
    public void translate(Vector3D dirVect) {
        super.translate(dirVect);
        updateMessageCallViews();
        updateChildren();
        /**
         * The message view needs to be informed to be able to update the layout of certain elements (if necessary).
         */
        updateParent();
    }

    /**
     * Updates the positions of the message lines of all messages associated with this view.
     */
    private void updateMessageCallViews() {
        for (MessageCallView messageCallView : messages) {
            messageCallView.updateLines();
        }
    }

    /**
     * Returns the height of the name container of this view. The name is the name of the represented typed element, but
     * might in addition also contain a stereotype.
     *
     * @return the height of the name
     */
    public float getNameHeight() {
        return nameContainer.getHeight();
    }

    /**
     * Returns the spacer corresponding (i.e., following) the fragment. 
     * If no spacer can be found, the initial spacer of this lifeline is returned.
     *
     * @param fragment the {@link InteractionFragment} for which the spacer is requested
     * @param modelIndex the index in the model of the fragment
     * @return the spacer following the view of the fragment
     */
    public RamRectangleComponent getSpacerForFragmentAt(InteractionFragment fragment, int modelIndex) {
        EventContainer eventContainer = getEventContainerFor(fragment);
        int viewIndex = getViewIndex(fragment, modelIndex);

        // Return the first spacer if the there was no fragment found (i.e., index too high).
        if (viewIndex < eventContainer.getChildCount()) {
            return (RamRectangleComponent) eventContainer.getChildByIndex(viewIndex - 1);
        } else if (startsWithCreate && (viewIndex - 3) < eventContainer.getChildCount()) {
            return (RamRectangleComponent) eventContainer.getChildByIndex(viewIndex - 3);
        } else {
            return (RamRectangleComponent) eventContainer.getChildByIndex(eventContainer.initialChildren - 1);
        }
    }

    /**
     * Returns the view of the fragment. If no view is found, null is returned.
     *
     * @param fragment the {@link InteractionFragment} for which the view is requested
     * @param modelIndex the index in the model of the fragment
     * @return the view of the fragment, null if none found
     */
    public RamRectangleComponent getFragmentView(InteractionFragment fragment, int modelIndex) {
        EventContainer eventContainer = getEventContainerFor(fragment);
        int viewIndex = getViewIndex(fragment, modelIndex);

        // If the lifeline starts with a create message, the found index might be wrong.
        // In that case we will try one before (because the first event is not on the event container).
        // Otherwise it really doesn't exist.
        if (viewIndex < eventContainer.getChildCount()) {
            return (RamRectangleComponent) eventContainer.getChildByIndex(viewIndex);
        } else if (startsWithCreate && (viewIndex - 2) < eventContainer.getChildCount()) {
            return (RamRectangleComponent) eventContainer.getChildByIndex(viewIndex - 2);
        } else {
            return null;
        }
    }

    /**
     * Retrieves the view index for the given fragment and its index in the model (in the containers list of fragments).
     * The view index is first searched in the view itself, whether there is a view that represents the fragment. In
     * case there is no view, the view index will be the index the fragment should be located at. This depends on the
     * number of fragments before the given model index.
     *
     * @param fragment the fragment to find the view's index for
     * @param modelIndex the model index of the fragment (in the containers list of fragments)
     * @return the index of the view that represents the fragment
     */
    private int getViewIndex(InteractionFragment fragment, int modelIndex) {
        EventContainer eventContainer = getEventContainerFor(fragment);

        // First, try to find an existing FragmentView that represents the given fragment.
        // Then we can for certain know the proper view index.
        // This supports situations when a message was deleted, but the fragments are still on the lifeline.
        for (int index = eventContainer.initialChildren; index < eventContainer.getChildren().length; index++) {
            MTComponent child = eventContainer.getChildByIndex(index);

            if (child instanceof FragmentView) {
                if (((FragmentView) child).fragment == fragment) {
                    return index;
                }
            }
        }

        // Fallback. In case the fragment wasn't found on the lifeline (usually when creating messages)
        // try to find the number of fragments before this fragments index in the model and use that.
        return eventContainer.initialChildren + getNumberOfFragmentsBefore(fragment.getContainer(), modelIndex) * 2;
    }

    /**
     * Returns the number of fragments that are located on this lifeline before the given model index in the container.
     *
     * @param container the container of fragments
     * @param modelIndex the index of the model element in the list of fragments of the container
     * @return the number of fragments before the given model index
     */
    private int getNumberOfFragmentsBefore(FragmentContainer container, int modelIndex) {
        int currentIndex = 0;

        for (int index = 0; index < container.getFragments().size(); index++) {
            InteractionFragment currentFragment = container.getFragments().get(index);

            if (currentFragment.getCovered().contains(lifeline)) {
                if (index == modelIndex) {
                    return currentIndex;
                }

                currentIndex++;
            }
        }

        return currentIndex;
    }

    /**
     * Returns the event container for the given fragment.
     *
     * @param fragment the fragment to find the event container for
     * @return the event container for the given fragment
     */
    private EventContainer getEventContainerFor(InteractionFragment fragment) {
        FragmentContainer container = fragment.getContainer();

        return eventContainers.get(container);
    }

    /**
     * Returns the fragment whose view is located before (vertically) the given location. 
     * If no fragment is located before, null is returned.
     *
     * @param location the location in this view
     * @return the fragment of which its view is located before (vertically) the given location, null if none found
     */
    public InteractionFragment getFragmentBefore(Vector3D location) {
        PickResult pickResults = pick(location.getX(), location.getY(), true);
        MTComponent component = pickResults.getNearestPickResult();

        if (!pickResults.isEmpty()) {
            EventContainer eventContainer = (EventContainer) pickResults.getPickList().get(1).hitObj;

            if (component != null) {
                int index = eventContainer.getChildIndexOf(component);

                if (index >= eventContainer.initialChildren) {
                    FragmentView fragmentView = (FragmentView) eventContainer.getChildByIndex(index - 1);
                    return fragmentView.fragment;
                } else if (startsWithCreate) {
                    // Assume that the fragment we want is the first in the (unordered) list.
                    // I.e., the one that is the receive event of the create message.
                    return lifeline.getCoveredBy().get(0);
                }
            }
        }

        return null;
    }

    /**
     * Returns the fragment container of which its view is placed at the given location. 
     * Returns null if no fragment container could be found.
     *
     * @param location the location at which the fragment container is requested for
     * @return the fragment container of which its view is placed at the given location, null if none found
     */
    public FragmentContainer getFragmentContainerAt(Vector3D location) {
        PickResult pickResults = pick(location.getX(), location.getY(), true);
        EventContainer eventContainer = (EventContainer) pickResults.getPickList().get(1).hitObj;

        for (Entry<FragmentContainer, EventContainer> entry : eventContainers.entrySet()) {
            if (entry.getValue() == eventContainer) {
                return entry.getKey();
            }
        }

        return null;
    }

    @Override
    public void moveRelationshipEnd(RamEnd<?, ?> end, Position oldPosition, Position newPosition) {
        if (oldPosition == null) {
            MessageCallView messageCallView = (MessageCallView) end.getRelationshipView();
            messages.add(messageCallView);

            // When dealing with create messages we need to keep track that there is one for layouting.
            if (end.getComponentView() == this) {
                @SuppressWarnings("unchecked")
                RamEnd<MessageEnd, RamRectangleComponent> receiveEnd = (RamEnd<MessageEnd, RamRectangleComponent>) end;
                if (end.getComponentView() == this
                        && receiveEnd.getModel().getMessage().getMessageSort() == MessageSort.CREATE_MESSAGE) {
                    startsWithCreate = true;

                    /**
                     * Handle the special case of create messages inside an operand.
                     */
                    if (messageViewView.getSpecification() != receiveEnd.getModel().eContainer()) {
                        handleCreationInOperand((MessageOccurrenceSpecification) receiveEnd.getModel());
                    }

                    baseEventContainer.allowCreation();
                }
            }
        }

        updateRelationshipEnd(end);
    }

    /**
     * Handles the special case of a create message inside an operand. 
     * Operands above the one the event is in must be removed, because the lifeline starts in that one. 
     * Additionally, the first spacer on the lifeline (belonging to the base event container) needs to be hidden.
     * The spacer will still exist but is invisible.
     *
     * @param fragment the {@link MessageOccurrenceSpecification} that is the receive event of the create message
     */
    private void handleCreationInOperand(MessageOccurrenceSpecification fragment) {
        createdInOperand = true;
        Spacer spacer = (Spacer) baseEventContainer.getChildByIndex(baseEventContainer.initialChildren - 1);

        CombinedFragment combinedFragment = (CombinedFragment) fragment.getContainer().eContainer();
        int operandIndex = combinedFragment.getOperands().indexOf(fragment.getContainer());

        if (operandIndex > 0) {
            for (int index = operandIndex - 1; index >= 0; index--) {
                InteractionOperand operand = combinedFragment.getOperands().get(index);
                removeInteractionOperand(operand);
            }
        }

        messageViewView.layoutMessageView();
        spacer.setMaximumHeight(0);
        spacer.setMinimumHeight(0);
        spacer.updateParent();
    }

    @Override
    public void updateRelationshipEnd(RamEnd<?, ?> end) {
        RamRectangleComponent endView = end.getComponentView();
        Vector3D location = new Vector3D(endView.getPosition(TransformSpace.GLOBAL));

        // Move the end of a create message to the center of the name box.
        // And move the end of a regular message to center of the end view
        // and to the middle of the lifeline.
        if (end.getComponentView() == this) {
            location.setY(location.getY() + getNameHeight() / 2f);
        } else {
            location.setY(location.getY() + endView.getHeight() / 2f);

            // Fix the x position to be in the middle of the lifeline, where the line of the lifeline is.
            float rightX = location.getX() + (endView.getWidth() / 2);
            location.setX(rightX);
        }

        /**
         * The position needs to be converted to relative position, because drawing everything excepts a relative
         * position.
         */
        location = convertGlobalToRelativePosition(location);
        end.setLocation(location);

        // Correct the position of ends of self messages.
        if (endView != this) {
            RamEnd<?, ?> otherEnd = end.getOpposite();

            if (end.getModel() instanceof MessageOccurrenceSpecification
                    && otherEnd.getModel() instanceof MessageOccurrenceSpecification) {
                Message message = ((MessageEnd) end.getModel()).getMessage();
                if (message.isSelfMessage()) {
                    end.setPosition(Position.RIGHT);
                }
            }
        }
    }

    /**
     * Converts a global position into a relative position. The relative position is relative to the parent of this
     * lifeline view.
     *
     * @param globalPosition the global position to convert
     * @return the relative position to the lifeline view's parent
     */
    private Vector3D convertGlobalToRelativePosition(Vector3D globalPosition) {
        return getGlobalVecToParentRelativeSpace(this, globalPosition);
    }

    @Override
    public void removeRelationshipEnd(RamEnd<?, ?> end) {
        messages.remove(end.getRelationshipView());
        RamRectangleComponent endView = end.getComponentView();

        // Only remove events on the lifeline itself (not the LifelineView).
        if (endView != this) {
            removeFragment((FragmentView) endView);
        }
    }

}
