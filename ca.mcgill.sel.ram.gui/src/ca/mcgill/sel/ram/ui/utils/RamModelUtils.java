package ca.mcgill.sel.ram.ui.utils;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.WildcardType;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.mt4j.components.MTComponent;

import ca.mcgill.sel.core.COREVisibilityType;
import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.AssociationEnd;
import ca.mcgill.sel.ram.Classifier;
import ca.mcgill.sel.ram.ImplementationClass;
import ca.mcgill.sel.ram.ObjectType;
import ca.mcgill.sel.ram.Operation;
import ca.mcgill.sel.ram.PrimitiveType;
import ca.mcgill.sel.ram.RAMVisibilityType;
import ca.mcgill.sel.ram.RArray;
import ca.mcgill.sel.ram.RCollection;
import ca.mcgill.sel.ram.REnum;
import ca.mcgill.sel.ram.RamFactory;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.StructuralView;
import ca.mcgill.sel.ram.Type;
import ca.mcgill.sel.ram.TypeParameter;
import ca.mcgill.sel.ram.controller.ControllerFactory;
import ca.mcgill.sel.ram.loaders.RamClassLoader;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamKeyboard;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent.Cardinal;
import ca.mcgill.sel.ram.ui.components.RamTextComponent;
import ca.mcgill.sel.ram.ui.components.listeners.DefaultRamKeyboardListener;
import ca.mcgill.sel.ram.ui.views.structural.OperationView;
import ca.mcgill.sel.ram.ui.views.structural.ParameterView;
import ca.mcgill.sel.ram.util.RAMModelUtil;

/**
 * A util class for correct creation of RAM metamodel elements. Also provide getters for these elements and makes sure
 * they are well formed.
 * 
 * @author vbonnet
 * @author eyildirim
 * @author mschoettle
 * @author oalam
 */
public final class RamModelUtils {

    private static final String GENERIC_DELIMITER_BEGIN = "<";

    private static final String GENERIC_DELIMITER_END = ">";

    private static final String ARRAY_DELIMITER_EMPTY = "[]";

    private static RamModelUtils instance;

    private Map<EStructuralFeature, Map<Object, String>> prettyPrinters;

    /**
     * Creates a new instance with default pretty printers.
     */
    private RamModelUtils() {
        prettyPrinters = new HashMap<EStructuralFeature, Map<Object, String>>();

        initializePrettyPrinters();
    }

    /**
     * Returns the singleton instance.
     * 
     * @return the singleton instance
     */
    private static RamModelUtils getInstance() {
        if (instance == null) {
            instance = new RamModelUtils();
        }
        return instance;
    }

    /**
     * Initializes the pretty printers.
     */
    private void initializePrettyPrinters() {
        // pretty printer for Visibility
        Map<Object, String> prettyPrinter = new HashMap<Object, String>();

        prettyPrinter.put(COREVisibilityType.PUBLIC, Strings.SYMBOL_PUBLIC);
        prettyPrinter.put(COREVisibilityType.CONCERN, Strings.SYMBOL_PACKAGE);
        prettyPrinter.put(RAMVisibilityType.PUBLIC, Strings.SYMBOL_PUBLIC);
        prettyPrinter.put(RAMVisibilityType.PACKAGE, Strings.SYMBOL_PACKAGE);
        prettyPrinter.put(RAMVisibilityType.PRIVATE, Strings.SYMBOL_PRIVATE);
        prettyPrinter.put(RAMVisibilityType.PROTECTED, Strings.SYMBOL_PROTECTED);

        prettyPrinters.put(CorePackage.Literals.CORE_MODEL_ELEMENT__VISIBILITY, prettyPrinter);
        prettyPrinters.put(RamPackage.Literals.OPERATION__EXTENDED_VISIBILITY, prettyPrinter);
    }

    /**
     * Returns the multiplicity representation of the association end.
     * 
     * @param associationEnd The end of interest.
     * @param isKeyIndexed Whether the feature selection is of type KeyIndexed
     * @return The multiplicity string for the association end.
     */
    public static String getMultiplicity(AssociationEnd associationEnd, boolean isKeyIndexed) {
        String upperBound;

        if (isKeyIndexed) {
            return "1";
        } else if (associationEnd.getUpperBound() == -1) {
            upperBound = "*";
        } else {
            upperBound = String.valueOf(associationEnd.getUpperBound());
        }

        if (associationEnd.getLowerBound() == associationEnd.getUpperBound()) {
            return upperBound;
        } else {
            StringBuilder builder = new StringBuilder();

            builder.append(associationEnd.getLowerBound());
            builder.append("..");
            builder.append(upperBound);

            return builder.toString();
        }
    }

