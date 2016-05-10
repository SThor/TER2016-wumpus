/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.solver;

import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JPanel;

/**
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class LinePanel extends JPanel {
    public static final int TOP_LEFT = 0,
                            TOP = 1,
                            TOP_RIGHT = 2,
                            RIGHT = 3,
                            BOTTOM_RIGHT = 4,
                            BOTTOM = 5,
                            BOTTOM_LEFT = 6,
                            LEFT = 7,
                            CENTER = 8;
    
    private Integer startPoint;
    private Integer endPoint;
    
    public LinePanel(Integer startPoint, Integer endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (startPoint != null && endPoint != null) {
            Point start = determinePoint(startPoint);
            Point end = determinePoint(endPoint);
            g.drawLine((int)start.getX(), (int)start.getY(), (int)end.getX(), (int)end.getY());
            g.drawOval(getWidth()/2, getHeight()/2, 10, 10);
        }
    }
    
    private Point determinePoint(int pos) {
        switch(pos) {
            case TOP_LEFT:      return new Point(0,0);
            case TOP:           return new Point(getWidth()/2, 0);
            case TOP_RIGHT:     return new Point(getWidth(), 0);
            case RIGHT:         return new Point(getWidth(), getHeight()/2);
            case BOTTOM_RIGHT:  return new Point(getWidth(), getHeight());
            case BOTTOM:        return new Point(getWidth()/2, getHeight());
            case BOTTOM_LEFT:   return new Point(0, getHeight());
            case LEFT:          return new Point(0, getHeight()/2);
            case CENTER:        return new Point(getWidth()/2, getHeight()/2);
            default:
                throw new IllegalArgumentException("Not a valide point: "+pos);
        }
    }
    
    public void setStartPoint(Integer startPoint) {
        this.startPoint = startPoint;
    }
    
    public void setEndPoint(Integer endPoint) {
        this.endPoint = endPoint;
    }
    
    public static int opposite(int point) {
        switch(point) {
            case TOP_LEFT:      return BOTTOM_RIGHT;
            case TOP:           return BOTTOM;
            case TOP_RIGHT:     return BOTTOM_LEFT;
            case RIGHT:         return LEFT;
            case BOTTOM_RIGHT:  return TOP_LEFT;
            case BOTTOM:        return TOP;
            case BOTTOM_LEFT:   return TOP_RIGHT;
            case LEFT:          return RIGHT;
            case CENTER:        return CENTER;
            default:
                throw new IllegalArgumentException("Not a valide point: "+point);
        }
    }
    
    public int getStartPoint() {
        return startPoint;
    }
    
    public int getEndPoint() {
        return endPoint;
    }
}
