/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver;

import ilog.concert.IloException;
import java.util.List;
import model.Trajectory;
import model.World;
import model.observations.Scenario;

/**
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public abstract class Solver {
    protected final World world;
    protected final Scenario scenario;

    public Solver(World world, Scenario scenario) {
        this.world = world;
        this.scenario = scenario;
    }
    
    public abstract List<Trajectory> solve() throws IloException;
    
    public abstract String description();
}
