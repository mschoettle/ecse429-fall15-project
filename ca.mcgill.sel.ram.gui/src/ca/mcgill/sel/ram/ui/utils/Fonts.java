package ca.mcgill.sel.ram.ui.utils;

import org.mt4j.util.MTColor;
import org.mt4j.util.font.FontManager;
import org.mt4j.util.font.IFont;

import ca.mcgill.sel.ram.ui.RamApp;

/**
 * Utility class that contains all available fonts and settings for them.
 *
 * @author mschoettle
 */
public final class Fonts {

    /**
     * The font to use.
     */
    public static final String FONT_NAME = "arial.ttf";
    /**
     * The italic font to use.
     */
    public static final String FONT_NAME_ITALIC = "arial-italic.ttf";
    /**
     * The font to use in the browser.
     */
    public static final String BROWSER_FONT_NAME = "arial.bold";
    /**
     * The default font size used throughout the application.
     */
    public static final int FONT_SIZE = 18;
    /**
     * The default font used throughout the application.
     */
    public static final IFont DEFAULT_FONT = FontManager.getInstance().createFont(RamApp.getApplication(), FONT_NAME,
            FONT_SIZE, MTColor.BLACK);
    /**
     * The default font in italic.
     */
    public static final IFont DEFAULT_FONT_ITALIC = FontManager.getInstance().createFont(RamApp.getApplication(),
            FONT_NAME_ITALIC, FONT_SIZE, MTColor.BLACK);

    /**
     * The default font size for class names.
     */
    public static final int FONTSIZE_CLASS_NAME = FONT_SIZE + 2;
    /**
     * The default font for class names.
     */
    public static final IFont FONT_CLASS_NAME = FontManager.getInstance().createFont(RamApp.getApplication(),
            FONT_NAME, FONTSIZE_CLASS_NAME, MTColor.BLACK);
    /**
     * The italic font for class names. Used for abstract classes.
     */
    public static final IFont FONT_CLASS_NAME_ITALIC = FontManager.getInstance().createFont(RamApp.getApplication(),
            FONT_NAME_ITALIC, FONTSIZE_CLASS_NAME, MTColor.BLACK);

    /**
     * The default font size for enum tag.
     */
    public static final int FONTSIZE_ENUM_TAG = FONT_SIZE - 2;
    /**
     * The default font for enum tag.
     */
    public static final IFont FONT_ENUM_TAG = FontManager.getInstance().createFont(RamApp.getApplication(), FONT_NAME,
            FONTSIZE_ENUM_TAG, MTColor.BLACK);

    /**
     * The default font size used for displaying the instantiations. Should not be bigger than 32
     */
    public static final int FONTSIZE_INSTANTIATION = 18;

    /**
     * The default font used for displaying the instantiations.
     */
    public static final IFont FONT_INSTANTIATION = FontManager.getInstance().createFont(RamApp.getApplication(),
            FONT_NAME, FONTSIZE_INSTANTIATION, MTColor.BLACK);

    /**
     * The default font for displaying rolenames and multiplicity.
     */
    public static final IFont FONT_ASSOCIATION_TEXT = FontManager.getInstance().createFont(RamApp.getApplication(),
            FONT_NAME, (int) (FONT_SIZE * 0.9), MTColor.BLACK);

    /**
     * The default font in medium size and black.
     */
    public static final IFont DEFAULT_FONT_MEDIUM = FontManager.getInstance().createFont(RamApp.getApplication(),
            FONT_NAME, 20, MTColor.BLACK);

    /**
     * The default font in small size and black.
     */
    public static final IFont DEFAULT_FONT_SMALL_BLACK = FontManager.getInstance().createFont(RamApp.getApplication(),
            FONT_NAME, 10, MTColor.BLACK);

    /**
     * The default font used throughout the application.
     */
    public static final IFont DEFAULT_FONT_SMALL_RED = FontManager.getInstance().createFont(RamApp.getApplication(),
            FONT_NAME, 10, MTColor.RED);

    /**
     * The default font used throughout interaction.
     */
    public static final IFont DEFAULT_FONT_INTERACTION = FontManager.getInstance().createFont(RamApp.getApplication(),
            FONT_NAME, 13, MTColor.BLACK);

    /**
     * The default font used by text fields with placeholder text.
     */
    public static final IFont DEFAULT_PLACEHOLDER_FONT = FontManager.getInstance().createFont(RamApp.getApplication(),
            FONT_NAME, 16, new MTColor(147, 172, 198));

    /**
     * The default font but in white.
     */
    public static final IFont DEFAULT_FONT_WHITE = FontManager.getInstance().createFont(RamApp.getApplication(),
            FONT_NAME, 17, MTColor.WHITE);

