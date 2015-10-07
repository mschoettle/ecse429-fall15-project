package ca.mcgill.sel.ram.ui.views;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.ram.ui.components.RamListComponent.Namer;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.components.RamSelectorComponent;
import ca.mcgill.sel.ram.ui.components.RamTextComponent;
import ca.mcgill.sel.ram.ui.components.listeners.RamSelectorListener;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.utils.LoggerUtils;

/**
 * A {@link SelectorView} is a {@link RamSelectorComponent} for an {@link EStructuralFeature} that is an
 * {@link org.eclipse.emf.ecore.EReference} which represents a 0 or 1 to many reference/association. It handles the
 * display of all possible elements and destroys itself if an element was selected. The action performed on selection
 * should be handled by a {@link RamSelectorListener} that can be registered on this view.
 *
 * @author mschoettle
 */
public class SelectorView extends RamSelectorComponent<Object> implements RamSelectorListener<Object> {

    /**
     * A namer that is capable of displaying different objects depending on the choice of values, i.e., {@link EObject}
     * s, enums or primitive objects.
     */
    private class InternalNamer implements Namer<Object> {

        @Override
        public RamRectangleComponent getDisplayComponent(Object element) {
            RamTextComponent view = null;

            if (element instanceof EObject) {
                view = new RamTextComponent(EMFEditUtil.getPropertyText(data, feature, element));
            } else {
                view = new RamTextComponent(element.toString());
            }

            view.setNoFill(false);
            view.setFillColor(Colors.DEFAULT_ELEMENT_FILL_COLOR);
            view.setNoStroke(false);
            return view;
        }

        @Override
        public String getSortingName(Object element) {
            String result = null;

            if (element instanceof EObject) {
                // add the objects class name at the beginning to get better sorting
                String className = EMFEditUtil.getTypeName((EObject) element);
                result = className + " " + EMFEditUtil.getPropertyText(data, feature, element);
            } else {
                result = element.toString();
            }

            return result;
        }

        @Override
        public String getSearchingName(Object element) {
            String result = null;

            if (element instanceof EObject) {
                // the search occurs only on the name of the object
                result = EMFEditUtil.getPropertyText(data, feature, element);
            } else {
                result = element.toString();
            }

            return result;
        }

    }

    /**
     * The object that contains the feature.
     */
    protected EObject data;

    /**
     * The feature for which a selection should be shown/provided.
     */
    protected EStructuralFeature feature;

    /**
     * Creates a new {@link SelectorView} for the given object and its feature.
     *
     * @param data the data object
     * @param feature the feature of the data
     */
    public SelectorView(EObject data, EStructuralFeature feature) {
        super();

        this.data = data;
        this.feature = feature;

        setNamer(new InternalNamer());
        initialize();

        // register ourself so we will know when an element was selected to destroy this
        registerListener(this);
    }

    @Override
    public void elementSelected(RamSelectorComponent<Object> selector, Object element) {
        destroy();
    }

    /**
     * Initializes the elements of the selector by providing the available choices for the feature of the data object.
     */
    protected void initialize() {
        // get the property descriptor
        IItemPropertyDescriptor propertyDescriptor = EMFEditUtil.getPropertyDescriptor(data, feature);

        if (propertyDescriptor != null) {
            // get elements
            Collection<?> choiceOfValues = propertyDescriptor.getChoiceOfValues(data);

            if (choiceOfValues != null) {
                choiceOfValues.remove(null);
                List<Object> elements = new ArrayList<Object>(choiceOfValues);
                setElements(elements);
            }
        } else {
            LoggerUtils.error("[SelectorView] No property descritor found for: " + data + " and " + feature);
        }
    }

    @Override
    public void closeSelector(RamSelectorComponent<Object> selector) {
        destroy();
    }

    @Override
    public void elementDoubleClicked(RamSelectorComponent<Object> selector, Object element) {
    }

    @Override
    public void elementHeld(RamSelectorComponent<Object> selector, Object element) {

    }
}
