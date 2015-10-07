package ca.mcgill.sel.ram.ui.utils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.mt4j.util.MTColor;

import ca.mcgill.sel.ram.validator.ValidationRuleErrorDescription;
import ca.mcgill.sel.ram.validator.ValidationRuleErrorDescription.ValidationErrorType;

/**
 * Utility class that contains all available colors and settings for them.
 *
 * @author mschoettle
 */
public final class Colors {

    /**
     * The default background color.
     */
    public static final MTColor BACKGROUND_COLOR = new MTColor(190, 190, 190, 255);

    /**
     * The default color for elements in a list.
     */
    public static final MTColor DEFAULT_ELEMENT_FILL_COLOR = MTColor.WHITE;

    /**
     * The default color for a line.
     */
    public static final MTColor DEFAULT_ELEMENT_STROKE_COLOR = MTColor.BLACK;

    /**
     * The highlight color for a line.
     */
    public static final MTColor HIGHLIGHT_ELEMENT_STROKE_COLOR = new MTColor(50, 180, 50);

    /*
     * -------------------- COMPONENTS -------------------------
     */
    /**
     * Fill color for {@link ca.mcgill.sel.ram.ui.components.RamImageComponent} for expand triangles.
     */
    public static final MTColor TRIANGLE_EXPAND_COLLAPSE_FILL_COLOR = new MTColor(58, 75, 93, 160);

    /**
     * A transparent dark grey color.
     */
    public static final MTColor TRANSPARENT_DARK_GREY = new MTColor(45, 45, 45, 180);

    /**
     * The fill color for an enabled button.
     */
    public static final MTColor COLOR_BUTTON_ENABLED = MTColor.BLACK;
    /**
     * The fill color for a disabled button.
     */
    public static final MTColor COLOR_BUTTON_DISABLED = MTColor.GRAY;

    /**
     * Default fill color the RamSelectorComponent.
     */
    public static final MTColor SELECTOR_DEFAULT_BACKGROUND_COLOR = new MTColor(204, 51, 51, 200);

    /**
     * Default fill color the RamListComponent when a element is selected.
     */
    public static final MTColor LIST_DEFAULT_BACKGROUND_SELECTED_COLOR = MTColor.GREEN;

    /**
     * Fill color the scroll buttons in RamSelectorComponent.
     */
    public static final MTColor SELECTOR_SCROLL_BUTTON_COLOR = new MTColor(125, 125, 160);
    /**
     * Fill color the scroll buttons in RamListComponent.
     */
    public static final MTColor LIST_SCROLL_BUTTON_COLOR = new MTColor(190, 72, 80, 228);

    /**
     * Fill color the root circle in RamMenu.
     */
    public static final MTColor MENU_BACKGROUND_COLOR = new MTColor(45, 45, 45);

    /**
     * Fill color the root circle in RamMenu.
     */
    public static final MTColor MENU_BACKGROUND_COLOR_TOGGLED = new MTColor(15, 15, 15);

    /*
     * --------------------------- STRUCTURAL VIEW ------------------------
     */
    /**
     * The default class view fill color.
     */
    public static final MTColor CLASS_VIEW_DEFAULT_FILL_COLOR = MTColor.WHITE;

    /**
     * The default class view stroke color.
     */
    public static final MTColor CLASS_VIEW_DEFAULT_STROKE_COLOR = MTColor.BLACK;

    /**
     * The stroke color for class in edit mode.
     */
    public static final MTColor CLASS_VIEW_EDIT_STROKE_COLOR = MTColor.RED;

    /**
     * The default stroke color for a selected view in structural diagram.
     */
    public static final MTColor CLASS_SELECTED_VIEW_FILL_COLOR = MTColor.YELLOW;

    /**
     * The default {@link ca.mcgill.sel.ram.ui.views.RelationshipView} fill color.
     */
    public static final MTColor DEFAULT_RELATIONSHIP_FILL_COLOR = MTColor.WHITE;

