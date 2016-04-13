/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.general.componentModels;

import javax.swing.table.AbstractTableModel;
import model.ObjectObservation;

/**
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
class ObjectObservationTableModel extends AbstractTableModel {
    private ObjectObservation observation;
    
    public ObjectObservationTableModel(ObjectObservation get) {
    }

    @Override
    public int getRowCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getColumnCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
