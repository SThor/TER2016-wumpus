/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver;

import model.World;
import model.observations.Scenario;

/**
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class Solvers {
    private final Solver[] availableSolvers;
    
    public Solvers(World world, Scenario scenario) {
        availableSolvers = new Solver[] {
            new ExhaustiveSolver(world, scenario),
            new BacktrackSolver(world, scenario)
        };
    }
    
    public Solver[] getAvailableSolvers() {
        return availableSolvers;
    }
}
