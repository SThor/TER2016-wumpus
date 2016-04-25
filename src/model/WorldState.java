/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Arrays;

/**
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class WorldState {
    private final Condition[] state;
    
    public WorldState(Condition[] state) {
        this.state = state;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Arrays.deepHashCode(this.state);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof WorldState)) {
            return false;
        }
        
        return Arrays.deepEquals(state, ((WorldState)obj).state);
    }
    
    public Condition[] asArray() {
        return state;
    }
}
