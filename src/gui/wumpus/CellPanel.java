/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.wumpus;

import java.awt.Dimension;
import java.awt.Graphics2D;

/**
 *
 * @author silmathoron
 */
public class CellPanel extends javax.swing.JPanel {
    Graphics2D g2;
     WumpusWorldPanel wumpusWorldPanel;
    int mainX;
    int mainY;
    
    int mainFontSize;
    int altFontSize;
    int shiftXCenter;
    int shiftYCenter;
    int shiftX1;
    int shiftY1;
    int shiftX2;
    int shiftY2;
    int shiftX3;
    int shiftY3;
    
    /**
     * Creates new form CellPanel
     */
    public CellPanel(WumpusWorldPanel wumpusWorldPanel, int mainX, int mainY) {
        this.wumpusWorldPanel = wumpusWorldPanel;
        this.mainX = mainX;
        this.mainY = mainY;
        setPreferredSize(new Dimension(wumpusWorldPanel.getCellWidth(), wumpusWorldPanel.getCellHeight()));
        computeConstants();
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void computeConstants() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
