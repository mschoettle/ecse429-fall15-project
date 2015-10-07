package ca.mcgill.sel.core.controller;

/**
 * A factory to obtain all controllers related to CORE.
 *
 * @author mschoettle
 */
public final class ControllerFactory {

    /**
     * The singleton instance of this factory.
     */
    public static final ControllerFactory INSTANCE = new ControllerFactory();

    private ConcernController concernController;

    /**
     * Creates a new instance of {@link ControllerFactory}.
     */
    private ControllerFactory() {

    }

    /**
     * Returns the controller for {@link ca.mcgill.sel.core.COREConcern}s.
     *
     * @return the controller for {@link ca.mcgill.sel.core.COREConcern}s
     */
    public ConcernController getConcernController() {
        if (concernController == null) {
            concernController = new ConcernController();
        }
        return concernController;
    }

}
