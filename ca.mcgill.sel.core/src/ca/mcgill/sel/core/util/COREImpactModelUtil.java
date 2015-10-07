package ca.mcgill.sel.core.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;

import ca.mcgill.sel.commons.emf.util.EMFModelUtil;
import ca.mcgill.sel.core.COREContribution;
import ca.mcgill.sel.core.COREFeatureImpactNode;
import ca.mcgill.sel.core.COREImpactModel;
import ca.mcgill.sel.core.COREImpactModelBinding;
import ca.mcgill.sel.core.COREImpactNode;
import ca.mcgill.sel.core.COREModelCompositionSpecification;
import ca.mcgill.sel.core.COREModelReuse;
import ca.mcgill.sel.core.COREReuse;
import ca.mcgill.sel.core.COREWeightedMapping;
import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.core.LayoutElement;
import ca.mcgill.sel.core.impl.LayoutContainerMapImpl;

/**
 * Helper class with convenient static methods for working with CORE EMF model objects. Used to manage the
 * {@link ca.mcgill.sel.core.COREImpactModel}
 *
 * @author Romain
 */
public final class COREImpactModelUtil {

    /**
     * Creates a new instance of {@link COREImpactModelUtil}.
     */
    private COREImpactModelUtil() {
        // suppress default constructor
    }

    /**
     * Retrieve the descendants of this {@link COREImpactNode}.
     *
     * @param impactModelElement the {@link COREImpactNode}
     * @return the descendants.
     */
    public static Set<COREImpactNode> getDescendants(COREImpactNode impactModelElement) {
        Set<COREImpactNode> res = new HashSet<COREImpactNode>();

        for (COREContribution incoming : impactModelElement.getIncoming()) {
            COREImpactNode child = incoming.getSource();
            res.add(child);
            res.addAll(getDescendants(child));
        }

        return res;
    }

    /**
     * Retrieve all the root element for this {@link COREImpactNode}. It will check for each element if a
     * {@link LayoutContainerMapImpl} exist and if yes, it will check if the element is contains in this
     * {@link LayoutContainerMapImpl}.
     *
     * @param impactModel the {@link COREImpactModel} that contains all the {@link COREImpactNode}
     * @param impactModelElement the {@link COREImpactNode} for which we seek the roots.
     * @return A {@link Collection} of roots
     */
    public static Collection<COREImpactNode> getRootByElement(COREImpactModel impactModel,
            COREImpactNode impactModelElement) {
        Collection<COREImpactNode> res = new HashSet<COREImpactNode>();

        EMap<EObject, EMap<EObject, LayoutElement>> layouts = impactModel.getLayouts();

        for (COREImpactNode element : impactModel.getImpactModelElements()) {
            LayoutContainerMapImpl containerMap = EMFModelUtil.getEntryFromMap(layouts, element);
            if (containerMap != null) {
                if (containerMap.getValue().containsKey(impactModelElement)) {
                    res.add(element);
                }
            }
        }

        return res;
    }

