package ca.mcgill.sel.ram.ui.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.INotifyChangedListener;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.commons.emf.util.EMFModelUtil;

/**
 * A {@link RamEcturalFeatureListComponent} is a {@link RamListComponent} for an {@link EStructuralFeature} that is
 * an {@link org.eclipse.emf.ecore.EReference} which represents a 0 or 1 to many reference/association. It handles the
 * display of all possible elements. The action performed on selection should be handled by a
 * {@link ca.mcgill.sel.ram.ui.components.listeners.RamListListener} that can be registered on this view.
 *
 * @author Romain
 *
 * @param <T> The type of elements
 */
public class RamEMFListComponent<T> extends RamListComponent<T> implements INotifyChangedListener {

    /**
     * The object that contains the feature.
     */
    protected EObject data;

    /**
     * The feature for which a selection should be shown/provided.
     */
    protected EStructuralFeature feature;

    /**
     * The type of the feature for which a selection should be shown/provided.
     */
    protected EClassifier type;

    /**
     * Creates a new non-scrollable empty {@link RamEcturalFeatureListComponent}.
     */
    public RamEMFListComponent() {
        super(false);
    }

    /**
     * Creates a new empty {@link RamEcturalFeatureListComponent}. This list can be a ScrollableList.
     *
     * @param isScrollable true if the list must be scrollable, false otherwise
     */
    public RamEMFListComponent(boolean isScrollable) {
        super(isScrollable);
    }

    /**
     * Creates a new {@link RamEMFListComponent} for the given object and its feature. This list can be a
     * ScrollableList.
     *
     * @param data the data object
     * @param feature the feature of the data
     * @param type the type of the EStructuralFeature
     * @param isScrollable true if the list must be scrollable, false otherwise
     */
    public RamEMFListComponent(EObject data, EStructuralFeature feature, EClassifier type,
            boolean isScrollable) {
        super(isScrollable);
        setElements(data, feature, type);
    }

    /**
     * Creates a new non-scrollable {@link RamEMFListComponent} for the given object and its feature.
     *
     * @param data the data object
     * @param feature the feature of the data
     * @param type the type of the EStructuralFeature
     */
    public RamEMFListComponent(EObject data, EStructuralFeature feature, EClassifier type) {
        this(data, feature, type, false);
    }

    /**
     * Creates a new non-scrollable {@link RamEMFListComponent} for the given object and its feature.
     * This list can be a ScrollableList. It will use the operation getEType() on the feature to retrieve the type. You
     * can specify a specific type using another constructor.
     *
     * @param data the data object
     * @param feature the feature of the data
     */
    public RamEMFListComponent(EObject data, EStructuralFeature feature) {
        this(data, feature, feature.getEType(), false);
    }

    /**
     * Creates a new {@link RamEMFListComponent} for the given object and its feature. It
     * will use the operation getEType() on the feature to retrieve the type. You can specify a specific type using
     * another constructor.
     *
     * @param data the data object
     * @param feature the feature of the data
     * @param isScrollable true if the list must be scrollable, false otherwise
     */
    public RamEMFListComponent(EObject data, EStructuralFeature feature, boolean isScrollable) {
        this(data, feature, feature.getEType(), isScrollable);
    }

    /**
     * Fills this list with the given object and its feature. It will use the operation getEType() on the feature to
     * retrieve the type. You can specify a specific type using another signature of operation fillsList(..).
     *
     * @param eData the data object
     * @param eFeature the feature of the data
     */
    public synchronized void setElements(EObject eData, EStructuralFeature eFeature) {
        this.setElements(eData, eFeature, eFeature.getEType());
    }

    /**
     * Fills this list with the given object and its feature.
     *
     * @param eData the data object
     * @param eFeature the feature of the data
     * @param eType the type of the EStructuralFeature
     */
    public synchronized void setElements(EObject eData, EStructuralFeature eFeature, EClassifier eType) {
        this.data = eData;
        this.feature = eFeature;
        this.type = eType;

        EMFEditUtil.addListenerFor(data, this);

        initialize();
    }

    @Override
    public void setElements(List<T> elements) {
        if (data != null) {
            EMFEditUtil.removeListenerFor(data, this);
        }

        this.data = null;
        this.feature = null;
        this.type = null;

        super.setElements(elements);
    }

    /**
     * Initializes the elements of the list by providing the available choices for the feature of the data object.
     */
    protected void initialize() {
        Collection<T> choiceOfValues = EMFModelUtil.collectElementsOfType(data, feature, type);
        if (choiceOfValues != null) {
            List<T> elements = new ArrayList<T>(choiceOfValues);
            fillList(elements);
        }
    }

    @Override
    public void notifyChanged(Notification notification) {
        if (data == null || feature == null) {
            return;
        }

        if (notification.getNotifier() == data && notification.getFeature() == feature) {
            switch (notification.getEventType()) {
                case Notification.ADD:
                    @SuppressWarnings("unchecked")
                    T newElement = (T) notification.getNewValue();
                    this.addElement(newElement);
                    break;
                case Notification.REMOVE:
                    @SuppressWarnings("unchecked")
                    T oldElement = (T) notification.getOldValue();
                    this.removeElement(oldElement);
                    break;
            }
        }
    }

    @Override
    public void destroy() {
        if (data != null) {
            EMFEditUtil.removeListenerFor(data, this);
        }
        super.destroy();
    }
}
