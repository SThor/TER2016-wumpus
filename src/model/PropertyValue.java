package model;

import ilog.solver.IlcAnyVar;
import ilog.solver.IlcConstraint;
import ilog.solver.IlcSolver;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.exceptions.NoSuchPropertyException;
import model.exceptions.NoSuchValueException;

/**
 * Represents a condition as an object and a wanted value of a certain property
 * of this object. The condition is verified if the <tt>SysObject</tt>'s current
 * property is the <tt>State</tt>.
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class PropertyValue implements Condition {

    /**
     * The concerned object.
     */
    private SysObject object;

    /**
     * The name of the concerned property
     */
    private String propertyName;

    /**
     * The wanted value for this property
     */
    private String wantedValue;

    /**
     * Constructs a property value condition.
     * <b>Warning:</b> it is possible to create a condition on a value not
     * contained in the property's possible values. Such a condition will never
     * be verified.
     *
     * @param object The object concerned
     * @param propertyName The name of the property concerned
     * @param wantedValue The wanted value for this property
     * @throws NoSuchPropertyException If the property name is not one of the
     * objects properties.
     * @throws NoSuchValueException If the wanted value is not in the property's
     * possible values
     */
    public PropertyValue(SysObject object, String propertyName, String wantedValue) {
        if (!object.isPossibleValueOf(propertyName, wantedValue)) {
            throw new NoSuchValueException(wantedValue, propertyName, object.getName());
        }

        this.object = object;
        this.propertyName = propertyName;
        this.wantedValue = wantedValue;
    }

    /**
     * Checks wether this condition is verified.
     *
     * @return <tt>true</tt> if the object's property currently has the wanted
     * value,
     * <tt>false</tt> otherwise.
     */
    @Override
    public boolean isVerified() {
        return object.getCurrentValueOf(propertyName).equals(wantedValue);
    }

    @Override
    public boolean isVerifiedIn(WorldState state) {
        return state.contains(this);
    }

    /**
     * Accessor to the object
     *
     * @return The concerned object
     */
    @Override
    public SysObject getObject() {
        return object;
    }

    /**
     * Accessor to the property
     *
     * @return The concerned property
     */
    @Override
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * Accessor to the wanted value
     *
     * @return The wanted value
     */
    public String getWantedValue() {
        return wantedValue;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + object.hashCode();
        hash = 11 * hash + propertyName.hashCode();
        hash = 11 * hash + wantedValue.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PropertyValue)) {
            return false;
        }

        PropertyValue pv = (PropertyValue) obj;

        if (!pv.object.equals(object)) {
            return false;
        }

        if (!pv.propertyName.equals(propertyName)) {
            return false;
        }

        return pv.wantedValue.equals(wantedValue);
    }

    @Override
    public String toString() {
        return object + "." + propertyName + " = '" + wantedValue + "'";
    }

    @Override
    public boolean largeEquals(SysObject object, String property, String value) {
        return this.object.equals(object) && (
                    property == null || (
                        this.propertyName.equals(property) && (
                            value == null || this.wantedValue.equals(value)
                        )
                    )
                );

    }

    @Override
    public IlcConstraint solverConstraint(IlcSolver solver, Map<SysObject, Map<String, IlcAnyVar>> worldMap) {
        IlcAnyVar concernedVar = worldMap.get(object).get(propertyName);
        return solver.eq(concernedVar, wantedValue);
    }

    @Override
    public List<String> getPropertiesNames() {
        List<String> propName = new ArrayList<>();
        propName.add(propertyName);
        return propName;
    }
}
