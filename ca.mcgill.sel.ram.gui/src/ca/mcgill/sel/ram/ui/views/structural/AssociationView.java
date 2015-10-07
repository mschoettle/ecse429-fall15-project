package ca.mcgill.sel.ram.ui.views.structural;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.mt4j.components.visibleComponents.shapes.MTPolygon;
import org.mt4j.input.gestureAction.TapAndHoldVisualizer;
import org.mt4j.input.inputProcessors.componentProcessors.tapAndHoldProcessor.TapAndHoldProcessor;
import org.mt4j.util.MTColor;
import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.commons.emf.util.EMFModelUtil;
import ca.mcgill.sel.core.COREFeature;
import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.Association;
import ca.mcgill.sel.ram.AssociationEnd;
import ca.mcgill.sel.ram.ClassifierMapping;
import ca.mcgill.sel.ram.ImplementationClass;
import ca.mcgill.sel.ram.Instantiation;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.ReferenceType;
import ca.mcgill.sel.ram.controller.ControllerFactory;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamTextComponent;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.utils.Constants;
import ca.mcgill.sel.ram.ui.utils.Fonts;
import ca.mcgill.sel.ram.ui.utils.GraphicalUpdater;
import ca.mcgill.sel.ram.ui.utils.RamModelUtils;
import ca.mcgill.sel.ram.ui.utils.Strings;
import ca.mcgill.sel.ram.ui.views.RamEnd;
import ca.mcgill.sel.ram.ui.views.RamEnd.Position;
import ca.mcgill.sel.ram.ui.views.RelationshipView;
import ca.mcgill.sel.ram.ui.views.TextView;
import ca.mcgill.sel.ram.ui.views.handler.HandlerFactory;
import ca.mcgill.sel.ram.ui.views.handler.ITextViewHandler;
import ca.mcgill.sel.ram.ui.views.handler.impl.TextViewHandler;
import ca.mcgill.sel.ram.util.AssociationConstants;

/**
 * AssociationViews are components used to draw {@link Association}s onto the {@link StructuralDiagramView}. They draw
 * {@link AssociationEnd}s using the correct visual representation and the link them together using a minimal set of
 * vertical and horizontal lines. All elements in this component change color when highlighted.
 *
 * @author vbonnet
 * @author mschoettle
 * @author eyildirim
 * @author tdimeco
 * @author cbensoussan
 */
