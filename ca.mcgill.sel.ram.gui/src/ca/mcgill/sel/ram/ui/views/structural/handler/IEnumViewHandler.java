package ca.mcgill.sel.ram.ui.views.structural.handler;

import ca.mcgill.sel.ram.ui.views.structural.EnumView;

/**
 * The implementation of this interface can handle events for a {@link EnumView}.
 * 
 * @author Franz
 */
public interface IEnumViewHandler extends IBaseViewHandler {

    /**
     * Create a new enum literal (and view).
     * 
     * @param eview The EnumView where this literal should be added.
     */
    void createLiteral(EnumView eview);

}
