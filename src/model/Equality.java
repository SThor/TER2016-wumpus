package model;

import model.exceptions.NoSuchPropertyException;

/**
 * Represents a condition as an object and a certain property of this object, as
 * well as as second object and the corresponding property. The condition is
 * verified if the <tt>SysObject</tt>'s current property value is the same for
 * both <tt>SysObject</tt>.
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class Equality implements Condition {

    private SysObject object;
    private String propertyName;
    private SysObject secondObject;
    private String secondPropertyName;

    /**
     * Constructs a condition.
     * <b>Warning:</b> it is possible to create a condition on a value not
     * contained in the property's possible values. Such a condition will never
     * be verified.
     *
     * @param object The object concerned
     * @param propertyName The name of the property concerned
     * @param secondObject The object containing the propery we're trying to
     * match
     * @param secondPropertyName The name of the property we're trying to match
     * @throws NoSuchPropertyException If the property name for either of the
     * objects is not in its properties.
     */
    public Equality(SysObject object, String propertyName, SysObject secondObject, String secondPropertyName) {
        boolean found = false;
        for (String property : object.getPropertiesNames()) {
            if (property.equals(propertyName)) {
                found = true;
                break;
            }
        }
        if (!found) {
            throw new NoSuchPropertyException(propertyName, object.toString());
        }
        found = false;
        for (String property : secondObject.getPropertiesNames()) {
            if (property.equals(secondPropertyName)) {
                found = true;
                break;
            }
        }
        if (!found) {
            throw new NoSuchPropertyException(secondPropertyName, secondObject.toString());
        }
        this.object = object;
        this.propertyName = propertyName;
        this.secondObject = secondObject;
        this.secondPropertyName = secondPropertyName;
    }

    @Override
    public SysObject getObject() {
        return this.object;
    }

    @Override
    public String getPropertyName() {
        return this.propertyName;
    }

    public SysObject getSecondObject() {
        return this.secondObject;
    }

    public String getSecondPropertyName() {
        return this.secondPropertyName;
    }

    @Override
    public boolean isVerified() {
        String wantedValue = secondObject.getCurrentValueOf(secondPropertyName);
        return object.getCurrentValueOf(propertyName).equals(wantedValue);
    }

    @Override
    public boolean isVerifiedIn(WorldState state) {
        String firstValue = null;
        String secondValue = null;
        for (PropertyValue propertyValue : state) {
            if (propertyValue.getObject().equals(object) && propertyValue.getPropertyName().equals(propertyName)) {
                firstValue = propertyValue.getWantedValue();
            }
            if (propertyValue.getObject().equals(secondObject) && propertyValue.getPropertyName().equals(secondPropertyName)) {
                secondValue = propertyValue.getWantedValue();
            }
        }
        
        if (firstValue != null && secondValue != null) {
            return firstValue.equals(secondValue);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + object.hashCode();
        hash = 11 * hash + propertyName.hashCode();
        hash = 11 * hash + secondObject.hashCode();
        hash = 11 * hash + secondPropertyName.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Equality)) {
            return false;
        }

        Equality e = (Equality) obj;

        return object.equals(e.object) && propertyName.equals(e.propertyName) && secondObject.equals(e.secondObject) && secondPropertyName.equals(e.secondPropertyName);
    }

    @Override
    public String toString() {
        return object + "." + propertyName + " = " + secondObject + "." + secondPropertyName;
    }

    @Override
    public boolean largeEquals(SysObject object, String property, String value) {
        return (this.object.equals(object) || this.secondObject.equals(object)) && (property == null || (this.propertyName.equals(property) || this.secondPropertyName.equals(property)));
    }
}
