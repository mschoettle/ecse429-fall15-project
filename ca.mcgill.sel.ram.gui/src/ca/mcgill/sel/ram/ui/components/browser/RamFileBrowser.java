package ca.mcgill.sel.ram.ui.components.browser;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.mt4j.components.MTComponent;
import org.mt4j.components.TransformSpace;
import org.mt4j.input.gestureAction.InertiaDragAction;
import org.mt4j.input.inputProcessors.componentProcessors.AbstractComponentProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapProcessor;
import org.mt4j.util.MTColor;
import org.mt4j.util.animation.Animation;
import org.mt4j.util.animation.AnimationEvent;
import org.mt4j.util.animation.IAnimation;
import org.mt4j.util.animation.IAnimationListener;
import org.mt4j.util.animation.MultiPurposeInterpolator;
import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamButton;
import ca.mcgill.sel.ram.ui.components.RamImageComponent;
import ca.mcgill.sel.ram.ui.components.RamKeyboard;
import ca.mcgill.sel.ram.ui.components.RamKeyboard.KeyboardPosition;
import ca.mcgill.sel.ram.ui.components.RamPopup;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.components.RamScrollComponent;
import ca.mcgill.sel.ram.ui.components.RamSelectorComponent;
import ca.mcgill.sel.ram.ui.components.RamTextComponent;
import ca.mcgill.sel.ram.ui.components.RamTextFieldComponent;
import ca.mcgill.sel.ram.ui.components.browser.interfaces.RamFileBrowserListener;
import ca.mcgill.sel.ram.ui.components.listeners.AbstractDefaultRamSelectorListener;
import ca.mcgill.sel.ram.ui.components.listeners.DefaultRamKeyboardListener;
import ca.mcgill.sel.ram.ui.components.listeners.RamTextListener;
import ca.mcgill.sel.ram.ui.events.listeners.ActionListener;
import ca.mcgill.sel.ram.ui.events.listeners.ITapListener;
import ca.mcgill.sel.ram.ui.layouts.GridLayout;
import ca.mcgill.sel.ram.ui.layouts.HorizontalLayoutRightAligned;
import ca.mcgill.sel.ram.ui.layouts.HorizontalLayoutVerticallyCentered;
import ca.mcgill.sel.ram.ui.layouts.Layout.HorizontalAlignment;
import ca.mcgill.sel.ram.ui.layouts.Layout.VerticalAlignment;
import ca.mcgill.sel.ram.ui.layouts.VerticalLayout;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.utils.Constants;
import ca.mcgill.sel.ram.ui.utils.Fonts;
import ca.mcgill.sel.ram.ui.utils.Icons;
import ca.mcgill.sel.ram.ui.utils.MathUtils;
import ca.mcgill.sel.ram.ui.utils.Strings;
import ca.mcgill.sel.ram.ui.views.handler.BaseHandler;

/**
 * A touch file browser to save and load files from the file system.
 * 
 * @author tdimeco
 */
public class RamFileBrowser extends RamRectangleComponent implements ActionListener, RamTextListener {

    private static final String DEFAULT_FOLDER = Constants.DIRECTORY_MODELS;
    private static final int COLUMNS_COUNT = 3;
    private static final float PADDING = 8;
    private static final float BIG_PADDING = 50;
    private static final float FILE_ITEM_WIDTH = 200;
    private static final float FILE_ITEM_HEIGHT = 60;
    private static final float FILES_CONTAINER_HEIGHT = 260;
    private static final float SEARCH_FIELD_WIDTH = 150;
    private static final float NEW_FOLDER_NAME_FIELD_WIDTH = 250;
    private static final float MOUSE_WHEEL_SENSITIVITY = 200;
    private static final float MINIMUM_DRAG_DISTANCE = 20;
    
    private static final String FOLDER_NAME_REGEXP = "[^*/><?\"|:]*";

    private static final String ACTION_BACK = "action.back";
    private static final String ACTION_UP = "action.hierarchy.up";
    private static final String ACTION_OTHER = "action.other";
    private static final String ACTION_HOME = "action.home";
    private static final String ACTION_LIBRARY = "action.library";
    private static final String ACTION_NEW_FOLDER = "action.new.folder";
    private static final String ACTION_NEW_FOLDER_VALIDATE = "action.new.folder.validate";
    private static final String ACTION_NEW_FOLDER_CANCEL = "action.new.folder.cancel";
    private static final String ACTION_CLOSE = "action.close";
    private static final String ACTION_VALIDATE = "action.validate";

    /**
     * Represents the type of the file browser.
     */
    public static enum RamFileBrowserType {
        /** A save file browser. */
        SAVE,
        /** A load file browser. */
        LOAD,
        /** A load folder file browser. */
        FOLDER
    }

    /**
     * Other button selector options.
     */
    private static enum OtherSelectorOption {
        MODELS(Strings.OPT_BROWSER_MODELS, Constants.DIRECTORY_MODELS),
        HOME(Strings.OPT_BROWSER_HOME, System.getProperty("user.home")),
        DESKTOP(Strings.OPT_BROWSER_DESKTOP, System.getProperty("user.home") + "/Desktop");

        private String path;
        private String name;

