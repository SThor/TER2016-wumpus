package model.exceptions;

/**
 * Thrown when an application attempts to add an already contained 
 * element to a collection that doesn't accept duplicates.
 * @author Paul Givel and Guillaume Hartenstein
 */
public class DuplicateElementException extends RuntimeException {

    /**
     * Create a <tt>DuplicateElementException</tt> with message
     * <tt>"This element is already contained in this list"</tt>.
     */
    public DuplicateElementException() {
        super("This element is already contained in this list");
    }
    
}
