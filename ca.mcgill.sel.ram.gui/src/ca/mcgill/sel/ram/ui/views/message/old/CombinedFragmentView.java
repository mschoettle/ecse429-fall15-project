package ca.mcgill.sel.ram.ui.views.message.old;

import java.util.ArrayList;
import java.util.List;

import org.mt4j.util.math.Vertex;

import ca.mcgill.sel.ram.CombinedFragment;
import ca.mcgill.sel.ram.InteractionOperand;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.ui.components.RamLineComponent;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.views.TextView;

public class CombinedFragmentView extends RamRectangleComponent {

    private CombinedFragment combinedFragment;
    private InteractionView interactionView;

    private TextView typeField;

    public CombinedFragmentView(CombinedFragment combinedFragment, InteractionView interactionView) {
        this.combinedFragment = combinedFragment;
        this.interactionView = interactionView;

        setNoStroke(false);
    }

    public void build() {
        TextView typeField = new TextView(combinedFragment, 
                RamPackage.Literals.COMBINED_FRAGMENT__INTERACTION_OPERATOR);
        typeField.setFont(InteractionView.DEFAULT_FONT);
        typeField.setBufferSize(Cardinal.EAST, InteractionView.PADDING);
        typeField.setNoStroke(false);
        addChild(typeField);

        float currentOperandY = typeField.getHeight();
        interactionView.increasePositionY(currentOperandY);

        float maxWidth = getWidth();
        List<RamLineComponent> lines = new ArrayList<RamLineComponent>();

        for (InteractionOperand operand : combinedFragment.getOperands()) {
            InteractionOperandView operandView = new InteractionOperandView(operand, interactionView);
            operandView.setWidthLocal(getWidth());
            addChild(operandView);
            operandView.translate(0, currentOperandY);
            operandView.build();

            currentOperandY += operandView.getHeight() + InteractionView.MESSAGE_SPACE;
            maxWidth = Math.max(maxWidth, operandView.getWidth());

            if (combinedFragment.getOperands().indexOf(operand) < (combinedFragment.getOperands().size() - 1)) {
                RamLineComponent dashedLine = new RamLineComponent(0, currentOperandY, maxWidth, currentOperandY);
                dashedLine.setLineStipple(InteractionView.LINE_STIPPLE_DASHED);
                addChild(dashedLine);

                lines.add(dashedLine);

                currentOperandY += InteractionView.MESSAGE_SPACE;
                interactionView.increasePositionY(InteractionView.MESSAGE_SPACE);
            }
        }

        interactionView.increasePositionY(InteractionView.MESSAGE_SPACE);

        // update all lines as well
        // unfortunately vertices have to be modified directly
        for (RamLineComponent line : lines) {
            Vertex[] vertices = line.getVerticesLocal();
            // only the endpoints x has to be modified
            vertices[1].x = maxWidth;
            line.setVertices(vertices);
        }

        setSizeLocal(maxWidth, currentOperandY);
    }
}
