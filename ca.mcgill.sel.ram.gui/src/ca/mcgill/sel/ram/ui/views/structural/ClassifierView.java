package ca.mcgill.sel.ram.ui.views.structural;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.mt4j.components.TransformSpace;
import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.ram.Attribute;
import ca.mcgill.sel.ram.Classifier;
import ca.mcgill.sel.ram.LayoutElement;
import ca.mcgill.sel.ram.NamedElement;
import ca.mcgill.sel.ram.Operation;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.RamApp.DisplayMode;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.components.RamTextComponent;
import ca.mcgill.sel.ram.ui.components.RamTextComponent.Alignment;
import ca.mcgill.sel.ram.ui.layouts.VerticalLayout;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.utils.Fonts;
import ca.mcgill.sel.ram.ui.utils.Strings;
import ca.mcgill.sel.ram.ui.views.RamEnd;
import ca.mcgill.sel.ram.ui.views.RamEnd.Position;
import ca.mcgill.sel.ram.ui.views.RelationshipView;
import ca.mcgill.sel.ram.ui.views.TextView;
import ca.mcgill.sel.ram.ui.views.handler.HandlerFactory;
import ca.mcgill.sel.ram.ui.views.structural.handler.IClassifierViewHandler;

/**
 * This view acts as a super class for all views that represent a sub-class of {@link Classifier}. It contains the basic
 * structure of a class and supports operations and relationships. Sub-classes may override the build methods in order
 * to change the visual appearance.
 *
 * @param <H> the handler interface that this component uses
 * @author mschoettle
 */
public abstract class ClassifierView<H extends IClassifierViewHandler> extends BaseView<H> {

    /**
     * Bottom buffer for attribute and operation container in order to prevent a static attribute or operation to be not
     * visible.
     */
    protected static final float BUFFER_CONTAINER_BOTTOM = 4f;

    /**
     * The minimum width of the view.
     */
    protected static final float MINIMUM_WIDTH = 150f;

    /**
     * The default icon size.
     */
    protected static final int ICON_SIZE = Fonts.DEFAULT_FONT.getFontAbsoluteHeight() + 2
            * RamTextComponent.DEFAULT_BUFFER_SIZE;

    /**
     * The buffer on the bottom (south) for each child).
     */
    protected static final int BUFFER_BOTTOM = 0;

    /**
     * The view container for attributes.
     */
    protected RamRectangleComponent attributesContainer;

    /**
     * The view container for operations.
     */
    protected RamRectangleComponent operationsContainer;

    /**
     * The map of operations to their views.
     */
    protected Map<Operation, OperationView> operations;

    /**
     * The map of attributes to their views.
     */
    protected Map<Attribute, AttributeView> attributes;

    /**
     * Whether operations can be edited.
     */
    protected boolean editableOperations;

    /**
     * TextView used to display the visibility.
     */
    protected TextView visibilityField;

    private Map<Position, List<RamEnd<?, ? extends RamRectangleComponent>>> relationshipEndByPosition;

    /**
     * Creates a new {@link ClassifierView} for a given classifier and layout element.
     *
     * @param structuralDiagramView the {@link StructuralDiagramView} that is the parent of this view
     * @param classifier the {@link Classifier} this view represents
     * @param layoutElement the {@link LayoutElement} that contains the layout information for this classifier
     * @param editableOperations true, if operations can be edited, false otherwise
     */
    protected ClassifierView(StructuralDiagramView structuralDiagramView, Classifier classifier,
            LayoutElement layoutElement, boolean editableOperations) {
        super(structuralDiagramView, classifier, layoutElement);
        this.structuralDiagramView = structuralDiagramView;
        this.classifier = classifier;
        this.editableOperations = editableOperations;

        setEnabled(true);
        setNoFill(false);
        setFillColor(Colors.CLASS_VIEW_DEFAULT_FILL_COLOR);
        setNoStroke(false);

        // build components
        build(classifier);

        // now init based on the class
        initializeClass(classifier);

        // initialize the position map
        relationshipEndByPosition = new HashMap<Position, List<RamEnd<?, ? extends RamRectangleComponent>>>();
        for (Position position : Position.values()) {
            relationshipEndByPosition.put(position, new ArrayList<RamEnd<?, ? extends RamRectangleComponent>>());
        }
        visibilityField.setHandler(HandlerFactory.INSTANCE.getClassifierVisibilityHandler());
    }

