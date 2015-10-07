package ca.mcgill.sel.ram.ui.views.message.handler.impl;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import org.mt4j.components.MTComponent;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.unistrokeProcessor.UnistrokeEvent;
import org.mt4j.input.inputProcessors.componentProcessors.unistrokeProcessor.UnistrokeUtils.UnistrokeGesture;
import org.mt4j.input.inputProcessors.componentProcessors.zoomProcessor.ZoomEvent;
import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.ram.Classifier;
import ca.mcgill.sel.ram.CombinedFragment;
import ca.mcgill.sel.ram.FragmentContainer;
import ca.mcgill.sel.ram.Interaction;
import ca.mcgill.sel.ram.InteractionFragment;
import ca.mcgill.sel.ram.Lifeline;
import ca.mcgill.sel.ram.Message;
import ca.mcgill.sel.ram.MessageEnd;
import ca.mcgill.sel.ram.MessageOccurrenceSpecification;
import ca.mcgill.sel.ram.MessageSort;
import ca.mcgill.sel.ram.Operation;
import ca.mcgill.sel.ram.RamFactory;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.Reference;
import ca.mcgill.sel.ram.TypedElement;
import ca.mcgill.sel.ram.controller.ControllerFactory;
import ca.mcgill.sel.ram.controller.FragmentsController;
import ca.mcgill.sel.ram.controller.MessageViewController;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.components.RamSelectorComponent;
import ca.mcgill.sel.ram.ui.components.listeners.AbstractDefaultRamSelectorListener;
import ca.mcgill.sel.ram.ui.components.listeners.RamSelectorListener;
import ca.mcgill.sel.ram.ui.events.WheelEvent;
import ca.mcgill.sel.ram.ui.views.AbstractView;
import ca.mcgill.sel.ram.ui.views.OptionSelectorView;
import ca.mcgill.sel.ram.ui.views.SelectorView;
import ca.mcgill.sel.ram.ui.views.handler.impl.AbstractViewHandler;
import ca.mcgill.sel.ram.ui.views.message.LifelineView;
import ca.mcgill.sel.ram.ui.views.message.MessageViewView;
import ca.mcgill.sel.ram.ui.views.message.handler.IMessageViewHandler;
import ca.mcgill.sel.ram.util.RAMModelUtil;

/**
 * The default handler for {@link MessageViewView}s.
 *
 * @author mschoettle
 */
public class MessageViewHandler extends AbstractViewHandler implements IMessageViewHandler {

    private static final float MIN_DISTANCE = 5f;

    /**
     * The options to create new fragments.
     */
    private enum FragmentOptions {
        CREATE_ASSIGNMENT, CREATE_STATEMENT, CREATE_COMBINED_FRAGMENT, CREATE_SELF_MESSAGE, CREATE_REPLY_MESSAGE
    }