    /**
     * Check if there is no {@link COREWeightedMapping} between these two {@link COREImpactNode}. The source can
     * from the same concern but different Reuse.
     *
     * e.g. You reuse (R1 and R2) the same concern (C1) twice and you can map a goal G1 from R1 and G1 from R2 to the
     * same FeatureImpactNode.
     *
     *
     * @param reuse the reuse that contains the source {@link COREImpactNode}
     * @param source the first {@link COREImpactNode}
     * @param target the second {@link COREImpactNode}
     * @return true if there is already a {@link COREContribution} between these two elements, false otherwise.
     */
    public static boolean areAlreadyMappedTogether(COREReuse reuse, COREImpactNode source,
            COREFeatureImpactNode target) {

        COREImpactModel impactModel =
                EMFModelUtil.getRootContainerOfType(target, CorePackage.Literals.CORE_IMPACT_MODEL);

        for (COREModelReuse modelReuse : impactModel.getModelReuses()) {
            if (modelReuse.getReuse() == reuse) {
                for (COREModelCompositionSpecification<?> modelCompositionSpecification : modelReuse
                        .getCompositions()) {
                    if (modelCompositionSpecification instanceof COREImpactModelBinding) {
                        COREImpactModelBinding impactModelBinding =
                                (COREImpactModelBinding) modelCompositionSpecification;
                        for (COREWeightedMapping weightedMapping : impactModelBinding.getMappings()) {
                            if (weightedMapping.getTo() == target && weightedMapping.getFrom() == source) {
                                return true;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    /**
     * Check if a {@link COREContribution} can be created between these two {@link COREImpactNode}s. It will
     * check if there is already a link or if the two elements are indirectly linked.
     *
     * @param source the first {@link COREImpactNode}
     * @param target the second {@link COREImpactNode}
     * @return true if a {@link COREContribution} can be created between these two {@link COREImpactNode}s,
     *         false otherwise.
     */
    public static boolean canCreateContribution(COREImpactNode source, COREImpactNode target) {
        Set<COREImpactNode> children = getDescendants(source);

        return !areAlreadyLinked(source, target) && !children.contains(target);
    }

    /**
     * Check if there is no {@link COREContribution} between these two {@link COREImpactNode}.
     *
     * @param source the first {@link COREImpactNode}
     * @param target the second {@link COREImpactNode}
     * @return true if there is already a {@link COREContribution} between these two elements, false otherwise.
     */
    public static boolean areAlreadyLinked(COREImpactNode source, COREImpactNode target) {
        for (COREContribution contribution : source.getIncoming()) {
            if (contribution.getSource() == target || contribution.getImpacts() == target) {
                return true;
            }
        }

        for (COREContribution contribution : source.getOutgoing()) {
            if (contribution.getSource() == target || contribution.getImpacts() == target) {
                return true;
            }
        }

        return false;
    }

    /**
     * Retrieve all the top root Goals. A root goal is a goal without outgoing contribution.
     *
     * @param impactModel the {@link COREImpactModel} that contains all the {@link COREImpactNode}
     * @return a list of root goals that doesn't have outgoing contribution.
     */
    public static Set<COREImpactNode> getRootGoals(COREImpactModel impactModel) {
        Set<COREImpactNode> res = new HashSet<COREImpactNode>();

        if (impactModel != null) {
            for (COREImpactNode impactModelElement : impactModel.getImpactModelElements()) {
                if (isRoot(impactModel, impactModelElement)) {
                    res.add(impactModelElement);
                }
            }
        }

        return res;
    }

    /**
     * Check if the {@link COREImpactNode} is a root goal.
     *
     * @param impactModel the {@link COREImpactModel} that contains all the {@link COREImpactNode}
     * @param impactModelElement the {@link COREImpactNode} for which we check if it is a root or not.
     * @return true if the impactModelElement is a root goal, false otherwise.
     */
    public static boolean isRoot(COREImpactModel impactModel, COREImpactNode impactModelElement) {
        return !(impactModelElement instanceof COREFeatureImpactNode)
                && impactModel.getLayouts().containsKey(impactModelElement)
                && (impactModelElement.getOutgoing() == null || impactModelElement.getOutgoing().isEmpty());
    }

    /**
     * Check if the {@link COREImpactNode} impactModelElement should be deleted or not.
     *
     * @param root the root key of the {@link LayoutContainerMapImpl}
     * @param impactModelElement the {@link COREImpactNode} for which we check if it has to be deleted or not.
     * @return true if the {@link COREImpactNode} has to be deleted, false otherwise.
     */
    public static boolean shouldDeleteNodeFromModel(COREImpactNode root,
            COREImpactNode impactModelElement) {
        COREImpactModel impactModel =
                EMFModelUtil.getRootContainerOfType(impactModelElement, CorePackage.Literals.CORE_IMPACT_MODEL);
        List<COREContribution> outgoings = new ArrayList<COREContribution>();

        if (root != null) {
            LayoutContainerMapImpl containerMap = EMFModelUtil.getEntryFromMap(impactModel.getLayouts(), root);

            for (COREContribution outgoing : impactModelElement.getOutgoing()) {
                if (containerMap.getValue().containsKey(outgoing.getImpacts())) {
                    outgoings.add(outgoing);
                }
            }
        } else {
            outgoings.addAll(impactModelElement.getOutgoing());
        }

        return !COREImpactModelUtil.isRoot(impactModel, impactModelElement)
                && outgoings.containsAll(impactModelElement.getOutgoing());
    }
}
