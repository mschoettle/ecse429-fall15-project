package ca.mcgill.sel.ram.ui.views.structural.handler.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.mt4j.components.MTComponent;
import org.mt4j.components.PickResult;
import org.mt4j.components.PickResult.PickEntry;
import org.mt4j.input.gestureAction.DefaultScaleAction;
import org.mt4j.input.inputProcessors.IGestureEventListener;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragEvent;
import org.mt4j.input.inputProcessors.componentProcessors.scaleProcessor.ScaleEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapAndHoldProcessor.TapAndHoldEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;
import org.mt4j.util.MTColor;
import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.core.COREMapping;
import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.ram.Attribute;
import ca.mcgill.sel.ram.AttributeMapping;
import ca.mcgill.sel.ram.Class;
import ca.mcgill.sel.ram.Classifier;
import ca.mcgill.sel.ram.ClassifierMapping;
import ca.mcgill.sel.ram.Instantiation;
import ca.mcgill.sel.ram.Operation;
import ca.mcgill.sel.ram.OperationMapping;
import ca.mcgill.sel.ram.controller.ControllerFactory;
import ca.mcgill.sel.ram.controller.InstantiationController;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.events.WheelEvent;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.utils.Strings;
import ca.mcgill.sel.ram.ui.views.handler.HandlerFactory;
import ca.mcgill.sel.ram.ui.views.structural.AttributeView;
import ca.mcgill.sel.ram.ui.views.structural.BaseView;
import ca.mcgill.sel.ram.ui.views.structural.ClassView;
import ca.mcgill.sel.ram.ui.views.structural.InstantiationSplitEditingView;
import ca.mcgill.sel.ram.ui.views.structural.OperationView;
import ca.mcgill.sel.ram.ui.views.structural.StructuralDiagramView;

/**
 * Default handler for {@link InstantiationSplitEditingView}.
 * 
 * @author eyildirim
 */
public class InstantiationSplitViewHandler implements IGestureEventListener {

    /**
     * This enum is used to know the type of the element that is tapped on in the lower level aspect.
     */
    public static enum SelectedElementType {
        /**
         * Indicates nothing is selected.
         */
        NO_SELECTION,
        /**
         * Indicates a class is selected.
         */
        CLASS,
        /**
         * Indicates an operation is selected.
         */
        OPERATION,
        /**
         * Indicates an attribute is selected.
         */
        ATTRIBUTE
    }

    /**
     * Inner class to hold selection information after selecting an item for mapping purposes.
     */
    private class Selection {
        private SelectedElementType selectedElementType;
        private OperationView myOperationView;
        private ClassView myClassView;
        private AttributeView myAttributeView;
        private Class fromClass;

        // if we create a mapping ( or multiple mappings for operation and attributes if a class is mapped to more than
        // one class)
        // due to a selection of an element we keep this information to be able to delete the mapping if the selection
        // is cancelled by tapping on the background.
        private List<COREMapping<?>> mappingsCreatedAfterSelection;

        public Selection() {
            selectedElementType = SelectedElementType.NO_SELECTION;

            // create an empty list for the potential auto created mappings.
            mappingsCreatedAfterSelection = new ArrayList<COREMapping<?>>();
        }

        public Class getFromClass() {
            return fromClass;
        }

        public List<COREMapping<?>> getLastStoredMappingsOfLastSelectedElement() {
            return mappingsCreatedAfterSelection;
        }

        public AttributeView getMyAttributeView() {
            return myAttributeView;
        }

        public ClassView getMyClassView() {
            return myClassView;
        }

        public OperationView getMyOperationView() {
            return myOperationView;
        }

        public SelectedElementType getSelectedElementType() {
            return selectedElementType;
        }

        public void resetElementData() {
            selectedElementType = SelectedElementType.NO_SELECTION;
            myOperationView = null;
            myClassView = null;
            mappingsCreatedAfterSelection.clear();
        }

        public void setFromClass(Class fromClass) {
            this.fromClass = fromClass;
        }

        public void setMyAttributeView(AttributeView myAttributeView) {
            this.myAttributeView = myAttributeView;
        }

        public void setMyClassView(ClassView myClassView) {
            this.myClassView = myClassView;
        }

        public void setMyOperationView(OperationView myOperationView) {
            this.myOperationView = myOperationView;
        }

        public void setSelectedElementType(SelectedElementType selectedElementType) {
            this.selectedElementType = selectedElementType;
        }

    }

    private InstantiationController controller = ControllerFactory.INSTANCE.getInstantiationController();

    private InstantiationSplitEditingView myInstantiationSplitEditingView;
    private Instantiation instantiation;
    private StructuralDiagramView lowerAspectStructuralDiagramView;
    private StructuralDiagramView higherAspectStructuralDiagramView;

    // Last selected lower level aspect item for the mapping purposes. It can be an operation,attribute or a class
    private Selection lastSelectedLowerLevelItem;

    // workaround to prevent receiving a tap and tap and hold event at the same time
    private boolean tapAndHoldPerformed;

    /**
     * Constructor.
     * 
     * @param instantiationSplitEditingView this is the split instantiation editing view which this handler is
     *            associated to.
     */
    public InstantiationSplitViewHandler(InstantiationSplitEditingView instantiationSplitEditingView) {
        myInstantiationSplitEditingView = instantiationSplitEditingView;
        instantiation = myInstantiationSplitEditingView.getInstantiation();
        lowerAspectStructuralDiagramView = myInstantiationSplitEditingView.getStructuralDiagramViewOfLowerAspect();
        higherAspectStructuralDiagramView = myInstantiationSplitEditingView.getStructuralDiagramViewOfHigherAspect();

        lastSelectedLowerLevelItem = new Selection();
    }

