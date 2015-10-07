package ca.mcgill.sel.ram.ui.views.message;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.INotifyChangedListener;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.ram.AssignmentStatement;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.StructuralFeature;
import ca.mcgill.sel.ram.Type;
import ca.mcgill.sel.ram.ui.components.ContainerComponent;
import ca.mcgill.sel.ram.ui.components.RamTextComponent;
import ca.mcgill.sel.ram.ui.events.listeners.ITapAndHoldListener;
import ca.mcgill.sel.ram.ui.layouts.HorizontalLayout;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.utils.Fonts;
import ca.mcgill.sel.ram.ui.utils.Strings;
import ca.mcgill.sel.ram.ui.views.TextView;
import ca.mcgill.sel.ram.ui.views.message.handler.MessageViewHandlerFactory;

/**
 * The view that represents an {@link AssignmentStatement}.
 * It shows a text view for the "assignTo" and the value to assign.
 * 
 * @author mschoettle
 */
public class AssignmentStatementView extends ContainerComponent<ITapAndHoldListener> implements INotifyChangedListener {
    
    private AssignmentStatement assignmentStatement;
    
    private TextView assignToTypeView;
    
    /**
     * Creates a new view for the given {@link AssignmentStatement}.
     * 
     * @param assignmentStatement the given {@link AssignmentStatement} this view should represent
     */
    public AssignmentStatementView(AssignmentStatement assignmentStatement) {
        super();
        
        this.assignmentStatement = assignmentStatement;
        
        setNoStroke(false);
        setNoFill(false);
        setFillColor(Colors.DEFAULT_ELEMENT_FILL_COLOR);
        
        build();
        
        EMFEditUtil.addListenerFor(assignmentStatement, this);
    }
    
    @Override
    public void destroy() {
        super.destroy();
        
        EMFEditUtil.removeListenerFor(assignmentStatement, this);
    }
    
    /**
     * Builds the child views with the following structure:
     * "assignTo type" "assignTo" := "value"
     * where the second and last are text views that allow their value to be changed.
     */
    private void build() {
        /**
         * Provide a text view for the type of assign to.
         * When no assignTo is set, nothing should be displayed
         * (i.e., null will lead to an empty string hiding the view).
         */
        StructuralFeature assignTo = assignmentStatement.getAssignTo();
        Type assignToType = (assignTo != null) ? assignTo.getType() : null;
        assignToTypeView = new TextView(assignToType, CorePackage.Literals.CORE_NAMED_ELEMENT__NAME);
        assignToTypeView.setFont(Fonts.DEFAULT_PLACEHOLDER_FONT);
        assignToTypeView.setBufferSize(Cardinal.EAST, 0);
        assignToTypeView.setBufferSize(Cardinal.NORTH, 2f);
        addChild(assignToTypeView);
        
        TextView assignToView = new TextView(assignmentStatement, RamPackage.Literals.ASSIGNMENT_STATEMENT__ASSIGN_TO);
        assignToView.setPlaceholderText(Strings.PH_SELECT_ASSIGNMENT);
        addChild(assignToView);
        assignToView.registerTapProcessor(MessageViewHandlerFactory.INSTANCE.getAssignmentAssignToHandler());
        
        RamTextComponent assignmentText = new RamTextComponent(Strings.SYMBOL_ASSIGNMENT);
        assignmentText.setBufferSize(Cardinal.EAST, 0);
        assignmentText.setBufferSize(Cardinal.WEST, 0);
        addChild(assignmentText);
        
        TextView valueView = new TextView(assignmentStatement, RamPackage.Literals.ASSIGNMENT_STATEMENT__VALUE);
        valueView.setPlaceholderText(Strings.PH_SPECIFY_VALUE);
        valueView.registerTapProcessor(MessageViewHandlerFactory.INSTANCE.getValueSpecificationHandler());
        addChild(valueView);
        
        setLayout(new HorizontalLayout());
    }
    
    /**
     * Returns the represented assignment statement.
     * 
     * @return the {@link AssignmentStatement} that is represented
     */
    public AssignmentStatement getAssignmentStatement() {
        return assignmentStatement;
    }
    
    @Override
    public void notifyChanged(Notification notification) {
        if (notification.getNotifier() == assignmentStatement
                && notification.getFeature() == RamPackage.Literals.ASSIGNMENT_STATEMENT__ASSIGN_TO) {
            StructuralFeature assignTo = assignmentStatement.getAssignTo();
            
            /**
             * Update the data of the type view manually to reflect the changes.
             */
            if (assignTo == null) {
                assignToTypeView.setData(null);
            } else {
                assignToTypeView.setData(assignTo.getType());
            }
        }
    }
    
}
