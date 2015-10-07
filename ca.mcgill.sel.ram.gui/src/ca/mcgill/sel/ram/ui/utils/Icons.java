package ca.mcgill.sel.ram.ui.utils;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.sel.ram.ui.RamApp;
import processing.core.PImage;

/**
 * Utility class that contains all available icons.
 *
 * @author mschoettle
 */
public final class Icons {

    /**
     * The default icon size used throughout the application.
     */
    public static final int ICON_SIZE = Fonts.FONT_SIZE + 2;

    /**
     * The path that icons are stored in.
     */
    private static final String ICON_PATH = "gfx/";

    private static RamApp app = RamApp.getApplication();

    /**
     * Creates a new instance.
     */
    private Icons() {
    }

    // CHECKSTYLE:IGNORE DeclarationOrderCheck FOR 1000 LINES: Doesn't work otherwise.
    /**
     * Resource for icons that add classifier mapping.
     */
    public static final PImage ICON_CLASSIFIER_MAPPING_ADD = app.loadImage(ICON_PATH + "addClass.png");

    /**
     * Resource for icons that add attribute mapping.
     */
    public static final PImage ICON_ATTRIBUTE_MAPPING_ADD = app.loadImage(ICON_PATH + "addAttribute.png");

    /**
     * Resource for icons that add operation mapping.
     */
    public static final PImage ICON_OPERATION_MAPPING_ADD = app.loadImage(ICON_PATH + "addOperation.png");

    /**
     * Resource for icons that add constructor mapping.
     */
    public static final PImage ICON_CONSTRUCTOR_MAPPING_ADD = app.loadImage(ICON_PATH + "addConstructor.png");

    /**
     * Resource for icons that add attribute mapping.
     */
    public static final PImage ICON_STATE_MACHINE_ADD = app.loadImage(ICON_PATH + "addStateMachine.png");

    /**
     * Resource for icons that add something.
     */
    public static final PImage ICON_ADD = app.loadImage(ICON_PATH + "plus32.png");

    /**
     * Resource for icons that delete something.
     */
    public static final PImage ICON_DELETE = app.loadImage(ICON_PATH + "minus32.png");

    /**
     * Underline icon for static fields.
     */
    public static final PImage ICON_UNDERLINE = app.loadImage(ICON_PATH + "underline32.png");

    /**
     * Icon that shows an arrow to the right.
     */
    public static final PImage ICON_ARROW_RIGHT = app.loadImage(ICON_PATH + "appbar.arrow.right.png");

    /**
     * Icon shows aggregation relationship.
     */
    public static final PImage ICON_AGGREGATION = app.loadImage(ICON_PATH + "aggregation32.png");

    /**
     * Icon shows composition relationship.
     */
    public static final PImage ICON_COMPOSITION = app.loadImage(ICON_PATH + "composition32.png");

    /**
     * Icon shows line for regular relationship.
     */
    public static final PImage ICON_LINE = app.loadImage(ICON_PATH + "line32.png");

    /**
     * Icon shows eye to represent showing the aspect.
     */
    public static final PImage ICON_SPLIT_VIEW = app.loadImage(ICON_PATH + "mappingButton.png");

    /**
     * Icon for close button for the loaded aspects in the menu (select aspect scene).
     */
    public static final PImage ICON_CLOSE = app.loadImage(ICON_PATH + "appbar.close.png");

    /**
     * Icon for expanding triangle button.
     * This can be used to indicate that a view is expandable (details can be shown).
     */
    public static final PImage ICON_EXPAND = app.loadImage(ICON_PATH + "arrowRightForExpand32.png");

    /**
     * Icon for collapsing triangle button. This can be used to indicate that a view is collapseable (details can be
     * hidden).
     */
    public static final PImage ICON_COLLAPSE = app.loadImage(ICON_PATH + "arrowDownForCollapse32.png");

    /**
     * Icon for signaling that editing is done.
     */
    public static final PImage ICON_EDIT_DONE = app.loadImage(ICON_PATH + "acceptButton.png");

