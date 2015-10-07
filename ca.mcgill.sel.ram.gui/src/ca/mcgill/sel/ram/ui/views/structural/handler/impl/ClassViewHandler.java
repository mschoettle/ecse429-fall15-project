package ca.mcgill.sel.ram.ui.views.structural.handler.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;

import ca.mcgill.sel.core.COREPartialityType;
import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.ram.Class;
import ca.mcgill.sel.ram.Parameter;
import ca.mcgill.sel.ram.PrimitiveType;
import ca.mcgill.sel.ram.RAMVisibilityType;
import ca.mcgill.sel.ram.RamFactory;
import ca.mcgill.sel.ram.Type;
import ca.mcgill.sel.ram.controller.ClassController;
import ca.mcgill.sel.ram.controller.ControllerFactory;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamKeyboard;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.components.RamTextComponent;
import ca.mcgill.sel.ram.ui.components.listeners.DefaultRamKeyboardListener;
import ca.mcgill.sel.ram.ui.components.menu.RamLinkedMenu;
import ca.mcgill.sel.ram.ui.utils.Icons;
import ca.mcgill.sel.ram.ui.utils.MetamodelRegex;
import ca.mcgill.sel.ram.ui.utils.RamModelUtils;
import ca.mcgill.sel.ram.ui.utils.Strings;
import ca.mcgill.sel.ram.ui.views.structural.ClassView;
import ca.mcgill.sel.ram.ui.views.structural.ClassifierView;
import ca.mcgill.sel.ram.ui.views.structural.handler.IClassViewHandler;

/**
 * The default handler for a {@link ClassView}.
 *
 * @author mschoettle
 */
public class ClassViewHandler extends ClassifierViewHandler implements IClassViewHandler {

    /**
     * The action to add a new attribute.
     */
    protected static final String ACTION_ATTRIBUTE_ADD = "view.class.attribute.add";
    private static final String ACTION_DESTRUCTOR_ADD = "view.class.destructor.add";
    private static final String ACTION_PUBLIC_PARTIAL = "view.class.public_partial";
    private static final String ACTION_CONCERN_PARTIAL = "view.class.concern_partial";
    private static final String ACTION_NOT_PARTIAL = "view.class.not_partial";
    private static final String ACTION_ABSTRACT = "view.class.abstract";
    private static final String ACTION_COLLAPSE_OPERATIONS = "view.class.operations.collapse";

    /**
     * Delimiter of a parameter (provided by the user) can be "," and optionally be followed by a whitespace.
     */
    private static final String PARAMETER_DELIMITER_PATTERN = ",\\s?";
    private static final String SUBMENU_CLASS_CHARACTERISTICS = "sub.class.partiality";

    @Override
    public void createAttribute(final ClassView classView) {
        final int index = classView.getRamClass().getAttributes().size();

        final RamTextComponent textRow = new RamTextComponent();
        textRow.setPlaceholderText(Strings.PH_ATTRIBUTE);
        final RamRectangleComponent attributesContainer = classView.getAttributesContainer();
        attributesContainer.addChild(index, textRow);

        RamKeyboard keyboard = new RamKeyboard();
        keyboard.registerListener(new DefaultRamKeyboardListener() {
            @Override
            public void keyboardCancelled() {
                attributesContainer.removeChild(textRow);

            }

            @Override
            public boolean verifyKeyboardDismissed() {
                try {
                    createAttribute(classView.getRamClass(), index, textRow.getText());
                    attributesContainer.removeChild(textRow);

                } catch (final IllegalArgumentException e) {
                    return false;
                }
                return true;
            }
        });

        textRow.showKeyboard(keyboard);
    }

