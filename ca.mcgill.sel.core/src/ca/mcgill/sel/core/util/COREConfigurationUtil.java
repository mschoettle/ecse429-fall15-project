package ca.mcgill.sel.core.util;

import java.util.HashSet;
import java.util.Set;

import ca.mcgill.sel.core.COREConfiguration;
import ca.mcgill.sel.core.COREFeature;
import ca.mcgill.sel.core.COREReuseConfiguration;

/**
 * This class provides helper methods to be handle {@link ca.mcgill.sel.core.COREConfiguration}s.
 * 
 * @author CCamillieri
 */
public final class COREConfigurationUtil {

    /**
     * Prevent to instantiate.
     */
    private COREConfigurationUtil() {
    }

    /**
     * Get leaves features from a given configuration selection.
     * It looks at used {@link ca.mcgill.sel.core.COREConcernConfiguration} if any.
     * This method DOES not look into extended configurations.
     * 
     * The current implementation considers that the selection has no "hole",
     * ie considering the hierarchy of features A > B > C; if C is selected, A and B must be in the selected list
     * of the configuration as well.
     * 
     * @param configuration - The configuration to check.
     * @return set of features that had no selected children.
     */
    public static Set<COREFeature> getSelectedLeaves(COREConfiguration configuration) {
        // Add selection from reused COREConcernConfiguaration
        Set<COREFeature> baseSelection = new HashSet<COREFeature>();
        baseSelection.addAll(configuration.getSelected());
        if (configuration instanceof COREReuseConfiguration
                && ((COREReuseConfiguration) configuration).getReusedConfiguration() != null) {
            baseSelection.addAll(((COREReuseConfiguration) configuration).getReusedConfiguration().getSelected());
        }
        Set<COREFeature> leaves = new HashSet<COREFeature>();
        // For each feature from the selection, check if one of its child is selected
        loop: for (COREFeature feature : baseSelection) {
            for (COREFeature child : feature.getChildren()) {
                if (baseSelection.contains(child)) {
                    continue loop;
                }
            }
            // If we're here, we didn't find any selected descendant
            leaves.add(feature);
        }
        return leaves;
    }

}
