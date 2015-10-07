package ca.mcgill.sel.commons.emf.util;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;

/**
 * ComposedAdapterFactory that knows its {@link EditingDomain}. This is required in order to use property descriptors
 * from outside Eclipse when command-based editing is required. Otherwise the descriptors can't find the editing domain
 * and will not execute commands.
 * 
 * Taken from: org.eclipse.emf.mapping.domain.AdapterFactoryMappingDomain (see source code online)
 * 
 * @see org.eclipse.emf.mapping.domain.AdapterFactoryMappingDomain
 * @author mschoettle
 */
public class EditingDomainProvidingComposedAdapterFactory extends ComposedAdapterFactory
        implements IEditingDomainProvider {
    
    private EditingDomain editingDomain;
    
    /**
     * Creates a new instance for the given adapter factory and editing domain.
     * 
     * @param adapterFactory the actual {@link AdapterFactory} to use
     * @param editingDomain the {@link EditingDomain} this adapter factory should know of
     */
    public EditingDomainProvidingComposedAdapterFactory(AdapterFactory adapterFactory, EditingDomain editingDomain) {
        super(adapterFactory);
        
        this.editingDomain = editingDomain;
        addAdapterFactory(adapterFactory);
    }
    
    /**
     * Returns the {@link EditingDomain}.
     * 
     * @return the {@link EditingDomain} associated with this adapter factory
     */
    @Override
    public EditingDomain getEditingDomain() {
        return editingDomain;
    }
}