    /**
     * Creates a new {@link ClassifierView} for a given classifier and layout element. The operations are editable.
     *
     * @param structuralDiagramView the {@link StructuralDiagramView} that is the parent of this view
     * @param classifier the {@link Classifier} this view represents
     * @param layoutElement the {@link LayoutElement} that contains the layout information for this classifier
     */
    protected ClassifierView(StructuralDiagramView structuralDiagramView, Classifier classifier,
            LayoutElement layoutElement) {
        this(structuralDiagramView, classifier, layoutElement, true);
    }

    /**
     * Adds a new operation view for the given operation to this view at the given index. The index is an index inside
     * the {@link #operationsContainer}.
     *
     * @param index the index inside the operations container where this operation should be added to
     * @param operation the operation to add
     */
    protected void addOperationField(int index, Operation operation) {
        OperationView operationView = new OperationView(this, operation, editableOperations);
        operations.put(operation, operationView);

        operationsContainer.addChild(index, operationView);
        operationView.setHandler(HandlerFactory.INSTANCE.getOperationViewHandler());
    }

    /**
     * Adds the given end to this view at the position it has stored.
     *
     * @param end the end to add to this view.
     * @precondition This view is that end's associated class view.
     */
    public void addRelationshipEndAtPosition(RamEnd<? extends EObject, ? extends RamRectangleComponent> end) {
        List<RamEnd<?, ? extends RamRectangleComponent>> list = relationshipEndByPosition.get(end.getPosition());
        if (list.size() == 0) {
            end.setIsAlone(true);
        } else {
            end.setIsAlone(false);
            for (RamEnd<?, ? extends RamRectangleComponent> ramEnd : list) {
                ramEnd.setIsAlone(false);
            }
        }

        list.add(end);
        setCorrectPosition(list, end.getPosition());
    }

    /**
     * Builds this view for the given classifier. Builds the name, attributes and operations.
     *
     * @param classifier the classifier for this view
     */
    protected void build(Classifier classifier) {
        setMinimumWidth(MINIMUM_WIDTH);

        buildNameField(classifier);
        buildAttributesContainer();
        buildOperationsContainer();
    }

    /**
     * Builds the name field for the given classifier.
     *
     * @param classifier the classifier for this view
     */
    protected void buildNameField(Classifier classifier) {

        // Adding the visibility
        visibilityField = new TextView(classifier, CorePackage.Literals.CORE_MODEL_ELEMENT__VISIBILITY);
        visibilityField.setBufferSize(Cardinal.WEST, BUFFER_BOTTOM);
        nameContainer.addChild(visibilityField);

        TextView classNameField = new TextView(classifier, CorePackage.Literals.CORE_NAMED_ELEMENT__NAME);
        classNameField.setFont(Fonts.FONT_CLASS_NAME);
        classNameField.setUniqueName(false);
        classNameField.setAlignment(Alignment.CENTER_ALIGN);
        classNameField.setPlaceholderText(Strings.PH_ENTER_CLASS_NAME);
        classNameField.setHandler(HandlerFactory.INSTANCE.getClassNameHandler());
        setNameField(classNameField);
    }

    /**
     * Builds the empty attributes container.
     */
    protected void buildAttributesContainer() {
        // create and add the attributes field
        attributes = new HashMap<Attribute, AttributeView>();
        attributesContainer = new RamRectangleComponent(new VerticalLayout());
        attributesContainer.setNoStroke(false);
        attributesContainer.setMinimumHeight(ICON_SIZE / 2);
        attributesContainer.setBufferSize(Cardinal.SOUTH, BUFFER_CONTAINER_BOTTOM);
        addChild(attributesContainer);
    }

