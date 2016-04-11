package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import model.exceptions.NoSuchPropertyException;

/**
 * Represent an object of a sytem.
 * @author Paul Givel and Guillaume Hartenstein
 */
public class SysObject {
    /**
     * Name of the object
     */
    private final String name;
    
    /**
     * Map the object's properties.
     */
    private final Map<String, ObjectProperty> properties;
    
    /**
     * Reference to the world this object is in
     */
    private World world;

    /**
     * Constructs a new world object.
     * @param name Teh name of the object.
     * @param world The world this object is in
     * @throws NullPointerException If the name is <tt>null</tt>
     */
    public SysObject(String name, World world) {
        if(name == null)
            throw new NullPointerException();
        
        this.name = name;
        this.world = world;
        properties = new HashMap<>();
    }
    
    /**
     * Change the value of a property of this object.
     * @param property Property to change
     * @throws NoSuchPropertyException If the property doesn't exist in this object.
     */
    public final void changeValueOf(String property) {
        try {
            properties.get(property).changeToNextValue();
        } catch(NullPointerException e) {
            throw new NoSuchPropertyException(property, toString());
        }
    }
    
    /**
     * Set the value of a property of this object to undefined
     * @param property Property to set to undefined
     * @throws NoSuchPropertyException If the property doesn't exist in this object.
     */
    public void setToUndefined(String property) {
        try {
            properties.get(property).setToUndefined();
        } catch(NullPointerException e) {
            throw new NoSuchPropertyException(property, toString());
        }
    }
    
    /**
     * Accessor to the current value of a property
     * @param property The property to access
     * @return The current value of the property
     * @throws NoSuchPropertyException If the property doesn't exist in this object.
     */
    public String getCurrentValueOf(String property) {
        try {
            return properties.get(property).getCurrentValue();
        } catch(NullPointerException e) {
            throw new NoSuchPropertyException(property, toString());
        }
    }
    
    /**
     * Add a property to this object or replace the possible values of an existing property
     * @param property The name of the property to add
     * @param newPossibleValues The possible values for the property
     */
    public void setPossibleValuesOf(String property, final UniqueList<String> newPossibleValues) {
        List<String> oldValues = properties.put(property, new ObjectProperty(newPossibleValues)).getPossibleValues();
        if(oldValues != null) {
            // Will retain only values that are not in the new possible values
            oldValues.removeIf(new Predicate<String>(){
                @Override
                public boolean test(String t) {
                    return newPossibleValues.contains(t);
                }
            });
            world.signalPossibleValuesChanged(this, property, oldValues);
        }
    }
    
    /**
     * Remove a property from this object
     * @param property The name of the property to remove
     */
    public void removeProperty(String property) {
        if(properties.remove(property) != null)
            world.signalPropertyRemoved(this, property);
    }
    
    @Override
    public String toString() {
        return name;
    }
    
    /**
     * Accessor to properties' names
     * @return A set of the properties names.
     */
    public List<String> getPropertiesNames() {
        return new UniqueList<>(properties.keySet());
    }
    
    /**
     * Checks wether a value is a possible value of a property.
     * @param property The concerned property
     * @param value The value to check
     * @return <tt>true</tt> if the value is one of the property's possible values,
     *         <tt>false</tt> otherwise.
     * @throws NoSuchPropertyException If the property doesn't exist in this object.
     */
     public boolean isPossibleValueOf(String property, String value) {
        try {
            return properties.get(property).getPossibleValues().contains(value);
        } catch(NullPointerException e) {
            throw new NoSuchPropertyException(property, toString());
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + name.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof SysObject))
            return false;
        
        return ((SysObject)obj).name.equals(name);
    }
}