        /**
         * Creates a new option.
         * 
         * @param name the name for this option
         * @param path the path for this option.
         */
        OtherSelectorOption(String name, String path) {
            this.name = name;
            this.path = path;
        }
    }

    private static RamFileBrowser currentFileBrowser;

    private RamFileBrowserType type;
    private String extensionFilter;
    private File currentFolder;
    private boolean saveOverrideFlag;
    private List<FileItem> fileItems;
    private Stack<String> navigationHistory;
    private FileItem selectedFileItem;
    private Set<RamFileBrowserListener> listeners;
    private RamRectangleComponent headerContainer;
    private RamScrollComponent middleContainer;
    private RamRectangleComponent filesContainer;
    private RamRectangleComponent footerContainer;
    private RamTextFieldComponent currentFolderLabel;
    private RamTextFieldComponent searchField;
    private RamTextComponent filenameLabel;
    private RamTextFieldComponent filenameField;
    private RamTextFieldComponent newFolderNameField;
    private RamButton upButton;
    private RamButton backButton;
    private RamButton otherButton;
    private RamButton libButton;
    private RamButton homeButton;
    private RamButton newFolderButton;
    private RamButton cancelButton;
    private RamButton validateButton;
    private RamSelectorComponent<String> otherSelector;
    private boolean isInitialized;
    // used to block the user in a certain hierarchy of folders
    private File browsingRootDirectory;
    // used to display callback waiting messages
    private boolean isCallbackThreaded;
    private String callbackPopupMessage;

    /**
     * Constructs a file browser instance, opens the models folder and show all file types.
     * 
     * @param type The type of the file browser
     */
    public RamFileBrowser(RamFileBrowserType type) {
        this(type, "", null);
    }

    /**
     * Constructs a file browser instance and opens the models folder.
     * 
     * @param type The type of the file browser
     * @param extensionFilter The extension you want to show (lower case, like "ram" or "core") or "" for all
     */
    public RamFileBrowser(RamFileBrowserType type, String extensionFilter) {
        this(type, extensionFilter, null);
    }

    /**
     * Constructs a file browser instance.
     * 
     * @param type The type of the file browser
     * @param extensionFilter The extension you want to show (lower case, like "ram" or "core") or "" for all
     * @param defaultFile The starting folder (the target file, for the save browser)
     */
    public RamFileBrowser(RamFileBrowserType type, String extensionFilter, File defaultFile) {
        this(type, extensionFilter, defaultFile, null);
    }

