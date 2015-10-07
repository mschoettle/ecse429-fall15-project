package ca.mcgill.sel.ram.util;

/**
 * A utility class that provides constants related to the association concern.
 *
 * @author cbensoussan
 *
 */
public final class AssociationConstants {

    /**
     * The association concern name.
     */
    public static final String CONCERN_NAME = "Association";

    /**
     * The name of the data class.
     */
    public static final String DATA_CLASS_NAME = "Data";

    /**
     * The name of the associated class.
     */
    public static final String ASSOCIATED_CLASS_NAME = "Associated";

    /**
     * The name of the key class.
     */
    public static final String KEY_CLASS_NAME = "Key";

    /**
     * The name of the value class.
     */
    public static final String VALUE_CLASS_NAME = "Value";

    /**
     * Name of association end corresponding to the collection.
     */
    public static final String ASSOCIATION_END_NAME_COLLECTION = "myCollection";

    /**
     * Association name between data and collection.
     */
    public static final String ASSOCIATION_NAME_DATA_COLLECTION = "Data_Collection";

    /**
     * Creates a new instance.
     */
    private AssociationConstants() {
    }
}
