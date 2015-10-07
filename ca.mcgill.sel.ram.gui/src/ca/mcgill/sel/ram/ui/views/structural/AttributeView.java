package ca.mcgill.sel.ram.ui.views.structural;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.mt4j.components.MTComponent;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.commons.emf.util.EMFModelUtil;
import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.Attribute;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.components.RamTextComponent;
import ca.mcgill.sel.ram.ui.layouts.HorizontalLayoutVerticallyCentered;
import ca.mcgill.sel.ram.ui.utils.GraphicalUpdater;
import ca.mcgill.sel.ram.ui.utils.Strings;
import ca.mcgill.sel.ram.ui.views.TextView;
import ca.mcgill.sel.ram.ui.views.handler.HandlerFactory;
import ca.mcgill.sel.ram.ui.views.handler.IHandled;
import ca.mcgill.sel.ram.ui.views.structural.handler.IAttributeViewHandler;

/**
 * Shows the type and name of an attribute. Displays the visibility of an attribute always as private.
 * 
 * @author mschoettle
 */
public class AttributeView extends RamRectangleComponent implements INotifyChangedListener,
        IHandled<IAttributeViewHandler> {

    private TextView nameField;
    private TextView typeField;

    private Attribute attribute;

    private GraphicalUpdater graphicalUpdater;

    private IAttributeViewHandler handler;

    /**
     * Creates an attribute view for the given {@link Attribute}.
     * 
     * @param classView
     *            the {@link ClassView} which contains this view.
     * @param attribute
     *            the {@link Attribute} that should be represented
     */
    public AttributeView(ClassView classView, Attribute attribute) {
        this.attribute = attribute;

        RamTextComponent privateModifier = new RamTextComponent(Strings.SYMBOL_PRIVATE);
        privateModifier.setBufferSize(Cardinal.SOUTH, 0);
        addChild(privateModifier);

        typeField = new TextView(attribute, RamPackage.Literals.ATTRIBUTE__TYPE);
        typeField.setBufferSize(Cardinal.SOUTH, 0);
        typeField.setBufferSize(Cardinal.WEST, 0);
        addChild(typeField);
        typeField.setHandler(HandlerFactory.INSTANCE.getTextViewHandler());

        nameField = new TextView(attribute, CorePackage.Literals.CORE_NAMED_ELEMENT__NAME);
        nameField.setBufferSize(Cardinal.SOUTH, 0);
        nameField.setBufferSize(Cardinal.WEST, 0);
        nameField.setUniqueName(true);
        addChild(nameField);
        nameField.setHandler(HandlerFactory.INSTANCE.getAttributeNameHandler());

        setLayout(new HorizontalLayoutVerticallyCentered(0));

        setUnderlined(attribute.isStatic());

        EMFEditUtil.addListenerFor(attribute, this);
        Aspect aspect = EMFModelUtil.getRootContainerOfType(attribute, RamPackage.Literals.ASPECT);
        graphicalUpdater = RamApp.getApplication().getGraphicalUpdaterForAspect(aspect);
        graphicalUpdater.addGUListener(attribute, this);
    }

    @Override
    public void destroy() {
        super.destroy();

        graphicalUpdater.removeGUListener(attribute, this);

        EMFEditUtil.removeListenerFor(attribute, this);
    }

    /**
     * Returns the {@link Attribute} that this view represents.
     * 
     * @return the {@link Attribute} represented by this view
     */
    public Attribute getAttribute() {
        return attribute;
    }

    @Override
    public IAttributeViewHandler getHandler() {
        return handler;
    }

    @Override
    public void notifyChanged(Notification notification) {
        // this gets called for every view (as there is only one item provider)
        // therefore an additional check is necessary
        if (notification.getNotifier() == attribute) {
            if (notification.getFeature() == RamPackage.Literals.STRUCTURAL_FEATURE__STATIC) {
                if (notification.getEventType() == Notification.SET) {
                    boolean newValue = notification.getNewBooleanValue();

                    setUnderlined(newValue);
                }
            }
        }
    }

    @Override
    public void setUnderlined(boolean underlined) {
        for (int i = 1; i < getChildCount(); i++) {
            MTComponent child = getChildByIndex(i);
            RamRectangleComponent text = (RamRectangleComponent) child;
            text.setUnderlined(underlined);
        }
    }

    @Override
    public void setHandler(IAttributeViewHandler handler) {
        this.handler = handler;
    }

    /**
     * Returns the name field view.
     * 
     * @return the name field view
     */
    public TextView getNameField() {
        return nameField;
    }

}
