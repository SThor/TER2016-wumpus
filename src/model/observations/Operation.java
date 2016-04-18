/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.observations;

/**
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public abstract class Operation implements Observation {
    protected Observation op1;
    protected Observation op2;
    
    public Operation(Observation op1, Observation op2) {
        this.op1 = op1;
        this.op2 = op2;
    }
}
