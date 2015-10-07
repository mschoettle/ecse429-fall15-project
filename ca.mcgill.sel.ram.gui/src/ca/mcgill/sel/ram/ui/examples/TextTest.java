package ca.mcgill.sel.ram.ui.examples;

import org.eclipse.emf.ecore.EObject;

import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamTextComponent;
import ca.mcgill.sel.ram.ui.components.RamTextComponent.Alignment;
import ca.mcgill.sel.ram.ui.scenes.RamAbstractScene;
import ca.mcgill.sel.ram.ui.scenes.handler.impl.DefaultRamSceneHandler;
import ca.mcgill.sel.ram.ui.utils.ResourceUtils;

public final class TextTest {
    
    public class TextScene extends RamAbstractScene<DefaultRamSceneHandler> {
        /**
         * @param app
         */
        public TextScene(final RamApp app) {
            super(app, "grid");
            
            final RamTextComponent left = new RamTextComponent("Left\naligned");
            left.setNoStroke(false);
            left.setAlignment(Alignment.LEFT_ALIGN);
            left.translate(100, 100);
            getCanvas().addChild(left);
            
            final RamTextComponent right = new RamTextComponent("Right\naligned");
            right.setNoStroke(false);
            right.setAlignment(Alignment.RIGHT_ALIGN);
            right.translate(200, 100);
            getCanvas().addChild(right);
            
            final RamTextComponent center = new RamTextComponent("Centered\nText");
            center.setNoStroke(false);
            center.setAlignment(Alignment.CENTER_ALIGN);
            center.translate(300, 100);
            getCanvas().addChild(center);
            
            final RamTextComponent maxed = new RamTextComponent("ThisOneHasA max width");
            maxed.setNoStroke(false);
            maxed.setAlignment(Alignment.LEFT_ALIGN);
            maxed.setMaximumWidth(100);
            maxed.translate(100, 200);
            getCanvas().addChild(maxed);
            
            final RamTextComponent editable = new RamTextComponent("This one is editable");
            editable.setNoStroke(false);
            editable.setAlignment(Alignment.LEFT_ALIGN);
            editable.translate(200, 200);
            editable.enableKeyboard(true);
            getCanvas().addChild(editable);
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
    
    private TextTest() {
        ResourceUtils.loadLibraries();
        RamApp.initialize(new Runnable() {
            
            @Override
            public void run() {
                RamApp app = RamApp.getApplication();
                app.changeScene(new TextScene(app));
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
        new TextTest();
    }
}
