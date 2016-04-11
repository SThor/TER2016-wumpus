package model;

import model.exceptions.NoSuchPropertyException;
import java.util.HashMap;
import java.util.Map;

/**
 * Observation of the world at a certain point in time.
 * @author Paul Givel and Guillaume Hartenstein
 */
public class Observation {
    /**
     * Map representing the observation.
     */
    private Map<SysObject, State> world;
    
    /**
     * Constructs a Map representing an empty observation.
     * Meaning that all objects were observed in state <tt>UNDIFINED</tt>.
     * @param w The observed world.
     */
    public Observation(World w) {
        world = new HashMap<>();
        
        for(SysObject obj : w.getObjects())
            world.put(obj, State.UNDEFINED);
    }
    
    /**
     * Change the observed state of an object.
     * @param obj The observed object.
     * @param state The observed state.
     * @throws NoSuchPropertyException 
     *  If <tt>state</tt> is not a possible state of object.
     */
    public void setObservedState(SysObject obj, State state) {
        if(!obj.isValidState(state))
            throw new NoSuchPropertyException();
        
        world.replace(obj, state);
    }
    
    /**
     * Access the observed state of an object.
     * @param obj The observed object.
     * @return The observed state.
     */
    public State getObservedState(SysObject obj) {
        return world.get(obj);
    }
    
    
    protected void removeObservations(SysObject object, String property, String value) {
        
    }
}