    @Override
    public void createOperation(final ClassifierView<?> classifierView) {
        final int index = classifierView.getOperationsContainer().getChildCount();

        final RamTextComponent textRow = new RamTextComponent();
        textRow.setPlaceholderText(Strings.PH_OPERATION);
        final RamRectangleComponent operationsContainer = classifierView.getOperationsContainer();
        operationsContainer.addChild(index, textRow);

        RamKeyboard keyboard = new RamKeyboard();
        keyboard.registerListener(new DefaultRamKeyboardListener() {
            @Override
            public void keyboardCancelled() {
                operationsContainer.removeChild(textRow);

            }

            @Override
            public boolean verifyKeyboardDismissed() {
                try {
                    Class ramClass = ((ClassView) classifierView).getRamClass();
                    createOperation(ramClass, ramClass.getOperations().size(), textRow.getText());
                    operationsContainer.removeChild(textRow);

                } catch (final IllegalArgumentException e) {
                    return false;
                }
                return true;
            }
        });

        textRow.showKeyboard(keyboard);
    }

    /**
     * Creates a new attribute based on the given attribute string (<code>"[typeName] [attributeName]"</code>).
     * The attribute is added to the class at the given index.
     *
     * @param owner the {@link Class} the attribute should be added to
     * @param index the index at which the attribute should be added to the class
     * @param attributeString the textual description of the attribute
     * @throws IllegalArgumentException if the given string does not match the expected pattern
     */
    private static void createAttribute(Class owner, int index, String attributeString) {
        Matcher matcher = Pattern.compile(MetamodelRegex.REGEX_ATTRIBUTE_DECLARATION).matcher(attributeString);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("The string " + attributeString + " is not valid syntax for attributes");
        }

        String name = matcher.group(3);
        PrimitiveType primitiveType = getPrimitiveType(matcher.group(1));

