package ca.mcgill.sel.ram.ui.events;

import org.mt4j.input.inputData.AbstractCursorInputEvt;
import org.mt4j.input.inputData.InputCursor;
import org.mt4j.input.inputData.MTMouseInputEvt;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragProcessor;

import processing.core.PApplet;

/**
 * Processor for being able to drag by using only right click.
 *
 * @author eyildirim
 */
public class RightClickDragProcessor extends DragProcessor {
    /**
     * Instantiates a new drag processor.
     *
     * @param graphicsContext
     *            the graphics context
     */
    public RightClickDragProcessor(PApplet graphicsContext) {
        super(graphicsContext);

    }

    /**
     * Instantiates a new right click drag processor.
     *
     * @param graphicsContext - the graphics context
     * @param stopEventPropagation - whether we want to stop the propagation of the event or not
     */
    public RightClickDragProcessor(PApplet graphicsContext, boolean stopEventPropagation) {
        super(graphicsContext, stopEventPropagation);
    }

    @Override
    public void cursorEnded(InputCursor c, AbstractCursorInputEvt fe) {
        if (fe instanceof MTMouseInputEvt) {
            MTMouseInputEvt mouseEvent = (MTMouseInputEvt) fe;
            if (mouseEvent.isRightButton()) {
                super.cursorEnded(c, fe);
            }
        }

    }

    @Override
    public void cursorStarted(InputCursor cursor, AbstractCursorInputEvt fe) {
        if (fe instanceof MTMouseInputEvt) {
            MTMouseInputEvt mouseEvent = (MTMouseInputEvt) fe;
            if (mouseEvent.isRightButton()) {
                super.cursorStarted(cursor, fe);
            }
        }

    }

    @Override
    public void cursorUpdated(InputCursor c, AbstractCursorInputEvt fe) {
        if (fe instanceof MTMouseInputEvt) {
            MTMouseInputEvt mouseEvent = (MTMouseInputEvt) fe;
            if (mouseEvent.isRightButton()) {
                super.cursorUpdated(c, fe);
            }
        }
    }

}