    /**
     * This function cancels the highlight effects and the auto created mappings. It is used when tapping on lower level
     * item and then tapping on the background to not select anything.
     */
    private void cancelPreviousTapEffects() {
        // remove the highlight colors from affected views
        removeAllHighlights();

        // Remove the mappings that are created automatically.
        removeLastCreatedMappingsIfNotUsed();

        // reset the last selected lower level item data.
        lastSelectedLowerLevelItem.resetElementData();
    }

    /**
     * This should be called before closing the split insantiation editing mode to remove all the highlights etc.
     */
    public void doFinalOperationsBeforeClosing() {
        cancelPreviousTapEffects();
    }

    /**
     * This is used to get a list of all attributes that are mapped as "To Attribute" in the mappings which has the
     * given operation as a "From Attribute".
     * 
     * @param instantiation Mappings are examined in this instantiation.
     * @param attribute From element in the attribute mappings for which we are looking for "To Attributes" that it is
     *            mapped to.
     * @return list of attributes which are mapped to the given operation in the given instantiation
     */
    private static Set<Attribute> getAllAttributesMappedTo(Instantiation instantiation, Attribute attribute) {
        Set<Attribute> mappedAttributes = new HashSet<Attribute>();
        EList<ClassifierMapping> classifierMappings = instantiation.getMappings();

        // Search all the classifier mappings for the attribute mappings which have the given attribute as a from
        // element
        for (ClassifierMapping classifierMapping : classifierMappings) {
            EList<AttributeMapping> attributeMappings = classifierMapping.getAttributeMappings();

            for (AttributeMapping attributeMapping : attributeMappings) {
                if (attributeMapping.getFrom() == attribute && attributeMapping.getTo() != null) {
                    mappedAttributes.add(attributeMapping.getTo());
                }
            }
        }

        return mappedAttributes;
    }

    /**
     * This is used to get a list of all classifiers that are mapped to the given class in an instantiation.
     * 
     * @param instantiation Mappings are examined in this instantiation.
     * @param classifier The classifier for which we are trying to find the classes that are mapped to it.
     * @return list of classifiers which are mapped to the given classifier in the given instantiation
     */
    private static List<Classifier> getAllMappedClassesOfAClass(Instantiation instantiation, Classifier classifier) {

        List<Classifier> mappedClasses = new ArrayList<Classifier>();
        EList<ClassifierMapping> classifierMappings = instantiation.getMappings();

        for (ClassifierMapping classifierMapping : classifierMappings) {
            if (classifierMapping.getFrom() == classifier && classifierMapping.getTo() != null) {
                mappedClasses.add(classifierMapping.getTo());
            }
        }

        return mappedClasses;
    }

    /**
     * This is used to get a list of all operations that are mapped as "To Operation" in the mappings which has the
     * given operation as a "From Operation".
     * 
     * @param instantiation Mappings are examined in this instantiation.
     * @param operation From element in the operation mappings for which we are looking for "To Operations" that it is
     *            mapped to.
     * @return list of operations which are mapped to the given operation in the given instantiation
     */
    private static Set<Operation> getAllOperationsMappedTo(Instantiation instantiation, Operation operation) {

        Set<Operation> mappedOperations = new HashSet<Operation>();
        EList<ClassifierMapping> classifierMappings = instantiation.getMappings();

        // Search all the classifier mappings for the operations mappings which have the given operation as a from
        // element
        for (ClassifierMapping classifierMapping : classifierMappings) {
            EList<OperationMapping> operationMappings = classifierMapping.getOperationMappings();

            for (OperationMapping operationMapping : operationMappings) {
                if (operationMapping.getFrom() == operation && operationMapping.getTo() != null) {
                    mappedOperations.add(operationMapping.getTo());
                }
            }
        }

        return mappedOperations;
    }

    /**
     * This function is used to check if the tapped location on screen contains one of these : a class view,an operation
     * view, or an attribute view at the specified location. It returns the result of the selection by using a
     * {@link SelectionLowerLevelAspec} Class which holds the information of selected element.
     * 
     * @param pickPoint
     * @return {@link SelectionLowerLevelAspec} this holds the information of selected element.
     */
    private Selection getSelectedView(Vector3D pickPoint) {

        Selection selectionResult = new Selection();

        // get the pick results
        PickResult pickResults = myInstantiationSplitEditingView.pick(pickPoint.getX(), pickPoint.getY(), false);

        for (final PickEntry pick : pickResults.getPickList()) {

            final MTComponent pickComponent = pick.hitObj;

            if (pickComponent instanceof OperationView) {
                selectionResult.setSelectedElementType(SelectedElementType.OPERATION);
                selectionResult.setMyOperationView((OperationView) pickComponent);
                break;
            } else if (pickComponent instanceof ClassView) {
                selectionResult.setSelectedElementType(SelectedElementType.CLASS);
                selectionResult.setMyClassView((ClassView) pickComponent);
                break;
            } else if (pickComponent instanceof AttributeView) {
                selectionResult.setSelectedElementType(SelectedElementType.ATTRIBUTE);
                selectionResult.setMyAttributeView((AttributeView) pickComponent);
                break;
            }
        }
        return selectionResult;
    }

