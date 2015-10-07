package ca.mcgill.sel.ram.ui;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.swing.JFrame;

import org.eclipse.emf.ecore.EObject;
import org.mt4j.MTApplication;
import org.mt4j.components.MTCanvas;
import org.mt4j.input.DesktopInputManager;
import org.mt4j.input.InputManager;
import org.mt4j.input.inputSources.MouseInputSource;
import org.mt4j.input.inputSources.Tuio2DCursorInputSource;
import org.mt4j.input.inputSources.Tuio2dObjectInputSource;
import org.mt4j.input.inputSources.Win7NativeTouchSource;
import org.mt4j.sceneManagement.AbstractScene;
import org.mt4j.sceneManagement.Iscene;
import org.mt4j.util.MT4jSettings;

import ca.mcgill.sel.commons.ResourceUtil;
import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.core.COREConcern;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.ui.components.ConfirmPopup;
import ca.mcgill.sel.ram.ui.components.RamPopup.PopupType;
import ca.mcgill.sel.ram.ui.components.browser.interfaces.AspectFileBrowserListener;
import ca.mcgill.sel.ram.ui.events.RamKeyboardInputSource;
import ca.mcgill.sel.ram.ui.scenes.DisplayAspectScene;
import ca.mcgill.sel.ram.ui.scenes.DisplayConcernEditScene;
import ca.mcgill.sel.ram.ui.scenes.RamAbstractScene;
import ca.mcgill.sel.ram.ui.scenes.SelectAspectScene;
import ca.mcgill.sel.ram.ui.scenes.handler.IConcernEditSceneHandler;
import ca.mcgill.sel.ram.ui.scenes.handler.impl.ConcernEditSceneHandler;
import ca.mcgill.sel.ram.ui.utils.BasicActionsUtils;
import ca.mcgill.sel.ram.ui.utils.GraphicalUpdater;
import ca.mcgill.sel.ram.ui.utils.LoggerUtils;
import ca.mcgill.sel.ram.ui.utils.OSXAdapter;
import ca.mcgill.sel.ram.ui.utils.ResourceUtils;
import ca.mcgill.sel.ram.ui.utils.ResourceUtils.OperatingSystem;
import ca.mcgill.sel.ram.ui.utils.Strings;
import ca.mcgill.sel.ram.ui.views.handler.HandlerFactory;
import ca.mcgill.sel.ram.ui.views.handler.IDisplaySceneHandler;
import ca.mcgill.sel.ram.ui.views.structural.StructuralDiagramView;
import processing.event.KeyEvent;

/**
 * Contains the current {@link MTApplication} instance. This can be retrieved using the getApplication() method for easy
 * of use. Since creating an application is asynchronous there is no way to create a new application and return it on
 * demand, so it is possible for the current application to be null.
 *
 * @author vbonnet
 * @author eyildirim
 * @author mschoettle
 * @author nishanth
 * @author omar
 */
public class RamApp extends MTApplication {

    /**
     * The different drawing modes available for RAM applications.
     *
     * @author vbonnet
     */
    public enum DisplayMode {
        /**
         * Pretty mode, take some extra time to make the display prettier.
         */
        PRETTY,
        /**
         * Be as efficient as possible, don't bother rearranging things.
         */
        FAST
    }

    /**
     * Default id for default purposes.
     */
    private static final long serialVersionUID = 1L;

    private static RamApp instance;

    private static Runnable onLoad;

    private final Set<Aspect> loadedAspects;

    private final Map<Aspect, GraphicalUpdater> graphicalUpdaters;

    private DisplayMode displayMode;

    private AbstractScene backgroundScene;

    /**
     * Creates a new RAM application. Would be private except that MTApplication requires it to be public.
     */
    public RamApp() {
        displayMode = DisplayMode.PRETTY;
        loadedAspects = new HashSet<Aspect>();
        graphicalUpdaters = new HashMap<Aspect, GraphicalUpdater>();
        // create our own desktop manager
        final DesktopInputManager desktopManager = new DesktopInputManager(this, false);
        // setting it now will stop a new one from being created
        setInputManager(desktopManager);
    }

