package ca.mcgill.sel.ram.ui.views.structural;

import org.eclipse.emf.common.notify.Notification;

import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.ram.Classifier;
import ca.mcgill.sel.ram.ImplementationClass;
import ca.mcgill.sel.ram.LayoutElement;
import ca.mcgill.sel.ram.TypeParameter;
import ca.mcgill.sel.ram.ui.components.RamTextComponent;
import ca.mcgill.sel.ram.ui.components.RamTextComponent.Alignment;
import ca.mcgill.sel.ram.ui.utils.Fonts;
import ca.mcgill.sel.ram.ui.utils.Strings;
import ca.mcgill.sel.ram.ui.views.TextView;
import ca.mcgill.sel.ram.ui.views.structural.handler.IClassifierViewHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.impl.ImplementationClassNameHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.impl.TypeParameterNameHandler;

/**
 * This view represents an {@link ImplementationClass} which is contained in a {@link StructuralDiagramView}. The
 * difference in the visual representation to a class is that it doesn't contain any attributes and there is a
 * stereotype (<<impl>>).
 * 
 * @author mschoettle
 */
public class ImplementationClassView extends ClassifierView<IClassifierViewHandler> {

    private ImplementationClass implementationClass;

    /**
     * Creates a new {@link ImplementationClassView} for a given implementation class and layout element.
     * 
     * @param structuralDiagramView the {@link StructuralDiagramView} that is the parent of this view
     * @param implementationClass the {@link ImplementationClass} this view represents
     * @param layoutElement the {@link LayoutElement} that contains the layout information for this implementation class
     */
    public ImplementationClassView(StructuralDiagramView structuralDiagramView,
            ImplementationClass implementationClass, LayoutElement layoutElement) {
        super(structuralDiagramView, implementationClass, layoutElement, false);

        this.implementationClass = implementationClass;
        buildImplementationClassView();

    }

    /**
     * Builds the implementation class view.
     */
    protected void buildImplementationClassView() {
        addNameField(implementationClass);
        addImplementationTag();
    }

    /**
     * Add the name field.
     * 
     * @param classifier to be displayed.
     */
    protected void addNameField(Classifier classifier) {
        // create name field
        TextView nameField = new TextView(classifier, CorePackage.Literals.CORE_NAMED_ELEMENT__NAME, false);
        nameField.setFont(Fonts.FONT_CLASS_NAME);
        nameField.setUniqueName(false);
        nameField.setAlignment(Alignment.CENTER_ALIGN);
        nameField.setHandler(new ImplementationClassNameHandler());
        setNameField(nameField);

        // create type parameters if exists
        if (classifier.getTypeParameters().size() != 0) {
            nameField.setBufferSize(Cardinal.EAST, 0);
            int numOfTypeParameters = classifier.getTypeParameters().size();

            // add open angle bracket
            RamTextComponent openAngleBracket = new RamTextComponent("<");
            openAngleBracket.setBufferSize(Cardinal.EAST, 0);
            openAngleBracket.setBufferSize(Cardinal.WEST, 0);
            nameContainer.addChild(openAngleBracket);

            for (int i = 0; i < numOfTypeParameters; i++) {

                // add generic type field
                TextView genericTypeField = new TextView(classifier.getTypeParameters().get(i),
                        CorePackage.Literals.CORE_NAMED_ELEMENT__NAME);

                genericTypeField.setHandler(new TypeParameterNameHandler());

                genericTypeField.setBufferSize(Cardinal.WEST, 0);
                genericTypeField.setBufferSize(Cardinal.EAST, 0);

                nameContainer.addChild(genericTypeField);

                if (i != numOfTypeParameters - 1) {
                    // add comma
                    RamTextComponent comma = new RamTextComponent(",");
                    comma.setBufferSize(Cardinal.WEST, 0);
                    nameContainer.addChild(comma);
                }
            }

            // add closed angle bracket
            RamTextComponent closedAngleBracket = new RamTextComponent(">");
            closedAngleBracket.setBufferSize(Cardinal.WEST, 0);
            nameContainer.addChild(closedAngleBracket);
        }
    }

    /**
     * Add implentation class tag ( <<imp>> ).
     */
    protected void addImplementationTag() {
        // Create the Text field
        RamTextComponent enumTagField = new RamTextComponent();
        enumTagField.setFont(Fonts.FONT_ENUM_TAG);
        enumTagField.setAlignment(Alignment.CENTER_ALIGN);
        enumTagField.setBufferSize(Cardinal.NORTH, 3);
        enumTagField.setBufferSize(Cardinal.SOUTH, 0);

        if (implementationClass.isInterface()) {
            enumTagField.setText(Strings.TAG_IMPL_INTERFACE);
        } else {
            enumTagField.setText(Strings.TAG_IMPL);
        }

        enumTagField.setNoStroke(true);
        setTagField(enumTagField);
    }

    /**
     * Are all the type parameters set.
     * 
     * @return true if all type parameters set, false otherwise.
     */
    public boolean areGenericTypesSet() {
        for (TypeParameter tp : implementationClass.getTypeParameters()) {
            if (tp.getGenericType() == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Getter for the implementation class.
     * 
     * @return the {@link ImplementationClass} represented by this view.
     */
    public ImplementationClass getImplementationClass() {
        return implementationClass;
    }

    @Override
    protected void initializeClass(Classifier classifier) {
        initializeOperations(classifier);
    }

    @Override
    public void notifyChanged(Notification notification) {
        // handle by super class first
        super.notifyChanged(notification);
    }

}
