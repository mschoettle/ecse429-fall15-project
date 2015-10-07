package ca.mcgill.sel.ram.ui.examples;

import java.util.Arrays;

import org.eclipse.emf.ecore.EObject;

import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamListComponent;
import ca.mcgill.sel.ram.ui.components.listeners.RamListListener;
import ca.mcgill.sel.ram.ui.layouts.VerticalLayout;
import ca.mcgill.sel.ram.ui.scenes.RamAbstractScene;
import ca.mcgill.sel.ram.ui.scenes.handler.impl.DefaultRamSceneHandler;
import ca.mcgill.sel.ram.ui.utils.ResourceUtils;

/**
 * A simple example to showcase the list layouts ({@link ca.mcgill.sel.ram.ui.layouts.VerticalLayout} and
 * {@link ca.mcgill.sel.ram.ui.layouts.HorizontalLayout}).
 *
 * @author vbonnet
 */
public final class ListExample {
    /**
     * The canvas class that will be loaded when launched.
     *
     * @author vbonnet
     */
    public class ListExampleScene extends RamAbstractScene<DefaultRamSceneHandler> {
        private final RamListComponent<String> list;

        /**
         * @param app
         */
        public ListExampleScene(final RamApp app) {
            super(app, "list");

            final String[] temp =
            { "Element1", "Thing2", "Pick Me!", "Elephant?", "Foo", "Bar", "Baz", "Element12", "Thing22",
                "Pick Me!2", "Elephant?2", "Foo2", "Bar2", "Baz2" };

            RamListListener<String> listener = new RamListListener<String>() {

                @Override
                public void elementSelected(RamListComponent<String> list, String element) {
                    System.out.println("one click" + element);
                }

                @Override
                public void elementDoubleClicked(RamListComponent<String> list, String element) {
                    System.out.println("double click" + element);
                }

                @Override
                public void elementHeld(RamListComponent<String> list, String element) {
                    System.out.println("held" + element);
                }
            };

            list = new RamListComponent<String>(Arrays.asList(temp), true);
            list.registerListener(listener);
            list.setLayout(new VerticalLayout());
            getCanvas().addChild(list);
            list.translate(100, 100);
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

    private ListExample() {
        ResourceUtils.loadLibraries();
        RamApp.initialize(new Runnable() {

            @Override
            public void run() {
                RamApp app = RamApp.getApplication();
                app.changeScene(new ListExampleScene(app));
            }
        });
    }

    /**
     * Starts the example.
     *
     * @param args unused.
     */
    public static void main(final String[] args) {
        new ListExample();
    }
}
