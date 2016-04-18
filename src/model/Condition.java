package model;

import model.observations.Observation;
import model.exceptions.NoSuchPropertyException;

/**
 * Represents a condition, which is an object and a wanted value of a certain property of this object.
 * The condition is verified if the <tt>SysObject</tt>'s current property is the <tt>State</tt>.
 * @author Paul Givel and Guillaume Hartenstein
 */
public class Condition implements Observation {
    /**
     * The concerned object.
     */
    private SysObject object;
    
    /**
     * The name of the concerned property
     */
    private String propertyName;
    
    /**
     * The wanted value for this property
     */
    private String wantedValue;

    /**
     * Constructs a condition.
     * <b>Warning:</b> it is possible to create a condition on a value not 
     * contained in the property's possible values. Such a condition will
     * never be verified.
     * @param object The object concerned
     * @param propertyName The name of the property concerned
     * @param wantedValue The wanted value for this property
     * @throws NoSuchPropertyException If the property name is not one of the objects properties.
     * @throws IllegalArgumentException If the wanted value is not in the property's possible values
     */
    public Condition(SysObject object, String propertyName, String wantedValue) {
        if(!object.isPossibleValueOf(propertyName, wantedValue))
            throw new IllegalArgumentException(wantedValue+" is not a possible value of the property "+propertyName);
        
        this.object = object;
        this.propertyName = propertyName;
        this.wantedValue = wantedValue;
    }
    
    /**
     * Checks wether this condition is verified.
     * @return <tt>true</tt> if the object's property currently has the wanted value,
     *         <tt>false</tt> otherwise.
     */
    @Override
    public boolean isVerified() {
        return object.getCurrentValueOf(propertyName).equals(wantedValue);
    }
    
    /**
     * Accessor to the object
     * @return The concerned object
     */
    public SysObject getObject() {
        return object;
    }
    
    /**
     * Accessor to the property
     * @return The concerned property
     */
    public String getPropertyName() {
        return propertyName;
    }
    
    /**
     * Accessor to the wanted value
     * @return The wanted value
     */
    public String getWantedValue() {
        return wantedValue;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + object.hashCode();
        hash = 11 * hash + propertyName.hashCode();
        hash = 11 * hash + wantedValue.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Condition))
            return false;
        
        Condition c = (Condition) obj;
        
        if(!c.object.equals(object))
            return false;
        
        if(!c.propertyName.equals(propertyName))
            return false;
        
        return c.wantedValue.equals(wantedValue);
    }

    @Override
    public String toString() {
        return object+"."+propertyName+" = '"+wantedValue+"'";
    }
}