    /**
     * Handles drag event.
     * 
     * @param gestureEvent
     */
    private void handleDragEvent(DragEvent dragEvent) {
        // First we need to figure the point where drag event occured andwhich element is located on the dragging point.
        Vector3D pickPoint = dragEvent.getFrom();

        // get the pick results
        PickResult pickResults = myInstantiationSplitEditingView.pick(pickPoint.getX(), pickPoint.getY(), false);

        for (final PickEntry pick : pickResults.getPickList()) {

            final MTComponent pickComponent = pick.hitObj;

            // Drag classes, implentationClasses and enumerations
            if (pickComponent instanceof BaseView<?>) {

                BaseView<?> classifierView = (BaseView<?>) pickComponent;
                // set the target of the event
                dragEvent.setTarget(classifierView);
                HandlerFactory.INSTANCE.getClassViewHandler().processDragEvent(dragEvent);
                return;

            } else if (pickComponent instanceof StructuralDiagramView) {
                StructuralDiagramView strView = (StructuralDiagramView) pickComponent;
                dragEvent.setTarget(strView);
                HandlerFactory.INSTANCE.getStructuralViewHandler().processDragEvent(dragEvent);
                return;
            }
        }
    }

    /**
     * Handles scale event.
     * 
     * @param gestureEvent
     */
    private void handleScaleEvent(ScaleEvent scaleEvent) {
        Vector3D scalingPoint = scaleEvent.getScalingPoint();

        float whereToSplit = myInstantiationSplitEditingView.getLocationLowerLevelAspect().getY();
        MTComponent containerLayer;

        if (scalingPoint.y < whereToSplit) {
            containerLayer = higherAspectStructuralDiagramView.getContainerLayer();
        } else {
            containerLayer = lowerAspectStructuralDiagramView.getContainerLayer();
        }

        DefaultScaleAction defaultScaleAction = new DefaultScaleAction(containerLayer);
        defaultScaleAction.processGestureEvent(scaleEvent);

    }

    /**
     * Handles tap and hold event.
     * 
     * @param gestureEvent
     */
    private void handleTapAndHoldEvent(TapAndHoldEvent tapAndHoldEvent) {
        // We are not using tap and hold. Intentionally left blank for future uses.
    }

    /**
     * Handles tap event by figuring out the element at the pick location and calls the function that handles the tap on
     * a specific view type.
     * 
     * @param gestureEvent
     */
    private void handleTapEvent(TapEvent tapEvent) {
        if (tapEvent.isTapped()) {

            Vector3D eventLocation = tapEvent.getLocationOnScreen();

            // Figuring out the element at the current tap location
            Selection currentSelectionResult = getSelectedView(eventLocation);

            // if the tap is in the upper part of the split view (the tap is on an element in the higher level aspect's
            // structural diagram view).
            if (isLocatedInHigherLevelAspect(eventLocation)) {

                // Check if we already tapped on a lower level aspect's element before this tap
                if (lastSelectedLowerLevelItem.selectedElementType == SelectedElementType.NO_SELECTION) {
                    // nothing to do
                } else if (currentSelectionResult.getSelectedElementType() == SelectedElementType.NO_SELECTION) {
                    // tapped on the higher level aspect but tapped on the background
                    // so we can cancel the effects of last tapping.

                    // cancel the highlights done after the tapping and delete the mapping that is caused by the last
                    // tap.
                    cancelPreviousTapEffects();

                } else if (currentSelectionResult.getSelectedElementType() == SelectedElementType.CLASS) {
                    // tapped on a class view on higher level aspect.
                    handleTapOnClassHigherLevel(currentSelectionResult.getMyClassView());

                } else if (currentSelectionResult.getSelectedElementType() == SelectedElementType.OPERATION) {
                    // tapped on an operation view on higher level aspect
                    handleTapOnOperationHigherLevel(currentSelectionResult);
                } else if (currentSelectionResult.getSelectedElementType() == SelectedElementType.ATTRIBUTE) {
                    // tapped on an attribute view on higher level aspect
                    handleTapOnAttributeHigherLevel(currentSelectionResult);
                }
                // if the tap is in the lower part of the split view (in lower level aspect's structural diagram view).
            } else {

                // If we had already selected something from the lower level aspect deselect it and remove the selection
                // effects.
                if (lastSelectedLowerLevelItem.getSelectedElementType() != SelectedElementType.NO_SELECTION) {
                    // cancel the highlights done after the tapping and delete the mapping that is caused by the last
                    // tap.
                    cancelPreviousTapEffects();
                }

                // tapped on a class view on lower level aspect.
                if (currentSelectionResult.getSelectedElementType() == SelectedElementType.CLASS) {

                    handleTapOnClassLowerLevel(currentSelectionResult);

                    // tapped on an operation view on higher level aspect
                } else if (currentSelectionResult.getSelectedElementType() == SelectedElementType.OPERATION) {
                    lastSelectedLowerLevelItem = currentSelectionResult;
                    handleTapOnOperationLowerLevel(currentSelectionResult);

                    // tapped on an attribute view on higher level aspect
                } else if (currentSelectionResult.getSelectedElementType() == SelectedElementType.ATTRIBUTE) {
                    handleTapOnAttributeLowerLevel(currentSelectionResult);
                }
            }

        }

    }

