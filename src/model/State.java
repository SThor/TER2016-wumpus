package model;

/**
 * Represents an object state.
 * @author Paul Givel and Guillaume Hartenstein
 */
public class State {
    /**
     * Undifined object state.
     */
    public static final State UNDEFINED = new State("-");
    
    /**
     * Name of the state.
     */
    private final String name;
    
    /**
     * Constructs a state.
     * @param name The name of the state
     * @throws NullPointerException If the name is <tt>null</tt>
     */
    public State(String name) {
        if(name == null)
            throw new IllegalArgumentException(name);
        
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + name.hashCode();
        return hash;
    }

    /**
     * Two states are equels if they have the same name.
     */
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof State)) 
            return false;
        
        return ((State)obj).name.equals(name);
    }
    
    @Override
    public String toString() {
        return name;
    }
}
