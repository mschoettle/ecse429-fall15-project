package ca.mcgill.sel.ram.ui.views.structural.handler.impl;

import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;

import ca.mcgill.sel.ram.ui.views.handler.impl.TextViewHandler;

/**
 * Handler for the feature selection of an association. Handles the display of the association feature model and setting
 * of the feature selection to the model.
 *
 * @author cbensoussan
 */
public class AssociationFeatureSelectionHandler extends TextViewHandler {

    @Override
    public boolean processTapEvent(TapEvent tapEvent) {
        return true;
    }
}
