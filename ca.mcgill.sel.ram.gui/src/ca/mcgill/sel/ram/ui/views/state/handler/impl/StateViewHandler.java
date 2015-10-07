package ca.mcgill.sel.ram.ui.views.state.handler.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.mt4j.input.inputProcessors.componentProcessors.tapAndHoldProcessor.TapAndHoldEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;
import org.mt4j.input.inputProcessors.componentProcessors.unistrokeProcessor.UnistrokeEvent;
import org.mt4j.input.inputProcessors.componentProcessors.unistrokeProcessor.UnistrokeUtils.UnistrokeGesture;
import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.StateView;
import ca.mcgill.sel.ram.controller.ControllerFactory;
import ca.mcgill.sel.ram.ui.views.AbstractView;
import ca.mcgill.sel.ram.ui.views.handler.impl.AbstractViewHandler;
import ca.mcgill.sel.ram.ui.views.state.StateDiagramView;
import ca.mcgill.sel.ram.ui.views.state.StateViewView;
import ca.mcgill.sel.ram.ui.views.state.handler.IStateViewHandler;

/**
 * The default handler for a {@link StateDiagramView}.
 * 
 * @author abirayed
 */
public class StateViewHandler extends AbstractViewHandler implements IStateViewHandler {
    
    @Override
    public boolean processTapAndHoldEvent(TapAndHoldEvent tapAndHoldEvent) {
        if (tapAndHoldEvent.isHoldComplete()) {
            StateDiagramView target = (StateDiagramView) tapAndHoldEvent.getTarget();
            
            createNewStateView(target, tapAndHoldEvent.getLocationOnScreen());
        }
        
        return true;
    }
    
    private static void createNewStateView(final StateDiagramView view, final Vector3D position) {
        final Aspect aspect = view.getAspect();
        
        aspect.eAdapters().add(new EContentAdapter()
        {
            
            private StateView stateView;
            
            @Override
            public void notifyChanged(Notification notification) {
                if (notification.getFeature() == RamPackage.Literals.ASPECT__STATE_VIEWS) {
                    if (notification.getEventType() == Notification.ADD) {
                        stateView = (StateView) notification.getNewValue();
                    }
                } else if (notification.getFeature() == RamPackage.Literals.LAYOUT__CONTAINERS) {
                    if (notification.getEventType() == Notification.ADD) {
                        view.getStateViewViewOf(stateView).showKeyboard();
                        aspect.eAdapters().remove(this);
                    }
                }
            }
        });
        
        ControllerFactory.INSTANCE.getStateViewController().createNewStateView(aspect, 0, 0);
        
    }
    
    @Override
    public boolean processTapEvent(TapEvent tapEvent) {
        if (tapEvent.isTapped()) {
            StateDiagramView target = (StateDiagramView) tapEvent.getTarget();
            
            target.deselect();
        }
        
        return true;
    }
    
    @Override
    public boolean handleTapAndHoldOnStateView(StateDiagramView stateDiagram, StateViewView stateView) {
        StateViewView selectedStateView = stateDiagram.getSelectedStateViewView();
        
        if (selectedStateView == null) {
            stateDiagram.select(stateView);
        }
        
        return true;
    }
    
    @Override
    public void handleUnistrokeGesture(AbstractView<?> target, UnistrokeGesture gesture, Vector3D startPosition,
            UnistrokeEvent event) {
        // Currently not supported.
    }
    
}
