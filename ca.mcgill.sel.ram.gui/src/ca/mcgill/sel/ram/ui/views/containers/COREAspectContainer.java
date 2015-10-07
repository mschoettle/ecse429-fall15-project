package ca.mcgill.sel.ram.ui.views.containers;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.mt4j.sceneManagement.transition.SlideTransition;

import ca.mcgill.sel.core.COREConcern;
import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.ConfirmPopup;
import ca.mcgill.sel.ram.ui.components.RamButton;
import ca.mcgill.sel.ram.ui.components.RamImageComponent;
import ca.mcgill.sel.ram.ui.components.RamListComponent;
import ca.mcgill.sel.ram.ui.components.RamListComponent.Namer;
import ca.mcgill.sel.ram.ui.components.RamPanelListComponent;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.components.RamTextComponent.Alignment;
import ca.mcgill.sel.ram.ui.components.listeners.RamListListener;
import ca.mcgill.sel.ram.ui.events.listeners.ActionListener;
import ca.mcgill.sel.ram.ui.layouts.HorizontalLayoutVerticallyCentered;
import ca.mcgill.sel.ram.ui.scenes.DisplayConcernEditScene;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.utils.Fonts;
import ca.mcgill.sel.ram.ui.utils.Icons;
import ca.mcgill.sel.ram.ui.utils.Strings;
import ca.mcgill.sel.ram.ui.views.TextView;

/**
 * Container class used to contain all the loaded aspects in the folder. Each entry contains a close button and the
 * aspect name.
 *
 * @author Nishanth
 */
public class COREAspectContainer extends RamPanelListComponent<Aspect> {

    private static final int DEFAULT_MAX_ELEMENT = 8;
    private static final int DEFAULT_ARC_RADIUS = 5;

    private Aspect selected;
    private DisplayConcernEditScene currentScene;

    /**
     * The container takes in the default path of the container. It displays all the aspects in the folder.
     *
     * @param concern - The COREConcern which is used for reading all the aspect associated with it.
     * @param scene - The scene of the current Concern.
     */
    public COREAspectContainer(COREConcern concern, DisplayConcernEditScene scene) {
        super(DEFAULT_ARC_RADIUS, Strings.LABEL_ASPECT_CONTAINER, HorizontalStick.LEFT, VerticalStick.TOP);
        this.setListener(new Listener());
        this.setNamer(new AspectNamer());
        this.setMaxNumberOfElements(DEFAULT_MAX_ELEMENT);
        this.currentScene = scene;
    }

    /**
     * Function which returns the selector.
     *
     * @return The current selected element.
     */
    public RamListComponent<Aspect> getSelector() {
        return list;
    }

    /**
     * Function which returns the selected element in the selector.
     *
     * @return The current selected element.
     */
    public Aspect getSelected() {
        return selected;
    }

    /**
     * Unselects the current selection and update colors in the scene.
     *
     * @param scene - The current scene
     */
    public void unselect(DisplayConcernEditScene scene) {
        if (selected == null) {
            return;
        }
        RamRectangleComponent component = list.getElementMap().get(selected);
        component.setFillColor(Colors.DEFAULT_ELEMENT_FILL_COLOR);
        selected = null;
        scene.switchToEditMode();
    }

    /**
     * Selects a new child.
     *
     * @param element - The newly selected aspect
     * @param scene - The current scene
     */
    private void select(Aspect element, DisplayConcernEditScene scene) {
        boolean firstSelection = selected == null || !list.getElementMap().containsKey(selected);
        if (!firstSelection) {
            RamRectangleComponent selComp = list.getElementMap().get(selected);
            selComp.setFillColor(Colors.DEFAULT_ELEMENT_FILL_COLOR);
        }

        selected = element;
        if (firstSelection) {
            scene.switchToAssociationMode();
        } else {
            
        }
        RamRectangleComponent component = list.getElementMap().get(selected);
        component.setFillColor(Colors.FEATURE_ASSIGNEMENT_FILL_COLOR);
    }

