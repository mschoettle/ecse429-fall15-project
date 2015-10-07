/**
 */
package ca.mcgill.sel.core.provider;


import java.util.Collection;
import java.util.List;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import ca.mcgill.sel.core.COREImpactModel;
import ca.mcgill.sel.core.CoreFactory;
import ca.mcgill.sel.core.CorePackage;

/**
 * This is the item provider adapter for a {@link ca.mcgill.sel.core.COREImpactModel} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class COREImpactModelItemProvider
    extends COREModelItemProvider {
    /**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public COREImpactModelItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /**
     * This returns the property descriptors for the adapted class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
        if (itemPropertyDescriptors == null) {
            super.getPropertyDescriptors(object);

        }
        return itemPropertyDescriptors;
    }

    /**
     * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
     * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
     * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
        if (childrenFeatures == null) {
            super.getChildrenFeatures(object);
            childrenFeatures.add(CorePackage.Literals.CORE_IMPACT_MODEL__IMPACT_MODEL_ELEMENTS);
            childrenFeatures.add(CorePackage.Literals.CORE_IMPACT_MODEL__LAYOUTS);
            childrenFeatures.add(CorePackage.Literals.CORE_IMPACT_MODEL__CONTRIBUTIONS);
        }
        return childrenFeatures;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EStructuralFeature getChildFeature(Object object, Object child) {
        // Check the type of the specified child object and return the proper feature to use for
        // adding (see {@link AddCommand}) it as a child.

        return super.getChildFeature(object, child);
    }

    /**
     * This returns COREImpactModel.gif.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/COREImpactModel"));
    }

    /**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getText(Object object) {
        String label = ((COREImpactModel)object).getName();
        return label == null || label.length() == 0 ?
            getString("_UI_COREImpactModel_type") :
            getString("_UI_COREImpactModel_type") + " " + label;
    }

    /**
     * This handles model notifications by calling {@link #updateChildren} to update any cached
     * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void notifyChanged(Notification notification) {
        updateChildren(notification);

        switch (notification.getFeatureID(COREImpactModel.class)) {
            case CorePackage.CORE_IMPACT_MODEL__IMPACT_MODEL_ELEMENTS:
            case CorePackage.CORE_IMPACT_MODEL__LAYOUTS:
            case CorePackage.CORE_IMPACT_MODEL__CONTRIBUTIONS:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
                return;
        }
        super.notifyChanged(notification);
    }

    /**
     * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
     * that can be created under this object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);

        newChildDescriptors.add
            (createChildParameter
                (CorePackage.Literals.CORE_IMPACT_MODEL__IMPACT_MODEL_ELEMENTS,
                 CoreFactory.eINSTANCE.createCOREImpactNode()));

        newChildDescriptors.add
            (createChildParameter
                (CorePackage.Literals.CORE_IMPACT_MODEL__IMPACT_MODEL_ELEMENTS,
                 CoreFactory.eINSTANCE.createCOREFeatureImpactNode()));

        newChildDescriptors.add
            (createChildParameter
                (CorePackage.Literals.CORE_IMPACT_MODEL__LAYOUTS,
                 CoreFactory.eINSTANCE.create(CorePackage.Literals.LAYOUT_CONTAINER_MAP)));

        newChildDescriptors.add
            (createChildParameter
                (CorePackage.Literals.CORE_IMPACT_MODEL__CONTRIBUTIONS,
                 CoreFactory.eINSTANCE.createCOREContribution()));
    }

}