    /**
     * The default class view fill color when it is selected in lower level aspect and for the ones which already mapped
     * in the higher level aspect Instantiation Split View.
     */
    public static final MTColor SELECTION_CLASS_DARK = new MTColor(31, 189, 0);

    /**
     * The class view fill color when the class is shown as mappable in higher level aspect in Instantiation Split View.
     */
    public static final MTColor SELECTION_CLASS_LIGHT = new MTColor(191, 255, 128);

    /**
     * The operation view default fill color.
     */
    public static final MTColor OPERATION_VIEW_DEFAULT_FILL_COLOR = MTColor.WHITE;

    /**
     * The operation view default stroke color.
     */
    public static final MTColor OPERATION_VIEW_DEFAULT_STROKE_COLOR = MTColor.WHITE;

    /**
     * The attribute view default fill color.
     */
    public static final MTColor ATTRIBUTE_VIEW_DEFAULT_FILL_COLOR = MTColor.WHITE;

    /**
     * The attribute view default stroke color.
     */
    public static final MTColor ATTRIBUTE_VIEW_DEFAULT_STROKE_COLOR = MTColor.WHITE;

    /**
     * The attribute view lighter fill color when it is selected in Instantiation Split View.
     */
    public static final MTColor SELECTION_ATTRIBUTE_LIGHT = SELECTION_CLASS_LIGHT;

    /**
     * The attribute view darker fill color when it is selected in Instantiation Split View.
     */
    public static final MTColor SELECTION_ATTRIBUTE_DARK = SELECTION_CLASS_DARK;

    /**
     * The attribute view's stroke color. The mentioned attribute view belongs to higher level aspect in split
     * instantiation view.
     */
    public static final MTColor MAPPING_ATTRIBUTE_STROKE = new MTColor(160, 240, 0);

    /**
     * The operation view darker fill color when it is selected in Instantiation Split View.
     */
    public static final MTColor SELECTION_OPERATION_DARK = SELECTION_CLASS_DARK;

    /**
     * The operation view lighter fill color when it is selected in Instantiation Split View.
     */
    public static final MTColor SELECTION_OPERATION_LIGHT = SELECTION_CLASS_LIGHT;

    /**
     * The operation view's stroke color. The mentioned operation view belongs to higher level aspect in split
     * instantiation view.
     */
    public static final MTColor MAPPING_OPERATION_STROKE = MAPPING_ATTRIBUTE_STROKE;

    /**
     * Instantiations title view color (each individual view showing instantiation's namet etc. located inside the
     * instantiations menu on the upper left)
     */
    public static final MTColor INSTANTIATIONS_MENU_ITEMS_FILL_COLOR = new MTColor(194, 145, 85, 220);

    /**
     * MappingContainerView's color.
     */
    public static final MTColor MAPPING_CONTAINER_VIEW_FILL_COLOR = new MTColor(130, 130, 130);

    /**
     * InstantiationView's color.
     */
    public static final MTColor INSTANTIATION_VIEW_FILL_COLOR = new MTColor(180, 180, 180, 180);

    /**
     * Background color of lower level aspect in split instantiation editing mode.
     */
    public static final MTColor BACKGROUND_COLOR_OF_LOWER_LEVEL_ASPECT_IN_SPLIT_VIEW = new MTColor(184, 186, 212);

    /**
     * ClassMappingView's color.
     */
    public static final MTColor CLASS_MAPPING_VIEW_FILL_COLOR = new MTColor(130, 130, 130);

    /**
     * AttributeMappingView's color.
     */
    public static final MTColor ATTRIBUTE_MAPPING_VIEW_FILL_COLOR = new MTColor(130, 130, 130);

    /**
     * OperationMappingView's color.
     */
    public static final MTColor OPERATION_MAPPING_VIEW_FILL_COLOR = new MTColor(130, 130, 130);

    /**
     * The default key selection fill color in structural view.
     */
    public static final MTColor STRUCT_KEY_SELECTION_FILL_COLOR = MTColor.WHITE;

