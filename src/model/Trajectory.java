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
public class Trajectory implements Comparable<Trajectory>{
    private int changesCount;
    private ArrayList<TrajectoryStep> list;
    
    private Trajectory(Collection<? extends TrajectoryStep> c, int changesCount) {
        list = new ArrayList<>(c);
        this.changesCount = changesCount;
    }
    
    public Trajectory(WorldState startPoint) {
        list = new ArrayList<>();
        list.add(new TrajectoryStep(startPoint, null));
        changesCount = 0;
    }
    
    public Trajectory append(TrajectoryStep step) {
        Trajectory res =  new Trajectory(list, changesCount);
        res.add(step);
        return res;
    }
    
    public TrajectoryStep last() {
        return list.get(list.size()-1);
    }

    public ArrayList<TrajectoryStep> getList() {
        return list;
    }
    
    public int size() {
        return list.size();
    }
    
    public TrajectoryStep get(int index) {
        return list.get(index);
    }
    
    public void add(TrajectoryStep e) {
        WorldState last = last().getState();
        list.add(e);
        for (int i = 0; i < last.size(); i++) {
            if (!last.get(i).equals(e.getState().get(i))) {
                changesCount++;
            }
        }
    }

    public int getChangesCount() {
        return changesCount;
    }
    
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (TrajectoryStep step : list) {
            str.append(step);
            str.append('\n');
        }
        return str.toString();
    }
    
    @Override
    public int compareTo(Trajectory o) {
        return changesCount - o.changesCount;
    }
}