    /**
     * Returns a map for pretty printing the given {@link EStructuralFeature}. Returns null if it doesn't exist.
     * 
     * @param feature
     *            the feature a pretty printer is looked for
     * @return the map for pretty printing, null if not existent
     */
    public static Map<Object, String> getPrettyPrinter(EStructuralFeature feature) {
        return getInstance().prettyPrinters.get(feature);
    }

    /**
     * Returns the {@link PrimitiveType} object with the given name. The type will be searched for in the
     * {@link StructuralView} of the active {@link ca.mcgill.sel.ram.Aspect}.
     * 
     * @param name
     *            the name of the {@link PrimitiveType} that is looked for
     * @return the {@link PrimitiveType} object that corresponds to the given name
     * @see RamModelUtils#getPrimitiveTypeByName(String, StructuralView)
     */
    public static PrimitiveType getPrimitiveTypeByName(String name) {
        return getPrimitiveTypeByName(name, RamApp.getActiveStructuralView().getStructuralView());
    }

    /**
     * Returns the {@link PrimitiveType} object with the given name that is contained in the given
     * {@link StructuralView}.
     * 
     * @param name
     *            the name of the {@link PrimitiveType} that is looked for
     * @param structuralView
     *            the {@link StructuralView} containing the types
     * @return the {@link PrimitiveType} object of the given {@link StructuralView} that corresponds to the given name
     */
    public static PrimitiveType getPrimitiveTypeByName(String name, StructuralView structuralView) {
        if (name.matches("^(.*)(\\[(\\d*)\\])$")) {
            return getArrayByName(name, true, structuralView);
        }

        for (PrimitiveType primType : RAMModelUtil.getPrimitiveTypes(structuralView)) {
            if (primType.getName() != null && primType.getName().equals(name)) {
                return primType;
            }
        }

        return null;
    }

    /**
     * Returns the type for the given name, if it exists.
     * 
     * @param name
     *            The name of the type.
     * @return The type with the given name in RamApp's active structural view; null if no such type exists.
     */
    public static Type getTypeByName(String name) {
        return getTypeByName(name, RamApp.getActiveStructuralView().getStructuralView());
    }

    /**
     * Returns the type for the given name, if it exists, of the given structural view.
     * 
     * @param name
     *            The name of the type to get.
     * @param structuralView
     *            The structural view of interest
     * @return The type associated with that name, null if no such type exists.
     */
    public static Type getTypeByName(String name, StructuralView structuralView) {
        if ("void".equals(name)) {
            return RAMModelUtil.getVoidType(structuralView);
        }

        if (name.matches("^(.*)(\\[(\\d*)\\])$")) {
            return getArrayByName(name, false, structuralView);
        }

        // if instance class name
        if (!name.contains(".")) {
            // TODO: find a better way
            // handle collections special for now
            if (name.contains(GENERIC_DELIMITER_BEGIN)) {
                // this will result in the type at index 0 and the typed parameter in index 1
                String[] parameterizedType = name.split("<|>");

                // get the type
                RCollection type = null;
                for (Type currentType : structuralView.getTypes()) {
                    // TODO: once the name of collections is not set by OCL in the way of Set<String>
                    // instead of name just the type name has to be used (parameterizedType[0])
                    if (currentType.getName().equals(name)) {
                        type = (RCollection) currentType;
                    }
                }

                // if the type hasn't been found we have to create it
                if (type == null) {
                    EClass typeClass = (EClass) RamPackage.eINSTANCE.getEClassifier("R" + parameterizedType[0]);

                    // the requested type doesn't exist
                    if (typeClass == null) {
                        return null;
                    }

                    // create a new instance of the type
                    type = (RCollection) RamFactory.eINSTANCE.create(typeClass);

                    // this ensures that we also find primitive types
                    ObjectType collectionType = (ObjectType) getTypeByName(parameterizedType[1], structuralView);

                    // if the class doesn't exist we cannot create the type
                    if (collectionType == null) {
                        return null;
                    }

                    type.setType(collectionType);
                    structuralView.getTypes().add(type);
                }

                return type;
            }

            for (PrimitiveType primType : RAMModelUtil.getPrimitiveTypes(structuralView)) {
                if (primType.getName().equals(name)) {
                    return primType;
                }
            }

            for (Classifier clazz : structuralView.getClasses()) {
                if (clazz.getName() != null && clazz.getName().equals(name)) {
                    return clazz;
                }
            }

            // Handle classes and enums from extended aspects.
            Set<Aspect> extendedAspects = RAMModelUtil.collectExtendedAspects((Aspect) structuralView.eContainer());

            for (Aspect aspect : extendedAspects) {
                for (Classifier clazz : aspect.getStructuralView().getClasses()) {
                    if (clazz.getName() != null && clazz.getName().equals(name)) {
                        return clazz;
                    }
                }

                for (Type type : aspect.getStructuralView().getTypes()) {
                    if (type.eClass() == RamPackage.Literals.RENUM) {
                        if (type.getName() != null && type.getName().equals(name)) {
                            return type;
                        }
                    }
                }
            }
        } else {
            if ("java.lang.String".equals(name)) {
                return RamModelUtils.getTypeByName("String");
            }
            for (Classifier clazz : structuralView.getClasses()) {
                if (clazz instanceof ImplementationClass) {
                    if (name.equals(getClassNameWithGenerics(clazz))) {
                        return clazz;
                    }
                }
            }
            for (Type type : structuralView.getTypes()) {
                if (type instanceof REnum) {
                    if (((REnum) type).getInstanceClassName() != null) {
                        if (name.equals(((REnum) type).getInstanceClassName())) {
                            return type;
                        }
                    }
                }
            }
        }

        return null;
    }

