/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver;

import ilog.concert.IloException;
import model.World;
import model.observations.Scenario;

/**
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class Solvers {
    private final Solver[] availableSolvers;
    
    public Solvers(World world, Scenario scenario) {
        availableSolvers = new Solver[2];
        availableSolvers[0] = new ExhaustiveSolver(world, scenario);
        try {
            availableSolvers[1] = new BacktrackSolver(world, scenario);
        } catch (IloException ex) {
            System.err.println("Couldn't instanciate backtrack solver");
            System.err.println(ex);
        }
    }
    
    public Solver[] getAvailableSolvers() {
        return availableSolvers;
    }
}
