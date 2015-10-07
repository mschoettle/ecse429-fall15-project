package ca.mcgill.sel.ram.ui.views.handler;

import ca.mcgill.sel.ram.ui.scenes.handler.IRamAbstractSceneHandler;
import ca.mcgill.sel.ram.ui.scenes.handler.ISelectSceneHandler;
import ca.mcgill.sel.ram.ui.scenes.handler.impl.ConcernEditSceneHandler;
import ca.mcgill.sel.ram.ui.scenes.handler.impl.DefaultRamSceneHandler;
import ca.mcgill.sel.ram.ui.scenes.handler.impl.DisplayAspectSceneHandler;
import ca.mcgill.sel.ram.ui.scenes.handler.impl.SelectSceneHandler;
import ca.mcgill.sel.ram.ui.utils.MetamodelRegex;
import ca.mcgill.sel.ram.ui.views.handler.impl.TextViewHandler;
import ca.mcgill.sel.ram.ui.views.handler.impl.ValidatingTextViewHandler;
import ca.mcgill.sel.ram.ui.views.state.handler.IStateComponentViewHandler;
import ca.mcgill.sel.ram.ui.views.state.handler.IStateMachineViewHandler;
import ca.mcgill.sel.ram.ui.views.state.handler.IStateViewHandler;
import ca.mcgill.sel.ram.ui.views.state.handler.IStateViewViewHandler;
import ca.mcgill.sel.ram.ui.views.state.handler.impl.StateComponentViewHandler;
import ca.mcgill.sel.ram.ui.views.state.handler.impl.StateMachineViewHandler;
import ca.mcgill.sel.ram.ui.views.state.handler.impl.StateViewHandler;
import ca.mcgill.sel.ram.ui.views.state.handler.impl.StateViewNameHandler;
import ca.mcgill.sel.ram.ui.views.state.handler.impl.StateViewViewHandler;
import ca.mcgill.sel.ram.ui.views.state.handler.impl.TransitionNameHandler;
import ca.mcgill.sel.ram.ui.views.state.handler.impl.TransitionViewHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.IAttributeNameHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.IAttributeViewHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.IClassNameHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.IClassViewHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.IClassifierViewHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.IEnumLiteralNameHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.IEnumLiteralViewHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.IEnumNameHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.IEnumViewHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.IInstantiationNameHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.IInstantiationViewHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.IInstantiationsContainerViewHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.IInstantiationsPanelHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.IMappingContainerViewHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.IOperationNameHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.IOperationViewHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.IParameterViewHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.IStructuralViewHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.impl.AspectExtensionsContainerViewHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.impl.AssociationFeatureSelectionHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.impl.AssociationMultiplicityHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.impl.AssociationRoleNameHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.impl.AssociationViewHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.impl.AttributeNameHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.impl.AttributeViewHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.impl.ClassNameHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.impl.ClassViewHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.impl.ClassifierVisibilityViewHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.impl.EnumLiteralNameHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.impl.EnumLiteralViewHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.impl.EnumNameHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.impl.EnumViewHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.impl.ImplementationClassViewHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.impl.InheritanceViewHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.impl.InstantiationDefaultNameHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.impl.InstantiationExtendNameHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.impl.InstantiationViewHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.impl.InstantiationsPanelHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.impl.MappingContainerViewHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.impl.OperationNameHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.impl.OperationViewHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.impl.OperationVisibilityViewHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.impl.ParameterViewHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.impl.StructuralViewHandler;

/**
 * A factory to obtain all (default) handlers.
 * 
 * @author mschoettle
 */
public final class HandlerFactory {

    /**
     * The singleton instance of this factory.
     */
    public static final HandlerFactory INSTANCE = new HandlerFactory();

