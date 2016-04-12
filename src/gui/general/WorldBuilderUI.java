package gui.general;

import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import model.Action;
import model.ObjectObservation;
import model.State;
import model.World;

/**
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class WorldBuilderUI extends javax.swing.JFrame {
    
    private final World world;
    private int objectIndex;
    private int actionIndex;
    private int stateIndex;
    private int preCondIndex;
    private int postCondIndex;
    private int observationIndex;
    
    /**
     * Creates new form WorldBuilderUI
     * @param world The world to work on
     */
    public WorldBuilderUI(World world) {
        this.world = world;
        objectIndex = -1;
        actionIndex = -1;
        stateIndex = -1;
        preCondIndex = -1;
        postCondIndex = -1;
        observationIndex = -1;
        
        initComponents();
        
        // In the observation table :
        
        // For each editable column, set the editor to correspond to abject states
        TableColumn column;
        Vector states;
        TableColumnModel obsColumnModel = tableObs.getColumnModel();
        for(int i=1; i<obsColumnModel.getColumnCount(); i++) {
            column = obsColumnModel.getColumn(i);
            states = new Vector(world.getObjects().get(i-1).getPossibleStates());
            states.add(0, State.UNDEFINED);
            column.setCellEditor(new DefaultCellEditor(new JComboBox(states)));
        }
        
        tableObs.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                tableObsValueChanged(e);
            }
        });
        
        // In condition table :
        
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
        
        setLocationRelativeTo(null);
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
        listStates = new javax.swing.JList<>();
        panelBtnStates = new javax.swing.JPanel();
        btnAddState = new javax.swing.JButton();
        btnRemState = new javax.swing.JButton();
        panelObjects = new javax.swing.JPanel();
        scrollListObjects = new javax.swing.JScrollPane();
        listObjects = new javax.swing.JList<>();
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
        scrollTableObs = new javax.swing.JScrollPane();
        tableObs = new javax.swing.JTable();
        panelBtnObs = new javax.swing.JPanel();
        btnAddObsAfter = new javax.swing.JButton();
        btnAddObsBefore = new javax.swing.JButton();
        btnMoveUp = new javax.swing.JButton();
        btnMoveDown = new javax.swing.JButton();
        btnRemObs = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("World Builder");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        splitTabObjects.setDividerLocation(150);

        panelStates.setLayout(new java.awt.BorderLayout());

        listStates.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Object states", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16))); // NOI18N
        listStates.setOpaque(false);
        listStates.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listStatesValueChanged(evt);
            }
        });
        scrollListProp.setViewportView(listStates);

        panelStates.add(scrollListProp, java.awt.BorderLayout.CENTER);

        btnAddState.setText("Add");
        panelBtnStates.add(btnAddState);

        btnRemState.setText("Remove");
        btnRemState.setEnabled(false);
        panelBtnStates.add(btnRemState);

        panelStates.add(panelBtnStates, java.awt.BorderLayout.PAGE_END);

        splitTabObjects.setRightComponent(panelStates);

        panelObjects.setLayout(new java.awt.BorderLayout());

        listObjects.setModel(new gui.general.WorldListModel(world.getObjects())
        );
        listObjects.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listObjects.setOpaque(false);
        listObjects.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listObjectsValueChanged(evt);
            }
        });
        scrollListObjects.setViewportView(listObjects);

        panelObjects.add(scrollListObjects, java.awt.BorderLayout.CENTER);

        btnAddObject.setText("Add");
        panelBtnObjects.add(btnAddObject);

        btnRemObject.setText("Remove");
        btnRemObject.setEnabled(false);
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
        panelBtnPost.add(btnRemPost);

        panelPost.add(panelBtnPost, java.awt.BorderLayout.SOUTH);

        panelLaws.add(panelPost);

        splitActionTab.setRightComponent(panelLaws);

        panelActions.setLayout(new java.awt.BorderLayout());

        listActions.setModel(new WorldListModel(world.getPossibleActions())
        );
        listActions.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
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
        panelBtnActions.add(btnRemAction);

        panelActions.add(panelBtnActions, java.awt.BorderLayout.PAGE_END);

        splitActionTab.setLeftComponent(panelActions);

        tabbedPane.addTab("Actions", splitActionTab);

        panelObservations.setLayout(new java.awt.BorderLayout());

        tableObs.setModel(new ObservationTableModel(world));
        tableObs.getTableHeader().setReorderingAllowed(false);
        scrollTableObs.setViewportView(tableObs);

        panelObservations.add(scrollTableObs, java.awt.BorderLayout.CENTER);

        btnAddObsAfter.setText("Add After");
        btnAddObsAfter.setEnabled(false);
        btnAddObsAfter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddObsAfterActionPerformed(evt);
            }
        });
        panelBtnObs.add(btnAddObsAfter);

        btnAddObsBefore.setText("Add Before");
        btnAddObsBefore.setEnabled(false);
        btnAddObsBefore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddObsBeforeActionPerformed(evt);
            }
        });
        panelBtnObs.add(btnAddObsBefore);

        btnMoveUp.setText("Move Up");
        btnMoveUp.setEnabled(false);
        btnMoveUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoveUpActionPerformed(evt);
            }
        });
        panelBtnObs.add(btnMoveUp);

        btnMoveDown.setText("Move Down");
        btnMoveDown.setEnabled(false);
        btnMoveDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoveDownActionPerformed(evt);
            }
        });
        panelBtnObs.add(btnMoveDown);

        btnRemObs.setText("Remove");
        btnRemObs.setEnabled(false);
        btnRemObs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemObsActionPerformed(evt);
            }
        });
        panelBtnObs.add(btnRemObs);

        panelObservations.add(panelBtnObs, java.awt.BorderLayout.PAGE_END);

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
            .addComponent(tabbedPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void listObjectsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listObjectsValueChanged
        objectIndex = listObjects.getSelectedIndex();
        if(objectIndex != -1) {
            btnRemObject.setEnabled(true);
            listStates.setModel(new WorldListModel(world.getObjects().get(objectIndex).getPossibleStates()));
        } else {
            btnRemObject.setEnabled(false);
            listStates.setModel(new DefaultListModel<String>());
        }
    }//GEN-LAST:event_listObjectsValueChanged

    private void listActionsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listActionsValueChanged
        actionIndex = listActions.getSelectedIndex();
        if(actionIndex != -1) {
            Action selected = world.getPossibleActions().get(actionIndex);
            btnRemAction.setEnabled(true);
            tablePreCond.setModel(new ConditionTableModel(selected.getPreConditions()));
            tablePostCond.setModel(new ConditionTableModel(selected.getPostConditions()));
        } else {
            btnRemAction.setEnabled(false);
            tablePreCond.setModel(new DefaultTableModel());
            tablePostCond.setModel(new DefaultTableModel());
        }
    }//GEN-LAST:event_listActionsValueChanged

    private void listStatesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listStatesValueChanged
        stateIndex = listStates.getSelectedIndex();
        btnRemState.setEnabled(stateIndex != -1);
    }//GEN-LAST:event_listStatesValueChanged

    private void btnAddObsAfterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddObsAfterActionPerformed
        observationIndex++;
        addObservationRow(observationIndex);
    }//GEN-LAST:event_btnAddObsAfterActionPerformed

    private void btnAddObsBeforeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddObsBeforeActionPerformed
        addObservationRow(observationIndex);
    }//GEN-LAST:event_btnAddObsBeforeActionPerformed

    private void btnRemObsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemObsActionPerformed
        ((ObservationTableModel)tableObs.getModel()).removeRow(observationIndex);
        observationIndex = -1;
        tableObs.clearSelection();
    }//GEN-LAST:event_btnRemObsActionPerformed

    private void btnMoveUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoveUpActionPerformed
        ((ObservationTableModel)tableObs.getModel()).moveUp(observationIndex);
        observationIndex--;
        tableObs.getSelectionModel().setSelectionInterval(observationIndex, observationIndex);
    }//GEN-LAST:event_btnMoveUpActionPerformed

    private void btnMoveDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoveDownActionPerformed
        ((ObservationTableModel)tableObs.getModel()).moveDown(observationIndex);
        observationIndex++;
        tableObs.getSelectionModel().setSelectionInterval(observationIndex, observationIndex);
    }//GEN-LAST:event_btnMoveDownActionPerformed

    private void tablePreCondValueChanged(ListSelectionEvent e) {
        preCondIndex = tablePreCond.getSelectedRow();
        btnRemPre.setEnabled(preCondIndex != -1);
    }

    private void tablePostCondValueChanged(ListSelectionEvent e) {
        postCondIndex = tablePostCond.getSelectedRow();
        btnRemPost.setEnabled(postCondIndex != -1);
    }
    
    private void tableObsValueChanged(ListSelectionEvent e) {
        observationIndex = tableObs.getSelectedRow();
        boolean hasSelection = observationIndex != -1;
        btnRemObs.setEnabled(hasSelection);
        btnAddObsAfter.setEnabled(hasSelection);
        btnAddObsBefore.setEnabled(hasSelection);
        btnMoveUp.setEnabled(hasSelection && observationIndex != 0);
        btnMoveDown.setEnabled(hasSelection && observationIndex != tableObs.getModel().getRowCount()-1);
    }
    
    private void addObservationRow(int index) {
        ((ObservationTableModel)tableObs.getModel()).addRow(observationIndex);
        tableObs.getSelectionModel().setSelectionInterval(observationIndex, observationIndex);
    } 
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddAction;
    private javax.swing.JButton btnAddObject;
    private javax.swing.JButton btnAddObsAfter;
    private javax.swing.JButton btnAddObsBefore;
    private javax.swing.JButton btnAddPost;
    private javax.swing.JButton btnAddPre;
    private javax.swing.JButton btnAddState;
    private javax.swing.JButton btnMoveDown;
    private javax.swing.JButton btnMoveUp;
    private javax.swing.JButton btnRemAction;
    private javax.swing.JButton btnRemObject;
    private javax.swing.JButton btnRemObs;
    private javax.swing.JButton btnRemPost;
    private javax.swing.JButton btnRemPre;
    private javax.swing.JButton btnRemState;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JList<String> listActions;
    private javax.swing.JList<String> listObjects;
    private javax.swing.JList<String> listStates;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JPanel panelActions;
    private javax.swing.JPanel panelBtnActions;
    private javax.swing.JPanel panelBtnObjects;
    private javax.swing.JPanel panelBtnObs;
    private javax.swing.JPanel panelBtnPost;
    private javax.swing.JPanel panelBtnPre;
    private javax.swing.JPanel panelBtnStates;
    private javax.swing.JPanel panelLaws;
    private javax.swing.JPanel panelObjects;
    private javax.swing.JPanel panelObservations;
    private javax.swing.JPanel panelPost;
    private javax.swing.JPanel panelPre;
    private javax.swing.JPanel panelStates;
    private javax.swing.JScrollPane scrollListActions;
    private javax.swing.JScrollPane scrollListObjects;
    private javax.swing.JScrollPane scrollListProp;
    private javax.swing.JScrollPane scrollTableObs;
    private javax.swing.JScrollPane scrollTablePost;
    private javax.swing.JScrollPane scrollTablePre;
    private javax.swing.JSplitPane splitActionTab;
    private javax.swing.JSplitPane splitTabObjects;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JTable tableObs;
    private javax.swing.JTable tablePostCond;
    private javax.swing.JTable tablePreCond;
    // End of variables declaration//GEN-END:variables
}
