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
public class And extends Operation{

    public And(Observation op1, Observation op2) {
        super(op1, op2);
    }
    
    @Override
    public boolean isVerified() {
        return op1.isVerified() && op2.isVerified();
    }
    
}
