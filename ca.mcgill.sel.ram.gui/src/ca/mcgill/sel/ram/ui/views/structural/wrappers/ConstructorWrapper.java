package ca.mcgill.sel.ram.ui.views.structural.wrappers;

import java.lang.reflect.Constructor;
import java.util.Arrays;

import ca.mcgill.sel.ram.ImplementationClass;

public class ConstructorWrapper extends CallableMemberWrapper {
    
    /**
     * Constructor.
     * 
     * @param constructor the constructor
     * @param owner Owner of the method
     */
    public ConstructorWrapper(Constructor<?> constructor, ImplementationClass owner) {
        super(owner, constructor.getModifiers(), constructor.getDeclaringClass().getSimpleName(),
                Arrays.asList(constructor.getGenericParameterTypes()));
    }
    
    @Override
    protected String getStringRepresentation() {
        String methodString = getVisibility() + " ";
        methodString += getName() + " ";
        methodString += "(" + getParametersAsString() + ")";
        return methodString;
    }
    
}