    @Override
    public boolean processUnistrokeEvent(UnistrokeEvent unistrokeEvent) {
        final MessageViewView view = (MessageViewView) unistrokeEvent.getTarget();
        int gestureID = unistrokeEvent.getId();

        if (gestureID == MTGestureEvent.GESTURE_STARTED) {
            view.getUnistrokeLayer().addChild(unistrokeEvent.getVisualization());
        } else if (gestureID == MTGestureEvent.GESTURE_ENDED || gestureID == MTGestureEvent.GESTURE_UPDATED) {
            Vector3D startPosition = unistrokeEvent.getCursor().getStartPosition();
            Vector3D endPosition = unistrokeEvent.getCursor().getCurrentEvent().getPosition();
            float distance = startPosition.distance(endPosition);
            LifelineView from = findLifelineView(view, startPosition);
            LifelineView to = findLifelineView(view, endPosition);

            if (distance >= MIN_DISTANCE) {
                // Highlight lifelines that can be reached while drawing the line.
                // Once ended, check for the to lifeline again.
                if (gestureID == MTGestureEvent.GESTURE_UPDATED) {
                    findLifelineViewAtX(view, from, endPosition.getX());
                } else if (gestureID == MTGestureEvent.GESTURE_ENDED) {
                    final Interaction interaction = view.getSpecification();
                    final FragmentContainer container = from.getFragmentContainerAt(startPosition);
                    /**
                     * The gesture position is global and needs to be transformed into relative, in order for the
                     * lifeline to appear independent of the current state of the view.
                     */
                    final float x = MTComponent.getGlobalVecToParentRelativeSpace(from, endPosition).getX();
                    final float y = MessageViewView.LIFELINE_Y;

                    // If no lifeline was directly "touched", try to find a lifeline at the x position.
                    // And highlight/dehighlight lifelines at the same time.
                    if (to == null) {
                        to = findLifelineViewAtX(view, from, endPosition.getX());
                    }

                    if (to != null) {
                        int addAtIndex = 1;
                        InteractionFragment fragment = to.getFragmentBefore(endPosition);

                        // fragment will be null if the lifeline doesn't contain any
                        if (fragment != null) {
                            addAtIndex = getIndexAfter(container, fragment);
                        } else {
                            fragment = from.getFragmentBefore(startPosition);
                            // Fragment is still null if we are inside an operand with no fragments yet.
                            addAtIndex = (fragment != null) ? getIndexAfter(container, fragment) : 0;
                        }

                        to.highlight(false);
                        createMessage(interaction, from.getLifeline(), to.getLifeline(), container, 
                                addAtIndex, endPosition);
                    } else {
                        InteractionFragment fragment = from.getFragmentBefore(startPosition);
                        // Fragment is still null if we are inside an operand with no fragments yet.
                        int addAtIndex = (fragment != null) ? getIndexAfter(container, fragment) : 0;

                        createLifelineWithMessage(interaction, from.getLifeline(), x, y, container, 
                                addAtIndex, endPosition);
                    }
                }
            }
        }

        return true;
    }

    /**
     * Tries to find a lifeline view the given position intersects with.
     *
     * @param view the message view containing the lifeline views
     * @param position the position to search a lifeline view for
     * @return the lifeline view that intersects with the given position, null if none found
     */
    @SuppressWarnings("static-method")
    private LifelineView findLifelineView(MessageViewView view, Vector3D position) {
        for (LifelineView lifelineView : view.getLifelineViews()) {
            if (lifelineView.containsPointGlobal(position)) {
                return lifelineView;
            }
        }

        return null;
    }

    /**
     * Tries to find a lifeline view at the given x position. The x position doesn't have to intersect with the lifeline
     * view, it is enough if the x position of the lifeline view is within a range of the given x. The first lifeline
     * view that is within range is highlighted as well, all other lifelines are de-highlighted. In case a lifeline view
     * is found, the caller needs to de-highlight the found lifeline view at a later point. The excluded lifeline view
     * is excempt from highlighting and will not be considered as the result. Potentially there could be multiple
     * lifeline views in range of x, however, only the first one is considered.
     *
     * @param view the {@link MessageViewView} containing the {@link LifelineView}s
     * @param excludedLifelineView the {@link LifelineView} to ignore
     * @param x the x position at which to look for {@link LifelineView}s
     * @return a {@link LifelineView} that is within range of the given x, null if none found
     */
    @SuppressWarnings("static-method")
    private LifelineView findLifelineViewAtX(MessageViewView view, LifelineView excludedLifelineView, float x) {
        LifelineView result = null;

        for (LifelineView lifelineView : view.getLifelineViews()) {
            if (lifelineView != excludedLifelineView) {
                float center = lifelineView.getCenterPointGlobal().getX();
                float difference = Math.abs(center - x);
                if (difference <= (MessageViewView.BOX_WIDTH / 2f) && result == null) {
                    // TODO: Highlight only valid lifelines
                    // (i.e., the ones that can actually be accessed)
                    // or highlight invalid ones in a different colour (but don't "find" them)
                    lifelineView.highlight(true);
                    result = lifelineView;
                } else {
                    lifelineView.highlight(false);
                }
            }
        }

        return result;
    }

