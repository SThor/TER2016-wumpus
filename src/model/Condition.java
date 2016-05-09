package model;

import model.observations.Observation;

/**
 * Represents a condition, which is an object and a certain property of this object.
 * Depending on the implementation, the wanted value can be a constant, or equal to the property of another object.
 * @author Paul Givel and Guillaume Hartenstein
 */
public interface Condition extends Observation{
    /**
     * Accessor to the object
     * @return The concerned object
     */
    public SysObject getObject();
    
    /**
     * Accessor to the property
     * @return The concerned property
     */
    public String getPropertyName();
    
    /**
     * A variant of Equals that, for each attribute of the condition, considers 'null' to always be equal
     * @return <tt>true</tt> if the conditions are of the same class, and every attribute is either equal to the corresponding argument or null,
     * <tt>false</tt> otherwise.
     */
    public boolean largeEquals(SysObject object, String property, Integer value);
}
