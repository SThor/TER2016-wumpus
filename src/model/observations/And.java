package model.observations;

/**
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class And extends Operation{

    public And(Observation ... observations) {
        super(observations);
    }
    
    @Override
    public boolean isVerified() {
        boolean res = true;
        for (Observation observation : observations) {
            res = res && observation.isVerified();
        }
        return res;
    }
}