public class AssociationView extends RelationshipView<AssociationEnd, ClassifierView<?>> implements
        INotifyChangedListener {

    private static final int ARROW_SIZE = 20;
    private static final int ROTATION = 90;
    private static final int TEXT_OFFSET_FROM_CLASSIFIER = 15;
    private static final int TEXT_OFFSET_FROM_ASSOCIATION = 5;
    private static final String FROM = "FROM";
    private static final String TO = "TO";

    /**
     * This is a rectangular text component for association multiplicity. It has the AssociationEnd information which it
     * is related to.
     */
    public class MultiplicityTextView extends TextView {

        /**
         * Creates a new text view for the multiplicity.
         *
         * @param associationEnd
         *            The association end to display
         */
        public MultiplicityTextView(AssociationEnd associationEnd) {
            // there is no feature for the multiplicity
            super(associationEnd, null);
        }

        @Override
        protected String getModelText() {
            return RamModelUtils.getMultiplicity((AssociationEnd) getData(),
                    hasKeyIndexedSelection((AssociationEnd) getData()));
        }

        @Override
        public void notifyChanged(Notification notification) {
            if (notification.getNotifier() == getData()) {
                updateText();
            }
        }

        @Override
        public void showKeyboard() {
            super.showKeyboard(this);
            getKeyboard().setSymbolsState(true);
        }
    }

    private TextView fromEndRolename;
    private TextView toEndRolename;

    private MultiplicityTextView fromEndMultiplicity;
    private MultiplicityTextView toEndMultiplicity;

    private TextView fromEndFeatureSelection;
    private TextView toEndFeatureSelection;

    private TextView fromEndKeySelection;
    private TextView toEndKeySelection;
    private ClassifierMapping fromKeyMapping;
    private ClassifierMapping toKeyMapping;

    private GraphicalUpdater graphicalUpdater;

    /**
     * Creates a new AssociationView.
     *
     * @param association
     *            the represented {@link Association}
     * @param from
     *            the from {@link AssociationEnd}
     * @param fromEndClassifierView
     *            the from {@link ClassifierView}
     * @param to
     *            the to {@link AssociationEnd}
     * @param toEndClassifierView
     *            the to {@link ClassifierView}
     * @see RelationshipView#RelationshipView(ca.mcgill.sel.ram.NamedElement, ClassifierView,
     *      ca.mcgill.sel.ram.NamedElement, ClassifierView)
     */
    public AssociationView(Association association, AssociationEnd from, ClassifierView<?> fromEndClassifierView,
            AssociationEnd to, ClassifierView<?> toEndClassifierView) {
        super(from, fromEndClassifierView, to, toEndClassifierView);

        // We do not need to add a listener for the "to" side, this listener already listens on the whole class
        EMFEditUtil.addListenerFor(from, this);

        Aspect aspect = EMFModelUtil.getRootContainerOfType(association, RamPackage.Literals.ASPECT);
        graphicalUpdater = RamApp.getApplication().getGraphicalUpdaterForAspect(aspect);
        graphicalUpdater.addGUListener(association, this);
    }

    /**
     * Returns the from or to end of the association view given a text field from the wanted end.
     * @param textView A text view from the wanted end
     * @return The from or to end of the association view
     */
    public RamEnd<AssociationEnd, ClassifierView<?>> getEnd(TextView textView) {
        if (!this.containsChild(textView)) {
            return null;
        }

        if (textView == fromEndFeatureSelection || textView == fromEndKeySelection || textView == fromEndRolename
                || textView == fromEndMultiplicity) {
            return this.getFromEnd();
        } else {
            return this.getToEnd();
        }
    }

    /**
     * Creates texts (multiplicity and role name) for the given ends if they haven't been created yet.
     *
     * @param ramEnd the first end
     * @param ramEnd2 the second (opposite) end
     */
    private void createTexts(RamEnd<AssociationEnd, ClassifierView<?>> ramEnd, RamEnd<AssociationEnd,
            ClassifierView<?>> ramEnd2) {
        if (fromEndRolename == null) {
            fromEndRolename = createRoleNameView(ramEnd.getModel());
            fromEndMultiplicity = createMultiplicityView(ramEnd.getModel());
            if ((ramEnd.getModel().getUpperBound() > 1
                    || ramEnd.getModel().getUpperBound() == -1)
                    && !(ramEnd.getModel().eContainer() instanceof ImplementationClass)) {
                fromEndFeatureSelection = createFeatureSelectionView(ramEnd.getModel());
            }
            if (hasKeyIndexedSelection(ramEnd.getModel())) {
                fromKeyMapping = getMapping(ramEnd.getModel(), AssociationConstants.KEY_CLASS_NAME);
                fromEndKeySelection = createKeySelectionView(fromKeyMapping);
            }
            graphicalUpdater.addGUListener(ramEnd.getModel(), fromEndRolename);
        }

        if (toEndRolename == null) {
            toEndRolename = createRoleNameView(ramEnd2.getModel());
            toEndMultiplicity = createMultiplicityView(ramEnd2.getModel());
            if ((ramEnd2.getModel().getUpperBound() > 1
                    || ramEnd2.getModel().getUpperBound() == -1)
                    && !(ramEnd2.getModel().eContainer() instanceof ImplementationClass)) {
                toEndFeatureSelection = createFeatureSelectionView(ramEnd2.getModel());
            }
            if (hasKeyIndexedSelection(ramEnd2.getModel())) {
                toKeyMapping = getMapping(ramEnd2.getModel(), AssociationConstants.KEY_CLASS_NAME);
                toEndKeySelection = createKeySelectionView(toKeyMapping);
            }
            graphicalUpdater.addGUListener(ramEnd2.getModel(), toEndRolename);
        }
    }

    /**
     * Moves the texts (multiplicity and role name) for the given end.
     *
     * @param ramEnd the end the texts to move for
     * @param roleName the view for the role name
     * @param multiplicity the view for the multiplicity
     * @param featureSelection the view for the feature selection
     * @param keySelection the view for the key selection
     */
    private void moveTexts(RamEnd<AssociationEnd, ClassifierView<?>> ramEnd, TextView roleName,
            MultiplicityTextView multiplicity, TextView featureSelection, TextView keySelection) {
        RamEnd<AssociationEnd, ClassifierView<?>> opposite = ramEnd.getOpposite();

        Vector3D keySelectionSize = new Vector3D(0, 0);
        if (keySelection != null) {
            keySelectionSize.setX(keySelection.getWidth());
            keySelectionSize.setY(keySelection.getHeight());
        }
        moveRoleName(roleName, opposite.getLocation(), opposite.getPosition(), keySelectionSize);
        moveMultiplicity(multiplicity, opposite.getLocation(), opposite.getPosition(), keySelectionSize);
        if (featureSelection != null) {
            moveFeatureSelection(featureSelection, opposite.getLocation(), opposite.getPosition(),
                    keySelectionSize, multiplicity.getWidth());
        }
        if (keySelection != null) {
            moveKeySelection(keySelection, opposite.getLocation(), opposite.getPosition());
        }
    }

    /**
     * Moves the role name to the proper position.
     *
     * @param textView the view of the role name
     * @param currentPosition the current position of the role name
     * @param position the side of the association end on the attached classifier view
     * @param keySelectionSize the size of the key selection box
     */
    @SuppressWarnings("static-method")
    private void moveRoleName(RamTextComponent textView, Vector3D currentPosition,
            Position position, Vector3D keySelectionSize) {
        float x = currentPosition.getX();
        float y = currentPosition.getY();

        // happily changing the anchor of the text area is sufficient in most cases
        switch (position) {
            case BOTTOM:
                textView.setAnchor(PositionAnchor.UPPER_RIGHT);
                x -= TEXT_OFFSET_FROM_ASSOCIATION;
                y += TEXT_OFFSET_FROM_CLASSIFIER + keySelectionSize.getY();
                break;
            case TOP:
                textView.setAnchor(PositionAnchor.LOWER_RIGHT);
                x -= TEXT_OFFSET_FROM_ASSOCIATION;
                y -= TEXT_OFFSET_FROM_CLASSIFIER + keySelectionSize.getY();
                break;
            case LEFT:
                textView.setAnchor(PositionAnchor.LOWER_RIGHT);
                x -= TEXT_OFFSET_FROM_CLASSIFIER + keySelectionSize.getX();
                y -= TEXT_OFFSET_FROM_ASSOCIATION;
                break;
            case RIGHT:
                textView.setAnchor(PositionAnchor.LOWER_LEFT);
                x += TEXT_OFFSET_FROM_CLASSIFIER + keySelectionSize.getX();
                y -= TEXT_OFFSET_FROM_ASSOCIATION;
                break;
        }

        textView.setPositionRelativeToParent(new Vector3D(x, y));
    }

    /**
     * Moves the multiplicity to the proper position.
     *
     * @param textView the view of the multiplicity
     * @param currentPosition the current position of the multiplicity
     * @param position the side of the association end on the attached classifier view
     * @param keySelectionSize the size of the key selection box
     */
    @SuppressWarnings("static-method")
    private void moveMultiplicity(RamTextComponent textView, Vector3D currentPosition,
            Position position, Vector3D keySelectionSize) {
        float x = currentPosition.getX();
        float y = currentPosition.getY();

        // gladly changing the anchor of the text area is sufficient in most cases
        switch (position) {
            case BOTTOM:
                textView.setAnchor(PositionAnchor.UPPER_LEFT);
                x += TEXT_OFFSET_FROM_ASSOCIATION;
                y += TEXT_OFFSET_FROM_CLASSIFIER + keySelectionSize.getY();
                break;
            case TOP:
                textView.setAnchor(PositionAnchor.LOWER_LEFT);
                x += TEXT_OFFSET_FROM_ASSOCIATION;
                y -= TEXT_OFFSET_FROM_CLASSIFIER + keySelectionSize.getY();
                break;
            case LEFT:
                textView.setAnchor(PositionAnchor.UPPER_RIGHT);
                x -= TEXT_OFFSET_FROM_CLASSIFIER + keySelectionSize.getX();
                y += TEXT_OFFSET_FROM_ASSOCIATION;
                break;
            case RIGHT:
                textView.setAnchor(PositionAnchor.UPPER_LEFT);
                x += TEXT_OFFSET_FROM_CLASSIFIER + keySelectionSize.getX();
                y += TEXT_OFFSET_FROM_ASSOCIATION;
                break;
        }

        textView.setPositionRelativeToParent(new Vector3D(x, y));
    }

    /**
     * Moves the feature selection to the proper position.
     *
     * @param textView the view of the feature selection
     * @param currentPosition the current position of the feature selection
     * @param position the side of the association end on the attached classifier view
     * @param keySelectionSize the size of the key selection box
     * @param multiplicityWidth the width of the multiplicity text view
     */
    @SuppressWarnings("static-method")
    private void moveFeatureSelection(RamTextComponent textView, Vector3D currentPosition,
            Position position, Vector3D keySelectionSize, float multiplicityWidth) {
        float x = currentPosition.getX();
        float y = currentPosition.getY();

        // gladly changing the anchor of the text area is sufficient in most cases
        switch (position) {
            case BOTTOM:
                textView.setAnchor(PositionAnchor.UPPER_LEFT);
                x += TEXT_OFFSET_FROM_ASSOCIATION + multiplicityWidth;
                y += TEXT_OFFSET_FROM_CLASSIFIER + keySelectionSize.getY();
                break;
            case TOP:
                textView.setAnchor(PositionAnchor.LOWER_LEFT);
                x += TEXT_OFFSET_FROM_ASSOCIATION + multiplicityWidth;
                y -= TEXT_OFFSET_FROM_CLASSIFIER + keySelectionSize.getY();
                break;
            case LEFT:
                textView.setAnchor(PositionAnchor.UPPER_RIGHT);
                x -= TEXT_OFFSET_FROM_CLASSIFIER + multiplicityWidth + keySelectionSize.getX();
                y += TEXT_OFFSET_FROM_ASSOCIATION;
                break;
            case RIGHT:
                textView.setAnchor(PositionAnchor.UPPER_LEFT);
                x += TEXT_OFFSET_FROM_CLASSIFIER + multiplicityWidth + keySelectionSize.getX();
                y += TEXT_OFFSET_FROM_ASSOCIATION;
                break;
        }

        textView.setPositionRelativeToParent(new Vector3D(x, y));
    }

    /**
     * Moves the multiplicity to the proper position.
     *
     * @param textView the view of the multiplicity
     * @param currentPosition the current position of the multiplicity
     * @param position the side of the association end on the attached classifier view
     */
    @SuppressWarnings("static-method")
    private void moveKeySelection(TextView textView, Vector3D currentPosition, Position position) {
        float x = currentPosition.getX();
        float y = currentPosition.getY();

        // gladly changing the anchor of the text area is sufficient in most cases
        switch (position) {
            case BOTTOM:
                textView.setAnchor(PositionAnchor.UPPER_LEFT);
                x -= textView.getWidth() / 2;
                break;
            case TOP:
                textView.setAnchor(PositionAnchor.LOWER_LEFT);
                x -= textView.getWidth() / 2;
                break;
            case LEFT:
                textView.setAnchor(PositionAnchor.UPPER_RIGHT);
                y -= textView.getHeight() / 2;
                break;
            case RIGHT:
                textView.setAnchor(PositionAnchor.UPPER_LEFT);
                y -= textView.getHeight() / 2;
                break;
        }

        textView.setPositionRelativeToParent(new Vector3D(x, y));
    }

    /**
     * Creates a view for the role name.
     *
     * @param associationEnd the end a role name view to create for
     * @return a view for the role name
     */
    private TextView createRoleNameView(AssociationEnd associationEnd) {
        TextView roleName = new TextView(associationEnd, CorePackage.Literals.CORE_NAMED_ELEMENT__NAME);
        // In rare cases the role name could be empty (issue #72).
        roleName.setPlaceholderText(Strings.PH_ENTER_ROLE_NAME);

        ITextViewHandler roleNameHandler = HandlerFactory.INSTANCE.getAssociationRoleNameHandler();
        registerTextViewProcessors(roleName, roleNameHandler, true);

        roleName.setFont(Fonts.getSmallFontByColor(drawColor));
        roleName.setUnderlined(associationEnd.isStatic());
        roleName.setBufferSize(Cardinal.SOUTH, 0);
        addChild(roleName);

        return roleName;
    }

    /**
     * Creates a view for the multiplicity.
     *
     * @param associationEnd the end a multiplicity view to create for
     * @return a view for the multiplicity
     */
    private MultiplicityTextView createMultiplicityView(AssociationEnd associationEnd) {
        MultiplicityTextView multiplicity = new MultiplicityTextView(associationEnd);

        ITextViewHandler multiplicityHandler = HandlerFactory.INSTANCE.getAssociationMultiplicityHandler();
        registerTextViewProcessors(multiplicity, multiplicityHandler, false);

        multiplicity.setFont(Fonts.getSmallFontByColor(drawColor));
        multiplicity.setBufferSize(Cardinal.SOUTH, 0);
        addChild(multiplicity);

        return multiplicity;
    }

    /**
     * Creates a view for the feature selection.
     *
     * @param associationEnd the end a feature selection view to create for
     * @return a view for the feature selection
     */
    private TextView createFeatureSelectionView(AssociationEnd associationEnd) {
        TextView featureSelection = new TextView(associationEnd,
                RamPackage.Literals.ASSOCIATION_END__FEATURE_SELECTION);
        featureSelection.setPlaceholderText(Strings.PH_SELECT_ASSOCIATIONEND_FEATURE);

        ITextViewHandler featureSelectionHandler = HandlerFactory.INSTANCE.getAssociationFeatureSelectionHandler();
        registerTextViewProcessors(featureSelection, featureSelectionHandler, false);

        featureSelection.setFont(Fonts.getSmallFontByColor(drawColor));
        featureSelection.setPlaceholderFont(Fonts.ASSOCIATION_PLACEHOLDER_FONT);
        featureSelection.setBufferSize(Cardinal.SOUTH, 0);
        addChild(featureSelection);

        return featureSelection;
    }

    /**
     * Creates a view for the key selection.
     *
     * @param keyMapping The key mapping to give to the text view
     * @return a view for the key selection
     */
    private TextView createKeySelectionView(ClassifierMapping keyMapping) {
        EMFEditUtil.addListenerFor(keyMapping, this);

        TextView keySelection = new TextView(keyMapping, CorePackage.Literals.CORE_MAPPING__TO, false);
        keySelection.setPlaceholderText(Strings.PH_KEY);

        ITextViewHandler keySelectionHandler = new TextViewHandler() {
            @Override
            protected void setValue(EObject data, EStructuralFeature feature, Object value) {
                ControllerFactory.INSTANCE.getAssociationController().setKeySelection((ClassifierMapping) data, value);
            }
        };
        registerTextViewProcessors(keySelection, keySelectionHandler, false);

        keySelection.setNoStroke(false);
        keySelection.setNoFill(false);
        keySelection.setFillColor(Colors.STRUCT_KEY_SELECTION_FILL_COLOR);
        keySelection.setPlaceholderFont(Fonts.ASSOCIATION_PLACEHOLDER_FONT);
        keySelection.setFont(Fonts.getSmallFontByColor(drawColor));
        keySelection.setBufferSize(Cardinal.SOUTH, 0);
        addChild(keySelection);

        return keySelection;
    }

    @Override
    public void destroy() {
        EMFEditUtil.removeListenerFor(getFromEnd().getModel(), this);
        super.destroy();
    }

    /**
     * Draws the correct polygon for an association end at the given location and position on the class.
     *
     * @param end the end to draw
     * @param x the x position of the end
     * @param y the y position of the end
     * @param position the side of the end on the view
     */
    private void drawAssociationEnd(AssociationEnd end, float x, float y, Position position) {
        ReferenceType type = end.getReferenceType();
        MTPolygon polygon = null;
        MTPolygon polygon2 = null;

        switch (type) {
            case AGGREGATION:
                polygon = new CompositionPolygon(x, y, drawColor);
                rotateShape(polygon, x, y, position);
                polygon.setFillColor(backgroundColor);

                if (!end.isNavigable()) {
                    polygon2 = getShiftedRotatedArrowPolygon(x, y, drawColor, position);

                }
                break;
            case COMPOSITION:
                polygon = new CompositionPolygon(x, y, drawColor);
                rotateShape(polygon, x, y, position);

                if (!end.isNavigable()) {
                    polygon2 = getShiftedRotatedArrowPolygon(x, y, drawColor, position);

                }

                break;
            case REGULAR:
                if (!end.isNavigable()) {
                    polygon = new ArrowPolygon(x, y, drawColor);
                    rotateShape(polygon, x, y, position);
                }
                break;
        }

        if (polygon != null) {
            addChild(polygon);
        }

        if (polygon2 != null) {
            addChild(polygon2);
        }
    }

    /**
     * Draws the given association end based on all information contained inside.
     *
     * @param ramEnd the end to draw
     */
    private void drawAssociationEnd(RamEnd<AssociationEnd, ClassifierView<?>> ramEnd) {
        drawAssociationEnd(ramEnd.getModel(), ramEnd.getLocation().getX(),
                ramEnd.getLocation().getY(), ramEnd.getPosition());
    }

    /**
     * This function is used to draw a navigability arrow head along with the aggregation/composition shape. It happens
     * when the reference type is aggregation or composition and navigability is negative.
     *
     * @param x the x position
     * @param y the y position
     * @param drawColor the color
     * @param position the current position
     * @return a new polygon that is properly positioned
     */
    private MTPolygon getShiftedRotatedArrowPolygon(float x, float y, MTColor drawColor, Position position) {
        MTPolygon polygon = null;

        Vector3D rotationPoint = null;
        // rotate depending on position
        switch (position) {
            case BOTTOM:
                polygon = new ArrowPolygon(x, y + ARROW_SIZE, drawColor);
                rotationPoint = new Vector3D(x, y + ARROW_SIZE, 1);
                polygon.rotateZ(rotationPoint, ROTATION);
                break;
            case TOP:
                polygon = new ArrowPolygon(x, y - ARROW_SIZE, drawColor);
                rotationPoint = new Vector3D(x, y - ARROW_SIZE, 1);
                polygon.rotateZ(rotationPoint, -ROTATION);
                break;

            case LEFT:
                polygon = new ArrowPolygon(x - ARROW_SIZE, y, drawColor);
                rotationPoint = new Vector3D(x - ARROW_SIZE, y, 1);
                polygon.rotateZ(rotationPoint, 2 * ROTATION);
                break;

            case RIGHT:
                polygon = new ArrowPolygon(x + ARROW_SIZE, y, drawColor);
                // rotationPoint = new Vector3D(x + 20, y, 1);
                break;
        }

        return polygon;
    }

    @Override
    public void notifyChanged(Notification notification) {
        if (notification.getNotifier() == getFromEnd().getModel()
                || notification.getNotifier() == getToEnd().getModel()
                || notification.getNotifier() instanceof ClassifierMapping) {
            if (notification.getFeature() == RamPackage.Literals.STRUCTURAL_FEATURE__STATIC) {
                if (notification.getEventType() == Notification.SET) {
                    boolean newValue = notification.getNewBooleanValue();
                    if (notification.getNotifier() == getFromEnd().getModel()) {
                        fromEndRolename.setUnderlined(newValue);
                    } else {
                        toEndRolename.setUnderlined(newValue);
                    }
                }
            } else if (notification.getFeature() == RamPackage.Literals.PROPERTY__UPPER_BOUND) {
                if (notification.getOldIntValue() == 1
                        && (notification.getNewIntValue() > 1 || notification.getNewIntValue() == -1)) {
                    if (notification.getNotifier() == getFromEnd().getModel()) {
                        if (fromEndFeatureSelection == null) {
                            fromEndFeatureSelection =
                                    createFeatureSelectionView((AssociationEnd) notification.getNotifier());
                        }
                    } else {
                        if (toEndFeatureSelection == null) {
                            toEndFeatureSelection =
                                    createFeatureSelectionView((AssociationEnd) notification.getNotifier());
                        }
                    }
                } else if ((notification.getOldIntValue() > 1 || notification.getOldIntValue() == -1)
                        && notification.getNewIntValue() == 1) {
                    if (notification.getNotifier() == getFromEnd().getModel()) {
                        destroyFeatureSelection(FROM);
                        destroyKeySelection(FROM);
                    } else if (notification.getNotifier() == getToEnd().getModel()) {
                        destroyFeatureSelection(TO);
                        destroyKeySelection(TO);
                    }
                    shouldUpdate();
                }
            } else if (notification.getFeature() == RamPackage.Literals.ASSOCIATION_END__FEATURE_SELECTION) {
                AssociationEnd associationEnd = (AssociationEnd) notification.getNotifier();
                if (associationEnd == getFromEnd().getModel()) {
                    destroyKeySelection(FROM);
                    if (hasKeyIndexedSelection(getFromEnd().getModel())) {
                        fromKeyMapping = getMapping(associationEnd, AssociationConstants.KEY_CLASS_NAME);
                        fromEndKeySelection = createKeySelectionView(fromKeyMapping);
                    }
                } else if (associationEnd == getToEnd().getModel()) {
                    destroyKeySelection(TO);
                    if (hasKeyIndexedSelection(getToEnd().getModel())) {
                        toKeyMapping = getMapping(associationEnd, AssociationConstants.KEY_CLASS_NAME);
                        toEndKeySelection = createKeySelectionView(toKeyMapping);
                    }
                }
                shouldUpdate();
            } else if (notification.getEventType() == Notification.SET) {
                shouldUpdate();
            }
        }
    }

    /**
     * Adds listeners to the textview parameter.
     *
     * @param textView The field where the listeners occurred
     * @param handler The text view handler to add
     * @param enabledTapAndHold If the tapAndHold event is enabled for this textview
     */
    private void registerTextViewProcessors(TextView textView, ITextViewHandler handler, boolean enabledTapAndHold) {

        textView.registerTapProcessor(handler);

        if (enabledTapAndHold) {
            textView.registerInputProcessor(new TapAndHoldProcessor(RamApp.getApplication(),
                    Constants.TAP_AND_HOLD_DURATION));
            textView.addGestureListener(TapAndHoldProcessor.class,
                    new TapAndHoldVisualizer(RamApp.getApplication(), this));
            textView.addGestureListener(TapAndHoldProcessor.class, handler);
        }
    }

    /**
     * Redraws the lines between the to and from {@link AssociationEnd}s.
     */
    @Override
    protected void update() {
        Vector3D fromEndOffset = getKeySelectionOffset(fromEndKeySelection, getFromEnd());
        Vector3D toEndOffset = getKeySelectionOffset(toEndKeySelection, getToEnd());
        drawAllLines(fromEndOffset.getX(), fromEndOffset.getY(), toEndOffset.getX(), toEndOffset.getY());

        drawAssociationEnd(getFromEnd());
        drawAssociationEnd(getToEnd());

        // create texts if they don't exist yet
        createTexts(getFromEnd(), getToEnd());

        // update the texts position
        moveTexts(getFromEnd(), fromEndRolename, fromEndMultiplicity, fromEndFeatureSelection, toEndKeySelection);
        moveTexts(getToEnd(), toEndRolename, toEndMultiplicity, toEndFeatureSelection, fromEndKeySelection);

        // update visibility depending on navigable
        boolean fromEndNavigable = getFromEnd().getModel().isNavigable();
        fromEndRolename.setVisible(fromEndNavigable);
        fromEndMultiplicity.setVisible(fromEndNavigable);
        if (fromEndFeatureSelection != null) {
            fromEndFeatureSelection.setVisible(fromEndNavigable);
        }
        if (fromEndKeySelection != null) {
            fromEndKeySelection.setVisible(fromEndNavigable);
        }

        boolean toEndNavigable = getToEnd().getModel().isNavigable();
        toEndRolename.setVisible(toEndNavigable);
        toEndMultiplicity.setVisible(toEndNavigable);
        if (toEndFeatureSelection != null) {
            toEndFeatureSelection.setVisible(toEndNavigable);
        }
        if (toEndKeySelection != null) {
            toEndKeySelection.setVisible(toEndNavigable);
        }
    }

    /**
     * Destroy feature selection.
     *
     * @param side The side (FROM or TO) to delete the selections for
     */
    private void destroyFeatureSelection(String side) {
        if (FROM.equals(side)) {
            if (fromEndFeatureSelection != null) {
                fromEndFeatureSelection.destroy();
                fromEndFeatureSelection = null;
            }
        } else if (TO.equals(side)) {
            if (toEndFeatureSelection != null) {
                toEndFeatureSelection.destroy();
                toEndFeatureSelection = null;
            }
        }
    }

    /**
     * Destroy key selection.
     *
     * @param side The side (FROM or TO) to delete the selections for
     */
    private void destroyKeySelection(String side) {
        if (FROM.equals(side)) {
            if (fromEndKeySelection != null) {
                if (fromKeyMapping != null) {
                    EMFEditUtil.removeListenerFor(fromKeyMapping, this);
                    fromKeyMapping = null;
                }
                fromEndKeySelection.destroy();
                fromEndKeySelection = null;
            }
        } else if (TO.equals(side)) {
            if (toEndKeySelection != null) {
                if (toKeyMapping != null) {
                    EMFEditUtil.removeListenerFor(toKeyMapping, this);
                    toKeyMapping = null;
                }
                toEndKeySelection.destroy();
                toEndKeySelection = null;
            }
        }
    }

    /**
     * Checks if the feature selections requires a key selection.
     *
     * @param associationEnd the ram end that might require a key selection
     * @return true if the ram end needs a key selection
     */
    private static boolean hasKeyIndexedSelection(AssociationEnd associationEnd) {
        if (associationEnd.getFeatureSelection() != null
                && associationEnd.getFeatureSelection().getReuse() != null
                && associationEnd.getFeatureSelection().getReuse().getSelectedConfiguration() != null) {
            for (COREFeature feature : associationEnd.getFeatureSelection()
                    .getReuse().getSelectedConfiguration().getSelected()) {
                String name = feature.getName();
                if ("KeyIndexed".equals(name) || "TreeMap".equals(name) || "HashMap".equals(name)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * The offset of the ram end if there is a key selection.
     *
     * @param textView The key selection text view to calculate the offset for
     * @param ramEnd The ram end for which there might be an offset
     * @return the of the offset
     */
    private static Vector3D getKeySelectionOffset(TextView textView, RamEnd<AssociationEnd, ClassifierView<?>> ramEnd) {
        Vector3D offset = new Vector3D();
        if (textView == null) {
            offset.setX(0);
            offset.setY(0);
        } else {
            switch (ramEnd.getPosition()) {
                case BOTTOM:
                    offset.setX(0);
                    offset.setY(textView.getHeight());
                    break;
                case TOP:
                    offset.setX(0);
                    offset.setY(-textView.getHeight());
                    break;
                case LEFT:
                    offset.setX(-textView.getWidth());
                    offset.setY(0);
                    break;
                case RIGHT:
                    offset.setX(textView.getWidth());
                    offset.setY(0);
                    break;
            }
        }
        return offset;
    }

    /**
     * Get the key classifier mapping from the association feature selection.
     *
     * @param associationEnd The associationEnd to look for the instantiation
     * @param mappingName The name of the mapping to get
     * @return The key classifier mapping
     */
    public ClassifierMapping getMapping(AssociationEnd associationEnd, String mappingName) {
        Instantiation instantiation = (Instantiation) associationEnd.getFeatureSelection().getCompositions().get(0);
        for (ClassifierMapping classifierMapping : instantiation.getMappings()) {
            if (mappingName.equals(classifierMapping.getFrom().getName())) {
                return classifierMapping;
            }
        }
        return null;
    }
}
