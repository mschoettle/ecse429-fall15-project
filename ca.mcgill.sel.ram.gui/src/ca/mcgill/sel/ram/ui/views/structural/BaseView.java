package ca.mcgill.sel.ram.ui.views.structural;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.commons.emf.util.EMFModelUtil;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.Classifier;
import ca.mcgill.sel.ram.LayoutElement;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.ContainerComponent;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.components.RamTextComponent;
import ca.mcgill.sel.ram.ui.layouts.HorizontalLayoutAllCentered;
import ca.mcgill.sel.ram.ui.layouts.VerticalLayout;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.utils.Fonts;
import ca.mcgill.sel.ram.ui.utils.GraphicalUpdater;
import ca.mcgill.sel.ram.ui.views.structural.handler.IBaseViewHandler;

/**
 * A view that represents the most basic view you can have. It only has a tag container and name container. The only
 * thing it can do is move. Any "uml box" view should extend this base view.
 * 
 * @author Franz
 * 
 * @param <H> Handler for this view
 */
public abstract class BaseView<H extends IBaseViewHandler> extends ContainerComponent<H> implements
        INotifyChangedListener {

    protected static final float ICON_SIZE = Fonts.FONTSIZE_CLASS_NAME + 2;

    protected LayoutElement layoutElement;
    protected Classifier classifier;

    protected GraphicalUpdater graphicalUpdater;

    protected StructuralDiagramView structuralDiagramView;

    protected RamRectangleComponent tagContainer;
    protected RamTextComponent tagField;

    protected RamRectangleComponent nameContainer;
    protected RamTextComponent nameField;

    protected boolean selected;

    protected Vector3D dragFrom;
    protected boolean wasDragged;

    protected BaseView(final StructuralDiagramView structuralDiagramView, Classifier pClassifier,
            final LayoutElement layoutElement) {
        this.structuralDiagramView = structuralDiagramView;
        this.classifier = pClassifier;

        setEnabled(true);
        setNoFill(false);
        setFillColor(Colors.CLASS_VIEW_DEFAULT_FILL_COLOR);
        setNoStroke(false);

        // build components
        buildTagContainer();
        buildNameContainer();

        // translate the class based on the meta-model
        if (layoutElement != null) {
            setLayoutElement(layoutElement);
        }

        setLayout(new VerticalLayout());

        EMFEditUtil.addListenerFor(classifier, this);
        Aspect aspect = EMFModelUtil.getRootContainerOfType(pClassifier, RamPackage.Literals.ASPECT);
        graphicalUpdater = RamApp.getApplication().getGraphicalUpdaterForAspect(aspect);
        graphicalUpdater.addGUListener(classifier, nameContainer);
    }

    protected final void buildTagContainer() {
        tagContainer = new RamRectangleComponent();
        tagContainer.setNoStroke(true);
        tagContainer.setBufferSize(Cardinal.WEST, 5f);
        tagContainer.setBufferSize(Cardinal.EAST, 5f);
        tagContainer.setLayout(new HorizontalLayoutAllCentered(Fonts.FONTSIZE_CLASS_NAME / 6));
        addChild(tagContainer);
    }

    protected final void buildNameContainer() {
        // create and add the class field
        nameContainer = new RamRectangleComponent();
        nameContainer.setNoStroke(true);
        nameContainer.setBufferSize(Cardinal.WEST, 5f);
        nameContainer.setBufferSize(Cardinal.EAST, 5f);
        nameContainer.setLayout(new HorizontalLayoutAllCentered(Fonts.FONTSIZE_CLASS_NAME / 6));
        addChild(nameContainer);
    }

    protected final void setNameField(final RamTextComponent pNameField) {
        if (nameField != null) {
            nameContainer.removeChild(nameField);
            nameField.destroy();
        }
        if (pNameField != null) {
            nameContainer.addChild(pNameField);
        }
        nameField = pNameField;
    }

    protected final void setTagField(final RamTextComponent pTagField) {
        if (tagField != null) {
            tagContainer.removeChild(tagField);
        }
        if (pTagField != null) {
            tagContainer.addChild(pTagField);
        }
        tagField = pTagField;
    }

    @Override
    public void notifyChanged(final Notification notification) {
        if (notification.getNotifier() == layoutElement) {
            setPositionRelativeToParent(new Vector3D(layoutElement.getX(), layoutElement.getY()));
        }
    }

    /**
     * Sets the layout element for the corresponding class.
     * 
     * @param layoutElement the {@link LayoutElement} to set
     */
    public void setLayoutElement(final LayoutElement layoutElement) {
        this.layoutElement = layoutElement;
        setPositionGlobal(new Vector3D(layoutElement.getX(), layoutElement.getY()));

        EMFEditUtil.addListenerFor(layoutElement, this);
    }

    @Override
    public void destroy() {

        graphicalUpdater.removeGUListener(classifier, nameContainer);

        // unregister
        EMFEditUtil.removeListenerFor(layoutElement, this);

        // destroy rest
        super.destroy();
    }

    /**
     * Returns the position from where the class was dragged.
     * 
     * @return the position from where the class was dragged
     */
    public final Vector3D getDragFrom() {
        return dragFrom;
    }

    /**
     * Sets the position from where the classifier was dragged.
     * 
     * @param v the position from where the classifier was dragged
     */
    public final void setDragFrom(final Vector3D v) {
        dragFrom = v;
        wasDragged = false;
    }

    /**
     * Returns whether this view was dragged previously.
     * 
     * @return true, if the classifier was dragged, false otherwise
     */
    public final boolean wasDragged() {
        return wasDragged;
    }

    /**
     * Sets that the classifier was dragged.
     */
    public final void setWasDragged() {
        wasDragged = true;
    }

    /**
     * This method returns true if the classifier is currently selected. This method should not be confused with
     * {@link #isSelected()}, which is already defined by MT4j for a different purpose.
     * 
     * @return if the classifier is currently selected
     */
    public boolean getIsSelected() {
        return selected;
    }

    /**
     * <b>Attention:</b> This method is implemented by MT4j. If you want to check if this view is selected/deselected
     * you have to use {@link #getIsSelected()} instead.
     * 
     * @return true, if it is selected
     */
    @Override
    public final boolean isSelected() {
        return super.isSelected();
    }

    /**
     * Selects this view. Note: {@link #setSelected(boolean)} is already defined my MT4j for a different purpose.
     * 
     * @param isSelected true, if the view is supposed to be selected
     */
    public final void setSelect(final boolean isSelected) {
        this.selected = isSelected;

        if (selected) {
            setStrokeColor(Colors.CLASS_SELECTED_VIEW_FILL_COLOR);
            structuralDiagramView.elementSelected(this);
        } else {
            setStrokeColor(Colors.CLASS_VIEW_DEFAULT_STROKE_COLOR);
            structuralDiagramView.elementDeselected(this);
        }

    }

    /**
     * <b>Attention:</b> This method is implemented by MT4j. If you want to select/deselect this view you have to use
     * {@link #setSelect(boolean)} instead.
     * 
     * @param selected the new selected
     */
    @Override
    public final void setSelected(boolean selected) {
        super.setSelected(selected);
    }

    /**
     * Getter for the classifier of this base view.
     * 
     * @return {@link Classifier} associated to this base view
     */
    public Classifier getClassifier() {
        return classifier;
    }

    /**
     * Getter for the structural view that contains this view.
     * 
     * @return {@link StructuralDiagramView} containing this base view.
     */
    public StructuralDiagramView getStructuralDiagramView() {
        return structuralDiagramView;
    }

}
