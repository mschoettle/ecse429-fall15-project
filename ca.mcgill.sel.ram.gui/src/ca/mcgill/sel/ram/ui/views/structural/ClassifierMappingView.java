package ca.mcgill.sel.ram.ui.views.structural;

import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.ram.ClassifierMapping;
import ca.mcgill.sel.ram.ui.components.RamButton;
import ca.mcgill.sel.ram.ui.components.RamImageComponent;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.components.RamTextComponent.Alignment;
import ca.mcgill.sel.ram.ui.events.listeners.ActionListener;
import ca.mcgill.sel.ram.ui.layouts.HorizontalLayoutVerticallyCentered;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.utils.Fonts;
import ca.mcgill.sel.ram.ui.utils.Icons;
import ca.mcgill.sel.ram.ui.utils.Strings;
import ca.mcgill.sel.ram.ui.views.TextView;
import ca.mcgill.sel.ram.ui.views.handler.HandlerFactory;

/**
 * This view shows the classifier mapping such as ClassA-->ClassB in an instantiation
 * along with some buttons : Attribute MApping adding button,Operation Mapping adding
 * button and mapping delete button.
 * 
 * @author Engin
 */
public class ClassifierMappingView extends RamRectangleComponent implements ActionListener {
    
    private static final String ACTION_ATTRIBUTE_MAPPING_ADD = "view.attributeMapping.add";
    private static final String ACTION_OPERATION_MAPPING_ADD = "view.operationMapping.add";
    private static final String ACTION_CLASSIFIER_MAPPING_DELETE = "view.classifierMapping.delete";
    
    private static final float ICON_SIZE = Fonts.FONTSIZE_INSTANTIATION + 2;
    
    private ClassifierMapping myClassifierMapping;
    
    /**
     * Button to add Attribute Mapping.
     */
    private RamButton buttonAttributeMappingAdd;
    
    /**
     * Button to add Operation Mapping.
     */
    private RamButton buttonOperationMappingAdd;
    
    /**
     * Button to delete Classifier Mapping.
     */
    private RamButton buttonClassifierMappingDelete;
    
    /**
     * ClassifierMapping from element.
     */
    private TextView textClassifierFromElement;
    
    /**
     * ClassifierMapping to element.
     */
    private TextView textClassifierToElement;
    
    /**
     * Image for an arrow between mapping elements.
     */
    private RamImageComponent arrow;
    
