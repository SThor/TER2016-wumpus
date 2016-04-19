package model.observations;

/**
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class Not extends Operation{

    public Not(Observation observation) {
        super(observation);
    }
    
    @Override
    public boolean isVerified() {
        Observation observation = observations.get(0);
        return !observation.isVerified();
    }

    @Override
    public String toString() {
        return "¬ "+observations.get(0);
    }
}
