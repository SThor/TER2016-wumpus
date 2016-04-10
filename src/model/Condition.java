package model;

import model.exceptions.UnknownObjectStateException;

/**
 * Represents a condition as a <tt>SysObject</tt> and a <tt>State</tt>.
 * The condition is verified if the <tt>SysObject</tt>'s current state is the <tt>State</tt>.
 * @author Paul Givel and Guillaume Hartenstein
 */
public class Condition {
    /**
     * The concerned object.
     */
    private SysObject object;
    
    /**
     * The wanted state.
     */
    private State state;
    
    /**
     * Constructs a condition.
     * @param object The concerned object.
     * @param state The wanted state.
     * @throws UnknownObjectStateException 
     *  If <tt>state</tt> is not a possible state of <tt>object</tt>.
     */
    public Condition(SysObject object, State state) {
        if(!object.isValidState(state))
            throw new UnknownObjectStateException();
        
        this.object = object;
        this.state = state;
    }
    
    /**
     * Check wether this condition is verified.
     * @return <tt>true</tt> if the object is currently in the wanted state,
     *         <tt>false</tt> otherwise
     */
    public boolean isVerified() {
        return object.getCurrentState().equals(state);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + object.hashCode();
        hash = 43 * hash + state.hashCode();
        return hash;
    }

    /**
     * Two <tt>Conditions</tt> are equals if they have the same
     * object and the same wanted state.
     */
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Condition))
            return false;
        
        Condition c = (Condition) obj;
        
        return c.state.equals(state) && c.object.equals(object);
    }

    /**
     * Access the object
     * @return The object concerned by this condition
     */
    public SysObject getObject() {
        return object;
    }

    /**
     * Access the state
     * @return The state concerned by this condition
     */
    public State getState() {
        return state;
    }
}
