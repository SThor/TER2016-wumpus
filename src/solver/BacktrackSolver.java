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
    private final List<Map<SysObject, Map<String, IlcAnyVar>>> worldMaps;
    private final List<List<IlcAnyVar>> varList;

    public BacktrackSolver(World world, Scenario scenario) throws IloException {
        super(world, scenario);
        
        solver = new IlcSolver();
        worldMaps = new ArrayList<>();
        varList = new ArrayList<>();
        
        // Create all variables (one for each Object.property at each instant)
        for (int k = 0; k < scenario.size(); k++) {
            Map<SysObject, Map<String, IlcAnyVar>> mapAtInstant = new HashMap<>();
            worldMaps.add(mapAtInstant);
            List<IlcAnyVar> listAtInstant = new ArrayList<>();
            varList.add(listAtInstant);
            for (int i = 0; i < world.getObjectCount(); i++) {
                SysObject object = world.getObjectAt(i);
                Map<String, IlcAnyVar> objectMap = new HashMap<>();
                mapAtInstant.put(object, objectMap);
                for (int j = 0; j < object.getPropertyCount(); j++) {
                    ObjectProperty property = object.getPropertyAt(j);
                    List<String> listValues = property.getPossibleValues();
                    IloAnyDomain propDomain = solver.anyDomain(listValues.toArray(new String[listValues.size()])); // Create a domain for every property
                    IlcAnyVar var = solver.anyVar(propDomain, k+"_"+object.getName()+"."+property.getName());
                    objectMap.put(property.getName(), var);
                    listAtInstant.add(var);
                }
            }
        }
    }

    @Override
    public List<Trajectory> solve() throws IloException {
        // Add constraints for each observations
        for (int i = 0; i < scenario.size(); i++) {
            solver.add(scenario.get(i).solverConstraint(solver, worldMaps.get(i)));
        }
        
        return null;
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
