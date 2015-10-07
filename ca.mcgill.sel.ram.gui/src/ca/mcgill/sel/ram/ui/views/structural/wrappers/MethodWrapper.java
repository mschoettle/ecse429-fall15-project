package ca.mcgill.sel.ram.ui.views.structural.wrappers;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Arrays;

import ca.mcgill.sel.ram.ImplementationClass;
import ca.mcgill.sel.ram.ui.utils.RamModelUtils;

/**
 * Wrapper for method object.
 * 
 * @author Franz
 * 
 */
public class MethodWrapper extends CallableMemberWrapper {
    
    protected Type returnType;
    protected boolean isGeneric;
    
    /**
     * Constructor.
     * 
     * @param m Some method
     * @param powner Owner of the method
     */
    public MethodWrapper(Method m, ImplementationClass powner) {
        super(powner, m.getModifiers(), m.getName(), Arrays.asList(m.getGenericParameterTypes()));
        returnType = m.getGenericReturnType();
        isGeneric = m.getTypeParameters().length != 0;
    }
    
    @Override
    protected String getStringRepresentation() {
        String methodString = getVisibility() + " ";
        if (isStatic()) {
            methodString += "static ";
        }
        methodString += getReturnTypeAsString() + " ";
        methodString += getName() + " ";
        methodString += "(" + getParametersAsString() + ")";
        return methodString;
    }
    
    /**
     * Checks if method is static.
     * 
     * @return true if method is static, false otherwise
     */
    public boolean isStatic() {
        return Modifier.isStatic(this.modifier);
    }
    
    /**
     * Getter for the return type.
     * 
     * @return String representing the return type
     */
    public String getReturnTypeAsString() {
        String name = RamModelUtils.getNameOfType(this.returnType, this.owner);
        return name;
    }
    
    public Type getReturnType() {
        return this.returnType;
    }
    
    /**
     * Checks if method is generic.
     * 
     * @return true if generic method, false otherwise.
     */
    public boolean isGenericMethod() {
        return isGeneric;
    }
    
}
