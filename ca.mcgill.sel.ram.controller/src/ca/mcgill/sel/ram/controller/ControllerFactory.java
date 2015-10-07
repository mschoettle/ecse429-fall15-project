package ca.mcgill.sel.ram.controller;

/**
 * A factory to obtain all controllers.
 * 
 * @author mschoettle
 */
public final class ControllerFactory {
    
    /**
     * The singleton instance of this factory.
     */
    public static final ControllerFactory INSTANCE = new ControllerFactory();
    
    private AspectController aspectController;
    private InstantiationController instantiationController;
    private StructuralViewController structuralViewController;
    private ClassController classController;
    private EnumController enumController;
    private AssociationController associationController;
    private ImplementationClassController implementationClassController;
    
    private StateViewController stateViewController;
    private StateMachineViewController stateMachineViewController;
    private StateController stateController;
    private StateViewViewController stateViewViewController;
    private TransitionController transitionController;
    
    private MessageViewController messageViewController;
    private MessageController messageController;
    private FragmentsController interactionFragmentsController;

    /**
     * Creates a new instance of {@link ControllerFactory}.
     */
    private ControllerFactory() {
        
    }
    
    /**
     * Returns the controller for {@link ca.mcgill.sel.ram.Aspect}s.
     * 
     * @return the controller for {@link ca.mcgill.sel.ram.Aspect}s
     */
    public AspectController getAspectController() {
        if (aspectController == null) {
            aspectController = new AspectController();
        }
        
        return aspectController;
    }
    
    /**
     * Returns the controller for {@link ca.mcgill.sel.ram.Instantiation}s.
     * 
     * @return the controller for {@link ca.mcgill.sel.ram.Instantiation}s.
     */
    public InstantiationController getInstantiationController() {
        if (instantiationController == null) {
            instantiationController = new InstantiationController();
        }
        
        return instantiationController;
    }
    
    /**
     * Returns the controller for {@link ca.mcgill.sel.ram.StructuralView}s.
     * 
     * @return the controller for {@link ca.mcgill.sel.ram.StructuralView}s
     */
    public StructuralViewController getStructuralViewController() {
        if (structuralViewController == null) {
            structuralViewController = new StructuralViewController();
        }
        
        return structuralViewController;
    }
    
    /**
     * Returns the controller for {@link ca.mcgill.sel.ram.Class}s.
     * 
     * @return the controller for {@link ca.mcgill.sel.ram.Class}s.
     */
    public ClassController getClassController() {
        if (classController == null) {
            classController = new ClassController();
        }
        
        return classController;
    }
    
    /**
     * Returns the controller for {@link ca.mcgill.sel.ram.REnum}s.
     * 
     * @return the controller for {@link ca.mcgill.sel.ram.REnum}s.
     */
    public EnumController getEnumController() {
        if (enumController == null) {
            enumController = new EnumController();
        }
        
        return enumController;
    }
    
    /**
     * Returns the controller for {@link ca.mcgill.sel.ram.Association}s
     * and {@link ca.mcgill.sel.ram.AssociationEnd}s.
     * 
     * @return the controller for {@link ca.mcgill.sel.ram.Association}s
     *         and {@link ca.mcgill.sel.ram.AssociationEnd}s
     */
    public AssociationController getAssociationController() {
        if (associationController == null) {
            associationController = new AssociationController();
        }
        
        return associationController;
    }
    
    /**
     * Returns the controller for {@link ca.mcgill.sel.ram.StateMachine}s.
     * 
     * @return the controller for {@link ca.mcgill.sel.ram.StateMachine}s
     */
    public StateMachineViewController getStateMachineViewController() {
        if (stateMachineViewController == null) {
            stateMachineViewController = new StateMachineViewController();
        }
        
        return stateMachineViewController;
    }
    
    /**
     * Returns the controller for {@link ca.mcgill.sel.ram.State}s.
     * 
     * @return the controller for {@link ca.mcgill.sel.ram.State}s.
     */
    public StateController getStateController() {
        if (stateController == null) {
            stateController = new StateController();
        }
        
        return stateController;
    }
    
    /**
     * Returns the controller for {@link ca.mcgill.sel.ram.StateView}s.
     * 
     * @return the controller for {@link ca.mcgill.sel.ram.StateView}s
     */
    public StateViewController getStateViewController() {
        
        if (stateViewController == null) {
            stateViewController = new StateViewController();
        }
        
        return stateViewController;
    }
    
    /**
     * Returns the controller for {@link ca.mcgill.sel.ram.StateMachine}s.
     * 
     * @return the controller for {@link ca.mcgill.sel.ram.StateMachine}s
     */
    public StateViewViewController getStateViewViewController() {
        if (stateViewViewController == null) {
            stateViewViewController = new StateViewViewController();
        }
        
        return stateViewViewController;
    }
    
    /**
     * Returns the controller for {@link ca.mcgill.sel.ram.Transition}s.
     * 
     * @return the controller for {@link ca.mcgill.sel.ram.Transition}s
     */
    public TransitionController getTransitionController() {
        if (transitionController == null) {
            transitionController = new TransitionController();
        }
        
        return transitionController;
    }
    
    /**
     * Returns the controller for {@link ca.mcgill.sel.ram.AbstractMessageView}s.
     * 
     * @return the controller for {@link ca.mcgill.sel.ram.AbstractMessageView}s
     */
    public MessageViewController getMessageViewController() {
        if (messageViewController == null) {
            messageViewController = new MessageViewController();
        }
        
        return messageViewController;
    }
    
    /**
     * Returns the controller for {@link ca.mcgill.sel.ram.Message}s.
     * 
     * @return the controller for {@link ca.mcgill.sel.ram.Message}s
     */
    public MessageController getMessageController() {
        if (messageController == null) {
            messageController = new MessageController();
        }
        
        return messageController;
    }
    
    /**
     * Returns the controller for {@link ca.mcgill.sel.ram.InteractionFragment}s.
     * 
     * @return the controller for {@link ca.mcgill.sel.ram.InteractionFragment}s
     */
    public FragmentsController getFragmentsController() {
        if (interactionFragmentsController == null) {
            interactionFragmentsController = new FragmentsController();
        }
        return interactionFragmentsController;
    }
    
    /**
     * Returns the controller for {@link ca.mcgill.sel.ram.ImplementationClass}es.
     * 
     * @return the controller for {@link ca.mcgill.sel.ram.ImplementationClass}es.
     */
    public ImplementationClassController getImplementationClassController() {
        if (implementationClassController == null) {
            implementationClassController = new ImplementationClassController();
        }
        
        return implementationClassController;
    }
    
}