    private ITextViewHandler textViewHandler;
    private ISelectSceneHandler selectSceneHandler;
    private IDisplaySceneHandler displayAspectSceneHandler;
    private ConcernEditSceneHandler displayConcernFMSceneHandler;
    private IStructuralViewHandler structuralViewHandler;
    private IAttributeViewHandler attributeViewHandler;
    private IOperationViewHandler operationViewHandler;
    private IClassViewHandler classViewHandler;
    private IClassNameHandler classNameHandler;
    private IOperationNameHandler operationNameHandler;
    private ITextViewHandler parameterNameHandler;
    private IAttributeNameHandler attributeNameHandler;
    private ITextViewHandler associationMultiplicityHandler;
    private ITextViewHandler associationRoleNameHandler;
    private ITextViewHandler associationFeatureSelectionHandler;
    private IRelationshipViewHandler associationViewHandler;
    private IRelationshipViewHandler inheritanceViewHandler;
    private IInstantiationsPanelHandler instantiationsPanelHandler;
    private IInstantiationsContainerViewHandler instantiationsContainerViewHandler;
    private IInstantiationViewHandler instantiationViewHandler;
    private IMappingContainerViewHandler mappingContainerViewHandler;
    private IInstantiationNameHandler instantiationNameHandler;
    private IInstantiationNameHandler instantiationExtendNameHandler;
    private IStateComponentViewHandler stateComponentViewHandler;
    private IRelationshipViewHandler transitionViewHandler;
    private IStateMachineViewHandler stateMachineViewHandler;
    private IStateViewHandler stateViewHandler;
    private IStateViewViewHandler stateViewViewHandler;
    private IClassifierViewHandler implementationClassViewHandler;
    private IClassNameHandler stateViewNameHandler;
    private IClassNameHandler transitionNameHandler;
    private IParameterViewHandler parameterHandler;
    private IEnumLiteralNameHandler enumLiteralNameHandler;
    private IEnumLiteralViewHandler enumLiteralViewHandler;
    private IEnumNameHandler enumNameHandler;
    private IEnumViewHandler enumViewHandler;
    private IEnumNameHandler impEnumNameHandler;
    private OperationVisibilityViewHandler operationVisibilityHandler;
    private ClassifierVisibilityViewHandler classifierVisibilityHandler;
    
    private DefaultAbstractViewHandler featureDiagramSelectHandler;

    private IRamAbstractSceneHandler defaultRamSceneHandler;

    /**
     * Creates a new instance.
     */
    private HandlerFactory() {

    }

    /**
     * Returns the default handler for the association multiplicity.
     * 
     * @return the default {@link IAssociationMultiplicityHandler}
     */
    public ITextViewHandler getAssociationMultiplicityHandler() {
        if (associationMultiplicityHandler == null) {
            associationMultiplicityHandler = new AssociationMultiplicityHandler();
        }

        return associationMultiplicityHandler;
    }

    /**
     * Returns the default handler for the association role name.
     * 
     * @return the default {@link ca.mcgill.sel.ram.ui.views.structural.handler.IAssociationRoleNameHandler}
     */
    public ITextViewHandler getAssociationRoleNameHandler() {
        if (associationRoleNameHandler == null) {
            associationRoleNameHandler = new AssociationRoleNameHandler();
        }

        return associationRoleNameHandler;
    }

    /**
     * Returns the default handler for operation visibility.
     * 
     * @return the default {@link OperationVisibilityHandler}
     */
    public ITextViewHandler getOperationVisibilityHandlerHandler() {
        if (operationVisibilityHandler == null) {
            operationVisibilityHandler = new OperationVisibilityViewHandler();
        }

        return operationVisibilityHandler;
    }

    /**
     * Returns the default handler for classifier visibility.
     * 
     * @return the default {@link ClassifierVisibilityViewHandler}
     */
    public ITextViewHandler getClassifierVisibilityHandler() {
        if (classifierVisibilityHandler == null) {
            classifierVisibilityHandler = new ClassifierVisibilityViewHandler();
        }

        return classifierVisibilityHandler;
    }

    /**
     * Returns the default handler for parameter.
     * 
     * @return the default {@link ParameterViewHandler}
     */
    public IParameterViewHandler getParameterHandler() {
        if (parameterHandler == null) {
            parameterHandler = new ParameterViewHandler();
        }

        return parameterHandler;
    }

    /**
     * Returns the default handler for the association feature selection.
     * 
     * @return the default {@link IAssociationFeatureSelectionHandler}
     */
    public ITextViewHandler getAssociationFeatureSelectionHandler() {
        if (associationFeatureSelectionHandler == null) {
            associationFeatureSelectionHandler = new AssociationFeatureSelectionHandler();
        }

        return associationFeatureSelectionHandler;
    }

