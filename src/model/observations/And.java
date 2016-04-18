package model.observations;

/**
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class And extends Operation{

    public And(Observation op1, Observation op2) {
        super(op1, op2);
    }
    
    @Override
    public boolean isVerified() {
        Observation op1 = observations.get(0);
        Observation op2 = observations.get(1);
        return op1.isVerified() && op2.isVerified();
    }
    
}