    /**
     * The default font for the menus.
     */
    public static final IFont DEFAULT_FONT_MENU = FontManager.getInstance().createFont(RamApp.getApplication(),
            FONT_NAME, 21, MTColor.WHITE);

    /**
     * The default font for the scenes name.
     */
    public static final IFont SCENE_FONT = FontManager.getInstance().createFont(RamApp.getApplication(), "MyriadPro",
            40, MTColor.BLACK);

    /**
     * The default placeholder font for scenes name.
     */
    public static final IFont SCENE_PLACEHOLDER_FONT = FontManager.getInstance().createFont(RamApp.getApplication(),
            "MyriadPro", 40, MTColor.SILVER);

    /**
     * Placeholder font for {@link ca.mcgill.sel.ram.ui.views.structural.AssociationView}.
     */
    public static final IFont ASSOCIATION_PLACEHOLDER_FONT = FontManager.getInstance().createFont(
            RamApp.getApplication(), FONT_NAME, 16, new MTColor(128, 128, 128));

    /**
     * Primary font for the {@link ca.mcgill.sel.ram.ui.components.browser.RamFileBrowser}.
     */
    public static final IFont BROWSER_PRIMARY_FONT = FontManager.getInstance().createFont(RamApp.getApplication(),
            BROWSER_FONT_NAME, 17, MTColor.WHITE);
    /**
     * Secondary font for the {@link ca.mcgill.sel.ram.ui.components.browser.RamFileBrowser}.
     */
    public static final IFont BROWSER_SECONDARY_FONT = FontManager.getInstance().createFont(RamApp.getApplication(),
            BROWSER_FONT_NAME, 17, new MTColor(140, 140, 140));
    /**
     * Small font for the {@link ca.mcgill.sel.ram.ui.components.browser.RamFileBrowser}.
     */
    public static final IFont BROWSER_SMALL_FONT = FontManager.getInstance().createFont(RamApp.getApplication(),
            BROWSER_FONT_NAME, 14, MTColor.WHITE);
    /**
     * Folder message font for the {@link ca.mcgill.sel.ram.ui.components.browser.RamFileBrowser}.
     */
    public static final IFont BROWSER_FOLDER_MESSAGE_FONT = FontManager.getInstance().createFont(
            RamApp.getApplication(), BROWSER_FONT_NAME, 22, new MTColor(140, 140, 140));
    /**
     * Folder error message font for the {@link ca.mcgill.sel.ram.ui.components.browser.RamFileBrowser}.
     */
    public static final IFont BROWSER_FOLDER_ERROR_MESSAGE_FONT = FontManager.getInstance().createFont(
            RamApp.getApplication(), BROWSER_FONT_NAME, 22, MTColor.RED);

    /**
     * Font for the title of {@link ca.mcgill.sel.ram.ui.components.RamPanelComponent}.
     */
    public static final IFont PANEL_COMPONENT_TITLE_FONT = FontManager.getInstance().createFont(RamApp.getApplication(),
            Fonts.FONT_NAME, Fonts.FONT_SIZE, MTColor.WHITE);
    
    /**
     * Font for the title of {@link ca.mcgill.sel.ram.ui.views.structural.InstantiationsContainerView}.
     */
    public static final IFont INST_CONTAINER_TITLE_FONT = FontManager.getInstance().createFont(RamApp.getApplication(),
            Fonts.FONT_NAME, Fonts.FONT_SIZE, new MTColor(240, 240, 240));
    
    /**
     * Font for the  {@link ca.mcgill.sel.ram.ui.views.containers.ValidationView}.
     */
    public static final IFont VALIDATION_VIEW_FONT = FontManager.getInstance().createFont(RamApp.getApplication(),
            FONT_NAME, FONT_SIZE, MTColor.WHITE);
    
    /**
     * Font for the  {@link ca.mcgill.sel.ram.ui.views.containers.ValidationView}.
     */
    public static final IFont SCROLL_ARROW_FONT = FontManager.getInstance().createFont(RamApp.getApplication(), 
            FONT_NAME, 11, MTColor.BLACK);
    
    /**
     * Creates a new instance.
     */
    private Fonts() {
    }

    /**
     * Get the small font associated with the given color.
     * @param color The color required for the font.
     * @return The small font with the given color.
     */
    public static IFont getSmallFontByColor(final MTColor color) {
        if (color.equals(MTColor.BLACK)) {
            return FONT_ASSOCIATION_TEXT;
        } else if (color.equals(MTColor.RED)) {
            return DEFAULT_FONT_SMALL_RED;
        }

        return null;
    }

}
