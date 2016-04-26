package model;

import model.observations.Observation;
import java.util.Iterator;
import java.util.List;
import model.exceptions.DuplicateElementException;

/**
 * Represents a possible action in a world.
 * @author Paul Givel and Guillaume Hartenstein
 */
public class Action implements Observation {
    /**
     * Name of the action.
     */
    private final String name;
    
    /**
     * List of pre-conditions for this action to be realised.
     */
    private final List<ObjectState> preConditions;
    
    /**
     * List of post-conditions after this action has been realised.
     */
    private final List<ObjectState> postConditions;
    
    /**
     * Constructs an action with no pre- and post-consitions 
     * @param name The name of this action.
     * @throws NullPointerException If <tt>name</tt> is null.
     */
    public Action(String name) {
        if(name == null)
            throw new NullPointerException();
        
        this.name = name;
        preConditions = new UniqueList<>();
        postConditions = new UniqueList<>();
    }
    
    /**
     * Check wether the pre-conditions are verified.
     * @return <tt>true</tt> if all the pre-conditions are verified, <tt>false</tt> otherwise.
     */
    public boolean preConditionsVerified() {
        for(ObjectState c : preConditions)
            if(!c.isVerified())
                return false;
        
        return true;
    }
    
    /**
     * Check wether the post-conditions are verified.
     * @return <tt>true</tt> if all the post-conditions are verified, <tt>false</tt> otherwise.
     */
    public boolean postConditionsVerified() {
        for(ObjectState c : postConditions)
            if(!c.isVerified())
                return false;
        
        return true;
    }
    
    /**
     * Add a pre-condition for this action.
     * @param preCondition The pre-condition to add
     * @throws DuplicateElementException If the pre-condition already exists.
     */
    public void addPreCondition(ObjectState preCondition) {
        preConditions.add(preCondition);
    }
    
    /**
     * Add a post-condition for this action.
     * @param postCondition  The post-condition to add
     * @throws DuplicateElementException If the post-condition already exists
     */
    public void addPostCondition(ObjectState postCondition) {
        postConditions.add(postCondition);
    }
    
    /**
     * Remove a pre-condition.
     * @param index The index of the pre-condition to remove
     */
    public void removePreCondition(int index) {
        preConditions.remove(index);
    }
    
    /**
     * Remove a post-condition.
     * @param index The index of the post-condition to remove
     */
    public void removePostCondition(int index) {
        postConditions.remove(index);
    }
    
    /**
     * Accessor to the pre-conditions.
     * @return The list of pre-conditions
     */
    public List<ObjectState> getPreConditions() {
        return preConditions;
    }
    
    /**
     * Accessor to the post-conditions.
     * @return The list of post-conditions
     */
    public List<ObjectState> getPostConditions() {
        return postConditions;
    }
    
    /**
     * @see Action#removeFromList(java.util.List, model.SysObject, java.lang.String, java.lang.String) 
     */
    protected void removeAllConditions(SysObject object, String property, String value) {
        removeFromList(preConditions, object, property, value);
        removeFromList(postConditions, object, property, value);
    }
    
    /**
     * Remove all condition associated to an object that has a certain property
     * which is currently at a certain value.
     * Note: if <tt>value</tt> is <tt>null</tt>, the value of the property is discarded.
     *       if <tt>property</tt> is <tt>null</tt> the object is deleted wether it has this propety or not.
     * @param object The associated object
     * @param property The associated property
     * @param values The list of possible values associated
     */
    private void removeFromList(List<ObjectState> conditions, SysObject object, String property, String value) {
        for(Iterator<ObjectState> it = conditions.iterator(); it.hasNext();) {
            ObjectState c = it.next();
            if (c.getObject().equals(object)
            &&(property == null || c.getPropertyName().equals(property))
            &&(value == null || value.equals(c.getWantedValue()))) {
                it.remove();
            }
        }
    }
    
    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + name.hashCode();
        return hash;
    }

    /**
     * Two <tt>Actions</tt> are equals if they have the same name, 
     * regardless of their pre-conditions.
     */
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Action))
            return false;
        
        return ((Action)obj).name.equals(name);
    }

    @Override
    public boolean isVerified() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isVerifiedIn(WorldState state) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String getName() {
        return name;
    }
}
