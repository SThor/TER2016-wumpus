/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class ObjectProperty {
    private List<String> possibleValues;
    private int currentValueIndex;
    private String name;
    
    public ObjectProperty(String name) {
        this(new UniqueList<String>(), name);
    }
    
    public ObjectProperty(UniqueList<String> possibleValues, String name) {
        this.possibleValues = possibleValues;
        currentValueIndex = -1;
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public List<String> getPossibleValues() {
        return possibleValues;
    }
    
    public String getCurrentValue() {
        return currentValueIndex == -1 ? null : possibleValues.get(currentValueIndex);
    }
    
    public void changeToNextValue() {
        currentValueIndex++;
        currentValueIndex %= possibleValues.size();
    }
    
    public void setToUndefined() {
        currentValueIndex = -1;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ObjectProperty){
            ObjectProperty property = (ObjectProperty)obj;
            return name.equals(property.getName());
        }else{
            return false;
        }
    }

    @Override
    public int hashCode() {
        return name.hashCode()*17;
    }

    /**
     * Replaces all of the possible values
     * @param newPossibleValues The possible values for the property
     */
    public void setPossibleValues(UniqueList<String> newPossibleValues) {
        possibleValues = newPossibleValues;
    }
    
    /**
     * Adds a possible value to the list
     * @param possibleValue Value to add
     */
    public void addPossibleValue(String possibleValue){
        possibleValues.add(possibleValue);
        
    }
    
    /**
     * Removes a possible value from the list via its index
     * @param index index of the value to remove
     * @return Value that was removed
     */
    public String removePossibleValue(int index){
        return possibleValues.remove(index);
    }

    @Override
    public String toString() {
        return name;
    }
}
