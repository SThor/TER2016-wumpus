/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.solver;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Trajectory;
import model.TrajectoryStep;
import model.WorldState;

/**
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class TrajectoryGraphPanel extends JPanel {
    private final Trajectory trajectory;
    private final List<WorldState>[] matrix;

    public TrajectoryGraphPanel(Trajectory trajectory, int instantCount) {
        this.trajectory = trajectory;
        matrix = new ArrayList[instantCount];
        
        List<WorldState> stateList = new ArrayList<>();
        for (TrajectoryStep trajectoryStep : trajectory) {
            WorldState state = trajectoryStep.getState();
            if (!stateList.contains(state)) {
                stateList.add(state);
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = stateList;
        }
        
        initLayout();
    }                        

    private void initLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.CENTER;
        
        // Add a blank panel at the top-left corner
        c.gridx = 0;
        c.gridy = 0;
        add(new JPanel(), c);
        
        // Add the instants labels (top row)
        for (int i = 0; i < matrix.length; i++) {
            c.gridx = i+1;
            add(new JLabel("Instant "+i), c);
        }
        
        // Add the states labels (left column)
        c.gridx = 0;
        for (WorldState state : matrix[0]) {
            c.gridy++;
            add(new JLabel(state.toString()), c);
        }
        
        List<LinePanel> panels = new ArrayList<>();
        
        // Array content (draw the matrix)
        c.gridx = 1;
        for (List<WorldState> list : matrix) {
            c.gridy = 1;
            for (WorldState state : list) {
                LinePanel toAdd = new LinePanel(null, null);
                panels.add(toAdd);
                add(toAdd, c);
                c.gridy++;
            }
            c.gridx++;
        }
        
        // Draw lines
        if (panels.size() > 1) {
            panels.get(0).setStartPoint(LinePanel.CENTER);
            for (int i = 1; i < panels.size() - 1; i++) {
                //TODO
            }
            
            LinePanel last = panels.get(panels.size()-1);
            LinePanel secondToLast = panels.get(panels.size()-2);
            last.setStartPoint(LinePanel.opposite(secondToLast.getEndPoint()));
            last.setEndPoint(LinePanel.CENTER);
        }
    }            
}
