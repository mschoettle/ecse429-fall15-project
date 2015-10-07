package ca.mcgill.sel.ram.ui.views.structural.handler.impl;

import org.mt4j.input.inputProcessors.componentProcessors.tapAndHoldProcessor.TapAndHoldEvent;

import ca.mcgill.sel.ram.Instantiation;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamSelectorComponent;
import ca.mcgill.sel.ram.ui.components.listeners.RamSelectorListener;
import ca.mcgill.sel.ram.ui.scenes.DisplayAspectScene;
import ca.mcgill.sel.ram.ui.utils.Strings;
import ca.mcgill.sel.ram.ui.views.OptionSelectorView;
import ca.mcgill.sel.ram.ui.views.TextView;
import ca.mcgill.sel.ram.ui.views.handler.HandlerFactory;
import ca.mcgill.sel.ram.ui.views.handler.IDisplaySceneHandler;
import ca.mcgill.sel.ram.ui.views.handler.impl.TextViewHandler;
import ca.mcgill.sel.ram.ui.views.structural.InstantiationSplitEditingView;
import ca.mcgill.sel.ram.ui.views.structural.InstantiationTitleView;
import ca.mcgill.sel.ram.ui.views.structural.InstantiationView;
import ca.mcgill.sel.ram.ui.views.structural.handler.IInstantiationNameHandler;

/**
 * The default handler for an instantiation name which is represented by a {@link TextView} in the
 * {@link InstantiationTitleView}.
 * 
 * @author eyildirim
 */
public class InstantiationDefaultNameHandler extends TextViewHandler implements IInstantiationNameHandler {

    /**
     * Enum with possible actions to do on Instantiation.
     */
    private enum Actions {
        OPEN_ASPECT, WEAVE_ASPECT
    }

    @Override
    public boolean processTapAndHoldEvent(final TapAndHoldEvent tapAndHoldEvent) {
        if (tapAndHoldEvent.isHoldComplete()) {
            OptionSelectorView<Actions> selector = new OptionSelectorView<Actions>(Actions.values());
            ((DisplayAspectScene) RamApp.getActiveScene())
                    .addComponent(selector, tapAndHoldEvent.getLocationOnScreen());

            selector.registerListener(new RamSelectorListener<InstantiationDefaultNameHandler.Actions>() {

                @Override
                public void elementSelected(RamSelectorComponent<Actions> selector, Actions element) {
                    final TextView target = (TextView) tapAndHoldEvent.getTarget();
                    if (!(target.getParent() instanceof InstantiationTitleView)) {
                        return;
                    }
                    InstantiationView view = (InstantiationView) target.getParent().getParent();
                    // Get the corresponding instantiation
                    Instantiation instantiation = view.getInstantiation();

                    if (element == Actions.WEAVE_ASPECT) {
                        weaveAspect(instantiation);
                    } else if (element == Actions.OPEN_ASPECT) {
                        openAspect(instantiation);
                    }
                }

                @Override
                public void elementHeld(RamSelectorComponent<Actions> selector, Actions element) {
                }
                @Override
                public void elementDoubleClicked(RamSelectorComponent<Actions> selector, Actions element) {
                }
                @Override
                public void closeSelector(RamSelectorComponent<Actions> selector) {
                }
            });
        }

        return true;
    }

    /**
     * Open the aspect corresponding to the view.
     * 
     * @param instantiation - The instantiation to display
     */
    protected void openAspect(Instantiation instantiation) {
        // Get the handler of the display aspect scene to call its function for switching into instantiation edit mode.
        IDisplaySceneHandler handler = HandlerFactory.INSTANCE.getDisplayAspectSceneHandler();

        // Get the latest used display aspect before switching into instantiation edit mode
        DisplayAspectScene displayAspectScene = RamApp.getActiveAspectScene();

        if (displayAspectScene != null && isSplitViewEnabled(displayAspectScene)) {
            // we are already in split instantiation editing mode, switch back to normal mode and then load aspect.
            handler.closeSplitView(displayAspectScene);
            RamApp.getApplication().loadAspect(instantiation.getSource());
        } else {
            // load external aspect
            RamApp.getApplication().loadAspect(instantiation.getSource());
        }
        return;
    }

    /**
     * Open the current aspect with the selected one.
     * 
     * @param instantiation - The instantiation to display
     */
    private static void weaveAspect(Instantiation instantiation) {
        // check if the split instantiation editing mode is enabled. If not we can do the weaving.
        boolean splitModeEnabled = isSplitViewEnabled(RamApp.getActiveAspectScene());

        if (!splitModeEnabled) {
            // Weave the tapped instantiation
            
        } else {
            RamApp.getActiveAspectScene().displayPopup(Strings.POPUP_CANT_WEAVE_EDITMODE);
        }
    }

    /**
     * Returns whether split view mode is enabled.
     * 
     * @param scene the current scene
     * @return true, if split view mode is enabled, false otherwise
     */
    private static boolean isSplitViewEnabled(DisplayAspectScene scene) {
        return scene.getCurrentView() instanceof InstantiationSplitEditingView;
    }

}
