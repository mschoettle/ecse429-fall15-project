package ca.mcgill.sel.ram.ui.views.structural.handler.impl;

import java.util.HashSet;
import java.util.Set;

import ca.mcgill.sel.commons.StringUtil;
import ca.mcgill.sel.ram.REnum;
import ca.mcgill.sel.ram.REnumLiteral;
import ca.mcgill.sel.ram.controller.ControllerFactory;
import ca.mcgill.sel.ram.ui.components.RamKeyboard;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.components.RamTextComponent;
import ca.mcgill.sel.ram.ui.components.listeners.DefaultRamKeyboardListener;
import ca.mcgill.sel.ram.ui.components.menu.RamLinkedMenu;
import ca.mcgill.sel.ram.ui.utils.Icons;
import ca.mcgill.sel.ram.ui.utils.MetamodelRegex;
import ca.mcgill.sel.ram.ui.utils.Strings;
import ca.mcgill.sel.ram.ui.views.handler.ILinkedMenuListener;
import ca.mcgill.sel.ram.ui.views.structural.EnumView;
import ca.mcgill.sel.ram.ui.views.structural.handler.IEnumViewHandler;

/**
 * The default handler for a {@link EnumView}.
 * 
 * @author Franz
 */
public class EnumViewHandler extends BaseViewHandler implements IEnumViewHandler, ILinkedMenuListener {

    private static final String ACTION_ENUM_LITERAL_ADD = "view.enum.literal.add";

    @Override
    public void createLiteral(final EnumView enumView) {
        // Get the index where we should add the literal
        final int index = enumView.getREnum().getLiterals().size();

        // create new row
        final RamTextComponent textRow = new RamTextComponent();
        textRow.setPlaceholderText(Strings.PH_ENUM_LITERALS);

        // Get the container holding all the literals
        final RamRectangleComponent literalsContainer = enumView.getEnumLiteralsContainer();

        // Add the new text row to it
        literalsContainer.addChild(index, textRow);

        // Create keyboard and add listener
        RamKeyboard keyboard = new RamKeyboard();
        keyboard.registerListener(new DefaultRamKeyboardListener() {
            @Override
            public void keyboardCancelled() {
                // If cancelled remove the row and enable the edit buttons
                literalsContainer.removeChild(textRow);
            }

            @Override
            public boolean verifyKeyboardDismissed() {
                try {
                    // Create the literal in the model
                    createLiteral(enumView.getREnum(), index, textRow.getText());

                    // Remove text row and enable the edit buttons
                    literalsContainer.removeChild(textRow);
                } catch (final IllegalArgumentException e) {
                    return false;
                }
                return true;
            }
        });

        textRow.showKeyboard(keyboard);

    }

    /**
     * Creates a Enum Literal by calling the EnumController.
     * 
     * @param owner REnum that should contain this literal
     * @param index Index where we should insert it
     * @param enumLiteralName Name given to this new literal
     * @throws IllegalArgumentException If it is a invalid enum literal.
     */
    private void createLiteral(REnum owner, int index, String enumLiteralName) {
        if (!enumLiteralName.matches(MetamodelRegex.REGEX_ENUM_LITERAL)) {
            throw new IllegalArgumentException("The string " + enumLiteralName
                    + " is not valid syntax for enum literals");
        }
        String[] allEnumLiterals = enumLiteralName.split(",");

        hasDuplicateLiterals(allEnumLiterals);

        for (String literal : allEnumLiterals) {
            isEnumLiteralUniqueIn(owner, literal.trim());
        }

        int i = index;
        for (String literal : allEnumLiterals) {
            // Create literal in the model with controller
            String literalName = StringUtil.toUpperCaseFirst(literal.trim());
            ControllerFactory.INSTANCE.getEnumController().createREnumLiteral(owner, i++, literalName);
        }
    }

    /**
     * Checks if literal is unique.
     * 
     * @param owner Owner of the literal.
     * @param literalName name of the literal.
     * @throws IllegalArgumentException
     */
    @SuppressWarnings("static-method")
    private void isEnumLiteralUniqueIn(REnum owner, String literalName) {
        for (REnumLiteral literal : owner.getLiterals()) {
            if (literal.getName().toLowerCase().equals(literalName.toLowerCase())) {
                throw new IllegalArgumentException("The string " + literalName
                        + " already exists in" + owner.getName());
            }
        }

    }

    /**
     * Checks if it has a duplicate literal.
     * 
     * @param literals All literals.
     */
    @SuppressWarnings("static-method")
    private void hasDuplicateLiterals(String[] literals) {
        Set<String> literalSet = new HashSet<String>();
        for (String literal : literals) {
            literalSet.add(literal.toLowerCase().trim());
        }
        if (literalSet.size() != literals.length) {
            throw new IllegalArgumentException("The string contains literal duplicates.");
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String actionCommand = event.getActionCommand();
        RamRectangleComponent pressedButton = (RamRectangleComponent) event.getTarget();
        RamLinkedMenu linkedMenu = (RamLinkedMenu) pressedButton.getParentOfType(RamLinkedMenu.class);
        if (linkedMenu != null) {

            EnumView enumView = (EnumView) linkedMenu.getLinkedView();

            if (ACTION_ENUM_LITERAL_ADD.equals(actionCommand)) {
                createLiteral(enumView);
            }
        }
        super.actionPerformed(event);
    }

    @Override
    public void initMenu(RamLinkedMenu menu) {
        super.initMenu(menu);
        menu.addAction(Strings.MENU_LITERAL_ADD, Icons.ICON_MENU_ADD_LITERAL, ACTION_ENUM_LITERAL_ADD, this,
                SUBMENU_ADD, true);
    }

}
