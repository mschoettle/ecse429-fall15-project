package ca.mcgill.sel.ram.ui.views.structural.handler.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Constructor;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;

import ca.mcgill.sel.ram.ImplementationClass;
import ca.mcgill.sel.ram.ObjectType;
import ca.mcgill.sel.ram.Operation;
import ca.mcgill.sel.ram.OperationType;
import ca.mcgill.sel.ram.Parameter;
import ca.mcgill.sel.ram.RAMVisibilityType;
import ca.mcgill.sel.ram.RamFactory;
import ca.mcgill.sel.ram.StructuralView;
import ca.mcgill.sel.ram.Type;
import ca.mcgill.sel.ram.TypeParameter;
import ca.mcgill.sel.ram.controller.ControllerFactory;
import ca.mcgill.sel.ram.loaders.RamClassLoader;
import ca.mcgill.sel.ram.loaders.RamClassUtils;
import ca.mcgill.sel.ram.loaders.exceptions.MissingJarException;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamListComponent.Namer;
import ca.mcgill.sel.ram.ui.components.RamPopup.PopupType;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.components.RamSelectorComponent;
import ca.mcgill.sel.ram.ui.components.RamTextComponent;
import ca.mcgill.sel.ram.ui.components.browser.JarFileBrowser;
import ca.mcgill.sel.ram.ui.components.browser.interfaces.JarFileBrowserListener;
import ca.mcgill.sel.ram.ui.components.listeners.AbstractDefaultRamSelectorListener;
import ca.mcgill.sel.ram.ui.components.menu.RamLinkedMenu;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.utils.RamModelUtils;
import ca.mcgill.sel.ram.ui.utils.Strings;
import ca.mcgill.sel.ram.ui.views.structural.ClassifierView;
import ca.mcgill.sel.ram.ui.views.structural.ImplementationClassView;
import ca.mcgill.sel.ram.ui.views.structural.wrappers.CallableMemberWrapper;
import ca.mcgill.sel.ram.ui.views.structural.wrappers.ConstructorWrapper;
import ca.mcgill.sel.ram.ui.views.structural.wrappers.MethodWrapper;

/**
 * The default handler for an {@link ImplementationClassView}.
 * 
 * @author Franz
 */
public class ImplementationClassViewHandler extends ClassifierViewHandler {

    @Override
    public final void createOperation(final ClassifierView<?> classifierView) {
        final int index = classifierView.getClassifier().getOperations().size();
        RamRectangleComponent temp = new RamRectangleComponent();
        classifierView.getOperationsContainer().addChild(index, temp);

        // Get the owner of this new operation
        ImplementationClass owner = (ImplementationClass) classifierView.getClassifier();

        // Will hold all selectable methods
        List<MethodWrapper> methods = new ArrayList<MethodWrapper>();
        try {
            // get all selectable methods from this class
            methods = getAllSelectableMethods((ImplementationClass) classifierView.getClassifier(),
                    RamClassLoader.INSTANCE.retrieveClass(owner.getInstanceClassName()));

            // create the selector
            RamSelectorComponent<MethodWrapper> selector = new RamSelectorComponent<MethodWrapper>(methods,
                    new Namer<MethodWrapper>() {

                        @Override
                        public RamRectangleComponent getDisplayComponent(MethodWrapper element) {
                            RamTextComponent view = new RamTextComponent(element.toString());
                            view.setNoFill(false);
                            view.setFillColor(Colors.DEFAULT_ELEMENT_FILL_COLOR);
                            view.setNoStroke(false);
                            return view;
                        }

                        @Override
                        public String getSortingName(MethodWrapper element) {
                            return element.getName() + element.getParametersAsString();
                        }

                        @Override
                        public String getSearchingName(MethodWrapper element) {
                            return getSortingName(element);
                        }

                    });

            // Show the selector
            RamApp.getActiveAspectScene().addComponent(selector, temp.getCenterPointGlobal());

            // load selected method
            selector.registerListener(new AbstractDefaultRamSelectorListener<MethodWrapper>() {

                @Override
                public void elementSelected(RamSelectorComponent<MethodWrapper> selector, MethodWrapper method) {
                    ImplementationClass owner = (ImplementationClass) classifierView.getClassifier();

                    // Create operation
                    String name = method.getName();
                    boolean isStatic = method.isStatic();
                    RAMVisibilityType visibility = extractVisibility(method);
                    Type returnType = extractReturnType(method, true);
                    List<Parameter> parameters = extractParameters(method, true);
                    ControllerFactory.INSTANCE.getImplementationClassController().addOperation(owner, index, name,
                            visibility, returnType, parameters, isStatic);
                    selector.destroy();
                }
            });

            selector.displayKeyboard();

        } catch (ClassNotFoundException e) {
            showLoadJarBrowser();
            RamApp.getActiveAspectScene()
                    .displayPopup(Strings.POPUP_ERROR_CLASS_NOT_FOUND, PopupType.ERROR);
        } catch (MissingJarException e) {
            RamApp.getActiveAspectScene().displayPopup(e.getMessage(), PopupType.ERROR);
        }

    }

