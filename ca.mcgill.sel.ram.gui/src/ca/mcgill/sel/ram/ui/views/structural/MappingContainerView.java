package ca.mcgill.sel.ram.ui.views.structural;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.mt4j.components.MTComponent;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.ram.AttributeMapping;
import ca.mcgill.sel.ram.ClassifierMapping;
import ca.mcgill.sel.ram.OperationMapping;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.components.RamRoundedRectangleComponent;
import ca.mcgill.sel.ram.ui.layouts.VerticalLayout;
import ca.mcgill.sel.ram.ui.utils.Colors;

/**
 * This view contains a ClassifierMappingView and all mapping related container views for a classifier mapping.
 * 
 * @author eyildirim
 */
public class MappingContainerView extends RamRoundedRectangleComponent implements INotifyChangedListener {
    // It is useful to keep the child as a reference for hiding/showing purposes
    private ClassifierMappingView myClassifierMappingView;
    
    // Classifier Mapping information
    private ClassifierMapping myClassifierMapping;
    
    // All the attribute mapping related views will be in this view:
    private RamRectangleComponent hideableAttributeContainer;
    
    // All the operation mapping related views will be in this view:
    private RamRectangleComponent hideableOperationContainer;
    
    /**
     * Creates a new mapping container view.
     * 
     * @param classifierMapping
     *            {@link ClassifierMapping} which we want to create a {@link MappingContainerView} for.
     */
    public MappingContainerView(ClassifierMapping classifierMapping) {
        super(0);
        setNoStroke(false);
        setStrokeWeight(3);
        setNoFill(false);
        setFillColor(Colors.MAPPING_CONTAINER_VIEW_FILL_COLOR);
        
        myClassifierMappingView = new ClassifierMappingView(this, classifierMapping);
        this.addChild(myClassifierMappingView);
        
        myClassifierMapping = classifierMapping;
        
        hideableAttributeContainer = new RamRectangleComponent();
        hideableAttributeContainer.setLayout(new VerticalLayout(0));
        hideableAttributeContainer.setFillColor(Colors.ATTRIBUTE_MAPPING_VIEW_FILL_COLOR);
        hideableAttributeContainer.setNoFill(false);
        hideableAttributeContainer.setBufferSize(Cardinal.EAST, 0);
        
        hideableOperationContainer = new RamRectangleComponent();
        hideableOperationContainer.setLayout(new VerticalLayout(0));
        hideableOperationContainer.setFillColor(Colors.OPERATION_MAPPING_VIEW_FILL_COLOR);
        hideableOperationContainer.setNoFill(false);
        hideableOperationContainer.setBufferSize(Cardinal.EAST, 0);
        
        this.addChild(hideableAttributeContainer);
        this.addChild(hideableOperationContainer);
        
        setLayout(new VerticalLayout());
        
        // setBufferSize(Cardinal.EAST, 0);
        setBuffers(0);
        // add all the operation and attribute mappings related to this classifier mapping.
        addAllOperationMappings();
        addAllAttributeMappings();
        
        EMFEditUtil.addListenerFor(myClassifierMapping, this);
        
    }
    
    /**
     * Adds all the attribute mappings which belongs to the classifier mapping.
     */
    private void addAllAttributeMappings() {
        EList<AttributeMapping> attributeMappings = myClassifierMapping.getAttributeMappings();
        for (AttributeMapping attributeMapping : attributeMappings) {
            addAttributeMappingView(attributeMapping);
        }
        
    }
    
    /**
     * Adds all the operation mappings which belongs to the classifier mapping.
     */
    private void addAllOperationMappings() {
        EList<OperationMapping> operationMappings = myClassifierMapping.getOperationMappings();
        for (OperationMapping operationMapping : operationMappings) {
            addOperationMappingView(operationMapping);
        }
        
    }
    