    /**
     * Resource for icons that add enum literal mapping.
     */
    public static final PImage ICON_ENUM_LITERAL_MAPPING_ADD = app.loadImage(ICON_PATH + "addLiteral.png");

    /**
     * Icon for showing more options separately.
     */
    public static final PImage ICON_MORE_OPTIONS = app.loadImage(ICON_PATH + "moreButton.png");

    /**
     * Icon for a file in the file browser.
     */
    public static final PImage ICON_FILE = app.loadImage(ICON_PATH + "file.png");

    /**
     * Icon for a folder in the file browser.
     */
    public static final PImage ICON_FOLDER = app.loadImage(ICON_PATH + "folder.png");

    /**
     * Icon for the library button in the file browser.
     */
    public static final PImage ICON_LIBRARY = app.loadImage(ICON_PATH + "book.png");

    /**
     * Icon for showing errors in Validator View.
     */
    public static final PImage ICON_CHECK_ERROR = app.loadImage(ICON_PATH + "checkError.png");

    /**
     * Icon for showing info in Validator View.
     */
    public static final PImage ICON_CHECK_INFO = app.loadImage(ICON_PATH + "checkInfo.png");

    /**
     * Icon for showing a valid model in Validator View.
     */
    public static final PImage ICON_CHECK_OK = app.loadImage(ICON_PATH + "checkOk.png");

    /**
     * Icon for showing warnings in Validator View.
     */
    public static final PImage ICON_CHECK_WARNING = app.loadImage(ICON_PATH + "checkWarning.png");

    /**
     * Icon for showing auto-selected features in select mode.
     */
    public static final PImage ICON_AUTO_SELECTED = app.loadImage(ICON_PATH + "autoSelected.png");

    /**
     * Icon for showing selected features in select mode.
     */
    public static final PImage ICON_SELECTED = app.loadImage(ICON_PATH + "selected.png");

    /**
     * Icon for showing problem in selections for features in select mode.
     */
    public static final PImage ICON_SELECTION_CLASH = app.loadImage(ICON_PATH + "selectionClash.png");

    /*
     * RamMenu icons
     */
    /**
     * Icon for the trash in RamMenu.
     */
    public static final PImage ICON_MENU_TRASH = app.loadImage(ICON_PATH + "trash.png");

    /**
     * Icon for undo in RamMenu.
     */
    public static final PImage ICON_MENU_UNDO = app.loadImage(ICON_PATH + "undo.png");

    /**
     * Icon for redo in RamMenu.
     */
    public static final PImage ICON_MENU_REDO = app.loadImage(ICON_PATH + "redo.png");

    /**
     * Icon for show more in RamMenu.
     */
    public static final PImage ICON_MENU_SHOW_FULL_MODE = app.loadImage(ICON_PATH + "full_mode.png");

    /**
     * Icon for show less in RamMenu.
     */
    public static final PImage ICON_MENU_SHOW_NORMAL_MODE = app.loadImage(ICON_PATH + "normal_mode.png");

    /**
     * Icon for add in RamMenu.
     */
    public static final PImage ICON_MENU_ADD = app.loadImage(ICON_PATH + "add.png");

    /**
     * Icon for save in RamMenu.
     */
    public static final PImage ICON_MENU_SAVE = app.loadImage(ICON_PATH + "save.png");

    /**
     * Icon for open in RamMenu.
     */
    public static final PImage ICON_MENU_OPEN = app.loadImage(ICON_PATH + "open.png");

    /**
     * Icon for buld application in RamMenu.
     */
    public static final PImage ICON_MENU_BUILD_APPLICATION = app.loadImage(ICON_PATH + "create_app.png");

   /**
     * Icon for back in RamMenu.
     */
    public static final PImage ICON_MENU_BACK = app.loadImage(ICON_PATH + "back.png");

    /**
     * Icon for collapse in RamMenu.
     */
    public static final PImage ICON_MENU_COLLAPSE = app.loadImage(ICON_PATH + "collapse.png");

    /**
     * Icon for generate in RamMenu.
     */
    public static final PImage ICON_MENU_GENERATE = app.loadImage(ICON_PATH + "generate.png");

