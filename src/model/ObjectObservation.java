package model;

import model.exceptions.NoSuchPropertyException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ObjectObservation of the world at a certain point in time.
 * @author Paul Givel and Guillaume Hartenstein
 */
public class ObjectObservation {
    /**
     * Map representing the observation.
     */
    private Map<SysObject, Map<String, String>> world;
    
    /**
     * Constructs a Map representing an empty observation.
     * Meaning that all objects were observed with properties at null values.
     * @param worldObjects Objects of the observed world
     */
    public ObjectObservation(List<SysObject> worldObjects) {
        world = new HashMap<>();
        
        for(SysObject obj : worldObjects)
            mapNewObject(obj);
    }
    
    /**
     * Change the observed property value
     * @param obj The observed object.
     * @param property The observed property
     * @param value The observed value
     * @throws IllegalAccessError 
     *  If the value is not a possible value of this property.
     * @throws NoSuchPropertyException
     *  If the object doesn't have that property
     */
    public void setObservedValue(SysObject obj, String property, String value) {
        if(!obj.isPossibleValueOf(property, value))
            throw new IllegalArgumentException(value+" is not a possible value of the property "+property);
        
        world.get(obj).replace(property, value);
    }
    
    /**
     * Access the observed value of an object's property.
     * @param obj The observed object.
     * @param property The observed property
     * @return The observed value.
     */
    public String getObservedState(SysObject obj, String property) {
        return world.get(obj).get(property);
    }
    
    /**
     * Add an object to this observation map.
     * @param object The new object
     */
    public final void mapNewObject(SysObject object) {
        Map<String, String> properties = new HashMap<>();
        for(String prop : object.getPropertiesNames())
            properties.put(prop, null);

        world.put(object, properties);
    }
    
    /**
     * Add a property to an object
     * @param object The concerned object
     * @param property The property to add
     */
    public void mapNewProperty(SysObject object, String property) {
        world.get(object).put(property, null);
    }
    
    /**
     * Remove an object from the map
     * @param object The object to remove
     */
    public void removeObject(SysObject object) {
        world.remove(object);
    }
    
    /**
     * Remove a property from an object
     * @param object The object to remove from
     * @param property The property to remove
     */
    public void removeProperty(SysObject object, String property) {
        world.get(object).remove(property);
    }
    
    /**
     * Accessor to the objects
     * @return A set of objects
     */
    public Set<SysObject> getObjects() {
        return world.keySet();
    }
}
