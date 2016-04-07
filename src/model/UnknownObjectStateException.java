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
class UnknownObjectStateException extends RuntimeException {

    public UnknownObjectStateException() {
        super("The state is not one of the object possible states.");
    }
    
}
