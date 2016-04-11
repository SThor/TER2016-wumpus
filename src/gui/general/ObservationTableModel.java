package gui.general;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.ObjectObservation;
import model.State;
import model.SysObject;
import model.World;

/**
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class ObservationTableModel extends AbstractTableModel {
    private final List<ObjectObservation> observations;
    private final List<SysObject> objects;
    private final World world;
    
    public ObservationTableModel(World world) {
        observations = world.getObservations();
        objects = world.getObjects();
        this.world = world;
    }
    
    @Override
    public int getRowCount() {
        return observations.size();
    }

    @Override
    public int getColumnCount() {
        return objects.size()+1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex == 0) {
            return rowIndex; // Instant
        } else {
            ObjectObservation obs = observations.get(rowIndex);
            SysObject object = objects.get(columnIndex-1);
            return obs.getObservedState(object);
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        ObjectObservation obs = observations.get(rowIndex);
        SysObject object = objects.get(columnIndex-1);
        obs.setObservedState(object, (State)aValue);
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex != 0;
    }

    @Override
    public String getColumnName(int column) {
        return column == 0 ? "Instant" : objects.get(column-1).toString();
    }
    
    public void addRow(int index) {
        world.addObservation(index, new ObjectObservation(world));
        fireTableRowsInserted(index, index);
    }
    
    public void removeRow(int index) {
        world.removeObservation(index);
        fireTableRowsDeleted(index, index);
    }
    
    public void moveUp(int index) {
        world.moveObservationUp(index);
        fireTableRowsUpdated(index-1, index);
    }
    
    public void moveDown(int index) {
        world.moveObservationDown(index);
        fireTableRowsUpdated(index, index+1);
    }
}