    private void handleTapOnAttributeHigherLevel(Selection currentSelectionResult) {
        // Get the attribute
        Attribute currentSelectedAttribute = currentSelectionResult.getMyAttributeView().getAttribute();

        // if lower level Aspect selection was a class or an operation but main Aspect (higher level Aspect) selection
        // is an attribute it can not be mapped.Display popup.
        if (lastSelectedLowerLevelItem.getSelectedElementType() == SelectedElementType.CLASS
                || lastSelectedLowerLevelItem.getSelectedElementType() == SelectedElementType.OPERATION) {
            RamApp.getActiveAspectScene().displayPopup(Strings.POPUP_CANT_MAP_OP_CLASS_TO_ATTRIBUTE);

        } else {
            // lower level aspect's selected element and higher level aspect's selected elements are both
            // attributes, do the attribute mapping.

            // User selected an attribute from higher level aspect but we don't know if the attribute is among the valid
            // attributes that the user can map to.
            if (!isAttributeMappableToAutoCreatedAttributes(currentSelectedAttribute)) {
                RamApp.getActiveAspectScene().displayPopup(Strings.POPUP_INVALID_ATTRIBUTE_MAPPING);
                return;
            }

            // We need to get auto created or existing mapping/mappings which are stored after the tap on the attribute
            // on the lower level aspect.
            // There can only be one one attribute mapping's "to element" be set.
            List<COREMapping<?>> lastStoredCandidateMappings = lastSelectedLowerLevelItem
                    .getLastStoredMappingsOfLastSelectedElement();

            // If the selection is valid we are going to set the correct auto created mapping's "To Element" as the one
            // currently selected attribute.

            for (COREMapping<?> mapping : lastStoredCandidateMappings) {

                // Each auto created attribute mapping belongs to a classifier mapping and this classifier mapping's
                // "to classifier" element is same as the current
                // selectedAttribute's classifier so we can do the mapping.
                Classifier toClassifier = ((ClassifierMapping) mapping.eContainer()).getTo();

                if (toClassifier == currentSelectedAttribute.eContainer()) {
                    controller.setToElement(mapping, CorePackage.Literals.CORE_MAPPING__TO, currentSelectedAttribute);
                    break;
                }
            }

            // Remove all the highlights
            cancelPreviousTapEffects();
        }

    }

    private void handleTapOnAttributeLowerLevel(Selection currentSelectionResult) {
        // current selection is assigned as the latest selected lower level aspect element.
        lastSelectedLowerLevelItem = currentSelectionResult;

        AttributeView lowerLevelSelectedAttributeView = currentSelectionResult.getMyAttributeView();
        Attribute selectedAttribute = lowerLevelSelectedAttributeView.getAttribute();

        // First highlight attribute view that the user tapped on from the lower level aspect.
        lowerLevelSelectedAttributeView.setFillColor(Colors.SELECTION_ATTRIBUTE_DARK);
        lowerLevelSelectedAttributeView.setNoFill(false);

        // First get the class of the operation
        Classifier classOfTheTappedOperation = (Classifier) selectedAttribute.eContainer();

        // Get all the classifier mappings
        EList<ClassifierMapping> allClassifierMappings = instantiation.getMappings();

        // Create a new list with classifier mappings in which the class that has the attribute is a "From Element" in
        // the classifier mapping
        List<ClassifierMapping> classifierMappingsWithSelectedFromElement = new ArrayList<ClassifierMapping>();

        for (ClassifierMapping currentClassifierMapping : allClassifierMappings) {
            if (currentClassifierMapping.getFrom() == classOfTheTappedOperation) {
                classifierMappingsWithSelectedFromElement.add(currentClassifierMapping);

            }
        }

        // Create a list of attribute mappings in which we are going to put either newly created attribute mappings (if
        // the classifier mapping doesn't have
        // an attribute mapping with the selected "from attribute") or the existing attribute mappings (if the
        // classifier mapping already have an attribute mapping with
        // the selected from attribute).

        List<COREMapping<?>> autoCreatedOrExistingMappingsWithSelected = lastSelectedLowerLevelItem
                .getLastStoredMappingsOfLastSelectedElement();
        for (ClassifierMapping classifierMapping : classifierMappingsWithSelectedFromElement) {
            // First check all the attribute mappings of the classifier and if it doesn't have a one with the selected
            // from attribute, create a mapping for the selected
            // element
            // and store its data.

            EList<AttributeMapping> classifierAllAttributeMappings = classifierMapping.getAttributeMappings();

            boolean existingSelectedAttributeMappingFound = false;

            // check if we have the selected attribute as a "from attribute"

            for (AttributeMapping currentAttributeMapping : classifierAllAttributeMappings) {
                if (currentAttributeMapping.getFrom() == selectedAttribute) {
                    autoCreatedOrExistingMappingsWithSelected.add(currentAttributeMapping);
                    existingSelectedAttributeMappingFound = true;
                    break;
                }

            }

            // If the attribute mapping with the selected attribute doesn't exist create one.
            if (!existingSelectedAttributeMappingFound) {
                // Create a mapping for the selected element
                AttributeMapping newAttributeMapping = controller.createAttributeMapping(classifierMapping);
                controller.setFromElement(newAttributeMapping, CorePackage.Literals.CORE_MAPPING__FROM,
                        selectedAttribute);

                autoCreatedOrExistingMappingsWithSelected.add(newAttributeMapping);
            }

        }

        // In order to highlight potential mappable attributes in the higher level aspect, first we need to use the
        // method of AdapterFactoryUtil to get
        // possible choice of values for the "To Attribute" in the potential attribute mapping. We do this for all of
        // the auto created attribute mappings
        // because a class might have been mapped to more than one class so each class has different potential mappable
        // attributes that we can use.

        for (COREMapping<?> currentAttributeMapping : autoCreatedOrExistingMappingsWithSelected) {
            // We have a collection of Attributes and we can map our attribute to one of these attributes.
            @SuppressWarnings("unchecked")
            Collection<Attribute> attributeCollection = (Collection<Attribute>) EMFEditUtil.getChoiceOfValuesFor(
                    currentAttributeMapping, CorePackage.Literals.CORE_MAPPING__TO);

            // Highlight all these attributes in the higher level aspect (attributes that belong to the fetched
            // collection)
            for (Attribute attribute : attributeCollection) {
                markAttribute(attribute, Colors.SELECTION_ATTRIBUTE_LIGHT, Colors.MAPPING_ATTRIBUTE_STROKE);
            }

        }

        // Now highlight with different color the attributes that are already mapped as "To Attribute" for the mappings
        // that has the selected attribute as
        // "From Attribute"
        Set<Attribute> alreadyMappedToAttributes = getAllAttributesMappedTo(instantiation, selectedAttribute);

        for (Attribute attribute : alreadyMappedToAttributes) {
            markAttribute(attribute, Colors.SELECTION_ATTRIBUTE_DARK, Colors.MAPPING_ATTRIBUTE_STROKE);
        }

    }

