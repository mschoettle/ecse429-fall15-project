package ca.mcgill.sel.ram.ui.views.structural;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import ca.mcgill.sel.ram.loaders.RamClassLoader;
import ca.mcgill.sel.ram.loaders.RamClassSearcher;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamButton;
import ca.mcgill.sel.ram.ui.components.RamImageComponent;
import ca.mcgill.sel.ram.ui.components.RamPopup.PopupType;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.components.RamSelectorComponent;
import ca.mcgill.sel.ram.ui.components.RamTextComponent;
import ca.mcgill.sel.ram.ui.components.browser.JarFileBrowser;
import ca.mcgill.sel.ram.ui.components.browser.interfaces.JarFileBrowserListener;
import ca.mcgill.sel.ram.ui.components.listeners.RamSelectorListener;
import ca.mcgill.sel.ram.ui.events.listeners.ActionListener;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.utils.Fonts;
import ca.mcgill.sel.ram.ui.utils.Icons;
import ca.mcgill.sel.ram.ui.utils.Strings;

/**
 * A selector that will display all the loadable implementation classes.
 *
 * Note: it filters out all implementation classes passed in as parameter.
 *
 * @author Franz
 */
public class ImplementationClassSelectorView extends RamSelectorComponent<String> 
                                            implements RamSelectorListener<String>, ActionListener {

    /**
     * The action command for adding a new JAR file.
     */
    protected static final String ACTION_JAR_ADD = "view.implclass.selector.addjar";

    // private static final int DEFAULT_NUM_RECOMMENDATIONS = 10;
    private static final int DEFAULT_MIN_NUM_TEXT = 4;
    private static final float ICON_SIZE = Fonts.FONTSIZE_CLASS_NAME + 2;

    private RamButton addButton;
    private List<String> filteredClasses;

    /**
     * Constructor. Builds the class selector with the add button.
     * 
     * @param pFilteredClasses classes to be filtered
     */
    public ImplementationClassSelectorView(List<String> pFilteredClasses) {
        registerListener(this);
        filteredClasses = new ArrayList<String>(pFilteredClasses);
        RamImageComponent addImage =
                new RamImageComponent(Icons.ICON_ADD, Colors.ICON_STRUCT_DEFAULT_COLOR, ICON_SIZE, ICON_SIZE);
        addButton = new RamButton(addImage);
        addButton.setActionCommand(ACTION_JAR_ADD);
        addButton.addActionListener(this);
        inputContainer.addChild(addButton);
    }

    @Override
    public void textModified(RamTextComponent component) {
        if (component == inputField) {
            updateRecommendedList(component.getText());
        }
    }

    /**
     * Updates the recommended list of loadable classes. It uses the searcher to get the best matching one and displays
     * the top 10. It might show more than the top 10 when the first class in the selector appears more than 10 times.
     * If this happens all the classes with same name as the first element appears.
     *
     * @param text class being searched
     */
    private void updateRecommendedList(String text) {
        if (text.length() >= DEFAULT_MIN_NUM_TEXT) {
            // get entire list of recommendations
            List<String> fullRecommendations =
                    RamClassSearcher.INSTANCE.getRecommendedLoadable(text, RamClassLoader.INSTANCE.getAllLoadable());

            // remove all classes that should be filtered
            fullRecommendations.removeAll(filteredClasses);

            // set a new map
            setMap(new LinkedHashMap<String, RamRectangleComponent>());
            // fill with new elements
            setElements(fullRecommendations);
        } else {
            setElements(Collections.EMPTY_LIST);
        }
    }

    @Override
    public void elementSelected(RamSelectorComponent<String> selector, String element) {
        destroy();
    }

    /**
     * Setter for the list of the name of implementation classes that should be filtered from this selector.
     *
     * @param pImpClasses List of names of implementation classes that should be filtered
     */
    public void setFilteredClasses(List<String> pImpClasses) {
        filteredClasses = new ArrayList<String>(pImpClasses);
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        String actionCommand = event.getActionCommand();

        if (ACTION_JAR_ADD.equals(actionCommand)) {
            JarFileBrowser.loadJarFile(new JarFileBrowserListener() {

                @Override
                public void jarLoaded(File file) {
                    try {
                        RamClassLoader.INSTANCE.addJarFile(file.getAbsolutePath());
                    } catch (FileNotFoundException e) {
                        RamApp.getActiveScene().displayPopup(Strings.POPUP_ERROR_FILE_LOAD, PopupType.ERROR);
                    }
                }
            });
        }
    }

    @Override
    public void closeSelector(RamSelectorComponent<String> selector) {
        destroy();
    }

    @Override
    public void elementDoubleClicked(RamSelectorComponent<String> selector, String element) {
    }

    @Override
    public void elementHeld(RamSelectorComponent<String> selector, String element) {

    }

}