        ControllerFactory.INSTANCE.getClassController().createAttribute(owner, index, name, primitiveType);
    }

    /**
     * Creates a new operation based on the given operation string.
     * The string should match the following pattern:
     * <code>"[visibility] [returnType] [operationName]([optional parameters])"</code>.
     * The parameters need to be separated by comma and have to match the following pattern:
     * <code>"[parameterType] [parameterName]"</code>.
     * The operation is added to the class at the given index.
     *
     * @param owner the {@link Class} the operation should be added to
     * @param index the index at which the operation should be added to the class
     * @param operationString the textual description of the operation
     * @throws IllegalArgumentException if the given string does not match the expected pattern
     */
    private static void createOperation(Class owner, int index, String operationString) {
        Matcher matcher =
                Pattern.compile("^" + MetamodelRegex.REGEX_OPERATION_DECLARATION + "$").matcher(operationString);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("The string " + operationString
                    + " does not conform to operation syntax");
        }

        RAMVisibilityType ramVisibility = RamModelUtils.getRamVisibilityFromStringRepresentation(matcher.group(1));
        if (ramVisibility == null) {
            throw new IllegalArgumentException("The visibility is not valid");
        }

        Type returnType = getType(matcher.group(2));
        String name = matcher.group(4);
        List<Parameter> parameters = getParameters(matcher.group(5));

        ControllerFactory.INSTANCE.getClassController().createOperation(owner, index, name, ramVisibility,
                returnType, parameters);
    }

    /**
     * Creates and returns a parameter based on the given parameter string.
     * The parameter needs to match following pattern: <code>"[parameterType] [parameterName]"</code>.
     *
     * @param parameterString the textual description of the parameter
     * @return the {@link Parameter} based on the given string
     * @throws IllegalArgumentException if the string does not match the expected pattern
     */
    private static Parameter getParameter(String parameterString) {
        Matcher matcher = Pattern.compile(MetamodelRegex.REGEX_ATTRIBUTE_DECLARATION).matcher(parameterString);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("The type " + parameterString + " does not exist in the model");
        }

        Parameter parameter = RamFactory.eINSTANCE.createParameter();
        Type type = getType(matcher.group(1));

        parameter.setType(type);
        parameter.setName(matcher.group(3));

        return parameter;
    }

    /**
     * Returns a list of parameters based on the given string.
     * The parameters need to be separated by comma and have to match the following pattern:
     * <code>"[parameterType] [parameterName]"</code>.
     *
     * @param parametersString the comma separated list of parameter strings
     * @return the list of {@link Parameter}s
     * @see #getParameter(String)
     */
    private static List<Parameter> getParameters(String parametersString) {
        List<Parameter> parameters = new ArrayList<Parameter>();

        if (parametersString != null && !parametersString.isEmpty()) {
            String[] parametersList = parametersString.split(PARAMETER_DELIMITER_PATTERN);

            for (String parameterString : parametersList) {
                Parameter parameter = getParameter(parameterString);
                parameters.add(parameter);
            }
        }

        return parameters;
    }

    /**
     * Returns the primitive type that matches the given type name.
     *
     * @param type the type name of the primitive type
     * @return the {@link PrimitiveType} that matches the name of the type
     * @throws IllegalArgumentException if the primitive type does not exist
     */
    private static PrimitiveType getPrimitiveType(String type) {
        PrimitiveType primitiveType = RamModelUtils.getPrimitiveTypeByName(type);

        if (primitiveType == null) {
            throw new IllegalArgumentException("The structural view does not contain a primitive type " + type);
        }

        return primitiveType;
    }

    /**
     * Returns the type that matches the given type name.
     *
     * @param typeString the type name
     * @return the {@link Type} that matches the name of the type
     * @throws IllegalArgumentException if the type does not exist
     */
    private static Type getType(String typeString) {
        Type returnType = RamModelUtils.getTypeByName(typeString);
        if (returnType == null) {
            throw new IllegalArgumentException("The type " + typeString + " does not exist in the model");
        }

        return returnType;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String actionCommand = event.getActionCommand();
        RamRectangleComponent pressedButton = (RamRectangleComponent) event.getTarget();
        RamLinkedMenu linkedMenu = pressedButton.getParentOfType(RamLinkedMenu.class);
        if (linkedMenu != null) {

            ClassifierView<?> clazz = (ClassifierView<?>) linkedMenu.getLinkedView();

            if (ACTION_ATTRIBUTE_ADD.equals(actionCommand)) {
                createAttribute((ClassView) clazz);
            } else if (ACTION_DESTRUCTOR_ADD.equals(actionCommand)) {
                createDestructor(clazz);
            } else if (ACTION_PUBLIC_PARTIAL.equals(actionCommand)) {
                switchPartiality(clazz, COREPartialityType.PUBLIC);
            } else if (ACTION_CONCERN_PARTIAL.equals(actionCommand)) {
                switchPartiality(clazz, COREPartialityType.CONCERN);
            } else if (ACTION_NOT_PARTIAL.equals(actionCommand)) {
                switchPartiality(clazz, COREPartialityType.NONE);
            } else if (ACTION_ABSTRACT.equals(actionCommand)) {
                switchToAbstract(clazz);
            } else if (ACTION_COLLAPSE_OPERATIONS.equals(actionCommand)) {
                ClassView classView = (ClassView) clazz;
                classView.collapseOperations();
                linkedMenu.toggleAction(classView.isShowingAllOperations(), ACTION_COLLAPSE_OPERATIONS);
            }
        }
        super.actionPerformed(event);
    }

    @Override
    public void switchToAbstract(ClassifierView<?> clazz) {
        ClassController controller = ControllerFactory.INSTANCE.getClassController();
        boolean switched = controller.switchAbstract((Class) clazz.getClassifier());
        if (!switched) {
            RamApp.getActiveAspectScene().displayPopup(Strings.POPUP_ABSTRACT_CLASS_NO_SWITCH);
        }
    }

    @Override
    public void switchPartiality(ClassifierView<?> clazz, COREPartialityType type) {
        ClassController controller = ControllerFactory.INSTANCE.getClassController();
        controller.setPartialityType(clazz.getClassifier(), type);
    }

    @Override
    public void initMenu(RamLinkedMenu menu) {
        super.initMenu(menu);
        menu.addAction(Strings.MENU_DESTRUCTOR_ADD, Icons.ICON_MENU_ADD_DESTRUCTOR, ACTION_DESTRUCTOR_ADD, this,
                SUBMENU_OPERATION, true);
        menu.addAction(Strings.MENU_ATTRIBUTE_ADD, Icons.ICON_MENU_ADD_ATTRIBUTE, ACTION_ATTRIBUTE_ADD, this,
                SUBMENU_ADD, true);

        menu.addSubMenu(1, SUBMENU_CLASS_CHARACTERISTICS);
        menu.addAction(Strings.MENU_PUBLIC_PARTIAL, Icons.ICON_MENU_PUBLIC_PARTIAL, ACTION_PUBLIC_PARTIAL, this,
                SUBMENU_CLASS_CHARACTERISTICS, true);
        menu.addAction(Strings.MENU_CONCERN_PARTIAL, Icons.ICON_MENU_CONCERN_PARTIAL, ACTION_CONCERN_PARTIAL,
                this, SUBMENU_CLASS_CHARACTERISTICS, true);
        menu.addAction(Strings.MENU_NO_PARTIAL, Icons.ICON_MENU_NOT_PARTIAL, ACTION_NOT_PARTIAL, this,
                SUBMENU_CLASS_CHARACTERISTICS, true);
        menu.addAction(Strings.MENU_ABSTRACT, Strings.MENU_NOT_ABSTRACT, Icons.ICON_MENU_ABSTRACT,
                Icons.ICON_MENU_NOT_ABSTRACT, ACTION_ABSTRACT, this, SUBMENU_CLASS_CHARACTERISTICS, true, false);

        menu.addAction(Strings.MENU_EXPAND_OPERATIONS, Strings.MENU_COLLAPSE_OPERATIONS,
                Icons.ICON_EXPAND_OPERATIONS, Icons.ICON_COLLAPSE_OPERATIONS,
                ACTION_COLLAPSE_OPERATIONS, this, SUBMENU_CLASS_CHARACTERISTICS, true, false);

        updateButtons(menu);
    }

    @Override
    public void updateMenu(RamLinkedMenu menu, Notification notification) {
        if (notification.getEventType() == Notification.SET
                || notification.getEventType() == Notification.UNSET
                || notification.getEventType() == Notification.ADD
                || notification.getEventType() == Notification.REMOVE) {
            updateButtons(menu);
        }
        super.updateMenu(menu, notification);
    }

    @Override
    public List<EObject> getEObjectToListenForUpdateMenu(RamRectangleComponent rectangle) {
        ClassView classView = (ClassView) rectangle;
        List<EObject> ret = new ArrayList<EObject>();
        ret.addAll(classView.getClassifier().getOperations());
        ret.addAll(super.getEObjectToListenForUpdateMenu(rectangle));
        return ret;
    }

    /**
     * Update buttons inside the menu.
     *
     * @param menu - the menu which contains buttons.
     */
    private static void updateButtons(RamLinkedMenu menu) {
        Class clazz = (Class) menu.geteObject();
        menu.toggleAction(clazz.isAbstract(), ACTION_ABSTRACT);

        Object obj = clazz.eGet(CorePackage.Literals.CORE_MODEL_ELEMENT__PARTIALITY);
        menu.enableAction(!obj.equals(COREPartialityType.NONE), ACTION_NOT_PARTIAL);
        menu.enableAction(!obj.equals(COREPartialityType.CONCERN), ACTION_CONCERN_PARTIAL);
        menu.enableAction(!obj.equals(COREPartialityType.PUBLIC), ACTION_PUBLIC_PARTIAL);
    }

    @Override
    public void createConstructor(ClassifierView<?> classifierView) {
        ControllerFactory.INSTANCE.getClassController().createConstructor((Class) classifierView.getClassifier());
    }

    @Override
    public void createDestructor(ClassifierView<?> clazz) {
        ControllerFactory.INSTANCE.getClassController().createDestructor((Class) clazz.getClassifier());
    }
}