package ca.mcgill.sel.ram.ui.views.structural;

import ca.mcgill.sel.core.COREReuse;
import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.ram.Instantiation;
import ca.mcgill.sel.ram.ui.components.RamButton;
import ca.mcgill.sel.ram.ui.components.RamImageComponent;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.events.listeners.ActionListener;
import ca.mcgill.sel.ram.ui.layouts.HorizontalLayout;
import ca.mcgill.sel.ram.ui.layouts.HorizontalLayoutVerticallyCentered;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.utils.Fonts;
import ca.mcgill.sel.ram.ui.utils.Icons;
import ca.mcgill.sel.ram.ui.views.TextView;
import ca.mcgill.sel.ram.ui.views.handler.HandlerFactory;

/**
 * An {@link InstantiationTitleView} is a {@link RamRectangleComponent} that shows {@link Instantiation} name
 * and type of the {@link Instantiation} (e.g. depends/extends)
 * along with buttons to do different operations.
 * It is used to give a general overview and as a shortcut to show the full details of an instantiation mappings.
 * 
 * @author eyildirim
 */
public class InstantiationTitleView extends RamRectangleComponent implements ActionListener {

    private static final int ICON_SIZE = Fonts.FONTSIZE_INSTANTIATION + 2;

    private static final String ACTION_INSTANTIATION_SHOW_EXTERNAL_ASPECT = "view.instantiation.showExternalAspect";
    private static final String ACTION_INSTANTIATION_DELETE = "view.instantiation.deleteInstantiation";
    private static final String ACTION_INSTANTIATION_SHOW_MAPPING_DETAILS = "view.instantiation.showMappingDetails";
    private static final String ACTION_INSTANTIATION_HIDE_MAPPING_DETAILS = "view.instantiation.hideMappingDetails";
    private static final String ACTION_INSTANTIATION_TOGGLE_SPLIT_VIEW = "view.instantiation.switchToSplitView";
    private static final String ACTION_CLASSIFIER_MAPPING_ADD = "mapping.classifier.add";

    private InstantiationView myInstantiationView;
    private Instantiation instantiation;

    /**
     * Button container for expanding or collapsing button.
     * These expand and collapse buttons will be used to show/hide the details of an instantiation.
     */
    private RamRectangleComponent expandCollapseButtonsContainer;

    /**
     * Button to show details of the instantiation.
     */
    private RamButton buttonExpand;

    /**
     * Button to hide details of the instantiation.
     */
    private RamButton buttonCollapse;

    /**
     * Button to view the full Aspect view.
     */
    private RamButton mappingButton;

    /**
     * Button to delete instantiation.
     */
    private RamButton deleteButton;

    /**
     * Button to add Classifier Mapping.
     */
    private RamButton buttonAddClassifierMapping;

    /**
     * Name displayed as title. If the instantiation if for extending an aspect, it's the name of the aspect.
     * If it comes from a reuse, it's the name of the reuse.
     */
    private TextView instantiationName;

