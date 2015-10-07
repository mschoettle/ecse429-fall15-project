package ca.mcgill.sel.ram.ui.events;

import java.lang.reflect.Field;

import org.mt4j.components.visibleComponents.shapes.MTPolygon;
import org.mt4j.input.inputData.AbstractCursorInputEvt;
import org.mt4j.input.inputData.InputCursor;
import org.mt4j.input.inputProcessors.componentProcessors.unistrokeProcessor.UnistrokeContext;
import org.mt4j.input.inputProcessors.componentProcessors.unistrokeProcessor.UnistrokeProcessor;
import org.mt4j.util.math.Vector3D;
import org.mt4j.util.math.Vertex;

import processing.core.PApplet;

/**
 * A {@link UnistrokeProcessor} that restricts gestures to be drawn on the non-restricted axis.
 * 
 * @author mschoettle
 */
public class LockedAxisUnistrokeProcessor extends UnistrokeProcessor {
    
    private Axis lockedAxis;
    private Vector3D startPosition;
    private MTPolygon visualization;
    
    /**
     * Creates a new {@link LockedAxisUnistrokeProcessor} with the given axis being locked.
     * 
     * @param app the application currently running
     * @param lockedAxis the {@link Axis} to be locked
     */
    public LockedAxisUnistrokeProcessor(PApplet app, Axis lockedAxis) {
        super(app);
        
        this.lockedAxis = lockedAxis;
    }
    
    @Override
    public void cursorStarted(InputCursor inputCursor, AbstractCursorInputEvt currentEvent) {
        if (this.canLock(inputCursor)) {
            super.cursorStarted(inputCursor, currentEvent);
            
            // Save the actual start position, because it might get modified later
            // when the visualization points get resampled.
            startPosition = currentEvent.getPosition();
            visualization = getVisualization();
        }
    }
    
    @Override
    public void cursorUpdated(InputCursor inputCursor, AbstractCursorInputEvt currentEvent) {
        lockEvent(inputCursor, currentEvent);
        super.cursorUpdated(inputCursor, currentEvent);
        
        // Make the visualization always from start to last position
        // to support moving back.
        if (visualization != null) {
            Vertex[] vertices = visualization.getVerticesLocal();
            
            Vertex first = vertices[0];
            Vertex last = vertices[vertices.length - 1];
            
            first.x = startPosition.getX();
            first.y = startPosition.getY();
            last.y = first.y;
            
            visualization.setVertices(new Vertex[] { first, last });
        }
    }
    
    @Override
    public void cursorEnded(InputCursor inputCursor, AbstractCursorInputEvt currentEvent) {
        lockEvent(inputCursor, currentEvent);
        
        super.cursorEnded(inputCursor, currentEvent);
    }
    
    /**
     * Locks the given event to on the locked axis according to the first event of the cursor.
     * 
     * @param inputCursor the input cursor
     * @param currentEvent the current event to lock
     */
    private void lockEvent(InputCursor inputCursor, AbstractCursorInputEvt currentEvent) {
        // Get first event.
        AbstractCursorInputEvt firstEvent = inputCursor.getFirstEvent();
        
        // Fix the start point due to the limited history depth.
        firstEvent.setScreenX(startPosition.getX());
        firstEvent.setScreenY(startPosition.getY());
        
        // Change last added event.
        switch (lockedAxis) {
            case X:
                currentEvent.setScreenX(firstEvent.getX());
                break;
            case Y:
                currentEvent.setScreenY(firstEvent.getY());
                break;
        }
    }
    
    /**
     * Loads the visualization of the unistroke in form of the polygon from the {@link UnistrokeContext}.
     * 
     * @return the visualization of the unistroke gesture
     */
    private MTPolygon getVisualization() {
        MTPolygon polygon = null;
        
        try {
            Field contextField = UnistrokeProcessor.class.getDeclaredField("context");
            contextField.setAccessible(true);
            UnistrokeContext context = (UnistrokeContext) contextField.get(this);
            
            polygon = context.getVisualizer();
            // CHECKSTYLE:IGNORE IllegalCatch FOR 2 LINES: Using reflection a lot of different exceptions
            // have to be handled, but in all cases we want to do nothing.
        } catch (Exception e) {
            // Don't do anything.
            System.err.println(e.getLocalizedMessage());
        }
        
        return polygon;
    }
    
}
