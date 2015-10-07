package ca.mcgill.sel.commons.emf.util;

import org.eclipse.emf.common.command.BasicCommandStack;

/**
 * Same as EMF {@link BasicCommandStack} except the listeners are notified on save (when saveisDone() is called).
 * 
 * @author CCamillieri
 */
public class CORECommandStack extends BasicCommandStack {

    @Override
    public void saveIsDone() {
        super.saveIsDone();
        notifyListeners();
    }

    /**
     * Undo or redo commandStack to go back to previous save.
     */
    public void goToLastSave() {
        boolean undo = this.top > this.saveIndex;
        while (isSaveNeeded()) {
            if (undo) {
                undo();
            } else {
                redo();
            }
        }
    }

}
