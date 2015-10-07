package ca.mcgill.sel.ram.ui.views.message.old;

import org.mt4j.components.TransformSpace;
import org.mt4j.util.math.Vector3D;

import processing.core.PGraphics;
import ca.mcgill.sel.ram.Lifeline;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.StructuralFeature;
import ca.mcgill.sel.ram.TypedElement;
import ca.mcgill.sel.ram.ui.components.RamLineComponent;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.components.RamTextComponent;
import ca.mcgill.sel.ram.ui.layouts.Layout;
import ca.mcgill.sel.ram.ui.layouts.VerticalLayout;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.utils.Strings;
import ca.mcgill.sel.ram.ui.views.TextView;

public class LifelineView extends RamRectangleComponent {
    
    private Lifeline lifeline;
    
    private boolean lifelineDrawn;
    
    private TextView nameField;
    private RamRectangleComponent nameContainer;
    
    public LifelineView(Lifeline lifeline) {
        this.lifeline = lifeline;
        
        // for debug purposes
        // setNoStroke(false);
        // setStrokeColor(MTColor.AQUA);
        nameContainer = new RamRectangleComponent();
        nameContainer.setLayout(new VerticalLayout(Layout.HorizontalAlignment.CENTER));
        nameContainer.setNoStroke(false);
        nameContainer.setNoFill(false);
        nameContainer.setFillColor(Colors.DEFAULT_ELEMENT_FILL_COLOR);
        addChild(nameContainer);
        
        TypedElement typedElement = lifeline.getRepresents();
        
        if (typedElement != null) {
            boolean metaClass = typedElement instanceof StructuralFeature
                    && ((StructuralFeature) typedElement).isStatic();
            
            if (metaClass) {
                RamTextComponent metaClassText = new RamTextComponent(Strings.TAG_META_CLASS);
                metaClassText.setFont(InteractionView.DEFAULT_FONT);
                nameContainer.addChild(metaClassText);
            }
        }
        
        nameField = new TextView(lifeline, RamPackage.Literals.LIFELINE__REPRESENTS, true);
        nameField.setFont(InteractionView.DEFAULT_FONT);
        nameContainer.addChild(nameField);
        
        // width and height of this component have to be set manually
        // as it is not layouted
        setWidthLocal(nameContainer.getWidth());
        setMinimumHeight(nameContainer.getHeight());
    }
    
    @Override
    public void drawComponent(PGraphics g) {
        super.drawComponent(g);
        
        if (!lifelineDrawn) {
            setHeightLocal(((RamRectangleComponent) getParent()).getHeight() - InteractionView.PADDING_BIG);
            
            float x = getPosition(TransformSpace.LOCAL).getX() + (getWidth() / 2.0f);
            float y = getPosition(TransformSpace.LOCAL).getY() + nameContainer.getHeight();
            RamLineComponent line = new RamLineComponent(x, y, x, getHeight());
            line.setLineStipple(InteractionView.LINE_STIPPLE_DASHED);
            addChild(line);
            
            lifelineDrawn = true;
        }
    }
    
    public Vector3D getLinePosition() {
        Vector3D position = getPosition(TransformSpace.RELATIVE_TO_PARENT);
        position.setX(position.getX() + (getWidth() / 2.0f));
        
        return position;
    }
}
