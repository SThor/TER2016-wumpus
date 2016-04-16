package model.exceptions;

/**
 * Thrown when an application attempts to get a non-existing property from an object
 * @author Paul Givel and Guillaume Hartenstein
 */
public class NoSuchObjectException extends RuntimeException {

    /**
     * Create a <tt>NoSuchObjectException</tt>
     * @param objectName Name of the non-existing object
     */
    public NoSuchObjectException(String objectName) {
        super(objectName + " is not an object of the world");
    }
    
}
