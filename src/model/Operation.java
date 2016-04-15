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
public abstract class Operation implements Observation {
    protected Operation op1;
    protected Operation op2;
    
    public Operation(Operation op1, Operation op2) {
        this.op1 = op1;
        this.op2 = op2;
    }
}
