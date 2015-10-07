package ca.mcgill.sel.ram.ui.utils;

import org.mt4j.util.MTColor;

import ca.mcgill.sel.ram.ui.utils.GraphicalUpdater.HighlightEvent;

/**
 * Interface for objects which want to listen notifications from the {@link GraphicalUpdater}.
 * @author lmartellotto
 *
 */
public interface GraphicalUpdaterListener {
    
    /**
     * Highlight the object with the desired color.
     * @param color The desired color.
     * @param event The event that occurred.
     */
    void highlight(MTColor color, HighlightEvent event);
    
}