    /**
     * Handles the creation of a lifeline with a message. First provides a selector for the "represents" of the lifeline
     * and then, after successful selection, a selector for the message to call on that lifeline.
     *
     * @param interaction the {@link Interaction} that contains the lifelines and messages
     * @param lifelineFrom the {@link Lifeline} from which the message should be originated
     * @param container the {@link FragmentContainer} that contains the fragments
     * @param x the x position of the lifeline
     * @param y the y position of the lifeline
     * @param addAtIndex the index at which to add the message ends to in the container
     * @param selectorPosition the position at which the selector should be displayed
     */
    private void createLifelineWithMessage(final Interaction interaction, final Lifeline lifelineFrom, final float x,
            final float y, final FragmentContainer container, final int addAtIndex, final Vector3D selectorPosition) {
        final Lifeline lifeline = RamFactory.eINSTANCE.createLifeline();
        final Message message = createTemporaryMessage(interaction, lifelineFrom, lifeline);
        // Create but don't use it.
        final Reference staticReference = RamFactory.eINSTANCE.createReference();

        addTemporaryObjects(interaction, message, container, addAtIndex, lifeline);

        RamSelectorComponent<Object> selector = new SelectorView(lifeline, RamPackage.Literals.LIFELINE__REPRESENTS);

        RamApp.getActiveAspectScene().addComponent(selector, selectorPosition);

        // CHECKSTYLE:IGNORE AnonInnerLength: Converting it to a nested class requires a constructor with 10 parameters.
        selector.registerListener(new RamSelectorListener<Object>() {
            @Override
            public void elementSelected(RamSelectorComponent<Object> selector, Object element) {
                if (element instanceof TypedElement) {
                    lifeline.setRepresents((TypedElement) element);
                } else if (element instanceof Classifier) {
                    Classifier classifier = (Classifier) element;
                    staticReference.setType(classifier);
                    staticReference.setStatic(true);
                    lifeline.setRepresents(staticReference);
                    interaction.getProperties().add(staticReference);
                }

                RamSelectorComponent<Object> messageSelector =
                        new SelectorView(message, RamPackage.Literals.MESSAGE__SIGNATURE);

                RamApp.getActiveAspectScene().addComponent(messageSelector, selectorPosition);

                messageSelector.registerListener(new RamSelectorListener<Object>() {

                    @Override
                    public void elementSelected(RamSelectorComponent<Object> selector, Object element) {
                        revertTemporaryObjects(interaction, message, container, lifeline, staticReference);

                        Operation operation = (Operation) element;
                        MessageViewController controller = ControllerFactory.INSTANCE.getMessageViewController();
                        controller.createLifelineWithMessage(interaction, lifeline.getRepresents(), x, y, lifelineFrom,
                                container, operation, addAtIndex);
                    }

                    @Override
                    public void closeSelector(RamSelectorComponent<Object> selector) {
                        revertTemporaryObjects(interaction, message, container, lifeline, staticReference);
                    }

                    @Override
                    public void elementDoubleClicked(RamSelectorComponent<Object> selector, Object element) {

                    }

                    @Override
                    public void elementHeld(RamSelectorComponent<Object> selector, Object element) {

                    }

                });
            }

            @Override
            public void closeSelector(RamSelectorComponent<Object> selector) {
                revertTemporaryObjects(interaction, message, container, lifeline, staticReference);
            }

            @Override
            public void elementDoubleClicked(RamSelectorComponent<Object> selector, Object element) {

            }

            @Override
            public void elementHeld(RamSelectorComponent<Object> selector, Object element) {

            }
        });
    }

