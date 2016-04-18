package model.observations;

/**
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class Or extends Operation{

    public Or(Observation ... observations) {
        super(observations);
    }
    
    @Override
    public boolean isVerified() {
        boolean res = false;
        for (Observation observation : observations) {
            res = res || observation.isVerified();
        }
        return res;
    }
    
}