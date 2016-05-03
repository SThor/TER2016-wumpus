/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver;

import ilog.concert.IloAnyDomain;
import ilog.concert.IloCopyManager;
import ilog.concert.IloCopyable;
import ilog.concert.IloException;
import ilog.solver.IlcAnyVar;
import ilog.solver.IlcConstraint;
import ilog.solver.IlcSolver;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.ObjectProperty;
import model.SysObject;
import model.Trajectory;
import model.World;
import model.observations.Scenario;

/**
 *  Uses JSolver CSP algorithm
 * 
 * @author Paul Givel and Guillaume Hartenstein
 */
public class BacktrackSolver extends Solver {
    private final IlcSolver solver;
    private final Map<SysObject, Map<String, IlcAnyVar>> worldMap;

    public BacktrackSolver(World world, Scenario scenario) throws IloException {
        super(world, scenario);
        
        solver = new IlcSolver();
        worldMap = new HashMap<>();
        
        // Create all variables (one for each Object.property)
        for (int i = 0; i < world.getObjectCount(); i++) {
            SysObject object = world.getObjectAt(i);
            Map<String, IlcAnyVar> objectMap = new HashMap<>();
            worldMap.put(object, objectMap);
            for (int j = 0; j < object.getPropertyCount(); j++) {
                ObjectProperty property = object.getPropertyAt(i);
                List<String> listValues = property.getPossibleValues();
                IloAnyDomain propDomain = solver.anyDomain(listValues.toArray(new String[listValues.size()])); // Create a domain for every property
                objectMap.put(property.getName(), solver.anyVar(propDomain, object.getName()+"."+property.getName()));
            }
        }
    }

    @Override
    public List<Trajectory> solve() {
        // TODO
        
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public String description() {
        return "Uses backtrack algorithm (from JSolver API)";
    }
    
    @Override
    public String toString() {
        return "Backtrack";
    }
}
