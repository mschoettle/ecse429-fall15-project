package ca.mcgill.sel.ram.ui.views.structural;

import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.ram.AttributeMapping;
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
import ca.mcgill.sel.ram.ui.views.structural.handler.impl.AttributeMappingToElementHandler;

/**
 * This view shows the attribute mapping such as AttributeA-->AttributeB along with a delete button.
 * 
 * @author eyildirim
 */
public class AttributeMappingView extends RamRoundedRectangleComponent implements ActionListener {
    
    private static final String ACTION_ATTRIBUTE_MAPPING_DELETE = "view.attributeMapping.delete";
    
    private static final float ICON_SIZE = Fonts.FONTSIZE_INSTANTIATION + 2;
    
    private AttributeMapping myAttributeMapping;
    
    /**
     * Button to delete Attribute Mapping.
     */
    private RamButton buttonAttributeMappingDelete;
    
    /**
     * AttributeMapping from element.
     */
    private TextView textAttributeMappingFromElement;
    
    /**
     * AttributeMapping to element.
     */
    private TextView textAttributeMappingToElement;
    
    /**
     * Image for an arrow between mapping elements.
     */
    private RamImageComponent arrow;
    
    /**
     * Creates a new view for the given attribute mapping.
     * 
     * @param mappingContainerView
     *            the {@link MappingContainerView} which holds this {@link AttributeMappingView}
     * @param attributeMapping the {@link AttributeMapping} to create a view for
     */
    public AttributeMappingView(MappingContainerView mappingContainerView, AttributeMapping attributeMapping) {
        super(4);
        setNoStroke(true);
        setNoFill(true);
        // setFillColor(Constants.ATTRIBUTE_MAPPING_VIEW_FILL_COLOR);
        // setBufferSize(Cardinal.NORTH, 5);
        setBuffers(0);
        // myMappingContainerView = mappingContainerView;
        myAttributeMapping = attributeMapping;
        
        // Add a button for deleting the attribute mapping
        
        RamImageComponent deleteAttributeMappingImage =
                new RamImageComponent(Icons.ICON_DELETE, Colors.ICON_DELETE_COLOR, ICON_SIZE, ICON_SIZE);
        buttonAttributeMappingDelete = new RamButton(deleteAttributeMappingImage);
        buttonAttributeMappingDelete.setActionCommand(ACTION_ATTRIBUTE_MAPPING_DELETE);
        buttonAttributeMappingDelete.addActionListener(this);
        buttonAttributeMappingDelete.setBuffers(0);
        buttonAttributeMappingDelete.setBufferSize(Cardinal.WEST, 5);
        addChild(buttonAttributeMappingDelete);
        
        RamTextComponent attrText = new RamTextComponent(Strings.LABEL_ATTRIBUTE);
        attrText.setFont(Fonts.FONT_INSTANTIATION);
        attrText.setBufferSize(Cardinal.SOUTH, 0);
        attrText.setBufferSize(Cardinal.EAST, 0);
        attrText.setBufferSize(Cardinal.WEST, Fonts.FONTSIZE_INSTANTIATION);
        this.addChild(attrText);
        
        textAttributeMappingFromElement = new TextView(myAttributeMapping,
                CorePackage.Literals.CORE_MAPPING__FROM);
        textAttributeMappingFromElement.setFont(Fonts.FONT_INSTANTIATION);
        textAttributeMappingFromElement.setBufferSize(Cardinal.SOUTH, 0);
        textAttributeMappingFromElement.setBufferSize(Cardinal.EAST, 0);
        textAttributeMappingFromElement.setBufferSize(Cardinal.WEST, 0);
        textAttributeMappingFromElement.setAlignment(Alignment.CENTER_ALIGN);
        textAttributeMappingFromElement.setPlaceholderText(Strings.PH_SELECT_ATTRIBUTE);
        textAttributeMappingFromElement.setAutoMinimizes(true);
        textAttributeMappingFromElement.setHandler(HandlerFactory.INSTANCE.getTextViewHandler());
        this.addChild(textAttributeMappingFromElement);
        
        arrow = new RamImageComponent(Icons.ICON_ARROW_RIGHT, Colors.ICON_ARROW_COLOR, ICON_SIZE, ICON_SIZE);
        arrow.setBufferSize(Cardinal.EAST, 1.0f);
        arrow.setBufferSize(Cardinal.WEST, 1.0f);
        this.addChild(arrow);
        
        textAttributeMappingToElement = new TextView(myAttributeMapping,
                CorePackage.Literals.CORE_MAPPING__TO);
        textAttributeMappingToElement.setFont(Fonts.FONT_INSTANTIATION);
        textAttributeMappingToElement.setBufferSize(Cardinal.SOUTH, 0);
        textAttributeMappingToElement.setBufferSize(Cardinal.EAST, 0);
        textAttributeMappingToElement.setBufferSize(Cardinal.WEST, 0);
        textAttributeMappingToElement.setAlignment(Alignment.CENTER_ALIGN);
        textAttributeMappingToElement.setPlaceholderText(Strings.PH_SELECT_ATTRIBUTE);
        textAttributeMappingToElement.setAutoMinimizes(true);
        textAttributeMappingToElement.setHandler(new AttributeMappingToElementHandler());
        this.addChild(textAttributeMappingToElement);
        
        setLayout(new HorizontalLayoutVerticallyCentered(Fonts.FONTSIZE_INSTANTIATION / 5));
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        String actionCommand = event.getActionCommand();
        
        if (ACTION_ATTRIBUTE_MAPPING_DELETE.equals(actionCommand)) {
            HandlerFactory.INSTANCE.getMappingContainerViewHandler().deleteAttributeMapping(myAttributeMapping);
        }
        
    }
    
    /**
     * Returns the Attribute Mapping.
     * 
     * @return {@link AttributeMapping}
     */
    public AttributeMapping getAttributeMapping() {
        return myAttributeMapping;
    }
    
}
