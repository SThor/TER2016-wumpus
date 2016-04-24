/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import model.Trajectory;
import model.World;
import model.exceptions.LongCapacityExceededException;
import model.observations.Scenario;

/**
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class ExhaustiveSolver extends Solver {
    public final long possibleTrajectoriesCount;
    
    private final String description;
    
    public ExhaustiveSolver(World world, Scenario scenario) {
        super(world, scenario);
        
        long worldStatesCount;
        try {
            worldStatesCount = world.statePossibilitiesCount();
        } catch (LongCapacityExceededException ex) {
            worldStatesCount = -1;
        }
        
        // Calculate the number of possible trajectories
        BigInteger trajCount = BigInteger.valueOf(worldStatesCount).multiply(BigInteger.valueOf(scenario.size()));
        
        // If the number of possible trajectories exceeds Long.MAX_VALUE, 
        // set it as -1 to signal the solver not to attempt to solve the system
        possibleTrajectoriesCount = trajCount.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) > 0 ? -1 : trajCount.longValue();
        
        StringBuilder desc = new StringBuilder("Uses the \"proof by exhaustion\" method, or \"brute force method\".\n");
        desc.append("This algorithm will check ALL possibilities for every property value of all objects.\n");
        desc.append("It is suitable for very basic worlds, since it will calculate ALL possible trajectories\n");
        desc.append("Warning: due to it's complexity, this algorithm should not be used on complex worlds. \n");
        desc.append("With the current world and scenario, there are ");
        NumberFormat formatter = DecimalFormat.getNumberInstance();
        if (possibleTrajectoriesCount == -1) {
            desc.append("more than ");
            desc.append(formatter.format(Long.MAX_VALUE));
            desc.append(" possible trajectories.\n");
            desc.append("This exceeds by far the system limitation, any attempt to use this algorithm will therefore fail.\n");
        } else {
            desc.append(formatter.format(possibleTrajectoriesCount));
            desc.append(" possible trajectories.\n");
        }
        
        description = desc.toString();
    }
    
    @Override
    public Trajectory[] solve() {
        if (possibleTrajectoriesCount == -1) {
            return null;
        }
        // TODO
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public String description() {
        return description;
    }
    
    @Override
    public String toString() {
        return "Exhaustion";
    }
}
