package model.exceptions;

/**
 * Thrown when an application attempts to get a non-existing property from an object
 * @author Paul Givel and Guillaume Hartenstein
 */
public class NoSuchPropertyException extends RuntimeException {

    /**
     * Create a <tt>NoSuchPropertyException</tt>
     * @param propertyName Name of the non-existing property
     * @param objectName Name of the object where the property should be found
     */
    public NoSuchPropertyException(String propertyName, String objectName) {
        super(propertyName + " is not a property of the object "+ objectName);
    }
    
}