    /*
     * -------------------- FEATURE MODEL -------------------------
     */
    /**
     * Color used for assignment highlighting when associating features and aspects.
     */
    public static final MTColor FEATURE_ASSIGNEMENT_FILL_COLOR = new MTColor(255, 200, 100, 255, true);
    /**
     * Color used for feature that have at least a realization model.
     */
    public static final MTColor FEATURE_ASSIGNED_FILL_COLOR = MTColor.WHITE;
    /**
     * Color used for feature that have no realization model.
     */
    public static final MTColor FEATURE_UNASSIGNED_FILL_COLOR = new MTColor(210, 210, 210, 255, true);

    /**
     * Color for reuses in feature model edit mode.
     */
    public static final MTColor FEATURE_REUSE_FILL_COLOR = MTColor.GRAY;

    /**
     * Color for optional relationship elements in feature model.
     */
    public static final MTColor FEATURE_OPTIONAL_RELATIONSHIP_FILL_COLOR = MTColor.WHITE;
    /**
     * Color for optional relationship elements in feature model.
     */
    public static final MTColor FEATURE_MANDATORY_RELATIONSHIP_FILL_COLOR = MTColor.BLACK;
    /**
     * Color for OR relationship elements in feature model.
     */
    public static final MTColor FEATURE_OR_RELATIONSHIP_COLOR = MTColor.BLACK;

    /**
     * Color for automatically selected features.
     */
    public static final MTColor FEATURE_AUTOSELECTED_FILL_COLOR = new MTColor(150, 255, 150, 255, true);
    /**
     * Color for manually selected features.
     */
    public static final MTColor FEATURE_SELECTED_FILL_COLOR = new MTColor(80, 200, 80, 255, true);
    /**
     * Color for non selected features.
     */
    public static final MTColor FEATURE_NOT_SELECTED_FILL_COLOR = MTColor.GRAY;
    /**
     * Color used to know we don't want to fill a feature.
     */
    public static final MTColor FEATURE_NO_FILL = new MTColor(255, 255, 255, 0, true);

    /**
     * Color for conflict in a feature selection.
     */
    public static final MTColor FEATURE_SELECTION_CLASH_COLOR = MTColor.RED;

    /**
     * Color for icons in feature selection mode.
     */
    public static final MTColor FEATURE_SELECTMODE_ICON_COLOR = MTColor.WHITE;

    /*
     * -------------------- IMPACT MODEL -------------------------
     */
    /**
     * Background color for a goal in impact model.
     */
    public static final MTColor IMPACT_GOAL_FILL_COLOR = MTColor.GRAY;

    /**
     * Background color for contribution bar in impact model.
     */
    public static final MTColor IMPACT_BAR_BACK_FILL_COLOR = MTColor.WHITE;
    /**
     * Foreground color for contribution bar in impact model.
     */
    public static final MTColor IMPACT_BAR_FORE_FILL_COLOR = MTColor.RED;

    /**
     * Fill color for feature and goal when feature is selected in concern select mode.
     */
    public static final MTColor IMPACT_SELECTED_FEATURE_FILL_COLOR = MTColor.GREEN;
    /**
     * Fill color for feature and goal when feature is not selected in concern select mode.
     */
    public static final MTColor IMPACT_UNSELECTED_FEATURE_FILL_COLOR = MTColor.RED;

    /*
     * -------------------- MESSAGE VIEW -------------------------
     */
    /**
     * Fill color for spacer in message view.
     */
    public static final MTColor MESSAGE_SPACER_FILL_COLOR = new MTColor(100, 100, 100, 160);
    /**
     * Fill color for lifeline label in message view.
     */
    public static final MTColor MESSAGE_LIFELINE_LABEL_FILL_COLOR = MTColor.WHITE;
    /**
     * Fill color for statements in message view.
     */
    public static final MTColor MESSAGE_STATEMENT_FILL_COLOR = MTColor.WHITE;

