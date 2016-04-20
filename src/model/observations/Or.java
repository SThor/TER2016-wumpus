package model.observations;

import java.util.Iterator;

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

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Iterator<Observation> iterator = observations.iterator(); iterator.hasNext();) {
            str.append(iterator.next());
            if(iterator.hasNext()) {
                str.append("∨ ");
            }
        }
        return str.toString();
    }
}