    /**
     * Returns the default handler for an association view.
     * 
     * @return the default {@link IAssociationViewHandler}
     */
    public IRelationshipViewHandler getAssociationViewHandler() {
        if (associationViewHandler == null) {
            associationViewHandler = new AssociationViewHandler();
        }

        return associationViewHandler;
    }

    /**
     * Returns the default handler for an attribute name text view.
     * 
     * @return the default {@link IAttributeNameHandler}
     */
    public IAttributeNameHandler getAttributeNameHandler() {
        if (attributeNameHandler == null) {
            attributeNameHandler = new AttributeNameHandler();
        }

        return attributeNameHandler;
    }

    /**
     * Returns the default handler for an attribute.
     * 
     * @return the default {@link IAttributeViewHandler}
     */
    public IAttributeViewHandler getAttributeViewHandler() {
        if (attributeViewHandler == null) {
            attributeViewHandler = new AttributeViewHandler();
        }

        return attributeViewHandler;
    }

    /**
     * Returns the default handler for a class name text view.
     * 
     * @return the default {@link IClassNameHandler}
     */
    public IClassNameHandler getClassNameHandler() {
        if (classNameHandler == null) {
            classNameHandler = new ClassNameHandler();
        }

        return classNameHandler;
    }

    /**
     * Returns the default handler for a name text view.
     * 
     * @return the default {@link IClassNameHandler}
     */
    public IClassNameHandler getStateViewNameHandler() {
        if (stateViewNameHandler == null) {
            stateViewNameHandler = new StateViewNameHandler();
        }

        return stateViewNameHandler;
    }

    /**
     * Returns the default handler for a class.
     * 
     * @return the default {@link IClassViewHandler}
     */
    public IClassViewHandler getClassViewHandler() {
        if (classViewHandler == null) {
            classViewHandler = new ClassViewHandler();
        }

        return classViewHandler;
    }

    /**
     * Returns the default handler for a display aspect scene.
     * 
     * @return the default {@link IDisplaySceneHandler}
     */
    public IDisplaySceneHandler getDisplayAspectSceneHandler() {
        if (displayAspectSceneHandler == null) {
            displayAspectSceneHandler = new DisplayAspectSceneHandler();
        }

        return displayAspectSceneHandler;
    }

    /**
     * Returns the default handler for a display concern scene.
     * 
     * @return DisplayConcernFMSceneHandler
     */
    public ConcernEditSceneHandler getDisplayConcernFMSceneHandler() {
        if (displayConcernFMSceneHandler == null) {
            displayConcernFMSceneHandler = new ConcernEditSceneHandler();
        }

        return displayConcernFMSceneHandler;
    }

    /**
     * Returns the default handler for an implementation class.
     * 
     * @return the default {@link ca.mcgill.sel.ram.ImplementationClass}
     */
    public IClassifierViewHandler getImplementationClassViewHandler() {
        if (implementationClassViewHandler == null) {
            implementationClassViewHandler = new ImplementationClassViewHandler();
        }

        return implementationClassViewHandler;
    }

    /**
     * Returns the default handler for an inheritance view.
     * 
     * @return the default handler for an {@link ca.mcgill.sel.ram.ui.views.structural.InheritanceView}
     */
    public IRelationshipViewHandler getInheritanceViewHandler() {
        if (inheritanceViewHandler == null) {
            inheritanceViewHandler = new InheritanceViewHandler();
        }

        return inheritanceViewHandler;
    }

    /**
     * Returns the default handler for an instantiation name text view.
     * 
     * @return the default {@link ca.mcgill.sel.ram.ui.views.structural.IOperationNameHandler}
     */
    public IInstantiationNameHandler getInstantiationExtendNameHandler() {
        if (instantiationExtendNameHandler == null) {
            instantiationExtendNameHandler = new InstantiationExtendNameHandler();
        }

        return instantiationExtendNameHandler;
    }

    /**
     * Returns the default handler for an instantiation name text view.
     * 
     * @return the default {@link ca.mcgill.sel.ram.ui.views.structural.IOperationNameHandler}
     */
    public IInstantiationNameHandler getInstantiationDefaultNameHandler() {
        if (instantiationNameHandler == null) {
            instantiationNameHandler = new InstantiationDefaultNameHandler();
        }

        return instantiationNameHandler;
    }
    
