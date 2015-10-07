package ca.mcgill.sel.ram.ui.views.structural;

import java.util.ArrayList;
import java.util.Collection;
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
import org.mt4j.input.inputProcessors.componentProcessors.panProcessor.PanProcessorTwoFingers;
import org.mt4j.input.inputProcessors.componentProcessors.tapAndHoldProcessor.TapAndHoldProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.unistrokeProcessor.UnistrokeUtils.Direction;
import org.mt4j.input.inputProcessors.componentProcessors.unistrokeProcessor.UnistrokeUtils.UnistrokeGesture;
import org.mt4j.input.inputProcessors.componentProcessors.zoomProcessor.ZoomProcessor;
import org.mt4j.util.MTColor;
import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.ram.Association;
import ca.mcgill.sel.ram.AssociationEnd;
import ca.mcgill.sel.ram.Class;
import ca.mcgill.sel.ram.Classifier;
import ca.mcgill.sel.ram.ImplementationClass;
import ca.mcgill.sel.ram.LayoutElement;
import ca.mcgill.sel.ram.REnum;
import ca.mcgill.sel.ram.RamFactory;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.StructuralView;
import ca.mcgill.sel.ram.Type;
import ca.mcgill.sel.ram.impl.ContainerMapImpl;
import ca.mcgill.sel.ram.impl.ElementMapImpl;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.events.MouseWheelProcessor;
import ca.mcgill.sel.ram.ui.events.RightClickDragProcessor;
import ca.mcgill.sel.ram.ui.events.UnistrokeProcessorLeftClick;
import ca.mcgill.sel.ram.ui.utils.Constants;
import ca.mcgill.sel.ram.ui.views.AbstractView;
import ca.mcgill.sel.ram.ui.views.RamEnd;
import ca.mcgill.sel.ram.ui.views.handler.HandlerFactory;
import ca.mcgill.sel.ram.ui.views.structural.handler.IStructuralViewHandler;

/**
 * The visual representation for a class diagram.
 * 
 * @author vbonnet
 * @author mschoettle
 */