    /**
     * Builds the empty operations container.
     */
    protected void buildOperationsContainer() {
        operations = new HashMap<Operation, OperationView>();
        operationsContainer = new RamRectangleComponent(new VerticalLayout());
        operationsContainer.setNoStroke(false);
        operationsContainer.setMinimumHeight(ICON_SIZE);
        operationsContainer.setBufferSize(Cardinal.SOUTH, BUFFER_CONTAINER_BOTTOM);
        addChild(operationsContainer);
    }

    @Override
    public void destroy() {
        EMFEditUtil.removeListenerFor(classifier, this);

        // destroy relationships
        for (RelationshipView<?, ?> view : getAllRelationshipViews()) {
            view.destroy();
        }

        relationshipEndByPosition.clear();

        // destroy rest
        super.destroy();
    }

    /**
     * Returns all relationship views associated with this view.
     *
     * @return all relationship for this view.
     */
    public Set<RelationshipView<?, ? extends RamRectangleComponent>> getAllRelationshipViews() {
        Set<RelationshipView<?, ? extends RamRectangleComponent>> allRelationships =
                new HashSet<RelationshipView<?, ? extends RamRectangleComponent>>();
        for (List<RamEnd<?, ? extends RamRectangleComponent>> list : relationshipEndByPosition.values()) {
            for (RamEnd<?, ? extends RamRectangleComponent> end : list) {
                allRelationships.add(end.getRelationshipView());
            }
        }
        return allRelationships;
    }

    /**
     * Returns the classifier represented by this view.
     *
     * @return the {@link Classifier} represented by this view
     */
    @Override
    public Classifier getClassifier() {
        return classifier;
    }

    /**
     * Returns the component containing all {@link OperationView}s.
     *
     * @return the {@link RamRectangleComponent} containing all {@link OperationView}s
     */
    public RamRectangleComponent getOperationsContainer() {
        return operationsContainer;
    }

    /**
     * Returns the {@link StructuralDiagramView} which contains this view.
     *
     * @return the {@link StructuralDiagramView} (parent of this view)
     */
    @Override
    public StructuralDiagramView getStructuralDiagramView() {
        return structuralDiagramView;
    }

    /**
     * Initializes the view for the given classifier.
     *
     * @param classifier the classifier represented by this view
     */
    protected abstract void initializeClass(Classifier classifier);

    /**
     * Adds operation views to the view for each operation of the given classifier.
     *
     * @param classifier the classifier for which to add operation views for
     */
    protected void initializeOperations(Classifier classifier) {
        for (int i = 0; i < classifier.getOperations().size(); i++) {
            Operation operation = classifier.getOperations().get(i);
            addOperationField(i, operation);
        }
    }

    /**
     * NOTE Will add the end if it's position is null. If the end position is null will remove it from the list and set
     * the end's position as null.
     *
     * @param end The end to move.
     * @param newPosition The new position to set.
     * @postcondition The end's position will be set to the newPosition.
     */
    public void moveRelationshipEnd(RamEnd<? extends EObject, ? extends RamRectangleComponent> end,
            Position newPosition) {
        if (end.getPosition() == null) {
            // it updates the position and calls
            // getRelationshipView().shouldUpdate();
            end.setPosition(newPosition);
            addRelationshipEndAtPosition(end);
            // setCorrectPosition(end);
        } else {
            moveRelationshipEnd(end, end.getPosition(), newPosition);
        }
    }

    /**
     * Moves the relationship end to the correct position. This needs to be done when the view itself changed either
     * size or position.
     *
     * @param end The end to move.
     * @param oldPosition The old position of this end in the class.
     * @param newPosition The new position of this end.
     * @precondition The end's position is the same position as it is in the class view's list.
     * @postcondition The end's position will be set to newPosition.
     */
    public void moveRelationshipEnd(RamEnd<?, ? extends RamRectangleComponent> end, Position oldPosition,
            Position newPosition) {
        List<RamEnd<?, ? extends RamRectangleComponent>> oldList = relationshipEndByPosition.get(oldPosition);
        oldList.remove(end);

        // update isAlone property
        if (oldList.size() == 1) {
            oldList.get(0).setIsAlone(true);
        }
        end.setIsAlone(false);

        setCorrectPosition(oldList, oldPosition);

        List<RamEnd<?, ? extends RamRectangleComponent>> newList = relationshipEndByPosition.get(newPosition);
        newList.add(end);

        // update isAlone property
        if (newList.size() == 1) {
            end.setIsAlone(true);
        } else {
            for (RamEnd<?, ? extends RamRectangleComponent> r : newList) {
                r.setIsAlone(false);
            }
        }
        end.setPosition(newPosition);
        setCorrectPosition(newList, newPosition);
    }

