package ca.mcgill.sel.ram.validator.quickfixes;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;

/**
 * Deletes the EObject target.
 * 
 * @author lmartellotto
 */
public class DeleteEObjectQuickfix implements Quickfix {

    private EObject target;

    /**
     * Builds the {@link DeleteEObjectQuickfix} for the target EObject.
     * @param t the target EObject
     */
    public DeleteEObjectQuickfix(EObject t) {
        target = t;
    }
    
    
    @Override
    public String getLabel() {
        return "Delete it";
    }

    @Override
    public void quickfix() {
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(target);
        
        // create remove Command
        Command removeCommand = RemoveCommand.create(editingDomain, target);
        editingDomain.getCommandStack().execute(removeCommand);
    }

}
