package gui.wumpus;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import model.PropertyValue;
import model.SysObject;
import model.WorldState;
import model.WumpusWorld;

/**
 *
 * @author silmathoron
 */
public class WumpusWorldPanel extends javax.swing.JPanel {

    private List<WorldState> stateList;
    private WumpusWorld wumpusWorld;
    private int instant = 0;
    private Integer xAgent;
    private Integer yAgent;
    private boolean hasGold;
    private boolean hasShot;
    private boolean agentAlive;
    private Integer xWumpus;
    private Integer yWumpus;
    private boolean wumpusAlive;
    private Integer xGold;
    private Integer yGold;
    private Integer xPit;
    private Integer yPit;
    private JPanel[][] panelHolder;
    private boolean isPickedUp;

    /**
     * Creates new tempPanel
     */
    public WumpusWorldPanel() {
        initComponents();
    }

    public void setWumpusWorld(WumpusWorld wumpusWorld) {
        this.wumpusWorld = wumpusWorld;
        initComponents();
    }

    public void setInstant(int instant) {
        this.instant = instant;
        initComponents();
        repaint();
    }

    public void setStateList(List<WorldState> stateList) {
        this.stateList = stateList;
        initComponents();
    }

    private void initComponents() {
        if (wumpusWorld != null) {
            int worldHeight = wumpusWorld.getHeight();
            int worldWidth = wumpusWorld.getWidth();
            setLayout(new GridLayout(worldHeight, worldWidth));
            panelHolder = new JPanel[worldHeight][worldWidth];
            for (int i = 0; i < worldHeight; i++) {
                for (int j = 0; j < worldWidth; j++) {
                    panelHolder[i][j] = new JPanel();
                    panelHolder[i][j].setBorder(new LineBorder(Color.BLACK));
                    add(panelHolder[i][j]);
                }
            }
            if (stateList != null) {
                WorldState worldState = stateList.get(instant);
                for (PropertyValue propertyValue : worldState) {
                    SysObject object = propertyValue.getObject();
                    String propertyName = propertyValue.getPropertyName();
                    Integer wantedValue = propertyValue.getWantedValue();
                    if (object.equals(wumpusWorld.getAgent())) {
                        switch (propertyName) {
                            case "xPosition":
                                xAgent = wantedValue;
                                break;
                            case "yPosition":
                                yAgent = wantedValue;
                                break;
                            case "hasGold":
                                hasGold = (wantedValue == 1);
                                break;
                            case "hasShot":
                                hasShot = (wantedValue == 1);
                                break;
                            case "isAlive":
                                agentAlive = (wantedValue == 1);
                        }
                    } else if (object.equals(wumpusWorld.getWumpus())) {
                        switch (propertyName) {
                            case "xPosition":
                                xWumpus = wantedValue;
                                break;
                            case "yPosition":
                                yWumpus = wantedValue;
                                break;
                            case "isAlive":
                                wumpusAlive = (wantedValue == 1);
                        }
                    } else if (object.equals(wumpusWorld.getGold())) {
                        switch (propertyName) {
                            case "xPosition":
                                xGold = wantedValue;
                                break;
                            case "yPosition":
                                yGold = wantedValue;
                                break;
                            case "isPickedUp":
                                isPickedUp = (wantedValue == 1);
                        }
                    } else if (object.equals(wumpusWorld.getPit())) {
                        switch (propertyName) {
                            case "xPosition":
                                xPit = wantedValue;
                                break;
                            case "yPosition":
                                yPit = wantedValue;
                                break;
                        }
                    }
                }
                panelHolder[xAgent][yAgent].add(new AgentPanel(this, xAgent, yAgent, agentAlive, hasGold, hasShot));
                panelHolder[xWumpus][yWumpus].add(new WumpusPanel(this, xWumpus, yWumpus, wumpusAlive));
                panelHolder[xGold][yGold].add(new GoldPanel(this, xGold, yGold, isPickedUp));
                panelHolder[xPit][yPit].add(new PitPanel(this, xPit, yPit));
            }
        }
    }

}