    /**
     * Class describing namer for aspects in the list.
     *
     * @author CCamillieri
     */
    private class AspectNamer implements Namer<Aspect> {
        @Override
        public RamRectangleComponent getDisplayComponent(final Aspect element) {
            RamRectangleComponent container = new RamRectangleComponent();
            container.setBufferSize(Cardinal.WEST, 5);
            container.setNoFill(false);
            container.setFillColor(Colors.DEFAULT_ELEMENT_FILL_COLOR);
            container.setNoStroke(false);
            container.setStrokeWeight(3.0f);
            container.setStrokeColor(Colors.DEFAULT_ELEMENT_STROKE_COLOR);

            // first add the close button

            RamImageComponent closeImage = new RamImageComponent(Icons.ICON_CLOSE, Colors.ICON_CLOSE_COLOR);
            closeImage.setMinimumSize(20, 20);
            closeImage.setMaximumSize(20, 20);
            closeImage.setBufferSize(Cardinal.WEST, 10);
            closeImage.setBufferSize(Cardinal.EAST, 10);

            RamButton closeButton = new RamButton(closeImage);

            closeButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent event) {
                    // TODO: belongs to handler
                    RamApp.getApplication();

                    ConfirmPopup deleteConfirmPopup = new ConfirmPopup(Strings.popupDeleteElement(element.getName()),
                            ConfirmPopup.OptionType.YES_NO_CANCEL);

                    currentScene.displayPopup(deleteConfirmPopup);

                    deleteConfirmPopup.setListener(new ConfirmPopup.SelectionListener() {
                        @Override
                        public void optionSelected(int selectedOption) {
                            if (selectedOption == ConfirmPopup.YES_OPTION) {
                                currentScene.getHandler().deleteAspect(currentScene, element);
                                if (selected == element) {
                                    selected = null;
                                    currentScene.switchToEditMode();
                                } else if (selected != null) {
                                    
                                }
                                return;
                            }
                        }
                    });
                }
            });
            container.addChild(closeButton);

            // now add the file name

            TextView aspectCell = new TextView(element, CorePackage.Literals.CORE_NAMED_ELEMENT__NAME);
            aspectCell.setFont(Fonts.FONT_CLASS_NAME);
            aspectCell.setNoStroke(true);

            aspectCell.setAutoMaximizes(true);
            aspectCell.setAlignment(Alignment.LEFT_ALIGN);
            container.addChild(aspectCell);

            container.setLayout(new HorizontalLayoutVerticallyCentered(5));
            return container;
        }

        @Override
        public String getSortingName(Aspect element) {
            return element.getName();
        }

        @Override
        public String getSearchingName(Aspect element) {
            return getSortingName(element);
        }

    }

    /**
     * Private class which is a listener of the selectors implemented.
     *
     * @author Nishanth
     */
    private class Listener implements RamListListener<Aspect> {

        /**
         * Function called when a selector is clicked / tapped.
         *
         * @param selectorPassed - The RamSelectorComponent of type FileName.
         * @param element - The element of type FileName, which was clicked.
         */
        @Override
        public void elementSelected(RamListComponent<Aspect> selectorPassed, Aspect element) {
            DisplayConcernEditScene scene = RamApp.getActiveConcernEditScene();
            if (scene != null) {
                if (selected == element) {
                    // Remove everything and un-select
                    unselect((DisplayConcernEditScene) scene);
                } else {
                    select(element, (DisplayConcernEditScene) scene);
                }
            }
        }

        @Override
        public void elementDoubleClicked(RamListComponent<Aspect> selectorPassed, Aspect element) {
            currentScene.setTransition(new SlideTransition(RamApp.getApplication(), 700, true));
            RamApp.getApplication().loadAspect(element);
        }

        @Override
        public void elementHeld(RamListComponent<Aspect> list, Aspect element) {
        }
    }

    /**
     * Goes through all the list content and refresh the elements if they are proxies.
     * Reference to the Aspects become proxies when the scene for the aspect is opened, so we need to refresh the
     * list when going back to the scene. 
     */
    public final void refreshContent() {
        Set<Aspect> proxies = new HashSet<Aspect>();
        for (Aspect element : list.getElementMap().keySet()) {
            if (element.eIsProxy()) {
                proxies.add(element);
            }
        }
        // Remove the elements from the list and add them again.s
        for (Aspect element : proxies) {
            Aspect resolvedAspect = (Aspect) EcoreUtil.resolve(element, currentScene.getConcern());
            list.removeElement(element);
            list.addElement(resolvedAspect);
            if (selected != null && selected == element) {
                select(resolvedAspect, currentScene);
            }
        }
    }

}
