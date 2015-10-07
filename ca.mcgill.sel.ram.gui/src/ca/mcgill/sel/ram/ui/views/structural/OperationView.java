package ca.mcgill.sel.ram.ui.views.structural;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.mt4j.components.MTComponent;
import org.mt4j.util.font.IFont;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.commons.emf.util.EMFModelUtil;
import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.Classifier;
import ca.mcgill.sel.ram.ImplementationClass;
import ca.mcgill.sel.ram.Operation;
import ca.mcgill.sel.ram.OperationType;
import ca.mcgill.sel.ram.Parameter;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.components.RamTextComponent;
import ca.mcgill.sel.ram.ui.layouts.HorizontalLayoutVerticallyCentered;
import ca.mcgill.sel.ram.ui.utils.Fonts;
import ca.mcgill.sel.ram.ui.utils.GraphicalUpdater;
import ca.mcgill.sel.ram.ui.views.TextView;
import ca.mcgill.sel.ram.ui.views.handler.HandlerFactory;
import ca.mcgill.sel.ram.ui.views.handler.IHandled;
import ca.mcgill.sel.ram.ui.views.structural.handler.IOperationViewHandler;

/**
 * A horizontal list of texts that represent a RAM {@link Operation}. They update according to the changes in the model.
 *
 * @author vbonnet
 * @author mschoettle
 */
