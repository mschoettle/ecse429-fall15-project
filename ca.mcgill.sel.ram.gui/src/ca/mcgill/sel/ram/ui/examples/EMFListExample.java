package ca.mcgill.sel.ram.ui.examples;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import ca.mcgill.sel.commons.emf.util.AdapterFactoryRegistry;
import ca.mcgill.sel.commons.emf.util.ResourceManager;
import ca.mcgill.sel.core.COREConcern;
import ca.mcgill.sel.core.COREModel;
import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.core.provider.CoreItemProviderAdapterFactory;
import ca.mcgill.sel.core.util.CoreResourceFactoryImpl;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.loaders.RamClassLoader;
import ca.mcgill.sel.ram.provider.RamItemProviderAdapterFactory;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamEMFListComponent;
import ca.mcgill.sel.ram.ui.components.RamListComponent;
import ca.mcgill.sel.ram.ui.components.listeners.RamListListener;
import ca.mcgill.sel.ram.ui.layouts.VerticalLayout;
import ca.mcgill.sel.ram.ui.scenes.RamAbstractScene;
import ca.mcgill.sel.ram.ui.scenes.handler.impl.DefaultRamSceneHandler;
import ca.mcgill.sel.ram.ui.utils.Constants;
import ca.mcgill.sel.ram.ui.utils.ResourceUtils;
import ca.mcgill.sel.ram.util.RamResourceFactoryImpl;

/**
 * A simple example to showcase the list layouts ({@link ca.mcgill.sel.ram.ui.layouts.VerticalLayout} and
 * {@link ca.mcgill.sel.ram.ui.layouts.HorizontalLayout}).
 *
 * @author vbonnet
 */
public final class EMFListExample {
    /**
     * The canvas class that will be loaded when launched.
     *
     * @author vbonnet
     */
    public class ListExampleScene extends RamAbstractScene<DefaultRamSceneHandler> {
        private final RamEMFListComponent<COREModel> list;

        /**
         * @param app
         */
        public ListExampleScene(final RamApp app) {
            super(app, "list");

            final COREConcern concern =
                    (COREConcern) ResourceManager
                            .loadModel("./models/reusable_concern_library/utility/Association/Association.core");

            RamListListener<COREModel> listener = new RamListListener<COREModel>() {

                @Override
                public void elementSelected(RamListComponent<COREModel> list, COREModel element) {
                    System.out.println("one click" + element.getName());
                }

                @Override
                public void elementDoubleClicked(RamListComponent<COREModel> list, COREModel element) {
                    System.out.println("double click" + element.getName());
                }

                @Override
                public void elementHeld(RamListComponent<COREModel> list, COREModel element) {
                    System.out.println("held" + element.getName());
                }
            };

            list =
                    new RamEMFListComponent<COREModel>(
                            true);
            list.registerListener(listener);
            list.setLayout(new VerticalLayout());
            getCanvas().addChild(list);
            list.translate(100, 100);
            list.setElements(concern,
                    CorePackage.Literals.CORE_CONCERN__MODELS);

            // new Thread(new Runnable() {
            //
            // @Override
            // public void run() {
            // try {
            // Thread.sleep(5000);
            // } catch (InterruptedException e) {
            // // TODO Auto-generated catch block
            // e.printStackTrace();
            // }
            //
            // Aspect aspect = RAMModelUtil.createAspect("AspectTest", concern);
            //
            // ControllerFactory.INSTANCE.getFeatureController().addModelToConcern(concern, aspect);
            // }
            // }).start();
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

    private EMFListExample() {
        // Initialize ResourceManager (will initialize OCL).
        // See issue #84.
        ResourceManager.initialize();

        // Initialize packages.
        RamPackage.eINSTANCE.eClass();
        CorePackage.eINSTANCE.eClass();

        // Register resource factories.
        ResourceManager.registerExtensionFactory(Constants.ASPECT_FILE_EXTENSION, new RamResourceFactoryImpl());
        ResourceManager.registerExtensionFactory(Constants.CORE_FILE_EXTENSION, new CoreResourceFactoryImpl());
        ResourceManager.registerExtensionFactory(Constants.JUCM_FILE_EXTENSION, new XMIResourceFactoryImpl());

        // Initialize adapter factories.
        AdapterFactoryRegistry.INSTANCE.addAdapterFactory(RamItemProviderAdapterFactory.class);
        AdapterFactoryRegistry.INSTANCE.addAdapterFactory(CoreItemProviderAdapterFactory.class);

        ResourceUtils.loadLibraries();
        RamApp.initialize(new Runnable() {

            @Override
            public void run() {
                RamApp app = RamApp.getApplication();
                app.changeScene(new ListExampleScene(app));
            }
        });

        RamClassLoader.INSTANCE.initializeWithJavaClasses();

    }

    /**
     * Starts the example.
     *
     * @param args unused.
     */
    public static void main(final String[] args) {
        new EMFListExample();
    }
}
