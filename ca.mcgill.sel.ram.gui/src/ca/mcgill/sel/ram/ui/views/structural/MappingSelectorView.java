package ca.mcgill.sel.ram.ui.views.structural;

import org.eclipse.emf.ecore.EStructuralFeature;

import ca.mcgill.sel.core.COREMapping;
import ca.mcgill.sel.ram.Classifier;
import ca.mcgill.sel.ram.ui.views.SelectorView;

/**
 * A {@link MappingSelectorView} is responsible for showing the possible selectable elements for a particular mapping.
 * Furthermore, it is able to create new elements that the user wants to use (by typing the name into the text field).
 * 
 * @author mschoettle
 */
public class MappingSelectorView extends SelectorView {
    
    /**
     * A handler for this mapping selector view that handles text entered into the input field.
     * 
     * @author mschoettle
     */
    public interface IMappingSelectorHandler {
        
        /**
         * Returns whether the text entered is valid (and was processed).
         * 
         * @param text
         *            the entered text
         * @return true, if the entered text is valid, false otherwise
         */
        boolean validTextEntered(String text);
        
    }
    
    private COREMapping<?> mapping;
    @SuppressWarnings("unused")
    private EStructuralFeature feature;
    
    private IMappingSelectorHandler handler;
    
    /**
     * Creates a new view for the given mapping and the feature.
     * 
     * @param mapping
     *            the kind of {@link Mapping} that should be represented
     * @param feature
     *            the {@link EStructuralFeature} of the mapping to be shown
     */
    public MappingSelectorView(COREMapping<?> mapping, EStructuralFeature feature) {
        super(mapping, feature);
        
        this.mapping = mapping;
        this.feature = feature;
    }
    
    /**
     * Sets the handler for this view.
     * 
     * @param handler
     *            the handler for this view
     */
    public void setHandler(IMappingSelectorHandler handler) {
        this.handler = handler;
    }
    
    @Override
    public boolean verifyKeyboardDismissed() {
        // feature == RamPackage.Literals.MAPPING__TO_ELEMENT
        if (!getInputField().getText().isEmpty()) {
            // only if it is not a Classifier, all other elements can be renamed
            if (!(mapping.getFrom() instanceof Classifier)) {
                // do something with the text (e.g. forward the call to handler)
                // can also be done in keyboardClosed(...)
                if (handler != null) {
                    boolean validTextEntered = handler.validTextEntered(getInputField().getText());
                    
                    if (validTextEntered) {
                        destroy();
                    }
                    
                    return validTextEntered;
                }
                
                destroy();
            }
        }
        
        return false;
    }
}