    private void markAttribute(Attribute attribute, MTColor colorFill, MTColor colorStroke) {
        ClassView classView = (ClassView) higherAspectStructuralDiagramView.getClassViewOf((Classifier) attribute
                .eContainer());
        classView.setFillandStrokeColorOfAttribute(attribute, colorFill, colorStroke);
    }

    private void handleTapOnClassHigherLevel(ClassView tappedClassView) {
        // if lower level Aspect selection was an operation or an attribut but main Aspect (higher level Aspect)
        // selection is a class it can not be mapped.Display popup.
        if (lastSelectedLowerLevelItem.getSelectedElementType() == SelectedElementType.OPERATION
                || lastSelectedLowerLevelItem.getSelectedElementType() == SelectedElementType.ATTRIBUTE) {
            RamApp.getActiveAspectScene().displayPopup(Strings.POPUP_CANT_MAP_CLASS_TO_ATTR_OP);
        } else {
            // lower level aspect's selected element and higher level aspect's selected elements are both class, do
            // the class mapping.

            // first check to see if the lower level has already been mapped which means we did not yet create a mapping
            // to it
            if (lastSelectedLowerLevelItem.getLastStoredMappingsOfLastSelectedElement().isEmpty()) {
                // we need to see if we are trying to map a lower level class to a higher level class that its not
                // mapped
                // to already. If that is the case we create a new classifier mapping and display it. otherwise we dont
                // do anything

                // Get the classes which are mapped to the lower level class that we had selected previously.
                List<Classifier> allMappedClasses = getAllMappedClassesOfAClass(instantiation,
                        lastSelectedLowerLevelItem.getFromClass());
                // Don't create a mapping if the tapped class already is mapped.
                if (allMappedClasses.contains(tappedClassView.getClassifier())) {
                    lastSelectedLowerLevelItem.setFromClass(null);
                    cancelPreviousTapEffects();
                    return;
                } else {
                    ClassifierMapping newClassifierMapping = controller.createClassifierMapping(instantiation);

                    controller.setFromElement(newClassifierMapping, CorePackage.Literals.CORE_MAPPING__FROM,
                            lastSelectedLowerLevelItem.getFromClass());

                    lastSelectedLowerLevelItem.getLastStoredMappingsOfLastSelectedElement().add(newClassifierMapping);
                    lastSelectedLowerLevelItem.setFromClass(null);
                }
            }
            // We need to get auto created mapping which happened after the tap on the class on lower level aspect.
            // There can only be one classmapping created
            // automatically. That is why we use "getMappingsCreatedAfterSelection().get(0);"
            COREMapping<?> lastAutoCreatedMapping = lastSelectedLowerLevelItem
                    .getLastStoredMappingsOfLastSelectedElement().get(0);

            // check if everything is ok with the automatically created mapping after tapping on a classview from the
            // lower level aspect because
            // we are going to set its "To Element" with the one currently selected
            if (lastAutoCreatedMapping instanceof ClassifierMapping && lastAutoCreatedMapping.getTo() == null) {

                ClassifierMapping classifierMapping = (ClassifierMapping) lastAutoCreatedMapping;

                controller.setToElement(classifierMapping, CorePackage.Literals.CORE_MAPPING__TO,
                        tappedClassView.getRamClass());
            } else {
                // test purposes
                System.out
                        .println("********There is something wrong with the last created auto mapping."
                                + "It is either not instance of classifierMapping or 'To Element' is not null."
                                + " (handleTapOnClassHigherLevel)");
            }

            cancelPreviousTapEffects();
        }

    }

    private void handleTapOnClassLowerLevel(Selection currentSelectionResult) {
        // current selection is assigned as the latest selected lower level aspect element.
        lastSelectedLowerLevelItem = currentSelectionResult;

        // figure out the class that was selected in the lower level
        ClassView lowerLevelSelectedClassView = currentSelectionResult.getMyClassView();
        Class selectedClass = lowerLevelSelectedClassView.getRamClass();

        // First highlight class view that the user tapped on from the lower level aspect.
        lowerLevelSelectedClassView.setFillColor(Colors.SELECTION_CLASS_DARK);

        // Get the classes which are mapped to the one that the user tapped on.
        List<Classifier> allMappedClasses = getAllMappedClassesOfAClass(instantiation, selectedClass);

        Collection<ClassView> allClassViewsInHigherAspect = higherAspectStructuralDiagramView.getClassViews();

        // First highlight all classes in the higher level aspect to indicate that they are mappable but we need to mark
        // the one already mapped by the current selected class. Next 2nd for loop will do that.
        for (ClassView classView : allClassViewsInHigherAspect) {
            if (allMappedClasses.contains(classView.getClassifier())) {
                classView.setFillColor(Colors.SELECTION_CLASS_DARK);
            } else {
                classView.setFillColor(Colors.SELECTION_CLASS_LIGHT);
            }
        }

        // Create a mapping for the selected element and store its data. Only if the selected class has not been
        // mapped yet
        if (allMappedClasses.isEmpty()) {
            ClassifierMapping newClassifierMapping = controller.createClassifierMapping(instantiation);

            controller.setFromElement(newClassifierMapping, CorePackage.Literals.CORE_MAPPING__FROM, selectedClass);

            lastSelectedLowerLevelItem.getLastStoredMappingsOfLastSelectedElement().add(newClassifierMapping);
        } else {
            // remember the from class(in lower level that was selected)
            lastSelectedLowerLevelItem.setFromClass(selectedClass);
        }
    }

