package gui.wumpus;

/**
 *
 * @author silmathoron
 */
public class PitPanel extends CellPanel {

    /**
     * Creates new PitPanel
     *
     * @param wumpusWorldPanel
     * @param pitX
     * @param pitY
     */
    public PitPanel(WumpusWorldPanel wumpusWorldPanel, int pitX, int pitY) {
        super(wumpusWorldPanel, pitX, pitY, "P");
    }
    
}