    /**
     * Constructs a file browser instance.
     * 
     * @param type The type of the file browser
     * @param extensionFilter The extension you want to show (lower case, like "ram" or "core") or "" for all
     * @param defaultFile The starting folder (the target file, for the save browser)
     * @param browsingRoot - Root directory as defined by the user. If it is not null,
     *            the user won't be able to go higher in the file hierarchy than this folder
     */
    public RamFileBrowser(RamFileBrowserType type, String extensionFilter, File defaultFile, File browsingRoot) {
        this.type = type;
        this.extensionFilter = extensionFilter;
        this.saveOverrideFlag = false;
        this.listeners = new HashSet<RamFileBrowserListener>();
        this.fileItems = new ArrayList<FileItem>();
        this.navigationHistory = new Stack<String>();
        this.isCallbackThreaded = false;
        this.callbackPopupMessage = type == RamFileBrowserType.SAVE ? Strings.POPUP_SAVING : Strings.POPUP_LOADING;

        if (defaultFile != null) {
            if (defaultFile.isDirectory()) {
                currentFolder = defaultFile;
            } else {
                currentFolder = defaultFile.getParentFile();
            }
        }
        if (currentFolder == null) {
            currentFolder = new File(DEFAULT_FOLDER).getAbsoluteFile();
        }
        if (browsingRoot != null) {
            if (browsingRoot.isDirectory() && browsingRoot.canRead()) {
                this.browsingRootDirectory = browsingRoot;
            } else {
                this.browsingRootDirectory = browsingRoot.getParentFile();
            }
        }

        // GUI
        setVisible(false);
        setNoFill(false);
        setNoStroke(true);
        setFillColor(Colors.BROWSER_BACKGROUND_COLOR);
        setBufferSize(Cardinal.NORTH, PADDING);
        setBufferSize(Cardinal.SOUTH, PADDING);
        setBufferSize(Cardinal.WEST, BIG_PADDING);
        setBufferSize(Cardinal.EAST, BIG_PADDING);
        setLayout(new VerticalLayout());

        headerContainer = new RamRectangleComponent(new HeaderLayout());

        backButton = createButton("\u25C4", ACTION_BACK, Colors.BROWSER_BUTTON_COLOR,
                Colors.BROWSER_BUTTON_COLOR_DISABLED);
        headerContainer.addChild(backButton);

        upButton = createButton("\u25B2", ACTION_UP, Colors.BROWSER_BUTTON_COLOR, Colors.BROWSER_BUTTON_COLOR_DISABLED);
        headerContainer.addChild(upButton);

        RamTextComponent currentFolderLabelText = new RamTextComponent();
        currentFolderLabelText.setFont(Fonts.BROWSER_SECONDARY_FONT);

        currentFolderLabel = new RamTextFieldComponent(currentFolderLabelText, false);
        headerContainer.addChild(currentFolderLabel);

        if (!hasBrowsingRoot()) {
            // TODO make it swag
            otherButton = createButton("...", ACTION_OTHER, Colors.BROWSER_BUTTON_COLOR,
                    Colors.BROWSER_BUTTON_COLOR_DISABLED);
            headerContainer.addChild(otherButton);
        }

        if (type != RamFileBrowserType.LOAD) {
            newFolderButton = createButton("\u253C", ACTION_NEW_FOLDER, Colors.BROWSER_BUTTON_COLOR,
                    Colors.BROWSER_BUTTON_COLOR_DISABLED);
            headerContainer.addChild(newFolderButton);
        }

        if (hasBrowsingRoot()) {
            homeButton = createButton("\u2302", ACTION_HOME, Colors.BROWSER_BUTTON_COLOR,
                    Colors.BROWSER_BUTTON_COLOR_DISABLED);
            headerContainer.addChild(homeButton);
        } else if (type == RamFileBrowserType.LOAD) {
            libButton = createButton(new RamImageComponent(Icons.ICON_LIBRARY, Colors.BROWSER_ICON_COLOR), 
                    ACTION_LIBRARY, Colors.BROWSER_BUTTON_COLOR, Colors.BROWSER_BUTTON_COLOR_DISABLED);
            headerContainer.addChild(libButton);
        }

        RamTextComponent searchFieldText = new RamTextComponent();
        searchFieldText.setFont(Fonts.BROWSER_PRIMARY_FONT);
        searchFieldText.setPlaceholderFont(Fonts.BROWSER_SECONDARY_FONT);
        searchFieldText.setPlaceholderText(Strings.PH_SEARCH);

        searchField = new RamTextFieldComponent(searchFieldText);
        searchField.registerListener(this);
        searchField.setKeyboardPosition(KeyboardPosition.TOP);
        searchField.setWidthLocal(SEARCH_FIELD_WIDTH);
        searchField.setNoFill(false);
        searchField.setFillColor(Colors.BROWSER_FOREGROUND_COLOR);
        headerContainer.addChild(searchField);

        cancelButton = createButton("\u2715", ACTION_CLOSE, Colors.BROWSER_CANCEL_BUTTON_COLOR,
                Colors.BROWSER_CANCEL_BUTTON_COLOR_DISABLED);
        headerContainer.addChild(cancelButton);

        footerContainer = new RamRectangleComponent(new FooterLayout());

        if (type == RamFileBrowserType.SAVE) {

            filenameLabel = new RamTextComponent(Strings.BROWSER_FILE_NAME_LABEL);
            filenameLabel.setFont(Fonts.BROWSER_PRIMARY_FONT);
            footerContainer.addChild(filenameLabel);

            RamTextComponent filenameFieldText = new RamTextComponent();
            filenameFieldText.setFont(Fonts.BROWSER_PRIMARY_FONT);
            filenameFieldText.setPlaceholderFont(Fonts.BROWSER_SECONDARY_FONT);
            filenameFieldText.setPlaceholderText(Strings.PH_ENTER_FILE_NAME);

            filenameField = new RamTextFieldComponent(filenameFieldText);
            filenameField.registerListener(this);
            filenameField.setKeyboardPosition(KeyboardPosition.BOTTOM);
            filenameField.setNoFill(false);
            filenameField.setFillColor(Colors.BROWSER_FOREGROUND_COLOR);
            footerContainer.addChild(filenameField);
        }

        validateButton = createButton("", ACTION_VALIDATE, Colors.BROWSER_VALIDATE_BUTTON_COLOR,
                Colors.BROWSER_VALIDATE_BUTTON_COLOR_DISABLED);
        footerContainer.addChild(validateButton);

        final float width = COLUMNS_COUNT * FILE_ITEM_WIDTH + (COLUMNS_COUNT + 1) * PADDING;
        final float height = FILES_CONTAINER_HEIGHT;
        middleContainer = new RamScrollComponent(width, height);
        middleContainer.setNoFill(false);
        middleContainer.setFillColor(Colors.BROWSER_FOREGROUND_COLOR);
        middleContainer.setBuffers(PADDING);
        middleContainer.setChildClip(width, height);
        middleContainer.setMouseWheelSensitivity(MOUSE_WHEEL_SENSITIVITY);
        middleContainer.setMinimumDragDistance(MINIMUM_DRAG_DISTANCE);

        RamRectangleComponent container = new RamRectangleComponent(new VerticalLayout(PADDING));
        container.addChild(headerContainer);
        container.addChild(middleContainer);
        container.addChild(footerContainer);
        addChild(container);

        // Events
        setDefaultMT4JGestureActions();
        addGestureListener(DragProcessor.class, new InertiaDragAction());
        for (AbstractComponentProcessor processor : getInputProcessors()) {
            processor.setBubbledEventsEnabled(true);
        }

        // Initialization
        isInitialized = true;
        changeCurrentFolder(currentFolder);

        if (type == RamFileBrowserType.SAVE && defaultFile != null && !defaultFile.isDirectory()) {
            filenameField.getTextComponent().setText(defaultFile.getName());
        }
    }

