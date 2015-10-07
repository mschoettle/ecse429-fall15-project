package ca.mcgill.sel.ram.ui.views.containers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.mt4j.input.gestureAction.TapAndHoldVisualizer;
import org.mt4j.input.inputProcessors.componentProcessors.tapAndHoldProcessor.TapAndHoldEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapAndHoldProcessor.TapAndHoldProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapProcessor;
import org.mt4j.util.MTColor;
import org.mt4j.util.math.Vector3D;

import processing.core.PImage;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamAnimatedImage;
import ca.mcgill.sel.ram.ui.components.RamImageComponent;
import ca.mcgill.sel.ram.ui.components.RamImageTextComponent;
import ca.mcgill.sel.ram.ui.components.RamListComponent.Namer;
import ca.mcgill.sel.ram.ui.components.RamPanelComponent;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.components.RamSelectorComponent;
import ca.mcgill.sel.ram.ui.components.RamSpacerComponent;
import ca.mcgill.sel.ram.ui.components.RamTextComponent;
import ca.mcgill.sel.ram.ui.components.listeners.AbstractDefaultRamSelectorListener;
import ca.mcgill.sel.ram.ui.components.listeners.RamSelectorListener;
import ca.mcgill.sel.ram.ui.events.listeners.ITapAndHoldListener;
import ca.mcgill.sel.ram.ui.events.listeners.ITapListener;
import ca.mcgill.sel.ram.ui.layouts.HorizontalLayoutVerticallyCentered;
import ca.mcgill.sel.ram.ui.layouts.VerticalLayout;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.utils.Constants;
import ca.mcgill.sel.ram.ui.utils.Fonts;
import ca.mcgill.sel.ram.ui.utils.GraphicalUpdater;
import ca.mcgill.sel.ram.ui.utils.Icons;
import ca.mcgill.sel.ram.ui.utils.Strings;
import ca.mcgill.sel.ram.ui.utils.validation.ValidationManager;
import ca.mcgill.sel.ram.ui.utils.validation.ValidationManager.AspectValidationThreadState;
import ca.mcgill.sel.ram.ui.utils.validation.ValidationManagerListener;
import ca.mcgill.sel.ram.ui.views.handler.BaseHandler;
import ca.mcgill.sel.ram.validator.ValidationError;
import ca.mcgill.sel.ram.validator.ValidationRuleErrorDescription.ValidationErrorType;
import ca.mcgill.sel.ram.validator.quickfixes.Quickfix;

/**
 * Validation view for a aspect. Displays the number and the list of errors, warnings and information. Each element of
 * the list is selectable to highlight it in the board and holdable to show possible quickfixes.
 *
 * @author lmartellotto
 */
public class ValidationView extends RamPanelComponent implements ValidationManagerListener {

    private static final int ICON_SIZE = 25;
    private static final int SMALL_ICON_SIZE = 20;

    private static final int MAX_NUMBER_ELEMENTS = 6;
    private static final int INDEX_ICON_VALIDATION_STATE = 4;

    private RamImageTextComponent errorCount;
    private RamImageTextComponent warningCount;
    private RamImageTextComponent infoCount;
    private RamImageComponent validationNothing;
    private RamSpacerComponent validationToCheck;
    private RamAnimatedImage validationChecking;

    private List<ValidationError> listErrors;

    private RamSelectorComponent<ValidationError> selector;
    private Set<ValidationErrorType> filtersSelector;
    private InternalQuickfixSelector currentQuickfixOpened;
    private ValidationError currentSelected;

    private GraphicalUpdater gu;

