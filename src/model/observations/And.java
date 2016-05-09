package model.observations;

import ilog.solver.IlcAnyVar;
import ilog.solver.IlcConstraint;
import ilog.solver.IlcSolver;
import java.util.Iterator;
import java.util.Map;
import model.SysObject;
import model.WorldState;

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
    
    @Override
    public boolean isVerifiedIn(WorldState state) {
        boolean res = true;
        for (Observation observation : observations) {
            res = res && observation.isVerifiedIn(state);
        }
        return res;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Iterator<Observation> iterator = observations.iterator(); iterator.hasNext();) {
            str.append(iterator.next());
            if(iterator.hasNext()) {
                str.append(" ∧ ");
            }
        }
        return str.toString();
    }
    
    @Override
    public IlcConstraint solverConstraint(IlcSolver solver, Map<SysObject, Map<String, IlcAnyVar>> worldMap) {
        if (observations.isEmpty()) {
            return null;
        }
        
        IlcConstraint constraint = observations.get(0).solverConstraint(solver, worldMap);
        for (int i = 1; i < observations.size(); i++) {
            constraint = solver.and(constraint, observations.get(i).solverConstraint(solver, worldMap));
        }
        return constraint;
    }
}