    /**
     * Returns the existing array of the structural view if it exists.
     * Otherwise it creates a new array with the appropriate properties,
     * adds it to the structural view and returns it.
     * The array size may also be specified, but is optional.
     * 
     * @param name the fully qualified name of the array (e.g., int[])
     * @param primitiveTypesOnly whether only primitive types as array type are allowed
     * @param structuralView the {@link StructuralView} of interest
     * @return the {@link RArray} corresponding to the given name,
     *         null if the primitive types only constraint is violated
     */
    private static RArray getArrayByName(String name, boolean primitiveTypesOnly, StructuralView structuralView) {
        RArray type = null;

        for (Type currentType : structuralView.getTypes()) {
            if (currentType.getName().equals(name)) {
                type = (RArray) currentType;
            }
        }

        if (type == null) {
            int arrayIndex = name.lastIndexOf('[');
            String arrayTypeName = name.substring(0, arrayIndex);
            String sizeString = name.substring(arrayIndex).replaceAll("\\[|\\]", "");

            ObjectType arrayType = (ObjectType) getTypeByName(arrayTypeName, structuralView);

            if (!(arrayType instanceof PrimitiveType) && primitiveTypesOnly) {
                return null;
            }

            type = RamFactory.eINSTANCE.createRArray();
            type.setType(arrayType);

            if (!sizeString.isEmpty()) {
                type.setSize(Integer.parseInt(sizeString));
            }

            structuralView.getTypes().add(type);
        }

        return type;
    }

    /**
     * Extract the class name with generic parameters of an ObjectType.
     * 
     * @param otype some ObjectType
     * @return the class name with generics
     */
    public static String getClassNameWithGenerics(ObjectType otype) {
        String name = otype.getName();
        // if implementation class but not a primitive type
        if (otype instanceof ImplementationClass && (!(otype instanceof PrimitiveType) || otype instanceof REnum)) {
            if (((ImplementationClass) otype).getInstanceClassName() != null) {
                name = ((ImplementationClass) otype).getInstanceClassName();
            }
        }
        if (otype instanceof Classifier) {
            // if has type parameters
            EList<TypeParameter> typeParams = ((Classifier) otype).getTypeParameters();
            if (typeParams.size() != 0) {
                name += GENERIC_DELIMITER_BEGIN;
                // go through every type parameter
                for (int i = 0; i < typeParams.size(); i++) {
                    TypeParameter tp = typeParams.get(i);
                    String tpName = tp.getName();
                    ObjectType genericType = tp.getGenericType();

                    // if implementation class or implementation enum extract name
                    if (genericType instanceof ImplementationClass || genericType instanceof REnum) {
                        if (((ImplementationClass) genericType).getInstanceClassName() != null) {
                            tpName = getClassNameWithGenerics(genericType);
                        }
                    } else if (genericType instanceof RArray) {
                        tpName = getClassNameWithGenerics(((RArray) genericType).getType()) + ARRAY_DELIMITER_EMPTY;
                    } else if (genericType instanceof PrimitiveType
                            || genericType instanceof ca.mcgill.sel.ram.Class) {
                        tpName = genericType.getName();
                    }
                    // add to name
                    name += tpName;
                    // add comma if not last one
                    if (i != typeParams.size() - 1) {
                        name += ",";
                    }
                }
                name += GENERIC_DELIMITER_END;
            }
        }

        return name;
    }

