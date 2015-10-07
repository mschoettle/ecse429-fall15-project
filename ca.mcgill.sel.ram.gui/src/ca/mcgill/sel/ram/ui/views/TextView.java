package ca.mcgill.sel.ram.ui.views;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapProcessor;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamTextComponent;
import ca.mcgill.sel.ram.ui.utils.Constants;
import ca.mcgill.sel.ram.ui.utils.RamModelUtils;
import ca.mcgill.sel.ram.ui.utils.Strings;
import ca.mcgill.sel.ram.ui.views.handler.IHandled;
import ca.mcgill.sel.ram.ui.views.handler.ITextViewHandler;

/**
 * A {@link TextView} is a {@link RamTextComponent} that displays text for the feature ({@link EStructuralFeature}) of a
 * given object. Furthermore, it handles updates and updates itself accordingly. For the feature name of
 * {@link ca.mcgill.sel.ram.NamedElement} the full textual representation is used.
 * Additionally, a {@link TextView} might have to contain a unique name.
 *
 * @author mschoettle
 */
public class TextView extends RamTextComponent implements INotifyChangedListener, IHandled<ITextViewHandler> {

    private ITextViewHandler handler;

    private EObject data;
    private EStructuralFeature feature;
    private boolean showFullLabel;
    private boolean uniqueName;
    /**
     * The item provider this view listens to when the feature is a reference to an object.
     * This allows to receive notifications regarding the referenced object to be able to update this view.
     */
    private ItemProviderAdapter referencedObjectItemProvider;

    /**
     * Creates a new {@link TextView} for the feature of the given object. If the feature is a name its full label will
     * be shown automatically.
     *
     * @param data
     *            the {@link EObject} that represents this {@link TextView}
     * @param feature
     *            the {@link EStructuralFeature} that should be displayed
     */
    public TextView(EObject data, EStructuralFeature feature) {
        // show the full label if it is the name
        this(data, feature, feature == CorePackage.Literals.CORE_NAMED_ELEMENT__NAME);
    }

    /**
     * Creates a new {@link TextView} for the feature of the given object.
     *
     * @param data
     *            the {@link EObject} that represents this {@link TextView}
     * @param feature
     *            the {@link EStructuralFeature} that should be displayed
     * @param showFullLabel
     *            whether the full label should be shown
     */
    public TextView(EObject data, EStructuralFeature feature, boolean showFullLabel) {
        super();

        setPlaceholderText(Strings.PH_SELECT);
        this.feature = feature;
        this.showFullLabel = showFullLabel;

        if (data != null) {
            setData(data);
        }
    }

    @Override
    public void destroy() {
        super.destroy();

        if (data != null) {
            unregisterChangeListener();
        }
    }

    /**
     * Returns the object that represents this {@link TextView}.
     *
     * @return the represented object
     */
    public EObject getData() {
        return data;
    }

    /**
     * Returns the feature that should be displayed for the object.
     *
     * @return the feature displayed for the object
     */
    public EStructuralFeature getFeature() {
        return feature;
    }

    @Override
    public ITextViewHandler getHandler() {
        return handler;
    }

    /**
     * Returns the text of the object depending on whether the full label should be used or not. If it is not an
     * {@link EObject} (e.g., an enum) it looks if a pretty printer exists (see
     * {@link RamModelUtils#getPrettyPrinter(EStructuralFeature)}). Otherwise it just gets the features text.
     *
     * @return the text of the object
     */
    protected String getModelText() {
        Object object = null;

        if (showFullLabel || data == null) {
            object = data;
        } else if (data.eClass().getEAllStructuralFeatures().contains(feature)) {
            object = data.eGet(feature);
        }

        String result = null;

        if (object == null) {
            result = "";
        } else if (object instanceof EObject) {
            if (!showFullLabel) {
                result = EMFEditUtil.getPropertyText(data, feature, object);
            }

            /**
             * Fall back in case getting the property text above was unsuccessful.
             */
            if (showFullLabel || result == null) {
                result = EMFEditUtil.getText((EObject) object);
            }
        } else {
            // if there is a pretty printer available for this feature use that one
            // otherwise use feature text
            if (RamModelUtils.getPrettyPrinter(feature) != null) {
                result = RamModelUtils.getPrettyPrinter(feature).get(object);
            } else {
                result = EMFEditUtil.getFeatureText(data, feature);
            }
        }

        return result;
    }

    /**
     * Returns whether the features notifications are of interested for this view. This is the case if it is a reference
     * with exactly one element and the feature is a feature of data.
     * Otherwise registering or unregistering as a listener will fail.
     * Note that the caller needs to make sure that the value of the feature is set and not null before using it.
     *
     * @return true, if a notification is required for the feature, false otherwise
     */
    private boolean isFeatureNotificationRequired() {
        return feature instanceof EReference
                && feature.getUpperBound() == 1
                /* Make sure the current feature and data are valid. */
                && data.eClass().getEAllStructuralFeatures().contains(feature);
    }

