package ca.mcgill.sel.ram.ui.views.structural;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edit.provider.INotifyChangedListener;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.core.COREReuse;
import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.ram.ClassifierMapping;
import ca.mcgill.sel.ram.Instantiation;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.events.listeners.ActionListener;
import ca.mcgill.sel.ram.ui.layouts.VerticalLayout;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.views.handler.HandlerFactory;
import ca.mcgill.sel.ram.ui.views.handler.IHandled;
import ca.mcgill.sel.ram.ui.views.structural.handler.IInstantiationViewHandler;

/**
 * This view contains all the views related to an instantiation
 * and details of the mappings of that particular instantiation. {@link InstantiationsContainerView} can contain any
 * number of this type of views.
 * 
 * @author eyildirim
 */
public class InstantiationView extends RamRectangleComponent implements ActionListener,
        IHandled<IInstantiationViewHandler>, INotifyChangedListener {
    
    /**
     * The action command to add a classifier mapping.
     */
    protected static final String ACTION_CLASSIFIER_MAPPING_ADD = "mapping.classifier.add";
    
    private Instantiation instantiation;
    /**
     * The InstantiationsContainerView which it belongs to.
     */
    private InstantiationsContainerView myInstantiationsContainerView;
    
    /**
     * Every instantiation view has an instantiation title view object.
     */
    private InstantiationTitleView myInstantiationTitleView;
    
    /**
     * We will show/hide this container by adding/removing it as a child.
     * It keeps all the mapping related containers.
     */
    private RamRectangleComponent hideableMappingContainer;
    
    /**
     * Map for easy accessing to views through classifier mapping information.
     */
    private Map<ClassifierMapping, MappingContainerView> classifierMappingToMappingContainerView;
    
    /**
     * Status of the instantiationView.
     * It is either expanded (detailed view, in which all the class mappings)
     * or collapsed (class mappings are hidden by removing the
     * hideableMappingContainer from the children of InstantiationView)
     */
    private boolean detailedViewOn;
    
    private IInstantiationViewHandler handler;
    
    /**
     * Creates a new instantiation view.
     * 
     * @param instantiation the instantiation to create a view for
     * @param reuse the reuse the instantiation is for
     * @param containerView  the instantiation container view that contains this instantiation view. 
     */
    public InstantiationView(Instantiation instantiation, COREReuse reuse, InstantiationsContainerView containerView) {
        setBufferSize(Cardinal.NORTH, 0);
        setBufferSize(Cardinal.SOUTH, 0);
        setBufferSize(Cardinal.WEST, 5);
        setBufferSize(Cardinal.EAST, 5);
        
        detailedViewOn = false;
        
        setHandler(HandlerFactory.INSTANCE.getInstantiationViewHandler());
        myInstantiationsContainerView = containerView;

        this.instantiation = instantiation;
        classifierMappingToMappingContainerView = new HashMap<ClassifierMapping, MappingContainerView>();
        
        setNoFill(false);
        setNoStroke(true);
        // setStrokeWeight(2);
        setFillColor(Colors.INSTANTIATION_VIEW_FILL_COLOR);
        
        // aspect = displayAspectScene.getAspect();
        
        myInstantiationTitleView = new InstantiationTitleView(this, reuse, detailedViewOn);
        this.addChild(myInstantiationTitleView);
        
        hideableMappingContainer = new RamRectangleComponent();
        // hideableMappingContainer.setBufferSize(Cardinal.WEST, 2);
        // hideableMappingContainer.setBufferSize(Cardinal.EAST, 2);
        // hideableMappingContainer.setBufferSize(Cardinal.SOUTH, 2);
        // hideableMappingContainer.setBufferSize(Cardinal.NORTH, 2);
        hideableMappingContainer.setBuffers(0);
        hideableMappingContainer.setNoStroke(true);
        hideableMappingContainer.setLineStipple((short) 0xAAAAA);
        hideableMappingContainer.setStrokeWeight(2 / 10);
        hideableMappingContainer.setStrokeColor(Colors.DEFAULT_ELEMENT_STROKE_COLOR);
        hideableMappingContainer.setLayout(new VerticalLayout(0));
        
        setLayout(new VerticalLayout(1));
        
        addAllClassifierMappings();
        
        // get updates from the instantiation in case of a change in the mappings
        EMFEditUtil.addListenerFor(instantiation, this);
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        String actionCommand = event.getActionCommand();
        
        if (ACTION_CLASSIFIER_MAPPING_ADD.equals(actionCommand)) {
            handler.addClassifierMapping(this);
        }
        
    }
    
    /**
     * Adds all classifier mappings to this view.
     */
    private void addAllClassifierMappings() {
        EList<ClassifierMapping> classifierMappings = instantiation.getMappings();
        
        // iterate through all classifier mappings and create the view according to that
        for (ClassifierMapping classifierMapping : classifierMappings) {
            classifierMapping.toString();
            addMappingContainerView(classifierMapping);
        }
        
    }
    
    /**
     * This method adds a {@link MappingContainerView} for the given {@link ClassifierMapping}.
     * 
     * @param newClassifierMapping
     *            the new {@link ClassifierMapping} to be added
     */
    public void addMappingContainerView(ClassifierMapping newClassifierMapping) {
        MappingContainerView mappingContainerView = new MappingContainerView(newClassifierMapping);
        hideableMappingContainer.addChild(mappingContainerView);
        classifierMappingToMappingContainerView.put(newClassifierMapping, mappingContainerView);
    }
    
    /**
     * Removes the given classifier mapping from the view.
     * 
     * @param deletedClassifierMapping the {@link ClassifierMapping} to delete
     */
    public void deleteMappingContainerView(ClassifierMapping deletedClassifierMapping) {
        // view to be deleted
        MappingContainerView mappingContainerView =
                classifierMappingToMappingContainerView.get(deletedClassifierMapping);
        hideableMappingContainer.removeChild(mappingContainerView);
        classifierMappingToMappingContainerView.remove(deletedClassifierMapping);
        mappingContainerView.destroy();
    }
    
    @Override
    public void destroy() {
        super.destroy();
        
        /**
         * The container needs to be destroyed manually if it is currently not displayed.
         */
        if (!detailedViewOn) {
            hideableMappingContainer.destroy();
        }
        
        if (instantiation != null) {
            EMFEditUtil.removeListenerFor(instantiation, this);
        }
    }
    
    @Override
    public IInstantiationViewHandler getHandler() {
        return handler;
    }
    
    /**
     * Gets the {@link Instantiation} that is associated to this view.
     * 
     * @return this view's {@link Instantiation} object
     */
    public Instantiation getInstantiation() {
        return instantiation;
    }
    
    /**
     * Gets the {@link InstantiationsContainerView} which it belongs to.
     * 
     * @return {@link InstantiationsContainerView} which it belongs to
     */
    public InstantiationsContainerView getInstantiationsContainerView() {
        return myInstantiationsContainerView;
    }
    
    /**
     * Getter for the instantiation title view .
     * 
     * @return {@link InstantiationTitleView}
     */
    public InstantiationTitleView getMyInstantiationTitleView() {
        return myInstantiationTitleView;
    }
    
    /**
     * Hides the instantiation mapping details.
     */
    public void hideMappingDetails() {
        if (isDetailedViewOn()) {
            this.removeChild(hideableMappingContainer);
            myInstantiationTitleView.showExpandButton();
            setDetailedViewOn(false);
        }
    }
    
    /**
     * Is detailed mapping view on for the instantiaton.
     * 
     * @return true if detailed view is on false otherwise
     */
    public boolean isDetailedViewOn() {
        return detailedViewOn;
    }
    
    @Override
    public void notifyChanged(Notification notification) {
        if (notification.getNotifier() == instantiation) {
            if (notification.getFeature() == CorePackage.Literals.CORE_BINDING__MAPPINGS) {
                switch (notification.getEventType()) {
                    case Notification.ADD:
                        ClassifierMapping newClassifierMapping = (ClassifierMapping) notification.getNewValue();
                        addMappingContainerView(newClassifierMapping);
                        break;
                    case Notification.REMOVE:
                        ClassifierMapping deletedClassifierMapping = (ClassifierMapping) notification.getOldValue();
                        deleteMappingContainerView(deletedClassifierMapping);
                        break;
                }
            }
        }
    }
    
    /**
     * Set the detailedViewOn value.
     * 
     * @param detailedViewOn whether the detailed view should be enabled
     */
    public void setDetailedViewOn(boolean detailedViewOn) {
        this.detailedViewOn = detailedViewOn;
    }
    
    @Override
    public void setHandler(IInstantiationViewHandler handler) {
        this.handler = handler;
        
    }
    
    /**
     * Shows instantiation mapping details.
     */
    public void showMappingDetails() {
        if (!containsChild(hideableMappingContainer)) {
            this.addChild(hideableMappingContainer);
            myInstantiationTitleView.showCollapseButton();
            myInstantiationTitleView.showDeleteInstantiationButton();
            myInstantiationTitleView.showMappingButton();
            myInstantiationTitleView.showAddClassifierMappingButton();
            setDetailedViewOn(true);
        }
        
    }
    
}
