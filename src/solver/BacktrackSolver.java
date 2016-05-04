/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver;

import ilog.concert.IloAnyDomain;
import ilog.concert.IloException;
import ilog.solver.IlcAnyVar;
import ilog.solver.IlcSolver;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.ObjectProperty;
import model.SysObject;
import model.Trajectory;
import model.World;
import model.observations.Observation;
import model.observations.Scenario;

/**
 *  Uses JSolver CSP algorithm
 * 
 * @author Paul Givel and Guillaume Hartenstein
 */
public class BacktrackSolver extends Solver {
    private IlcSolver solver;
    private final Map<SysObject, Map<String, IlcAnyVar>> worldMap;
    private final List<IlcAnyVar> varList;

    public BacktrackSolver(World world, Scenario scenario) throws IloException {
        super(world, scenario);
        
        solver = new IlcSolver();
        worldMap = new HashMap<>();
        varList = new ArrayList<>();
        
        // Create all variables (one for each Object.property)
        for (int i = 0; i < world.getObjectCount(); i++) {
            SysObject object = world.getObjectAt(i);
            Map<String, IlcAnyVar> objectMap = new HashMap<>();
            worldMap.put(object, objectMap);
            for (int j = 0; j < object.getPropertyCount(); j++) {
                ObjectProperty property = object.getPropertyAt(i);
                List<String> listValues = property.getPossibleValues();
                IloAnyDomain propDomain = solver.anyDomain(listValues.toArray(new String[listValues.size()])); // Create a domain for every property
                IlcAnyVar var = solver.anyVar(propDomain, object.getName()+"."+property.getName());
                objectMap.put(property.getName(), var);
                varList.add(var);
            }
        }
    }

    @Override
    public List<Trajectory> solve() throws IloException {
        
        solver.add(scenario.get(0).solverConstraint(solver, worldMap));
        solver.newSearch(solver.generate(varList.toArray(new IlcAnyVar[varList.size()])));
        while (solver.next()) {
            System.out.println(varList);
        }
        /*for (Observation observation : scenario) {
            solver.add(observation.solverConstraint(solver, worldMap));
            solver.newSearch(solver.generate(varList.toArray(new IlcAnyVar[varList.size()])));
            //... TODO : get the results in some form + get all possible solutions
            solver = new IlcSolver(); // TODO : necessary ? or does newSearch() remove previous constraints ?
        }*/
        
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
