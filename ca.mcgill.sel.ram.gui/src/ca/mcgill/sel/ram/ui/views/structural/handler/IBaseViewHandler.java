package ca.mcgill.sel.ram.ui.views.structural.handler;

import ca.mcgill.sel.ram.ui.events.listeners.IDragListener;
import ca.mcgill.sel.ram.ui.events.listeners.ITapAndHoldListener;
import ca.mcgill.sel.ram.ui.events.listeners.ITapListener;
import ca.mcgill.sel.ram.ui.views.structural.BaseView;

/**
 * The handler of the {@link BaseView}.
 * 
 * @author Franz
 * 
 */
public interface IBaseViewHandler extends IDragListener, ITapAndHoldListener, ITapListener {

    /**
     * Handles the removal of a class.
     * 
     * @param baseView the BaseView to be removed
     */
    void removeClass(BaseView<?> baseView);

}