    /**
     * Returns whether the name is unique among all objects.
     *
     * @return true, if the name is unique, false otherwise
     */
    public boolean isUniqueName() {
        return uniqueName;
    }

    @Override
    public void keyboardCancelled() {
        // this needs to be called in order to perform the appropriate steps for the text component when the keyboard is
        // closed/cancelled
        super.keyboardCancelled();
        handler.keyboardCancelled(this);
    }

    @Override
    public void keyboardOpened() {
        handler.keyboardOpened(this);
    }

    @Override
    public void notifyChanged(Notification notification) {
        Object updatedElement = ((ViewerNotification) notification).getElement();
        // if the label is fully shown and depends on referenced objects, the item provider might use a change notifier.
        // in that case, the notifier doesn't match but the
        // element of the notification indicate the correct object
        if (updatedElement == data
                // if the label is fully shown (i.e., for named elements the getText of the item provider of the data
                // object is
                // called)
                // update the text no matter which feature was changed because it could be contained in the textual
                // representation
                || notification.getNotifier() == data && showFullLabel
                // otherwise just update if the feature represented here was changed
                || notification.getNotifier() == data && notification.getFeature() == feature
                // if the feature is a reference and the notifier is the referenced item, update the text as well
                || feature instanceof EReference
                        && (notification.getNotifier() == data.eGet(feature) || updatedElement == data.eGet(feature))) {
            updateText();

            // the listener needs to be removed from the old object
            // and added to the new one, otherwise no notifications will be received
            if (feature instanceof EReference) {
                // in case the old value was null no unregister is necessary
                if (getData() != null
                        && notification.getOldValue() != notification.getNewValue()) {
                    unregisterChangeListener();
                    registerChangeListener();
                }
            }
        }

    }

    /**
     * Registers this view as a listener to the data.
     */
    private void registerChangeListener() {
        EMFEditUtil.addListenerFor(data, this);

        // register for updates on the referenced item (only if the features value is not null)
        if (isFeatureNotificationRequired() && data.eGet(feature) != null) {
            EObject referencedObject = (EObject) data.eGet(feature);
            EMFEditUtil.addListenerFor(referencedObject, this);
            /**
             * Remember the listener to be able to remove when the feature is updated (issue #142).
             */
            referencedObjectItemProvider = EMFEditUtil.getItemProvider(referencedObject);
        }
    }

    /**
     * Sets the object that represents this {@link TextView} and updates the text of it.
     *
     * @param data
     *            the data object representing this {@link TextView}
     */
    public void setData(EObject data) {
        /**
         * If the data changes, the listener must be removed from the previous data.
         */
        if (this.data != null) {
            unregisterChangeListener();
        }

        this.data = data;
        updateText();

        if (data != null) {
            registerChangeListener();
        }
    }

    @Override
    public void setHandler(ITextViewHandler handler) {
        this.handler = handler;
    }

    /**
     * Sets whether the represented property (in this case a name) is unique.
     *
     * @param uniqueName
     *            true, if the name is unique, false otherwise
     */
    public void setUniqueName(boolean uniqueName) {
        this.uniqueName = uniqueName;
    }

    /**
     * Unregisters the change listeners for the data.
     */
    private void unregisterChangeListener() {
        if (data != null) {
            EMFEditUtil.removeListenerFor(data, this);

            /**
             * Unregister a previously registered listener on the referenced object of the feature.
             */
            if (referencedObjectItemProvider != null) {
                referencedObjectItemProvider.removeListener(this);
                referencedObjectItemProvider = null;
            }
        }
    }

    /**
     * Forces the text to be updated.
     */
    public void updateText() {
        String newText = getModelText();
        if (!newText.equals(this.getText())) {
            setText(newText);
        }
    }

    /**
     * Clear the text.
     */
    public void clearText() {
        setText("");
    }

    @Override
    public boolean verifyKeyboardDismissed() {
        return handler.shouldDismissKeyboard(this);
    }

    /**
     * Sets a new feature to this text view.
     *
     * @param feature the feature to be set
     * @param showFullName whether to show the full name
     */
    // CHECKSTYLE:IGNORE HiddenField: This is okay here.
    public void setFeature(EStructuralFeature feature, boolean showFullName) {
        this.feature = feature;
        this.showFullLabel = showFullName;

        if (data != null) {
            setData(data);
        }
    }

    /**
     * Registers a tap processor and sets the given handler as the listener.
     * The handler will also act as the handler of this view.
     *
     * @param handler the handler for this view
     */
    // CHECKSTYLE:IGNORE HiddenField: Okay here.
    public void registerTapProcessor(ITextViewHandler handler) {
        registerInputProcessor(new TapProcessor(RamApp.getApplication(), Constants.TAP_MAX_FINGER_UP,
                true, Constants.TAP_DOUBLE_TAP_TIME));
        addGestureListener(TapProcessor.class, handler);
        setHandler(handler);
    }
}
