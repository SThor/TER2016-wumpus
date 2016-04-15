/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.general.componentModels;

import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import model.ObjectProperty;
import model.SysObject;

/**
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class SysObjectTreeModel extends DefaultTreeModel {
    private final List<SysObject> objects;
    
    public SysObjectTreeModel(List<SysObject> objects) {
        super(new DefaultMutableTreeNode());
        this.objects = objects;
    }

    @Override
    public boolean isLeaf(Object node) {
        return node instanceof ObjectProperty;
    }

    @Override
    public int getChildCount(Object parent) {
        if(parent == getRoot())
            return objects.size();
        
        if(parent instanceof SysObject)
            return ((SysObject)parent).getProperties().size();
        
        return 0;
    }

    @Override
    public Object getChild(Object parent, int index) {
       return parent == getRoot() ? 
               objects.get(index) : 
               ((SysObject)parent).getProperties().get(index);
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        return parent == getRoot() ? 
                objects.indexOf(child) : 
                ((SysObject)parent).getProperties().indexOf(child);
    }
    
    public void removeObject(SysObject toRemove) {
        int index = objects.indexOf(toRemove);
        objects.remove(index);
        fireTreeNodesRemoved(this, new Object[]{getRoot()}, new int[]{index}, new Object[]{toRemove});
    }
    
    public void removeProperty(SysObject parent, ObjectProperty toRemove) {
        int index = parent.getProperties().indexOf(toRemove);
        parent.getProperties().remove(index);
        fireTreeNodesRemoved(this, new Object[]{getRoot(), parent}, new int[]{index}, new Object[]{toRemove});
    }
    
    public void addObject(SysObject toAdd) {
        objects.add(toAdd);
        fireTreeNodesInserted(this, new Object[]{getRoot()}, new int[]{objects.size()-1}, new Object[]{toAdd});
    }

    public void addProperty(SysObject parent, ObjectProperty toAdd) {
        parent.getProperties().add(toAdd);
        int addIndex = parent.getProperties().size()-1;
        fireTreeNodesInserted(this, new Object[]{getRoot(), parent}, new int[]{addIndex}, new Object[]{toAdd});
    }
}