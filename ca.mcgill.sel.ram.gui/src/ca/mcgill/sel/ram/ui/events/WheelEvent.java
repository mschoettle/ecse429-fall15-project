package ca.mcgill.sel.ram.ui.events;

import org.mt4j.components.interfaces.IMTComponent3D;
import org.mt4j.input.inputData.InputCursor;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.scaleProcessor.ScaleEvent;
import org.mt4j.util.math.Vector3D;

public class WheelEvent extends MTGestureEvent {
    private final float scale;
    private final Vector3D scalePoint;
    
    public WheelEvent(final MouseWheelProcessor source, final int id, final IMTComponent3D targetComponent,
            final float scale, final Vector3D scalingPoint) {
        super(source, id, targetComponent);
        this.scale = scale;
        scalePoint = scalingPoint;
    }
    
    public ScaleEvent asScaleEvent() {
        final InputCursor cursor = new InputCursor();
        return new ScaleEvent(getSource(), getId(), getTarget(), cursor, cursor, scale, scale, 1, scalePoint);
    }
    
    public ScaleEvent asScaleEvent(IMTComponent3D customTarget) {
        final InputCursor cursor = new InputCursor();
        return new ScaleEvent(getSource(), getId(), customTarget, cursor, cursor, scale, scale, 1, scalePoint);
    }
    
    public Vector3D getLocationOnScreen() {
        return scalePoint;
    }
    
    public float getScale() {
        return scale;
    }
    
}