    /**
     * Basic Constructor.
     * 
     * @param g The GUI updater.
     */
    public ValidationView(GraphicalUpdater g) {
        // CHECKSTYLE:IGNORE MagicNumber: Radius of the arc of corners
        super(5);

        gu = g;
        setLabel(Strings.LABEL_VALIDATION_VIEW);

        // Filters of types for selector. By default, all are selected.
        filtersSelector = new HashSet<ValidationErrorType>();
        filtersSelector.add(ValidationErrorType.ERROR);
        filtersSelector.add(ValidationErrorType.WARNING);
        filtersSelector.add(ValidationErrorType.INFO);

        setLayout(new ValidatorViewLayout());

        // Buttons on the top of the window
        errorCount = new RamImageTextComponent(Icons.ICON_CHECK_ERROR, Fonts.VALIDATION_VIEW_FONT);
        errorCount.setImageSize(ICON_SIZE, ICON_SIZE);
        errorCount.setText(String.valueOf(0));
        errorCount.addGestureListener(TapProcessor.class, new InternalSortingByErrorHandler(ValidationErrorType.ERROR));
        errorCount.registerInputProcessor(new TapProcessor(RamApp.getApplication()));
        addHeaderComponent(errorCount);

        warningCount = new RamImageTextComponent(Icons.ICON_CHECK_WARNING, Fonts.VALIDATION_VIEW_FONT);
        warningCount.setImageSize(ICON_SIZE, ICON_SIZE);
        warningCount.setText(String.valueOf(0));
        warningCount.addGestureListener(TapProcessor.class, new InternalSortingByErrorHandler(
                ValidationErrorType.WARNING));
        warningCount.registerInputProcessor(new TapProcessor(RamApp.getApplication()));
        addHeaderComponent(warningCount);

        infoCount = new RamImageTextComponent(Icons.ICON_CHECK_INFO, Fonts.VALIDATION_VIEW_FONT);
        infoCount.setImageSize(ICON_SIZE, ICON_SIZE);
        infoCount.setText(String.valueOf(0));
        infoCount.addGestureListener(TapProcessor.class, new InternalSortingByErrorHandler(ValidationErrorType.INFO));
        infoCount.registerInputProcessor(new TapProcessor(RamApp.getApplication()));
        addHeaderComponent(infoCount);

        validationNothing = new RamImageComponent(Icons.ICON_CHECK_OK, Colors.VALIDATION_TRANSPARENT);
        validationNothing.setSizeLocal(ICON_SIZE, ICON_SIZE);
        validationNothing.setPickable(false);
        addHeaderComponent(validationNothing);

        final int timeForAnimatedImage = 300;
        validationChecking = new RamAnimatedImage(Icons.ICON_WHEEL, timeForAnimatedImage);
        validationChecking.setImageSize(ICON_SIZE, ICON_SIZE);
        validationChecking.setPickable(false);

        validationToCheck = new RamSpacerComponent(ICON_SIZE, ICON_SIZE);
        validationToCheck.setPickable(false);

        // Selector
        selector = new RamSelectorComponent<ValidationError>(new ArrayList<ValidationError>());
        selector.showCloseButton(false);
        selector.showTextField(false);
        selector.setNoFill(true);
        selector.setNoStroke(true);
        selector.setDragAvailableOnSelector(false);
        selector.setNamer(new InternalValidationErrorNamer());
        selector.registerListener(new ValidatorViewSelectorListener());
        selector.setScrollButtonColor(Colors.TRANSPARENT_DARK_GREY);
        selector.setMaxNumberOfElements(MAX_NUMBER_ELEMENTS);
        setContent(selector);
        showContent(false);

        updateErrorList(new HashMap<EObject, List<ValidationError>>());

        currentQuickfixOpened = null;
        currentSelected = null;

        updateLayout();

        setVerticalAlign(VerticalStick.BOTTOM);
        setHorizontalAlign(HorizontalStick.RIGHT);

        // Puts in the right bottom corner
        setPositionGlobal(new Vector3D(getRenderer().getWidth(), getRenderer().getHeight()));
    }

    /**
     * Returns the color of the element depends on the error type.
     * 
     * @param type The error type
     * @return The color.
     */
    private static MTColor getColorElement(ValidationErrorType type) {
        MTColor color;
        switch (type) {
            case ERROR:
                color = Colors.VALIDATION_ERROR_FILL_COLOR;
                break;
            case WARNING:
                color = Colors.VALIDATION_WARNING_FILL_COLOR;
                break;
            case INFO:
                color = Colors.VALIDATION_INFO_FILL_COLOR;
                break;
            default:
                color = Colors.VALIDATION_ERROR_FILL_COLOR;
        }
        return color;
    }

