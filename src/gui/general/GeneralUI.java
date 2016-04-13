package gui.general;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import model.Action;
import model.ObjectProperty;
import model.SysObject;
import model.World;

/**
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class GeneralUI extends javax.swing.JFrame {
    
    private int _object,
                _property,
                _propValue,
                _action,
                _preCond,
                _postCond,
                _observation,
                _instant;
                                

    private final World world;
    
    /**
     * Creates new form GeneralUI
     * @param world The world to work on
     */
    public GeneralUI(World world) {
        this.world = world;
        
        _object =_property =_propValue =_action =_preCond =_postCond =_observation = -1;
        
        initComponents();
        
        treeObjects.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        
        tablePreCond.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                tablePreCondValueChanged(e);
            }
        });
        
        tablePostCond.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                tablePostCondValueChanged(e);
            }
        });
        
        tableObservation.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                tableObservationValueChanged(e);
            }
        });
        
        super.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabbedPane = new javax.swing.JTabbedPane();
        splitTabObjects = new javax.swing.JSplitPane();
        panelStates = new javax.swing.JPanel();
        scrollListProp = new javax.swing.JScrollPane();
        listProperties = new javax.swing.JList<>();
        panelBtnStates = new javax.swing.JPanel();
        btnAddState = new javax.swing.JButton();
        btnRemProperty = new javax.swing.JButton();
        panelObjects = new javax.swing.JPanel();
        scrollPaneObjects = new javax.swing.JScrollPane();
        treeObjects = new javax.swing.JTree();
        panelBtnObjects = new javax.swing.JPanel();
        btnAddObject = new javax.swing.JButton();
        btnRemObject = new javax.swing.JButton();
        splitActionTab = new javax.swing.JSplitPane();
        panelLaws = new javax.swing.JPanel();
        panelPre = new javax.swing.JPanel();
        scrollTablePre = new javax.swing.JScrollPane();
        tablePreCond = new javax.swing.JTable();
        panelBtnPre = new javax.swing.JPanel();
        btnAddPre = new javax.swing.JButton();
        btnRemPre = new javax.swing.JButton();
        panelPost = new javax.swing.JPanel();
        scrollTablePost = new javax.swing.JScrollPane();
        tablePostCond = new javax.swing.JTable();
        panelBtnPost = new javax.swing.JPanel();
        btnAddPost = new javax.swing.JButton();
        btnRemPost = new javax.swing.JButton();
        panelActions = new javax.swing.JPanel();
        scrollListActions = new javax.swing.JScrollPane();
        listActions = new javax.swing.JList<>();
        panelBtnActions = new javax.swing.JPanel();
        btnAddAction = new javax.swing.JButton();
        btnRemAction = new javax.swing.JButton();
        panelObservations = new javax.swing.JPanel();
        sliderInstant = new javax.swing.JSlider();
        panelObservation = new javax.swing.JPanel();
        scrollTableObservation = new javax.swing.JScrollPane();
        tableObservation = new javax.swing.JTable();
        panelBtnObservation = new javax.swing.JPanel();
        btnAddObservation = new javax.swing.JButton();
        btnRemObservation = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        splitTabObjects.setDividerLocation(150);

        panelStates.setLayout(new java.awt.BorderLayout());

        listProperties.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Possible values", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16))); // NOI18N
        listProperties.setOpaque(false);
        listProperties.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listPropertiesValueChanged(evt);
            }
        });
        scrollListProp.setViewportView(listProperties);

        panelStates.add(scrollListProp, java.awt.BorderLayout.CENTER);

        btnAddState.setText("Add");
        panelBtnStates.add(btnAddState);

        btnRemProperty.setText("Remove");
        btnRemProperty.setEnabled(false);
        btnRemProperty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemPropertyActionPerformed(evt);
            }
        });
        panelBtnStates.add(btnRemProperty);

        panelStates.add(panelBtnStates, java.awt.BorderLayout.PAGE_END);

        splitTabObjects.setRightComponent(panelStates);

        panelObjects.setLayout(new java.awt.BorderLayout());

        treeObjects.setModel(new SysObjectTreeModel(world.getObjects()));
        treeObjects.setOpaque(false);
        treeObjects.setRootVisible(false);
        treeObjects.setShowsRootHandles(true);
        treeObjects.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                treeObjectsValueChanged(evt);
            }
        });
        scrollPaneObjects.setViewportView(treeObjects);

        panelObjects.add(scrollPaneObjects, java.awt.BorderLayout.CENTER);

        btnAddObject.setText("Add");
        panelBtnObjects.add(btnAddObject);

        btnRemObject.setText("Remove");
        btnRemObject.setEnabled(false);
        btnRemObject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemObjectActionPerformed(evt);
            }
        });
        panelBtnObjects.add(btnRemObject);

        panelObjects.add(panelBtnObjects, java.awt.BorderLayout.PAGE_END);

        splitTabObjects.setLeftComponent(panelObjects);

        tabbedPane.addTab("Objects", splitTabObjects);

        splitActionTab.setDividerLocation(150);

        panelLaws.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Action laws", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16))); // NOI18N
        panelLaws.setLayout(new java.awt.GridLayout(2, 1));

        panelPre.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pre-conditions", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        panelPre.setLayout(new java.awt.BorderLayout());

        tablePreCond.setModel(new DefaultTableModel());
        tablePreCond.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablePreCond.getTableHeader().setReorderingAllowed(false);
        scrollTablePre.setViewportView(tablePreCond);

        panelPre.add(scrollTablePre, java.awt.BorderLayout.CENTER);

        btnAddPre.setText("Add");
        panelBtnPre.add(btnAddPre);

        btnRemPre.setText("Remove");
        btnRemPre.setEnabled(false);
        btnRemPre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemPreActionPerformed(evt);
            }
        });
        panelBtnPre.add(btnRemPre);

        panelPre.add(panelBtnPre, java.awt.BorderLayout.SOUTH);

        panelLaws.add(panelPre);

        panelPost.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Post-conditions", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        panelPost.setLayout(new java.awt.BorderLayout());

        tablePostCond.setModel(new DefaultTableModel());
        tablePostCond.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablePostCond.getTableHeader().setReorderingAllowed(false);
        scrollTablePost.setViewportView(tablePostCond);

        panelPost.add(scrollTablePost, java.awt.BorderLayout.CENTER);

        btnAddPost.setText("Add");
        panelBtnPost.add(btnAddPost);

        btnRemPost.setText("Remove");
        btnRemPost.setEnabled(false);
        btnRemPost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemPostActionPerformed(evt);
            }
        });
        panelBtnPost.add(btnRemPost);

        panelPost.add(panelBtnPost, java.awt.BorderLayout.SOUTH);

        panelLaws.add(panelPost);

        splitActionTab.setRightComponent(panelLaws);

        panelActions.setLayout(new java.awt.BorderLayout());

        listActions.setModel(new WorldListModel(world.getPossibleActions())
        );
        listActions.setOpaque(false);
        listActions.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listActionsValueChanged(evt);
            }
        });
        scrollListActions.setViewportView(listActions);

        panelActions.add(scrollListActions, java.awt.BorderLayout.CENTER);

        btnAddAction.setText("Add");
        panelBtnActions.add(btnAddAction);

        btnRemAction.setText("Remove");
        btnRemAction.setEnabled(false);
        btnRemAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemActionActionPerformed(evt);
            }
        });
        panelBtnActions.add(btnRemAction);

        panelActions.add(panelBtnActions, java.awt.BorderLayout.PAGE_END);

        splitActionTab.setLeftComponent(panelActions);

        tabbedPane.addTab("Actions", splitActionTab);

        panelObservations.setLayout(new java.awt.BorderLayout());

        sliderInstant.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sliderInstant.setMaximum(world.getObservations().size()-1);
        sliderInstant.setPaintLabels(true);
        sliderInstant.setPaintTicks(true);
        sliderInstant.setSnapToTicks(true);
        sliderInstant.setToolTipText("");
        sliderInstant.setValue(0);
        sliderInstant.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Instant", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        panelObservations.add(sliderInstant, java.awt.BorderLayout.NORTH);

        panelObservation.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Observation", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        panelObservation.setLayout(new java.awt.BorderLayout());

        tableObservation.setModel(new DefaultTableModel());
        tableObservation.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableObservation.getTableHeader().setReorderingAllowed(false);
        scrollTableObservation.setViewportView(tableObservation);

        panelObservation.add(scrollTableObservation, java.awt.BorderLayout.CENTER);

        btnAddObservation.setText("Add");
        panelBtnObservation.add(btnAddObservation);

        btnRemObservation.setText("Remove");
        btnRemObservation.setEnabled(false);
        panelBtnObservation.add(btnRemObservation);

        panelObservation.add(panelBtnObservation, java.awt.BorderLayout.SOUTH);

        panelObservations.add(panelObservation, java.awt.BorderLayout.CENTER);

        tabbedPane.addTab("Observations", panelObservations);

        jMenu1.setText("File");
        menuBar.add(jMenu1);

        jMenu2.setText("World");
        menuBar.add(jMenu2);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void listPropertiesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listPropertiesValueChanged
        _propValue = listProperties.getSelectedIndex();
        btnRemProperty.setEnabled(_propValue != -1);
    }//GEN-LAST:event_listPropertiesValueChanged

    private void listActionsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listActionsValueChanged
        _action = listActions.getSelectedIndex();
        
        if(_action != -1) {
            tablePreCond.setModel(new ConditionTableModel(world.getActionAt(_action).getPreConditions()));
            tablePostCond.setModel(new ConditionTableModel(world.getActionAt(_action).getPostConditions()));
            btnRemAction.setEnabled(true);
        } else {
            tablePreCond.setModel(new DefaultTableModel());
            tablePostCond.setModel(new DefaultTableModel());
            btnRemAction.setEnabled(false);
        }
    }//GEN-LAST:event_listActionsValueChanged

    private void treeObjectsValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_treeObjectsValueChanged
        Object selected = null;
        Object parent = null;
        
        _propValue = -1;
        
        try {
            TreePath path = treeObjects.getSelectionPath();
            selected = path.getLastPathComponent();
            parent = path.getParentPath().getLastPathComponent();
            
        } catch(NullPointerException e) { // If caught, nothing is selected in the JTree
            _object = -1;
            _property = -1;
            listProperties.setModel(new DefaultListModel<String>());
            btnRemObject.setEnabled(false);
            return;
        }
        
        TreeModel treeModel = treeObjects.getModel();
        
        // A SysObject has been selected
        if(selected instanceof SysObject) {
            _object = treeModel.getIndexOfChild(parent, selected);
            _property = -1;
            listProperties.setModel(new DefaultListModel<String>());
            
        } else { // An ObjectProperty has been selected
            _object = treeModel.getIndexOfChild(treeModel.getRoot(), parent);
            _property = treeModel.getIndexOfChild(parent, selected);
            listProperties.setModel(new WorldListModel(world.getObjectAt(_object).getProperties().get(_property).getPossibleValues()));
        }
        
        // In both case
        btnRemObject.setEnabled(true);
    }//GEN-LAST:event_treeObjectsValueChanged

    private void btnRemObjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemObjectActionPerformed
        if(_property != -1)
            removeProperty();
        else
            removeObject();
    }//GEN-LAST:event_btnRemObjectActionPerformed

    private void btnRemPropertyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemPropertyActionPerformed
        SysObject obj = world.getObjectAt(_object);
        ObjectProperty prop = obj.getProperties().get(_property);
        String propValue = prop.getPossibleValues().get(_propValue);
        String message = "Delete value \""+ propValue +"\" from property \""+ prop +"\" in object \""+ obj +"\" ?";
        int result = JOptionPane.showConfirmDialog(this, message, "Deletion confirmation", JOptionPane.YES_NO_OPTION);
        if(result == JOptionPane.YES_OPTION)
            ((WorldListModel)listProperties.getModel()).removeElement(_propValue);
    }//GEN-LAST:event_btnRemPropertyActionPerformed

    private void btnRemActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemActionActionPerformed
        Action act = world.getActionAt(_action);
        String message = "Delete action \""+ act +"\" from world ?";
        int result = JOptionPane.showConfirmDialog(this, message, "Deletion confirmation", JOptionPane.YES_NO_OPTION);
        if(result == JOptionPane.YES_OPTION)
            ((WorldListModel)listActions.getModel()).removeElement(_action);
    }//GEN-LAST:event_btnRemActionActionPerformed

    private void btnRemPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemPreActionPerformed
        Action act = world.getActionAt(_action);
        String message = "Delete selected pre-condition from action \""+ act +"\" ?";
        int result = JOptionPane.showConfirmDialog(this, message, "Deletion confirmation", JOptionPane.YES_NO_OPTION);
        if(result == JOptionPane.YES_OPTION)
            ((ConditionTableModel)tablePreCond.getModel()).removeRow(_preCond);
    }//GEN-LAST:event_btnRemPreActionPerformed

    private void btnRemPostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemPostActionPerformed
        Action act = world.getActionAt(_action);
        String message = "Delete selected post-condition from action \""+ act +"\" ?";
        int result = JOptionPane.showConfirmDialog(this, message, "Deletion confirmation", JOptionPane.YES_NO_OPTION);
        if(result == JOptionPane.YES_OPTION)
            ((ConditionTableModel)tablePostCond.getModel()).removeRow(_postCond);
    }//GEN-LAST:event_btnRemPostActionPerformed

    private void tablePreCondValueChanged(ListSelectionEvent e) {
        _preCond = tablePreCond.getSelectedRow();
        btnRemPre.setEnabled(_preCond != -1);
    }

    private void tablePostCondValueChanged(ListSelectionEvent e) {
        _postCond = tablePostCond.getSelectedRow();
        btnRemPost.setEnabled(_postCond != -1);
    }

    private void tableObservationValueChanged(ListSelectionEvent e) {
        _observation = tablePostCond.getSelectedRow();
        btnRemObservation.setEnabled(_observation != -1);
    }
    
    private void removeProperty() {
        SysObject obj = world.getObjectAt(_object);
        String message = "Delete property \""+ obj.getProperties().get(_property) +"\" from object \""+ obj +"\" ?";
        int result = JOptionPane.showConfirmDialog(this, message, "Deletion confirmation", JOptionPane.YES_NO_OPTION);
        if(result == JOptionPane.YES_OPTION)
            ((SysObjectTreeModel)treeObjects.getModel()).removeProperty(_object, _property);
    }

    private void removeObject() {
        String message = "Delete object \""+ world.getObjectAt(_object) +"\" from world ?";
        int result = JOptionPane.showConfirmDialog(this, message, "Deletion confirmation", JOptionPane.YES_NO_OPTION);
        if(result == JOptionPane.YES_OPTION)
            ((SysObjectTreeModel)treeObjects.getModel()).removeObject(_object);
    }
    
    // <editor-fold defaultstate="collapsed" desc="Variable declarations">  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddAction;
    private javax.swing.JButton btnAddObject;
    private javax.swing.JButton btnAddObservation;
    private javax.swing.JButton btnAddPost;
    private javax.swing.JButton btnAddPre;
    private javax.swing.JButton btnAddState;
    private javax.swing.JButton btnRemAction;
    private javax.swing.JButton btnRemObject;
    private javax.swing.JButton btnRemObservation;
    private javax.swing.JButton btnRemPost;
    private javax.swing.JButton btnRemPre;
    private javax.swing.JButton btnRemProperty;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JList<String> listActions;
    private javax.swing.JList<String> listProperties;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JPanel panelActions;
    private javax.swing.JPanel panelBtnActions;
    private javax.swing.JPanel panelBtnObjects;
    private javax.swing.JPanel panelBtnObservation;
    private javax.swing.JPanel panelBtnPost;
    private javax.swing.JPanel panelBtnPre;
    private javax.swing.JPanel panelBtnStates;
    private javax.swing.JPanel panelLaws;
    private javax.swing.JPanel panelObjects;
    private javax.swing.JPanel panelObservation;
    private javax.swing.JPanel panelObservations;
    private javax.swing.JPanel panelPost;
    private javax.swing.JPanel panelPre;
    private javax.swing.JPanel panelStates;
    private javax.swing.JScrollPane scrollListActions;
    private javax.swing.JScrollPane scrollListProp;
    private javax.swing.JScrollPane scrollPaneObjects;
    private javax.swing.JScrollPane scrollTableObservation;
    private javax.swing.JScrollPane scrollTablePost;
    private javax.swing.JScrollPane scrollTablePre;
    private javax.swing.JSlider sliderInstant;
    private javax.swing.JSplitPane splitActionTab;
    private javax.swing.JSplitPane splitTabObjects;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JTable tableObservation;
    private javax.swing.JTable tablePostCond;
    private javax.swing.JTable tablePreCond;
    private javax.swing.JTree treeObjects;
    // End of variables declaration//GEN-END:variables
    // </editor-fold> 
}