    /**
     * Returns the {@link RAMVisibilityType} from the given string.
     * 
     * @param stringRepresentation
     *            The string representation of the visibility type.
     * @return The visibility type associated with the representation, null if none exists.
     */
    public static RAMVisibilityType getRamVisibilityFromStringRepresentation(String stringRepresentation) {
        RAMVisibilityType result = null;

        if (Strings.SYMBOL_PUBLIC.equals(stringRepresentation)) {
            result = RAMVisibilityType.PUBLIC;
        } else if (Strings.SYMBOL_PACKAGE.equals(stringRepresentation)) {
            result = RAMVisibilityType.PACKAGE;
        } else if (Strings.SYMBOL_PRIVATE.equals(stringRepresentation)) {
            result = RAMVisibilityType.PRIVATE;
        } else if (Strings.SYMBOL_PROTECTED.equals(stringRepresentation)) {
            result = RAMVisibilityType.PROTECTED;
        }

        return result;
    }

    /**
     * Get the name of a specified type. Replaces all type parameters by the type parameters of the owner.
     * 
     * @param type some type
     * @param owner ImplementationClass that uses this type as return type or parameter.
     * @return name of the type
     */
    public static String getNameOfType(java.lang.reflect.Type type, ImplementationClass owner) {
        // get name
        String name = type.toString();

        // if it is a parameterized type get all type parameters
        if (type instanceof ParameterizedType) {
            // get raw name
            ParameterizedType pType = (ParameterizedType) type;
            name = pType.getRawType().toString().replaceAll("(interface|class|enum) ", "");

            // get all generics
            java.lang.reflect.Type[] generics = pType.getActualTypeArguments();
            if (generics.length != 0) {
                name += GENERIC_DELIMITER_BEGIN;
                for (int i = 0; i < generics.length; i++) {
                    // if also a parameterized type recursively get the name
                    if (generics[i] instanceof WildcardType) {
                        name += "?";
                    } else {
                        name += getNameOfType(generics[i], owner);
                    }

                    if (i != generics.length - 1) {
                        name += ",";
                    }
                }
                name += GENERIC_DELIMITER_END;
            }
        } else if (type instanceof GenericArrayType) {
            name = getNameOfType(((GenericArrayType) type).getGenericComponentType(), owner) + ARRAY_DELIMITER_EMPTY;
        } else if (isGenericOf(owner, name)) {
            name = RamModelUtils.getClassNameWithGenerics(getGenericOf(owner, name).getGenericType());
        } else if (type instanceof Class) {
            if (((Class<?>) type).isArray()) {
                name = getNameOfType(((Class<?>) type).getComponentType(), owner) + ARRAY_DELIMITER_EMPTY;
            } else {
                name = ((Class<?>) type).getCanonicalName();
            }
        }
        // Replace java.lang.String to String
        return name.replaceAll("java.lang.String", "String");
    }

    /**
     * Gets the TypeParameter of some ImplementationClass given the type parameter name.
     * 
     * @param owner of the type parameter
     * @param typeName name of type parameter
     * @return TypeParameter with the specified name
     */
    public static TypeParameter getGenericOf(ImplementationClass owner, String typeName) {
        for (TypeParameter tp : owner.getTypeParameters()) {
            if (typeName.equals(tp.getName())) {
                return tp;
            }
        }
        return null;
    }

    /**
     * Checks if ImplementationClass has a generic with the specified type parameter name.
     * 
     * @param owner Some ImplementationClass.
     * @param typeName Type parameter name
     * @return true if owner contains the typeName, false otherwise
     */
    public static boolean isGenericOf(ImplementationClass owner, String typeName) {
        return getGenericOf(owner, typeName) != null;
    }

    /**
     * Returns a set of implementation classes that exist in the structural view
     * and have the given class as the super type.
     * Returns an empty set if none found.
     * 
     * @param structuralView the {@link StructuralView} containing classes
     * @param className the fully qualified name of the potential super class
     * @return the set of existing implementation classes that are a subtype of the given class, empty set if none found
     */
    public static Set<ImplementationClass> getExistingSubTypesFor(StructuralView structuralView, String className) {
        Set<ImplementationClass> result = new HashSet<ImplementationClass>();

        Collection<ImplementationClass> classes =
                EcoreUtil.getObjectsByType(structuralView.getClasses(), RamPackage.Literals.IMPLEMENTATION_CLASS);

        for (ImplementationClass classifier : classes) {
            Set<String> superTypes = RamClassLoader.INSTANCE.getAllSuperClassesFor(classifier.getInstanceClassName());
            if (superTypes.contains(className)) {
                result.add(classifier);
            }
        }

        return result;
    }

