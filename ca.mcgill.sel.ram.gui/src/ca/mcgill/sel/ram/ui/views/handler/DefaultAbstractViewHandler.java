package ca.mcgill.sel.ram.ui.views.handler;

import org.mt4j.input.inputProcessors.componentProcessors.unistrokeProcessor.UnistrokeEvent;
import org.mt4j.input.inputProcessors.componentProcessors.unistrokeProcessor.UnistrokeUtils.UnistrokeGesture;
import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.ram.ui.views.AbstractView;
import ca.mcgill.sel.ram.ui.views.handler.impl.AbstractViewHandler;

/**
 * Default {@link AbstractViewHandler} with no unistroke gesture handling.
 * 
 * @author CCamillieri
 */
public class DefaultAbstractViewHandler extends AbstractViewHandler {

    @Override
    public void handleUnistrokeGesture(AbstractView<?> target, UnistrokeGesture gesture, Vector3D startPosition,
            UnistrokeEvent event) {
    }

}
