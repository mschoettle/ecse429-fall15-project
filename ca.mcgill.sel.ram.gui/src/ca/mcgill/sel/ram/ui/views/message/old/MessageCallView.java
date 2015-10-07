package ca.mcgill.sel.ram.ui.views.message.old;

import org.mt4j.components.visibleComponents.shapes.MTPolygon;
import org.mt4j.util.math.Vector3D;
import org.mt4j.util.math.Vertex;

import ca.mcgill.sel.ram.Message;
import ca.mcgill.sel.ram.MessageSort;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamLineComponent;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.components.RamTextComponent.Alignment;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.views.TextView;

public class MessageCallView extends RamRectangleComponent {
    
    /**
     * Polygon which represents an arrow to be used for reply messages. The arrow is drawn pointing to the given
     * coordinates (coming from the right side).
     * 
     * @author mschoettle
     */
    protected class MessageReplyArrow extends MTPolygon {
        public MessageReplyArrow(float x, float y) {
            super(RamApp.getApplication(), new Vertex[] {});
            final Vertex start = new Vertex(x + 12, y - 5);
            final Vertex center = new Vertex(x, y);
            final Vertex end = new Vertex(x + 12, y + 5);
            setVertices(new Vertex[] { start, center, end });
            // trick to use polygon as a polyline
            setNoFill(true);
            setStrokeWeight(STROKE_WEIGHT);
            setStrokeColor(Colors.DEFAULT_ELEMENT_STROKE_COLOR);
        }
    }
    
    /**
     * Polygon which represents an arrow to be used for synchronized message calls. The arrow is drawn pointing to the
     * given coordinates (coming from the left side).
     * 
     * @author mschoettle
     */
    protected class MessageSyncCallArrow extends MTPolygon {
        public MessageSyncCallArrow(float x, float y) {
            super(RamApp.getApplication(), new Vertex[] {});
            final Vertex start = new Vertex(x - 12, y - 5);
            final Vertex center = new Vertex(x, y);
            final Vertex end = new Vertex(x - 12, y + 5);
            setVertices(new Vertex[] { start, center, end, start });
            setStrokeWeight(STROKE_WEIGHT);
            setStrokeColor(Colors.DEFAULT_ELEMENT_STROKE_COLOR);
            setFillColor(Colors.DEFAULT_ELEMENT_STROKE_COLOR);
        }
    }
    
    /**
     * The weight of the stroke of a line.
     */
    private static final float STROKE_WEIGHT = 2.0f;
    
    private static final float SPACE = 5;
    
    private static final float SELF_MESSAGE_HEIGHT = 30;
    
    private Message message;
    
    private TextView signatureField;
    
    public MessageCallView(float width, Message message) {
        this.message = message;
        
        // for debug purposes
        // setNoStroke(false);
        
        signatureField = new TextView(message, RamPackage.Literals.MESSAGE__SIGNATURE, true);
        signatureField.setFont(InteractionView.DEFAULT_FONT);
        signatureField.setAlignment(Alignment.CENTER_ALIGN);
        signatureField.setWidthLocal(width);
        // TODO: remove hardcoded max width, find better solution
        // (the problem is that if a message on the bottom gets resized all above messages to that lifeline are wrong)
        signatureField.setMaximumWidth(290);
        addChild(signatureField);
        
        // TODO: make it a little bit bigger but after that the text component is not centered anymore
        width = (width < signatureField.getWidth()) ? (signatureField.getWidth() + InteractionView.PADDING) : width;
        // if it is a self message we need a bit more space
        float height = (message.isSelfMessage()) ? signatureField.getHeight() + SELF_MESSAGE_HEIGHT : signatureField
                .getHeight();
        // height += SPACE;
        setSizeLocal(width, height);
        
        drawMessageCall();
    }
    
    private MTPolygon drawArrow(float x, float y) {
        MTPolygon arrow;
        
        if (message.getMessageSort() == MessageSort.REPLY) {
            arrow = new MessageReplyArrow(x, y);
        } else {
            arrow = new MessageSyncCallArrow(x, y);
        }
        
        addChild(arrow);
        
        return arrow;
    }
    
    private void drawMessageCall() {
        float linePositionY = signatureField.getHeight() - SPACE;
        float linePositionXStart = 0;
        float linePositionXEnd = getWidth();
        
        if (!message.isSelfMessage()) {
            RamLineComponent line = new RamLineComponent(linePositionXStart, linePositionY, linePositionXEnd,
                    linePositionY);
            addChild(line);
            
            // decide which arrow to draw depending on message sort
            if (message.getMessageSort() == MessageSort.REPLY) {
                drawArrow(linePositionXStart, linePositionY);
                line.setLineStipple(InteractionView.LINE_STIPPLE_DASHED);
            } else {
                drawArrow(linePositionXEnd, linePositionY);
            }
        } else {
            drawSelfMessage(linePositionXStart, linePositionY);
        }
        
    }
    
    private void drawSelfMessage(float startX, float startY) {
        float x2 = startX + SELF_MESSAGE_HEIGHT;
        float y2 = startY + SELF_MESSAGE_HEIGHT;
        
        RamLineComponent line = new RamLineComponent(startX, startY, x2, startY);
        addChild(line);
        
        line = new RamLineComponent(x2, startY, x2, y2);
        addChild(line);
        
        line = new RamLineComponent(x2, y2, startX, y2);
        addChild(line);
        
        MTPolygon arrow = drawArrow(startX, y2);
        arrow.rotateZ(new Vector3D(startX, y2, 1), 180);
    }
}
