package ca.mcgill.sel.ram.ui.views;

import ca.mcgill.sel.ram.ui.views.RamEnd.Position;

/**
 * The interface for views that represent an end of a relationship. The "relationship end view" must provide certain
 * functionality to handle placement and updates of the ends position imposed by
 * {@link ca.mcgill.sel.ram.ui.views.RelationshipView}.
 *
 * @author mschoettle
 */
public interface IRelationshipEndView {

    /**
     * Moves the end from the old position to the new position.
     *
     * @param end the end representation of the relationship
     * @param oldPosition the old position of the end
     * @param newPosition the requested new position of the end
     */
    void moveRelationshipEnd(RamEnd<?, ?> end, Position oldPosition, Position newPosition);

    /**
     * Updates the position of the end, which might be necessary if the opposite ends position changed.
     *
     * @param end the end representation of the relationship
     */
    void updateRelationshipEnd(RamEnd<?, ?> end);

    /**
     * Removes the end from its parent.
     *
     * @param end the end representation of the relationship
     */
    void removeRelationshipEnd(RamEnd<?, ?> end);
}
