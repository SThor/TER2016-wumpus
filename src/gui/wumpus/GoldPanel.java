package gui.wumpus;

import java.awt.Color;

/**
 *
 * @author silmathoron
 */
public class GoldPanel extends CellPanel {

    /**
     * Creates new form AgentPanel
     *
     * @param wumpusWorldPanel
     * @param goldX
     * @param goldY
     * @param isPickedUp
     */
    public GoldPanel(WumpusWorldPanel wumpusWorldPanel, int goldX, int goldY, boolean isPickedUp) {
        super(wumpusWorldPanel, goldX, goldY, "G");
        setAlt(1, Color.GREEN, Color.RED, "P", "P", isPickedUp);
    }
    
}
