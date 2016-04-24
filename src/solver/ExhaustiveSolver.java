/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver;

import java.util.logging.Level;
import java.util.logging.Logger;
import model.Trajectory;
import model.World;
import model.exceptions.LongCapacityExceededException;
import model.observations.Scenario;

/**
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class ExhaustiveSolver extends Solver {
    private long worldPossibleStates;
    
    public ExhaustiveSolver(World world, Scenario scenario) {
        super(world, scenario);
        
        try {
            worldPossibleStates = world.statePossibilitiesCount();
        } catch (LongCapacityExceededException ex) {
            worldPossibleStates = -1;
        }
    }
    
    @Override
    public Trajectory[] solve() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String description() {
        return "Uses the \"proof by exhaustion\" method, also called \"brute force method\".\n"
                + "This algorithm will check ALL possibilities for every property value of all objects."
                + "It is suitable for very basic worlds, since it will calculate ALL possible trajectories\n"
                + "Warning: due to it's complexity, this algorithm should not be used on complex worlds. \n"
                + "If your world is considered too complex, the program will allow you to limit the maximum running time of the calculation.";
    }
    
    @Override
    public String toString() {
        return "Exhaustion";
    }
}