    /**
     * Handles the creation of a new message. The message is created between the two given lifelines. A selector is
     * displayed to select the signature of the message.
     *
     * @param interaction the {@link Interaction} containing the lifelines
     * @param lifelineFrom the {@link Lifeline} the message originates from
     * @param lifelineTo the {@link Lifeline} the message goes to
     * @param container the {@link FragmentContainer} that contains the message ends
     * @param addAtIndex the index at which the message ends are added to in the container
     * @param selectorPosition the position at which the selector should be displayed at
     */
    private void createMessage(final Interaction interaction, final Lifeline lifelineFrom, final Lifeline lifelineTo,
            final FragmentContainer container, final int addAtIndex, Vector3D selectorPosition) {
        // Create but don't use it.
        final Message message = createTemporaryMessage(interaction, lifelineFrom, lifelineTo);
        addTemporaryObjects(interaction, message, container, addAtIndex, null);

        RamSelectorComponent<Object> selector = new SelectorView(message, RamPackage.Literals.MESSAGE__SIGNATURE);

        RamApp.getActiveAspectScene().addComponent(selector, selectorPosition);

        selector.registerListener(new RamSelectorListener<Object>() {

            @Override
            public void elementSelected(RamSelectorComponent<Object> selector, Object element) {
                revertTemporaryObjects(interaction, message, container, null, null);

                Operation operation = (Operation) element;
                MessageViewController controller = ControllerFactory.INSTANCE.getMessageViewController();
                controller.createMessage(interaction, lifelineFrom, lifelineTo, container, operation, addAtIndex);
            }

            @Override
            public void closeSelector(RamSelectorComponent<Object> selector) {
                revertTemporaryObjects(interaction, message, container, null, null);
            }

            @Override
            public void elementDoubleClicked(RamSelectorComponent<Object> selector, Object element) {

            }

            @Override
            public void elementHeld(RamSelectorComponent<Object> selector, Object element) {

            }

        });
    }

    /**
     * Creates a temporary message between the two lifelines to be able to make use of the item providers for the valid
     * choices visualized in the selector.
     *
     * @param interaction the {@link Interaction} containing the lifelines
     * @param lifelineFrom the {@link Lifeline} the message originates from
     * @param lifelineTo the {@link Lifeline} the message goes to
     * @return the temporary message that was created
     */
    @SuppressWarnings("static-method")
    private Message createTemporaryMessage(Interaction interaction, Lifeline lifelineFrom, Lifeline lifelineTo) {
        Message message = RamFactory.eINSTANCE.createMessage();
        MessageOccurrenceSpecification sendEvent = RamFactory.eINSTANCE.createMessageOccurrenceSpecification();
        sendEvent.getCovered().add(lifelineFrom);
        sendEvent.setMessage(message);
        MessageOccurrenceSpecification receiveEvent = RamFactory.eINSTANCE.createMessageOccurrenceSpecification();
        receiveEvent.getCovered().add(lifelineTo);
        receiveEvent.setMessage(message);

        message.setSendEvent(sendEvent);
        message.setReceiveEvent(receiveEvent);

        return message;
    }

    /**
     * Adds the temporary objects (message, its events and (optional) a lifeline) to the given interaction and
     * container. <b>Note:</b> This operation has to be reverted at a later point by the caller (see
     * {@link #revertTemporaryObjects(Interaction, Message, FragmentContainer, Lifeline, Reference)})
     *
     * @param interaction the {@link Interaction} the temporary objects should be added to
     * @param message the temporary {@link Message} to add to the interaction
     * @param container the {@link FragmentContainer} the message ends should be added to
     * @param addAtIndex the index at which the message ends should be added to the container
     * @param lifeline the lifeline to add to the interaction, null if not necessary
     */
    @SuppressWarnings("static-method")
    private void addTemporaryObjects(Interaction interaction, Message message, FragmentContainer container,
            int addAtIndex, Lifeline lifeline) {
        // Suppress notifications while manipulating interaction temporarily for selectors.
        interaction.eSetDeliver(false);
        interaction.getMessages().add(message);
        container.eSetDeliver(false);
        container.getFragments().add(addAtIndex, (InteractionFragment) message.getSendEvent());
        container.getFragments().add(addAtIndex + 1, (InteractionFragment) message.getReceiveEvent());

        if (lifeline != null) {
            interaction.getLifelines().add(lifeline);
        }
    }

