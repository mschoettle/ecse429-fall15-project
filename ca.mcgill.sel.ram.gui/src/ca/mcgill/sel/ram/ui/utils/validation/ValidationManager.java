package ca.mcgill.sel.ram.ui.utils.validation;

import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.ecore.EObject;
import org.mt4j.components.interfaces.IMTController;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.LayoutElement;
import ca.mcgill.sel.ram.ui.utils.GraphicalUpdater;
import ca.mcgill.sel.ram.validator.ValidationError;
import ca.mcgill.sel.ram.validator.Validator;
import ca.mcgill.sel.ram.validator.ValidatorListener;
import ca.mcgill.sel.ram.validator.Validator.ValidatorEvaluationError;

/**
 * Manages the Validation thread: notifies the thread when it needs to run,
 * specially when modification have occurred in the model
 * or if you call the launchValidation() method.
 * 
 * Moreover, it checks every frame (thanks to the update() method)
 * if the thread has new errors: if true, it retrieves them and notifies listeners.
 * 
 * @author lmartellotto
 */
public class ValidationManager implements IMTController, CommandStackListener {
    
    /**
     * Different states for the validation thread.
     */
    public enum AspectValidationThreadState {
        /** When there has not new modifications and so, the thread has nothing to do. */
        WAITING_TO_NOTHING,
        
        /** When there has new modifications and so, the thread is waiting to check them. */
        WAITING_TO_CHECK,
        
        /** When the thread is currently to check. */
        CHECKING
    }
    
    /**
     * Thread which executes the validation of the current aspect
     * and collects the errors from the {@link Validator}.
     * 
     * The lists of errors (current list of errors, list of newly added errors
     * and list of newly dismissed errors) are stocked in the thread
     * which updates its state "isNewValidation" to true.
     * 
     * The {@link ValidationManager} checks every frame (thanks to the update() method)
     * if the thread has new errors and retrieves them if true.
     * 
     * There is a unique {@AspectValidationThread} by aspect.
     * It just waits until there are new modifications.
     * 
     * @author lmartellotto
     */
    private class AspectValidationThread extends Thread implements ValidatorListener {
        
        /** Time to wait before launch a validation if there is not new modifications. */
        private static final int TIME_TO_WAIT = 3000;
        
        /** Time to sleep before checking if a validation have to be launched. */
        private static final int TIME_TO_SLEEP = 1000;
        
        private Integer timer;
        
        private AspectValidationThreadState state;
        
        /**
         * Creates a new thread and sets the timer to -1 to wait until new modifications.
         * 
         * Note: There is a unique {@link AspectValidationThread} by aspect.
         * It just waits until there are new modifications.
         */
        public AspectValidationThread() {
            timer = -1;
            state = AspectValidationThreadState.WAITING_TO_NOTHING;
            String aspectName = aspect.getName();
            setName("Validation Thread for " + aspectName);
        }
        
