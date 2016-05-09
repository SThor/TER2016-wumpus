/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver;

import ilog.concert.IloAnyDomain;
import ilog.concert.IloException;
import ilog.solver.IlcAnyVar;
import ilog.solver.IlcConstraint;
import ilog.solver.IlcSolver;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Action;
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
        int i;
        // Add constraints for each observation => observational constraints
        for (i = 0; i < scenario.size(); i++) {
            solver.add(scenario.get(i).solverConstraint(solver, worldMaps.get(i)));
        }
        
        IlcConstraint transitionCons = null;
        // Add constraints for each transition (is there an action which allows the transition ?)
        // => transitional constraints
        for (i = 1; i < scenario.size(); i++) {
            IlcConstraint instantTransConstraint = null;
            for (Action action : world.getPossibleActions()) {
                IlcConstraint actionTransConstraint = action.transitionConstraint(solver, worldMaps.get(i-1), worldMaps.get(i));
                if (instantTransConstraint == null) {
                    instantTransConstraint = actionTransConstraint;
                } else {
                    instantTransConstraint = solver.or(instantTransConstraint, actionTransConstraint);
                }
                System.out.println(actionTransConstraint);
            }
            
            // TODO
            // For each variable, add the "unchanged" possibility
            /*for (int j = 1; j < varList.get(i-1).size(); j++) {
                List<IlcAnyVar> listBefore = varList.get(j-1);
                List<IlcAnyVar> listAfter = varList.get(j);
                for (int k = 0; k < listBefore.size(); k++) {
                    IlcConstraint unchanged = solver.imply(,);
                    if (instantTransConstraint == null) {
                    }
                }
                
            }*/
            
            if (transitionCons == null) {
                transitionCons = instantTransConstraint;
            } else {
                transitionCons = solver.and(transitionCons, instantTransConstraint);
            }
        }
        
        solver.add(transitionCons);
        
        List<IlcAnyVar> flatVarList = new ArrayList<>();
        for (List<IlcAnyVar> list : varList) {
            for (IlcAnyVar var : list) {
                flatVarList.add(var);
            }
        }
        
        solver.newSearch(solver.generate(flatVarList.toArray(new IlcAnyVar[flatVarList.size()])));
        while (solver.next()) {
            System.out.println(flatVarList);
        }
        solver.endSearch();
        
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
