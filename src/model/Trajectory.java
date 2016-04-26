/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class Trajectory {
    private final List<WorldState> trajectory;
    
    public Trajectory(WorldState startPoint) {
        trajectory = new ArrayList<>();
        trajectory.add(startPoint);
    }
    
    public void addPoint(WorldState point) {
        trajectory.add(point);
    }
}
