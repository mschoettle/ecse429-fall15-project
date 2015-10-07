package ca.mcgill.sel.ram.ui.examples;

import org.eclipse.emf.ecore.EObject;
import org.mt4j.components.visibleComponents.shapes.MTRectangle.PositionAnchor;
import org.mt4j.input.inputProcessors.IGestureEventListener;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapProcessor;
import org.mt4j.util.animation.AnimationEvent;
import org.mt4j.util.animation.IAnimation;
import org.mt4j.util.animation.IAnimationListener;
import org.mt4j.util.animation.ani.AniAnimation;
import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamButton;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.components.RamRoundedRectangleComponent;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent.Cardinal;
import ca.mcgill.sel.ram.ui.layouts.VerticalLayout;
import ca.mcgill.sel.ram.ui.scenes.RamAbstractScene;
import ca.mcgill.sel.ram.ui.scenes.handler.impl.DefaultRamSceneHandler;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.utils.ResourceUtils;

public final class AnimationExample {
    
    public class AnimationExampleScene extends RamAbstractScene<DefaultRamSceneHandler> {
        
        private boolean doSlideIn;
        
        private boolean animationRunning;
        
        public AnimationExampleScene(final RamApp app) {
            
            super(app, "Animation");
            
            final RamRoundedRectangleComponent menu = new RamRoundedRectangleComponent(15);
            menu.setBufferSize(Cardinal.WEST, 60);
            menu.setBufferSize(Cardinal.SOUTH, 300);
            menu.setLayout(new VerticalLayout(1));
            menu.setAnchor(PositionAnchor.UPPER_LEFT);
            // we don't want the children of the menu to respond the tap action for this example.
            menu.setComposite(true);
            
            menu.setFillColor(Colors.TRANSPARENT_DARK_GREY);
            menu.setNoFill(false);
            menu.setNoStroke(false);
            
            final RamButton button1 = new RamButton("button1");
            final RamButton button2 = new RamButton("button2");
            
            menu.addChild(button1);
            menu.addChild(button2);
            // menu.addChild(new RamRectangleComponent(0, 0, 950, 200));
            
            getCanvas().addChild(menu);
            
            // we move the menu to the bottom of the screen
            menu.setPositionGlobal(new Vector3D(-30, RamApp.getApplication().height - menu.getHeight() + 25, 0));
            
            menu.registerInputProcessor(new TapProcessor(RamApp.getApplication()));
            menu.addGestureListener(TapProcessor.class, new IGestureEventListener() {
                
                @Override
                public boolean processGestureEvent(MTGestureEvent ge) {
                    if (((TapEvent) ge).getTapID() == TapEvent.TAPPED) {
                        if (!animationRunning) {
                            animationRunning = true;
                            if (doSlideIn) {
                                IAnimation slideInY = new AniAnimation(menu.getY(),
                                        menu.getY() - menu.getHeight() + 85, 700, AniAnimation.BACK_OUT, menu);
                                slideInY.addAnimationListener(new IAnimationListener() {
                                    @Override
                                    public void processAnimationEvent(AnimationEvent ae) {
                                        float delta = ae.getDelta();
                                        // System.out.println("delta:" + delta);
                                        RamRectangleComponent r = (RamRectangleComponent) ae.getTarget();
                                        
                                        r.translate(new Vector3D(0, delta, 0));
                                        
                                    }
                                });
                                
                                IAnimation slideInX = new AniAnimation(menu.getX(), menu.getX() + menu.getWidth() - 85,
                                        700, AniAnimation.BACK_OUT, menu);
                                slideInX.addAnimationListener(new IAnimationListener() {
                                    @Override
                                    public void processAnimationEvent(AnimationEvent ae) {
                                        float delta = ae.getDelta();
                                        // System.out.println("delta:" + delta);
                                        RamRectangleComponent r = (RamRectangleComponent) ae.getTarget();
                                        
                                        r.translate(new Vector3D(delta, 0, 0));
                                        switch (ae.getId()) {
                                            case AnimationEvent.ANIMATION_ENDED:
                                                doSlideIn = false;
                                                animationRunning = false;
                                                break;
                                        }
                                    }
                                });
                                
                                slideInY.start();
                                slideInX.start();
                            } else {
                                final IAnimation slideOutY = new AniAnimation(menu.getY(), menu.getY()
                                        + menu.getHeight() - 85, 700, AniAnimation.BACK_OUT, menu);
                                slideOutY.addAnimationListener(new IAnimationListener() {
                                    @Override
                                    public void processAnimationEvent(AnimationEvent ae) {
                                        float delta = ae.getDelta();
                                        // System.out.println("deltaSlideOut:" + delta);
                                        RamRectangleComponent r = (RamRectangleComponent) ae.getTarget();
                                        
                                        r.translate(new Vector3D(0, delta, 0));
                                    }
                                });
                                
                                final IAnimation slideOutX = new AniAnimation(menu.getX(), menu.getX()
                                        - menu.getWidth() + 85, 700, AniAnimation.BACK_OUT, menu);
                                slideOutX.addAnimationListener(new IAnimationListener() {
                                    @Override
                                    public void processAnimationEvent(AnimationEvent ae) {
                                        float delta = ae.getDelta();
                                        // System.out.println("deltaSlideOut:" + delta);
                                        RamRectangleComponent r = (RamRectangleComponent) ae.getTarget();
                                        
                                        r.translate(new Vector3D(delta, 0, 0));
                                        
                                        switch (ae.getId()) {
                                            case AnimationEvent.ANIMATION_ENDED:
                                                doSlideIn = true;
                                                animationRunning = false;
                                                break;
                                        }
                                    }
                                });
                                
                                slideOutY.start();
                                slideOutX.start();
                            }
                        }
                    }
                    return false;
                }
            });
            
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
    
    private AnimationExample() {
        ResourceUtils.loadLibraries();
        RamApp.initialize(new Runnable() {
            
            @Override
            public void run() {
                RamApp app = RamApp.getApplication();
                app.changeScene(new AnimationExampleScene(app));
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
        new AnimationExample();
    }
    
}
