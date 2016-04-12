package model;

import java.util.ArrayList;
import java.util.List;
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
     * List the object's properties.
     */
    private final List<ObjectProperty> properties;
    
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
        properties = new ArrayList<>();
    }
    
    /**
     * Change the value of a property of this object via its name.
     * @param property Name of the property to change
     * @throws NoSuchPropertyException If the property doesn't exist in this object.
     */
    public final void changeValueOf(String property) {
        changeValueOf(new ObjectProperty(property));
    }
    
    /**
     * Change the value of a property of this object.
     * @param propertyToChange Property to change
     * @throws NoSuchPropertyException If the property doesn't exist in this object.
     */
    public final void changeValueOf(ObjectProperty propertyToChange) {
        if(properties.contains(propertyToChange)){
            for (ObjectProperty property : properties) {
                if(property.equals(propertyToChange)){
                    property.changeToNextValue();
                    break;
                }
            }
        }else{
            throw new NoSuchPropertyException(propertyToChange.getName(), toString());
        }
    }
    
    /**
     * Set the value of a property of this object to undefined via its name
     * @param property Name of the property to set to undefined
     * @throws NoSuchPropertyException If the property doesn't exist in this object.
     */
    public void setToUndefined(String property) {
        setToUndefined(new ObjectProperty(property));
    }
    
    /**
     * Set the value of a property of this object to undefined
     * @param propertyToChange Property to set to undefined
     * @throws NoSuchPropertyException If the property doesn't exist in this object.
     */
    public void setToUndefined(ObjectProperty propertyToChange) {
        if(properties.contains(propertyToChange)){
            for (ObjectProperty property : properties) {
                if(property.equals(propertyToChange)){
                    property.setToUndefined();
                    break;
                }
            }
        }else{
            throw new NoSuchPropertyException(propertyToChange.getName(), toString());
        }
    }
    
    /**
     * Accessor to the current value of a property via its name
     * @param property Name of the property to access
     * @return The current value of the property
     * @throws NoSuchPropertyException If the property doesn't exist in this object.
     */
    public String getCurrentValueOf(String property) {
        return getCurrentValueOf(new ObjectProperty(property));
    }
    
    /**
     * Accessor to the current value of a property
     * @param propertyWanted The property to access
     * @return The current value of the property
     * @throws NoSuchPropertyException If the property doesn't exist in this object.
     */
    public String getCurrentValueOf(ObjectProperty propertyWanted) {
        if(properties.contains(propertyWanted)){
            String value = null;
            for (ObjectProperty property : properties) {
                if(property.equals(propertyWanted)){
                    value = property.getCurrentValue();
                    break;
                }
            }
            return value;
        }else{
            throw new NoSuchPropertyException(propertyWanted.getName(), toString());
        }
    }
    
    /**
     * Add a property via its name to this object or replace the possible values of an existing property via its name
     * @param property The name of the property to add
     * @param newPossibleValues The possible values for the property
     */
    public void setPossibleValuesOf(String property, final UniqueList<String> newPossibleValues) {
        setPossibleValuesOf(new ObjectProperty(property), newPossibleValues);
    }
    
    /**
     * Add a property to this object or replace the possible values of an existing property
     * @param property The property to add
     * @param newPossibleValues The possible values for the property
     */
    public void setPossibleValuesOf(ObjectProperty property, final UniqueList<String> newPossibleValues) {
        if(properties.contains(property)){
            List<String> oldValues = null;
            for (ObjectProperty prop : properties) {
                if(prop.equals(property)){
                    oldValues = prop.getPossibleValues();
                    prop.setPossibleValues(newPossibleValues);
                    break;
                }
            }
            world.signalPossibleValuesChanged(this, property.getName(), oldValues);
        }else{
            properties.add(new ObjectProperty(newPossibleValues, property.getName()));
            world.signalPropertyAdded(this, property.getName());
        }
    }
    
    /**
     * Remove a property from this object via its name
     * @param property The name of the property to remove
     */
    public void removeProperty(String property) {
        removeProperty(new ObjectProperty(property));
    }
    
    /**
     * Remove a property from this object
     * @param property The property to remove
     */
    public void removeProperty(ObjectProperty property) {
        if( properties.remove(property))
            world.signalPropertyRemoved(this, property.getName());
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
        List<String> list = new UniqueList<>();
        for (ObjectProperty property : properties) {
            list.add(property.getName());
        }
        return list;
    }
    
    /**
     * Checks wether a value is a possible value of a property via the property's name.
     * @param property Name of the concerned property
     * @param value The value to check
     * @return <tt>true</tt> if the value is one of the property's possible values,
     *         <tt>false</tt> otherwise.
     * @throws NoSuchPropertyException If the property doesn't exist in this object.
     */
     public boolean isPossibleValueOf(String property, String value) {
        return isPossibleValueOf(new ObjectProperty(property), value);
    }
     
    /**
     * Checks wether a value is a possible value of a property.
     * @param propertyToCheck The concerned property
     * @param value The value to check
     * @return <tt>true</tt> if the value is one of the property's possible values,
     *         <tt>false</tt> otherwise.
     * @throws NoSuchPropertyException If the property doesn't exist in this object.
     */
     public boolean isPossibleValueOf(ObjectProperty propertyToCheck, String value) {
        if(properties.contains(propertyToCheck)){
            boolean res = false;
            for (ObjectProperty property : properties) {
                if(property.equals(propertyToCheck)){
                    res = property.getPossibleValues().contains(value);
                    break;
                }
            }
            return res;
        }else{
            throw new NoSuchPropertyException(propertyToCheck.getName(), toString());
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

    public List<ObjectProperty> getProperties() {
        return properties;
    }
}