    /**
     * Creates a new title view.
     * 
     * @param instantiationView the {@link InstantiationView} the title view is for
     * @param reuse - the {@link COREReuse} the instantiation is for.
     * @param detailedViewOn whether the detailed view should be enabled
     */
    public InstantiationTitleView(InstantiationView instantiationView, COREReuse reuse, boolean detailedViewOn) {
        myInstantiationView = instantiationView;
        instantiation = myInstantiationView.getInstantiation();

        // This will be invisible and we will put expand/collapse buttons
        // inside of this container depending on the situation
        expandCollapseButtonsContainer = new RamRectangleComponent(new HorizontalLayout());
        this.addChild(expandCollapseButtonsContainer);

        RamImageComponent expandImage = new RamImageComponent(Icons.ICON_EXPAND,
                Colors.TRIANGLE_EXPAND_COLLAPSE_FILL_COLOR);
        expandImage.setMinimumSize(ICON_SIZE, ICON_SIZE);
        expandImage.setMaximumSize(ICON_SIZE, ICON_SIZE);
        buttonExpand = new RamButton(expandImage);
        buttonExpand.setActionCommand(ACTION_INSTANTIATION_SHOW_MAPPING_DETAILS);
        buttonExpand.addActionListener(this);

        RamImageComponent collapseImage = new RamImageComponent(Icons.ICON_COLLAPSE,
                Colors.TRIANGLE_EXPAND_COLLAPSE_FILL_COLOR);
        collapseImage.setMinimumSize(ICON_SIZE, ICON_SIZE);
        collapseImage.setMaximumSize(ICON_SIZE, ICON_SIZE);
        buttonCollapse = new RamButton(collapseImage);
        buttonCollapse.setActionCommand(ACTION_INSTANTIATION_HIDE_MAPPING_DETAILS);
        buttonCollapse.addActionListener(this);

        // depending on the detailed mapping view mode on/off
        if (detailedViewOn) {
            expandCollapseButtonsContainer.addChild(buttonCollapse);
        } else {
            expandCollapseButtonsContainer.addChild(buttonExpand);
        }

        RamImageComponent deleteImage = new RamImageComponent(Icons.ICON_DELETE, Colors.ICON_DELETE_COLOR);
        deleteImage.setMinimumSize(ICON_SIZE, ICON_SIZE);
        deleteImage.setMaximumSize(ICON_SIZE, ICON_SIZE);
        deleteButton = new RamButton(deleteImage);
        deleteButton.setActionCommand(ACTION_INSTANTIATION_DELETE);
        deleteButton.addActionListener(this);
        deleteImage.setBufferSize(Cardinal.WEST, Fonts.FONTSIZE_INSTANTIATION / 5);
        deleteImage.setBufferSize(Cardinal.EAST, Fonts.FONTSIZE_INSTANTIATION / 5);
        if (detailedViewOn) {
            addChild(deleteButton);
        }

        if (reuse != null) {
            instantiationName = new TextView(reuse, CorePackage.Literals.CORE_NAMED_ELEMENT__NAME);
            instantiationName.setUniqueName(true);
            instantiationName.setHandler(HandlerFactory.INSTANCE.getInstantiationDefaultNameHandler());
        } else {
            instantiationName =
                    new TextView(instantiation.getSource(), CorePackage.Literals.CORE_NAMED_ELEMENT__NAME);
            instantiationName.setHandler(HandlerFactory.INSTANCE.getInstantiationExtendNameHandler());
        }
        instantiationName.setBufferSize(Cardinal.SOUTH, 0);
        instantiationName.setBufferSize(Cardinal.EAST, 0);
        instantiationName.setBufferSize(Cardinal.WEST, 0);
        instantiationName.setFont(Fonts.FONT_INSTANTIATION);
        addChild(instantiationName);

        RamImageComponent mappingImage = new RamImageComponent(Icons.ICON_SPLIT_VIEW, Colors.ICON_STRUCT_DEFAULT_COLOR);
        mappingImage.setMinimumSize(ICON_SIZE, ICON_SIZE);
        mappingImage.setMaximumSize(ICON_SIZE, ICON_SIZE);
        mappingButton = new RamButton(mappingImage);
        mappingButton.setActionCommand(ACTION_INSTANTIATION_TOGGLE_SPLIT_VIEW);
        mappingButton.addActionListener(this);
        if (detailedViewOn) {
            addChild(mappingButton);
        }

        RamImageComponent addClassifierImage = new RamImageComponent(Icons.ICON_CLASSIFIER_MAPPING_ADD,
                Colors.ICON_STRUCT_DEFAULT_COLOR);
        addClassifierImage.setMinimumSize(ICON_SIZE, ICON_SIZE);
        addClassifierImage.setMaximumSize(ICON_SIZE, ICON_SIZE);
        buttonAddClassifierMapping = new RamButton(addClassifierImage);
        buttonAddClassifierMapping.setActionCommand(ACTION_CLASSIFIER_MAPPING_ADD);
        buttonAddClassifierMapping.addActionListener(this);
        if (detailedViewOn) {
            addChild(buttonAddClassifierMapping);
        }

        setBuffers(0);
        setLayout(new HorizontalLayoutVerticallyCentered(Fonts.FONTSIZE_INSTANTIATION / 5));

    }

    @Override
    protected void destroyComponent() {
        buttonAddClassifierMapping.destroy();
        deleteButton.destroy();
        mappingButton.destroy();
        buttonCollapse.destroy();
        buttonExpand.destroy();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String actionCommand = event.getActionCommand();
        if (ACTION_INSTANTIATION_DELETE.equals(actionCommand)) {
            myInstantiationView.getInstantiationsContainerView().getHandler().deleteInstantiation(instantiation);
        } else if (ACTION_INSTANTIATION_SHOW_MAPPING_DETAILS.equals(actionCommand)) {
            myInstantiationView.getHandler().showMappingDetails(myInstantiationView);
        } else if (ACTION_INSTANTIATION_HIDE_MAPPING_DETAILS.equals(actionCommand)) {
            myInstantiationView.getHandler().hideMappingDetails(myInstantiationView);
        } else if (ACTION_INSTANTIATION_SHOW_EXTERNAL_ASPECT.equals(actionCommand)) {
            myInstantiationView.getHandler().showExternalAspectOfInstantiation(myInstantiationView);
        } else if (ACTION_INSTANTIATION_TOGGLE_SPLIT_VIEW.equals(actionCommand)) {
            myInstantiationView.getHandler().switchToSplitView(myInstantiationView);
        } else if (ACTION_CLASSIFIER_MAPPING_ADD.equals(actionCommand)) {
            myInstantiationView.getHandler().addClassifierMapping(myInstantiationView);
        }
    }

    /**
     * Hide the add classifier mapping button.
     */
    public void hideAddClassifierMappingButton() {
        this.removeChild(buttonAddClassifierMapping);
    }

    /**
     * Hide the delete instantiation button.
     */
    public void hideDeleteInstantiationButton() {
        this.removeChild(deleteButton);
    }

    /**
     * Hide the mapping button.
     */
    public void hideMappingButton() {
        this.removeChild(mappingButton);
    }

    /**
     * Reveals the add classifier button.
     */
    public void showAddClassifierMappingButton() {
        this.addChild(buttonAddClassifierMapping);
    }

    /**
     * Reveals collapse button.
     */
    public void showCollapseButton() {
        expandCollapseButtonsContainer.removeAllChildren();
        expandCollapseButtonsContainer.addChild(buttonCollapse);
    }

    /**
     * Reveals the delete instantiation button.
     */
    public void showDeleteInstantiationButton() {
        this.addChild(2, deleteButton);
    }

    /**
     * Reveals expand button.
     */
    public void showExpandButton() {
        expandCollapseButtonsContainer.removeAllChildren();
        expandCollapseButtonsContainer.addChild(buttonExpand);
    }

    /**
     * Reveals eye button.
     */
    public void showMappingButton() {
        this.addChild(mappingButton);
    }
}
