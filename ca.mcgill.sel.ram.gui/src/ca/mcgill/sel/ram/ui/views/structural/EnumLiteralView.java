package ca.mcgill.sel.ram.ui.views.structural;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.INotifyChangedListener;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.ram.REnumLiteral;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.components.RamSpacerComponent;
import ca.mcgill.sel.ram.ui.layouts.HorizontalLayoutVerticallyCentered;
import ca.mcgill.sel.ram.ui.utils.Fonts;
import ca.mcgill.sel.ram.ui.views.TextView;
import ca.mcgill.sel.ram.ui.views.handler.HandlerFactory;
import ca.mcgill.sel.ram.ui.views.handler.IHandled;
import ca.mcgill.sel.ram.ui.views.structural.handler.IEnumLiteralViewHandler;

/**
 * This view draws the RAM representation of a REnumLiteral onto a {@link EnumView}. It only contains a name field.
 * 
 * @author Franz
 * 
 */
public class EnumLiteralView extends RamRectangleComponent implements INotifyChangedListener,
        IHandled<IEnumLiteralViewHandler> {

    private static final int LITERAL_FRONT_SPACE = 10;

    private TextView nameField;

    private REnumLiteral literal;

    private boolean isMutable;

    private IEnumLiteralViewHandler handler;

    /**
     * Constructor. Builds the enum literal view.
     * 
     * @param enumView {@link EnumView} the owner of this literal
     * @param literal {@link REnumLiteral} the model of this view
     * @param mutable true if the enum literal can be modified.
     */
    public EnumLiteralView(EnumView enumView, REnumLiteral literal, boolean mutable) {
        this.isMutable = mutable;
        this.literal = literal;
        addChild(new RamSpacerComponent(LITERAL_FRONT_SPACE, Fonts.FONT_SIZE));

        // Create name field for the literal
        nameField = new TextView(literal, CorePackage.Literals.CORE_NAMED_ELEMENT__NAME);
        nameField.setBufferSize(Cardinal.SOUTH, 0);
        nameField.setBufferSize(Cardinal.WEST, 0);
        nameField.setUniqueName(true);
        addChild(nameField);
        if (isMutable) {
            nameField.setHandler(HandlerFactory.INSTANCE.getEnumLiteralNameHandler());
        }
        // set layout
        setLayout(new HorizontalLayoutVerticallyCentered(0));

        EMFEditUtil.addListenerFor(literal, this);

    }

    /**
     * Getter for {@link REnumLiteral} associated to this view.
     * 
     * @return {@link REnumLiteral} associated to this view.
     */
    public REnumLiteral getLiteral() {
        return literal;
    }

    @Override
    public void setHandler(IEnumLiteralViewHandler handler) {
        this.handler = handler;
    }

    @Override
    public void destroy() {
        super.destroy();

        EMFEditUtil.removeListenerFor(literal, this);
    }

    @Override
    public IEnumLiteralViewHandler getHandler() {
        return handler;
    }

    @Override
    public void notifyChanged(Notification notification) {
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
