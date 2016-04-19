package model.exceptions;

/**
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class UnknownOperationException extends RuntimeException{
    /**
     * Creates a <tt>UnknownObservationException</tt>
     */
    public UnknownOperationException() {
        super("The operation is of no known type");
    }
}
