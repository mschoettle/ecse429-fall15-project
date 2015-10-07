package ca.mcgill.sel.ram.validator.quickfixes;

import java.util.Collections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EContentsEList;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.commons.emf.util.EMFModelUtil;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.Attribute;
import ca.mcgill.sel.ram.LayoutElement;
import ca.mcgill.sel.ram.Operation;
import ca.mcgill.sel.ram.Parameter;
import ca.mcgill.sel.ram.REnum;
import ca.mcgill.sel.ram.RamFactory;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.Type;
import ca.mcgill.sel.ram.impl.ContainerMapImpl;
import ca.mcgill.sel.ram.impl.ElementMapImpl;

/**
 * Creates a REnum from the name parameter, adds it as type to the structural view.
 * For all Attributes, Operations and Parameters: 
 *      Checks for all their EReferences, if it refers the previous undefined REnum,
 *      and if it is, sets the new REnum.
 * 
 * @author lmartellotto
 */
public class CreateEnumQuickfix implements Quickfix {

    private static final int POS_X = 50;
    private static final int POS_Y = 50;

    private EObject target;
    private String name;

    /**
     * Builds the {@link CreateEnumQuickfix} for the target EObject.
     * @param t the target EObject
     * @param n the name of the future enum
     */
    public CreateEnumQuickfix(EObject t, String n) {
        target = t;
        name = n;
    }

    @Override
    public String getLabel() {
        return "Create the Enum '" + name + "'";
    }

    @Override
    public void quickfix() {
        
        EditingDomain editingDomain = EMFEditUtil.getEditingDomain(target);
        Aspect aspect = EMFModelUtil.getRootContainerOfType(target, RamPackage.Literals.ASPECT);

        // Create REnum
        REnum newEnum = RamFactory.eINSTANCE.createREnum();
        newEnum.setName(name);

        // Create the layout element
        LayoutElement layoutElement = RamFactory.eINSTANCE.createLayoutElement();
        layoutElement.setX(POS_X);
        layoutElement.setY(POS_Y);

        // Create the add command for the new enum
        Command addEnumCommand = AddCommand.create(editingDomain, aspect.getStructuralView(),
                RamPackage.Literals.STRUCTURAL_VIEW__TYPES,
                newEnum);

        // Get the ContainerMap.
        ContainerMapImpl containerMap = 
                EMFModelUtil.getEntryFromMap(aspect.getLayout().getContainers(), aspect.getStructuralView());
        
        System.out.println("MAP : ");
        for (EObject obj : containerMap.getEMap().keySet()) {
            for (EObject obj2 : containerMap.getEMap().get(obj).keySet()) {
                System.out.println(obj2);
                System.out.println(containerMap.getEMap().get(obj).get(obj2));
            }
        }
        
        // Get the new child descriptor for the entry of the map.
        CommandParameter newChildDescriptor = EMFEditUtil.getNewChildDescriptor(containerMap,
                RamPackage.Literals.CONTAINER_MAP__VALUE);
        
        // Set the key and value of the ElementMap.
        // We evade using commands here because it is not necessary.
        ElementMapImpl elementMap = (ElementMapImpl) newChildDescriptor.getValue();
        
        elementMap.setKey(newEnum);
        elementMap.setValue(layoutElement);
        
        // The newChildDescriptor references the element map we modified, so we can just use
        // the CreateChildCommand.
        Command layoutCommand = 
                CreateChildCommand.create(editingDomain, containerMap, newChildDescriptor, Collections.EMPTY_LIST);
         
        // Create compound command.
        CompoundCommand compoundCommand = new CompoundCommand();
        compoundCommand.append(addEnumCommand);
        compoundCommand.append(layoutCommand);
 
        // For ALL the objects in the Aspect
        TreeIterator<EObject> iterAspect = aspect.eAllContents();
        while (iterAspect.hasNext()) {
            EObject next = iterAspect.next();
            
            // Hack:
            // Only EReferences from Operation, Attribute and Parameter can be set.
            if (!(next instanceof Parameter) && !(next instanceof Operation) && !(next instanceof Attribute)) {
                continue;
            }
            
            // For ALL references for the current object
            for (EContentsEList.FeatureIterator<?> featureIterator = 
                    (EContentsEList.FeatureIterator<?>) next.eCrossReferences().iterator();
                   featureIterator.hasNext();) {
                
                EObject eObject = (EObject) featureIterator.next();
                EReference eReference = (EReference) featureIterator.feature();
                
                // If the reference is a Type and if the name is equals to the new enum name,
                // we set the new enum as type for this reference
                if (eObject instanceof Type) {
                    Type type = (Type) eObject;
                    if (type.getName().equals(name)) {
                        Command setCommand = SetCommand.create(editingDomain, next, eReference, newEnum);
                        compoundCommand.append(setCommand);      
                    }
                }
            }
        }

        editingDomain.getCommandStack().execute(compoundCommand);
        aspect.getStructuralView().getTypes().add(newEnum);
        
        System.out.println("MAP : ");
        for (EObject obj : containerMap.getEMap().keySet()) {
            for (EObject obj2 : containerMap.getEMap().get(obj).keySet()) {
                System.out.println(obj2);
                System.out.println(containerMap.getEMap().get(obj).get(obj2));
            }
        }
    }

}
