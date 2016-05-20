package gui.wumpus;

import java.awt.Color;

/**
 *
 * @author silmathoron
 */
public class WumpusPanel extends CellPanel {

    /**
     * Creates new form AgentPanel
     *
     * @param wumpusWorldPanel
     * @param wumpusX
     * @param wumpusY
     * @param isAlive
     */
    public WumpusPanel(WumpusWorldPanel wumpusWorldPanel, int wumpusX, int wumpusY, boolean isAlive) {
        super(wumpusWorldPanel, wumpusX, wumpusY, "W");
        setAlt(1, Color.GREEN, Color.RED, "A", "D", isAlive);
    }
    
}
