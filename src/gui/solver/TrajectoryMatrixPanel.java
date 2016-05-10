/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.solver;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author Guillaume Hartenstein and Paul Givel
 */
public class TrajectoryMatrixPanel extends JPanel {
    private final boolean isFilled;
    
    public TrajectoryMatrixPanel(boolean isFilled) {
        this.isFilled = isFilled;
        super.setPreferredSize(new Dimension(30, 30));
        super.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.black));
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isFilled) {
            int diameter, x, y;
            diameter = (getWidth() + getHeight()) / 15;
            x = (getWidth() - diameter) / 2;
            y = (getHeight() - diameter) / 2;
            g.setColor(Color.black);
            g.fillOval(x, y, diameter, diameter);
        }
    }
}
