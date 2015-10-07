package ca.mcgill.sel.ram.validator.test;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;

import ca.mcgill.sel.core.COREVisibilityType;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.Class;
import ca.mcgill.sel.ram.MessageView;
import ca.mcgill.sel.ram.Operation;
import ca.mcgill.sel.ram.RAMVisibilityType;
import ca.mcgill.sel.ram.RamFactory;
import ca.mcgill.sel.ram.StructuralView;
import ca.mcgill.sel.ram.validator.ValidationRulesParser;
import ca.mcgill.sel.ram.validator.Validator;

/**
 * To test parsing files.
 * @author lmartellotto
 */
public class ValidationParserTest {

    private static Validator v;

    /**
     * Parsing test.
     */
    public void test() {
        
        for (EClassifier c : ValidationRulesParser.getInstance().getRules().keySet()) {
            System.out.println(c);
            System.out.println(ValidationRulesParser.getInstance().getRules().get(c));
            System.out.println();
        }

        /*
        for (String c : ValidationRulesParser.getInstance().getParsingErrors().keySet()) {
            System.err.println(c);
            System.err.println(ValidationRulesParser.getInstance().getParsingErrors().get(c));
            System.err.println();
        }
        */
        Aspect aspect = RamFactory.eINSTANCE.createAspect();
        aspect.setName("aaaaaaaaaaaeiw");
        
        
        StructuralView sv = RamFactory.eINSTANCE.createStructuralView();
        
        Class cA = RamFactory.eINSTANCE.createClass();
        cA.setName("Class A");
        
        ca.mcgill.sel.ram.Class cB = RamFactory.eINSTANCE.createClass();
        cB.setName("Class A");
        
        Class cC = RamFactory.eINSTANCE.createClass();
        cC.setName("Class C");
        
        Operation o = RamFactory.eINSTANCE.createOperation();
        o.setVisibility(COREVisibilityType.PUBLIC);
        o.setExtendedVisibility(RAMVisibilityType.PUBLIC);
        o.setName("operationTest");
        cA.getOperations().add(o);
        
        MessageView m = RamFactory.eINSTANCE.createMessageView();
        m.setSpecifies(o);
        
        sv.getClasses().add(cA);
        sv.getClasses().add(cB);
        sv.getClasses().add(cC);
        aspect.setStructuralView(sv);
        aspect.getMessageViews().add(m);
        
        
        System.out.println("1 ------------------------");
        v.check(aspect);
        printErrors();


    }
    
    /**
     * Prints errors.
     */
    private void printErrors() {
        System.out.println("");
        System.out.println("----------ERRORS");
        for (EObject o : v.getErrors().keySet()) {
            System.out.println(o);
            System.out.println(v.getErrors().get(o));
            System.out.println();
        }
        
        System.out.println("---ADDED");
        for (EObject o : v.getAddedErrors().keySet()) {
            System.out.println(o);
            System.out.println(v.getAddedErrors().get(o));
            System.out.println();
        }
        
        System.out.println("---DISMISSED");
        for (EObject o : v.getDismissedErrors().keySet()) {
            System.out.println(o);
            System.out.println(v.getDismissedErrors().get(o));
            System.out.println();
        }
    }

    /**
     * Tests.
     * @param args
     *     Params.
     */
    public static void main(String[] args) {
        v = new Validator();
        ValidationParserTest t = new ValidationParserTest();
        t.test();
    }
}
