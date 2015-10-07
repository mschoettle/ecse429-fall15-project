package ca.mcgill.sel.ram.ui.views.structural;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.INotifyChangedListener;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.core.COREModelCompositionSpecification;
import ca.mcgill.sel.core.COREModelReuse;
import ca.mcgill.sel.core.COREReuse;
import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.Instantiation;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamButton;
import ca.mcgill.sel.ram.ui.components.RamExpendableComponent;
import ca.mcgill.sel.ram.ui.components.RamImageComponent;
import ca.mcgill.sel.ram.ui.components.RamPopup.PopupType;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.components.RamTextComponent;
import ca.mcgill.sel.ram.ui.events.listeners.ActionListener;
import ca.mcgill.sel.ram.ui.layouts.HorizontalLayout;
import ca.mcgill.sel.ram.ui.layouts.HorizontalLayoutVerticallyCentered;
import ca.mcgill.sel.ram.ui.layouts.VerticalLayout;
import ca.mcgill.sel.ram.ui.scenes.DisplayAspectScene;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.utils.Fonts;
import ca.mcgill.sel.ram.ui.utils.Icons;
import ca.mcgill.sel.ram.ui.utils.Strings;
import ca.mcgill.sel.ram.ui.views.structural.handler.IInstantiationsContainerViewHandler;
import ca.mcgill.sel.ram.util.AssociationConstants;

/**
 * An {@link InstantiationsContainerView} is a {@link RamExpendableComponent} which shows all the
 * instantiations that an {@link Aspect} has. By using this container it is possible to see the details of an
 * {@link Instantiation}.
 *
 * @author eyildirim
 * @author oalam
 */
