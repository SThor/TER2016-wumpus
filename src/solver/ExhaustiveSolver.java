/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import model.Trajectory;
import model.World;
import model.observations.Scenario;

/**
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class ExhaustiveSolver extends Solver {
    /**
     * Caps the maximum number of possibilities before throwing exception
     */
    public static final long POSSIBILITIES_CAP = 1_000_000_000_000L;
    
    private long possibleTrajectoriesCount;
    
    private final String description;
    
    public ExhaustiveSolver(World world, Scenario scenario) {
        super(world, scenario);
        
        try {
            possibleTrajectoriesCount = world.statePossibilitiesCount(); // Possible exception
            long overflowCheck;
            
            // Calculate the number of possible trajectories
            int i = scenario.size();
            while (i > 0) {
                overflowCheck = possibleTrajectoriesCount * possibleTrajectoriesCount;
                if (overflowCheck > POSSIBILITIES_CAP || overflowCheck < possibleTrajectoriesCount) {
                    throw new TooManyPossibilitiesException();
                }
                possibleTrajectoriesCount = overflowCheck;
                i--;
            }
            
        } catch (TooManyPossibilitiesException ex) {
            possibleTrajectoriesCount = -1;
        }
        
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
    
    public long getpossibleTrajectoriesCount() {
        return possibleTrajectoriesCount;
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