    @Override
    public void notifyChanged(Notification notification) {
        super.notifyChanged(notification);
        if (notification.getNotifier() == classifier) {
            if (notification.getFeature() == RamPackage.Literals.CLASSIFIER__OPERATIONS) {
                switch (notification.getEventType()) {
                    case Notification.REMOVE:
                        Operation operation = (Operation) notification.getOldValue();
                        removeOperation(operation);
                        break;
                    case Notification.ADD:
                        operation = (Operation) notification.getNewValue();
                        // Ensure that operation field is added at the end of the operations container and not at an
                        // index out of bound , there might be more operations in the class than in the
                        // operations container.
                        int index = Math.min(notification.getPosition(), operationsContainer.getChildCount());
                        addOperationField(index, operation);
                        break;
                }
            } else if (notification.getFeature() == RamPackage.Literals.CLASSIFIER__SUPER_TYPES) {
                switch (notification.getEventType()) {
                    case Notification.REMOVE:
                        Classifier superType = (Classifier) notification.getOldValue();
                        structuralDiagramView.removeInheritanceView(classifier, superType);
                        break;
                    case Notification.ADD:
                        superType = (Classifier) notification.getNewValue();
                        structuralDiagramView.addInheritanceView(classifier, superType);
                        break;
                }
            }
        }
    }

    /**
     * Removes the view of the given operation from this view.
     *
     * @param operation the operation to remove
     */
    protected void removeOperation(final Operation operation) {
        if (operations.containsKey(operation)) {
            RamApp.getApplication().invokeLater(new Runnable() {

                @Override
                public void run() {
                    OperationView operationView = operations.remove(operation);

                    operationsContainer.removeChild(operationView);
                    operationView.destroy();
                }
            });
        }
    }

    /**
     * Removes the association end from the class view.
     *
     * @param end The end to remove.
     * @precondition The end's position is the same position as it is in the class view's list.
     */
    public void removeRelationshipEnd(RamEnd<? extends NamedElement, ? extends RamRectangleComponent> end) {
        List<RamEnd<?, ? extends RamRectangleComponent>> list = relationshipEndByPosition.get(end.getPosition());
        list.remove(end);
        if (list.size() == 1) {
            list.get(0).setIsAlone(true);
        }
        setCorrectPosition(list, end.getPosition());
    }

    @Override
    public void scale(float x, float y, float z, Vector3D scalingPoint, TransformSpace transformSpace) {
        super.scale(x, y, z, scalingPoint, transformSpace);
        updateRelationships();
    }

    /**
     * Changes the position of the given end to a specific position depending on the positioning.
     *
     * @param end the end to set the location for
     * @param classLocation the location of the class
     * @param number the number (in the index sense) of this end on this side of the class
     * @param space the space between two relationships
     */
    private void changePosition(RamEnd<?, ?> end, Vector3D classLocation, int number, float space) {
        switch (end.getPosition()) {
            case TOP:
                end.setLocation(new Vector3D(classLocation.getX() + number * space, classLocation.getY()));
                break;
            case BOTTOM:
                end.setLocation(new Vector3D(classLocation.getX() + number * space, classLocation.getY()
                        + getHeightXY(TransformSpace.RELATIVE_TO_PARENT)));
                break;
            case RIGHT:
                end.setLocation(new Vector3D(classLocation.getX() + getWidthXY(TransformSpace.RELATIVE_TO_PARENT),
                        classLocation.getY() + number * space));
                break;
            case LEFT:
                end.setLocation(new Vector3D(classLocation.getX(), classLocation.getY() + number * space));
                break;
        }
    }