    private void handleTapOnOperationHigherLevel(Selection currentSelectionResult) {
        // Get the operation
        Operation currentSelectedOperation = currentSelectionResult.getMyOperationView().getOperation();

        // if lower level Aspect selection was a class or an attribute but main Aspect (higher level Aspect) selection
        // is an operation it can not be mapped.Display popup.
        if (lastSelectedLowerLevelItem.getSelectedElementType() == SelectedElementType.CLASS
                || lastSelectedLowerLevelItem.getSelectedElementType() == SelectedElementType.ATTRIBUTE) {
            RamApp.getActiveAspectScene().displayPopup(Strings.POPUP_CANT_MAP_ATTR_CLASS_TO_OPERATION);
        } else {
            // lower level aspect's selected element and higher level aspect's selected elements are both
            // operations, do the operation mapping.

            // User selected an operation from higher level aspect but we don't know if the operation is among the valid
            // operations that the user can map to.
            if (!isOperationMappableToAutoCreatedOperations(currentSelectedOperation)) {
                RamApp.getActiveAspectScene().displayPopup(Strings.POPUP_INVALID_OPERATION_MAPPING);
                return;
            }

            // We need to get auto created or already existing mapping/mappings which are stored after the tap on the
            // operation on the lower level aspect. There can only
            // be one operation
            // mapping's to element be set
            List<COREMapping<?>> lastStoredCandidateMappings = lastSelectedLowerLevelItem
                    .getLastStoredMappingsOfLastSelectedElement();

            // If the selection is valid we are going to set the correct stored mapping's "To Element" as the one
            // currently selected operation.
            for (COREMapping<?> mapping : lastStoredCandidateMappings) {
                OperationMapping operationMapping = (OperationMapping) mapping;
                Set<Operation> alreadyMappedToOperations = getAllOperationsMappedTo(instantiation,
                        operationMapping.getFrom());

                // If there is already a mapping from the from operation to the selected operation
                // don't do anything.
                if (!alreadyMappedToOperations.contains(currentSelectedOperation)) {
                    // Each auto created operation mapping belongs to a classifier mapping and this classifier mappings
                    // "to classifier" element is same as the current
                    // selectedOperation's classifier so we can do the mapping.
                    Classifier toClassifier = ((ClassifierMapping) mapping.eContainer()).getTo();

                    if (toClassifier == currentSelectedOperation.eContainer()) {
                        controller.setToElement(mapping, CorePackage.Literals.CORE_MAPPING__TO,
                                currentSelectedOperation);
                        break;
                    }
                }
            }

            // Remove all the highlights
            cancelPreviousTapEffects();
        }
    }

    private void handleTapOnOperationLowerLevel(Selection currentSelectionResult) {
        // current selection is assigned as the latest selected lower level aspect element.
        lastSelectedLowerLevelItem = currentSelectionResult;

        OperationView lowerLevelSelectedOperationView = currentSelectionResult.getMyOperationView();
        Operation selectedOperation = lowerLevelSelectedOperationView.getOperation();

        // First highlight operation view that the user tapped on from the lower level aspect.
        lowerLevelSelectedOperationView.setFillColor(Colors.SELECTION_OPERATION_DARK);
        lowerLevelSelectedOperationView.setNoFill(false);

        // Then get the class of the operation
        Classifier classOfTheTappedOperation = (Classifier) selectedOperation.eContainer();

        // Get all the classifier mappings
        EList<ClassifierMapping> allClassifierMappings = instantiation.getMappings();

        // Create a new list with classifier mappings.we look for the class (one that has the selected operation) as a
        // "From Element" in the classifier mapping
        List<ClassifierMapping> classifierMappingsWithSelectedFromElement = new ArrayList<ClassifierMapping>();

        for (ClassifierMapping currentClassifierMapping : allClassifierMappings) {
            if (currentClassifierMapping.getFrom() == classOfTheTappedOperation) {
                classifierMappingsWithSelectedFromElement.add(currentClassifierMapping);
            }
        }

        // Create a list of operation mappings in which we are going to put either newly created mappings if the
        // classifier mapping (*) doesn't have
        // an operation mapping with the selected from element or the existing operation mapping in which we have the
        // selected operation
        // (* in each of the classifier mappings that has the selected Classifier as a From Element in the mapping.)
        List<COREMapping<?>> autoCreatedOrExistingOperationMappings = lastSelectedLowerLevelItem
                .getLastStoredMappingsOfLastSelectedElement();

        for (ClassifierMapping classifierMapping : classifierMappingsWithSelectedFromElement) {
            OperationMapping newOperationMapping = controller.createOperationMapping(classifierMapping);
            controller.setFromElement(newOperationMapping, CorePackage.Literals.CORE_MAPPING__FROM, selectedOperation);
            autoCreatedOrExistingOperationMappings.add(newOperationMapping);
        }

        Set<Operation> alreadyMappedToOperations = getAllOperationsMappedTo(instantiation, selectedOperation);

        // In order to highlight potential mappable operations in the higher level aspect, first we need to use the
        // method of AdapterFactoryUtil to get
        // possible choice of values for the "To Operation" in the potential operation mapping. We do this for all of
        // the auto created operation mappings
        // because a class might have been mapped to more than one class so each class has different potential mappable
        // operations that we can use.
        for (COREMapping<?> currentOperationMapping : autoCreatedOrExistingOperationMappings) {
            // We have a collection of Operations and we can map our operation to one of these operaitons.
            @SuppressWarnings("unchecked")
            Collection<Operation> operationCollection = (Collection<Operation>) EMFEditUtil.getChoiceOfValuesFor(
                    currentOperationMapping, CorePackage.Literals.CORE_MAPPING__TO);

            // Highlight all these operations in the higher level aspect (operations that belong to the fetched
            // collection)
            for (Operation operation : operationCollection) {
                if (alreadyMappedToOperations.contains(operation)) {
                    markOperation(operation, Colors.SELECTION_OPERATION_DARK, Colors.MAPPING_OPERATION_STROKE);
                } else {
                    markOperation(operation, Colors.SELECTION_OPERATION_LIGHT, Colors.MAPPING_OPERATION_STROKE);
                }
            }
        }
    }

