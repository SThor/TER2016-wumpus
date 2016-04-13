/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.general;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Condition;

/**
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
class ConditionTableModel extends AbstractTableModel {
    private final List<Condition> conditions;
    
    public ConditionTableModel(List<Condition> conditions) {
        this.conditions = conditions;
    }

    @Override
    public int getRowCount() {
        return conditions.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Condition c = conditions.get(rowIndex);
        switch(columnIndex) {
            case 0: return c.getObject();
            case 1: return c.getPropertyName();
            case 2: return c.getWantedValue();
            default:
                throw new RuntimeException("Unexpected behavior.");
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0: return "Object";
            case 1: return "Property";
            case 2: return "Value";
            default:
                throw new RuntimeException("Unexpected behavior.");
        }
    }
    
    public void removeRow(int index) {
        conditions.remove(index);
        fireTableRowsDeleted(index, index);
    }
}
