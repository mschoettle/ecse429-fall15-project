package ca.mcgill.sel.ram.ui.views.message.handler;

import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.ram.FragmentContainer;
import ca.mcgill.sel.ram.ui.events.listeners.IUnistrokeListener;
import ca.mcgill.sel.ram.ui.views.handler.IAbstractViewHandler;
import ca.mcgill.sel.ram.ui.views.message.LifelineView;
import ca.mcgill.sel.ram.ui.views.message.MessageViewView;

/**
 * This interface is implemented by something that can handle events for a
 * {@link ca.mcgill.sel.ram.ui.views.message.MessageViewView}.
 * 
 * @author mschoettle
 */
public interface IMessageViewHandler extends IAbstractViewHandler, IUnistrokeListener {
    
    /**
     * Handles the request to create a new fragment on the given lifeline.
     * 
     * @param messageViewView the {@link MessageViewView} that is affected (and visualized)
     * @param lifelineView the {@link LifelineView} on which a new fragment should be created
     * @param location the location of the event
     * @param container the {@link FragmentContainer} that contains fragments at that location
     */
    void handleCreateFragment(MessageViewView messageViewView, LifelineView lifelineView,
            Vector3D location, FragmentContainer container);
    
}