    /**
     * Returns the default handler for the panel containing views of instantiations.
     * 
     * @return the default handler for {@link ca.mcgill.sel.ram.ui.views.structural.InstantiationsPanel}
     */
    public IInstantiationsPanelHandler getInstantiationsPanelHandler() {
        if (instantiationsPanelHandler == null) {
            instantiationsPanelHandler = new InstantiationsPanelHandler();
        }
        return instantiationsPanelHandler;
    }

    /**
     * Returns the default handler for the container view of instantiations.
     * 
     * @return the default handler for {@link ca.mcgill.sel.ram.ui.views.structural.InstantiationsContainerView}
     */
    public IInstantiationsContainerViewHandler getAspectExtensionsContainerViewHandler() {
        if (instantiationsContainerViewHandler == null) {
            instantiationsContainerViewHandler = new AspectExtensionsContainerViewHandler();
        }
        return instantiationsContainerViewHandler;
    }

    /**
     * Returns the default handler for {@link ca.mcgill.sel.ram.ui.views.structural.InstantiationView}.
     * 
     * @return the defaul {@link ca.mcgill.sel.ram.ui.views.structural.IInstantiationViewHandler}
     */
    public IInstantiationViewHandler getInstantiationViewHandler() {

        if (instantiationViewHandler == null) {
            instantiationViewHandler = new InstantiationViewHandler();
        }

        return instantiationViewHandler;
    }

    /**
     * Returns the default handler for a mapping container view.
     * 
     * @return the default {@link IMappingContainerViewHandler}
     */
    public IMappingContainerViewHandler getMappingContainerViewHandler() {
        if (mappingContainerViewHandler == null) {
            mappingContainerViewHandler = new MappingContainerViewHandler();
        }

        return mappingContainerViewHandler;
    }

    /**
     * Returns the default handler for an operation name text view.
     * 
     * @return the default {@link IOperationNameHandler}
     */
    public IOperationNameHandler getOperationNameHandler() {
        if (operationNameHandler == null) {
            operationNameHandler = new OperationNameHandler();
        }

        return operationNameHandler;
    }

    /**
     * Returns the default handler for an operation.
     * 
     * @return the default {@link IOperationViewHandler}
     */
    public IOperationViewHandler getOperationViewHandler() {
        if (operationViewHandler == null) {
            operationViewHandler = new OperationViewHandler();
        }

        return operationViewHandler;
    }

    /**
     * Returns the default handler for a parameter name text view.
     * 
     * @return the default {@link ITextViewHandler}
     */
    public ITextViewHandler getParameterNameHandler() {
        if (parameterNameHandler == null) {
            parameterNameHandler = new ValidatingTextViewHandler(MetamodelRegex.REGEX_TYPE_NAME);
        }

        return parameterNameHandler;
    }
    
    /**
     * Returns the default handler for a select aspect scene.
     * 
     * @return the default {@link ISelectSceneHandler}
     */
    public ISelectSceneHandler getSelectSceneHandler() {
        if (selectSceneHandler == null) {
            selectSceneHandler = new SelectSceneHandler();
        }

        return selectSceneHandler;
    }

    /**
     * Returns the default handler for a state view.
     * 
     * @return the default {@link IStateComponentViewHandler}
     */
    public IStateComponentViewHandler getStateComponentViewHandler() {
        if (stateComponentViewHandler == null) {
            stateComponentViewHandler = new StateComponentViewHandler();
        }

        return stateComponentViewHandler;
    }

    /**
     * Returns the default handler for a state machine view.
     * 
     * @return the default {@link IStateMachineViewHandler}
     */
    public IStateMachineViewHandler getStateMachineViewHandler() {
        if (stateMachineViewHandler == null) {
            stateMachineViewHandler = new StateMachineViewHandler();
        }

        return stateMachineViewHandler;
    }

    /**
     * Returns the default handler for a state diagram.
     * 
     * @return the default {@link IStateViewHandler}
     */
    public IStateViewHandler getStateViewHandler() {
        if (stateViewHandler == null) {
            stateViewHandler = new StateViewHandler();
        }

        return stateViewHandler;
    }

