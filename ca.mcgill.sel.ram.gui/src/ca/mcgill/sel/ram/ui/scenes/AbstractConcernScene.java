package ca.mcgill.sel.ram.ui.scenes;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mt4j.components.visibleComponents.shapes.MTRectangle;

import ca.mcgill.sel.core.COREConcern;
import ca.mcgill.sel.core.COREFeature;
import ca.mcgill.sel.core.COREModel;
import ca.mcgill.sel.core.util.COREModelUtil;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.scenes.handler.IRamAbstractSceneHandler;

/**
 * ConcernAbstractScene is used as an abstract class for rest of the concern scenes to extend it from.
 *
 * @author Nishanth.
 * @param <T> the class for the feature diagram type
 * @param <E> generic type the handler
 */
public abstract class AbstractConcernScene<T, E extends IRamAbstractSceneHandler>
        extends RamAbstractScene<E> {

    /**
     * Constant number defined to refer to the menu position.
     */
    protected static final int FIVE = 5;

    /**
     * The root Feature of the Feature Model.
     */
    protected COREFeature root;

    /**
     * The concern representing the Feature Model.
     */
    protected COREConcern concern;

    /**
     * The file path of the concern.
     */
    protected File filePath;

    /**
     * View for the feature model.
     */
    protected T featureDiagramView;

    /**
     * View for the feature model.
     */
    protected MTRectangle concernRectangle;

    /**
     * Default Constructor which passes the RAMApplication and name of the scene.
     *
     * @param application - The RAM Application
     * @param name - The scene name passed by the user
     * @param defaultActions - Whether we want the default actions or not.
     */
    public AbstractConcernScene(RamApp application, String name, boolean defaultActions) {
        super(application, name, defaultActions);

    }

    /**
     * Method used to initialize and display the whole scene.
     */
    protected abstract void replot();

    /**
     * Method used to redraw only the feature diagram after model changes for example.
     *
     * @param repopulate - Whether we have to update the tree because a new element has been added or just update
     *            the position of the features and/or their links.
     */
    public abstract void redrawFeatureDiagram(boolean repopulate);

    /**
     * Getter of the concern.
     *
     * @return concern
     */
    public COREConcern getConcern() {
        return concern;
    }

    /**
     * Function called to unload all the resources. Resource Manager takes up resources which have to be unloaded after
     * use.
     */
    public void unLoadAllResources() {
        for (COREModel model : concern.getModels()) {
            if (model.eResource() != null) {
                COREModelUtil.unloadEObject(model);
            }
        }
        COREModelUtil.unloadEObject(concern);

    }

    /**
     * Get children of the given feature that are part of the given list.
     *
     * @param featurePassed - the feature to check
     * @param list - the list to check against
     * @return intersection between given list and children of passed feature
     */
    protected Set<COREFeature> getContainedChildren(COREFeature featurePassed, List<COREFeature> list) {

        Set<COREFeature> collectedChildren = new HashSet<COREFeature>();

        if (list.contains(featurePassed)) {
            collectedChildren.add(featurePassed);
        }

        for (COREFeature child : featurePassed.getChildren()) {
            if (list.contains(child)) {
                collectedChildren.add(child);
            }
            collectedChildren.addAll(getContainedChildren(child, list));
        }

        return collectedChildren;
    }

}