    /**
     * Icon for weave in RamMenu.
     */
    public static final PImage ICON_MENU_WEAVE = app.loadImage(ICON_PATH + "weave.png");

    /**
     * Icon for weave with csp in RamMenu.
     */
    public static final PImage ICON_MENU_WEAVE_CSP = app.loadImage(ICON_PATH + "weave_csp.png");

    /**
     * Icon for weave without csp in RamMenu.
     */
    public static final PImage ICON_MENU_WEAVE_NO_CSP = app.loadImage(ICON_PATH + "weave_no_csp.png");

    /**
     * Icon for got to message view in RamMenu.
     */
    public static final PImage ICON_MENU_MESSAGE_VIEW = app.loadImage(ICON_PATH + "messageView.png");

    /**
     * Icon for go the structural view in RamMenu.
     */
    public static final PImage ICON_MENU_STRUCTURAL_VIEW = app.loadImage(ICON_PATH + "structural.png");

    /**
     * Icon for go to state view in RamMenu.
     */
    public static final PImage ICON_MENU_STATE_VIEW = app.loadImage(ICON_PATH + "state.png");

    /**
     * Icon for showing the validator in RamMenu.
     */
    public static final PImage ICON_MENU_VALIDATOR = app.loadImage(ICON_PATH + "validation.png");

    /**
     * Icon for showing the tracing in RamMenu.
     */
    public static final PImage ICON_MENU_TRACING = app.loadImage(ICON_PATH + "tracing.png");

    /**
     * Icon for showing the reuses in RamMenu.
     */
    public static final PImage ICON_MENU_SHOW_REUSE = app.loadImage(ICON_PATH + "show_reuse.png");

    /**
     * Icon for hiding the reuses in RamMenu.
     */
    public static final PImage ICON_MENU_HIDE_REUSE = app.loadImage(ICON_PATH + "hide_reuse.png");

    /**
     * Icon for expand all features in RamMenu.
     */
    public static final PImage ICON_MENU_EXPAND_ALL_FEATURES = app.loadImage(ICON_PATH + "expand_all_features.png");

    /**
     * Icon for add a root goal in RamMenu.
     */
    public static final PImage ICON_MENU_NEW_ROOT_GOAL = app.loadImage(ICON_PATH + "new_root_goal.png");

    /**
     * Icon for add an aspect in RamMenu.
     */
    public static final PImage ICON_MENU_ADD_ASPECT = app.loadImage(ICON_PATH + "add_aspect.png");

    /**
     * Icon for go home in RamMenu.
     */
    public static final PImage ICON_MENU_HOME = app.loadImage(ICON_PATH + "home.png");

    /**
     * Icon for close in RamMenu.
     */
    public static final PImage ICON_MENU_CLOSE = app.loadImage(ICON_PATH + "close.png");

    /**
     * Icon for validate in RamMenu.
     */
    public static final PImage ICON_MENU_VALIDATE = app.loadImage(ICON_PATH + "validate.png");

    /**
     * Icon for add attribute in RamMenu.
     */
    public static final PImage ICON_MENU_ADD_ATTRIBUTE = app.loadImage(ICON_PATH + "add_attribute.png");

    /**
     * Icon for add constructor in RamMenu.
     */
    public static final PImage ICON_MENU_ADD_CONSTRUCTOR = app.loadImage(ICON_PATH + "add_constructor.png");

    /**
     * Icon for add destructor in RamMenu.
     */
    public static final PImage ICON_MENU_ADD_DESTRUCTOR = app.loadImage(ICON_PATH + "add_destructor.png");

    /**
     * Icon for add operation in RamMenu.
     */
    public static final PImage ICON_MENU_ADD_OPERATION = app.loadImage(ICON_PATH + "add_operation.png");

    /**
     * Icon for add parameter in RamMenu.
     */
    public static final PImage ICON_MENU_ADD_PARAMETER = app.loadImage(ICON_PATH + "add_parameter.png");