    /**
     * Add a parameter at the end of an operation.
     * It add the placeholder in the view and in the model
     * 
     * @param operationView - the related {@link OperationView}
     */
    public static void createParameterEndOperation(final OperationView operationView) {
        final RamTextComponent textRow = new RamTextComponent();
        textRow.setBufferSize(Cardinal.SOUTH, OperationView.BUFFER_BOTTOM);
        textRow.setPlaceholderText(Strings.PH_PARAM);

        int visualIndex = operationView.getChildCount() - 1;
        operationView.addChild(visualIndex, textRow);

        final int nbParameters = operationView.getOperation().getParameters().size();

        // visual index for delimiter; after if it is the first, before otherwise
        final int delimiterIndex = visualIndex;

        if (nbParameters > 0) {
            operationView.addDelimiter(delimiterIndex);
        }
        final MTComponent delimiter = operationView.getChildByIndex(delimiterIndex);

        RamKeyboard keyboard = new RamKeyboard();

        keyboard.registerListener(new DefaultRamKeyboardListener() {
            @Override
            public void keyboardCancelled() {
                operationView.removeChild(textRow);
                if (delimiter != null) {
                    operationView.removeChild(delimiter);
                }
            }

            @Override
            public boolean verifyKeyboardDismissed() {
                try {
                    int nbParameters = operationView.getOperation().getParameters().size();
                    createParameterInModel(operationView.getOperation(), nbParameters, textRow.getText());
                } catch (final IllegalArgumentException e) {
                    return false;
                }
                if (delimiter != null) {
                    operationView.removeChild(delimiter);
                }
                operationView.removeChild(textRow);

                return true;
            }
        });

        textRow.showKeyboard(keyboard);
    }

    /**
     * Add a parameter around another parameter. It can be on the left or on the right.
     * It add the placeholder in the view and in the model
     * 
     * @param left - true if the parameter has to be on the left of the given parameter.
     * @param parameterView - the related {@link ParameterView}
     */
    public static void createParameterAroundParameter(final boolean left, final ParameterView parameterView) {
        final OperationView operationView = (OperationView) parameterView.getParent();
        final RamTextComponent textRow = new RamTextComponent();
        textRow.setBufferSize(Cardinal.SOUTH, OperationView.BUFFER_BOTTOM);
        textRow.setPlaceholderText(Strings.PH_PARAM);

        int parameterViewIndex = operationView.getChildIndexOf(parameterView);
        int visualIndex = left ? parameterViewIndex : parameterViewIndex + 1;
        operationView.addChild(visualIndex, textRow);

        // visual index for delimiter; after if it is the first, before otherwise
        final int delimiterIndex = !left ? visualIndex : visualIndex + 1;
        operationView.addDelimiter(delimiterIndex);
        final MTComponent delimiter = operationView.getChildByIndex(delimiterIndex);

        RamKeyboard keyboard = new RamKeyboard();

        keyboard.registerListener(new DefaultRamKeyboardListener() {
            @Override
            public void keyboardCancelled() {
                operationView.removeChild(textRow);
                operationView.removeChild(delimiter);
            }

            @Override
            public boolean verifyKeyboardDismissed() {
                try {
                    int currentModelIndex =
                            operationView.getOperation().getParameters().indexOf(parameterView.getParameter());
                    int modelIndex = left ? currentModelIndex : currentModelIndex + 1;
                    createParameterInModel(operationView.getOperation(), modelIndex, textRow.getText());
                } catch (final IllegalArgumentException e) {
                    return false;
                }

                operationView.removeChild(delimiter);
                operationView.removeChild(textRow);

                return true;
            }
        });

        textRow.showKeyboard(keyboard);
    }

    /**
     * Creates a new parameter in the model.
     * 
     * @param owner the operation the parameter should be added to
     * @param index the index in the list of parameters where the parameter should be added at
     * @param parameterString the string representation of the parameter (e.g., "&lt;type> &lt;parameterName>)
     */
    private static void createParameterInModel(Operation owner, int index, String parameterString) {
        Matcher matcher =
                Pattern.compile("^" + MetamodelRegex.REGEX_PARAMETER_DECLARATION + "$").matcher(parameterString);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("The string " + parameterString
                    + " does not conform to parameter syntax");
        }

        Type type = RamModelUtils.getTypeByName(matcher.group(1));
        String nameString = matcher.group(3);
        if (type == null) {
            throw new IllegalArgumentException("No type with that name exists");
        }

        if (nameString == null) {
            throw new IllegalArgumentException("Parameter name did not match naming syntax");
        }

        ControllerFactory.INSTANCE.getClassController().createParameter(owner, index, nameString, type);
    }

}
