/**
 */
package ca.mcgill.sel.ram;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Operation Visibility</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see ca.mcgill.sel.ram.RamPackage#getOperationVisibility()
 * @model
 * @generated
 */
public enum OperationVisibility implements Enumerator {
    /**
     * The '<em><b>Private</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #PRIVATE_VALUE
     * @generated
     * @ordered
     */
    PRIVATE(1, "private", "private"),

    /**
     * The '<em><b>Protected</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #PROTECTED_VALUE
     * @generated
     * @ordered
     */
    PROTECTED(2, "protected", "protected"),

    /**
     * The '<em><b>Core</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #CORE_VALUE
     * @generated
     * @ordered
     */
    CORE(0, "core", "core");

    /**
     * The '<em><b>Private</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Private</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #PRIVATE
     * @model name="private"
     * @generated
     * @ordered
     */
    public static final int PRIVATE_VALUE = 1;

    /**
     * The '<em><b>Protected</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Protected</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #PROTECTED
     * @model name="protected"
     * @generated
     * @ordered
     */
    public static final int PROTECTED_VALUE = 2;

    /**
     * The '<em><b>Core</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Core</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #CORE
     * @model name="core"
     * @generated
     * @ordered
     */
    public static final int CORE_VALUE = 0;

    /**
     * An array of all the '<em><b>Operation Visibility</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final OperationVisibility[] VALUES_ARRAY =
        new OperationVisibility[] {
            PRIVATE,
            PROTECTED,
            CORE,
        };

    /**
     * A public read-only list of all the '<em><b>Operation Visibility</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List<OperationVisibility> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Operation Visibility</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static OperationVisibility get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            OperationVisibility result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Operation Visibility</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static OperationVisibility getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            OperationVisibility result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Operation Visibility</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static OperationVisibility get(int value) {
        switch (value) {
            case PRIVATE_VALUE: return PRIVATE;
            case PROTECTED_VALUE: return PROTECTED;
            case CORE_VALUE: return CORE;
        }
        return null;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private final int value;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private final String name;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private final String literal;

    /**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private OperationVisibility(int value, String name, String literal) {
        this.value = value;
        this.name = name;
        this.literal = literal;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getValue() {
      return value;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getName() {
      return name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getLiteral() {
      return literal;
    }

    /**
     * Returns the literal value of the enumerator, which is its string representation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        return literal;
    }
    
} //OperationVisibility
