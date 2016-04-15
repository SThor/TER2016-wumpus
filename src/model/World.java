package model;

import java.util.List;
import model.exceptions.DuplicateElementException;

/**
 * Represents a whole world to study.
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class World {

    /**
     * List of objects
     */
    private final List<SysObject> worldObjects;

    /**
     * List of possible actions.
     */
    private final List<Action> possibleActions;

    /**
     * Constructs a new empty world.
     */
    public World() {
        worldObjects = new UniqueList<>();
        possibleActions = new UniqueList<>();
    }

    /**
     * Getter on a possible action
     *
     * @param index index of the desired action
     * @return desired action
     */
    public Action getActionAt(int index) {
        return possibleActions.get(index);
    }

    /**
     * Getter on an object of the world
     *
     * @param index index of the desired SysObject
     * @return desired SysObject
     */
    public SysObject getObjectAt(int index) {
        return worldObjects.get(index);
    }

    /**
     * Add an object to the world.
     *
     * @param object The object to add.
     * @throws DuplicateElementException If this object is already in the world.
     */
    public void addObject(SysObject object) {
        worldObjects.add(object);
    }

    /**
     * Add a possible action to the world.
     *
     * @param action The action to add.
     * @throws DuplicateElementException If this action is already in the world.
     */
    public void addPossibleAction(Action action) {
        possibleActions.add(action);
    }

    /**
     * Remove an object from the world and all associated actions and
     * objectObservations.
     *
     * @param index The index of the object to remove
     */
    public void removeObject(int index) {
        SysObject removed = worldObjects.remove(index);
        propagateSignalToActions(removed, null, null);
    }

    /**
     * Remove a possible action
     *
     * @param index The index of the action to remove
     */
    public void removePossibleAction(int index) {
        possibleActions.remove(index);
    }

    /**
     * Access the objects list.
     *
     * @return A list of the objects
     */
    public List<SysObject> getObjects() {
        return worldObjects;
    }

    /**
     * Access the possible actions list.
     *
     * @return A list of the possible actions
     */
    public List<Action> getPossibleActions() {
        return possibleActions;
    }

    /**
     * Triggered when a property has been removed from an object
     *
     * @param object The object concerned by the modification
     * @param property The removed property
     */
    protected void signalPropertyRemoved(SysObject object, String property) {
        propagateSignalToActions(object, property, null);
    }

    /**
     * Apply to all actions
     *
     * @see Action#removeAllConditions(model.SysObject, java.lang.String,
     * java.util.List)
     */
    private void propagateSignalToActions(SysObject object, String property, String removedValue) {
        for (Action a : possibleActions) {
            a.removeAllConditions(object, property, removedValue);
        }
    }

    void signalPossibleValueRemoved(SysObject object, String property, String removedValue) {
        propagateSignalToActions(object, property, removedValue);
    }
}