    @Override
    public final void createConstructor(final ClassifierView<?> classifierView) {
        int index = classifierView.getClassifier().getOperations().size();
        final RamRectangleComponent temp = new RamRectangleComponent();
        classifierView.getOperationsContainer().addChild(index, temp);

        // Get the owner of this new operation
        ImplementationClass owner = (ImplementationClass) classifierView.getClassifier();

        // Will hold all selectable methods
        List<ConstructorWrapper> constructors = new ArrayList<ConstructorWrapper>();
        try {
            // get all selectable methods from this class
            constructors = getAllSelectableConstructors((ImplementationClass) classifierView.getClassifier(),
                    RamClassLoader.INSTANCE.retrieveClass(owner.getInstanceClassName()));

            // create the selector
            RamSelectorComponent<ConstructorWrapper> selector = new RamSelectorComponent<ConstructorWrapper>(
                    constructors,
                    new Namer<ConstructorWrapper>() {

                        @Override
                        public RamRectangleComponent getDisplayComponent(ConstructorWrapper element) {
                            RamTextComponent view = new RamTextComponent(element.toString());
                            view.setNoFill(false);
                            view.setFillColor(Colors.DEFAULT_ELEMENT_FILL_COLOR);
                            view.setNoStroke(false);
                            return view;
                        }

                        @Override
                        public String getSortingName(ConstructorWrapper element) {
                            return element.getName() + element.getParametersAsString();
                        }

                        @Override
                        public String getSearchingName(ConstructorWrapper element) {
                            return getSortingName(element);
                        }

                    });

            // Show the selector
            RamApp.getActiveAspectScene().addComponent(selector, temp.getCenterPointGlobal());

            // load selected method
            selector.registerListener(new AbstractDefaultRamSelectorListener<ConstructorWrapper>() {

                @Override
                public void elementSelected(RamSelectorComponent<ConstructorWrapper> selector,
                        ConstructorWrapper method) {
                    temp.destroy();
                    selector.destroy();

                    ImplementationClass owner = (ImplementationClass) classifierView.getClassifier();

                    // Create operation
                    String name = method.getName();
                    RAMVisibilityType visibility = extractVisibility(method);
                    List<Parameter> parameters = extractParameters(method, true);
                    ControllerFactory.INSTANCE.getImplementationClassController().addConstructor(owner, name,
                            visibility, parameters);
                }
            });

            selector.displayKeyboard();

        } catch (ClassNotFoundException e) {
            showLoadJarBrowser();
            RamApp.getActiveAspectScene()
                    .displayPopup(Strings.POPUP_ERROR_CLASS_NOT_FOUND, PopupType.ERROR);
        } catch (MissingJarException e) {
            RamApp.getActiveAspectScene().displayPopup(e.getMessage(), PopupType.ERROR);
        }
    }

