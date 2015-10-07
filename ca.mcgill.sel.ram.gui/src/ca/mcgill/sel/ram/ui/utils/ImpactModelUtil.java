package ca.mcgill.sel.ram.ui.utils;

import ca.mcgill.sel.core.COREImpactModel;
import ca.mcgill.sel.core.util.COREModelUtil;

/**
 * Utility class for managing the impact model. It should contains the business rules for the impact model.
 *
 * @author Romain
 *
 */
public final class ImpactModelUtil {
    /**
     * Prevent someone to create an object from this utility class.
     */
    private ImpactModelUtil() {
    }

    /**
     * Return a unique name for a new goal. The resulting name should be unique throughout all goals in an impact model.
     *
     * @param impactModel the impact model of this concern
     * @return a unique name for the new goal.
     */
    public static String getUniqueGoalName(COREImpactModel impactModel) {
        if (impactModel == null) {
            return Strings.DEFAULT_GOAL_NAME;
        }

        return COREModelUtil.createUniqueNameFromElements(Strings.DEFAULT_GOAL_NAME,
                impactModel.getImpactModelElements());
    }
}
