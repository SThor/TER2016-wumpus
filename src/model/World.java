package model;

import java.util.List;
import model.exceptions.DuplicateElementException;
import solver.ExhaustiveSolver;
import solver.TooManyPossibilitiesException;

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
    
    public int getObjectCount() {
        return worldObjects.size();
    }
    
    public int getIndexOfObject(Object object) {
        return worldObjects.indexOf(object);
    }
    
    /**
     * Add an object to the world.
     *
     * @param object The object to add.
     * @return The index of the added object
     * @throws DuplicateElementException If this object is already in the world.
     */
    public int addObject(SysObject object) {
        worldObjects.add(object);
        return worldObjects.size()-1;
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
     * @param object The object to remove
     * @return The index of the removed object
     */
    public int removeObject(SysObject object) {
        int index = worldObjects.indexOf(object);
        if (worldObjects.remove(object)) {
            removeActionConditions(object, null, null);
        }
        return index;
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
        removeActionConditions(object, property, null);
    }

    /**
     * Triggered when a possible value has been removed from a property in an object
     *
     * @param object The object concerned by the modification
     * @param property The property concerned by the modification
     * @param removedValue The removed value
     */
    protected void signalPossibleValueRemoved(SysObject object, String property, String removedValue) {
        removeActionConditions(object, property, removedValue);
    }

    /**
     * Apply to all actions
     *
     * @see Action#removeAllConditions(model.SysObject, java.lang.String,
     * java.util.List)
     */
    private void removeActionConditions(SysObject object, String property, String removedValue) {
        for (Action a : possibleActions) {
            a.removeAllConditions(object, property, removedValue);
        }
    }
    
    /**
     * Calculate the number of different states this world can take.
     * That is, multiplying the number 
     * @return
     * @throws TooManyPossibilitiesException 
     */
    public long statePossibilitiesCount() throws TooManyPossibilitiesException {
        long count = 1;
        long overflowCheck;
        for (SysObject object : worldObjects) {
            overflowCheck = count * object.statePossibilitiesCount();
            // See SysObject#statePossibilitiesCount() for comment on overflowCheck
            if (overflowCheck > ExhaustiveSolver.POSSIBILITIES_CAP || overflowCheck < count) {
                throw new TooManyPossibilitiesException();
            }
            count = overflowCheck;
        }
        return count;
    }
}