    /**
     * Creates a file browser button.
     * 
     * @param content The content of the button (a String or a RamImageComponent)
     * @param command The action command
     * @param backgroundColor The background color
     * @param disabledBackgroundColor The background color for the disabled button
     * @return The new button instance
     */
    private RamButton createButton(Object content, String command, MTColor backgroundColor,
            MTColor disabledBackgroundColor) {

        final float northBuffer = 5;
        RamButton btn;

        if (content instanceof RamImageComponent) {
            btn = new RamButton((RamImageComponent) content, 1);
            btn.setBufferSize(Cardinal.NORTH, 0);
        } else {
            btn = new RamButton(content.toString(), 1);
            btn.setBufferSize(Cardinal.NORTH, northBuffer);
        }

        btn.setNoFill(false);
        btn.setNoStroke(true);
        btn.setBackgroundColor(backgroundColor);
        btn.setDisabledBackgroundColor(disabledBackgroundColor);
        btn.setFont(Fonts.BROWSER_PRIMARY_FONT);
        btn.setDisabledFont(Fonts.BROWSER_SECONDARY_FONT);
        btn.addActionListener(this);
        btn.setActionCommand(command);
        return btn;
    }

    /**
     * Change the current folder and update the GUI.
     * 
     * @param folder The new folder
     */
    private void changeCurrentFolder(File folder) {
        changeCurrentFolder(folder, true);
    }

    /**
     * Change the current folder and update the GUI.
     * 
     * @param folder The new folder
     * @param addToHistory - whether we should add the new folder to the navigation history
     */
    private void changeCurrentFolder(File folder, boolean addToHistory) {

        // Check folder not null
        if (folder == null) {
            return;
        }

        final boolean isTheSameFolder = folder.equals(currentFolder);

        // Create filter
        FileFilter filter = new FileFilter() {

            // CHECKSTYLE:IGNORE ReturnCount FOR 1 LINES: Enhance function readability
            @Override
            public boolean accept(File file) {
                String search = searchField.getTextComponent().getText().toLowerCase().trim();
                if (isTheSameFolder && !search.isEmpty() && !file.getName().toLowerCase().contains(search)) {
                    return false;
                }
                if (file.isDirectory() && !file.isHidden()) {
                    return true;
                }
                if (type != RamFileBrowserType.FOLDER && file.isFile() && !file.isHidden()) {
                    if (extensionFilter.isEmpty()
                            || file.getName().toLowerCase().matches(".+\\.(" + extensionFilter + ")$")) {
                        return true;
                    }
                }
                return false;
            }
        };

        // Get files
        File[] files = folder.listFiles(filter);

        if (folder.canRead() && files != null) {

            if (files.length > 0) {

                filesContainer = new RamRectangleComponent(new GridLayout(COLUMNS_COUNT, PADDING));
                middleContainer.destroyAllChildren();
                middleContainer.setInnerView(filesContainer);
                fileItems.clear();

                for (final File file : files) {
                    FileItem item = new FileItem(file);
                    filesContainer.addChild(item, false);
                    fileItems.add(item);
                }

                filesContainer.updateLayout();

            } else {
                showFolderEmptyMessage();
            }

        } else {
            showFolderErrorMessage();
        }

        // Reset search field if the folder changes
        if (!isTheSameFolder) {
            searchField.unregisterListener(this);
            searchField.getTextComponent().clear();
            searchField.closeKeyboard();
            searchField.registerListener(this);
            if (addToHistory) {
                navigationHistory.push(currentFolder.getAbsolutePath());
            }
        }

        currentFolder = folder;
        selectedFileItem = null;
        saveOverrideFlag = false;

        // Update header
        currentFolderLabel.getTextComponent().setText(folder.getName() + File.separator);
        upButton.setEnabled(folder.getParent() != null && !isBlockedByBrowsingRoot());
        backButton.setEnabled(!navigationHistory.isEmpty());

        updateValidateButton();
    }