    /**
     * Sets the correct position of all ends of the given list. The list is for the given position. The ends are
     * positioned such that a "pretty drawing" is achieved. More documentation is necessary.
     *
     * @param list the list of ends for a specific position
     * @param position the position the list of ends is for
     */
    // TODO: Needs refactoring!
    // CHECKSTYLE:IGNORE MethodLength FOR 2 LINES: Temporary fix. Needs refactoring.
    // CHECKSTYLE:IGNORE ReturnCount: Unfortunately, it is very difficult to reduce the return count. Needs refactoring.
    private void setCorrectPosition(List<RamEnd<?, ? extends RamRectangleComponent>> list, Position position) {
        // to speed things up
        if (list.size() == 0) {
            return;
        }

        // the top left position is needed to determine the new ends position along the side of the
        // class
        Vector3D classLocation = getLocalVecToParentRelativeSpace(this, getBounds().getVectorsLocal()[0]);

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

        // if this end is the only one on this position, calculate its coordinates accordingly
        if (list.size() == 1) {
            RamEnd<?, ? extends RamRectangleComponent> thisEnd = list.get(0);
            RamEnd<?, ? extends RamRectangleComponent> otherEnd = thisEnd.getOpposite();
            if (thisEnd.isAlone() && otherEnd.isAlone()) {
                // the other end is also along on its position, so we can align them in the middle
                // of the overlap
                Vector3D otherClassLocation =
                        getLocalVecToParentRelativeSpace(otherEnd.getComponentView(), otherEnd.getComponentView()
                                .getBounds().getVectorsLocal()[0]);
                final float thisXLeft = classLocation.getX();
                final float thisXRight = thisXLeft + getWidthXY(TransformSpace.RELATIVE_TO_PARENT);
                final float thisYTop = classLocation.getY();
                final float thisYBottom = thisYTop + getHeightXY(TransformSpace.RELATIVE_TO_PARENT);
                final float otherXLeft = otherClassLocation.getX();
                final float otherXRight =
                        otherXLeft + otherEnd.getComponentView().getWidthXY(TransformSpace.RELATIVE_TO_PARENT);
                final float otherYTop = otherClassLocation.getY();
                final float otherYBottom =
                        otherYTop + otherEnd.getComponentView().getHeightXY(TransformSpace.RELATIVE_TO_PARENT);

                if (thisXLeft > otherXLeft && thisXLeft < otherXRight || thisXRight > otherXLeft
                        && thisXRight < otherXRight || thisYTop > otherYTop && thisYTop < otherYBottom
                        || thisYBottom > otherYTop && thisYBottom < otherYBottom || otherXLeft > thisXLeft
                        && otherXLeft < thisXRight || otherXRight > thisXLeft && otherXRight < thisXRight
                        || otherYBottom > thisYTop && otherYBottom < thisYBottom || otherYTop > thisYTop
                        && otherYTop < thisYBottom) {
                    switch (position) {
                        case TOP:
                            if (thisXLeft > otherXLeft && thisXRight < otherXRight) {
                                // this class' edge is included in the other class'edge -> put the
                                // end in the middle
                                thisEnd.setLocation(new Vector3D((thisXLeft + thisXRight) / 2, thisYTop));
                            } else if (otherXLeft > thisXLeft && otherXRight < thisXRight) {
                                // the other class' edge is included in this edge -> put the end in
                                // the middle of the other one
                                thisEnd.setLocation(new Vector3D((otherXLeft + otherXRight) / 2, thisYTop));
                            } else {
                                // they overlap -> put the end in the center of the overlap
                                final float left = thisXLeft > otherXLeft ? thisXLeft : otherXLeft;
                                final float right = thisXRight < otherXRight ? thisXRight : otherXRight;
                                thisEnd.setLocation(new Vector3D((left + right) / 2, thisYTop));
                            }
                            break;
                        case BOTTOM:
                            if (thisXLeft > otherXLeft && thisXRight < otherXRight) {
                                // this class' edge is included in the other class'edge -> put the
                                // end in the middle
                                thisEnd.setLocation(new Vector3D((thisXLeft + thisXRight) / 2, thisYBottom));
                            } else if (otherXLeft > thisXLeft && otherXRight < thisXRight) {
                                // the other class' edge is included in this edge -> put the end in
                                // the middle of the other one
                                thisEnd.setLocation(new Vector3D((otherXLeft + otherXRight) / 2, thisYBottom));
                            } else {
                                // they overlap -> put the end in the center of the overlap
                                final float left = thisXLeft > otherXLeft ? thisXLeft : otherXLeft;
                                final float right = thisXRight < otherXRight ? thisXRight : otherXRight;
                                thisEnd.setLocation(new Vector3D((left + right) / 2, thisYBottom));
                            }
                            break;
                        case RIGHT:
                            if (thisYTop > otherYTop && thisYBottom < otherYBottom) {
                                // this class' edge is included in the other class'edge -> put the
                                // end in the middle
                                thisEnd.setLocation(new Vector3D(thisXRight, (thisYTop + thisYBottom) / 2));
                            } else if (otherYTop > thisYTop && otherYBottom < thisYBottom) {
                                // the other class' edge is included in this edge -> put the end in
                                // the middle of the other one
                                thisEnd.setLocation(new Vector3D(thisXRight, (otherYTop + otherYBottom) / 2));
                            } else {
                                final float top = thisYTop > otherYTop ? thisYTop : otherYTop;
                                final float bottom = thisYBottom < otherYBottom ? thisYBottom : otherYBottom;
                                thisEnd.setLocation(new Vector3D(thisXRight, (top + bottom) / 2));
                            }
                            break;
                        case LEFT:
                            if (thisYTop > otherYTop && thisYBottom < otherYBottom) {
                                // this class' edge is included in the other class'edge -> put the
                                // end in the middle
                                thisEnd.setLocation(new Vector3D(thisXLeft, (thisYTop + thisYBottom) / 2));
                            } else if (otherYTop > thisYTop && otherYBottom < thisYBottom) {
                                // the other class' edge is included in this edge -> put the end in
                                // the middle of the other one
                                thisEnd.setLocation(new Vector3D(thisXLeft, (otherYTop + otherYBottom) / 2));
                            } else {
                                final float top = thisYTop > otherYTop ? thisYTop : otherYTop;
                                final float bottom = thisYBottom < otherYBottom ? thisYBottom : otherYBottom;
                                thisEnd.setLocation(new Vector3D(thisXLeft, (top + bottom) / 2));
                            }
                            break;
                    }
                    return;
                }
            } else if (thisEnd.isAlone()) {
                // this end is the only one on this position, but the other one has a fixed position
                // align this ends coordinates with the other ones
                final float thisXLeft = classLocation.getX();
                final float thisXRight = thisXLeft + getWidthXY(TransformSpace.RELATIVE_TO_PARENT);
                final float thisYTop = classLocation.getY();
                final float thisYBottom = thisYTop + getHeightXY(TransformSpace.RELATIVE_TO_PARENT);
                final Vector3D otherEndLocation = otherEnd.getLocation();
                final float otherX = otherEndLocation.getX();
                final float otherY = otherEndLocation.getY();

                switch (position) {
                    case TOP:
                        if (thisXLeft < otherX && thisXRight > otherX) {
                            thisEnd.setLocation(new Vector3D(otherX, thisYTop));
                            return;
                        }
                        break;
                    case BOTTOM:
                        if (thisXLeft < otherX && thisXRight > otherX) {
                            thisEnd.setLocation(new Vector3D(otherX, thisYBottom));
                            return;
                        }
                        break;
                    case RIGHT:
                        if (thisYTop < otherY && thisYBottom > otherY) {
                            thisEnd.setLocation(new Vector3D(thisXRight, otherY));
                            return;
                        }
                        break;
                    case LEFT:
                        if (thisYTop < otherY && thisYBottom > otherY) {
                            thisEnd.setLocation(new Vector3D(thisXLeft, otherY));
                            return;
                        }
                        break;
                }
            }
        }

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

        // First, figure out the number of inheritance relationship kinds.
        // Multiple kinds on the same edge are grouped together.
        // Then, these numbers are used to calculate the divider amount between all ends.
        // In the last step, the correct position is set for each end,
        // depending on at what position (index) in the list it occurs.
        // There might be a slight flicker sometimes. This seems to happen when the relationship position changes
        // and the line is made straight instead of edged (because it jumps all of a sudden).
        int numberOfEnds = list.size();

        int numOfInheritance = 0;
        int numOfExtends = 0;
        int numOfImplements = 0;
        for (int index = 0; index < list.size(); index++) {
            RamEnd<?, ?> associationEnd = list.get(index);
            if (associationEnd.getRelationshipView() instanceof InheritanceView) {
                numOfInheritance++;

                InheritanceView inheritanceView = (InheritanceView) associationEnd.getRelationshipView();
                if (inheritanceView.isExtends()) {
                    numOfExtends++;
                } else {
                    numOfImplements++;
                }
            }
        }

        float dividerDistance;
        // The actual number of elements on one side of the class,
        // which share the available space.
        int actualNumOfEnds = numberOfEnds + 1;
        if (numOfInheritance > 0) {
            // If both kinds of inheritance are available, 2 slots are needed,
            // otherwise just 1.
            actualNumOfEnds += (numOfExtends > 0 && numOfImplements > 0) ? 2 : 1;

            dividerDistance = availableSpace / (actualNumOfEnds - numOfInheritance);
        } else {
            dividerDistance = availableSpace / (actualNumOfEnds - numOfInheritance);
        }

        // The number is the slot number on the side of the class.
        // E.g., from left to right, the first end has number 1, the next number 2 and so on.
        int firstExtendsNumber = 0;
        int firstImplementsNumber = 0;
        // Keep track of the number of additional inheritances to be able to figure out the actual index of an end.
        int additionalInheritance = 0;

        for (int index = 0; index < list.size(); index++) {
            RamEnd<?, ?> associationEnd = list.get(index);

            if (associationEnd.getRelationshipView() instanceof InheritanceView) {
                InheritanceView inheritanceView = (InheritanceView) associationEnd.getRelationshipView();

                int number = 0;
                if (inheritanceView.isExtends()) {
                    if (firstExtendsNumber == 0) {
                        firstExtendsNumber = index + 1 - additionalInheritance;
                    } else {
                        additionalInheritance++;
                    }
                    number = firstExtendsNumber;
                } else {
                    if (firstImplementsNumber == 0) {
                        firstImplementsNumber = index + 1 - additionalInheritance;
                    } else {
                        additionalInheritance++;
                    }
                    number = firstImplementsNumber;
                }
                changePosition(associationEnd, classLocation, number, dividerDistance);
            } else {
                changePosition(associationEnd, classLocation, index + 1 - additionalInheritance, dividerDistance);
            }
        }
    }

    /**
     * Resets the positions for all ends on the same edge of this view.
     *
     * @param end the end that moved.
     * @precondition The end's position is the same position as it is in the class view's list.
     */
    public void setCorrectPosition(RamEnd<?, ? extends RamRectangleComponent> end) {
        List<RamEnd<?, ? extends RamRectangleComponent>> list = relationshipEndByPosition.get(end.getPosition());
        setCorrectPosition(list, end.getPosition());
    }

    @Override
    public void setSizeLocal(float width, float height) {
        super.setSizeLocal(width, height);
        updateRelationships();
    }

    @Override
    public void translate(Vector3D dirVect) {
        super.translate(dirVect);
        updateRelationships();
    }

    /**
     * Updates the view for all of this class' associations.
     */
    protected void updateRelationships() {
        if (relationshipEndByPosition != null) {
            for (RelationshipView<?, ? extends RamRectangleComponent> view : getAllRelationshipViews()) {
                view.updateLines();
            }
        }
    }

}
