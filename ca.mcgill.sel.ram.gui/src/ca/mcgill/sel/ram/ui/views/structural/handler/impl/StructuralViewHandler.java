package ca.mcgill.sel.ram.ui.views.structural.handler.impl;

import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.mt4j.components.TransformSpace;
import org.mt4j.input.inputData.InputCursor;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapAndHoldProcessor.TapAndHoldEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;
import org.mt4j.input.inputProcessors.componentProcessors.unistrokeProcessor.UnistrokeEvent;
import org.mt4j.input.inputProcessors.componentProcessors.unistrokeProcessor.UnistrokeUtils.UnistrokeGesture;
import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.commons.emf.util.EMFModelUtil;
import ca.mcgill.sel.core.util.COREModelUtil;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.Class;
import ca.mcgill.sel.ram.Classifier;
import ca.mcgill.sel.ram.ImplementationClass;
import ca.mcgill.sel.ram.LayoutElement;
import ca.mcgill.sel.ram.PrimitiveType;
import ca.mcgill.sel.ram.REnum;
import ca.mcgill.sel.ram.RamFactory;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.StructuralView;
import ca.mcgill.sel.ram.Type;
import ca.mcgill.sel.ram.TypeParameter;
import ca.mcgill.sel.ram.controller.ControllerFactory;
import ca.mcgill.sel.ram.controller.StructuralViewController;
import ca.mcgill.sel.ram.loaders.RamClassLoader;
import ca.mcgill.sel.ram.loaders.RamClassUtils;
import ca.mcgill.sel.ram.loaders.exceptions.MissingJarException;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamPopup.PopupType;
import ca.mcgill.sel.ram.ui.components.RamSelectorComponent;
import ca.mcgill.sel.ram.ui.components.listeners.AbstractDefaultRamSelectorListener;
import ca.mcgill.sel.ram.ui.events.WheelEvent;
import ca.mcgill.sel.ram.ui.utils.Constants;
import ca.mcgill.sel.ram.ui.utils.MathUtils;
import ca.mcgill.sel.ram.ui.utils.RamModelUtils;
import ca.mcgill.sel.ram.ui.utils.Strings;
import ca.mcgill.sel.ram.ui.utils.UnistrokeProcessorUtils;
import ca.mcgill.sel.ram.ui.views.AbstractView;
import ca.mcgill.sel.ram.ui.views.OptionSelectorView;
import ca.mcgill.sel.ram.ui.views.handler.impl.AbstractViewHandler;
import ca.mcgill.sel.ram.ui.views.structural.BaseView;
import ca.mcgill.sel.ram.ui.views.structural.ClassView;
import ca.mcgill.sel.ram.ui.views.structural.ClassifierView;
import ca.mcgill.sel.ram.ui.views.structural.ImplementationClassSelectorView;
import ca.mcgill.sel.ram.ui.views.structural.ImplementationClassView;
import ca.mcgill.sel.ram.ui.views.structural.StructuralDiagramView;
import ca.mcgill.sel.ram.ui.views.structural.handler.IStructuralViewHandler;

/**
 * The default handler for a {@link StructuralDiagramView}.
 *
 * @author mschoettle
 */
public class StructuralViewHandler extends AbstractViewHandler implements IStructuralViewHandler {

    /**
     * The options to display when tap-and-hold is performed.
     */
    private enum CreateFeature {
        CLASS, ENUM, IMPLEMENTATION_CLASS
    }

    /**
     * Handles the creation of a new class. Takes care of openeing a keyboard for the name once the class was added to
     * the structural view.
     *
     * @param view the structural view view representing the structural view
     * @param position the position where the class should be located
     */
    @SuppressWarnings("static-method")
    private void createNewClass(final StructuralDiagramView view, final Vector3D position) {
        final StructuralView structuralView = view.getStructuralView();

        final Aspect aspect = (Aspect) structuralView.eContainer();

        aspect.eAdapters().add(new EContentAdapter() {

            private Class clazz;

            @Override
            public void notifyChanged(Notification notification) {
                if (notification.getFeature() == RamPackage.Literals.STRUCTURAL_VIEW__CLASSES) {
                    if (notification.getEventType() == Notification.ADD) {
                        clazz = (Class) notification.getNewValue();
                    }
                } else if (notification.getFeature() == RamPackage.Literals.CONTAINER_MAP__VALUE) {
                    if (notification.getEventType() == Notification.ADD) {
                        ((ClassView) view.getClassViewOf(clazz)).showKeyboard();
                        ((ClassView) view.getClassViewOf(clazz)).clearNameField();
                        aspect.eAdapters().remove(this);
                    }
                }
            }
        });

        // Look for the highest index used by any class
        String className = COREModelUtil.createUniqueNameFromElements(Strings.DEFAULT_CLASS_NAME,
                new HashSet<Classifier>(view.getStructuralView().getClasses()));

        ControllerFactory.INSTANCE.getStructuralViewController().createNewClass(structuralView,
                className, position.getX(), position.getY());
    }

