package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.observations.Scenario;

/**
 * Object used to generate a world for the wumpus problem, with a few fixed and
 * a few variable parameters
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class WumpusWorld {

//    private int wumpusQty = 1;
//    private int pitQty = 1;
//    private int goldQty = 1;
//    private List<Point> wumpusPosition = new ArrayList<>();
//    private List<Point> pitPosition = new ArrayList<>();
//    private List<Point> goldPosition = new ArrayList<>();
    private Point wumpusPosition;
    private Point pitPosition;
    private Point goldPosition;
    private Point agentPosition;
    private int height = 2;
    private int width = 3;
    private final UniqueList<String> trueFalseList = new UniqueList<>();
    private World wumpusWorld = new World();
    private SysObject agent = new SysObject("agent", wumpusWorld);
    private SysObject wumpus = new SysObject("wumpus", wumpusWorld);
    private SysObject pit = new SysObject("pit", wumpusWorld);
    private SysObject gold = new SysObject("gold", wumpusWorld);

    public WumpusWorld() {
        trueFalseList.add("true");
        trueFalseList.add("false");
    }

    public void setAgentPosition(Point agentPosition) {
        this.agentPosition = agentPosition;
    }

    public void setDimensions(int width, int height) {
        this.height = height;
        this.width = width;
    }

    public void setWumpusPosition(Point wumpusPosition) {
        this.wumpusPosition = wumpusPosition;
    }

    public void setPitPosition(Point pitPosition) {
        this.pitPosition = pitPosition;
    }

    public void setGoldPosition(Point goldPosition) {
        this.goldPosition = goldPosition;
    }

    public World generateWorld() {
        generateAgent();
        generateWumpus();
        generatePit();
        generateGold();
        generateActions();

        return wumpusWorld;
    }

    public Scenario generateScenario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void generateAgent() {
        ObjectProperty propertyHasGold = new ObjectProperty(trueFalseList, "hasGold");
        agent.addProperty(propertyHasGold);

        agent.addProperty(generateXPositionProperty());
        agent.addProperty(generateYPositionProperty());

        ObjectProperty propertyIsAlive = new ObjectProperty(trueFalseList, "isAlive");
        agent.addProperty(propertyIsAlive);

        ObjectProperty propertyHasShot = new ObjectProperty(trueFalseList, "hasShot");
        agent.addProperty(propertyHasShot);

        wumpusWorld.addObject(agent);
    }

    private void generateWumpus() {
        wumpus.addProperty(generateXPositionProperty());
        wumpus.addProperty(generateYPositionProperty());

        ObjectProperty propertyIsAlive = new ObjectProperty(trueFalseList, "isAlive");
        wumpus.addProperty(propertyIsAlive);

        wumpusWorld.addObject(wumpus);
    }

    private void generatePit() {
        pit.addProperty(generateXPositionProperty());
        pit.addProperty(generateYPositionProperty());

        ObjectProperty propertyIsFull = new ObjectProperty(trueFalseList, "isFull");
        pit.addProperty(propertyIsFull);

        wumpusWorld.addObject(pit);
    }

    private void generateGold() {
        gold.addProperty(generateXPositionProperty());
        gold.addProperty(generateYPositionProperty());

        ObjectProperty propertyIsPickedUp = new ObjectProperty(trueFalseList, "isPickedUp");
        gold.addProperty(propertyIsPickedUp);

        wumpusWorld.addObject(gold);
    }

    private ObjectProperty generateXPositionProperty() {
        ObjectProperty xPositionProperty = new ObjectProperty("xPosition");
        for (int i = 0; i < width; i++) {
            xPositionProperty.addPossibleValue("" + i);
        }
        return xPositionProperty;
    }

    private ObjectProperty generateYPositionProperty() {
        ObjectProperty yPositionProperty = new ObjectProperty("yPosition");
        for (int i = 0; i < height; i++) {
            yPositionProperty.addPossibleValue("" + i);
        }
        return yPositionProperty;
    }

    private void generateActions() {
        addMoveAgent();
        addMoveWumpus();
        addShootWumpus();
//TODO: make it so that it is adjacent instead of on the same case.
//        wumpusWorld.addPossibleAction(fallAgent);
//        wumpusWorld.addPossibleAction(fallWumpus);
//        wumpusWorld.addPossibleAction(eatAgent);
//        wumpusWorld.addPossibleAction(pickUpGold);
    }

    private void addMoveAgent() {
        Action moveAgent = new Action("moveAgent");
        moveAgent.addPreCondition(new PropertyValue(agent, "isAlive", "true"));
        wumpusWorld.addPossibleAction(moveAgent);
    }

    private void addMoveWumpus() {
        Action moveWumpus = new Action("moveWumpus");
        moveWumpus.addPreCondition(new PropertyValue(wumpus, "isAlive", "true"));
        wumpusWorld.addPossibleAction(moveWumpus);
    }

    private void addShootWumpus() {
        Action shootWumpus = new Action("shootWumpus");
        shootWumpus.addPreCondition(new PropertyValue(agent, "isAlive", "true"));
        shootWumpus.addPreCondition(new PropertyValue(agent, "hasShot", "false"));
        shootWumpus.addPreCondition(new Equality(agent, "xPosition", wumpus, "xPosition"));
        shootWumpus.addPreCondition(new Equality(agent, "yPosition", wumpus, "yPosition"));
        shootWumpus.addPostCondition(new PropertyValue(wumpus, "isAlive", "false"));
        shootWumpus.addPostCondition(new PropertyValue(agent, "hasShot", "true"));
        wumpusWorld.addPossibleAction(shootWumpus);
    }
}
