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
        this.possibleValues = new UniqueList<>();
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
}
