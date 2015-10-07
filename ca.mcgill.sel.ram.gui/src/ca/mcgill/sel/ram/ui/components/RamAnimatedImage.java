package ca.mcgill.sel.ram.ui.components;

import java.util.List;

import processing.core.PImage;
import ca.mcgill.sel.ram.ui.utils.Colors;

/**
 * Creates an animation of images from a list of images and a delay.
 * @author lmartellotto
 */
public class RamAnimatedImage extends RamImageComponent {
    
    private List<PImage> sprites;
    private float delay;
    private int currentImage;
    
    private float timer;
    
    /**
     * Constructor.
     * @param s
     *            The sprites.
     * @param d
     *            The delay between each sprite.
     */
    public RamAnimatedImage(List<PImage> s, float d) {
        super(s.get(0), Colors.IMAGE_DEFAULT_COLOR);
        
        sprites = s;
        delay = d;
        currentImage = 0;
    }
    
    /**
     * Sets the new image size.
     * @param width
     *            The width.
     * @param height
     *            The height.
     */
    public void setImageSize(int width, int height) {
        setMinimumSize(width, height);
        setMaximumSize(width, height);
        updateLayout();
    }
    
    @Override
    public void updateComponent(long timeDelta) {
        super.updateComponent(timeDelta);
        
        timer += timeDelta;
        if (timer > delay) {
            timer = 0;
            setTexture(sprites.get((++currentImage) % sprites.size()));
        }
    }
}