    /**
     * Called when a button of the file browser is clicked.
     * 
     * @param event The action event
     */
    @Override
    public void actionPerformed(ActionEvent event) {

        if (event.getActionCommand().equals(ACTION_UP)) {
            if (hasBrowsingRoot() && currentFolder.equals(browsingRootDirectory)) {
                return;
            }
            File parent = currentFolder.getParentFile();
            if (parent != null) {
                changeCurrentFolder(parent);
            }
        } else if (event.getActionCommand().equals(ACTION_BACK)) {
            File previous = new File(navigationHistory.pop());
            if (previous != null && previous.exists()) {
                changeCurrentFolder(previous, false);
            } else {
                changeCurrentFolder(currentFolder, false);
            }
        } else if (event.getActionCommand().equals(ACTION_HOME)) {
            changeCurrentFolder(browsingRootDirectory);
        } else if (event.getActionCommand().equals(ACTION_OTHER)) {

            if (otherSelector != null) {
                otherSelector.destroy();
            }

            List<String> availableOptions = new ArrayList<String>();

            // Create the list of available options using the existing enum options
            // plus the existing roots of the file system (since it is OS-specific).
            for (OtherSelectorOption option : OtherSelectorOption.values()) {
                availableOptions.add(option.name);
            }

            for (File root : File.listRoots()) {
                availableOptions.add(root.toString());
            }

            otherSelector = new RamSelectorComponent<String>(availableOptions);
            otherSelector.registerListener(new OtherSelectorListener());
            RamApp.getApplication().getCanvas().addChild(otherSelector);
            otherSelector.setPositionRelativeToParent(new Vector3D(otherButton.getX(), otherButton.getY()));

        } else if (event.getActionCommand().equals(ACTION_LIBRARY)) {

            changeCurrentFolder(new File(Constants.DIRECTORY_LIBRARIES));

        } else if (event.getActionCommand().equals(ACTION_NEW_FOLDER)) {

            showNewFolderForm();
            newFolderNameField.showKeyboard(new DefaultRamKeyboardListener() {
                @Override
                public boolean verifyKeyboardDismissed() {
                    String newFolderName = newFolderNameField.getTextComponent().getText().trim();
                    return isFolderNameValid(newFolderName);
                }

                @Override
                public void keyboardClosed(RamKeyboard ramKeyboard) {
                    createNewFolder();
                }
            });

        } else if (event.getActionCommand().equals(ACTION_NEW_FOLDER_VALIDATE)) {
            createNewFolder();
        } else if (event.getActionCommand().equals(ACTION_NEW_FOLDER_CANCEL)) {
            changeCurrentFolder(currentFolder);
            // close the keyboard
            newFolderNameField.closeKeyboard();
        } else if (event.getActionCommand().equals(ACTION_CLOSE)) {
            close(true);
        } else if (event.getActionCommand().equals(ACTION_VALIDATE)) {

            if (type != RamFileBrowserType.FOLDER && hasDirectorySelectedItem()) {
                changeCurrentFolder(selectedFileItem.getFile());
            } else if (type != RamFileBrowserType.SAVE) {
                if (selectedFileItem != null) {
                    openFile(selectedFileItem.getFile());
                } else {
                    openFile(currentFolder);
                }
            } else {
                openFile(getSaveFile());
            }
        }
    }

    /**
     * Creates a new folder. First checks if name is valid. If so create the new folder, move to it and close the
     * keyboard.
     */
    protected void createNewFolder() {
        String newFolderName = newFolderNameField.getTextComponent().getText().trim();
        if (isFolderNameValid(newFolderName)) {
            File newFolder = new File(currentFolder, newFolderName);
            newFolder.mkdirs();
            changeCurrentFolder(newFolder);
            newFolderNameField.closeKeyboard();
        }
    }

    /**
     * Checks if the given folder name is valid and if a folder with that name doesn't already exist.
     * 
     * @param newFolderName the name of the folder we want to test
     * @return false if name is invalid or folder already exists, true otherwise
     */
    protected boolean isFolderNameValid(String newFolderName) {
        File newFolder = new File(currentFolder, newFolderName);
        if (newFolderName.length() > 0 && !newFolder.exists() && currentFolder.canWrite()) {
            return newFolderName.matches(FOLDER_NAME_REGEXP);
        }
        return false;
    }

    /**
     * Called when a text component is modified.
     * 
     * @param component The text component
     */
    @Override
    public void textModified(RamTextComponent component) {
        if (component == searchField.getTextComponent()) {
            changeCurrentFolder(currentFolder);
        } else if (component == filenameField.getTextComponent()) {
            if (selectedFileItem != null) {
                selectedFileItem.unselectItem();
                selectedFileItem = null;
            }
            saveOverrideFlag = false;
            updateValidateButton();
        }
    }

    /**
     * Called when a file item is tapped.
     * 
     * @param item The tapped file item
     */
    private void fileItemTapped(FileItem item) {

        File file = item.getFile();

        if (selectedFileItem != null) {
            selectedFileItem.unselectItem();
        }

        if (type == RamFileBrowserType.SAVE && file.isFile()) {
            filenameField.getTextComponent().setText(file.getName());
        }

        item.selectItem();
        selectedFileItem = item;
        saveOverrideFlag = false;
        updateValidateButton();
    }

    /**
     * Called when a file item is double tapped.
     * 
     * @param item The double tapped file item
     */
    private void fileItemDoubleTapped(FileItem item) {
        final File file = item.getFile();
        if (file.isDirectory()) {
            // FIX MT4J BUG: Change the folder later because it will destroy the file item
            RamApp.getApplication().invokeLater(new Runnable() {
                @Override
                public void run() {
                    changeCurrentFolder(file);
                }
            });
        } else {
            openFile(file);
        }
    }