    /**
     * Shows a browser to load a JAR file.
     */
    private static void showLoadJarBrowser() {
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

    /**
     * Gets all classes that are selectable. Filters all static generic methods. Filters all existing methods.
     * 
     * @param owner The owner of the methods.
     * @param clazz Class associated to the owner implementation class.
     * @return List of selectable methods.
     */
    private List<MethodWrapper> getAllSelectableMethods(ImplementationClass owner, Class<?> clazz) {

        // All selectable methods
        List<MethodWrapper> methods = new ArrayList<MethodWrapper>();

        // Go through every method in the Class object
        for (Method m : clazz.getMethods()) {
            // Check if operation exists
            boolean opAlreadyExist = false;
            MethodWrapper mw = new MethodWrapper(m, owner);
            // Compare the method to the operation already in the implementation class
            for (Operation o : owner.getOperations()) {
                if (o.getOperationType() == OperationType.NORMAL) {
                    if (o.isStatic() == mw.isStatic()
                            && o.getName().equals(mw.getName())
                            && o.getExtendedVisibility() == extractVisibility(mw)
                            && o.getReturnType() == extractReturnType(mw, false)) {

                        opAlreadyExist = true;

                        // Compare parameters
                        List<Parameter> params = extractParameters(mw, false);
                        if (o.getParameters().size() == params.size()) {
                            for (int i = 0; i < params.size(); i++) {
                                Parameter paramOne = o.getParameters().get(i);
                                Parameter paramTwo = params.get(i);
                                if (paramOne.getType() != paramTwo.getType()) {
                                    opAlreadyExist = false;
                                    continue;
                                }
                            }
                        } else {
                            opAlreadyExist = false;
                        }
                    }
                }
                if (opAlreadyExist) {
                    break;
                }
            }
            // if operation does not exists and its not a generic static method
            // then add to list.
            if (!opAlreadyExist && !mw.isGenericMethod()) {
                methods.add(mw);
            }
        }
        return methods;
    }

    /**
     * Returns a list of selectable constructors of the given class.
     * 
     * @param owner the {@link ImplementationClass} representing the clazz
     * @param clazz the actual {@link Class} that is loaded
     * @return a list of selectable constructors of the given class
     */
    private List<ConstructorWrapper> getAllSelectableConstructors(ImplementationClass owner, Class<?> clazz) {
        // All selectable methods
        List<ConstructorWrapper> constructors = new ArrayList<ConstructorWrapper>();

        // Go through every method in the Class object
        for (Constructor<?> c : clazz.getConstructors()) {
            // Check if operation exists
            boolean opAlreadyExist = false;
            ConstructorWrapper cw = new ConstructorWrapper(c, owner);
            // Compare the method to the operation already in the implementation class
            for (Operation o : owner.getOperations()) {
                if (o.getOperationType() == OperationType.CONSTRUCTOR) {
                    if (o.getName().equals(cw.getName()) && o.getExtendedVisibility() == extractVisibility(cw)) {

                        opAlreadyExist = true;

                        // Compare parameters
                        List<Parameter> params = extractParameters(cw, false);
                        if (o.getParameters().size() == params.size()) {
                            for (int i = 0; i < params.size(); i++) {
                                Parameter paramOne = o.getParameters().get(i);
                                Parameter paramTwo = params.get(i);
                                if (paramOne.getType() != paramTwo.getType()) {
                                    opAlreadyExist = false;
                                    continue;
                                }
                            }
                        } else {
                            opAlreadyExist = false;
                        }
                    }
                }
                if (opAlreadyExist) {
                    break;
                }
            }
            // if operation does not exists and its not a generic static method
            // then add to list.
            if (!opAlreadyExist) {
                constructors.add(cw);
            }
        }
        return constructors;
    }

    /**
     * Returns the model type for the given actual type.
     * In case the type is not found (e.g., because it is one from a class of a JAR) it can be loaded.
     * 
     * @param owner {@link ImplementationClass} that uses this type as return type or parameter
     * @param someType the type to search a model type for
     * @param loadIfMissing true, if missing types should be loaded, false otherwise
     * @return the found type
     */
    private Type getType(ImplementationClass owner, java.lang.reflect.Type someType, boolean loadIfMissing) {
        // This should use the regular getType operation (in RamModelUtils), which already takes care of
        // arrays etc. However, it currently cannot load missing types (from libraries).
        int arrayDimension = 0;
        java.lang.reflect.Type realType = someType;

        // If array we must find the type of array
        while (realType instanceof Class && ((Class<?>) realType).isArray()) {
            realType = ((Class<?>) realType).getComponentType();
            arrayDimension++;
        }

        // If array we must find the type of array
        while (realType instanceof GenericArrayType) {
            realType = ((GenericArrayType) realType).getGenericComponentType();
            arrayDimension++;
        }

        Type type = null;
        StructuralView structuralView = (StructuralView) owner.eContainer();

        // if it's primitive. Use RamModelUtils/
        if (isPrimitiveType(realType)) {
            String fullClassName = RamClassUtils.getClassName(realType);
            type = RamModelUtils.getTypeByName(RamClassUtils.extractClassName(fullClassName));
        } else if (RamModelUtils.isGenericOf(owner, realType.toString())) {
            // if generic of the owner then get the associated generic type.
            type = RamModelUtils.getGenericOf(owner, realType.toString()).getGenericType();

        } else {
            // else load the class if missing. And retrieve it from view.

            // load type if missing
            if (loadIfMissing) {
                loadTypeIfMissing(structuralView, owner, realType);
            }

            type = RamModelUtils.getTypeByName(RamModelUtils.getNameOfType(realType, owner),
                    structuralView);
        }

        if (arrayDimension > 0) {
            String arrayName = type.getName();
            int dimension = arrayDimension;

            while (dimension > 0) {
                arrayName += "[]";
                dimension--;
            }

            type = RamModelUtils.getTypeByName(arrayName, structuralView);
        }
        return type;
    }

    /**
     * Extract the return type of a method.
     * 
     * @param method Some method
     * @param loadIfMissing load type if missing
     * @return Return Type of this method.
     */
    private Type extractReturnType(MethodWrapper method, boolean loadIfMissing) {

        ImplementationClass owner = method.getOwner();

        // Get return type
        java.lang.reflect.Type returnType = method.getReturnType();
        return getType(owner, returnType, loadIfMissing);
    }

    /**
     * Extracts the visibility of a method.
     * 
     * @param method some method.
     * @return visibility of the method
     */
    @SuppressWarnings("static-method")
    private RAMVisibilityType extractVisibility(CallableMemberWrapper method) {
        // get visibility
        String modifier = method.getVisibility();

        RAMVisibilityType v = RAMVisibilityType.PUBLIC;
        if ("protected".equals(modifier)) {
            v = RAMVisibilityType.PROTECTED;
        } else if ("private".equals(modifier)) {
            v = RAMVisibilityType.PRIVATE;
        }

        return v;
    }

    /**
     * Extracts all parameters of method.
     * 
     * @param method some method.
     * @param loadIfMissing load parameter if missing in view.
     * @return all parameters of the method.
     */
    private List<Parameter> extractParameters(CallableMemberWrapper method, boolean loadIfMissing) {

        ImplementationClass owner = method.getOwner();

        int counter = 0;

        List<Parameter> parameters = new ArrayList<Parameter>();

        // load all the parameters.
        for (java.lang.reflect.Type someType : method.getParameters()) {

            // create parameter object.
            Parameter param = RamFactory.eINSTANCE.createParameter();

            // set the default parameter name
            param.setName(CallableMemberWrapper.DEFAULT_PARAM_NAME + counter++);
            Type type = getType(owner, someType, loadIfMissing);
            param.setType(type);
            parameters.add(param);
        }
        return parameters;
    }

    /**
     * Loads the specified type inside the StructuralView if it does not exist yet.
     * 
     * @param view in wich we want to load the type.
     * @param owner Owner of the method containing the type we want to load.
     * @param someType Some type we want to load.
     */
    private void loadTypeIfMissing(StructuralView view, ImplementationClass owner, java.lang.reflect.Type someType) {
        try {
            // Retrieve the class associated with this type
            String className = RamClassUtils.getClassName(someType);
            Class<?> someClass = RamClassUtils.getClassFromType(someType);

            // if it's an enum. Find in view and create if not there.
            if (someClass.isEnum()) {
                if (!isEnumInStructuralView(view, className)) {
                    String shortName = RamClassUtils.extractClassName(className);
                    List<String> literals = new ArrayList<String>();
                    for (Object literal : someClass.getEnumConstants()) {
                        literals.add(literal.toString());
                    }

                    ControllerFactory.INSTANCE.getStructuralViewController().createImplementationEnum(view, shortName,
                            className, 0, 0, literals);
                }
            } else {
                // Load implementation class if does not exists yet.
                boolean isInterface = someClass.isInterface();
                if (!isImplementationClassInStructuralView(view, owner, someType)) {
                    List<TypeParameter> typeParameters = createTypeParameters(view, owner, someType);
                    Set<String> superTypes = RamClassLoader.INSTANCE.getAllSuperClassesFor(className);
                    Set<ImplementationClass> subTypes = RamModelUtils.getExistingSubTypesFor(view, className);
                    ControllerFactory.INSTANCE.getStructuralViewController().createImplementationClass(view, className,
                            typeParameters, isInterface, 0, 0, superTypes, subTypes);
                }
            }
        } catch (ClassNotFoundException e) {
            // log?
            e.printStackTrace();
        }
    }

    /**
     * Checks if implementation class is in the structural view.
     * 
     * @param view Current view.
     * @param owner of the method that holds this type.
     * @param someType type corresponding to the implementation class.
     * @return true if exists in view, false otherwise
     */
    @SuppressWarnings("static-method")
    private boolean isImplementationClassInStructuralView(StructuralView view, ImplementationClass owner,
            java.lang.reflect.Type someType) {
        return RamModelUtils.getTypeByName(RamModelUtils.getNameOfType(someType, owner), view) != null;
    }

    /**
     * Checks if enum is in the structural view.
     * 
     * @param view Current view.
     * @param enumInstanceName instance name of the enum we want to load
     * @return true if exists in view, false otherwise
     */
    @SuppressWarnings("static-method")
    private boolean isEnumInStructuralView(StructuralView view, String enumInstanceName) {
        return RamModelUtils.getTypeByName(enumInstanceName, view) != null;
    }

    /**
     * Checks if the type object is a primitive.
     * 
     * @param someType type we are checking
     * @return true if primitive type, false otherwise
     */
    @SuppressWarnings("static-method")
    private boolean isPrimitiveType(java.lang.reflect.Type someType) {
        if (someType instanceof Class && (((Class<?>) someType).isPrimitive() || someType == String.class)) {
            return true;
        }
        return false;
    }

    /**
     * Create the type parameters of a specified type.
     * 
     * @param view The view containing the specified type.
     * @param owner Implementation class representing the type.
     * @param someType A type.
     * @return List of type parameters
     */
    private List<TypeParameter> createTypeParameters(StructuralView view, ImplementationClass owner,
            java.lang.reflect.Type someType) {
        try {
            // get the Class object of this type
            Class<?> someClass = RamClassUtils.getClassFromType(someType);

            // will hold the type parameters
            List<TypeParameter> typeParameters = new ArrayList<TypeParameter>();

            // if it is a generic type then get the Type parameters
            if (someType instanceof ParameterizedType) {

                // location of the current type parameter we are looking at
                int i = 0;

                // Go through all the type parameters
                for (java.lang.reflect.Type tp : ((ParameterizedType) someType).getActualTypeArguments()) {

                    // create instance of type parameter
                    TypeParameter typeParameter = RamFactory.eINSTANCE.createTypeParameter();

                    if (tp instanceof WildcardType) {
                        // Causes issue
                        typeParameter.setName("?");
                        typeParameters.add(typeParameter);
                    } else if (tp instanceof TypeVariable) {
                        // If type parameter of owner with the same name
                        TypeParameter typeParam = RamModelUtils.getGenericOf(owner, ((TypeVariable<?>) tp).getName());
                        if (typeParam != null) {
                            // set to the same generic type as the type parameter retrieved from owner
                            typeParameter.setName(someClass.getTypeParameters()[i].getName());
                            typeParameter.setGenericType(typeParam.getGenericType());
                            typeParameters.add(typeParameter);
                        }
                    } else {
                        // else, load it to view and set it
                        typeParameter.setName(someClass.getTypeParameters()[i].getName());
                        // loadTypeIfMissing(view, owner, tp);
                        // String name = getNameOfType(tp, owner);
                        // Type genericType = findTypeInView(view, name);
                        Type genericType = getType(owner, tp, true);
                        typeParameter.setGenericType((ObjectType) genericType);
                        typeParameters.add(typeParameter);
                    }
                    i++;
                }
            }
            return typeParameters;
        } catch (ClassNotFoundException e) {
            // TODO?
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void initMenu(RamLinkedMenu menu) {
        super.initMenu(menu);
        updateButtons(menu);
    }

    /**
     * Updates buttons inside the menu in function of the class state.
     * 
     * @param menu - the menu which contains the buttons to update.
     */
    private static void updateButtons(RamLinkedMenu menu) {
        ImplementationClassView implClassView = (ImplementationClassView) menu.getLinkedView();
        boolean genericSet = implClassView.areGenericTypesSet();
        menu.enableAction(genericSet, ACTION_OPERATION_ADD);
        menu.enableAction(genericSet, ACTION_CONSTRUCTOR_ADD);
    }

    @Override
    public void updateMenu(RamLinkedMenu menu, Notification notification) {
        if (notification.getEventType() == Notification.SET || notification.getEventType() == Notification.UNSET) {
            updateButtons(menu);
        }
        super.updateMenu(menu, notification);
    }

    @Override
    public List<EObject> getEObjectToListenForUpdateMenu(RamRectangleComponent rectangle) {
        List<EObject> ret = new ArrayList<EObject>();
        ImplementationClassView implClassView = (ImplementationClassView) rectangle;
        ret.add(implClassView.getImplementationClass());
        ret.addAll(super.getEObjectToListenForUpdateMenu(rectangle));
        return ret;
    }
}
