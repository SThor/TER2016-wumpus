package gui.wumpus;

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultBoundedRangeModel;
import model.Trajectory;
import model.TrajectoryStep;
import model.WorldState;
import model.WumpusWorld;

/**
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class WumpusWorldFrame extends javax.swing.JFrame {
    private List<WorldState> stateList;
    
    /**
     * Creates new form WumpusWorldFrame
     */
    public WumpusWorldFrame(WumpusWorld wumpusWorld) {
        initComponents();        
        sliderInstant.setLabelTable(sliderInstant.createStandardLabels(1));
        wumpusWorldPanel.setWumpusWorld(wumpusWorld);
    }
    
    public void setTrajectory(Trajectory trajectory){
        stateList = new ArrayList<>();
        for (TrajectoryStep trajectoryStep : trajectory.getList()) {
            WorldState state = trajectoryStep.getState();
            if (!stateList.contains(state)) {
                stateList.add(state);
            }
        }
        wumpusWorldPanel.setStateList(stateList);
        sliderInstant.setMaximum(stateList.size());
        sliderInstant.setModel(new DefaultBoundedRangeModel(0, 1, 0, stateList.size()));        
        sliderInstant.setLabelTable(sliderInstant.createStandardLabels(1));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelInstant = new javax.swing.JPanel();
        wumpusWorldPanel = new gui.wumpus.WumpusWorldPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Wumpus World");

        panelInstant.setLayout(new java.awt.BorderLayout());

        sliderInstant.setMaximum(0);
        sliderInstant.setPaintLabels(true);
        sliderInstant.setPaintTicks(true);
        sliderInstant.setSnapToTicks(true);
        sliderInstant.setModel(new DefaultBoundedRangeModel(0,0,0,1));
        sliderInstant.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderInstantStateChanged(evt);
            }
        });
        panelInstant.add(sliderInstant, java.awt.BorderLayout.CENTER);

        getContentPane().add(panelInstant, java.awt.BorderLayout.SOUTH);

        javax.swing.GroupLayout wumpusWorldPanelLayout = new javax.swing.GroupLayout(wumpusWorldPanel);
        wumpusWorldPanel.setLayout(wumpusWorldPanelLayout);
        wumpusWorldPanelLayout.setHorizontalGroup(
            wumpusWorldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        wumpusWorldPanelLayout.setVerticalGroup(
            wumpusWorldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 269, Short.MAX_VALUE)
        );

        getContentPane().add(wumpusWorldPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sliderInstantStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderInstantStateChanged
        wumpusWorldPanel.setInstant(sliderInstant.getValue());
    }//GEN-LAST:event_sliderInstantStateChanged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panelInstant;
    private final javax.swing.JSlider sliderInstant = new javax.swing.JSlider();
    private gui.wumpus.WumpusWorldPanel wumpusWorldPanel;
    // End of variables declaration//GEN-END:variables
}
