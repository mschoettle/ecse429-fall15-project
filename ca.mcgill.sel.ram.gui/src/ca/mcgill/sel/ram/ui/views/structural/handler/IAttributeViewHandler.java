package ca.mcgill.sel.ram.ui.views.structural.handler;

import org.mt4j.input.inputProcessors.IGestureEventListener;

import ca.mcgill.sel.core.COREPartialityType;
import ca.mcgill.sel.ram.ui.views.structural.AttributeView;

/**
 * This interface is implemented by something that can handle events for an {@link AttributeView}.
 * 
 * @author mschoettle
 */
public interface IAttributeViewHandler extends IGestureEventListener {

    /**
     * Handles the removal of an attribute.
     * 
     * @param attributeView - the affected {@link AttributeView}
     */
    void removeAttribute(AttributeView attributeView);

    /**
     * Handles the set of attribute to static.
     * 
     * @param attributeView - the affected {@link AttributeView}
     */
    void setAttributeStatic(AttributeView attributeView);

    /**
     * Handles the generation of getter.
     * 
     * @param attributeView - the affected {@link AttributeView}
     */
    void generateGetter(AttributeView attributeView);

    /**
     * Handles the generation of setter.
     * 
     * @param attributeView - the affected {@link AttributeView}
     */
    void generateSetter(AttributeView attributeView);

    /**
     * Handles the toggle of a {@link ca.mcgill.sel.ram.Attribute} to given partiality.
     * 
     * @param attribute - the affected {@link AttributeView}
     * @param type - the new {@link COREPartialityType}
     */
    void switchPartiality(AttributeView attribute, COREPartialityType type);
}
