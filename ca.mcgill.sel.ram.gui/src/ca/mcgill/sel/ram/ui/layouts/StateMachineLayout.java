package ca.mcgill.sel.ram.ui.layouts;

import org.mt4j.components.MTComponent;

import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.views.state.StateComponentView;
import ca.mcgill.sel.ram.ui.views.state.StateMachineView;
import ca.mcgill.sel.ram.ui.views.state.StateViewView;

/**
 * @author abirayed
 */
public class StateMachineLayout extends AbstractBaseLayout {
    
    private float padding;
    private float rightPadding = 200;
    
    /**
     * Constructor.
     * 
     * @param padding
     */
    public StateMachineLayout(float padding) {
        super();
        this.padding = padding;
    }
    
    @Override
    public void layout(RamRectangleComponent component, LayoutUpdatePhase updatePhase) {
        /**
         * TODO: The layout should be corrected since the "new" layout system.
         */
        float maxX = 0;
        float maxY = 0;
        
        for (final MTComponent c : component.getChildren()) {
            
            if (!(c instanceof StateComponentView)) {
                continue;
            }
            RamRectangleComponent child = (RamRectangleComponent) c;
            
            float x = child.getX() + child.getMinimumWidth() + rightPadding;
            if (x > maxX) {
                maxX = x;
            }
            float y = child.getY() + child.getHeight() + padding;
            if (y > maxY) {
                maxY = y;
            }
        }
        if (component.getBounds() == null) {
            return;
        }
        if (component.getChildCount() > 0) {
            maxX -= component.getX();
            maxY -= component.getY();
        }
        
        // Get the size of the StateView
        StateMachineView stateMachine = (StateMachineView) component.getParent();
        StateViewView stateView = (StateViewView) stateMachine.getParent().getParent();
        
        float stateViewWidth = 0;
        // TODO: This is weird and needs to be fixed.
        if (stateView != null && stateView.getParent() != null && stateView.getParent().getParent() != null) {
            stateViewWidth = ((RamRectangleComponent) stateView.getParent().getParent()).getWidth();
        }
        
        // if (stateViewWidth > maxX) {
        // maxX = stateViewWidth;
        // }
        
        // resize ourselves, we need to be big enough to include all elements
        if (updatePhase == LayoutUpdatePhase.FROM_CHILD && !getForceToKeepSize()) {
            setMinimumSize(component, maxX, maxY);
        }
    }
}