    /**
     * Returns the current active {@link DisplayAspectScene} if there is an active one.
     *
     * @return the current active {@link DisplayAspectScene}, null if there is none active
     */
    public static DisplayAspectScene getActiveAspectScene() {
        final Iscene scene = getApplication().getCurrentScene();
        if (scene instanceof DisplayAspectScene) {
            return (DisplayAspectScene) scene;
        } else {
            return null;
        }
    }

    /**
     * Returns the current active {@link DisplayConcernEditScene} if there is an active one.
     *
     * @return the current active {@link DisplayConcernEditScene}, null if there is none active
     */
    public static DisplayConcernEditScene getActiveConcernEditScene() {
        final Iscene scene = getApplication().getCurrentScene();
        if (scene instanceof DisplayConcernEditScene) {
            return (DisplayConcernEditScene) scene;
        } else {
            return null;
        }
    }

    /**
     * Returns the current active {@link RamAbstractScene}.
     *
     * @return the current active {@link RamAbstractScene}
     */
    public static RamAbstractScene<?> getActiveScene() {
        final RamAbstractScene<?> scene = (RamAbstractScene<?>) getApplication().getCurrentScene();
        return scene;
    }

    /**
     * Returns the instance of RamApp running.
     *
     * @return the current MTApplication instance. CAN be null if no application has finished initializing.
     */
    public static RamApp getApplication() {
        return RamApp.instance;
    }

    /**
     * Returns the display mode of the instance.
     *
     * @return The display mode for the current application.
     */
    public static DisplayMode getDisplayMode() {
        return instance.displayMode;
    }

    /**
     * Initializes the application.
     */
    public static void initialize() {
        MTApplication.initialize();
    }

    /**
     * Initializes the application. This should be the FIRST mt4j method called and should only be called once.
     *
     * @param callback The callback to invoke when the application is done loading.
     */
    public static void initialize(final Runnable callback) {
        onLoad = callback;
        // This needs to be the FIRST thing called because mt4j decrees it so
        // Also it should be called from MTApplication, so redirect.
        MTApplication.initialize();
    }

    /**
     * Sets the display mode for the currently running application.
     *
     * @param mode The mode to set.
     */
    public static void setDisplayMode(final DisplayMode mode) {
        instance.displayMode = mode;
    }

    /**
     * Returns the active structural view of the RamApp instance. Returns null if aspect scene is not the active scene.
     *
     * @return The structural view of the active {@link DisplayAspectScene}, null if the current scene is of another
     *         type.
     */
    public static StructuralDiagramView getActiveStructuralView() {
        return getApplication().getStructuralView();
    }

    /**
     * Function called to close the aspect scene. Destroys the scene, unregisters the scene, and unloads the aspect.
     *
     * @param scene - The DisplayAspectScene which is passed to the function to be closed.
     */
    public void closeAspectScene(final DisplayAspectScene scene) {
        synchronized (loadedAspects) {
            if (scene == getCurrentScene()) {
                changeScene(backgroundScene);
            }

            Aspect aspect = scene.getAspect();

            // unregister the aspect from the the main ram app
            unregisterAspect(aspect);
            // destroy the scene manually (call to destroySceneAfterTransition has no effect since there is no
            // transition)
            scene.destroy();
            destroySceneAfterTransition(scene);
            // remove it
            removeScene(scene);
        }
    }

    /**
     * Returns the canvas of the current scene.
     *
     * @return The canvas of the currently loaded scene.
     */
    public MTCanvas getCanvas() {
        return getCurrentScene().getCanvas();
    }

