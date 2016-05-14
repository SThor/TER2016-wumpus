/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.solver;

import gui.ImageExporter;
import ilog.concert.IloException;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.Trajectory;
import solver.BacktrackSolver;

/**
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class BacktrackSolverResults extends javax.swing.JFrame {
    private Integer _solution;
    private final List<Trajectory> trajectories;

    /**
     * Creates new form BacktrackSolverResults
     * @param solver
     * @throws ilog.concert.IloException
     */
    public BacktrackSolverResults(BacktrackSolver solver) throws IloException {
        trajectories = solver.solve();
        _solution = 0;
        
        initComponents();
        
        for (Trajectory trajectory : trajectories) {
            TrajectoryGraphPanel graphPane = new TrajectoryGraphPanel(trajectory, solver.getInstantCount());
            panelTrajectoryGraph.add(_solution.toString(), new JScrollPane(graphPane));
        }
        
        if (panelTrajectoryGraph.getComponentCount() > 0) {
            panelTrajectoryGraph.setPreferredSize(panelTrajectoryGraph.getComponent(0).getPreferredSize());
        } else {
            panelTrajectoryGraph.setPreferredSize(new Dimension(50, 50));
        }
        
        btnNext.setEnabled(!trajectories.isEmpty());
        btnPrevious.setEnabled(!trajectories.isEmpty());
        
        super.setLocationRelativeTo(null);
        super.pack();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        panelGraphics = new javax.swing.JPanel();
        panelNav = new javax.swing.JPanel();
        btnPrevious = new javax.swing.JButton();
        lblNavInfo = new javax.swing.JLabel();
        btnNext = new javax.swing.JButton();
        panelTrajectoryGraph = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        miSaveAsJPG = new javax.swing.JMenuItem();
        miSaveAsPNG = new javax.swing.JMenuItem();
        miSaveAsGIF = new javax.swing.JMenuItem();

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Backtrack algorithm results");
        setMinimumSize(new java.awt.Dimension(315, 100));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        panelGraphics.setLayout(new java.awt.BorderLayout());

        panelNav.setMinimumSize(new java.awt.Dimension(315, 35));
        panelNav.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 30, 5));

        btnPrevious.setText("Previous");
        btnPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousActionPerformed(evt);
            }
        });
        panelNav.add(btnPrevious);

        lblNavInfo.setText("Solution  "
            +(trajectories.isEmpty() ? _solution : _solution+1)
            +" / "
            +trajectories.size());
        panelNav.add(lblNavInfo);

        btnNext.setText("Next");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        panelNav.add(btnNext);

        panelGraphics.add(panelNav, java.awt.BorderLayout.NORTH);

        panelTrajectoryGraph.setPreferredSize(new java.awt.Dimension(0, 0));
        panelTrajectoryGraph.setLayout(new java.awt.CardLayout());
        panelGraphics.add(panelTrajectoryGraph, java.awt.BorderLayout.CENTER);

        getContentPane().add(panelGraphics, java.awt.BorderLayout.CENTER);

        menuFile.setText("File");

        miSaveAsJPG.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        miSaveAsJPG.setText("Save as JPG");
        miSaveAsJPG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miSaveAsJPGActionPerformed(evt);
            }
        });
        menuFile.add(miSaveAsJPG);

        miSaveAsPNG.setText("Save as PNG");
        miSaveAsPNG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miSaveAsPNGActionPerformed(evt);
            }
        });
        menuFile.add(miSaveAsPNG);

        miSaveAsGIF.setText("Save as GIF");
        miSaveAsGIF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miSaveAsGIFActionPerformed(evt);
            }
        });
        menuFile.add(miSaveAsGIF);

        menuBar.add(menuFile);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousActionPerformed
        _solution--;
        if (_solution < 0) {
            _solution = trajectories.size()-1;
        }
        setNavInfo();
        ((CardLayout)panelTrajectoryGraph.getLayout()).previous(panelTrajectoryGraph);
        panelTrajectoryGraph.setPreferredSize(panelTrajectoryGraph.getComponent(_solution).getPreferredSize());
    }//GEN-LAST:event_btnPreviousActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        _solution++;
        _solution %= trajectories.size();
        setNavInfo();
        ((CardLayout)panelTrajectoryGraph.getLayout()).next(panelTrajectoryGraph);
        panelTrajectoryGraph.setPreferredSize(panelTrajectoryGraph.getComponent(_solution).getPreferredSize());
    }//GEN-LAST:event_btnNextActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        int count = trajectories.size();
        JOptionPane.showMessageDialog(this, 
                "The solving terminated successfully\n"
              + count 
              + (count == 1 ? " solution was found." : " solutions were found."),
                "Solver information",
                JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_formWindowOpened

    private void miSaveAsPNGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miSaveAsPNGActionPerformed
        ImageExporter.saveImage(this, panelTrajectoryGraph, "png");
    }//GEN-LAST:event_miSaveAsPNGActionPerformed

    private void miSaveAsGIFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miSaveAsGIFActionPerformed
        ImageExporter.saveImage(this, panelTrajectoryGraph, "png");
    }//GEN-LAST:event_miSaveAsGIFActionPerformed

    private void miSaveAsJPGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miSaveAsJPGActionPerformed
        ImageExporter.saveImage(this, panelTrajectoryGraph, "png");
    }//GEN-LAST:event_miSaveAsJPGActionPerformed

    
    private void setNavInfo() {
        lblNavInfo.setText("Solution  "+(_solution+1)+" / "+trajectories.size());
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrevious;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel lblNavInfo;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenuItem miSaveAsGIF;
    private javax.swing.JMenuItem miSaveAsJPG;
    private javax.swing.JMenuItem miSaveAsPNG;
    private javax.swing.JPanel panelGraphics;
    private javax.swing.JPanel panelNav;
    private javax.swing.JPanel panelTrajectoryGraph;
    // End of variables declaration//GEN-END:variables
}
