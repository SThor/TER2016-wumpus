package model.observations;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public abstract class Operation implements Observation {
    protected List<Observation> observations;
    
    public Operation(Observation ... observations) {
        this.observations=Arrays.asList(observations);
    }

    public Observation get(int index){
        return observations.get(index);
    }

    public List<Observation> getObservations() {
        return observations;
    }
}
