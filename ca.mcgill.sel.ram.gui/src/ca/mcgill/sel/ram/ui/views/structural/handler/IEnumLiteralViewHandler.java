package ca.mcgill.sel.ram.ui.views.structural.handler;

import org.mt4j.input.inputProcessors.IGestureEventListener;

import ca.mcgill.sel.ram.ui.views.structural.EnumLiteralView;

/**
 * The implementation of this interface can handle events for a {@link EnumLiteralView}.
 * 
 * @author Franz
 */
public interface IEnumLiteralViewHandler extends IGestureEventListener {
    
    /**
     * Removes EnumLiteralView and its corresponding enum literal.
     * 
     * @param eLiteralView An EnumLiteralView to be removed.
     */
    void removeLiteral(EnumLiteralView eLiteralView);
    
}