    /**
     * Reverts the previously added temporary objects by removing them from their containers.
     *
     * @param interaction the {@link Interaction} the temporary objects should be removed from
     * @param message the temporary {@link Message} to remove from the interaction
     * @param container the {@link FragmentContainer} the message ends should be remove from
     * @param lifeline the lifeline to remove from the interaction, null if not necessary
     * @param reference the static reference to remove from the interaction, null if not necessary
     */
    @SuppressWarnings("static-method")
    private void revertTemporaryObjects(Interaction interaction, Message message, FragmentContainer container,
            Lifeline lifeline, Reference reference) {

        interaction.eSetDeliver(false);
        container.eSetDeliver(false);

        InteractionFragment sendEvent = (InteractionFragment) message.getSendEvent();
        InteractionFragment receiveEvent = (InteractionFragment) message.getReceiveEvent();

        // Force removal of lifelines coveredBy.
        sendEvent.getCovered().clear();
        receiveEvent.getCovered().clear();

        container.getFragments().remove(receiveEvent);
        container.getFragments().remove(sendEvent);
        interaction.getMessages().remove(message);
        // Will only be removed if they are not null.
        interaction.getLifelines().remove(lifeline);
        interaction.getProperties().remove(reference);

        // Enable notifications again.
        interaction.eSetDeliver(true);
        container.eSetDeliver(true);
    }

    @Override
    public void handleCreateFragment(final MessageViewView messageViewView, final LifelineView lifelineView,
            final Vector3D location, final FragmentContainer container) {
        InteractionFragment fragment = lifelineView.getFragmentBefore(location);

        final int addAtIndex = (fragment != null) ? getIndexAfter(container, fragment) : 0;
        final Lifeline lifeline = lifelineView.getLifeline();
        final Interaction interaction = (Interaction) lifeline.eContainer();

        EnumSet<FragmentOptions> availableOptions = EnumSet.allOf(FragmentOptions.class);

        /**
         * If there is no fragment in an operand, the fragment is null and we need to use the containing combined
         * fragment to retrieve the initial message.
         */
        if (fragment == null) {
            fragment = (CombinedFragment) container.eContainer();
        }

        /**
         * The options need to be filtered in case there may not be a reply possible. An additional reply should only be
         * possible inside a combined fragment and at the end of an operand as the last fragment. Also, a reply should
         * not be possible if the initial message represents anything other than a synch call.
         */
        final Message initialMessage = RAMModelUtil.findInitialMessage(fragment);

        if (initialMessage == null
                || initialMessage.getSignature().getReturnType().eClass() == RamPackage.Literals.RVOID
                || container == interaction
                // Right now we only allow reply messages for the operation the message view defines.
                || initialMessage.getSignature() != messageViewView.getSpecifies()
                || initialMessage.getMessageSort() != MessageSort.SYNCH_CALL
                // Only allow reply messages when they are added as the last message in the container.
                // The check works, because the container is not the interaction, but an operand.
                || (container.getFragments().size() - addAtIndex > 0)) {
            availableOptions.remove(FragmentOptions.CREATE_REPLY_MESSAGE);
        }

        OptionSelectorView<FragmentOptions> selector =
                new OptionSelectorView<FragmentOptions>(availableOptions.toArray(new FragmentOptions[] {}));

        RamApp.getActiveAspectScene().addComponent(selector, location);

        // CHECKSTYLE:IGNORE AnonInnerLength: Acceptable here.
        selector.registerListener(new AbstractDefaultRamSelectorListener<FragmentOptions>() {

            @Override
            public void elementSelected(RamSelectorComponent<FragmentOptions> selector, FragmentOptions element) {
                FragmentsController fragmentsController = ControllerFactory.INSTANCE.getFragmentsController();
                MessageViewController messageViewController = ControllerFactory.INSTANCE.getMessageViewController();

                switch (element) {
                    case CREATE_STATEMENT:
                        fragmentsController.createExecutionStatement(container, lifeline, addAtIndex);
                        break;
                    case CREATE_COMBINED_FRAGMENT:
                        fragmentsController.createCombinedFragment(container, lifeline, addAtIndex);
                        break;
                    case CREATE_SELF_MESSAGE:
                        createMessage(interaction, lifeline, lifeline, container, addAtIndex, location);
                        break;
                    case CREATE_REPLY_MESSAGE:
                        /**
                         * Since reply messages in nested behaviour is not possible right now, the to lifeline is always
                         * null. Otherwise the to lifeline could be retrieved from the initial message's send event.
                         */
                        messageViewController.createReplyMessage(interaction, lifeline, null, container,
                                initialMessage.getSignature(), addAtIndex);
                        break;
                    case CREATE_ASSIGNMENT:
                        fragmentsController.createAssignmentStatement(container, lifeline, addAtIndex);
                        break;
                }
            }

        });
    }

