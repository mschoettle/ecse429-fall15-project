package ca.mcgill.sel.ram.ui.views.structural.wrappers;

import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.List;

import ca.mcgill.sel.ram.ImplementationClass;
import ca.mcgill.sel.ram.ui.utils.RamModelUtils;

public abstract class CallableMemberWrapper {
    
    public static final String DEFAULT_PARAM_NAME = "arg";
    protected ImplementationClass owner;
    protected int modifier;
    protected String name;
    protected List<Type> parameters;
    
    public CallableMemberWrapper(ImplementationClass owner, int modifier, String name, List<Type> parameters) {
        this.owner = owner;
        this.modifier = modifier;
        this.name = name;
        this.parameters = parameters;
    }
    
    /**
     * Getter for the owner of this constructor.
     * 
     * @return Implementation class owner of this method.
     */
    public ImplementationClass getOwner() {
        return owner;
    }
    
    /**
     * Getter for visibility.
     * 
     * @return String representing the visibility.
     */
    public String getVisibility() {
        String v = "public";
        if (Modifier.isProtected(this.modifier)) {
            v = "protected";
        } else if (Modifier.isPrivate(this.modifier)) {
            v = "private";
        }
        return v;
    }
    
    /**
     * Getter for the name of the method.
     * 
     * @return name of the method
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Getter for the parameters.
     * 
     * @return String with all the parameters
     */
    public String getParametersAsString() {
        int counter = 0;
        int numParam = this.parameters.size();
        String params = "";
        for (java.lang.reflect.Type someType : this.parameters) {
            String paramType = RamModelUtils.getNameOfType(someType, owner);
            String parameterName = DEFAULT_PARAM_NAME + counter;
            params += paramType + " " + parameterName;
            if (counter != numParam - 1) {
                params += ", ";
            }
            counter++;
        }
        return params;
    }
    
    public List<Type> getParameters() {
        return this.parameters;
    }
    
    @Override
    public String toString() {
        return getStringRepresentation();
    }
    
    protected abstract String getStringRepresentation();
    
}
