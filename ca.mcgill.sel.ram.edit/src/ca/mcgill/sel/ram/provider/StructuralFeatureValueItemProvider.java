/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ca.mcgill.sel.ram.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IViewerNotification;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import ca.mcgill.sel.commons.emf.util.EMFModelUtil;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.Lifeline;
import ca.mcgill.sel.ram.Message;
import ca.mcgill.sel.ram.MessageOccurrenceSpecification;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.StructuralFeature;
import ca.mcgill.sel.ram.StructuralFeatureValue;
import ca.mcgill.sel.ram.provider.util.RAMEditUtil;
import ca.mcgill.sel.ram.util.RAMModelUtil;

/**
 * This is the item provider adapter for a {@link ca.mcgill.sel.ram.StructuralFeatureValue} object.
 * <!-- begin-user-doc
 * --> <!-- end-user-doc -->
 * @generated
 */
public class StructuralFeatureValueItemProvider extends ValueSpecificationItemProvider {

    class ChangeListener implements INotifyChangedListener {
        @Override
        public void notifyChanged(Notification notification) {

            if (notification.getNotifier() != null && getTarget() != null) {
                Object element = ((IViewerNotification) notification).getElement();
                StructuralFeatureValue structuralFeatureValue = (StructuralFeatureValue) getTarget();
                StructuralFeature structuralFeature = structuralFeatureValue.getValue();
                Object notifier = notification.getNotifier();

                /**
                 * First make sure that the element is the notifier to filter out invalid requests. The item provider
                 * needs to be stateful due to the target. We just need to make sure that the update is only done when
                 * necessary. E.g., the notifier needs to be the structural feature itself.
                 */
                if (element == notifier && structuralFeature != null && notifier == structuralFeature) {
                    ((IChangeNotifier) getAdapterFactory()).removeListener(this);
                    fireNotifyChanged(new ViewerNotification(notification, getTarget(), false, true));
                    ((IChangeNotifier) getAdapterFactory()).addListener(this);
                }
            }
        }
    }

    private ChangeListener changeListener;

    /**
     * This constructs an instance from a factory and a notifier. <!-- begin-user-doc -->
     * 
     * @param adapterFactory the adapter factory for this item provider <!-- end-user-doc -->
     * @generated NOT
     */
    public StructuralFeatureValueItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);

        /**
         * The change listener is required to get notifications about referenced elements and pass the notifications to
         * its own listener. I.e., The values name could change.
         */
        if (adapterFactory instanceof IChangeNotifier) {
            IChangeNotifier changeNotifier = (IChangeNotifier) adapterFactory;
            changeListener = new ChangeListener();
            changeNotifier.addListener(changeListener);
        }
    }

    /**
     * This returns the property descriptors for the adapted class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
        if (itemPropertyDescriptors == null) {
            super.getPropertyDescriptors(object);

            addValuePropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Value feature.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected void addValuePropertyDescriptorGen(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_StructuralFeatureValue_value_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_StructuralFeatureValue_value_feature", "_UI_StructuralFeatureValue_type"),
                 RamPackage.Literals.STRUCTURAL_FEATURE_VALUE__VALUE,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null));
    }
    
    /**
     * This adds a property descriptor for the Value feature.
     * <!-- begin-user-doc -->
     * @param object the object to add a property descriptor for
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addValuePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(
            new ItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_StructuralFeatureValue_value_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_StructuralFeatureValue_value_feature", 
                         "_UI_StructuralFeatureValue_type"),
                 RamPackage.Literals.STRUCTURAL_FEATURE_VALUE__VALUE,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null) {

                    @Override
                    public Collection<?> getChoiceOfValues(Object object) {
                        StructuralFeatureValue featureValue = (StructuralFeatureValue) object;
                        
                        // Make sure that the message actually contains this object, and not another one.
                        Message message =
                                EMFModelUtil.getRootContainerOfType(featureValue, RamPackage.Literals.MESSAGE);
                        
                        if (message != null) {
                            MessageOccurrenceSpecification sendEvent = 
                                    (MessageOccurrenceSpecification) message.getSendEvent();
                            
                            Message initialMessage = RAMModelUtil.findInitialMessage(sendEvent);
                            Lifeline lifeline = null;
                            if (sendEvent != null && sendEvent.getCovered().size() > 0) {
                                lifeline = sendEvent.getCovered().get(0);
                            }
                            
                            Aspect aspect = EMFModelUtil.getRootContainerOfType(message, RamPackage.Literals.ASPECT);
                            
                            return RAMEditUtil.collectStructuralFeatures(aspect, lifeline, 
                                    initialMessage, featureValue.getValue());
                        }

                        return super.getChoiceOfValues(object);
                    }

                });
    }

    /**
     * This returns StructuralFeatureValue.gif.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/StructuralFeatureValue"));
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc -->
     * 
     * @param object the object a textual representation to get for
     * @return the textual representation for the given object <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public String getText(Object object) {
        StructuralFeatureValue structuralFeatureValue = (StructuralFeatureValue) object;

        String label = null;

        if (structuralFeatureValue.getValue() != null) {
            label = structuralFeatureValue.getValue().getName();
        }

        return label == null || label.length() == 0 ? getString("_UI_StructuralFeatureValue_type")
                        : getString("_UI_StructuralFeatureValue_type") + " " + label;
    }

    /**
     * This handles model notifications by calling {@link #updateChildren} to update any cached children and by creating
     * a viewer notification, which it passes to {@link #fireNotifyChanged}. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     */
    @Override
    public void notifyChanged(Notification notification) {
        updateChildren(notification);
        super.notifyChanged(notification);
    }

    @Override
    public void addListener(INotifyChangedListener listener) {
        super.addListener(listener);
    }

    /**
     * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
     * that can be created under this object.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);
    }

}
