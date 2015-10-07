package ca.mcgill.sel.ram.ui.views.structural.handler;

import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.ram.ui.events.listeners.ITapAndHoldListener;
import ca.mcgill.sel.ram.ui.events.listeners.ITapListener;
import ca.mcgill.sel.ram.ui.views.handler.IAbstractViewHandler;
import ca.mcgill.sel.ram.ui.views.structural.BaseView;
import ca.mcgill.sel.ram.ui.views.structural.StructuralDiagramView;

/**
 * This interface is implemented by something that can handle events for a {@link StructuralDiagramView}.
 * 
 * @author mschoettle
 */
public interface IStructuralViewHandler extends IAbstractViewHandler, ITapListener, ITapAndHoldListener {
    
    /**
     * This method should be used to drag all selected classes when a drag event is processed on a class that is
     * selected.
     * 
     * @param svd
     *            The structural view that the drag event was applied to
     * @param directionVector
     *            The translation vector coming from the drag event
     */
    void dragAllSelectedClasses(StructuralDiagramView svd, Vector3D directionVector);
    
    /**
     * Handles a double tap performed on a specific {@link ca.mcgill.sel.ram.ui.views.structural.ClassifierView}.
     * 
     * @param structuralView
     *            the {@link StructuralDiagramView} that contains the
     *            {@link ca.mcgill.sel.ram.ui.views.structural.ClassifierView}
     * @param target
     *            the {@link ca.mcgill.sel.ram.ui.views.structural.ClassifierView} that was double tapped
     * @return true, if the event was handled, false otherwise
     */
    boolean handleDoubleTapOnClass(StructuralDiagramView structuralView, BaseView<?> target);
    
    /**
     * Handles a tap-and-hold performed on a specific {@link ca.mcgill.sel.ram.ui.views.structural.ClassifierView}.
     * 
     * @param structuralView
     *            the {@link StructuralDiagramView} that contains the
     *            {@link ca.mcgill.sel.ram.ui.views.structural.ClassifierView}
     * @param target
     *            the {@link ca.mcgill.sel.ram.ui.views.structural.ClassifierView} that a tap-and-hold was performed on
     * @return true, if the event was handled, false otherwise
     */
    boolean handleTapAndHoldOnClass(StructuralDiagramView structuralView, BaseView<?> target);
    
    /**
     * Updates the positions when a selected {@link ca.mcgill.sel.ram.ui.views.structural.ClassifierView} has been
     * dragged.
     * 
     * @param v the {@link StructuralDiagramView} that contains the dragged
     *            {@link ca.mcgill.sel.ram.ui.views.structural.ClassifierView}
     */
    void moveAllSelectedClasses(StructuralDiagramView v);
    
}