    /*
     * -------------------- STATE VIEW -------------------------
     */
    /**
     * Fill color for start states in state view.
     */
    public static final MTColor STATE_START_FILL_COLOR = MTColor.GRAY;
    /**
     * Stroke color for states in edit mode.
     */
    public static final MTColor STATE_EDIT_MODE_STROKE_COLOR = MTColor.RED;

    /*
     * -------------------- FILE BROWSER -------------------------
     */
    /**
     * Background color for the file browser.
     */
    public static final MTColor BROWSER_BACKGROUND_COLOR = new MTColor(0, 0, 0, 200);
    /**
     * Foreground color for the file browser.
     */
    public static final MTColor BROWSER_FOREGROUND_COLOR = new MTColor(70, 70, 70);
    /**
     * Foreground color for text fields in the file browser.
     */
    public static final MTColor BROWSER_FIELD_FOREGROUND_COLOR = new MTColor(90, 90, 90);
    /**
     * Background color for selected items in file browser.
     */
    public static final MTColor BROWSER_SELECTED_BACKGROUND_COLOR = new MTColor(30, 120, 210);
    /**
     * Background color for folders in file browser.
     */
    public static final MTColor BROWSER_FOLDER_BACKGROUND_COLOR = new MTColor(130, 130, 130);
    /**
     * Background color for files in file browser.
     */
    public static final MTColor BROWSER_FILE_BACKGROUND_COLOR = new MTColor(90, 90, 90);
    /**
     * Background color for the file browser.
     */
    public static final MTColor BROWSER_ICON_COLOR = MTColor.WHITE;
    /**
     * Default color for enabled buttons in the file browser.
     */
    public static final MTColor BROWSER_BUTTON_COLOR = new MTColor(95, 95, 95);
    /**
     * Default color for disabled buttons in the file browser.
     */
    public static final MTColor BROWSER_BUTTON_COLOR_DISABLED = new MTColor(43, 43, 43);
    /**
     * Color for enabled validation button in the file browser.
     */
    public static final MTColor BROWSER_VALIDATE_BUTTON_COLOR = new MTColor(20, 140, 25);
    /**
     * Color for disabled validation button in the file browser.
     */
    public static final MTColor BROWSER_VALIDATE_BUTTON_COLOR_DISABLED = new MTColor(12, 89, 15);
    /**
     * Color for enabled file override button in the file browser.
     */
    public static final MTColor BROWSER_OVERRIDE_BUTTON_COLOR = new MTColor(255, 120, 0);
    /**
     * Color for disabled file override button in the file browser.
     */
    public static final MTColor BROWSER_OVERRIDE_BUTTON_COLOR_DISABLED = new MTColor(204, 95, 0);
    /**
     * Color for enabled cancel button in the file browser.
     */
    public static final MTColor BROWSER_CANCEL_BUTTON_COLOR = new MTColor(150, 20, 15);
    /**
     * Color for disabled cancel button in the file browser.
     */
    public static final MTColor BROWSER_CANCEL_BUTTON_COLOR_DISABLED = new MTColor(99, 13, 10);

    /*
     * -------------------- KEYBOARD -------------------------
     */
    /**
     * Background color for the keyboard.
     */
    public static final MTColor KEYBOARD_BACKGROUND_COLOR = new MTColor(0, 0, 0, 200);

    /**
     * Background color for keys of the keyboard when up.
     */
    public static final MTColor KEYBOARD_KEY_UP_COLOR = new MTColor(100, 100, 100);
    /**
     * Background color for keys of the keyboard when pressed.
     */
    public static final MTColor KEYBOARD_KEY_DOWN_COLOR = new MTColor(220, 220, 220);

    /**
     * Background color for the modifier keys of the keyboard when up.
     */
    public static final MTColor KEYBOARD_MODIFIER_KEY_UP_COLOR = new MTColor(80, 80, 80);
    /**
     * Background color for the modifier keys of the keyboard when down.
     */
    public static final MTColor KEYBOARD_MODIFIER_KEY_DOWN_COLOR = new MTColor(220, 145, 35);

