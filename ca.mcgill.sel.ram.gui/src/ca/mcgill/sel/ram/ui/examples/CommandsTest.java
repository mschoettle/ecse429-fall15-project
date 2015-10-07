package ca.mcgill.sel.ram.ui.examples;

import java.util.EventObject;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor.PropertyValueWrapper;

import ca.mcgill.sel.commons.emf.util.ResourceManager;
import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.Classifier;
import ca.mcgill.sel.ram.provider.RamItemProviderAdapterFactory;

public final class CommandsTest {
    
    private CommandsTest() {
        
    }
    
    public static void main(final String[] args) {
        ResourceManager.initialize();
        
        final Aspect aspect = (Aspect) ResourceManager.loadModel("testFiles/Observer.ram");
        
        // code taken from generated RAMEditor (editor code)
        // Create an adapter factory that yields item providers.
        final ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(
                ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
        
        // this requires the generated edit code
        // the only advantage I can see right now is that when for example creating a delete command only the to be
        // deleted
        // object has to be passed without stating the owner and feature
        
        // TODO: uncomment this line
        adapterFactory.addAdapterFactory(new RamItemProviderAdapterFactory());
        
        // create command stack
        final BasicCommandStack commandStack = new BasicCommandStack();
        
        // register a listener
        // this can be used to enable/disable undo/redo buttons/gestures
        commandStack.addCommandStackListener(new CommandStackListener() {
            
            @Override
            public void commandStackChanged(final EventObject event) {
                System.out.println(((CommandStack) event.getSource()).canUndo());
                System.out.println(((CommandStack) event.getSource()).canRedo());
            }
        });
        
        // create the editing domain
        final AdapterFactoryEditingDomain domain = new AdapterFactoryEditingDomain(adapterFactory, commandStack);
        
        // create a command
        Command command = SetCommand.create(domain, aspect, CorePackage.Literals.CORE_NAMED_ELEMENT__NAME, "test");
        
        // execute the command
        commandStack.execute(command);
        
        // see if it worked
        System.out.println("test".equals(aspect.getName()));
        
        // subject
        final Classifier clazz = aspect.getStructuralView().getClasses().get(0);
        
        // create remove command
        // this has to be used if RamItemProviderAdapterFactory (i.e. generated edit code) is not used
        // command = RemoveCommand.create(domain, aspect.getStructuralView(),
        // RamPackage.Literals.STRUCTURAL_VIEW__CLASSES, clazz);
        command = RemoveCommand.create(domain, clazz);
        
        // execute
        commandStack.execute(command);
        
        // see if it worked
        System.out.println(!aspect.getStructuralView().getClasses().get(0).getName().equals(clazz.getName()));
        
        // undo
        System.out.println(commandStack.canUndo());
        commandStack.undo();
        
        // see if it worked
        System.out.println(aspect.getStructuralView().getClasses().get(0).getName().equals(clazz.getName()));
        
        // undo again
        System.out.println(commandStack.canUndo());
        commandStack.undo();
        
        // see if it worked
        System.out.println(aspect.getName());
        
        // can we still undo?
        System.out.println(commandStack.canUndo());
        
        // is model changed?
        System.out.println(commandStack.isSaveNeeded());
        // when saved
        commandStack.saveIsDone();
        
        // get a property descriptor
        IItemPropertySource propertySource = (IItemPropertySource) adapterFactory.adapt(clazz,
                IItemPropertySource.class);
        
        IItemPropertyDescriptor propertyDescriptor = propertySource.getPropertyDescriptor(clazz,
                CorePackage.Literals.CORE_NAMED_ELEMENT__NAME);
        
        System.out.println(((PropertyValueWrapper) propertyDescriptor.getPropertyValue(clazz)).getEditableValue(clazz));
        
        // also see here: http://www.eclipse.org/forums/index.php/m/420487/?srch=property+descriptor#msg_420487
        // for how to overwrite the label provider of a property descriptor
    }
}
