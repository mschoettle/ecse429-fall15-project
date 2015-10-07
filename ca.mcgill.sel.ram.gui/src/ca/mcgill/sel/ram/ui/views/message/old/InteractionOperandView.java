package ca.mcgill.sel.ram.ui.views.message.old;

import org.mt4j.components.MTComponent;
import org.mt4j.components.TransformSpace;

import ca.mcgill.sel.ram.InteractionOperand;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.views.TextView;

public class InteractionOperandView extends RamRectangleComponent {
    
    private InteractionView interactionView;
    private InteractionOperand interactionOperand;
    
    public InteractionOperandView(InteractionOperand interactionOperand, InteractionView interactionView) {
        this.interactionView = interactionView;
        this.interactionOperand = interactionOperand;
        
        // setHeightLocal(constraintField.getHeight());
        // setNoStroke(false);
    }
    
    public void build() {
        float startY = interactionView.currentMessagePositionY;
        
        TextView constraintField = new TextView(interactionOperand,
                RamPackage.Literals.INTERACTION_OPERAND__INTERACTION_CONSTRAINT);
        constraintField.setFont(InteractionView.DEFAULT_FONT);
        addChild(constraintField);
        
        // for the constraint field
        interactionView.increasePositionY(2 * InteractionView.MESSAGE_SPACE);
        
        interactionView.buildInteractionOperand(interactionOperand);
        
        float maxWidth = 0;
        
        // determine the max width needed
        for (MTComponent component : getChildList()) {
            if (component instanceof RamRectangleComponent) {
                RamRectangleComponent ramComponent = (RamRectangleComponent) component;
                float x = ramComponent.getPosition(TransformSpace.RELATIVE_TO_PARENT).getX() + ramComponent.getWidth()
                        + InteractionView.PADDING_LARGE;
                maxWidth = Math.max(maxWidth, x);
            }
        }
        
        float newHeight = interactionView.currentMessagePositionY - startY - InteractionView.MESSAGE_SPACE;
        setSizeLocal(maxWidth, newHeight);
    }
}