    /**
     * Creates a new classifier mapping view for the given classifier mapping.
     * 
     * @param containerView the container this view is contained in
     * @param classifierMapping the {@link ClassifierMapping} to create a view for
     */
    public ClassifierMappingView(MappingContainerView containerView, ClassifierMapping classifierMapping) {
        setNoStroke(true);
        setNoFill(false);
        setFillColor(Colors.CLASS_MAPPING_VIEW_FILL_COLOR);
        setBuffers(0);
        // setBufferSize(Cardinal.NORTH, 2);
        // setBufferSize(Cardinal.EAST, 0);
        
        // myMappingContainerView = mappingContainerView;
        myClassifierMapping = classifierMapping;
        
        // Add delete button
        
        RamImageComponent deleteClassifierMappingImage = new RamImageComponent(Icons.ICON_DELETE, 
                Colors.ICON_DELETE_COLOR);
        deleteClassifierMappingImage.setMinimumSize(ICON_SIZE, ICON_SIZE);
        deleteClassifierMappingImage.setMaximumSize(ICON_SIZE, ICON_SIZE);
        buttonClassifierMappingDelete = new RamButton(deleteClassifierMappingImage);
        buttonClassifierMappingDelete.setActionCommand(ACTION_CLASSIFIER_MAPPING_DELETE);
        buttonClassifierMappingDelete.addActionListener(this);
        addChild(buttonClassifierMappingDelete);
        
        textClassifierFromElement = new TextView(myClassifierMapping,
                CorePackage.Literals.CORE_MAPPING__FROM);
        textClassifierFromElement.setFont(Fonts.FONT_INSTANTIATION);
        textClassifierFromElement.setBufferSize(Cardinal.SOUTH, 0);
        textClassifierFromElement.setBufferSize(Cardinal.EAST, 0);
        textClassifierFromElement.setBufferSize(Cardinal.WEST, 0);
        textClassifierFromElement.setAlignment(Alignment.CENTER_ALIGN);
        textClassifierFromElement.setPlaceholderText(Strings.PH_SELECT_CLASS);
        textClassifierFromElement.setAutoMinimizes(true);
        textClassifierFromElement.setHandler(HandlerFactory.INSTANCE.getTextViewHandler());
        this.addChild(textClassifierFromElement);
        
        arrow = new RamImageComponent(Icons.ICON_ARROW_RIGHT, Colors.ICON_ARROW_COLOR);
        arrow.setMinimumSize(ICON_SIZE, ICON_SIZE);
        arrow.setMaximumSize(ICON_SIZE, ICON_SIZE);
        this.addChild(arrow);
        
        textClassifierToElement = new TextView(myClassifierMapping, CorePackage.Literals.CORE_MAPPING__TO);
        textClassifierToElement.setFont(Fonts.FONT_INSTANTIATION);
        textClassifierToElement.setBufferSize(Cardinal.SOUTH, 0);
        textClassifierToElement.setBufferSize(Cardinal.EAST, 0);
        textClassifierToElement.setBufferSize(Cardinal.WEST, 0);
        textClassifierToElement.setAlignment(Alignment.CENTER_ALIGN);
        textClassifierToElement.setPlaceholderText(Strings.PH_SELECT_CLASS);
        textClassifierToElement.setAutoMinimizes(true);
        textClassifierToElement.setHandler(HandlerFactory.INSTANCE.getTextViewHandler());
        
        this.addChild(textClassifierToElement);
        
        // Add buttons for adding attribute mapping,
        // adding operation mapping and a button for classifier mapping deleting
        RamImageComponent attributeMappingAddImage = new RamImageComponent(Icons.ICON_ATTRIBUTE_MAPPING_ADD,
                Colors.ICON_STRUCT_DEFAULT_COLOR);
        attributeMappingAddImage.setMinimumSize(ICON_SIZE, ICON_SIZE);
        attributeMappingAddImage.setMaximumSize(ICON_SIZE, ICON_SIZE);
        buttonAttributeMappingAdd = new RamButton(attributeMappingAddImage);
        buttonAttributeMappingAdd.setActionCommand(ACTION_ATTRIBUTE_MAPPING_ADD);
        buttonAttributeMappingAdd.addActionListener(this);
        addChild(buttonAttributeMappingAdd);
        
        RamImageComponent operationMappingAddImage = new RamImageComponent(Icons.ICON_OPERATION_MAPPING_ADD,
                Colors.ICON_STRUCT_DEFAULT_COLOR);
        operationMappingAddImage.setMinimumSize(ICON_SIZE, ICON_SIZE);
        operationMappingAddImage.setMaximumSize(ICON_SIZE, ICON_SIZE);
        buttonOperationMappingAdd = new RamButton(operationMappingAddImage);
        buttonOperationMappingAdd.setActionCommand(ACTION_OPERATION_MAPPING_ADD);
        buttonOperationMappingAdd.addActionListener(this);
        addChild(buttonOperationMappingAdd);
        
        setLayout(new HorizontalLayoutVerticallyCentered(Fonts.FONTSIZE_INSTANTIATION / 5));
        
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        String actionCommand = event.getActionCommand();
        
        if (ACTION_ATTRIBUTE_MAPPING_ADD.equals(actionCommand)) {
            HandlerFactory.INSTANCE.getMappingContainerViewHandler().addAttributeMapping(myClassifierMapping);
        } else if (ACTION_OPERATION_MAPPING_ADD.equals(actionCommand)) {
            HandlerFactory.INSTANCE.getMappingContainerViewHandler().addOperationMapping(myClassifierMapping);
        } else if (ACTION_CLASSIFIER_MAPPING_DELETE.equals(actionCommand)) {
            HandlerFactory.INSTANCE.getMappingContainerViewHandler().deleteClassifierMapping(myClassifierMapping);
        }
    }
    
}
