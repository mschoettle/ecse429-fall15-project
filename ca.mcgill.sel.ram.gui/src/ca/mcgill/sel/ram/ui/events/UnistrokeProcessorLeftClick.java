package ca.mcgill.sel.ram.ui.events;

import org.mt4j.input.inputData.AbstractCursorInputEvt;
import org.mt4j.input.inputData.InputCursor;
import org.mt4j.input.inputData.MTMouseInputEvt;
import org.mt4j.input.inputProcessors.componentProcessors.unistrokeProcessor.UnistrokeProcessor;

import processing.core.PApplet;

/**
 * This processor is same as UnistrokeProcessor
 * but when mouse is used it will process the gesture if the left click is used.
 *
 * @author Engin
 */
public class UnistrokeProcessorLeftClick extends UnistrokeProcessor {

    /**
     * Instantiates a new unistroke processor but when mouse is used it will process the gesture if the left click is
     * used.
     *
     * @param pa the pa
     */
    public UnistrokeProcessorLeftClick(PApplet pa) {
        super(pa);
    }

    @Override
    public void cursorEnded(InputCursor inputCursor, AbstractCursorInputEvt currentEvent) {
        if (currentEvent instanceof MTMouseInputEvt) {
            MTMouseInputEvt mouseEvent = (MTMouseInputEvt) currentEvent;
            if (mouseEvent.isLeftButton()) {
                super.cursorEnded(inputCursor, currentEvent);
            }
        } else {
            super.cursorEnded(inputCursor, currentEvent);
        }
    }

    @Override
    public void cursorStarted(InputCursor inputCursor, AbstractCursorInputEvt currentEvent) {

        if (currentEvent instanceof MTMouseInputEvt) {
            MTMouseInputEvt mouseEvent = (MTMouseInputEvt) currentEvent;
            if (mouseEvent.isLeftButton()) {
                super.cursorStarted(inputCursor, currentEvent);
            }
        } else {
            super.cursorStarted(inputCursor, currentEvent);
        }

    }

    @Override
    public void cursorUpdated(InputCursor inputCursor, AbstractCursorInputEvt currentEvent) {
        if (currentEvent instanceof MTMouseInputEvt) {
            MTMouseInputEvt mouseEvent = (MTMouseInputEvt) currentEvent;
            if (mouseEvent.isLeftButton()) {
                super.cursorUpdated(inputCursor, currentEvent);
            }
        } else {
            super.cursorUpdated(inputCursor, currentEvent);
        }

    }

}
