package ca.mcgill.sel.ram.ui.components;

import java.util.List;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import ca.mcgill.sel.ram.ui.components.RamListComponent.Namer;

/**
 * The {@link RamEMFSelectorComponent} is a {@link RamSelectorComponent} that use a {@link RamEMFListComponent}.
 * 
 * @see RamSelectorComponent
 * @author Romain
 * @param <T> The type of the elements to be selected.
 */
public class RamEMFSelectorComponent<T> extends RamSelectorComponent<T> {

    /**
     * Creates a selector class for the given elements. The names (both for sorting and displaying) are determine by
     * element.toString()
     *
     * @param elements The elements which can be selected.
     */
    public RamEMFSelectorComponent(List<T> elements) {
        super(elements, null);
    }

    /**
     * Creates a selector class for the given elements.
     *
     * @param elements The elements which can be selected.
     * @param namer Determines the sorting and display names for the elements.
     */
    public RamEMFSelectorComponent(List<T> elements, Namer<T> namer) {
        super(elements, namer);
    }

    /**
     * Creates an empty selector with the given namer.
     *
     * @param namer The namer to use in displaying elements.
     */
    public RamEMFSelectorComponent(Namer<T> namer) {
        super(namer);
    }

    /**
     * Creates a new {@link RamEMFSelectorComponent} that will create a {@link RamEMFListComponent} for the
     * given object and its feature.
     *
     * @param data the data object
     * @param feature the feature of the data
     * @param type the type of the EStructuralFeature
     * @param namer Determines the sorting and display names for the elements.
     */
    public RamEMFSelectorComponent(EObject data, EStructuralFeature feature, EClassifier type, Namer<T> namer) {
        super(namer);
        ((RamEMFListComponent<T>) scrollableListComponent).setElements(data, feature, type);
    }

    /**
     * Creates a new {@link RamEMFSelectorComponent} that will create a {@link RamEMFListComponent} for the
     * given object and its feature.
     *
     * @param data the data object
     * @param feature the feature of the data
     */
    public RamEMFSelectorComponent(EObject data, EStructuralFeature feature) {
        super();
        ((RamEMFListComponent<T>) scrollableListComponent).setElements(data, feature);
    }

    /**
     * Initialize the {@link RamListComponent}.
     */
    @Override
    protected void initListComponent() {
        scrollableListComponent = new RamEMFListComponent<T>(true);
        scrollableListComponent.registerListener(this);

        this.addChild(scrollableListComponent);
    }

    /**
     * Fills this list with the given object and its feature. It will use the operation getEType() on the feature to
     * retrieve the type. You can specify a specific type using another signature of operation fillsList(..).
     *
     * @param eData the data object
     * @param eFeature the feature of the data
     */
    public synchronized void setElements(EObject eData, EStructuralFeature eFeature) {
        ((RamEMFListComponent<T>) this.scrollableListComponent).setElements(eData, eFeature);
    }

    /**
     * Fills this list with the given object and its feature.
     *
     * @param eData the data object
     * @param eFeature the feature of the data
     * @param eType the type of the EStructuralFeature
     */
    public synchronized void setElements(EObject eData, EStructuralFeature eFeature, EClassifier eType) {
        ((RamEMFListComponent<T>) this.scrollableListComponent).setElements(eData, eFeature, eType);
    }
}
