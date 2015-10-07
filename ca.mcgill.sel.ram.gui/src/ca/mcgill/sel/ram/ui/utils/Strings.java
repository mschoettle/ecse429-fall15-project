package ca.mcgill.sel.ram.ui.utils;

/**
 * A utility class that provides access to the texts displayed in the app.
 * Might be useful later on for localization purposes.
 *
 * @author CCamillieri
 */
public final class Strings {

    /*
     * -------------------- GENERAL -------------------------
     */
    /**
     * Name of the application.
     */
    public static final String APP_NAME = "TouchCORE";
    /**
     * String used to describe concerns.
     */
    public static final String MODEL_CONCERN = "Concern";
    /**
     * String used to describe aspects.
     */
    public static final String MODEL_ASPECT = "Aspect";

    /**
     * String used to show metaclasses.
     */
    public static final String TAG_META_CLASS = "<<metaClass>>";
    /**
     * String used to show enumerations.
     */
    public static final String TAG_ENUMERATION = "<<enumeration>>";
    /**
     * String used to show implementation.
     */
    public static final String TAG_IMPL = "<<impl>>";
    /**
     * String used to show implementation of interfaces.
     */
    public static final String TAG_IMPL_INTERFACE = "<<impl interface>>";

    /**
     * Text for affected by field.
     */
    public static final String AFFECTED_BY = "affected by";
    /**
     * Text for pointcut.
     */
    public static final String POINTCUT = "Pointcut";
    /**
     * Text for advice.
     */
    public static final String ADVICE = "Advice";

    /*
     * -------------------- DEFAULT NAMES -------------------------
     */
    /**
     * Default property name in assignments.
     */
    public static final String DEFAULT_PROPERTY_NAME = "untitled";
    /**
     * Default property name in assignments.
     */
    public static final String DEFAULT_FEATURE_NAME = "newChild";
    /**
     * Default name of an class.
     */
    public static final String DEFAULT_CLASS_NAME = "UnnamedClass";
    /**
     * Default name of an enum.
     */
    public static final String DEFAULT_ENUM_NAME = "UnnamedEnum";

    /**
     * Default name of an aspect.
     */
    public static final String DEFAULT_ASPECT_NAME = "Untitled";

    /**
     * Default name of a goal.
     */
    public static final String DEFAULT_GOAL_NAME = "UnnamedGoal";

    /*
     * -------------------- SYMBOLS -------------------------
     */
    /**
     * Symbol used to display exclude constraints.
     */
    public static final String SYMBOL_EXCLUDE_CONSTRAINT = "<>";
    /**
     * Symbol used to display requirement constraints.
     */
    public static final String SYMBOL_REQUIRE_CONSTRAINT = "-->";
    /**
     * Symbol used to display assignments.
     */
    public static final String SYMBOL_ASSIGNMENT = ":=";

    /**
     * Symbol used to display public elements.
     */
    public static final String SYMBOL_PUBLIC = "+";
    /**
     * Symbol used to display private elements.
     */
    public static final String SYMBOL_PRIVATE = "-";
    /**
     * Symbol used to display protected elements.
     */
    public static final String SYMBOL_PROTECTED = "#";
    /**
     * Symbol used to display package visiblity elements.
     */
    public static final String SYMBOL_PACKAGE = "~";

    /**
     * Symbol used to display partial elements.
     */
    public static final String SYMBOL_PARTIAL_PUBLIC = "|";

    /**
     * Symbol used to display concern partial elements.
     */
    public static final String SYMBOL_PARTIAL_CONERN = "\u00A6";

    /*
     * -------------------- SCENE NAMES -------------------------
     */
    /**
     * Name for the select concern scene.
     */
    public static final String SCENE_NAME_SELECT_CONCERN = "Select Concern";
    /**
     * Name for the concern scene in select mode (for reusing).
     */
    public static final String SCENE_NAME_CONCERN_SELECT_MODE = "Select Mode";

