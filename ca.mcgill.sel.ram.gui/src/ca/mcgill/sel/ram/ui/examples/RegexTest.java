package ca.mcgill.sel.ram.ui.examples;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ca.mcgill.sel.ram.ui.utils.MetamodelRegex;

public final class RegexTest {
    
    private RegexTest() {
        
    }
    
    /**
     * @param args
     */
    public static void main(final String[] args) {
        
        matchesOpertion("+ String foo()");
        matchesOpertion("+ String oper(int num, String s)");
        matchesMultiplicity("0..*");
        System.out.println("");
        splitOperation("+ String oper(String s)");
        splitParams("String num");
        splitMultiplicity("12..*");
        System.out.println("");
        
    }
    
    private static boolean matchesMultiplicity(final String s) {
        
        final boolean matches = s.matches(MetamodelRegex.REGEX_MULTIPLICITY);
        System.out.println(s + " matches? " + matches);
        return matches;
    }
    
    private static boolean matchesName(final String s) {
        final boolean matches = s.matches("^" + MetamodelRegex.REGEX_TYPE + "$");
        System.out.println(s + " matches? " + matches);
        return matches;
    }
    
    private static boolean matchesOpertion(final String s) {
        final boolean matches = s.matches("^" + MetamodelRegex.REGEX_OPERATION_DECLARATION + "$");
        System.out.println(s + " matches? " + matches);
        return matches;
    }
    
    private static void splitMultiplicity(final String s) {
        final Matcher matcher = Pattern.compile(MetamodelRegex.REGEX_GROUP_MULTIPLICITY).matcher(s);
        if (matcher.matches()) {
            for (int i = 0; i <= matcher.groupCount(); i++) {
                System.out.println(matcher.group(i));
            }
        }
    }
    
    private static void splitOperation(final String s) {
        final Matcher matcher = Pattern.compile("^" + MetamodelRegex.REGEX_OPERATION_DECLARATION + "$").matcher(s);
        if (matcher.matches()) {
            for (int i = 0; i <= matcher.groupCount(); i++) {
                System.out.println(matcher.group(i));
            }
        }
    }
    
    private static void splitParams(final String s) {
        final Matcher matcher = Pattern.compile("^" + MetamodelRegex.REGEX_ATTRIBUTE_DECLARATION + "$").matcher(s);
        if (matcher.matches()) {
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
        }
    }
    
}
