/**
 */
package ca.mcgill.sel.ram.provider;


import ca.mcgill.sel.commons.emf.util.EMFModelUtil;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.AssignmentStatement;
import ca.mcgill.sel.ram.Lifeline;
import ca.mcgill.sel.ram.Message;
import ca.mcgill.sel.ram.RamFactory;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.StructuralFeature;
import ca.mcgill.sel.ram.provider.util.RAMEditUtil;
import ca.mcgill.sel.ram.util.RAMModelUtil;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link ca.mcgill.sel.ram.AssignmentStatement} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class AssignmentStatementItemProvider extends InteractionFragmentItemProvider {
    /**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AssignmentStatementItemProvider(AdapterFactory adapterFactory) {
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

            addAssignToPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }
    
    /**
     * This adds a property descriptor for the Assign To feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addAssignToPropertyDescriptorGen(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_AssignmentStatement_assignTo_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_AssignmentStatement_assignTo_feature", "_UI_AssignmentStatement_type"),
                 RamPackage.Literals.ASSIGNMENT_STATEMENT__ASSIGN_TO,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Assign To feature.
     * <!-- begin-user-doc -->
     * @param object the object to add a property descriptor for
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addAssignToPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(
            new ItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_AssignmentStatement_assignTo_feature"),
                 getString("_UI_PropertyDescriptor_description", 
                             "_UI_AssignmentStatement_assignTo_feature", 
                             "_UI_AssignmentStatement_type"),
                 RamPackage.Literals.ASSIGNMENT_STATEMENT__ASSIGN_TO,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null) {
                
                @Override
                public Collection<?> getChoiceOfValues(Object object) {
                    AssignmentStatement assignmentStatement = (AssignmentStatement) object;
                    StructuralFeature assignTo = assignmentStatement.getAssignTo();
                    
                    Message initialMessage = RAMModelUtil.findInitialMessage(assignmentStatement);
                    
                    Lifeline lifeline = null;
                    if (assignmentStatement != null && assignmentStatement.getCovered().size() > 0) {
                        lifeline = assignmentStatement.getCovered().get(0);
                    }
                    
                    Aspect aspect = EMFModelUtil.getRootContainerOfType(initialMessage, RamPackage.Literals.ASPECT);
                    return RAMEditUtil.collectStructuralFeatures(aspect, lifeline, initialMessage, assignTo);
                }
                
            });
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
            childrenFeatures.add(RamPackage.Literals.ASSIGNMENT_STATEMENT__VALUE);
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
     * This returns AssignmentStatement.gif.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/AssignmentStatement"));
    }

    /**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getText(Object object) {
        return getString("_UI_AssignmentStatement_type");
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

        switch (notification.getFeatureID(AssignmentStatement.class)) {
            case RamPackage.ASSIGNMENT_STATEMENT__ASSIGN_TO:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
                return;
            case RamPackage.ASSIGNMENT_STATEMENT__VALUE:
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
                (RamPackage.Literals.ASSIGNMENT_STATEMENT__VALUE,
                 RamFactory.eINSTANCE.createStructuralFeatureValue()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.ASSIGNMENT_STATEMENT__VALUE,
                 RamFactory.eINSTANCE.createParameterValue()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.ASSIGNMENT_STATEMENT__VALUE,
                 RamFactory.eINSTANCE.createOpaqueExpression()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.ASSIGNMENT_STATEMENT__VALUE,
                 RamFactory.eINSTANCE.createLiteralString()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.ASSIGNMENT_STATEMENT__VALUE,
                 RamFactory.eINSTANCE.createLiteralInteger()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.ASSIGNMENT_STATEMENT__VALUE,
                 RamFactory.eINSTANCE.createLiteralBoolean()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.ASSIGNMENT_STATEMENT__VALUE,
                 RamFactory.eINSTANCE.createLiteralNull()));

        newChildDescriptors.add
            (createChildParameter
                (RamPackage.Literals.ASSIGNMENT_STATEMENT__VALUE,
                 RamFactory.eINSTANCE.createEnumLiteralValue()));
    }

}
