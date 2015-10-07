package ca.mcgill.sel.ram.ui.examples;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.mt4j.util.MTColor;

import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamExpendableComponent;
import ca.mcgill.sel.ram.ui.components.RamListComponent;
import ca.mcgill.sel.ram.ui.components.RamListComponent.Namer;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.components.listeners.RamListListener;
import ca.mcgill.sel.ram.ui.scenes.RamAbstractScene;
import ca.mcgill.sel.ram.ui.scenes.handler.impl.DefaultRamSceneHandler;
import ca.mcgill.sel.ram.ui.utils.ResourceUtils;

/**
 * The SelectorExample showcases a {@link RamListComponent} with a number of elements inside.
 *
 * @author vbonnet
 */
public final class ExpendableExample {

    /**
     * The canvas class that will be loaded when launched.
     *
     * @author vbonnet
     */
    public class ExpendableExampleScene extends RamAbstractScene<DefaultRamSceneHandler> {
        private final RamListComponent<String> expendableSelectorComponent;

        private Namer<String> namer = new Namer<String>() {

            @Override
            public RamRectangleComponent getDisplayComponent(String element) {
                RamListComponent<String> list = getListE();
                return new RamExpendableComponent(element, list);
            }

            @Override
            public String getSortingName(String element) {
                return element;
            }

            @Override
            public String getSearchingName(String element) {
                return getSortingName(element);
            }
        };

        private RamListListener<String> listenerList = new RamListListener<String>() {

            @Override
            public void elementSelected(RamListComponent<String> list, String element) {
                System.out.println("Big List one click" + element);
                list.selectElement(element, new MTColor(247, 24, 218, 20));
            }

            @Override
            public void elementDoubleClicked(RamListComponent<String> list, String element) {
                System.out.println("Big List double click" + element);
            }

            @Override
            public void elementHeld(RamListComponent<String> list, String element) {
                System.out.println("Big List held" + element);
            }
        };

        private RamListListener<Integer> listenerList2 = new RamListListener<Integer>() {

            @Override
            public void elementSelected(RamListComponent<Integer> list, Integer element) {
                System.out.println("Little List one click" + element);
                list.selectElement(element, new MTColor(247, 24, 218, 20));
            }

            @Override
            public void elementDoubleClicked(RamListComponent<Integer> list, Integer element) {
                System.out.println("Little List double click" + element);
            }

            @Override
            public void elementHeld(RamListComponent<Integer> list, Integer element) {
                System.out.println("Little List held" + element);
            }
        };

        /**
         * Scene.
         *
         * @param app the RamApp
         */
        public ExpendableExampleScene(final RamApp app) {
            super(app, "list");

            // RamRectangleComponent rect = new RamRectangleComponent(new VerticalLayout());
            // rect.addChild(new RamTextComponent("sub sub element 1"));
            //
            // RamExpendableComponent r = new RamExpendableComponent("element 1", new
            // RamExpendableComponent("sub element 1", rect));
            //
            // getCanvas().addChild(r);
            // r.translate(100, 100);

            List<String> elements = new ArrayList<String>();
            for (int i = 0; i < 15; i++) {
                elements.add("element " + i);
            }

            expendableSelectorComponent = new RamListComponent<String>(elements, namer);
            expendableSelectorComponent.setMaxNumberOfElements(5);
            expendableSelectorComponent.registerListener(listenerList);
            getCanvas().addChild(expendableSelectorComponent);
            expendableSelectorComponent.translate(100, 100);
        }

        private RamListComponent<String> getListE() {
            List<String> subElement = new ArrayList<String>();
            for (int j = 0; j < 4; j++) {

                subElement.add("sub-element " + j);
            }

            RamListComponent<String> component = new RamListComponent<String>(subElement, new Namer<String>() {

                @Override
                public RamRectangleComponent getDisplayComponent(String element) {
                    RamExpendableComponent expendableComponent = new RamExpendableComponent(element, getList());
                    return expendableComponent;
                }

                @Override
                public String getSortingName(String element) {
                    return element;
                }

                @Override
                public String getSearchingName(String element) {
                    return element;
                }
            });

            return component;
        }

        private RamListComponent<Integer> getList() {
            List<Integer> subElements = new ArrayList<Integer>();
            for (int j = 0; j < 3; j++) {
                subElements.add(j);
            }

            RamListComponent<Integer> res = new RamListComponent<Integer>(subElements);
            res.registerListener(listenerList2);

            return res;
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

    private ExpendableExample() {
        ResourceUtils.loadLibraries();
        RamApp.initialize(new Runnable() {

            @Override
            public void run() {
                RamApp app = RamApp.getApplication();
                app.changeScene(new ExpendableExampleScene(app));
            }
        });
    }

    /**
     * Starts the example.
     *
     * @param args unused.
     */
    public static void main(final String[] args) {
        new ExpendableExample();
    }
}
