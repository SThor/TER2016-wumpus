package model.observations;

import ilog.solver.IlcAnyVar;
import ilog.solver.IlcConstraint;
import ilog.solver.IlcSolver;
import java.util.Map;
import model.SysObject;
import model.WorldState;

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
    public boolean isVerifiedIn(WorldState state) {
        Observation observation = observations.get(0);
        return !observation.isVerifiedIn(state);
    }

    @Override
    public String toString() {
        return "¬("+observations.get(0)+")";
    }

    @Override
    public IlcConstraint solverConstraint(IlcSolver solver, Map<SysObject, Map<String, IlcAnyVar>> worldMap) {
        return solver.not(observations.get(0).solverConstraint(solver, worldMap));
    }
}
