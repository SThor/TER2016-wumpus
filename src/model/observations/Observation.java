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
public interface Observation {
    /**
     * Checks wether this observation is verified
     * @return <tt>true</tt> if the observation is verified
     *         <tt>false</tt> otherwise.
     */
    boolean isVerified();
}