        @Override
        public void run() {
            boolean toLaunch = false;
            
            while (true) {
                try {
                    synchronized (this) {
                        // If there is no validation to do,
                        // and that the timer is over.
                        if (timer < 0 && !toLaunch) {
                            state = AspectValidationThreadState.WAITING_TO_NOTHING;
                            
                            wait();
                        }
                        
                        // If there was validation to do (but reported
                        // because of the initialization of validator)
                        // but new commands occurred on the aspect
                        if (toLaunch && timer >= 0) {
                            toLaunch = false;
                        }
                        
                        // If there is no validation to launch
                        // we continue to decrement the timer
                        if (!toLaunch) {
                            state = AspectValidationThreadState.WAITING_TO_CHECK;
                            timer -= TIME_TO_SLEEP;
                        }
                        
                        // If the timer is over (so, no new commands)
                        // and so, the validation can be launched.
                        if (timer < 0) {
                            toLaunch = true;
                        }
                        
                    }
                    
                    if (toLaunch) {
                        if (validator.isInitialized()) {
                            if (aspect != null) {
                                state = AspectValidationThreadState.CHECKING;
                                validator.check(aspect);
                                state = AspectValidationThreadState.WAITING_TO_NOTHING;
                                
                                toLaunch = false;
                                for (ValidatorEvaluationError v : validator.getEvaluationErrors()) {
                                    System.err.println(v);
                                }
                            } else {
                                System.out.println("ASPECT NOT FOUND");
                            }
                        }
                    }
                    sleep(TIME_TO_SLEEP);
                    
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
        
        /**
         * Sets the timer to the timer parameter
         * to wait other possibly validation demands (GUI modifications).
         * @param timeToWait Time to wait before really checking.
         */
        public void scheduleValidation(int timeToWait) {
            timer = timeToWait;
        }
        
        @Override
        public void updateValidationErrors(Map<EObject, List<ValidationError>> errs,
                Map<EObject, List<ValidationError>> errsAdded,
                Map<EObject, List<ValidationError>> errsDismissed) {
            
            synchronized (isNewValidation) {
                
                errors = errs;
                errorsAdded = errsAdded;
                errorsDismissed = errsDismissed;
                
                isNewValidation = true;
            }
        }
        
        /**
         * Gets the current state.
         * @return the current state.
         */
        public AspectValidationThreadState getAspectValidationThreadState() {
            return state;
        }
        
    }
    
    private AspectValidationThread thread;
    private AspectValidationThreadState previousState;
    private Validator validator;
    private Aspect aspect;
    
    private GraphicalUpdater gu;
    
    private Map<EObject, List<ValidationError>> errors;
    private Map<EObject, List<ValidationError>> errorsAdded;
    private Map<EObject, List<ValidationError>> errorsDismissed;
    private Boolean isNewValidation;
    
    private List<ValidationManagerListener> listeners;
    
    /**
     * Constructor.
     * Listens the Command Stack of the GUI for the current aspect.
     * @param aspect The aspect to check.
     * @param v The validator.
     * @param updater The updater of the GUI.
     */
    public ValidationManager(Aspect aspect, Validator v, GraphicalUpdater updater) {
        this(aspect, v, updater, null);
    }
    
    /**
     * Constructor.
     * Listens the Command Stack of the GUI for the current aspect.
     * The list of listeners can be initialized here to allow them to be warned
     * for the first validation of the aspect, launched in this constructor.
     * 
     * @param a The aspect to check.
     * @param v The validator.
     * @param updater The updater of the GUI.
     * @param list The list of listeners.
     */
    public ValidationManager(Aspect a, Validator v, GraphicalUpdater updater, List<ValidationManagerListener> list) {
        gu = updater;
        validator = v;
        aspect = a;
        
        EMFEditUtil.getCommandStack(aspect).addCommandStackListener(this);
        listeners = new ArrayList<ValidationManagerListener>();
        if (list != null) {
            listeners.addAll(list);
        }
        
        isNewValidation = false;
    }
    
    /**
     * When a new command occurs, a validation is scheduled.
     * @param event The event.
     */
    @Override
    public void commandStackChanged(EventObject event) {
        
        // Ignore when it's just about layout
        BasicCommandStack stack = (BasicCommandStack) event.getSource();
        Command cmd = stack.getMostRecentCommand();
        
        if (cmd == null) {
            return;
        }
        
        boolean toIgnore = true;
        for (Object o : cmd.getAffectedObjects()) {
            if (!(o instanceof LayoutElement)) {
                toIgnore = false;
            }
        }
        
        if (!toIgnore) {
            scheduleValidation(AspectValidationThread.TIME_TO_WAIT);
        }
    }
    
    /**
     * Gets if there is a new validation.
     * @return True if there is a validation occurred.
     */
    public Boolean getIsNewValidation() {
        return isNewValidation;
    }
    
    /**
     * Manual validation launch.
     * The boolean parameter allows the user to say if he wants to force the
     * validation even if the thread is not running (and so, to start it).
     * 
     * @param toForceLaunch If the user want to force to run the validation.
     * @return If the validation will occur (if the thread is running) or not.
     */
    public boolean launchValidation(boolean toForceLaunch) {
        return launchValidation(toForceLaunch, AspectValidationThread.TIME_TO_WAIT);
    }
    
    /**
     * Manual validation launch.
     * The boolean parameter allows the user to say if he wants to force the
     * validation even if the thread is not running (and so, to start it).
     * 
     * @param toForceLaunch If the user want to force to run the validation.
     * @param timeToWait Time to wait before checking.
     * @return If the validation will occur (if the thread is running) or not.
     */
    public boolean launchValidation(boolean toForceLaunch, int timeToWait) {
        
        if (thread != null) {
            scheduleValidation(timeToWait);
            return true;
        }
        
        if (thread == null && toForceLaunch) {
            startValidationThread();
            scheduleValidation(timeToWait);
            return true;
        }
        
        return false;
    }
    
    /**
     * Adds a listener to this {@ValidatorManager} instance.
     * @param listener The added listener.
     */
    public void addListener(ValidationManagerListener listener) {
        listeners.add(listener);
    }
    
    /**
     * Removes a listener to this instance.
     * @param listener The removed listener.
     */
    public void removeListener(ValidationManagerListener listener) {
        listeners.remove(listener);
    }
    
    /**
     * Schedules a new validation to do.
     * Warns the Validation thread to validate the new aspect.
     * @param timeToWait Time to wait before checking
     */
    private void scheduleValidation(int timeToWait) {
        
        if (thread == null) {
            return;
        }
        
        thread.scheduleValidation(timeToWait);
        
        synchronized (thread) {
            if (thread.getState() == State.WAITING) {
                thread.notify();
            }
        }
        
    }
    
    /**
     * Sets if there is a new validation.
     * This method is synchronized because this flag is used by the {@link AspectValidationThread} for setting new
     * validation errors.
     * 
     * @param isNewValidation the isNewValidation to set
     */
    public synchronized void setIsNewValidation(Boolean isNewValidation) {
        this.isNewValidation = isNewValidation;
    }
    
    /**
     * Starts the Validation thread in background.
     */
    public void startValidationThread() {
        if (thread == null) {
            thread = new AspectValidationThread();
            thread.start();
            validator.addListener(thread);
        }
    }
    
    /**
     * Stops the Validation thread.
     */
    public void stopValidationThread() {
        if (thread != null) {
            thread.interrupt();
            validator.removeListener(thread);
            thread = null;
        }
    }
    
    @Override
    public void update(long timeDelta) {
        
        AspectValidationThreadState state = thread.getAspectValidationThreadState();
        if (state != previousState) {
            for (ValidationManagerListener l : listeners) {
                l.validationState(state);
            }
            previousState = state;
        }
        
        /**
         * This verification is not synchronized to avoid to lose time,
         * because we are in the update() method which occurs each frame.
         * The rest is synchronized and occurs only when there is a new validation.
         */
        if (!getIsNewValidation()) {
            return;
        }
        
        /**
         * The flag warns if there is a new validation, so it needs to be synchronized.
         * The retrieving of the errors have to be synchronized also with this flag,
         * otherwise the lists of errors can be changed during the retrieving.
         */
        synchronized (isNewValidation) {
            setIsNewValidation(false);
            
            for (EObject o : errorsAdded.keySet()) {
                for (ValidationError validationError : errors.get(o)) {
                    gu.validationEvent(o, validationError.getSeverity());
                }
            }
            
            for (EObject o : errorsDismissed.keySet()) {
                if (!errorsDismissed.containsKey(o)) {
                    gu.validationEvent(o, null);
                } else {
                    for (ValidationError validationError : errorsDismissed.get(o)) {
                        gu.validationEvent(o, validationError.getSeverity());
                    }
                }
            }
            
            for (ValidationManagerListener l : listeners) {
                l.updateValidationErrors(errors, errorsAdded, errorsDismissed);
            }
            
        }
    }
    
    /**
     * Don't forget to destroy the contained thread when we destroy this object.
     */
    public void destroy() {
        stopValidationThread();
    }
    
}
