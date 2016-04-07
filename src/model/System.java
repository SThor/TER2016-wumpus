/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class System {
    private final Set<SysObject> systemObjects;
    private final Set<Action> possibleActions;
    
    public System() {
        systemObjects = new HashSet<>();
        possibleActions = new HashSet<>();
    }
    
    public void addObject(SysObject object) {
        systemObjects.add(object);
    }
    
    public void addPossibleAction(Action action) {
        possibleActions.add(action);
    }
    
    public void removeObject(SysObject object) {
        systemObjects.remove(object);
    }
    
    public void removePossibleAction(Action action) {
        possibleActions.remove(action);
    }
    
    public Set<SysObject> getObjects() {
        return systemObjects;
    }
}
