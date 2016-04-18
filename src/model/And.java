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
public class And extends Operation{

    public And(Operation op1, Operation op2) {
        super(op1, op2);
    }
    
    @Override
    public boolean isVerified() {
        return op1.isVerified() && op2.isVerified();
    }

    @Override
    public String toString() {
        return op1.toString()+" ∧ "+op2.toString();
    }
}