    /**
     * The function loads the concern passed and registers the scene and changes it accordingly.
     *
     * @param concern - the concern to display
     */
    public void displayConcernFM(final COREConcern concern) {
        invokeLater(new Runnable() {
            @Override
            public void run() {
                ConcernEditSceneHandler handler = HandlerFactory.INSTANCE.getDisplayConcernFMSceneHandler();
                DisplayConcernEditScene scene = null;
                scene = new DisplayConcernEditScene(RamApp.this, concern);
                scene.setHandler(handler);
                addScene(scene);
                changeScene(scene);
            }
        });
    }

    /**
     * This function gets the scene of an aspect from loaded aspects but if that aspect has not been loaded before it
     * creates one scene with that aspect. If there is an error during initialization, null is returned, which the
     * caller should check for.
     *
     * @param aspect the aspect to load a scene for
     * @return the loaded {@link DisplayAspectScene}, null if there was an error during initialization
     */
    public DisplayAspectScene getExistingOrCreateAspectScene(Aspect aspect) {
        synchronized (loadedAspects) {
            registerAspect(aspect);
            String aspectSceneName = getSceneName(aspect);
            DisplayAspectScene scene = (DisplayAspectScene) getScene(aspectSceneName);

            if (scene == null) {
                IDisplaySceneHandler handler = HandlerFactory.INSTANCE.getDisplayAspectSceneHandler();
                try {
                    scene = new DisplayAspectScene(RamApp.this, aspect, aspectSceneName);
                    scene.setHandler(handler);
                    addScene(scene);
                    // CHECKSTYLE:IGNORE IllegalCatch: There could be many different exceptions during initialization.
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return scene;
        }

    }

    /**
     * Creates a unique name for a scene which displays an aspect.
     *
     * @param aspect the aspect that the scene should display
     * @return the unique name of the aspect scene
     */
    private static String getSceneName(Aspect aspect) {
        String aspectString = aspect.toString();
        // The string contains the name of the aspect. Since an aspect could be renamed,
        // the scene name has to be independent of the name of an aspect.
        // Just strip that part of and use the identify of the object.
        aspectString = aspectString.substring(0, aspectString.indexOf(' '));

        return aspectString;
    }

    /**
     * Returns a set of all loaded aspects.
     *
     * @return a {@link Set} of loaded {@link Aspect}s
     */
    public Set<Aspect> getLoadedAspects() {
        return loadedAspects;
    }

    /**
     * Gets the GraphicalUpdater linked to the aspect parameter.
     *
     * @param aspect The aspect
     * @return The GraphicalUpdater linked to the aspect.
     */
    public GraphicalUpdater getGraphicalUpdaterForAspect(Aspect aspect) {
        return graphicalUpdaters.get(aspect);
    }

    /**
     * Function used to get the structural view from the current scene. Will return null if the current scene is not a
     * DisplayAspectScene
     *
     * @return StructuralDiagramView - The structural diagram view of the current aspect scene.
     */
    private StructuralDiagramView getStructuralView() {
        final Iscene scene = getCurrentScene();
        if (scene != null && scene instanceof DisplayAspectScene) {
            final DisplayAspectScene aspectscene = (DisplayAspectScene) scene;
            return aspectscene.getStructuralDiagramView();
        }
        return null;
    }

    @Override
    protected void handleKeyEvent(KeyEvent event) {
        // handle the ESC key specially
        // to show a confirm popup
        if (event.getAction() == KeyEvent.PRESS && event.getKey() == java.awt.event.KeyEvent.VK_ESCAPE) {
            handleQuitRequest();
            return;
        }

        super.handleKeyEvent(event);
    }

    /**
     * Loads an aspect and changes to the scene of that aspect. If a scene for the given aspect does not exist yet it
     * will be created.
     *
     * @param aspect the aspect to display
     */
    public void loadAspect(final Aspect aspect) {
        loadAspect(aspect, null);
    }
    
    /**
     * Loads an aspect and changes to the scene of that aspect. If a scene for the given aspect does not exist yet it
     * will be created.
     *
     * @param aspect - the aspect to display
     * @param handler - the handler to set for the scene. If null, handler of the scene will not be modified.
     */
    public void loadAspect(final Aspect aspect, final IDisplaySceneHandler handler) {
        
        invokeLater(new Runnable() {
            @Override
            public void run() {
                DisplayAspectScene scene = getExistingOrCreateAspectScene(aspect);
                if (handler != null) {
                    scene.setHandler(handler);
                }
                if (scene != null) {
                    changeScene(scene);
                } else {
                    getActiveScene().displayPopup(Strings.popupAspectMalformed(aspect.getName()), PopupType.ERROR);
                }
            }
        });
    }

    /**
     * Creates a scene (but doesn't add it to the application) so that it can be called upon at a later time when
     * someone tries to load that scene.
     *
     * @param aspect The aspect to register
     */
    public void registerAspect(final Aspect aspect) {
        synchronized (loadedAspects) {
            loadedAspects.add(aspect);
            if (!graphicalUpdaters.containsKey(aspect)) {
                graphicalUpdaters.put(aspect, new GraphicalUpdater());
            }
        }
    }

    /**
     * Function default to register the default input sources.
     *
     * @param inputManager - The inputManager object.
     */
    private void registerDefaultInputSources(final InputManager inputManager) {
        /*
         * This code is pretty much the same as DesktopInputManger#registerDefaultInputSources() except with some
         * comments or useless code removed. It is copied here because the keyboard input is bad and this is sadly the
         * easiest way I found to fix it.
         */
        // boolean enableMultiMouse = false;
        final Properties properties = new Properties();

        try {
            final FileInputStream fi =
                    new FileInputStream(MT4jSettings.getInstance().getDefaultSettingsPath() + "Settings.txt");
            properties.load(fi);
            // enableMultiMouse = Boolean.parseBoolean(properties.getProperty("MultiMiceEnabled", "false").trim());
            // CHECKSTYLE:IGNORE IllegalCatch: Many exceptions can occur and we don't want to crash the application.
        } catch (final Exception e) {
            logger.debug("Failed to load Settings.txt from the File system. Trying to load it from classpath..");
            try {
                final InputStream in =
                        Thread.currentThread().getContextClassLoader().getResourceAsStream("Settings.txt");
                if (in != null) {
                    properties.load(in);
                    // enableMultiMouse = Boolean.parseBoolean(properties.getProperty("MultiMiceEnabled",
                    // "false").trim());
                } else {
                    logger.debug("Couldnt load Settings.txt as a resource. Using defaults.");
                }
            } catch (final IOException e1) {
                logger.error("Couldnt load Settings.txt. Using defaults.");
                e1.printStackTrace();
            }
        }
        final MouseInputSource mouseInput = new MouseInputSource(this);
        inputManager.registerInputSource(mouseInput);

        // Check if we run windows 7
        if (System.getProperty("os.name").toLowerCase().contains("windows 7")) {
            final Win7NativeTouchSource win7NativeInput = new Win7NativeTouchSource(this);
            if (win7NativeInput.isSuccessfullySetup()) {
                inputManager.registerInputSource(win7NativeInput);
            }
        }

        final MTApplication desktopApp = this;
        inputManager.registerInputSource(new Tuio2DCursorInputSource(desktopApp));
        inputManager.registerInputSource(new Tuio2dObjectInputSource(desktopApp));

        /*
         * This is the bit that is significantly different from the mt4j coe.
         */
        final RamKeyboardInputSource keyInput = new RamKeyboardInputSource(this);
        inputManager.registerInputSource(keyInput);

    }

    @Override
    public void startUp() {
        // Processing registers a listener in PApplet#setupExternalMessages()
        // which exits the application by default.
        // Remove it so we can use our own.
        for (WindowListener listener : frame.getWindowListeners()) {
            frame.removeWindowListener(listener);
        }

        // Registers listener for closing window requests.
        // This will not work on Mac OS X (Command+Q and selecting Quit from the menu bar).
        // Adding a hook for handling quit events on Mac OS X is done in registerForMacOSXEvents().
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                handleQuitRequest();
            }
        });

