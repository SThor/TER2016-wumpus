/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import model.ObjectState;
import model.ObjectProperty;
import model.SysObject;
import model.Trajectory;
import model.World;
import model.WorldState;
import model.observations.Observation;
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
    private long worldStatesCount;
    private List<Trajectory> trajectories;
    
    private final String description;
    
    public ExhaustiveSolver(World world, Scenario scenario) {
        super(world, scenario);
        
        try {
            worldStatesCount = world.statePossibilitiesCount(); // Possible exception
            possibleTrajectoriesCount = 1;
            long overflowCheck;
            
            // Calculate the number of possible trajectories
            int i = scenario.size();
            while (i > 0) {
                overflowCheck = possibleTrajectoriesCount * worldStatesCount;
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
            desc.append(formatter.format(POSSIBILITIES_CAP));
            desc.append(" possible trajectories.\n");
            desc.append("This exceeds the system limitation, any attempt to use this algorithm will therefore fail.\n");
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
        
        trajectories = new ArrayList<>();
        
        world.resetObjects();
        
        // Get a list of all the world's possible states
        List<WorldState> allStates = new ArrayList<>();
        
        SysObject curObject, subCurObject;
        ObjectProperty curProperty, subCurProperty;
        int i, j, k, l, m, n;
        for (i = 0; i < world.getObjectCount(); i++) {
            curObject = world.getObjectAt(i);
            for (j = 0; j < curObject.getPropertyCount(); j++) {
                curProperty = curObject.getPropertyAt(j);
                k = curProperty.getPossibleValues().size();
                while (k >= 0) {
                    // At this point, we need to iterate again over the world, excluding the current property
                    // So we can change all other properties values
                    for (l = 0; l < world.getObjectCount(); l++) {
                        subCurObject = world.getObjectAt(l);
                        for (m = 0; m < subCurObject.getPropertyCount(); m++) {
                            if (l == i && m == j) continue; // Excludes the current property (on previous level)
                            subCurProperty = subCurObject.getPropertyAt(m);
                            n = subCurProperty.getPossibleValues().size();
                            while (n >= 0) {
                                subCurProperty.changeToNextValue();
                                // TODO add trajectory here ?
                                WorldState snapshot = world.snapShot();
                                if(!allStates.contains(snapshot)) {
                                    allStates.add(snapshot);
                                }
                                n--;
                            }
                        }
                    }
                    
                    curProperty.changeToNextValue();
                    k--;
                }
            }
        }
        
        // TODO remove (printing tests)
        System.out.println("Number of states: "+allStates.size());
        for (WorldState state : allStates) {
            System.out.println("----------------------");
            for (ObjectState condition : state) {
                System.out.println(condition);
            }
        }
        //------
        
        // At this point we have the list of all the world possible states
        // Or add trajectory here ?
        
        // TODO continue
        return null;
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

    private void addTrajectory() {
        for (Observation observation : scenario) {
            
        }
    }
}
