/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.exceptions;

/**
 * Thrown when a value exceeds <tt>Long.MAX_VALUE</tt>
 * 
 * @author Paul Givel and Guillaume Hartenstein
 */
public class LongCapacityExceededException extends Exception {

    public LongCapacityExceededException() {
        super("The value exceeded Long.MAX_VALUE ("+Long.MAX_VALUE+")");
    }
    
}