    private void markOperation(Operation operation, MTColor colorFill, MTColor colorStroke) {
        // TODO: support ImplementationClass (i.e., ClassifierView)
        ClassView classView = (ClassView) higherAspectStructuralDiagramView.getClassViewOf((Classifier) operation
                .eContainer());

        // Issue #59: In case the class view is not contained in the structural view
        // of the higher-level aspect, don't do anything.
        // This could be the case, if the class is contained in an extended aspect.
        if (classView != null) {
            classView.setFillandStrokeColorOfOperation(operation, colorFill, colorStroke);
        }
    }

    /**
     * Handles a {@link WheelEvent} caused by a mouse wheel rotation. Scales the {@link StructuralDiagramView}
     * accordingly.
     * 
     * @param wheelEvent The {@link WheelEvent}
     */
    private void handleWheelEvent(WheelEvent wheelEvent) {
        if (wheelEvent.getTarget() == myInstantiationSplitEditingView.getOverlay()) {
            float whereToSplit = myInstantiationSplitEditingView.getLocationLowerLevelAspect().getY();
            MTComponent containerLayer;

            if (wheelEvent.getLocationOnScreen().y < whereToSplit) {
                containerLayer = higherAspectStructuralDiagramView.getContainerLayer();
            } else {
                containerLayer = lowerAspectStructuralDiagramView.getContainerLayer();
            }

            ScaleEvent se = wheelEvent.asScaleEvent(containerLayer);
            DefaultScaleAction defaultScaleAction = new DefaultScaleAction(containerLayer);
            defaultScaleAction.processGestureEvent(se);
        }
    }

    private boolean isAttributeMappableToAutoCreatedAttributes(Attribute testedAttribute) {

        // Get auto created attribute mappings to be able to get a list of attributes that are possible to be in the
        // attribute mapping by using the
        // method in AdapterFactoryUtil.
        List<COREMapping<?>> autoCreatedAttributeMappings = lastSelectedLowerLevelItem
                .getLastStoredMappingsOfLastSelectedElement();

        for (COREMapping<?> currentAttributeMapping : autoCreatedAttributeMappings) {
            // We have a collection of Attributes and we can map our attribute to one of these attributes.
            @SuppressWarnings("unchecked")
            Collection<Attribute> attributeCollection = (Collection<Attribute>) EMFEditUtil.getChoiceOfValuesFor(
                    currentAttributeMapping, CorePackage.Literals.CORE_MAPPING__TO);

            // Highlight all these attributes in the higher level aspect (attributes that belong to the fetched
            // collection)
            for (Attribute attribute : attributeCollection) {
                if (attribute == testedAttribute) {
                    return true;
                }
            }

        }

        return false;
    }

    /**
     * Finds out if the given point is located in the higher Level Aspect's structural view or the lower level aspect's
     * structural view.
     * 
     * @param pickPoint
     * @return
     */
    private boolean isLocatedInHigherLevelAspect(Vector3D testPoint) {
        Vector3D splitPoint = myInstantiationSplitEditingView.getLocationLowerLevelAspect();
        if (testPoint.y < splitPoint.y) {
            return true;
        }

        return false;
    }

    /**
     * This is used to check if the given operation can be used as a "To Operation" for the auto created Operation.
     * Mappings that are saved as candidates after selecting an operation from the lower level aspect.
     * 
     * @param testedOperation Operation that we are checking the validity for the operation mapping.
     * @return true if it is a valid operation for the mapping.
     */
    private boolean isOperationMappableToAutoCreatedOperations(Operation testedOperation) {

        // Get auto created operation mappings to be able to get a list of operations that are possible to be in the
        // operation mapping by using the method in
        // AdapterFactoryUtil.
        List<COREMapping<?>> autoCreatedOrExistingOperationMappings = lastSelectedLowerLevelItem
                .getLastStoredMappingsOfLastSelectedElement();

        for (COREMapping<?> currentOperationMapping : autoCreatedOrExistingOperationMappings) {
            // We have a collection of Operations and we can map our operation to one of these operations.
            @SuppressWarnings("unchecked")
            Collection<Operation> operationCollection = (Collection<Operation>) EMFEditUtil.getChoiceOfValuesFor(
                    currentOperationMapping, CorePackage.Literals.CORE_MAPPING__TO);

            // Highlight all these operations in the higher level aspect (operations that belong to the fetched
            // collection)
            for (Operation operation : operationCollection) {
                if (operation == testedOperation) {
                    return true;
                }
            }

        }

        return false;
    }

