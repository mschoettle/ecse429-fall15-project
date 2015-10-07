package ca.mcgill.sel.ram.ui.views;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import ca.mcgill.sel.commons.StringUtil;
import ca.mcgill.sel.ram.ui.components.RamImageComponent;
import ca.mcgill.sel.ram.ui.components.RamListComponent.Namer;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.components.RamSelectorComponent;
import ca.mcgill.sel.ram.ui.components.RamTextComponent;
import ca.mcgill.sel.ram.ui.components.listeners.RamSelectorListener;
import ca.mcgill.sel.ram.ui.layouts.HorizontalLayout;
import ca.mcgill.sel.ram.ui.utils.Colors;
import ca.mcgill.sel.ram.ui.utils.Fonts;

/**
 * A selector view which shows options to the user depending on a given enum. The order of how elements are shown
 * depends on the order of the elements of the enum (i.e., the ordinal). The enum may implement {@link Iconified} if an
 * icon should be displayed as well.
 *
 * @param <T> the type of the {@link Enum} used for the options of this selector
 * @author mschoettle
 */
public class OptionSelectorView<T extends Enum<?>> extends RamSelectorComponent<T> implements RamSelectorListener<T> {

    /**
     * Provides an icon.
     */
    public interface Iconified {

        /**
         * Returns the icon.
         *
         * @return the icon
         */
        RamImageComponent getIcon();

    }

    /**
     * A namer for the options of this selector. The namer is capable of displaying an icon in front of an option.
     */
    private class InternalNamer implements Namer<T> {

        @Override
        public RamRectangleComponent getDisplayComponent(T element) {
            String text = element.toString().toLowerCase(Locale.ENGLISH).replace('_', ' ');
            text = StringUtil.toUpperCaseFirstAll(text);
            RamTextComponent textComponent = new RamTextComponent(text);
            RamRectangleComponent view;

            // if it is iconified a container component is required
            if (element instanceof Iconified) {
                view = new RamRectangleComponent();
                // add the icon and set its size
                RamImageComponent image = ((Iconified) element).getIcon();
                image.setMinimumSize(ICON_SIZE, ICON_SIZE);

                view.addChild(image);
                view.addChild(textComponent);
                view.setLayout(new HorizontalLayout());
            } else {
                view = textComponent;
            }

            view.setNoFill(false);
            view.setFillColor(Colors.DEFAULT_ELEMENT_FILL_COLOR);
            view.setNoStroke(false);

            return view;
        }

        @Override
        public String getSortingName(T element) {
            return String.valueOf(element.ordinal());
        }

        @Override
        public String getSearchingName(T element) {
            return getSortingName(element);
        }

    }

    private static final int ICON_SIZE = Fonts.DEFAULT_FONT_MEDIUM.getFontAbsoluteHeight()
            + RamTextComponent.DEFAULT_BUFFER_SIZE;

    /**
     * Creates an option selector with the given elements. The elements should be values of the {@link Enum} (retrieved
     * by calling EnumName#values()).
     *
     * @param elements the elements of the {@link Enum}
     */
    public OptionSelectorView(List<T> elements) {
        super();

        showTextField(false);

        setNamer(new InternalNamer());
        setElements(elements);

        // register ourself so we will know when an element was selected to destroy this
        registerListener(this);
    }

    /**
     * Creates an option selector with the given elements. The elements should be values of the {@link Enum} (retrieved
     * by calling EnumName#values()).
     *
     * @param elements the elements of the {@link Enum}
     */
    public OptionSelectorView(T[] elements) {
        this(Arrays.asList(elements));
    }

    @Override
    public void elementSelected(RamSelectorComponent<T> selector, T element) {
        destroy();
    }

    @Override
    public void closeSelector(RamSelectorComponent<T> selector) {
        destroy();
    }

    @Override
    public void elementDoubleClicked(RamSelectorComponent<T> selector, T element) {
    }

    @Override
    public void elementHeld(RamSelectorComponent<T> selector, T element) {

    }

}
