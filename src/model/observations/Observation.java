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
public interface Observation {
    /**
     * Checks wether this observation is verified
     * @return <tt>true</tt> if the observation is verified
     *         <tt>false</tt> otherwise.
     */
    boolean isVerified();
    
    /**
     * Checks wether this observation is verified i a given world state
     * @param state The world state
     * @return <tt>true</tt> if the observation is verified in the world state
     *         <tt>false</tt> otherwise.
     */
    boolean isVerifiedIn(WorldState state);
    
    /**
     * Return the constraint represented by this observation
     * @param solver The solver to constraint
     * @param worldMap The map representing the world
     * @return The IlcConstraint
     */
    IlcConstraint solverConstraint(IlcSolver solver, Map<SysObject, Map<String, IlcAnyVar>> worldMap);
}