    /**
     * Adds an {@link AttributeMappingView} to the mapping container view (inside of hideableAttributeContainer).
     * 
     * @param newAttributeMapping the {@link AttributeMapping} to add a view for
     */
    private void addAttributeMappingView(AttributeMapping newAttributeMapping) {
        AttributeMappingView attributeMappingView = new AttributeMappingView(this, newAttributeMapping);
        hideableAttributeContainer.addChild(attributeMappingView);
    }
    
    /**
     * Adds an {@link OperationMappingView} to the maping container view.
     * 
     * @param newOperationMapping the {@link OperationMapping} to add a view for
     */
    private void addOperationMappingView(OperationMapping newOperationMapping) {
        OperationMappingView operationMappingView = new OperationMappingView(this, newOperationMapping);
        hideableOperationContainer.addChild(operationMappingView);
    }
    
    /**
     * Deletes an {@link AttributeMappingView} from the mapping container view (inside to hideableAttributeContainer).
     * 
     * @param deletedAttributeMapping the {@link AttributeMapping} to remove the view for
     */
    private void deleteAttributeMappingView(AttributeMapping deletedAttributeMapping) {
        MTComponent[] attributeMappingViews = hideableAttributeContainer.getChildren();
        for (MTComponent view : attributeMappingViews) {
            if (view instanceof AttributeMappingView) {
                AttributeMappingView attributeMappingView = (AttributeMappingView) view;
                if (attributeMappingView.getAttributeMapping() == deletedAttributeMapping) {
                    hideableAttributeContainer.removeChild(attributeMappingView);
                }
            }
        }
        
    }
    
    /**
     * Deletes an {@link OperationMappingView} from the mapping container view (inside to hideableOperationContainer).
     * 
     * @param deletedOperationMapping the {@link OperationMapping} to remove the view for
     */
    private void deleteOperationMappingView(OperationMapping deletedOperationMapping) {
        
        MTComponent[] operationMappingViews = hideableOperationContainer.getChildren();
        for (MTComponent view : operationMappingViews) {
            if (view instanceof OperationMappingView) {
                OperationMappingView operationMappingView = (OperationMappingView) view;
                if (operationMappingView.getOperationMapping() == deletedOperationMapping) {
                    hideableOperationContainer.removeChild(operationMappingView);
                }
            }
        }
        
    }
    
    @Override
    public void destroy() {
        EMFEditUtil.removeListenerFor(myClassifierMapping, this);
        super.destroy();
    }
    
    /**
     * Getter for ClassifierMapping information of the view.
     * 
     * @return {@link ClassifierMapping}
     */
    public ClassifierMapping getClassifierMapping() {
        return myClassifierMapping;
    }
    
    @Override
    public void notifyChanged(Notification notification) {
        if (notification.getNotifier() == myClassifierMapping) {
            if (notification.getFeature() == RamPackage.Literals.CLASSIFIER_MAPPING__ATTRIBUTE_MAPPINGS) {
                switch (notification.getEventType()) {
                    case Notification.ADD:
                        
                        AttributeMapping newAttributeMapping = (AttributeMapping) notification.getNewValue();
                        addAttributeMappingView(newAttributeMapping);
                        break;
                        
                    case Notification.REMOVE:
                        AttributeMapping oldAttributeMapping = (AttributeMapping) notification.getOldValue();
                        deleteAttributeMappingView(oldAttributeMapping);
                        break;
                }
            } else if (notification.getFeature() == RamPackage.Literals.CLASSIFIER_MAPPING__OPERATION_MAPPINGS) {
                switch (notification.getEventType()) {
                    case Notification.ADD:
                        
                        OperationMapping newOperationMapping = (OperationMapping) notification.getNewValue();
                        addOperationMappingView(newOperationMapping);
                        break;
                        
                    case Notification.REMOVE:
                        OperationMapping oldOperationMapping = (OperationMapping) notification.getOldValue();
                        deleteOperationMappingView(oldOperationMapping);
                        break;
                }
            }
        }
        
    }
}