    /**
     * Updates the background and the call of listeners depending on the selected element.
     * 
     * @param e The selected element.
     */
    private void updateElementSelected(ValidationError e) {

        // Removes the opened quickfix
        if (currentQuickfixOpened != null && e != currentQuickfixOpened.getValidationError()) {
            currentQuickfixOpened.destroy();
            currentQuickfixOpened = null;
        }

        // If the current selected element is not null, we have to unselect it
        if (currentSelected != null) {

            // If the selector contains the selected element
            if (selector.getElementMap().containsKey(currentSelected)) {
                selector.getElementMap().get(currentSelected).setFillColor(Colors.DEFAULT_ELEMENT_FILL_COLOR);
            }
            gu.validationUserEvent(currentSelected.getTarget(), null);
            currentSelected = null;
        }

        // If the new selected element is not null, we have to select it
        if (e != null) {
            selector.getElementMap().get(e).setFillColor(getColorElement(e.getSeverity()));
            gu.validationUserEvent(e.getTarget(), e.getSeverity());
            currentSelected = e;
        }

    }

    /**
     * Updates the list of errors.
     * 
     * @param errors The list of errors.
     */
    private void updateErrorList(Map<EObject, List<ValidationError>> errors) {

        ArrayList<ValidationError> errs = new ArrayList<ValidationError>();
        for (EObject obj : errors.keySet()) {
            for (ValidationError e : errors.get(obj)) {
                errs.add(e);
            }
        }

        listErrors = errs;
        sortListDependingOnFilters();
    }

    /**
     * Sorts the list of errors depending on the current selected filters.
     */
    private void sortListDependingOnFilters() {

        ArrayList<ValidationError> errs = new ArrayList<ValidationError>();
        for (ValidationError err : listErrors) {
            if (filtersSelector.contains(err.getSeverity())) {
                errs.add(err);
            }
        }

        selector.setElements(errs);
        selector.updateLayout();

        // If the selector again contains the element.
        if (currentSelected != null && selector.getElementMap().containsKey(currentSelected)) {
            updateElementSelected(currentSelected);
        } else {
            updateElementSelected(null);
        }
    }

    @Override
    public void updateValidationErrors(Map<EObject, List<ValidationError>> errors,
            Map<EObject, List<ValidationError>> errorsAdded, Map<EObject, List<ValidationError>> errorsDismissed) {

        int errorC = 0;
        int warningC = 0;
        int infoC = 0;

        for (EObject o : errors.keySet()) {
            for (ValidationError e : errors.get(o)) {
                switch (e.getSeverity()) {
                    case ERROR:
                        errorC++;
                        break;
                    case WARNING:
                        warningC++;
                        break;
                    case INFO:
                        infoC++;
                        break;
                }
            }
        }
        errorCount.setText(String.valueOf(errorC));
        warningCount.setText(String.valueOf(warningC));
        infoCount.setText(String.valueOf(infoC));

        updateErrorList(errors);
        updateLayout();
        setPositionGlobal(getCorrectPosition(this, getX(), getY(), true));
    }

    @Override
    public void validationState(AspectValidationThreadState state) {

        // Removes icon for validation state
        header.removeChild(INDEX_ICON_VALIDATION_STATE, false);

        if (state == AspectValidationThreadState.CHECKING) {
            if (currentQuickfixOpened != null) {
                currentQuickfixOpened.destroy();
                currentQuickfixOpened = null;
            }
            addHeaderComponent(INDEX_ICON_VALIDATION_STATE, validationChecking, true);
        }

        if (state == AspectValidationThreadState.WAITING_TO_CHECK) {
            addHeaderComponent(INDEX_ICON_VALIDATION_STATE, validationToCheck, true);
        }

        if (state == AspectValidationThreadState.WAITING_TO_NOTHING) {
            addHeaderComponent(INDEX_ICON_VALIDATION_STATE, validationNothing, true);
        }

        setPositionGlobal(getCorrectPosition(this, getX(), getY(), true));
    }

    @Override
    public void destroy() {

        super.destroy();
        updateElementSelected(null);

        if (getController() != null && getController() instanceof ValidationManager) {
            ValidationManager controller = (ValidationManager) getController();
            controller.destroy();
        }
    }

    /**
     * Handler for buttons in the top of the window, for sorting the selector depending on the type of the error.
     */
    private class InternalSortingByErrorHandler extends BaseHandler implements ITapListener {

        private ValidationErrorType type;

