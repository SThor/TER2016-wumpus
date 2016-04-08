/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class SysObject {
    private final String name;
    private final List<State> states; // TODO Maybe a class dedicated to Object states ?
    private State currentState;

    public SysObject(String name) {
        this.name = name;
        states = new ArrayList<>();
        currentState = State.UNDEFINED;
    }
    
    public final void changeState(State newState) {
        if(!states.contains(newState))
            throw new UnknownObjectStateException();
        
        currentState = newState;
    }
    
    public State getCurrentState() {
        return currentState;
    }
    
    public void addState(State state) {
        states.add(state);
    }
    
    public void removeState(State state) {
        states.remove(state);
    }

    @Override
    public String toString() {
        return name;
    }
    
    public boolean isValidState(State state) {
        return states.contains(state);
    }
    
    public List<State> getPossibleStates() {
        return states;
    }
}
