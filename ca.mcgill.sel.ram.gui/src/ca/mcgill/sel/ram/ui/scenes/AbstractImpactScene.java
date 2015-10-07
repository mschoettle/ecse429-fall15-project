package ca.mcgill.sel.ram.ui.scenes;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicEMap.Entry;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.mt4j.sceneManagement.transition.SlideTransition;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.commons.emf.util.EMFModelUtil;
import ca.mcgill.sel.core.COREImpactModel;
import ca.mcgill.sel.core.COREImpactNode;
import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.core.impl.LayoutContainerMapImpl;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.scenes.handler.IRamAbstractSceneHandler;
import ca.mcgill.sel.ram.ui.utils.Fonts;
import ca.mcgill.sel.ram.ui.utils.Icons;
import ca.mcgill.sel.ram.ui.utils.Strings;
import ca.mcgill.sel.ram.ui.views.TextView;

/**
 * Class used to represent the Impact Model Scene.
 *
 * @author Romain
 */
public abstract class AbstractImpactScene extends RamAbstractScene<IRamAbstractSceneHandler>
        implements INotifyChangedListener {

    private static final String ACTION_BACK = "display.back";

    /**
     * The {@link COREImpactModel} of this concern.
     */
    protected COREImpactModel impactModel;

    /**
     * The root element of this scene.
     */
    protected COREImpactNode rootElement;

    private LayoutContainerMapImpl mapLayoutEntry;
    private TextView impactName;

    /**
     * Constructor called from the RamApp to display the Impact Model.
     *
     * @param application - The RamApp application.
     * @param name - The name of the scene.
     * @param impactModel - The {@link COREImpactModel} of this scene
     * @param rootNode the root node of this scene
     * @param defaultActions - Whether we want the default actions or not
     */
    public AbstractImpactScene(RamApp application, String name, COREImpactModel impactModel,
            COREImpactNode rootNode, boolean defaultActions) {
        super(application, name, defaultActions);

        this.impactModel = impactModel;
        this.rootElement = rootNode;

        buildName();

        mapLayoutEntry =
                EMFModelUtil.getEntryFromMap(this.impactModel.getLayouts(), rootElement);

        // register to the ContainerMap to receive adds/removes of ElementMaps
        EMFEditUtil.addListenerFor(mapLayoutEntry, this);
    }

    /**
     * Builds the name for the scene.
     */
    private void buildName() {
        impactName = new TextView(impactModel.getCoreConcern(), CorePackage.Literals.CORE_NAMED_ELEMENT__NAME);
        impactName.setFont(Fonts.SCENE_FONT);
        impactName.setPlaceholderFont(Fonts.SCENE_PLACEHOLDER_FONT);
        impactName.setPlaceholderText(Strings.PH_ENTER_IMPACTMODEL_NAME);
        containerLayer.addChild(impactName);
        impactName.translate(10, 8);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String actionCommand = event.getActionCommand();
        if (ACTION_BACK.equals(actionCommand)) {
            switchToPreviousScene();
        } else {
            super.actionPerformed(event);
        }
    }

    @Override
    public void notifyChanged(Notification notification) {
        if (notification.getFeature() == CorePackage.Literals.LAYOUT_CONTAINER_MAP__VALUE
                && notification.getNotifier() == this.mapLayoutEntry) {
            Entry<?, ?> entry;
            switch (notification.getEventType()) {
                case Notification.REMOVE:
                    entry = (Entry<?, ?>) notification.getOldValue();

                    COREImpactNode impactModelElement = (COREImpactNode) entry.getKey();

                    if (impactModelElement == rootElement) {
                        switchToPreviousScene();
                    }

                    break;
            }
        }
    }

    /**
     * Switch to the previous scene.
     */
    private void switchToPreviousScene() {
        this.setTransition(new SlideTransition(RamApp.getApplication(), 700, false));

        this.getApplication().changeScene(this.getPreviousScene());
        this.getApplication().destroySceneAfterTransition(this);
    }

    @Override
    public boolean destroy() {
        EMFEditUtil.removeListenerFor(mapLayoutEntry, this);
        return super.destroy();
    }

    @Override
    protected void initMenu() {
        menu.addSubMenu(1, ACTION_BACK);
        menu.addAction(Strings.MENU_BACK, Icons.ICON_MENU_CLOSE, ACTION_BACK, this, ACTION_BACK, true);
    }
}
