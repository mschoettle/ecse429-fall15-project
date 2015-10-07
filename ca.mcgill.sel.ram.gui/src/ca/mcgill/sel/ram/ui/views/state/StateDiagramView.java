package ca.mcgill.sel.ram.ui.views.state;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.mt4j.input.inputProcessors.IGestureEventListener;
import org.mt4j.input.inputProcessors.componentProcessors.panProcessor.PanProcessorTwoFingers;
import org.mt4j.input.inputProcessors.componentProcessors.tapAndHoldProcessor.TapAndHoldProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.zoomProcessor.ZoomProcessor;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.commons.emf.util.EMFModelUtil;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.Layout;
import ca.mcgill.sel.ram.LayoutElement;
import ca.mcgill.sel.ram.RamFactory;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.StateView;
import ca.mcgill.sel.ram.impl.ContainerMapImpl;
import ca.mcgill.sel.ram.impl.RamFactoryImpl;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.events.MouseWheelProcessor;
import ca.mcgill.sel.ram.ui.events.RightClickDragProcessor;
import ca.mcgill.sel.ram.ui.utils.Constants;
import ca.mcgill.sel.ram.ui.views.AbstractView;
import ca.mcgill.sel.ram.ui.views.handler.HandlerFactory;
import ca.mcgill.sel.ram.ui.views.state.handler.IStateViewHandler;

/**
 * The visual representation for a state diagram.
 * 
 * @author abirayed
 */
public class StateDiagramView extends AbstractView<IStateViewHandler> implements INotifyChangedListener {
    
    private Map<StateView, StateViewView> ramStateViewToViewMap;
    // used to keep the containerMap when this class is notified before the creation of the StateViewView
    // see notifyChanged method
    // private Map<StateView, ContainerMapImpl> ramStateViewToLayoutContainer;
    
    private Layout layout;
    
    private StateViewView selectedStateView;
    private Aspect aspect;
    
    /**
     * Creates a new StateDiagramView for the given state diagram. IMPORTANT: All the view elements related to the
     * StateDiagramView are added to the containerLayer.
     * 
     * @param aspect
     * @param layout
     * @param width
     *            The width over which the elements can be displayed
     * @param height
     *            The height over which the elements can be displayed.
     */
    public StateDiagramView(Aspect aspect, Layout layout, float width, float height) {
        super(width, height);
        
        this.aspect = aspect;
        this.layout = layout;
        
        // Add Existing state views
        ramStateViewToViewMap = new HashMap<StateView, StateViewView>();
        for (StateView ramStateView : aspect.getStateViews()) {
            createLayoutContainer(ramStateView);
            StateViewView view = addStateView(ramStateView, width);
        }
        
        EMFEditUtil.addListenerFor(aspect, this);
        // register to the ContainerMap to receive adds/removes of ElementMaps
        EMFEditUtil.addListenerFor(layout, this);
        
        // ramStateViewToLayoutContainer = new HashMap<StateView, ContainerMapImpl>();
    }
    
    @Override
    protected void destroyComponent() {
        EMFEditUtil.removeListenerFor(aspect, this);
        EMFEditUtil.removeListenerFor(layout, this);
    }
    
    private ContainerMapImpl createLayoutContainer(StateView stateView) {
        ContainerMapImpl layoutContainer = EMFModelUtil.getEntryFromMap(layout.getContainers(), stateView);
        if (layoutContainer == null) {
            layoutContainer = (ContainerMapImpl) ((RamFactoryImpl) RamFactory.eINSTANCE).createContainerMap();
            layoutContainer.setKey(stateView);
            layoutContainer.setValue(new BasicEMap<EObject, LayoutElement>());
            
            // layout.getContainers().add(layoutContainer);
            layout.getContainers().put(stateView, new BasicEMap<EObject, LayoutElement>());
        }
        return layoutContainer;
    }
    
    private StateViewView addStateView(StateView stateView, float width) {
        
        StateViewView view = new StateViewView(aspect, stateView, layout, this);
        
        view.setWidthLocal(width);
        
        view.build();
        
        ramStateViewToViewMap.put(stateView, view);
        // Fix for when this class is notified of a layout change before the state view
        // is added to ramStateViewToViewMap
        // ContainerMapImpl containerMap = ramStateViewToLayoutContainer.get(stateView);
        // if (containerMap != null) {
        // view.setLayoutElement(containerMap);
        // }
        
        addChild(view);
        
        view.setHandler(HandlerFactory.INSTANCE.getStateViewViewHandler());
        return view;
    }
    
