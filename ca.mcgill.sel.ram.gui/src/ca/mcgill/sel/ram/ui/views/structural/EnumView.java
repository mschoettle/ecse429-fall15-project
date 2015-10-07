package ca.mcgill.sel.ram.ui.views.structural;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;

import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.ram.Classifier;
import ca.mcgill.sel.ram.LayoutElement;
import ca.mcgill.sel.ram.REnum;
import ca.mcgill.sel.ram.REnumLiteral;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.components.RamTextComponent;
import ca.mcgill.sel.ram.ui.components.RamTextComponent.Alignment;
import ca.mcgill.sel.ram.ui.layouts.VerticalLayout;
import ca.mcgill.sel.ram.ui.utils.Fonts;
import ca.mcgill.sel.ram.ui.utils.Strings;
import ca.mcgill.sel.ram.ui.views.TextView;
import ca.mcgill.sel.ram.ui.views.handler.HandlerFactory;
import ca.mcgill.sel.ram.ui.views.structural.handler.IEnumViewHandler;

/**
 * This view draws the RAM representation of a REnum onto a {@link StructuralDiagramView}. It contains a name field, and
 * a list for its literals.
 * 
 * @author Franz
 * 
 */
public class EnumView extends BaseView<IEnumViewHandler> {

    /**
     * The minimum width of the view.
     */
    protected static final float MINIMUM_WIDTH = 150f;

    /**
     * Reference on the related {@link REnum}.
     */
    protected REnum aEnum;

    /**
     * Literals eObject of the {@link REnum} and their {@link EnumLiteralView}.
     */
    protected Map<REnumLiteral, EnumLiteralView> literals;

    /**
     * Container for the literals.
     */
    protected RamRectangleComponent literalsContainer;
    /**
     * Indicates if the {@link REnum} is an implementation.
     */
    protected boolean isImplementationEnum;

    /**
     * Constructor.
     * 
     * @param structuralDiagramView that will contain this enum view.
     * @param someEnum Enum represented by this view.
     * @param layoutElement of this view.
     * @param isImpEnum true if implementation enum, false otherwise
     */
    protected EnumView(StructuralDiagramView structuralDiagramView, REnum someEnum, LayoutElement layoutElement,
            boolean isImpEnum) {
        super(structuralDiagramView, someEnum, layoutElement);
        isImplementationEnum = isImpEnum;
        // Initialize fields
        aEnum = someEnum;
        literals = new HashMap<REnumLiteral, EnumLiteralView>();
        buildEnumView();
        nameField.setNoStroke(true);
        nameField.setBufferSize(Cardinal.SOUTH, 0);

        // initialize with give enum
        initializeEnum(someEnum);
    }

    /**
     * Initializes the enum view with the REnum.
     * 
     * @param someEnum REnum to initialize.
     */
    private void initializeEnum(REnum someEnum) {
        for (REnumLiteral literal : someEnum.getLiterals()) {
            addLiteralField(literalsContainer.getChildCount(), literal);
        }
    }

    /**
     * Will build the view.
     */
    protected void buildEnumView() {
        setMinimumWidth(MINIMUM_WIDTH);
        addNameField(aEnum, Strings.PH_ENTER_ENUM_NAME);
        addEnumTag();
        addLiteralsContainer();
    }

    /**
     * Adds container that will hold the literals.
     */
    protected void addLiteralsContainer() {
        // add literals container to view
        literalsContainer = new RamRectangleComponent(new VerticalLayout());
        literalsContainer.setNoStroke(false);
        literalsContainer.setMinimumHeight(ICON_SIZE);
        addChild(literalsContainer);
    }

    /**
     * Adds the name field.
     * 
     * @param classifier whose name we want to display.
     * @param placeholder text.
     */
    protected void addNameField(Classifier classifier, String placeholder) {
        // Add the name field to base view
        TextView nameField = new TextView(classifier, CorePackage.Literals.CORE_NAMED_ELEMENT__NAME);
        nameField.setFont(Fonts.FONT_CLASS_NAME);
        nameField.setUniqueName(true);
        nameField.setAlignment(Alignment.CENTER_ALIGN);
        nameField.setPlaceholderText(placeholder);
        if (!isImplementationEnum) {
            nameField.setHandler(HandlerFactory.INSTANCE.getEnumNameHandler());
        } else {
            nameField.setHandler(HandlerFactory.INSTANCE.getImplementationEnumNameHandler());
        }
        setNameField(nameField);

    }

    /**
     * Adds the enum tag. ( <<enum>> )
     */
    protected void addEnumTag() {
        // Create the Text field
        RamTextComponent enumTagField = new RamTextComponent();
        enumTagField.setFont(Fonts.FONT_ENUM_TAG);
        enumTagField.setAlignment(Alignment.CENTER_ALIGN);
        enumTagField.setText(Strings.TAG_ENUMERATION);
        enumTagField.setNoStroke(true);
        enumTagField.setBufferSize(Cardinal.SOUTH, 0);
        setTagField(enumTagField);
    }

    /**
     * Getter for the {@link REnum} associated to this view.
     * 
     * @return REnum associated to this view
     */
    public REnum getREnum() {
        return aEnum;
    }

    /**
     * Adds a enum literal at the specified index.
     * 
     * @param index where to insert.
     * @param literal we want to insert.
     */
    private void addLiteralField(int index, REnumLiteral literal) {
        // Add an enum literal
        EnumLiteralView literalView = new EnumLiteralView(this, literal, !isImplementationEnum);
        literals.put(literal, literalView);

        literalsContainer.addChild(index, literalView);
        literalView.setHandler(HandlerFactory.INSTANCE.getEnumLiteralViewHandler());
    }

    /**
     * Getter for the {@link RamRectangleComponent} corresponding to the enum literals.
     * 
     * @return RamRectangleComponent corresponding to the enum literals
     */
    public RamRectangleComponent getEnumLiteralsContainer() {
        return literalsContainer;
    }

    @Override
    public void notifyChanged(Notification notification) {
        // handle it first by the super class
        super.notifyChanged(notification);

        // this gets called for every ClassView (as there is only one item provider)
        // therefore an additional check is necessary
        if (notification.getNotifier() == aEnum) {
            if (notification.getFeature() == RamPackage.Literals.RENUM__LITERALS) {
                switch (notification.getEventType()) {
                    case Notification.REMOVE:
                        REnumLiteral literal = (REnumLiteral) notification.getOldValue();
                        removeLiteral(literal);
                        break;
                    case Notification.ADD:
                        literal = (REnumLiteral) notification.getNewValue();
                        addLiteralField(notification.getPosition(), literal);
                        break;
                }
            }
        }
    }

    /**
     * Removes a specified literal.
     * 
     * @param literal to be removed.
     */
    private void removeLiteral(final REnumLiteral literal) {
        if (literals.containsKey(literal)) {
            RamApp.getApplication().invokeLater(new Runnable() {

                @Override
                public void run() {
                    EnumLiteralView literalView = literals.remove(literal);

                    literalsContainer.removeChild(literalView);
                    literalView.destroy();
                }
            });
        }
    }

    /**
     * Show keyboard for name field of this view.
     * 
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

}
