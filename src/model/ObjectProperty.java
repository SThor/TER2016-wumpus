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
    
    public ObjectProperty() {
        this(new UniqueList<String>());
    }
    
    public ObjectProperty(UniqueList<String> possibleValues) {
        this.possibleValues = new UniqueList<>();
        currentValueIndex = -1;
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