    /*
     * ---------------------- MENUS -------------------------
     */
    /**
     * String for the back action menu button.
     */
    public static final String MENU_BACK = "Back";
    /**
     * String for the save action menu button.
     */
    public static final String MENU_SAVE = "Save";
    /**
     * String for the undo action menu button.
     */
    public static final String MENU_UNDO = "Undo";
    /**
     * String for the redo action menu button.
     */
    public static final String MENU_REDO = "Redo";
    /**
     * String for the create concern action menu button.
     */
    public static final String MENU_CONCERN_CREATE = "New Concern";

    /**
     * String for the 'build application' option in the menu.
     */
    public static final String MENU_BUILD_APPLICATION = "Build Application";

    /**
     * String for the load concern action menu button.
     */
    public static final String MENU_CONCERN_LOAD = "Load Concern";
    /**
     * String for the reuse action in {@link ca.mcgill.sel.ram.ui.scenesDisplayConcernSelectScene}.
     */
    public static final String MENU_REUSE = "Reuse";
    /**
     * String for the clear selection action in {@link ca.mcgill.sel.ram.ui.scenesDisplayConcernSelectScene}.
     */
    public static final String MENU_CLEAR_SELECTION = "Clear Selection";
    /**
     * String for the 'switch to next mode' action in {@link ca.mcgill.sel.ram.ui.scenesDisplayConcernSelectScene}.
     */
    public static final String MENU_SWITCH_NEXT = "Switch to 'Next Mode'";
    /**
     * String for the 'switch to full mode' action in {@link ca.mcgill.sel.ram.ui.scenesDisplayConcernSelectScene}.
     */
    public static final String MENU_SWITCH_FULL = "Switch to 'Full Mode'";
    /**
     * String for the 'show mandatory features' action in {@link ca.mcgill.sel.ram.ui.scenesDisplayConcernSelectScene}.
     */
    public static final String MENU_SHOW_MAND_FEATURES = "Show Mandatory Selections";
    /**
     * String for the 'hide mandatory features' action in {@link ca.mcgill.sel.ram.ui.scenesDisplayConcernSelectScene}.
     */
    public static final String MENU_HIDE_MAND_FEATURES = "Hide Mandatory Selections";
    /**
     * String for the 'new aspect' action in {@link ca.mcgill.sel.ram.ui.scenesDisplayConcernEditScene}.
     */
    public static final String MENU_NEW_ASPECT = "New RAM Model";
    /**
     * String for the 'new impact root goal' action in {@link ca.mcgill.sel.ram.ui.scenesDisplayConcernEditScene}.
     */
    public static final String MENU_NEW_IMPACT_ROOT = "New Root Goal";
    /**
     * String for the 'show reuses' action in {@link ca.mcgill.sel.ram.ui.scenesDisplayConcernEditScene}.
     */
    public static final String MENU_SHOW_REUSES = "Show Reuses";
    /**
     * String for the 'hide reuses' action in {@link ca.mcgill.sel.ram.ui.scenesDisplayConcernEditScene}.
     */
    public static final String MENU_HIDE_REUSES = "Hide Reuses";
    /**
     * String for the 'Expand all features' action in {@link ca.mcgill.sel.ram.ui.scenesDisplayConcernEditScene}.
     */
    public static final String MENU_EXPAND_ALL = "Expand All";
    /**
     * String for the 'home' action in {@link ca.mcgill.sel.ram.ui.scenes.DisplayConcernEditScene}.
     */
    public static final String MENU_HOME = "Home";
    /**
     * String for the 'back to concern' action in {@link ca.mcgill.sel.ram.ui.scenes.DisplayAspectScene}.
     */
    public static final String MENU_BACK_CONCERN = "Back to Concern";
    /**
     * String for the 'go to message view' action in {@link ca.mcgill.sel.ram.ui.scenes.DisplayAspectScene}.
     */
    public static final String MENU_GOTO_MESSAGEVIEW = "Go to Message Views";
    /**
     * String for the 'go to state view' action in {@link ca.mcgill.sel.ram.ui.scenes.DisplayAspectScene}.
     */
    public static final String MENU_GOTO_STATEVIEW = "Go to State Views";
    /**
     * String for the 'go to state view' action in {@link ca.mcgill.sel.ram.ui.scenes.DisplayAspectScene}.
     */
    public static final String MENU_GOTO_STRUCTURALVIEW = "Go to Structural View";
    /**
     * String for the 'apply CSP' action in {@link ca.mcgill.sel.ram.ui.scenes.DisplayAspectScene}.
     */
    public static final String MENU_APPLY_CSP = "Apply CSP";
    /**
     * String for the 'weave all' action in {@link ca.mcgill.sel.ram.ui.scenes.DisplayAspectScene}.
     */
    public static final String MENU_WEAVE_ALL = "Weave All";
    /**
     * String for the 'weave without CSP' action in {@link ca.mcgill.sel.ram.ui.scenes.DisplayAspectScene}.
     */
    public static final String MENU_WEAVE_NO_CSP = "Weave without CSP";
    /**
     * String for the 'generate code' action in {@link ca.mcgill.sel.ram.ui.scenes.DisplayAspectScene}.
     */
    public static final String MENU_GENERATE_CODE = "Generate Code";
    /**
     * String for the 'close split view' action in {@link ca.mcgill.sel.ram.ui.scenes.DisplayAspectScene}.
     */
    public static final String MENU_CLOSE_SPLITVIEW = "Close Split View";
    /**
     * String for add attribute action in RamMenu.
     */
    public static final String MENU_ATTRIBUTE_ADD = "Add attribute";
    /**
     * String for add destructor action in RamMenu.
     */
    public static final String MENU_DESTRUCTOR_ADD = "Add destructor";
    /**
     * String for add constructor action in RamMenu.
     */
    public static final String MENU_CONSTRUCTOR_ADD = "Add constructor";
    /**
     * String for add operation action in RamMenu.
     */
    public static final String MENU_OPERATION_ADD = "Add operation";
    /**
     * String for add literal action in RamMenu.
     */
    public static final String MENU_LITERAL_ADD = "Add literal";
    /**
     * String for add parameter action in RamMenu.
     */
    public static final String MENU_PARAMETER_ADD = "Add parameter";
    /**
     * String for add parameter action before another one in RamMenu.
     */
    public static final String MENU_PARAMETER_ADD_BEFORE = "Add parameter before";
    /**
     * String for add parameter action after another one in RamMenu.
     */
    public static final String MENU_PARAMETER_ADD_AFTER = "Add parameter after";
    /**
     * String for delete an eObject in RamMenu.
     */
    public static final String MENU_DELETE = "Delete";
    /**
     * String for close the menu in RamMenu.
     */
    public static final String MENU_CLOSE = "Close";
    /**
     * String to switch to public partial in RamMenu.
     */
    public static final String MENU_PUBLIC_PARTIAL = "Public partial";
    /**
     * String to switch to concern partial in RamMenu.
     */
    public static final String MENU_CONCERN_PARTIAL = "Concern partial";
    /**
     * String to switch to not partial in RamMenu.
     */
    public static final String MENU_NO_PARTIAL = "Not partial";
    /**
     * String to switch to abstract in RamMenu.
     */
    public static final String MENU_ABSTRACT = "Abstract";
    /**
     * String to switch to not abstract in RamMenu.
     */
    public static final String MENU_NOT_ABSTRACT = "Not abstract";
    /**
     * String to switch to static in RamMenu.
     */
    public static final String MENU_STATIC = "Static";
    /**
     * String to switch to not static in RamMenu.
     */
    public static final String MENU_NO_STATIC = "Not static";
    /**
     * String to show advice view in RamMenu.
     */
    public static final String MENU_ADVICE = "Advice";
    /**
     * String to switch class visibility to concern in RamMenu.
     */
    public static final String MENU_CLASS_CONCERN = "Concern visible";
    /**
     * String to switch class visibility to public in RamMenu.
     */
    public static final String MENU_CLASS_PUBLIC = "Public visible";
    /**
     * String to generate getter of attribute in RamMenu.
     */
    public static final String MENU_ATTRIBUTE_GETTER = "Generate getter";
    /**
     * String to generate setter of attribute in RamMenu.
     */
    public static final String MENU_ATTRIBUTE_SETTER = "Generate setter";
    /**
     * String to collapse class operations.
     */
    public static final String MENU_COLLAPSE_OPERATIONS = "Collapse operations";
    /**
     * String to expand class operations.
     */
    public static final String MENU_EXPAND_OPERATIONS = "Expand operations";

