package ca.mcgill.sel.ram.ui.views.structural.handler.impl;

import java.util.Set;

import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;

import ca.mcgill.sel.ram.ImplementationClass;
import ca.mcgill.sel.ram.ObjectType;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.StructuralView;
import ca.mcgill.sel.ram.TypeParameter;
import ca.mcgill.sel.ram.controller.ControllerFactory;
import ca.mcgill.sel.ram.loaders.RamClassLoader;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamSelectorComponent;
import ca.mcgill.sel.ram.ui.components.listeners.AbstractDefaultRamSelectorListener;
import ca.mcgill.sel.ram.ui.utils.RamModelUtils;
import ca.mcgill.sel.ram.ui.views.SelectorView;
import ca.mcgill.sel.ram.ui.views.TextView;
import ca.mcgill.sel.ram.ui.views.handler.impl.TextViewHandler;
import ca.mcgill.sel.ram.ui.views.structural.handler.ITypeParameterNameHandler;

/**
 * The handler for type parameter.
 * 
 * @author Franz
 *
 */
public class TypeParameterNameHandler extends TextViewHandler implements ITypeParameterNameHandler {

    @Override
    public boolean shouldDismissKeyboard(TextView textView) {
        return false;
    }

    @Override
    public boolean processTapEvent(TapEvent tapEvent) {
        if (tapEvent.isDoubleTap()) {

            final TextView target = (TextView) tapEvent.getTarget();

            final TypeParameter typeParameter = (TypeParameter) target.getData();

            // if generic not set and not wildcard then show selector.

            if (typeParameter.getGenericType() == null
                    && !("?".equals(typeParameter.getName()))) {

                // create selector
                RamSelectorComponent<Object> selector = new SelectorView(target.getData(),
                        RamPackage.Literals.TYPE_PARAMETER__GENERIC_TYPE);

                RamApp.getActiveAspectScene().addComponent(selector, tapEvent.getLocationOnScreen());
                selector.registerListener(new AbstractDefaultRamSelectorListener<Object>() {
                    @Override
                    public void elementSelected(RamSelectorComponent<Object> selector, Object element) {
                        ImplementationClass classifier = (ImplementationClass) typeParameter.eContainer();
                        String className = classifier.getInstanceClassName();

                        Set<String> superTypes = RamClassLoader.INSTANCE.getAllSuperClassesFor(className);
                        StructuralView structuralView = (StructuralView) classifier.eContainer();
                        Set<ImplementationClass> subTypes =
                                RamModelUtils.getExistingSubTypesFor(structuralView, className);

                        ControllerFactory.INSTANCE.getImplementationClassController().setTypeParameterType(classifier,
                                typeParameter, (ObjectType) element, superTypes, subTypes);
                    }
                });
            }
        }

        return true;
    }

}
