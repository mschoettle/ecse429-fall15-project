package ca.mcgill.sel.ram.ui.scenes;

import org.eclipse.emf.ecore.EObject;

import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.scenes.handler.ISelectSceneHandler;
import ca.mcgill.sel.ram.ui.utils.Icons;
import ca.mcgill.sel.ram.ui.utils.LoggerUtils;
import ca.mcgill.sel.ram.ui.utils.Strings;

/**
 * This is a scene that simply displays a list of loaded aspects. Clicking on an element in the list loads the scene
 * associated with it. A load button is always displayed allowing you to load up new aspects.
 *
 * @author vbonnet
 * @author mschoettle
 */
public class SelectAspectScene extends RamAbstractScene<ISelectSceneHandler> {

    private static final String ACTION_FM_CREATE = "select.FM.create";
    private static final String ACTION_FM_LOAD_EDIT = "select.FM.load.edit";

    /**
     * Creates a new Select Aspect Scene.
     *
     * @param app - The RamApp which is open.
     */
    public SelectAspectScene(RamApp app) {
        super(app, Strings.SCENE_NAME_SELECT_CONCERN, false);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (handler != null) {
            String actionCommand = event.getActionCommand();
            if (ACTION_FM_LOAD_EDIT.equals(actionCommand)) {
                handler.loadConcernDisplay(this);
            } else if (ACTION_FM_CREATE.equals(actionCommand)) {
                handler.createConcernDisplay(this);
            } else {
                super.actionPerformed(event);
            }
        } else {
            LoggerUtils.warn("No handler set for " + this.getClass().getName());
        }
    }

    @Override
    protected void initMenu() {
        getMenu().addAction(Strings.MENU_CONCERN_LOAD, Icons.ICON_MENU_OPEN, ACTION_FM_LOAD_EDIT, this, true);
        getMenu().addAction(Strings.MENU_CONCERN_CREATE, Icons.ICON_MENU_ADD, ACTION_FM_CREATE, this, true);
    }

    @Override
    protected EObject getElementToSave() {
        return null;
    }

}
