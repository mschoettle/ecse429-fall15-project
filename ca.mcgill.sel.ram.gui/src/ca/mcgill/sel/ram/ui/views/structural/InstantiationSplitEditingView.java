package ca.mcgill.sel.ram.ui.views.structural;

import org.mt4j.components.MTComponent;
import org.mt4j.input.inputProcessors.IGestureEventListener;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.scaleProcessor.ScaleProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.tapAndHoldProcessor.TapAndHoldProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapProcessor;
import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.ram.Instantiation;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.events.MouseWheelProcessor;
import ca.mcgill.sel.ram.ui.scenes.DisplayAspectScene;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.utils.Constants;
import ca.mcgill.sel.ram.ui.views.structural.handler.impl.InstantiationSplitViewHandler;

/**
 * This view is used for instantiations' editing by seeing higher level aspect and lower level aspects at the same time.
 * 
 * @author Engin
 */
public class InstantiationSplitEditingView extends MTComponent {
    private Instantiation instantiation;
    
    // screen width and height
    private int width;
    
    private int height;
    private DisplayAspectScene displayAspectSceneHigherLevel;
    
    private DisplayAspectScene displayAspectSceneLowerLevel;
    private StructuralDiagramView structuralDiagramViewOfHigherAspect;
    
    private StructuralDiagramView structuralDiagramViewOfLowerAspect;
    
    // where the lower level aspect is located (split point).
    private Vector3D locationLowerLevelAspect;
    
    // This component will consume all the gestures and will do gesture forwarding depending on the conditions
    private RamRectangleComponent overlay;
    
    private InstantiationSplitViewHandler handler;
    
    /**
     * Creates a new instance of the split editing view.
     * 
     * @param displayAspectSceneHigherLevel
     *            display aspect scene of the higher level aspect
     * @param displayAspectSceneLowerLevel
     *            display aspect scene of the lower level aspect
     * @param instantiation
     *            instantiation for which we are editing mappings.
     */
    public InstantiationSplitEditingView(DisplayAspectScene displayAspectSceneHigherLevel,
            DisplayAspectScene displayAspectSceneLowerLevel, Instantiation instantiation) {
        super(RamApp.getApplication(), "splitview");
        
        width = RamApp.getApplication().getWidth();
        height = RamApp.getApplication().getHeight();
        
        this.displayAspectSceneHigherLevel = displayAspectSceneHigherLevel;
        this.displayAspectSceneLowerLevel = displayAspectSceneLowerLevel;
        
        this.instantiation = instantiation;
        structuralDiagramViewOfHigherAspect = displayAspectSceneHigherLevel.getStructuralDiagramView();
        structuralDiagramViewOfLowerAspect = displayAspectSceneLowerLevel.getStructuralDiagramView();
        
        // In case the lower level aspect didn't show the structural view.
        displayAspectSceneLowerLevel.switchToView(structuralDiagramViewOfLowerAspect);
        
        // adding the structural view as a child will remove it from its previous parent. So we need to put them
        // back when we are done with the instantiation view.
        addChild(structuralDiagramViewOfHigherAspect);
        addChild(structuralDiagramViewOfLowerAspect);
        
        // This is the new position of the lower level aspect.
        locationLowerLevelAspect = new Vector3D(0, height / 2);
        structuralDiagramViewOfLowerAspect.setPositionGlobal(locationLowerLevelAspect);
        
        structuralDiagramViewOfLowerAspect.setNoFill(false);
        structuralDiagramViewOfLowerAspect.setFillColor(Colors.BACKGROUND_COLOR_OF_LOWER_LEVEL_ASPECT_IN_SPLIT_VIEW);
        
        // adding the overlay to capture all the gestures:
        overlay = new RamRectangleComponent(0, 0, width, height);
        addChild(overlay);
        
        // TODO: mschoettle: This should be done by the one creating this view.
        handler = new InstantiationSplitViewHandler(this);
        
        registerInputProcessorsForOverlay();
        registerGestureListeners(handler);
        
    }
    
    @Override
    public void destroy() {
        // remove listeners
        overlay.removeAllGestureEventListeners();
        
        // do rest
        super.destroy();
    }
    
