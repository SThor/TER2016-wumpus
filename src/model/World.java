package model;

import java.util.ArrayList;
import java.util.Collections;
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
     * List of objectObservations.
     */
    private final List<ObjectObservation> objectObservations;

    /**
     * Constructs a new empty world.
     */
    public World() {
        worldObjects = new UniqueList<>();
        possibleActions = new UniqueList<>();
        objectObservations = new ArrayList<>();
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
     * Getter on an objectObservation
     *
     * @param index index of the desired ObjectObservation
     * @return desired observation
     */
    public ObjectObservation getObservationAt(int index) {
        return objectObservations.get(index);
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
        for (ObjectObservation o : objectObservations) {
            o.mapNewObject(object);
        }
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
     * Add an observation to the world
     *
     * @param index The index to add the observation to.
     * @param obs The observation.
     */
    public void addObservation(int index, ObjectObservation obs) {
        objectObservations.add(index, obs);
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
        for (ObjectObservation o : objectObservations) {
            o.removeObject(removed);
        }
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
     * Remove an observation
     *
     * @param index The index of the observation to remove
     */
    public void removeObservation(int index) {
        objectObservations.remove(index);
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
        for (ObjectObservation o : objectObservations) {
            o.removeProperty(object, property);
        }
    }

    /**
     * Triggered when a property has been added to an object
     *
     * @param object The object concerned by the modification
     * @param property The added property
     */
    protected void signalPropertyAdded(SysObject object, String property) {
        for (ObjectObservation o : objectObservations) {
            o.mapNewProperty(object, property);
        }
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

    /**
     * Access to the objectObservations
     *
     * @return The list of objectObservations
     */
    public List<ObjectObservation> getObservations() {
        return objectObservations;
    }

    /**
     * Move an observation up in the list.
     *
     * @param index The index of the observation to move.
     */
    public void moveObservationUp(int index) {
        Collections.swap(objectObservations, index, index - 1);
    }

    /**
     * Move an observation down in the list.
     *
     * @param index The index of the observation to move.
     */
    public void moveObservationDown(int index) {
        Collections.swap(objectObservations, index, index + 1);
    }

    void signalPossibleValueRemoved(SysObject object, String property, String removedValue) {
        propagateSignalToActions(object, property, removedValue);
        for (ObjectObservation o : objectObservations) {
            if (removedValue.equals(o.getObservedState(object, property))) {
                o.setObservedValue(object, property, null);
            }
        }
    }
}
