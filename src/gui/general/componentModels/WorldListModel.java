/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.general.componentModels;

import java.util.List;
import javax.swing.AbstractListModel;

/**
 *
 * @author Paul Givel and Guillaume Hartenstein
 * @param <E> Type of list elements
 */
public class WorldListModel<E> extends AbstractListModel {
    private List<E> list;
    
    public WorldListModel(List<E> list) {
        this.list = list;
    }

    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public Object getElementAt(int index) {
        return list.get(index);
    }
    
    public void removeElement(E toRemove) {
        int index = list.indexOf(toRemove);
        list.remove(toRemove);
        fireIntervalRemoved(this, index, index);
    }
    
    public void addElement(E toAdd) {
        list.add(toAdd);
        int index = list.size()-1;
        fireIntervalAdded(this, index, index);
    }
    
    public void setData(List<E> list) {
        this.list = list;
        fireContentsChanged(this, 0, list.size());
    }
    
    public void triggerUpdate() {
        fireContentsChanged(this, 0, list.size());
    }
}