        // By default, the window will hide on close.
        ((JFrame) frame).setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // Load app icons. Do it here, because we need the frame.
        loadAppIcons();

        // Workaround for Mac OS X. The dock icon is usually set through an app bundle.
        // Until TouchCORE is bundled properly for Mac OS X, set the dock icon explicitly.
        // Using modified OSXAdapter from Apple, which should work also on Java 7.
        if (ResourceUtils.getOperatingSystem() == OperatingSystem.OSX) {
            registerForMacOSXEvents(frame.getIconImages().get(frame.getIconImages().size() - 1));
        }

        instance = this;

        // Add the first scene.
        backgroundScene = new SelectAspectScene(this);
        ((SelectAspectScene) backgroundScene).setHandler(HandlerFactory.INSTANCE.getSelectSceneHandler());
        addScene(backgroundScene);

        registerDefaultInputSources(getInputManager());

        if (onLoad != null) {
            onLoad.run();
        }
    }

    /**
     * Function used to load the application icons.
     */
    private void loadAppIcons() {
        List<String> icons = ResourceUtil.getResourceListing("icons/", ".png");

        // Sort the images according to their image size.
        Collections.sort(icons, new Comparator<String>() {
            @Override
            public int compare(String icon1, String icon2) {
                int sizeIndex1 = icon1.lastIndexOf('_') + 1;
                int sizeIndex2 = icon2.lastIndexOf('_') + 1;

                // Get the image size. Icon filenames follow the pattern of "icon_<size>x<size>"
                // and may also have "@2x" appended (for retina resolutions).
                String iconSize1 = icon1.substring(sizeIndex1, icon1.indexOf('x', sizeIndex1));
                String iconSize2 = icon2.substring(sizeIndex2, icon2.indexOf('x', sizeIndex2));

                return Integer.valueOf(iconSize1).compareTo(Integer.valueOf(iconSize2));
            }
        });

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        List<Image> images = new ArrayList<Image>();

        // Create images for all icons.
        for (String icon : icons) {
            try {
                URL iconURL = new URL(icon);
                images.add(toolkit.createImage(iconURL));
            } catch (MalformedURLException e) {
                LoggerUtils.warn("Can't load app icon: " + icon);
            }
        }

        frame.setIconImages(images);
    }

    /**
     * Function called when the Operating System is MacOS. A docking icon image is registered accordingly.
     *
     * @param dockIcon - The docking Icon icon which needs to be set.
     */
    private void registerForMacOSXEvents(Image dockIcon) {
        if (OSXAdapter.isMacOSX()) {
            try {
                OSXAdapter.setQuitHandler(this,
                        getClass().getDeclaredMethod("handleQuitRequest", (java.lang.Class[]) null));
                OSXAdapter.setDockIconImage(dockIcon);
                // CHECKSTYLE:IGNORE IllegalCatch: Many exceptions can occur and we don't want to crash the application.
            } catch (Exception e) {
                System.err.println("Error while loading the OSXAdapter:");
                e.printStackTrace();
            }
        }
    }

    /**
     * Switches the app back to the background scene.
     *
     * @param scene the current scene
     */
    public void switchToBackground(Iscene scene) {
        changeScene(backgroundScene);
    }

    /**
     * Deletes a scene.
     *
     * @param aspect the aspect to unregister
     */
    private void unregisterAspect(final Aspect aspect) {
        synchronized (loadedAspects) {
            loadedAspects.remove(aspect);
            graphicalUpdaters.remove(aspect);
        }
    }

    @Override
    public void exit() {
        LoggerUtils.info("Shutting down TouchCORE...");

        super.exit();

        // Unload application handlers.
        if (ResourceUtils.getOperatingSystem() == OperatingSystem.OSX) {
            OSXAdapter.clearApplicationHandlers();
        }
    }

    /**
     * Should be called whenever the user wants to quit the application. Takes care of making sure this is what the user
     * wants.
     */
    public void handleQuitRequest() {
        // Get a list of all DisplayAspectScenes that have unsaved changes.
        final List<RamAbstractScene<?>> unsavedScenes = new ArrayList<RamAbstractScene<?>>();

        for (Iscene scene : getScenes()) {
            EObject model = null;
            RamAbstractScene<?> currentScene = null;

            if (scene instanceof DisplayAspectScene) {
                DisplayAspectScene ramScene = (DisplayAspectScene) scene;
                model = ramScene.getAspect();
                currentScene = ramScene;
            } else if (scene instanceof DisplayConcernEditScene) {
                DisplayConcernEditScene concernScene = (DisplayConcernEditScene) scene;
                model = concernScene.getConcern();
                currentScene = concernScene;
            }

            if (model != null) {
                boolean isSaveNeeded = EMFEditUtil.getCommandStack(model).isSaveNeeded();
                if (isSaveNeeded) {
                    unsavedScenes.add(currentScene);
                }

            }
        }

        invokeLater(new Runnable() {
            @Override
            public void run() {
                showExitConfirm(unsavedScenes);
            }
        });
    }

    /**
     * Function called to show exit confirmation pop up to all the scenes who are not saved yet.
     *
     * @param unsavedScenes - The list of unsaved scenes.
     */
    private void showExitConfirm(final List<RamAbstractScene<?>> unsavedScenes) {
        RamAbstractScene<?> currentScene = (RamAbstractScene<?>) getCurrentScene();

        if (!unsavedScenes.isEmpty()) {
            final RamAbstractScene<?> scene = unsavedScenes.iterator().next();

            if (scene instanceof DisplayAspectScene) {
                final DisplayAspectScene aspectScene = (DisplayAspectScene) scene;

                aspectScene.showCloseConfirmPopup(currentScene, new ConfirmPopup.SelectionListener() {

                    @Override
                    public void optionSelected(int selectedOption) {
                        if (selectedOption == ConfirmPopup.YES_OPTION) {
                            BasicActionsUtils.saveAspect(aspectScene.getAspect(), new AspectFileBrowserListener() {

                                @Override
                                public void aspectSaved(File file) {
                                    unsavedScenes.remove(scene);
                                    showExitConfirm(unsavedScenes);
                                }

                                @Override
                                public void aspectLoaded(Aspect aspect) {
                                }
                            });
                        } else if (selectedOption == ConfirmPopup.NO_OPTION) {
                            unsavedScenes.remove(scene);
                            showExitConfirm(unsavedScenes);
                        }
                    }
                });
            } else {
                final DisplayConcernEditScene sceneCopy = (DisplayConcernEditScene) scene;

                sceneCopy.showCloseConfirmPopup(currentScene, new ConfirmPopup.SelectionListener() {

                    @Override
                    public void optionSelected(int selectedOption) {
                        if (selectedOption == ConfirmPopup.YES_OPTION) {
                            IConcernEditSceneHandler handler = sceneCopy.getHandler();
                            handler.save(sceneCopy.getConcern());
                            unsavedScenes.remove(scene);
                            showExitConfirm(unsavedScenes);
                        } else if (selectedOption == ConfirmPopup.NO_OPTION) {
                            unsavedScenes.remove(scene);
                            showExitConfirm(unsavedScenes);
                        }
                    }
                });

            }

        } else {
            ConfirmPopup popup =
                    new ConfirmPopup(Strings.POPUP_QUIT_CONFIRM, ConfirmPopup.OptionType.YES_NO);
            popup.setListener(new ConfirmPopup.SelectionListener() {

                @Override
                public void optionSelected(int selectedOption) {
                    if (selectedOption == ConfirmPopup.YES_OPTION) {
                        exit();
                    }
                }
            });

            currentScene.displayPopup(popup);
        }

    }

}
