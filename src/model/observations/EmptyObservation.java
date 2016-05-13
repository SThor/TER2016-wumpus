/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.observations;

import ilog.solver.IlcAnyVar;
import ilog.solver.IlcConstraint;
import ilog.solver.IlcSolver;
import java.util.Map;
import model.SysObject;
import model.WorldState;

/**
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class EmptyObservation implements Observation {

    @Override
    public boolean isVerified() {
        return true;
    }

    @Override
    public boolean isVerifiedIn(WorldState state) {
        return true;
    }

    @Override
    public IlcConstraint solverConstraint(IlcSolver solver, Map<SysObject, Map<String, IlcAnyVar>> worldMap) {
        return null;
    }
    
    @Override
    public String toString() {
        return "";
    }
}
