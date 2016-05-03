/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver;

import java.util.List;
import model.Trajectory;
import model.World;
import model.observations.Scenario;

/**
 *  Uses JSolver CSP algorithm
 * 
 * @author Paul Givel and Guillaume Hartenstein
 */
public class BacktrackSolver extends Solver {

    public BacktrackSolver(World world, Scenario scenario) {
        super(world, scenario);
    }

    @Override
    public List<Trajectory> solve() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String description() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