    @Override
    public boolean processGestureEvent(MTGestureEvent gestureEvent) {

        if (gestureEvent instanceof TapEvent) {
            // prevent a tap event from being processed when a tap and hold event was processed before
            if (tapAndHoldPerformed) {
                tapAndHoldPerformed = false;
                return true;
            }
            handleTapEvent((TapEvent) gestureEvent);
        } else if (gestureEvent instanceof DragEvent) {

            handleDragEvent((DragEvent) gestureEvent);
        } else if (gestureEvent instanceof TapAndHoldEvent) {
            TapAndHoldEvent tapAndHoldEvent = (TapAndHoldEvent) gestureEvent;
            // used for workaround to prevent tap event from being executed at the same time
            switch (tapAndHoldEvent.getId()) {
                case MTGestureEvent.GESTURE_ENDED:
                    if (tapAndHoldEvent.isHoldComplete()) {
                        tapAndHoldPerformed = true;
                    }
                    break;
            }

            handleTapAndHoldEvent((TapAndHoldEvent) gestureEvent);
        } else if (gestureEvent instanceof ScaleEvent) {
            handleScaleEvent((ScaleEvent) gestureEvent);
        } else if (gestureEvent instanceof WheelEvent) {
            handleWheelEvent((WheelEvent) gestureEvent);
        }

        return true;

    }

    /**
     * It removes the highlighting effects made after a selection which comes from the user (sets affected views' color
     * to default ones).
     */
    private void removeAllHighlights() {
        removeHighlightLastSelectedLowerLevelItem();
        removeHighlightsOfHigherLevelItems();
    }

    /**
     * Disables the color change in the stroke color of the last selected item in lower structural view.
     */
    private void removeHighlightLastSelectedLowerLevelItem() {
        if (lastSelectedLowerLevelItem.getSelectedElementType() == SelectedElementType.CLASS) {
            lastSelectedLowerLevelItem.getMyClassView().setFillColorToDefault();

        } else if (lastSelectedLowerLevelItem.getSelectedElementType() == SelectedElementType.OPERATION) {
            // TODO: mschoettle: Move this into the view itself, like in ClassView.
            OperationView op = lastSelectedLowerLevelItem.getMyOperationView();
            op.setFillColor(Colors.OPERATION_VIEW_DEFAULT_FILL_COLOR);
            op.setNoFill(true);
            op.setStrokeColor(Colors.OPERATION_VIEW_DEFAULT_STROKE_COLOR);
            op.setNoStroke(true);
        } else if (lastSelectedLowerLevelItem.getSelectedElementType() == SelectedElementType.ATTRIBUTE) {
            // TODO: mschoettle: Move this into the view itself, like in ClassView.
            AttributeView att = lastSelectedLowerLevelItem.getMyAttributeView();
            att.setFillColor(Colors.ATTRIBUTE_VIEW_DEFAULT_FILL_COLOR);
            att.setNoFill(true);
            att.setStrokeColor(Colors.ATTRIBUTE_VIEW_DEFAULT_STROKE_COLOR);
            att.setNoStroke(true);
        }

    }

    /**
     * It removes the highlighting effects of higher level aspect's views.
     */
    private void removeHighlightsOfHigherLevelItems() {
        if (lastSelectedLowerLevelItem.getSelectedElementType() == SelectedElementType.CLASS) {
            higherAspectStructuralDiagramView.setFillAndStrokeColorOfAllClasses(Colors.CLASS_VIEW_DEFAULT_FILL_COLOR,
                    Colors.CLASS_VIEW_DEFAULT_STROKE_COLOR);

        } else if (lastSelectedLowerLevelItem.getSelectedElementType() == SelectedElementType.OPERATION) {
            Collection<ClassView> allClassViews = higherAspectStructuralDiagramView.getClassViews();

            for (ClassView classview : allClassViews) {
                classview.setDefaultFillAndStrokeColorForAllOperations();
            }
        } else if (lastSelectedLowerLevelItem.getSelectedElementType() == SelectedElementType.ATTRIBUTE) {
            Collection<ClassView> allClassViews = higherAspectStructuralDiagramView.getClassViews();

            for (ClassView classview : allClassViews) {
                classview.setDefaultFillAndStrokeColorForAllAttributes();
            }
        }

    }

    /**
     * This checks if the user's last selection caused a mapping/mappings creation and if a mapping is not completed
     * (such as : OperationA-->Select Operation) it removes the mapping.
     */
    private void removeLastCreatedMappingsIfNotUsed() {
        // check if the user's previous selection caused a mapping or mappings creation and if one/some of them is not
        // completed.
        List<COREMapping<?>> autoCreatedMappings = lastSelectedLowerLevelItem
                .getLastStoredMappingsOfLastSelectedElement();

        if (autoCreatedMappings.size() > 0) {

            for (COREMapping<?> currentMapping : autoCreatedMappings) {

                // If user did not set any ToElements to this auto created mapping
                // else do nothing because user mapped a ToElement by using the related Instantiation View.
                if (currentMapping.eContainer() != null && currentMapping.getTo() == null) {
                    // delete the last created mapping(whose creation was
                    // triggered by users selection).
                    controller.deleteMapping(currentMapping);
                }

            }
        }

    }

}
