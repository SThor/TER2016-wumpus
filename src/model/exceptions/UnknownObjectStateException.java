package model.exceptions;

/**
 * Thrown when an application attempts to associate an unknown
 * state to a SysObject.
 * @author Paul Givel and Guillaume Hartenstein
 */
public class UnknownObjectStateException extends RuntimeException {

    /**
     * Create a <tt>UnknownObjectStateException</tt> with message
     * <tt>"The state is not one of the object possible states."</tt>.
     */
    public UnknownObjectStateException() {
        super("The state is not one of the object possible states.");
    }
    
}