    /**
     * Gets the {@link StateViewView} of the specified StateView.
     * 
     * @param specifiedStateView
     *            the StateView element for which we want to get the StateViewView
     * @return {@link ca.mcgill.sel.ram.ui.views.structural.ClassView}
     */
    public StateViewView getStateMachineViewOf(StateView specifiedStateView) {
        return ramStateViewToViewMap.get(specifiedStateView);
        
    }
    
    /**
     * Returns all {@link StateViewView}s contained in this state view.
     * 
     * @return the {@link StateViewView}s contained in this view
     */
    public Collection<StateViewView> getStateViews() {
        return ramStateViewToViewMap.values();
    }
    
    @Override
    protected void registerGestureListeners(IGestureEventListener listener) {
        super.registerGestureListeners(listener);
        
        addGestureListener(TapProcessor.class, listener);
        addGestureListener(TapAndHoldProcessor.class, listener);
    }
    
    @Override
    protected void registerInputProcessors() {
        registerInputProcessor(new TapProcessor(RamApp.getApplication(), Constants.TAP_MAX_FINGER_UP, false,
                Constants.TAP_DOUBLE_TAP_TIME));
        registerInputProcessor(new TapAndHoldProcessor(RamApp.getApplication(), Constants.TAP_AND_HOLD_DURATION));
        registerInputProcessor(new PanProcessorTwoFingers(RamApp.getApplication()));
        registerInputProcessor(new RightClickDragProcessor(RamApp.getApplication()));
        registerInputProcessor(new ZoomProcessor(RamApp.getApplication()));
        registerInputProcessor(new MouseWheelProcessor(RamApp.getApplication()));
    }
    
    public Aspect getAspect() {
        return aspect;
    }
    
    @Override
    public void notifyChanged(Notification notification) {
        if (notification.getFeature() == RamPackage.Literals.ASPECT__STATE_VIEWS) {
            StateView stateView = null;
            
            switch (notification.getEventType()) {
                case Notification.ADD:
                    stateView = (StateView) notification.getNewValue();
                    addStateView(stateView, 0);
                    
                    break;
                case Notification.REMOVE:
                    stateView = (StateView) notification.getOldValue();
                    deleteStateView(stateView);
                    break;
            }
        } else if (notification.getFeature() == RamPackage.Literals.LAYOUT__CONTAINERS) {
            if (notification.getEventType() == Notification.ADD) {
                ContainerMapImpl containerMap = (ContainerMapImpl) notification.getNewValue();
                if (containerMap.getKey() instanceof StateView) {
                    StateViewView view = ramStateViewToViewMap.get(containerMap.getKey());
                    if (view != null) {
                        view.setLayoutElement(containerMap);
                    }
                    // } else {
                    // ramStateViewToLayoutContainer.put((StateView) containerMap.getKey(), containerMap);
                    // }
                    
                }
            }
        }
        
    }
    
    private void deleteStateView(final StateView stateView) {
        RamApp.getApplication().invokeLater(new Runnable() {
            
            @Override
            public void run() {
                selectedStateView = null;
                StateViewView stateViewView = ramStateViewToViewMap.remove(stateView);
                
                removeChild(stateViewView);
                stateViewView.destroy();
            }
        });
        
    }
    
    /**
     * Gets the {@link StateViewView} of the specified stateView.
     * 
     * @param specifiedStateView
     *            the StateView element for which we want to get the StateView view
     * @return {@link StateViewView}
     */
    public StateViewView getStateViewViewOf(StateView specifiedStateView) {
        return ramStateViewToViewMap.get(specifiedStateView);
    }
    
    /**
     * @return the current selected state view
     */
    public StateViewView getSelectedStateViewView() {
        return selectedStateView;
    }
    
    /**
     * Selects the given view and enables edit mode for it.
     * 
     * @param view
     *            The view to enable.
     */
    public void select(StateViewView view) {
        selectedStateView = view;
        selectedStateView.enableEditMode();
    }
    
    /**
     * Deselects the currently selected state view and disabled its edit mode.
     */
    public void deselect() {
        if (selectedStateView != null) {
            selectedStateView.disableEditMode();
            selectedStateView = null;
        }
    }
}
