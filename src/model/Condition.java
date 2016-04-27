package model;

import model.observations.Observation;

/**
 * Represents a condition, which is an object and a certain property of this object.
 * Depending on the implementation, the wanted value can be a constant, or equal to the property of another object.
 * @author Paul Givel and Guillaume Hartenstein
 */
interface Condition extends Observation{
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
}
