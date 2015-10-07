package ca.mcgill.sel.ram.ui.views.state;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.mt4j.components.visibleComponents.shapes.MTPolygon;
import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.core.CorePackage;
import ca.mcgill.sel.ram.Constraint;
import ca.mcgill.sel.ram.LiteralSpecification;
import ca.mcgill.sel.ram.LiteralString;
import ca.mcgill.sel.ram.Operation;
import ca.mcgill.sel.ram.Transition;
import ca.mcgill.sel.ram.ui.components.RamTextComponent;
import ca.mcgill.sel.ram.ui.utils.Strings;
import ca.mcgill.sel.ram.ui.views.RamEnd;
import ca.mcgill.sel.ram.ui.views.RamEnd.Position;
import ca.mcgill.sel.ram.ui.views.RelationshipView;
import ca.mcgill.sel.ram.ui.views.TextView;
import ca.mcgill.sel.ram.ui.views.handler.HandlerFactory;

/**
 * @author abirayed
 */
public class TransitionView extends RelationshipView<Operation, StateComponentView> implements INotifyChangedListener {
    
    public enum TransitionEndType {
        START,
        END
    }
    
    private TextView transitionName;
    private Transition transition;
    
    public TransitionView(final Transition transition, final StateComponentView startState,
            final StateComponentView endState) {
        super(transition.getSignature(), startState, transition.getSignature(), endState);
        this.transition = transition;
        
        EMFEditUtil.addListenerFor(transition, this);
        // TODO: this could be done by the item provider
        if (transition.getSignature() != null) {
            EMFEditUtil.addListenerFor(transition.getSignature(), this);
        }
    }
    
    @Override
    protected void destroyComponent() {
        EMFEditUtil.removeListenerFor(transition, this);
        // TODO: this could be done by the item provider
        if (transition.getSignature() != null) {
            EMFEditUtil.removeListenerFor(transition.getSignature(), this);
        }
    }
    
    /**
     * Creates the role name and multiplicity texts for the given association end. Also takes care of the creation at
     * the proper position depending on the position it is located at.
     * 
     * @param ramEnd
     *            the end of the association the texts should be created for
     * @param roleName
     * @param multiplicity
     */
    public void createTexts(final RamEnd<Operation, StateComponentView> ramEnd) {
        if (transitionName != null) {
            transitionName.unregisterAllInputProcessors();
            transitionName.removeAllGestureEventListeners();
            transitionName.destroy();
        }
        
        RamEnd<Operation, StateComponentView> opposite = ramEnd.getOpposite();
        
        transitionName = new TextView(transition, CorePackage.Literals.CORE_NAMED_ELEMENT__NAME, true);
        // transitionName.setFont(DEFAULT_FONT);
        transitionName.setPlaceholderText(Strings.PH_SELECT_OPERATION);
        transitionName.setHandler(HandlerFactory.INSTANCE.getTransitionNameHandler());
        
        moveRoleName(transitionName, opposite.getLocation(), opposite.getPosition());
        addChild(transitionName);
        
    }
    
    @Override
    public void destroy() {
        EMFEditUtil.removeListenerFor(transition, this);
        
        if (transition.getSignature() != null) {
            EMFEditUtil.removeListenerFor(transition.getSignature(), this);
        }
        
        super.destroy();
    }
    
    /**
     * Draws the given transition end based on all information contained inside.
     */
    private void drawTransitionEnd(RamEnd<Operation, StateComponentView> end) {
        TransitionEndType type = TransitionEndType.START;
        if (transition.getEndState().equals(end.getComponentView().getState())) {
            type = TransitionEndType.END;
        }
        // For Debug purposes.
        // System.out.println(end.getLocation().getX() + " -  " + end.getLocation().getY() + " - " + end.getPosition());
        drawTransitionEnd(type, end.getLocation().getX(), end.getLocation().getY(), end.getPosition());
    }
    
    /**
     * Draws the correct polygon for an transition end at the given location and position on the class.
     */
    private void drawTransitionEnd(TransitionEndType type, float x, float y, Position position) {
        if (!type.equals(TransitionEndType.END)) {
            return;
        }
        
        MTPolygon polygon = new ArrowPolygon(x, y, drawColor);
        rotateShape(polygon, x, y, position);
        
        addChild(polygon);
        polygon.setPickable(false);
        polygon.setEnabled(false);
        
    }
    
    /**
     * @return the m_transitionNmae
     */
    public TextView getTransitionName() {
        return transitionName;
    }
    
    @Override
    public void notifyChanged(Notification notification) {
        if (notification.getNotifier() == getFromEnd().getModel()
                || notification.getNotifier() == getToEnd().getModel()) {
            if (notification.getEventType() == Notification.SET) {
                shouldUpdate();
                if (notification.getFeature() == CorePackage.Literals.CORE_NAMED_ELEMENT__NAME) {
                    transition.setName((String) notification.getNewValue());
                }
            }
        }
    }
    
    @Override
    protected void update() {
        drawAllLines();
        
        drawTransitionEnd(getFromEnd());
        if (!transition.getEndState().equals(transition.getStartState())) {
            drawTransitionEnd(getToEnd());
        }
        
        // only create texts for an end if it is navigable
        createTexts(getFromEnd());
        
        // update the texts position
        moveTexts(getFromEnd(), transitionName);
        
    }
    
    private static void moveTexts(RamEnd<Operation, StateComponentView> ramEnd, TextView roleName) {
        RamEnd<Operation, StateComponentView> opposite = ramEnd.getOpposite();
        
        moveRoleName(roleName, opposite.getLocation(), opposite.getPosition());
        
    }
    
    private static void moveRoleName(RamTextComponent textArea, Vector3D currentPosition, Position position) {
        float x = currentPosition.getX();
        float y = currentPosition.getY();
        
        // happily changing the anchor of the text area is sufficient in most cases
        switch (position) {
            case BOTTOM:
                textArea.setAnchor(PositionAnchor.LOWER_RIGHT);
                y += 44;
                break;
            case TOP:
                textArea.setAnchor(PositionAnchor.LOWER_RIGHT);
                y -= 10;
                break;
            case LEFT:
                // change the anchor to the lower right so it will be kind of aligned to the right
                textArea.setAnchor(PositionAnchor.LOWER_RIGHT);
                x -= 15;
                break;
            case RIGHT:
                textArea.setAnchor(PositionAnchor.LOWER_LEFT);
                x += 15;
                break;
        }
        
        textArea.setPositionRelativeToParent(new Vector3D(x, y));
    }
    
    /**
     * Sets the class specified by this state view.
     * 
     * @param signature
     */
    public void setSignature(Operation signature, Constraint guard) {
        String trName = "";
        if (signature != null) {
            EMFEditUtil.addListenerFor(signature, this);
            trName = signature.getName();
        }
        if (guard != null) {
            
            if (guard.getSpecification() instanceof LiteralSpecification) {
                if (guard.getSpecification() instanceof LiteralString) {
                    trName += "[" + ((LiteralString) guard.getSpecification()).getValue() + "]";
                }
            }
        }
        transition.setSignature(signature);
        transition.setGuard(guard);
        transition.setName(trName);
    }
    
    public Transition getTransition() {
        return transition;
    }
}