    /**
     * Icon for add parameter before another one.
     */
    public static final PImage ICON_MENU_ADD_PARAMETER_BEFORE = app.loadImage(ICON_PATH + "add_parameter_before.png");

    /**
     * Icon for add parameter after another one.
     */
    public static final PImage ICON_MENU_ADD_PARAMETER_AFTER = app.loadImage(ICON_PATH + "add_parameter_after.png");

    /**
     * Icon for add literal in RamMenu.
     */
    public static final PImage ICON_MENU_ADD_LITERAL = app.loadImage(ICON_PATH + "add_literal.png");

    /**
     * Icon for clean selection in RamMenu.
     */
    public static final PImage ICON_MENU_CLEAR_SELECTION = app.loadImage(ICON_PATH + "clean.png");

    /**
     * Icon to switch to public partial.
     */
    public static final PImage ICON_MENU_PUBLIC_PARTIAL = app.loadImage(ICON_PATH + "public_partial.png");

    /**
     * Icon to switch to concern partial.
     */
    public static final PImage ICON_MENU_CONCERN_PARTIAL = app.loadImage(ICON_PATH + "concern_partial.png");

    /**
     * Icon to switch to not partial.
     */
    public static final PImage ICON_MENU_NOT_PARTIAL = app.loadImage(ICON_PATH + "not_partial.png");

    /**
     * Icon to switch to abstract.
     */
    public static final PImage ICON_MENU_ABSTRACT = app.loadImage(ICON_PATH + "abstract.png");

    /**
     * Icon to switch to not abstract.
     */
    public static final PImage ICON_MENU_NOT_ABSTRACT = app.loadImage(ICON_PATH + "not_abstract.png");

    /**
     * Icon to switch to concern visibility.
     */
    public static final PImage ICON_MENU_CONCERN = app.loadImage(ICON_PATH + "concern.png");

    /**
     * Icon to switch to public visibility.
     */
    public static final PImage ICON_MENU_PUBLIC = app.loadImage(ICON_PATH + "public.png");

    /**
     * Icon to switch to static.
     */
    public static final PImage ICON_MENU_STATIC = app.loadImage(ICON_PATH + "static.png");

    /**
     * Icon to switch to not static.
     */
    public static final PImage ICON_MENU_NOT_STATIC = app.loadImage(ICON_PATH + "not_static.png");

    /**
     * Icon to generate getter of attribute.
     */
    public static final PImage ICON_MENU_ATTRIBUTE_GETTER = app.loadImage(ICON_PATH + "get.png");

    /**
     * Icon to generate setter of attribute.
     */
    public static final PImage ICON_MENU_ATTRIBUTE_SETTER = app.loadImage(ICON_PATH + "set.png");

    /**
     * Icon to show advice view.
     */
    public static final PImage ICON_MENU_ADVICE = app.loadImage(ICON_PATH + "advice.png");

    /**
     * Icon to collapse operations.
     */
    public static final PImage ICON_COLLAPSE_OPERATIONS = app.loadImage(ICON_PATH + "operations_collapse.png");

    /**
     * Icon to collapse operations.
     */
    public static final PImage ICON_EXPAND_OPERATIONS = app.loadImage(ICON_PATH + "operations_expand.png");

    /**
     * The single icons for the wheel animated icon.
     */
    public static final List<PImage> ICON_WHEEL = new ArrayList<PImage>();

    static {
        ICON_WHEEL.add(app.loadImage(ICON_PATH + "wheel1.png"));
        ICON_WHEEL.add(app.loadImage(ICON_PATH + "wheel2.png"));
        ICON_WHEEL.add(app.loadImage(ICON_PATH + "wheel3.png"));
        ICON_WHEEL.add(app.loadImage(ICON_PATH + "wheel4.png"));
        ICON_WHEEL.add(app.loadImage(ICON_PATH + "wheel5.png"));
        ICON_WHEEL.add(app.loadImage(ICON_PATH + "wheel6.png"));
        ICON_WHEEL.add(app.loadImage(ICON_PATH + "wheel7.png"));
        ICON_WHEEL.add(app.loadImage(ICON_PATH + "wheel8.png"));
    }

}
