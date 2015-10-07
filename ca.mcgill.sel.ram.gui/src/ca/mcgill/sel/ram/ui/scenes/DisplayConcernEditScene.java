package ca.mcgill.sel.ram.ui.scenes;

import java.io.File;
import java.util.Collection;
import java.util.Map.Entry;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.commons.emf.util.EMFModelUtil;
import ca.mcgill.sel.core.COREConcern;
import ca.mcgill.sel.core.COREFeatureModel;
import ca.mcgill.sel.core.COREImpactModel;
import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.core.impl.LayoutContainerMapImpl;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.ConfirmPopup;
import ca.mcgill.sel.ram.ui.components.browser.AspectFileBrowser;
import ca.mcgill.sel.ram.ui.scenes.handler.IConcernEditSceneHandler;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.utils.Constants;
import ca.mcgill.sel.ram.ui.utils.Icons;
import ca.mcgill.sel.ram.ui.utils.Strings;
import ca.mcgill.sel.ram.ui.views.containers.COREAspectContainer;

/**
 * Class for the FeatureModel scene of the concern.
 *
 * @author Nishanth
 */
public class DisplayConcernEditScene extends AbstractConcernScene<FeatureDiagramEditView, IConcernEditSceneHandler>
        implements INotifyChangedListener {

    private static final String ACTION_MENU = "display.menu";
    private static final String ACTION_NEW_ASPECT = "new.aspect";
    private static final String ACTION_EXPAND_FEATURES = "expand.feature";

    /**
     * Boolean value to hold whether the reuses should be displayed or not.
     */
    private boolean showReuses;

    private COREAspectContainer aspectContainer;

    /**
     * Constructor called when the concern is loaded. Initializes everything and adds to the TopLayer.
     *
     * @param app - The current {@link RamApp}
     * @param concern - The {@link COREConcern} to display
     */
    public DisplayConcernEditScene(RamApp app, COREConcern concern) {

        // Calling the constructor of the Abstract scene with the name of the concern
        super(app, concern.getName(), true);

        // Assigning the concern and the filePath
        this.concern = concern;
        filePath = new File(concern.eResource().getURI().trimSegments(1).toFileString());

        concernRectangle = new MTRectangle(app, getWidth(), getHeight());
        concernRectangle.setFillColor(Colors.BACKGROUND_COLOR);
        concernRectangle.setNoFill(false);
        concernRectangle.setPickable(false);
        concernRectangle.unregisterAllInputProcessors();

        EMFEditUtil.addListenerFor(concern.getFeatureModel(), this);
        EMFEditUtil.addListenerFor(concern, this);
        COREImpactModel im = concern.getImpactModel();
        if (im != null) {
            EMFEditUtil.addListenerFor(im, this);
        }

        replot();

        setCommandStackListener(concern);
    }

    @Override
    public boolean destroy() {
        EMFEditUtil.removeListenerFor(concern.getFeatureModel(), this);
        EMFEditUtil.removeListenerFor(concern, this);
        COREImpactModel im = concern.getImpactModel();
        if (im != null) {
            EMFEditUtil.removeListenerFor(im, this);
        }

        return super.destroy();
    }

    /**
     * Getter for the showReuse boolean.
     *
     * @return showReuses
     */
    public boolean isShowReuses() {
        return showReuses;
    }

    /**
     * Getter for the {@link Aspect} selector container.
     *
     * @return - The {@link COREAspectContainer}
     */
    public COREAspectContainer getAspectSelector() {
        return aspectContainer;
    }

    /**
     * Get all aspects in the model of the concern.
     *
     * @return the list of the concern's aspects
     */
    private Collection<Aspect> getAllAspects() {
        return EMFModelUtil.collectElementsOfType(concern,
                CorePackage.Literals.CORE_CONCERN__MODELS, RamPackage.eINSTANCE.getAspect());
    }

    /**
     * Checks whether an aspect is selected in the {@link COREAspectContainer} or not.
     *
     * @return true if an Aspect was selected, false otherwise
     */
    public boolean isAspectSelected() {
        return getAspectSelector() != null && getAspectSelector().getSelected() != null;
    }

    /**
     * Shows a confirm popup for the given aspect to ask the user whether the aspect should be saved.
     *
     * @param parent the scene where the popup should be displayed, usually the current scene
     * @param listener the listener to inform which option the user selected
     */
    public void showCloseConfirmPopup(RamAbstractScene<?> parent, ConfirmPopup.SelectionListener listener) {
        String message = Strings.MODEL_CONCERN + " " + concern.getName() + Strings.POPUP_MODIFIED_SAVE;
        ConfirmPopup saveConfirmPopup = new ConfirmPopup(message, ConfirmPopup.OptionType.YES_NO_CANCEL);
        saveConfirmPopup.setListener(listener);

        parent.displayPopup(saveConfirmPopup);
    }

    @Override
    public void onEnter() {
        AspectFileBrowser.setInitialFolder(filePath.getAbsolutePath());
        AspectFileBrowser.setRootFolder(filePath.getAbsolutePath());

        // Called because when going back from an aspect scene, resources are unloaded for the aspect
        // so the aspect in the container is a proxy. This updates all list elements to not be proxies.
        if (aspectContainer != null) {
            aspectContainer.refreshContent();
        }
    }

    /*
     * --------------------- DISPLAY -------------------
     */
    /**
     * Function called to re-traverse from the root and plot again.
     */
    @Override
    protected void replot() {
        // Get the root of the feature model
        COREFeatureModel fm = concern.getFeatureModel();
        root = fm.getRoot();

        // Draw feature diagram
        redrawFeatureDiagram(true);

        // Container for the aspects defined in the concern
        aspectContainer = new COREAspectContainer(concern, this);
        aspectContainer.setElements(getAllAspects());

        containerLayer.addChild(aspectContainer);
    }



    /*
     * --------------------- BEHAVIOR ---------------------
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        String actionCommand = event.getActionCommand();
        if (ACTION_MENU.equals(actionCommand)) {
            handler.switchToHome(this);
        } else if (ACTION_NEW_ASPECT.equals(actionCommand)) {
            handler.createAspect(this);
        } else if (ACTION_EXPAND_FEATURES.equals(actionCommand)) {
            
        } else {
            super.actionPerformed(event);
        }
    }

    @Override
    public void notifyChanged(Notification notification) {
        if (notification.getFeature() == CorePackage.Literals.CORE_CONCERN__MODELS) {
            // if an aspect has been added or removed, update the list
            if (notification.getEventType() == Notification.ADD && notification.getNewValue() instanceof Aspect) {
                aspectContainer.addElement((Aspect) notification.getNewValue());
            } else if (notification.getEventType() == Notification.REMOVE
                    && notification.getOldValue() instanceof Aspect) {
                Aspect aspect = (Aspect) notification.getOldValue();
                if (aspect.eIsProxy()) {
                    aspect = (Aspect) EcoreUtil.resolve(aspect, concern);
                }
                aspectContainer.removeElement(aspect);
            }
        } else if (notification.getFeature() == CorePackage.Literals.CORE_CONCERN__IMPACT_MODEL) {
            COREImpactModel im;
            switch (notification.getEventType()) {
                case Notification.SET:
                    im = (COREImpactModel) notification.getNewValue();
                    if (im != null) {
                        EMFEditUtil.addListenerFor(im, this);
                        
                        break;
                    }
                case Notification.UNSET:
                    im = (COREImpactModel) notification.getOldValue();
                    EMFEditUtil.removeListenerFor(im, this);
                    break;
            }
        } else if (notification.getFeature() == CorePackage.Literals.CORE_IMPACT_MODEL__LAYOUTS) {
            if (this == RamApp.getActiveScene()) {
                final LayoutContainerMapImpl containerMapImpl;
                switch (notification.getEventType()) {
                    case Notification.ADD:
                        containerMapImpl = (LayoutContainerMapImpl) notification.getNewValue();

                        containerMapImpl.eAdapters().add(new EContentAdapter() {
                            @Override
                            public void notifyChanged(final Notification notif) {
                                if (notif.getFeature() == CorePackage.Literals.LAYOUT_CONTAINER_MAP__VALUE) {
                                    final Entry<?, ?> entry;
                                    switch (notif.getEventType()) {
                                        case Notification.ADD:
                                            containerMapImpl.eAdapters().remove(this);
                                            entry = (Entry<?, ?>) notif.getNewValue();
                                            
                                            break;
                                    }
                                }
                            }
                        });
                        break;
                }
            }
        } else if (notification.getFeature() == CorePackage.Literals.CORE_FEATURE__EXCLUDES
                || notification.getFeature() == CorePackage.Literals.CORE_FEATURE__REQUIRES) {
            
        }
    }

    /**
     * Function called when the show// hide reuse button need to be interchanged and switched accordingly.
     */
    public void switchShowHideReuse() {
        
    }


    /**
     * Switch from normal edit mode to association mode.
     * Update the handlers for the features and expand collapsed features
     */
    public void switchToAssociationMode() {
        
    }

    /**
     * Switch from association mode to normal edit mode.
     * Un-select everything from the aspect container and update the handlers for the features.
     */
    public void switchToEditMode() {
        if (isAspectSelected()) {
            aspectContainer.unselect(this);
            return;
        }
    }

    /*
     * ---------------------- COLLAPSE -----------------------------
     */
    /**
     * Update the status of the 'expand all' button.
     * Check if features have to be removed from the collapsed feature list and if so remove them.
     */
    public void updateExpandButton() {
        
    }

    @Override
    protected void initMenu() {
        this.getMenu().addAction(Strings.MENU_HOME, Icons.ICON_MENU_HOME, ACTION_MENU, this, true);
        this.getMenu().addSubMenu(1, Constants.MENU_EXTRA);
        this.getMenu().addAction(Strings.MENU_NEW_ASPECT, Icons.ICON_MENU_ADD_ASPECT, ACTION_NEW_ASPECT, this,
                Constants.MENU_EXTRA,
                true);
    }

    @Override
    protected EObject getElementToSave() {
        return concern;
    }

    @Override
    public void redrawFeatureDiagram(boolean repopulate) {
        // TODO Auto-generated method stub
        
    }

}