        /**
         * Constructor.
         * 
         * @param t The kind of error.
         */
        public InternalSortingByErrorHandler(ValidationErrorType t) {
            type = t;
        }

        @Override
        public boolean processTapEvent(TapEvent tapEvent) {
            if (tapEvent.isTapped()) {

                // Updates the color
                RamImageTextComponent comp = (RamImageTextComponent) tapEvent.getTarget();
                if (filtersSelector.contains(type)) {
                    filtersSelector.remove(type);
                    comp.setFillColor(Colors.VALIDATION_HIGH_TRANSPARENT);
                } else {
                    filtersSelector.add(type);
                    comp.setFillColor(Colors.VALIDATION_TRANSPARENT);
                }

                // Updates the list
                sortListDependingOnFilters();

                return true;
            }
            return false;
        }

    }

    /**
     * A namer that is capable of displaying {@link ValidationError}. Just the error message is displayed. The searching
     * and sorting methods use more completed string : > ['type' 'name'] 'error message'
     */
    private class InternalValidationErrorNamer implements Namer<ValidationError> {

        private static final String PATTERN = "[%s %s] %s";

        @Override
        public RamRectangleComponent getDisplayComponent(ValidationError e) {
            RamRectangleComponent view = new RamRectangleComponent(new HorizontalLayoutVerticallyCentered());
            view.registerInputProcessor(new TapAndHoldProcessor(RamApp.getApplication(),
                    Constants.TAP_AND_HOLD_DURATION));
            view.addGestureListener(TapAndHoldProcessor.class, new InternalValidationErrorHandler(e));
            view.addGestureListener(TapAndHoldProcessor.class, new TapAndHoldVisualizer(RamApp.getApplication(),
                    getParent()));

            // Removes quotes of the message
            String message = e.getMessage();
            if (message.startsWith("'")) {
                message = message.substring(1);
            }
            if (message.endsWith("'")) {
                message = message.substring(0, message.length() - 1);
            }

            RamTextComponent text = new RamTextComponent(String.format("%s", message));
            text.setPickable(false);

            PImage ic;
            switch (e.getSeverity()) {
                case ERROR:
                    ic = Icons.ICON_CHECK_ERROR;
                    break;
                case WARNING:
                    ic = Icons.ICON_CHECK_WARNING;
                    break;
                case INFO:
                    ic = Icons.ICON_CHECK_INFO;
                    break;
                default:
                    ic = Icons.ICON_CHECK_ERROR;
            }
            RamImageComponent img =
                    new RamImageComponent(ic, Colors.VALIDATION_ICON_COLOR, SMALL_ICON_SIZE, SMALL_ICON_SIZE);
            img.setPickable(false);

            // Adds spaces in left and right
            final int space = 5;
            view.setBufferSize(Cardinal.WEST, space);
            view.setBufferSize(Cardinal.EAST, space);

            view.addChild(img);
            view.addChild(text);

            view.setNoFill(false);
            view.setFillColor(Colors.DEFAULT_ELEMENT_FILL_COLOR);
            view.setNoStroke(false);
            return view;
        }

        @Override
        public String getSortingName(ValidationError e) {
            String result = null;

            result = String.format(PATTERN, e.getTarget(), e.getName(), e.getMessage());
            // We want elements with higher severity to be displayed first.
            int sortingIndex = ValidationErrorType.values().length - e.getSeverity().getIndex();
            return sortingIndex + result;
        }

        @Override
        public String getSearchingName(ValidationError e) {
            String result = null;

            result = String.format(PATTERN, e.getTarget(), e.getName(), e.getMessage());
            return result;
        }
    }

    /**
     * Forbids the close of the selector (do nothing). Selects or unselects the selected element.
     */
    private class ValidatorViewSelectorListener implements RamSelectorListener<ValidationError> {

        @Override
        public void closeSelector(RamSelectorComponent<ValidationError> s) {
            // Do nothing, it is never closed.
        }

        @Override
        public void elementSelected(RamSelectorComponent<ValidationError> s, ValidationError e) {

            // If the opened quickfixes selector concerns the current selected error,
            // we don't unselect the error
            if (currentQuickfixOpened != null && currentQuickfixOpened.getValidationError() == e
                    && currentSelected == e) {
                return;
            }

            // If the user re-click on a same element, the element is unselected.
            if (currentSelected == e) {
                updateElementSelected(null);
            } else {
                updateElementSelected(e);
            }
        }

