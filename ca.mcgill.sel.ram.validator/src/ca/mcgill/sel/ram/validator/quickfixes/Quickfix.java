package ca.mcgill.sel.ram.validator.quickfixes;

/**
 * Interface for quickfixes. 
 * 
 * IMPORTANT: All the quickfixes have to contains the constructor with this signature:
 *      Quickfix(EObject obj)  
 *      
 * @author lmartellotto
 */
public interface Quickfix {
        
    /**
     * The short description of the quickfix.
     * @return The short description.
     */
    String getLabel();
    
    /**
     * The method called to quick fix the error.
     */
    void quickfix();
    
}