    /*
     * -------------------- LABEL -------------------------
     */
    /**
     * String for attributes.
     */
    public static final String LABEL_ATTRIBUTE = "Attr:";
    /**
     * String for operations.
     */
    public static final String LABEL_OPERATION = "Op:";

    /*
     * -------------------- PLACEHOLDER STRINGS -------------------------
     */
    /**
     * Search.
     */
    public static final String PH_SEARCH = "Search...";
    /**
     * Form of attributes the user needs to define.
     */
    public static final String PH_ATTRIBUTE = "<type> <attribute name>";
    /**
     * Form of operations the user needs to define.
     */
    public static final String PH_OPERATION = "<visibility> <return type> <operation name>([<parameters>])";
    /**
     * Form of enum literals the user needs to define.
     */
    public static final String PH_ENUM_LITERALS = "<literal>[, <literal>]";
    /**
     * Form of parameters the user needs to define.
     */
    public static final String PH_PARAM = "<type> <param name>";

    /**
     * Enter class name. Used if no name is defined for a class.
     */
    public static final String PH_ENTER_CLASS_NAME = "Enter class name";
    /**
     * Enter enum name. Used if no name is defined for an enum.
     */
    public static final String PH_ENTER_ENUM_NAME = "Enter enum name";
    /**
     * Enter aspect name. Used if no name is defined for an aspect.
     */
    public static final String PH_ENTER_ASPECT_NAME = "Enter aspect name";
    /**
     * Enter aspect name. Used if no name is defined for an impact model.
     */
    public static final String PH_ENTER_IMPACTMODEL_NAME = "Enter impact model name";
    /**
     * Enter file name. Used in browser.
     */
    public static final String PH_ENTER_FILE_NAME = "Enter a file name...";
    /**
     * Enter folder name. Used in browser.
     */
    public static final String PH_ENTER_FOLDER_NAME = "Enter a folder name...";
    /**
     * Enter state name. Used in state view.
     */
    public static final String PH_ENTER_STATE_NAME = "Enter state name";
    /**
     * Enter state view name. Used in state view.
     */
    public static final String PH_ENTER_STATEVIEW_NAME = "Enter state view name";
    /**
     * Enter role name. Used in structural view.
     */
    public static final String PH_ENTER_ROLE_NAME = "Enter role name";

