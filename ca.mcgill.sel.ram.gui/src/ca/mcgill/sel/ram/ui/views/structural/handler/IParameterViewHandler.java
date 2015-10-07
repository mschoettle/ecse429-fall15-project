package ca.mcgill.sel.ram.ui.views.structural.handler;

import org.mt4j.input.inputProcessors.IGestureEventListener;

import ca.mcgill.sel.ram.ui.views.structural.ParameterView;

/**
 * This interface is implemented by something that can handle events for a {@link ParameterView}.
 * 
 * @author g.Nicolas
 *
 */
public interface IParameterViewHandler extends IGestureEventListener {

    /**
     * Handles the removal of a {@link ca.mcgill.sel.ram.Parameter}.
     * 
     * @param parameterView
     *            the affected {@link ParameterView}
     */
    void removeParameter(ParameterView parameterView);

    /**
     * Handles an add of parameter before another one.
     * 
     * @param parameterView - the parameter view after the created parameter
     */
    void addParameterBefore(ParameterView parameterView);

    /**
     * Handles an add of parameter after another one.
     * 
     * @param parameterView - the parameter view before the created parameter
     */
    void addParameterAfter(ParameterView parameterView);

}