    /**
     * Displays a selector with implementation classes that can be created.
     *
     * @param view View where the implementation class selector should be added
     * @param position The position where the selector should be added.
     */
    @SuppressWarnings("static-method")
    private void createNewImplementationClass(final StructuralDiagramView view, final Vector3D position) {
        // Get the structural view
        final StructuralView structuralView = view.getStructuralView();

        // Get all the implementation classes that already exists in the view (only non generic ones)
        List<String> existingImplementationClasses = new ArrayList<String>();
        for (Classifier classifier : view.getStructuralView().getClasses()) {
            if (classifier instanceof ImplementationClass) {
                // If it's not generic add it to list
                if (((ImplementationClass) classifier).getTypeParameters().size() == 0) {
                    existingImplementationClasses.add(((ImplementationClass) classifier).getInstanceClassName());
                }
            }
        }

        // Create an implementation class view
        ImplementationClassSelectorView selector = new ImplementationClassSelectorView(existingImplementationClasses);

        selector.registerListener(new AbstractDefaultRamSelectorListener<String>() {
            @Override
            public void elementSelected(RamSelectorComponent<String> selector, String element) {
                handleImplementationClassSelection(structuralView, element, position.getX(), position.getY());
            }
        });

        // Add selector to screen + keyboard
        RamApp.getActiveAspectScene().addComponent(selector, position);
        selector.displayKeyboard();
    }

    /**
     * Handles the selection of an implementation class to import by the user.
     * 
     * @param structuralView the {@link StructuralView} the class should be added to
     * @param className the name
     * @param x the x position of the class
     * @param y the y position of the class
     */
    private static void handleImplementationClassSelection(StructuralView structuralView, String className,
            float x, float y) {
        StructuralViewController controller = ControllerFactory.INSTANCE.getStructuralViewController();
        try {
            java.lang.Class<?> loadedClass = RamClassLoader.INSTANCE.retrieveClass(className);
            if (RamClassLoader.INSTANCE.isLoadableEnum(className)) {
                List<String> literals = new ArrayList<String>();
                for (Object literal : loadedClass.getEnumConstants()) {
                    literals.add(literal.toString());
                }
                controller.createImplementationEnum(structuralView, RamClassUtils.extractClassName(className),
                        className, x, y, literals);
            } else {
                List<TypeParameter> typeParameters = new ArrayList<TypeParameter>();
                for (TypeVariable<?> tv : loadedClass.getTypeParameters()) {
                    TypeParameter typeParameter = RamFactory.eINSTANCE.createTypeParameter();
                    typeParameter.setName(tv.getName());
                    typeParameters.add(typeParameter);
                }
                boolean isInterface = RamClassLoader.INSTANCE.isLoadableInterface(className);
                Set<String> superTypes = RamClassLoader.INSTANCE.getAllSuperClassesFor(className);
                Set<ImplementationClass> subTypes = RamModelUtils.getExistingSubTypesFor(structuralView, className);
                // If an element is selected create the implementation class with the controller
                controller.createImplementationClass(structuralView, className, typeParameters, isInterface,
                        x, y, superTypes, subTypes);
            }
        } catch (ClassNotFoundException e) {
            RamApp.getActiveAspectScene().displayPopup(Strings.POPUP_ERROR_CLASS_NOT_FOUND, PopupType.ERROR);
        } catch (MissingJarException e) {
            RamApp.getActiveAspectScene().displayPopup(e.getMessage(), PopupType.ERROR);
        }
    }

    /**
     * Create an Enum to be added to the structural view.
     *
     * @param view View where the enum should be added
     * @param position The position where it should be added.
     */
    @SuppressWarnings("static-method")
    private void createNewEnum(final StructuralDiagramView view, Vector3D position) {
        final StructuralView structuralView = view.getStructuralView();

        final Aspect aspect = (Aspect) structuralView.eContainer();

        // Will show keyboard when the enum view appears on screen
        aspect.eAdapters().add(new EContentAdapter() {

            private REnum renum;

            @Override
            public void notifyChanged(Notification notification) {
                if (notification.getFeature() == RamPackage.Literals.STRUCTURAL_VIEW__TYPES) {
                    if (notification.getEventType() == Notification.ADD) {
                        renum = (REnum) notification.getNewValue();
                    }
                } else if (notification.getFeature() == RamPackage.Literals.CONTAINER_MAP__VALUE) {
                    if (notification.getEventType() == Notification.ADD) {
                        view.getEnumView(renum).showKeyboard();
                        view.getEnumView(renum).clearNameField();
                        aspect.eAdapters().remove(this);
                    }
                }
            }
        });

        // Get a unique name for the enum
        Set<REnum> enums = new HashSet<REnum>();
        for (Type type : view.getStructuralView().getTypes()) {
            if (type instanceof REnum) {
                enums.add((REnum) type);
            }
        }
        String enumName = COREModelUtil.createUniqueNameFromElements(Strings.DEFAULT_ENUM_NAME, enums);

        // Create the REnum using the structural view controller
        ControllerFactory.INSTANCE.getStructuralViewController().createEnum(structuralView,
                enumName, position.getX(), position.getY());

    }

