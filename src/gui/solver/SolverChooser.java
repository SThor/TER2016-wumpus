/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.solver;

import gui.general.GeneralUI;
import model.World;
import model.observations.Scenario;
import solver.Solvers;
import solver.Solver;
import javax.swing.DefaultComboBoxModel;
import solver.ExhaustiveSolver;

/**
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class SolverChooser extends javax.swing.JDialog {

    private final Solver[] solvers;
    private Solver selected;

    /**
     * Creates new form SolverUI
     * @param world The world to operate on
     * @param scenario The scenario to operate on
     * @param parent The calling UI
     * @throws gui.solver.NoSolverException If no solvers are implemented in the solver package
     */ 
    public SolverChooser(World world, Scenario scenario, GeneralUI parent) throws NoSolverException {
        super(parent);
        this.solvers = new Solvers(world, scenario).getAvailableSolvers();
        if(solvers.length == 0) {
            throw new NoSolverException();
        }
        initComponents();
        
        cbSolversItemStateChanged(null);
        super.setLocationRelativeTo(parent);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelChoose = new javax.swing.JPanel();
        lblAlgo = new javax.swing.JLabel();
        cbSolvers = new javax.swing.JComboBox<>();
        scrollAreaInfo = new javax.swing.JScrollPane();
        areaInfo = new javax.swing.JTextArea();
        panelButtons = new javax.swing.JPanel();
        btnStart = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Solver chooser");

        lblAlgo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblAlgo.setText("Solving algorithm: ");
        panelChoose.add(lblAlgo);

        cbSolvers.setModel(new DefaultComboBoxModel<Solver>(solvers)
        );
        cbSolvers.setPreferredSize(new java.awt.Dimension(150, 20));
        cbSolvers.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbSolversItemStateChanged(evt);
            }
        });
        panelChoose.add(cbSolvers);

        getContentPane().add(panelChoose, java.awt.BorderLayout.NORTH);

        areaInfo.setEditable(false);
        areaInfo.setColumns(20);
        areaInfo.setFont(new java.awt.Font("Monospaced", 0, 11)); // NOI18N
        areaInfo.setLineWrap(true);
        areaInfo.setRows(5);
        areaInfo.setWrapStyleWord(true);
        areaInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Algorithm description", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        areaInfo.setOpaque(false);
        scrollAreaInfo.setViewportView(areaInfo);

        getContentPane().add(scrollAreaInfo, java.awt.BorderLayout.CENTER);

        btnStart.setText("Start solving");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });
        panelButtons.add(btnStart);

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        panelButtons.add(btnCancel);

        getContentPane().add(panelButtons, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        if(selected instanceof ExhaustiveSolver) {
            // Launch exhaustive solving interface
        }/* else if (selected instanceof ...) {
        
        }
        */
    }//GEN-LAST:event_btnStartActionPerformed

    private void cbSolversItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbSolversItemStateChanged
        selected = (Solver) cbSolvers.getSelectedItem();
        areaInfo.setText(selected.description());
    }//GEN-LAST:event_cbSolversItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaInfo;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnStart;
    private javax.swing.JComboBox<Solver> cbSolvers;
    private javax.swing.JLabel lblAlgo;
    private javax.swing.JPanel panelButtons;
    private javax.swing.JPanel panelChoose;
    private javax.swing.JScrollPane scrollAreaInfo;
    // End of variables declaration//GEN-END:variables
}
