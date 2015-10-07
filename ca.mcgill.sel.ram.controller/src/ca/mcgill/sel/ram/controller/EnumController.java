package ca.mcgill.sel.ram.controller;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.ram.REnum;
import ca.mcgill.sel.ram.REnumLiteral;
import ca.mcgill.sel.ram.RamFactory;
import ca.mcgill.sel.ram.RamPackage;

/**
 * Controller for REnums.
 * @author Franz
 */
public class EnumController extends BaseController {
    
    /**
     * Removes a specified REnumLiteral from the model.
     * @param literal REnumLiteral to be removed.
     */
    public void removeLiteral(REnumLiteral literal) {
        // Get the editing domain
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(literal);
        
        // Execute remove command for this literal
        Command removeCommand = RemoveCommand.create(editingDomain, literal);
        doExecute(editingDomain, removeCommand);
    }
    
    /**
     * Create an REnum literal in the model.
     * @param owner REnum that will own this literal.
     * @param index Index where we should insert it.
     * @param name Name of the literal.
     * @return The created REnumLiteral.
     */
    public REnumLiteral createREnumLiteral(REnum owner, int index, String name) {
        // create the literal
        REnumLiteral literal = RamFactory.eINSTANCE.createREnumLiteral();
        
        // Set owner and name
        literal.setName(name);
        
        // Add literal to model
        doAdd(owner, RamPackage.Literals.RENUM__LITERALS, literal, index);
        return literal;
    }
    
}
