package ca.mcgill.sel.ram.ui.views.structural;

import java.util.LinkedList;
import java.util.List;

import org.mt4j.components.MTComponent;
import org.mt4j.util.animation.AnimationEvent;
import org.mt4j.util.animation.IAnimation;
import org.mt4j.util.animation.IAnimationListener;
import org.mt4j.util.animation.ani.AniAnimation;
import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.ContainerComponent;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.components.RamRoundedRectangleComponent;
import ca.mcgill.sel.ram.ui.layouts.VerticalLayout;
import ca.mcgill.sel.ram.ui.scenes.DisplayAspectScene;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.utils.Constants;
import ca.mcgill.sel.ram.ui.utils.MathUtils;
import ca.mcgill.sel.ram.ui.views.structural.handler.IInstantiationsPanelHandler;

/**
 * An {@link InstantiationsPanel} is a {@link RamRoundedRectangleComponent} container which shows
 * a list of {@link InstantiationsContainerView}s.
 * 
 * ***** IMPORTANT *****: Instead of adding the items as a child to InstantiationsContainerView
 * we will add them to roundIstantiationsContainer which works as the main container. We didn't overwrite the addChild
 * method because it is already overriden by the ContainterComponent.
 * 
 * @author eyildirim
 * @author oalam
 */
public class InstantiationsPanel extends ContainerComponent<IInstantiationsPanelHandler> {

    // Values for the spaces between container items and the border of the container as well as the width of the hidden
    // part of the container.
    // We have a big space from left because we don't want a space between the left part of the menu and the screen
    // during the animation, this
    // way it looks like it is connected to the view.
    private static final float BUFFER_FROM_RIGHT = 30;
    private static final float BUFFER_FROM_LEFT = 90;

    // this value shows the width of the hidden portion of the container while it is not slided off the screen.
    private static final float CONTAINER_HIDDEN_PART_FROM_LEFT = BUFFER_FROM_LEFT - 10;
    private static final float INSTANTIATION_CONTAINERS_PADDING = 5f;

    private static final int ANIMATION_DURATION = 700;

    // Y location of the container

    // Flags to control container's sliding in/out animation
    private boolean animationRunning;
    private boolean doSlideIn;

    // whether the component should stick at the bottom of the screen when resized.
    // TODO. This is not very good because it mimics a bit the behavior of RamPanelComponents.
    // It would be better to create a parent Class of component that can stick to screen borders. 
    private boolean bottomStick;

    // Normally InstantiationsContainerView is a RamRectangleComponent with sharp corners and we want a rounded
    // rectangle for better visual design . So will put a
    // RamRoundedRectangle Component inside the main container and make the main container invisible (only round
    // rectangle will be visible).
    // Instead of adding the items as a child to InstantiationsContainerView object we will add them to
    // roundIstantiationsContainer.
    private RamRoundedRectangleComponent roundInstantiationsContainer;

    private List<InstantiationsContainerView> instantiationsViews;

    /**
     * Constructor.
     * 
     * @param displayAspectScene is the scene which has this instantiationContainerView.
     */
    public InstantiationsPanel(DisplayAspectScene displayAspectScene) {
        super();
        // First make the main container invisible to hide sharp corners
        setNoFill(true);
        setEnabled(true);
        setNoStroke(true);

        // put the rounded rectangle menu inside the main container to have rounded corners
        // we will add everything related to the menu inside this rounded rectangle menu.
        roundInstantiationsContainer = new RamRoundedRectangleComponent(100, 100, 10);
        roundInstantiationsContainer.setNoFill(false);
        roundInstantiationsContainer.setNoStroke(true);
        // roundInstantiationsContainer.setStrokeWeight(3);
        roundInstantiationsContainer.setLayout(new VerticalLayout(INSTANTIATION_CONTAINERS_PADDING));
        roundInstantiationsContainer.setFillColor(Colors.TRANSPARENT_DARK_GREY);
        roundInstantiationsContainer.setBufferSize(Cardinal.WEST, BUFFER_FROM_LEFT);
        roundInstantiationsContainer.setBufferSize(Cardinal.EAST, BUFFER_FROM_RIGHT);
        this.addChild(roundInstantiationsContainer);

        // keep a map of instantiation views. We used Linked hash map because we want to keep the orders that the
        // elements are added.
        instantiationsViews = new LinkedList<InstantiationsContainerView>();

        setLayout(new VerticalLayout());
    }

    /**
     * Get the list of {@link InstantiationsContainerView}s for this container.
     *
     * @return list of {@link InstantiationsContainerView}s.
     */
    public List<InstantiationsContainerView> getInstantiationsContainers() {
        return instantiationsViews;
    }

    /**
     * Show the given {@link InstantiationsContainerView}.
     * Only works if it was already added once with addInstantiationsContainerView.
     *
     * @param view - The view to display
     */
    public void showInstantiationsContainerView(InstantiationsContainerView view) {
        if (instantiationsViews.contains(view) && !roundInstantiationsContainer.containsChild(view)) {
            roundInstantiationsContainer.addChild(instantiationsViews.indexOf(view), view);
        }
    }

    /**
     * Hide the given {@link InstantiationsContainerView}.
     *
     * @param view - The view to display
     */
    public void hideInstantiationsContainerView(InstantiationsContainerView view) {
        if (roundInstantiationsContainer.containsChild(view)) {
            roundInstantiationsContainer.removeChild(view);
        }
    }

    /**
     * Adds an {@link InstantiationsContainerView} to the display.
     *
     * @param instantiationsContainerView - The {@link InstantiationsContainerView} to add.
     */
    public void addInstantiationsContainerView(InstantiationsContainerView instantiationsContainerView) {
        instantiationsViews.add(instantiationsContainerView);
        roundInstantiationsContainer.addChild(instantiationsContainerView);
    }