    /**
     * Key. Used for associations.
     */
    public static final String PH_KEY = "Key";

    /**
     * Select.
     */
    public static final String PH_SELECT = "Select...";

    /**
     * Select class. Used to map classes.
     */
    public static final String PH_SELECT_CLASS = "Select Class";
    /**
     * Select attribute. Used to map attributes.
     */
    public static final String PH_SELECT_ATTRIBUTE = "Select Attribute";
    /**
     * Select. Used for Feature Selection of association end
     */
    public static final String PH_SELECT_ASSOCIATIONEND_FEATURE = "{select}";

    /**
     * Select operation. Used in state view.
     */
    public static final String PH_SELECT_OPERATION = "Select operation...";

    /**
     * Select assignment. Used in message view.
     */
    public static final String PH_SELECT_ASSIGNMENT = "Select assignment...";
    /**
     * Select return. Used in message view.
     */
    public static final String PH_SELECT_RETURN = "Select return...";
    /**
     * Select message. Used in message view.
     */
    public static final String PH_SELECT_MESSAGE = "Select message...";

    /**
     * Specify value. Used for assignments in message view.
     */
    public static final String PH_SPECIFY_VALUE = "Specify value...";
    /**
     * Specify constraint. Used in message view.
     */
    public static final String PH_SPECIFY_CONSTRAINT = "Specify constraint...";
    /**
     * Specify statement. Used in message view.
     */
    public static final String PH_SPECIFY_STATEMENT = "Specify statement...";

