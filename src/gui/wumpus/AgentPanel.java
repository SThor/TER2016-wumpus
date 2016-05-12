package gui.wumpus;

import java.awt.Color;

/**
 *
 * @author silmathoron
 */
public class AgentPanel extends CellPanel {

    /**
     * Creates new form AgentPanel
     *
     * @param wumpusWorldPanel
     * @param agentX
     * @param agentY
     * @param isAlive
     * @param hasGold
     * @param hasShot
     */
    public AgentPanel(WumpusWorldPanel wumpusWorldPanel, int agentX, int agentY, boolean isAlive, boolean hasGold, boolean hasShot) {
        super(wumpusWorldPanel, agentX, agentY, "A");
        setAlt(1, Color.GREEN, Color.RED, "A", "D", isAlive);
        setAlt(2, Color.YELLOW, Color.GRAY, "G", "G", hasGold);
        setAlt(3, Color.GREEN, Color.GRAY, "B", "B", !hasShot);
    }
    
}