    /**
     * Returns the default handler for a StateView view.
     * 
     * @return the default {@link IStateViewViewHandler}
     */
    public IStateViewViewHandler getStateViewViewHandler() {
        if (stateViewViewHandler == null) {
            stateViewViewHandler = new StateViewViewHandler();
        }

        return stateViewViewHandler;
    }

    /**
     * Returns the default handler for a structural view.
     * 
     * @return the default {@link IStructuralViewHandler}
     */
    public IStructuralViewHandler getStructuralViewHandler() {
        if (structuralViewHandler == null) {
            structuralViewHandler = new StructuralViewHandler();
        }

        return structuralViewHandler;
    }

    /**
     * Returns the default handler for a text view.
     * 
     * @return the default {@link ITextViewHandler}
     */
    public ITextViewHandler getTextViewHandler() {
        if (textViewHandler == null) {
            textViewHandler = new TextViewHandler();
        }

        return textViewHandler;
    }

    /**
     * Returns the default handler for a text view.
     * 
     * @return the default {@link ITextViewHandler}
     */
    public IRelationshipViewHandler getTransitionViewHandler() {
        if (transitionViewHandler == null) {
            transitionViewHandler = new TransitionViewHandler();
        }

        return transitionViewHandler;
    }

    /**
     * Returns the default handler for a transition text.
     * 
     * @return the default {@link IClassNameHandler}
     */
    public IClassNameHandler getTransitionNameHandler() {
        if (transitionNameHandler == null) {
            transitionNameHandler = new TransitionNameHandler();
        }
        return transitionNameHandler;
    }

    /**
     * Returns the default handler for the name of an enum literal view.
     * 
     * @return the default {@link IEnumLiteralNameHandler}
     */
    public IEnumLiteralNameHandler getEnumLiteralNameHandler() {
        if (enumLiteralNameHandler == null) {
            enumLiteralNameHandler = new EnumLiteralNameHandler();
        }
        return enumLiteralNameHandler;
    }

    /**
     * Returns the default handler for of an enum literal view.
     * 
     * @return the default {@link IEnumLiteralViewHandler}
     */
    public IEnumLiteralViewHandler getEnumLiteralViewHandler() {
        if (enumLiteralViewHandler == null) {
            enumLiteralViewHandler = new EnumLiteralViewHandler();
        }
        return enumLiteralViewHandler;
    }

    /**
     * Returns the default handler for the name of an enum view.
     * 
     * @return the default {@link IEnumNameHandler}
     */
    public IEnumNameHandler getEnumNameHandler() {
        if (enumNameHandler == null) {
            enumNameHandler = new EnumNameHandler(false);
        }
        return enumNameHandler;
    }

    /**
     * Returns the default handler of an enum view.
     * 
     * @return the default {@link IEnumViewHandler}
     */
    public IEnumViewHandler getEnumViewHandler() {
        if (enumViewHandler == null) {
            enumViewHandler = new EnumViewHandler();
        }
        return enumViewHandler;
    }

    /**
     * Returns the default handler of an enum name view.
     * 
     * @return the default {@link IEnumNameHandler}
     */
    public IEnumNameHandler getImplementationEnumNameHandler() {
        if (impEnumNameHandler == null) {
            impEnumNameHandler = new EnumNameHandler(true);
        }
        return impEnumNameHandler;
    }

    /**
     * Returns the default handler for feature diagram in select mode.
     * 
     * @return the default {@link DefaultAbstractViewHandler}
     */
    public DefaultAbstractViewHandler getFeatureDiagramSelectHandler() {
        if (featureDiagramSelectHandler == null) {
            featureDiagramSelectHandler = new DefaultAbstractViewHandler();
        }
        return featureDiagramSelectHandler;
    }

    /**
     * Returns the default handler for scenes.
     * 
     * @return the default {@link IRamAbstractSceneHandler}
     */
    public IRamAbstractSceneHandler getDefaultRamSceneHandler() {
        if (defaultRamSceneHandler == null) {
            defaultRamSceneHandler = new DefaultRamSceneHandler();
        }
        return defaultRamSceneHandler;
    }

}
