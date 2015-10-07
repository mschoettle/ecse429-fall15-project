package ca.mcgill.sel.ram.ui.views.structural;

import org.eclipse.emf.common.notify.Notification;
import org.mt4j.util.MTColor;

import ca.mcgill.sel.commons.emf.util.EMFModelUtil;
import ca.mcgill.sel.core.COREModelReuse;
import ca.mcgill.sel.core.COREVisibilityType;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.Attribute;
import ca.mcgill.sel.ram.Class;
import ca.mcgill.sel.ram.Classifier;
import ca.mcgill.sel.ram.ClassifierMapping;
import ca.mcgill.sel.ram.Instantiation;
import ca.mcgill.sel.ram.LayoutElement;
import ca.mcgill.sel.ram.Operation;
import ca.mcgill.sel.ram.OperationMapping;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.utils.Fonts;
import ca.mcgill.sel.ram.ui.views.TextView;
import ca.mcgill.sel.ram.ui.views.handler.HandlerFactory;
import ca.mcgill.sel.ram.ui.views.structural.handler.IClassViewHandler;
import ca.mcgill.sel.ram.util.AssociationConstants;

/**
 * This view draws the RAM representation of a Class onto a {@link StructuralDiagramView}. It contains a name field, and
 * two lists for methods and attributes. ClassViews are inspectable and create a {@link ClassInspectorView} when click
 * on.
 *
 * @author vbonnet
 * @author eyildirim
 * @author mschoettle
 */
public class ClassView extends ClassifierView<IClassViewHandler> {

    private Class ramClass;

    /**
     * Whether not public operations should be displayed or not.
     */
    private boolean showAllOperations;

    /**
     * Creates a new view representing the given class.
     *
     * @param structuralDiagram the {@link StructuralDiagramView} that owns this view
     * @param clazz The RAM class to display.
     * @param layoutElement The position at which to display it.
     */
    public ClassView(StructuralDiagramView structuralDiagram, Class clazz, LayoutElement layoutElement) {
        super(structuralDiagram, clazz, layoutElement);
        ramClass = clazz;
        showAllOperations = false;

        setClassNameItalic(ramClass.isAbstract());
    }

    /**
     * Adds an attribute view for the given attribute at the given index. The index corresponds to the position in the
     * container of attributes.
     *
     * @param index the index where to add the view at
     * @param attribute the attribute to add an attribute for
     */
    private void addAttributeField(int index, Attribute attribute) {
        AttributeView attributeView = new AttributeView(this, attribute);
        attributes.put(attribute, attributeView);

        attributesContainer.addChild(index, attributeView);
        attributeView.setHandler(HandlerFactory.INSTANCE.getAttributeViewHandler());
    }

    /**
     * Returns the component containing all {@link AttributeView}s.
     *
     * @return the {@link RamRectangleComponent} containing all {@link AttributeView}s
     */
    public RamRectangleComponent getAttributesContainer() {
        return attributesContainer;
    }

    /**
     * Returns the class this view is visualizing.
     *
     * @return the ram {@link Class} this view is displaying.
     */
    public Class getRamClass() {
        return ramClass;
    }

    @Override
    protected void initializeClass(Classifier classifier) {
        ramClass = (Class) classifier;

        for (int i = 0; i < ramClass.getAttributes().size(); i++) {
            Attribute attr = ramClass.getAttributes().get(i);
            addAttributeField(i, attr);
        }

        initializeOperations(classifier);

        updateOperationVisibility();
    }

    @Override
    public void notifyChanged(Notification notification) {
        // handle it first by the super class
        super.notifyChanged(notification);

        // this gets called for every ClassView (as there is only one item provider)
        // therefore an additional check is necessary

        if (notification.getNotifier() == ramClass) {
            if (notification.getFeature() == RamPackage.Literals.CLASS__ATTRIBUTES) {
                switch (notification.getEventType()) {
                    case Notification.REMOVE:
                        Attribute attribute = (Attribute) notification.getOldValue();
                        removeAttribute(attribute);
                        break;
                    case Notification.ADD:
                        attribute = (Attribute) notification.getNewValue();
                        addAttributeField(notification.getPosition(), attribute);
                        break;
                }
            } else if (notification.getFeature() == RamPackage.Literals.CLASS__ABSTRACT) {
                boolean italic = notification.getNewBooleanValue();
                setClassNameItalic(italic);
            }
        }
    }

    /**
     * Set the class name in italic.
     *
     * @param italic true if you want to set the class name in italic. false otherwise.
     */
    private void setClassNameItalic(boolean italic) {
        if (italic) {
            nameField.setFont(Fonts.FONT_CLASS_NAME_ITALIC);
        } else {
            nameField.setFont(Fonts.FONT_CLASS_NAME);
        }
    }

    /**
     * Removes the view of the given attribute.
     *
     * @param attribute the {@link Attribute} to remove the view for
     */
    private void removeAttribute(final Attribute attribute) {
        if (attributes.containsKey(attribute)) {
            RamApp.getApplication().invokeLater(new Runnable() {

                @Override
                public void run() {
                    AttributeView attributeView = attributes.remove(attribute);

                    attributesContainer.removeChild(attributeView);
                    attributeView.destroy();
                }
            });
        }
    }