        @Override
        public void elementDoubleClicked(RamSelectorComponent<ValidationError> ramSelector, ValidationError element) {
        }

        @Override
        public void elementHeld(RamSelectorComponent<ValidationError> ramSelector, ValidationError element) {
        }
    }

    /**
     * Listens TapAndHoldEvent: Displays a selector component for quickfixes.
     */
    private class InternalValidationErrorHandler extends BaseHandler implements ITapAndHoldListener {
        private ValidationError error;

        /**
         * Initializes the validation error for which the quickfixes will be display.
         * 
         * @param e the error
         */
        public InternalValidationErrorHandler(ValidationError e) {
            super();
            error = e;
        }

        @Override
        public boolean processTapAndHoldEvent(TapAndHoldEvent tapAndHoldEvent) {
            if (tapAndHoldEvent instanceof TapAndHoldEvent) {
                TapAndHoldEvent te = tapAndHoldEvent;

                if (te.isHoldComplete() && error.getQuickfixes().size() > 0) {
                    InternalQuickfixSelector selectorQf = new InternalQuickfixSelector(error);

                    Vector3D pos = te.getLocationOnScreen();
                    RamApp.getActiveAspectScene().addComponent(selectorQf,
                            getCorrectPosition(selectorQf, pos.x, pos.y, false));

                    // Only one quickfix selector opened at the same time.
                    if (currentQuickfixOpened != null) {
                        currentQuickfixOpened.destroy();
                    }
                    currentQuickfixOpened = selectorQf;
                }
                return true;
            }
            return false;
        }

    }

    /**
     * Builds a {@link RamSelectorComponent} for display {@link Quickfix} elements. Sets a {@link InternalQuickfixNamer}
     * as namer and saves the {@link ValidationError} for which these quickfixes are displayed.
     */
    private class InternalQuickfixSelector extends RamSelectorComponent<Quickfix> {

        private ValidationError error;

        /**
         * Sets a {@link InternalQuickfixNamer} as namer and saves the {@link ValidationError} for which these
         * quickfixes are displayed.
         * 
         * @param e the error
         */
        public InternalQuickfixSelector(ValidationError e) {
            super(new InternalQuickfixNamer());
            showTextField(false);
            error = e;

            registerListener(new AbstractDefaultRamSelectorListener<Quickfix>() {

                @Override
                public void elementSelected(RamSelectorComponent<Quickfix> s, Quickfix element) {
                    element.quickfix();
                    s.destroy();
                }
            });

            setElements(error.getQuickfixes());
        }

        /**
         * Gets the {@link ValidationError} for which these quickfixes are displayed.
         * 
         * @return the error.
         */
        public ValidationError getValidationError() {
            return error;
        }
    }

    /**
     * A namer that is capable of displaying {@link Quickfix}. The label of the quickfix is used for both displaying,
     * sorting and searching methods.
     */
    private class InternalQuickfixNamer implements Namer<Quickfix> {

        @Override
        public RamRectangleComponent getDisplayComponent(Quickfix element) {
            RamRectangleComponent view = new RamRectangleComponent(new HorizontalLayoutVerticallyCentered());
            RamTextComponent text = new RamTextComponent(element.getLabel());
            view.addChild(text);
            view.setNoFill(false);
            view.setFillColor(Colors.DEFAULT_ELEMENT_FILL_COLOR);
            view.setNoStroke(false);
            return view;
        }

        @Override
        public String getSortingName(Quickfix element) {
            return element.getLabel();
        }

        @Override
        public String getSearchingName(Quickfix element) {
            return element.getLabel();
        }

    }

    /**
     * Updates position depends on the size of the selector after scrolling.
     */
    private class ValidatorViewLayout extends VerticalLayout {

        /**
         * Basic constructor.
         */
        public ValidatorViewLayout() {
            super(2);
        }

        @Override
        public void layout(RamRectangleComponent component, LayoutUpdatePhase updatePhase) {
            super.layout(component, updatePhase);
            setPositionGlobal(getCorrectPosition(ValidationView.this, getX(), getY(), true));
        }
    }

}
