package model.exceptions;

/**
 * Thrown when an application attempts to get a non-existing property from an object
 * @author Paul Givel and Guillaume Hartenstein
 */
public class NoSuchObjectException extends RuntimeException {
    
    private final String object;

    /**
     * Create a <tt>NoSuchObjectException</tt>
     * @param object The name of the non-existing object
     */
    public NoSuchObjectException(String object) {
        super("'"+object + "' is not an object of the world");
        this.object = object;
    }
    
    public String getObjectName() {
        return object;
    }
}
