package model;

import model.exceptions.UnknownObjectStateException;
import java.util.List;
import model.exceptions.DuplicateElementException;

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
     * List of the possible possible states.
     */
    private final UniqueList<State> possibleStates;
    
    /**
     * Current state of the object.
     */
    private State currentState;
    
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
        possibleStates = new UniqueList<>();
        currentState = State.UNDEFINED;
    }
    
    /**
     * Change the state of this object.
     * @param newState The new state of the object
     * @throws UnknownObjectStateException
     *  If the new state is not one of this object's possible states.
     */
    public final void changeState(State newState) {
        if(!isValidState(newState))
            throw new UnknownObjectStateException();
        
        currentState = newState;
    }
    
    /**
     * Access the curretn state of this object.
     * @return The current state of this object.
     */
    public State getCurrentState() {
        return currentState;
    }
    
    /**
     * Add a possible state to this object.
     * @param state The new possible state
     * @throws IllegalArgumentException If the state is <tt>State.UNDEFINED</tt>
     * @throws DuplicateElementException
     *  If the new state is already a possible state.
     */
    public void addPossibleState(State state) {
        if(state == State.UNDEFINED)
            throw new IllegalArgumentException(state.toString());
        
        possibleStates.add(state);
    }
    
    /**
     * Remove a possible state.
     * @param index The index of the state to remove 
     */
    public void removePossibleState(int index) {
        world.updateWorld(this, possibleStates.get(index));
        possibleStates.remove(index);
    }

    @Override
    public String toString() {
        return name;
    }
    
    /**
     * Check wether a state is a possible state of this object.
     * @param state The state to check
     * @return <tt>true</tt> if the state is a possible state of this object,
     *         <tt>false</tt> otherwise.
     */
    public boolean isValidState(State state) {
        return possibleStates.contains(state);
    }
    
    /**
     * Access the possible states.
     * @return An unmodifiable list of this object's possible states.
     */
    public List<State> getPossibleStates() {
        return possibleStates.asList();
    }
}