    /**
     * Rename property.
     */
    public static final String RENAME_PROPERTY = "Rename property";
    /**
     * Change value.
     */
    public static final String CHANGE_VALUE = "Change value";

    /*
     * ---------------------- BROWSER -------------------------
     */
    /**
     * File name label for the browser.
     */
    public static final String BROWSER_FILE_NAME_LABEL = "File name:";
    /**
     * Load label for the browser.
     */
    public static final String BROWSER_LOAD = "Load";
    /**
     * Save label for the browser.
     */
    public static final String BROWSER_SAVE = "Save";
    /**
     * Open label for the browser.
     */
    public static final String BROWSER_OPEN = "Open";
    /**
     * Choose selected folder label for the browser.
     */
    public static final String BROWSER_CHOOSE_SELECTED = "Choose selected";
    /**
     * Choose current folder label for the browser.
     */
    public static final String BROWSER_CHOOSE_CURRENT = "Choose current";
    /**
     * Override label for the browser.
     */
    public static final String BROWSER_OVERRIDE = "Overwrite?";

    /**
     * Message displayed in browser when the folder is empty.
     */
    public static final String BROWSER_EMPTY_FOLDER = "The folder is empty.";

    /**
     * Message displayed in browser when the folder could not be opened.
     */
    public static final String BROWSER_FOLDER_OPEN_ERROR = "Unable to open the folder";

    /*
     * ---------------------- POPUPS -------------------------
     */
    /**
     * String used to ask if the user wants to quit the app.
     */
    public static final String POPUP_QUIT_CONFIRM = "Are you sure you want to close " + APP_NAME + "?";

    /**
     * String used to ask if the user wants to save a modified model.
     */
    public static final String POPUP_MODIFIED_SAVE = " has been modified. Save changes?";
    /**
     * Yes.
     */
    public static final String POPUP_YES = "Yes";
    /**
     * No.
     */
    public static final String POPUP_NO = "No";
    /**
     * Cancel.
     */
    public static final String POPUP_CANCEL = "Cancel";
    /**
     * Close.
     */
    public static final String POPUP_CLOSE = "Close";

    /**
     * Generation of code in progress.
     */
    public static final String POPUP_GENERATING = "Generating...";
    /**
     * Error during code generation.
     */
    public static final String POPUP_GENERATION_ERROR = "Error while generating the code: ";

    /**
     * Reusing in progress.
     */
    public static final String POPUP_REUSING = "Reusing...";
    /**
     * Weaving in progress.
     */
    public static final String POPUP_WEAVING = "Weaving...";

    /**
     * Message when reusing failed.
     */
    public static final String POPUP_ERROR_REUSE = "An error occurred while reusing. Please see error log.";

    /**
     * Message when weaving failed.
     */
    public static final String POPUP_ERROR_WEAVING = "An error occured while weaving";

    /**
     * Message when trying to reuse the same concern.
     */
    public static final String POPUP_ERROR_SELF_REUSE = "You cannot reuse the same concern.";

    /**
     * Message when trying to extend the same aspect.
     */
    public static final String POPUP_ERROR_SELF_EXTENDS = "A model cannot extend itself.";

    /**
     * Message when trying to extend an aspect that already extends you.
     */
    public static final String POPUP_ERROR_CYCLIC_EXTENDS = "You cannot extend this model because it would create "
            + "a cycle in the dependencies.";

    /**
     * Message when trying to extend the same aspect twice.
     */
    public static final String POPUP_ERROR_SAME_EXTENDS = "You already extend this model.";

    /**
     * Message when trying to reuse the same concern.
     */
    public static final String POPUP_ERROR_REUSE_NO_REALIZED_FEATURE = "You cannot reuse a concern in this aspect.\n"
            + "The aspect must realize at least one feature.";

    /**
     * Message when saving file.
     */
    public static final String POPUP_SAVING = "Saving...";
    /**
     * Message when file saving failed.
     */
    public static final String POPUP_ERROR_FILE_SAVE = "Save failed: ";

    /**
     * Message when loading file.
     */
    public static final String POPUP_LOADING = "Loading...";

