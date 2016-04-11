package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.exceptions.DuplicateElementException;

/**
 * Represents a whole world to study.
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
     * List of observations.
     */
    private final List<Observation> observations;
    
    /**
     * Constructs a new empty world.
     */
    public World() {
        worldObjects = new UniqueList<>();
        possibleActions = new UniqueList<>();
        observations = new ArrayList<>();
    }
    
    /**
     * Add an object to the world.
     * @param object The object to add.
     * @throws DuplicateElementException If this object is already in the world.
     */
    public void addObject(SysObject object) {
        worldObjects.add(object);
        for(Observation o : observations)
            o.signalObjectAdded(object);
    }
    
    /**
     * Add a possible action to the world.
     * @param action The action to add.
     * @throws DuplicateElementException If this action is already in the world.
     */
    public void addPossibleAction(Action action) {
        possibleActions.add(action);
    }
    
    /**
     * Add an observation to the world
     * @param index The index to add the observation to.
     * @param obs The observation.
     */
    public void addObservation(int index, Observation obs) {
        observations.add(index, obs);
    }
    
    /**
     * Remove an object from the world and all 
     * associated actions and observations.
     * @param index The index of the object to remove
     */
    public void removeObject(int index) {
        SysObject removed = worldObjects.remove(index);
        
        propagateSignalToActions(removed, null, null);
        
        for(Observation o : observations)//TODO
            o.rem oveObservations(removed, null, null);
    }
    
    /**
     * Remove a possible action
     * @param index The index of the action to remove
     */
    public void removePossibleAction(int index) {
        possibleActions.remove(index);
    }
    
    /**
     * Remove an observation
     * @param index The index of the observation to remove
     */
    public void removeObservation(int index) {
        observations.remove(index);
    }
    
    /**
     * Access the objects list.
     * @return A list of the objects
     */
    public List<SysObject> getObjects() {
        return worldObjects;
    }
    
    /**
     * Access the possible actions list.
     * @return A list of the possible actions
     */
    public List<Action> getPossibleActions() {
        return possibleActions;
    }
    
    /**
     * Triggered when a property has been removed from an object
     * @param object The object concerned by the modification
     * @param property The removed property
     */
    protected void signalPropertyRemoved(SysObject object, String property) {
        propagateSignalToActions(object, property, null);
        
        for(Observation o : observations) //TODO
            if(o.getObservedState(object).equals(state))
                o.setObservedState(object, State.UNDEFINED);
    }
    
    /**
     * Triggered when a property has changed its possible values in an object.
     * @param object The concerned object
     * @param property The concerned property
     * @param removedValues Values that were in the previous version
     */
    protected void signalPossibleValuesChanged(SysObject object, String property, List<String> removedValues) {
        propagateSignalToActions(object, property, removedValues);
        //TODO observation
    }
    
    /**
     * Apply to all actions
     * @see Action#removeAllConditions(model.SysObject, java.lang.String, java.util.List) 
     */
    private void propagateSignalToActions(SysObject object, String property, List<String> removedValues) {
        for(Action a : possibleActions)
            a.removeAllConditions(object, property, removedValues);
    }
    /**
     * Access to the observations
     * @return The list of observations
     */
    public List<Observation> getObservations() {
        return observations;
    }

    /**
     * Move an observation up in the list.
     * @param index The index of the observation to move.
     */
    public void moveObservationUp(int index) {
        Collections.swap(observations, index, index-1);
    }
    
    /**
     * Move an observation down in the list.
     * @param index The index of the observation to move.
     */
    public void moveObservationDown(int index) {
        Collections.swap(observations, index, index+1);
    }
}
