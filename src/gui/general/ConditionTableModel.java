/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.general;

import javax.swing.table.AbstractTableModel;
import model.Condition;
import model.State;
import model.SysObject;
import model.UniqueList;

/**
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
class ConditionTableModel extends AbstractTableModel {
    private final UniqueList<Condition> conditions;
    
    public ConditionTableModel(UniqueList<Condition> conditions) {
        this.conditions = conditions;
    }

    @Override
    public int getRowCount() {
        return conditions.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Condition c = conditions.get(rowIndex);
        switch(columnIndex) {
            case 0: return c.getObject();
            case 1: return c.getState();
            default:
                throw new RuntimeException("Unexpected behavior");
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0: return "Object";
            case 1: return "State";
            default:
                throw new RuntimeException("Unexpected behavior");
        }
    }
}
