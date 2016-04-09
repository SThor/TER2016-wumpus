package model;

import java.util.ArrayList;
import java.util.List;
import model.exceptions.DuplicateElementException;

/**
 * Represents a whole system to study.
 * @author Paul Givel and Guillaume Hartenstein
 */
public class System {
    /**
     * List of objects
     */
    private final UniqueList<SysObject> systemObjects;
    
    /**
     * List of possible actions.
     */
    private final UniqueList<Action> possibleActions;
    
    /**
     * List of observations.
     */
    private final List<Observation> observations;
    
    /**
     * Constructs a new empty system.
     */
    public System() {
        systemObjects = new UniqueList<>();
        possibleActions = new UniqueList<>();
        observations = new ArrayList<>();
    }
    
    /**
     * Add an object to the system.
     * @param object The object to add.
     * @throws DuplicateElementException If this object is already in the system.
     */
    public void addObject(SysObject object) {
        systemObjects.add(object);
        for(Observation o : observations)
            o.updateSystem(this);
    }
    
    /**
     * Add a possible action to the system.
     * @param action The action to add.
     * @throws DuplicateElementException If this action is already in the system.
     */
    public void addPossibleAction(Action action) {
        possibleActions.add(action);
    }
    
    /**
     * Add an observation to the system
     * @param index The index to add the observation to.
     * @param obs The observation.
     */
    public void addObservation(int index, Observation obs) {
        observations.add(index, obs);
    }
    
    /**
     * Remove an object from the system and all 
     * associated actions and observations.
     * @param index The index of the object to remove
     */
    public void removeObject(int index) {
        SysObject object = systemObjects.get(index);
        
        for(Action a : possibleActions.asList()) {
            a.removePreConditions(object, null);
            a.removePostConditions(object, null);
        }
        
        systemObjects.remove(index);
        
        for(Observation o : observations)
            o.updateSystem(this);
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
     * @return An unmodifiable list of the objects
     */
    public List<SysObject> getObjects() {
        return systemObjects.asList();
    }

    /**
     * Update the system in case of a possible state deletion of an object.
     * @param object The modified object
     * @param state The deleted state
     */
    protected void updateSystem(SysObject object, State state) {
        for(Observation o : observations)
            if(o.getObservedState(object).equals(state))
                o.setObservedState(object, State.UNDEFINED);
        
        for(Action a : possibleActions.asList()) {
            a.removePreConditions(object, state);
            a.removePostConditions(object, state);
        }
    }
}
