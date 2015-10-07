package ca.mcgill.sel.ram.provider.util;

import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edit.provider.AdapterFactoryItemDelegator;
import org.eclipse.emf.edit.provider.IItemLabelProvider;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.ram.Classifier;
import ca.mcgill.sel.ram.Operation;
import ca.mcgill.sel.ram.PrimitiveType;
import ca.mcgill.sel.ram.RCollection;

/**
 * A label provider for the to element of mappings that can be used by property descriptors.
 * It displays the aspect name in front of classes and provides proper operation signatures.
 * 
 * @author mschoettle
 */
public class MappingToLabelProvider implements IItemLabelProvider {
    
    private AdapterFactory adapterFactory;
    private AdapterFactoryItemDelegator itemDelegator;
    
    /**
     * Creates a new label provider.
     * 
     * @param adapterFactory the {@link AdapterFactory} of the property descriptor
     * @param itemDelegator the {@link AdapterFactoryItemDelegator} of the property descriptor
     */
    public MappingToLabelProvider(AdapterFactory adapterFactory, AdapterFactoryItemDelegator itemDelegator) {
        this.adapterFactory = adapterFactory;
        this.itemDelegator = itemDelegator;
    }
    
    // CHECKSTYLE:IGNORE ReturnCount: Easier to implement when supporting recursive calls.
    @Override
    public String getText(Object object) {
        // TODO: Move this into a separate class that can be used throughout item providers?
        // build Aspect.Class to be able to distinguish between classes 
        // from different aspects (due to extends)
        // TODO: don't do it for Implementation Classes?
        if (!(object instanceof PrimitiveType) && !(object instanceof RCollection) 
                && object instanceof Classifier) {
            Classifier classifier = (Classifier) object;
            StringBuffer result = new StringBuffer();
            
            result.append(EMFEditUtil.getTypeName(classifier));
            result.append(" ");
            result.append(EMFEditUtil.getTextFor(adapterFactory, 
                            classifier.eContainer().eContainer()));
            result.append(".");
            result.append(EMFEditUtil.getTextFor(adapterFactory, classifier));
            
            return result.toString();
        } else if (object instanceof Operation) {
            return RAMEditUtil.getOperationSignature(adapterFactory, (Operation) object, false);
        } else if (object instanceof EList<?>) {
            // since it is a 1-n feature we will receive a list
            StringBuffer result = new StringBuffer();
            for (Object child : (List<?>) object) {
                if (result.length() != 0) {
                    result.append(", ");
                }
                result.append(getText(child));
            }
            return result.toString();
        }
        
        return itemDelegator.getText(object);
    }
    
    @Override
    public Object getImage(Object object) {
        return itemDelegator.getImage(object);
    }
    
}
