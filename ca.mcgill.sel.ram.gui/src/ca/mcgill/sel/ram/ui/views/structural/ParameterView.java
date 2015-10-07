package ca.mcgill.sel.ram.ui.views.structural;

import ca.mcgill.sel.commons.emf.util.EMFModelUtil;
import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.Parameter;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.layouts.HorizontalLayoutVerticallyCentered;
import ca.mcgill.sel.ram.ui.utils.Fonts;
import ca.mcgill.sel.ram.ui.utils.GraphicalUpdater;
import ca.mcgill.sel.ram.ui.views.TextView;
import ca.mcgill.sel.ram.ui.views.handler.HandlerFactory;
import ca.mcgill.sel.ram.ui.views.handler.IHandled;
import ca.mcgill.sel.ram.ui.views.structural.handler.IParameterViewHandler;

/**
 * A {@link ParameterView} represents a {@link Parameter} and is contained inside an {@link OperationView}.
 * 
 * @author mschoettle
 */
public class ParameterView extends RamRectangleComponent implements IHandled<IParameterViewHandler> {

    private TextView nameField;
    private TextView typeField;

    private Parameter parameter;

    private boolean isMutable;

    private GraphicalUpdater graphicalUpdater;

    private IParameterViewHandler handler;

    /**
     * Creates a new {@link ParameterView} for the given {@link Parameter}.
     * 
     * @param operationView the parent {@link OperationView}
     * @param parameter the {@link Parameter} to be represented
     * @param mutable is this parameter view mutable
     */
    public ParameterView(OperationView operationView, Parameter parameter, boolean mutable) {
        this.parameter = parameter;
        setBuffers(0);

        isMutable = mutable;

        typeField = new TextView(parameter, RamPackage.Literals.PARAMETER__TYPE);
        typeField.setBufferSize(Cardinal.SOUTH, OperationView.BUFFER_BOTTOM);
        typeField.setBufferSize(Cardinal.WEST, 2);
        typeField.setBufferSize(Cardinal.EAST, 2);
        addChild(typeField);

        nameField = new TextView(parameter, CorePackage.Literals.CORE_NAMED_ELEMENT__NAME);
        nameField.setBufferSize(Cardinal.SOUTH, OperationView.BUFFER_BOTTOM);
        nameField.setBufferSize(Cardinal.EAST, 2);
        nameField.setBufferSize(Cardinal.WEST, 2);
        nameField.setUniqueName(true);
        addChild(nameField);

        setLayout(new HorizontalLayoutVerticallyCentered(Fonts.FONT_SIZE / 8));

        if (isMutable) {
            typeField.setHandler(HandlerFactory.INSTANCE.getTextViewHandler());
            nameField.setHandler(HandlerFactory.INSTANCE.getParameterNameHandler());
        }

        Aspect aspect = EMFModelUtil.getRootContainerOfType(parameter, RamPackage.Literals.ASPECT);
        graphicalUpdater = RamApp.getApplication().getGraphicalUpdaterForAspect(aspect);
        graphicalUpdater.addGUListener(parameter, this);
    }

    /**
     * Constructor. Makes a default mutable parameter.
     * 
     * @param operationView Owner of this parameter
     * @param parameter Parameter to add the the operationview
     */
    public ParameterView(OperationView operationView, Parameter parameter) {
        this(operationView, parameter, true);
    }

    @Override
    public void destroy() {
        super.destroy();

        graphicalUpdater.removeGUListener(parameter, this);
    }

    /**
     * Returns the {@link Parameter} that is represented by this view.
     * 
     * @return the represented {@link Parameter}
     */
    public Parameter getParameter() {
        return parameter;
    }

    @Override
    public IParameterViewHandler getHandler() {
        return this.handler;
    }

    @Override
    public void setHandler(IParameterViewHandler handler) {
        this.handler = handler;
    }

    /**
     * Returns the name component.
     * 
     * @return the name component
     */
    public TextView getNameField() {
        return nameField;
    }

}
