package ca.mcgill.sel.ram.ui.examples;

import org.mt4j.sceneManagement.AbstractScene;
import org.mt4j.util.MTColor;

import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamRoundedRectangleComponent;
import ca.mcgill.sel.ram.ui.components.RamTextComponent;
import ca.mcgill.sel.ram.ui.layouts.GridLayout;
import ca.mcgill.sel.ram.ui.utils.ResourceUtils;

/**
 * A simple example to display the functionality of {@link GridLayout} in all it's glory.
 * 
 * @author vbonnet
 */
public final class GridExample {
    /**
     * The canvas class that will be loaded when launched.
     * 
     * @author vbonnet
     */
    public class GridExampleScene extends AbstractScene {
        /**
         * @param app
         */
        public GridExampleScene(final RamApp app) {
            super(app, "grid");
            
            final RamRoundedRectangleComponent g = new RamRoundedRectangleComponent(20, 20, 10);
            g.setFillColor(MTColor.WHITE);
            g.setNoFill(false);
            g.setLayout(new GridLayout(3, 2));
            final String[] temp = { "ohai", "there", "how", "areyou", "doing", "?" };
            for (final String s : temp) {
                final RamTextComponent tf = new RamTextComponent(s);
                tf.setNoStroke(false);
                g.addChild(tf);
            }
            getCanvas().addChild(g);
            g.translate(100, 100);
        }
    }
    
    private GridExample() {
        ResourceUtils.loadLibraries();
        RamApp.initialize(new Runnable() {
            
            @Override
            public void run() {
                RamApp app = RamApp.getApplication();
                app.changeScene(new GridExampleScene(app));
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
        new GridExample();
    }
}
