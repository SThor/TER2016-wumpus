/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.general;

import java.util.List;
import javax.swing.AbstractListModel;

/**
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class WorldListModel extends AbstractListModel {
    private final List<?> list;
    
    public WorldListModel(List<?> list) {
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
}