    /**
     * Returns the view index after the view index of the given fragment. In case of message ends it depends on the
     * message kind.
     *
     * @param container the {@link FragmentContainer} containing the fragments
     * @param fragment the preceding {@link InteractionFragment} for which to find the following view index
     * @return the view index after the view index of the given fragment
     */
    private static int getIndexAfter(FragmentContainer container, InteractionFragment fragment) {
        int index = container.getFragments().indexOf(fragment) + 1;

        // When there is a create message on the first lifeline a fragment may not be found.
        // No index (-1) will be found.
        // Assume that the new index will be 1.
        if (fragment == null) {
            index = 1;
        } else if (fragment instanceof MessageOccurrenceSpecification) {
            MessageOccurrenceSpecification messageEnd = (MessageOccurrenceSpecification) fragment;

            // If the end is not the receive event, we need to find the last event.
            if (messageEnd.getMessage().getSendEvent() == messageEnd) {
                // If it is the send event and part of a reply message,
                // we want the index after the receive event (index + 2).
                if (messageEnd.getMessage().getMessageSort() == MessageSort.REPLY) {
                    index++;
                } else {
                    Lifeline lifeline = messageEnd.getCovered().get(0);

                    /**
                     * Possible lifelines are consecutive lifelines that the behaviour is covered on going from the
                     * current message end.
                     */
                    Set<Lifeline> possibleLifelines = new HashSet<Lifeline>();
                    possibleLifelines.add(lifeline);
                    possibleLifelines.add(getLifeline(messageEnd.getMessage().getReceiveEvent()));

                    for (int i = index; i < container.getFragments().size(); i++) {
                        InteractionFragment currentFragment = container.getFragments().get(i);

                        /**
                         * Certain cases need to be considered. 1. The fragment covers the same lifeline, so it comes
                         * after, then the current index is the correct one. 2. Otherwise we need to check if the
                         * covered lifeline is a possible one. a) In case of send events the receive events lifeline is
                         * added, if the send event covers a possible lifeline. Otherwise we found a message that does
                         * not belong to the behaviour and we found the next index. b) For all other fragments we only
                         * need to check if the covered lifeline is valid, otherwise we found one outside and therefore
                         * the next index.
                         */
                        if (lifeline.getCoveredBy().contains(currentFragment)) {
                            index = i;
                            break;
                        } else {
                            if (currentFragment instanceof MessageOccurrenceSpecification) {
                                MessageOccurrenceSpecification event = (MessageOccurrenceSpecification) currentFragment;

                                if (event.getMessage().getSendEvent() == event) {
                                    if (!possibleLifelines.contains(event.getCovered().get(0))) {
                                        break;
                                    } else {
                                        possibleLifelines.add(getLifeline(event.getMessage().getReceiveEvent()));
                                    }
                                }
                            } else {
                                if (!possibleLifelines.contains(currentFragment.getCovered().get(0))) {
                                    break;
                                }
                            }
                        }

                        index++;
                    }
                }
            }
        }

        return index;
    }

    /**
     * Returns the lifeline the given message end covers.
     *
     * @param messageEnd the {@link MessageEnd} to get the covered lifeline for
     * @return the lifeline that is covered by the given message end
     */
    private static Lifeline getLifeline(MessageEnd messageEnd) {
        MessageOccurrenceSpecification event = (MessageOccurrenceSpecification) messageEnd;
        return event.getCovered().get(0);
    }

    @Override
    public void handleUnistrokeGesture(AbstractView<?> target, UnistrokeGesture gesture, Vector3D startPosition,
            UnistrokeEvent event) {
        // Ignore.
    }

    @Override
    protected boolean processWheelEvent(WheelEvent wheelEvent) {
        return true;
    }

    @Override
    protected boolean processZoomEvent(ZoomEvent zoomEvent) {
        return true;
    }

}