    /**
     * Background color for the validate key of the keyboard when up.
     */
    public static final MTColor KEYBOARD_VALIDATE_KEY_UP_COLOR = new MTColor(20, 140, 25);
    /**
     * Background color for the validate key of the keyboard when pressed.
     */
    public static final MTColor KEYBOARD_VALIDATE_KEY_DOWN_COLOR = new MTColor(25, 190, 30);

    /**
     * Background color for the cancel key of the keyboard.
     */
    public static final MTColor KEYBOARD_CANCEL_KEY_UP_COLOR = new MTColor(150, 20, 15);
    /**
     * Background color for the cancel key of the keyboard.
     */
    public static final MTColor KEYBOARD_CANCEL_KEY_DOWN_COLOR = new MTColor(195, 30, 25);

    /**
     * Default font color for the keyboard.
     */
    public static final MTColor KEYBOARD_DEFAULT_FONT_COLOR = MTColor.WHITE;
    /**
     * Default font color for the keys when pressed.
     */
    public static final MTColor KEYBOARD_PRESSED_FONT_COLOR = MTColor.BLACK;

    /*
     * -------------------- POPUPS -------------------------
     */
    /**
     * Default dill color for the popups.
     */
    public static final MTColor POPUP_DEFAULT_FILL_COLOR = new MTColor(128, 128, 128, 220);
    /**
     * Fill color for the success popups.
     */
    public static final MTColor POPUP_SUCCESS_FILL_COLOR = new MTColor(0, 128, 0, 220);
    /**
     * Stroke color for the success popups.
     */
    public static final MTColor POPUP_SUCCESS_STROKE_COLOR = new MTColor(0, 60, 0);
    /**
     * Fill color for the error popups.
     */
    public static final MTColor POPUP_ERROR_FILL_COLOR = new MTColor(180, 0, 0, 220);
    /**
     * Stroke color for the error popups.
     */
    public static final MTColor POPUP_ERROR_STROKE_COLOR = new MTColor(60, 0, 0);

    /*
     * -------------------- VALIDATION VIEW -------------------------
     */
    /**
     * Color for validation info highlight.
     */
    public static final MTColor VALIDATION_INFO_FILL_COLOR = new MTColor(91, 104, 200);
    /**
     * Color for validation warning highlight.
     */
    public static final MTColor VALIDATION_WARNING_FILL_COLOR = new MTColor(200, 196, 91);
    /**
     * Color for validation error highlight.
     */
    public static final MTColor VALIDATION_ERROR_FILL_COLOR = new MTColor(200, 91, 106);
    /**
     * Transparent color for validation.
     */
    public static final MTColor VALIDATION_TRANSPARENT = new MTColor(256, 256, 256, MTColor.ALPHA_NO_TRANSPARENCY);
    /**
     * High transparency color for validation.
     */
    public static final MTColor VALIDATION_HIGH_TRANSPARENT = new MTColor(256, 256, 256,
            MTColor.ALPHA_HIGH_TRANSPARENCY);

    /**
     * Color for icons in validation view.
     */
    public static final MTColor VALIDATION_ICON_COLOR = MTColor.WHITE;

    /*
     * ------------------------- ICONS AND IMAGES ------------------------
     */
    /**
     * Default color for {@link ca.mcgill.sel.ram.ui.components.RamImageComponent}.
     */
    public static final MTColor IMAGE_DEFAULT_COLOR = MTColor.WHITE;