    /**
     * Update the validate button GUI.
     */
    private void updateValidateButton() {

        // Enabled
        if (type == RamFileBrowserType.LOAD) {
            boolean enabled = selectedFileItem != null;
            validateButton.setEnabled(enabled);
        } else if (type == RamFileBrowserType.FOLDER) {
            boolean enabled = selectedFileItem == null || hasDirectorySelectedItem();
            validateButton.setEnabled(enabled);
        } else if (type == RamFileBrowserType.SAVE) {
            File targetFile = getSaveFile();
            boolean enabled = targetFile != null;
            validateButton.setEnabled(enabled);
        }

        // Text and color
        if (saveOverrideFlag) {
            validateButton.setText(Strings.BROWSER_OVERRIDE);
            validateButton.setBackgroundColor(Colors.BROWSER_OVERRIDE_BUTTON_COLOR);
            validateButton.setDisabledBackgroundColor(Colors.BROWSER_OVERRIDE_BUTTON_COLOR_DISABLED);
        } else {
            String text;
            switch (type) {
                case LOAD:
                    text = Strings.BROWSER_LOAD;
                    break;
                case SAVE:
                    text = Strings.BROWSER_SAVE;
                    break;
                case FOLDER:
                default:
                    if (hasDirectorySelectedItem()) {
                        text = Strings.BROWSER_CHOOSE_SELECTED;
                    } else {
                        text = Strings.BROWSER_CHOOSE_CURRENT;
                    }
            }
            
            if (type != RamFileBrowserType.FOLDER && hasDirectorySelectedItem()) {
                text = Strings.BROWSER_OPEN;
            }
            validateButton.setText(text);
            validateButton.setBackgroundColor(Colors.BROWSER_VALIDATE_BUTTON_COLOR);
            validateButton.setDisabledBackgroundColor(Colors.BROWSER_VALIDATE_BUTTON_COLOR_DISABLED);
        }
    }

    /**
     * Returns whether an item is selected and is a directory.
     * 
     * @return true or false
     */
    private boolean hasDirectorySelectedItem() {
        return selectedFileItem != null && selectedFileItem.getFile().isDirectory();
    }

    /**
     * Shows the new folder form in the middle container.
     */
    private void showNewFolderForm() {

        RamRectangleComponent container = new RamRectangleComponent(new HorizontalLayoutVerticallyCentered(PADDING));

        RamTextComponent text = new RamTextComponent();
        text.setFont(Fonts.BROWSER_PRIMARY_FONT);
        text.setPlaceholderFont(Fonts.BROWSER_SECONDARY_FONT);
        text.setPlaceholderText(Strings.PH_ENTER_FOLDER_NAME);

        newFolderNameField = new RamTextFieldComponent(text);
        newFolderNameField.setKeyboardPosition(KeyboardPosition.TOP);
        newFolderNameField.setNoFill(false);
        newFolderNameField.setFillColor(Colors.BROWSER_FIELD_FOREGROUND_COLOR);
        newFolderNameField.setWidthLocal(NEW_FOLDER_NAME_FIELD_WIDTH);
        container.addChild(newFolderNameField);

        RamButton validate = createButton("\u2713", ACTION_NEW_FOLDER_VALIDATE, Colors.BROWSER_VALIDATE_BUTTON_COLOR,
                Colors.BROWSER_VALIDATE_BUTTON_COLOR_DISABLED);
        container.addChild(validate);

        RamButton cancel = createButton("\u2715", ACTION_NEW_FOLDER_CANCEL, Colors.BROWSER_CANCEL_BUTTON_COLOR,
                Colors.BROWSER_CANCEL_BUTTON_COLOR_DISABLED);
        container.addChild(cancel);

        setViewInMiddleContainer(container);
    }

    /**
     * Shows a folder empty message in the middle container.
     */
    private void showFolderEmptyMessage() {
        RamTextComponent text = new RamTextComponent(Fonts.BROWSER_FOLDER_MESSAGE_FONT, Strings.BROWSER_EMPTY_FOLDER);
        setViewInMiddleContainer(text);
    }

    /**
     * Shows a folder error message in the middle container.
     */
    private void showFolderErrorMessage() {
        RamTextComponent text = new RamTextComponent(Fonts.BROWSER_FOLDER_ERROR_MESSAGE_FONT, 
                Strings.BROWSER_FOLDER_OPEN_ERROR);
        setViewInMiddleContainer(text);
    }

    /**
     * Adds the given component in the middle container.
     * 
     * @param component The component
     */
    private void setViewInMiddleContainer(RamRectangleComponent component) {

        if (filesContainer != null) {
            filesContainer.destroy();
            filesContainer = null;
        }
        middleContainer.removeAllChildren();
        middleContainer.addChild(component);

        final float x = middleContainer.getCenterPointLocal().getX() - component.getWidth() / 2;
        final float y = middleContainer.getCenterPointLocal().getY() - component.getHeight() / 2;
        component.setPositionRelativeToParent(new Vector3D(x, y));
    }

