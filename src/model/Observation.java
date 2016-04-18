package model;

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
