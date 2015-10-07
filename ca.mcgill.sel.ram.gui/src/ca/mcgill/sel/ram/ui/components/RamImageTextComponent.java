package ca.mcgill.sel.ram.ui.components;

import org.mt4j.util.MTColor;
import org.mt4j.util.font.IFont;

import processing.core.PImage;
import ca.mcgill.sel.ram.ui.layouts.HorizontalLayoutVerticallyCentered;
import ca.mcgill.sel.ram.ui.utils.Fonts;

/**
 * Contains an image and a text in a same component.
 * @author lmartellotto
 */
public class RamImageTextComponent extends RamRectangleComponent {
    
    private RamTextComponent text;
    private RamRectangleComponent image;
    
    /**
     * Basic Constructor.
     */
    public RamImageTextComponent() {
        this(null, Fonts.DEFAULT_FONT);
    }
    
    /**
     * Main Constructor.
     * @param img
     *            The image.
     * @param font
     *            The font of the text.
     */
    public RamImageTextComponent(PImage img, IFont font) {
        
        setAutoMaximizes(false);
        
        image = new RamRectangleComponent();
        image.setNoFill(false);
        if (img != null) {
            image.setTexture(img);
        }
        image.setPickable(false);
        
        text = new RamTextComponent();
        text.setFont(font);
        text.setPickable(false);
        text.setAutoMaximizes(false);
        
        addChild(image);
        addChild(text);
        
        setLayout(new HorizontalLayoutVerticallyCentered(5));
    }
    
    /**
     * Sets the new text.
     * @param txt
     *            The new text.
     */
    public void setText(String txt) {
        text.setText(txt);
    }
    
    /**
     * Sets the new image size.
     * @param width
     *            The width.
     * @param height
     *            The height.
     */
    public void setImageSize(int width, int height) {
        image.setMinimumSize(width, height);
        image.setMaximumSize(width, height);
        updateLayout();
    }
    
    /**
     * Sets the new image.
     * @param img
     *            The image.
     */
    public void setImage(PImage img) {
        image.setTexture(img);
    }
    
    /**
     * Sets the new font.
     * @param font
     *            The font.
     */
    public void setFont(IFont font) {
        text.setFont(font);
    }
    
    @Override
    public void setFillColor(MTColor color) {
        image.setFillColor(color);
    }
}
