package ca.mcgill.sel.ram.ui.utils;

import java.io.File;

import org.mt4j.util.MT4jSettings;

import ca.mcgill.sel.commons.ResourceUtil;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.utils.ResourceUtils.OperatingSystem;

/**
 * A utility class that provides easy access to some of the more commonly used items in the application. This provides
 * an easy point to change the default fonts, colors... NOTE fonts must be created from the main thread, otherwise
 * OpenGL will cause an "Invalid memory access" error. The Java observer pattern spawns threads so make sure that this
 * class has been initialized before using fonts from spawned threads.
 *
 * @author vbonnet
 */
public final class Constants {

    /**
     * The default directory for all models.
     */
    public static final String DIRECTORY_MODELS = ResourceUtil.getResourcesDirectory(Constants.class) + "models";

    /**
     * The default directory for all libraries.
     */
    public static final String DIRECTORY_LIBRARIES = DIRECTORY_MODELS + File.separator + "reusable_concern_library";

    /**
     * The location of the association concern file.
     */
    public static final String ASSOCIATION_CONCERN_LOCATION = DIRECTORY_LIBRARIES
            + "/utility/Association/Association.core";

    /**
     * The file extension for aspects.
     */
    public static final String ASPECT_FILE_EXTENSION = "ram";

    /**
     * The file extension for concerns.
     */
    public static final String CORE_FILE_EXTENSION = "core";

    /**
     * The file extension for jucm.
     */
    public static final String JUCM_FILE_EXTENSION = "jucm";

    /**
     * The file extension for jars.
     */
    public static final String JAR_FILE_EXTENSION = "jar";

    /**
     * String appended to woven concern when saved.
     */
    public static final String WOVEN_PREFIX = "Woven_";

    /**
     * String appended to concern when saving the reuse.
     */
    public static final String REUSED_PREFIX = "Reused_";

    /**
     * Y value of the split point where we split the screen in instantiation split view.
     */
    public static final float SPLIT_INSTANTIATION_VIEW_WHERE_TO_SPLIT = RamApp.getApplication().getHeight() * 11 / 19;

    /**
     * The duration (in milliseconds) needed to complete a tap and hold.
     */
    public static final int TAP_AND_HOLD_DURATION = 500;

    /**
     * The maximum finger up distance used for determining a tap.
     */
    public static final float TAP_MAX_FINGER_UP = 25f;

    /**
     * The maximum time (in milliseconds?) for a double tap.
     */
    public static final int TAP_DOUBLE_TAP_TIME = 300;

    /**
     * The maximum time (in milliseconds?) for a long double tap.
     */
    public static final int TAP_DOUBLE_TAP_LONG_TIME = 600;

    /**
     * Margin used to detect if the start of a unistroke event is close to a element.
     */
    public static final int MARGIN_ELEMENT_DETECTION = 20;

    /**
     * The minimum distance an object has to be dragged before it actually is dragged.
     */
    public static final float DELAYED_DRAG_MIN_DRAG_DISTANCE = 2f;

    /**
     * The initial height of a root goal.
     */
    public static final float ROOT_GOAL_INITIAL_HEIGHT = 50f;

    /**
     * String used to give a default name to some subMenus.
     */
    public static final String MENU_EXTRA = "ExtraMenu";

    /**
     * Height in pixels of the menu bar in the OS.
     */
    public static final float MENU_BAR_HEIGHT;
    static {
        if (ResourceUtils.getOperatingSystem() == OperatingSystem.OSX && MT4jSettings.getInstance().isFullscreen()
                && !MT4jSettings.getInstance().isFullscreenExclusive()) {
            MENU_BAR_HEIGHT = 24f;

        } else {
            MENU_BAR_HEIGHT = 0;
        }
    }

    /**
     * Creates a new instance.
     */
    private Constants() {
    }

}