public class InstantiationsContainerView extends RamExpendableComponent implements
        INotifyChangedListener, ActionListener {

    // default filling color of the menu
    private static final String ACTION_INSTANTIATION_ADD = "view.instantiation.add";

    private static final float ICON_SIZE = Icons.ICON_ADD.width - 8;

    // The aspect which has all the instantiations
    private Aspect aspect;

    // button for adding instantiations
    private RamButton buttonPlus;

    // A container located at the top of the instantiation container which contains instantiation adding button . More
    // buttons can be added in the future.
    private final RamRectangleComponent buttonContainer;
    private final RamRectangleComponent titleContainer;
    private final RamRectangleComponent instantiationsContainer;

    private Map<Instantiation, InstantiationView> instantiationViews;
    private boolean isModelReuseContainer;

    private IInstantiationsContainerViewHandler handler;

    /**
     * Constructor.
     *
     * @param displayAspectScene is the scene which has this instantiationContainerView.
     * @param isModelReuseContainer is a boolean to indicate whether the InstantiationsContainerView is called to show
     *            the modelreuses.
     * @param title - the title for the view
     */
    public InstantiationsContainerView(DisplayAspectScene displayAspectScene, boolean isModelReuseContainer,
            String title) {
        super(0, ICON_SIZE);

        this.isModelReuseContainer = isModelReuseContainer;
        aspect = displayAspectScene.getAspect();

        setNoFill(true);
        setTitleNoFill(true);
        setTitleNoStroke(true);
        setEnabled(true);
        setNoStroke(true);

        EMFEditUtil.addListenerFor(aspect, this);

        /* Build title */
        titleContainer = new RamRectangleComponent(new HorizontalLayoutVerticallyCentered());

        RamTextComponent titleText = new RamTextComponent(title);
        titleText.setFont(Fonts.INST_CONTAINER_TITLE_FONT);
        // create a row of container for the buttons and add it to the top of the menu
        buttonContainer = new RamRectangleComponent();
        buttonContainer.setLayout(new HorizontalLayout());
        initButtons();
        titleContainer.addChild(buttonContainer);
        titleContainer.addChild(titleText);
        setTitle(titleContainer);

        /* Build content */
        instantiationsContainer = new RamRectangleComponent(new VerticalLayout());

        // keep a map of instantiation views. We used Linked hash map because we want to keep the orders that the
        // elements are added.
        instantiationViews = Collections.synchronizedMap(new LinkedHashMap<Instantiation, InstantiationView>());

        // create all instantiation views and add them to the container
        if (isModelReuseContainer) {
            addAllModelReuseViews();
        } else {
            addAllInstantiationViews();
        }

        setHideableComponent(instantiationsContainer);
        // Expanded by default
        showHideableComponent();
    }

    /**
     * Set handler for this {@link InstantiationsContainerView}.
     *
     * @param handler - the {@link IInstantiationsContainerViewHandler} to set.
     */
    public void setHandler(IInstantiationsContainerViewHandler handler) {
        this.handler = handler;
    }

    /**
     * Get handler for this {@link InstantiationsContainerView}.
     *
     * @return the {@link IInstantiationsContainerViewHandler} to for this view.
     */
    public IInstantiationsContainerViewHandler getHandler() {
        return handler;
    }

    /**
     * Getter for isModelReuseContainer.
     *
     * @return isModelReuseContainer - whether this container is to show model reuses.
     */
    public boolean isModelReuseContainer() {
        return isModelReuseContainer;
    }

    /**
     * Adds the plus button.
     */
    public void initButtons() {
        buttonContainer.removeAllChildren();
        // add plus button to the top row button container
        RamImageComponent addImage = new RamImageComponent(Icons.ICON_ADD, Colors.ICON_STRUCT_DEFAULT_COLOR,
                ICON_SIZE, ICON_SIZE);

        buttonPlus = new RamButton(addImage);
        buttonPlus.setActionCommand(ACTION_INSTANTIATION_ADD);
        buttonPlus.addActionListener(this);

        buttonContainer.addChild(buttonPlus);
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        String actionCommand = event.getActionCommand();

        if (ACTION_INSTANTIATION_ADD.equals(actionCommand)) {
            if (isModelReuseContainer && aspect.getRealizes().isEmpty()) {
                RamApp.getActiveScene().displayPopup(Strings.popupAspectNeedsRealize(aspect.getName()),
                        PopupType.ERROR);
            } else {
                getHandler().loadBrowser(aspect);
            }
        } else {
            super.actionPerformed(event);
        }
    }

    /**
     * Adds all instantiation views.
     */
    private void addAllInstantiationViews() {
        for (final Instantiation instantiation : aspect.getInstantiations()) {
            createInstantiationView(instantiation, null);
        }
    }

    /**
     * Adds all ModelReuseViews.
     */
    private void addAllModelReuseViews() {
        for (COREModelReuse modelReuse : aspect.getModelReuses()) {
            for (COREModelCompositionSpecification<?> comp : modelReuse.getCompositions()) {
                if (!isAssociationReuse(modelReuse)) {
                    createInstantiationView((Instantiation) comp, modelReuse.getReuse());
                }
            }
        }
    }

    /**
     * Creates an instantiation view and put instantiation data into the map along with its mapping view.It also adds
     * itself as a child.
     *
     * @param instantiation instantiation to be created
     * @return instantiation just created.
     */
    private InstantiationView createInstantiationView(Instantiation instantiation) {
        return createInstantiationView(instantiation, null);
    }

    /**
     * Creates an instantiation view and put instantiation data into the map along with its mapping view.It also adds
     * itself as a child.
     *
     * @param instantiation instantiation to be created
     * @param reuse the reuse the instantiation is for
     * @return instantiation just created.
     */
    private InstantiationView createInstantiationView(Instantiation instantiation, COREReuse reuse) {
        InstantiationView view = new InstantiationView(instantiation, reuse, this);
        instantiationViews.put(instantiation, view);
        instantiationsContainer.addChild(view);
        return view;
    }

    /**
     * Deletes an instantiation view.
     *
     * @param instantiation is the instantiation to be deleted.
     */
    private void deleteInstantiationView(Instantiation instantiation) {
        InstantiationView view = instantiationViews.get(instantiation);
        instantiationViews.remove(instantiation);
        instantiationsContainer.removeChild(view);
        view.destroy();
    }

    @Override
    public void destroy() {
        EMFEditUtil.removeListenerFor(aspect, this);
        super.destroy();
    }

    /**
     * Gets the button container of the view.
     *
     * @return buttonContainer
     */
    public RamRectangleComponent getButtonContainer() {
        return buttonContainer;
    }

    /**
     * Gets the map for the instantiation views.
     *
     * @return the map for all Instantiations to InstantiatoinViews.
     */
    public Map<Instantiation, InstantiationView> getInstantiationViews() {
        return instantiationViews;
    }

    /**
     * Gets the plus button.
     *
     * @return plus button
     */
    public RamButton getPlusButton() {
        return buttonPlus;
    }

    /**
     * Gets the visible biggest container of the {@link InstantiationsContainerView}.
     *
     * @return biggest container of the {@link InstantiationsContainerView}.
     */
    public RamRectangleComponent getInstantiationsContainer() {
        return instantiationsContainer;
    }

    @Override
    public void notifyChanged(Notification notification) {

        if (notification.getNotifier() == aspect) {

            if (notification.getFeature() == CorePackage.Literals.CORE_MODEL__MODEL_REUSES && isModelReuseContainer) {
                switch (notification.getEventType()) {
                    case Notification.ADD:
                        COREModelReuse modelReuseToAdd = (COREModelReuse) notification.getNewValue();
                        if (!isAssociationReuse(modelReuseToAdd)) {
                            for (COREModelCompositionSpecification<?> composition : modelReuseToAdd.getCompositions()) {
                                createInstantiationView((Instantiation) composition, modelReuseToAdd.getReuse());
                            }
                        }
                        break;
                    case Notification.REMOVE:
                        COREModelReuse modelReuseToRemove = (COREModelReuse) notification.getOldValue();
                        if (!isAssociationReuse(modelReuseToRemove)) {
                            for (COREModelCompositionSpecification<?> composition : modelReuseToRemove
                                    .getCompositions()) {
                                deleteInstantiationView((Instantiation) composition);
                            }
                        }
                        break;
                }
            } else if (notification.getFeature() == RamPackage.Literals.ASPECT__INSTANTIATIONS
                    && !isModelReuseContainer) {
                switch (notification.getEventType()) {
                    case Notification.ADD:
                        Instantiation instantiationNew = (Instantiation) notification.getNewValue();
                        createInstantiationView(instantiationNew);
                        break;

                    case Notification.REMOVE:
                        Instantiation instantiationOld = (Instantiation) notification.getOldValue();
                        deleteInstantiationView(instantiationOld);
                        break;
                }
            }
        }
    }

    /**
     * Returns whether if the model reuse is a reuse of the association concern.
     *
     * @param modelReuse The model reuse to check for.
     * @return true if the model reuse is a reuse of the association concern.
     */
    private static boolean isAssociationReuse(COREModelReuse modelReuse) {
        return AssociationConstants.CONCERN_NAME.equals(modelReuse.getReuse().getReusedConcern().getName());
    }
}
