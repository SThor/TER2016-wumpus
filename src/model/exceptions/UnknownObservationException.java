package model.exceptions;

/**
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class UnknownObservationException extends RuntimeException{
    /**
     * Creates a <tt>UnknownObservationException</tt>
     */
    public UnknownObservationException() {
        super("The observation is of no known type");
    }
}
