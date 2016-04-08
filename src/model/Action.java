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
public class Action {
    private final String name;
    private final List<Condition> preConditions;
    private final List<Condition> postConditions;
    
    public Action(String name) {
        this.name = name;
        preConditions = new ArrayList<>();
        postConditions = new ArrayList<>();
    }
    
    public boolean preConditionsVerified() {
        for(Condition c : preConditions)
            if(!c.isVerified())
                return false;
        
        return true;
    }
    
    public boolean postConditionsVerified() {
        for(Condition c : postConditions)
            if(!c.isVerified())
                return false;
        
        return true;
    }
    
    public void addPreCondition(Condition preCondition) {
        preConditions.add(preCondition);
    }
    
    public void addPostCondition(Condition postCondition) {
        preConditions.add(postCondition);
    }
    
    public void removePreCondition(Condition preCondition) {
        preConditions.remove(preCondition);
    }
    
    public void removePostCondition(Condition postCondition) {
        preConditions.remove(postCondition);
    }
    @Override
    public String toString() {
        return name;
    }
}