    @Override
    protected void handleChildResized(MTComponent component) {
        super.handleChildResized(component);
        if (component.getParent() == null) {
            return;
        }
        // If the container is resized while collapsed, we have to update its X location.
        if (!animationRunning && doSlideIn) {
            float xObjective = getCorrectXPosition(doSlideIn);
            float currentX = getX();
            if (xObjective != currentX) {
                translate(xObjective - currentX, 0);
            }
        }
        // Update Y location to not go out of screen
        float currentY = getY();
        float yObjective = getCorrectYPosition(currentY);
        translate(0, yObjective - currentY);
    }

    /**
     * Animation of showing and hiding the menu.
     */
    public void doSlideInOut() {
        if (!animationRunning) {
            animationRunning = true;
            final float xObjective = getCorrectXPosition(!doSlideIn);
            final float initialX = getX();
            final float initialWidth = getWidth();
            // Move to desired position
            IAnimation slideInX = new AniAnimation(initialX, xObjective,
                    ANIMATION_DURATION, AniAnimation.BACK_OUT, InstantiationsPanel.this);
            slideInX.addAnimationListener(new CustomAnimListener(xObjective, initialX, initialWidth));
            slideInX.start();
        }
    }

    /**
     * Class that handles animation for the reuses container. Take in account the fact that the container's size
     * may be changed during animation (by undoing/redoing removal of an instantiation for example).
     * 
     * @author CCamillieri
     */
    private class CustomAnimListener implements IAnimationListener {

        private float previousObjective;
        private float previousWidth;
        private float previousX;
        // Used to increase speed of the translation in case the container is made bigger.
        private boolean left;
        // Used to keep track of the fact that the animation has finished, and should not continue.
        private boolean done;
        // Used to keep track of the fact that width was modified during the animation.
        private boolean changed;

        /**
         * Constructor for animation listener.
         * 
         * @param xObjective - Initial X position we want to the container to go to.
         * @param initialX - Initial X position of the container.
         * @param initialWidth - Initial Width of the container.
         */
        public CustomAnimListener(float xObjective, float initialX, float initialWidth) {
            previousObjective = xObjective;
            previousWidth = initialWidth;
            previousX = initialX;
            left = previousObjective < previousX;
        }

        @Override
        public void processAnimationEvent(AnimationEvent ae) {
            // System.out.println(ae.getValue());
            RamRectangleComponent r = (RamRectangleComponent) ae.getTarget();
            switch (ae.getId()) {
                case AnimationEvent.ANIMATION_STARTED:
                case AnimationEvent.ANIMATION_UPDATED:
                    float delta = ae.getDelta();
                    float newObjective = getCorrectXPosition(!doSlideIn);
                    float newWidth = getWidth();
                    float newMove = newObjective - getX();
                    if (left && done) {
                        // We've already been placed at the final position: stay there
                        r.translate(new Vector3D(newMove, 0, 0));
                    } else if (left && (newObjective != previousObjective || changed)) {
                        boolean bigger = newWidth > previousWidth;
                        float moved = previousX - getX();
                        if (bigger && newMove + moved < previousObjective - previousX) {
                            // Increased size : move to position
                            r.translate(new Vector3D(newObjective - previousObjective, 0, 0));
                        } else if (!bigger && newMove < 0) {
                            // Reduced and we had not passed the objective yet: continue.
                            r.translate(new Vector3D(delta, 0, 0));
                        } else if (!bigger && newMove >= 0) {
                            // We already went passed the objective: go back to it.
                            r.translate(new Vector3D(newMove, 0, 0));
                            done = true;
                        } else {
                            r.translate(new Vector3D(delta, 0, 0));
                        }
                        changed = true;
                    } else {
                        r.translate(new Vector3D(delta, 0, 0));
                    }
                    break;
                case AnimationEvent.ANIMATION_ENDED:
                    float xObjective = getCorrectXPosition(!doSlideIn);
                    r.translate(new Vector3D(xObjective - getX(), 0, 0));
                    doSlideIn = !doSlideIn;
                    animationRunning = false;
                    break;
            }
        }
    };

    /**
     * Compute X position we want the view to be at.
     *
     * @param slideIn - Whether the element should be hidden or not.
     * @return position the container should be depending of it state (collapsed or not).
     */
    private float getCorrectXPosition(boolean slideIn) {
        return slideIn ? -getWidth() + BUFFER_FROM_RIGHT : -CONTAINER_HIDDEN_PART_FROM_LEFT;
    }

    /**
     * Compute Y position we want the view to be at.
     *
     * @param currentY - The position we want to compare against.
     * @return position the container should be.
     */
    public float getCorrectYPosition(float currentY) {
        float minY;
        float maxY;
        float screenHeight = RamApp.getApplication().getSize().height;
        float height = getHeight();
        if (screenHeight >= height) {
            minY = bottomStick ? screenHeight - height : Constants.MENU_BAR_HEIGHT;
            maxY = screenHeight - height;
        } else {
            minY = -height + screenHeight / 3;
            maxY = Constants.MENU_BAR_HEIGHT + screenHeight * 2 / 3;
        }
        return MathUtils.clampFloat(minY, currentY, maxY);
    }

    /**
     * Setter for the bottomStick attribute.
     *
     * @param bottomStick - Whether the component should stick at the bottom of screen or not.
     */
    public void setBottomStick(boolean bottomStick) {
        this.bottomStick = bottomStick;
    }

}