    /**
     * Sets color of the all attribute views to default attribute view color and setNoFill, setNoStroke to true.
     */
    public void setDefaultFillAndStrokeColorForAllAttributes() {
        for (AttributeView attView : attributes.values()) {
            attView.setFillColor(Colors.ATTRIBUTE_VIEW_DEFAULT_FILL_COLOR);
            attView.setStrokeColor(Colors.ATTRIBUTE_VIEW_DEFAULT_STROKE_COLOR);
            attView.setNoFill(true);
            attView.setNoStroke(true);
        }
    }

    /**
     * Sets color of the all operation views to default operation view color and setNoFill, setNoStroke to true.
     */
    public void setDefaultFillAndStrokeColorForAllOperations() {
        for (OperationView opView : operations.values()) {
            opView.setFillColor(Colors.OPERATION_VIEW_DEFAULT_FILL_COLOR);
            opView.setStrokeColor(Colors.OPERATION_VIEW_DEFAULT_STROKE_COLOR);
            opView.setNoFill(true);
            opView.setNoStroke(true);
        }
    }

    /**
     * Sets the fill and stroke color of the view representing the given {@link Attribute}.
     *
     * @param attributeToBeColored the {@link Attribute} whose view should be changed
     * @param colorFill the fill color to set
     * @param colorStroke the stroke color to set
     */
    public void setFillandStrokeColorOfAttribute(Attribute attributeToBeColored,
            MTColor colorFill, MTColor colorStroke) {
        AttributeView attributeView = attributes.get(attributeToBeColored);
        attributeView.setFillColor(colorFill);
        attributeView.setStrokeColor(colorStroke);

        attributeView.setNoFill(false);
        attributeView.setNoStroke(false);
    }

    /**
     * Sets the fill and stroke color of the given operation to the given colors.
     *
     * @param operationToBeColored OperationView of this operation will be colored.
     * @param colorFill fill color of the operation view.
     * @param colorStroke stroke color of the operation view.
     */
    public void setFillandStrokeColorOfOperation(Operation operationToBeColored,
            MTColor colorFill, MTColor colorStroke) {
        OperationView operationView = operations.get(operationToBeColored);
        operationView.setFillColor(colorFill);
        operationView.setStrokeColor(colorStroke);

        operationView.setNoFill(false);
        operationView.setNoStroke(false);
    }

    /**
     * Sets Fill color of the class view to default fill color.
     */
    public void setFillColorToDefault() {
        setFillColor(Colors.CLASS_VIEW_DEFAULT_FILL_COLOR);
    }

    /**
     * Displays the keyboard.
     */
    public void showKeyboard() {
        nameField.showKeyboard();
    }

    /**
     * Clear the name field of this view.
     *
     */
    public void clearNameField() {
        ((TextView) nameField).clearText();
    }

    /**
     * Displays/Hide operations that are not public.
     */
    public void collapseOperations() {
        showAllOperations = !showAllOperations;
        if (showAllOperations) {
            for (int i = 0; i < classifier.getOperations().size(); i++) {
                Operation operation = classifier.getOperations().get(i);
                if (!this.containsChild(operations.get(operation))) {
                    operationsContainer.addChild(i, operations.get(operation));
                }
            }
        } else {
            updateOperationVisibility();
        }
    }

    /**
     * Update operation visibility by going through all instantiations.
     */
    private void updateOperationVisibility() {
        for (COREModelReuse modelReuse : ((Aspect) ramClass.eContainer().eContainer()).getModelReuses()) {
            if (AssociationConstants.CONCERN_NAME.equals(modelReuse.getReuse().getReusedConcern().getName())) {
                for (ClassifierMapping classifierMapping
                    : ((Instantiation) modelReuse.getCompositions().get(0)).getMappings()) {
                    if (classifierMapping.getTo() == ramClass) {
                        updateOperationVisibility(classifierMapping);
                    }
                }
            }
        }
    }

    /**
     * Update the operation visibility with a given classifier.
     * If an operation is mapped, is not public and is not used in message views, it will be hidden.
     * @param classifierMapping The classifier mapping for which we want to hide mapped operations
     */
    public void updateOperationVisibility(ClassifierMapping classifierMapping) {
        for (OperationMapping operationMapping : classifierMapping.getOperationMappings()) {
            if (operations.containsKey(operationMapping.getTo())
                    && !EMFModelUtil.referencedInFeature(
                    operationMapping.getTo(), RamPackage.Literals.MESSAGE__SIGNATURE)
                    && operationMapping.getTo().getVisibility() != COREVisibilityType.PUBLIC) {
                operationsContainer.removeChild(operations.get(operationMapping.getTo()));
            }
        }
    }

    /**
     * Returns the showAllOperations boolean attribute.
     * @return the showAllOperations boolean attribute
     */
    public boolean isShowingAllOperations() {
        return this.showAllOperations;
    }

}