    /**
     * Message when file loading failed.
     */
    public static final String POPUP_ERROR_FILE_LOAD = "The file could not be loaded.";
    /**
     * Message when file loading failed because of file syntax.
     */
    public static final String POPUP_ERROR_FILE_LOAD_SYNTAX = "The file could not be loaded, syntax is invalid.";

    /**
     * Message when calling external classes fails in implementation class.
     */
    public static final String POPUP_ERROR_CLASS_NOT_FOUND =
            "Error. Could not find the class. Please reload the jar file.";

    /**
     * Message to display when user tries to specify behavior to a partial operation.
     */
    public static final String POPUP_PARTIAL_ABSTRACT_NO_BEHAVIOR =
            "Partial and/or Abstract methods don't have specified behaviour.";

    /**
     * Message to display when user tries to make abstract a class that has abstract operations.
     */
    public static final String POPUP_ABSTRACT_CLASS_NO_SWITCH =
            "Switch impossible because the class contains abstract operations.";

    /**
     * Message to display when user tries to change the visibility of a class that has to be public.
     */
    public static final String POPUP_PUBLIC_CLASS_NO_SWITCH =
            "You cannot switch the visibility because the class has public operations.";

    /**
     * Message when the reuse can't be deleted because there are still model reuses.
     */
    public static final String POPUP_CANT_DELETE_REUSE = "The reuse cannot be deleted: "
            + " Reuses in realization models need to be removed first.";

    /**
     * Message when the reuse can't be deleted because there are still model reuses.
     */
    public static final String POPUP_CANT_DELETE_FEATURE = "The feature cannot be deleted: "
            + " Reuses in realization models need to be removed first.";

    /**
     * Message the user can't weave because they are in edit mode.
     */
    public static final String POPUP_CANT_WEAVE_EDITMODE = "You can not weave when you are in instantiation edit mode.";

    /**
     * Message the user can't delete an instantiation because they are being edited.
     */
    public static final String POPUP_CANT_DELETE_INST_EDIT = "You can not delete the instantiation while editing it.";

    /**
     * Message when the user tries to map an operation or class with an attribute.
     */
    public static final String POPUP_CANT_MAP_OP_CLASS_TO_ATTRIBUTE =
            "You can not map a class or an operation to an attribute.";

    /**
     * Message when the user tries to map an attribute or class with an operation.
     */
    public static final String POPUP_CANT_MAP_ATTR_CLASS_TO_OPERATION =
            "You can not map a class or an attribute to an operation.";

    /**
     * Message when the user tries to map a class to an operation or an attribute.
     */
    public static final String POPUP_CANT_MAP_CLASS_TO_ATTR_OP =
            "You can not map a class to an operation or to an attribute";

    /**
     * Message when the user tries to map incompatible attributes.
     */
    public static final String POPUP_INVALID_ATTRIBUTE_MAPPING =
            "The attribute you selected is not valid for the mapping.";
    /**
     * Message when the user tries to map incompatible operations.
     */
    public static final String POPUP_INVALID_OPERATION_MAPPING =
            "The operation you selected is not valid for the mapping.";

    /**
     * Message when we want to reuse but the aspect was not saved.
     */
    public static final String POPUP_MUST_SAVE_ASPECT_TO_REUSE = "You must save the aspect before reusing a concern.";

    /**
     * Message when calling external classes fails in implementation class.
     */
    public static final String POPUP_ERROR_MAPPING_ALREADY_DEFINED =
            "This goal is already used in this Impact Feature.";

    /**
     * Message when calling external classes fails in implementation class.
     */
    public static final String POPUP_ERROR_ROOT_GOAL_OUTGOING =
            "The root goal can not have an outgoing contribution.";

    /**
     * Message when trying opening a concern that could not be found.
     */
    public static final String POPUP_ERROR_CONCERN_NOT_FOUND = "The concern was not found.";

    /*
     * -------------------- CONTAINER LABELS -------------------------
     */
    /**
     * Panel title for ValidationView when it's closed.
     */
    public static final String PANEL_OPEN_VALIDATION = "Open Validation";
    /**
     * Panel title for TracingView when it's closed.
     */
    public static final String PANEL_OPEN_TRACING = "Open Tracing";

