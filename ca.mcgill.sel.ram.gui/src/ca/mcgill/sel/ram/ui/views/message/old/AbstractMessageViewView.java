package ca.mcgill.sel.ram.ui.views.message.old;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.mt4j.util.font.IFont;

import ca.mcgill.sel.commons.emf.util.EMFEditUtil;
import ca.mcgill.sel.ram.AbstractMessageView;
import ca.mcgill.sel.ram.AspectMessageView;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.components.RamTextComponent;
import ca.mcgill.sel.ram.ui.layouts.HorizontalLayout;
import ca.mcgill.sel.ram.ui.layouts.VerticalLayout;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.utils.Fonts;
import ca.mcgill.sel.ram.ui.utils.Strings;
import ca.mcgill.sel.ram.ui.views.TextView;

/**
 * An abstract view for all kinds of message views.
 * Consists of a header which shows the type of the message view, 
 * its name and affected bys (unless it's an aspect message view).
 * 
 * @param <T> the specific type of the message view
 * @author mschoettle
 */
public abstract class AbstractMessageViewView<T extends AbstractMessageView> extends RamRectangleComponent 
        implements INotifyChangedListener {
    
    /**
     * The default font used within this view.
     */
    protected static final IFont DEFAULT_FONT = Fonts.DEFAULT_FONT_MEDIUM;
    
    /**
     * The default padding used for layouting this view.
     */
    protected static final int PADDING = 10;
    
    /**
     * Text to be used for separate mapped elements.
     */
    private static final String SEPARATOR = ",";
    
    /**
     * The message view represented by this view.
     */
    protected T messageView;
    private Map<AspectMessageView, TextView> affectedBys;
    
    private RamRectangleComponent header;
    private RamRectangleComponent affectedByContainer;
    private RamRectangleComponent interactionContainer;
    
    /**
     * Creates a new view for the given message view.
     * 
     * @param messageView the message view to create a view for
     */
    public AbstractMessageViewView(T messageView) {
        this.messageView = messageView;
        
        setNoStroke(false);
        setStrokeColor(Colors.DEFAULT_ELEMENT_STROKE_COLOR);
        setNoFill(false);
        setFillColor(Colors.DEFAULT_ELEMENT_FILL_COLOR);
        setAutoMinimizes(false);
        setAutoMaximizes(true);
        
        EMFEditUtil.addListenerFor(messageView, this);
    }
    
    @Override
    protected void destroyComponent() {
        EMFEditUtil.removeListenerFor(messageView, this);
    }
    
    /**
     * Adds the interaction view to this view.
     * 
     * @param interactionView the interaction view to display
     */
    protected void addInteractionView(InteractionView interactionView) {
        interactionContainer.addChild(interactionView);
    }
    
    /**
     * Adds the given name field to the header after the type field.
     * 
     * @param nameField the component showing the name of this view
     */
    protected void addNameField(RamTextComponent nameField) {
        // add name after type
        header.addChild(1, nameField);
    }
    
    /**
     * Builds this view.
     */
    protected void build() {
        // create a component for all the head components
        header = new RamRectangleComponent(new HorizontalLayout());
        header.setNoStroke(false);
        header.setBufferSize(Cardinal.EAST, PADDING);
        // let the header not auto resize
        // this way it stays as big as it is and the border stops there (looks nicer)
        header.setAutoMaximizes(false);
        header.setAutoMinimizes(true);
        addChild(header);
        
        RamTextComponent typeNameField = new RamTextComponent(DEFAULT_FONT);
        typeNameField.setText(messageView.eClass().getName());
        header.addChild(typeNameField);
        
        // add a field per affectedBy (will be needed later anyway for editing)
        affectedBys = new HashMap<AspectMessageView, TextView>();
        
        if (!(messageView instanceof AspectMessageView)) {
            buildAffectedBys();
        }
        
        // create a component that contains the interaction(s)
        // only in case of the AspectMessageView there will be two
        interactionContainer = new RamRectangleComponent(new HorizontalLayout(PADDING));
        // set the width so children can access it
        interactionContainer.setMinimumWidth(getWidth());
        // interactionContainer.setMinimumHeight(42.0f);
        addChild(interactionContainer);
        
        setLayout(new VerticalLayout());
    }
    
    /**
     * Builds the affected by list.
     */
    private void buildAffectedBys() {
        affectedByContainer = new RamRectangleComponent(new HorizontalLayout());
        
        RamTextComponent description = new RamTextComponent(DEFAULT_FONT, Strings.AFFECTED_BY);
        header.addChild(description);
        header.addChild(affectedByContainer);
        
        for (int index = 0; index < messageView.getAffectedBy().size(); index++) {
            AspectMessageView affectedBy = messageView.getAffectedBy().get(index);
            addAffectedBy(affectedBy, index);
        }
    }

    /**
     * Adds the given aspect message view to the list of affected bys at the given index.
     * 
     * @param affectedBy the {@link AspectMessageView} that affects the message view this view represents
     * @param index the model index in the list of affected bys
     */
    private void addAffectedBy(AspectMessageView affectedBy, int index) {
        int viewIndex = index * 2;
        
        // showing the full label works in this case as right now only visualization is supported
        TextView affectedByField = new TextView(affectedBy, RamPackage.Literals.ABSTRACT_MESSAGE_VIEW__AFFECTED_BY,
                true);
        affectedByField.setFont(DEFAULT_FONT);
        affectedByField.setBufferSize(Cardinal.EAST, 0);
        
        // add a separator if it is not the first element in the list
        if (affectedByContainer.getChildCount() >= 1) {
            RamTextComponent comma = new RamTextComponent(Fonts.DEFAULT_FONT_MEDIUM, SEPARATOR);
            comma.setBufferSize(Cardinal.WEST, 1);
            affectedByContainer.addChild(viewIndex - 1, comma);
        }
        
        affectedByContainer.addChild(viewIndex, affectedByField);
        
        affectedBys.put(affectedBy, affectedByField);
    }
    
   /**
    * Removes the aspect message view from the visual list of affected bys.
    * 
    * @param affectedBy the {@link AspectMessageView} that affects the message view this view represents
    */
    private void removeAffectedBy(AspectMessageView affectedBy) {
        TextView affectedByField = affectedBys.remove(affectedBy);
        int viewIndex = affectedByContainer.getChildIndexOf(affectedByField);
        
        affectedByContainer.removeChild(viewIndex);
        if (affectedByContainer.getChildCount() >= 1) {
            affectedByContainer.removeChild(viewIndex - 1, false);
        }
    }
    
    @Override
    public void notifyChanged(Notification notification) {
        if (notification.getNotifier() == messageView
                && notification.getFeature() == RamPackage.Literals.ABSTRACT_MESSAGE_VIEW__AFFECTED_BY) {
            switch (notification.getEventType()) {
                case Notification.ADD:
                    AspectMessageView affectedBy = (AspectMessageView) notification.getNewValue();
                    addAffectedBy(affectedBy, notification.getPosition());
                    break;
                case Notification.REMOVE:
                    affectedBy = (AspectMessageView) notification.getOldValue();
                    removeAffectedBy(affectedBy);
                    break;
            }
        }
    }
    
}
