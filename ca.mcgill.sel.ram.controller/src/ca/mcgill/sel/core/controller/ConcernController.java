package ca.mcgill.sel.core.controller;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.core.COREConcern;
import ca.mcgill.sel.core.COREModel;
import ca.mcgill.sel.core.CorePackage;

/**
 * The controller for {link @Feature}.
 *
 * @author Romain
 *
 */
public class ConcernController extends CoreBaseController {

    /**
     * Creates a new instance of {@link ConcernController}.
     */
    ConcernController() {
        // prevent anyone outside this package to instantiate
    }

    /**
     * Add a model to a concern's models.
     *
     * @param concern - The concern
     * @param model - The model to add
     */
    public void addModelToConcern(COREConcern concern, COREModel model) {

        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(model);

        Command setConcernForModelCommand =
                SetCommand.create(editingDomain, model, CorePackage.Literals.CORE_MODEL__CORE_CONCERN, concern);

        doExecute(editingDomain, setConcernForModelCommand);

    }
}
