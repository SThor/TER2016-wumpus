package model;

import model.exceptions.UnknownObjectStateException;
import java.util.HashMap;
import java.util.Map;

/**
 * Observation of the system at a certain point in time.
 * @author Paul Givel and Guillaume Hartenstein
 */
public class Observation {
    /**
     * Map representing the observation.
     */
    private Map<SysObject, State> system;
    
    /**
     * Constructs a Map representing an empty observation.
     * Meaning that all objects were observed in state <tt>UNDIFINED</tt>.
     * @param s The observed system.
     */
    public Observation(System s) {
        system = new HashMap<>();
        
        for(SysObject obj : s.getObjects())
            system.put(obj, State.UNDEFINED);
    }
    
    /**
     * Change the observed state of an object.
     * @param obj The observed object.
     * @param state The observed state.
     * @throws UnknownObjectStateException 
     *  If <tt>state</tt> is not a possible state of object.
     */
    public void setObservedState(SysObject obj, State state) {
        if(!obj.isValidState(state))
            throw new UnknownObjectStateException();
        
        system.replace(obj, state);
    }
    
    /**
     * Access the observed state of an object.
     * @param obj The observed object.
     * @return The observed state.
     */
    public State getObservedState(SysObject obj) {
        return system.get(obj);
    }
    
    /**
     * Change update the observed system
     * @param s The observed system
     */
    protected void updateSystem(System s) {
        Map<SysObject, State> old = new HashMap<>(system);
        system = new HashMap<>();
        for(SysObject obj : s.getObjects())
            system.put(obj, old.getOrDefault(obj, State.UNDEFINED));
    } 
}
