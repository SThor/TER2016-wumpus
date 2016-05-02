/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.solver;

/**
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class NoSolverException extends Exception {

    public NoSolverException() {
        super("No solver has been found on the system");
    }
    
}