    /**
     * The icon color for arrows buttons.
     */
    public static final MTColor ICON_ARROW_COLOR = MTColor.BLACK;
    /**
     * The icon color for close buttons.
     */
    public static final MTColor ICON_CLOSE_COLOR = MTColor.BLACK;
    /**
     * The icon color for close button in selector.
     */
    public static final MTColor ICON_CLOSE_SELECTOR_COLOR = MTColor.MAROON;
    /**
     * The icon color for delete buttons.
     */
    public static final MTColor ICON_DELETE_COLOR = MTColor.RED;
    /**
     * The default icon color for add buttons.
     */
    public static final MTColor ICON_ADD_DEFAULT_COLOR = MTColor.WHITE;
    /**
     * The icon color for add button in message view.
     */
    public static final MTColor ICON_ADD_MESSAGEVIEW_COLOR = MTColor.WHITE;
    /**
     * The icon color for add button in structural diagram.
     */
    public static final MTColor ICON_STRUCT_DEFAULT_COLOR = MTColor.WHITE;
    /**
     * The icon color for add button in state view.
     */
    public static final MTColor ICON_ADD_STATEVIEW_COLOR = MTColor.WHITE;

    /** The list of colors to use for the {@link ca.mcgill.sel.ram.ui.views.containers.TracingView}. */
    public static final List<MTColor> TRACING_VIEW_COLORS;
    static {
        TRACING_VIEW_COLORS = new LinkedList<MTColor>();
        TRACING_VIEW_COLORS.add(new MTColor(255, 153, 153));
        TRACING_VIEW_COLORS.add(new MTColor(255, 204, 153));
        TRACING_VIEW_COLORS.add(new MTColor(255, 255, 153));
        TRACING_VIEW_COLORS.add(new MTColor(204, 255, 153));
        TRACING_VIEW_COLORS.add(new MTColor(153, 255, 153));
        TRACING_VIEW_COLORS.add(new MTColor(153, 255, 204));
        TRACING_VIEW_COLORS.add(new MTColor(153, 255, 255));
        TRACING_VIEW_COLORS.add(new MTColor(153, 204, 255));
        TRACING_VIEW_COLORS.add(new MTColor(153, 153, 255));
        TRACING_VIEW_COLORS.add(new MTColor(204, 153, 255));
        TRACING_VIEW_COLORS.add(new MTColor(255, 153, 255));
        TRACING_VIEW_COLORS.add(new MTColor(255, 153, 204));
    }

    /**
     * Colors to display for VALIDATOR_EVENT on model elements concerned by validation events. (When the validation
     * occurred).
     */
    public static final Map<ValidationErrorType, MTColor> VALIDATOR_EVENT_COLORS;
    static {
        VALIDATOR_EVENT_COLORS = new HashMap<ValidationRuleErrorDescription.ValidationErrorType, MTColor>();
        VALIDATOR_EVENT_COLORS.put(ValidationErrorType.ERROR, VALIDATION_ERROR_FILL_COLOR);
        VALIDATOR_EVENT_COLORS.put(ValidationErrorType.WARNING, VALIDATION_WARNING_FILL_COLOR);
        VALIDATOR_EVENT_COLORS.put(ValidationErrorType.INFO, VALIDATION_INFO_FILL_COLOR);
    }

    /**
     * Colors to display for VALIDATION_VIEW_EVENT in the validation view. (When the user clicked on an error in the
     * ValidationView).
     */
    public static final Map<ValidationErrorType, MTColor> VALIDATIONVIEW_EVENT_COLORS;
    static {
        VALIDATIONVIEW_EVENT_COLORS = new HashMap<ValidationRuleErrorDescription.ValidationErrorType, MTColor>();
        VALIDATIONVIEW_EVENT_COLORS.put(ValidationErrorType.ERROR, VALIDATION_ERROR_FILL_COLOR);
        VALIDATIONVIEW_EVENT_COLORS.put(ValidationErrorType.WARNING, VALIDATION_WARNING_FILL_COLOR);
        VALIDATIONVIEW_EVENT_COLORS.put(ValidationErrorType.INFO, VALIDATION_INFO_FILL_COLOR);
    }

    /**
     * Creates a new instance.
     */
    private Colors() {
    }

}
