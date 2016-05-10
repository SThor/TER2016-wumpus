/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.solver;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
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
        JPanel blank = new JPanel();
        blank.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.black));
        add(blank, c);
        
        Border noLeftBorder = BorderFactory.createMatteBorder(1, 0, 1, 1, Color.black);
        JLabel label;
        
        // Add the instants labels (top row)
        for (int i = 0; i < matrix.length; i++) {
            c.gridx = i+1;
            label = new JLabel("Instant "+i);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setBorder(noLeftBorder);
            add(label, c);
        }
        
        Border noTopBorder = BorderFactory.createMatteBorder(0, 1, 1, 1, Color.black);
        
        // Add the states labels (left column)
        c.gridx = 0;
        for (WorldState state : matrix[0]) {
            c.gridy++;
            label = new JLabel(state.toString());
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setBorder(noTopBorder);
            add(label, c);
        }
        
        // Array content (draw the matrix)
        c.gridx = 1;
        for (int i = 0; i < matrix.length; i++) {
            List<WorldState> allStates = matrix[i];
            c.gridy = 1;
            for (WorldState state : allStates) {
                add(new TrajectoryMatrixPanel(state.equals(trajectory.get(i).getState())), c);
                c.gridy++;
            }
            c.gridx++;
        }
    }            
}