    /**
     * This will check to see if the delete(x) gesture is done over a class. If yes it will delete the class.
     * Determining if the gesture was done over a class is by comparing the x and y values of the start ] and end
     * position of the mouse cursor with the position of the class.
     *
     * @param structuralView this represent the structural view in which the gesture was performed on
     * @param startPosition The position of the cursor when the gesture was started
     * @param endPosition The position of the cursor when the gesture was ended
     * @param inputCursor the input cursor of the event
     */
    @SuppressWarnings("static-method")
    private void deleteClass(StructuralDiagramView structuralView, Vector3D startPosition, Vector3D endPosition,
            InputCursor inputCursor) {

        Vector3D intersection = UnistrokeProcessorUtils.getIntersectionPoint(startPosition, endPosition, inputCursor);

        float intersectionX = intersection.x;
        float intersectionY = intersection.y;

        // find the class(es) that are underneath the intersection
        for (BaseView<?> baseView : structuralView.getBaseViews()) {
            float left = baseView.getX();
            float upper = baseView.getY();
            float right = left + baseView.getWidth();
            float lower = upper + baseView.getHeight();

            if (left < intersectionX && right > intersectionX && upper < intersectionY && lower > intersectionY) {
                ControllerFactory.INSTANCE.getStructuralViewController().removeClassifier(baseView.getClassifier());
                break;
            }
        }

    }

    @Override
    public void dragAllSelectedClasses(StructuralDiagramView svd, Vector3D directionVector) {
        for (BaseView<?> baseView : svd.getSelectedElements()) {
            // create a copy of the translation vector, because translateGlobal modifies it
            baseView.translateGlobal(new Vector3D(directionVector));
        }
    }

    @Override
    public boolean handleDoubleTapOnClass(StructuralDiagramView structuralView, BaseView<?> targetBaseView) {
        Set<BaseView<?>> selectedBaseViews = structuralView.getSelectedElements();

        if (selectedBaseViews != null && selectedBaseViews.size() == 1) {
            BaseView<?> selectedBaseView = structuralView.getSelectedElements().iterator().next();
            createAssociation(structuralView, selectedBaseView, targetBaseView, true);
        }

        return true;
    }

    @Override
    public boolean handleTapAndHoldOnClass(StructuralDiagramView structuralView, BaseView<?> targetClassifierView) {
        Set<BaseView<?>> selectedBaseViews = structuralView.getSelectedElements();

        // Only allow an inheritance to be added if only one classifier is selected.
        if (selectedBaseViews != null && selectedBaseViews.size() == 1
                // Prevent this to be executed if the selected class and the target class are the same.
                && !selectedBaseViews.contains(targetClassifierView)) {
            BaseView<?> selectedBaseView = selectedBaseViews.iterator().next();

            // Don't allow this for ImplementationClasses or Enums.
            if (selectedBaseView instanceof ClassView
                    && !(targetClassifierView.getClassifier() instanceof PrimitiveType)) {
                ClassView selectedClassView = (ClassView) selectedBaseView;

                Classifier superType = targetClassifierView.getClassifier();
                Class subType = selectedClassView.getRamClass();

                ControllerFactory.INSTANCE.getClassController().addSuperType(subType, superType);

                return true;
            }
        }

        return false;
    }

    @Override
    public void moveAllSelectedClasses(StructuralDiagramView v) {
        StructuralView structuralView = v.getStructuralView();

        Map<Classifier, LayoutElement> positionMap = new HashMap<Classifier, LayoutElement>();

        for (BaseView<?> baseView : v.getSelectedElements()) {
            // relative position is required in order to correctly set the position independent of zoom level
            Vector3D position = baseView.getPosition(TransformSpace.RELATIVE_TO_PARENT);

            LayoutElement layoutElement = RamFactory.eINSTANCE.createLayoutElement();
            layoutElement.setX(position.getX());
            layoutElement.setY(position.getY());

            positionMap.put(baseView.getClassifier(), layoutElement);
        }

        ControllerFactory.INSTANCE.getStructuralViewController().moveClassifiers(structuralView, positionMap);
    }