public class StructuralDiagramView extends AbstractView<IStructuralViewHandler>
        implements INotifyChangedListener {

    private static final int DISTANCE_BETWEEN_CLASSES = 200;

    private Map<Classifier, ClassifierView<?>> classToViewMap;
    private Map<REnum, EnumView> enumToViewMap;
    private Map<Association, AssociationView> associationToViewMap;
    private List<InheritanceView> inheritanceViewList;
    private Set<BaseView<?>> selectedElements;

    private ContainerMapImpl layout;
    private StructuralView structuralView;

    /**
     * Creates a new StructuralDiagramView for the given class diagram. For every class inside the class diagram its own
     * view is created as well as for every association. IMPORTANT: All the view elements related to the
     * StructuralDiagramView are added to the containerLayer.
     * 
     * @param structuralView the class diagram
     * @param layout some layout.
     * @param width The width over which the elements can be displayed
     * @param height The height over which the elements can be displayed.
     */
    public StructuralDiagramView(StructuralView structuralView, ContainerMapImpl layout, float width, float height) {
        super(width, height);

        this.layout = layout;
        this.structuralView = structuralView;

        associationToViewMap = new HashMap<Association, AssociationView>();
        // maintain a map from class to the associated view since this is needed for creating associations
        classToViewMap = new HashMap<Classifier, ClassifierView<?>>();
        enumToViewMap = new HashMap<REnum, EnumView>();
        inheritanceViewList = new ArrayList<InheritanceView>();

        buildAndLayout(width, height);

        EMFEditUtil.addListenerFor(structuralView, this);
        // register to the ContainerMap to receive adds/removes of ElementMaps
        EMFEditUtil.addListenerFor(this.layout, this);

        // create the list for storing selected classifiers
        selectedElements = new HashSet<BaseView<?>>();
    }

    /**
     * Builds this view and layouts all classes within the dimensions given.
     * 
     * @param width the width of this view
     * @param height the height of this view
     */
    private void buildAndLayout(float width, float height) {
        // Collect all classifiers to display first.
        List<Classifier> classifiers = new ArrayList<Classifier>();

        for (Classifier clazz : structuralView.getClasses()) {
            classifiers.add(clazz);
        }

        for (Type type : structuralView.getTypes()) {
            if (type instanceof REnum) {
                classifiers.add((REnum) type);
            }
        }

        // Now add them to the structural view and layout the ones that don't have a layout yet.
        Vector3D position = new Vector3D(100, 100);
        int maxHeight = 0;

        float maxX = width;
        float maxY = height;
        for (Classifier classifier : classifiers) {
            LayoutElement layoutElement = layout == null ? null : layout.getValue().get(classifier);
            BaseView<?> classifierView = null;

            // Enums need to be tested before ImplementationClass,
            // because Enums are PrimitiveType, which is an ImplementationClass.
            if (classifier instanceof Class) {
                addClass((Class) classifier, layoutElement);
                classifierView = classToViewMap.get(classifier);
            } else if (classifier instanceof REnum) {
                REnum enumType = (REnum) classifier;
                addEnum(enumType, layoutElement, enumType.getInstanceClassName() != null);
                classifierView = enumToViewMap.get(enumType);
            } else if (classifier instanceof ImplementationClass) {
                addImplementationClass((ImplementationClass) classifier, layoutElement);
                classifierView = classToViewMap.get(classifier);
            }

            if (layoutElement == null) {

                layoutElement = RamFactory.eINSTANCE.createLayoutElement();
                this.layout.getValue().put(classifier, layoutElement);

                // TODO: move to separate method or class (e.g. a layouter)
                // Move the class so they will be created next to each other.
                position.setX(position.getX() + DISTANCE_BETWEEN_CLASSES);
                // Really weird fix (3 * width) but it allows (for now) to have a wider class diagram after weaving.
                if (position.getX() + classifierView.getWidth() >= 3 * width) {
                    position.setX(DISTANCE_BETWEEN_CLASSES);
                    position.setY(position.getY() + maxHeight + DISTANCE_BETWEEN_CLASSES);
                    maxHeight = 0;
                }

                layoutElement.setX(position.getX());
                layoutElement.setY(position.getY());
                classifierView.setLayoutElement(layoutElement);

                maxHeight = Math.max(maxHeight, (int) classifierView.getHeightXY(TransformSpace.LOCAL));
                position.setX(position.getX() + classifierView.getWidthXY(TransformSpace.RELATIVE_TO_PARENT));
            } else {
                position.setX(Math.max(position.getX(), layoutElement.getX() + classifierView.getWidth()));
                position.setY(Math.max(position.getY(), layoutElement.getY() + classifierView.getHeight()));
            }

            maxX = Math.max(maxX, classifierView.getX() + classifierView.getWidth());
            maxY = Math.max(maxY, classifierView.getY() + classifierView.getHeight());
        }

        float scale = Math.min(width / maxX, height / maxY);
        scaleGlobal(scale, scale, 1f, new Vector3D(0, 0, 0));

        for (Association association : structuralView.getAssociations()) {
            addAssociationView(association);
        }

        // Create inheritance links.
        // This can't be done above when iterating through all the classes
        // since a corresponding ClassView might not exist yet.
        for (Classifier classifier : structuralView.getClasses()) {

            for (Classifier superClass : classifier.getSuperTypes()) {
                addInheritanceView(classifier, superClass);
            }
        }
    }

    /**
     * Adds an {@link AssociationView} for the given association to this view.
     * The association is created between the two classifiers of the association,
     * which is determined by the association ends.
     * 
     * @param association the association to add a view for
     */
    private void addAssociationView(Association association) {
        AssociationEnd toEnd = association.getEnds().get(0);
        AssociationEnd fromEnd = association.getEnds().get(1);
        ClassifierView<?> fromEndClassifierView = classToViewMap.get(fromEnd.getClassifier());
        ClassifierView<?> toEndClassifierView = classToViewMap.get(toEnd.getClassifier());

        AssociationView view = new AssociationView(association, fromEnd, fromEndClassifierView,
                toEnd, toEndClassifierView);
        view.setHandler(HandlerFactory.INSTANCE.getAssociationViewHandler());
        view.setBackgroundColor(this.getFillColor());
        associationToViewMap.put(association, view);

        view.updateLines();
        addChild(view);
    }

    /**
     * Adds a new {@link ClassView} for the given class to this view.
     * 
     * @param clazz the class to add a view for
     * @param layoutElement the layout element containing layout information for the class
     */
    private void addClass(Class clazz, LayoutElement layoutElement) {
        // this view is registered as observer on the class (it is done in the class' constructor)
        ClassView classView = new ClassView(this, clazz, layoutElement);

        classToViewMap.put(clazz, classView);

        addChild(classView);

        classView.setHandler(HandlerFactory.INSTANCE.getClassViewHandler());
    }

    /**
     * Add an enum to structural diagram view.
     * 
     * @param renum that we want to add
     * @param layoutElement of the enum view.
     * @param isImplementationEnum is it an implementation enum?
     */
    private void addEnum(REnum renum, LayoutElement layoutElement, boolean isImplementationEnum) {
        // this view is registered as observer on the class (it is done in the class' constructor)
        EnumView enumView = new EnumView(this, renum, layoutElement, isImplementationEnum);

        enumToViewMap.put(renum, enumView);

        addChild(enumView);

        enumView.setHandler(HandlerFactory.INSTANCE.getEnumViewHandler());
    }

    /**
     * Adds a new implementation class.
     * 
     * @param implementationClass to be added.
     * @param layoutElement of the ImplementationClassView
     */
    private void addImplementationClass(ImplementationClass implementationClass, LayoutElement layoutElement) {
        ImplementationClassView implementationClassView = new ImplementationClassView(this, implementationClass,
                layoutElement);
        classToViewMap.put(implementationClass, implementationClassView);

        addChild(implementationClassView);

        implementationClassView.setHandler(HandlerFactory.INSTANCE.getImplementationClassViewHandler());
    }

    /**
     * Adds an {@link InheritanceView} between the given super and sub class.
     * 
     * @param derivedClass the sub class
     * @param superClass the super class
     */
    public void addInheritanceView(Classifier derivedClass, Classifier superClass) {
        InheritanceView inheritanceView = new InheritanceView(classToViewMap.get(derivedClass),
                classToViewMap.get(superClass));
        // Interfaces should be handled differently.
        if (superClass instanceof ImplementationClass && ((ImplementationClass) superClass).isInterface()) {
            inheritanceView.setImplementsInheritance(true);
        }
        // Make sure that inheritance between implementation classes cannot be removed by the user.
        if (!(derivedClass instanceof ImplementationClass)) {
            inheritanceView.setHandler(HandlerFactory.INSTANCE.getInheritanceViewHandler());
        }
        inheritanceViewList.add(inheritanceView);

        inheritanceView.updateLines();
        addChild(inheritanceView);
    }

    /**
     * Changes the stroke color of the given class' view.
     * 
     * @param ramClass the class to change the stroke color of
     * @param color the color to change the stroke to
     */
    public void changeStrokeColorOfClassView(Class ramClass, MTColor color) {
        ClassifierView<?> view = classToViewMap.get(ramClass);
        view.setStrokeColor(color);
    }

    /**
     * Deselects all currently selected classifiers.
     */
    private void deselectClassifiers() {
        // Use separate set here, otherwise a concurrent modification occurs,
        // because the view notifies us that it was deselected, which triggers
        // the removal of the view from our set.
        for (BaseView<?> baseView : new HashSet<BaseView<?>>(selectedElements)) {
            baseView.setSelect(false);
        }

        selectedElements.clear();
    }

    /**
     * Removes the given {@link BaseView} from the list of selected views.
     * 
     * @param baseView the {@link BaseView} to remove
     */
    protected void elementDeselected(BaseView<?> baseView) {
        selectedElements.remove(baseView);
    }

    /**
     * Adds the given {@link BaseView} to the list of selected views.
     * 
     * @param baseView the baseView to add to the selection
     */
    protected void elementSelected(BaseView<?> baseView) {
        selectedElements.add(baseView);
    }

    /**
     * Deletes the view of the given classifier from this view.
     * 
     * @param classifier the {@link Classifier} to delete
     */
    private void deleteClassifier(final Classifier classifier) {
        RamApp.getApplication().invokeLater(new Runnable() {

            @Override
            public void run() {

                ClassifierView<?> classifierView = classToViewMap.remove(classifier);
                selectedElements.remove(classifierView);

                removeChild(classifierView);
                classifierView.destroy();
            }
        });
    }

    /**
     * Deletes some renum from view.
     * 
     * @param renum to be removed
     */
    private void deleteEnum(final REnum renum) {
        RamApp.getApplication().invokeLater(new Runnable() {

            @Override
            public void run() {
                EnumView enumView = enumToViewMap.remove(renum);

                removeChild(enumView);
                enumView.destroy();
            }
        });
    }

    /**
     * Deselects all classes.
     */
    public void deselect() {
        deselectClassifiers();
    }

    @Override
    public void destroy() {
        // remove listeners
        EMFEditUtil.removeListenerFor(layout, this);
        EMFEditUtil.removeListenerFor(structuralView, this);

        // destroy relationships first
        for (InheritanceView view : inheritanceViewList) {
            view.destroy();
        }

        for (AssociationView view : associationToViewMap.values()) {
            view.destroy();
        }

        // destroy class views
        for (ClassifierView<?> view : getClassifierViews()) {
            view.destroy();
        }

        // do rest
        super.destroy();
    }

    /**
     * Returns all classifier views of this view.
     * 
     * @return the {@link ClassifierView} contained in this view.
     */
    public Collection<ClassifierView<?>> getClassifierViews() {
        return classToViewMap.values();
    }

    /**
     * Gets the {@link ClassifierView} of the specified classifier.
     * 
     * @param specifiedClass the Classifier element for which we want to get the classifier view
     * @return {@link ClassifierView}
     */
    public ClassifierView<?> getClassViewOf(Classifier specifiedClass) {
        return classToViewMap.get(specifiedClass);
    }

    /**
     * Get an {@link EnumView} within this structural view with an {@link REnum}.
     * 
     * @param renum the {@link REnum} of which we want the enum view of
     * @return {@link EnumView} associated to the {@link REnum}
     */
    public EnumView getEnumView(REnum renum) {
        return enumToViewMap.get(renum);
    }

    /**
     * Returns all {@link ClassView}s contained in this structural view.
     * 
     * @return the {@link ClassView}s contained in this view
     */
    public Collection<ClassView> getClassViews() {
        Collection<ClassView> classViews = new HashSet<ClassView>();

        for (ClassifierView<?> classifierView : classToViewMap.values()) {
            if (classifierView instanceof ClassView) {
                classViews.add((ClassView) classifierView);
            }
        }

        return classViews;
    }

    /**
     * Returns a collection of all base views.
     * This includes {@link ClassView}, {@link ImplementationClassView} and {@link EnumView}.
     * 
     * @return a collection of all base views
     */
    public Collection<BaseView<?>> getBaseViews() {
        Collection<BaseView<?>> classifierViews = new HashSet<BaseView<?>>();

        classifierViews.addAll(classToViewMap.values());
        classifierViews.addAll(enumToViewMap.values());

        return classifierViews;
    }

    /**
     * Returns all currently selected {@link ClassifierView}s.
     * 
     * @return a set of all currently selected classifier views
     */
    public Set<BaseView<?>> getSelectedElements() {
        return selectedElements;
    }

    /**
     * Returns the {@link StructuralView} represented by this view.
     * 
     * @return the represented {@link StructuralView}
     */
    public StructuralView getStructuralView() {
        return structuralView;
    }

    @Override
    public void notifyChanged(Notification notification) {
        if (notification.getFeature() == RamPackage.Literals.STRUCTURAL_VIEW__CLASSES) {
            Classifier classifier = null;
            switch (notification.getEventType()) {
                case Notification.ADD:
                    classifier = (Classifier) notification.getNewValue();
                    if (classifier instanceof Class) {
                        addClass((Class) classifier, layout.getValue().get(classifier));
                    } else if (classifier instanceof ImplementationClass) {
                        addImplementationClass((ImplementationClass) classifier, layout.getValue().get(classifier));
                    }

                    // create an inheritance for all super types
                    for (Classifier superClass : classifier.getSuperTypes()) {
                        addInheritanceView(classifier, superClass);
                    }
                    break;
                case Notification.REMOVE:
                    classifier = (Classifier) notification.getOldValue();
                    deleteClassifier(classifier);
                    break;
            }
        } else if (notification.getFeature() == RamPackage.Literals.CONTAINER_MAP__VALUE) {
            if (notification.getEventType() == Notification.ADD) {
                ElementMapImpl elementMap = (ElementMapImpl) notification.getNewValue();
                if (elementMap.getKey() instanceof REnum) {
                    enumToViewMap.get(elementMap.getKey()).setLayoutElement(elementMap.getValue());
                } else if (elementMap.getKey() instanceof Classifier) {
                    classToViewMap.get(elementMap.getKey()).setLayoutElement(elementMap.getValue());
                    // since this is the point the layout is set we can show the keyboard here

                    // classToViewMap.get(elementMap.getKey()).showKeyboard();
                }
            }
        } else if (notification.getFeature() == RamPackage.Literals.STRUCTURAL_VIEW__ASSOCIATIONS) {
            switch (notification.getEventType()) {
                case Notification.REMOVE:
                    Association association = (Association) notification.getOldValue();
                    removeAssociationView(association);
                    break;
                case Notification.ADD:
                    association = (Association) notification.getNewValue();
                    addAssociationView(association);
                    break;
            }
        } else if (notification.getFeature() == RamPackage.Literals.STRUCTURAL_VIEW__TYPES) {
            // if enum
            if (notification.getNewValue() instanceof REnum || notification.getOldValue() instanceof REnum) {
                REnum renum = null;
                switch (notification.getEventType()) {
                    case Notification.ADD:
                        renum = (REnum) notification.getNewValue();
                        addEnum(renum, layout.getValue().get(renum), renum.getInstanceClassName() != null);
                        break;
                    case Notification.REMOVE:
                        renum = (REnum) notification.getOldValue();
                        deleteEnum(renum);
                        break;
                }

            }
        }
    }

    @Override
    protected void registerGestureListeners(IGestureEventListener listener) {
        super.registerGestureListeners(listener);

        addGestureListener(TapProcessor.class, listener);
        addGestureListener(TapAndHoldProcessor.class, listener);
        addGestureListener(TapAndHoldProcessor.class, new TapAndHoldVisualizer(RamApp.getApplication(), getParent()));
    }

    @Override
    protected void registerInputProcessors() {
        registerInputProcessor(new TapProcessor(RamApp.getApplication(), Constants.TAP_MAX_FINGER_UP, false,
                Constants.TAP_DOUBLE_TAP_TIME));
        registerInputProcessor(new TapAndHoldProcessor(RamApp.getApplication(), Constants.TAP_AND_HOLD_DURATION));
        registerInputProcessor(new PanProcessorTwoFingers(RamApp.getApplication()));
        registerInputProcessor(new RightClickDragProcessor(RamApp.getApplication()));
        registerInputProcessor(new ZoomProcessor(RamApp.getApplication()));
        registerInputProcessor(new MouseWheelProcessor(RamApp.getApplication()));

        UnistrokeProcessorLeftClick up = new UnistrokeProcessorLeftClick(RamApp.getApplication());
        up.addTemplate(UnistrokeGesture.CIRCLE, Direction.CLOCKWISE);
        up.addTemplate(UnistrokeGesture.CIRCLE, Direction.COUNTERCLOCKWISE);
        up.addTemplate(UnistrokeGesture.RECTANGLE, Direction.CLOCKWISE);
        up.addTemplate(UnistrokeGesture.RECTANGLE, Direction.COUNTERCLOCKWISE);
        up.addTemplate(UnistrokeGesture.CHECK, Direction.CLOCKWISE);
        up.addTemplate(UnistrokeGesture.TRIANGLE, Direction.COUNTERCLOCKWISE);
        up.addTemplate(UnistrokeGesture.TRIANGLE, Direction.CLOCKWISE);
        up.addTemplate(UnistrokeGesture.QUESTION, Direction.CLOCKWISE);
        up.addTemplate(UnistrokeGesture.X, Direction.CLOCKWISE);
        up.addTemplate(UnistrokeGesture.X, Direction.COUNTERCLOCKWISE);

        registerInputProcessor(up);
    }

    /**
     * Removes the {@link AssociationView} for the given {@link Association}.
     * 
     * @param association the association that was deleted
     */
    public void removeAssociationView(Association association) {
        AssociationView assocView = associationToViewMap.get(association);
        RamEnd<AssociationEnd, ClassifierView<?>> ramEnd = assocView.getFromEnd();
        RamEnd<AssociationEnd, ClassifierView<?>> ramEndOpposite = assocView.getToEnd();
        ClassifierView<?> classViewFrom = ramEnd.getComponentView();
        ClassifierView<?> classViewTo = ramEndOpposite.getComponentView();

        removeChild(assocView);
        associationToViewMap.remove(association);

        assocView.destroy();

        classViewFrom.removeRelationshipEnd(ramEnd);
        classViewTo.removeRelationshipEnd(ramEndOpposite);
    }

    /**
     * Removes the {@link InheritanceView} between the given super and sub class.
     * 
     * @param derivedClass the sub class
     * @param superClass the super class
     */
    public void removeInheritanceView(Classifier derivedClass, Classifier superClass) {
        InheritanceView inherViewToBeDeleted = null;

        for (InheritanceView inherView : inheritanceViewList) {

            Classifier fromRamClass = inherView.getFromEnd().getModel();
            Classifier toRamClass = inherView.getToEnd().getModel();

            if (fromRamClass == derivedClass && toRamClass == superClass) {
                inherViewToBeDeleted = inherView;
                break;
            }
        }

        RamEnd<Classifier, ClassifierView<?>> ramEndFrom = inherViewToBeDeleted.getFromEnd();
        RamEnd<Classifier, ClassifierView<?>> ramEndTo = inherViewToBeDeleted.getToEnd();

        ClassifierView<?> classViewFrom = ramEndFrom.getComponentView();
        ClassifierView<?> classViewTo = ramEndTo.getComponentView();

        inheritanceViewList.remove(inherViewToBeDeleted);
        removeChild(inherViewToBeDeleted);

        inherViewToBeDeleted.destroy();

        classViewFrom.removeRelationshipEnd(ramEndFrom);
        classViewTo.removeRelationshipEnd(ramEndTo);

    }

    /**
     * Sets the fill and stroke color of all class views to the given fill and stroke color.
     * 
     * @param colorFill the fill color
     * @param colorStroke the stroke color
     */
    public void setFillAndStrokeColorOfAllClasses(MTColor colorFill, MTColor colorStroke) {
        for (ClassifierView<?> view : getClassifierViews()) {
            view.setFillColor(colorFill);
            view.setStrokeColor(colorStroke);
        }
    }

    /**
     * Overloaded method to also update the association's background color.
     * 
     * @param color
     *            The new background color
     */
    @Override
    public void setFillColor(MTColor color) {
        super.setFillColor(color);

        for (AssociationView a : associationToViewMap.values()) {
            a.setBackgroundColor(color);
        }
    }
}