    /**
     * Called when the user has selected a file in the file browser.
     * 
     * @param file The selected file
     */
    private void openFile(final File file) {

        // Check override flag
        if (type == RamFileBrowserType.SAVE && file.exists() && !saveOverrideFlag) {
            saveOverrideFlag = true;
            updateValidateButton();
            return;
        }

        // Execute the callback on a new thread or not depending on the user choice
        if (isCallbackThreaded) {
            final RamPopup popup = new RamPopup(callbackPopupMessage, false);
            RamApp.getActiveScene().displayPopup(popup);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (RamFileBrowserListener l : listeners) {
                        l.fileSelected(file, RamFileBrowser.this);
                    }
                    RamApp.getApplication().invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            popup.destroy();
                        }
                    });
                }
            }, "File Browser callback").start();
        } else {
            for (RamFileBrowserListener l : listeners) {
                l.fileSelected(file, RamFileBrowser.this);
            }
        }

        close(!isCallbackThreaded);
    }

    /**
     * Return the target save file depending on the content of the filename field.
     * 
     * @return The target save file, or null if empty/error
     */
    private File getSaveFile() {
        if (filenameField == null) {
            return null;
        }
        String filename = filenameField.getTextComponent().getText().trim();
        if (filename.isEmpty()) {
            return null;
        }
        if (!filename.endsWith("." + extensionFilter)) {
            filename += "." + extensionFilter;
        }
        return new File(currentFolder, filename);
    }

    /**
     * Display the file browser at the center of the screen.
     */
    public void display() {

        if (currentFileBrowser != null) {
            currentFileBrowser.close(false);
        }

        RamApp app = RamApp.getApplication();
        float x = (app.getWidth() - getWidth()) / 2;
        float y = (app.getHeight() - getHeight()) / 2;
        setPositionGlobal(new Vector3D(x, y));
        app.getCurrentScene().getCanvas().addChild(this);
        setVisible(true);
        currentFileBrowser = this;
    }

    /**
     * Close and destroy the file browser.
     * 
     * @param animate true to animate the close, false otherwise
     */
    public void close(boolean animate) {

        if (!animate) {
            setVisible(false);
            destroy();
            return;
        }

        // Animate closing
        final float from = this.getWidthXY(TransformSpace.RELATIVE_TO_PARENT);
        final float to = 1f;
        final float duration = 300f;
        final float endAcceleration = 0.2f;
        final float startAcceleration = 0.5f;
        final int loopCount = 1;

        IAnimation kbCloseAnimation = new Animation("File Browser Fade", new MultiPurposeInterpolator(from, to,
                duration, endAcceleration, startAcceleration, loopCount), this);

        kbCloseAnimation.addAnimationListener(new IAnimationListener() {
            @Override
            public void processAnimationEvent(AnimationEvent ae) {
                switch (ae.getId()) {
                    case AnimationEvent.ANIMATION_STARTED:
                    case AnimationEvent.ANIMATION_UPDATED:
                        float value = ae.getAnimation().getValue();
                        MathUtils.setWidthRelativeToParent(RamFileBrowser.this, value);
                        break;
                    case AnimationEvent.ANIMATION_ENDED:
                        setVisible(false);
                        destroy();
                        break;
                    default:
                        break;
                }
            }
        });
        kbCloseAnimation.start();
    }

    @Override
    public void destroy() {
        if (otherSelector != null) {
            otherSelector.destroy();
        }
        currentFileBrowser = null;
        super.destroy();
    }

    /**
     * Add a listener to the file browser.
     * 
     * @param listener The listener
     */
    public void addFileBrowserListener(RamFileBrowserListener listener) {
        listeners.add(listener);
    }

    /**
     * Remove a listener from the file browser.
     * 
     * @param listener The listener
     */
    public void removeFileBrowserListener(RamFileBrowserListener listener) {
        listeners.remove(listener);
    }

    /**
     * Sets whether the file browser execute the callback in a separate thread or not.
     * 
     * @param callbackThreaded true or false
     */
    public void setCallbackThreaded(boolean callbackThreaded) {
        this.isCallbackThreaded = callbackThreaded;
    }

    /**
     * Sets the message of the callback waiting popup.
     * 
     * @param message The message
     */
    public void setCallbackPopupMessage(String message) {
        this.callbackPopupMessage = message;
    }

    /**
     * Returns the type of the file browser.
     * 
     * @return The type
     */
    public RamFileBrowserType getType() {
        return type;
    }

    /**
     * Check if the browser is to be blocked to a given root folder.
     * 
     * @return true if the user defined a root for browsing.
     */
    private boolean hasBrowsingRoot() {
        return browsingRootDirectory != null && browsingRootDirectory.isDirectory() && browsingRootDirectory.canRead();
    }

    /**
     * Check if the user can go up the hierarchy or if he's at the custom root folder.
     * 
     * @return true if we can't go up in the hierarchy because of the custom root defined.
     */
    private boolean isBlockedByBrowsingRoot() {
        if (hasBrowsingRoot()) {
            return currentFolder.equals(browsingRootDirectory);
        }
        return false;
    }

    /**
     * File item class. Represents a file item view in the file browser.
     */
    private class FileItem extends RamRectangleComponent {

        private static final float NORTH_PADDING = 9;
        private static final float INNER_PADDING = 2;

        /**
         * Handler for tap events on the file item.
         */
        private class TapHandler extends BaseHandler implements ITapListener {

            @Override
            public boolean processTapEvent(TapEvent tapEvent) {
                if (tapEvent.isTapped()) {
                    RamFileBrowser.this.fileItemTapped(FileItem.this);
                } else if (tapEvent.isDoubleTap()) {
                    RamFileBrowser.this.fileItemDoubleTapped(FileItem.this);
                }
                return true;
            }
        }

        private File file;

        /**
         * Constructs a file item with the given file object.
         * 
         * @param file The file
         */
        public FileItem(File file) {

            this.file = file;

            // GUI
            setSizeLocal(FILE_ITEM_WIDTH, FILE_ITEM_HEIGHT);
            setBufferSize(Cardinal.NORTH, NORTH_PADDING);
            setChildClip(FILE_ITEM_WIDTH - PADDING, FILE_ITEM_HEIGHT);
            setNoFill(false);
            setNoStroke(true);
            unselectItem();

            RamImageComponent img;

            if (file.isDirectory()) {
                img = new RamImageComponent(Icons.ICON_FOLDER, Colors.BROWSER_ICON_COLOR);
            } else {
                img = new RamImageComponent(Icons.ICON_FILE, Colors.BROWSER_ICON_COLOR);
            }

            addChild(img);

            RamTextComponent text = new RamTextComponent(file.getName());
            text.setFont(Fonts.BROWSER_SMALL_FONT);
            text.setAutoMaximizes(false);
            addChild(text);

            VerticalLayout layout = new VerticalLayout(INNER_PADDING, HorizontalAlignment.CENTER);
            layout.setForceToKeepSize(true);
            layout.setVerticalAlignment(VerticalAlignment.MIDDLE);
            setLayout(layout);

            // Events
            addGestureListener(TapProcessor.class, new TapHandler());
        }

        /**
         * Select the file item.
         */
        public void selectItem() {
            setFillColor(Colors.BROWSER_SELECTED_BACKGROUND_COLOR);
        }

        /**
         * Unselect the file item.
         */
        public void unselectItem() {
            if (file.isDirectory()) {
                setFillColor(Colors.BROWSER_FOLDER_BACKGROUND_COLOR);
            } else {
                setFillColor(Colors.BROWSER_FILE_BACKGROUND_COLOR);
            }
        }

        @Override
        protected void registerInputProcessors() {

            AbstractComponentProcessor tapProcessor = new TapProcessor(RamApp.getApplication(),
                    Constants.TAP_MAX_FINGER_UP, true, Constants.TAP_DOUBLE_TAP_LONG_TIME);

            tapProcessor.setBubbledEventsEnabled(true);
            registerInputProcessor(tapProcessor);
        }

        /**
         * Returns the associated file.
         * 
         * @return The associated file
         */
        public File getFile() {
            return file;
        }
    }

    /**
     * Layout class of the header.
     */
    private class HeaderLayout extends HorizontalLayoutVerticallyCentered {

        /**
         * Constructs a layout with the global padding.
         */
        public HeaderLayout() {
            super(PADDING);
        }

        @Override
        public void layout(RamRectangleComponent component, LayoutUpdatePhase updatePhase) {

            if (isInitialized) {

                // Square buttons
                for (MTComponent c : headerContainer.getChildren()) {
                    if (c instanceof RamButton) {
                        RamButton b = (RamButton) c;
                        b.setMinimumHeight(headerContainer.getHeight());
                        b.setMinimumWidth(b.getHeight());
                    }
                }

                // Search field height
                searchField.setHeightLocal(component.getHeight());

                // Current folder label size
                float labelSize = middleContainer.getWidth();
                for (MTComponent c : headerContainer.getChildren()) {
                    if (c instanceof RamRectangleComponent && c != currentFolderLabel) {
                        labelSize -= ((RamRectangleComponent) c).getWidth();
                    }
                }

                labelSize -= (headerContainer.getChildCount() - 1) * PADDING;

                currentFolderLabel.setMinimumWidth(labelSize);
                currentFolderLabel.setWidthLocal(labelSize);
            }

            super.layout(component, updatePhase);
        }
    }

    /**
     * Layout class of the footer.
     */
    private class FooterLayout extends HorizontalLayoutRightAligned {

        /**
         * Constructs a layout with the global padding.
         */
        public FooterLayout() {
            super(PADDING);
        }

        @Override
        public void layout(RamRectangleComponent component, LayoutUpdatePhase updatePhase) {

            if (isInitialized) {

                // Square buttons
                for (MTComponent c : footerContainer.getChildren()) {
                    if (c instanceof RamButton) {
                        RamButton b = (RamButton) c;
                        b.setMinimumHeight(footerContainer.getHeight());
                        b.setMinimumWidth(b.getHeight());
                    }
                }

                // Filename field size
                if (filenameField != null) {

                    float fieldWidth = middleContainer.getWidth();
                    for (MTComponent c : footerContainer.getChildren()) {
                        if (c instanceof RamRectangleComponent && c != filenameField) {
                            fieldWidth -= ((RamRectangleComponent) c).getWidth();
                        }
                    }

                    fieldWidth -= (footerContainer.getChildCount() - 1) * PADDING;

                    filenameField.setMinimumWidth(fieldWidth);
                    filenameField.setSizeLocal(fieldWidth, component.getHeight());
                }
            }

            super.layout(component, updatePhase);
        }
    }

    /**
     * Listener class for the other selector.
     */
    private class OtherSelectorListener extends AbstractDefaultRamSelectorListener<String> {

        @Override
        public void elementSelected(RamSelectorComponent<String> selector, String element) {

            File folder = null;
            try {
                OtherSelectorOption option = OtherSelectorOption.valueOf(element.toUpperCase());
                folder = new File(option.path);
            } catch (IllegalArgumentException e) {
                folder = new File(element);
            }

            changeCurrentFolder(folder);
            selector.destroy();
            otherSelector = null;
        }
    }

}