    @Override
    public boolean processTapAndHoldEvent(final TapAndHoldEvent tapAndHoldEvent) {
        if (tapAndHoldEvent.isHoldComplete()) {
            final StructuralDiagramView target = (StructuralDiagramView) tapAndHoldEvent.getTarget();
            OptionSelectorView<CreateFeature> selector = new OptionSelectorView<CreateFeature>(CreateFeature.values());

            final Vector3D position = tapAndHoldEvent.getLocationOnScreen();
            RamApp.getActiveAspectScene().addComponent(selector, position);

            selector.registerListener(new AbstractDefaultRamSelectorListener<CreateFeature>() {

                @Override
                public void elementSelected(RamSelectorComponent<CreateFeature> selector, CreateFeature element) {
                    switch (element) {
                        case CLASS:
                            createNewClass(target, position);
                            break;
                        case ENUM:
                            createNewEnum(target, position);
                            break;
                        case IMPLEMENTATION_CLASS:
                            createNewImplementationClass(target, position);
                            break;
                    }
                }
            });
        }
        return true;
    }

    @Override
    public boolean processTapEvent(TapEvent tapEvent) {
        if (tapEvent.isTapped()) {

            StructuralDiagramView target = (StructuralDiagramView) tapEvent.getTarget();

            target.deselect();
        }

        return true;
    }

    @Override
    protected boolean processWheelEvent(WheelEvent wheelEvent) {
        super.processWheelEvent(wheelEvent);

        if (wheelEvent.getTarget() instanceof StructuralDiagramView) {
            StructuralDiagramView target = (StructuralDiagramView) wheelEvent.getTarget();

            Collection<ClassifierView<?>> allClassViews = target.getClassifierViews();
            Aspect aspect = EMFModelUtil.getRootContainerOfType(target.getStructuralView(), RamPackage.Literals.ASPECT);

            // TODO: Why is this necessary?
            for (ClassifierView<?> v : allClassViews) {
                Vector3D position = v.getPosition(TransformSpace.RELATIVE_TO_PARENT);

                LayoutElement layoutElement =
                        aspect.getLayout().getContainers().get(target.getStructuralView()).get(v.getClassifier());

                layoutElement.setX(position.getX());
                layoutElement.setY(position.getY());

                // if we want to use a command to move the class, do this:
                // ClassController classController = ControllerFactory.INSTANCE.getClassController();
                // classController.moveClass(v.getRamClass(), position.getX(), position.getY());
            }
        }

        return true;
    }

    @Override
    public void handleUnistrokeGesture(AbstractView<?> target, UnistrokeGesture gesture, Vector3D startPosition,
            UnistrokeEvent event) {
        StructuralDiagramView structuralDiagramView = (StructuralDiagramView) target;

        switch (event.getId()) {
            case MTGestureEvent.GESTURE_ENDED:
                Vector3D startGesturePoint = event.getCursor().getStartPosition();
                Vector3D endGesturePoint = event.getCursor().getPosition();
                ClassifierView<?> startClass = null;
                ClassifierView<?> endClass = null;

                for (ClassifierView<?> view : structuralDiagramView.getClassifierViews()) {
                    if (MathUtils.pointIsInRectangle(startGesturePoint, view, Constants.MARGIN_ELEMENT_DETECTION)) {
                        startClass = view;
                    }
                    if (view.containsPointGlobal(endGesturePoint)) {
                        endClass = view;
                    }
                    if (startClass != null & endClass != null) {
                        createAssociation(structuralDiagramView, startClass, endClass, false);
                        return;
                    }
                }
                break;
        }

        switch (gesture) {
            case RECTANGLE:
                createNewClass(structuralDiagramView, startPosition);
                break;
            case X:
                Vector3D endPosition = event.getCursor().getPosition();
                deleteClass(structuralDiagramView, startPosition, endPosition, event.getCursor());
                break;
            case CIRCLE:
                createNewEnum(structuralDiagramView, startPosition);
                break;
            case TRIANGLE:
                createNewImplementationClass(structuralDiagramView, startPosition);
                break;
        }
    }

    /**
     * Create an association between the given {@link BaseView}s.
     *
     * @param structuralView - The {@link StructuralDiagramView} the views come from.
     * @param baseView - Start of the association.
     * @param targetView - End point of the association.
     * @param bidirectional - Whether the relationship between two classes should be bidirectional.
     */
    private static void createAssociation(StructuralDiagramView structuralView,
            BaseView<?> baseView, BaseView<?> targetView, boolean bidirectional) {
        if (baseView instanceof ClassifierView && targetView instanceof ClassifierView
                && !(baseView instanceof ImplementationClassView)) {
            ClassifierView<?> selectedClassifierView = (ClassifierView<?>) baseView;
            ClassifierView<?> targetClassifierView = (ClassifierView<?>) targetView;
            ControllerFactory.INSTANCE.getStructuralViewController().createAssociation(
                    structuralView.getStructuralView(), selectedClassifierView.getClassifier(),
                    targetClassifierView.getClassifier(), bidirectional);
        }
    }

}
