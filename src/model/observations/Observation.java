package model.observations;

import model.WorldState;

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
    
    /**
     * Checks wether this observation is verified i a given world state
     * @param state The world state
     * @return <tt>true</tt> if the observation is verified in the world state
     *         <tt>false</tt> otherwise.
     */
    boolean isVerifiedIn(WorldState state);
}
