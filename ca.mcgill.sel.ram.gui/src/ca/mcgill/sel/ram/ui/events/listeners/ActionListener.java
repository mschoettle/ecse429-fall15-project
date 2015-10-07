package ca.mcgill.sel.ram.ui.events.listeners;

/**
 * This interface is implemented by something that wants to receive a notification
 * when an action was performed (e.g., a button was pressed).
 * 
 * @author mschoettle
 * @see java.awt.event.ActionListener ActionListener of AWT
 */
public interface ActionListener {

    /**
     * The event of an action. Contains the action command and the target.
     */
    public class ActionEvent {

        private String actionCommand;
        private Object target;

        /**
         * Creates a new action event.
         * 
         * @param target
         *            the target where the action was performed on
         * @param actionCommand
         *            the action command
         */
        public ActionEvent(Object target, String actionCommand) {
            this.actionCommand = actionCommand;
            this.target = target;
        }

        /**
         * Returns the action command.
         * 
         * @return the action command
         */
        public String getActionCommand() {
            return actionCommand;
        }

        /**
         * Returns the target.
         * 
         * @return the target
         */
        public Object getTarget() {
            return target;
        }

    }

    /**
     * Gets called when an action (e.g., a button was pressed) was performed and should be handled.
     * 
     * @param event
     *            the {@link ActionEvent}
     */
    void actionPerformed(ActionEvent event);

}
