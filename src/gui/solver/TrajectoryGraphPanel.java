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
import javax.swing.JTextArea;
import javax.swing.border.Border;
import model.Action;
import model.Trajectory;
import model.TrajectoryStep;
import model.WorldState;

/**
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class TrajectoryGraphPanel extends JPanel {
    private final Trajectory trajectory;
    private final List<TrajectoryStep>[] matrix;
    private final boolean showActions;

    public TrajectoryGraphPanel(Trajectory trajectory, boolean showActions) {
        this.trajectory = trajectory;
        matrix = new ArrayList[trajectory.size()];
        this.showActions = showActions;
        
        List<WorldState> stateList = new ArrayList<>();
        List<TrajectoryStep> steps = new ArrayList<>();
        for (int i = 0; i < trajectory.size(); i++) {
            TrajectoryStep step = trajectory.get(i);
            WorldState state = step.getState();
            if (!stateList.contains(state)) {
                stateList.add(state);
                steps.add(step);
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = steps;
        }
        
        initLayout();
    }                        

    private void initLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH; // Streching
        c.weightx = 1; // Resize weight on x axis
        c.weighty = 1; // Resize weight on y axis
        c.anchor = GridBagConstraints.CENTER; // Component display mode
        c.ipadx = 20; // Padding on x axis
        c.ipady = 20; // Padding on y axis
        
        // Add a blank panel at the top-left corner
        c.gridx = 0;
        c.gridy = 0;
        JLabel lblChangeCount = new JLabel(" Number of changes: "+trajectory.getChangesCount());
        lblChangeCount.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.black));
        add(lblChangeCount, c);
        
        JLabel label;
        Font labelFont = new Font("SansSerif", Font.PLAIN, 12);
        Border noLeftBorder = BorderFactory.createMatteBorder(1, 0, 1, 1, Color.black);
        // Add the instants labels (top row)
        for (int i = 0; i < matrix.length; i++) {
            c.gridx = i+1;
            label = new JLabel("Instant "+i);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setBorder(noLeftBorder);
            label.setFont(labelFont);
            add(label, c);
        }
        
        Border noTopBorder = BorderFactory.createMatteBorder(0, 1, 1, 1, Color.black);
        JTextArea textArea;
        String text;
        // Add the states labels (left column)
        c.gridx = 0;
        for (TrajectoryStep step : matrix[0]) {
            c.gridy++;
            text = step.getState().toString();
            if (showActions && step.getAction() != null) {
                text += "\n(Action: "+ step.getAction() +")";
            }
            textArea = new JTextArea(text);
            textArea.setEditable(false);
            textArea.setWrapStyleWord(true);
            textArea.setBorder(noTopBorder);
            textArea.setOpaque(false);
            textArea.setFont(labelFont);
            add(textArea, c);
        }
        
        // Array content (draw the matrix)
        c.gridx = 1;
        for (int i = 0; i < matrix.length; i++) {
            List<TrajectoryStep> allSteps = matrix[i];
            c.gridy = 1;
            for (TrajectoryStep step : allSteps) {
                TrajectoryMatrixPanel panel = new TrajectoryMatrixPanel(step.getState().equals(trajectory.get(i).getState()));
                add(panel, c);
                c.gridy++;
            }
            c.gridx++;
        }
    }            
}
