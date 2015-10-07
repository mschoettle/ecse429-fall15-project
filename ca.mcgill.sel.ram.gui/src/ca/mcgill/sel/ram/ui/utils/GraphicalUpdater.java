package ca.mcgill.sel.ram.ui.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.mt4j.util.MTColor;

import ca.mcgill.sel.ram.validator.ValidationRuleErrorDescription.ValidationErrorType;

/**
 * Sends notifications to elements of the GUI depending on the events and their priority.
 * 
 * Components of the GUI have to register to the GraphicalUpdater of their Aspect parent
 * (mapping between Aspect and GraphicalUpdater instance done in the RamApp class).
 * for the object target they want to be notified.
 * Then, these components will be able to receive notifications from this GraphicalUpdater
 * to update their display.
 * 
 * The GraphicalUpdater knows what information it has to send, depending on kind of received event.
 * For example, if the Validator send it there is an error on an EObject 'A',
 * all listeners of this EObject 'A' will receive a notification to highlight in red color.
 *
 * Moreover, the GraphicalUpdater remembers the sets of events for each object,
 * and sorts them by priority.
 * For example, the tracing event is most important than the validation event.
 * So, when an object receives events from validation AND tracing,
 * its listeners just receive the tracing event. They will receive validation event
 * when the tracing event is deleted.
 * 
 * Events :
 * - HighlightEvent :
 * * VALIDATOR_EVENT (when the validator terminated the validation)
 * * VALIDATION_VIEW_EVENT (when the user clicks on a error in the
 * {@link ca.mcgill.sel.ram.ui.views.containers.ValidationView})
 * * TRACING_EVENT (when the user clicks on a extended
 * aspect in the {@link ca.mcgill.sel.ram.ui.views.containers.TracingView})
 * 
 * @author lmartellotto
 */
public class GraphicalUpdater {

    /************** OBJECTS FOR THE GRAPHICAL UPDATER **************/

    /** The main map of EObject and their listeners. */
    private Map<EObject, List<GraphicalUpdaterListener>> guListeners;

    /** The map of EObject and their set of current commands. */
    private Map<EObject, LinkedList<HighlightCommand>> highlightStates;

    /**
     * Constructor, just initializes the maps.
     */
    public GraphicalUpdater() {
        guListeners = new HashMap<EObject, List<GraphicalUpdaterListener>>();
        highlightStates = new HashMap<EObject, LinkedList<HighlightCommand>>();
    }

    /**
     * Method for VALIDATOR_EVENT (when the validation occurred).
     * Sends the correct color from the map validatorEventColors
     * depending on the error type, to all listeners of the target EObject.
     * 
     * @param obj The object which gets error.
     * @param type The type of error.
     */
    public void validationEvent(EObject obj, ValidationErrorType type) {
        if (!highlightStates.containsKey(obj)) {
            highlightStates.put(obj, new LinkedList<HighlightCommand>());
        }

        HighlightCommand oldCommand = null;
        // Deletes all the similar commands for this object
        for (HighlightCommand h : highlightStates.get(obj)) {
            if (h.event == HighlightEvent.VALIDATOR_EVENT) {
                oldCommand = h;
            }
        }

        if (oldCommand != null) {
            highlightStates.get(obj).remove(oldCommand);
        }

        MTColor color = Colors.VALIDATOR_EVENT_COLORS.get(type);
        // If the color is null, there is not new command to add
        if (color != null) {
            highlightStates.get(obj).add(new HighlightCommand(HighlightEvent.VALIDATOR_EVENT, color));
        }

        highlight(obj);
    }

    /**
     * Method for VALIDATION_VIEW_EVENT (when the user clicked on an error).
     * Sends the correct color from the map validationViewEventColors
     * depending on the error type, to all listeners of the target EObject.
     * 
     * @param obj The object which gets error.
     * @param type The type of error.
     */
    public void validationUserEvent(EObject obj, ValidationErrorType type) {

        if (!highlightStates.containsKey(obj)) {
            highlightStates.put(obj, new LinkedList<HighlightCommand>());
        }

        // Deletes the possible similar command for this object
        HighlightCommand oldCommand = null;
        for (HighlightCommand h : highlightStates.get(obj)) {
            if (h.event == HighlightEvent.VALIDATION_VIEW_EVENT) {
                oldCommand = h;
            }
        }

        if (oldCommand != null) {
            highlightStates.get(obj).remove(oldCommand);
        }

        // If the color is null, there is not new command to add
        MTColor color = Colors.VALIDATIONVIEW_EVENT_COLORS.get(type);
        if (color != null) {
            highlightStates.get(obj).add(new HighlightCommand(HighlightEvent.VALIDATION_VIEW_EVENT, color));
        }

        highlight(obj);
    }