    /**
     * Gets the display aspect scene of the higher level aspect.
     * 
     * @return Display aspect scene.
     */
    public DisplayAspectScene getDisplayAspectSceneHigherLevel() {
        return displayAspectSceneHigherLevel;
    }
    
    /**
     * Gets the display aspect scene of the lower level aspect.
     * 
     * @return Display aspect scene.
     */
    public DisplayAspectScene getDisplayAspectSceneLowerLevel() {
        return displayAspectSceneLowerLevel;
    }
    
    /**
     * Gets the handler.
     * 
     * @return {@link InstantiationSplitViewHandler} default handler.
     */
    public InstantiationSplitViewHandler getHandler() {
        return handler;
    }
    
    /**
     * Returns the instantiation this view represents.
     * 
     * @return the represented {@link Instantiation}
     */
    public Instantiation getInstantiation() {
        return instantiation;
    }
    
    /**
     * Returns the component which consumes all the gestures.
     * @return
     *         The component which consumes all the gestures.
     */
    public RamRectangleComponent getOverlay() {
        return overlay;
    }
    
    /**
     * Gets where the lower level aspect is located (split point).
     * 
     * @return the location of the lower-level aspect
     */
    public Vector3D getLocationLowerLevelAspect() {
        return locationLowerLevelAspect;
    }
    
    /**
     * Returns the StructuralView view of the higher-level aspect.
     * 
     * @return the {@link StructuralDiagramView} of the higher-level aspect
     */
    public StructuralDiagramView getStructuralDiagramViewOfHigherAspect() {
        return structuralDiagramViewOfHigherAspect;
    }
    
    /**
     * Returns the StructuralView view of the lower-level aspect.
     * 
     * @return the {@link StructuralDiagramView} of the lower-level aspect
     */
    public StructuralDiagramView getStructuralDiagramViewOfLowerAspect() {
        return structuralDiagramViewOfLowerAspect;
    }
    
    /**
     * Registers gesture listeners.
     * 
     * @param listener the listener that acts as the gesture listener
     */
    private void registerGestureListeners(IGestureEventListener listener) {
        overlay.addGestureListener(DragProcessor.class, listener);
        overlay.addGestureListener(TapProcessor.class, listener);
        overlay.addGestureListener(TapAndHoldProcessor.class, listener);
        overlay.addGestureListener(MouseWheelProcessor.class, listener);
        overlay.addGestureListener(ScaleProcessor.class, listener);
    }
    
    /**
     * Registers input processors.
     */
    private void registerInputProcessorsForOverlay() {
        RamApp application = RamApp.getApplication();
        
        overlay.registerInputProcessor(new DragProcessor(application));
        overlay.registerInputProcessor(new TapProcessor(application, Constants.TAP_MAX_FINGER_UP,
                true, Constants.TAP_DOUBLE_TAP_TIME));
        overlay.registerInputProcessor(new TapAndHoldProcessor(application, Constants.TAP_AND_HOLD_DURATION));
        overlay.registerInputProcessor(new MouseWheelProcessor(application));
        overlay.registerInputProcessor(new ScaleProcessor(application));
    }
    
    /**
     * Sets the display aspect scene of the higher level aspect.
     * 
     * @param displayAspectSceneHigherLevel display aspect scene of the higher level aspect
     */
    public void setDisplayAspectSceneHigherLevel(DisplayAspectScene displayAspectSceneHigherLevel) {
        this.displayAspectSceneHigherLevel = displayAspectSceneHigherLevel;
    }
    
    /**
     * Sets the display aspect scene of the lower level aspect.
     * 
     * @param displayAspectSceneLowerLevel display aspect scene of the lower level aspect
     */
    public void setDisplayAspectSceneLowerLevel(DisplayAspectScene displayAspectSceneLowerLevel) {
        this.displayAspectSceneLowerLevel = displayAspectSceneLowerLevel;
    }
    
    /**
     * Sets where the lower level aspect is located (split point).
     * 
     * @param locationLowerLevelAspect the location of the lower-level aspect
     */
    public void setLocationLowerLevelAspect(Vector3D locationLowerLevelAspect) {
        this.locationLowerLevelAspect = locationLowerLevelAspect;
    }
    
}