    /**
     * Label for {@link ca.mcgill.sel.ram.ui.views.containers.COREAspectContainer}.
     */
    public static final String LABEL_ASPECT_CONTAINER = "RAM Models";
    /**
     * Label for {@link ca.mcgill.sel.ram.ui.views.containers.COREConstraintContainer}.
     */
    public static final String LABEL_CONSTRAINT_CONTAINER = "Constraints";
    /**
     * Label for {@link ca.mcgill.sel.ram.ui.views.containers.COREImpactContainer} in the concern scene.
     */
    public static final String LABEL_IMPACT_CONTAINER_VIEW = "Goals";
    /**
     * Label for {@link ca.mcgill.sel.ram.ui.views.containers.COREImpactContainer} in the impact scene.
     */
    public static final String LABEL_IMPACT_CONTAINER_ADD = "Import Existing Goal";
    /**
     * Label for {@link ca.mcgill.sel.ram.ui.views.containers.COREFeatureContainer} in the impact scene.
     */
    public static final String LABEL_FEATURE_CONTAINER = "Add Feature";
    /**
     * Label for {@link ca.mcgill.sel.ram.ui.views.containers.TracingView}.
     */
    public static final String LABEL_TRACING_VIEW = "Tracing";
    /**
     * Label for {@link ca.mcgill.sel.ram.ui.views.containers.ValidationView}.
     */
    public static final String LABEL_VALIDATION_VIEW = "Validation";

    /**
     * Title for the reuses container in aspect scene.
     */
    public static final String LABEL_CONTAINER_MODEL_REUSE = "Concern Reuses";
    /**
     * Title for the reuses container in aspect scene.
     */
    public static final String LABEL_CONTAINER_MODEL_EXTENDS = "Extended RAM Models";

    /*
     * -------------------- SELECTOR OPTIONS -------------------------
     */
    /**
     * Generating java.
     */
    public static final String OPT_GENERATE_JAVA = "Java";
    /**
     * Generating C++.
     */
    public static final String OPT_GENERATE_CPP = "C++";

    /**
     * Home option in browser.
     */
    public static final String OPT_BROWSER_HOME = "Home";
    /**
     * Models option in browser.
     */
    public static final String OPT_BROWSER_MODELS = "Models";
    /**
     * Desktop option in browser.
     */
    public static final String OPT_BROWSER_DESKTOP = "Desktop";

    /*
     * -------------------- DEBUG STRING -------------------------
     */
    /**
     * Creates a new instance.
     */
    private Strings() {
    }

    /**
     * Message to display when code has been generated.
     *
     * @param folder - name of the saved folder.
     * @return The message to display
     */
    public static String popupCodeGenerated(String folder) {
        return "Code successfuly generated into the " + folder + "/ folder!";
    }

    /**
     * Message to display when an aspect could not be loaded because it is malformed.
     *
     * @param aspect - name of the aspect.
     * @return The message to display
     */
    // CHECKSTYLE:IGNORE MultipleStringLiterals FOR 2 LINES: Okay in this case.
    public static String popupAspectMalformed(String aspect) {
        return "The aspect " + aspect + " is malformed and could not be loaded."
                + " Please see log for details.";
    }

    /**
     * Message to display when we want to delete an element and this operation cannot be undone.
     *
     * @param element - The element to delete
     * @return the message to display.
     */
    public static String popupDeleteElement(String element) {
        return "Delete " + element + "?\n This action cannot be undone.";
    }

    /**
     * Message to display when we want to tell the user the aspect needs to realize at least a feature.
     *
     * @param aspect - The aspect
     * @return the message to display.
     */
    public static String popupAspectNeedsRealize(String aspect) {
        return "The aspect " + aspect + " needs to realize a feature.";
    }

    /**
     * Message to display when we want to tell the user the aspect needs to be saved.
     *
     * @param aspect - The aspect
     * @return the message to display.
     */
    public static String popupAspectNeedsSave(String aspect) {
        return "The aspect " + aspect + " needs to be saved.";
    }
}
