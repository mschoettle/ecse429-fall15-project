/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ca.mcgill.sel.ram.provider;


import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.ram.ExecutionStatement;
import ca.mcgill.sel.ram.RamFactory;
import ca.mcgill.sel.ram.RamPackage;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link ca.mcgill.sel.ram.ExecutionStatement} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ExecutionStatementItemProvider
    extends InteractionFragmentItemProvider {
    /**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ExecutionStatementItemProvider(AdapterFactory adapterFactory) {
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
            childrenFeatures.add(RamPackage.Literals.EXECUTION_STATEMENT__SPECIFICATION);
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
     * This returns ExecutionStatement.gif.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/ExecutionStatement"));
    }

    /**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
     * @param object the object a textual representation to get for
     * @return the textual representation for the given object
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public String getText(Object object) {
        ExecutionStatement executionStatement = (ExecutionStatement) object;
        
        String label = null;
        
        if (executionStatement.getSpecification() != null) {
            label = EMFEditUtil.getTextFor(getAdapterFactory(), executionStatement.getSpecification());
        }
        
        return label == null || label.length() == 0 
                ? getString("_UI_ExecutionStatement_type")
                : getString("_UI_ExecutionStatement_type") + " " + label;
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

        switch (notification.getFeatureID(ExecutionStatement.class)) {
            case RamPackage.EXECUTION_STATEMENT__SPECIFICATION:
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
                (RamPackage.Literals.EXECUTION_STATEMENT__SPECIFICATION,
                 RamFactory.eINSTANCE.createStructuralFeatureValue()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.EXECUTION_STATEMENT__SPECIFICATION,
                 RamFactory.eINSTANCE.createParameterValue()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.EXECUTION_STATEMENT__SPECIFICATION,
                 RamFactory.eINSTANCE.createOpaqueExpression()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.EXECUTION_STATEMENT__SPECIFICATION,
                 RamFactory.eINSTANCE.createLiteralString()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.EXECUTION_STATEMENT__SPECIFICATION,
                 RamFactory.eINSTANCE.createLiteralInteger()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.EXECUTION_STATEMENT__SPECIFICATION,
                 RamFactory.eINSTANCE.createLiteralBoolean()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.EXECUTION_STATEMENT__SPECIFICATION,
                 RamFactory.eINSTANCE.createLiteralNull()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.EXECUTION_STATEMENT__SPECIFICATION,
                 RamFactory.eINSTANCE.createEnumLiteralValue()));
    }

}