    /**
     * Method for TRACING_EVENT (when the user clicked on an error).
     * Sends the correct color to all listeners of the target EObject.
     * 
     * @param obj The object which gets error.
     * @param indexColor The target woven aspect.
     * @param toHighlight If the object need to be highlighted.
     */
    public void tracingEvent(EObject obj, int indexColor, boolean toHighlight) {

        if (!highlightStates.containsKey(obj)) {
            highlightStates.put(obj, new LinkedList<HighlightCommand>());
        }

        // Deletes the possible similar command for this object
        HighlightCommand oldCommand = null;
        if (!toHighlight) {
            for (HighlightCommand h : highlightStates.get(obj)) {
                if (h.event == HighlightEvent.TRACING_EVENT && h.color == Colors.TRACING_VIEW_COLORS.get(indexColor)) {
                    oldCommand = h;
                }
            }
        }

        if (oldCommand != null) {
            highlightStates.get(obj).remove(oldCommand);
        }

        MTColor color = null;
        if (toHighlight && indexColor < getSizeTracingEventColors()) {
            color = Colors.TRACING_VIEW_COLORS.get(indexColor);
        }

        if (color != null) {
            highlightStates.get(obj).add(new HighlightCommand(HighlightEvent.TRACING_EVENT, color));
        }

        highlight(obj);
    }

    /**
     * Gets the size of colors for TRACING_EVENT.
     * 
     * @return the size
     */
    public int getSizeTracingEventColors() {
        return Colors.TRACING_VIEW_COLORS.size();
    }

    /**
     * Adds listener for an EObject.
     * 
     * @param obj The object in the model to listen.
     * @param l The graphical updater listener.
     */
    public void addGUListener(EObject obj, GraphicalUpdaterListener l) {
        if (!guListeners.containsKey(obj)) {
            guListeners.put(obj, new ArrayList<GraphicalUpdaterListener>());
        }
        guListeners.get(obj).add(l);
    }

    /**
     * Removes listener for an object.
     * 
     * @param obj The object in the model which the listener listened.
     * @param l The graphical updater listener.
     */
    public void removeGUListener(EObject obj, GraphicalUpdaterListener l) {
        if (guListeners.containsKey(obj)) {
            guListeners.put(obj, new ArrayList<GraphicalUpdaterListener>());
            guListeners.get(obj).remove(l);

            // If there is no anymore listeners, we can remove the object
            if (guListeners.get(obj).size() == 0) {
                guListeners.remove(obj);
            }
        }
    }

    /**
     * Call the highlight() method of the listeners if there are new notifications,
     * for the EObject parameter.
     * 
     * @param obj
     *            The object to highlight.
     */
    private void highlight(EObject obj) {
        // If there are no listeners or no set of states registered for the object
        if (!guListeners.containsKey(obj) || !highlightStates.containsKey(obj)) {
            return;
        }

        MTColor color = null;
        HighlightEvent event = null;
        if (highlightStates.get(obj).size() != 0) {
            color = highlightStates.get(obj).getLast().getColor();
            event = highlightStates.get(obj).getLast().getEvent();
        }

        // FIX : mix colors
        /*
         * // If there are many commands of same event, we add color.
         * if (event != null && color != null) {
         * for (HighlighCommand c : highlightStates.get(obj)) {
         * if (c.getEvent() == event) {
         * color.setR(color.getR() + c.getColor().getR());
         * color.setG(color.getG() + c.getColor().getG());
         * color.setB(color.getB() + c.getColor().getB());
         * }
         * }
         * }
         */
        for (GraphicalUpdaterListener l : guListeners.get(obj)) {
            l.highlight(color, event);
        }
    }

    /**
     * List of events which can occur for highlighting.
     */
    public enum HighlightEvent {
        /** When the validation occurred. */
        VALIDATOR_EVENT(3),

        /** When the user clicked on an error in the {@AspectValidatorView}. */
        VALIDATION_VIEW_EVENT(2),

        /** When the user clicked on an error in the {@WeaverTracableView}. */
        TRACING_EVENT(1);

        private int priority;

        /**
         * Constructor.
         * 
         * @param prio
         *            The priority.
         */
        private HighlightEvent(int prio) {
            priority = prio;
        }

        /**
         * Gets the priority.
         * 
         * @return the priority
         */
        public int getPriority() {
            return priority;
        }
    }

    /**
     * Contains an event (the provider of this command) and the desired color.
     */
    private class HighlightCommand implements Comparable<HighlightCommand> {
        private HighlightEvent event;
        private MTColor color;

        /**
         * Constructor.
         * 
         * @param e
         *            The event.
         * @param c
         *            The color.
         */
        public HighlightCommand(HighlightEvent e, MTColor c) {
            event = e;
            color = c;
        }

        /**
         * Gets the event.
         * 
         * @return the event
         */
        public HighlightEvent getEvent() {
            return event;
        }

        /**
         * Gets the color.
         * 
         * @return the color
         */
        public MTColor getColor() {
            return color;
        }

        @Override
        public int compareTo(HighlightCommand o) {
            return event.getPriority() - o.event.getPriority();
        }
    }

}
