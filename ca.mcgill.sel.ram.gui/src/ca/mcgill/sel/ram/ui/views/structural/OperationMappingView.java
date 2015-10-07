package ca.mcgill.sel.ram.ui.views.structural;

import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.ram.OperationMapping;
import ca.mcgill.sel.ram.ui.components.RamButton;
import ca.mcgill.sel.ram.ui.components.RamImageComponent;
import ca.mcgill.sel.ram.ui.components.RamRoundedRectangleComponent;
import ca.mcgill.sel.ram.ui.components.RamTextComponent;
import ca.mcgill.sel.ram.ui.components.RamTextComponent.Alignment;
import ca.mcgill.sel.ram.ui.events.listeners.ActionListener;
import ca.mcgill.sel.ram.ui.layouts.HorizontalLayoutVerticallyCentered;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.utils.Fonts;
import ca.mcgill.sel.ram.ui.utils.Icons;
import ca.mcgill.sel.ram.ui.utils.Strings;
import ca.mcgill.sel.ram.ui.views.TextView;
import ca.mcgill.sel.ram.ui.views.handler.HandlerFactory;
import ca.mcgill.sel.ram.ui.views.structural.handler.impl.OperationMappingToElementHandler;

/**
 * This view shows the operation mapping such as OperationA-->OperationB along with a delete button.
 * 
 * @author eyildirim
 */
public class OperationMappingView extends RamRoundedRectangleComponent implements ActionListener {
    
    /**
     * The action to delete the operation mapping.
     */
    protected static final String ACTION_OPERATION_MAPPING_DELETE = "view.operationMapping.delete";
    
    private OperationMapping myOperationMapping;
    
    // button to delete Operation Mapping
    private RamButton buttonOperationMappingDelete;
    
    // OperationMapping from element
    private TextView textOperationMappingFromElement;
    
    // OperationMapping to element
    private TextView textOperationMappingToElement;
    
    // image for an arrow between mapping elements
    private RamImageComponent arrow;
    
    /**
     * Creates a new view for the given operation mapping.
     * 
     * @param mappingContainerView
     *            the {@link MappingContainerView} which holds this {@link OperationMappingView}
     * @param operationMapping the {@link OperationMapping} this view should visualize
     */
    public OperationMappingView(MappingContainerView mappingContainerView, OperationMapping operationMapping) {
        super(4);
        setNoStroke(true);
        // setStrokeWeight(1.0f);
        setNoFill(true);
        // setFillColor(Constants.OPERATION_MAPPING_VIEW_FILL_COLOR);
        // setBufferSize(Cardinal.NORTH, 1);
        setBuffers(0);
        myOperationMapping = operationMapping;
        
        // Add a button for deleting the operation mapping
        
        RamImageComponent deleteOperationMappingImage = new RamImageComponent(Icons.ICON_DELETE,
                Colors.ICON_DELETE_COLOR);
        deleteOperationMappingImage.setMinimumSize(Fonts.FONTSIZE_INSTANTIATION + 2, Fonts.FONTSIZE_INSTANTIATION + 2);
        deleteOperationMappingImage.setMaximumSize(Fonts.FONTSIZE_INSTANTIATION + 2, Fonts.FONTSIZE_INSTANTIATION + 2);
        buttonOperationMappingDelete = new RamButton(deleteOperationMappingImage);
        buttonOperationMappingDelete.setActionCommand(ACTION_OPERATION_MAPPING_DELETE);
        buttonOperationMappingDelete.addActionListener(this);
        buttonOperationMappingDelete.setBufferSize(Cardinal.WEST, Fonts.FONTSIZE_INSTANTIATION / 5);
        addChild(buttonOperationMappingDelete);
        
        // Add "Op:" text
        
        RamTextComponent opText = new RamTextComponent(Strings.LABEL_OPERATION);
        opText.setFont(Fonts.FONT_INSTANTIATION);
        opText.setFont(Fonts.FONT_INSTANTIATION);
        opText.setBufferSize(Cardinal.SOUTH, 0);
        opText.setBufferSize(Cardinal.EAST, 0);
        opText.setBufferSize(Cardinal.WEST, Fonts.FONTSIZE_INSTANTIATION);
        this.addChild(opText);
        
        // Add the operation mapping from
        
        textOperationMappingFromElement = new TextView(myOperationMapping, CorePackage.Literals.CORE_MAPPING__FROM);
        textOperationMappingFromElement.setFont(Fonts.FONT_INSTANTIATION);
        textOperationMappingFromElement.setBufferSize(Cardinal.SOUTH, 0);
        textOperationMappingFromElement.setBufferSize(Cardinal.WEST, 0);
        textOperationMappingFromElement.setBufferSize(Cardinal.EAST, 0);
        textOperationMappingFromElement.setAlignment(Alignment.CENTER_ALIGN);
        textOperationMappingFromElement.setPlaceholderText(Strings.PH_SELECT_OPERATION);
        textOperationMappingFromElement.setAutoMinimizes(true);
        textOperationMappingFromElement.setHandler(HandlerFactory.INSTANCE.getTextViewHandler());
        this.addChild(textOperationMappingFromElement);
        
        // Add arrow
        
        arrow = new RamImageComponent(Icons.ICON_ARROW_RIGHT, Colors.ICON_ARROW_COLOR);
        arrow.setMinimumSize(Fonts.FONTSIZE_INSTANTIATION + 2, Fonts.FONTSIZE_INSTANTIATION + 2);
        arrow.setMaximumSize(Fonts.FONTSIZE_INSTANTIATION + 2, Fonts.FONTSIZE_INSTANTIATION + 2);
        this.addChild(arrow);
        
        // Add the operation mapping to
        
        textOperationMappingToElement = new TextView(myOperationMapping, CorePackage.Literals.CORE_MAPPING__TO);
        textOperationMappingToElement.setFont(Fonts.FONT_INSTANTIATION);
        textOperationMappingToElement.setBufferSize(Cardinal.SOUTH, 0);
        textOperationMappingToElement.setBufferSize(Cardinal.WEST, 0);
        textOperationMappingToElement.setBufferSize(Cardinal.EAST, 0);
        textOperationMappingToElement.setAlignment(Alignment.CENTER_ALIGN);
        textOperationMappingToElement.setPlaceholderText(Strings.PH_SELECT_OPERATION);
        textOperationMappingToElement.setAutoMinimizes(true);
        textOperationMappingToElement.setHandler(new OperationMappingToElementHandler());
        this.addChild(textOperationMappingToElement);
        
        setLayout(new HorizontalLayoutVerticallyCentered(Fonts.FONTSIZE_INSTANTIATION / 5));
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        String actionCommand = event.getActionCommand();
        
        if (ACTION_OPERATION_MAPPING_DELETE.equals(actionCommand)) {
            HandlerFactory.INSTANCE.getMappingContainerViewHandler().deleteOperationMapping(myOperationMapping);
        }
        
    }
    
    /**
     * Getter for the Operation Mapping.
     * 
     * @return {@link OperationMapping}
     */
    public OperationMapping getOperationMapping() {
        return myOperationMapping;
    }
    
}
