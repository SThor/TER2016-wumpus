/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Objects;

/**
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class State {
    public static final State UNDEFINED = new State("Undefined");
    
    private final String name;
    
    public State(String name) {
        if(name == null)
            throw new IllegalArgumentException("Null");
        
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof State)) 
            return false;
        
        return ((State)obj).name.equals(name);
    }
    
    @Override
    public String toString() {
        return name;
    }
}
