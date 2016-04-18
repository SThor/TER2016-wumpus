package model.observations;

/**
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class AndMulti extends Operation{

    public AndMulti(Observation ... observations) {
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