public class OperationView extends RamRectangleComponent implements INotifyChangedListener,
        IHandled<IOperationViewHandler> {

    /**
     * The buffer on the bottom (south) for each child).
     */
    public static final int BUFFER_BOTTOM = 0;

    /**
     * The textual representation of a parameter delimiter in an operations signature.
     */
    private static final String PARAMETER_DELIMITER = ",";

    private static final String BRACKET_OPEN = "(";
    private static final String BRACKET_CLOSE = ")";

    private TextView nameField;
    private TextView returnTypeField;
    private TextView visibilityField;
    private RamTextComponent openBracketComponent;
    private RamTextComponent closeBracketComponent;

    private Operation operation;
    private Map<Parameter, ParameterView> parameters;

    private GraphicalUpdater graphicalUpdater;

    private IOperationViewHandler handler;
    private boolean isMutable;

    /**
     * Creates an operation view.
     *
     * @param classifierView the {@link ClassifierView} that contains this view.
     * @param operation The operation to display.
     * @param mutable Allow the operation view to be editable.
     */
    public OperationView(ClassifierView<?> classifierView, Operation operation, boolean mutable) {
        this.operation = operation;
        parameters = new HashMap<Parameter, ParameterView>();

        isMutable = mutable;

        visibilityField = new TextView(operation, RamPackage.Literals.OPERATION__EXTENDED_VISIBILITY);
        visibilityField.setBufferSize(Cardinal.SOUTH, BUFFER_BOTTOM);
        addChild(visibilityField);

        if (operation.getOperationType() == OperationType.NORMAL) {
            returnTypeField = new TextView(operation, RamPackage.Literals.OPERATION__RETURN_TYPE);
            returnTypeField.setBufferSize(Cardinal.SOUTH, BUFFER_BOTTOM);
            returnTypeField.setBufferSize(Cardinal.WEST, 0);
            addChild(returnTypeField);
            returnTypeField.setUnderlined(operation.isStatic());
        }

        nameField = new TextView(operation, CorePackage.Literals.CORE_NAMED_ELEMENT__NAME);
        nameField.setBufferSize(Cardinal.SOUTH, BUFFER_BOTTOM);
        nameField.setBufferSize(Cardinal.WEST, 1);
        nameField.setBufferSize(Cardinal.EAST, 1);
        nameField.setUniqueName(true);
        addChild(nameField);
        nameField.setUnderlined(operation.isStatic());

        openBracketComponent = new RamTextComponent(BRACKET_OPEN);
        openBracketComponent.setBufferSize(Cardinal.SOUTH, BUFFER_BOTTOM);
        openBracketComponent.setBufferSize(Cardinal.EAST, 1);
        openBracketComponent.setBufferSize(Cardinal.WEST, 1);
        addChild(openBracketComponent);

        for (Parameter parameter : operation.getParameters()) {

            addParameter(parameter, getChildCount());

            boolean lastParameter =
                    operation.getParameters().indexOf(parameter) == operation.getParameters().size() - 1;
            if (!lastParameter) {
                addDelimiter(getChildCount());
            }
        }

        closeBracketComponent = new RamTextComponent(BRACKET_CLOSE);
        closeBracketComponent.setBufferSize(Cardinal.SOUTH, BUFFER_BOTTOM);
        closeBracketComponent.setBufferSize(Cardinal.WEST, 1);
        addChild(closeBracketComponent);

        setLayout(new HorizontalLayoutVerticallyCentered(0));

        EMFEditUtil.addListenerFor(operation, this);
        Aspect aspect = EMFModelUtil.getRootContainerOfType(operation, RamPackage.Literals.ASPECT);
        graphicalUpdater = RamApp.getApplication().getGraphicalUpdaterForAspect(aspect);
        graphicalUpdater.addGUListener(operation, this);

        if (isMutable) {
            visibilityField.setHandler(HandlerFactory.INSTANCE.getOperationVisibilityHandlerHandler());
            // Prevent changes to constructors and destructors.
            if (operation.getOperationType() == OperationType.NORMAL) {
                returnTypeField.setHandler(HandlerFactory.INSTANCE.getTextViewHandler());
            }
            nameField.setHandler(HandlerFactory.INSTANCE.getOperationNameHandler());
        }

        setOperationItalic(this.operation.isAbstract());
    }

    /**
     * Creates an editable operation view.
     *
     * @param classifierView the {@link ClassifierView} that contains this view.
     * @param operation The operation to display.
     */
    public OperationView(ClassifierView<?> classifierView, Operation operation) {
        this(classifierView, operation, true);
    }

    /**
     * Returns true if the operation can be edited.
     * 
     * @return true if the operation can be edited
     */
    public boolean isMutable() {
        return isMutable;
    }

    /**
     * Adds a parameter view for the given parameter at the given index.
     *
     * @param parameter the {@link Parameter} to add a view for
     * @param index the index to add the parameter at
     */
    private void addParameter(Parameter parameter, int index) {
        ParameterView parameterView = new ParameterView(this, parameter, isMutable);
        Classifier classifier = (Classifier) parameter.eContainer().eContainer();
        if (!(classifier instanceof ImplementationClass)) {
            parameterView.setHandler(HandlerFactory.INSTANCE.getParameterHandler());
        }
        parameterView.setUnderlined(operation.isStatic());
        parameters.put(parameter, parameterView);
        addChild(index, parameterView);
    }

    /**
     * Adds a parameter delimiter at the given index.
     *
     * @param index the index the delimiter to add at
     */
    public void addDelimiter(int index) {
        RamTextComponent delimiter = new RamTextComponent(PARAMETER_DELIMITER);
        delimiter.setBufferSize(Cardinal.SOUTH, BUFFER_BOTTOM);
        delimiter.setBufferSize(Cardinal.WEST, 0);
        delimiter.setBufferSize(Cardinal.EAST, 2);
        addChild(index, delimiter);
    }

    @Override
    public void destroy() {
        super.destroy();

        graphicalUpdater.removeGUListener(operation, this);

        EMFEditUtil.removeListenerFor(operation, this);
    }

    @Override
    public IOperationViewHandler getHandler() {
        return handler;
    }

    /**
     * Returns the view parameter for the given parameter.
     *
     * @param parameter the parameter
     * @param position the position in the model
     * @return the view index for the parameter view
     */
    private int getIndexForParameter(Parameter parameter, int position) {
        int index = getChildIndexOf(openBracketComponent) + 1;

        if (position > 0) {
            Parameter previous = ((Operation) parameter.eContainer()).getParameters().get(position - 1);
            ParameterView visualPrevious = parameters.get(previous);
            index = visualPrevious.getParent().getChildIndexOf(visualPrevious) + 1;
        }

        return index;
    }

    /**
     * Returns the {@link Operation} that this view represents.
     *
     * @return the {@link Operation} represented by this view
     */
    public Operation getOperation() {
        return operation;
    }

    @Override
    public void notifyChanged(Notification notification) {
        if (notification.getNotifier() == operation) {
            if (notification.getFeature() == RamPackage.Literals.OPERATION__STATIC) {
                if (notification.getEventType() == Notification.SET) {
                    boolean newValue = notification.getNewBooleanValue();

                    for (int i = 1; i < getChildCount(); i++) {
                        MTComponent child = getChildByIndex(i);
                        RamRectangleComponent text = (RamRectangleComponent) child;
                        text.setUnderlined(newValue);
                    }
                }
            } else if (notification.getFeature() == RamPackage.Literals.OPERATION__ABSTRACT) {
                boolean newValue = notification.getNewBooleanValue();
                setOperationItalic(newValue);

            } else if (notification.getFeature() == RamPackage.Literals.OPERATION__PARAMETERS) {
                switch (notification.getEventType()) {
                    case Notification.ADD:
                        Parameter parameter = (Parameter) notification.getNewValue();
                        // figure out the index where the parameter has to be added
                        int visualIndex = getIndexForParameter(parameter, notification.getPosition());
                        if (!parameters.isEmpty()) {
                            // if it is the first parameter we need to add the delimiter after
                            if (notification.getPosition() == 0) {
                                addDelimiter(visualIndex);
                            } else {
                                // otherwise we need it before the parameter view
                                addDelimiter(visualIndex++);
                            }
                        }
                        addParameter(parameter, visualIndex);
                        break;
                    case Notification.REMOVE:
                        parameter = (Parameter) notification.getOldValue();
                        removeParameter(parameter);
                        break;
                }
            }
        }
    }

    /**
     * Set all the operation signature in italic.
     *
     * @param italic true if you want to set operation in italic. false otherwise.
     */
    private void setOperationItalic(boolean italic) {
        IFont font;
        if (italic) {
            font = Fonts.DEFAULT_FONT_ITALIC;
        } else {
            font = Fonts.DEFAULT_FONT;
        }

        for (int i = 1; i < getChildCount(); i++) {
            MTComponent child = getChildByIndex(i);
            if (child instanceof RamTextComponent) {
                RamTextComponent childRectangle = (RamTextComponent) child;
                childRectangle.setFont(font);
            } else if (child instanceof ParameterView) {
                ParameterView childRectangle = (ParameterView) child;
                for (int j = 0; j < childRectangle.getChildCount(); j++) {
                    MTComponent childChild = childRectangle.getChildByIndex(j);
                    if (childChild instanceof RamTextComponent) {
                        RamTextComponent childChildText = (RamTextComponent) childChild;
                        childChildText.setFont(font);
                    }
                }

            }
        }
    }

    /**
     * Removes the view of the given parameter.
     *
     * @param parameter the parameter to remove the view for
     */
    private void removeParameter(final Parameter parameter) {
        if (parameters.containsKey(parameter)) {
            RamApp.getApplication().invokeLater(new Runnable() {

                @Override
                public void run() {
                    ParameterView parameterView = parameters.remove(parameter);

                    int index = getChildIndexOf(parameterView);
                    removeChild(parameterView);

                    if (!parameters.isEmpty()) {
                        // also remove the delimiter after if there is one
                        if (getChildByIndex(index) != closeBracketComponent) {
                            removeChild(index);
                        } else {
                            // and the last delimiter
                            removeChild(index - 1);
                        }
                    }

                    parameterView.destroy();
                }
            });
        }
    }

    @Override
    public void setHandler(IOperationViewHandler handler) {
        this.handler = handler;
    }

    /**
     * Returns the close bracket component.
     * 
     * @return the close bracket component.
     */
    public RamTextComponent getCloseBracketComponent() {
        return closeBracketComponent;
    }

    /**
     * Returns the name field component.
     * 
     * @return the name field component.
     */
    public TextView getNameField() {
        return nameField;
    }

}
