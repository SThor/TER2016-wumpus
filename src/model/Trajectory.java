/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class Trajectory extends ArrayList<TrajectoryStep> {

    private Trajectory(Collection<? extends TrajectoryStep> c) {
        super(c);
    }
    
    public Trajectory(WorldState startPoint) {
        super();
        add(new TrajectoryStep(startPoint, null));
    }
    
    public Trajectory append(TrajectoryStep step) {
        Trajectory res =  new Trajectory(this);
        res.add(step);
        return res;
    }
    
    public TrajectoryStep last() {
        return get(size()-1);
    }
}
