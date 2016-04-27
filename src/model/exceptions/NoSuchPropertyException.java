package model.exceptions;

/**
 * Thrown when an application attempts to get a non-existing property from an object
 * @author Paul Givel and Guillaume Hartenstein
 */
public class NoSuchPropertyException extends RuntimeException {
    
    private final String object;
    private final String property;

    /**
     * Create a <tt>NoSuchPropertyException</tt>
     * @param property The name of the non-existing property
     * @param object The name of the object where the property should be found
     */
    public NoSuchPropertyException(String property, String object) {
        super("'"+property + "' is not a property of the object "+ object);
        this.object = object;
        this.property = property;
    }

    public String getObject() {
        return object;
    }

    public String getProperty() {
        return property;
    }
}
