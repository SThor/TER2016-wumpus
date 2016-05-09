/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class TrajectoryStep {
    private final WorldState state;
    private final Action action;

    public TrajectoryStep(WorldState state, Action action) {
        this.state = state;
        this.action = action;
    }

    public WorldState getState() {
        return state;
    }

    public Action getAction() {
        return action;
    }

    @Override
    public String toString() {
        return action+" => "+state;
    }
}
