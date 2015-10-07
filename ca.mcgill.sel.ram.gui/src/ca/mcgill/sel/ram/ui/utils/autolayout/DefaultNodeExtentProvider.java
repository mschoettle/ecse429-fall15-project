package ca.mcgill.sel.ram.ui.utils.autolayout;

import org.abego.treelayout.NodeExtentProvider;

import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;

/**
 * Default NodeExtentProvider for ui components. Used to display auto-layout trees for feature and impact models.
 * @author CCamillieri
 * @param <T> the type of the displayed components 
 */
public class DefaultNodeExtentProvider<T extends RamRectangleComponent> implements NodeExtentProvider<T> {

    @Override
    public double getHeight(T component) {
        return component.getHeight();
    }

    @Override
    public double getWidth(T component) {
        return component.getWidth();
    }

}
