/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class Condition {
    private SysObject object;
    private State state;
    
    public Condition(SysObject object, State state) {
        if(!object.isValidState(state))
            throw new UnknownObjectStateException();
        
        this.object = object;
        this.state = state;
    }
    
    public boolean isVerified() {
        return object.getCurrentState().equals(state);
    }
    
    public String getObjectName() {
        return object.toString();
    }
    
    public String getStateName() {
        return state.toString();
    }
}
