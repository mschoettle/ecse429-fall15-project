package ca.mcgill.sel.ram.ui.examples;

import org.mt4j.sceneManagement.AbstractScene;
import org.mt4j.util.MTColor;

import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamImageComponent;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.utils.ResourceUtils;

public final class ImageExample {
    /**
     * The canvas class that will be loaded when launched.
     * 
     * @author vbonnet
     */
    public class ImageExampleScene extends AbstractScene {
        /**
         * @param app
         */
        public ImageExampleScene(final RamApp app) {
            super(app, "grid");
            
            final RamImageComponent rect = new RamImageComponent("plus32.png", new MTColor(0, 255, 0));
            
            getCanvas().addChild(rect);
            
            final RamRectangleComponent invisibleRectangle = new RamRectangleComponent(50, 50, 40, 40);
            
            invisibleRectangle.setFillColor(new MTColor(90, 90, 90));
            invisibleRectangle.setStrokeColor(new MTColor(190, 20, 30));
            invisibleRectangle.isEnabled();
            invisibleRectangle.setNoFill(false);
            invisibleRectangle.setNoStroke(false);
            getCanvas().addChild(invisibleRectangle);
            
            rect.translate(100, 100);
        }
    }
    
    private ImageExample() {
        ResourceUtils.loadLibraries();
        RamApp.initialize(new Runnable() {
            
            @Override
            public void run() {
                RamApp app = RamApp.getApplication();
                app.changeScene(new ImageExampleScene(app));
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
        new ImageExample();
    }
}
