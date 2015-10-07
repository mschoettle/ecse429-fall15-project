package ca.mcgill.sel.ram.validator.quickfixes;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.ram.NamedElement;

/**
 * Renames the EObject to give it a unique name depends on the contents of its container.
 * 
 * Retrieves all the contents of its container 
 *  and searches for the contents of the same target type to give it a unique name.
 * @author lmartellotto
 */
public class UniqueNameQuickfix implements Quickfix {

    private EObject target;

    /**
     * Builds the {@link UniqueNameQuickfix} for the target EObject.
     * @param t the target EObject
     */
    public UniqueNameQuickfix(EObject t) {
        target = t;
    }

    /** 
     * Find a unique name and sets it to the target.
     */
    @Override
    public void quickfix() {

        String newName = getUniqueName();
        if (newName != null) {
            EMFEditUtil.getPropertyDescriptor(target, CorePackage.Literals.CORE_NAMED_ELEMENT__NAME)
                .setPropertyValue(target, newName);
        }
        
    }

    @Override
    public String getLabel() {
        return "Rename in '" + getUniqueName() + "'";
    }

    /**
     * Retrieves all the contents of its container 
     * and searches for the contents of the same target type to give it a unique name.
     * @return a unique name
     */
    private String getUniqueName() {

        // If the target has not name
        if (!(target instanceof NamedElement)) {
            return null;
        }
        NamedElement namedTarget = (NamedElement) target;
        EObject container = target.eContainer();
        
        if (container == null) {
            return null;
        }
        
        List<EObject> contents = container.eContents();
        int index = 0;

        for (EObject obj : contents) {
            if (obj instanceof NamedElement) {
                NamedElement namedObj = (NamedElement) obj;
                if (namedObj.getName().matches(namedTarget.getName() + "\\d+")) {
                    index = Math.max(index, Integer.parseInt(namedObj.getName().replaceAll(namedTarget.getName(), "")));
                }
            }
        }

        return namedTarget.getName() + (index + 1);
    }

}
