package ca.mcgill.sel.ram.ui.examples;

import org.eclipse.emf.ecore.EObject;
import org.mt4j.util.MTColor;

import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamPopup;
import ca.mcgill.sel.ram.ui.components.RamRoundedRectangleComponent;
import ca.mcgill.sel.ram.ui.components.RamTextComponent;
import ca.mcgill.sel.ram.ui.layouts.VerticalLayout;
import ca.mcgill.sel.ram.ui.scenes.RamAbstractScene;
import ca.mcgill.sel.ram.ui.scenes.handler.impl.DefaultRamSceneHandler;
import ca.mcgill.sel.ram.ui.utils.ResourceUtils;

/**
 * A simple example to showcase the list layouts ({@link VerticalLayout} and 
 * {@link ca.mcgill.sel.ram.ui.layouts.HorizontalLayout}).
 * 
 * @author vbonnet
 */
public final class PopupExample {
    /**
     * The canvas class that will be loaded when launched.
     * 
     * @author vbonnet
     */
    public class PopupExampleScene extends RamAbstractScene<DefaultRamSceneHandler> {
        /**
         * @param app
         */
        public PopupExampleScene(final RamApp app) {
            super(app, "popup");
            
            final RamRoundedRectangleComponent vert = new RamRoundedRectangleComponent(20, 20, 10);
            vert.setFillColor(MTColor.WHITE);
            vert.setNoFill(false);
            vert.setLayout(new VerticalLayout());
            final String[] temp = { "something", "to", "hover", "over", "and", "test", "opacity" };
            for (final String s : temp) {
                final RamTextComponent tf = new RamTextComponent(s);
                tf.setNoStroke(false);
                vert.addChild(tf);
            }
            vert.translate(200, 200);
            getCanvas().addChild(vert);
            
            final RamPopup popup = new RamPopup("Popup with lots of text to test buttons!", true);
            getCanvas().addChild(popup);
            popup.translate(200, 100);
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
    
    private PopupExample() {
        ResourceUtils.loadLibraries();
        RamApp.initialize(new Runnable() {
            
            @Override
            public void run() {
                RamApp app = RamApp.getApplication();
                app.changeScene(new PopupExampleScene(app));
            }
        });
    }
    
    /**
     * Starts the example.
     * 
     * @param args
     *            unused.
     */
    public static void main(final String[] args) {
        new PopupExample();
    }
}
