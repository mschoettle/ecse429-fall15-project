package ca.mcgill.sel.ram.loaders.interfaces;

import java.util.List;

/**
 * Search for classes that matches a search string.
 * 
 * @author Franz
 * 
 */
public interface IRamClassSearcher {

    /**
     * Get a list of stings corresponding to the name of classes ordered from the best to worst match of className.
     * 
     * @param className name of the class we are searching
     * @param classList list of all class names to be searched
     * @return list of class name ordered from best to worst match
     */
    List<String> getRecommendedLoadable(final String className, List<String> classList);

}
