/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class Observation {
    private final Map<SysObject, State> system;
        
    public Observation(System s) {
        system = new HashMap<>();
        
        for(SysObject obj : s.getObjects())
            system.put(obj, State.UNDEFINED);
    }
    
    public void setObservedState(SysObject obj, State state) {
        if(!obj.isValidState(state))
            throw new UnknownObjectStateException();
        
        system.replace(obj, state);
    }
    
    public State getObservedState(SysObject obj) {
        return system.get(obj);
    }
}
