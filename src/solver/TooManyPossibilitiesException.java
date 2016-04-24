/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver;

import model.World;

/**
 * Thrown when a number of possibilities exceeds the system cap
 * 
 * @author Paul Givel and Guillaume Hartenstein
 */
public class TooManyPossibilitiesException extends Exception {

    public TooManyPossibilitiesException() {
        super("The number of possibilities exceeded "+ExhaustiveSolver.POSSIBILITIES_CAP);
    }
    
}
