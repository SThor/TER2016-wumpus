package model.exceptions;

/**
 * Thrown when an application attempts to get a non-existing value from a property
 * @author Paul Givel and Guillaume Hartenstein
 */
public class NoSuchValueException extends RuntimeException {
    
    private final String object;
    private final String property;
    private final Integer value;
    
    /**
     * Create a <tt>NoSuchValueException</tt>
     * @param value The non-existing value
     * @param property The name of the concerned property
     * @param object The name of the concerned object
     */
    public NoSuchValueException(Integer value, String property, String object) {
        super("'"+value + "' is not a possible value of the property '"+property+"' in object '"+object+"'");
        this.object = object;
        this.property = property;
        this.value = value;
    }

    public String getObject() {
        return object;
    }

    public String getProperty() {
        return property;
    }

    public Integer getValue() {
        return value;
    }
}
