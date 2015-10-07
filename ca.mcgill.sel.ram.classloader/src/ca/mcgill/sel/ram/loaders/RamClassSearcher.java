package ca.mcgill.sel.ram.loaders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import ca.mcgill.sel.ram.loaders.interfaces.IRamClassSearcher;

/**
 * Searches through a list of class names for the class name that is the best match for the search string provided as
 * input.
 * 
 * @author Franz
 */
public final class RamClassSearcher implements IRamClassSearcher {

    /**
     * Singleton.
     */
    public static final RamClassSearcher INSTANCE = new RamClassSearcher();
    
    // private static final int MIN_NUM_RESULTS = 10;

    /**
     * Constructor.
     */
    public RamClassSearcher() {
        
    }

    /**
     * Returns a list of loadable classes. 
     * The list consists of a subset of classes from the given class list that match the given class name.
     * 
     * @param className the part of a class name that is searched
     * @param classList the list of available classes to load
     * @return a list of recommended classes that can be loaded
     */
    public List<String> getRecommendedLoadable(final String className, final List<String> classList) {
        // extract class name
        final String newClassName = RamClassUtils.extractClassName(className).toLowerCase();

        // copy list
        List<String> results = new ArrayList<String>();
        Map<Integer, List<String>> scoreToClasses = getMapFromScoreToListOfClass(className, classList);
        List<Integer> allScores = new ArrayList<Integer>(scoreToClasses.keySet());
        Collections.sort(allScores);

        for (int score : allScores) {
            // If classes do not match with the all search text
            if (score != 0) {
                break;
            }
            
            List<String> someClasses = scoreToClasses.get(score);
            // Sort by length
            Collections.sort(someClasses, new Comparator<String>() {

                @Override
                public int compare(final String first, final String second) {
                    // Compare by length of the class name (actually the filename since we want anonymous class to be
                    // further down the list
                    String newFirst = RamClassUtils.extractClassNameWithParent(first);
                    String newSecond = RamClassUtils.extractClassNameWithParent(second);
                    if (newFirst.length() < newSecond.length()) {
                        return -1;
                    } else if (newFirst.length() > newSecond.length()) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            });

            // Put everything that starts with the class name in front so that it stays in front with the stable sort
            List<String> startsWith = new ArrayList<String>();
            for (String someString : someClasses) {
                String newSomeString = RamClassUtils.extractClassName(someString).toLowerCase();
                if (newSomeString.startsWith(newClassName)) {
                    startsWith.add(someString);
                }
            }
            // move all startswith at the beginning of the list
            someClasses.removeAll(startsWith);
            someClasses.addAll(0, startsWith);

            results.addAll(someClasses);
            
        }
        return results;
    }

    /**
     * Get a mapping from scores to a list of classes.
     * Lower score means better match.
     * 
     * @param className Class name we are searching.
     * @param classList List of all classes.
     * @return mapping from score to list of classes.
     */
    private Map<Integer, List<String>> getMapFromScoreToListOfClass(final String className, 
                                                                    final List<String> classList) {
        
        // extract class name
        final String newClassName = RamClassUtils.extractClassName(className).toLowerCase();

        //map from score to list of classes
        Map<Integer, List<String>> scoreToClasses = new HashMap<Integer, List<String>>();

        for (String someString : classList) {
            //for every class compare with specified className
            String newSomeString = RamClassUtils.extractClassName(someString).toLowerCase();
            
            //get score
            int score = StringUtils.getLevenshteinDistance(newSomeString, newClassName);
            for (int start = 0, end = newClassName.length(); end < newSomeString.length(); start++, end++) {
                score = Math.min(score,
                        StringUtils.getLevenshteinDistance(newClassName, newSomeString.substring(start, end)));
            }
            
            //if score does not exists yet add it to map
            if (!scoreToClasses.containsKey(score)) {
                List<String> newEntry = new ArrayList<String>();
                newEntry.add(someString);
                scoreToClasses.put(score, newEntry);
            } else {
                scoreToClasses.get(score).add(someString);
            }
        }
        return scoreToClasses;
    }

}
