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
public class Trajectory extends ArrayList<WorldState>{

    private Trajectory(Collection<? extends WorldState> c) {
        super(c);
    }
    
    public Trajectory(WorldState startPoint) {
        super();
        add(startPoint);
    }
    
    public Trajectory append(WorldState point) {
        Trajectory res =  new Trajectory(this);
        res.add(point);
        return res;
    }
    
    public WorldState last() {
        return get(size()-1);
    }
}
