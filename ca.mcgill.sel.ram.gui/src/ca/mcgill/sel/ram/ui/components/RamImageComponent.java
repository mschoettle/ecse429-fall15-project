package ca.mcgill.sel.ram.ui.components;

import org.mt4j.util.MTColor;

import processing.core.PImage;
import ca.mcgill.sel.commons.ResourceUtil;
import ca.mcgill.sel.ram.ui.RamApp;

/**
 * Component which contains only an image and a background color.
 * 
 * @author g.NICOLAS
 *
 */
public class RamImageComponent extends RamRectangleComponent {

    /**
     * Constructs a component with an image inside and the given color in background.
     * 
     * @param image - the image in background
     * @param color - the color behind the image
     */
    public RamImageComponent(final PImage image, final MTColor color) {
        setNoFill(false);
        setFillColor(color);

        setTexture(image);
        setSizeLocal(image.width, image.height);

        setAutoMaximizes(false);
        setAutoMinimizes(false);
    }

    /**
     * Constructs a component with the image corresponding to the name.
     * 
     * @param imageName - path of the image in background
     * @param color - the color behind the image
     */
    public RamImageComponent(final String imageName, final MTColor color) {
        this(RamApp.getApplication().loadImage(ResourceUtil.getResourcePath(imageName)), color);
    }

    /**
     * Constructs a component with the given image of the specified size, and background color.
     * 
     * @param image - the image in background
     * @param color - the color behind the image
     * @param width - the image width
     * @param height - the image height
     */
    public RamImageComponent(PImage image, MTColor color, float width, float height) {
        this(image, color);

        setMinimumSize(width, height);
        setMaximumSize(width, height);
    }

}
