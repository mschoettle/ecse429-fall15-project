package ca.mcgill.sel.ram.ui.layouts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.mt4j.components.MTComponent;
import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.ram.ui.components.RamRectangleComponent;
import ca.mcgill.sel.ram.ui.components.RamRectangleComponent.Cardinal;

/**
 * A grid layout with m*n elements. All elements in a column will have the same width and
 * all elements in a row will have the same height. Note that the position of the elements
 * in the grid is determined by their position in the child list.
 * 
 * To keep a location empty, use a RamSpacerComponent.
 * 
 * @author vbonnet
 * @author tdimeco
 */
public class GridLayout extends AbstractBaseLayout {
    
    private int numRows;
    private int numCols;
    private float padding;
    
    /**
     * Creates a grid layout with the given number of columns and expand rows depending on the content.
     * 
     * @param cols The number of columns in this layout
     */
    public GridLayout(int cols) {
        this(0, cols, 0);
    }
    
    /**
     * Creates a grid layout with the given number of columns and expand rows depending on the content.
     * 
     * @param cols The number of columns in this layout
     * @param padding The padding between each cell
     */
    public GridLayout(int cols, float padding) {
        this(0, cols, padding);
    }
    
    /**
     * Creates a grid layout with the given dimensions.
     * 
     * @param rows The number of rows in this layout.
     * @param cols The number of columns in this layout.
     */
    public GridLayout(int rows, int cols) {
        this(rows, cols, 0);
    }
    
    /**
     * Creates a grid layout with the given dimensions.
     * 
     * @param rows The number of rows in this layout.
     * @param cols The number of columns in this layout.
     * @param padding The padding between each cell
     */
    public GridLayout(int rows, int cols, float padding) {
        this.numRows = rows;
        this.numCols = cols;
        this.padding = padding;
    }
    
    @Override
    public void layout(final RamRectangleComponent component, LayoutUpdatePhase updatePhase) {
        
        // Get only RAM components
        final List<RamRectangleComponent> components = new ArrayList<RamRectangleComponent>();
        for (MTComponent c : component.getChildren()) {
            if (c instanceof RamRectangleComponent) {
                components.add((RamRectangleComponent) c);
            }
        }
        
        final int nComponents = components.size();
        final int nCols = this.numCols;
        final int nRows = this.numRows <= 0 ? getRowForIndex(nComponents - 1, nCols) + 1 : this.numRows;
        
        final float[] colWidths = new float[nCols];
        final float[] rowHeights = new float[nRows];
        
        float minimumColWidthAboveZero = -1;
        
        // Get max width/height for each column/row
        Arrays.fill(colWidths, -1);
        
        for (int i = 0; i < nComponents; i++) {
            
            final int row = getRowForIndex(i, nCols);
            final int col = getColForIndex(i, nCols);
            RamRectangleComponent c = components.get(i);
            
            c.minimizeSize();
            
            final float w = c.getWidth();
            final float h = c.getHeight();
            colWidths[col] = Math.max(colWidths[col], w);
            rowHeights[row] = Math.max(rowHeights[row], h);
            
            if (minimumColWidthAboveZero < 0) {
                minimumColWidthAboveZero = w;
            } else {
                minimumColWidthAboveZero = Math.min(minimumColWidthAboveZero, w);
            }
        }
        
        for (int n = 0; n < colWidths.length; n++) {
            if (colWidths[n] < 0) {
                colWidths[n] = minimumColWidthAboveZero;
            }
        }
        
        // Compute content size
        float contentWidth = 0;
        float contentHeight = 0;
        
        for (float width : colWidths) {
            contentWidth += width;
        }
        for (float height : rowHeights) {
            contentHeight += height;
        }
        
        final float totalPaddingWidth = nCols > 0 ? (nCols - 1) * padding : 0;
        final float totalPaddingHeight = nRows > 0 ? (nRows - 1) * padding : 0;
        
        // If the update occurs from a child, the layout has to fit the content.
        // Otherwise, the content has to stretch to fill the current size of the layout.
        if (updatePhase == LayoutUpdatePhase.FROM_CHILD && !getForceToKeepSize()) {
            
            final float width = component.getBufferSize(Cardinal.WEST) + contentWidth + totalPaddingWidth
                    + component.getBufferSize(Cardinal.EAST);
            final float height = component.getBufferSize(Cardinal.NORTH) + contentHeight + totalPaddingHeight
                    + component.getBufferSize(Cardinal.SOUTH);
            
            setMinimumSize(component, width, height);
            
        } else {
            
            final float componentWidth = component.getChildWidth() - totalPaddingWidth;
            final float componentHeight = component.getChildHeight() - totalPaddingHeight;
            final float widthRatio = componentWidth / contentWidth;
            final float heightRatio = componentHeight / contentHeight;
            
            for (int m = 0; m < rowHeights.length; m++) {
                rowHeights[m] *= heightRatio;
            }
            for (int n = 0; n < colWidths.length; n++) {
                colWidths[n] *= widthRatio;
            }
            
            contentWidth = componentWidth;
            contentHeight = componentHeight;
        }
        
        // Update position/size of components
        float currentX = 0;
        float currentY = 0;
        
        for (int i = 0; i < nComponents; i++) {
            
            final int row = getRowForIndex(i, nCols);
            final int col = getColForIndex(i, nCols);
            RamRectangleComponent c = components.get(i);
            
            c.setPositionRelativeToParent(new Vector3D(
                    component.getBufferSize(Cardinal.WEST) + currentX,
                    component.getBufferSize(Cardinal.NORTH) + currentY));
            
            if (c.autoMaximizes()) {
                c.setSizeLocal(colWidths[col], rowHeights[row]);
            }
            
            currentX += colWidths[col] + padding;
            
            if (col + 1 >= nCols) {
                currentX = 0;
                currentY += rowHeights[row] + padding;
            }
        }
    }
    
    /**
     * Get the row from the index of a component.
     * 
     * @param index The index of the component
     * @param nCols The number of columns
     * @return The row index
     */
    private static int getRowForIndex(int index, int nCols) {
        return ((int) Math.ceil(((float) (index + 1)) / (float) nCols)) - 1;
    }
    
    /**
     * Get the column from the index of a component.
     * 
     * @param index The index of the component
     * @param nCols The number of columns
     * @return The column index
     */
    private static int getColForIndex(int index, int nCols) {
        return index % nCols;
    }
}
