package ca.mcgill.sel.ram.ui.examples;

import java.util.Arrays;

import org.eclipse.emf.ecore.EObject;

import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamListComponent.Namer;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.components.RamSelectorComponent;
import ca.mcgill.sel.ram.ui.components.RamTextComponent;
import ca.mcgill.sel.ram.ui.scenes.RamAbstractScene;
import ca.mcgill.sel.ram.ui.scenes.handler.impl.DefaultRamSceneHandler;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.utils.ResourceUtils;

/**
 * The SelectorExample showcases a {@link RamSelectorComponent} with a number of elements inside.
 *
 * @author vbonnet
 */
public final class SelectorExample {

    /**
     * The canvas class that will be loaded when launched.
     *
     * @author vbonnet
     */
    public class SelectorExampleScene extends RamAbstractScene<DefaultRamSceneHandler> {
        private final RamSelectorComponent<String> selector;

        private Namer<String> namer = new Namer<String>() {

            @Override
            public RamRectangleComponent getDisplayComponent(String element) {
                RamTextComponent view = new RamTextComponent(element.toString());
                view.setNoFill(false);
                view.setFillColor(Colors.DEFAULT_ELEMENT_FILL_COLOR);
                view.setNoStroke(false);
                return view;
            }

            @Override
            public String getSortingName(String element) {
                return element;
            }

            @Override
            public String getSearchingName(String element) {
                return element;
            }
        };

        /**
         * @param app
         */
        public SelectorExampleScene(final RamApp app) {
            super(app, "list");

            final String[] temp = { "Element1", "Thing2", "Pick Me!", "Elephant?", "Foo", "Bar", "Baz", "Element12",
                "Thing22", "Pick Me!2", "Elephant?2", "Foo2", "Bar2", "Baz2" };

            selector = new RamSelectorComponent<String>(Arrays.asList(temp), namer);
            getCanvas().addChild(selector);
            selector.translate(100, 100);
        }

        @Override
        protected void initMenu() {
            // TODO Auto-generated method stub

        }

        @Override
        protected EObject getElementToSave() {
            // TODO Auto-generated method stub
            return null;
        }
    }

    private SelectorExample() {
        ResourceUtils.loadLibraries();
        RamApp.initialize(new Runnable() {

            @Override
            public void run() {
                RamApp app = RamApp.getApplication();
                app.changeScene(new SelectorExampleScene(app));
            }
        });
    }

    /**
     * Starts the example.
     *
     * @param args unused.
     */
    public static void main(final String[] args) {
        new SelectorExample();
    }
}
