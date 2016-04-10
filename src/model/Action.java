package model;

import java.util.Iterator;
import java.util.List;
import model.exceptions.DuplicateElementException;

/**
 * Represents a possible action in a world.
 * @author Paul Givel and Guillaume Hartenstein
 */
public class Action {
    /**
     * Name of the action.
     */
    private final String name;
    
    /**
     * List of pre-conditions for this action to be realised.
     */
    private final UniqueList<Condition> preConditions;
    
    /**
     * List of post-conditions after this action has been realised.
     */
    private final UniqueList<Condition> postConditions;
    
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
        for(Condition c : preConditions.asList())
            if(!c.isVerified())
                return false;
        
        return true;
    }
    
    /**
     * Check wether the post-conditions are verified.
     * @return <tt>true</tt> if all the post-conditions are verified, <tt>false</tt> otherwise.
     */
    public boolean postConditionsVerified() {
        for(Condition c : postConditions.asList())
            if(!c.isVerified())
                return false;
        
        return true;
    }
    
    /**
     * Add a pre-condition for this action.
     * @param preCondition The pre-condition to add
     * @throws DuplicateElementException If the pre-condition already exists.
     */
    public void addPreCondition(Condition preCondition) {
        preConditions.add(preCondition);
    }
    
    /**
     * Add a post-condition for this action.
     * @param postCondition  The post-condition to add
     * @throws DuplicateElementException If the post-condition already exists
     */
    public void addPostCondition(Condition postCondition) {
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
     * Access the pre-conditions.
     * @return The list of pre-conditions
     */
    public UniqueList<Condition> getPreConditions() {
        return preConditions;
    }
    
    /**
     * Access the post-conditions.
     * @return The list of post-conditions
     */
    public UniqueList<Condition> getPostConditions() {
        return postConditions;
    }
    
    /**
     * Remove all pre-conditions associated to an object in a certain state.
     * If state is <tt>null</tt> the pre-condition will be deleted regardless of the state.
     * @param object The associated object
     * @param state The associated state
     */
    protected void removePreConditions(SysObject object, State state) {
        for(Iterator<Condition> it = preConditions.iterator(); it.hasNext();) {
            Condition c = it.next();
            if(c.getObject().equals(object) && (state == null || c.getState().equals(state)))
                it.remove();
        }
            
    }

    /**
     * Remove all post-conditions associated to an object in a certain state.
     * If state is <tt>null</tt> the post-condition will be deleted regardless of the state.
     * @param object The associated object
     * @param state The associated state
     */
    protected void removePostConditions(SysObject object, State state) {
        for(Iterator<Condition> it = preConditions.iterator(); it.hasNext();) {
            Condition c = it.next();
            if(c.getObject().equals(object) && (state == null || c.getState().equals(state)))
                it.remove();
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
}
